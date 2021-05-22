package com.example.android_developer_challenge.ui.details.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android_developer_challenge.base.BaseViewModel
import com.example.android_developer_challenge.repository.GithubUserRepository
import com.example.entity.entities.GithubUser
import com.example.network.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailsViewModel constructor(
    private val githubUserRepository: GithubUserRepository,
    private val context: CoroutineContext = Dispatchers.Main
) : BaseViewModel() {

    var githubUserResource: MutableLiveData<Resource<GithubUser>> = MutableLiveData()
    var name: MutableLiveData<String> = MutableLiveData()
    var login: MutableLiveData<String> = MutableLiveData()
    var bio: MutableLiveData<String> = MutableLiveData()
    var image: MutableLiveData<String> = MutableLiveData()
    val company: MutableLiveData<String> = MutableLiveData()
    val location: MutableLiveData<String> = MutableLiveData()
    val email: MutableLiveData<String> = MutableLiveData()
    val publicRepos: MutableLiveData<String> = MutableLiveData()
    val followersCount: MutableLiveData<String> = MutableLiveData()
    val followingCount: MutableLiveData<String> = MutableLiveData()

    init {
    }

    fun fetchGithubUser(loginVal: String) {

        viewModelScope.launch(context = context) {
            collect(githubUserRepository.fetchGithubUser(loginVal)) { result ->
                githubUserResource.value = result
                if (result.data != null && result.data is GithubUser) {
                    val user = result.data as GithubUser
                    name.value = user.name
                    login.value = user.login
                    bio.value = user.bio
                    image.value = user.avatar_url
                    company.value = user.company
                    location.value = user.location
                    email.value = user.email
                    publicRepos.value = user.publicRepos?.toString()
                    followersCount.value = user.followersCount?.toString()
                    followingCount.value = user.followingCount?.toString()
                }
            }

        }
    }
}