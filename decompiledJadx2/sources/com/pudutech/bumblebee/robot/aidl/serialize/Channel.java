package com.pudutech.bumblebee.robot.aidl.serialize;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Channel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 $2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0001$B\u0007\b\u0016¢\u0006\u0002\u0010\u0003B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0011\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0000H\u0096\u0002J\b\u0010\u0018\u001a\u00020\bH\u0016J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u0017\u001a\u0004\u0018\u00010\u001bH\u0096\u0002J\b\u0010\u001c\u001a\u00020\bH\u0016J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0004\u001a\u00020\u0005J\b\u0010\u001f\u001a\u00020 H\u0016J\u001a\u0010!\u001a\u00020\u001e2\b\u0010\"\u001a\u0004\u0018\u00010\u00052\u0006\u0010#\u001a\u00020\bH\u0016R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012¨\u0006%"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/aidl/serialize/Channel;", "Landroid/os/Parcelable;", "", "()V", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "channelId", "", "getChannelId", "()I", "setChannelId", "(I)V", "receiveFrequency", "", "getReceiveFrequency", "()F", "setReceiveFrequency", "(F)V", "sendFrequency", "getSendFrequency", "setSendFrequency", "compareTo", "other", "describeContents", "equals", "", "", "hashCode", "readFromParcel", "", "toString", "", "writeToParcel", "dest", "flags", "CREATOR", "RobotAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class Channel implements Parcelable, Comparable<Channel> {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int channelId;
    private float receiveFrequency;
    private float sendFrequency;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final int getChannelId() {
        return this.channelId;
    }

    public final void setChannelId(int i) {
        this.channelId = i;
    }

    public final float getSendFrequency() {
        return this.sendFrequency;
    }

    public final void setSendFrequency(float f) {
        this.sendFrequency = f;
    }

    public final float getReceiveFrequency() {
        return this.receiveFrequency;
    }

    public final void setReceiveFrequency(float f) {
        this.receiveFrequency = f;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        if (dest != null) {
            dest.writeInt(this.channelId);
            dest.writeFloat(this.sendFrequency);
            dest.writeFloat(this.receiveFrequency);
        }
    }

    public Channel() {
    }

    public Channel(Parcel parcel) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        readFromParcel(parcel);
    }

    public final void readFromParcel(Parcel parcel) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        this.channelId = parcel.readInt();
        this.sendFrequency = parcel.readFloat();
        this.receiveFrequency = parcel.readFloat();
    }

    /* compiled from: Channel.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/aidl/serialize/Channel$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/bumblebee/robot/aidl/serialize/Channel;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/bumblebee/robot/aidl/serialize/Channel;", "RobotAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.bumblebee.robot.aidl.serialize.Channel$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes2.dex */
    public static final class Companion implements Parcelable.Creator<Channel> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Channel createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            return new Channel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Channel[] newArray(int size) {
            return new Channel[size];
        }
    }

    public String toString() {
        return "Channel(channelId=" + this.channelId + ", sendFrequency=" + this.sendFrequency + ", receiveFrequency=" + this.receiveFrequency + ')';
    }

    @Override // java.lang.Comparable
    public int compareTo(Channel other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        if (Math.abs(this.sendFrequency - 490.0f) > Math.abs(other.sendFrequency - 490.0f)) {
            return 1;
        }
        return Math.abs(this.sendFrequency - 490.0f) == Math.abs(other.sendFrequency - 490.0f) ? 0 : -1;
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
        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot.aidl.serialize.Channel");
    }

    public int hashCode() {
        return (Float.valueOf(this.sendFrequency).hashCode() * 31) + Float.valueOf(this.receiveFrequency).hashCode();
    }
}
