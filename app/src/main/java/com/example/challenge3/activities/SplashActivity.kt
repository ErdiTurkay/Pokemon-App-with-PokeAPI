package com.example.challenge3.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.challenge3.R
import com.example.challenge3.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)

        supportActionBar?.hide()

        setContentView(binding.root)

        Glide.with(applicationContext)
            .load(R.drawable.pokemon_gif_3)
            .fitCenter()
            .into(binding.splashImage)

        binding.apply {
            splashImage.animate().setDuration(3000).alpha(1f).withEndAction{
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }
    }
}