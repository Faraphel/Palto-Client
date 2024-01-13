package com.example.palto.ui.session.new_student

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.palto.NfcViewModel
import com.example.palto.R
import com.example.palto.databinding.FragmentNewSessionBinding
import com.example.palto.databinding.FragmentNewStudentBinding
import com.example.palto.ui.CardViewModel
import com.example.palto.ui.UserViewModel
import com.example.palto.ui.menu.MenuViewModel
import com.example.palto.ui.session.SessionViewModel

/**
 *
 */
class NewStudentFragment : Fragment() {

    private val newStudentViewModel: NewStudentViewModel by viewModels()

    private val sessionViewModel: SessionViewModel by navGraphViewModels(R.id.nav_graph)

    private val userViewModel: UserViewModel by navGraphViewModels(R.id.nav_graph)

    private val cardViewModel: CardViewModel by navGraphViewModels(R.id.nav_graph)

    // This property is only valid between onCreateView and onDestroyView
    private lateinit var binding: FragmentNewStudentBinding

    @OptIn(ExperimentalStdlibApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewStudentBinding.inflate(inflater, container, false)

        // If the bundle value tagId of key "tagId" exists,
        // Set it on the screen.
        val tagId = arguments?.getString("tagId")
        if (tagId != null) {
            binding.newStudentCardId.text = tagId
        }

        // Bind the create button.
        binding.newStudentCreate.setOnClickListener {
            val user = userViewModel.createUser(binding.newStudentName.text.toString())

            // If a tag has been provided, create the card.
            // The user would not need to create his account afterward.
            if (tagId != null) {
                cardViewModel.createCard(user, tagId)
            }

            sessionViewModel.addAttendance(user)
            findNavController().popBackStack()
        }

        return binding.root
    }
}