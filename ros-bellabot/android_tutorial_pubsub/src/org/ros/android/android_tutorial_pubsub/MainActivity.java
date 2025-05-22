/*
 * Copyright (C) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.ros.android.android_tutorial_pubsub;

import static java.lang.Math.abs;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import org.ros.address.InetAddressFactory;
import org.ros.android.RosActivity;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

public class MainActivity extends RosActivity {

  private CANBus cANBus;
  private boolean ledOn = false;

  // Polling
  private final HandlerThread handlerThread = new HandlerThread("ControllerPollingThread");
  private Handler handler;
  private final Handler rosHandler = new Handler();
  private boolean isRunning = true;
  private boolean stopped = false;

  private final double maxLinearSpeed = 0.5;
  private final double maxAngularSpeed = 0.5;
  private double linearSp = 0.3;
  private double angularSp = 0.3;
  private double linearSpToSend = 0.0;
  private double angularSpToSend = 0.0;

  private DoublePub linearPub;
  private DoublePub angularPub;
  private DoublePub encoderPubL;
  private DoublePub encoderPubA;
  private DoublePub maxSpeedPub;
  private IntPub batteryPub;

  public MainActivity() {
    // The RosActivity constructor configures the notification title and ticker
    // messages.
    super("ROS Bellabot", "ROS Bellabot");
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // ROS Stuff
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    // Teleop Stuff
    cANBus = new CANBus(getApplicationContext());
    cANBus.bootUp();
  }

  @Override
  protected void init(NodeMainExecutor nodeMainExecutor) {
    // Set up the publishers
    linearPub = new DoublePub("bellabot/linear_speed");
    angularPub = new DoublePub("bellabot/angular_speed");
    encoderPubL = new DoublePub("bellabot/encoder_linear");
    encoderPubA = new DoublePub("bellabot/encoder_angular");
    maxSpeedPub = new DoublePub("bellabot/max_speed");
    batteryPub = new IntPub("bellabot/battery");

    // Initialise the ROS Node
    NodeConfiguration nodeConfiguration = NodeConfiguration.newPublic(
      InetAddressFactory.newNonLoopback().getHostAddress(), getMasterUri()
    );

    // Execute the publishers
    nodeMainExecutor.execute(linearPub, nodeConfiguration);
    nodeMainExecutor.execute(angularPub, nodeConfiguration);
    nodeMainExecutor.execute(encoderPubL, nodeConfiguration);
    nodeMainExecutor.execute(encoderPubA, nodeConfiguration);
    nodeMainExecutor.execute(maxSpeedPub, nodeConfiguration);
    nodeMainExecutor.execute(batteryPub, nodeConfiguration);
    
    // Start the controller polling
    startControllerPolling();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    stopControllerPolling();
    rosHandler.removeCallbacks(rosRunnable);
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    switch (keyCode) {
      // controller
      case KeyEvent.KEYCODE_BUTTON_B:
        cANBus.controlWheel(0.0, 0.0, false);
        stopped = true;
        break;
      case KeyEvent.KEYCODE_BUTTON_A:
        stopped = false;
        break;
      case KeyEvent.KEYCODE_BUTTON_L1:
        linearSp -= 0.01;
        break;
      case KeyEvent.KEYCODE_BUTTON_R1:
        linearSp += 0.01;
        break;
      case KeyEvent.KEYCODE_BUTTON_X:
        cANBus.clearWheelError();
        break;

      // keyboard
      case KeyEvent.KEYCODE_W:
        linearSpToSend = linearSp;
      case KeyEvent.KEYCODE_S:
        linearSpToSend = -linearSp;
      case KeyEvent.KEYCODE_A:
        angularSpToSend = angularSp;
      case KeyEvent.KEYCODE_D:
        angularSpToSend = -angularSp;
      case KeyEvent.KEYCODE_Q:
        linearSp -= 0.1;
      case KeyEvent.KEYCODE_E:
        linearSp += 0.1;
      case KeyEvent.KEYCODE_0:
        linearSp = 0.0;
      case KeyEvent.KEYCODE_1:
        linearSp = 0.1;
      case KeyEvent.KEYCODE_2:
        linearSp = 0.2;
      case KeyEvent.KEYCODE_3:
        linearSp = 0.3;
    }

    if (linearSp < 0) {
      linearSp = 0.0;
    } else if (linearSp > 0.5) {
      linearSp = 0.5;
    }
    angularSp = linearSp * 2;
    return true;
  }

  @Override
  public boolean onKeyUp(int keyCode, KeyEvent event) {
    switch (keyCode) {
      case KeyEvent.KEYCODE_W:
      case KeyEvent.KEYCODE_S:
        linearSpToSend = 0.0;
      case KeyEvent.KEYCODE_A:
      case KeyEvent.KEYCODE_D:
        angularSpToSend = 0.0;
    }
    return true;
  }

  @Override
  public boolean onGenericMotionEvent(MotionEvent event) {
    // check if event came from a controller

    if ((event.getSource() & InputDevice.SOURCE_JOYSTICK) == InputDevice.SOURCE_JOYSTICK &&
      event.getAction() == MotionEvent.ACTION_MOVE) {

      float xAxis = event.getAxisValue(MotionEvent.AXIS_Z);
      float yAxis = event.getAxisValue(MotionEvent.AXIS_Y);

      if (abs(xAxis) < 0.2) {
        xAxis = 0.0f;
      }
      if (abs(yAxis) < 0.2) {
        yAxis = 0.0f;
      }

      linearSpToSend = -linearSp * yAxis;
      angularSpToSend = -angularSp * xAxis;
      return true;
    }
    return super.onGenericMotionEvent(event);
  }

  private void startControllerPolling() {
    handlerThread.start();
    handler = new Handler(handlerThread.getLooper());
    handler.post(pollingRunnable);
    rosHandler.post(rosRunnable);
  
    cANBus.setAccelerationData((byte) 1, 0.4);
    cANBus.setAccelerationData((byte) 2, 0.6);
    cANBus.setAccelerationData((byte) 3, 1.0);
  }

  private void stopControllerPolling() {
    isRunning = false;
    handlerThread.quitSafely();
  }

  private final Runnable pollingRunnable = new Runnable() {
    @Override
    public void run() {
      if (isRunning) {
        if (stopped) {
          cANBus.controlWheel(0.0, 0.0, false);
        } else {
          cANBus.controlWheel(linearSpToSend, angularSpToSend, true);
        }

        // pollController()
        handler.postDelayed(this, 5);
      }
    }
  };

  private final Runnable rosRunnable = new Runnable() {
    @Override
    public void run() {
      if (isRunning) {
        // Estop is enabled
        if (stopped) {
          linearPub.publishDouble(0.0);
          angularPub.publishDouble(0.0);
          maxSpeedPub.publishDouble(linearSp);
          encoderPubL.publishDouble(cANBus.getEncoder().getSpeed().getLineSpeed());
          encoderPubA.publishDouble(cANBus.getEncoder().getSpeed().getAngularSpeed());
          batteryPub.publishInt(cANBus.getLastPowerPercent());
        } else {
          // Estop is disabled - robot is accepting movement commands
          linearPub.publishDouble(linearSpToSend);
          angularPub.publishDouble(angularSpToSend);
          maxSpeedPub.publishDouble(linearSp);
          encoderPubL.publishDouble(cANBus.getEncoder().getSpeed().getLineSpeed());
          encoderPubA.publishDouble(cANBus.getEncoder().getSpeed().getAngularSpeed());
          batteryPub.publishInt(cANBus.getLastPowerPercent());
        }
        // 100ms delay
        rosHandler.postDelayed(this, 100);
      }
    }
  };
}
