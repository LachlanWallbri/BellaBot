package com.pudutech.mirsdk.mircore.mirperception;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: DynamicObject.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004H\u0086 J\u0011\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086 J\t\u0010\t\u001a\u00020\u0006H\u0086 J9\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000fH\u0086 J\u0011\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\rH\u0086 J\u0011\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0086 ¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/mirperception/DynamicObject;", "", "()V", "getDynamicAddress", "", "initialize", "", "machineModel", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "isModuleInited", "setOtherRobotInfo", "", "id", "", "x", "", "y", "yaw", "vx", "vy", "setTopoTrackPath", "topo_path", "switchDynamic", "enable_dynamic", "MirPerception_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DynamicObject {
    public static final DynamicObject INSTANCE = new DynamicObject();

    public final native long[] getDynamicAddress();

    public final native boolean initialize(MachineModel machineModel);

    public final native boolean isModuleInited();

    public final native void setOtherRobotInfo(String id, double x, double y, double yaw, double vx, double vy);

    public final native void setTopoTrackPath(String topo_path);

    public final native boolean switchDynamic(boolean enable_dynamic);

    static {
        Pdlog.m3273d(PerceptionKt.getTAG(), "load library: dynamic_object");
        System.loadLibrary("dynamic_object");
    }

    private DynamicObject() {
    }
}
