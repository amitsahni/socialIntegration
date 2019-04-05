package com.firebaseauth.twitter.callback

interface OnError<ERROR> {
    fun onError(error: ERROR)
}