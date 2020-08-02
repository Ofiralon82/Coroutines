package b_asynchronous_flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

//In case processing a flow takes a long time a buffer is useful to accumulate flow values that
//can be processed later
//it's storing values that were generated and put it in some queue - in case for example when
//the receiver can't handle it so fast
//here - without the buffer() - takes about 1200ms, while with buffer() - takes about 1000ms (calculate it)
fun main() {
    runBlocking {
        val time = measureTimeMillis {
            generate()
                    .buffer()
                    .collect {
                        delay(300L)
                        println(it)
                    }
        }
        println("Collected in $time ms")
    }
}

fun generate() = flow {
    for(i in 1..3) {
        delay(100L)
        emit(i)
    }
}