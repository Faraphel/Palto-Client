package com.example.palto.ui.session

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.palto.NfcViewModel
import com.example.palto.R
import com.example.palto.databinding.FragmentSessionListBinding
import com.example.palto.ui.CardViewModel
import com.example.palto.ui.menu.MenuViewModel

/**
 * A fragment representing a list of attendances.
 */
class SessionFragment : Fragment() {

    private val sessionViewModel: SessionViewModel by
        navGraphViewModels(R.id.nav_graph) { SessionViewModel.Factory }

    private val menuViewModel: MenuViewModel by
        navGraphViewModels(R.id.nav_graph) { MenuViewModel.Factory }

    private val cardViewModel: CardViewModel by
        navGraphViewModels(R.id.nav_graph)

    private val nfcViewModel: NfcViewModel by activityViewModels()

    // This property is only valid between onCreateView and onDestroyView
    private lateinit var binding: FragmentSessionListBinding

    /**
     * Have the fragment instantiate the user interface.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSessionListBinding.inflate(inflater, container, false)

        // If the bundle value id of key "session" is not empty,
        // set the attendance list to that of the selected session.
        arguments?.getInt("session")?.let { id ->
            val session = menuViewModel.getSession(id)
            if (session != null) {
                Log.d("Palto", "SessionFragment: Session id ${session.id} has been found.")
                sessionViewModel.session = session
                sessionViewModel.setAttendanceList(session.attendances)
            }
        }

        // Set the adapter of the view for managing automatically the list of items on the screen.
        val adapter = SessionAdapter()
        binding.sessionList.adapter = adapter
        sessionViewModel.attendances.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        // Set the listener for a new NFC tag.
        nfcViewModel.tagId.observe(viewLifecycleOwner) {

            // If the NFC tag has not been handled.
            it.getContentIfNotHandled()?.let { cardId ->
                val card = cardViewModel.getCard(cardId)
                // If a card with this tag exists, add this card.
                if (card != null) {
                    sessionViewModel.addAttendance(card.user)
                // Else go to the NewStudentFragment to create a new card and student.
                } else {
                    val bundle = bundleOf("tagId" to cardId)
                    findNavController()
                        .navigate(R.id.action_sessionFragment_to_newStudentFragment, bundle)
                }
            }
        }

        // Manual add student button
        binding.addStudent.setOnClickListener {
            findNavController().navigate(R.id.action_sessionFragment_to_newStudentFragment)
        }

        // Print the result of adding an attendance on the view.
        sessionViewModel.result.observe(viewLifecycleOwner) {
            // If the result has not been already shown
            it.getContentIfNotHandled()?.let { result ->
                Toast.makeText(
                    activity,
                    getString(result.message, result.username),
                    Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }


}