package com.example.palto.ui.sessionList

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.example.palto.ui.sessionList.placeholder.PlaceholderContent.PlaceholderItem
import com.example.palto.databinding.FragmentSessionItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 */
class SessionListAdapter(private val values: List<PlaceholderItem>) :
    RecyclerView.Adapter<SessionListAdapter.ViewHolder>() {

    class ViewHolder(binding: FragmentSessionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = FragmentSessionItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.size
}