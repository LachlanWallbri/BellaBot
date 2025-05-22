package com.pudu.library.loracall;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SendMsgType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J'\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0014\u0010\t\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u000e\u0010\r\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0010¨\u0006\u001b"}, m3961d2 = {"Lcom/pudu/library/loracall/RespondCallMsg;", "Lcom/pudu/library/loracall/BaseMsg;", "devAddr", "", "isSuc", "", "distance", "", "([BZI)V", "data", "getData", "()[B", "getDevAddr", "dis", "getDistance", "()I", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "toString", "", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class RespondCallMsg extends BaseMsg {
    private final byte[] data;
    private final byte[] devAddr;
    private final byte[] dis;
    private final int distance;
    private final boolean isSuc;

    public static /* synthetic */ RespondCallMsg copy$default(RespondCallMsg respondCallMsg, byte[] bArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            bArr = respondCallMsg.devAddr;
        }
        if ((i2 & 2) != 0) {
            z = respondCallMsg.isSuc;
        }
        if ((i2 & 4) != 0) {
            i = respondCallMsg.distance;
        }
        return respondCallMsg.copy(bArr, z, i);
    }

    /* renamed from: component1, reason: from getter */
    public final byte[] getDevAddr() {
        return this.devAddr;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsSuc() {
        return this.isSuc;
    }

    /* renamed from: component3, reason: from getter */
    public final int getDistance() {
        return this.distance;
    }

    public final RespondCallMsg copy(byte[] devAddr, boolean isSuc, int distance) {
        Intrinsics.checkParameterIsNotNull(devAddr, "devAddr");
        return new RespondCallMsg(devAddr, isSuc, distance);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RespondCallMsg)) {
            return false;
        }
        RespondCallMsg respondCallMsg = (RespondCallMsg) other;
        return Intrinsics.areEqual(this.devAddr, respondCallMsg.devAddr) && this.isSuc == respondCallMsg.isSuc && this.distance == respondCallMsg.distance;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        byte[] bArr = this.devAddr;
        int hashCode = (bArr != null ? Arrays.hashCode(bArr) : 0) * 31;
        boolean z = this.isSuc;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return ((hashCode + i) * 31) + Integer.hashCode(this.distance);
    }

    public String toString() {
        return "RespondCallMsg(devAddr=" + Arrays.toString(this.devAddr) + ", isSuc=" + this.isSuc + ", distance=" + this.distance + ")";
    }

    public final byte[] getDevAddr() {
        return this.devAddr;
    }

    public final int getDistance() {
        return this.distance;
    }

    public final boolean isSuc() {
        return this.isSuc;
    }

    public RespondCallMsg(byte[] devAddr, boolean z, int i) {
        Intrinsics.checkParameterIsNotNull(devAddr, "devAddr");
        this.devAddr = devAddr;
        this.isSuc = z;
        this.distance = i;
        this.dis = KotlinUtilsKt.tolBytes(this.distance);
        this.data = ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(new byte[]{(byte) 128, 2}, this.devAddr), this.isSuc ? (byte) 1 : (byte) 0), this.dis[0]), this.dis[1]);
    }

    @Override // com.pudu.library.loracall.BaseMsg
    public byte[] getData() {
        return this.data;
    }
}
