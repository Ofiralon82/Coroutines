package b_asynchronous_flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

//operators - take an input flow, transform it and return an output flow
//operators are cold
//the returning flow is synchronous
fun main() {
    runBlocking {
//        mapOperator()
//        filterOperator()
        transformOperator()
    }
}

//transform operator - general transformation operator
//can emit any value at any time
suspend fun transformOperator() {
    (1..10).asFlow()
            .transform {
                emit("Emitting string value $it")
                emit(it)
            }
            .collect {
                println(it)
            }
}

suspend fun filterOperator() {
    (1..10).asFlow()
            .filter {
                it % 2 == 0
            }
            .collect {
                println(it)
            }
}

suspend fun mapOperator() {
    (1..10).asFlow()
            .map {
                delay(500L)
                "mapping $it"
            }
            .collect {
                println(it)
            }
}