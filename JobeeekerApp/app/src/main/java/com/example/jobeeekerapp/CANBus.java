package com.example.jobeeekerapp;

import android.content.Context;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

import com.example.jobeeekerapp.serialize.ChargeState;
import com.example.jobeeekerapp.serialize.WheelError;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

public class CANBus {
  private static CANBus instance;
  private final String TAG;
  private final ArrayBlockingQueue<byte[]> dataQueue;
  private final Encoder encoder;
  private final Gyroscope gyroscope;
  private boolean isConnect;
  public volatile boolean receivedCAN = false;
  public volatile boolean receivedEncoder = false;
  public volatile boolean receivedIMU = false;
  private ChargeState lastChargeState;
  private volatile int lastPowerPercent;
  private double[] lastAcceleration;
  private boolean isWheelLocked;
  private List<WheelError>[] leftWheelLastError;
  private List<WheelError>[] rightWheelLastError;
  private long lastMotorNormalUpdateTimer;
  private AtomicReference<Thread> recvThread;
  private AtomicReference<Thread> sendThread;
  private InputStream socketInputStream;
  private OutputStream socketOutputStream;
  private Context appContext;
  private CANParser canParser;

  private static final int BAUD_RATE = 10000;
  public static final int ACTION_POINTER_INDEX_MASK = 65280;

  /**
   * Retrieves the singleton instance of the `CANBus` class, creating it if necessary.
   *
   * @param appContext The application context used to initialize the `CANBus` instance.
   *
   * @return The singleton instance of `CANBus`, initialized with the provided context.
   */
  public static CANBus getInstance(Context appContext) {
    instance = new CANBus(appContext);
    return instance;
  }

  /**
   * Retrieves the singleton instance of the `CANBus` class.
   *
   * @return The singleton instance of `CANBus`, or `null` if it has not been initialized.
   */
  public static CANBus getInstance() {
    return instance;
  }

  /**
   * Initializes a new instance of the CANBus class.
   *
   * @param appContext The application context used for accessing
   *                   application-specific resources and classes.
   */
  private CANBus(Context appContext) {
    this.TAG = "CANBus.java";
    this.canParser = new CANParser(this);
    this.dataQueue = new ArrayBlockingQueue<>(10);
    this.sendThread = new AtomicReference<>(null);
    this.recvThread = new AtomicReference<>(null);
    this.appContext = appContext;
    this.encoder = new Encoder();
    this.gyroscope = new Gyroscope();

    this.lastChargeState = ChargeState.Idle;
    this.lastPowerPercent = 100;
    this.lastAcceleration = new double[4];
    this.leftWheelLastError = new ArrayList[3];
    this.rightWheelLastError = new ArrayList[3];
    for (int i = 0; i < leftWheelLastError.length; i++) {
      this.leftWheelLastError[i] = new ArrayList<>();
      this.rightWheelLastError[i] = new ArrayList<>();
    }
  }

  /**
   * Handles the receipt of a CAN message with a universal identifier.
   *
   * @param id    The identifier of the CAN message.
   * @param bytes The data bytes of the CAN message.
   */
  public void onReceiveUNIV(final int id, byte[] bytes) {
    // Write received data to Log
    Log.d("Receiving", "id: " + id + ", bytes: " + bytesToHex(bytes));

    // Use CANParser to parse data
    canParser.parserManager(id, bytes);
  }

  /**
   * Boots up the CANBus by connecting to the CAN socket service,
   * starting send and receive threads, and sending setup data to the CAN bus.
   */
  public void bootUp() {
    // Connect to can socket
    connectToCanSocketService();
    if (!this.isConnect) {
      return;
    }

    // open send and receive threads
    startSendCAN();
    startReceiveCAN();

    // Send setup data to CAN. Setups up connected components
    sendGBYM(new byte[] { 22, 2, 4, 1, 0, 0, -23 });
    sendGBYM(new byte[] { 22, 13, 4, 1, 0, 0, -23 }); // NEW
    sendGBYM(new byte[] { 22, 4, 4, 1, 0, 0, -23 });
    sendGBYM(new byte[] { 22, 5, 4, 1, 0, 0, -23 }); // NEW
    sendGBYM(new byte[] { 22, 6, 4, 1, 0, 0, -23 });
    sendGBYM(new byte[] { 22, -1, -31, 4, 0, 0, -23 });

    Log.i(this.TAG, "Boot up complete");
  }

  /**
   * Connects to the CAN socket service by extracting the appropriate CAN service
   * binary from the assets,
   * making it executable, and establishing a connection.
   */
  public void connectToCanSocketService() {
    String funTag = "connectToCanSocketService, ";
    boolean useCanServiceUsb = Build.VERSION.SDK_INT > 22;
    Log.i(this.TAG, funTag + "release can_service from assets, use_can_service_usb:" + useCanServiceUsb);
    File filesDir = this.appContext.getFilesDir();
    String absolutePath = filesDir.getAbsolutePath();

    try {
      // Open the appropriate CAN service binary from assets
      InputStream open = this.appContext.getAssets().open(useCanServiceUsb ? "can_service_usb" : "can_service_socket");
      File file = new File(absolutePath + "/can_service");

      // If the file doesn't exist, create it
      if (!file.exists()) {
        file.createNewFile();
      }

      // Write the contents of the binary to the file
      FileOutputStream fileOutputStream = new FileOutputStream(file);
      byte[] bArr = new byte[1024];
      int read;
      while ((read = open.read(bArr)) > 0) {
        fileOutputStream.write(bArr, 0, read);
      }

      // Flush and close the streams
      fileOutputStream.flush();
      fileOutputStream.close();
      open.close();
    } catch (FileNotFoundException unused) {
      Log.i(this.TAG, funTag + "can_service can not be overwrite, maybe busy");
      return;
    } catch (IOException e) {
      Log.e(this.TAG, funTag + "put can_service fail " + Log.getStackTraceString(e));
      return;
    }
    Log.i(this.TAG, funTag + "release can_service success");

    // Kill any existing can_service process
    killUnusedExe("can_service");

    // Make the CAN service executable
    Tools.execCommand("chmod 777 " + absolutePath + "/can_service", useCanServiceUsb);

    // Execute the CAN service with the current elapsed time as an argument
    long elapsedRealtime = SystemClock.elapsedRealtime();
    Tools.execCommand(absolutePath + "/can_service " + elapsedRealtime, useCanServiceUsb);
    Log.i(this.TAG, funTag + "connecting can_service");

    // Connect to the CAN service via LocalSocket
    try {
      LocalSocket localSocket = new LocalSocket();
      String sb = "/pudu/can_service" + (useCanServiceUsb ? elapsedRealtime : "");
      localSocket.connect(new LocalSocketAddress(sb));
      this.socketOutputStream = localSocket.getOutputStream();
      this.socketInputStream = localSocket.getInputStream();
      Log.d(this.TAG, funTag + "connect succeed");
      this.isConnect = true;
    } catch (IOException e) {
      Log.e(this.TAG, funTag + "connect fail: " + Log.getStackTraceString(e));
    }
  }

  /**
   * Starts the thread responsible for receiving data from the CAN service.
   */
  private void startReceiveCAN() {
    final String funTag = "startReceiveCAN, ";
    if (recvThread.get() == null) {
      final byte[] bArr = new byte[8];
      recvThread.set(new Thread("RecvCAN") {
        @Override
        public void run() {
          try {
            while (true) {
              // Read data from the CAN service input stream
              InputStream inputStream = CANBus.this.socketInputStream;
              Integer bytesRead = (inputStream != null) ? inputStream.read(bArr, 0, 8) : null;

              if (bytesRead != null && bytesRead == 8) {
                // Data successfully read, process it
                CANBus.this.receivedCAN = true;
                byte b = bArr[0];
                byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
                CANBus.this.onReceiveUNIV(b, copyOf);
              } else {
                // Log error if the received data size is incorrect
                Log.e(CANBus.this.TAG, funTag + "recv CAN size error: " + bytesRead);
              }
            }
          } catch (Exception e) {
            Log.e(CANBus.this.TAG, funTag + "recv exception: " + Log.getStackTraceString(e));
            CANBus.this.recvThread.set(null);
          }
        }
      });

      // Start the receive thread
      recvThread.get().start();
    }
  }

  /**
   * Starts the thread responsible for sending data to the CAN service.
   */
  private void startSendCAN() {
    sendThread.set(new Thread("SendCAN") {
      @Override
      public void run() {
        try {
          while (true) {
            try {
              // Take data from the queue
              byte[] data = CANBus.this.dataQueue.take();
              byte[] copyOf = Arrays.copyOf(data, data.length);
              String hexString = bytesToHex(copyOf);
              Log.d(CANBus.this.TAG, "output to can service " + hexString);

              // Write data to the CAN service output stream
              OutputStream outputStream = CANBus.this.socketOutputStream;
              if (outputStream != null) {
                outputStream.write(copyOf);
              }
            } catch (IOException e) {
              Log.e(CANBus.this.TAG, "send exception: " + Log.getStackTraceString(e));
              CANBus.this.sendThread.set(null);
              return;
            }
          }
        } catch (Exception e) {
          Log.e(CANBus.this.TAG, "send exception: " + Log.getStackTraceString(e));
          CANBus.this.sendThread.set(null);
        }
      }
    });

    // Start the send thread
    sendThread.get().start();
  }

  /**
   * Converts a byte array to its hexadecimal string representation.
   *
   * @param bytes The byte array to be converted.
   * @return The hexadecimal string representation of the byte array.
   */
  private static String bytesToHex(byte[] bytes) {
    StringBuilder hexString = new StringBuilder();
    for (byte b : bytes) {
      hexString.append(String.format("%02X", b));
    }
    return hexString.toString();
  }

  /**
   * Kills any running instances of the specified executable.
   *
   * @param exe The name of the executable to be killed.
   */
  private final void killUnusedExe(String exe) {
    Pair<Integer, String> execCommandResult = Tools.execCommand("ps -A | grep " + exe, false);
    String format = String.format("%d - %s", Arrays.copyOf(new Object[] { execCommandResult.first, execCommandResult.second }, 2));
    Log.i(this.TAG, "kill " + exe + " " + format);

    Integer exitCode = execCommandResult.first;
    if (exitCode != null && exitCode == 0) {
      String commandOutput = execCommandResult.second;
      if (commandOutput.contains(exe)) {
        String[] commandLines = commandOutput.split("\n");
        int numLines = commandLines.length;
        if (numLines > 0) {
          for (String line : commandLines) {
            String[] fields = line.split("\\s", 6);
            int numFields = fields.length;
            if (numFields > 0) {
              Log.i(this.TAG, "fields: " + Arrays.toString(fields));
              if (fields.length > 1) {
                String pid = null;
                for (int i = 1; i < fields.length && pid == null; i++) {
                  String field = fields[i];
                  int length = field.length() - 1;
                  int start = 0;
                  boolean foundNonSpace = false;
                  while (start <= length) {
                    boolean isSpace = field.charAt(!foundNonSpace ? start : length) <= ' ';
                    if (foundNonSpace) {
                      if (!isSpace) {
                        break;
                      } else {
                        length--;
                      }
                    } else if (isSpace) {
                      start++;
                    } else {
                      foundNonSpace = true;
                    }
                  }
                  if (!TextUtils.isEmpty(field.subSequence(start, length + 1).toString())) {
                    pid = fields[i];
                  }
                }
                if (pid == null) {
                  try {
                    throw new NullPointerException("PID in killUnusedExe is null");
                  } catch (Exception e) {
                    Log.e(this.TAG, "parse pid failed", e);
                    return;
                  }
                }
                Tools.execCommand("kill " + Integer.parseInt(pid), true);
              }
            } else {
              throw new ClassCastException("null cannot be cast to non-null type Array<T>");
            }
          }
          return;
        }
        throw new ClassCastException("null cannot be cast to non-null type Array<T>");
      }
    }
  }

  /**
   * Sends data to the CAN bus with specific formatting and padding.
   *
   * @param bytes The byte array to be sent. If the array is smaller than 7 bytes,
   *              it will be padded with zeros.
   *              If the array is exactly 7 bytes, a checksum byte will be appended.
   *              If the array is not null and its length is 8, it will be sent to
   *              the data queue.
   */
  public void sendGBYM(byte[] bytes) {
    if (bytes == null || bytes.length == 0) {
      Log.d(this.TAG, "send empty data");
      return;
    }

    byte[] data = bytes;
    int size = bytes.length;

    // If the data size is less than 7, pad it with zeros
    if (size < 7) {
      int paddingSize = 7 - size;
      byte[] padding = new byte[paddingSize];
      Arrays.fill(padding, (byte) 0);
      byte[] paddedData = new byte[data.length + padding.length];
      System.arraycopy(data, 0, paddedData, 0, data.length);
      System.arraycopy(padding, 0, paddedData, data.length, padding.length);
      data = paddedData;
      Log.d(this.TAG, "data padded to 7 bytes");
    }

    // If the data size is exactly 7, calculate and append a checksum byte
    if (data.length == 7) {
      byte checksum = 0;
      for (byte b : data) {
        checksum += b;
      }
      byte[] dataWithChecksum = new byte[data.length + 1];
      System.arraycopy(data, 0, dataWithChecksum, 0, data.length);
      dataWithChecksum[data.length] = checksum;
      data = dataWithChecksum;
    }

    // If the data size is 8, add it to the data queue
    if (data.length == 8) {
      try {
        this.dataQueue.put(data);
      } catch (InterruptedException e) {
        Log.d(this.TAG, "dataQueue was interrupted");
      }
      return;
    }

    // If the data size is not 8, log an invalid data size error
    Log.d(this.TAG, "send invalid data size: " + data.length);
  }

  /**
   * Controls the IR LED of the camera by sending a specific command to the CAN
   * bus.
   *
   * @param lightOn If true, turns the IR LED on. If false, turns it off.
   */
  public void controlCameraIRDLED(boolean lightOn) {
    // Prepare the command to control the IR LED
    // The first byte (113) is the command identifier
    // The second byte is 1 if the light should be on, 0 if it should be off
    // The remaining bytes are padding to meet the required length
    byte[] command = new byte[] { 113, lightOn ? (byte) 1 : (byte) 0, 0, 0, 0, 0, 0 };

    // Send the command via the CAN bus
    sendGBYM(command);
  }

  /**
   * Controls the wheels of the robot by sending speed and mode commands to the
   * CAN bus.
   *
   * @param linearSpeed  The linear speed to be set for the wheels.
   * @param angularSpeed The angular speed to be set for the wheels.
   * @param isCloseLoop  If true, enables close loop control. If false, disables
   *                     it.
   */
  public void controlWheel(double linearSpeed, double angularSpeed, boolean isCloseLoop) {
    // Calculate the wheel speeds based on the linear and angular speeds, and the wheel spacing
    int leftWheelSpeed = (int) (((linearSpeed * 2 - (this.encoder.getWheelSpacing() * angularSpeed)) / 2) * BAUD_RATE);
    int rightWheelSpeed = (int) (((linearSpeed * 2 + (this.encoder.getWheelSpacing() * angularSpeed)) / 2) * BAUD_RATE);

    // Determine the control byte based on whether close loop control is enabled
    byte controlByte = isCloseLoop ? (byte) 64 : (byte) 0;

    // If both speeds are zero and close loop control is enabled, set the control byte to zero
     if (false && linearSpeed == 0.0d && angularSpeed == 0.0d && isCloseLoop) {
      controlByte = (byte) 0;
     }

    // Create the command byte array to send to the CAN bus
    // 64 is the command identifier
    // The next two bytes are the high and low bytes of the left wheel speed
    // The next two bytes are the high and low bytes of the right wheel speed
    // The next byte is the control byte
    // The last byte is padding
    byte[] command = new byte[] {
        64,
        (byte) ((leftWheelSpeed & 0xFF00) >> 8),
        (byte) (leftWheelSpeed & 0xFF),
        (byte) ((rightWheelSpeed & 0xFF00) >> 8),
        (byte) (rightWheelSpeed & 0xFF),
        controlByte,
        0
    };

    // Send the command via the CAN bus
    sendGBYM(command);
  }

  /**
   * Requests acceleration data of a specific type from the CAN bus.
   *
   * @param type The type of acceleration data to be requested.
   *             This can be one of the following:
   *             - 0: Unknown
   *             - 1: Acceleration
   *             - 2: Deceleration
   *             - 3: EmergencyStopDeceleration
   */
  public void getAccelerationData(Byte type) {
    // Create the command byte array to request acceleration data
    // The first byte is 0 (command identifier)
    // The second byte is 73 (sub-command identifier)
    // The third byte is the type of acceleration data requested
    // The remaining bytes are padding
    byte[] command = new byte[] { 0, 73, type, 0, 0, 0, 0 };

    // Send the command via the CAN bus
    sendGBYM(command);
  }

  /**
   * Sets acceleration data of a specific type and sends it to the CAN bus.
   *
   * @param type The type of acceleration data to be set. This can be:
   *             - 0: Unknown
   *             - 1: Acceleration
   *             - 2: Deceleration
   *             - 3: EmergencyStopDeceleration
   * @param data The acceleration data value to be set.
   */
  public void setAccelerationData(Byte type, double data) {
    // Convert the acceleration data to bytes after scaling it by the BAUD_RATE
    byte[] bytes = ByteBuffer.allocate(4).putInt((int) (data * BAUD_RATE)).array();

    // Log the acceleration data being set
    String logMessage = "setAccelerationData " + bytesToHex(bytes);
    Log.d(this.TAG, logMessage);

    // Create the command byte array to set the acceleration data
    // The first byte is 72 (command identifier)
    // The second byte is the type of acceleration data
    // The next four bytes are the scaled acceleration data
    // The last byte is padding
    byte[] command = new byte[] { 72, type, bytes[0], bytes[1], bytes[2], bytes[3], 0 };

    // Send the command via the CAN bus
    sendGBYM(command);
  }

  /**
   * Clears any wheel errors by sending a specific command to the CAN bus.
   */
  public void clearWheelError() {
    // Create the command byte array to clear wheel errors
    // The first byte is 65 (command identifier)
    // The second byte is 1 (sub-command identifier to clear wheel error)
    byte[] command = new byte[] { 65, 1 };

    // Send the command via the CAN bus
    sendGBYM(command);
  }

  /**
   * Performs geomagnetic calibration by sending the calibration parameters to the
   * CAN bus.
   *
   * @param leftMax  The maximum value for the left geomagnetic sensor.
   * @param rightMax The maximum value for the right geomagnetic sensor.
   * @param openFlag A flag to indicate whether to open (true) or close (false)
   *                 the calibration.
   */
  public void geomagneticCalibration(int leftMax, int rightMax, boolean openFlag) {
    // Convert the leftMax and rightMax values to bytes
    byte[] leftMaxBytes = { (byte) ((leftMax >> 8) & 255), (byte) (leftMax & 255) };
    byte[] rightMaxBytes = { (byte) ((rightMax >> 8) & 255), (byte) (rightMax & 255) };

    // Create the command byte array for geomagnetic calibration
    // The first byte is -84 (command identifier)
    // The second byte is the openFlag (1 for open, 0 for close)
    // The next two bytes are the leftMax value
    // The next two bytes are the rightMax value
    // The last byte is padding
    byte[] command = new byte[] { -84, openFlag ? (byte) 1 : (byte) 0, leftMaxBytes[0], leftMaxBytes[1],
        rightMaxBytes[0], rightMaxBytes[1], 0 };

    // Send the command via the CAN bus
    sendGBYM(command);
  }

  /**
   * Controls the disinfection power by sending a command to the CAN bus.
   *
   * @param powerOn A flag to indicate whether to turn the disinfection power on
   *                (true) or off (false).
   */
  public void controlDisinfectionPower(boolean powerOn) {
    // Create the command byte array to control disinfection power
    // The first byte is -125 (command identifier)
    // The second byte is the powerOn flag (1 for on, 0 for off)
    // The remaining bytes are padding
    byte[] command = new byte[] { -125, powerOn ? (byte) 1 : (byte) 0, 0, 0, 0, 0, 0 };

    // Send the command via the CAN bus
    sendGBYM(command);
  }

  /**
   * Opens or closes the head USB port by sending a command to the CAN bus.
   *
   * @param open A flag to indicate whether to open (true) or close (false) the
   *             head USB port.
   */
  public void openOrCloseHeadUsb(boolean open) {
    // Create the command byte array to open or close the head USB port
    // The first byte is -32 (command identifier)
    // The second byte is the open flag (1 for open, 0 for close)
    // The remaining bytes are padding
    byte[] command = new byte[] { -32, open ? (byte) 1 : (byte) 0, 0, 0, 0, 0, 0 };

    // Send the command via the CAN bus
    sendGBYM(command);
  }

  /**
   * Requests the hardware version by sending a command to the CAN bus.
   */
  public void getHardwareVersion() {
    // Create the command byte array to request the hardware version
    // The first byte is 0 (command identifier)
    // The second byte is 19 (specific request identifier)
    // The remaining bytes are padding
    byte[] command = new byte[] { 0, 19, 0, 0, 0, 0, 0 };

    // Send the command via the CAN bus
    sendGBYM(command);
  }

  /**
   * Handles notifications of wheel errors by updating the internal error lists
   * and logging the errors.
   *
   * @param leftError  The list of errors associated with the left wheel.
   * @param rightError The list of errors associated with the right wheel.
   */
  public void notifyWheelError(final List<WheelError> leftError, final List<WheelError> rightError, int index) {
    // Clear the previous error lists
    this.leftWheelLastError[index] = leftError;
    this.rightWheelLastError[index] = rightError;

    // If there are no errors in either wheel, check the elapsed time since the last
    // motor update
    if (leftError.isEmpty() && rightError.isEmpty()) {
      long elapsedRealtime = SystemClock.elapsedRealtime();

      // Update the motor timer if more than 1000 milliseconds have passed
      if (elapsedRealtime - this.lastMotorNormalUpdateTimer > 1000) {
        this.lastMotorNormalUpdateTimer = elapsedRealtime;
      }
      return;
    }

    // If there are wheel errors, reset the motor normal update timer
    this.lastMotorNormalUpdateTimer = 0L;

    // Log the wheel errors for debugging purposes
    Log.d(this.TAG, "onWheelError " + leftError + ' ' + rightError);
  }

  /**
   * Retrieves the Encoder instance associated with the CANBus.
   *
   * @return The Encoder instance.
   */
  public Encoder getEncoder() {
    return encoder;
  }

  /**
   * Retrieves the Gyroscope instance associated with the CANBus.
   *
   * @return The Gyroscope instance.
   */
  public Gyroscope getGyroscope() {
    return gyroscope;
  }

  /**
   * Sets the status of whether the encoder data has been received.
   *
   * @param hasReceived True if the encoder data has been received, false
   *                    otherwise.
   */
  public void setReceivedEncoder(Boolean hasReceived) {
    this.receivedEncoder = hasReceived;
  }

  /**
   * Sets the status of whether the IMU (Inertial Measurement Unit) data has been
   * received.
   *
   * @param hasReceived True if the IMU data has been received, false otherwise.
   */
  public void setReceivedIMU(Boolean hasReceived) {
    this.receivedIMU = hasReceived;
  }

  /**
   * Sets the last recorded power percentage.
   *
   * @param lastPower The last recorded power percentage to be set.
   */
  public void setLastPowerPercent(int lastPower) {
    this.lastPowerPercent = lastPower;
  }

  /**
   * Sets the last recorded charge state.
   *
   * @param newState The new charge state to be set.
   */
  public void setLastChargeState(ChargeState newState) {
    this.lastChargeState = newState;
  }

  /**
   * Retrieves the last recorded charge state.
   *
   * @return The last recorded charge state.
   */
  public ChargeState getLastChargeState() {
    return this.lastChargeState;
  }

  /**
   * Retrieves the timestamp of the last normal update for the motor.
   *
   * @return The timestamp of the last motor normal update in milliseconds.
   */
  public long getLastMotorNormalUpdateTimer() {
    return this.lastMotorNormalUpdateTimer;
  }

  /**
   * Retrieves the list of wheel errors for either the left or right wheel.
   *
   * @param left If true, retrieves the errors for the left wheel; otherwise,
   *             retrieves the errors for the right wheel.
   * @return A list of wheel errors for the specified wheel.
   */
  public List<WheelError> getWheelError(Boolean left, int index) {
    if (left) {
      return leftWheelLastError[index];
    }
    return rightWheelLastError[index];
  }

  /**
   * Retrieves a string representation of the most recent errors for the left wheel.
   *
   * @return A comma-separated string of error names representing the most recent errors for the left wheel.
   *         If no errors are present, the string will be empty.
   */
  public String getLeftWheelError() {
    List<String> errors = new ArrayList<>();

    for (List<WheelError> w : leftWheelLastError) {
      for (WheelError error : w) {
        errors.add(error.name());
      }
    }
    return String.join(", ", errors);
  }

  /**
   * Retrieves a string representation of the most recent errors for the right wheel.
   *
   * @return A comma-separated string of error names representing the most recent errors for the right wheel.
   *         If no errors are present, the string will be empty.
   */
  public String getRightWheelError() {
    List<String> errors = new ArrayList<>();

    for (List<WheelError> w : rightWheelLastError) {
      for (WheelError error : w) {
        errors.add(error.name());
      }
    }

    return String.join(", ", errors);
  }

  /**
   * Retrieves the last recorded power percentage.
   *
   * @return The last recorded power percentage.
   */
  public int getLastPowerPercent() {
    return this.lastPowerPercent;
  }

  /**
   * Sets the last acceleration value for a given type.
   *
   * @param acceleration The acceleration value to be set.
   * @param type         The type of acceleration.
   *                     This can be one of the following:
   *                     - 0: Unknown
   *                     - 1: Acceleration
   *                     - 2: Deceleration
   *                     - 3: EmergencyStopDeceleration
   */
  public void setLastAcceleration(double acceleration, byte type) {
    // Ensure the type index is within the bounds of the array
    if (type >= 0 && type < this.lastAcceleration.length) {
      this.lastAcceleration[type] = acceleration;
    } else {
      Log.e(this.TAG, "Invalid acceleration type: " + type);
    }
  }

  /**
   * Sets the last acceleration value for a given type.
   *
   * @param type The type of acceleration.
   *             This can be one of the following:
   *             - 0: Unknown
   *             - 1: Acceleration
   *             - 2: Deceleration
   *             - 3: EmergencyStopDeceleration
   *
   * @return The last recorded acceleration value for the given type
   */
  public double getLastAcceleration(byte type) {
    return this.lastAcceleration[type];
  }

  /**
   * Sets the wheel lock status.
   *
   * @param isLocked Indicates whether the wheel is locked (true) or unlocked
   *                 (false).
   */
  public void setWheelLocked(boolean isLocked) {
    this.isWheelLocked = true;
  }

  /**
   * Retrieves the current wheel lock status.
   *
   * @return true if the wheel is locked, false otherwise.
   */
  public boolean getWheelLocked() {
    return this.isWheelLocked;
  }

  /**
   * Gets whether the `CANBus` instance is currently connected.
   *
   * @return true if the `CANBus` instance is connected; `false` otherwise.
   */
  public boolean getIsConnect() { return this.isConnect; }

  /**
   * Retrieves the current instance of the CANParser associated with the CANBus.
   *
   * @return The CANParser instance currently associated with the CANBus.
   */
  public CANParser getCanParser() {
    return this.canParser;
  }
}
