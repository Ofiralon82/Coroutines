package b_asynchronous_flows

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

//exception handling
fun main() {
    runBlocking {
//        tryCatch()
//        catch()
        onCompletion()
    }
}

//here it's more like completion code instead of catch
suspend fun onCompletion() {
    (1..3).asFlow()
            .onEach { check(it != 2) }
            .onCompletion { e ->
                if(e != null)
                    println("Flow completed with exception $e")
                else
                    println("Flow completed successfully")
            }
            .catch { e -> println("Caught exception $e") }
            .collect { println(it) }
}

//the location of the catch is important. if we do some operation after the catch it wont catch it.
//we can combine different kind of catch this way
suspend fun catch() {
    (1..3).asFlow()
            .onEach { check(it != 2) }
            .catch { e -> println("Caught exception $e") }
            .collect { println(it) }
}

suspend fun tryCatch() {
    try {
        (1..3).asFlow()
                .onEach { check(it != 2) }
                .collect { println(it) }
    } catch (e: Exception) {
        println("Caught exception $e")
    }
}