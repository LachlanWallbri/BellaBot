package com.pudutech.lidar.port;

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
import com.felhr.utils.HexData;
import com.hoho.android.usbserial.driver.UsbId;
import com.iflytek.cloud.SpeechConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.port.SerialUSB;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: SerialUSB.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000{\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010\u0012\n\u0002\b\u0004*\u0001(\u0018\u0000 82\u00020\u0001:\u000389:B-\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u001c\b\u0002\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007¢\u0006\u0002\u0010\bJ\b\u0010*\u001a\u00020+H\u0002J\b\u0010,\u001a\u00020+H\u0002J\n\u0010-\u001a\u0004\u0018\u00010#H\u0002J\b\u0010.\u001a\u00020+H\u0002J\u0016\u0010/\u001a\u00020+2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016J\b\u00100\u001a\u00020+H\u0002J\b\u00101\u001a\u00020+H\u0002J\u000e\u00102\u001a\u00020+2\u0006\u00103\u001a\u00020\u0014J\b\u00104\u001a\u00020+H\u0002J\u000e\u00105\u001a\u00020+2\u0006\u00106\u001a\u000207R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0004\n\u0002\u0010)¨\u0006;"}, m3961d2 = {"Lcom/pudutech/lidar/port/SerialUSB;", "", "context", "Landroid/content/Context;", "list", "Ljava/util/ArrayList;", "Lcom/pudutech/lidar/port/SerialUSB$SerialModel;", "Lkotlin/collections/ArrayList;", "(Landroid/content/Context;Ljava/util/ArrayList;)V", "ACTION_USB_ATTACHED", "", "ACTION_USB_DATAERROR_RESET", "ACTION_USB_DETACHED", "ACTION_USB_PERMISSION", "TAG", "baudRate", "", "eventStep", "Lcom/pudutech/lidar/port/SerialUSB$USBEvent;", "isPermissionOK", "", "listener", "Lcom/pudutech/lidar/port/SerialUSBListener;", "receiveHandler", "Landroid/os/Handler;", "getReceiveHandler", "()Landroid/os/Handler;", "setReceiveHandler", "(Landroid/os/Handler;)V", "retryAskPermission", "Ljava/lang/Runnable;", "serialPort", "Lcom/felhr/usbserial/UsbSerialDevice;", "supportList", "usbDevice", "Landroid/hardware/usb/UsbDevice;", "usbHandler", "usbManager", "Landroid/hardware/usb/UsbManager;", "usbReceiver", "com/pudutech/lidar/port/SerialUSB$usbReceiver$1", "Lcom/pudutech/lidar/port/SerialUSB$usbReceiver$1;", "askPermissionAgain", "", "eventFSM", "findSerialPortDevice", "logDevicesWhenAttached", "openDevice", "openUSBSerial", "requestUserPermission", "setDTR", "bool", "setFilter", "write", "data", "", "Companion", "SerialModel", "USBEvent", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class SerialUSB {
    private final String ACTION_USB_ATTACHED;
    private final String ACTION_USB_DATAERROR_RESET;
    private final String ACTION_USB_DETACHED;
    private final String ACTION_USB_PERMISSION;
    private final String TAG;
    private int baudRate;
    private final Context context;
    private USBEvent eventStep;
    private boolean isPermissionOK;
    private SerialUSBListener listener;
    private Handler receiveHandler;
    private final Runnable retryAskPermission;
    private UsbSerialDevice serialPort;
    private ArrayList<SerialModel> supportList;
    private UsbDevice usbDevice;
    private Handler usbHandler;
    private final UsbManager usbManager;
    private final SerialUSB$usbReceiver$1 usbReceiver;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int MESSAGE_FROM_SERIAL_PORT = 100;
    private static final int CTS_CHANGE = 101;
    private static final int DSR_CHANGE = 102;
    private static final SerialModel CP2102 = new SerialModel(UsbId.VENDOR_SILABS, 60000, "CP2102 USB to UART Bridge Controller");
    private static final SerialModel CP2102_A = new SerialModel(UsbId.VENDOR_SILABS, 60000, "CP2102 USB to UART Bridge Controller_A");
    private static final SerialModel CH340 = new SerialModel(UsbId.VENDOR_QINHENG, UsbId.QINHENG_CH340, "USB Serial");

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: SerialUSB.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/lidar/port/SerialUSB$USBEvent;", "", "(Ljava/lang/String;I)V", "ATTACHED", "PERMISSION_OK", "DETACHED", "ERROR", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum USBEvent {
        ATTACHED,
        PERMISSION_OK,
        DETACHED,
        ERROR
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
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

    public static final /* synthetic */ Handler access$getUsbHandler$p(SerialUSB serialUSB) {
        Handler handler = serialUSB.usbHandler;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usbHandler");
        }
        return handler;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: SerialUSB.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/lidar/port/SerialUSB$SerialModel;", "", SpeechConstant.ISV_VID, "", "pid", "name", "", "(IILjava/lang/String;)V", "getName", "()Ljava/lang/String;", "getPid", "()I", "getVid", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final /* data */ class SerialModel {
        private final String name;
        private final int pid;
        private final int vid;

        public static /* synthetic */ SerialModel copy$default(SerialModel serialModel, int i, int i2, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = serialModel.vid;
            }
            if ((i3 & 2) != 0) {
                i2 = serialModel.pid;
            }
            if ((i3 & 4) != 0) {
                str = serialModel.name;
            }
            return serialModel.copy(i, i2, str);
        }

        /* renamed from: component1, reason: from getter */
        public final int getVid() {
            return this.vid;
        }

        /* renamed from: component2, reason: from getter */
        public final int getPid() {
            return this.pid;
        }

        /* renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final SerialModel copy(int vid, int pid, String name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            return new SerialModel(vid, pid, name);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SerialModel)) {
                return false;
            }
            SerialModel serialModel = (SerialModel) other;
            return this.vid == serialModel.vid && this.pid == serialModel.pid && Intrinsics.areEqual(this.name, serialModel.name);
        }

        public int hashCode() {
            int i = ((this.vid * 31) + this.pid) * 31;
            String str = this.name;
            return i + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "SerialModel(vid=" + this.vid + ", pid=" + this.pid + ", name=" + this.name + ")";
        }

        public SerialModel(int i, int i2, String name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            this.vid = i;
            this.pid = i2;
            this.name = name;
        }

        public final String getName() {
            return this.name;
        }

        public final int getPid() {
            return this.pid;
        }

        public final int getVid() {
            return this.vid;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: SerialUSB.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\fX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\fX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\fX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000e¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/lidar/port/SerialUSB$Companion;", "", "()V", "CH340", "Lcom/pudutech/lidar/port/SerialUSB$SerialModel;", "getCH340", "()Lcom/pudutech/lidar/port/SerialUSB$SerialModel;", "CP2102", "getCP2102", "CP2102_A", "getCP2102_A", "CTS_CHANGE", "", "getCTS_CHANGE", "()I", "DSR_CHANGE", "getDSR_CHANGE", "MESSAGE_FROM_SERIAL_PORT", "getMESSAGE_FROM_SERIAL_PORT", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getMESSAGE_FROM_SERIAL_PORT() {
            return SerialUSB.MESSAGE_FROM_SERIAL_PORT;
        }

        public final int getCTS_CHANGE() {
            return SerialUSB.CTS_CHANGE;
        }

        public final int getDSR_CHANGE() {
            return SerialUSB.DSR_CHANGE;
        }

        public final SerialModel getCP2102() {
            return SerialUSB.CP2102;
        }

        public final SerialModel getCP2102_A() {
            return SerialUSB.CP2102_A;
        }

        public final SerialModel getCH340() {
            return SerialUSB.CH340;
        }
    }

    public /* synthetic */ SerialUSB(Context context, ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? (ArrayList) null : arrayList);
    }

    public SerialUSB(Context context, ArrayList<SerialModel> arrayList) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "SerialUSB_Lidar";
        this.ACTION_USB_ATTACHED = "android.hardware.usb.action.USB_DEVICE_ATTACHED";
        this.ACTION_USB_DETACHED = "android.hardware.usb.action.USB_DEVICE_DETACHED";
        this.ACTION_USB_DATAERROR_RESET = "android.hardware.usb.action.ACTION_USB_DATAERROR_RESET";
        this.ACTION_USB_PERMISSION = "com.pudutech.lidar.ACTION_USB_PERMISSION";
        this.supportList = CollectionsKt.arrayListOf(CP2102, CH340, CP2102_A);
        this.eventStep = USBEvent.ATTACHED;
        this.retryAskPermission = new Runnable() { // from class: com.pudutech.lidar.port.SerialUSB$retryAskPermission$1
            @Override // java.lang.Runnable
            public final void run() {
                SerialUSB.this.askPermissionAgain();
            }
        };
        this.baudRate = 115200;
        this.usbReceiver = new SerialUSB$usbReceiver$1(this);
        Pdlog.m3275i(this.TAG, " context=" + context + "  serial list=" + arrayList);
        if (arrayList != null) {
            this.supportList = arrayList;
        }
        this.context = context;
        HandlerThread handlerThread = new HandlerThread("usbThread");
        handlerThread.start();
        this.usbHandler = new Handler(handlerThread.getLooper());
        Object systemService = context.getSystemService("usb");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.hardware.usb.UsbManager");
        }
        this.usbManager = (UsbManager) systemService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void askPermissionAgain() {
        Pdlog.m3275i(this.TAG, "retryAskPermission. eventStep=" + this.eventStep + " isPermissionOK=" + this.isPermissionOK);
        Handler handler = this.usbHandler;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usbHandler");
        }
        handler.post(new Runnable() { // from class: com.pudutech.lidar.port.SerialUSB$askPermissionAgain$1
            @Override // java.lang.Runnable
            public final void run() {
                boolean z;
                UsbDevice usbDevice;
                Runnable runnable;
                Runnable runnable2;
                z = SerialUSB.this.isPermissionOK;
                if (z) {
                    return;
                }
                usbDevice = SerialUSB.this.usbDevice;
                if (usbDevice != null) {
                    Handler access$getUsbHandler$p = SerialUSB.access$getUsbHandler$p(SerialUSB.this);
                    runnable = SerialUSB.this.retryAskPermission;
                    access$getUsbHandler$p.removeCallbacks(runnable);
                    SerialUSB.this.requestUserPermission();
                    Handler access$getUsbHandler$p2 = SerialUSB.access$getUsbHandler$p(SerialUSB.this);
                    runnable2 = SerialUSB.this.retryAskPermission;
                    access$getUsbHandler$p2.postDelayed(runnable2, 2000L);
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
                    if (!this.isPermissionOK) {
                        Handler handler = this.usbHandler;
                        if (handler == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("usbHandler");
                        }
                        handler.removeCallbacks(this.retryAskPermission);
                        requestUserPermission();
                        Handler handler2 = this.usbHandler;
                        if (handler2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("usbHandler");
                        }
                        handler2.postDelayed(this.retryAskPermission, 2000L);
                    }
                }
                if (this.usbDevice != null) {
                    return;
                }
                Pdlog.m3277w(this.TAG, "couldn't find device");
                SerialUSBListener serialUSBListener2 = this.listener;
                if (serialUSBListener2 != null) {
                    serialUSBListener2.onDeviceOpen(false, "couldn't find device");
                    Unit unit = Unit.INSTANCE;
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
            Handler handler3 = this.usbHandler;
            if (handler3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("usbHandler");
            }
            handler3.removeCallbacks(this.retryAskPermission);
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
            Handler handler4 = this.usbHandler;
            if (handler4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("usbHandler");
            }
            handler4.removeCallbacks(this.retryAskPermission);
            SerialUSBListener serialUSBListener3 = this.listener;
            if (serialUSBListener3 != null) {
                serialUSBListener3.onDeviceDisConnect();
                Unit unit2 = Unit.INSTANCE;
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
        Handler handler = this.usbHandler;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usbHandler");
        }
        handler.post(new Runnable() { // from class: com.pudutech.lidar.port.SerialUSB$openDevice$1
            @Override // java.lang.Runnable
            public final void run() {
                SerialUSB.this.eventStep = SerialUSB.USBEvent.ATTACHED;
                SerialUSB.this.eventFSM();
            }
        });
    }

    private final void setFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(this.ACTION_USB_PERMISSION);
        intentFilter.addAction(this.ACTION_USB_DETACHED);
        intentFilter.addAction(this.ACTION_USB_ATTACHED);
        intentFilter.addAction(this.ACTION_USB_DATAERROR_RESET);
        this.context.registerReceiver(this.usbReceiver, intentFilter);
    }

    private final UsbDevice findSerialPortDevice() {
        Pdlog.m3275i(this.TAG, "findSerialPortDevice");
        SerialUSB$findSerialPortDevice$format$1 serialUSB$findSerialPortDevice$format$1 = new Function1<Integer, String>() { // from class: com.pudutech.lidar.port.SerialUSB$findSerialPortDevice$format$1
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
            sb.append(serialUSB$findSerialPortDevice$format$1.invoke((SerialUSB$findSerialPortDevice$format$1) Integer.valueOf(dev.getVendorId())));
            sb.append(" pid:");
            sb.append(serialUSB$findSerialPortDevice$format$1.invoke((SerialUSB$findSerialPortDevice$format$1) Integer.valueOf(dev.getProductId())));
            sb.append(" name:");
            sb.append(dev.getDeviceName());
            sb.append(' ');
            sb.append(dev.getProductName());
            Pdlog.m3275i(str, sb.toString());
            for (SerialModel serialModel : this.supportList) {
                if (dev.getVendorId() == serialModel.getVid() && dev.getProductId() == serialModel.getPid() && Intrinsics.areEqual(dev.getProductName(), serialModel.getName())) {
                    usbDevice = dev;
                }
            }
        }
        if (usbDevice != null) {
            Pdlog.m3275i(this.TAG, "find lidar device: " + serialUSB$findSerialPortDevice$format$1.invoke((SerialUSB$findSerialPortDevice$format$1) Integer.valueOf(usbDevice.getVendorId())) + ' ' + serialUSB$findSerialPortDevice$format$1.invoke((SerialUSB$findSerialPortDevice$format$1) Integer.valueOf(usbDevice.getProductId())));
        }
        return usbDevice;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void logDevicesWhenAttached() {
        Pdlog.m3273d(this.TAG, "lodDeviceWhenAttached ");
        SerialUSB$logDevicesWhenAttached$format$1 serialUSB$logDevicesWhenAttached$format$1 = new Function1<Integer, String>() { // from class: com.pudutech.lidar.port.SerialUSB$logDevicesWhenAttached$format$1
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
            sb.append(serialUSB$logDevicesWhenAttached$format$1.invoke((SerialUSB$logDevicesWhenAttached$format$1) Integer.valueOf(dev.getVendorId())));
            sb.append(" pid:");
            sb.append(serialUSB$logDevicesWhenAttached$format$1.invoke((SerialUSB$logDevicesWhenAttached$format$1) Integer.valueOf(dev.getProductId())));
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
                    createUsbSerialDevice.read(new UsbSerialInterface.UsbReadCallback() { // from class: com.pudutech.lidar.port.SerialUSB$openUSBSerial$$inlined$let$lambda$1
                        @Override // com.felhr.usbserial.UsbSerialInterface.UsbReadCallback
                        public final void onReceivedData(byte[] bArr) {
                            String str;
                            Message obtainMessage;
                            str = SerialUSB.this.TAG;
                            Object[] objArr = new Object[1];
                            StringBuilder sb = new StringBuilder();
                            sb.append("read ");
                            sb.append(bArr != null ? Integer.valueOf(bArr.length) : null);
                            objArr[0] = sb.toString();
                            Pdlog.m3276v(str, objArr);
                            Handler receiveHandler = SerialUSB.this.getReceiveHandler();
                            if (receiveHandler == null || (obtainMessage = receiveHandler.obtainMessage(SerialUSB.INSTANCE.getMESSAGE_FROM_SERIAL_PORT(), bArr)) == null) {
                                return;
                            }
                            obtainMessage.sendToTarget();
                        }
                    });
                    createUsbSerialDevice.getCTS(new UsbSerialInterface.UsbCTSCallback() { // from class: com.pudutech.lidar.port.SerialUSB$openUSBSerial$$inlined$let$lambda$2
                        @Override // com.felhr.usbserial.UsbSerialInterface.UsbCTSCallback
                        public final void onCTSChanged(boolean z) {
                            Message obtainMessage;
                            Handler receiveHandler = SerialUSB.this.getReceiveHandler();
                            if (receiveHandler == null || (obtainMessage = receiveHandler.obtainMessage(SerialUSB.INSTANCE.getCTS_CHANGE(), Boolean.valueOf(z))) == null) {
                                return;
                            }
                            obtainMessage.sendToTarget();
                        }
                    });
                    createUsbSerialDevice.getDSR(new UsbSerialInterface.UsbDSRCallback() { // from class: com.pudutech.lidar.port.SerialUSB$openUSBSerial$$inlined$let$lambda$3
                        @Override // com.felhr.usbserial.UsbSerialInterface.UsbDSRCallback
                        public final void onDSRChanged(boolean z) {
                            Message obtainMessage;
                            Handler receiveHandler = SerialUSB.this.getReceiveHandler();
                            if (receiveHandler == null || (obtainMessage = receiveHandler.obtainMessage(SerialUSB.INSTANCE.getDSR_CHANGE(), Boolean.valueOf(z))) == null) {
                                return;
                            }
                            obtainMessage.sendToTarget();
                        }
                    });
                    SerialUSBListener serialUSBListener = this.listener;
                    if (serialUSBListener != null) {
                        serialUSBListener.onDeviceOpen(true, null);
                        return;
                    }
                    return;
                }
                Pdlog.m3274e(this.TAG, "serialPort open fail");
                if (this.serialPort instanceof CDCSerialDevice) {
                    Pdlog.m3274e(this.TAG, "CDC serial dev not work");
                    SerialUSBListener serialUSBListener2 = this.listener;
                    if (serialUSBListener2 != null) {
                        serialUSBListener2.onDeviceOpen(false, "CDC serial dev not work");
                        return;
                    }
                    return;
                }
                Pdlog.m3274e(this.TAG, "USB serial dev not work");
                SerialUSBListener serialUSBListener3 = this.listener;
                if (serialUSBListener3 != null) {
                    serialUSBListener3.onDeviceOpen(false, "USB serial dev not work");
                    return;
                }
                return;
            }
            Pdlog.m3274e(this.TAG, "createUsbSerialDevice fail. usb not supported");
            SerialUSBListener serialUSBListener4 = this.listener;
            if (serialUSBListener4 != null) {
                serialUSBListener4.onDeviceOpen(false, "createUsbSerialDevice fail. usb not supported");
            }
        } catch (Exception e) {
            Pdlog.m3277w(this.TAG, "openUSBSerial " + e);
        }
    }

    public final void write(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        Pdlog.m3276v(this.TAG, "write " + HexData.hexToString(data) + " to serialPort=" + this.serialPort);
        UsbSerialDevice usbSerialDevice = this.serialPort;
        if (usbSerialDevice != null) {
            usbSerialDevice.write(data);
        }
    }

    public final void setDTR(boolean bool) {
        Pdlog.m3276v(this.TAG, "set DTR " + bool);
        UsbSerialDevice usbSerialDevice = this.serialPort;
        if (usbSerialDevice != null) {
            usbSerialDevice.setDTR(bool);
        }
    }
}
