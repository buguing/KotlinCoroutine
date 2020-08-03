package com.wellee.coroutine.api

import com.wellee.coroutine.entity.Article
import com.wellee.coroutine.entity.Banner
import com.wellee.coroutine.entity.Response
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET

interface Service {

    @GET("banner/json")
    fun getBanner(): Call<Response<List<Banner>>>

    @GET("banner/json")
    fun getBannerWithRx(): Single<Response<List<Banner>>>

    @GET("banner/json")
    suspend fun getBannerWithCoroutines(): Response<List<Banner>>

    @GET("wxarticle/chapters/json")
    fun getArticle(): Single<Response<List<Article>>>

    @GET("wxarticle/chapters/json")
    suspend fun getArticleWithCoroutines(): Response<List<Article>>
}