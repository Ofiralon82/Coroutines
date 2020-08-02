package c_channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

//pipelines - it's a pattern. not something the exist in coroutine. theoretical way to structure the code.
//A pipeline is a development pattern - where one channel output is given as an input to another channel
//chaining channels
//one coroutine is producing a (potentially infinite) set of values
//one or more coroutines are consuming and transforming those values
fun main() {
    runBlocking {
        val numbers = produceNumbers()
        val squares = square(numbers)
        for (i in 1..5)
            println(squares.receive())
        println("Done!")
        coroutineContext.cancelChildren()
    }
}

fun CoroutineScope.produceNumbers() = produce {
    var x = 1
    while (true)
        send(x++)
}

fun CoroutineScope.square(numbers: ReceiveChannel<Int>) = produce {
    for (x in numbers)
        send(x * x)
}