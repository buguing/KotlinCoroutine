package com.wellee.coroutine

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.wellee.coroutine.api.Service
import com.wellee.coroutine.entity.Article
import com.wellee.coroutine.entity.Banner
import com.wellee.coroutine.entity.Response
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread

class SecondActivity : AppCompatActivity() {

    private var mCompositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        mCompositeDisposable = CompositeDisposable()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://www.wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getRetrofitByRxAdapter(): Retrofit {
        return Retrofit.Builder().baseUrl("https://www.wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    /**
     * 协程请求接口
     */
    private fun coroutines() {
        lifecycleScope.launch {
            val banners: Response<List<Banner>>
            try {
                withContext(Dispatchers.IO) {
                    banners = getRetrofit().create(Service::class.java)
                        .getBannerWithCoroutines()
                }
                Log.e("onSuccess", banners.toString())
                tv_content.text = banners.toString()
            } catch (e: Throwable) {
                Log.e("onError", e.message ?: "未知异常")
                tv_content.text = e.message ?: "未知异常"
            }
        }

    }

    /**
     * 协程请求两个接口同时拿到数据
     */
    private fun coroutinesZip() {
        lifecycleScope.launch {

            try {
                val banners = async {
                    getRetrofit().create(Service::class.java).getBannerWithCoroutines()
                }
                val articles = async {
                    getRetrofit().create(Service::class.java).getArticleWithCoroutines()
                }
                val s = banners.await().data[0].desc + "——" + articles.await().data[0].name
                Log.e("onSuccess", s)
                tv_content.text = s
            } catch (e: Throwable) {
                Log.e("onError", e.message ?: "未知异常")
                tv_content.text = e.message ?: "未知异常"
            }
        }

    }

    /**
     * rxjava3请求接口
     */
    private fun rxjava3() {
        getRetrofitByRxAdapter().create(Service::class.java)
            .getBannerWithRx()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<List<Banner>>> {
                override fun onSuccess(t: Response<List<Banner>>?) {
                    Log.e("onSuccess", t.toString())
                    tv_content.text = t.toString()
                }

                override fun onSubscribe(d: Disposable?) {
                    mCompositeDisposable?.add(d)
                }

                override fun onError(e: Throwable?) {
                    Log.e("onError", e?.message ?: "未知异常")
                    tv_content.text = e?.message ?: "未知异常"
                }
            })
    }

    /**
     * 单 retrofit 请求接口
     */
    private fun retrofit() {
        thread {
            getRetrofit().create(Service::class.java)
                .getBanner().enqueue(object : Callback<Response<List<Banner>>> {
                    override fun onFailure(call: Call<Response<List<Banner>>>, t: Throwable) {
                        Log.e("onFailure", t.message ?: "未知异常")
                        tv_content.text = t.message ?: "未知异常"
                    }

                    override fun onResponse(
                        call: Call<Response<List<Banner>>>,
                        response: retrofit2.Response<Response<List<Banner>>>
                    ) {
                        Log.e("onResponse", response.body().toString())
                        tv_content.text = response.body().toString()
                    }
                })
        }
    }

    /**
     * rx合并请求
     */
    private fun rxjava3zip() {
        val banner = getRetrofitByRxAdapter().create(Service::class.java).getBannerWithRx()
        val article = getRetrofitByRxAdapter().create(Service::class.java).getArticle()
        Single.zip<Response<List<Banner>>, Response<List<Article>>, String>(
            banner,
            article,
            BiFunction { bannerResponse, articleResponse ->
                return@BiFunction bannerResponse.data[0].desc + "——" + articleResponse.data[0].name
            }).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<String> {
                override fun onSuccess(t: String?) {
                    Log.e("onSuccess", t ?: "")
                    tv_content.text = t
                }

                override fun onSubscribe(d: Disposable?) {
                    mCompositeDisposable?.add(d)
                }

                override fun onError(e: Throwable?) {
                    Log.e("onError", e?.message ?: "未知异常")
                    tv_content.text = e?.message ?: "未知异常"
                }
            })
    }

    fun onClick(view: View) {
        tv_content.text = "请求中"
        when (view.id) {
            R.id.btn0 -> retrofit()
            R.id.btn1 -> rxjava3()
            R.id.btn2 -> coroutines()
            R.id.btn3 -> rxjava3zip()
            R.id.btn4 -> coroutinesZip()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable?.clear()
    }

}