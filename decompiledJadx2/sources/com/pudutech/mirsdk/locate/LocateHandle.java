package com.pudutech.mirsdk.locate;

import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.compat.topo.ConfigJson;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: LocateHandle.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u0003H&J\u001e\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u0003J\u0010\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/mirsdk/locate/LocateHandle;", "", ConfigJson.SENSOR, "", "(I)V", ES6Iterator.NEXT_METHOD, "checkLocate", "", "locateCase", "Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "machine", "Lcom/pudutech/mirsdk/hardware/serialize/ProductMachineType;", "proceed", "setNextHandle", "", "handle", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public abstract class LocateHandle {
    private LocateHandle next;
    private final int sensor;

    public abstract boolean checkLocate(LocateCase locateCase, ProductMachineType machine, int sensor);

    public LocateHandle(int i) {
        this.sensor = i;
    }

    public final void setNextHandle(LocateHandle handle) {
        this.next = handle;
    }

    public final boolean proceed(LocateCase locateCase, ProductMachineType machine, int sensor) {
        Intrinsics.checkParameterIsNotNull(locateCase, "locateCase");
        Intrinsics.checkParameterIsNotNull(machine, "machine");
        if (this.sensor == sensor) {
            return checkLocate(locateCase, machine, sensor);
        }
        LocateHandle locateHandle = this.next;
        if (locateHandle == null) {
            return false;
        }
        if (locateHandle == null) {
            Intrinsics.throwNpe();
        }
        return locateHandle.proceed(locateCase, machine, sensor);
    }
}
