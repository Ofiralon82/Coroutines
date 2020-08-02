package d_concurrency_and_shared_state

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

//All access to shared data is confined to a single thread
//Fine-grained - each individual increment switches context - mush slower
//Coarse-grained - the whole function is run on a single thread, no context switching - faster (but not in parallel)
fun main() {
    runBlocking {
        val counterContext = newSingleThreadContext("CounterContext")
        var counter = 0
        withContext(counterContext) {
            massiveRunThreads {
//                withContext(counterContext) {
                counter++
//                }
            }
        }
        println("Counter = $counter")
    }
}

suspend fun massiveRunThreads(action: suspend () -> Unit) {
    val n = 100
    val k = 1000
    val time = measureTimeMillis {
        coroutineScope {
            repeat(n) {
                launch {
                    repeat(k) {
                        action()
                    }
                }
            }
        }
    }
    println("Completed ${n * k} actions in $time ms")
}