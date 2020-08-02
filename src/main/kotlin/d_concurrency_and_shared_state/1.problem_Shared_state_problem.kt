package d_concurrency_and_shared_state

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

//Shared State:
//multiple coroutines can update a shared state variable concurrently
//Some updated may be lost (two coroutines for example updating the same variable at the same time)
fun main() {
    runBlocking {
        var counter = 0
        withContext(Dispatchers.Default) {
            massiveRun { counter++ }
        }
        println("Counter = $counter")
    }
}
//100 coroutines updating 1000 times - we would except 100,000 increments - but we don't get it
suspend fun massiveRun(action: suspend () -> Unit) {
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