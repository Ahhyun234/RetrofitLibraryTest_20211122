package com.neppplus.retrofitlibrarytest_20211122.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.retrofitlibrarytest_20211122.R
import com.neppplus.retrofitlibrarytest_20211122.datas.SmallCategoryData

class CategoryRecyclerAdapter(val mContext: Context, val mList: List<SmallCategoryData>) : RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder>() {

//     클래스 안에서만 쓰는 클래스 (inner Class) 를 만들것

    inner class CategoryViewHolder(view : View) : RecyclerView.ViewHolder(view){

//이 내부의 용도 : xml -> UI를 ㅈ찾아내서 데이터를 반영하는 기능
        val txtCategoryName = view.findViewById<TextView>(R.id.txtCategoryName)

        fun bind(data: SmallCategoryData){
            txtCategoryName.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

    val row= LayoutInflater.from(mContext).inflate(R.layout.category_list_item,parent,false)
        return CategoryViewHolder(row)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val data = mList[position]

        holder.bind(data)

    }

    override fun getItemCount() = mList.size
}