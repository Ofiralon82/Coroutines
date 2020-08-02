package a_basics

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

//with context
//allows us to easily change context - easily switch between dispatchers
//switch from back thread to main thread for example
fun main() {
    runBlocking {
        launch(Dispatchers.Default) {
            println("First context: $coroutineContext")
            withContext(Dispatchers.IO) {
                println("Second context: $coroutineContext")
            }

            println("Third context: $coroutineContext")
        }
    }
}