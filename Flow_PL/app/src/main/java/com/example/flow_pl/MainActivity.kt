package com.example.flow_pl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.flow_pl.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //activity_main ==>> Activity_Main_Binding
    private lateinit var binding: ActivityMainBinding
    private val myViewModel: MyViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.text = myViewModel.stateFlow.value.toString()

        binding.button.setOnClickListener {
            myViewModel.incrementCounter()


//            lifecycleScope.launchWhenCreated {
//                repeatOnLifecycle(Lifecycle.State.STARTED) {
//                    myViewModel.stateFlow.collectLatest {
//                        binding.textView.text = it.toString()
//                    }
//                }
//            }
//
//        }
            collectLatestLifeCycleFlow(myViewModel.stateFlow) {

                binding.textView.text = it.toString()
            }


        }

    }
}

fun <T> AppCompatActivity.collectLatestLifeCycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(collect)
        }

    }
}


