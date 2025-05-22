package com.pudutech.pd_network.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: storage.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/StorageRequest;", "", "mac", "", "bucket_type", "", "isp", "(Ljava/lang/String;ILjava/lang/String;)V", "getBucket_type", "()I", "getIsp", "()Ljava/lang/String;", "getMac", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class StorageRequest {
    private final int bucket_type;
    private final String isp;
    private final String mac;

    public static /* synthetic */ StorageRequest copy$default(StorageRequest storageRequest, String str, int i, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = storageRequest.mac;
        }
        if ((i2 & 2) != 0) {
            i = storageRequest.bucket_type;
        }
        if ((i2 & 4) != 0) {
            str2 = storageRequest.isp;
        }
        return storageRequest.copy(str, i, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    /* renamed from: component2, reason: from getter */
    public final int getBucket_type() {
        return this.bucket_type;
    }

    /* renamed from: component3, reason: from getter */
    public final String getIsp() {
        return this.isp;
    }

    public final StorageRequest copy(String mac, int bucket_type, String isp) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(isp, "isp");
        return new StorageRequest(mac, bucket_type, isp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StorageRequest)) {
            return false;
        }
        StorageRequest storageRequest = (StorageRequest) other;
        return Intrinsics.areEqual(this.mac, storageRequest.mac) && this.bucket_type == storageRequest.bucket_type && Intrinsics.areEqual(this.isp, storageRequest.isp);
    }

    public int hashCode() {
        String str = this.mac;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + Integer.hashCode(this.bucket_type)) * 31;
        String str2 = this.isp;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "StorageRequest(mac=" + this.mac + ", bucket_type=" + this.bucket_type + ", isp=" + this.isp + ")";
    }

    public StorageRequest(String mac, int i, String isp) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(isp, "isp");
        this.mac = mac;
        this.bucket_type = i;
        this.isp = isp;
    }

    public /* synthetic */ StorageRequest(String str, int i, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, (i2 & 4) != 0 ? "" : str2);
    }

    public final int getBucket_type() {
        return this.bucket_type;
    }

    public final String getIsp() {
        return this.isp;
    }

    public final String getMac() {
        return this.mac;
    }
}
