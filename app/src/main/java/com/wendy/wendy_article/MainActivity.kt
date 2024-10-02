package com.wendy.wendy_article

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvArticles : RecyclerView
    private val list = ArrayList<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setHomeAsUpIndicator(R.drawable.logomenu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvArticles = findViewById(R.id.rv_articles)
        rvArticles.setHasFixedSize(true)

        list.addAll(getArticleList())
        showRecyclerList()

    }

    private fun getArticleList(): ArrayList<Article> {
        val dataCategory = resources.getStringArray(R.array.data_category)
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataDate = resources.getStringArray(R.array.data_date)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataRate = resources.getStringArray(R.array.data_rate)
        val dataDesc = resources.getStringArray(R.array.data_desc)
        val listArticle = ArrayList<Article>()
        for (i in dataTitle.indices) {
            val article = Article(dataCategory[i], dataTitle[i], dataDate[i], dataPhoto[i], dataRate[i], dataDesc[i])
            listArticle.add(article)
        }
        return listArticle
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profilemenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showRecyclerList() {
        rvArticles.layoutManager = LinearLayoutManager(this)
        val articleListAdapter = ArticleListAdapter(list)
        rvArticles.adapter = articleListAdapter

        articleListAdapter.setOnItemClickCallback(object : ArticleListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Article) {
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra("DATA", data)
                startActivity(intentToDetail)
            }
        })

    }

}