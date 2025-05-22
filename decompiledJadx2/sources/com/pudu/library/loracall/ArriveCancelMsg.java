package com.pudu.library.loracall;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SendMsgType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0014\u0010\u0007\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000b¨\u0006\u0016"}, m3961d2 = {"Lcom/pudu/library/loracall/ArriveCancelMsg;", "Lcom/pudu/library/loracall/BaseMsg;", "isArrive", "", "devAddr", "", "(Z[B)V", "data", "getData", "()[B", "getDevAddr", "()Z", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class ArriveCancelMsg extends BaseMsg {
    private final byte[] data;
    private final byte[] devAddr;
    private final boolean isArrive;

    public static /* synthetic */ ArriveCancelMsg copy$default(ArriveCancelMsg arriveCancelMsg, boolean z, byte[] bArr, int i, Object obj) {
        if ((i & 1) != 0) {
            z = arriveCancelMsg.isArrive;
        }
        if ((i & 2) != 0) {
            bArr = arriveCancelMsg.devAddr;
        }
        return arriveCancelMsg.copy(z, bArr);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getIsArrive() {
        return this.isArrive;
    }

    /* renamed from: component2, reason: from getter */
    public final byte[] getDevAddr() {
        return this.devAddr;
    }

    public final ArriveCancelMsg copy(boolean isArrive, byte[] devAddr) {
        Intrinsics.checkParameterIsNotNull(devAddr, "devAddr");
        return new ArriveCancelMsg(isArrive, devAddr);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ArriveCancelMsg)) {
            return false;
        }
        ArriveCancelMsg arriveCancelMsg = (ArriveCancelMsg) other;
        return this.isArrive == arriveCancelMsg.isArrive && Intrinsics.areEqual(this.devAddr, arriveCancelMsg.devAddr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    public int hashCode() {
        boolean z = this.isArrive;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        byte[] bArr = this.devAddr;
        return i + (bArr != null ? Arrays.hashCode(bArr) : 0);
    }

    public String toString() {
        return "ArriveCancelMsg(isArrive=" + this.isArrive + ", devAddr=" + Arrays.toString(this.devAddr) + ")";
    }

    public ArriveCancelMsg(boolean z, byte[] devAddr) {
        Intrinsics.checkParameterIsNotNull(devAddr, "devAddr");
        this.isArrive = z;
        this.devAddr = devAddr;
        byte[] bArr = new byte[2];
        bArr[0] = (byte) 128;
        bArr[1] = this.isArrive ? (byte) 3 : (byte) 4;
        this.data = ArraysKt.plus(bArr, this.devAddr);
    }

    public final byte[] getDevAddr() {
        return this.devAddr;
    }

    public final boolean isArrive() {
        return this.isArrive;
    }

    @Override // com.pudu.library.loracall.BaseMsg
    public byte[] getData() {
        return this.data;
    }
}
