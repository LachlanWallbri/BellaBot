package com.felhr.utils;

import android.hardware.usb.UsbRequest;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public class SafeUsbRequest extends UsbRequest {
    static final String usbRqBufferField = "mBuffer";
    static final String usbRqLengthField = "mLength";

    @Override // android.hardware.usb.UsbRequest
    public boolean queue(ByteBuffer byteBuffer, int i) {
        try {
            Field declaredField = UsbRequest.class.getDeclaredField(usbRqBufferField);
            Field declaredField2 = UsbRequest.class.getDeclaredField(usbRqLengthField);
            declaredField.setAccessible(true);
            declaredField2.setAccessible(true);
            declaredField.set(this, byteBuffer);
            declaredField2.set(this, Integer.valueOf(i));
            return super.queue(byteBuffer, i);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
