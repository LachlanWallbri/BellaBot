package com.pudutech.mirsdk.mapify;

import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Destination.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0015\"\u0004\b \u0010\u0017R\u001a\u0010!\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0015\"\u0004\b#\u0010\u0017R\u001a\u0010$\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0015\"\u0004\b&\u0010\u0017R\u001a\u0010'\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\f\"\u0004\b)\u0010\u000eR\u001a\u0010*\u001a\u00020+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/¨\u00060"}, m3961d2 = {"Lcom/pudutech/mirsdk/mapify/Destination;", "", "()V", MapElement.Key.DIR, "", "getDir", "()D", "setDir", "(D)V", MapElement.Key.DIR_MODE, "", "getDirMode", "()I", "setDirMode", "(I)V", "doubleDir", "getDoubleDir", "setDoubleDir", MapElement.Key.GROUP, "", "getGroup", "()Ljava/lang/String;", "setGroup", "(Ljava/lang/String;)V", "high_precision", "", "getHigh_precision", "()Z", "setHigh_precision", "(Z)V", "id", "getId", "setId", "mode", "getMode", "setMode", "name", "getName", "setName", "sort_weight", "getSort_weight", "setSort_weight", MapElement.Key.VECTOR, "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "getVector", "()Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "setVector", "(Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class Destination {
    private double dir;
    private int dirMode;
    private int doubleDir;
    private int sort_weight;
    private Vector3d vector = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
    private String name = "";
    private String mode = "";
    private String id = "";
    private String group = "";
    private boolean high_precision = true;

    public final Vector3d getVector() {
        return this.vector;
    }

    public final void setVector(Vector3d vector3d) {
        Intrinsics.checkParameterIsNotNull(vector3d, "<set-?>");
        this.vector = vector3d;
    }

    public final double getDir() {
        return this.dir;
    }

    public final void setDir(double d) {
        this.dir = d;
    }

    public final int getDoubleDir() {
        return this.doubleDir;
    }

    public final void setDoubleDir(int i) {
        this.doubleDir = i;
    }

    public final int getDirMode() {
        return this.dirMode;
    }

    public final void setDirMode(int i) {
        this.dirMode = i;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public final String getMode() {
        return this.mode;
    }

    public final void setMode(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mode = str;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.id = str;
    }

    public final String getGroup() {
        return this.group;
    }

    public final void setGroup(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.group = str;
    }

    public final boolean getHigh_precision() {
        return this.high_precision;
    }

    public final void setHigh_precision(boolean z) {
        this.high_precision = z;
    }

    public final int getSort_weight() {
        return this.sort_weight;
    }

    public final void setSort_weight(int i) {
        this.sort_weight = i;
    }
}
