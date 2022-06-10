package com.vargasgabriel.chatcleanarchitecture.feature.chatList.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vargasgabriel.chatcleanarchitecture.databinding.ChatViewHolderBinding

typealias OnChatClicked = (position: Int) -> Unit

class ChatListAdapter(
    private val onChatClicked: OnChatClicked,
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

    inner class ViewHolder(
        private val binding: ChatViewHolderBinding,
        private val onChatClicked: OnChatClicked
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onChatClicked.invoke(adapterPosition)
            }
        }

        fun bind(chatViewState: ChatViewState) {
            binding.title.text = chatViewState.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ChatViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onChatClicked
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}