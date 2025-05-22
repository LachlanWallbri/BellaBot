package com.unsw.bellabot

import android.os.Build
import android.os.Bundle

import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint.fabricEnabled
import com.facebook.react.defaults.DefaultReactActivityDelegate

import android.os.Handler
import android.os.HandlerThread
import android.os.SystemClock
import android.util.Log
import android.view.InputDevice
import android.view.KeyEvent
import android.view.MotionEvent
import java.lang.Math.abs

import expo.modules.ReactActivityDelegateWrapper

class MainActivity : ReactActivity() {
    private val TAG = "MainActivity"

    private lateinit var cANBus : CANBus

    private lateinit var handlerThread: HandlerThread
    private lateinit var handler: Handler
    private var isRunning = true

    private var stopped = false
    private var lastMotionInput = 0L;

    private val maxLinearSpeed = 0.5;
    private val maxAngularSpeed = 0.5;

    private var linearSp = 0.3;
    private var angularSp = 0.3;

    private var linearSpToSend = 0.0;
    private var angularSpToSend = 0.0;

    override fun onCreate(savedInstanceState: Bundle?) {
        // Set the theme to AppTheme BEFORE onCreate to support
        // coloring the background, status bar, and navigation bar.
        // This is required for expo-splash-screen.

        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState)

        cANBus = CANBus.getInstance(applicationContext)
        cANBus.bootUp()

        startControllerPolling()
    }

    /**
     * Returns the name of the main component registered from JavaScript. This is used to schedule
     * rendering of the component.
     */
    override fun getMainComponentName(): String = "main"

    /**
     * Returns the instance of the [ReactActivityDelegate]. We use [DefaultReactActivityDelegate]
     * which allows you to enable New Architecture with a single boolean flags [fabricEnabled]
     */
    override fun createReactActivityDelegate(): ReactActivityDelegate {
        return ReactActivityDelegateWrapper(
            this,
            BuildConfig.IS_NEW_ARCHITECTURE_ENABLED,
            object : DefaultReactActivityDelegate(
                this,
                mainComponentName,
                fabricEnabled
            ){})
    }

    /**
     * Align the back button behavior with Android S
     * where moving root activities to background instead of finishing activities.
     * @see <a href="https://developer.android.com/reference/android/app/Activity#onBackPressed()">onBackPressed</a>
     */
    override fun invokeDefaultOnBackPressed() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R) {
            if (!moveTaskToBack(false)) {
                // For non-root activities, use the default implementation to finish them.
                super.invokeDefaultOnBackPressed()
            }
            return
        }

        // Use the default back button implementation on Android S
        // because it's doing more than [Activity.moveTaskToBack] in fact.
        super.invokeDefaultOnBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopControllerPolling()
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
                Log.d("accel 0 data","${cANBus.getAccelerationData(0)}")
                Log.d("accel A data","${cANBus.getAccelerationData(1)}")
                Log.d("decel D data","${cANBus.getAccelerationData(2)}")
                Log.d("estop E data","${cANBus.getAccelerationData(3)}")
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


            Log.d("Controller Polling", "Linear: $linearSpToSend, angular: $angularSpToSend")
            //cANBus.controlWheel(linearSpToSend, angularSpToSend, true);

            lastMotionInput = SystemClock.elapsedRealtime();
            return true
        }
        return super.onGenericMotionEvent(event)
    }

    private fun startControllerPolling() {
        handlerThread = HandlerThread("ControllerPollingThread")
        handlerThread.start()
        handler = Handler(handlerThread.looper)
        handler.post(pollingRunnable)

        cANBus.setAccelerationData(1.toByte(), 0.6)
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
                if (stopped || ((SystemClock.elapsedRealtime() - lastMotionInput > 250) && linearSpToSend == 0.0 && angularSpToSend == 0.0)) {
                    cANBus.controlWheel(0.0, 0.0, false);
                } else {
                    cANBus.controlWheel(linearSpToSend, angularSpToSend, true);
                }

                //pollController()
                handler.postDelayed(this, 5) // Polling at roughly 60Hz
            }
        }
    }
}