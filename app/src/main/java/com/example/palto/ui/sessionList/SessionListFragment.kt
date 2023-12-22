package com.example.palto.ui.sessionList

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.palto.R
import com.example.palto.ui.sessionList.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Sessions.
 */
class SessionListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
         container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_session_list, container, false)

        if (view is RecyclerView) {
            view.layoutManager = LinearLayoutManager(context)
            view.adapter = SessionListAdapter(PlaceholderContent.ITEMS)
        }
        return view
    }
}