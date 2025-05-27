package com.pain.space.model

data class Comment(
    val username: String,
    val timeAgo: String,
    val commentText: String,

    // Emotion drawable resources
    val chk: Int,
    val confused: Int,
    val cool: Int,
    val angry: Int,
    val facebookReactions: Int,
    val sad: Int,
    val sad2: Int,
    val neutral: Int,
    val science: Int,
    val einstein: Int,
    val sportCar: Int,
    val startup: Int,
    val chemistry: Int,
    val galaxy: Int,
    val angryBirds: Int,
    val tesla: Int,
    val gravity: Int,
    val isaacNewton: Int,
    val astronaut: Int,
    val planet: Int
)

