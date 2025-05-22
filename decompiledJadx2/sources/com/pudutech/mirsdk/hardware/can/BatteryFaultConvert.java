package com.pudutech.mirsdk.hardware.can;

import java.util.HashMap;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: BatteryFaultConvert.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fR6\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/can/BatteryFaultConvert;", "", "()V", "faultMap", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "getFaultMap", "()Ljava/util/HashMap;", "setFaultMap", "(Ljava/util/HashMap;)V", "getBatteryFaultMsg", "bytes", "Lkotlin/UByteArray;", "getBatteryFaultMsg-GBYM_sE", "([B)Ljava/lang/String;", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class BatteryFaultConvert {
    public static final BatteryFaultConvert INSTANCE = new BatteryFaultConvert();
    private static HashMap<String, String> faultMap;

    static {
        HashMap<String, String> hashMap = new HashMap<>();
        faultMap = hashMap;
        hashMap.put("11", "电芯过压警告");
        faultMap.put("12", "电芯欠压警告");
        faultMap.put("21", "放电过温警告");
        faultMap.put("22", "电芯欠压警告");
        faultMap.put("31", "充电过温警告");
        faultMap.put("32", "充电低温警报");
        faultMap.put("41", "充电过流警报");
        faultMap.put("51", "放电过流警报");
        faultMap.put("61", "电芯异常");
    }

    private BatteryFaultConvert() {
    }

    public final HashMap<String, String> getFaultMap() {
        return faultMap;
    }

    public final void setFaultMap(HashMap<String, String> hashMap) {
        Intrinsics.checkParameterIsNotNull(hashMap, "<set-?>");
        faultMap = hashMap;
    }

    /* renamed from: getBatteryFaultMsg-GBYM_sE, reason: not valid java name */
    public final String m4422getBatteryFaultMsgGBYM_sE(byte[] bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 6; i++) {
            String str = faultMap.get(i + UByte.m4563toStringimpl(UByteArray.m4577getimpl(bytes, i)));
            String str2 = str;
            if (!(str2 == null || str2.length() == 0)) {
                sb.append(str);
                sb.append(",");
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "buffer.toString()");
        return sb2;
    }
}
