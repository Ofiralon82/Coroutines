package asynchronous_flows

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

//we can generate flow by emitting each value (for each example)
fun main() {
    runBlocking {
        sendNumbers().collect {
            println("Received $it")
        }
    }
}

fun sendNumbers()
        = flowOf("One", "Two", "Three")

//        = listOf(1, 2, 3).asFlow()

//        = flow {
//    for (i in 1..10)
//        emit(i)
//}