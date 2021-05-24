package com.example.android_developer_challenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.android_developer_challenge.domain.GithubUserDomain
import com.example.android_developer_challenge.repository.GithubUserRepository
import com.example.entity.database.GithubUserDao
import com.example.entity.entities.GithubUser
import com.example.network.vo.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class GithubUserRepositoryTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = BaseCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val githubUserDao: GithubUserDao = mock(GithubUserDao::class.java)
    private val githubUserDomain: GithubUserDomain = mock(GithubUserDomain::class.java)
    private lateinit var githubUserRepository: GithubUserRepository


    @Before
    fun setup() {
        githubUserRepository =
            GithubUserRepository(githubUserDao, githubUserDomain)
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGithubUsers() = runBlocking {
        val loadState = Resource.loading(null)
        val dataState = Resource.success(arrayListOf<GithubUser>())

        `when`(githubUserDomain.searchGithubUsers("Jack")).thenReturn(flow {
            emit(loadState)
            emit(dataState)
        })

        val flow = githubUserRepository.searchGithubUsers("Jack")


        val listCollect = arrayListOf<Resource<ArrayList<GithubUser>>>()
        flow.collect {
            listCollect.add(it)
        }

        assertEquals(listCollect[0], loadState)
        assertEquals(listCollect[1], dataState)

    }
}