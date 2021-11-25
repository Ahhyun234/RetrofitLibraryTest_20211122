package com.neppplus.retrofitlibrarytest_20211122.datas

import java.io.Serializable

class ReviewData(

    var id : Int,
    var title : String,
    var content : String,
    var score : Double,
    var user_id : Int,

) : Serializable  {
}


