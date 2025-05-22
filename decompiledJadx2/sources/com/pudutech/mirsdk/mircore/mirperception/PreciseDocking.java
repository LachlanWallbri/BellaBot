package com.pudutech.mirsdk.mircore.mirperception;

import com.pudutech.base.Pdlog;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: PreciseDocking.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086 J\t\u0010\u0007\u001a\u00020\bH\u0086 J\t\u0010\t\u001a\u00020\nH\u0086 J\t\u0010\u000b\u001a\u00020\u0004H\u0086 J\t\u0010\f\u001a\u00020\u0004H\u0086 J\u0011\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086 J\u0019\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014H\u0086 ¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/mirperception/PreciseDocking;", "", "()V", "enableRGBD", "", "rgbd_config_file", "", "getMarkerPose", "", "getMarkerPoseAddress", "", "initialize", "isModuleInited", "setDockDist", "", "dock_dist", "", "setDockingSwitch", "need_docking", "target_id", "", "MirPerception_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class PreciseDocking {
    public static final PreciseDocking INSTANCE = new PreciseDocking();

    public final native boolean enableRGBD(String rgbd_config_file);

    public final native double[] getMarkerPose();

    public final native long getMarkerPoseAddress();

    public final native boolean initialize();

    public final native boolean isModuleInited();

    public final native void setDockDist(double dock_dist);

    public final native void setDockingSwitch(boolean need_docking, int target_id);

    static {
        Pdlog.m3273d(PerceptionKt.getTAG(), "load library: circle_marker_detect");
        System.loadLibrary("circle_marker_detect");
    }

    private PreciseDocking() {
    }
}
