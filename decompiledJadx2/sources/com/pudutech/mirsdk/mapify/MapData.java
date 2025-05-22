package com.pudutech.mirsdk.mapify;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MapData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0014\u0018\u00002\u00020\u0001BA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0011\"\u0004\b\u0017\u0010\u0013R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0019\"\u0004\b\u001d\u0010\u001b¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/mirsdk/mapify/MapData;", "", "origin_x", "", "origin_y", "scale", "size_x", "", "size_y", "data", "", "(DDDII[B)V", "getData", "()[B", "setData", "([B)V", "getOrigin_x", "()D", "setOrigin_x", "(D)V", "getOrigin_y", "setOrigin_y", "getScale", "setScale", "getSize_x", "()I", "setSize_x", "(I)V", "getSize_y", "setSize_y", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MapData {
    private byte[] data;
    private double origin_x;
    private double origin_y;
    private double scale;
    private int size_x;
    private int size_y;

    public MapData() {
        this(0.0d, 0.0d, 0.0d, 0, 0, null, 63, null);
    }

    public MapData(double d, double d2, double d3, int i, int i2, byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.origin_x = d;
        this.origin_y = d2;
        this.scale = d3;
        this.size_x = i;
        this.size_y = i2;
        this.data = data;
    }

    public final double getOrigin_x() {
        return this.origin_x;
    }

    public final void setOrigin_x(double d) {
        this.origin_x = d;
    }

    public final double getOrigin_y() {
        return this.origin_y;
    }

    public final void setOrigin_y(double d) {
        this.origin_y = d;
    }

    public final double getScale() {
        return this.scale;
    }

    public final void setScale(double d) {
        this.scale = d;
    }

    public final int getSize_x() {
        return this.size_x;
    }

    public final void setSize_x(int i) {
        this.size_x = i;
    }

    public final int getSize_y() {
        return this.size_y;
    }

    public final void setSize_y(int i) {
        this.size_y = i;
    }

    public /* synthetic */ MapData(double d, double d2, double d3, int i, int i2, byte[] bArr, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0.0d : d, (i3 & 2) != 0 ? 0.0d : d2, (i3 & 4) == 0 ? d3 : 0.0d, (i3 & 8) != 0 ? 0 : i, (i3 & 16) != 0 ? 0 : i2, (i3 & 32) != 0 ? new byte[0] : bArr);
    }

    public final byte[] getData() {
        return this.data;
    }

    public final void setData(byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "<set-?>");
        this.data = bArr;
    }
}
