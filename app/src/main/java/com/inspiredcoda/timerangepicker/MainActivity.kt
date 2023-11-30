package com.inspiredcoda.timerangepicker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inspiredcoda.timerangepicker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.timeBtn.setOnClickListener {
            showTimeRangePicker()
        }


    }

    private fun showTimeRangePicker() {
        TimePicker()
            .setTitle("Select Date Range")
            .setOnTimeChangedListener(
                object : TimePicker.OnTimeChangedListener {
                    override fun onTimeRangeSet(view: TimePicker?, startTime: String, endTime: String) {
                        binding.startTimeText.text = startTime
                        binding.endTimeText.text = endTime
                        println("Start Time: $startTime \nEnd Time: $endTime")
                    }
                }
            )
            .show(supportFragmentManager, "time_picker")
    }

}