package com.twitterconnect.callback

interface OnSuccess<RESULT> {

    fun onSuccess(result: RESULT)
}