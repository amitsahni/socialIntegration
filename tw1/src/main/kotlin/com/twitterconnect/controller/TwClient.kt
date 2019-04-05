package com.twitterconnect.controller

import com.twitter.sdk.android.core.TwitterApiClient
import com.twitter.sdk.android.core.TwitterSession

import okhttp3.OkHttpClient

/**
 * Created by amit on 10/2/17.
 */

class TwClient : TwitterApiClient {

    /**
     * Provide CustomService with defined endpoints
     */
    val twitterService: TwitterService
        get() = getService(TwitterService::class.java)

    constructor(client: OkHttpClient) : super(client) {}

    constructor(session: TwitterSession, client: OkHttpClient) : super(session, client) {}
}
