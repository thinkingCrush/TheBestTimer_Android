package kr.Tcrush.TheBestTimer.View

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class MainViewPager : ViewPager {
    companion object{
        var enable = true
    }

    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!,
        attrs
    )

    fun setEnable(enable: Boolean) {
        MainViewPager.enable = enable
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return try {
            enable && super.onInterceptTouchEvent(ev)
        } catch (e: Exception) {
            false
        }
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        try {
            return enable && super.onTouchEvent(ev)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
}