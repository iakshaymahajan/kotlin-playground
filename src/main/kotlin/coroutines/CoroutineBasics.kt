package coroutines

import kotlinx.coroutines.*

object CoroutineBasics {

    suspend fun bathTime() {
        println("[${Thread.currentThread().getName()}] Going to bathroom")
        delay(500L)
        println("[${Thread.currentThread().getName()}] Bath done !!!")
    }

    suspend fun dressUp() {
        println("[${Thread.currentThread().getName()}] Dressing up")
        delay(2500L)
        println("[${Thread.currentThread().getName()}] Dressing done !!!")
    }

    suspend fun boilingWater() {
        println("[${Thread.currentThread().getName()}] Boiling water")
        delay(1000L)
        println("[${Thread.currentThread().getName()}] Boiled !!!")
    }

    suspend fun sequentialMorningRoutine() {
        coroutineScope { //start a "context" for a coroutine.
            bathTime()
            dressUp()
            // add more code, including calls to other suspend functions
            // Things within one coroutineScope remains isolated from other coroutineScope
            // parallel code here, all needs to finish before the scope is closed.
            // next coroutineScope will start after this finishes
            // all suspend functions within a coroutineScope are executed in sequence. 1. bathTime 2. dressUp
        }

        coroutineScope {
            boilingWater()
        }
    }

    suspend fun concurrentMorningRoutine() {
        coroutineScope { //start a "context" for a coroutine.
            // launch { fn() } will start a new coroutine which will execute the function in parallel
            launch { bathTime() } // this coroutine is a CHILD of the coroutineScope
            launch { boilingWater() }
            launch { dressUp() }
        }
    }

    suspend fun morningRoutineWithDressingUp() {
        coroutineScope {
            val bathTimeJob = launch { boilingWater() }
            val dressUpJob = launch { bathTime() }

            bathTimeJob.join()
            dressUpJob.join()

            launch { dressUp() }
        }
    }

    //Structured Concurrency
    suspend fun morningRoutineWithDressingUpStructured() {
        coroutineScope {
            coroutineScope {
                launch { bathTime() }
                launch { boilingWater() }
            }
            launch { dressUp() }
        }
    }

    suspend fun getBoilingWater(): String {
        println("[${Thread.currentThread().getName()}] Boiling water")
        delay(1000L)
        println("[${Thread.currentThread().getName()}] Boiled !!!")
        return "Water Boiled"
    }

    suspend fun makeCoffee(): String {
        println("[${Thread.currentThread().getName()}] Making Coffee")
        delay(1000L)
        println("[${Thread.currentThread().getName()}] Coffee Prepared !!!")
        return "Espresso"
    }

    suspend fun prepareBreakFast() {
        coroutineScope {
            val boilingWater = async { getBoilingWater() }
            val coffee = async { makeCoffee() }

            val waterBoiled = boilingWater.await()
            val makeCoffee = coffee.await()

            println("[${Thread.currentThread().getName()}] $waterBoiled now I am gonna make $makeCoffee")

        }
    }
}