package a_basics

import kotlinx.coroutines.*

//scope - provides lifecycle methods for coroutines. allow us to start and stop coroutines
//GlobalScope.launch - the scope is the entire application scope - usually not used
//runBlocking - creates a scope and runs a coroutine in a blocking way. not very used.
//coroutineScope{ } - creates a new scope. does not complete until all children complete
//here - the runBlocking block the execution and the last print wait until all finished.

//https://stackoverflow.com/questions/53535977/coroutines-runblocking-vs-coroutinescope - nice discussion
//summary:
//when we use withContext() blocks, we remain within the same scope of the same coroutine, we don't get concurrency (just switching context)
//and structured concurrency works

//when we launch new coroutine within parent coroutine (simply launch() {}) - we get concurrency and structured concurrency

//when we launch new coroutine within parent coroutine but we don't use same scope but external scope for example - we still get concurrency BUT
//not structured concurrency

fun main() {
    println("program execution will now block")
    runBlocking {
        launch {
            delay(1000L)
            println("Task from runBlocking")
        }

        GlobalScope.launch {
            delay(500L)
            println("Task from global Scope")
        }

        coroutineScope {
            launch {
                delay(1500L)
                println("Task from coroutineScope")
            }
        }
    }
    println(" program execution will now continue")
}