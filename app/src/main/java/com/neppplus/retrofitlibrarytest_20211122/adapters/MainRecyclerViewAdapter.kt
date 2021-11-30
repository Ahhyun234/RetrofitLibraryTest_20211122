package com.neppplus.retrofitlibrarytest_20211122.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.neppplus.retrofitlibrarytest_20211122.R
import com.neppplus.retrofitlibrarytest_20211122.datas.ReviewData

class MainRecyclerViewAdapter(val mContext : Context , val mList : List<ReviewData> ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    두가지 뷰홀더가 필요함. -> 0번칸 : 상단부(header) xml / 나머지 칸(item) : 리뷰모양 XML
    inner class headerViewHolder(row:View): RecyclerView.ViewHolder(row){

        val imgCategory1 = row.findViewById<ImageView>(R.id.imgCategory1)
        val bannerViewPager = row.findViewById<ViewPager>(R.id.bannerViewPager)

    fun bind(){
//        bannerViewPager.adapter =

        imgCategory1.setOnClickListener {

        }
    }


    }
    inner class itemViewHolder(row: View): RecyclerView.ViewHolder(row){

        val txtReviewproductName = row.findViewById<TextView>(R.id.txtReviewproductName)
        val txtReviewWriterName = row.findViewById<TextView>(R.id.txtReviewWriterName)
        val imgProductImage = row.findViewById<ImageView>(R.id.imgProductImage)

        fun bind(data: ReviewData){
            txtReviewWriterName.text = data.user.nickname
            txtReviewproductName.text = data.product.name
//            Glide.with(mContext).load()
        }

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

        when(holder){
            is headerViewHolder -> {

                holder.bind()


            }
            is itemViewHolder -> {
//                리뷰 아이템 목록의 바인딩
                holder.bind(mList[position-1])
            }
        }

    }

    override fun getItemCount()= mList.size +1

}