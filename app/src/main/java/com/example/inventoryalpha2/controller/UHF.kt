package com.example.inventoryalpha2.controller

import com.example.inventoryalpha2.helper.Bluetooth
import com.rscja.deviceapi.RFIDWithUHFBLE
import com.rscja.deviceapi.interfaces.ConnectionStatus

class UHF() {
    var uhf: RFIDWithUHFBLE = RFIDWithUHFBLE.getInstance()
    //lateinit var controller: Controller
    lateinit var listener: DeviceListener
    private var total: Long = 0
    private var loopFlag = false
    private var isScanning = false
    private var isRunning = false
    var mStrTime: Long = 0;


    // Class
    inner class ConnectStatus : Bluetooth.IConnectStatus {
        override fun getStatus(connectionStatus: ConnectionStatus?) {
            if (connectionStatus == ConnectionStatus.CONNECTED) {
                if (!loopFlag) {
                    try {
                        Thread.sleep(500)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            } else if (connectionStatus == ConnectionStatus.DISCONNECTED) {
                loopFlag = false
                isScanning = false
            }
        }
//        override fun getStatus(connectionStatus: ConnectionStatus) {
//            if (connectionStatus == ConnectionStatus.CONNECTED) {
//                if (!loopFlag) {
//                    try {
//                        Thread.sleep(500)
//                    } catch (e: InterruptedException) {
//                        e.printStackTrace()
//                    }
//                }
//            } else if (connectionStatus == ConnectionStatus.DISCONNECTED) {
//                loopFlag = false
//                isScanning = false
//            }
//        }

    }

    interface DeviceListener{
        fun batteryStatus(battery: Int) {}
        fun temperatureStatus(temperature: Int) {}
        fun connectionStatus(connection: String?) {}
        fun scanning(scanStatus: Boolean) {}
        fun dataScanning(tag: String, data: Any) {}
        fun singleScanning(tag: String?) {}
    }
}