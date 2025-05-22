package com.pudutech.lidar.rslidar16;

import androidx.exifinterface.media.ExifInterface;
import com.google.common.base.Ascii;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.UShort;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BaseConfigOfRsLidar16.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BH\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\nø\u0001\u0000¢\u0006\u0002\u0010\fJ\u0011\u0010\u0019\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0011\u0010\u001a\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0011\u0010\u001b\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0011\u0010\u001c\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0011\u0010\u001d\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0011\u0010\u001e\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0011\u0010\u001f\u001a\u00020\nHÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0015J\u0011\u0010 \u001a\u00020\nHÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0015Jc\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nHÆ\u0001ø\u0001\u0000¢\u0006\u0004\b\"\u0010#J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010'\u001a\u00020(J\t\u0010)\u001a\u00020*HÖ\u0001J\t\u0010+\u001a\u00020,HÖ\u0001R\u0016\u0010\u0005\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0006\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0010\u0010\u000eR\u0016\u0010\u0007\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0011\u0010\u000eR\u0016\u0010\u0004\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0012\u0010\u000eR\u0016\u0010\b\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0013\u0010\u000eR\u0016\u0010\t\u001a\u00020\nø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u000b\u001a\u00020\nø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0017\u0010\u0015R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0018\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006-"}, m3961d2 = {"Lcom/pudutech/lidar/rslidar16/FrameTime;", "", "year", "Lkotlin/UByte;", "month", "day", "hour", "minute", "second", "second_ms", "Lkotlin/UShort;", "second_us", "(BBBBBBSSLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getDay", "()B", "B", "getHour", "getMinute", "getMonth", "getSecond", "getSecond_ms", "()S", ExifInterface.LATITUDE_SOUTH, "getSecond_us", "getYear", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "copy-jyemUQQ", "(BBBBBBSS)Lcom/pudutech/lidar/rslidar16/FrameTime;", "equals", "", "other", "getFrameTime", "", "hashCode", "", "toString", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class FrameTime {
    private final byte day;
    private final byte hour;
    private final byte minute;
    private final byte month;
    private final byte second;
    private final short second_ms;
    private final short second_us;
    private final byte year;

    /* renamed from: component1, reason: from getter */
    public final byte getYear() {
        return this.year;
    }

    /* renamed from: component2, reason: from getter */
    public final byte getMonth() {
        return this.month;
    }

    /* renamed from: component3, reason: from getter */
    public final byte getDay() {
        return this.day;
    }

    /* renamed from: component4, reason: from getter */
    public final byte getHour() {
        return this.hour;
    }

    /* renamed from: component5, reason: from getter */
    public final byte getMinute() {
        return this.minute;
    }

    /* renamed from: component6, reason: from getter */
    public final byte getSecond() {
        return this.second;
    }

    /* renamed from: component7, reason: from getter */
    public final short getSecond_ms() {
        return this.second_ms;
    }

    /* renamed from: component8, reason: from getter */
    public final short getSecond_us() {
        return this.second_us;
    }

    /* renamed from: copy-jyemUQQ, reason: not valid java name */
    public final FrameTime m4399copyjyemUQQ(byte year, byte month, byte day, byte hour, byte minute, byte second, short second_ms, short second_us) {
        return new FrameTime(year, month, day, hour, minute, second, second_ms, second_us);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FrameTime)) {
            return false;
        }
        FrameTime frameTime = (FrameTime) other;
        return this.year == frameTime.year && this.month == frameTime.month && this.day == frameTime.day && this.hour == frameTime.hour && this.minute == frameTime.minute && this.second == frameTime.second && this.second_ms == frameTime.second_ms && this.second_us == frameTime.second_us;
    }

    public int hashCode() {
        return (((((((((((((this.year * Ascii.f1926US) + this.month) * 31) + this.day) * 31) + this.hour) * 31) + this.minute) * 31) + this.second) * 31) + this.second_ms) * 31) + this.second_us;
    }

    public String toString() {
        return "FrameTime(year=" + UByte.m4563toStringimpl(this.year) + ", month=" + UByte.m4563toStringimpl(this.month) + ", day=" + UByte.m4563toStringimpl(this.day) + ", hour=" + UByte.m4563toStringimpl(this.hour) + ", minute=" + UByte.m4563toStringimpl(this.minute) + ", second=" + UByte.m4563toStringimpl(this.second) + ", second_ms=" + UShort.m4796toStringimpl(this.second_ms) + ", second_us=" + UShort.m4796toStringimpl(this.second_us) + ")";
    }

    private FrameTime(byte b, byte b2, byte b3, byte b4, byte b5, byte b6, short s, short s2) {
        this.year = b;
        this.month = b2;
        this.day = b3;
        this.hour = b4;
        this.minute = b5;
        this.second = b6;
        this.second_ms = s;
        this.second_us = s2;
    }

    public /* synthetic */ FrameTime(byte b, byte b2, byte b3, byte b4, byte b5, byte b6, short s, short s2, DefaultConstructorMarker defaultConstructorMarker) {
        this(b, b2, b3, b4, b5, b6, s, s2);
    }

    public final byte getYear() {
        return this.year;
    }

    public final byte getMonth() {
        return this.month;
    }

    public final byte getDay() {
        return this.day;
    }

    public final byte getHour() {
        return this.hour;
    }

    public final byte getMinute() {
        return this.minute;
    }

    public final byte getSecond() {
        return this.second;
    }

    public final short getSecond_ms() {
        return this.second_ms;
    }

    public final short getSecond_us() {
        return this.second_us;
    }

    public final double getFrameTime() {
        return UnsignedKt.uintToDouble(UInt.m4595constructorimpl(UInt.m4595constructorimpl(UInt.m4595constructorimpl(UInt.m4595constructorimpl(UInt.m4595constructorimpl(UInt.m4595constructorimpl(UInt.m4595constructorimpl(UInt.m4595constructorimpl(this.year & 255) + UInt.m4595constructorimpl(this.month & 255)) + UInt.m4595constructorimpl(this.day & 255)) + UInt.m4595constructorimpl(this.hour & 255)) + UInt.m4595constructorimpl(this.minute & 255)) + UInt.m4595constructorimpl(this.second & 255)) + UInt.m4595constructorimpl(this.second_ms & UShort.MAX_VALUE)) + UInt.m4595constructorimpl(this.second_us & UShort.MAX_VALUE)));
    }
}
