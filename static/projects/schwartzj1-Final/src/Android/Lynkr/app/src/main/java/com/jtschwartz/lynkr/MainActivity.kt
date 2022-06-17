//*******************************************************************************
//
//      filename:  MainActivity.kt
//
//   description:  Implements the Main UI Activity and all necessary communication functions
//
//        author:  Schwartz, Jacob T.
//       Copyright (c) 2019 Schwartz, Jacob T. University of Dayton
//
//******************************************************************************

package com.jtschwartz.lynkr

import android.app.Activity
import android.app.ProgressDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*


class MainActivity : AppCompatActivity() {
	
	companion object {
		lateinit var btAdapter: BluetoothAdapter
		private var btAddress: String? = null
		var btSocket: BluetoothSocket? = null
		var btDevice: BluetoothDevice? = null
		var deviceUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
		const val EXTRA_ADDRESS: String = "Device_Address"
		var isConnected: Boolean = false
		private const val OPEN_SETTINGS = 1
		lateinit var progress: ProgressDialog
	}
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		curBtDevice.text = if (btAddress != null) "Connected to: ${btAdapter.getRemoteDevice(btAddress)}" else "No Connected Device"
	}
	
	fun btnLogout(view: View) {
		sendCommand(Commands.Access.logout)
	}
	
	fun btnKeyStroke(view: View) {
		val event: String? = when (view.id) {
			keystroke_alpha.id -> Commands.Keystroke.alpha
			keystroke_beta.id -> Commands.Keystroke.beta
			keystroke_gamma.id -> Commands.Keystroke.gamma
			keystroke_delta.id -> Commands.Keystroke.delta
			else -> null
		}
		
		sendCommand(event)
	}
	
	fun btnLock(view: View) {
		sendCommand(Commands.Access.lock)
	}
	
	fun btnPowerOptions(view: View) {
		sendCommand(Commands.Power.shutdown)
	}
	
	fun btnVolume(view: View) {
		sendCommand(when (view.id) {
			volume_increase.id -> Commands.Volume.increase
			volume_decrease.id -> Commands.Volume.decrease
			volume_mute.id -> Commands.Volume.mute
			else -> null
		})
	}
	
	fun btnSettings(view: View) {
		println("Settings")
		val openSettings = Intent(this, SettingsActivity::class.java)
		openSettings.putExtra(EXTRA_ADDRESS, btAddress)
		startActivityForResult(openSettings, OPEN_SETTINGS)
	}
	
	fun disconnect() {
		if (btSocket != null) {
			try {
				btSocket!!.close()
				btSocket = null
				isConnected = false
			} catch (e: IOException) {
				e.printStackTrace()
			}
		}
	}
	
	private fun sendCommand(payload: String?) {
		if (btSocket != null) {
			try {
				btSocket!!.outputStream.write("${payload!!}\r\n".toByteArray())
				btSocket!!.outputStream.flush()
				println(payload)
				val reception = btSocket!!.inputStream.read()
				println("Received: $reception")
				btSocket!!.outputStream.close()
				
				btAdapter = BluetoothAdapter.getDefaultAdapter()
				val device: BluetoothDevice = btAdapter.getRemoteDevice(btAddress)
				btSocket = device.createInsecureRfcommSocketToServiceRecord(deviceUUID)
				BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
				btSocket!!.connect()
			} catch (e: IOException) {
				e.printStackTrace()
			}
		}
	}
	
	private fun handleDeepLink(data: Uri?) {
		try {
			when (data!!.path) {
				DeepLink.KEYSTROKE -> {
					when (data.getQueryParameter(DeepLink.Params.ACTIVITY_TYPE).orEmpty()) {
						DeepLink.Params.ALPHA -> sendCommand(Commands.Keystroke.alpha)
						DeepLink.Params.BETA -> sendCommand(Commands.Keystroke.beta)
						DeepLink.Params.GAMMA -> sendCommand(Commands.Keystroke.gamma)
						DeepLink.Params.DELTA -> sendCommand(Commands.Keystroke.delta)
					}
				}
				DeepLink.VOLUME_INCREASE -> sendCommand(Commands.Volume.increase)
				DeepLink.VOLUME_DECREASE -> sendCommand(Commands.Volume.decrease)
				DeepLink.MUTE -> sendCommand(Commands.Volume.mute)
				DeepLink.SHUTDOWN -> sendCommand(Commands.Power.shutdown)
				DeepLink.RESTART -> sendCommand(Commands.Power.restart)
				DeepLink.LOCK -> sendCommand(Commands.Access.lock)
				DeepLink.LOGOUT -> sendCommand(Commands.Access.logout)
				else -> {
					Log.i("d", "Unable to handle path")
				}
			}
		} catch (e: java.lang.Exception) {
			Log.i("d", "Received null DeepLink")
		}
	}
	
	private class ConnectToDevice(c: Context) : AsyncTask<Void, Void, String>() {
		private var connectSuccess: Boolean = true
		private val context: Context

		init {
			this.context = c
		}
		
		override fun onPreExecute() {
			super.onPreExecute()
			progress = ProgressDialog.show(context, "Connecting...", "please wait")
		}
		
		override fun doInBackground(vararg p0: Void?): String? {
			try {
				if (btSocket == null || !isConnected) {
					btAdapter = BluetoothAdapter.getDefaultAdapter()
					val device: BluetoothDevice = btAdapter.getRemoteDevice(btAddress)
					btSocket = device.createInsecureRfcommSocketToServiceRecord(deviceUUID)
					BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
					btSocket!!.connect()
				}
			} catch (e: IOException) {
				connectSuccess = false
				e.printStackTrace()
			}
			return null
		}
		
		override fun onPostExecute(result: String?) {
			super.onPostExecute(result)
			if (!connectSuccess) {
				Log.i("data", "couldn't connect")
			} else {
				connectSuccess = true
			}
			progress.dismiss()
		}
	}
	
	
	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (requestCode == OPEN_SETTINGS) {
			if (resultCode == Activity.RESULT_OK) {
				val returnedDevice = data?.getStringExtra(SettingsActivity.EXTRA_ADDRESS)
				val returnedDeviceName = data?.getStringExtra(SettingsActivity.EXTRA_NAME)
				println("Got Device: $returnedDevice")
				val displayString = "Connected to: $returnedDeviceName"
				curBtDevice.text = displayString
				
				if (returnedDevice != null) {
					btAddress = returnedDevice.toString()
					btDevice = BluetoothAdapter.getDefaultAdapter()
						.getRemoteDevice(returnedDevice.toString())
					
					ConnectToDevice(this).execute()
				}
			}
		}
	}
}
