package com.neppplus.retrofitlibrarytest_20211122.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.neppplus.retrofitlibrarytest_20211122.R
import com.neppplus.retrofitlibrarytest_20211122.databinding.FragmentMyProfileBinding
import com.neppplus.retrofitlibrarytest_20211122.datas.BasicResponse
import com.neppplus.retrofitlibrarytest_20211122.utils.ContextUtil
import com.neppplus.retrofitlibrarytest_20211122.utils.GlobalData
import com.neppplus.retrofitlibrarytest_20211122.utils.URIPathHelper
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.security.Permission

class MyProfileFragment : BaseFragment() {

    lateinit var binding: FragmentMyProfileBinding
    var REQUEST_FOR_GALLERY =1000

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate( inflater, R.layout.fragment_my_profile, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_FOR_GALLERY){
            if (resultCode==Activity.RESULT_OK){
                val selectedImageUri = data?.data!!
                Log.d("선택된 이미지", selectedImageUri.toString())

//                URI를 실제로 첨부 가능한 파일로 변환해야함
//                  uri File형태로 변환 -> 그 실제 경로를 추출해서 Retrofit에 첨부할 수 있게 됨

                val file = File(URIPathHelper().getPath(mContext,selectedImageUri))


//                파일을 레트로핏에서 첨부가 가능한 Request Body형태로 가공

                val fileRequestBody = RequestBody.create(MediaType.get("image/+"),file)
//                실제 첨부하는 데이터로 변경
                val body = MultipartBody.Part.createFormData("profile_image","myFile.jpg",fileRequestBody)
                apiService.putRequestProfileImage(body).enqueue(object :Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful){
                            Toast.makeText(mContext, "프로필 사진 변경", Toast.LENGTH_SHORT).show()
//                            사용자가 선택한 사진을 프로필 사진 뷰에 반영
                            Glide.with(mContext).load(selectedImageUri).into(binding.imgProfile)
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }

                })

            }
        }

    }
    override fun setupEvents() {

        binding.imgProfile.setOnClickListener {

//            실제 파일 경로 읽는 권한 필요(업로드가 가능해짐)
            val pl = object : PermissionListener {
                override fun onPermissionGranted() {
//                갤러리로 사진 가지러 이동
                val myIntent tnpntpntnp
            }
        }


//            갤러리(안드로이드 제공) 로 사진 가지로 이동
            val myIntent = Intent()
            myIntent.action = Intent.ACTION_PICK
            myIntent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
            startActivityForResult(myIntent,REQUEST_FOR_GALLERY )
        }

        binding.btnEditProfile.setOnClickListener {
//            닉네임 변경 입력(Alert diallog + 커스텀 부) + API 호출

            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("닉네임 변경")
//            xml의 내부 ui 접근 활용 -> Inflate 해와서 사용하자

            val customView = LayoutInflater.from(mContext).inflate(R.layout.my_custom_alert,null)
            alert.setView(customView)

            val edtNickname = customView.findViewById<EditText>(R.id.edtNickname)


           alert.setPositiveButton("확인",DialogInterface.OnClickListener {dialogInterface, i ->

                val inputNickName = edtNickname.text.toString()

                apiService.patchRequestEditUserInfo("nickname",inputNickName).enqueue(object :Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful){
                            val br = response.body()!!
//                            토큰값 추출 -> 다시 저장 (사용자 정보가 바꼈으면 토큰이 바꼈을 수 있음)

                            val token = br.data.token
                            ContextUtil.setToken(mContext,token)

                            GlobalData.logInUser = br.data.user

                            Toast.makeText(mContext, "닉네임 변경 성공", Toast.LENGTH_SHORT).show()

                            getMyInfoFromServer()

                        }
                        else{

                            val jsonObj  =  JSONObject(  response.errorBody()!!.string()  )
                            Log.e("닉네임변경실패", jsonObj.toString())

                            val message = jsonObj.getString("message")

                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

                        }

                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {



                    }

                })

            })
            alert.setNegativeButton("취소",null)
            alert.show()

        }

    }

    override fun setValues() {

        getMyInfoFromServer()

        binding.txtNickname.text = GlobalData.logInUser!!.nickname
        Glide.with(mContext).load(GlobalData.logInUser!!.profileImageURL).into(binding.imgProfile)
    }

    fun getMyInfoFromServer() {

        apiService.getRequestMyInfo().enqueue( object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful) {

                    val br = response.body()!!

                    binding.txtNickname.text = br.data.user.nickname
                    Glide.with(mContext).load(br.data.user.profileImageURL).into(binding.imgProfile)

                    when(GlobalData.logInUser!!.provider){

                        "facebook"-> {
                            binding.imgProvider.setImageResource(R.drawable.facebook_logo)
                            binding.imgProvider.visibility
                        }
                        "kakao"-> {
                            binding.imgProvider.setImageResource(R.drawable.kakao_logo)
                            binding.imgProvider.visibility
                        }
                        else-> {
                            binding.imgProvider.visibility = View.GONE
                        }
                    }

                }

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })

    }

}