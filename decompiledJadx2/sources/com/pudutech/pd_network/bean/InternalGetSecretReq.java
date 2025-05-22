package com.pudutech.pd_network.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: verify.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0080\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/InternalGetSecretReq;", "", "pid", "", "mac", "key_type", "", "(Ljava/lang/String;Ljava/lang/String;I)V", "getKey_type", "()I", "getMac", "()Ljava/lang/String;", "getPid", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class InternalGetSecretReq {
    private final int key_type;
    private final String mac;
    private final String pid;

    public static /* synthetic */ InternalGetSecretReq copy$default(InternalGetSecretReq internalGetSecretReq, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = internalGetSecretReq.pid;
        }
        if ((i2 & 2) != 0) {
            str2 = internalGetSecretReq.mac;
        }
        if ((i2 & 4) != 0) {
            i = internalGetSecretReq.key_type;
        }
        return internalGetSecretReq.copy(str, str2, i);
    }

    /* renamed from: component1, reason: from getter */
    public final String getPid() {
        return this.pid;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    /* renamed from: component3, reason: from getter */
    public final int getKey_type() {
        return this.key_type;
    }

    public final InternalGetSecretReq copy(String pid, String mac, int key_type) {
        Intrinsics.checkParameterIsNotNull(pid, "pid");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        return new InternalGetSecretReq(pid, mac, key_type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InternalGetSecretReq)) {
            return false;
        }
        InternalGetSecretReq internalGetSecretReq = (InternalGetSecretReq) other;
        return Intrinsics.areEqual(this.pid, internalGetSecretReq.pid) && Intrinsics.areEqual(this.mac, internalGetSecretReq.mac) && this.key_type == internalGetSecretReq.key_type;
    }

    public int hashCode() {
        String str = this.pid;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.mac;
        return ((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + Integer.hashCode(this.key_type);
    }

    public String toString() {
        return "InternalGetSecretReq(pid=" + this.pid + ", mac=" + this.mac + ", key_type=" + this.key_type + ")";
    }

    public InternalGetSecretReq(String pid, String mac, int i) {
        Intrinsics.checkParameterIsNotNull(pid, "pid");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        this.pid = pid;
        this.mac = mac;
        this.key_type = i;
    }

    public final int getKey_type() {
        return this.key_type;
    }

    public final String getMac() {
        return this.mac;
    }

    public final String getPid() {
        return this.pid;
    }
}
