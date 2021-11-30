package com.neppplus.retrofitlibrarytest_20211122.adapters

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.neppplus.retrofitlibrarytest_20211122.MainActivity
import com.neppplus.retrofitlibrarytest_20211122.R
import com.neppplus.retrofitlibrarytest_20211122.datas.BannerData
import com.neppplus.retrofitlibrarytest_20211122.datas.ReviewData
import java.util.*
import kotlin.collections.ArrayList

class MainRecyclerViewAdapter(val mContext : Context , val mList : List<ReviewData> ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    화면 상단에 보여줄 배너 목록을 담고있는 ArrayList만들기
    val mBannerList = ArrayList<BannerData>()

//    상단 뷰페이저 어댑터 변수 -> 객체는 bind에서 생성
    lateinit var bannerViewPagerAdapter: BannerViewPagerAdapter



//    두가지 뷰홀더가 필요함. -> 0번칸 : 상단부(header) xml / 나머지 칸(item) : 리뷰모양 XML
    inner class headerViewHolder(row:View): RecyclerView.ViewHolder(row){

        val imgCategory1 = row.findViewById<ImageView>(R.id.imgCategory1)
        val bannerViewPager = row.findViewById<ViewPager>(R.id.bannerViewPager)

    fun bind(){

//       배너 페이지 어댑터 생성
//        1. fm => 화면 =>supportFragmentManager
//        2. 배너 리스트 => Fragment에서 배너 목록 API 호출 -> 파싱 된것을 받아오자

        bannerViewPagerAdapter = BannerViewPagerAdapter((mContext as MainActivity).supportFragmentManager, mBannerList)

        bannerViewPager.adapter=bannerViewPagerAdapter

//        완성된 배너 어댑터에 2초마다 자동으로 다음 그림으로 넘어가게
//        >할일 코드 생성
            val nextPage = {

            } //Runnable (할일이 담긴 코드)

//        타이머 안에서 할 일을 UI스레드로 전달해주는 도구 만들기 (handler)
        val myHandler = Handler(Looper.getMainLooper())

//        Timer클래스 활용 => 할일(코드)를 2초 마다 반복

        val timer = Timer()
        timer.schedule(object :TimerTask(){
            override fun run() {
//                반복적으로 수행할 코드 => UI를 조작하면 앱이 죽음
//                ui스레드에 NextPage에 작힌 할일 실행하도록 넘겨주자
                myHandler.post(nextPage)

            }

        },2000 ,2000)

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