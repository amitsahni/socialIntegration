package com.firebaseauth.twitter.callback

interface OnSuccess<RESULT> {
    fun onSuccess(result: RESULT)
}