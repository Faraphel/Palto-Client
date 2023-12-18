package com.example.palto.ui.sheet

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.palto.R

class SheetDetailFragment : Fragment() {

    companion object {
        fun newInstance() = SheetDetailFragment()
    }

    private lateinit var viewModel: SheetDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sheet_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SheetDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}