package com.mayur.shaadiapp.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mayur.shaadiapp.R
import com.mayur.shaadiapp.adapter.NameListAdapter
import com.mayur.shaadiapp.database.NameEntity
import com.mayur.shaadiapp.model.NameListModel
import com.mayur.shaadiapp.model.Result
import com.mayur.shaadiapp.network.RetroInstance
import com.mayur.shaadiapp.network.RetroService
import com.mayur.shaadiapp.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), NameListAdapter.ActionButtonClickListener {

    lateinit var viewModel: MainActivityViewModel
    lateinit var nameListAdapter: NameListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadApiData()
        initRecyclerView()
    }

    private fun loadApiData() {
        val retroInstance = RetroInstance.getRetrofitInstance().create(RetroService::class.java)
        val call: Call<NameListModel> = retroInstance.getNameListFromApi("10")

        call.enqueue(object : Callback<NameListModel> {
            override fun onResponse(call: Call<NameListModel>, response: Response<NameListModel>) {
                if (response.isSuccessful) {
                    val res = response.body()!!.results;

                    insertIntoDatabase(res)
                }
            }

            override fun onFailure(call: Call<NameListModel>, t: Throwable) {
//                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                Toast.makeText(
                    this@MainActivity,
                    "Please connect to internet, and try again!...",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }

    private fun insertIntoDatabase(res: ArrayList<Result>) {
        res.forEach() { it ->
            val first = it.name.first
            val last = it.name.last
            val age = it.dob.age
            val city = it.location.city
            val state = it.location.state
            val email = it.email
            val thumb = it.picture.thumbnail
            val dbList = NameEntity(
                null,
                firstName = first,
                lastName = last,
                age = age,
                city = city,
                state = state,
                email = email,
                thumbnail = thumb,
                status = ""
            )
            viewModel.insertNameInfo(dbList)
        }
    }

    fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration =
                DividerItemDecoration(applicationContext, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(decoration)
            nameListAdapter = NameListAdapter(this@MainActivity)
            adapter = nameListAdapter

        }
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.getAllNamesObservers().observe(this, Observer {
            nameListAdapter.setListData(ArrayList(it))
            nameListAdapter.notifyDataSetChanged()
        })
    }

    override fun onConnectClickListener(nameList: NameEntity) {
        nameList.id?.let { viewModel.updateStatus("Accepted", it) }
    }

    override fun onDeclineClickListener(nameList: NameEntity) {
        nameList.id?.let { viewModel.updateStatus("Declined", it) }
    }
}