package com.pudutech.bumblebee.business.ims.lora;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoRaChannel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0003H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannel;", "", "channelId", "", "frequencyGroup", "", "(Ljava/lang/Integer;[I)V", "getChannelId", "()Ljava/lang/Integer;", "setChannelId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getFrequencyGroup", "()[I", "setFrequencyGroup", "([I)V", "equals", "", "other", "hashCode", "toString", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoRaChannel {
    private Integer channelId;
    private int[] frequencyGroup;

    public LoRaChannel(Integer num, int[] iArr) {
        this.channelId = num;
        this.frequencyGroup = iArr;
    }

    public final Integer getChannelId() {
        return this.channelId;
    }

    public final int[] getFrequencyGroup() {
        return this.frequencyGroup;
    }

    public final void setChannelId(Integer num) {
        this.channelId = num;
    }

    public final void setFrequencyGroup(int[] iArr) {
        this.frequencyGroup = iArr;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            return !(Intrinsics.areEqual(this.channelId, ((LoRaChannel) other).channelId) ^ true);
        }
        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.business.ims.lora.LoRaChannel");
    }

    public int hashCode() {
        Integer num = this.channelId;
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("LoRaChannel(channelId=");
        sb.append(this.channelId);
        sb.append(", frequencyGroup=");
        int[] iArr = this.frequencyGroup;
        if (iArr != null) {
            str = Arrays.toString(iArr);
            Intrinsics.checkExpressionValueIsNotNull(str, "java.util.Arrays.toString(this)");
        } else {
            str = null;
        }
        sb.append(str);
        sb.append(')');
        return sb.toString();
    }
}
