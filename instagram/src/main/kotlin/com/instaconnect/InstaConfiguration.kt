package com.instaconnect

import android.app.Application
import webconnect.com.webconnect.ApiConfiguration

object InstaConfiguration {

    internal var clientId: String = ""
    internal var redirectUrl: String = ""
    private var isDebug: Boolean = true

    @JvmStatic
    fun clientId(clientId: String, redirectUrl: String): InstaConfiguration {
        this.clientId = clientId
        this.redirectUrl = redirectUrl
        return this
    }

    fun isDebug(debug: Boolean): InstaConfiguration {
        isDebug = debug
        return this
    }

    fun config(context: Application) {
        ApiConfiguration.Builder(context)
                .baseUrl("https://api.instagram.com/v1/")
                .debug(isDebug)
                .config()
    }
}