package com.twitterconnect.controller

/**
 * Created by amit on 4/2/17.
 */

object WebEndPoint {
    val USER_ID_KEY = "user_id"
    private val BASE_URL = "https://api.twitter.com/"
    const val VERSION = "1.1/"

    const val PROFILE = "/" + VERSION + "users/show.json"
    const val FRIENDS = "/" + VERSION + "friends/list.json"

    internal var PROFILE_TASK_ID = 1
}
