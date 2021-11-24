package com.neppplus.retrofitlibrarytest_20211122.datas

import com.google.gson.annotations.SerializedName

class ProductData(

    var id: Int,
    var name: String,
    var price : Int,
    @SerializedName("image_url")
    var imageUrl : String,


    var store: StoreData,
    @SerializedName("small_category")
    var smallCategory : SmallCategoryData




) {
}