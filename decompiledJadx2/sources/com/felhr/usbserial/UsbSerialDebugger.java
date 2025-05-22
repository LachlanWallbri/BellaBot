package com.felhr.usbserial;

import android.util.Log;
import com.felhr.utils.HexData;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public class UsbSerialDebugger {
    private static final String CLASS_ID = UsbSerialDebugger.class.getSimpleName();
    public static final String ENCODING = "UTF-8";

    private UsbSerialDebugger() {
    }

    public static void printLogGet(byte[] bArr, boolean z) {
        if (!z) {
            Log.i(CLASS_ID, "Data obtained from write buffer: " + new String(bArr));
            return;
        }
        Log.i(CLASS_ID, "Data obtained from write buffer: " + new String(bArr));
        Log.i(CLASS_ID, "Raw data from write buffer: " + HexData.hexToString(bArr));
        Log.i(CLASS_ID, "Number of bytes obtained from write buffer: " + bArr.length);
    }

    public static void printLogPut(byte[] bArr, boolean z) {
        if (!z) {
            Log.i(CLASS_ID, "Data obtained pushed to write buffer: " + new String(bArr));
            return;
        }
        Log.i(CLASS_ID, "Data obtained pushed to write buffer: " + new String(bArr));
        Log.i(CLASS_ID, "Raw data pushed to write buffer: " + HexData.hexToString(bArr));
        Log.i(CLASS_ID, "Number of bytes pushed from write buffer: " + bArr.length);
    }

    public static void printReadLogGet(byte[] bArr, boolean z) {
        if (!z) {
            Log.i(CLASS_ID, "Data obtained from Read buffer: " + new String(bArr));
            return;
        }
        Log.i(CLASS_ID, "Data obtained from Read buffer: " + new String(bArr));
        Log.i(CLASS_ID, "Raw data from Read buffer: " + HexData.hexToString(bArr));
        Log.i(CLASS_ID, "Number of bytes obtained from Read buffer: " + bArr.length);
    }

    public static void printReadLogPut(byte[] bArr, boolean z) {
        if (!z) {
            Log.i(CLASS_ID, "Data obtained pushed to read buffer: " + new String(bArr));
            return;
        }
        Log.i(CLASS_ID, "Data obtained pushed to read buffer: " + new String(bArr));
        Log.i(CLASS_ID, "Raw data pushed to read buffer: " + HexData.hexToString(bArr));
        Log.i(CLASS_ID, "Number of bytes pushed from read buffer: " + bArr.length);
    }
}
