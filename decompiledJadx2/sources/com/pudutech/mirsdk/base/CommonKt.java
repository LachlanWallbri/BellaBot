package com.pudutech.mirsdk.base;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.config.SDKConfig;
import com.pudutech.mirsdk.update.ApiConstants;
import java.io.File;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlin.text.UStringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: Common.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0006\u0010\u0006\u001a\u00020\u0005\u001a\u000e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005\u001a\f\u0010\t\u001a\u00020\u0005*\u00020\u0003H\u0007¨\u0006\n"}, m3961d2 = {"canChecksum", "", "byteArray", "", "getWIFIMac", "", "getWifiMacMethod", "readUtf8TextFile", "fileName", "toHexString", "MirFunction_packRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CommonKt {
    public static final byte canChecksum(byte[] byteArray) {
        Intrinsics.checkParameterIsNotNull(byteArray, "byteArray");
        if (byteArray.length < 7) {
            return (byte) 0;
        }
        int i = 0;
        for (int i2 = 0; i2 <= 6; i2++) {
            i ^= byteArray[i2];
        }
        return (byte) i;
    }

    public static final String toHexString(byte[] toHexString) {
        Intrinsics.checkParameterIsNotNull(toHexString, "$this$toHexString");
        return CollectionsKt.joinToString$default(UByteArray.m4570boximpl(UByteArray.m4572constructorimpl(toHexString)), " ", null, null, 0, null, new Function1<UByte, String>() { // from class: com.pudutech.mirsdk.base.CommonKt$toHexString$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ String invoke(UByte uByte) {
                return invoke(uByte.getData());
            }

            public final String invoke(byte b) {
                return StringsKt.padStart(UStringsKt.m5471toStringLxnNnR4(b, 16), 2, '0');
            }
        }, 30, null);
    }

    public static final String readUtf8TextFile(String fileName) {
        Intrinsics.checkParameterIsNotNull(fileName, "fileName");
        return FilesKt.readText(new File(fileName), Charsets.UTF_8);
    }

    public static final String getWIFIMac() {
        return SDKConfig.INSTANCE.getMAC();
    }

    public static final String getWifiMacMethod() {
        String str = ApiConstants.MAC_ADDRESS;
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface nif = (NetworkInterface) it.next();
                StringBuilder sb = new StringBuilder();
                Intrinsics.checkExpressionValueIsNotNull(nif, "nif");
                sb.append(nif.getName());
                sb.append(", ");
                sb.append(nif.getHardwareAddress());
                Pdlog.m3273d("getWIFIMac", sb.toString());
                if (StringsKt.equals(nif.getName(), "wlan0", true)) {
                    byte[] hardwareAddress = nif.getHardwareAddress();
                    if (hardwareAddress == null) {
                        Pdlog.m3273d("getWIFIMac", nif.getName() + ", macBytes = NULL");
                        return ApiConstants.MAC_ADDRESS;
                    }
                    StringBuilder sb2 = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        Object[] objArr = {Byte.valueOf(b)};
                        String format = String.format("%02X:", Arrays.copyOf(objArr, objArr.length));
                        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                        sb2.append(format);
                    }
                    if (sb2.length() > 0) {
                        sb2.deleteCharAt(sb2.length() - 1);
                    }
                    String sb3 = sb2.toString();
                    Intrinsics.checkExpressionValueIsNotNull(sb3, "mac_str.toString()");
                    try {
                        Pdlog.m3273d("getWIFIMac", "macAddress = " + sb3);
                        return sb3;
                    } catch (Exception e) {
                        e = e;
                        str = sb3;
                        Pdlog.m3274e("common", "get mac address fail:" + e.getMessage());
                        return str;
                    }
                }
            }
        } catch (Exception e2) {
            e = e2;
        }
        return str;
    }
}
