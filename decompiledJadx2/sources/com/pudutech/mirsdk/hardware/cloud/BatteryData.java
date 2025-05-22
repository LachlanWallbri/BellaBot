package com.pudutech.mirsdk.hardware.cloud;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: BatteryHealth.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R*\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/cloud/BatteryData;", "Lcom/pudutech/mirsdk/hardware/cloud/HardwareCloudHead;", "()V", "data", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getData", "()Ljava/util/ArrayList;", "setData", "(Ljava/util/ArrayList;)V", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class BatteryData extends HardwareCloudHead {
    private ArrayList<Byte> data;

    public BatteryData() {
        super(null, 0L, null, null, 15, null);
        this.data = new ArrayList<>();
    }

    public final ArrayList<Byte> getData() {
        return this.data;
    }

    public final void setData(ArrayList<Byte> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.data = arrayList;
    }
}
