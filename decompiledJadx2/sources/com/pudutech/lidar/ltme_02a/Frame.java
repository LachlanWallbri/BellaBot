package com.pudutech.lidar.ltme_02a;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.UShort;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: Frame.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u00104\u001a\u000205H\u0016R\u001f\u0010\u0003\u001a\u00020\u0004X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001f\u0010\n\u001a\u00020\u000bX\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001f\u0010\u0011\u001a\u00020\u000bX\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR\u001f\u0010\u0014\u001a\u00020\u0004X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0015\u0010\u0006\"\u0004\b\u0016\u0010\bR'\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0018X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR$\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u0018X\u0086\u000e¢\u0006\u0010\n\u0002\u0010$\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001f\u0010%\u001a\u00020\u000bX\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b&\u0010\r\"\u0004\b'\u0010\u000fR\u001f\u0010(\u001a\u00020\u000bX\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b)\u0010\r\"\u0004\b*\u0010\u000fR\u0019\u0010+\u001a\u00020\u000bX\u0086Dø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b,\u0010\rR\u001f\u0010-\u001a\u00020.X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u00103\u001a\u0004\b/\u00100\"\u0004\b1\u00102\u0082\u0002\u0004\n\u0002\b\u0019¨\u00066"}, m3961d2 = {"Lcom/pudutech/lidar/ltme_02a/Frame;", "", "()V", "blockNumber", "Lkotlin/UByte;", "getBlockNumber", "()B", "setBlockNumber-7apg3OU", "(B)V", "B", "checkSum", "Lkotlin/UShort;", "getCheckSum", "()S", "setCheckSum-xj2QHRw", "(S)V", ExifInterface.LATITUDE_SOUTH, "count", "getCount", "setCount-xj2QHRw", "flags", "getFlags", "setFlags-7apg3OU", "intensities", "", "getIntensities", "()[Lkotlin/UByte;", "setIntensities", "([Lkotlin/UByte;)V", "[Lkotlin/UByte;", "ranges", "", "getRanges", "()[Ljava/lang/Double;", "setRanges", "([Ljava/lang/Double;)V", "[Ljava/lang/Double;", "reserved1", "getReserved1", "setReserved1-xj2QHRw", "reserved2", "getReserved2", "setReserved2-xj2QHRw", "signature", "getSignature", "timestamp_us", "Lkotlin/UInt;", "getTimestamp_us", "()I", "setTimestamp_us-WZ4Q5Ns", "(I)V", "I", "toString", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class Frame {
    private byte blockNumber;
    private short checkSum;
    private short count;
    private byte flags;
    private UByte[] intensities;
    private Double[] ranges;
    private short reserved1;
    private short reserved2;
    private final short signature = -1;
    private int timestamp_us;

    public final short getSignature() {
        return this.signature;
    }

    public final short getReserved1() {
        return this.reserved1;
    }

    /* renamed from: setReserved1-xj2QHRw, reason: not valid java name */
    public final void m4376setReserved1xj2QHRw(short s) {
        this.reserved1 = s;
    }

    public final byte getBlockNumber() {
        return this.blockNumber;
    }

    /* renamed from: setBlockNumber-7apg3OU, reason: not valid java name */
    public final void m4372setBlockNumber7apg3OU(byte b) {
        this.blockNumber = b;
    }

    public final byte getFlags() {
        return this.flags;
    }

    /* renamed from: setFlags-7apg3OU, reason: not valid java name */
    public final void m4375setFlags7apg3OU(byte b) {
        this.flags = b;
    }

    public final short getCount() {
        return this.count;
    }

    /* renamed from: setCount-xj2QHRw, reason: not valid java name */
    public final void m4374setCountxj2QHRw(short s) {
        this.count = s;
    }

    public final int getTimestamp_us() {
        return this.timestamp_us;
    }

    /* renamed from: setTimestamp_us-WZ4Q5Ns, reason: not valid java name */
    public final void m4378setTimestamp_usWZ4Q5Ns(int i) {
        this.timestamp_us = i;
    }

    public final short getCheckSum() {
        return this.checkSum;
    }

    /* renamed from: setCheckSum-xj2QHRw, reason: not valid java name */
    public final void m4373setCheckSumxj2QHRw(short s) {
        this.checkSum = s;
    }

    public final short getReserved2() {
        return this.reserved2;
    }

    /* renamed from: setReserved2-xj2QHRw, reason: not valid java name */
    public final void m4377setReserved2xj2QHRw(short s) {
        this.reserved2 = s;
    }

    public final Double[] getRanges() {
        return this.ranges;
    }

    public final void setRanges(Double[] dArr) {
        this.ranges = dArr;
    }

    public final UByte[] getIntensities() {
        return this.intensities;
    }

    public final void setIntensities(UByte[] uByteArr) {
        this.intensities = uByteArr;
    }

    public String toString() {
        return "blockNumber=" + UByte.m4563toStringimpl(this.blockNumber) + " flags=" + UByte.m4563toStringimpl(this.flags) + " count=" + UShort.m4796toStringimpl(this.count) + " timestamp_us=" + UInt.m4632toStringimpl(this.timestamp_us) + " checkSum=" + UShort.m4796toStringimpl(this.checkSum);
    }
}
