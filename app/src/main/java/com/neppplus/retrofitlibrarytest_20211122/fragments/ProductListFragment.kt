package com.neppplus.retrofitlibrarytest_20211122.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.neppplus.retrofitlibrarytest_20211122.R
import com.neppplus.retrofitlibrarytest_20211122.adapters.ProductAdapter
import com.neppplus.retrofitlibrarytest_20211122.adapters.ProductRecyclerViewAdapter
import com.neppplus.retrofitlibrarytest_20211122.databinding.FragmentProductListBinding
import com.neppplus.retrofitlibrarytest_20211122.datas.BasicResponse
import com.neppplus.retrofitlibrarytest_20211122.datas.ProductData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListFragment : BaseFragment() {

    lateinit var binding: FragmentProductListBinding

    val mProductList = ArrayList<ProductData>()

    lateinit var mProductRecyclerAdapter : ProductRecyclerViewAdapter
//
//    val mProductList = ArrayList<ProductData>()
//
//    lateinit var mProductAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate( inflater, R.layout.fragment_product_list, container, false )
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
        getProductListFromServer()

        mProductRecyclerAdapter = ProductRecyclerViewAdapter(mContext,mProductList)
        binding.productListRecyclerView.adapter = mProductRecyclerAdapter
        binding.productListRecyclerView.layoutManager = LinearLayoutManager(mContext)
//
//        mProductAdapter = ProductAdapter(mContext, R.layout.product_list_item, mProductList)
//        binding.productListView.adapter = mProductAdapter

    }

    fun getProductListFromServer(){

        apiService.getRequestProductList().enqueue(object :Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful ){

                    mProductList.clear()
                    mProductList.addAll(response.body()!!.data.products)
                    mProductRecyclerAdapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })


    }



}