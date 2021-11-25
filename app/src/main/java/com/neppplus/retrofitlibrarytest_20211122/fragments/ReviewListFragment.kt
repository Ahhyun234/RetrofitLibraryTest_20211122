package com.neppplus.retrofitlibrarytest_20211122.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.neppplus.retrofitlibrarytest_20211122.R
import com.neppplus.retrofitlibrarytest_20211122.adapters.CategoryRecyclerAdapter
import com.neppplus.retrofitlibrarytest_20211122.adapters.ReviewRecyclerViewAdapter
import com.neppplus.retrofitlibrarytest_20211122.databinding.FragmentReviewListBinding
import com.neppplus.retrofitlibrarytest_20211122.datas.BasicResponse
import com.neppplus.retrofitlibrarytest_20211122.datas.ReviewData
import com.neppplus.retrofitlibrarytest_20211122.datas.SmallCategoryData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewListFragment : BaseFragment() {

    lateinit var binding: FragmentReviewListBinding

    val mReviewList = ArrayList<ReviewData>()

    lateinit var mReviewRecyclerViewAdapter: ReviewRecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate( inflater, R.layout.fragment_review_list, container, false)
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

        mReviewRecyclerViewAdapter = ReviewRecyclerViewAdapter(mContext,mReviewList)
        binding.reviewListRecyclerView.adapter = mReviewRecyclerViewAdapter
        binding.reviewListRecyclerView.layoutManager=LinearLayoutManager(mContext)



    }

    fun getReviewListFromServer(){

        apiService.getRequestReviewList().enqueue(object :Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful){

                    var br = response.body()!!
                    mReviewList.clear()
                    mReviewList.addAll(br.data.reviews)
                    mReviewRecyclerViewAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })



    }



}
