package com.neppplus.retrofitlibrarytest_20211122.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.retrofitlibrarytest_20211122.R

class MainRecyclerViewAdapter(val mContext : Context , val mList : List ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    두가지 뷰홀더가 필요함. -> 0번칸 : 상단부(header) xml / 나머지 칸(item) : 리뷰모양 XML
    inner class headerViewHolder(row:View): RecyclerView.ViewHolder(row){

    }
    inner class itemViewHolder(row: View): RecyclerView.ViewHolder(row){

    }

    val HEADER_VIEW_TYPE =1000
    val REVIEW_ITEM_TYPE =1001

    override fun getItemViewType(position: Int): Int {

//        position이 0 => 맨 윗칸
//        position이 그위 => 리뷰 아이템
        return when(position){
            0->HEADER_VIEW_TYPE
            else ->REVIEW_ITEM_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){
            HEADER_VIEW_TYPE -> {
                val row = LayoutInflater.from(mContext).inflate(R.layout.main_recycler_item_top_view,parent,false)
                headerViewHolder(row)

            }
            else-> {
                val row= LayoutInflater.from(mContext).inflate(R.layout.main_recyclerview_review_item,parent,false)
                itemViewHolder(row)
//                리뷰 아이템
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount()= mList.size +1

}