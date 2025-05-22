package com.pudutech.disinfect.baselib.network.response;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: RobotMapResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b)\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0006¢\u0006\u0002\u0010\u000eJ\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0006HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\tHÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0006HÆ\u0003Ji\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\r\u001a\u00020\u0006HÆ\u0001J\t\u00100\u001a\u00020\u0006HÖ\u0001J\u0013\u00101\u001a\u00020\t2\b\u00102\u001a\u0004\u0018\u000103HÖ\u0003J\t\u00104\u001a\u00020\u0006HÖ\u0001J\t\u00105\u001a\u00020\u0003HÖ\u0001J\u0019\u00106\u001a\u0002072\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0013\"\u0004\b\u001d\u0010\u0015R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0013\"\u0004\b\u001f\u0010\u0015R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0013\"\u0004\b!\u0010\u0015R\u001a\u0010\r\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0017\"\u0004\b#\u0010\u0019R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0013\"\u0004\b%\u0010\u0015¨\u0006;"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/RobotMapResp;", "Landroid/os/Parcelable;", "name", "", "url", "lv", "", "md5", "isSetting", "", "progress", "localPath", "state", "status", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "()Z", "setSetting", "(Z)V", "getLocalPath", "()Ljava/lang/String;", "setLocalPath", "(Ljava/lang/String;)V", "getLv", "()I", "setLv", "(I)V", "getMd5", "setMd5", "getName", "setName", "getProgress", "setProgress", "getState", "setState", "getStatus", "setStatus", "getUrl", "setUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class RobotMapResp implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private boolean isSetting;
    private String localPath;
    private int lv;
    private String md5;
    private String name;
    private String progress;
    private String state;
    private int status;
    private String url;

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            return new RobotMapResp(in.readString(), in.readString(), in.readInt(), in.readString(), in.readInt() != 0, in.readString(), in.readString(), in.readString(), in.readInt());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new RobotMapResp[i];
        }
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* renamed from: component3, reason: from getter */
    public final int getLv() {
        return this.lv;
    }

    /* renamed from: component4, reason: from getter */
    public final String getMd5() {
        return this.md5;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getIsSetting() {
        return this.isSetting;
    }

    /* renamed from: component6, reason: from getter */
    public final String getProgress() {
        return this.progress;
    }

    /* renamed from: component7, reason: from getter */
    public final String getLocalPath() {
        return this.localPath;
    }

    /* renamed from: component8, reason: from getter */
    public final String getState() {
        return this.state;
    }

    /* renamed from: component9, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    public final RobotMapResp copy(String name, String url, int lv, String md5, boolean isSetting, String progress, String localPath, String state, int status) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        return new RobotMapResp(name, url, lv, md5, isSetting, progress, localPath, state, status);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RobotMapResp)) {
            return false;
        }
        RobotMapResp robotMapResp = (RobotMapResp) other;
        return Intrinsics.areEqual(this.name, robotMapResp.name) && Intrinsics.areEqual(this.url, robotMapResp.url) && this.lv == robotMapResp.lv && Intrinsics.areEqual(this.md5, robotMapResp.md5) && this.isSetting == robotMapResp.isSetting && Intrinsics.areEqual(this.progress, robotMapResp.progress) && Intrinsics.areEqual(this.localPath, robotMapResp.localPath) && Intrinsics.areEqual(this.state, robotMapResp.state) && this.status == robotMapResp.status;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.name;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.url;
        int hashCode2 = (((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + Integer.hashCode(this.lv)) * 31;
        String str3 = this.md5;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        boolean z = this.isSetting;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode3 + i) * 31;
        String str4 = this.progress;
        int hashCode4 = (i2 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.localPath;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.state;
        return ((hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31) + Integer.hashCode(this.status);
    }

    public String toString() {
        return "RobotMapResp(name=" + this.name + ", url=" + this.url + ", lv=" + this.lv + ", md5=" + this.md5 + ", isSetting=" + this.isSetting + ", progress=" + this.progress + ", localPath=" + this.localPath + ", state=" + this.state + ", status=" + this.status + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.name);
        parcel.writeString(this.url);
        parcel.writeInt(this.lv);
        parcel.writeString(this.md5);
        parcel.writeInt(this.isSetting ? 1 : 0);
        parcel.writeString(this.progress);
        parcel.writeString(this.localPath);
        parcel.writeString(this.state);
        parcel.writeInt(this.status);
    }

    public RobotMapResp(String name, String url, int i, String md5, boolean z, String str, String str2, String str3, int i2) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        this.name = name;
        this.url = url;
        this.lv = i;
        this.md5 = md5;
        this.isSetting = z;
        this.progress = str;
        this.localPath = str2;
        this.state = str3;
        this.status = i2;
    }

    public final int getLv() {
        return this.lv;
    }

    public final String getMd5() {
        return this.md5;
    }

    public final String getName() {
        return this.name;
    }

    public final String getUrl() {
        return this.url;
    }

    public final boolean isSetting() {
        return this.isSetting;
    }

    public final void setLv(int i) {
        this.lv = i;
    }

    public final void setMd5(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.md5 = str;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public final void setSetting(boolean z) {
        this.isSetting = z;
    }

    public final void setUrl(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.url = str;
    }

    public /* synthetic */ RobotMapResp(String str, String str2, int i, String str3, boolean z, String str4, String str5, String str6, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, str3, (i3 & 16) != 0 ? false : z, (i3 & 32) != 0 ? (String) null : str4, (i3 & 64) != 0 ? (String) null : str5, (i3 & 128) != 0 ? "0" : str6, (i3 & 256) != 0 ? 1 : i2);
    }

    public final String getLocalPath() {
        return this.localPath;
    }

    public final String getProgress() {
        return this.progress;
    }

    public final void setLocalPath(String str) {
        this.localPath = str;
    }

    public final void setProgress(String str) {
        this.progress = str;
    }

    public final String getState() {
        return this.state;
    }

    public final void setState(String str) {
        this.state = str;
    }

    public final int getStatus() {
        return this.status;
    }

    public final void setStatus(int i) {
        this.status = i;
    }
}
