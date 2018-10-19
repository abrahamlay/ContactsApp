package com.abrahamlay.contactsapp.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.abrahamlay.contactsapp.R
import com.abrahamlay.contactsapp.model.DataItem
import com.abrahamlay.contactsapp.ui.BaseListFragment
import com.abrahamlay.contactsapp.ui.ItemClickListener
import com.abrahamlay.contactsapp.ui.detail.DetailActivity
import com.abrahamlay.contactsapp.ui.widget.DialogComposer
import com.abrahamlay.contactsapp.ui.widget.GeneralListener

import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * Created by Abraham on 18/10/2018.
 */
class MainFragment : BaseListFragment() ,MainContract.MainView, ItemClickListener {

    private val presenter: MainPresenter by inject{ parametersOf(this) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initFab()
        loadData()
    }

    private fun initFab() {
        fab.setOnClickListener { view ->
            DialogComposer.showWriteDialog(activity as Activity,
                getString(R.string.add),
                getString(R.string.add_contact),null,
                object : GeneralListener.OnClickListener{
                    override fun onClick(data: Any) {
                        val person=data as DataItem
                        createContact(person)
                    }

                })
        }
    }

    private fun createContact(person: DataItem) {
        presenter.createContact(person)
    }

    override fun loadData() {
        presenter.loadAllContacts()
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }

    override fun onContactLoaded(testList: List<DataItem>) {
        adapter= MainAdapter(context,testList,this)
        rv_list.adapter=adapter
    }

    override fun onContactCreated(message: String?) {
//        SnackbarComposer.showSnackbar(view,message,null,null)
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
        onRefresh()
    }

    override fun onContactDeleted(message: String?) {
//        SnackbarComposer.showSnackbar(view,message,null,null)
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
        loadData()
    }


    override fun onItemClicked(view: View, item: Any, position: Int) {
        when(view.id){
            R.id.iv_delete->DialogComposer.showDeleteDialog(activity as MainActivity,
                item as DataItem,
                object :GeneralListener.OnClickListener{
                    override fun onClick(data: Any) {
                        deleteContact((item as DataItem).id)
                    }

                })
            else-> startActivityForResult(
                DetailActivity.newInstance(context,item as DataItem),
                PARAM_REFRESH
            )
        }


    }

    private fun deleteContact(id: String?) {
        presenter.deleteContact(id)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode== PARAM_REFRESH && resultCode==Activity.RESULT_OK){
            onRefresh()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        private const val PARAM_REFRESH: Int=101
    }

}