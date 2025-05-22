package com.pudutech.rgbdlib;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RgbdDeviceInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\f\u001a\u00020\u0003H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/rgbdlib/RgbdDeviceInfo;", "", "name", "", "sn", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getSn", "setSn", "toString", "RGBDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RgbdDeviceInfo {
    private String name;
    private String sn;

    public RgbdDeviceInfo(String name, String sn) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(sn, "sn");
        this.name = name;
        this.sn = sn;
    }

    public final String getName() {
        return this.name;
    }

    public final String getSn() {
        return this.sn;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public final void setSn(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.sn = str;
    }

    public String toString() {
        return "RgbdDeviceInfo(name='" + this.name + "', sn='" + this.sn + "')";
    }
}
