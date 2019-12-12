//*******************************************************************************
//
//      filename:  server.kt
//
//   description:  Runs the Bluetooth server required for wireless connection
//
//        author:  Schwartz, Jacob T.
//       Copyright (c) 2019 Schwartz, Jacob T. University of Dayton
//
//******************************************************************************

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.*
import javax.bluetooth.*
import javax.bluetooth.RemoteDevice
import javax.bluetooth.UUID
import javax.microedition.io.Connector
import javax.microedition.io.StreamConnection
import javax.microedition.io.StreamConnectionNotifier

class BluetoothServer {

    companion object {
        fun runServer() {
            println("Initializing Bluetooth Server")
            val localDevice: LocalDevice = LocalDevice.getLocalDevice()
            println("Server Address: " + localDevice.bluetoothAddress)
            val sampleSPPServer = BluetoothServer()
            while (true) {
                sampleSPPServer.startServer()
            }
        }
    }

    private fun handlePayload(payload: String) {
        val payloadTokens = payload.split("|")

        GlobalScope.launch {
            when (payloadTokens[0]) {
                "access" -> ChannelManager.access.send(payloadTokens[1].trim())
	            "keystroke" -> ChannelManager.keystroke  .send(payloadTokens[1].trim())
                "power" -> ChannelManager.power.send(payloadTokens[1].trim())
                "volume" -> ChannelManager.volume.send(payloadTokens[1].trim())
	            "disconnect" -> runServer()
                else -> println("Action Item ${payloadTokens[0]} Does Not Exist")
            }
        }
    }

    @Throws(IOException::class)
    fun startServer() {
        val uuid = UUID("c820a3480e0e11ea8d71362b9e155667", false)
        val connectionString = "btspp://localhost:$uuid;name=LynkrSPPServer"
        val streamConnNotifier: StreamConnectionNotifier = Connector.open(connectionString) as StreamConnectionNotifier

        println("\nServer Started. Waiting for clients to connect...")
        val connection: StreamConnection = streamConnNotifier.acceptAndOpen()

        val device: RemoteDevice = RemoteDevice.getRemoteDevice(connection)
        println("Remote Device Address: " + device.bluetoothAddress)
//        println("Remote device name: " + device.getFriendlyName(true))

        val inStream: InputStream = connection.openInputStream()
        val bReader = BufferedReader(InputStreamReader(inStream))
        val lineRead = bReader.readLine() ?: "disconnect"
        println("Message from mobile device: $lineRead")

        handlePayload(lineRead)

        val outStream: OutputStream = connection.openOutputStream()
        val pWriter = PrintWriter(OutputStreamWriter(outStream))
        pWriter.write("received\r\n")
        pWriter.flush()
        pWriter.close()
        streamConnNotifier.close()
    }
}