package com.pudutech.mirsdk.charge;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.base.CommonKt;
import com.pudutech.mirsdk.bluetooth.BluetoothBleHelper;
import com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterUtils;
import java.util.zip.CRC32;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChargeMessageCenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u000e\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ&\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012J\u0016\u0010\u0014\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0004J\u001e\u0010\u0015\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0017\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0018\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0019\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u001a\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u001b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u0012J\u000e\u0010\u001d\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u001e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\f\u0010\u001f\u001a\u00020\u0004*\u00020\u0004H\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006 "}, m3961d2 = {"Lcom/pudutech/mirsdk/charge/ChargeMessageCenter;", "", "()V", "HEAD", "", "getHEAD", "()[B", "sendCheckPile", "", "mac", "", "sendCheckPileSuccess", "sendDeviceToAPPMode", "sendDeviceToIAPMode", "sendModeReq", "sendPackCrc32Data", "data", "index", "", "dataSize", "sendPackData", "sendPackSizeInfo", "size", "sendRebootReq", "sendStartCharge", "sendStateReq", "sendStopCharge", "sendTotalSize", "fileSize", "sendVersionReq", "sendVersionReqInUpdate", "sumCheck", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ChargeMessageCenter {
    public static final ChargeMessageCenter INSTANCE = new ChargeMessageCenter();
    private static final byte[] HEAD = {80, 68, 67, PrinterUtils.BarCode.CODE93, 65, 82, 71, 69};

    private ChargeMessageCenter() {
    }

    public final byte[] getHEAD() {
        return HEAD;
    }

    public final void sendVersionReq(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        byte b = (byte) 0;
        BluetoothBleHelper.INSTANCE.writeData(mac, sumCheck(ArraysKt.plus(HEAD, new byte[]{b, b, (byte) 85, (byte) 3, (byte) 160, (byte) 1, b})));
    }

    public final void sendStateReq(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        byte b = (byte) 0;
        BluetoothBleHelper.INSTANCE.writeData(mac, sumCheck(ArraysKt.plus(HEAD, new byte[]{b, b, (byte) 85, (byte) 3, (byte) 161, (byte) 2, b})));
    }

    public final void sendStartCharge(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        byte b = (byte) 0;
        BluetoothBleHelper.INSTANCE.writeData(mac, sumCheck(ArraysKt.plus(HEAD, new byte[]{b, b, (byte) 85, (byte) 3, (byte) 162, (byte) 1, b})));
    }

    public final void sendStopCharge(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        byte b = (byte) 0;
        BluetoothBleHelper.INSTANCE.writeData(mac, sumCheck(ArraysKt.plus(HEAD, new byte[]{b, b, (byte) 85, (byte) 3, (byte) 162, (byte) 2, b})));
    }

    public final void sendRebootReq(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        byte b = (byte) 0;
        BluetoothBleHelper.INSTANCE.writeData(mac, sumCheck(ArraysKt.plus(HEAD, new byte[]{b, b, (byte) 85, (byte) 3, (byte) 163, b, b})));
    }

    public final void sendCheckPile(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        byte b = (byte) 0;
        BluetoothBleHelper.INSTANCE.writeData(mac, sumCheck(ArraysKt.plus(HEAD, new byte[]{b, b, (byte) 85, (byte) 3, (byte) 166, (byte) 1, b})));
    }

    public final void sendCheckPileSuccess(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        byte b = (byte) 0;
        BluetoothBleHelper.INSTANCE.writeData(mac, sumCheck(ArraysKt.plus(HEAD, new byte[]{b, b, (byte) 85, (byte) 3, (byte) 166, (byte) 2, b})));
    }

    public final void sendDeviceToIAPMode(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        byte b = (byte) 0;
        byte b2 = (byte) 1;
        BluetoothBleHelper.INSTANCE.writeData(mac, sumCheck(ArraysKt.plus(HEAD, new byte[]{b, b, (byte) 85, b2, b2})));
    }

    public final void sendModeReq(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        byte b = (byte) 0;
        BluetoothBleHelper.INSTANCE.writeData(mac, sumCheck(ArraysKt.plus(HEAD, new byte[]{b, b, (byte) 85, (byte) 1, (byte) 2})));
    }

    public final void sendVersionReqInUpdate(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        byte b = (byte) 0;
        BluetoothBleHelper.INSTANCE.writeData(mac, sumCheck(ArraysKt.plus(HEAD, new byte[]{b, b, (byte) 85, (byte) 1, (byte) 3})));
    }

    public final byte[] sendTotalSize(String mac, int fileSize) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        byte[] bArr = {(byte) 5, (byte) ((fileSize >> 16) & 255), (byte) ((fileSize >> 8) & 255), (byte) ((fileSize >> 0) & 255)};
        byte b = (byte) 0;
        BluetoothBleHelper.INSTANCE.writeData(mac, sumCheck(ArraysKt.plus(ArraysKt.plus(HEAD, new byte[]{b, b, (byte) 85, (byte) 4}), bArr)));
        return bArr;
    }

    public final byte[] sendPackSizeInfo(String mac, int size, int index) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        byte[] bArr = {(byte) 6, (byte) ((size >> 8) & 255), (byte) (size & 255), (byte) ((index >> 8) & 255), (byte) (index & 255)};
        byte b = (byte) 0;
        BluetoothBleHelper.INSTANCE.writeData(mac, sumCheck(ArraysKt.plus(ArraysKt.plus(HEAD, new byte[]{b, b, (byte) 85, (byte) 5}), bArr)));
        return bArr;
    }

    public final void sendPackData(String mac, byte[] data) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(data, "data");
        byte b = (byte) 0;
        BluetoothBleHelper.INSTANCE.writeData(mac, sumCheck(ArraysKt.plus(ArraysKt.plus(HEAD, new byte[]{b, b, (byte) 85, (byte) ((data.length + 1) & 255), (byte) 7}), data)));
    }

    public final void sendPackCrc32Data(String mac, byte[] data, int index, int dataSize) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(data, "data");
        byte[] bArr = new byte[data.length + 2];
        bArr[0] = (byte) ((index >> 8) & 255);
        bArr[1] = (byte) (index & 255);
        System.arraycopy(data, 0, bArr, 2, dataSize);
        Pdlog.m3273d(ChargeUpdateTask.TAG, "sendPackCrc32Data " + CommonKt.toHexString(bArr));
        new CRC32().update(bArr);
        byte b = (byte) 0;
        BluetoothBleHelper.INSTANCE.writeData(mac, sumCheck(ArraysKt.plus(ArraysKt.plus(HEAD, new byte[]{b, b, (byte) 85, (byte) 5, (byte) 8}), new byte[]{(byte) ((r11.getValue() & 4278190080L) >> 24), (byte) ((r11.getValue() & 16711680) >> 16), (byte) ((r11.getValue() & 65280) >> 8), (byte) (r11.getValue() & 255)})));
    }

    public final void sendDeviceToAPPMode(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        byte b = (byte) 0;
        BluetoothBleHelper.INSTANCE.writeData(mac, sumCheck(ArraysKt.plus(HEAD, new byte[]{b, b, (byte) 85, (byte) 1, (byte) 9})));
    }

    private final byte[] sumCheck(byte[] bArr) {
        int i = 0;
        for (byte b : bArr) {
            i = (i + (b & 255)) % 256;
        }
        return ArraysKt.plus(bArr, (byte) i);
    }
}
