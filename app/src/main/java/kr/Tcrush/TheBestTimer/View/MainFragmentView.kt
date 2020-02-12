package kr.Tcrush.TheBestTimer.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.Tcrush.TheBestTimer.R

class MainFragmentView : Fragment(){

    var mainViewPager : MainViewPager ?= null
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
            mainViewPager = view.findViewById(R.id.mainViewPager)
            mainViewPager?.adapter = MainPagerAdapter(activity?.supportFragmentManager)

        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}