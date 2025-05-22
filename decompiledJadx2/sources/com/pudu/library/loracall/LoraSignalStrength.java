package com.pudu.library.loracall;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReceiveMsgType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR\u001a\u0010\u0010\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\f¨\u0006\u001c"}, m3961d2 = {"Lcom/pudu/library/loracall/LoraSignalStrength;", "Lcom/pudu/library/loracall/ReceiveMsgType;", "data", "", "([B)V", "getData", "()[B", "receiveCount", "", "getReceiveCount", "()Ljava/lang/String;", "setReceiveCount", "(Ljava/lang/String;)V", "receiveCycle", "getReceiveCycle", "setReceiveCycle", "signalStrength", "getSignalStrength", "setSignalStrength", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class LoraSignalStrength extends ReceiveMsgType {
    private final byte[] data;
    private String receiveCount;
    private String receiveCycle;
    private String signalStrength;

    public static /* synthetic */ LoraSignalStrength copy$default(LoraSignalStrength loraSignalStrength, byte[] bArr, int i, Object obj) {
        if ((i & 1) != 0) {
            bArr = loraSignalStrength.data;
        }
        return loraSignalStrength.copy(bArr);
    }

    /* renamed from: component1, reason: from getter */
    public final byte[] getData() {
        return this.data;
    }

    public final LoraSignalStrength copy(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        return new LoraSignalStrength(data);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof LoraSignalStrength) && Intrinsics.areEqual(this.data, ((LoraSignalStrength) other).data);
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
        return "LoraSignalStrength(data=" + Arrays.toString(this.data) + ")";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoraSignalStrength(byte[] data) {
        super(null);
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.data = data;
        this.signalStrength = "";
        this.receiveCycle = "";
        this.receiveCount = "";
        byte[] bArr = this.data;
        if (bArr.length >= 7) {
            this.signalStrength = String.valueOf((int) bArr[0]);
            this.receiveCycle = String.valueOf(KotlinUtilsKt.bytesToIntThree(this.data, 1));
            this.receiveCount = String.valueOf(KotlinUtilsKt.bytesToIntThree(this.data, 4));
        }
    }

    public final byte[] getData() {
        return this.data;
    }

    public final String getSignalStrength() {
        return this.signalStrength;
    }

    public final void setSignalStrength(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.signalStrength = str;
    }

    public final String getReceiveCycle() {
        return this.receiveCycle;
    }

    public final void setReceiveCycle(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.receiveCycle = str;
    }

    public final String getReceiveCount() {
        return this.receiveCount;
    }

    public final void setReceiveCount(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.receiveCount = str;
    }
}
