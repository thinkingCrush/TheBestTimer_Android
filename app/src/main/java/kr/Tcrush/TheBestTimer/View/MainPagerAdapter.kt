package kr.Tcrush.TheBestTimer.View

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainPagerAdapter(fm: FragmentManager?): FragmentPagerAdapter(fm!!,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
{
    val PAGE_MAX_CNT = 3

    override fun getCount(): Int {
        return PAGE_MAX_CNT
    }

    override fun getItem(position: Int): Fragment {
        val fragment = when(position)
        {
            0 -> FragmentA().newInstance()
            1 -> FragmentB().newInstance()
            2 -> FragmentC().newInstance()
            else -> FragmentA().newInstance()
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val title = when(position)
        {
            1 -> "A"
            2 -> "B"
            3 -> "C"
            else -> "main"
        }
        return title
    }
}