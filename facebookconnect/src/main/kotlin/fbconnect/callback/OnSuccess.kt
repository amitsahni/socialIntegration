package fbconnect.callback

interface OnSuccess<RESULT> {

    fun onSuccess(result: RESULT)
}