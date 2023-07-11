package com.astutesol.notesappmvvm.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.astutesol.notesappmvvm.NoteViewModel
import com.astutesol.notesappmvvm.R
import com.astutesol.notesappmvvm.adapter.BuyListAdapter
import com.astutesol.notesappmvvm.databinding.FragmentBuyListBinding
import com.astutesol.notesappmvvm.utils.MyTools
import com.astutesol.notesappmvvm.utils.NetworkResult
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuyListFragment : Fragment() {

    private var _binding : FragmentBuyListBinding? = null
    private val binding get() = _binding!!

    private val noteViewModel by viewModels<NoteViewModel>()

    private lateinit var adapter: BuyListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentBuyListBinding.inflate(inflater, container, false)

        adapter = BuyListAdapter()

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindObservers()

        if (MyTools.isNetworkConnected(requireContext()))
        {
            noteViewModel.getBuyListVm()

        }
        else
        {
            binding.animationView.isVisible = true
            Toast.makeText(context,"no internet connection", Toast.LENGTH_SHORT).show()
        }


        binding.rvBuyList.adapter = adapter



    }

    private fun bindObservers() {
        noteViewModel.buyLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false


            when(it)
            {
                is NetworkResult.Success ->{
                    adapter.addItems(it.data!!)
                    Log.d("BuyerDAta__" , it.data.toString())
                }

                is NetworkResult.Error ->{
                    Toast.makeText(context,it.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading ->{
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