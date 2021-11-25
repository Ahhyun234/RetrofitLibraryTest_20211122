package com.neppplus.retrofitlibrarytest_20211122.api

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

class DateDeserializer: JsonDeserializer<Date>{
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date {

//        서버가 주는 항목중에 Date로 파싱하려는 항목을 일단 String으로 받아오자
        val dateStr = json?.asString

        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//        서버가 준 String을 date 형식으로 변환 (parse)

        val date = sdf.parse(dateStr)!!

//        만들어진 date변수에는 서버가 알려준 시간이 GMT로 분석되어 들어감
//        한국은 GML +9 로 세티외어있음 => 맞춰주자

        val now = Calendar.getInstance()
        date.time += now.timeZone.rawOffset //시차를 ms단위로 계산




//       파싱 결과로 완성된 date 선정
        return date

    }
}