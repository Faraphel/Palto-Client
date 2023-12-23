package com.example.palto.ui.attendanceList

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navGraphViewModels
import com.example.palto.R
import com.example.palto.databinding.FragmentAttendanceListBinding

/**
 * A fragment representing a list of attendances.
 */
class AttendanceListFragment : Fragment() {

    private val attendanceListViewModel: AttendanceListViewModel by
        navGraphViewModels(R.id.nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAttendanceListBinding.inflate(inflater, container, false)

        val adapter = AttendanceListAdapter()
        binding.list.adapter = adapter

        attendanceListViewModel.cardsLiveData.observe(viewLifecycleOwner) {
            it -> adapter.submitList(it)
        }

        return binding.root
    }
}