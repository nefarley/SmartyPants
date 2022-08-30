package com.example.smartypants

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.example.smartypants.databinding.DetailActivityBinding


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}