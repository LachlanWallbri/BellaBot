package com.pudutech.lidar.pandarxt;

import com.google.common.base.Ascii;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: BaseConfigOfPandarXT.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B8\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0011\u0010\u0012\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0011\u0010\u0013\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0011\u0010\u0014\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0011\u0010\u0015\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0011\u0010\u0016\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0011\u0010\u0017\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000bJO\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010\u001e\u001a\u00020\u001fJ\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020#HÖ\u0001R\u0016\u0010\u0005\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0006\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\r\u0010\u000bR\u0016\u0010\u0007\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000e\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000f\u0010\u000bR\u0016\u0010\b\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0010\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0011\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, m3961d2 = {"Lcom/pudutech/lidar/pandarxt/DataTime;", "", "year", "Lkotlin/UByte;", "month", "day", "hour", "minute", "second", "(BBBBBBLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getDay", "()B", "B", "getHour", "getMinute", "getMonth", "getSecond", "getYear", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "copy-BUD_dEw", "(BBBBBB)Lcom/pudutech/lidar/pandarxt/DataTime;", "equals", "", "other", "getSecondTime", "", "hashCode", "", "toString", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class DataTime {
    private final byte day;
    private final byte hour;
    private final byte minute;
    private final byte month;
    private final byte second;
    private final byte year;

    /* renamed from: copy-BUD_dEw$default, reason: not valid java name */
    public static /* synthetic */ DataTime m4387copyBUD_dEw$default(DataTime dataTime, byte b, byte b2, byte b3, byte b4, byte b5, byte b6, int i, Object obj) {
        if ((i & 1) != 0) {
            b = dataTime.year;
        }
        if ((i & 2) != 0) {
            b2 = dataTime.month;
        }
        byte b7 = b2;
        if ((i & 4) != 0) {
            b3 = dataTime.day;
        }
        byte b8 = b3;
        if ((i & 8) != 0) {
            b4 = dataTime.hour;
        }
        byte b9 = b4;
        if ((i & 16) != 0) {
            b5 = dataTime.minute;
        }
        byte b10 = b5;
        if ((i & 32) != 0) {
            b6 = dataTime.second;
        }
        return dataTime.m4388copyBUD_dEw(b, b7, b8, b9, b10, b6);
    }

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

    /* renamed from: copy-BUD_dEw, reason: not valid java name */
    public final DataTime m4388copyBUD_dEw(byte year, byte month, byte day, byte hour, byte minute, byte second) {
        return new DataTime(year, month, day, hour, minute, second);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DataTime)) {
            return false;
        }
        DataTime dataTime = (DataTime) other;
        return this.year == dataTime.year && this.month == dataTime.month && this.day == dataTime.day && this.hour == dataTime.hour && this.minute == dataTime.minute && this.second == dataTime.second;
    }

    public int hashCode() {
        return (((((((((this.year * Ascii.f1926US) + this.month) * 31) + this.day) * 31) + this.hour) * 31) + this.minute) * 31) + this.second;
    }

    public String toString() {
        return "DataTime(year=" + UByte.m4563toStringimpl(this.year) + ", month=" + UByte.m4563toStringimpl(this.month) + ", day=" + UByte.m4563toStringimpl(this.day) + ", hour=" + UByte.m4563toStringimpl(this.hour) + ", minute=" + UByte.m4563toStringimpl(this.minute) + ", second=" + UByte.m4563toStringimpl(this.second) + ")";
    }

    private DataTime(byte b, byte b2, byte b3, byte b4, byte b5, byte b6) {
        this.year = b;
        this.month = b2;
        this.day = b3;
        this.hour = b4;
        this.minute = b5;
        this.second = b6;
    }

    public /* synthetic */ DataTime(byte b, byte b2, byte b3, byte b4, byte b5, byte b6, DefaultConstructorMarker defaultConstructorMarker) {
        this(b, b2, b3, b4, b5, b6);
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

    public final double getSecondTime() {
        return UnsignedKt.uintToDouble(UInt.m4595constructorimpl(UInt.m4595constructorimpl(UInt.m4595constructorimpl(UInt.m4595constructorimpl(UInt.m4595constructorimpl(UInt.m4595constructorimpl(this.year & 255) + UInt.m4595constructorimpl(this.month & 255)) + UInt.m4595constructorimpl(this.day & 255)) + UInt.m4595constructorimpl(this.hour & 255)) + UInt.m4595constructorimpl(this.minute & 255)) + UInt.m4595constructorimpl(this.second & 255)));
    }
}
