package com.awais.hilt.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.awais.hilt.databinding.FragmentNoteBinding
import com.awais.hilt.ui.main.models.DataResponse
import com.awais.hilt.ui.main.adapter.DataAdapter
import com.awais.hilt.ui.main.viewmodel.NoteViewModel
import com.awais.hilt.utils.Constants.TAG
import com.awais.hilt.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!
    private val noteViewModel by viewModels<NoteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pullToRefresh.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {

            noteViewModel.getAllNotes()


        })

        bindObservers()

        noteViewModel.getAllNotes()
    }

    private fun bindObservers() {
        noteViewModel.notesLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    Log.d(TAG, "bindObservers: ${noteViewModel.notesLiveData.value?.data}")
                    bindResponse(noteViewModel.notesLiveData.value?.data)
                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Loading -> {

                }
            }
        })
    }

    private fun bindResponse(notesLiveData: DataResponse?) {

        val recordsAdapter = notesLiveData?.let { DataAdapter(notesLiveData.data) }
        binding.recyclerViewAll.adapter=recordsAdapter

        binding.pullToRefresh.isRefreshing = false

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}