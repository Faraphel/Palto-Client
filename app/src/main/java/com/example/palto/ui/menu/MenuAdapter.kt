package com.example.palto.ui.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.palto.databinding.FragmentMenuItemBinding
import com.example.palto.domain.Session

/**
 * A [ListAdapter] that can display [Session] items.
 */
class MenuAdapter :
    ListAdapter<Session, MenuAdapter.ViewHolder>(SessionDiffCallback) {

        /*
    class ViewHolder(binding: FragmentSessionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
         */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentMenuItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.idView.text = position.toString()
        holder.contentView.text = item.id
    }

    //override fun getItemCount(): Int = values.size

    inner class ViewHolder(
        binding: FragmentMenuItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content
        override fun toString(): String {
            return super.toString() + " '" + idView.text + contentView.text + "'"
        }
    }
}

object SessionDiffCallback : DiffUtil.ItemCallback<Session>() {
    override fun areItemsTheSame(oldItem: Session, newItem: Session): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Session, newItem: Session): Boolean {
        return oldItem.id == newItem.id
    }
}