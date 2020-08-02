package c_channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//channel is a queue of data
//a coroutine can asynchronously put elements - send(data)
//another can blockingly get elements - .receive()
//a channel is closed when there are no more elements - .close()

//Channel VS Flows:
//****************
//For many use cases where the best tool so far was Channel, Flow has become the new best tool.
//
//As a specific example, callbackFlow is now the best approach to receiving data from a 3rd-party
//API's callback. This works especially well in a GUI setting. It couples the callback, a channel,
//and the associated receiving coroutine all in the same self-contained Flow instance.
//The callback is registered only while the flow is being collected. Cancellation of the flow automatically
//propagates into closing the channel and deregistering the callback. You just have to
//provide the callback-deregistering code once.
//
//You should look at Channel as a lower-level primitive that Flow uses in its implementation.
//Consider working with it directly only after you realize Flow doesn't fit your requirements.
fun main() {
    runBlocking {
        val channel = Channel<Int>()
        launch {
            for (x in 1..5)
                channel.send(x * x)
            channel.close()
        }

//        for(i in 1..5)
//            println(channel.receive())
        for (i in channel)
            println(i)
    }
}
