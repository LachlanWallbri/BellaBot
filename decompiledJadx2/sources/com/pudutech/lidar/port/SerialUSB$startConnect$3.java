package com.pudutech.lidar.port;

import android.os.Handler;
import android.os.Message;
import com.felhr.usbserial.UsbSerialInterface;
import kotlin.Metadata;

/* compiled from: SerialUSB.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "state", "", "onDSRChanged"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final class SerialUSB$startConnect$3 implements UsbSerialInterface.UsbDSRCallback {
    final /* synthetic */ SerialUSB this$0;

    SerialUSB$startConnect$3(SerialUSB serialUSB) {
        this.this$0 = serialUSB;
    }

    @Override // com.felhr.usbserial.UsbSerialInterface.UsbDSRCallback
    public final void onDSRChanged(boolean z) {
        Message obtainMessage;
        Handler receiveHandler = this.this$0.getReceiveHandler();
        if (receiveHandler == null || (obtainMessage = receiveHandler.obtainMessage(102, Boolean.valueOf(z))) == null) {
            return;
        }
        obtainMessage.sendToTarget();
    }
}
