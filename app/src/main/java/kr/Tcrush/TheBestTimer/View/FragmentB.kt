package kr.Tcrush.TheBestTimer.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import kr.Tcrush.TheBestTimer.R
import java.lang.Exception

class FragmentB : Fragment() {

    var ll_fragmentB_background : LinearLayout?= null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_b,container,false)

        initView(view)

        return view
    }
    fun newInstance(): FragmentB
    {
        val args = Bundle()

        val frag = FragmentB()
        frag.arguments = args

        return frag
    }

    fun initView(view : View){
        try {
            ll_fragmentB_background = view.findViewById(R.id.ll_fragmentB_background)
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

}