package com.instaconnect

import android.content.Context

class Builder {

    fun login(context: Context, clientId: String, redirectUrl: String): RequestBuilder.LoginBuilder {
        return RequestBuilder.LoginBuilder(context, clientId, redirectUrl)
    }

    fun logout(context: Context) {
        val pref = P.defaultPrefs(context)
        pref["access_token"] = ""
    }

    fun profile(context: Context): RequestBuilder.ProfileBuilder {
        return RequestBuilder.ProfileBuilder(context)
    }
}