package com.neppplus.retrofitlibrarytest_20211122.datas

import java.io.Serializable

class ReviewData(

    var id : Int,
    var title : String,
    var content : String,
    var score : Double,

//    <이미 있는 데이터 사용하기>

    var user : UserData,
    var product : ProductData,

) : Serializable  {
}


