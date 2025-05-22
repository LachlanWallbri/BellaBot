package com.pudutech.factory_test.esp32.port;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.felhr.usbserial.CDCSerialDevice;
import com.felhr.usbserial.UsbSerialDevice;
import com.felhr.usbserial.UsbSerialInterface;
import com.pudutech.base.Pdlog;
import com.pudutech.factory_test.esp32.port.SerialUSBESP;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: SerialUSBESP.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000i\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\u0012\n\u0002\b\u0003*\u0001#\u0018\u0000 42\u00020\u0001:\u000245B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010%\u001a\u00020&H\u0002J\u0006\u0010'\u001a\u00020&J\b\u0010(\u001a\u00020&H\u0002J\n\u0010)\u001a\u0004\u0018\u00010\u001fH\u0002J\b\u0010*\u001a\u00020&H\u0002J\u0016\u0010+\u001a\u00020&2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010,\u001a\u00020&H\u0002J\b\u0010-\u001a\u00020&H\u0002J\u000e\u0010.\u001a\u00020&2\u0006\u0010/\u001a\u00020\u0011J\b\u00100\u001a\u00020&H\u0002J\u000e\u00101\u001a\u00020&2\u0006\u00102\u001a\u000203R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0004\n\u0002\u0010$¨\u00066"}, m3961d2 = {"Lcom/pudutech/factory_test/esp32/port/SerialUSBESP;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "ACTION_USB_ATTACHED", "", "ACTION_USB_DATAERROR_RESET", "ACTION_USB_DETACHED", "ACTION_USB_PERMISSION", "ESP32_PRODUCT_NAME", "TAG", "baudRate", "", "eventStep", "Lcom/pudutech/factory_test/esp32/port/SerialUSBESP$USBEvent;", "isPermissionOK", "", "listener", "Lcom/pudutech/factory_test/esp32/port/SerialUSBListener;", "receiveHandler", "Landroid/os/Handler;", "getReceiveHandler", "()Landroid/os/Handler;", "setReceiveHandler", "(Landroid/os/Handler;)V", "retryAskPermission", "Ljava/lang/Runnable;", "serialPort", "Lcom/felhr/usbserial/UsbSerialDevice;", "usbDevice", "Landroid/hardware/usb/UsbDevice;", "usbManager", "Landroid/hardware/usb/UsbManager;", "usbReceiver", "com/pudutech/factory_test/esp32/port/SerialUSBESP$usbReceiver$1", "Lcom/pudutech/factory_test/esp32/port/SerialUSBESP$usbReceiver$1;", "askPermissionAgain", "", "closeUSBSerial", "eventFSM", "findSerialPortDevice", "logDevicesWhenAttached", "openDevice", "openUSBSerial", "requestUserPermission", "setDTR", "bool", "setFilter", "write", "data", "", "Companion", "USBEvent", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class SerialUSBESP {
    private final String ACTION_USB_ATTACHED;
    private final String ACTION_USB_DATAERROR_RESET;
    private final String ACTION_USB_DETACHED;
    private final String ACTION_USB_PERMISSION;
    private final String ESP32_PRODUCT_NAME;
    private final String TAG;
    private int baudRate;
    private final Context context;
    private USBEvent eventStep;
    private boolean isPermissionOK;
    private SerialUSBListener listener;
    private Handler receiveHandler;
    private final Runnable retryAskPermission;
    private UsbSerialDevice serialPort;
    private UsbDevice usbDevice;
    private final UsbManager usbManager;
    private final SerialUSBESP$usbReceiver$1 usbReceiver;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int MESSAGE_FROM_SERIAL_PORT = 100;
    private static final int CTS_CHANGE = 101;
    private static final int DSR_CHANGE = 102;
    private static final Lazy thread$delegate = LazyKt.lazy(new Function0<HandlerThread>() { // from class: com.pudutech.factory_test.esp32.port.SerialUSBESP$Companion$thread$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final HandlerThread invoke() {
            HandlerThread handlerThread = new HandlerThread("usbThread");
            handlerThread.start();
            return handlerThread;
        }
    });
    private static final Lazy usbHandler$delegate = LazyKt.lazy(new Function0<Handler>() { // from class: com.pudutech.factory_test.esp32.port.SerialUSBESP$Companion$usbHandler$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Handler invoke() {
            HandlerThread thread;
            thread = SerialUSBESP.INSTANCE.getThread();
            return new Handler(thread.getLooper());
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SerialUSBESP.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/factory_test/esp32/port/SerialUSBESP$USBEvent;", "", "(Ljava/lang/String;I)V", "ATTACHED", "PERMISSION_OK", "DETACHED", "ERROR", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum USBEvent {
        ATTACHED,
        PERMISSION_OK,
        DETACHED,
        ERROR
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[USBEvent.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[USBEvent.ATTACHED.ordinal()] = 1;
            $EnumSwitchMapping$0[USBEvent.PERMISSION_OK.ordinal()] = 2;
            $EnumSwitchMapping$0[USBEvent.DETACHED.ordinal()] = 3;
            $EnumSwitchMapping$0[USBEvent.ERROR.ordinal()] = 4;
        }
    }

    /* compiled from: SerialUSBESP.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/factory_test/esp32/port/SerialUSBESP$Companion;", "", "()V", "CTS_CHANGE", "", "getCTS_CHANGE", "()I", "DSR_CHANGE", "getDSR_CHANGE", "MESSAGE_FROM_SERIAL_PORT", "getMESSAGE_FROM_SERIAL_PORT", "thread", "Landroid/os/HandlerThread;", "getThread", "()Landroid/os/HandlerThread;", "thread$delegate", "Lkotlin/Lazy;", "usbHandler", "Landroid/os/Handler;", "getUsbHandler", "()Landroid/os/Handler;", "usbHandler$delegate", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Companion {
        /* JADX INFO: Access modifiers changed from: private */
        public final HandlerThread getThread() {
            Lazy lazy = SerialUSBESP.thread$delegate;
            Companion companion = SerialUSBESP.INSTANCE;
            return (HandlerThread) lazy.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Handler getUsbHandler() {
            Lazy lazy = SerialUSBESP.usbHandler$delegate;
            Companion companion = SerialUSBESP.INSTANCE;
            return (Handler) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getMESSAGE_FROM_SERIAL_PORT() {
            return SerialUSBESP.MESSAGE_FROM_SERIAL_PORT;
        }

        public final int getCTS_CHANGE() {
            return SerialUSBESP.CTS_CHANGE;
        }

        public final int getDSR_CHANGE() {
            return SerialUSBESP.DSR_CHANGE;
        }
    }

    public SerialUSBESP(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "SerialUSBESP";
        this.ACTION_USB_ATTACHED = "android.hardware.usb.action.USB_DEVICE_ATTACHED";
        this.ACTION_USB_DETACHED = "android.hardware.usb.action.USB_DEVICE_DETACHED";
        this.ACTION_USB_DATAERROR_RESET = "android.hardware.usb.action.ACTION_USB_DATAERROR_RESET";
        this.ACTION_USB_PERMISSION = "com.pudutech.esp.ACTION_USB_PERMISSION";
        this.ESP32_PRODUCT_NAME = "CP2102N USB to UART Bridge Controller";
        this.eventStep = USBEvent.ATTACHED;
        this.retryAskPermission = new Runnable() { // from class: com.pudutech.factory_test.esp32.port.SerialUSBESP$retryAskPermission$1
            @Override // java.lang.Runnable
            public final void run() {
                SerialUSBESP.this.askPermissionAgain();
            }
        };
        this.baudRate = 576000;
        this.usbReceiver = new SerialUSBESP$usbReceiver$1(this);
        this.context = context;
        Object systemService = context.getSystemService("usb");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.hardware.usb.UsbManager");
        }
        this.usbManager = (UsbManager) systemService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void askPermissionAgain() {
        Pdlog.m3275i(this.TAG, "retryAskPermission. eventStep=" + this.eventStep + " isPermissionOK=" + this.isPermissionOK);
        INSTANCE.getUsbHandler().post(new Runnable() { // from class: com.pudutech.factory_test.esp32.port.SerialUSBESP$askPermissionAgain$1
            @Override // java.lang.Runnable
            public final void run() {
                boolean z;
                UsbDevice usbDevice;
                Runnable runnable;
                Runnable runnable2;
                z = SerialUSBESP.this.isPermissionOK;
                if (z) {
                    return;
                }
                usbDevice = SerialUSBESP.this.usbDevice;
                if (usbDevice != null) {
                    Handler usbHandler = SerialUSBESP.INSTANCE.getUsbHandler();
                    runnable = SerialUSBESP.this.retryAskPermission;
                    usbHandler.removeCallbacks(runnable);
                    SerialUSBESP.this.requestUserPermission();
                    Handler usbHandler2 = SerialUSBESP.INSTANCE.getUsbHandler();
                    runnable2 = SerialUSBESP.this.retryAskPermission;
                    usbHandler2.postDelayed(runnable2, 2000L);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void eventFSM() {
        SerialUSBListener serialUSBListener;
        Pdlog.m3275i(this.TAG, "eventFSM " + this.eventStep);
        int i = WhenMappings.$EnumSwitchMapping$0[this.eventStep.ordinal()];
        if (i == 1) {
            if (this.usbDevice == null) {
                UsbDevice findSerialPortDevice = findSerialPortDevice();
                this.usbDevice = findSerialPortDevice;
                if (findSerialPortDevice != null) {
                    Pdlog.m3275i(this.TAG, "usbDevice attached. ");
                    if (this.isPermissionOK) {
                        return;
                    }
                    INSTANCE.getUsbHandler().removeCallbacks(this.retryAskPermission);
                    requestUserPermission();
                    INSTANCE.getUsbHandler().postDelayed(this.retryAskPermission, 2000L);
                    return;
                }
                return;
            }
            Pdlog.m3275i(this.TAG, "usbDevice already attached");
            return;
        }
        if (i == 2) {
            if (this.isPermissionOK) {
                return;
            }
            this.isPermissionOK = true;
            INSTANCE.getUsbHandler().removeCallbacks(this.retryAskPermission);
            openUSBSerial();
            return;
        }
        if (i != 3) {
            if (i != 4) {
                return;
            }
            UsbSerialDevice usbSerialDevice = this.serialPort;
            if (usbSerialDevice != null) {
                usbSerialDevice.close();
            }
            this.serialPort = (UsbSerialDevice) null;
            if (this.usbDevice != null && (serialUSBListener = this.listener) != null) {
                serialUSBListener.onDeviceDisConnect();
            }
            UsbDevice findSerialPortDevice2 = findSerialPortDevice();
            this.usbDevice = findSerialPortDevice2;
            if (findSerialPortDevice2 != null) {
                openUSBSerial();
                return;
            }
            return;
        }
        if (this.usbDevice != null) {
            UsbDevice findSerialPortDevice3 = findSerialPortDevice();
            this.usbDevice = findSerialPortDevice3;
            if (findSerialPortDevice3 != null) {
                return;
            }
            Pdlog.m3275i(this.TAG, "usbDevice detached");
            this.isPermissionOK = false;
            INSTANCE.getUsbHandler().removeCallbacks(this.retryAskPermission);
            SerialUSBListener serialUSBListener2 = this.listener;
            if (serialUSBListener2 != null) {
                serialUSBListener2.onDeviceDisConnect();
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    public final Handler getReceiveHandler() {
        return this.receiveHandler;
    }

    public final void setReceiveHandler(Handler handler) {
        this.receiveHandler = handler;
    }

    public final void openDevice(int baudRate, SerialUSBListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Pdlog.m3275i(this.TAG, "openDevice " + baudRate + ' ' + listener);
        this.baudRate = baudRate;
        this.listener = listener;
        setFilter();
        INSTANCE.getUsbHandler().post(new Runnable() { // from class: com.pudutech.factory_test.esp32.port.SerialUSBESP$openDevice$1
            @Override // java.lang.Runnable
            public final void run() {
                SerialUSBESP.this.eventStep = SerialUSBESP.USBEvent.ATTACHED;
                SerialUSBESP.this.eventFSM();
            }
        });
    }

    private final void setFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(this.ACTION_USB_PERMISSION);
        intentFilter.addAction(this.ACTION_USB_DETACHED);
        intentFilter.addAction(this.ACTION_USB_ATTACHED);
        intentFilter.addAction(this.ACTION_USB_DATAERROR_RESET);
        this.context.getApplicationContext().registerReceiver(this.usbReceiver, intentFilter);
    }

    private final UsbDevice findSerialPortDevice() {
        Pdlog.m3275i(this.TAG, "findSerialPortDevice");
        SerialUSBESP$findSerialPortDevice$format$1 serialUSBESP$findSerialPortDevice$format$1 = new Function1<Integer, String>() { // from class: com.pudutech.factory_test.esp32.port.SerialUSBESP$findSerialPortDevice$format$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ String invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final String invoke(int i) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("0x%04X", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                return format;
            }
        };
        UsbDevice usbDevice = (UsbDevice) null;
        HashMap<String, UsbDevice> deviceList = this.usbManager.getDeviceList();
        Intrinsics.checkExpressionValueIsNotNull(deviceList, "usbManager.deviceList");
        Iterator<Map.Entry<String, UsbDevice>> it = deviceList.entrySet().iterator();
        while (it.hasNext()) {
            UsbDevice dev = it.next().getValue();
            String str = this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("device vid:");
            Intrinsics.checkExpressionValueIsNotNull(dev, "dev");
            sb.append(serialUSBESP$findSerialPortDevice$format$1.invoke((SerialUSBESP$findSerialPortDevice$format$1) Integer.valueOf(dev.getVendorId())));
            sb.append(" pid:");
            sb.append(serialUSBESP$findSerialPortDevice$format$1.invoke((SerialUSBESP$findSerialPortDevice$format$1) Integer.valueOf(dev.getProductId())));
            sb.append(" name:");
            sb.append(dev.getDeviceName());
            sb.append(' ');
            sb.append(dev.getProductName());
            Pdlog.m3275i(str, sb.toString());
            if (dev.getVendorId() == 4292 && dev.getProductId() == 60000 && Intrinsics.areEqual(dev.getProductName(), this.ESP32_PRODUCT_NAME)) {
                usbDevice = dev;
            }
        }
        if (usbDevice != null) {
            Pdlog.m3275i(this.TAG, "find device: " + serialUSBESP$findSerialPortDevice$format$1.invoke((SerialUSBESP$findSerialPortDevice$format$1) Integer.valueOf(usbDevice.getVendorId())) + ' ' + serialUSBESP$findSerialPortDevice$format$1.invoke((SerialUSBESP$findSerialPortDevice$format$1) Integer.valueOf(usbDevice.getProductId())));
        }
        return usbDevice;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void logDevicesWhenAttached() {
        Pdlog.m3273d(this.TAG, "lodDeviceWhenAttached ");
        SerialUSBESP$logDevicesWhenAttached$format$1 serialUSBESP$logDevicesWhenAttached$format$1 = new Function1<Integer, String>() { // from class: com.pudutech.factory_test.esp32.port.SerialUSBESP$logDevicesWhenAttached$format$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ String invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final String invoke(int i) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("0x%04X", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                return format;
            }
        };
        HashMap<String, UsbDevice> deviceList = this.usbManager.getDeviceList();
        Intrinsics.checkExpressionValueIsNotNull(deviceList, "usbManager.deviceList");
        Iterator<Map.Entry<String, UsbDevice>> it = deviceList.entrySet().iterator();
        while (it.hasNext()) {
            UsbDevice dev = it.next().getValue();
            String str = this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("device vid:");
            Intrinsics.checkExpressionValueIsNotNull(dev, "dev");
            sb.append(serialUSBESP$logDevicesWhenAttached$format$1.invoke((SerialUSBESP$logDevicesWhenAttached$format$1) Integer.valueOf(dev.getVendorId())));
            sb.append(" pid:");
            sb.append(serialUSBESP$logDevicesWhenAttached$format$1.invoke((SerialUSBESP$logDevicesWhenAttached$format$1) Integer.valueOf(dev.getProductId())));
            sb.append(" name:");
            sb.append(dev.getDeviceName());
            sb.append(' ');
            sb.append(dev.getProductName());
            Pdlog.m3275i(str, sb.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestUserPermission() {
        Pdlog.m3275i(this.TAG, "requestUserPermission");
        this.usbManager.requestPermission(this.usbDevice, PendingIntent.getBroadcast(this.context, 0, new Intent(this.ACTION_USB_PERMISSION), 0));
    }

    private final void openUSBSerial() {
        Pdlog.m3275i(this.TAG, "openUSBSerial");
        try {
            UsbSerialDevice createUsbSerialDevice = UsbSerialDevice.createUsbSerialDevice(this.usbDevice, this.usbManager.openDevice(this.usbDevice));
            this.serialPort = createUsbSerialDevice;
            if (createUsbSerialDevice != null) {
                if (createUsbSerialDevice.open()) {
                    createUsbSerialDevice.setBaudRate(this.baudRate);
                    createUsbSerialDevice.setDataBits(8);
                    createUsbSerialDevice.setStopBits(1);
                    createUsbSerialDevice.setParity(0);
                    createUsbSerialDevice.setFlowControl(0);
                    createUsbSerialDevice.read(new UsbSerialInterface.UsbReadCallback() { // from class: com.pudutech.factory_test.esp32.port.SerialUSBESP$openUSBSerial$$inlined$let$lambda$1
                        @Override // com.felhr.usbserial.UsbSerialInterface.UsbReadCallback
                        public final void onReceivedData(byte[] bArr) {
                            String str;
                            Message obtainMessage;
                            str = SerialUSBESP.this.TAG;
                            Object[] objArr = new Object[1];
                            StringBuilder sb = new StringBuilder();
                            sb.append("read ");
                            sb.append(bArr != null ? Integer.valueOf(bArr.length) : null);
                            objArr[0] = sb.toString();
                            Pdlog.m3276v(str, objArr);
                            Handler receiveHandler = SerialUSBESP.this.getReceiveHandler();
                            if (receiveHandler == null || (obtainMessage = receiveHandler.obtainMessage(SerialUSBESP.INSTANCE.getMESSAGE_FROM_SERIAL_PORT(), bArr)) == null) {
                                return;
                            }
                            obtainMessage.sendToTarget();
                        }
                    });
                    createUsbSerialDevice.getCTS(new UsbSerialInterface.UsbCTSCallback() { // from class: com.pudutech.factory_test.esp32.port.SerialUSBESP$openUSBSerial$$inlined$let$lambda$2
                        @Override // com.felhr.usbserial.UsbSerialInterface.UsbCTSCallback
                        public final void onCTSChanged(boolean z) {
                            Message obtainMessage;
                            Handler receiveHandler = SerialUSBESP.this.getReceiveHandler();
                            if (receiveHandler == null || (obtainMessage = receiveHandler.obtainMessage(SerialUSBESP.INSTANCE.getCTS_CHANGE(), Boolean.valueOf(z))) == null) {
                                return;
                            }
                            obtainMessage.sendToTarget();
                        }
                    });
                    createUsbSerialDevice.getDSR(new UsbSerialInterface.UsbDSRCallback() { // from class: com.pudutech.factory_test.esp32.port.SerialUSBESP$openUSBSerial$$inlined$let$lambda$3
                        @Override // com.felhr.usbserial.UsbSerialInterface.UsbDSRCallback
                        public final void onDSRChanged(boolean z) {
                            Message obtainMessage;
                            Handler receiveHandler = SerialUSBESP.this.getReceiveHandler();
                            if (receiveHandler == null || (obtainMessage = receiveHandler.obtainMessage(SerialUSBESP.INSTANCE.getDSR_CHANGE(), Boolean.valueOf(z))) == null) {
                                return;
                            }
                            obtainMessage.sendToTarget();
                        }
                    });
                    SerialUSBListener serialUSBListener = this.listener;
                    if (serialUSBListener != null) {
                        serialUSBListener.onDeviceOpen();
                        return;
                    }
                    return;
                }
                Pdlog.m3274e(this.TAG, "serialPort open fail");
                if (this.serialPort instanceof CDCSerialDevice) {
                    Pdlog.m3274e(this.TAG, "CDC serial dev not work");
                    return;
                } else {
                    Pdlog.m3274e(this.TAG, "USB serial dev not work");
                    return;
                }
            }
            Pdlog.m3274e(this.TAG, "createUsbSerialDevice fail. usb not supported");
        } catch (Exception e) {
            Pdlog.m3277w(this.TAG, "openUSBSerial " + e);
        }
    }

    public final void closeUSBSerial() {
        Pdlog.m3273d(this.TAG, "closeUSBSerial ");
        this.context.getApplicationContext().unregisterReceiver(this.usbReceiver);
        INSTANCE.getUsbHandler().removeCallbacksAndMessages(null);
        this.usbDevice = (UsbDevice) null;
        UsbSerialDevice usbSerialDevice = this.serialPort;
        if (usbSerialDevice != null) {
            usbSerialDevice.close();
        }
    }

    public final void write(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        UsbSerialDevice usbSerialDevice = this.serialPort;
        if (usbSerialDevice != null) {
            usbSerialDevice.write(data);
        }
    }

    public final void setDTR(boolean bool) {
        UsbSerialDevice usbSerialDevice = this.serialPort;
        if (usbSerialDevice != null) {
            usbSerialDevice.setDTR(bool);
        }
    }
}
