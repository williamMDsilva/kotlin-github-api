package com.example.githubapikotlin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapikotlin.lib.baseApi.NetworkUtils
import com.example.githubapikotlin.user.User
import com.example.githubapikotlin.user.UserApi
import com.example.githubapikotlin.views.UserRecyclerViewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    var listUser: List<User> = ArrayList<User>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getUsers()
    }

    fun showElements(){
        viewManager = LinearLayoutManager(this)
        viewAdapter = UserRecyclerViewAdapter(listUser)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view_user).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
    }

    private fun getUsers() {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://api.github.com/")

        val endpoint = retrofitClient.create(UserApi::class.java)
        val callback = endpoint.getUsers()

        callback.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                listUser = response.body()!!
                showElements()

                Log.println(Log.DEBUG, "REQ USERS","Dados: ${listUser.toString()}}")
            }
        })

    }

}