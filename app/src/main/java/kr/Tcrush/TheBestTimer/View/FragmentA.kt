package kr.Tcrush.TheBestTimer.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_a.*
import kr.Tcrush.TheBestTimer.R
import java.lang.Exception

class FragmentA : Fragment(){

    var ll_fragmentA_background : LinearLayout ?= null
    var tp_timerPicker : TimePicker ?= null
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
            tp_timerPicker = view.findViewById(R.id.tp_timerPicker)
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}