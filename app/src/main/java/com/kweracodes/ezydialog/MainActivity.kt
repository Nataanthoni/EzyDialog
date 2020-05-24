package com.kweracodes.ezydialog


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.signin_bottom_sheet.*
import kotlinx.android.synthetic.main.signup_bottom_sheet.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Open dialog when button clicked
        openDialog.setOnClickListener {
            //calling this method to show our android custom alert dialog
            openDialogOne()
        }

        //Handles bottom sheet
        var bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet)
        openDialog2.setOnClickListener {
            if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        val bottomSheetBehavior2 = BottomSheetBehavior.from(signin_bottom_sheet)
        openDialog3.setOnClickListener {
            if (bottomSheetBehavior2.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior2.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                bottomSheetBehavior2.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        val bottomSheetBehavior3 = BottomSheetBehavior.from(signup_bottom_sheet)
        openDialog4.setOnClickListener {
            if (bottomSheetBehavior3.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior3.state = BottomSheetBehavior.STATE_EXPANDED
            } else

                bottomSheetBehavior3.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        openSignup.setOnClickListener {
            bottomSheetBehavior2.state = BottomSheetBehavior.STATE_COLLAPSED
            bottomSheetBehavior3.state = BottomSheetBehavior.STATE_EXPANDED
        }

        openLogin.setOnClickListener {
            bottomSheetBehavior3.state = BottomSheetBehavior.STATE_COLLAPSED
            bottomSheetBehavior2.state = BottomSheetBehavior.STATE_EXPANDED
        }

        //open payments activity
        openPayments.setOnClickListener {
            val intent = Intent(this, Payments::class.java)
            startActivity(intent)
        }


        proceedPayments.setOnClickListener {

            val enteredNumber = editPhone.text.toString().trim()
            val realNumber = enteredNumber.removePrefix("0")
            val phoneNumber = "256$realNumber"
            enteredNumber.validator()
                .nonEmpty()
                .minLength(9)
                .maxLength(10)
                .validNumber()
                .addErrorCallback {
                    editPhone.error = it
                }
                .addSuccessCallback {
                    if (phoneNumber.startsWith("25670") || phoneNumber.startsWith("25675") || phoneNumber.startsWith(
                            "25620"
                        )
                    ) {
                        SweetAlertDialog(this@MainActivity, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Airtel Number")
                            .setContentText("Proceed with payments. Are you sure?")
                            .setCancelText("No")
                            .setConfirmText("Pay")
                            .show()
                    } else if (phoneNumber.startsWith("25679")
                    ) {
                        SweetAlertDialog(this@MainActivity, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Africell Number")
                            .setContentText("Proceed with payments. Are you sure?")
                            .setCancelText("No")
                            .setConfirmText("Pay")
                            .show()
                    } else if (phoneNumber.startsWith("25677") || phoneNumber.startsWith("25678") || phoneNumber.startsWith(
                            "25639"
                        )
                    ) {
                        SweetAlertDialog(this@MainActivity, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("MTN Number")
                            .setContentText("Proceed with payments. Are you sure?")
                            .setCancelText("No")
                            .setConfirmText("Pay")
                            .show()
                    } else {
                        SweetAlertDialog(this@MainActivity, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Invalid Number entered")
                            .setContentText("Enter a valid Ugandan phone number!")
                            .show()
                    }
                }
                .check()


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
