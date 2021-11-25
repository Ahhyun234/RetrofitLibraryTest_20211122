package com.neppplus.retrofitlibrarytest_20211122.api

import android.content.ContentUris
import android.content.Context
import com.neppplus.retrofitlibrarytest_20211122.utils.ContextUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerAPI {

//    Retrofit 타입 객체 하나만만 생성-> 모두가 공유.

    companion object {

//        기본 접속 서버
        private var BASE_URL = "https://api.gudoc.in"
//        private var BASE_URL = "https://keepthetime.xyz"

        private var retrofit : Retrofit? = null  // 앱이 처음 켜질때는 없다. => 한번만 만들고 함수를 통해서 공유.

        fun getRetrofit(context:Context) : Retrofit {

            if (retrofit == null) {
//                통신 담당 객체를 만들지 않았다면 => 없을때만 새로 한번만들자.


//              레트로핏 함수 안에 추가 설정 진행
//    1. 토큰 자동 첨부 (Retrofit 변수를 더 가공)
//    API 요청이 들어오면 가로채서 헤더를 추가해주자 *** => 다시 API 요청 진행
                val interceptor = Interceptor{
                    with(it){
                        val newRequest = request().newBuilder()
                            .addHeader("X-Http-Token", ContextUtil.getToken(context))
                            .build()
//                        새 리퀘스트로 나머지 동작 이어가기
                        proceed(newRequest)
                    }

                }

//                Retrofit은 Okhttp의 확장판 => 클라이언트의 역할 : okHttp 클래스 활용
//                새 통신 클라이언트 제작 (API 요청에 헤더를 붙이는)

                val myClient = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)  // 어느 서버로 접속?
                    .addConverterFactory(GsonConverterFactory.create())  // 파싱을 자동 도구로 활용.
                    .client(myClient)
                    .build()

            }

            return retrofit!!

        }

    }


}