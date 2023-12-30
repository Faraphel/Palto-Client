package com.example.palto.ui.attendanceList

import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import com.example.palto.PaltoViewModel
import com.example.palto.R
import com.example.palto.databinding.FragmentAttendanceListBinding

/**
 * A fragment representing a list of attendances.
 */
class AttendanceListFragment : Fragment() {

    private val attendanceListViewModel: AttendanceListViewModel by
        navGraphViewModels(R.id.nav_graph) { AttendanceListViewModel.Factory }

    private val paltoViewModel: PaltoViewModel by
        activityViewModels()

    private var _binding: FragmentAttendanceListBinding? = null
    // This property is only valid between onCreateView and onDestroyView
    private val binding get() = _binding!!

    /**
     * Only inflate the view.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAttendanceListBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Logic on the returned view of onCreateView.
     */
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // Set the adapter of the view for managing automatically the list of items on the screen.
        val adapter = AttendanceListAdapter()
        binding.list.adapter = adapter
        attendanceListViewModel.cardsLiveData.observe(viewLifecycleOwner) {
            Log.d("NFC", "A card has been had to the list")
            adapter.submitList(it)
        }

        // Set the listener for a new NFC tag.
        paltoViewModel.tagLiveData.observe(viewLifecycleOwner) {
            Log.d("NFC", "tag observer has been notified")
            attendanceListViewModel.insertCard(it)
        }
    }
}