package com.ldd.gankapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ldd.gankapp.fragment.HistoryFragment
import com.ldd.gankapp.fragment.TodayFragment
import com.ldd.gankapp.fragment.VideoFragment
import com.ldd.gankapp.fragment.WalfareFragment

class MainActivity : AppCompatActivity() {



    private lateinit var fragments: List<Fragment>
    private lateinit var todayFragment: TodayFragment
    private lateinit var historyFragment: HistoryFragment
    private lateinit var walfareFragment: WalfareFragment
    private lateinit var videoFragment: VideoFragment
    private var lastShowFragment: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.title = "今日"
//        supportActionBar!!.hide()
        initFragments()
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout, fragments[0])
        transaction.show(fragments[0]).commitAllowingStateLoss()
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_today -> {
                if (lastShowFragment != 0) {
                    switchFrament(lastShowFragment, 0)
                    lastShowFragment = 0
                }
                supportActionBar!!.title = "今日"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_history -> {
                if (lastShowFragment != 1) {
                    switchFrament(lastShowFragment, 1)
                    lastShowFragment = 1
                }
                supportActionBar!!.title = "历史"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_welfare -> {
                if (lastShowFragment != 2) {
                    switchFrament(lastShowFragment, 2)
                    lastShowFragment = 2
                }
                supportActionBar!!.title = "福利"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_video -> {
                if (lastShowFragment != 3) {
                    switchFrament(lastShowFragment, 3)
                    lastShowFragment = 3
                }
                supportActionBar!!.title = "视频"
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun initFragments() {
        todayFragment = TodayFragment()
        historyFragment = HistoryFragment()
        walfareFragment = WalfareFragment()
        videoFragment = VideoFragment()
        fragments = mutableListOf<Fragment>().apply {
            this.add(todayFragment)
            this.add(historyFragment)
            this.add(walfareFragment)
            this.add(videoFragment)
        }
        lastShowFragment = 0
    }

    private fun switchFrament(lastIndex: Int, index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.hide(this.fragments[lastIndex])
        if (!fragments[index].isAdded) {
            transaction.add(R.id.frameLayout, fragments[index])
        }
        transaction.show(fragments[index]).commitAllowingStateLoss()
    }


}
