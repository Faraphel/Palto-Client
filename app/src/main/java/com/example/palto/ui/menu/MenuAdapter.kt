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
class MenuAdapter(private val onClick: (Session) -> Unit) :
    ListAdapter<Session, MenuAdapter.ViewHolder>(SessionDiffCallback) {
    inner class ViewHolder(binding: FragmentMenuItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
        private val sessionNameText: TextView = binding.sessionName
        private var currentSession: Session? = null

        init {
            binding.root.setOnClickListener {
                currentSession?.let {
                    onClick(it)
                }
            }
        }

        fun bind(session: Session) {
            currentSession = session
            sessionNameText.text = session.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentMenuItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun getItemCount() = currentList.size
}

object SessionDiffCallback : DiffUtil.ItemCallback<Session>() {
    override fun areItemsTheSame(oldItem: Session, newItem: Session): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Session, newItem: Session): Boolean {
        return oldItem.id == newItem.id
    }
}