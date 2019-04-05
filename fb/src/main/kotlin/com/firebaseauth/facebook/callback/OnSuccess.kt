package com.firebaseauth.facebook.callback

interface OnSuccess<RESULT> {
    fun onSuccess(result: RESULT)
}