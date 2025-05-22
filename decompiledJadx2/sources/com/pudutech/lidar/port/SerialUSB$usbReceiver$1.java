package com.pudutech.lidar.port;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.port.SerialUSB;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: SerialUSB.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/lidar/port/SerialUSB$usbReceiver$1", "Landroid/content/BroadcastReceiver;", "onReceive", "", "arg0", "Landroid/content/Context;", "arg1", "Landroid/content/Intent;", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class SerialUSB$usbReceiver$1 extends BroadcastReceiver {
    final /* synthetic */ SerialUSB this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SerialUSB$usbReceiver$1(SerialUSB serialUSB) {
        this.this$0 = serialUSB;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context arg0, Intent arg1) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        SerialUSBListener serialUSBListener;
        String str10;
        String str11;
        Intrinsics.checkParameterIsNotNull(arg0, "arg0");
        Intrinsics.checkParameterIsNotNull(arg1, "arg1");
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onReceive intent=" + arg1.getAction() + ' ' + arg1.getDataString());
        String action = arg1.getAction();
        str2 = this.this$0.ACTION_USB_ATTACHED;
        if (Intrinsics.areEqual(action, str2)) {
            str11 = this.this$0.TAG;
            Pdlog.m3273d(str11, "attached ");
            this.this$0.logDevicesWhenAttached();
            SerialUSB.access$getUsbHandler$p(this.this$0).post(new Runnable() { // from class: com.pudutech.lidar.port.SerialUSB$usbReceiver$1$onReceive$1
                @Override // java.lang.Runnable
                public final void run() {
                    SerialUSB$usbReceiver$1.this.this$0.eventStep = SerialUSB.USBEvent.ATTACHED;
                    SerialUSB$usbReceiver$1.this.this$0.eventFSM();
                }
            });
            return;
        }
        String action2 = arg1.getAction();
        str3 = this.this$0.ACTION_USB_PERMISSION;
        if (Intrinsics.areEqual(action2, str3)) {
            str8 = this.this$0.TAG;
            Pdlog.m3275i(str8, "action permission ");
            Bundle extras = arg1.getExtras();
            if (extras == null) {
                Intrinsics.throwNpe();
            }
            if (extras.getBoolean("permission")) {
                str10 = this.this$0.TAG;
                Pdlog.m3275i(str10, "ask accepted ");
                SerialUSB.access$getUsbHandler$p(this.this$0).post(new Runnable() { // from class: com.pudutech.lidar.port.SerialUSB$usbReceiver$1$onReceive$2
                    @Override // java.lang.Runnable
                    public final void run() {
                        SerialUSB$usbReceiver$1.this.this$0.eventStep = SerialUSB.USBEvent.PERMISSION_OK;
                        SerialUSB$usbReceiver$1.this.this$0.eventFSM();
                    }
                });
                return;
            } else {
                str9 = this.this$0.TAG;
                Pdlog.m3275i(str9, "ask not accepted ");
                serialUSBListener = this.this$0.listener;
                if (serialUSBListener != null) {
                    serialUSBListener.onDeviceOpen(false, "no permission");
                    return;
                }
                return;
            }
        }
        String action3 = arg1.getAction();
        str4 = this.this$0.ACTION_USB_DETACHED;
        if (Intrinsics.areEqual(action3, str4)) {
            str7 = this.this$0.TAG;
            Pdlog.m3275i(str7, "detached ");
            SerialUSB.access$getUsbHandler$p(this.this$0).post(new Runnable() { // from class: com.pudutech.lidar.port.SerialUSB$usbReceiver$1$onReceive$3
                @Override // java.lang.Runnable
                public final void run() {
                    SerialUSB$usbReceiver$1.this.this$0.eventStep = SerialUSB.USBEvent.DETACHED;
                    SerialUSB$usbReceiver$1.this.this$0.eventFSM();
                }
            });
            return;
        }
        String action4 = arg1.getAction();
        str5 = this.this$0.ACTION_USB_DATAERROR_RESET;
        if (Intrinsics.areEqual(action4, str5)) {
            str6 = this.this$0.TAG;
            Pdlog.m3274e(str6, "error to reset lidar");
            SerialUSB.access$getUsbHandler$p(this.this$0).post(new Runnable() { // from class: com.pudutech.lidar.port.SerialUSB$usbReceiver$1$onReceive$4
                @Override // java.lang.Runnable
                public final void run() {
                    SerialUSB$usbReceiver$1.this.this$0.eventStep = SerialUSB.USBEvent.ERROR;
                    SerialUSB$usbReceiver$1.this.this$0.eventFSM();
                }
            });
        }
    }
}
