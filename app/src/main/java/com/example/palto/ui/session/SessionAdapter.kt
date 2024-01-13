package com.example.palto.ui.session

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.palto.databinding.FragmentSessionItemBinding
import com.example.palto.domain.Attendance
import com.example.palto.domain.Card
import java.time.format.DateTimeFormatter

/**
 * A [ListAdapter] that can display [Attendance] items.
 */
class SessionAdapter : ListAdapter<Attendance, SessionAdapter.ViewHolder>(AttendanceDiffCallback) {


    inner class ViewHolder(binding: FragmentSessionItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
        private val attendanceUsernameText: TextView = binding.attendanceUsername
        private val attendanceDate: TextView = binding.attendanceDate

        fun bind(attendance: Attendance) {
            attendanceUsernameText.text = attendance.student.username
            attendanceDate.text = attendance.date.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentSessionItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @OptIn(ExperimentalStdlibApi::class)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

object AttendanceDiffCallback : DiffUtil.ItemCallback<Attendance>() {
    override fun areItemsTheSame(oldItem: Attendance, newItem: Attendance): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Attendance, newItem: Attendance): Boolean {
        return oldItem.id == newItem.id
    }
}