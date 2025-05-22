package com.pudutech.lidar.pandarxt;

import kotlin.Metadata;
import kotlin.UInt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: BaseConfigOfPandarXT.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004J\u0011\u0010\b\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u001d\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/lidar/pandarxt/Extra;", "", "udpSequence", "Lkotlin/UInt;", "(ILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getUdpSequence", "()I", "I", "component1", "copy", "copy-WZ4Q5Ns", "(I)Lcom/pudutech/lidar/pandarxt/Extra;", "equals", "", "other", "hashCode", "", "toString", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class Extra {
    private final int udpSequence;

    /* renamed from: copy-WZ4Q5Ns$default, reason: not valid java name */
    public static /* synthetic */ Extra m4389copyWZ4Q5Ns$default(Extra extra, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = extra.udpSequence;
        }
        return extra.m4390copyWZ4Q5Ns(i);
    }

    /* renamed from: component1, reason: from getter */
    public final int getUdpSequence() {
        return this.udpSequence;
    }

    /* renamed from: copy-WZ4Q5Ns, reason: not valid java name */
    public final Extra m4390copyWZ4Q5Ns(int udpSequence) {
        return new Extra(udpSequence);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof Extra) && this.udpSequence == ((Extra) other).udpSequence;
        }
        return true;
    }

    public int hashCode() {
        return this.udpSequence;
    }

    public String toString() {
        return "Extra(udpSequence=" + UInt.m4632toStringimpl(this.udpSequence) + ")";
    }

    private Extra(int i) {
        this.udpSequence = i;
    }

    public /* synthetic */ Extra(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    public final int getUdpSequence() {
        return this.udpSequence;
    }
}
