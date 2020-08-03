package com.wellee.coroutine.entity

data class Response<T>(var errorCode: Int, var errorMsg: String, var data: T)