package com.example.kotlinflows.part5.data

data class User(
    val username: String = "",
    val description: String = "",
    val profilePicUrl: String = ""
)

data class Post(
    val imageUrl: String = "",
    val username: String = "",
    val description: String = "",
)

data class ProfileState(
    val profilePicUrl: String = "",
    val username: String = "",
    val description: String = "",
    val posts: List<Post> = emptyList()
)
