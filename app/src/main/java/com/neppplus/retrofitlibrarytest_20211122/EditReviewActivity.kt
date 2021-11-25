package com.neppplus.retrofitlibrarytest_20211122

import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.neppplus.retrofitlibrarytest_20211122.databinding.ActivityEditReviewBinding
import com.neppplus.retrofitlibrarytest_20211122.datas.BasicResponse
import com.neppplus.retrofitlibrarytest_20211122.datas.ProductData
import com.neppplus.retrofitlibrarytest_20211122.utils.GlobalData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class EditReviewActivity : BaseActivity() {

    lateinit var binding: ActivityEditReviewBinding
    lateinit var mProductData : ProductData
    val mInputTagList = ArrayList<tag>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_edit_review)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        한글자를 입력할 때 마다, 스페이스를 넣었는지 검사

        binding.edtTag.addTextChangedListener {

            val nowText = it.toString()

            if (nowText == ""){
                return@addTextChangedListener
            }
            Log.d("입력값",it.toString())

//            지금 입력된 글자에 스페이스가 있는가?

            if (nowText.last() == ' ' ){

                Log.d("입력값","스페이스가 들어옴")

//                입력된 값을 태그로 등록(공백 제거 후 사용)
               val tag = nowText.replace(" ", "")
//                태그 목록으로 추가해보자
                mInputTagList.add(tag)
                binding.edtTag.setText("")


            }

        }

        binding.btnWrite.setOnClickListener{

            for (tag in mInputTagList){
                Log.d("입력태그",tag)
            }
            return@setOnClickListener

            val inputTitle = binding.edtReviewTitle.text.toString()
            val inputContent = binding.edtReviewContent.text.toString()

            val rating = binding.ratingBar.rating.toInt()
            Log.d("평점 점수", rating.toString())

            apiService.postRequestReview(mProductData.id,inputTitle,inputContent,rating).enqueue(object :Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })
        }

    }

    override fun setValues() {

        mProductData = intent.getSerializableExtra("product") as ProductData
        binding.txtProductName.text = mProductData.name
        binding.txtUserNickname.text = GlobalData.logInUser!!.nickname

//        오늘 날짜를 가져와서 이걸 2021.11.25 형태로 가공 -> 텍스트뷰에 반영
//        1. 오늘 날짜?
        val now = Calendar.getInstance() //현재 일시 자동 기록
//        원하는 형채로 가공
        val sdf = SimpleDateFormat("yyyy.M.D")
        val nowString = sdf.format(now.time)

        binding.txtToday.text = nowString

    }
}