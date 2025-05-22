package com.pudutech.lidar.port;

import com.felhr.usbserial.UsbSerialInterface;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SerialUSB.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "bytes", "", "kotlin.jvm.PlatformType", "onReceivedData"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final class SerialUSB$startConnect$1 implements UsbSerialInterface.UsbReadCallback {
    final /* synthetic */ SerialUSB this$0;

    SerialUSB$startConnect$1(SerialUSB serialUSB) {
        this.this$0 = serialUSB;
    }

    @Override // com.felhr.usbserial.UsbSerialInterface.UsbReadCallback
    public final void onReceivedData(byte[] bytes) {
        this.this$0.getSerialUsbDataArrived().set(true);
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("read ");
        sb.append(bytes != null ? Integer.valueOf(bytes.length) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d("SerialUSB_Lidar", objArr);
        Function1 receiveLidarDataListener$LidarLib_mirRelease = this.this$0.getReceiveLidarDataListener$LidarLib_mirRelease();
        if (receiveLidarDataListener$LidarLib_mirRelease != null) {
            Intrinsics.checkExpressionValueIsNotNull(bytes, "bytes");
        }
    }
}
