package com.wellee.coroutine.entity

data class Article(
    var courseId: Int,
    var id: Int,
    var name: String?,
    var order: Int,
    var parentChapterId: Int,
    var isUserControlSetTop: Boolean,
    var visible: Int,
    var children: List<*>? = null
)