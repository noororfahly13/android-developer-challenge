package com.example.network.resources

import com.example.network.vo.Resource
import com.example.network.vo.api.ApiEmptyResponse
import com.example.network.vo.api.ApiErrorResponse
import com.example.network.vo.api.ApiResponse
import com.example.network.vo.api.ApiSuccessResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType>(private val withProgress: Boolean = true) {

    private var result: Flow<Resource<ResultType>>

    init {
        result = flow<Resource<ResultType>> {

            if (withProgress)
                emit(Resource.loading(null))

            val localData = loadFromDb()

            emit(Resource.success(localData))

            if (shouldFetch(localData)) {

                val apiResponse: ApiResponse<RequestType>

                val call = createCall().first()
                apiResponse = call ?: ApiErrorResponse("Unknown error")


                apiResponse.let {
                    when (it) {
                        is ApiSuccessResponse -> {

                            val resultData = processResponse(it)
                            saveCallResult(resultData)

                            emit(Resource.success(loadFromDb(), isLastPage(resultData)))
                        }
                        is ApiEmptyResponse -> {
                            emit(Resource.success(loadFromDb()))
                        }
                        is ApiErrorResponse -> {

                            onFetchFailed()

                            emit(
                                Resource.error(
                                    it.errorMessage,
                                    localData,
                                    it.code,
                                    it.rawResponse
                                )
                            )
                        }
                    }
                }


            }

        }.catch {
            emit(Resource.error("", null, -1, null))
        }.flowOn(Dispatchers.Default)
    }


    protected open fun isLastPage(item: RequestType): Boolean {
        return false
    }

    protected abstract fun onFetchFailed()


    fun asFlowData() = result


    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body


    protected abstract suspend fun saveCallResult(item: RequestType)


    protected abstract fun shouldFetch(data: ResultType?): Boolean


    protected abstract suspend fun loadFromDb(): ResultType


    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>?>
}
