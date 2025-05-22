package com.pudutech.lora.library;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: LoRaChannel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\f\u001a\u00020\rH\u0016R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/lora/library/LoRaChannel;", "", "address", "", "channelId", "([B[B)V", "getAddress", "()[B", "setAddress", "([B)V", "getChannelId", "setChannelId", "toString", "", "library_lora_release"}, m3962k = 1, m3963mv = {1, 1, 15})
/* loaded from: classes.dex */
public final class LoRaChannel {
    private byte[] address;
    private byte[] channelId;

    public LoRaChannel(byte[] bArr, byte[] bArr2) {
        this.address = bArr;
        this.channelId = bArr2;
    }

    public final byte[] getAddress() {
        return this.address;
    }

    public final byte[] getChannelId() {
        return this.channelId;
    }

    public final void setAddress(byte[] bArr) {
        this.address = bArr;
    }

    public final void setChannelId(byte[] bArr) {
        this.channelId = bArr;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("LoRaChannel(address=");
        byte[] bArr = this.address;
        String str2 = null;
        if (bArr != null) {
            str = Arrays.toString(bArr);
            Intrinsics.checkExpressionValueIsNotNull(str, "java.util.Arrays.toString(this)");
        } else {
            str = null;
        }
        sb.append(str);
        sb.append(", channelId=");
        byte[] bArr2 = this.channelId;
        if (bArr2 != null) {
            str2 = Arrays.toString(bArr2);
            Intrinsics.checkExpressionValueIsNotNull(str2, "java.util.Arrays.toString(this)");
        }
        sb.append(str2);
        sb.append(')');
        return sb.toString();
    }
}
