package com.pudutech.lidar.rslidar16;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UShort;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BaseConfigOfRsLidar16.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B \u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000¢\u0006\u0002\u0010\bJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u0012\u001a\u00020\u0005HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u0011\u0010\u0013\u001a\u00020\u0007HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u0005ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/lidar/rslidar16/ChannelData;", "", "id", "", "distance_cm", "Lkotlin/UShort;", "reflectivity", "Lkotlin/UByte;", "(ISBLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getDistance_cm", "()S", ExifInterface.LATITUDE_SOUTH, "getId", "()I", "getReflectivity", "()B", "B", "component1", "component2", "component3", "copy", "copy-gq6AKHk", "(ISB)Lcom/pudutech/lidar/rslidar16/ChannelData;", "equals", "", "other", "hashCode", "toString", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class ChannelData {
    private final short distance_cm;
    private final int id;
    private final byte reflectivity;

    /* renamed from: copy-gq6AKHk$default, reason: not valid java name */
    public static /* synthetic */ ChannelData m4394copygq6AKHk$default(ChannelData channelData, int i, short s, byte b, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = channelData.id;
        }
        if ((i2 & 2) != 0) {
            s = channelData.distance_cm;
        }
        if ((i2 & 4) != 0) {
            b = channelData.reflectivity;
        }
        return channelData.m4395copygq6AKHk(i, s, b);
    }

    /* renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final short getDistance_cm() {
        return this.distance_cm;
    }

    /* renamed from: component3, reason: from getter */
    public final byte getReflectivity() {
        return this.reflectivity;
    }

    /* renamed from: copy-gq6AKHk, reason: not valid java name */
    public final ChannelData m4395copygq6AKHk(int id, short distance_cm, byte reflectivity) {
        return new ChannelData(id, distance_cm, reflectivity);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ChannelData)) {
            return false;
        }
        ChannelData channelData = (ChannelData) other;
        return this.id == channelData.id && this.distance_cm == channelData.distance_cm && this.reflectivity == channelData.reflectivity;
    }

    public int hashCode() {
        return (((this.id * 31) + this.distance_cm) * 31) + this.reflectivity;
    }

    public String toString() {
        return "ChannelData(id=" + this.id + ", distance_cm=" + UShort.m4796toStringimpl(this.distance_cm) + ", reflectivity=" + UByte.m4563toStringimpl(this.reflectivity) + ")";
    }

    private ChannelData(int i, short s, byte b) {
        this.id = i;
        this.distance_cm = s;
        this.reflectivity = b;
    }

    public /* synthetic */ ChannelData(int i, short s, byte b, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, s, b);
    }

    public final int getId() {
        return this.id;
    }

    public final short getDistance_cm() {
        return this.distance_cm;
    }

    public final byte getReflectivity() {
        return this.reflectivity;
    }
}
