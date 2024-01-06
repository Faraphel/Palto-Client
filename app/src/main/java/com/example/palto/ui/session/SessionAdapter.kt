package com.example.palto.ui.session

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.palto.databinding.FragmentSessionItemBinding
import com.example.palto.domain.Card

/**
 * A [ListAdapter] that can display [Card] items.
 */
class SessionAdapter :
    ListAdapter<Card, SessionAdapter.ViewHolder>(CardDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentSessionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @OptIn(ExperimentalStdlibApi::class)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.cardId.text = item.uid.toHexString()
    }

    inner class ViewHolder(
        binding: FragmentSessionItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        val cardId: TextView = binding.cardId
        override fun toString(): String {
            return super.toString() + " '" + cardId.text + "'"
        }
    }
}

object CardDiffCallback : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem.id == newItem.id
    }
}