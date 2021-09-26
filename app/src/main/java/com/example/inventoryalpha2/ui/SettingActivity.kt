package com.example.inventoryalpha2.ui

import android.app.Activity
import android.app.Dialog
import android.app.DownloadManager
import android.app.VoiceInteractor
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.example.inventoryalpha2.R
import com.example.inventoryalpha2.helper.Toast
import com.mancj.slideup.SlideUp
import com.mancj.slideup.SlideUpBuilder
import kotlinx.android.synthetic.main.activity_setting.*
import javax.xml.transform.Result

class SettingActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var dialog: Dialog
    lateinit var toast: Toast
    lateinit var mBAdapter: BluetoothAdapter
    lateinit var slide: SlideUp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        initUI()
    }

    private fun initUI() {
        supportActionBar?.title = "Setting"
        dialog = Dialog(this)
        toast = Toast(this)

        val bluetoothManager = this.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        mBAdapter = bluetoothManager.adapter
        slide = SlideUpBuilder(slide_view)
            .withStartState(SlideUp.State.HIDDEN)
            .withStartGravity(Gravity.BOTTOM)
            .build()
        //slide.show()

        setting_bluetooth.setOnClickListener(this)
    }

    override fun onClick(btn: View?) {
        when(btn?.id){
            setting_bluetooth.id ->{
                openBTDialog()
            }
        }
    }

    private fun openBTDialog() {
        if (!packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)){
            toast.error("Device not supported!")
            finish()
        }
        checkBluetooth()
//        dialog.setContentView(R.layout.activity_bluetooth)
//        dialog.show()
    }

    private val getResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if (it.resultCode == Activity.RESULT_OK){
            toast.success("Bluetooth ON")
            slide.show()
        }else{
            toast.warning("Bluetooth hasn't turn on")
            //finish()
        }
    }

    private val btResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if (it.resultCode == Activity.RESULT_OK){
            toast.success("Bluetooth ON")
        }else{
            toast.warning("Bluetooth hasn't turn on")
        }
    }

    private fun checkBluetooth() {
        if (mBAdapter == null){
            toast.error("Bluetooth not available in this device")
            return
        }

        if (!mBAdapter.isEnabled){
            println("SETTING: BT not enabled")
            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            getResult.launch(intent)
        }else{
                if (slide.isVisible) {
                    slide.hide()
                } else {
                    slide.show()
                }
        }
    }

    companion object {
        const val REQUEST_SELECT_DEVICE = 1
        const val REQUEST_ENABLE_BT = 2
    }
}