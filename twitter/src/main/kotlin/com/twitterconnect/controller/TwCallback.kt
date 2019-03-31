package com.twitterconnect.controller

import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException

/**
 * Created by amit on 13/2/17.
 */

open class TwCallback<T> : Callback<T>() {

    override fun success(result: Result<T>) {

    }

    override fun failure(exception: TwitterException) {

    }
}
