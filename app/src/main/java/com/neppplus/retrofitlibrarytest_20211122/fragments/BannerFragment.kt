package com.neppplus.retrofitlibrarytest_20211122.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.neppplus.retrofitlibrarytest_20211122.R
import com.neppplus.retrofitlibrarytest_20211122.databinding.FragmentBannersBinding

class BannerFragment(val mBannerData:) :BaseFragment() {

    lateinit var binding:FragmentBannersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_banners,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.imgBanner.setOnClickListener {
//            배너 클릭하면 인터넷 링크
            val myUri = Uri.parse(mBannerData.clickedUrl)
            val myIntent = Intent(Intent.getIntentOld().ACTION_VIEW,MyUri)
            startActivity(myIntent)
        }

    }

    override fun setValues() {
//        프레그 먼트를 만들대 받아온 배너 데이터 -> 그림 표시
        Glide.with(mContext).load(mBannerData.displayImgUrl).into(binding.imgBanner)

    }
}