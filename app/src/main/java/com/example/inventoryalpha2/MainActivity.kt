package com.example.inventoryalpha2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.inventoryalpha2.ui.SettingActivity
import github.com.st235.lib_expandablebottombar.navigation.ExpandableBottomBarNavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navigationHost) as NavHostFragment
        val navigationController = navHostFragment.findNavController()
        ExpandableBottomBarNavigationUI.setupWithNavController(bottomNavigation, navigationController)
        setupActionBarWithNavController(navigationController)


//        bottomNavigation.onItemSelectedListener = { view, menuItem, b ->
//            setupActionBar(menuItem.text.toString())
//
//        }
    }

//    private fun setupActionBar(f: String){
//        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this,
//            color[f]!!)))
//
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.setting -> startActivity(Intent(this, SettingActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}