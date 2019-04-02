package com.instaconnect

import android.content.Context

class Builder {

    fun login(context: Context): RequestBuilder.LoginBuilder {
        return RequestBuilder.LoginBuilder(context)
    }

    fun logout(context: Context) {
        val pref = P.defaultPrefs(context)
        pref["access_token"] = ""
    }

    fun profile(context: Context): RequestBuilder.ProfileBuilder {
        return RequestBuilder.ProfileBuilder(context)
    }
}