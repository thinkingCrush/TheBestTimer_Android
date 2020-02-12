package kr.Tcrush.TheBestTimer.Tool

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

class VibratorSupport {
    fun doVibrator(context: Context, miiliseconds: Int) {
        try {
            val vibrator =
                context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(300, -1))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}