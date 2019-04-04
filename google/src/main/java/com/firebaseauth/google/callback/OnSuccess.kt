package com.firebaseauth.google.callback

interface OnSuccess<RESULT> {
    fun onSuccess(result: RESULT)
}