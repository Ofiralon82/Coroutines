package d_concurrency_and_shared_state

import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

//The easiest solution is to use an atomic variable for the counter (at almost the same time)
//downside for Atomic - can be complicated for complex data type.
fun main() {
    runBlocking {
        var counter = AtomicInteger(0)
        withContext(Dispatchers.Default) {
            massiveRunAtomic { counter.incrementAndGet() }
        }
        println("Counter = $counter")
    }
}

suspend fun massiveRunAtomic(action: suspend () -> Unit) {
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