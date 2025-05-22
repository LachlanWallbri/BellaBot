package com.pudutech.mirsdk.mirhardware;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

/* compiled from: GeomagneticData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001J\b\u0010\u001e\u001a\u00020\u0003H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/mirsdk/mirhardware/GeomagneticData;", "", "direction", "", "x", "", "y", CompressorStreamFactory.f8930Z, "(Ljava/lang/String;III)V", "getDirection", "()Ljava/lang/String;", "setDirection", "(Ljava/lang/String;)V", "getX", "()I", "setX", "(I)V", "getY", "setY", "getZ", "setZ", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class GeomagneticData {
    private String direction;
    private int x;
    private int y;
    private int z;

    public static /* synthetic */ GeomagneticData copy$default(GeomagneticData geomagneticData, String str, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = geomagneticData.direction;
        }
        if ((i4 & 2) != 0) {
            i = geomagneticData.x;
        }
        if ((i4 & 4) != 0) {
            i2 = geomagneticData.y;
        }
        if ((i4 & 8) != 0) {
            i3 = geomagneticData.z;
        }
        return geomagneticData.copy(str, i, i2, i3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDirection() {
        return this.direction;
    }

    /* renamed from: component2, reason: from getter */
    public final int getX() {
        return this.x;
    }

    /* renamed from: component3, reason: from getter */
    public final int getY() {
        return this.y;
    }

    /* renamed from: component4, reason: from getter */
    public final int getZ() {
        return this.z;
    }

    public final GeomagneticData copy(String direction, int x, int y, int z) {
        Intrinsics.checkParameterIsNotNull(direction, "direction");
        return new GeomagneticData(direction, x, y, z);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GeomagneticData)) {
            return false;
        }
        GeomagneticData geomagneticData = (GeomagneticData) other;
        return Intrinsics.areEqual(this.direction, geomagneticData.direction) && this.x == geomagneticData.x && this.y == geomagneticData.y && this.z == geomagneticData.z;
    }

    public int hashCode() {
        String str = this.direction;
        return ((((((str != null ? str.hashCode() : 0) * 31) + this.x) * 31) + this.y) * 31) + this.z;
    }

    public GeomagneticData(String direction, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(direction, "direction");
        this.direction = direction;
        this.x = i;
        this.y = i2;
        this.z = i3;
    }

    public final String getDirection() {
        return this.direction;
    }

    public final int getX() {
        return this.x;
    }

    public final int getY() {
        return this.y;
    }

    public final int getZ() {
        return this.z;
    }

    public final void setDirection(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.direction = str;
    }

    public final void setX(int i) {
        this.x = i;
    }

    public final void setY(int i) {
        this.y = i;
    }

    public final void setZ(int i) {
        this.z = i;
    }

    public String toString() {
        return "GeomagneticData(direction='" + this.direction + "', x=" + this.x + ", y=" + this.y + ", z=" + this.z + ')';
    }
}
