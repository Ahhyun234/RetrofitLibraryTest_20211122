package com.neppplus.retrofitlibrarytest_20211122.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.retrofitlibrarytest_20211122.R
import com.neppplus.retrofitlibrarytest_20211122.datas.ProductData

class ProductRecyclerViewAdapter(val mContext:Context,val mList:List<ProductData>) : RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(view:View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val row = LayoutInflater.from(mContext).inflate(R.layout.product_list_item,parent,false)
        return ProductViewHolder(row)

    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

    }

    override fun getItemCount()= mList.size
}