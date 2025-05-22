package com.example.jobeeekerapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.InputDevice
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jobeeekerapp.camera.BaseActivity
import com.example.jobeeekerapp.camera.CameraLib
import com.example.jobeeekerapp.camera.MarkerView
import com.example.jobeeekerapp.camera.MonocularView
import com.example.jobeeekerapp.ui.theme.JobeeekerAppTheme
import java.lang.Math.abs

class MainActivity : BaseActivity() {
    private val TAG: String = "MainActivity"

    private lateinit var cANBus : CANBus
    private var camLib : CameraLib = CameraLib.INSTANCE
    private var markerView : MarkerView = MarkerView()
    private var monocularView : MonocularView = MonocularView()

    // Polling stuff
    private lateinit var handlerThread: HandlerThread
    private lateinit var handler: Handler
    private var isRunning = true

    private var stopped = false

    // Driving values
    private val maxLinearSpeed = 0.5;
    private val maxAngularSpeed = 0.5;
    private var linearSp = 0.3;
    private var angularSp = 0.3;
    private var linearSpToSend = 0.0;
    private var angularSpToSend = 0.0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cANBus = CANBus.getInstance(applicationContext);
        cANBus.bootUp()
        Log.d(TAG, "before opening marker camera")
        var result = camLib.openMarkerCamera()
        val markerRows = camLib.getMarkerFrame().rows
        val markerCols = camLib.getMarkerFrame().cols
        Log.d(TAG, "opening marker camera result is $result")

        Log.d(TAG, "before opening monocular camera")
        result = camLib.openMonocularCamera()
        val monoRows = camLib.getMonocularFrame().rows
        val monoCols = camLib.getMonocularFrame().cols
        Log.d(TAG, "opening monocular camera result is $result")

        startControllerPolling()

        enableEdgeToEdge()
        setContent {
            JobeeekerAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Greeting(name = "Android")

                        // Collect the camera permission state as a Compose state to automatically update the UI upon change
                        var showMarkerPreview by remember { mutableStateOf(false) }
                        val permissionGranted = isCameraPermissionGranted.collectAsState().value
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                showMarkerPreview = !showMarkerPreview
                                if (!permissionGranted) {
                                    handleCameraPermission()
                                }
                            },
                        ) {
                            markerView.bmp.value = camLib.getFrameBmp(camLib.getFrameIArr(
                                camLib.getMarkerFrame()), markerRows, markerCols)!!.asImageBitmap()
                            Text(if (showMarkerPreview) "Close preview" else "Show preview")
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp)
                            .clipToBounds()) {
                            // Conditional UI rendering based on camera permission state
                            if (permissionGranted && showMarkerPreview) {
                                Log.d(TAG, "attempting to open camerapreview")
                                // If permission is granted, display the camera preview
                                markerView.CameraPreview(camLib)
                            }
                        }

                        var showMonocularPreview by remember { mutableStateOf(false) }
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                showMonocularPreview = !showMonocularPreview
                                if (!permissionGranted) {
                                    handleCameraPermission()
                                }
                            },
                        ) {
                            monocularView.bmp.value = camLib.getFrameBmp(camLib.getFrameIArr(
                                camLib.getMonocularFrame()), monoRows, monoCols)!!.asImageBitmap()
                            Text(if (showMonocularPreview) "Close preview" else "Show preview")
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .rotate(90f)
                            .height(240.dp)
                            .clipToBounds()) {
                            // Conditional UI rendering based on camera permission state
                            if (permissionGranted && showMonocularPreview) {
                                Log.d(TAG, "attempting to open monocular camerapreview")
                                // If permission is granted, display the camera preview
                                monocularView.CameraPreview(camLib)
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                markerView.bmp.value = camLib.getFrameBmp(camLib.getFrameIArr(
                                    camLib.getMarkerFrame()), markerRows, markerCols)!!.asImageBitmap()
                                monocularView.bmp.value = camLib.getFrameBmp(camLib.getFrameIArr(
                                    camLib.getMonocularFrame()), monoRows, monoCols)!!.asImageBitmap()
                            },
                        ) {
                            Text( "Update previews")
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopControllerPolling()
        camLib.closeMarkerCamera()
        camLib.closeMonocularCamera()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("speeds", "Linear: $linearSp, angular: $angularSp")
        Log.d("speeds", "button is $keyCode")
        when (keyCode) {
            // gamepad
            KeyEvent.KEYCODE_BUTTON_B -> {
                cANBus.controlWheel(0.0, 0.0, false);
                stopped = true
            }
            KeyEvent.KEYCODE_BUTTON_A -> stopped = false
            KeyEvent.KEYCODE_BUTTON_L1 -> linearSp -= 0.01
            KeyEvent.KEYCODE_BUTTON_R1 -> linearSp += 0.01

            KeyEvent.KEYCODE_BUTTON_X -> cANBus.clearWheelError()
            KeyEvent.KEYCODE_BUTTON_Y -> {
                Log.d("accel data","${cANBus.getAccelerationData(1)}")
                Log.d("decel data","${cANBus.getAccelerationData(2)}")
                Log.d("estop data","${cANBus.getAccelerationData(3)}")
            }

            // keyboard
            KeyEvent.KEYCODE_W -> linearSpToSend = linearSp
            KeyEvent.KEYCODE_S -> linearSpToSend = -linearSp
            KeyEvent.KEYCODE_A -> angularSpToSend = angularSp
            KeyEvent.KEYCODE_D -> angularSpToSend = -angularSp
            KeyEvent.KEYCODE_Q -> linearSp -= 0.01
            KeyEvent.KEYCODE_E -> linearSp += 0.01
            KeyEvent.KEYCODE_0 -> linearSp = 0.0
            KeyEvent.KEYCODE_1 -> linearSp = 0.1
            KeyEvent.KEYCODE_2 -> linearSp = 0.2
            KeyEvent.KEYCODE_3 -> linearSp = 0.3
        }
        if (linearSp < 0) {
            linearSp = 0.0;
        } else if (linearSp > 0.5) {
            linearSp = 0.5
        }
        angularSp = linearSp * 2
        return true
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_W, KeyEvent.KEYCODE_S -> linearSpToSend = 0.0
            KeyEvent.KEYCODE_A, KeyEvent.KEYCODE_D -> angularSpToSend = 0.0
        }
        return true
    }

    override fun onGenericMotionEvent(event: MotionEvent): Boolean {
        // Check if the event came from a game controller

        if (event.source and InputDevice.SOURCE_JOYSTICK == InputDevice.SOURCE_JOYSTICK &&
            event.action == MotionEvent.ACTION_MOVE) {

            // Get the x and y axis values
            var xAxis = event.getAxisValue(MotionEvent.AXIS_Z)
            var yAxis = event.getAxisValue(MotionEvent.AXIS_Y)

            if (abs(xAxis) < 0.2) {
                xAxis = 0.0F
            }
            if (abs(yAxis) < 0.2) {
                yAxis = 0.0F
            }



            linearSpToSend = -linearSp * yAxis;
            angularSpToSend = -angularSp * xAxis;


            Log.d(TAG, "Controller Polling: Linear: $linearSpToSend, angular: $angularSpToSend")

            return true
        }
        return super.onGenericMotionEvent(event)
    }

    private fun startControllerPolling() {
        handlerThread = HandlerThread("ControllerPollingThread")
        handlerThread.start()
        handler = Handler(handlerThread.looper)
        handler.post(pollingRunnable)

        cANBus.setAccelerationData(1.toByte(), 0.4)
        cANBus.setAccelerationData(2.toByte(), 0.6)
        cANBus.setAccelerationData(3.toByte(), 1.0)
    }

    private fun stopControllerPolling() {
        isRunning = false
        handlerThread.quitSafely()
    }

    private val pollingRunnable = object : Runnable {
        override fun run() {
            if (isRunning) {
                if (stopped) {
                    cANBus.controlWheel(0.0, 0.0, false);
                } else {
                    cANBus.controlWheel(linearSpToSend, angularSpToSend, true);
                }

                //pollController()
                handler.postDelayed(this, 1) // Polling at roughly 60Hz
            }
        }
    }

    @SuppressLint("Recycle")
    private fun pollController() {
        val deviceIds = InputDevice.getDeviceIds()
        for (id in deviceIds) {
            val device = InputDevice.getDevice(id)
            Log.d(TAG, "Controller Polling Device: $device")
            if (device != null) {
                val src = device.sources
                val src_joystick = InputDevice.SOURCE_JOYSTICK
                Log.d(TAG, "Controller Polling device.sources: $src, $src_joystick")
                if (device.sources and InputDevice.SOURCE_JOYSTICK == InputDevice.SOURCE_JOYSTICK) {
                    val event = MotionEvent.obtain(0, 0, MotionEvent.ACTION_MOVE, 0f, 0f, 0)
                    val xAxis = event.getAxisValue(MotionEvent.AXIS_X)
                    val yAxis = event.getAxisValue(MotionEvent.AXIS_Y)

                    val linear = maxLinearSpeed * yAxis;
                    val angular = maxAngularSpeed * xAxis;

                    Log.d(TAG, "Controller Polling Linear: $linear, angular: $angular")
                    cANBus.controlWheel(linear, angular, true);
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun Startup(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "start up!")
    }
}

@Composable
fun ForwardButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Forward")
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMainActivity() {
    JobeeekerAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(it)) {
                Greeting("Android")
                Spacer(modifier = Modifier.height(16.dp))
                Startup(onClick = { /* Preview onClick function */ })
                Spacer(modifier = Modifier.height(16.dp))
                ForwardButton(onClick = { /* Preview onClick function */ })
                Spacer(modifier = Modifier.height(16.dp))

            }
        }
    }
}


