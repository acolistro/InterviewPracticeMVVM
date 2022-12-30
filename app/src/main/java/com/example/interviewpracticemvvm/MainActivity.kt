package com.example.interviewpracticemvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.interviewpracticemvvm.adapter.MovieAdapter
import com.example.interviewpracticemvvm.databinding.ActivityMainBinding
import com.example.interviewpracticemvvm.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
