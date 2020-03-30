package com.example.moviesforum

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesforum.Model.SearchModel.ResultsItem
import com.example.moviesforum.adapter.SearchAdapter
import com.example.moviesforum.adapter.childadapter.MovieChildAdapter.UpcomingMovieAdapter
import com.example.moviesforum.client.Client
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main.*
import kotlinx.android.synthetic.main.main_search.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val list = arrayListOf<ResultsItem>()
    val searchadapter = SearchAdapter(list)

    val list1 = arrayListOf<com.example.moviesforum.Model.MovieModel.upcomingresponse.ResultsItem>()
    val upcomingadapter = UpcomingMovieAdapter(list1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(toolBarSearch)

        searchRv.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity,RecyclerView.VERTICAL,false)
            adapter = upcomingadapter
        }

        upcomingadapter.onItemClick = {
            Toast.makeText(this,"movie",Toast.LENGTH_LONG).show()

            val intent = Intent(this,DisplayActivity::class.java)
            intent.putExtra("movieid",it.id.toString())
            startActivity(intent)
        }

        GlobalScope.launch (Dispatchers.Main) {
            for (i in 1..10){
                val response = withContext(Dispatchers.IO) { Client.api.getAllUpcomingMovies("${i}") }
                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        res.results?.let {
                            list1.addAll(it) }
                        runOnUiThread { upcomingadapter.notifyDataSetChanged() }
                    }
                }
            }
        }

        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolBarSearch,
            R.string.open,
            R.string.close
        )

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.movie -> {
                startActivity(Intent(this, MainActivity::class.java))
                // Toast.makeText(this,"MOVIE", Toast.LENGTH_SHORT).show()
            }
            R.id.tvSeries -> {
                startActivity(Intent(this, Main2Activity::class.java))
            }
            R.id.discover -> {
                startActivity(Intent(this, Main3Activity::class.java))
            }
            R.id.wishlistMovie -> {
                startActivity(Intent(this, WishlistMovieActivity::class.java))
            }
            R.id.wishlistTvSeries -> {
                startActivity(Intent(this, WishlistTvActivity::class.java))
            }
        }
        drawer.closeDrawer(GravityCompat.START)   //used to close the navigation drawer when the items inside the drawer are clicked
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu2, menu)

        val item = menu?.findItem(R.id.search)

        val searchView = item?.actionView as androidx.appcompat.widget.SearchView

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView.queryHint = "search here..."
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener{


            override fun onQueryTextSubmit(p0: String?): Boolean {

                if (p0?.length!! > 2){
                    loadJson(p0.toString())
                }
                searchadapter.notifyDataSetChanged()
                return false
            }


            override fun onQueryTextChange(p0: String?): Boolean {
                if (!p0!!.isNullOrEmpty()){
                    loadJson(p0.toString())
                }
                else if(p0.isNullOrEmpty()){
                 loadJson(p0.toString())
                }
                return false
            }

        })

        item.icon.setVisible(false,false)
        return true

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id: Int = item.itemId

        if (id == R.id.search) {
            Toast.makeText(this, "search here", Toast.LENGTH_LONG).show()
        }
        return true

    }

    private fun loadJson(keyword:String){

        if (keyword.length > 2){
            searchRv.apply {
                layoutManager = LinearLayoutManager(this@SearchActivity, RecyclerView.VERTICAL,false)
                adapter = searchadapter
            }
            searchadapter.onItemClick = {
                val intent = Intent(this, DisplayActivity::class.java)
                intent.putExtra("movieid",it.id.toString())
                startActivity(intent)
            }
            searchadapter.notifyDataSetChanged()

            GlobalScope.launch {
                val response = withContext(Dispatchers.IO){ Client.api.getSearch("$keyword")}
                if (response.isSuccessful){
                    response.body()?.let { res->
                        res.results?.let{
                            list1.clear()
                            list.clear()
                            list.addAll(it)}
                        runOnUiThread{searchadapter.notifyDataSetChanged()}
                    }
                }
            }
            upcomingadapter.notifyDataSetChanged()
        }
        else if (keyword.isNullOrEmpty()){
            searchRv.apply {
                layoutManager = LinearLayoutManager(this@SearchActivity,RecyclerView.VERTICAL,false)
                adapter = upcomingadapter
            }

            upcomingadapter.onItemClick = {
//                Toast.makeText(this,"movie",Toast.LENGTH_LONG).show()

                val intent = Intent(this,DisplayActivity::class.java)
                intent.putExtra("movieid",it.id.toString())
                startActivity(intent)
            }
            upcomingadapter.notifyDataSetChanged()

            GlobalScope.launch (Dispatchers.Main) {
                for (i in 1..10){
                val response = withContext(Dispatchers.IO) { Client.api.getAllUpcomingMovies("${i}") }
                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        res.results?.let {
                            list.clear()
//                            list1.clear()
                            list1.addAll(it) }
                        runOnUiThread { upcomingadapter.notifyDataSetChanged() }
                    }
                }
//                    upcomingadapter.notifyDataSetChanged()
            }
//            upcomingadapter.notifyDataSetChanged()
        }
        }
    }
}