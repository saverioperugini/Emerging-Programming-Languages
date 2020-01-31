//*******************************************************************************
//
//      filename:  SettingsActivity.kt
//
//   description:  Implements the Settings UI Activity for selecting a Lynkr server
//
//        author:  Schwartz, Jacob T.
//       Copyright (c) 2019 Schwartz, Jacob T. University of Dayton
//
//******************************************************************************

package com.jtschwartz.lynkr

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*
import org.jetbrains.anko.toast

class SettingsActivity : AppCompatActivity() {
	
	companion object {
		var btAdapter: BluetoothAdapter? = null
		var btDevice: BluetoothDevice? = null
		const val EXTRA_ADDRESS: String = "BT_Device"
		const val EXTRA_NAME: String = "BT_Name"
		lateinit var pairedDevices: Set<BluetoothDevice>
		const val REQUEST_ENABLE_BLUETOOTH = 1
	}
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_settings)
		
		btAdapter = BluetoothAdapter.getDefaultAdapter()
		if (btAdapter == null) {
			toast("Device does not support Bluetooth")
			return
		} else if (!btAdapter!!.isEnabled){
			val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
			startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BLUETOOTH)
		}
		
		refreshBluetoothList()
	}
	
	fun refreshBluetoothList(view: View? = null) {
		pairedDevices = btAdapter!!.bondedDevices
		val btDeviceList: ArrayList<BluetoothDevice> = ArrayList()
		
		if (pairedDevices.isNotEmpty()) {
			for (device: BluetoothDevice in pairedDevices) {
				btDeviceList.add(device)
			}
		} else {
			toast("No paired bluetooth devices found")
		}
		
		val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, btDeviceList.map { it.name })
		settings_devices.adapter = adapter
		settings_devices.onItemClickListener = AdapterView.OnItemClickListener{_, _, pos, _ ->
			btDevice = btDeviceList[pos]
		}
	}
	
	fun navigateHome(view: View) {
		val result = Intent()
		result.putExtra(EXTRA_ADDRESS, btDevice.toString())
		result.putExtra(EXTRA_NAME, btDevice!!.name)
		setResult(Activity.RESULT_OK, result)
		
		finish()
	}
	
	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (requestCode == REQUEST_ENABLE_BLUETOOTH) {
			if (resultCode == Activity.RESULT_OK) {
				if (btAdapter!!.isEnabled) {
					toast("Bluetooth has been enabled")
				} else {
					toast("Bluetooth has been disabled")
				}
			} else if (resultCode == Activity.RESULT_CANCELED) {
				toast("Bluetooth enabling has been cancelled")
			}
		}
	}
}
