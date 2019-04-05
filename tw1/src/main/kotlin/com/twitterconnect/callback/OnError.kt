package com.twitterconnect.callback

interface OnError<ERROR> {

    fun onError(error: ERROR)
}