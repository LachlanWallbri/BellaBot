package com.pudutech.disinfect.baselib.network.response;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: FileUpdateResponse.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/FileUpdateResponse;", "", "md5", "", "version_code", "", "url", "(Ljava/lang/String;ILjava/lang/String;)V", "getMd5", "()Ljava/lang/String;", "getUrl", "getVersion_code", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class FileUpdateResponse {
    private final String md5;
    private final String url;
    private final int version_code;

    public static /* synthetic */ FileUpdateResponse copy$default(FileUpdateResponse fileUpdateResponse, String str, int i, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = fileUpdateResponse.md5;
        }
        if ((i2 & 2) != 0) {
            i = fileUpdateResponse.version_code;
        }
        if ((i2 & 4) != 0) {
            str2 = fileUpdateResponse.url;
        }
        return fileUpdateResponse.copy(str, i, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMd5() {
        return this.md5;
    }

    /* renamed from: component2, reason: from getter */
    public final int getVersion_code() {
        return this.version_code;
    }

    /* renamed from: component3, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    public final FileUpdateResponse copy(String md5, int version_code, String url) {
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        Intrinsics.checkParameterIsNotNull(url, "url");
        return new FileUpdateResponse(md5, version_code, url);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FileUpdateResponse)) {
            return false;
        }
        FileUpdateResponse fileUpdateResponse = (FileUpdateResponse) other;
        return Intrinsics.areEqual(this.md5, fileUpdateResponse.md5) && this.version_code == fileUpdateResponse.version_code && Intrinsics.areEqual(this.url, fileUpdateResponse.url);
    }

    public int hashCode() {
        String str = this.md5;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + Integer.hashCode(this.version_code)) * 31;
        String str2 = this.url;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "FileUpdateResponse(md5=" + this.md5 + ", version_code=" + this.version_code + ", url=" + this.url + ")";
    }

    public FileUpdateResponse(String md5, int i, String url) {
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        Intrinsics.checkParameterIsNotNull(url, "url");
        this.md5 = md5;
        this.version_code = i;
        this.url = url;
    }

    public final String getMd5() {
        return this.md5;
    }

    public final String getUrl() {
        return this.url;
    }

    public final int getVersion_code() {
        return this.version_code;
    }
}
