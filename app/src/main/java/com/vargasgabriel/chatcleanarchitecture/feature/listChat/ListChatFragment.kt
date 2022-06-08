package com.vargasgabriel.chatcleanarchitecture.feature.listChat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vargasgabriel.chatcleanarchitecture.databinding.FragmentListChatBinding

class ListChatFragment : Fragment() {

    private var _binding: FragmentListChatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListChatBinding.inflate(
            layoutInflater,
            container,
            false
        ).also {
//            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
        return binding.root
    }
}