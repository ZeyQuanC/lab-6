package com.codepath.articlesearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.codepath.articlesearch.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.serialization.json.Json

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "MainActivity/"


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val bestSellerBooksFragment: Fragment = BestSellerBooksFragment()
    private val articleListFragment: Fragment = ArticleListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fragmentManager: FragmentManager = supportFragmentManager

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        // Handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_bestselling_books -> replaceFragment(bestSellerBooksFragment)
                R.id.menu_article_search -> replaceFragment(articleListFragment)
            }
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.menu_bestselling_books
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.article_frame_layout, fragment)
            .commit()
    }
}
