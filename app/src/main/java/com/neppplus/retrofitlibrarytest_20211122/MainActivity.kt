package com.neppplus.retrofitlibrarytest_20211122

import android.media.audiofx.BassBoost
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager

import com.neppplus.retrofitlibrarytest_20211122.adapters.MainViewPagerAdapter
import com.neppplus.retrofitlibrarytest_20211122.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var mvpa : MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        1. view pager를 옮기면 바텀 네비게이션 클릭
        binding.mainViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
//                손으로 옮기는 모든 순간 => 얼만큼 옮기는지도 확인 가능

            }

            override fun onPageSelected(position: Int) {

//                페이지 선택이 완료되었을 때
//                바텀 네비게이션바의 position에 맞는 메뉴를 클릭
                binding.mainBottomNavigationView.menu.getItem(position).isChecked = true



            }

            override fun onPageScrollStateChanged(state: Int) {
//                가만히 있다가 움직이기 시작함

            }

        })


//        2. 바텀 네비게이션 클릭 => 뷰페이저 페이지 이동 등
        binding.mainBottomNavigationView.setOnItemReselectedListener {
           when(it.itemId){
               R.id.menuHome -> binding.mainViewPager.currentItem =0
               R.id.menuRanking -> binding.mainViewPager.currentItem =1
               R.id.menuMyProfile -> binding.mainViewPager.currentItem =2
           }
            return@setOnItemReselectedListener true
        }


    }

    override fun setValues() {


        mvpa = MainViewPagerAdapter( supportFragmentManager )
        binding.mainViewPager.adapter = mvpa

//        뷰페이저가 3장의 프레그먼트를 계속 메모리에 유지시키게 하자
        binding.mainViewPager.offscreenPageLimit=3

        binding.mainTabLayout.setupWithViewPager( binding.mainViewPager )


    }
}