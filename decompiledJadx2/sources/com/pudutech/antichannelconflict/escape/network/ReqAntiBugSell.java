package com.pudutech.antichannelconflict.escape.network;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NetworkUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/antichannelconflict/escape/network/ReqAntiBugSell;", "", "mac", "", "type", "(Ljava/lang/String;Ljava/lang/String;)V", "getMac", "()Ljava/lang/String;", "getType", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class ReqAntiBugSell {
    private final String mac;
    private final String type;

    public ReqAntiBugSell() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ ReqAntiBugSell copy$default(ReqAntiBugSell reqAntiBugSell, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = reqAntiBugSell.mac;
        }
        if ((i & 2) != 0) {
            str2 = reqAntiBugSell.type;
        }
        return reqAntiBugSell.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    /* renamed from: component2, reason: from getter */
    public final String getType() {
        return this.type;
    }

    public final ReqAntiBugSell copy(String mac, String type) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(type, "type");
        return new ReqAntiBugSell(mac, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ReqAntiBugSell)) {
            return false;
        }
        ReqAntiBugSell reqAntiBugSell = (ReqAntiBugSell) other;
        return Intrinsics.areEqual(this.mac, reqAntiBugSell.mac) && Intrinsics.areEqual(this.type, reqAntiBugSell.type);
    }

    public int hashCode() {
        String str = this.mac;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.type;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "ReqAntiBugSell(mac=" + this.mac + ", type=" + this.type + ")";
    }

    public ReqAntiBugSell(String mac, String type) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.mac = mac;
        this.type = type;
    }

    public /* synthetic */ ReqAntiBugSell(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "lock_state" : str2);
    }

    public final String getMac() {
        return this.mac;
    }

    public final String getType() {
        return this.type;
    }
}
