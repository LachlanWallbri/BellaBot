package com.pudutech.mirsdk.hardware.mcuupdate;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: UpdateStatus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/mcuupdate/UpdateStatus;", "", "(Ljava/lang/String;I)V", "NOT_READY", "CONTINUE", "NO_NEW_VERSION", "BATTERY_NOT_ENOUGH", "VERSION_RESPONSE_FAULT", "FILE_UPDATE_FAIL", "SUCCESS", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public enum UpdateStatus {
    NOT_READY,
    CONTINUE,
    NO_NEW_VERSION,
    BATTERY_NOT_ENOUGH,
    VERSION_RESPONSE_FAULT,
    FILE_UPDATE_FAIL,
    SUCCESS
}
