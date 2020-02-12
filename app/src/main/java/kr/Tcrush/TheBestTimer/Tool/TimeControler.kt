package kr.Tcrush.TheBestTimer.Tool

import android.os.Handler
import android.os.Message
import android.widget.TextView
import java.lang.Exception

class TimeControler {
    //여기서 time TextView 객체 받아와서 set 해주면 될듯

    companion object{
        var timeHandler : Handler?= null
    }
    fun setTimeHandler(tv_mainTime : TextView?){
        try{
            timeHandler = object:Handler(){
                override fun handleMessage(msg: Message) {
                    when(msg.what){
                        1 -> {tv_mainTime?.setText(msg.obj.toString()) }

                    //
                    }
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun setTimeText(date : String){
        timeHandler?.obtainMessage(1,date)?.sendToTarget()
    }
}