package base.api

class HttpError(errorMessage: String) {

    companion object {
        const val UNKNOWN_ERROR = "UNKNOWN_ERROR"
        const val HTTP_EXCEPTION = "Http Exception"
        const val UNKNOW_HOST_EXCEPTION = "Unknown Host Exception"
        const val SOCKET_TIME_OUT_EXCEPTION = "Socket Timeout Exception"
    }
}