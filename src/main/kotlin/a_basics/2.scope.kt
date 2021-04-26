package a_basics

import kotlinx.coroutines.*

//scope - provides lifecycle methods for coroutines. allow us to start and stop coroutines
//GlobalScope.launch - the scope is the entire application scope - usually not used
//runBlocking - creates a scope and runs a coroutine in a blocking way. not very used.
//coroutineScope{ } - creates a new scope. does not complete until all children complete
//here - the runBlocking block the execution and the last print wait until all finished.

//https://stackoverflow.com/questions/53535977/coroutines-runblocking-vs-coroutinescope - nice discussion
//summary:
//more:
//CoroutineScope and CoroutineContext are almost the same (scope wraps the context). but different purposes -
//CoroutineScope uses to manage coroutines, to lunch new coroutines and CoroutineContext uses to control the execution environment
//(CoroutineScope is the builder while coroutineScope is something like launch)
//everytime we alter the coroutineContext we basically create new coroutine scope
//
//summary and examples right after:
//when we use withContext() blocks, we remain within the same scope of the same coroutine, we don't get concurrency (just switching context)
//and structured concurrency works

//when we launch new coroutine within parent coroutine (simply launch() {}) - we get concurrency and structured concurrency

//when we launch new coroutine within parent coroutine but we don't use same scope but external scope for example - we still get concurrency BUT
//not structured concurrency

//again with examples:
//runBlocking {
//    val scopeJob = Job()
//    val scope = CoroutineScope(scopeJob + CoroutineName("outer scope") + Dispatchers.Default)
//    val job = scope.launch(CoroutineName("coroutine")) {
//        delay(500)
//        println("before nested")
//        withContext(Dispatchers.IO) {
//            delay(500)
//            printJobsHierarchy(scopeJob)
//            println("nested")
//        }
//        println("after nested")
//    }
//above we have job Hierarchy:
// - scope Job
//   - coroutine Job
//     - context Job
// - no concurrency there BUT we do change thread from within withContext. and the job will wait for it in order to finish (structured concurrency)
//
//runBlocking {
//    val scopeJob = Job()
//    val scope = CoroutineScope(scopeJob + CoroutineName("outer scope") + Dispatchers.Default)
//    val job = scope.launch(CoroutineName("coroutine")) {
//        delay(500)
//        println("before nested")
//        val nestedJob = launch(Dispatchers.IO) {
//            delay(500)
//            printJobsHierarchy(scopeJob)
//            println("nested")
//        }
//        println("after nested")
//    }
//above - the job hierarchy stays the same BUT the launch block is now concurrency running. we still have structured concurrency - means that
//the launch (create new coroutine) uses the same scope of the parent coroutine.

//NonCancellable is "detaching" withContext (and ONLY withContext) from its parent job - we can get the same effect by simply create new Job()


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