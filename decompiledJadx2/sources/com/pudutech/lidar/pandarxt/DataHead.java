package com.pudutech.lidar.pandarxt;

import com.google.common.base.Ascii;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: BaseConfigOfPandarXT.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B8\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0011\u0010\u0012\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0011\u0010\u0013\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0011\u0010\u0014\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0011\u0010\u0015\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0011\u0010\u0016\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0011\u0010\u0017\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000bJO\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\r\u0010\u000bR\u0016\u0010\u0006\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000e\u0010\u000bR\u0016\u0010\u0005\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000f\u0010\u000bR\u0016\u0010\u0007\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0010\u0010\u000bR\u0016\u0010\b\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0011\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, m3961d2 = {"Lcom/pudutech/lidar/pandarxt/DataHead;", "", "channelNum", "Lkotlin/UByte;", "blockNum", "firstBlockReturn", "disUnit", "returnNumber", "udpSeq", "(BBBBBBLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getBlockNum", "()B", "B", "getChannelNum", "getDisUnit", "getFirstBlockReturn", "getReturnNumber", "getUdpSeq", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "copy-BUD_dEw", "(BBBBBB)Lcom/pudutech/lidar/pandarxt/DataHead;", "equals", "", "other", "hashCode", "", "toString", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class DataHead {
    private final byte blockNum;
    private final byte channelNum;
    private final byte disUnit;
    private final byte firstBlockReturn;
    private final byte returnNumber;
    private final byte udpSeq;

    /* renamed from: copy-BUD_dEw$default, reason: not valid java name */
    public static /* synthetic */ DataHead m4383copyBUD_dEw$default(DataHead dataHead, byte b, byte b2, byte b3, byte b4, byte b5, byte b6, int i, Object obj) {
        if ((i & 1) != 0) {
            b = dataHead.channelNum;
        }
        if ((i & 2) != 0) {
            b2 = dataHead.blockNum;
        }
        byte b7 = b2;
        if ((i & 4) != 0) {
            b3 = dataHead.firstBlockReturn;
        }
        byte b8 = b3;
        if ((i & 8) != 0) {
            b4 = dataHead.disUnit;
        }
        byte b9 = b4;
        if ((i & 16) != 0) {
            b5 = dataHead.returnNumber;
        }
        byte b10 = b5;
        if ((i & 32) != 0) {
            b6 = dataHead.udpSeq;
        }
        return dataHead.m4384copyBUD_dEw(b, b7, b8, b9, b10, b6);
    }

    /* renamed from: component1, reason: from getter */
    public final byte getChannelNum() {
        return this.channelNum;
    }

    /* renamed from: component2, reason: from getter */
    public final byte getBlockNum() {
        return this.blockNum;
    }

    /* renamed from: component3, reason: from getter */
    public final byte getFirstBlockReturn() {
        return this.firstBlockReturn;
    }

    /* renamed from: component4, reason: from getter */
    public final byte getDisUnit() {
        return this.disUnit;
    }

    /* renamed from: component5, reason: from getter */
    public final byte getReturnNumber() {
        return this.returnNumber;
    }

    /* renamed from: component6, reason: from getter */
    public final byte getUdpSeq() {
        return this.udpSeq;
    }

    /* renamed from: copy-BUD_dEw, reason: not valid java name */
    public final DataHead m4384copyBUD_dEw(byte channelNum, byte blockNum, byte firstBlockReturn, byte disUnit, byte returnNumber, byte udpSeq) {
        return new DataHead(channelNum, blockNum, firstBlockReturn, disUnit, returnNumber, udpSeq);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DataHead)) {
            return false;
        }
        DataHead dataHead = (DataHead) other;
        return this.channelNum == dataHead.channelNum && this.blockNum == dataHead.blockNum && this.firstBlockReturn == dataHead.firstBlockReturn && this.disUnit == dataHead.disUnit && this.returnNumber == dataHead.returnNumber && this.udpSeq == dataHead.udpSeq;
    }

    public int hashCode() {
        return (((((((((this.channelNum * Ascii.f1926US) + this.blockNum) * 31) + this.firstBlockReturn) * 31) + this.disUnit) * 31) + this.returnNumber) * 31) + this.udpSeq;
    }

    public String toString() {
        return "DataHead(channelNum=" + UByte.m4563toStringimpl(this.channelNum) + ", blockNum=" + UByte.m4563toStringimpl(this.blockNum) + ", firstBlockReturn=" + UByte.m4563toStringimpl(this.firstBlockReturn) + ", disUnit=" + UByte.m4563toStringimpl(this.disUnit) + ", returnNumber=" + UByte.m4563toStringimpl(this.returnNumber) + ", udpSeq=" + UByte.m4563toStringimpl(this.udpSeq) + ")";
    }

    private DataHead(byte b, byte b2, byte b3, byte b4, byte b5, byte b6) {
        this.channelNum = b;
        this.blockNum = b2;
        this.firstBlockReturn = b3;
        this.disUnit = b4;
        this.returnNumber = b5;
        this.udpSeq = b6;
    }

    public /* synthetic */ DataHead(byte b, byte b2, byte b3, byte b4, byte b5, byte b6, DefaultConstructorMarker defaultConstructorMarker) {
        this(b, b2, b3, b4, b5, b6);
    }

    public final byte getChannelNum() {
        return this.channelNum;
    }

    public final byte getBlockNum() {
        return this.blockNum;
    }

    public final byte getFirstBlockReturn() {
        return this.firstBlockReturn;
    }

    public final byte getDisUnit() {
        return this.disUnit;
    }

    public final byte getReturnNumber() {
        return this.returnNumber;
    }

    public final byte getUdpSeq() {
        return this.udpSeq;
    }
}
