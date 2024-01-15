package com.example.palto.ui.menu.new_session

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.palto.R
import com.example.palto.databinding.FragmentNewSessionBinding
import com.example.palto.ui.menu.MenuViewModel

/**
 *
 */
class NewSessionFragment : Fragment() {

    private val newSessionViewModel: NewSessionViewModel by viewModels()

    private val menuViewModel: MenuViewModel by
        navGraphViewModels(R.id.nav_graph) { MenuViewModel.Factory }

    // This property is only valid between onCreateView and onDestroyView
    private lateinit var binding: FragmentNewSessionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewSessionBinding.inflate(inflater, container, false)

        // Bind the create button
        binding.newSessionCreate.setOnClickListener {
            menuViewModel.createSession(
                name = binding.newSessionName.text.toString()
            )
            findNavController().popBackStack()
        }

        return binding.root
    }
}