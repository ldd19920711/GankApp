package com.ldd.gankapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ldd.gankapp.R
import com.ldd.gankapp.pojo.ApiAndroidDataDay
import org.jetbrains.anko.find

class AndroidAdapter(private val items: List<ApiAndroidDataDay.ResultsBean>, private val context: Context?) :
    RecyclerView.Adapter<AndroidAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_android, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.bind()
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.textView.text = items[position].desc
        holder.tvTitle.text = items[position].desc
        holder.tvAuthor.text = items[position].who
        holder.tvTime.text = items[position].createdAt
        holder.llItem.setOnClickListener {
            Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
            //跳转webview
        }
    }


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        lateinit var llItem: LinearLayout
        lateinit var tvTitle: TextView
        lateinit var tvAuthor: TextView
        lateinit var tvTime: TextView
        fun bind() {
            llItem = view.findViewById(R.id.ll_item)
            tvTitle = view.find(R.id.tv_title)
            tvAuthor = view.findViewById(R.id.tv_author)
            tvTime = view.findViewById(R.id.tv_time)

        }
    }
}