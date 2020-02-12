package kr.Tcrush.TheBestTimer.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import kr.Tcrush.TheBestTimer.R
import java.lang.Exception

class FragmentA : Fragment(){

    var ll_fragmentA_background : LinearLayout ?= null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_a,container,false)

        initView(view)

        return view
    }


    fun newInstance(): FragmentA
    {
        val args = Bundle()

        val frag = FragmentA()
        frag.arguments = args

        return frag
    }

    fun initView(view : View){
        try {
            ll_fragmentA_background = view.findViewById(R.id.ll_fragmentA_background)
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}