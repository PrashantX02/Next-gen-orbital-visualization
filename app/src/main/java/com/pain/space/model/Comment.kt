package com.pain.space.model

data class Comment(
    var uid: String = "",
    var username: String = "",
    var timeAgo: String = "",
    var commentText: String = "",

    // Emotion drawable resources
    var chk: Int = 0,
    var confused: Int = 0,
    var cool: Int = 0,
    var angry: Int = 0,
    var facebookReactions: Int = 0,
    var sad: Int = 0,
    var sad2: Int = 0,
    var neutral: Int = 0,
    var science: Int = 0,
    var einstein: Int = 0,
    var sportCar: Int = 0,
    var startup: Int = 0,
    var chemistry: Int = 0,
    var galaxy: Int = 0,
    var angryBirds: Int = 0,
    var tesla: Int = 0,
    var gravity: Int = 0,
    var isaacNewton: Int = 0,
    var astronaut: Int = 0,
    var planet: Int = 0
)


