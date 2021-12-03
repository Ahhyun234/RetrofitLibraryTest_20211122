package com.neppplus.retrofitlibrarytest_20211122

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.coroutine.TedPermission
import com.neppplus.retrofitlibrarytest_20211122.databinding.ActivityEditReviewBinding
import com.neppplus.retrofitlibrarytest_20211122.datas.BasicResponse
import com.neppplus.retrofitlibrarytest_20211122.datas.ProductData
import com.neppplus.retrofitlibrarytest_20211122.utils.GlobalData
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class EditReviewActivity : BaseActivity() {

    lateinit var binding: ActivityEditReviewBinding
    lateinit var mProductData : ProductData
    val mInputTagList = ArrayList<String>()
//    대표 사진을 가지러 간다
    val REQ_FOR_GALLERY = 1004
//    선택한 이미지의 URI를 담아줄 변수
    var mSelectedThubnailUri : Uri? =null //처음에는 선택한 이미지가 없다

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_edit_review)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        val ocl = View.OnClickListener {
//            권한쳌크

            val pl = object : PermissionListener{
                override fun onPermissionGranted() {
//                    권한이 있을 때 -> 사진을 가지러 이동
                    val myIntent = Intent()
                    myIntent.action = Intent.ACTION_PICK
                    myIntent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
                    startActivityForResult( myIntent, REQ_FOR_GALLERY )

                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    Toast.makeText(mContext, "갤러리 권한이 없습니다.", Toast.LENGTH_SHORT).show()
                }

            }
            TedPermission.create()
                .setPermissionListener(pl).setPermissions(Menifest.permission.READ_EXTERNAL_STORAGE).check()
        }
        binding.txtEmptyImg.setOnClickListener(ocl)

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

//                태그목록 보여줄 레이아웃에 택스트뷰를 생성 (코틀린에서 텍스트뷰 생성)

                val tagBox = LayoutInflater.from(mContext).inflate(R.layout.tag_list_item,null)
                val txtTag = tagBox.findViewById<TextView>(R.id.txtTag)
                txtTag.text = "#${tag}"


                binding.tagListLayout.addView(tagBox)

//                입력값 초기화
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


//           태그 임시
            val tagStr = ""

//            선택한 사진 첨부


//            선택한 사진 추출 => mSelectedThumbnailUri 에 담겨있다
            if (mSelectedThubnailUri==null){
                Toast.makeText(mContext, "첨부하세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            flqb wkrtjd: 2가지 데이터 (MultyPart) 보내자
//            1. 일반 파라미터들


//            2. 이미지 등 파일 파라미터 들
            val productIdBody = RequestBody.create(MediaType.parse("text/plain"),mProductData.id.toString())
            val productTitleBody = RequestBody.create(MediaType.parse("text/plain"),inputTitle)
            val productContentBody = RequestBody.create(MediaType.parse("text/plain"),inputContent)
            val productScoreBody = RequestBody.create(MediaType.parse("text/plain"),rating.toString())
            val productTagListBody = RequestBody.create(MediaType.parse("text/plain"),tagStr)



            apiService.postRequestReview(
//                1. 일반
//                2. 이미지 등 파일 : MultyPart.part

            ).enqueue(object :Callback<BasicResponse>{
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_FOR_GALLERY){
            if (resultCode == RESULT_OK){
//                첨부한 이미지(data.data) uri 를 저장 -> 업로드 시에 첨부

                mSelectedThubnailUri = data!!.data

//                선택한 이미지를 이미지 뷰에 표시
                Glide.with(mContext).load(mSelectedThubnailUri).into(binding.imgSelected)
//                선택 유도 문구 숨김 & 선택한 이미지뷰 표시
                binding.txtEmptyImg.visibility = View.GONE
                binding.imgSelected.visibility = View.VISIBLE
            }
        }
    }

}