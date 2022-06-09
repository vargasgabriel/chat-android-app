package com.vargasgabriel.chatcleanarchitecture.feature.chatList.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vargasgabriel.chatcleanarchitecture.databinding.ChatViewHolderBinding

class ChatListAdapter(
    val onChatClicked: (ChatViewState) -> Unit,
) : ListAdapter<ChatViewState, ChatListAdapter.ViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ChatViewState>() {
            override fun areItemsTheSame(oldItem: ChatViewState, newItem: ChatViewState): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ChatViewState,
                newItem: ChatViewState
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class ViewHolder(private val _binding: ChatViewHolderBinding) : RecyclerView.ViewHolder(_binding.root) {

        fun bind(chatViewState: ChatViewState) {
            _binding.root.setOnClickListener {
                onChatClicked.invoke(chatViewState)
            }
            _binding.title.text = chatViewState.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ChatViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}