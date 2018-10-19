package com.abrahamlay.contactsapp.ui

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.abrahamlay.contactsapp.R
import com.abrahamlay.contactsapp.ui.widget.EmptyViewHolder
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * Created by Abraham on 18/10/2018.
 */
abstract class BaseListFragment:BaseFragment(),BaseView, SwipeRefreshLayout.OnRefreshListener{
    protected var adapter: RecyclerView.Adapter<*>? = null
    protected var itemList: List<Any>?= mutableListOf()
    protected var pageToLoad = 1

    protected lateinit var emptyViewHolder: EmptyViewHolder

    protected abstract fun loadData()

    protected abstract fun getLayoutManager(): RecyclerView.LayoutManager


    override fun onRefresh() {
        initState()
        loadData()
    }

    private fun initState() {
        pageToLoad = 1
        adapter = null
        emptyViewHolder.hide()
        rv_list.layoutManager = getLayoutManager()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val emptyView = view.findViewById<View>(R.id.empty_view)
        emptyViewHolder = EmptyViewHolder(emptyView)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        itemList= ArrayList()
        initState()
        refresh.setOnRefreshListener(this)
    }


    override fun showLoading(active: Boolean) {
        if(pageToLoad==1){
            refresh.isRefreshing=active
            progress_bar_view.visibility = if (active) View.VISIBLE else View.GONE
            rv_list.visibility = if (active) View.GONE else View.VISIBLE
        }
    }

    override fun showEmpty(message: String?) {
        super.showEmpty(message)
        if (isAdded) {
            setEmptyRvList()
            emptyViewHolder.setMessage(message ?: getString(R.string.oops_we_cannot_find_the_data))
        }
    }

    private fun setEmptyRvList() {
        rv_list.visibility = View.GONE
        emptyViewHolder.show()
    }


    override fun onUnknownError(code: Int, errorMessage: String?) {
        if(code==400){
            super.onUnknownError(code, errorMessage)
            return
        }
        if (isAdded) {
            if (pageToLoad == 1) {
                setEmptyRvList()
                emptyViewHolder.setMessage(errorMessage)
            } else {
                adapter?.notifyDataSetChanged()
                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onTimeout() {
        super.onTimeout()
        if (isAdded) {
            if (pageToLoad == 1) {
                setEmptyRvList()
                emptyViewHolder.showOnTimeout()
            } else {
                adapter?.notifyDataSetChanged()
                Toast.makeText(context, R.string.timeout_error, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onNetworkError() {
        if (isAdded) {
            if (pageToLoad == 1) {
                setEmptyRvList()
                emptyViewHolder.showOnNetworkError()
            } else {
                adapter?.notifyDataSetChanged()
                Toast.makeText(context, R.string.network_error, Toast.LENGTH_LONG).show()
            }
        }
    }

}