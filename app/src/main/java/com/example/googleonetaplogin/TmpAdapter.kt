package com.example.googleonetaplogin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TmpAdapter(
    context: Context
): RecyclerView.Adapter<TmpAdapter.TmpViewholder>() {

    private val mContext = context
    private lateinit var mList: List<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TmpViewholder {
        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.simple_spinner_item, null)
        return TmpViewholder(view)
    }

    override fun onBindViewHolder(holder: TmpViewholder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setDataList(list: List<String>) {
        mList = list
    }

    inner class TmpViewholder(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {

        private val tvTitle: TextView = itemView.findViewById(R.id.tv_spinner)

        fun bind(position: Int) {
            tvTitle.text = position.toString()
        }

    }

}