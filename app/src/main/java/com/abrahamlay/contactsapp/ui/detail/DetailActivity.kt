package com.abrahamlay.contactsapp.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.abrahamlay.contactsapp.R
import com.abrahamlay.contactsapp.model.DataItem
import com.abrahamlay.contactsapp.ui.BaseActivity
import com.abrahamlay.contactsapp.ui.BaseView
import com.abrahamlay.contactsapp.ui.widget.GlideHelper
import com.abrahamlay.contactsapp.util.Constant
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity() {

    companion object {
        fun newInstance(context: Context?, contact:DataItem): Intent {
            val intent= Intent(context,DetailActivity::class.java)
            intent.putExtra(Constant.PARAM_OBJECT,contact)
            return intent
        }
    }

    var actionEdit: ImageView? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        actionEdit=findViewById<ImageView>(R.id.action_edit)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initFragment()
    }

    private fun initFragment() {
        val contact=intent.getParcelableExtra<DataItem>(Constant.PARAM_OBJECT)

        showImage(contact.photo)
        supportActionBar?.title = "${contact.firstName} ${contact.lastName}"
        replaceFragment(DetailFragment.newInstance(contact),false)
    }

    fun showImage(url:String?){
        GlideHelper.showImage(this,url,iv_detail_pict)
    }
}
