package com.astutesol.notesappmvvm.adapter
import com.astutesol.notesappmvvm.R
import com.astutesol.notesappmvvm.databinding.IvCallListBinding
import com.astutesol.notesappmvvm.models.GetCallListModelResopnseItem

class CallListAdapter : BaseRecyclerViewAdapter<GetCallListModelResopnseItem, IvCallListBinding>(){

    override fun getLayout() = R.layout.iv_call_list

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<IvCallListBinding>,
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