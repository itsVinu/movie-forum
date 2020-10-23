package com.example.moviesforum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.moviesforum.adapter.FragmentAdapter.MyMovieFragmentAdapter
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main.*

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolBar)

        //these lines set our adapter
        val fragmentAdapter =
            MyMovieFragmentAdapter(
                supportFragmentManager
            )
        viewPager.adapter = fragmentAdapter

        // this line will connect our tabLayout with viewPager
        tabLayout.setupWithViewPager(viewPager)


        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolBar,
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
        menuInflater.inflate(R.menu.menu1, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id:Int = item.itemId

        if (id == R.id.search){
            Toast.makeText(this,"search the news content here", Toast.LENGTH_LONG).show()
            startActivity(Intent(this,SearchActivity::class.java))
        }
        return true

    }

}
