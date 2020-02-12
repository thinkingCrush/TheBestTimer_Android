package kr.Tcrush.TheBestTimer

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kr.Tcrush.TheBestTimer.Tool.GyroscopeListener
import kr.Tcrush.TheBestTimer.Tool.TimeControler
import kr.Tcrush.TheBestTimer.View.MainFragmentView

class MainActivity : AppCompatActivity() {

    /**
     * 1. 타이머 시작하여 집중하고, 5분간 휴식시간
     * 2. 뷰 구조는 프레그먼트, 뷰페이저
     * 3. 센서를 이용해 화면 엎어놓은거 감지
     * */
    companion object{
        var mainContext : Context? = null
        var tv_mainTime : TextView? = null
    }

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainContext = baseContext
        initSensor(baseContext)
        mainChangeMenu(MainFragmentView())
        initView()
    }


    fun initView(){
        tv_mainTime = findViewById(R.id.tv_mainTime)
        TimeControler().setTimeHandler(tv_mainTime)

    }



    override fun onResume() {
        super.onResume()
        registerAccel()
    }

    override fun onPause() {
        super.onPause()
        unRegisterAccel()
    }

    private var time: Long = 0
    override fun onBackPressed() {
        if(System.currentTimeMillis() - time >= 2000){
            time = System.currentTimeMillis()
            Toast.makeText(baseContext,getString(R.string.close_app),Toast.LENGTH_SHORT).show()
        }else {
            finishAffinity()
            System.runFinalization()
            System.exit(0)
        }
    }


    fun mainChangeMenu(changeFragment: Fragment?) {
        try {
            val fragmentManager =
                supportFragmentManager
            val fragment =
                fragmentManager.findFragmentByTag("Fragment")
            if (fragment != null) {
                try {
                    fragmentManager?.popBackStackImmediate(
                        "Fragment",
                        FragmentManager.POP_BACK_STACK_INCLUSIVE
                    )
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
            if (fragmentManager != null) {
                val fragmentTransaction =
                    fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_container, changeFragment!!, "Fragment")
                try {
                    fragmentTransaction.commitNowAllowingStateLoss()
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                    fragmentTransaction.commitNow()
                }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 센서부
     * */
    var sensorManager : SensorManager? = null
    var accelSensor : Sensor?= null
    fun initSensor(context: Context){
        try{
            sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager?
            accelSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun registerAccel(){
        try{
            sensorManager?.registerListener(GyroscopeListener(),accelSensor,SensorManager.SENSOR_DELAY_NORMAL)
        }catch (e : Exception){

        }
    }

    fun unRegisterAccel(){
        try{
            sensorManager?.unregisterListener(GyroscopeListener())
        }catch (e : Exception){

        }
    }



}
