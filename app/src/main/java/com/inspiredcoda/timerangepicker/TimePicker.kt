package com.inspiredcoda.timerangepicker

import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.inspiredcoda.timerangepicker.databinding.TimePickerSelectorBinding
import java.text.DecimalFormat

class TimePicker : DialogFragment() {

    private var _binding: TimePickerSelectorBinding? = null
    private val binding: TimePickerSelectorBinding
        get() = _binding!!

    private lateinit var startTimeListener: TimePicker.OnTimeChangedListener
    private lateinit var endTimeListener: TimePicker.OnTimeChangedListener

    private var onTimeChangedListener: OnTimeChangedListener? = null

    private var startTime: String = "00:00"
    private var endTime: String = "00:00"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = TimePickerSelectorBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTimeListeners()

//        dialog?.setTitle("Select Date")

        binding.startTime.apply {
            setOnClickListener {
//                binding.startTimePicker.setOnTimeChangedListener(startTimeListener)
                binding.startTime.hideKeyboard()

                binding.startTime.setBackgroundResource(R.drawable.focus_outline_bg)
                binding.endTime.setBackgroundResource(R.drawable.default_outline_bg)

                binding.startTimePicker.visibility = View.VISIBLE
                binding.endTimePicker.visibility = View.GONE
            }

        }

        binding.endTime.apply {
            setOnClickListener {
                binding.endTimePicker.setOnTimeChangedListener(endTimeListener)
                binding.endTime.hideKeyboard()

                binding.endTime.setBackgroundResource(R.drawable.focus_outline_bg)
                binding.startTime.setBackgroundResource(R.drawable.default_outline_bg)

                binding.endTimePicker.visibility = View.VISIBLE
                binding.startTimePicker.visibility = View.GONE
            }

        }

        binding.startTimePicker.setOnTimeChangedListener(startTimeListener)
        binding.endTimePicker.setOnTimeChangedListener(endTimeListener)

        binding.cancelBtn.setOnClickListener {
            dismiss()
        }

        binding.submitBtn.setOnClickListener {
            onTimeChangedListener?.onTimeRangeSet(this, startTime, endTime)
        }

    }

    fun setTitle(title: String): com.inspiredcoda.timerangepicker.TimePicker {
        dialog?.setTitle(title)
        return this
    }

    fun setOnTimeChangedListener(listener: OnTimeChangedListener): com.inspiredcoda.timerangepicker.TimePicker {
        onTimeChangedListener = listener
        return this
    }

    private fun View.hideKeyboard() {
        val inputManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
    }

    private fun initTimeListeners() {
        val doubleDigitFormat = DecimalFormat("00")
        startTimeListener = object :
            TimePicker.OnTimeChangedListener {
            override fun onTimeChanged(view: TimePicker?, hourOfDay: Int, minute: Int) {
                val time =
                    "${doubleDigitFormat.format(hourOfDay)}:${doubleDigitFormat.format(minute)}"
                binding.startTime.text = time
                startTime = time
            }
        }


        endTimeListener = object :
            TimePicker.OnTimeChangedListener {
            override fun onTimeChanged(view: TimePicker?, hourOfDay: Int, minute: Int) {
                val time =
                    "${doubleDigitFormat.format(hourOfDay)}:${doubleDigitFormat.format(minute)}"
                binding.endTime.text = time
                endTime = time
            }
        }
    }

    private fun Fragment.showTimeRangePicker(block: (Int, Int) -> Unit) {
        TimePickerDialog(
            requireContext(),
            object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    block(hourOfDay, minute)
                }

            },
            1, 0, false
        ).show()
    }


    private fun initRecyclerViews() {
//        binding.hourIndicator.apply {
//            layoutManager =
//                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//            adapter = TimePickerAdapter().apply {
//                submitTimeIdentifiers((1..12).toList())
//            }
//        }
//
//        binding.minuteIndicator.apply {
//            layoutManager =
//                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//            adapter = TimePickerAdapter().apply {
//                submitTimeIdentifiers((0..59).toList())
//            }
//        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    interface OnTimeChangedListener {

        fun onTimeRangeSet(
            view: com.inspiredcoda.timerangepicker.TimePicker?,
            startTime: String,
            endTime: String
        )

    }

}