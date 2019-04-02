package com.instaconnect

import android.content.Context

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

}