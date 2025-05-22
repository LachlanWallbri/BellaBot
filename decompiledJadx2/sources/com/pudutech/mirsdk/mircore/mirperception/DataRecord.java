package com.pudutech.mirsdk.mircore.mirperception;

import com.pudutech.base.Pdlog;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: DataRecord.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004H\u0086 J\u0011\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0086 J\t\u0010\b\u001a\u00020\u0007H\u0086 J\t\u0010\t\u001a\u00020\u0007H\u0086 ¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/mirperception/DataRecord;", "", "()V", "cleanData", "", "enableDataRecord", "enable", "", "initialize", "isModuleInited", "MirPerception_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DataRecord {
    public static final DataRecord INSTANCE = new DataRecord();

    public final native void cleanData();

    public final native void enableDataRecord(boolean enable);

    public final native boolean initialize();

    public final native boolean isModuleInited();

    static {
        Pdlog.m3273d(PerceptionKt.getTAG(), "load library: data_record");
        System.loadLibrary("data_record");
    }

    private DataRecord() {
    }
}
