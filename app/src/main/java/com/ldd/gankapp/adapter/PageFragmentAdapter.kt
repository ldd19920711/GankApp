package com.ldd.gankapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

class PageFragmentAdapter(private val fm: FragmentManager, private val fragmentList: List<Fragment>) :
    FragmentPagerAdapter(fm) {
    override fun getItem(idx: Int): Fragment {
        return fragmentList[idx % fragmentList.size]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE  //没有找到child要求重新加载
    }
}