package com.abrahamlay.contactsapp.ui

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.abrahamlay.contactsapp.R

/**
 * Created by Abraham on 18/10/2018.
 */
abstract class BaseActivity : AppCompatActivity(){
    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        val ft = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .replace(R.id.container, fragment)

        if (addToBackStack) ft.addToBackStack(null)

        ft.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}