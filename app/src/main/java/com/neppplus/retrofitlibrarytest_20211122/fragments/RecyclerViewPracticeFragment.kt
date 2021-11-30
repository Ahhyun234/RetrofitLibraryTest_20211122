package com.neppplus.retrofitlibrarytest_20211122.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.neppplus.retrofitlibrarytest_20211122.R
import com.neppplus.retrofitlibrarytest_20211122.adapters.MainRecyclerViewAdapter
import com.neppplus.retrofitlibrarytest_20211122.databinding.FragmentRecyclerviewPracticeBinding
import com.neppplus.retrofitlibrarytest_20211122.datas.BasicResponse
import com.neppplus.retrofitlibrarytest_20211122.datas.ReviewData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerViewPracticeFragment : BaseFragment() {

    lateinit var binding:FragmentRecyclerviewPracticeBinding
    val mReviewList = ArrayList<ReviewData>()
    lateinit var mMainRecyclerAdapter : Adapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_recyclerview_practice,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {



        getReviewListFromServer()

        mMainRecyclerAdapter = MainRecyclerViewAdapter(mContext,mReviewList)
        binding.mainRecyclerView.adapter = mMainRecyclerAdapter
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(mContext)

    }


    fun getReviewListFromServer(){

        apiService.getRequestReviewList().enqueue(object :Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                val br = response.body()!!

                mReviewList.clrear()
                mReviewList.addAll(br.data.reviews)

                mReviewList.notyfy
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })

    }
}