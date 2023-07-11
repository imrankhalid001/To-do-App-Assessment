package com.astutesol.notesappmvvm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.astutesol.notesappmvvm.NoteViewModel
import com.astutesol.notesappmvvm.adapter.CallListAdapter
import com.astutesol.notesappmvvm.databinding.FragmentMainBinding
import com.astutesol.notesappmvvm.utils.MyTools
import com.astutesol.notesappmvvm.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val noteViewModel by viewModels<NoteViewModel>()

    private lateinit var adapter: CallListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        adapter = CallListAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindObservers()

        if (MyTools.isNetworkConnected(requireContext())) {
            noteViewModel.getCallListVm()

        } else {
            binding.animationView.isVisible = true
            Toast.makeText(context, "no internet connection", Toast.LENGTH_SHORT).show()
        }


        binding.rvCallList.adapter = adapter


    }

    private fun bindObservers() {
        noteViewModel.callLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false


            when (it) {
                is NetworkResult.Success -> {
                    adapter.addItems(it.data!!)
                }

                is NetworkResult.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }

            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}