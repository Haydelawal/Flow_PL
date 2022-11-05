package com.example.flow_pl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor() : ViewModel() {

    val countDownFlow = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue
        emit(startingValue)

        while (currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }

    init {
        collectMyFlow()
    }

    private fun collectMyFlow() {
//        viewModelScope.launch {
//           val count = countDownFlow
//                // intermediate operators
//                .filter { it % 2 == 0 }
//
//                .map { it * it }
//
//                .onEach { println(" value is: $it") }
//
//                //// terminal operators
//                .count{
//                    it % 2 == 0
//                }
//            println("The count is $count")
//            // 100, 64, 36, 16, 4, 0
//
//        }

        // terminal operators
        // using reduce terminal operator
//        viewModelScope.launch {
////            val reduceCount = countDownFlow
////                .reduce { accumulator, value ->
////                    accumulator + value
////                }
////            println("Value is $reduceCount")
////        }

            // fold
//        viewModelScope.launch {
//            val foldCount = countDownFlow
//                .fold(100) { accumulator, value ->
//                    accumulator + value
//                }
//            println("Value is $foldCount")
//        }

        /**
         flat map takes for an example, a list inside a list and returns a single list eg
         [[1,2,3] [4,5,6] [7,8,9]]
         flat map will output 1,2,3,4,5,6,7,8,9   */

        /**
         * ---flat map concat -- discouraged to use
         * ---flat map merge --- discouraged to use
         * --- flat map Latest ---- can be used*/


        // buffer vs conflate
        val foodFlow = flow{
            delay(250L)
            emit("Appetizer")
            delay(1000L)
            emit("Main Dish")
            delay(250L)
            emit("Dessert")

        }

        viewModelScope.launch {
            foodFlow.onEach {
                println("Food: $it is delivered")
            }.buffer()
                .collect{
                    println("Food: Now Eating $it")
                    delay(1500L)
                    println("Food: Finished eating $it")
                }

        }

    }
}