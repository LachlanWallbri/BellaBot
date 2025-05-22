package com.pudutech.rfidlib;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import com.hoho.android.usbserial.driver.UsbId;
import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;
import com.hoho.android.usbserial.util.SerialInputOutputManager;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import java.io.IOException;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: RFIDSerialInputOutputManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u00017B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010,\u001a\u00020\rJ\u0006\u0010-\u001a\u00020.J\u0006\u0010/\u001a\u00020\u0013J\u0010\u00100\u001a\u00020.2\u0006\u00101\u001a\u000202H\u0016J\u0014\u00103\u001a\u00020.2\n\u00104\u001a\u000605j\u0002`6H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010%\u001a\u0004\u0018\u00010&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020\u0013X\u0082D¢\u0006\u0002\n\u0000¨\u00068"}, m3961d2 = {"Lcom/pudutech/rfidlib/RFIDSerialInputOutputManager;", "Lcom/hoho/android/usbserial/util/SerialInputOutputManager$Listener;", "context", "Landroid/content/Context;", "callback", "Lcom/pudutech/rfidlib/RFIDListener;", "(Landroid/content/Context;Lcom/pudutech/rfidlib/RFIDListener;)V", "INTENT_ACTION_GRANT_USB", "", "TAG", "getTAG", "()Ljava/lang/String;", "baudRate", "", "broadcastReceiver", "Landroid/content/BroadcastReceiver;", "getCallback", "()Lcom/pudutech/rfidlib/RFIDListener;", "connected", "", "getContext", "()Landroid/content/Context;", "mProductId", "mVendorId", "portNum", "getPortNum", "()I", "setPortNum", "(I)V", "usbIoManager", "Lcom/hoho/android/usbserial/util/SerialInputOutputManager;", "getUsbIoManager", "()Lcom/hoho/android/usbserial/util/SerialInputOutputManager;", "setUsbIoManager", "(Lcom/hoho/android/usbserial/util/SerialInputOutputManager;)V", "usbPermission", "Lcom/pudutech/rfidlib/RFIDSerialInputOutputManager$UsbPermission;", "usbSerialPort", "Lcom/hoho/android/usbserial/driver/UsbSerialPort;", "getUsbSerialPort", "()Lcom/hoho/android/usbserial/driver/UsbSerialPort;", "setUsbSerialPort", "(Lcom/hoho/android/usbserial/driver/UsbSerialPort;)V", "withIoManager", MqttServiceConstants.CONNECT_ACTION, MqttServiceConstants.DISCONNECT_ACTION, "", "isConnected", "onNewData", "data", "", "onRunError", C3898x.f4338g, "Ljava/lang/Exception;", "Lkotlin/Exception;", "UsbPermission", "RFIDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RFIDSerialInputOutputManager implements SerialInputOutputManager.Listener {
    private final String INTENT_ACTION_GRANT_USB;
    private final String TAG;
    private final int baudRate;
    private BroadcastReceiver broadcastReceiver;
    private final RFIDListener callback;
    private boolean connected;
    private final Context context;
    private final int mProductId;
    private final int mVendorId;
    private int portNum;
    private SerialInputOutputManager usbIoManager;
    private UsbPermission usbPermission;
    private UsbSerialPort usbSerialPort;
    private final boolean withIoManager;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: RFIDSerialInputOutputManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/rfidlib/RFIDSerialInputOutputManager$UsbPermission;", "", "(Ljava/lang/String;I)V", "Unknown", "Requested", "Granted", "Denied", "RFIDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum UsbPermission {
        Unknown,
        Requested,
        Granted,
        Denied
    }

    public RFIDSerialInputOutputManager(Context context, RFIDListener callback) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.context = context;
        this.callback = callback;
        this.TAG = "RFIDHelper";
        this.INTENT_ACTION_GRANT_USB = "com.pudutech.mir.rfid.ACTION_USB_PERMISSION";
        this.mVendorId = UsbId.VENDOR_FTDI;
        this.mProductId = UsbId.FTDI_FT4232H;
        this.baudRate = 115200;
        this.withIoManager = true;
        this.portNum = 1;
        this.usbPermission = UsbPermission.Unknown;
        this.broadcastReceiver = new BroadcastReceiver() { // from class: com.pudutech.rfidlib.RFIDSerialInputOutputManager.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                Intrinsics.checkParameterIsNotNull(context2, "context");
                Intrinsics.checkParameterIsNotNull(intent, "intent");
                if (Intrinsics.areEqual(RFIDSerialInputOutputManager.this.INTENT_ACTION_GRANT_USB, intent.getAction())) {
                    RFIDSerialInputOutputManager.this.usbPermission = intent.getBooleanExtra("permission", false) ? UsbPermission.Granted : UsbPermission.Denied;
                    RFIDSerialInputOutputManager.this.connect();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(this.INTENT_ACTION_GRANT_USB);
        this.context.getApplicationContext().registerReceiver(this.broadcastReceiver, intentFilter);
    }

    public final Context getContext() {
        return this.context;
    }

    public final RFIDListener getCallback() {
        return this.callback;
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final int getPortNum() {
        return this.portNum;
    }

    public final void setPortNum(int i) {
        this.portNum = i;
    }

    public final SerialInputOutputManager getUsbIoManager() {
        return this.usbIoManager;
    }

    public final void setUsbIoManager(SerialInputOutputManager serialInputOutputManager) {
        this.usbIoManager = serialInputOutputManager;
    }

    public final UsbSerialPort getUsbSerialPort() {
        return this.usbSerialPort;
    }

    public final void setUsbSerialPort(UsbSerialPort usbSerialPort) {
        this.usbSerialPort = usbSerialPort;
    }

    @Override // com.hoho.android.usbserial.util.SerialInputOutputManager.Listener
    public void onNewData(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.callback.onData(data);
    }

    @Override // com.hoho.android.usbserial.util.SerialInputOutputManager.Listener
    public void onRunError(Exception e) {
        Intrinsics.checkParameterIsNotNull(e, "e");
        Pdlog.m3274e(this.TAG, "error: " + e.getMessage());
        disconnect();
    }

    public final boolean isConnected() {
        UsbSerialPort usbSerialPort = this.usbSerialPort;
        if (usbSerialPort == null) {
            return false;
        }
        if (usbSerialPort == null) {
            Intrinsics.throwNpe();
        }
        return usbSerialPort.isOpen();
    }

    public final int connect() {
        if (this.connected) {
            Pdlog.m3274e(this.TAG, "has connected");
            return 2;
        }
        UsbDevice usbDevice = (UsbDevice) null;
        Object systemService = this.context.getSystemService("usb");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.hardware.usb.UsbManager");
        }
        UsbManager usbManager = (UsbManager) systemService;
        Iterator<UsbDevice> it = usbManager.getDeviceList().values().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            UsbDevice v = it.next();
            String str = this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("pid ");
            Intrinsics.checkExpressionValueIsNotNull(v, "v");
            sb.append(v.getProductId());
            sb.append(" , vid ");
            sb.append(v.getVendorId());
            sb.append(" serial number ");
            sb.append(v.getSerialNumber());
            Pdlog.m3273d(str, sb.toString());
            if (v.getVendorId() == this.mVendorId && v.getProductId() == this.mProductId) {
                usbDevice = v;
                break;
            }
        }
        if (usbDevice == null) {
            Pdlog.m3274e(this.TAG, "connection failed: device not found");
            return 3;
        }
        UsbSerialDriver probeDevice = UsbSerialProber.getDefaultProber().probeDevice(usbDevice);
        if (probeDevice == null) {
            Pdlog.m3274e(this.TAG, "connection failed: no driver for device");
            return 4;
        }
        this.usbSerialPort = probeDevice.getPorts().get(this.portNum);
        if (!usbManager.hasPermission(probeDevice.getDevice())) {
            this.usbPermission = UsbPermission.Requested;
            usbManager.requestPermission(probeDevice.getDevice(), PendingIntent.getBroadcast(this.context, 0, new Intent(this.INTENT_ACTION_GRANT_USB), 0));
            return 5;
        }
        UsbDeviceConnection openDevice = usbManager.openDevice(probeDevice.getDevice());
        if (openDevice == null) {
            if (!usbManager.hasPermission(probeDevice.getDevice())) {
                Pdlog.m3274e(this.TAG, "connection failed: permission denied");
                return 6;
            }
            Pdlog.m3274e(this.TAG, "connection failed: open failed");
            return 6;
        }
        try {
            UsbSerialPort usbSerialPort = this.usbSerialPort;
            if (usbSerialPort != null) {
                usbSerialPort.open(openDevice);
            }
            UsbSerialPort usbSerialPort2 = this.usbSerialPort;
            if (usbSerialPort2 != null) {
                usbSerialPort2.setParameters(this.baudRate, 8, 1, 0);
            }
            if (this.withIoManager) {
                this.usbIoManager = new SerialInputOutputManager(this.usbSerialPort, this);
                SerialInputOutputManager serialInputOutputManager = this.usbIoManager;
                if (serialInputOutputManager != null) {
                    serialInputOutputManager.start();
                }
            }
            Pdlog.m3274e(this.TAG, "connected");
            this.connected = true;
            return 1;
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "connection failed: " + e.getMessage());
            disconnect();
            return 7;
        }
    }

    public final void disconnect() {
        this.connected = false;
        SerialInputOutputManager serialInputOutputManager = this.usbIoManager;
        if (serialInputOutputManager != null) {
            if (serialInputOutputManager != null) {
                serialInputOutputManager.setListener((SerialInputOutputManager.Listener) null);
            }
            SerialInputOutputManager serialInputOutputManager2 = this.usbIoManager;
            if (serialInputOutputManager2 != null) {
                serialInputOutputManager2.stop();
            }
        }
        this.usbIoManager = (SerialInputOutputManager) null;
        try {
            UsbSerialPort usbSerialPort = this.usbSerialPort;
            if (usbSerialPort != null) {
                usbSerialPort.close();
            }
        } catch (IOException unused) {
        }
        this.usbSerialPort = (UsbSerialPort) null;
    }
}
