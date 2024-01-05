package com.example.palto.ui.menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.palto.databinding.FragmentMenuListBinding
import com.example.palto.databinding.FragmentSessionListBinding

/**
 * A fragment representing a list of Sessions.
 */
class MenuFragment : Fragment() {

    private val menuViewModel: MenuViewModel by viewModels()

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
        binding.menuList.adapter = MenuAdapter()
        menuViewModel.sessions.observe(viewLifecycleOwner) {
            Log.d("PALTO", "A session has been created")
            adapter.submitList(it)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuViewModel.createSession()
    }
}