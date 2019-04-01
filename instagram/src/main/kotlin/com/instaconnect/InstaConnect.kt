package com.instaconnect

import android.app.Application
import android.content.Context
import webconnect.com.webconnect.ApiConfiguration

object InstaConnect {


    /**
     * With builder.
     *
     * @param context the context
     * @return the builder
     */
    @JvmStatic
    fun with(): Builder {
        return Builder()

    }

    @JvmStatic
    fun isAlreadyLogin(context: Context): Boolean {
        val pref = P.defaultPrefs(context)
        val token = pref["access_token", ""]
        return !token.isBlank()
    }

    @JvmStatic
    fun accessToken(context: Context): String {
        val pref = P.defaultPrefs(context)
        return pref["access_token", ""]
    }

    @JvmStatic
    fun isDebug(context: Application) {
        ApiConfiguration.Builder(context)
                .baseUrl("https://api.instagram.com/v1/")
                .debug(true)
                .config()
    }
}