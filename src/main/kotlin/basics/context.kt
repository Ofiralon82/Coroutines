package basics

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Context - scope and context are slimier
//all coroutines have an associated context - every scope has context of his own.
//important elements of context:
//Dispatcher - which thread the coroutine is run on
//Job - handle on the coroutine lifecycle
//The video shows that the GlobalScope is an object (singleton) and that it's only variable is
//a CoroutineContext. so it's only has a CoroutineContext and thats it.
//means - GlobalScope and CoroutineContext are kind of the same thing
fun main() {
    println("program execution will now block")
    runBlocking {
        launch(CoroutineName("myCoroutine")) {
            println("This is run from ${coroutineContext[CoroutineName.Key]}")
        }
    }
}