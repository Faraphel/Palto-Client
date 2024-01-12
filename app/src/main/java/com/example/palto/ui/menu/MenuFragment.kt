package com.example.palto.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.palto.R
import com.example.palto.databinding.FragmentMenuListBinding
import com.example.palto.domain.Session
import com.example.palto.ui.UserViewModel

/**
 * A fragment representing a list of Sessions.
 */
class MenuFragment : Fragment() {

    private val menuViewModel: MenuViewModel by
        navGraphViewModels(R.id.nav_graph) { MenuViewModel.Factory }

    private val userViewModel: UserViewModel by
        activityViewModels() { UserViewModel.Factory }

    // This property is only valid between onCreateView and onDestroyView
    private lateinit var binding: FragmentMenuListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuListBinding.inflate(inflater, container, false)

        val navController = findNavController()

        // Connect the user.
        userViewModel.user.observe(viewLifecycleOwner) {
            if (it != null) {
                // Get sessions of the user from remote.
            } else {
                navController.navigate(R.id.loginFragment)
            }
        }

        // Display the list of sessions.

        // Create a new MenuAdapter (list) with the given function when clicking an item.
        val adapter = MenuAdapter { adapterOnClick(it) }
        binding.menuList.adapter = adapter
        // Link the adapter with the session list in the menuViewMode.
        menuViewModel.sessions.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        // Bind the add button.
        binding.createSession.setOnClickListener {
            navController.navigate(R.id.action_menuFragment_to_newSessionFragment)
        }

        return binding.root
    }

    private fun adapterOnClick(session: Session) {
        val bundle = bundleOf("session" to session.id)
        findNavController().navigate(R.id.action_menuFragment_to_sessionFragment, bundle)
    }
}