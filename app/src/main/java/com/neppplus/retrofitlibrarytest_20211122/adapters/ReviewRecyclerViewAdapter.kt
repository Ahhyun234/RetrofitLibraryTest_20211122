package com.neppplus.retrofitlibrarytest_20211122.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.retrofitlibrarytest_20211122.R
import com.neppplus.retrofitlibrarytest_20211122.datas.ReviewData

class ReviewRecyclerViewAdapter (val mContext: Context, val mList:List<ReviewData>) : RecyclerView.Adapter<ReviewRecyclerViewAdapter.ReviewViewHolder>()  {


    inner class ReviewViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val txtReviewTitle = itemView.findViewById<TextView>(R.id.txtReviewTitle)

        fun bind(data: ReviewData ){

            txtReviewTitle.text = data.title

//           txt와 파싱한 내용 연결

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {

        val  row  = LayoutInflater.from(mContext).inflate(R.layout.review_item,  parent, false)
        return  ReviewViewHolder( row )

    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {

        holder.bind( mList [position])

    }

    override fun getItemCount()=mList.size


}