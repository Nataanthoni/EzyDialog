package com.kweracodes.ezydialog


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Open dialog when button clicked
        openDialog.setOnClickListener {
            //calling this method to show our android custom alert dialog
            openDialogOne()
        }
    }

    private fun openDialogOne() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        val viewGroup = findViewById<ViewGroup>(R.id.content)

        //then we will inflate the custom alert dialog xml that we created
        val dialogView: View =
            LayoutInflater.from(this).inflate(R.layout.success_dialog, viewGroup, false)

        //Now we need an AlertDialog.Builder object
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView)

        //finally creating the alert dialog and displaying it
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

}
