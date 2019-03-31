package com.twitterconnect.controller

import com.twitter.sdk.android.core.models.User
import com.twitterconnect.controller.WebEndPoint.PROFILE

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Created by amit on 10/2/17.
 */

interface TwitterService {

    @GET(PROFILE)
    fun show(@QueryMap map: Map<String, String>): Call<User>
}
