package com.neppplus.retrofitlibrarytest_20211122.datas

import com.google.gson.annotations.SerializedName

class BannerData (
    val id : Int,
    @SerializedName("img_url")
    val displayImgUrl : String

        ) {
}