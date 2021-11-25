package com.neppplus.retrofitlibrarytest_20211122.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neppplus.retrofitlibrarytest_20211122.EditReviewActivity
import com.neppplus.retrofitlibrarytest_20211122.R
import com.neppplus.retrofitlibrarytest_20211122.datas.ProductData

class ProductRecyclerViewAdapter(val mContext:Context,val mList:List<ProductData>) : RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        val rootLayout = view.findViewById<LinearLayout>(R.id.rootLayout)
        val imgStoreLogo = view.findViewById<ImageView >(R.id.imgStoreLogo)
         val imgProductImage = view.findViewById<ImageView>(R.id.imgProductImage)
        val txtProductName = view.findViewById<TextView>(R.id.txtProductName)
        val txtStoreName = view.findViewById<TextView>(R.id.txtStoreName)
        val txtProductPrice = view.findViewById<TextView>(R.id.txtProductPrice)
        val btnWriteReview = view.findViewById<Butten>(R.id.btnWriteReview)
        

        fun bind(data: ProductData){

            txtProductName.text = data.store.name
            txtStoreName.text = data.store.name
            Glide.with(mContext).load(data.imageUrl).into(imgProductImage)
            Glide.with(mContext).load(data.imageUrl).into(imgStoreLogo)

//            상품 데이터에 가격을 가공해주는 함수를 추가해보자 (productData에 추가하고 돌아오기)
            txtProductPrice.text = data.getFormatedPrice()
            
            rootLayout.setOnClickListener{
                Toast.makeText(mContext, "${data.name} 상품 클릭 됨", Toast.LENGTH_SHORT).show()
            }

            rootLayout.setOnClickListener {
                val alert = AlertDialog.Builder(mContext)
                alert.setTitle("상품 삭제")
                alert.setMessage("정말 삭제하겠습니ㄸ까")
                alert.setPositiveButton("확인",null)
                alert.setNegativeButton("확인",null)
                alert.show()
                return@setOnClickListener true
            }
            btnWriteReview.setOnClickLisner{

                val myIntent = Intent(mContext, EditReviewActivity::class.java)
                startActivity(myIntent)





            }
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