package com.neppplus.retrofitlibrarytest_20211122.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neppplus.retrofitlibrarytest_20211122.R
import com.neppplus.retrofitlibrarytest_20211122.datas.ProductData

class ProductRecyclerViewAdapter(val mContext:Context,val mList:List<ProductData>) : RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(view:View) : RecyclerView.ViewHolder(view) {
         val imgProductImage = view.findViewById<ImageView>(R.id.imgProductImage)
        val txtProductName = view.findViewById<TextView>(R.id.txtProductName)
        val txtStoreName = view.findViewById<TextView>(R.id.txtStoreName)

        fun bind(data: ProductData){
            txtProductName.text = data.store.name
            txtStoreName.text = data.store.name
            Glide.with(mContext).load(data.imageUrl).into(imgProductImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val row = LayoutInflater.from(mContext).inflate(R.layout.product_list_item,parent,false)
        return ProductViewHolder(row)

    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        holder.bind( mList [position])

    }

    override fun getItemCount()= mList.size
}