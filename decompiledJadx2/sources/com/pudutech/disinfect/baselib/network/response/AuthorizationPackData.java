package com.pudutech.disinfect.baselib.network.response;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AuthorizationPackResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\t\u0010\u000e\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/AuthorizationPackData;", "Landroid/os/Parcelable;", "name", "", "status", "", "(Ljava/lang/String;I)V", "getName", "()Ljava/lang/String;", "getStatus", "()I", "component1", "component2", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class AuthorizationPackData implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private final String name;
    private final int status;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            return new AuthorizationPackData(in.readString(), in.readInt());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new AuthorizationPackData[i];
        }
    }

    public static /* synthetic */ AuthorizationPackData copy$default(AuthorizationPackData authorizationPackData, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = authorizationPackData.name;
        }
        if ((i2 & 2) != 0) {
            i = authorizationPackData.status;
        }
        return authorizationPackData.copy(str, i);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    public final AuthorizationPackData copy(String name, int status) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new AuthorizationPackData(name, status);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthorizationPackData)) {
            return false;
        }
        AuthorizationPackData authorizationPackData = (AuthorizationPackData) other;
        return Intrinsics.areEqual(this.name, authorizationPackData.name) && this.status == authorizationPackData.status;
    }

    public int hashCode() {
        String str = this.name;
        return ((str != null ? str.hashCode() : 0) * 31) + Integer.hashCode(this.status);
    }

    public String toString() {
        return "AuthorizationPackData(name=" + this.name + ", status=" + this.status + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.name);
        parcel.writeInt(this.status);
    }

    public AuthorizationPackData(String name, int i) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.name = name;
        this.status = i;
    }

    public final String getName() {
        return this.name;
    }

    public final int getStatus() {
        return this.status;
    }
}
