package com.mayur.shaadiapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mayur.shaadiapp.R
import com.mayur.shaadiapp.database.NameEntity
import com.mayur.shaadiapp.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.recycler_list_row.view.*

class NameListAdapter(val listener: ActionButtonClickListener) :
    RecyclerView.Adapter<NameListAdapter.MyViewHolder>() {

    lateinit var viewModel: MainActivityViewModel

    //    var nameListData = ArrayList<Result>()
    var items = ArrayList<NameEntity>()

    fun setListData(data: ArrayList<NameEntity>) {
        this.items = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NameListAdapter.MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_row, parent, false)
        return MyViewHolder(inflater, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.fab_connect.setOnClickListener {

            listener.onConnectClickListener(items[position])

        }

        holder.fab_decline.setOnClickListener {
            listener.onDeclineClickListener(items[position])
        }

        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(view: View, private val listener: ActionButtonClickListener) :
        RecyclerView.ViewHolder(view) {
        val tv_created_date = view.tv_created_date
        val iv_profile_image = view.iv_profile_image
        val tv_name_age = view.tv_name_age
        val tv_location = view.tv_location
        val tv_email = view.tv_email
        val fab_decline = view.fab_decline
        val fab_connect = view.fab_connect
//        val tv_fab_decline = view.tv_fab_decline
        val tv_fab_connect = view.tv_fab_connect


        fun bind(data: NameEntity) {
            val url = data.thumbnail
            Glide.with(iv_profile_image)
                .load(url)
                .into(iv_profile_image)
            val name_age =
                data.firstName.plus(" ").plus(data.lastName).plus(" (").plus(data.age)
                    .plus(")")
            tv_name_age.text = name_age
            val loc = data.city.plus(" ").plus(data.state)
            tv_location.text = loc
            tv_email.text = data.email
            if (data.status != ""){
                tv_fab_connect.text = data.status
                fab_decline.visibility = View.GONE
                fab_connect.visibility = View.GONE
                tv_fab_connect.visibility = View.VISIBLE
            }else{
                fab_decline.visibility = View.VISIBLE
                fab_connect.visibility = View.VISIBLE
                tv_fab_connect.visibility = View.GONE
            }
            tv_fab_connect.text = data.status
            fab_connect.setOnClickListener {
                listener.onConnectClickListener(data)
                fab_decline.visibility = View.GONE
                fab_connect.visibility = View.GONE
                tv_fab_connect.visibility = View.VISIBLE

            }

            fab_decline.setOnClickListener {
                listener.onDeclineClickListener(data)
                fab_decline.visibility = View.GONE
                fab_connect.visibility = View.GONE
                tv_fab_connect.visibility = View.VISIBLE
                tv_fab_connect.text = data.status

            }
        }
    }

    interface ActionButtonClickListener {
        fun onConnectClickListener(nameList: NameEntity)
        fun onDeclineClickListener(nameList: NameEntity)
    }
}