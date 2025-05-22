package com.pudutech.disinfect.baselib.network.response;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: VoicePackCloud.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u001d\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BC\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003JG\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\t\u0010\"\u001a\u00020#HÖ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'HÖ\u0003J\t\u0010(\u001a\u00020#HÖ\u0001J\t\u0010)\u001a\u00020\u0003HÖ\u0001J\u0019\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020#HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\f\"\u0004\b\u0016\u0010\u000eR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000e¨\u0006/"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/VoicePackCloud;", "Landroid/os/Parcelable;", "name", "", "id", "", "version_code", "update_time", "url", "md5", "(Ljava/lang/String;JJJLjava/lang/String;Ljava/lang/String;)V", "getId", "()J", "setId", "(J)V", "getMd5", "()Ljava/lang/String;", "setMd5", "(Ljava/lang/String;)V", "getName", "setName", "getUpdate_time", "setUpdate_time", "getUrl", "setUrl", "getVersion_code", "setVersion_code", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class VoicePackCloud implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private long id;
    private String md5;
    private String name;
    private long update_time;
    private String url;
    private long version_code;

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            return new VoicePackCloud(in.readString(), in.readLong(), in.readLong(), in.readLong(), in.readString(), in.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new VoicePackCloud[i];
        }
    }

    public VoicePackCloud() {
        this(null, 0L, 0L, 0L, null, null, 63, null);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* renamed from: component3, reason: from getter */
    public final long getVersion_code() {
        return this.version_code;
    }

    /* renamed from: component4, reason: from getter */
    public final long getUpdate_time() {
        return this.update_time;
    }

    /* renamed from: component5, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* renamed from: component6, reason: from getter */
    public final String getMd5() {
        return this.md5;
    }

    public final VoicePackCloud copy(String name, long id, long version_code, long update_time, String url, String md5) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        return new VoicePackCloud(name, id, version_code, update_time, url, md5);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VoicePackCloud)) {
            return false;
        }
        VoicePackCloud voicePackCloud = (VoicePackCloud) other;
        return Intrinsics.areEqual(this.name, voicePackCloud.name) && this.id == voicePackCloud.id && this.version_code == voicePackCloud.version_code && this.update_time == voicePackCloud.update_time && Intrinsics.areEqual(this.url, voicePackCloud.url) && Intrinsics.areEqual(this.md5, voicePackCloud.md5);
    }

    public int hashCode() {
        String str = this.name;
        int hashCode = (((((((str != null ? str.hashCode() : 0) * 31) + Long.hashCode(this.id)) * 31) + Long.hashCode(this.version_code)) * 31) + Long.hashCode(this.update_time)) * 31;
        String str2 = this.url;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.md5;
        return hashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "VoicePackCloud(name=" + this.name + ", id=" + this.id + ", version_code=" + this.version_code + ", update_time=" + this.update_time + ", url=" + this.url + ", md5=" + this.md5 + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.name);
        parcel.writeLong(this.id);
        parcel.writeLong(this.version_code);
        parcel.writeLong(this.update_time);
        parcel.writeString(this.url);
        parcel.writeString(this.md5);
    }

    public VoicePackCloud(String name, long j, long j2, long j3, String str, String md5) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        this.name = name;
        this.id = j;
        this.version_code = j2;
        this.update_time = j3;
        this.url = str;
        this.md5 = md5;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public final long getId() {
        return this.id;
    }

    public final void setId(long j) {
        this.id = j;
    }

    public final long getVersion_code() {
        return this.version_code;
    }

    public final void setVersion_code(long j) {
        this.version_code = j;
    }

    public final long getUpdate_time() {
        return this.update_time;
    }

    public final void setUpdate_time(long j) {
        this.update_time = j;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        this.url = str;
    }

    public /* synthetic */ VoicePackCloud(String str, long j, long j2, long j3, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? -1L : j, (i & 4) != 0 ? 0L : j2, (i & 8) == 0 ? j3 : 0L, (i & 16) != 0 ? "" : str2, (i & 32) == 0 ? str3 : "");
    }

    public final String getMd5() {
        return this.md5;
    }

    public final void setMd5(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.md5 = str;
    }
}
