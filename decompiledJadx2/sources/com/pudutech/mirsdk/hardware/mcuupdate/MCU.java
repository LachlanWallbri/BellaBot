package com.pudutech.mirsdk.hardware.mcuupdate;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: MCU.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0014\u001a\u00020\u0005H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/mcuupdate/MCU;", "", "deviceID", "", "name", "", "(BLjava/lang/String;)V", "getDeviceID", "()B", "setDeviceID", "(B)V", "isIAP", "", "()Z", "setIAP", "(Z)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "toString", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class MCU {
    private byte deviceID;
    private boolean isIAP;
    private String name;

    public MCU(byte b, String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.deviceID = b;
        this.name = name;
        this.isIAP = false;
    }

    public final byte getDeviceID() {
        return this.deviceID;
    }

    public final String getName() {
        return this.name;
    }

    public final void setDeviceID(byte b) {
        this.deviceID = b;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    /* renamed from: isIAP, reason: from getter */
    public final boolean getIsIAP() {
        return this.isIAP;
    }

    public final void setIAP(boolean z) {
        this.isIAP = z;
    }

    public String toString() {
        return this.name + " id=" + ((int) this.deviceID) + " isIAP=" + this.isIAP;
    }
}
