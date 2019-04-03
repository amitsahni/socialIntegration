package com.instaconnect.callback

interface OnError<ERROR> {

    fun onError(error: ERROR)
}