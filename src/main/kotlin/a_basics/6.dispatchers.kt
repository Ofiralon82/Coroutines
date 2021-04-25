package a_basics

import kotlinx.coroutines.*
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

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

        //another way:
//        private val threadPool = ThreadPoolExecutor(
//                3, //3 threads
//                Integer.MAX_VALUE, //max number of thread is unlimited
//                60L, //wait for 60 sec
//                TimeUnit.SECONDS,
//                SynchronousQueue<Runnable>(), //load off immidiadly and not wait on a queue
//                threadFactory //just for thread name
//        );
//
//        private val dispatcher = threadPool.asCoroutineDispatcher()
//
//        override fun dispatch(context: CoroutineContext, block: Runnable) {
//            dispatcher.dispatch(context, block)
//        }

        /**
         * Background CoroutineDispatcher for Android applications which replaces both
         * [Dispatchers.Default] and [Dispatchers.IO].
         */
//        val kotlinx.coroutines.Dispatchers.Background get() = BackgroundDispatcher
    }
}