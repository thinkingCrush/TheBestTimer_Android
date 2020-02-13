package kr.Tcrush.TheBestTimer.Tool

import kr.Tcrush.TheBestTimer.MainActivity

class ButtonClickEffect {
    fun buttonClick(){
        try{
            MainActivity.mainContext?.let { VibratorSupport().doVibrator(it,30) }
        }catch (e:Exception){
            e.printStackTrace()
        }

    }
}