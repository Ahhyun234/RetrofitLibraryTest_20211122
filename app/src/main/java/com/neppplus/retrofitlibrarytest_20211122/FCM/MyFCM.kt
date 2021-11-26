package com.neppplus.retrofitlibrarytest_20211122.FCM

import android.os.Looper
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFCM : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
//    실제로 푸시 신호가 왔을 때 할 행동을 적기 p0 :어떤 메세지 인지 담아주는 변수

        val text = p0.notification!!.title

//        UI 스레드에서 토스트를 듸워햐 함 => 핸들러 활용

        val myHandler = android.os.Handler(Looper.getMainLooper())

        myHandler.post{
            Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()

        }




    }
}