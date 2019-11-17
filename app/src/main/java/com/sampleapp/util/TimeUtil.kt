package com.sampleapp.util

import java.text.SimpleDateFormat

class TimeUtil {
    companion object{
        fun convertDatetoRequiredformat(date_time:String):String{

            val timeStampFormat = SimpleDateFormat("yyyy-mm-dd HH:mm")
             var date = timeStampFormat.parse(date_time);
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")


            return simpleDateFormat.format(date)
        }
    }
}