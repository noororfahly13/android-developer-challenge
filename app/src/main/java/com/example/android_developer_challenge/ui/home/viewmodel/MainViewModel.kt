package com.example.android_developer_challenge.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android_developer_challenge.base.BaseViewModel
import com.example.android_developer_challenge.repository.GithubUserRepository
import com.example.entity.entities.GithubUser
import com.example.network.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import kotlin.coroutines.CoroutineContext

class MainViewModel constructor(
    private val githubUserRepository: GithubUserRepository,
    private val context: CoroutineContext = Dispatchers.Main
) : BaseViewModel() {

    var githubUsersResource: MutableLiveData<Resource<ArrayList<GithubUser>>> = MutableLiveData()

    init {
    }

    fun searchGithubUsers(query: String) {

        viewModelScope.launch(context = context) {
            collect(githubUserRepository.searchGithubUsers(query)) { result ->
                githubUsersResource.value = result
                result.data?.let { users ->
                    Timber.v("Got results ${users.size}")
                    for (user in users)
                        Timber.v("user ${user.login}")
                }
            }

        }
    }
}