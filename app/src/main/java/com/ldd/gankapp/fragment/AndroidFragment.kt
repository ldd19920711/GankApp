package com.ldd.gankapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ldd.gankapp.R
import com.ldd.gankapp.adapter.AndroidAdapter
import com.ldd.gankapp.pojo.ApiAndroidDataDay
import com.ldd.gankapp.util.Constant
import com.ldd.gankapp.util.Util
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AndroidFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_android, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val items = ArrayList<String>()
        activity.doAsync {
            println("aa  " + Thread.currentThread().id + Thread.currentThread().name)
            var pageNo = 1
            var pageSize = 10
            var url = Constant.androidDataUrl
            url = "$url/$pageSize/$pageNo"
            val okHttpClient = OkHttpClient()
            val builder = Request.Builder().url(url).get().build()
            val call = okHttpClient.newCall(builder)
            val execute = call.execute()
            uiThread {
                val string = execute.body?.string()
                val apiAndroidDataDay = Util.gson.fromJson<ApiAndroidDataDay>(string, ApiAndroidDataDay::class.java)
                val results = apiAndroidDataDay.results
                println(results)
                if (results != null && results.isNotEmpty()) {
                    results.forEach { items.add(it.desc.toString()) }
                    recyclerView.adapter = AndroidAdapter(results, context)
                }
            }
        }
    }
}
