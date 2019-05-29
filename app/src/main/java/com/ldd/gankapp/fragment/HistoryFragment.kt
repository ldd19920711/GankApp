package com.ldd.gankapp.fragment

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.ldd.gankapp.R
import com.ldd.gankapp.adapter.PageFragmentAdapter


class HistoryFragment : Fragment(), ViewPager.OnPageChangeListener {


    private lateinit var viewPager: ViewPager
    private lateinit var rgChannel: RadioGroup
    private lateinit var hvChannel: HorizontalScrollView
    private lateinit var adapter: PageFragmentAdapter
    private var fragmentList = ArrayList<Fragment>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        rgChannel = view.findViewById(R.id.rgChannel) as RadioGroup
        viewPager = view.findViewById(R.id.vpNewsList) as ViewPager
        hvChannel = view.findViewById(R.id.hvChannel) as HorizontalScrollView
        rgChannel.setOnCheckedChangeListener { group, checkedId -> viewPager.currentItem = checkedId }
        viewPager.addOnPageChangeListener(this)
        initTab()//动态产生RadioButton
        initViewPager()
        rgChannel.check(0)
    }

    private fun initTab() {
        val channelList = getSelectedChannel()
        for (i in channelList.indices) {
            val rb = LayoutInflater.from(context).inflate(R.layout.tab_rb, null) as RadioButton
            rb.id = i
            rb.text = channelList[i].name
            val params = RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT
            )
            rgChannel.addView(rb, params)
        }
    }

    private fun initViewPager() {
        val androidFragment = AndroidFragment()
        val iosFragment = IosFragment()
        val appFragment = AppFragment()
        val webFragment = WebFragment()
        val tzzyFragment = TzzyFragment()
        val xtjFragment = XtjFragment()
        fragmentList.add(androidFragment)
        fragmentList.add(iosFragment)
        fragmentList.add(appFragment)
        fragmentList.add(webFragment)
        fragmentList.add(tzzyFragment)
        fragmentList.add(xtjFragment)
        adapter = PageFragmentAdapter(activity!!.supportFragmentManager, fragmentList)
        viewPager.adapter = adapter
        //viewPager.setOffscreenPageLimit(0);
    }

    /**
     * 滑动ViewPager时调整ScroollView的位置以便显示按钮
     * @param idx
     */
    private fun setTab(idx: Int) {
        val rb = rgChannel.getChildAt(idx) as RadioButton
        rb.isChecked = true
        val left = rb.left
        val width = rb.measuredWidth
        val metrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(metrics)
        val screenWidth = metrics.widthPixels
        val len = left + width / 2 - screenWidth / 2
        hvChannel.smoothScrollTo(len, 0)//滑动ScroollView
    }


    private fun getSelectedChannel(): List<Channel> {
        val selectedChannel = ArrayList<Channel>()
        selectedChannel.add(Channel("", "Android", 0, "", ""))
        selectedChannel.add(Channel("", "iOS", 0, "", ""))
        selectedChannel.add(Channel("", "App", 0, "", ""))
        selectedChannel.add(Channel("", "前端", 0, "", ""))
        selectedChannel.add(Channel("", "拓展资源", 0, "", ""))
        selectedChannel.add(Channel("", "瞎推荐", 0, "", ""))
        return selectedChannel
    }

    override fun onPageScrollStateChanged(state: Int) {
        println("onPageScrollStateChanged")
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        println("onPageScrolled")
    }

    override fun onPageSelected(position: Int) {
        setTab(position)
    }

    //        selectedChannel.add(new Channel("","头条",0,"",""));
    data class Channel(var t1: String, var name: String, var count: Int, var webUrl: String, var t3: String)
}
