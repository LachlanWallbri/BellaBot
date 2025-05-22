package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.aliyun.alink.p022h2.api.Constraint;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RegisterResponse.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u001d\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\nHÆ\u0003JE\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\t\u0010%\u001a\u00020\u0005HÖ\u0001J\u0013\u0010&\u001a\u00020\n2\b\u0010'\u001a\u0004\u0018\u00010(HÖ\u0003J\t\u0010)\u001a\u00020\u0005HÖ\u0001J\t\u0010*\u001a\u00020\u0003HÖ\u0001J\u0019\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\r\"\u0004\b\u001d\u0010\u000f¨\u00060"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/RegisterResponse;", "Landroid/os/Parcelable;", "address", "", "port", "", "productKey", "deviceName", Constraint.PARAM_DEVICE_SECRET, "enableTLS", "", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getAddress", "()Ljava/lang/String;", "setAddress", "(Ljava/lang/String;)V", "getDeviceName", "setDeviceName", "getDeviceSecret", "setDeviceSecret", "getEnableTLS", "()Z", "setEnableTLS", "(Z)V", "getPort", "()I", "setPort", "(I)V", "getProductKey", "setProductKey", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "describeContents", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class RegisterResponse implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private String address;
    private String deviceName;
    private String deviceSecret;
    private boolean enableTLS;
    private int port;
    private String productKey;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            return new RegisterResponse(in.readString(), in.readInt(), in.readString(), in.readString(), in.readString(), in.readInt() != 0);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new RegisterResponse[i];
        }
    }

    public static /* synthetic */ RegisterResponse copy$default(RegisterResponse registerResponse, String str, int i, String str2, String str3, String str4, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = registerResponse.address;
        }
        if ((i2 & 2) != 0) {
            i = registerResponse.port;
        }
        int i3 = i;
        if ((i2 & 4) != 0) {
            str2 = registerResponse.productKey;
        }
        String str5 = str2;
        if ((i2 & 8) != 0) {
            str3 = registerResponse.deviceName;
        }
        String str6 = str3;
        if ((i2 & 16) != 0) {
            str4 = registerResponse.deviceSecret;
        }
        String str7 = str4;
        if ((i2 & 32) != 0) {
            z = registerResponse.enableTLS;
        }
        return registerResponse.copy(str, i3, str5, str6, str7, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    /* renamed from: component2, reason: from getter */
    public final int getPort() {
        return this.port;
    }

    /* renamed from: component3, reason: from getter */
    public final String getProductKey() {
        return this.productKey;
    }

    /* renamed from: component4, reason: from getter */
    public final String getDeviceName() {
        return this.deviceName;
    }

    /* renamed from: component5, reason: from getter */
    public final String getDeviceSecret() {
        return this.deviceSecret;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getEnableTLS() {
        return this.enableTLS;
    }

    public final RegisterResponse copy(String address, int port, String productKey, String deviceName, String deviceSecret, boolean enableTLS) {
        Intrinsics.checkParameterIsNotNull(address, "address");
        Intrinsics.checkParameterIsNotNull(productKey, "productKey");
        Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
        Intrinsics.checkParameterIsNotNull(deviceSecret, "deviceSecret");
        return new RegisterResponse(address, port, productKey, deviceName, deviceSecret, enableTLS);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RegisterResponse)) {
            return false;
        }
        RegisterResponse registerResponse = (RegisterResponse) other;
        return Intrinsics.areEqual(this.address, registerResponse.address) && this.port == registerResponse.port && Intrinsics.areEqual(this.productKey, registerResponse.productKey) && Intrinsics.areEqual(this.deviceName, registerResponse.deviceName) && Intrinsics.areEqual(this.deviceSecret, registerResponse.deviceSecret) && this.enableTLS == registerResponse.enableTLS;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.address;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + this.port) * 31;
        String str2 = this.productKey;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.deviceName;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.deviceSecret;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        boolean z = this.enableTLS;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode4 + i;
    }

    public String toString() {
        return "RegisterResponse(address=" + this.address + ", port=" + this.port + ", productKey=" + this.productKey + ", deviceName=" + this.deviceName + ", deviceSecret=" + this.deviceSecret + ", enableTLS=" + this.enableTLS + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.address);
        parcel.writeInt(this.port);
        parcel.writeString(this.productKey);
        parcel.writeString(this.deviceName);
        parcel.writeString(this.deviceSecret);
        parcel.writeInt(this.enableTLS ? 1 : 0);
    }

    public RegisterResponse(String address, int i, String productKey, String deviceName, String deviceSecret, boolean z) {
        Intrinsics.checkParameterIsNotNull(address, "address");
        Intrinsics.checkParameterIsNotNull(productKey, "productKey");
        Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
        Intrinsics.checkParameterIsNotNull(deviceSecret, "deviceSecret");
        this.address = address;
        this.port = i;
        this.productKey = productKey;
        this.deviceName = deviceName;
        this.deviceSecret = deviceSecret;
        this.enableTLS = z;
    }

    public final String getAddress() {
        return this.address;
    }

    public final void setAddress(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.address = str;
    }

    public final int getPort() {
        return this.port;
    }

    public final void setPort(int i) {
        this.port = i;
    }

    public final String getProductKey() {
        return this.productKey;
    }

    public final void setProductKey(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.productKey = str;
    }

    public final String getDeviceName() {
        return this.deviceName;
    }

    public final void setDeviceName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.deviceName = str;
    }

    public final String getDeviceSecret() {
        return this.deviceSecret;
    }

    public final void setDeviceSecret(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.deviceSecret = str;
    }

    public final boolean getEnableTLS() {
        return this.enableTLS;
    }

    public final void setEnableTLS(boolean z) {
        this.enableTLS = z;
    }
}
