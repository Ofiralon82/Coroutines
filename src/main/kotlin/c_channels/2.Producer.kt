package c_channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

//producer - allows a data source to create and return a channel
fun main() {
    runBlocking {
        produceSquares().consumeEach { println(it) }//consumeEach - instead of for loop
    }
}

//extension function
fun CoroutineScope.produceSquares() = produce {
    for(x in 1..5)
        send(x * x)
}

//fun main() {
//    runBlocking {
//        val channel = produce {
//            for (x in 1..5) {
//                send(x * x)
//            }
//        }
//
//        for (y in channel) {
//            println(y)
//        }
//    }
//}