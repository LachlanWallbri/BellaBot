package com.pudutech.bumblebee.business.ims.lora;

import com.pudutech.bumblebee.business.ims.config.LoRaChannelConfig;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoRaChannelManager2.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0011\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0000H\u0096\u0002J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0010\u001a\u0004\u0018\u00010\u0016H\u0096\u0002J\b\u0010\u0017\u001a\u00020\u0007H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/lora/Channel;", "", "sendFrequency", "", "receiveFrequency", "(FF)V", "channelId", "", "getChannelId", "()I", "setChannelId", "(I)V", "getReceiveFrequency", "()F", "getSendFrequency", "compareTo", "other", "component1", "component2", "copy", "equals", "", "", "hashCode", "toString", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class Channel implements Comparable<Channel> {
    private int channelId;
    private final float receiveFrequency;
    private final float sendFrequency;

    public static /* synthetic */ Channel copy$default(Channel channel, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = channel.sendFrequency;
        }
        if ((i & 2) != 0) {
            f2 = channel.receiveFrequency;
        }
        return channel.copy(f, f2);
    }

    /* renamed from: component1, reason: from getter */
    public final float getSendFrequency() {
        return this.sendFrequency;
    }

    /* renamed from: component2, reason: from getter */
    public final float getReceiveFrequency() {
        return this.receiveFrequency;
    }

    public final Channel copy(float sendFrequency, float receiveFrequency) {
        return new Channel(sendFrequency, receiveFrequency);
    }

    public Channel(float f, float f2) {
        this.sendFrequency = f;
        this.receiveFrequency = f2;
    }

    public final float getReceiveFrequency() {
        return this.receiveFrequency;
    }

    public final float getSendFrequency() {
        return this.sendFrequency;
    }

    public final int getChannelId() {
        return this.channelId;
    }

    public final void setChannelId(int i) {
        this.channelId = i;
    }

    public String toString() {
        return "Channel(channelId=" + this.channelId + ", sendFrequency=" + this.sendFrequency + ", receiveFrequency=" + this.receiveFrequency + ')';
    }

    @Override // java.lang.Comparable
    public int compareTo(Channel other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        if (Math.abs(this.sendFrequency - LoRaChannelConfig.INSTANCE.getDefaultFrequency().floatValue()) > Math.abs(other.sendFrequency - LoRaChannelConfig.INSTANCE.getDefaultFrequency().floatValue())) {
            return 1;
        }
        return Math.abs(this.sendFrequency - LoRaChannelConfig.INSTANCE.getDefaultFrequency().floatValue()) == Math.abs(other.sendFrequency - LoRaChannelConfig.INSTANCE.getDefaultFrequency().floatValue()) ? 0 : -1;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            Channel channel = (Channel) other;
            return this.sendFrequency == channel.sendFrequency && this.receiveFrequency == channel.receiveFrequency;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.business.ims.lora.Channel");
    }

    public int hashCode() {
        return (Float.valueOf(this.sendFrequency).hashCode() * 31) + Float.valueOf(this.receiveFrequency).hashCode();
    }
}
