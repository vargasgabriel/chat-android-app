package com.vargasgabriel.chatcleanarchitecture.feature.chatList.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.vargasgabriel.chatcleanarchitecture.data.source.local.chat.ChatLocalDataSourceMemoryImpl
import com.vargasgabriel.chatcleanarchitecture.data.source.remote.chat.ChatRemoteDataSourceMemoryImpl
import com.vargasgabriel.chatcleanarchitecture.databinding.ChatListFragmentBinding
import kotlinx.coroutines.flow.collect

class ChatListFragment : Fragment() {

    private var _binding: ChatListFragmentBinding? = null
    private val binding get() = _binding!!

    private var _viewModel: ChatListViewModel? = null
    private val viewModel get() = _viewModel!!

    private val adapter = ChatListAdapter(::onChatClicked)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _viewModel = ViewModelProvider(
            this,
            ChatListViewModelFactory(
                chatLocalDataSource = ChatLocalDataSourceMemoryImpl(),
                chatRemoteDataSource = ChatRemoteDataSourceMemoryImpl()
            )
        ).get(ChatListViewModel::class.java)

        _binding = ChatListFragmentBinding.inflate(
            layoutInflater,
            container,
            false
        ).also {
            it.lifecycleOwner = this
        }

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                it?.let { newState -> updateUI(newState) }
            }
        }

        return binding.root
    }

    private fun updateUI(viewState: ChatListViewState) {
        when (viewState) {
            is ChatListViewState.Content -> {
                binding.viewChatList.isVisible = true
                binding.errorView.isVisible = false
                binding.loadingView.isVisible = false
                adapter.submitList(viewState.chats)
            }
            ChatListViewState.Error -> {
                binding.viewChatList.isVisible = false
                binding.errorView.isVisible = true
                binding.loadingView.isVisible = false
            }
            ChatListViewState.Loading -> {
                binding.viewChatList.isVisible = false
                binding.errorView.isVisible = false
                binding.loadingView.isVisible = true
            }
        }
    }

    // parameter just to show how to retrieve data from Adapter to the fragment
    private fun onChatClicked(viewState: ChatViewState) {
//        findNavController().navigate(ProductListFragmentDirections.actionProductListFragmentToProductDetailsFragment())
    }
}