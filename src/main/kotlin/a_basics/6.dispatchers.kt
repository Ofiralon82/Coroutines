package a_basics

import kotlinx.coroutines.*

//dispatchers - determines which thread or thread pool the coroutine runs on
//if you want to be more accurate
//Main(ui thread). main dispatcher needs to be defined in Gradle (we get this automatically in android studio)
//Main vs Main.immediate - handler(mainLooper).post vs Activity.runOnUiThread
//default - usually for cpu intensive work - thread pool with maxThreads = max(2, NUM_OF_CPU_CORES)
//
//IO - useful for network communication or reading files etc
//by default - Thread pool with maxThreads = max(64, NUM_OF_CPU_CORES)
//we can adjust it - doc
//
//unconfined - starts the coroutine in the inherited dispatcher that called it - if we don't specified a dispatcher
//we get the current one (can't be controlled!!)
//newSingleThreadContext("MyThread") - forces creation of a new thread - use only for a specific scenarios
fun main() {
    runBlocking {
//        launch(Dispatchers.Main) {
//            println("Main dispatcher. Thread: ${Thread.currentThread().name}")
//        }

        launch(Dispatchers.Unconfined) {
            println("Unconfined1. Thread: ${Thread.currentThread().name}")
            delay(100L)
            println("Unconfined2. Thread: ${Thread.currentThread().name}")
        }

        launch(Dispatchers.Default) {
            println("Default. Thread: ${Thread.currentThread().name}")
        }

        launch(Dispatchers.IO) {
            println("IO. Thread: ${Thread.currentThread().name}")
        }

        launch(newSingleThreadContext("MyThread")) {
            println("newSingleThreadContext. Thread: ${Thread.currentThread().name}")
        }
    }
}