package kr.Tcrush.TheBestTimer.Tool

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import kr.Tcrush.TheBestTimer.MainActivity

class GyroscopeListener : SensorEventListener{



    companion object{
        var faceOut : Boolean = false

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {


        val accX = event!!.values[0].toDouble()
        val accY = event.values[1].toDouble()
        val accZ = event.values[2].toDouble()

        val angleXZ = Math.atan2(accX, accZ) * 180 / Math.PI
        val angleYZ = Math.atan2(accY, accZ) * 180 / Math.PI

        if((angleXZ >=170 || angleXZ <= - 170) && (angleYZ >= 170 || angleYZ<=-170)){
            if(!faceOut){
                faceOut = true
                MainActivity.mainContext?.let { VibratorSupport().doVibrator(it,300) }
            }
        }else{
            faceOut = false
        }
    }


}
