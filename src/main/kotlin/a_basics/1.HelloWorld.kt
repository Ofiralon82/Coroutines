package a_basics

import kotlinx.coroutines.*

//coroutines - very light - try to start 1_000_000 thread - you will crush
//go a head and start as many coroutines as you want
//fun main() {
//    runBlocking {
//        repeat(1_000_000) {
//            launch {
//                print(".")
//            }
//        }
//    }
//}

//hello world
fun main() {
    GlobalScope.launch {
        delay(1000)
        println("World!")
    }

    print("Hello, ")
    Thread.sleep(2000)
}



















