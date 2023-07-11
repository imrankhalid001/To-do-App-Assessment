package com.astutesol.notesappmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.astutesol.notesappmvvm.R
import com.astutesol.notesappmvvm.db.Product
import com.astutesol.notesappmvvm.models.Contact


class DataAdapter(private var productList: List<Product>) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tvTitle)
        val priceTextView: TextView = itemView.findViewById(R.id.tvPrice)
        val quantityTextView: TextView = itemView.findViewById(R.id.tvQuantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.iv_buy_list, parent, false)
        return ViewHolder(view)
    }

    fun updateData(newProductList: List<Product>) {
        productList = newProductList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]
        holder.nameTextView.text = product.name
        holder.priceTextView.text = "Name: $${product.name}"
        holder.quantityTextView.text = "Price: ${product.quantity}"
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}