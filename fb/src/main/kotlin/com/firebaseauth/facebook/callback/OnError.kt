package com.firebaseauth.facebook.callback

interface OnError<ERROR> {
    fun onError(error: ERROR)
}