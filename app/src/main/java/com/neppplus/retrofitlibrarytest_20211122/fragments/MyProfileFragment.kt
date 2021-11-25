package com.neppplus.retrofitlibrarytest_20211122.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.neppplus.retrofitlibrarytest_20211122.R
import com.neppplus.retrofitlibrarytest_20211122.databinding.FragmentMyProfileBinding
import com.neppplus.retrofitlibrarytest_20211122.datas.BasicResponse
import com.neppplus.retrofitlibrarytest_20211122.utils.ContextUtil
import com.neppplus.retrofitlibrarytest_20211122.utils.GlobalData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyProfileFragment : BaseFragment() {

    lateinit var binding: FragmentMyProfileBinding

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

    override fun setupEvents() {

        binding.btnEditProfile.setOnClickListener {
//            닉네임 변경 입력(Alert diallog + 커스텀 부) + API 호출

            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("닉네임 변경")
//            xml의 내부 ui 접근 활용 -> Inflate 해와서 사용하자

            val customView = LayoutInflater.from(mContext).inflate(R.layout.my_custom_alert,null)
            alert.setView(customView)

            val edtNickname = customView.findViewById<EditText>(R.id.edtNickname)


            alert.setNegativeButton("취소",null)

            alert.setPositiveButton("확인",DialogInterface.OnClickListener {
                    dialogInterface, i ->

                val inputNickName = edtNickname.text.toString()
            })
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