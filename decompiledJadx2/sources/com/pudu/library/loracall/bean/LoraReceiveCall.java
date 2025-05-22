package com.pudu.library.loracall.bean;

import com.pudu.library.loracall.KotlinUtilsKt;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoraReceiveCall.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0003H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, m3961d2 = {"Lcom/pudu/library/loracall/bean/LoraReceiveCall;", "", "type", "", "devAddr", "", "(I[B)V", "getDevAddr", "()[B", "getType", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class LoraReceiveCall {
    private final byte[] devAddr;
    private final int type;

    public static /* synthetic */ LoraReceiveCall copy$default(LoraReceiveCall loraReceiveCall, int i, byte[] bArr, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = loraReceiveCall.type;
        }
        if ((i2 & 2) != 0) {
            bArr = loraReceiveCall.devAddr;
        }
        return loraReceiveCall.copy(i, bArr);
    }

    /* renamed from: component1, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* renamed from: component2, reason: from getter */
    public final byte[] getDevAddr() {
        return this.devAddr;
    }

    public final LoraReceiveCall copy(int type, byte[] devAddr) {
        Intrinsics.checkParameterIsNotNull(devAddr, "devAddr");
        return new LoraReceiveCall(type, devAddr);
    }

    public LoraReceiveCall(int i, byte[] devAddr) {
        Intrinsics.checkParameterIsNotNull(devAddr, "devAddr");
        this.type = i;
        this.devAddr = devAddr;
    }

    public final byte[] getDevAddr() {
        return this.devAddr;
    }

    public final int getType() {
        return this.type;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            LoraReceiveCall loraReceiveCall = (LoraReceiveCall) other;
            return this.type == loraReceiveCall.type && Arrays.equals(this.devAddr, loraReceiveCall.devAddr);
        }
        throw new TypeCastException("null cannot be cast to non-null type com.pudu.library.loracall.bean.LoraReceiveCall");
    }

    public int hashCode() {
        return (this.type * 31) + Arrays.hashCode(this.devAddr);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\ntype:");
        sb.append(this.type);
        sb.append("\n");
        sb.append("类型:");
        int i = this.type;
        sb.append(i != 1 ? i != 2 ? i != 3 ? "未知" : "取消" : "呼叫" : "绑定");
        sb.append("\n");
        sb.append("devAddr:");
        sb.append(KotlinUtilsKt.toHexString(this.devAddr));
        sb.append("\n");
        sb.append("}");
        return sb.toString();
    }
}
