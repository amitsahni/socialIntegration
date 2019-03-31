package com.twitterconnect

import android.content.Context
import android.util.Log

import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig

import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by clickapps on 18/9/17.
 */

object TwitterConfiguration {
    var loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
    private var consumerKey: String = ""
    private var secretKey: String = ""
    private var isDebug: Boolean = true

    @JvmStatic
    fun keys(consumerKey: String, secretKey: String): TwitterConfiguration {
        this.consumerKey = consumerKey
        this.secretKey = secretKey
        return this
    }

    fun isDebug(isDebug: Boolean): TwitterConfiguration {
        this.isDebug = isDebug
        return this
    }

    fun config(context: Context) {
        val builder = TwitterConfig.Builder(context)
        builder.debug(isDebug)
        builder.logger(DefaultLogger(Log.DEBUG))
        val level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        loggingInterceptor.level = level
        builder.twitterAuthConfig(TwitterAuthConfig(consumerKey, secretKey))
        Twitter.initialize(builder.build())
    }

}
