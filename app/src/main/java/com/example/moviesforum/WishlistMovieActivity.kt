package com.example.moviesforum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.moviesforum.adapter.WishlistAdapterMovies
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main4.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class WishlistMovieActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

        val db by lazy {
            Room.databaseBuilder(
                this,
                AppDatabase::class.java,
                "app.db")
                .fallbackToDestructiveMigration()
                .build()
        }

        val list = arrayListOf<Wishesmovies>()
        val wishlistAdapter = WishlistAdapterMovies(list)
        var trigger = MutableLiveData<Boolean>()


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_wishlist_movie)

            setSupportActionBar(toolbar)

            val toogle = ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.open,
                R.string.close
            )

            drawer.addDrawerListener(toogle)
            toogle.syncState()

            navView.setNavigationItemSelectedListener(this)


            db.wishesdao().getAllUsersMovies().observe(this, Observer {
                list.clear()
                trigger.value = true
                list.addAll(it)
                wishlistAdapter.notifyDataSetChanged()
            })

            peopleRv.apply {
                layoutManager = LinearLayoutManager(this@WishlistMovieActivity, RecyclerView.VERTICAL,false)
                adapter = wishlistAdapter
            }

            wishlistAdapter.onItemClick = {
                val j = Intent(this,DisplayActivity::class.java)
                j.putExtra("movieid",it.id.toString())
                startActivity(j)

            }

            val itemTouchHelper = ItemTouchHelper(simpleCallback)
            return itemTouchHelper.attachToRecyclerView(peopleRv)
        }


        val simpleCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.START
                    or ItemTouchHelper.END
                    or ItemTouchHelper.UP
                    or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT
                    or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition

                Collections.swap(list,fromPosition,toPosition)
                peopleRv.adapter?.notifyItemMoved(fromPosition,toPosition)
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val position = viewHolder.adapterPosition

                if (direction == ItemTouchHelper.LEFT){
                    GlobalScope.launch(Dispatchers.IO) { db.wishesdao().deleteTaskMovies(wishlistAdapter.getItemId(position)) }
                }
                else if (direction == ItemTouchHelper.RIGHT){
                    GlobalScope.launch(Dispatchers.IO) { db.wishesdao().deleteTaskMovies(wishlistAdapter.getItemId(position)) }
                }
            }
        }


        override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.movie -> {
                startActivity(Intent(this,MainActivity::class.java))
                // Toast.makeText(this,"MOVIE", Toast.LENGTH_SHORT).show()
            }
            R.id.tvSeries ->{
                startActivity(Intent(this,Main2Activity::class.java))
            }
            R.id.discover ->{
                startActivity(Intent(this,Main3Activity::class.java))
            }
            R.id.wishlistMovie ->{
                startActivity(Intent(this,WishlistMovieActivity::class.java))
            }
            R.id.wishlistTvSeries ->{
                startActivity(Intent(this,WishlistTvActivity::class.java))
            }
        }
        drawer.closeDrawer(GravityCompat.START)   //used to close the navigation drawer when the items inside the drawer are clicked
        return true
    }


        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu2,menu)

            val item = menu?.findItem(R.id.search)
            val searchView = item?.actionView as SearchView
            searchView.setQueryHint("type here to search")

            item.setOnActionExpandListener(object : MenuItem.OnActionExpandListener{
                override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                    displayTodo()
                    return true
                }

                override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                    displayTodo()
                    return true
                }
            })

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(!newText.isNullOrEmpty()){
                        displayTodo(newText)
                    }
                    return true
                }

            })
            return super.onCreateOptionsMenu(menu)
        }


        fun displayTodo(newText:String = ""){
            db.wishesdao().getAllUsersMovies().observe(this,Observer{
                if (it.isNotEmpty()){
                    list.clear()
                    list.addAll(
                        it.filter {todo ->
                            todo.name.contains(newText,true)
                        }
                    )
                    wishlistAdapter.notifyDataSetChanged()
                }
            })
        }


        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            val id = item.itemId

            if (id == R.id.search){
                Toast.makeText(this,"search clicked",Toast.LENGTH_SHORT).show()
            }

            return true
        }

}




