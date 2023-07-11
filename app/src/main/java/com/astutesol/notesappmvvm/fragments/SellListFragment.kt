package com.astutesol.notesappmvvm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.astutesol.notesappmvvm.adapter.DataAdapter
import com.astutesol.notesappmvvm.databinding.FragmentSellListBinding
import com.astutesol.notesappmvvm.db.DatabaseHelper
import com.astutesol.notesappmvvm.db.Product

class SellListFragment : Fragment() {

    private var _binding: FragmentSellListBinding? = null
    private val binding get() = _binding!!
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var adapter: DataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSellListBinding.inflate(inflater, container, false)
        dbHelper = DatabaseHelper(requireContext())

        setupRecyclerView()
        AddProductList()
        loadDataFromDatabase()

        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = DataAdapter(emptyList())
        binding.rvSellList.adapter = adapter
        binding.rvSellList.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun loadDataFromDatabase() {
        val productList = dbHelper.getAllProducts()
        adapter.updateData(productList) // Update the adapter with new data
    }


    private fun AddProductList() {
        dbHelper.insertProduct(Product(1, "Jason White", 6665,2,4))
        dbHelper.insertProduct(Product(2, "Wasim Khan", 237890,2,4))
        dbHelper.insertProduct(Product(3, "Amir Khan", 9820,2,4))

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
