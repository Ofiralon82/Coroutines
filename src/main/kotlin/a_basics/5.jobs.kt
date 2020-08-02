package a_basics

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Jobs
//handle on that coroutine - .launch() returns a job
//allow us to manipulate the coroutine lifecycle
//live in the hierarchy of other jobs. both as parents or children (job2 lives inside job1 etc)
// we use cancel(), join() - join the coroutine to the current thread.
//cancel job - all the jobs inside will be cancled
//fun main() {
//    runBlocking {
//        val job1 = launch {
//            delay(3000L)
//            println("Job1 launched")
//        }
//
//        job1.invokeOnCompletion { println("Job1 is completed") }
//
//        delay(500L)
//        println("Job1 will be canceled")
//        job1.cancel()
//    }
//}

fun main() {
    runBlocking {
        val job1 = launch {
            println("Job1 launched")
            val job2 = launch {
                println("Job2 launch")
                delay(3000L)
                println("Job2 is finishing")
            }
            job2.invokeOnCompletion { println("Job2 completed") }

            val job3 = launch {
                println("Job3 lunched")
                delay(3000L)
                println("Job3 is finishing")
            }
            job3.invokeOnCompletion { println("Job3 completed") }
        }

        job1.invokeOnCompletion { println("Job1 is completed") }

        delay(500L)
        println("Job1 will be canceled")
        job1.cancel()
    }
}