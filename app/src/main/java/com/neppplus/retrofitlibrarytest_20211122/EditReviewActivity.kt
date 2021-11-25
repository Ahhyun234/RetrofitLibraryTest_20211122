package com.neppplus.retrofitlibrarytest_20211122

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.retrofitlibrarytest_20211122.databinding.ActivityEditReviewBinding
import com.neppplus.retrofitlibrarytest_20211122.databinding.ActivitySplashBinding

class EditReviewActivity : BaseActivity() {

    lateinit var binding: ActivityEditReviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_edit_review)
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}