package kr.Tcrush.TheBestTimer

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import kotlinx.android.synthetic.main.activity_main.*
import kr.Tcrush.TheBestTimer.Tool.ButtonClickEffect
import kr.Tcrush.TheBestTimer.Tool.Dlog
import kr.Tcrush.TheBestTimer.Tool.GyroscopeListener
import kr.Tcrush.TheBestTimer.Tool.TimeControler
import kr.Tcrush.TheBestTimer.View.FragmentA
import kr.Tcrush.TheBestTimer.View.MainPagerAdapter

class MainActivity : AppCompatActivity(), View.OnClickListener {

    /**
     * 1. 타이머 시작하여 집중하고, 5분간 휴식시간
     * 2. 뷰 구조는 프레그먼트, 뷰페이저
     * 3. 센서를 이용해 화면 엎어놓은거 감지
     * */
    companion object{
        var mainContext : Context? = null
        var tv_mainTime : TextView? = null
        val timerState_Standby = 1
        val timerState_Running = 2
        val timerState_Select = 3
        var timerState : Int? = timerState_Standby

    }

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainContext = baseContext
        initSensor(baseContext)
        mainChangeMenu(FragmentA())
        initView()
    }


    fun initView(){
        val tv_mainTime : TextView = findViewById(R.id.tv_mainTime)
        TimeControler().setTimeHandler(tv_mainTime)

        var tv_startHelp : TextView = findViewById(R.id.tv_startHelp)
        tv_startHelp.setOnClickListener(this)
        var btn_start : Button = findViewById(R.id.btn_start)
        btn_start.setOnClickListener(this)
        var btn_finish : Button = findViewById(R.id.btn_finish)
        btn_finish.setOnClickListener(this)

        try {

            val mainViewPager: ViewPager = findViewById(R.id.mainViewPager)
            mainViewPager.adapter = MainPagerAdapter(supportFragmentManager)

            val dotsIndicator = findViewById<DotsIndicator>(R.id.dots_indicator)
            dotsIndicator.setViewPager(mainViewPager)

        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    /**
     * 시작, 중지, 종료 3가지 옵션이 있네.
     * */
    override fun onClick(v: View?) {
        try{
            when (v?.id){
                R.id.btn_start -> {
                    ButtonClickEffect().buttonClick()
                    when(timerState){
                        timerState_Standby -> {
                            timerState = timerState_Running
                            //중지로 바꿔줘야함.
                            btn_start.background = baseContext.getDrawable(R.drawable.button_clicked_round)
                            btn_start.text = "중지"
                            btn_start.setTextColor(Color.parseColor("#ffffff"))
                        }
                        timerState_Running -> {
                            timerState = timerState_Select
                            //여기서 한개의 버튼이 하나 더 생성.
                            //재시작, 종료
                            btn_start.background = baseContext.getDrawable(R.drawable.button_unclick_round)
                            btn_start.text = "재시작"
                            btn_start.setTextColor(Color.parseColor("#000000"))
                            btn_finish.visibility=View.VISIBLE
                            btn_finish.animate().translationX(2*btn_finish.width.toFloat()/3)
                            btn_start.animate().translationX(-2*btn_start.width.toFloat()/3)
                            btn_finish.background = baseContext.getDrawable(R.drawable.button_clicked_round)
                            btn_finish.text = "종료"
                            btn_finish.setTextColor(Color.parseColor("#ffffff"))
                        }
                        timerState_Select -> { // 선택중 재시작 클릭시.
                            timerState = timerState_Running
                            //재시작 클릭시.

                            btn_finish.animate().translationX(0f)
                                .setListener(object : AnimatorListenerAdapter(){
                                    override fun onAnimationEnd(animation: Animator?) {
                                        super.onAnimationEnd(animation)
                                        if(timerState== timerState_Running){
                                            btn_finish.visibility=View.INVISIBLE
                                            btn_start.background = baseContext.getDrawable(R.drawable.button_clicked_round)
                                            btn_start.text = "중지"
                                            btn_start.setTextColor(Color.parseColor("#ffffff"))
                                        }


                                    }
                                })
                            btn_start.animate().translationX(0f)
                        }
                    }
                }
                R.id.btn_finish -> {
                    ButtonClickEffect().buttonClick()
                    timerState = timerState_Standby
                    btn_finish.animate().translationX(0f)
                        .setListener(object : AnimatorListenerAdapter(){
                            override fun onAnimationEnd(animation: Animator?) {
                                super.onAnimationEnd(animation)
                                if(timerState== timerState_Standby){
                                    btn_finish.visibility=View.INVISIBLE
                                }


                            }
                        })
                    btn_start.animate().translationX(0f)
                    btn_start.background = baseContext.getDrawable(R.drawable.button_unclick_round)
                    btn_start.text = "시작"
                    btn_start.setTextColor(Color.parseColor("#000000"))
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }

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
                    fragmentManager.popBackStackImmediate(
                        "Fragment",
                        FragmentManager.POP_BACK_STACK_INCLUSIVE
                    )
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
            val fragmentTransaction =
                fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, changeFragment!!, "Fragment")
            try {
                fragmentTransaction.commitNowAllowingStateLoss()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                fragmentTransaction.commitNow()
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
