package com.pudutech.lidar.pandarxt;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UShort;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: BaseConfigOfPandarXT.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B*\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007ø\u0001\u0000¢\u0006\u0002\u0010\tJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u0014\u001a\u00020\u0005HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u0011\u0010\u0015\u001a\u00020\u0007HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0011\u0010\u0016\u001a\u00020\u0007HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0016\u0010\b\u001a\u00020\u0007ø\u0001\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0004\u001a\u00020\u0005ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0012\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, m3961d2 = {"Lcom/pudutech/lidar/pandarxt/Channel;", "", "channelId", "", "distance_mm", "Lkotlin/UShort;", "reflectivity", "Lkotlin/UByte;", "blank", "(ISBBLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getBlank", "()B", "B", "getChannelId", "()I", "getDistance_mm", "()S", ExifInterface.LATITUDE_SOUTH, "getReflectivity", "component1", "component2", "component3", "component4", "copy", "copy-PoR_94I", "(ISBB)Lcom/pudutech/lidar/pandarxt/Channel;", "equals", "", "other", "hashCode", "toString", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class Channel {
    private final byte blank;
    private final int channelId;
    private final short distance_mm;
    private final byte reflectivity;

    /* renamed from: copy-PoR_94I$default, reason: not valid java name */
    public static /* synthetic */ Channel m4379copyPoR_94I$default(Channel channel, int i, short s, byte b, byte b2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = channel.channelId;
        }
        if ((i2 & 2) != 0) {
            s = channel.distance_mm;
        }
        if ((i2 & 4) != 0) {
            b = channel.reflectivity;
        }
        if ((i2 & 8) != 0) {
            b2 = channel.blank;
        }
        return channel.m4380copyPoR_94I(i, s, b, b2);
    }

    /* renamed from: component1, reason: from getter */
    public final int getChannelId() {
        return this.channelId;
    }

    /* renamed from: component2, reason: from getter */
    public final short getDistance_mm() {
        return this.distance_mm;
    }

    /* renamed from: component3, reason: from getter */
    public final byte getReflectivity() {
        return this.reflectivity;
    }

    /* renamed from: component4, reason: from getter */
    public final byte getBlank() {
        return this.blank;
    }

    /* renamed from: copy-PoR_94I, reason: not valid java name */
    public final Channel m4380copyPoR_94I(int channelId, short distance_mm, byte reflectivity, byte blank) {
        return new Channel(channelId, distance_mm, reflectivity, blank);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Channel)) {
            return false;
        }
        Channel channel = (Channel) other;
        return this.channelId == channel.channelId && this.distance_mm == channel.distance_mm && this.reflectivity == channel.reflectivity && this.blank == channel.blank;
    }

    public int hashCode() {
        return (((((this.channelId * 31) + this.distance_mm) * 31) + this.reflectivity) * 31) + this.blank;
    }

    public String toString() {
        return "Channel(channelId=" + this.channelId + ", distance_mm=" + UShort.m4796toStringimpl(this.distance_mm) + ", reflectivity=" + UByte.m4563toStringimpl(this.reflectivity) + ", blank=" + UByte.m4563toStringimpl(this.blank) + ")";
    }

    private Channel(int i, short s, byte b, byte b2) {
        this.channelId = i;
        this.distance_mm = s;
        this.reflectivity = b;
        this.blank = b2;
    }

    public /* synthetic */ Channel(int i, short s, byte b, byte b2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, s, b, b2);
    }

    public /* synthetic */ Channel(int i, short s, byte b, byte b2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, s, b, b2);
    }

    public final int getChannelId() {
        return this.channelId;
    }

    public final short getDistance_mm() {
        return this.distance_mm;
    }

    public final byte getReflectivity() {
        return this.reflectivity;
    }

    public final byte getBlank() {
        return this.blank;
    }
}
