package com.pudutech.disinfect.baselib.network.response.ad;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdverListResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006¢\u0006\u0002\u0010\tJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J;\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006HÆ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eHÖ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J\t\u0010 \u001a\u00020\u0006HÖ\u0001J\u0019\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000b¨\u0006&"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/ad/Material;", "Landroid/os/Parcelable;", "size", "", "type", "url", "", "md5", "file_path", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFile_path", "()Ljava/lang/String;", "setFile_path", "(Ljava/lang/String;)V", "getMd5", "setMd5", "getSize", "()I", "getType", "getUrl", "component1", "component2", "component3", "component4", "component5", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class Material implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private String file_path;
    private String md5;
    private final int size;
    private final int type;
    private final String url;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            return new Material(in.readInt(), in.readInt(), in.readString(), in.readString(), in.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new Material[i];
        }
    }

    public static /* synthetic */ Material copy$default(Material material, int i, int i2, String str, String str2, String str3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = material.size;
        }
        if ((i3 & 2) != 0) {
            i2 = material.type;
        }
        int i4 = i2;
        if ((i3 & 4) != 0) {
            str = material.url;
        }
        String str4 = str;
        if ((i3 & 8) != 0) {
            str2 = material.md5;
        }
        String str5 = str2;
        if ((i3 & 16) != 0) {
            str3 = material.file_path;
        }
        return material.copy(i, i4, str4, str5, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final int getSize() {
        return this.size;
    }

    /* renamed from: component2, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* renamed from: component3, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* renamed from: component4, reason: from getter */
    public final String getMd5() {
        return this.md5;
    }

    /* renamed from: component5, reason: from getter */
    public final String getFile_path() {
        return this.file_path;
    }

    public final Material copy(int size, int type, String url, String md5, String file_path) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        Intrinsics.checkParameterIsNotNull(file_path, "file_path");
        return new Material(size, type, url, md5, file_path);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Material)) {
            return false;
        }
        Material material = (Material) other;
        return this.size == material.size && this.type == material.type && Intrinsics.areEqual(this.url, material.url) && Intrinsics.areEqual(this.md5, material.md5) && Intrinsics.areEqual(this.file_path, material.file_path);
    }

    public int hashCode() {
        int hashCode = ((Integer.hashCode(this.size) * 31) + Integer.hashCode(this.type)) * 31;
        String str = this.url;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.md5;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.file_path;
        return hashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "Material(size=" + this.size + ", type=" + this.type + ", url=" + this.url + ", md5=" + this.md5 + ", file_path=" + this.file_path + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeInt(this.size);
        parcel.writeInt(this.type);
        parcel.writeString(this.url);
        parcel.writeString(this.md5);
        parcel.writeString(this.file_path);
    }

    public Material(int i, int i2, String url, String md5, String file_path) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        Intrinsics.checkParameterIsNotNull(file_path, "file_path");
        this.size = i;
        this.type = i2;
        this.url = url;
        this.md5 = md5;
        this.file_path = file_path;
    }

    public final int getSize() {
        return this.size;
    }

    public final int getType() {
        return this.type;
    }

    public final String getUrl() {
        return this.url;
    }

    public final String getMd5() {
        return this.md5;
    }

    public final void setMd5(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.md5 = str;
    }

    public /* synthetic */ Material(int i, int i2, String str, String str2, String str3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, str, (i3 & 8) != 0 ? "" : str2, (i3 & 16) != 0 ? "" : str3);
    }

    public final String getFile_path() {
        return this.file_path;
    }

    public final void setFile_path(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.file_path = str;
    }
}
