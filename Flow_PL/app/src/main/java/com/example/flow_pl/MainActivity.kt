package com.example.flow_pl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.flow_pl.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
   //activity_main ==>> Activity_Main_Binding
       private lateinit var binding: ActivityMainBinding
       private val myViewModel: MyViewModel by viewModels()


       override fun onCreate(savedInstanceState: Bundle?) {
           super.onCreate(savedInstanceState)
           binding = ActivityMainBinding.inflate(layoutInflater)
           setContentView(binding.root)

           lifecycleScope.launchWhenCreated {
                myViewModel.countDownFlow.collectLatest {
                    binding.textView.text = it.toString()
                }
           }

       }

}