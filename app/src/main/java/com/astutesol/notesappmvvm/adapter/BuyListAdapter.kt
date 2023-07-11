package com.astutesol.notesappmvvm.adapter

import com.astutesol.notesappmvvm.R
import com.astutesol.notesappmvvm.databinding.IvBuyListBinding
import com.astutesol.notesappmvvm.models.GetCallListModelResopnseItem

class BuyListAdapter  : BaseRecyclerViewAdapter<GetCallListModelResopnseItem, IvBuyListBinding>(){

    override fun getLayout() = R.layout.iv_buy_list

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<IvBuyListBinding>,
        position: Int
    ) {
        holder.binding.apply {
            item = items[position]


            root.setOnClickListener {
                try {
                    listener?.invoke(it, items[position], position)

                } catch (e: IndexOutOfBoundsException) {
                    print(e.localizedMessage)
                }

            }

        }

    }

}