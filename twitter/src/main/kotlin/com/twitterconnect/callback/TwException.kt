package com.twitterconnect.callback

import com.twitter.sdk.android.core.TwitterException

/**
 * Created by amit on 4/2/17.
 */

class TwException : com.twitter.sdk.android.core.TwitterException {
    constructor(detailMessage: String) : super(detailMessage) {}

    constructor(detailMessage: String, throwable: Throwable) : super(detailMessage, throwable) {}
}
