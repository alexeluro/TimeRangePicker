package com.inspiredcoda.timerangepicker.custom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.inspiredcoda.timerangepicker.databinding.TimeTextLayoutBinding

class TimePickerAdapter : Adapter<TimePickerAdapter.TimePickerViewHolder>() {

    private var time: MutableList<Int> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimePickerViewHolder {
        val binding = TimeTextLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return TimePickerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return time.size
    }

    override fun onBindViewHolder(holder: TimePickerViewHolder, position: Int) {
        holder.bind(time[position])
    }

    fun submitTimeIdentifiers(identifiers: List<Int>) {
        time.clear()
        time.addAll(identifiers)
    }

    class TimePickerViewHolder(private val binding: TimeTextLayoutBinding) :
        ViewHolder(binding.root) {

        fun bind(timeInHourOrMinute: Int) {
            binding.root.text = timeInHourOrMinute.toString()
        }

    }


}