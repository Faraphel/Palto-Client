package com.example.palto.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.palto.R
import com.example.palto.databinding.FragmentMenuListBinding

/**
 * A fragment representing a list of Sessions.
 */
class MenuFragment : Fragment() {

    private val menuViewModel: MenuViewModel by
        navGraphViewModels(R.id.nav_graph)

    private var _binding: FragmentMenuListBinding? = null
    // This property is only valid between onCreateView and onDestroyView
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuListBinding.inflate(inflater, container, false)

        val adapter = MenuAdapter()
        binding.menuList.adapter = adapter
        menuViewModel.sessions.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.createSession.setOnClickListener {
            menuViewModel.createSession()
            findNavController().navigate(R.id.action_menuFragment_to_sessionFragment)
        }

        return binding.root
    }
}