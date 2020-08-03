package com.wellee.coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch(Dispatchers.Main) {
            ioFun1()
            uiFun1()
            ioFun2()
            uiFun2()
            ioFun3()
            uiFun3()
        }
        println("main : current thread name == ${Thread.currentThread().name}")
    }

    private suspend fun ioFun1() {
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
            println("ioFun1 : current thread name == ${Thread.currentThread().name}")
        }
    }

    private suspend fun ioFun2() {
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
            println("ioFun2 : current thread name == ${Thread.currentThread().name}")
        }
    }

    private suspend fun ioFun3() {
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
            println("ioFun3 : current thread name == ${Thread.currentThread().name}")
        }
    }

    private fun uiFun1() {
        println("uiFun1 : current thread name == ${Thread.currentThread().name}")
    }

    private fun uiFun2() {
        println("uiFun2 : current thread name == ${Thread.currentThread().name}")
    }

    private fun uiFun3() {
        println("uiFun3 : current thread name == ${Thread.currentThread().name}")
    }
}
