package com.fec.ex.exmaterial2k

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.bottomappbar.BottomAppBar
import android.support.design.widget.Snackbar
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomAppBar.replaceMenu(R.menu.main)

        bottomAppBar.setOnMenuItemClickListener{
            Snackbar.make(bottomAppBar, title, Snackbar.LENGTH_SHORT).setAction("OK", View.OnClickListener { bottomAppBar.switchStyle() }).show()
            true
        }

        bottomFAB.setOnClickListener{
            bottomAppBar.switchStyle()
        }

        btnSwitch.setOnClickListener {
            bottomAppBar.switchStyle()
        }

    }

    private fun BottomAppBar.switchStyle() {
        val currStyle = fabAlignmentMode
        fabAlignmentMode = currStyle.xor(1)
    }

}


