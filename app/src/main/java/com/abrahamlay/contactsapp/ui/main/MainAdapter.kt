package com.abrahamlay.contactsapp.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import com.abrahamlay.contactsapp.R
import com.abrahamlay.contactsapp.model.DataItem
import com.abrahamlay.contactsapp.ui.BaseListAdapter
import com.abrahamlay.contactsapp.ui.ItemClickListener
import com.bumptech.glide.Glide

/**
 * Created by Abraham on 18/10/2018.
 */
class MainAdapter(private val context: Context?,
                      private val itemList:List<DataItem>,
                      private val listener: ItemClickListener
                    ) : BaseListAdapter<MainAdapter.TestViewHolder>(itemList) {
    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.bindData(itemList[position], position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder =
        TestViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.contact_item_list,
                parent,
                false), listener)

    class TestViewHolder(itemView: View, private val listener: ItemClickListener): RecyclerView.ViewHolder(itemView){
        fun bindData(item: DataItem?, position:Int){
            Glide.with(itemView).load(item?.photo).into(itemView.findViewById(R.id.iv_pict))
            itemView.findViewById<TextView>(R.id.tv_first_name).text=item?.firstName
            val icDelete=itemView.findViewById<ImageView>(R.id.iv_delete)
            itemView.setOnClickListener {listener.onItemClicked(itemView,item as Any ,position)}
            icDelete.setOnClickListener {listener.onItemClicked(icDelete,item as Any ,position)}
        }
    }
}