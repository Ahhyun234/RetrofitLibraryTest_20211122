package com.neppplus.retrofitlibrarytest_20211122.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MainRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    두가지 뷰홀더가 필요함. -> 0번칸 : 상단부(header) xml / 나머지 칸(item) : 리뷰모양 XML
    inner class headerViewHolder(row:View): RecyclerView.ViewHolder(row){

    }
    inner class itemViewHolder(row: View): RecyclerView.ViewHolder(row){

    }

}