package com.twitterconnect

import android.content.Intent
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.identity.TwitterAuthClient

object TwitterConnect {

    @JvmStatic
    val twitterAuthClient: TwitterAuthClient = TwitterAuthClient()

    @JvmStatic
    val session: TwitterSession?
        get() {
            return TwitterCore.getInstance()
                    .sessionManager.activeSession
        }

    @JvmStatic
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        twitterAuthClient.onActivityResult(requestCode, resultCode, data)
    }

    @JvmStatic
    fun with(): Builder {
        return Builder()

    }
}