package com.pudutech.mirsdk.mapify;

import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import kotlin.Metadata;

/* compiled from: TopoTrack.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/mirsdk/mapify/TopoTrack;", "", "()V", "dual_width", "", "getDual_width", "()D", "setDual_width", "(D)V", "end_pose", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "getEnd_pose", "()Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "setEnd_pose", "(Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "path_width", "getPath_width", "setPath_width", "start_pose", "getStart_pose", "setStart_pose", "topo_id", "", "getTopo_id", "()I", "setTopo_id", "(I)V", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TopoTrack {
    private double dual_width;
    private Vector3d end_pose;
    private double path_width;
    private Vector3d start_pose;
    private int topo_id;

    public final int getTopo_id() {
        return this.topo_id;
    }

    public final void setTopo_id(int i) {
        this.topo_id = i;
    }

    public final Vector3d getStart_pose() {
        return this.start_pose;
    }

    public final void setStart_pose(Vector3d vector3d) {
        this.start_pose = vector3d;
    }

    public final Vector3d getEnd_pose() {
        return this.end_pose;
    }

    public final void setEnd_pose(Vector3d vector3d) {
        this.end_pose = vector3d;
    }

    public final double getPath_width() {
        return this.path_width;
    }

    public final void setPath_width(double d) {
        this.path_width = d;
    }

    public final double getDual_width() {
        return this.dual_width;
    }

    public final void setDual_width(double d) {
        this.dual_width = d;
    }
}
