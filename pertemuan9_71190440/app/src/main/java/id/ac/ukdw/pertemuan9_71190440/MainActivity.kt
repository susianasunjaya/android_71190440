package id.ac.ukdw.pertemuan9_71190440

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar_default))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //instansiasi ViewPager
        val viewPager = findViewById<ViewPager2>(R.id.pager)

        //Memasukkan seluruh fragment ke dalam ArrayList
        val listFragment = arrayListOf<Fragment>(FragmentUtama(),FragmentSatu(),FragmentDua(),FragmentTiga())

        //instansiasi Adapter untuk ViewPager
        val pagerAdapter = PagerAdapter(this, listFragment)

        viewPager.adapter = pagerAdapter
    }
    class PagerAdapter(val activity: AppCompatActivity, val listFragment: ArrayList<Fragment>) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = listFragment.size

        override fun createFragment(position: Int): Fragment = listFragment.get(position)
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_default, menu)
        return true
        }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.menu_notification -> {
            val intent = Intent(this,Notification::class.java)
            startActivity(intent)
            true
        }
        R.id.menu_person -> {
            val intent = Intent(this,Profile::class.java)
            startActivity(intent)
            true
        }
        R.id.menu_setting -> {
            val intent = Intent(this,Setting::class.java)
            startActivity(intent)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }


    }
    }