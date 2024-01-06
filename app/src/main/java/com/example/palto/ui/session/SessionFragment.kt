package com.example.palto.ui.session

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.example.palto.ui.NfcViewModel
import com.example.palto.R
import com.example.palto.databinding.FragmentSessionListBinding

/**
 * A fragment representing a list of attendances.
 */
class SessionFragment : Fragment() {

    private val sessionViewModel: SessionViewModel by
        navGraphViewModels(R.id.nav_graph) { SessionViewModel.Factory }

    private val nfcViewModel: NfcViewModel by
        activityViewModels()

    private var _binding: FragmentSessionListBinding? = null
    // This property is only valid between onCreateView and onDestroyView
    private val binding get() = _binding!!

    /**
     * Have the fragment instantiate the user interface.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSessionListBinding.inflate(inflater, container, false)

        // Set the adapter of the view for managing automatically the list of items on the screen.
        val adapter = SessionAdapter()
        binding.sessionList.adapter = adapter
        sessionViewModel.cards.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        // Set the listener for a new NFC tag.
        nfcViewModel.tag.observe(viewLifecycleOwner) {
            Log.d("NFC", "The tag observers has been notified.")
            sessionViewModel.insertCard(it)
        }

        binding.createCard.setOnClickListener {
            //sessionViewModel.
        }

        return binding.root
    }
}