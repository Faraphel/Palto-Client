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
import com.example.palto.ui.attendanceList.placeholder.PlaceholderContent

/**
 * A fragment representing a list of attendances.
 */
class AttendanceListFragment : Fragment() {

    private val attendanceListViewModel: AttendanceListViewModel by
        navGraphViewModels(R.id.nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_attendance_list, container, false)
        if (view is RecyclerView) {
            view.layoutManager = LinearLayoutManager(context)
            view.adapter = AttendanceListAdapter(PlaceholderContent.ITEMS)
        }
        return view
    }
}