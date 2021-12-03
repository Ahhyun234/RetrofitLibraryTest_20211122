package com.neppplus.retrofitlibrarytest_20211122

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.retrofitlibrarytest_20211122.databinding.ActivityPurchaseListBinding

class PurchaseListActivity : BaseActivity() {

    lateinit var binding: ActivityPurchaseListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_purchase_list)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }


}