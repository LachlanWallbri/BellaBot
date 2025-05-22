package com.pudutech.pd_network.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: verify.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0080\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/InternalGetTokenReq;", "", "secret", "", "key_type", "", "mac", "pid", "app_type", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getApp_type", "()Ljava/lang/String;", "getKey_type", "()I", "getMac", "getPid", "getSecret", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class InternalGetTokenReq {
    private final String app_type;
    private final int key_type;
    private final String mac;
    private final String pid;
    private final String secret;

    public static /* synthetic */ InternalGetTokenReq copy$default(InternalGetTokenReq internalGetTokenReq, String str, int i, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = internalGetTokenReq.secret;
        }
        if ((i2 & 2) != 0) {
            i = internalGetTokenReq.key_type;
        }
        int i3 = i;
        if ((i2 & 4) != 0) {
            str2 = internalGetTokenReq.mac;
        }
        String str5 = str2;
        if ((i2 & 8) != 0) {
            str3 = internalGetTokenReq.pid;
        }
        String str6 = str3;
        if ((i2 & 16) != 0) {
            str4 = internalGetTokenReq.app_type;
        }
        return internalGetTokenReq.copy(str, i3, str5, str6, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final String getSecret() {
        return this.secret;
    }

    /* renamed from: component2, reason: from getter */
    public final int getKey_type() {
        return this.key_type;
    }

    /* renamed from: component3, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    /* renamed from: component4, reason: from getter */
    public final String getPid() {
        return this.pid;
    }

    /* renamed from: component5, reason: from getter */
    public final String getApp_type() {
        return this.app_type;
    }

    public final InternalGetTokenReq copy(String secret, int key_type, String mac, String pid, String app_type) {
        Intrinsics.checkParameterIsNotNull(secret, "secret");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(pid, "pid");
        Intrinsics.checkParameterIsNotNull(app_type, "app_type");
        return new InternalGetTokenReq(secret, key_type, mac, pid, app_type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InternalGetTokenReq)) {
            return false;
        }
        InternalGetTokenReq internalGetTokenReq = (InternalGetTokenReq) other;
        return Intrinsics.areEqual(this.secret, internalGetTokenReq.secret) && this.key_type == internalGetTokenReq.key_type && Intrinsics.areEqual(this.mac, internalGetTokenReq.mac) && Intrinsics.areEqual(this.pid, internalGetTokenReq.pid) && Intrinsics.areEqual(this.app_type, internalGetTokenReq.app_type);
    }

    public int hashCode() {
        String str = this.secret;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + Integer.hashCode(this.key_type)) * 31;
        String str2 = this.mac;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.pid;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.app_type;
        return hashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "InternalGetTokenReq(secret=" + this.secret + ", key_type=" + this.key_type + ", mac=" + this.mac + ", pid=" + this.pid + ", app_type=" + this.app_type + ")";
    }

    public InternalGetTokenReq(String secret, int i, String mac, String pid, String app_type) {
        Intrinsics.checkParameterIsNotNull(secret, "secret");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(pid, "pid");
        Intrinsics.checkParameterIsNotNull(app_type, "app_type");
        this.secret = secret;
        this.key_type = i;
        this.mac = mac;
        this.pid = pid;
        this.app_type = app_type;
    }

    public final String getSecret() {
        return this.secret;
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

    public final String getApp_type() {
        return this.app_type;
    }
}
