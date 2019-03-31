package fbconnect.callback

interface OnError<ERROR> {

    fun onError(error: ERROR)
}