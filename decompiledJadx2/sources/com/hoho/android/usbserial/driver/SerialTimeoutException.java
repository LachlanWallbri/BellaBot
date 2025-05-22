package com.hoho.android.usbserial.driver;

import java.io.InterruptedIOException;

/* loaded from: classes4.dex */
public class SerialTimeoutException extends InterruptedIOException {
    public SerialTimeoutException(String s) {
        super(s);
    }
}
