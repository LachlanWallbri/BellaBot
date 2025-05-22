package org.ros.android.android_tutorial_pubsub;

import android.content.Context;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import org.ros.android.android_tutorial_pubsub.serialize.ChargeState;
import org.ros.android.android_tutorial_pubsub.serialize.WheelError;
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


public final class CANBus {
    private final String TAG;
    private final ArrayBlockingQueue<byte[]> dataQueue;
    private final Encoder encoder;
    private final Gyroscope gyroscope;
    private boolean isConnect;
    private volatile boolean receivedCAN = false;
    private volatile boolean receivedEncoder = false;
    private volatile boolean receivedIMU = false;
    private ChargeState lastChargeState;
    private volatile int lastPowerPercent;
    private List<WheelError> leftWheelLastError;
    private List<WheelError> rightWheelLastError;
    private long lastMotorNormalUpdateTimer;
    private AtomicReference<Thread> recvThread;
    private AtomicReference<Thread> sendThread;
    private InputStream socketInputStream;
    private OutputStream socketOutputStream;
    private Context appContext;
    private CANParser canParser;

    private static final int BAUD_RATE = 10000;
    public static final int ACTION_POINTER_INDEX_MASK = 65280;

    public CANBus(Context appContext) {
        this.TAG = "CANBus.java";
        this.canParser = new CANParser(this);
        this.dataQueue = new ArrayBlockingQueue<>(10);
        this.sendThread = new AtomicReference<>(null);
        this.recvThread = new AtomicReference<>(null);
        this.appContext = appContext;
        this.encoder = new Encoder();
        this.gyroscope = new Gyroscope();

        this.lastChargeState = ChargeState.Idle;        this.lastPowerPercent = 100;
        this.leftWheelLastError = new ArrayList<WheelError>();
        this.rightWheelLastError = new ArrayList<WheelError>();

    }

    public final void onReceiveUNIV(final int id, byte[] bytes) {
        // Write received data to Log
        String sb = "id: " + id + ", bytes: " + bytesToHex(bytes);
        Log.d("Receiving", sb);

        // Use CANParser to parse data
        canParser.parserManager(id, bytes);
    }

    public void bootUp() {
        // Connect to can socket
        connectToCanSocketService();

        // open send and receive threads
        startSendCAN();
        startReceiveCAN();

        // Send setup data to CAN. Presumably sets up components
        sendGBYM(new byte[]{22, 2, 4, 1, 0, 0, -23});
        sendGBYM(new byte[]{22, 13, 4, 1, 0, 0, -23}); // NEW
        sendGBYM(new byte[]{22, 4, 4, 1, 0, 0, -23});
        sendGBYM(new byte[]{22, 5, 4, 1, 0, 0, -23}); // NEW
        sendGBYM(new byte[]{22, 6, 4, 1, 0, 0, -23});
        sendGBYM(new byte[]{22, -1, -31, 4, 0, 0, -23});

        Log.i(this.TAG, "weird send complete");
    }

    public final void connectToCanSocketService() {
        // MIGHT NEED FUNCTIONALITY FROM FERNFLOWER DECOMPILATION. JADX SEEMS TO BE MISSING CODE.
        // EDIT: I HAVE FOUND THIS CODE IN JADX AT BETTER QUALITY BY ENABLING AN EXTRA PREFERENCE
        // ---------------------- STRUCTURE ----------------------
        // ----------------------  On init  ----------------------
        // try get app assets this.appContext.getAssets(); -- This is the home of our socket presumably
        // Check if VERSION.SDK_INT > 22, if so then we are using can_service_usb, else using can_service_socket
        // from our appAssetManager, we now attempt to open either can_service_usb or can_service_socket, depending on above
        // if we can, this is our new InputStream and we also make a new file at our absolute asset path/can_service. If it doesn't exist, we create it
        // We now make a new OutputStream at this file and initialise a 1024 byte array
        //
        // While(true), we want to try read from our input stream into our 1024 byte array, let call the val returned nextByte
        // if nextByte is <= 0, this means that the end of the input stream has been reached.
        //      - In this case, we want to flush our output stream and close both input and output
        // else, we want to write to our OutputStream, using our 1024 byte array, and give it the length of the data.
        // This seems to be us copying over the driver to a new local file
        //
        // Next, kill existing can_service using killUnusedExe
        // Execute chmod 777/'absolutePath'/can_service to make it readable, writeable and executable
        // executes something else as well. (absolutePath)/can_service (SystemClock.elapsedRealtime())
        // This seems to run our CAN driver with an argument of current time
        // Note: execCommand is a pudutech base tool which takes a bool for VERSION.SDK_INT > 22.
        // ^This is checking android version. Android 8.1 makes this TRUE, so we are using a usb driver
        // It either then uses su or sh, which mean either super user (su) via root or standard user permission with shell (sh).
        // This means that the factory test app has root permission. We need to be able to give our app these perms

        String funTag = "connectToCanSocketService, ";

        boolean useCanServiceUsb = Build.VERSION.SDK_INT > 22;
        Log.i(this.TAG, funTag + "release can_service from assets, use_can_service_usb:" + useCanServiceUsb);
        File filesDir = this.appContext.getFilesDir();
        String absolutePath = filesDir.getAbsolutePath();
        try {
            InputStream open = this.appContext.getAssets().open(useCanServiceUsb ? "can_service_usb" : "can_service_socket");
            File file = new File(absolutePath + "/can_service");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read <= 0) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            open.close();
        } catch (FileNotFoundException unused) {
            Log.i(this.TAG, funTag + "can_service can not be overwrite, maybe busy");
        } catch (IOException e) {
            Log.e(this.TAG, funTag + "put can_service fail " + Log.getStackTraceString(e));
        }
        Log.i(this.TAG, funTag + "release can_service success");
        killUnusedExe("can_service");
        Tools.execCommand("chmod 777 " + absolutePath + "/can_service", useCanServiceUsb);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Tools.execCommand(absolutePath + "/can_service " + elapsedRealtime, useCanServiceUsb);
        Log.i(this.TAG, funTag + "connecting can_service");

        //
        // Now we want to actually connect to can_service. This is all the JADX code
        //
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

    private void startReceiveCAN() {
        final String funTag = "startReceiveCAN, ";
        if (recvThread.get() == null) {
            final byte[] bArr = new byte[8];
            recvThread.set(new Thread("RecvCAN") {
                @Override
                public void run() {
                    try {
                        while (true) {
                            InputStream inputStream = CANBus.this.socketInputStream;
                            Integer bytesRead = (inputStream != null) ? inputStream.read(bArr, 0, 8) : null;
                            if (bytesRead != null && bytesRead == 8) {
                                CANBus.this.receivedCAN = true;
                                byte b = bArr[0];
                                byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
                                CANBus.this.onReceiveUNIV(b, copyOf);
                            } else {
                                Log.e(CANBus.this.TAG, funTag + "recv CAN size error: " + bytesRead);
                            }
                        }
                    } catch (Exception e) {
                        Log.e(CANBus.this.TAG, funTag + "recv exception: " + Log.getStackTraceString(e));
                        CANBus.this.recvThread.set(null);
                    }
                }
            });
            recvThread.get().start();
        }
    }

    private void startSendCAN() {
        sendThread.set(new Thread("SendCAN") {
            @Override
            public void run() {
                try {
                    while (true) {
                        try {
                            byte[] data = CANBus.this.dataQueue.take();
                            byte[] copyOf = Arrays.copyOf(data, data.length);
                            String hexString = bytesToHex(copyOf);
                            Log.d(CANBus.this.TAG, "output to can service " + hexString);
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
        sendThread.get().start();
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02X", b));
        }
        return hexString.toString();
    }

    private final void killUnusedExe(String exe) {
        int i = 0;
        Pair<Integer, String> execCommand = Tools.execCommand("ps -A | grep " + exe, false);
        String format = String.format("%d - %s", Arrays.copyOf(new Object[]{execCommand.first, execCommand.second}, 2));
        //Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        Log.i(this.TAG, "kill "+ exe + " " + format);
        Integer num = (Integer) execCommand.first;
        if (num != null && num == 0) {
            String secondStr = execCommand.second;
            //Intrinsics.checkExpressionValueIsNotNull(obj, "res.second");
            if (secondStr.contains(exe)) {
                //Intrinsics.checkExpressionValueIsNotNull(obj2, "res.second");
                String[] strArr = secondStr.split(System.lineSeparator());
                int length = strArr.length;
                if (length > 0) { // Need to make a different check
                    int i2 = 0;
                    while (i2 < length) {
                        String[] strArr2 = strArr[i2].split("\\s", 6);
                        int length2str = strArr2.length;
                        if (length2str > 0) {
                            Log.i(this.TAG, "fields: " + Arrays.toString(strArr2));
                            if (strArr2.length > 1) {
                                String str4 = null;
                                for (int i3 = 1; i3 < strArr2.length && str4 == null; i3++) {
                                    String str5 = strArr2[i3];
                                    int length2 = str5.length() - 1;
                                    int i4 = 0;
                                    boolean z = false;
                                    while (i4 <= length2) {
                                        boolean z2 = str5.charAt(!z ? i4 : length2) <= ' ';
                                        if (z) {
                                            if (!z2) {
                                                break;
                                            } else {
                                                length2--;
                                            }
                                        } else if (z2) {
                                            i4++;
                                        } else {
                                            z = true;
                                        }
                                    }
                                    if (!TextUtils.isEmpty(str5.subSequence(i4, length2 + 1).toString())) {
                                        str4 = strArr2[i3];
                                    }
                                }
                                if (str4 == null) {
                                    try {
                                        throw new NullPointerException("str4 in killUnusedExe is null");
                                    } catch (Exception e) {
                                        Log.e(this.TAG, "parse pid failed", e);
                                        return;
                                    }
                                }
                                Tools.execCommand("kill " + Integer.parseInt(str4), true);
                            }
                            i2++;
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

    public void sendGBYM(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            Log.d(this.TAG, "send empty data");
            return;
        }

        byte[] data = bytes;
        int size = bytes.length;

        if (size < 7) {
            int paddingSize = 7 - size;
            byte[] padding = new byte[paddingSize];
            Arrays.fill(padding, (byte) 0);
            byte[] result = new byte[data.length + padding.length];
            System.arraycopy(data, 0, result, 0, data.length);
            System.arraycopy(padding, 0, result, data.length, padding.length);
            data = result;
            Log.d(this.TAG, "send empty data");
        }

        if (data.length == 7) {
            byte checksum = 0;
            for (byte b : data) {
                checksum += b;
            }
            byte[] result = new byte[data.length + 1];
            System.arraycopy(data, 0, result, 0, data.length);
            result[data.length] = checksum;
            data = result;
        }

        if (data.length == 8) {
            try {
                this.dataQueue.put(data);
            } catch (InterruptedException e) {
                Log.d(this.TAG, "dataQueue was interrupted");
            }
            return;
        }

        Log.d(this.TAG, "send invalid data size: " + data.length);
    }

    public void controlCameraIRDLED(boolean lightOn) {
        sendGBYM(new byte[]{113, lightOn ? (byte) 1 : (byte) 0, 0, 0, 0, 0, 0});
    }

    public void controlWheel(double linearSpeed, double angularSpeed, boolean isCloseLoop) {
        int wheelSpacing = (int) (((linearSpeed * 2 - (this.encoder.getWheelSpacing() * angularSpeed)) / 2) * BAUD_RATE);
        int wheelSpacing2 = (int) (((linearSpeed * 2 + (this.encoder.getWheelSpacing() * angularSpeed)) / 2) * BAUD_RATE);
        byte controlByte = isCloseLoop ? (byte) 64 : (byte) 0;
//        if (linearSpeed == 0.0d && angularSpeed == 0.0d && isCloseLoop) {
//            controlByte = (byte) 0;
//            //controlByte = controlByte | Byte.MIN_VALUE;
//        }
        sendGBYM(new byte[]{64, (byte) ((wheelSpacing & ACTION_POINTER_INDEX_MASK) >> 8), (byte) (wheelSpacing & 255), (byte) ((wheelSpacing2 & ACTION_POINTER_INDEX_MASK) >> 8), (byte) (wheelSpacing2 & 255), controlByte, 0});
    }

    public void getAccelerationData(Byte type) {
        //    Unknown((byte) 0),
        //    Acceleration((byte) 1),
        //    Deceleration((byte) 2),
        //    EmergencyStopDeceleration((byte) 3);
        sendGBYM(new byte[]{0, 73, type, 0, 0, 0, 0});
    }

    public void setAccelerationData(Byte type, double data) {
        byte[] bytes = ByteBuffer.allocate(4).putInt((int) (data * BAUD_RATE)).array();
        String sb = "setAccelerationData " + bytesToHex(bytes);
        Log.d(this.TAG, sb);
        sendGBYM(new byte[]{72, type, bytes[0], bytes[1], bytes[2], bytes[3], 0});
    }

    public final void clearWheelError() {
        sendGBYM(new byte[]{65, 1});
    }

    public void geomagneticCalibration(int leftMax, int rightMax, boolean openFlag) {
        byte[] bArr = {(byte) ((leftMax >> 8) & 255), (byte) (leftMax & 255)};
        byte[] bArr2 = {(byte) ((rightMax >> 8) & 255), (byte) (rightMax & 255)};
        sendGBYM(new byte[]{-84, openFlag ? (byte) 1 : (byte) 0, bArr[0], bArr[1], bArr2[0], bArr2[1], 0});
    }

    public void controlDisinfectionPower(boolean powerOn) {
        sendGBYM(new byte[]{-125, powerOn ? (byte) 1 : (byte) 0, 0, 0, 0, 0, 0});
    }

    public void openOrCloseHeadUsb(Boolean open) {
        sendGBYM(new byte[]{-32, open ? (byte) 1 : (byte) 0, 0, 0, 0, 0, 0});
    }

    public void getHardwareVersion() {
        sendGBYM(new byte[]{0, 19, 0, 0, 0, 0, 0});
        // TODO : Test functionality
    }

    public void notifyWheelError(final List<WheelError> leftError, final List<WheelError> rightError) {
        this.leftWheelLastError.clear();
        this.rightWheelLastError.clear();
        if (leftError.isEmpty() && rightError.isEmpty()) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            // Check if we need to update our motor timer
            if (elapsedRealtime - this.lastMotorNormalUpdateTimer > 1000) {
                // Update our motor timer to show CANBus is still communicating
                this.lastMotorNormalUpdateTimer = elapsedRealtime;
            }
            return;
        }

        // We have encountered a wheel error, set normal update timer to zero
        this.lastMotorNormalUpdateTimer = 0L;
        Log.d(this.TAG, "onWheelError " + leftError + ' ' + rightError);
    }

    public Encoder getEncoder() {
        return encoder;
    }

    public Gyroscope getGyroscope() {
        return gyroscope;
    }

    public void setReceivedEncoder(Boolean hasReceived) {
        this.receivedEncoder = hasReceived;
    }

    public void setReceivedIMU(Boolean hasReceived) {
        this.receivedIMU = hasReceived;
    }

    public void setLastPowerPercent(int lastPower) {
        this.lastPowerPercent = lastPower;
    }

    public void setLastChargeState(ChargeState newState) {
        this.lastChargeState = newState;
    }

    public ChargeState getLastChargeState() {
        return this.lastChargeState;
    }

    public void setLastMotorNormalUpdateTimer(long timer) {
        this.lastMotorNormalUpdateTimer = timer;
    }

    public long getLastMotorNormalUpdateTimer() {
        return this.lastMotorNormalUpdateTimer;
    }

    public List<WheelError> getWheelError(Boolean left) {
        if (left) {
            return leftWheelLastError;
        }
        return rightWheelLastError;
    }

    public int getLastPowerPercent() {
        return this.lastPowerPercent;
    }
}