package com.example.kotlinflows.part5

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinflows.part5.data.Post
import com.example.kotlinflows.part5.data.ProfileState
import com.example.kotlinflows.part5.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import kotlin.math.log

private const val TAG = "MainViewModel5"
class MainViewModel5 : ViewModel() {
    val numberString = "5"
    private val isAuthenticated = MutableStateFlow(true)

    private val user = MutableStateFlow<User?>(null)
    private val posts = MutableStateFlow(emptyList<Post>())

    val profileState = user.combine(posts) { user: User?, posts: List<Post> ->
        Log.d(TAG, "user.combine(posts) : ")
        ProfileState(
            profilePicUrl = user?.profilePicUrl ?: "",
            username = user?.username ?: "",
            description = user?.description ?: "",
            posts = posts
        )
    }

    init {
        isAuthenticated.combine(profileState) { isAuthenticated, ProfileState ->
            Log.d(TAG, "isAuthenticated.combine(profileState) : ")
            if (isAuthenticated) {
                ProfileState
            } else {
                null
            }
        }.launchIn(viewModelScope)

        isAuthenticated.zip(profileState) { isAuthenticated, ProfileState ->
            Log.d(TAG, "isAuthenticated.combine(profileState) : ")
            if (isAuthenticated) {
                ProfileState
            } else {
                null
            }
        }.launchIn(viewModelScope)

       // merge()
    }
}