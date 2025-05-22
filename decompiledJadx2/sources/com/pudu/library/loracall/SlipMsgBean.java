package com.pudu.library.loracall;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SlipMsgBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B7\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0002\u0010\u000bJ\u0006\u0010'\u001a\u00020(J\b\u0010)\u001a\u00020*H\u0016R\u001f\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001cR\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001a\"\u0004\b!\u0010\u001cR\u001a\u0010\"\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001a\"\u0004\b$\u0010\u001cR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001a\"\u0004\b&\u0010\u001c¨\u0006+"}, m3961d2 = {"Lcom/pudu/library/loracall/SlipMsgBean;", "", "()V", "type", "", "id", "data", "", "callback", "Lkotlin/Function1;", "", "(BB[BLkotlin/jvm/functions/Function1;)V", "getCallback", "()Lkotlin/jvm/functions/Function1;", "count", "", "getCount", "()I", "setCount", "(I)V", "getData", "()[B", "setData", "([B)V", "dest", "getDest", "()B", "setDest", "(B)V", "frameNumber", "getFrameNumber", "setFrameNumber", "getId", "setId", "src", "getSrc", "setSrc", "getType", "setType", "isHeartbeatBroadcast", "", "toString", "", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SlipMsgBean {
    private final Function1<SlipMsgBean, Unit> callback;
    private int count;
    private byte[] data;
    private byte dest;
    private byte frameNumber;
    private byte id;
    private byte src;
    private byte type;

    /* JADX WARN: Multi-variable type inference failed */
    public SlipMsgBean(byte b, byte b2, byte[] data, Function1<? super SlipMsgBean, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.type = b;
        this.id = b2;
        this.data = data;
        this.callback = function1;
        this.src = (byte) 1;
        this.dest = (byte) 16;
        this.count = 3;
    }

    public final byte getType() {
        return this.type;
    }

    public final void setType(byte b) {
        this.type = b;
    }

    public final byte getId() {
        return this.id;
    }

    public final void setId(byte b) {
        this.id = b;
    }

    public final byte[] getData() {
        return this.data;
    }

    public final void setData(byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "<set-?>");
        this.data = bArr;
    }

    public /* synthetic */ SlipMsgBean(byte b, byte b2, byte[] bArr, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(b, b2, (i & 4) != 0 ? new byte[0] : bArr, (i & 8) != 0 ? (Function1) null : function1);
    }

    public final Function1<SlipMsgBean, Unit> getCallback() {
        return this.callback;
    }

    public final byte getFrameNumber() {
        return this.frameNumber;
    }

    public final void setFrameNumber(byte b) {
        this.frameNumber = b;
    }

    public final byte getSrc() {
        return this.src;
    }

    public final void setSrc(byte b) {
        this.src = b;
    }

    public final byte getDest() {
        return this.dest;
    }

    public final void setDest(byte b) {
        this.dest = b;
    }

    public final int getCount() {
        return this.count;
    }

    public final void setCount(int i) {
        this.count = i;
    }

    public SlipMsgBean() {
        this((byte) 0, (byte) 0, null, null, 12, null);
    }

    public String toString() {
        return "{\nframeNumber:" + KotlinUtilsKt.toHexString(this.frameNumber) + "\nsrc:" + KotlinUtilsKt.toHexString(this.src) + "\ndest:" + KotlinUtilsKt.toHexString(this.dest) + "\ntype:" + KotlinUtilsKt.toHexString(this.type) + "\nid:" + KotlinUtilsKt.toHexString(this.id) + "\ndata:0x" + KotlinUtilsKt.toHexString(this.data) + "\ncount:" + this.count + "\n}";
    }

    public final boolean isHeartbeatBroadcast() {
        byte b;
        if (this.type == ((byte) 3) && this.id == ((byte) 32)) {
            byte[] bArr = this.data;
            if (bArr.length == 3 && bArr[0] == (b = (byte) 2) && bArr[1] == b) {
                return true;
            }
        }
        return false;
    }
}
