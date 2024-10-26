package com.example.job3



import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.job3.databinding.ActivityMainBinding

import com.example.job3.view.LoginActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


class MainActivity : AppCompatActivity() {
    lateinit var actionDrawerToggle: ActionBarDrawerToggle
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navController = findNavController(R.id.fragmentContainerView)
        binding.bottomBar.setupWithNavController(navController)
        binding.drawerNav.setupWithNavController(navController)

        actionDrawerToggle = ActionBarDrawerToggle(
            this, binding.drawerlayout,
            R.string.nav_open,
            R.string.nav_close
        )
        actionDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.drawerNav.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.logout -> {
                    Firebase.auth.signOut()
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    finish()
                    true
                }
                R.id.friendsFragment -> {
                    navController.navigate(R.id.friendsFragment)
                    binding.drawerlayout.closeDrawers() // Close drawer after selection
                    true
                }
                R.id.profileFragment -> {
                    navController.navigate(R.id.profileFragment)
                    binding.drawerlayout.closeDrawers() // Close drawer after selection
                    true
                }
                else -> true
            }
        }

        binding.bottomBar.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.logout -> {
                    Firebase.auth.signOut()
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    finish()
                    true
                }
                R.id.friendsFragment -> {
                    navController.navigate(R.id.friendsFragment)
                    true
                }
                R.id.profileFragment -> {
                    navController.navigate(R.id.profileFragment)
                    true
                }
                else -> true
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

}