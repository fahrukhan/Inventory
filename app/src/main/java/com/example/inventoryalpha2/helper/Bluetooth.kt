package com.example.inventoryalpha2.helper

import android.bluetooth.BluetoothDevice
import android.content.Context
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.inventoryalpha2.data.Preference
import com.example.inventoryalpha2.data.Preference.*
import com.example.inventoryalpha2.data.Preference.Key.*
import com.example.inventoryalpha2.data.Preference.Name.*
import com.rscja.deviceapi.RFIDWithUHFBLE
import com.rscja.deviceapi.interfaces.ConnectionStatus
import com.rscja.deviceapi.interfaces.ConnectionStatusCallback

class Bluetooth(private val context: Context): AppCompatActivity() {

    companion object {
        const val RECONNECT_NUM = Int.MAX_VALUE // 重连次数
        const val BT_CONNECTED = "CONNECTED"
        const val BT_CONNECTING = "CONNECTING..."
        const val BT_DISCONNECT = "DISCONNECT"
    }

    private var mIsActiveDisconnect = true
    private var mReConnectCount = RECONNECT_NUM // 重新连接次数
    //private val controller: Controller? = null
    private var btStatus: BTStatus? = null
    private val listener: Listener? = null
    lateinit var mDevice: BluetoothDevice

    private val connectStatusList = listOf<IConnectStatus>()
    private var remoteBTName = ""
    private var remoteBTAdd = ""
    private val shouldShowDisconnected = mIsActiveDisconnect || mReConnectCount == 0

    private val prefBT = Preference(context, BLUETOOTH)
    private val uhf = RFIDWithUHFBLE.getInstance()

    inner class BTStatus: ConnectionStatusCallback<Any> {
        override fun getStatus(connectionStatus: ConnectionStatus?, device1: Any?) {
            runOnUiThread {
                val device = device1 as BluetoothDevice?
                remoteBTName = ""
                remoteBTAdd = ""
                if (connectionStatus == ConnectionStatus.CONNECTED) {
                    remoteBTName = device!!.name
                    remoteBTAdd = device.address
                    with(prefBT){
                        //controller.setBluetoothName(device.name.toString())
                        //controller.setBluetoothAddress(device.address.toString())
                        writeString(BLUETOOTH_NAME, device.name.toString())
                        writeString(BLUETOOTH_ADDRESS, device.address.toString());
                    }
                    listener?.message(BT_CONNECTED)


                    //                        tvAddress.setText(String.format("%s(%s)\nconnected", remoteBTName, remoteBTAdd));
                    //if (shouldShowDisconnected()) {
                        //                            showToast(R.string.connect_success);
                        //                            listener.message(device.getName());
                    //}

                    // 保存已链接记录
                    //if (!TextUtils.isEmpty(remoteBTAdd)) {
                        //                            saveConnectedDevice(remoteBTAdd, remoteBTName);
                    //}
                    mIsActiveDisconnect = false
                    mReConnectCount = RECONNECT_NUM

                } else if (connectionStatus == ConnectionStatus.DISCONNECTED) {
                    if (device != null) {
                        remoteBTName = device.name
                        remoteBTAdd = device.address
                        if (shouldShowDisconnected) //                            tvAddress.setText(String.format("%s(%s)\ndisconnected", remoteBTName, remoteBTAdd));
                            listener?.message(BT_DISCONNECT)
                    } else {
                        if (shouldShowDisconnected) //                            tvAddress.setText("disconnected");
                            listener?.message(BT_DISCONNECT)
                    }
                    if (shouldShowDisconnected) //                            showToast(R.string.disconnect);
                        listener?.message(BT_DISCONNECT)
                    val reconnect: Boolean =
                        //SPUtils.getInstance(context).getSPBoolean(SPUtils.AUTO_RECONNECT, false)
                        prefBT.readBool(AUTO_RECONNECT, false)
                    if (reconnect) {
                        reConnect(mDevice.address) // 重连
                    }
                }
                for (iConnectStatus in connectStatusList) {
                    iConnectStatus.getStatus(connectionStatus)
                }
            }
        }
    }

    fun connect(deviceAddress: String, listener: Listener) {
        btStatus = BTStatus()
        uhf.disconnect()
        if (uhf.connectStatus == ConnectionStatus.CONNECTING){
            listener.message(BT_CONNECTING)
        }else{
            uhf.connect(deviceAddress, btStatus)
        }
    }


    private fun reConnect(address: String?) {
        if (!mIsActiveDisconnect && mReConnectCount > 0){
            connect(address!!, listener!!)
            mReConnectCount--
        }
    }

    //Interface
    interface IConnectStatus { fun getStatus(connectionStatus: ConnectionStatus?) }
    interface Listener { fun message(msg: String) }
}