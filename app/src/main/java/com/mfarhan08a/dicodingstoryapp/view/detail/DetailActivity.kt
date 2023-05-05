package com.mfarhan08a.dicodingstoryapp.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.mfarhan08a.dicodingstoryapp.R
import com.mfarhan08a.dicodingstoryapp.data.Result
import com.mfarhan08a.dicodingstoryapp.data.model.Story
import com.mfarhan08a.dicodingstoryapp.databinding.ActivityDetailBinding
import com.mfarhan08a.dicodingstoryapp.utils.ViewModelFactory


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private val detailViewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getStringExtra(EXTRA_ID)

        detailViewModel.apply {
            getToken().observe(this@DetailActivity) { token ->
                if (token != null && id != null) {
                    getDetailStory(token, id).observe(this@DetailActivity) {
                        when (it) {
                            is Result.Loading -> {
                                showLoading(true)
                            }
                            is Result.Success -> {
                                showLoading(false)
                                showDetail(it.data.story)
                            }
                            is Result.Error -> {
                                showLoading(false)
                                Toast.makeText(
                                    this@DetailActivity,
                                    getString(R.string.load_error),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDetail(story: Story) {
        binding.apply {
            tvDetailName.text = story.name
            tvDetailDescription.text = story.description
            tvDetailDate.text = story.createdAt
            Glide.with(this@DetailActivity)
                .load(story.photoUrl)
                .into(ivDetailPhoto)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}