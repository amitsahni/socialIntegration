package com.twitterconnect

import android.app.Activity
import com.twitter.sdk.android.core.TwitterCore

/**
 * Created by amit on 10/2/17.
 */

class Builder {


    fun login(activity: Activity): RequestBuilder.LoginBuilder {
        return RequestBuilder.LoginBuilder(activity)
    }

    fun logOut() {
        TwitterCore.getInstance().sessionManager.clearActiveSession()
    }

    fun profile(): RequestBuilder.ProfileBuilder {
        return RequestBuilder.ProfileBuilder()
    }
}
