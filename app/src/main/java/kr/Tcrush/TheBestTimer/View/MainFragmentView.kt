package kr.Tcrush.TheBestTimer.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import kr.Tcrush.TheBestTimer.R
import kr.Tcrush.TheBestTimer.Tool.Dlog

class MainFragmentView : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_main,container,false)

        initView(view)

        return view
    }

    fun initView(view : View){
        try {
            val dotsIndicator = view.findViewById<DotsIndicator>(R.id.dots_indicator)
            val mainViewPager = view.findViewById<ViewPager>(R.id.mainViewPager)
            mainViewPager.adapter = MainPagerAdapter(activity?.supportFragmentManager)
            dotsIndicator.setViewPager(mainViewPager)

        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}