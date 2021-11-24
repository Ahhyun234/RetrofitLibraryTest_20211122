package com.neppplus.retrofitlibrarytest_20211122.datas

import com.google.gson.annotations.SerializedName

class UserData(

    var id: Int,
    var email: String,
    var provider : String,
    var uid : String?,

//    서버의 이름표와 변수의 이름이 다를때
    @SerializedName("nick_name")
    var nickname: String,
    @SerializedName("profile_img")
    var profileImageURL: String

) {
}