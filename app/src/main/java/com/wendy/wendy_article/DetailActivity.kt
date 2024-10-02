package com.wendy.wendy_article

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class DetailActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detailmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            R.id.action_share ->{
                shareArticle()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareArticle() {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Share this article to...")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share this article to..."))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val detail = if (Build.VERSION.SDK_INT >=33){
            intent.getParcelableExtra("DATA", Article::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("DATA")
        }

        if (detail != null) {
            val category: TextView = findViewById(R.id.category_details)
            val title: TextView = findViewById(R.id.title_details)
            val rate: TextView = findViewById(R.id.rate_details)
            val date: TextView = findViewById(R.id.date_details)
            val photos: ImageView = findViewById(R.id.image_details)
            val desc: TextView = findViewById(R.id.desc_details)


            category.text = detail.category
            title.text = detail.title
            rate.text = detail.rate
            date.text = detail.date
            desc.text = detail.desc
            Glide.with(this).load(detail.photo).into(photos)

        }


    }

}