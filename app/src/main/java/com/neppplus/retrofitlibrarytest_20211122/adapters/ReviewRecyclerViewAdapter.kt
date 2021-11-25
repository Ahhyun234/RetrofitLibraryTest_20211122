package com.neppplus.retrofitlibrarytest_20211122.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.retrofitlibrarytest_20211122.R
import com.neppplus.retrofitlibrarytest_20211122.datas.ReviewData
import java.text.SimpleDateFormat

class ReviewRecyclerViewAdapter (val mContext: Context, val mList:List<ReviewData>) : RecyclerView.Adapter<ReviewRecyclerViewAdapter.ReviewViewHolder>()  {


    inner class ReviewViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val txtReviewTitle = itemView.findViewById<TextView>(R.id.txtReviewTitle)
        val txtReviewContent = itemView.findViewById<TextView>(R.id.txtReviewContent)
        val txtReviewProductName   = itemView.findViewById<TextView >(R.id.txtReviewProductName)
        val txtReviewWriterName   = itemView.findViewById<TextView >(R.id.txtReviewWriterName)
        val txtReviewProductRating   = itemView.findViewById<TextView >(R.id.txtReviewProductRating)
        val txtcreatedAt   = itemView.findViewById<TextView >(R.id.txtcreatedAt)



        fun bind(data: ReviewData ){

            txtReviewTitle.text = data.title
            txtReviewContent.text = data.content
            txtReviewProductName.text = data.product.name
            txtReviewProductRating.text = data.score.toString()
            txtReviewWriterName.text = data.user.nickname

            val sdf = SimpleDateFormat("yyyy.M.D a H:mm")
            txtcreatedAt.text = sdf.format(data.createdAt)



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