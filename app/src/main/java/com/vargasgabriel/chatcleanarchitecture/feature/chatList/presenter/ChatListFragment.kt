package com.vargasgabriel.chatcleanarchitecture.feature.chatList.presenter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.vargasgabriel.chatcleanarchitecture.databinding.ChatListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ChatListFragment : Fragment() {

    private var _binding: ChatListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ChatListViewModel by viewModels()

    private val chatListAdapter = ChatListAdapter(::onChatClicked)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
    }

    private fun updateUI(viewState: ChatListViewState) {
        when (viewState) {
            is ChatListViewState.Content -> {
                binding.viewChatList.isVisible = true
                binding.errorView.isVisible = false
                binding.loadingView.isVisible = false
                chatListAdapter.submitList(viewState.chats)
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

    private fun setupList() {
        binding.viewChatList.apply {
            adapter = chatListAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    private fun onChatClicked(position: Int) {
        val chatViewState = chatListAdapter.currentList[position]
        Snackbar.make(binding.root, "click $position", Snackbar.LENGTH_SHORT).show()
        Log.d(javaClass.name, "position: $position => $chatViewState")

//        findNavController().navigate(ProductListFragmentDirections.actionProductListFragmentToProductDetailsFragment())
    }
}