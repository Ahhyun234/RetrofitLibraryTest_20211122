package com.neppplus.retrofitlibrarytest_20211122.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.retrofitlibrarytest_20211122.R
import com.neppplus.retrofitlibrarytest_20211122.datas.ProductData
import com.neppplus.retrofitlibrarytest_20211122.datas.ReviewData

class ReviewRecyclerViewAdapter (val mContext: Context, val mList:List<ReviewData>) : RecyclerView.Adapter<ReviewRecyclerViewAdapter.ReviewViewHolder>()  {


    inner class ReviewViewHolder(view:View):RecyclerView.ViewHolder(view){

        val txtReviewTitle = view.findViewById<TextView>(R.id.txtReviewTitle)

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
//        위치에맞는 데이터 / ViewHolder클래스 이용 -> 실제 데이터 반영

        holder.bind( mList [position])

    }

    override fun getItemCount()=mList.size


}