package com.pudutech.serialport.library;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* compiled from: SerialPortHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u0000 K2\u00020\u0001:\u0002KLB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010?\u001a\u00020@J\u0006\u0010A\u001a\u00020@JR\u0010B\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010,\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\b\u00109\u001a\u0004\u0018\u00010:2\b\u00103\u001a\u0004\u0018\u000104J\\\u0010B\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010,\u001a\u0004\u0018\u00010\u000f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\b\u00109\u001a\u0004\u0018\u00010:2\b\u00103\u001a\u0004\u0018\u000104J\u0006\u0010C\u001a\u00020!J\u0006\u0010D\u001a\u00020!J\u0012\u0010E\u001a\u00020@2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u0006\u0010F\u001a\u00020@J\u0012\u0010G\u001a\u00020@2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u0010\u0010H\u001a\u00020@2\b\u0010I\u001a\u0004\u0018\u00010JR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u0006\"\u0004\b\r\u0010\bR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0011\"\u0004\b\u001f\u0010\u0013R\u001a\u0010 \u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001c\u0010&\u001a\u0004\u0018\u00010'X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001c\u0010,\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u0011\"\u0004\b.\u0010\u0013R\u000e\u0010/\u001a\u000200X\u0082.¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u00103\u001a\u0004\u0018\u000104X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u0010\u00109\u001a\u0004\u0018\u00010:X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010;\u001a\b\u0018\u00010<R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020>X\u0082.¢\u0006\u0002\n\u0000¨\u0006M"}, m3961d2 = {"Lcom/pudutech/serialport/library/SerialPortHelper;", "", "()V", "baudRate", "", "getBaudRate", "()I", "setBaudRate", "(I)V", "context", "Landroid/content/Context;", "flags", "getFlags", "setFlags", "idProduct", "", "getIdProduct", "()Ljava/lang/String;", "setIdProduct", "(Ljava/lang/String;)V", "idVendor", "getIdVendor", "setIdVendor", "inputStream", "Ljava/io/InputStream;", "getInputStream", "()Ljava/io/InputStream;", "setInputStream", "(Ljava/io/InputStream;)V", "interfaceNum", "getInterfaceNum", "setInterfaceNum", "opened", "", "getOpened", "()Z", "setOpened", "(Z)V", "outputStream", "Ljava/io/OutputStream;", "getOutputStream", "()Ljava/io/OutputStream;", "setOutputStream", "(Ljava/io/OutputStream;)V", "product", "getProduct", "setProduct", "readDataThread", "Lcom/pudutech/serialport/library/ReadDataThread;", "serialPort", "Lcom/pudutech/serialport/library/SerialPort;", "serialPortDataReceiveCallback", "Lcom/pudutech/serialport/library/ISerialPortDataReceiveCallback;", "getSerialPortDataReceiveCallback", "()Lcom/pudutech/serialport/library/ISerialPortDataReceiveCallback;", "setSerialPortDataReceiveCallback", "(Lcom/pudutech/serialport/library/ISerialPortDataReceiveCallback;)V", "serialPortOpenStatusCallback", "Lcom/pudutech/serialport/library/ISerialPortOpenStatusCallback;", "usbStateReceiver", "Lcom/pudutech/serialport/library/SerialPortHelper$UsbStateReceiver;", "writeDataThread", "Lcom/pudutech/serialport/library/WriteDataThread;", "closeSerialPort", "", "flush", "init", "isOpened", "openSerialPort", "registerUsbStateReceiver", "release", "unregisterUsbStateReceiver", "writeData", "data", "", "Companion", "UsbStateReceiver", "library_serialport_release"}, m3962k = 1, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class SerialPortHelper {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<SerialPortHelper>() { // from class: com.pudutech.serialport.library.SerialPortHelper$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SerialPortHelper invoke() {
            return new SerialPortHelper();
        }
    });
    public static final String TAG = "SerialPortHelper";
    private int baudRate;
    private Context context;
    private int flags;
    private String idProduct;
    private String idVendor;
    private InputStream inputStream;
    private String interfaceNum;
    private boolean opened;
    private OutputStream outputStream;
    private String product;
    private ReadDataThread readDataThread;
    private SerialPort serialPort;
    private ISerialPortDataReceiveCallback serialPortDataReceiveCallback;
    private ISerialPortOpenStatusCallback serialPortOpenStatusCallback;
    private UsbStateReceiver usbStateReceiver;
    private WriteDataThread writeDataThread;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* compiled from: SerialPortHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/serialport/library/SerialPortHelper$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/serialport/library/SerialPortHelper;", "getINSTANCE", "()Lcom/pudutech/serialport/library/SerialPortHelper;", "INSTANCE$delegate", "Lkotlin/Lazy;", "TAG", "", "library_serialport_release"}, m3962k = 1, m3963mv = {1, 1, 15})
    /* loaded from: classes2.dex */
    public static final class Companion {
        static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Companion.class), "INSTANCE", "getINSTANCE()Lcom/pudutech/serialport/library/SerialPortHelper;"))};

        public final SerialPortHelper getINSTANCE() {
            Lazy lazy = SerialPortHelper.INSTANCE$delegate;
            Companion companion = SerialPortHelper.INSTANCE;
            KProperty kProperty = $$delegatedProperties[0];
            return (SerialPortHelper) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final String getIdVendor() {
        return this.idVendor;
    }

    public final void setIdVendor(String str) {
        this.idVendor = str;
    }

    public final String getIdProduct() {
        return this.idProduct;
    }

    public final void setIdProduct(String str) {
        this.idProduct = str;
    }

    public final String getProduct() {
        return this.product;
    }

    public final void setProduct(String str) {
        this.product = str;
    }

    public final String getInterfaceNum() {
        return this.interfaceNum;
    }

    public final void setInterfaceNum(String str) {
        this.interfaceNum = str;
    }

    public final int getBaudRate() {
        return this.baudRate;
    }

    public final void setBaudRate(int i) {
        this.baudRate = i;
    }

    public final int getFlags() {
        return this.flags;
    }

    public final void setFlags(int i) {
        this.flags = i;
    }

    public final InputStream getInputStream() {
        return this.inputStream;
    }

    public final void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public final OutputStream getOutputStream() {
        return this.outputStream;
    }

    public final void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public final boolean getOpened() {
        return this.opened;
    }

    public final void setOpened(boolean z) {
        this.opened = z;
    }

    public final ISerialPortDataReceiveCallback getSerialPortDataReceiveCallback() {
        return this.serialPortDataReceiveCallback;
    }

    public final void setSerialPortDataReceiveCallback(ISerialPortDataReceiveCallback iSerialPortDataReceiveCallback) {
        this.serialPortDataReceiveCallback = iSerialPortDataReceiveCallback;
    }

    public final SerialPortHelper init(Context context, String idVendor, String idProduct, String product, int baudRate, int flags, ISerialPortOpenStatusCallback serialPortOpenStatusCallback, ISerialPortDataReceiveCallback serialPortDataReceiveCallback) {
        return init(context, idVendor, idProduct, product, null, baudRate, flags, serialPortOpenStatusCallback, serialPortDataReceiveCallback);
    }

    public final SerialPortHelper init(Context context, String idVendor, String idProduct, String product, String interfaceNum, int baudRate, int flags, ISerialPortOpenStatusCallback serialPortOpenStatusCallback, ISerialPortDataReceiveCallback serialPortDataReceiveCallback) {
        this.idVendor = idVendor;
        this.idProduct = idProduct;
        this.product = product;
        this.baudRate = baudRate;
        this.interfaceNum = interfaceNum;
        this.flags = flags;
        this.serialPortOpenStatusCallback = serialPortOpenStatusCallback;
        this.serialPortDataReceiveCallback = serialPortDataReceiveCallback;
        this.context = context;
        registerUsbStateReceiver(context);
        return this;
    }

    public final boolean openSerialPort() throws IllegalArgumentException {
        String serialPortName;
        String str = this.interfaceNum;
        if (str == null || str.length() == 0) {
            serialPortName = SerialPortFinder.INSTANCE.getINSTANCE().getSerialPortName(this.idVendor, this.idProduct, this.product);
        } else {
            serialPortName = SerialPortFinder.INSTANCE.getINSTANCE().getSerialPortName(this.idVendor, this.idProduct, this.product, this.interfaceNum);
        }
        String str2 = serialPortName;
        if (str2 == null || str2.length() == 0) {
        }
        Pdlog.m3273d(TAG, "serialPortName = " + serialPortName);
        Tools.execCommand("chmod 777 /dev/" + serialPortName, true);
        try {
            SerialPort serialPort = new SerialPort(new File("/dev/" + serialPortName), this.baudRate, this.flags);
            this.serialPort = serialPort;
            if (serialPort == null) {
                return false;
            }
            this.opened = true;
            flush();
            ISerialPortOpenStatusCallback iSerialPortOpenStatusCallback = this.serialPortOpenStatusCallback;
            if (iSerialPortOpenStatusCallback != null) {
                iSerialPortOpenStatusCallback.callbackOpenStatus(true);
            }
            this.inputStream = serialPort.getInputStream();
            this.outputStream = serialPort.getOutputStream();
            WriteDataThread writeDataThread = new WriteDataThread(this);
            this.writeDataThread = writeDataThread;
            if (writeDataThread == null) {
                Intrinsics.throwUninitializedPropertyAccessException("writeDataThread");
            }
            writeDataThread.start();
            ReadDataThread readDataThread = new ReadDataThread(this);
            this.readDataThread = readDataThread;
            if (readDataThread == null) {
                Intrinsics.throwUninitializedPropertyAccessException("readDataThread");
            }
            readDataThread.start();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Pdlog.m3274e(TAG, e);
            this.opened = false;
            ISerialPortOpenStatusCallback iSerialPortOpenStatusCallback2 = this.serialPortOpenStatusCallback;
            if (iSerialPortOpenStatusCallback2 != null) {
                iSerialPortOpenStatusCallback2.callbackOpenStatus(false);
            }
            return false;
        }
    }

    public final void writeData(byte[] data) {
        if (data != null) {
            if (!(data.length == 0)) {
                WriteDataThread writeDataThread = this.writeDataThread;
                if (writeDataThread == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("writeDataThread");
                }
                writeDataThread.addDataToQueue(data);
                return;
            }
        }
        Pdlog.m3277w(TAG, "writeData failed, reason: data is null or empty.");
    }

    private final void registerUsbStateReceiver(Context context) {
        if (context == null) {
            return;
        }
        this.usbStateReceiver = new UsbStateReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        context.registerReceiver(this.usbStateReceiver, intentFilter);
    }

    private final void unregisterUsbStateReceiver(Context context) {
        if (context == null) {
            return;
        }
        context.unregisterReceiver(this.usbStateReceiver);
    }

    public final void closeSerialPort() {
        if (this.opened) {
            SerialPort serialPort = this.serialPort;
            if (serialPort != null) {
                serialPort.close();
            }
            this.opened = false;
            ISerialPortOpenStatusCallback iSerialPortOpenStatusCallback = this.serialPortOpenStatusCallback;
            if (iSerialPortOpenStatusCallback != null) {
                iSerialPortOpenStatusCallback.callbackOpenStatus(false);
            }
            ReadDataThread readDataThread = this.readDataThread;
            if (readDataThread == null) {
                Intrinsics.throwUninitializedPropertyAccessException("readDataThread");
            }
            readDataThread.interrupt();
            WriteDataThread writeDataThread = this.writeDataThread;
            if (writeDataThread == null) {
                Intrinsics.throwUninitializedPropertyAccessException("writeDataThread");
            }
            writeDataThread.interrupt();
        }
    }

    public final void flush() {
        SerialPort serialPort;
        if (this.opened && (serialPort = this.serialPort) != null) {
            serialPort.tcflush();
        }
    }

    public final void release() {
        this.serialPort = (SerialPort) null;
        unregisterUsbStateReceiver(this.context);
    }

    public final boolean isOpened() {
        return this.opened;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* compiled from: SerialPortHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/serialport/library/SerialPortHelper$UsbStateReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/pudutech/serialport/library/SerialPortHelper;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "library_serialport_release"}, m3962k = 1, m3963mv = {1, 1, 15})
    /* loaded from: classes2.dex */
    public final class UsbStateReceiver extends BroadcastReceiver {
        public UsbStateReceiver() {
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x001a A[Catch: Exception -> 0x00c9, TryCatch #0 {Exception -> 0x00c9, blocks: (B:3:0x0004, B:5:0x000e, B:10:0x001a, B:11:0x0059, B:13:0x00ab, B:16:0x00ba, B:18:0x0037), top: B:2:0x0004 }] */
        /* JADX WARN: Removed duplicated region for block: B:13:0x00ab A[Catch: Exception -> 0x00c9, TryCatch #0 {Exception -> 0x00c9, blocks: (B:3:0x0004, B:5:0x000e, B:10:0x001a, B:11:0x0059, B:13:0x00ab, B:16:0x00ba, B:18:0x0037), top: B:2:0x0004 }] */
        /* JADX WARN: Removed duplicated region for block: B:16:0x00ba A[Catch: Exception -> 0x00c9, TRY_LEAVE, TryCatch #0 {Exception -> 0x00c9, blocks: (B:3:0x0004, B:5:0x000e, B:10:0x001a, B:11:0x0059, B:13:0x00ab, B:16:0x00ba, B:18:0x0037), top: B:2:0x0004 }] */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0037 A[Catch: Exception -> 0x00c9, TryCatch #0 {Exception -> 0x00c9, blocks: (B:3:0x0004, B:5:0x000e, B:10:0x001a, B:11:0x0059, B:13:0x00ab, B:16:0x00ba, B:18:0x0037), top: B:2:0x0004 }] */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void onReceive(Context context, Intent intent) {
            boolean z;
            boolean isPullOut;
            try {
                String interfaceNum = SerialPortHelper.this.getInterfaceNum();
                if (interfaceNum != null && interfaceNum.length() != 0) {
                    z = false;
                    if (!z) {
                        isPullOut = SerialPortFinder.INSTANCE.getINSTANCE().isPullOut(SerialPortHelper.this.getIdVendor(), SerialPortHelper.this.getIdProduct(), SerialPortHelper.this.getProduct());
                    } else {
                        isPullOut = SerialPortFinder.INSTANCE.getINSTANCE().isPullOut(SerialPortHelper.this.getIdVendor(), SerialPortHelper.this.getIdProduct(), SerialPortHelper.this.getProduct(), SerialPortHelper.this.getInterfaceNum());
                    }
                    Pdlog.m3273d(SerialPortHelper.TAG, "isPullOut = " + isPullOut + ", idVendor = " + SerialPortHelper.this.getIdVendor() + ", idProduct = " + SerialPortHelper.this.getIdProduct() + ", product = " + SerialPortHelper.this.getProduct() + ", interfaceNum = " + SerialPortHelper.this.getInterfaceNum());
                    if (!isPullOut) {
                        Pdlog.m3273d(SerialPortHelper.TAG, "UsbStateReceiver 串口已拔出");
                        SerialPortHelper.this.closeSerialPort();
                        return;
                    } else {
                        Pdlog.m3273d(SerialPortHelper.TAG, "UsbStateReceiver 串口已插入");
                        SerialPortHelper.this.openSerialPort();
                        return;
                    }
                }
                z = true;
                if (!z) {
                }
                Pdlog.m3273d(SerialPortHelper.TAG, "isPullOut = " + isPullOut + ", idVendor = " + SerialPortHelper.this.getIdVendor() + ", idProduct = " + SerialPortHelper.this.getIdProduct() + ", product = " + SerialPortHelper.this.getProduct() + ", interfaceNum = " + SerialPortHelper.this.getInterfaceNum());
                if (!isPullOut) {
                }
            } catch (Exception e) {
                Pdlog.m3274e(SerialPortHelper.TAG, Log.getStackTraceString(e));
            }
        }
    }
}
