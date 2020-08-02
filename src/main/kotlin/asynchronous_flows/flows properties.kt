package asynchronous_flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

//Flow properties - cold, cancellation, builders
//flows are cold - the code doesn't run until the collect function is called
//cancellation - a flow cannot be cancelled by itself. it will be cancelled
//when the encompassing coroutine is cancelled.
fun main() {
    runBlocking {
        val numbersFlow = sendNewNumbers()
        println("Flow hasn't started yet")
        println("Starting flow now")
        withTimeoutOrNull(1000L) {
            numbersFlow.collect { println(it) }
        }
    }
}

fun sendNewNumbers() = flow {
    listOf(1, 2, 3).forEach {
        delay(400L)
        emit(it)
    }
}