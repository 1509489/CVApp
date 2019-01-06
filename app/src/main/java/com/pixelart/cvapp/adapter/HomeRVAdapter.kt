package com.pixelart.cvapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pixelart.cvapp.R
import com.pixelart.cvapp.databinding.RvHomeLayoutBinding
import com.pixelart.cvapp.model.SampleCv
import kotlinx.android.synthetic.main.rv_home_layout.view.*

class HomeRVAdapter(private val listener: OnItemClickedListener): ListAdapter<SampleCv, HomeRVAdapter.ViewHolder>(DIFF_CALLBACK){
    private lateinit var context: Context

    companion object {
        val DIFF_CALLBACK : DiffUtil.ItemCallback<SampleCv> = object : DiffUtil.ItemCallback<SampleCv>(){
            override fun areItemsTheSame(oldItem: SampleCv, newItem: SampleCv): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: SampleCv, newItem: SampleCv): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view: RvHomeLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.rv_home_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cv = getItem(position)

        if (cv != null){
            holder.itemView.apply {
                tvName.text = cv.name
                tvEmail.text = cv.email
                tvPhone.text = cv.phoneNumber
            }
        }
        holder.itemView.setOnClickListener { listener.onItemClicked(position) }
    }

    class ViewHolder(binding: RvHomeLayoutBinding): RecyclerView.ViewHolder(binding.root)

    interface OnItemClickedListener{
        fun onItemClicked(position: Int)
    }
}