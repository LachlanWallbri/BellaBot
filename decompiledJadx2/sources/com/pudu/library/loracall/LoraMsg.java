package com.pudu.library.loracall;

import com.pudu.library.loracall.bean.LoraReceiveCall;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReceiveMsgType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, m3961d2 = {"Lcom/pudu/library/loracall/LoraMsg;", "Lcom/pudu/library/loracall/ReceiveMsgType;", "data", "", "([B)V", "getData", "()[B", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toLoraReceiveCall", "Lcom/pudu/library/loracall/bean/LoraReceiveCall;", "toString", "", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class LoraMsg extends ReceiveMsgType {
    private final byte[] data;

    public static /* synthetic */ LoraMsg copy$default(LoraMsg loraMsg, byte[] bArr, int i, Object obj) {
        if ((i & 1) != 0) {
            bArr = loraMsg.data;
        }
        return loraMsg.copy(bArr);
    }

    /* renamed from: component1, reason: from getter */
    public final byte[] getData() {
        return this.data;
    }

    public final LoraMsg copy(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        return new LoraMsg(data);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof LoraMsg) && Intrinsics.areEqual(this.data, ((LoraMsg) other).data);
        }
        return true;
    }

    public int hashCode() {
        byte[] bArr = this.data;
        if (bArr != null) {
            return Arrays.hashCode(bArr);
        }
        return 0;
    }

    public String toString() {
        return "LoraMsg(data=" + Arrays.toString(this.data) + ")";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoraMsg(byte[] data) {
        super(null);
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.data = data;
    }

    public final byte[] getData() {
        return this.data;
    }

    public final LoraReceiveCall toLoraReceiveCall() {
        byte[] bArr = this.data;
        if (bArr.length < 6 || bArr[0] != ((byte) 128)) {
            return null;
        }
        return new LoraReceiveCall(bArr[1], ArraysKt.copyOfRange(bArr, 2, bArr.length));
    }
}
