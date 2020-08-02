package basics

import kotlinx.coroutines.*

//exception handling
//depends on the coroutine builder (we have 2 builders - launch and async)
//launch - the exception will be thrown immediately. use try - catch
//async - exceptions are deferred until the result ic consumed ONLY.
//use try - catch inside of it or wrap the await line.
//we have CoroutineExceptionHandler as well:
fun main() {
    runBlocking {
        val myHandler = CoroutineExceptionHandler {coroutineContext, throwable ->
            println("Exception handled: ${throwable.localizedMessage}")
        }

        val job = GlobalScope.launch(myHandler) {
            println("Throwing exception from job")
            throw IndexOutOfBoundsException("exception in coroutine")
        }
        job.join()//without it we won't see the exception

        val deferred = GlobalScope.async {
            println("Throwing exception from async")
            throw ArithmeticException("exception from async")
        }

        try {
            deferred.await()
        } catch (e: java.lang.ArithmeticException) {
            println("Caught ArithmeticException ${e.localizedMessage}")
        }
    }
}