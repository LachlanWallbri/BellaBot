package com.pudutech.bumblebee.robot.markscanner;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import com.felhr.usbserial.UsbSerialDevice;
import com.felhr.usbserial.UsbSerialInterface;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
  classes3.dex
 */
/* compiled from: MarkScanner.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000[\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\f*\u0001\u001f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\"\u001a\u00020\u0011J\b\u0010#\u001a\u00020\u000bH\u0002J\u000e\u0010$\u001a\u00020\u00112\u0006\u0010%\u001a\u00020\rJ\b\u0010&\u001a\u00020\u0011H\u0002J\b\u0010'\u001a\u00020\u0011H\u0002J\b\u0010(\u001a\u00020\u0011H\u0002J\b\u0010)\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R#\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00110\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010 R\u000e\u0010!\u001a\u00020\u0017X\u0082D¢\u0006\u0002\n\u0000¨\u0006*"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/markscanner/MarkScanner;", "", "()V", "ACTION_USB_ATTACHED", "", "ACTION_USB_DETACHED", "ACTION_USB_PERMISSION", "TAG", "byteStream", "Ljava/io/ByteArrayOutputStream;", "connected_scanner_", "", "context_", "Landroid/content/Context;", "markListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lkotlin/Function1;", "", "getMarkListener", "()Lcom/pudutech/base/architecture/ThreadSafeListener;", "openJob", "Lkotlinx/coroutines/Job;", "productId", "", "serialPort", "Lcom/felhr/usbserial/UsbSerialDevice;", "usbDevice", "Landroid/hardware/usb/UsbDevice;", "usbManager", "Landroid/hardware/usb/UsbManager;", "usbReceiver", "com/pudutech/bumblebee/robot/markscanner/MarkScanner$usbReceiver$1", "Lcom/pudutech/bumblebee/robot/markscanner/MarkScanner$usbReceiver$1;", "vendorId", "closeScanner", "findScannerDevice", "initScanner", "context", "openDevice", "requestUserPermission", "setFilter", "startScanner", "MarkerScanner_release"}, m3962k = 1, m3963mv = {1, 1, 15})
/* loaded from: classes.dex */
public final class MarkScanner {
    private boolean connected_scanner_;
    private Context context_;
    private Job openJob;
    private UsbSerialDevice serialPort;
    private UsbDevice usbDevice;
    private UsbManager usbManager;
    private final String TAG = "MarkScanner";
    private final int vendorId = 9168;
    private final int productId = 3265;
    private final String ACTION_USB_ATTACHED = "android.hardware.usb.action.USB_DEVICE_ATTACHED";
    private final String ACTION_USB_DETACHED = "android.hardware.usb.action.USB_DEVICE_DETACHED";
    private final String ACTION_USB_PERMISSION = "com.pudutech.markscan.ACTION_USB_PERMISSION";
    private final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    private final ThreadSafeListener<Function1<String, Unit>> markListener = new ThreadSafeListener<>();
    private final MarkScanner$usbReceiver$1 usbReceiver = new BroadcastReceiver() { // from class: com.pudutech.bumblebee.robot.markscanner.MarkScanner$usbReceiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context arg0, Intent arg1) {
            String str;
            String str2;
            String str3;
            String str4;
            boolean findScannerDevice;
            String str5;
            String str6;
            String str7;
            String str8;
            boolean findScannerDevice2;
            Intrinsics.checkParameterIsNotNull(arg0, "arg0");
            Intrinsics.checkParameterIsNotNull(arg1, "arg1");
            String action = arg1.getAction();
            str = MarkScanner.this.ACTION_USB_ATTACHED;
            if (Intrinsics.areEqual(action, str)) {
                str8 = MarkScanner.this.TAG;
                Pdlog.m3273d(str8, "attached ");
                findScannerDevice2 = MarkScanner.this.findScannerDevice();
                if (findScannerDevice2) {
                    MarkScanner.this.requestUserPermission();
                    return;
                }
                return;
            }
            String action2 = arg1.getAction();
            str2 = MarkScanner.this.ACTION_USB_PERMISSION;
            if (Intrinsics.areEqual(action2, str2)) {
                str5 = MarkScanner.this.TAG;
                Pdlog.m3275i(str5, "action permission ");
                Bundle extras = arg1.getExtras();
                if (extras == null) {
                    Intrinsics.throwNpe();
                }
                if (extras.getBoolean("permission")) {
                    str7 = MarkScanner.this.TAG;
                    Pdlog.m3275i(str7, "ask accepted ");
                    MarkScanner.this.openDevice();
                    MarkScanner.this.connected_scanner_ = true;
                    return;
                }
                str6 = MarkScanner.this.TAG;
                Pdlog.m3275i(str6, "ask not accepted ");
                return;
            }
            String action3 = arg1.getAction();
            str3 = MarkScanner.this.ACTION_USB_DETACHED;
            if (Intrinsics.areEqual(action3, str3)) {
                str4 = MarkScanner.this.TAG;
                Pdlog.m3275i(str4, "detached ");
                findScannerDevice = MarkScanner.this.findScannerDevice();
                if (findScannerDevice) {
                    return;
                }
                MarkScanner.this.connected_scanner_ = false;
                MarkScanner.this.startScanner();
            }
        }
    };

    public final ThreadSafeListener<Function1<String, Unit>> getMarkListener() {
        return this.markListener;
    }

    public final void initScanner(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Pdlog.m3273d(this.TAG, "start mark scanner");
        if (this.connected_scanner_) {
            return;
        }
        this.context_ = context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context_");
        }
        Object systemService = context.getSystemService("usb");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.hardware.usb.UsbManager");
        }
        this.usbManager = (UsbManager) systemService;
        setFilter();
        startScanner();
    }

    public final void closeScanner() {
        Context context = this.context_;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context_");
        }
        context.unregisterReceiver(this.usbReceiver);
        Job job = this.openJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }

    private final void setFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(this.ACTION_USB_PERMISSION);
        intentFilter.addAction(this.ACTION_USB_DETACHED);
        intentFilter.addAction(this.ACTION_USB_ATTACHED);
        Context context = this.context_;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context_");
        }
        context.registerReceiver(this.usbReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean findScannerDevice() {
        UsbManager usbManager = this.usbManager;
        if (usbManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usbManager");
        }
        HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
        if (deviceList != null) {
            for (Map.Entry<String, UsbDevice> entry : deviceList.entrySet()) {
                String str = this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("vendor id: ");
                UsbDevice value = entry.getValue();
                Intrinsics.checkExpressionValueIsNotNull(value, "device.value");
                sb.append(value.getVendorId());
                sb.append(" product id: ");
                UsbDevice value2 = entry.getValue();
                Intrinsics.checkExpressionValueIsNotNull(value2, "device.value");
                sb.append(value2.getProductId());
                sb.append(" device name ");
                UsbDevice value3 = entry.getValue();
                Intrinsics.checkExpressionValueIsNotNull(value3, "device.value");
                sb.append(value3.getDeviceName());
                sb.append(" product name ");
                UsbDevice value4 = entry.getValue();
                Intrinsics.checkExpressionValueIsNotNull(value4, "device.value");
                sb.append(value4.getProductName());
                Pdlog.m3273d(str, sb.toString());
                UsbDevice value5 = entry.getValue();
                Intrinsics.checkExpressionValueIsNotNull(value5, "device.value");
                if (value5.getVendorId() == this.vendorId) {
                    UsbDevice value6 = entry.getValue();
                    Intrinsics.checkExpressionValueIsNotNull(value6, "device.value");
                    if (value6.getProductId() == this.productId) {
                        Pdlog.m3273d(this.TAG, "find device vendor id: 0x23d0, product id: 0x0cc0");
                        UsbDevice value7 = entry.getValue();
                        Intrinsics.checkExpressionValueIsNotNull(value7, "device.value");
                        this.usbDevice = value7;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openDevice() {
        UsbDevice usbDevice = this.usbDevice;
        if (usbDevice == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usbDevice");
        }
        UsbManager usbManager = this.usbManager;
        if (usbManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usbManager");
        }
        UsbDevice usbDevice2 = this.usbDevice;
        if (usbDevice2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usbDevice");
        }
        UsbSerialDevice createUsbSerialDevice = UsbSerialDevice.createUsbSerialDevice(usbDevice, usbManager.openDevice(usbDevice2));
        this.serialPort = createUsbSerialDevice;
        if (createUsbSerialDevice == null || !createUsbSerialDevice.open()) {
            return;
        }
        createUsbSerialDevice.read(new UsbSerialInterface.UsbReadCallback() { // from class: com.pudutech.bumblebee.robot.markscanner.MarkScanner$openDevice$$inlined$let$lambda$1
            @Override // com.felhr.usbserial.UsbSerialInterface.UsbReadCallback
            public final void onReceivedData(byte[] bArr) {
                ByteArrayOutputStream byteArrayOutputStream;
                ByteArrayOutputStream byteArrayOutputStream2;
                ByteArrayOutputStream byteArrayOutputStream3;
                String str;
                ByteArrayOutputStream byteArrayOutputStream4;
                if (bArr != null) {
                    byteArrayOutputStream = MarkScanner.this.byteStream;
                    byteArrayOutputStream.write(bArr);
                    byteArrayOutputStream2 = MarkScanner.this.byteStream;
                    byte[] byteArray = byteArrayOutputStream2.toByteArray();
                    Intrinsics.checkExpressionValueIsNotNull(byteArray, "byteStream.toByteArray()");
                    if (ArraysKt.contains(byteArray, (byte) 13)) {
                        byteArrayOutputStream3 = MarkScanner.this.byteStream;
                        final String byteArrayOutputStream5 = byteArrayOutputStream3.toString("UTF-8");
                        str = MarkScanner.this.TAG;
                        Pdlog.m3273d(str, "mark is " + byteArrayOutputStream5);
                        MarkScanner.this.getMarkListener().notify(new Function2<Function1<? super String, ? extends Unit>, String, Unit>() { // from class: com.pudutech.bumblebee.robot.markscanner.MarkScanner$openDevice$$inlined$let$lambda$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Function1<? super String, ? extends Unit> function1, String str2) {
                                invoke2((Function1<? super String, Unit>) function1, str2);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Function1<? super String, Unit> it, String str2) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                                String markstr = byteArrayOutputStream5;
                                Intrinsics.checkExpressionValueIsNotNull(markstr, "markstr");
                                it.invoke(markstr);
                            }
                        });
                        byteArrayOutputStream4 = MarkScanner.this.byteStream;
                        byteArrayOutputStream4.reset();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestUserPermission() {
        Pdlog.m3275i(this.TAG, "requestUserPermission");
        Context context = this.context_;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context_");
        }
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, new Intent(this.ACTION_USB_PERMISSION), 0);
        UsbManager usbManager = this.usbManager;
        if (usbManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usbManager");
        }
        UsbDevice usbDevice = this.usbDevice;
        if (usbDevice == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usbDevice");
        }
        usbManager.requestPermission(usbDevice, broadcast);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startScanner() {
        Job launch$default;
        Job job = this.openJob;
        if (job == null || job == null || !job.isActive()) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new MarkScanner$startScanner$1(this, null), 3, null);
            this.openJob = launch$default;
        }
    }
}
