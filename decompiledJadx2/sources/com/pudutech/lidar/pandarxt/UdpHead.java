package com.pudutech.lidar.pandarxt;

import androidx.exifinterface.media.ExifInterface;
import com.aliyun.alink.linksdk.alcs.coap.option.OptionNumberRegistry;
import com.google.common.base.Ascii;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UShort;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: BaseConfigOfPandarXT.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B0\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\bø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0011\u0010\u0013\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0011\u0010\u0014\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0011\u0010\u0015\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0011\u0010\u0016\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0011\u0010\u0017\u001a\u00020\bHÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000bJE\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u0016\u0010\u0007\u001a\u00020\bø\u0001\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0005\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0006\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0010\u0010\u000eR\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0011\u0010\u000eR\u0016\u0010\u0004\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0012\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, m3961d2 = {"Lcom/pudutech/lidar/pandarxt/UdpHead;", "", "packetStartFlag1", "Lkotlin/UByte;", "packetStartFlag2", "majorVersion", "minorVersion", OptionNumberRegistry.Names.Reserved, "Lkotlin/UShort;", "(BBBBSLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getReserved", "()S", ExifInterface.LATITUDE_SOUTH, "getMajorVersion", "()B", "B", "getMinorVersion", "getPacketStartFlag1", "getPacketStartFlag2", "component1", "component2", "component3", "component4", "component5", "copy", "copy-MeL6Z1Y", "(BBBBS)Lcom/pudutech/lidar/pandarxt/UdpHead;", "equals", "", "other", "hashCode", "", "toString", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class UdpHead {
    private final short Reserved;
    private final byte majorVersion;
    private final byte minorVersion;
    private final byte packetStartFlag1;
    private final byte packetStartFlag2;

    /* renamed from: copy-MeL6Z1Y$default, reason: not valid java name */
    public static /* synthetic */ UdpHead m4392copyMeL6Z1Y$default(UdpHead udpHead, byte b, byte b2, byte b3, byte b4, short s, int i, Object obj) {
        if ((i & 1) != 0) {
            b = udpHead.packetStartFlag1;
        }
        if ((i & 2) != 0) {
            b2 = udpHead.packetStartFlag2;
        }
        byte b5 = b2;
        if ((i & 4) != 0) {
            b3 = udpHead.majorVersion;
        }
        byte b6 = b3;
        if ((i & 8) != 0) {
            b4 = udpHead.minorVersion;
        }
        byte b7 = b4;
        if ((i & 16) != 0) {
            s = udpHead.Reserved;
        }
        return udpHead.m4393copyMeL6Z1Y(b, b5, b6, b7, s);
    }

    /* renamed from: component1, reason: from getter */
    public final byte getPacketStartFlag1() {
        return this.packetStartFlag1;
    }

    /* renamed from: component2, reason: from getter */
    public final byte getPacketStartFlag2() {
        return this.packetStartFlag2;
    }

    /* renamed from: component3, reason: from getter */
    public final byte getMajorVersion() {
        return this.majorVersion;
    }

    /* renamed from: component4, reason: from getter */
    public final byte getMinorVersion() {
        return this.minorVersion;
    }

    /* renamed from: component5, reason: from getter */
    public final short getReserved() {
        return this.Reserved;
    }

    /* renamed from: copy-MeL6Z1Y, reason: not valid java name */
    public final UdpHead m4393copyMeL6Z1Y(byte packetStartFlag1, byte packetStartFlag2, byte majorVersion, byte minorVersion, short Reserved) {
        return new UdpHead(packetStartFlag1, packetStartFlag2, majorVersion, minorVersion, Reserved);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UdpHead)) {
            return false;
        }
        UdpHead udpHead = (UdpHead) other;
        return this.packetStartFlag1 == udpHead.packetStartFlag1 && this.packetStartFlag2 == udpHead.packetStartFlag2 && this.majorVersion == udpHead.majorVersion && this.minorVersion == udpHead.minorVersion && this.Reserved == udpHead.Reserved;
    }

    public int hashCode() {
        return (((((((this.packetStartFlag1 * Ascii.f1926US) + this.packetStartFlag2) * 31) + this.majorVersion) * 31) + this.minorVersion) * 31) + this.Reserved;
    }

    public String toString() {
        return "UdpHead(packetStartFlag1=" + UByte.m4563toStringimpl(this.packetStartFlag1) + ", packetStartFlag2=" + UByte.m4563toStringimpl(this.packetStartFlag2) + ", majorVersion=" + UByte.m4563toStringimpl(this.majorVersion) + ", minorVersion=" + UByte.m4563toStringimpl(this.minorVersion) + ", Reserved=" + UShort.m4796toStringimpl(this.Reserved) + ")";
    }

    private UdpHead(byte b, byte b2, byte b3, byte b4, short s) {
        this.packetStartFlag1 = b;
        this.packetStartFlag2 = b2;
        this.majorVersion = b3;
        this.minorVersion = b4;
        this.Reserved = s;
    }

    public /* synthetic */ UdpHead(byte b, byte b2, byte b3, byte b4, short s, DefaultConstructorMarker defaultConstructorMarker) {
        this(b, b2, b3, b4, s);
    }

    public final byte getPacketStartFlag1() {
        return this.packetStartFlag1;
    }

    public final byte getPacketStartFlag2() {
        return this.packetStartFlag2;
    }

    public final byte getMajorVersion() {
        return this.majorVersion;
    }

    public final byte getMinorVersion() {
        return this.minorVersion;
    }

    public final short getReserved() {
        return this.Reserved;
    }
}
