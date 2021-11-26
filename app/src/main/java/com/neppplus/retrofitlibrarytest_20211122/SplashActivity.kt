package com.neppplus.retrofitlibrarytest_20211122

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.google.firebase.messaging.FirebaseMessaging
import com.neppplus.retrofitlibrarytest_20211122.databinding.ActivitySplashBinding
import com.neppplus.retrofitlibrarytest_20211122.datas.BasicResponse
import com.neppplus.retrofitlibrarytest_20211122.utils.GlobalData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : BaseActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {




    }

    override fun setValues() {

//        //푸쉬용 기기 토큰 발급 요청
        FirebaseMessaging.getInstance().token.addOnCompleteListener{

            if (it.isSuccessful){
                val deviceToken = it.result

                Log.d("토큰",deviceToken!!)
            }



        }


        apiService.getRequestMyInfo().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful ){
                    GlobalData.logInUser =  response.body()!!.data.user

                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })

        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed( {

            val myIntent: Intent

            if ( GlobalData.logInUser != null ) {
                myIntent = Intent(mContext, MainActivity::class.java)
            }
            else {
                myIntent = Intent(mContext, LoginActivity::class.java)
            }

            startActivity(myIntent)

            finish()

        }, 1500 )

    }

}