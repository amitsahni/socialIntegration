package com.firebaseauth.google.callback

interface OnError<ERROR> {
    fun onError(error: ERROR)
}