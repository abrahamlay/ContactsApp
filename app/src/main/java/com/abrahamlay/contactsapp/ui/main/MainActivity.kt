package com.abrahamlay.contactsapp.ui.main


import android.os.Bundle
import com.abrahamlay.contactsapp.R
import com.abrahamlay.contactsapp.ui.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(MainFragment(),false)
    }
}
