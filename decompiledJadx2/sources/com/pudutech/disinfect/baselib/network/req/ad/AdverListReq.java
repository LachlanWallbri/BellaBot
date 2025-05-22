package com.pudutech.disinfect.baselib.network.req.ad;

import com.google.gson.annotations.SerializedName;
import com.pudutech.disinfect.baselib.util.WifiUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdverListReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/ad/AdverListReq;", "", "macAddress", "", "shopId", "", "kind", "(Ljava/lang/String;II)V", "getKind", "()I", "getMacAddress", "()Ljava/lang/String;", "getShopId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class AdverListReq {

    @SerializedName("kind")
    private final int kind;

    @SerializedName("mac")
    private final String macAddress;

    @SerializedName("shop_id")
    private final int shopId;

    public static /* synthetic */ AdverListReq copy$default(AdverListReq adverListReq, String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = adverListReq.macAddress;
        }
        if ((i3 & 2) != 0) {
            i = adverListReq.shopId;
        }
        if ((i3 & 4) != 0) {
            i2 = adverListReq.kind;
        }
        return adverListReq.copy(str, i, i2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMacAddress() {
        return this.macAddress;
    }

    /* renamed from: component2, reason: from getter */
    public final int getShopId() {
        return this.shopId;
    }

    /* renamed from: component3, reason: from getter */
    public final int getKind() {
        return this.kind;
    }

    public final AdverListReq copy(String macAddress, int shopId, int kind) {
        Intrinsics.checkParameterIsNotNull(macAddress, "macAddress");
        return new AdverListReq(macAddress, shopId, kind);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AdverListReq)) {
            return false;
        }
        AdverListReq adverListReq = (AdverListReq) other;
        return Intrinsics.areEqual(this.macAddress, adverListReq.macAddress) && this.shopId == adverListReq.shopId && this.kind == adverListReq.kind;
    }

    public int hashCode() {
        String str = this.macAddress;
        return ((((str != null ? str.hashCode() : 0) * 31) + Integer.hashCode(this.shopId)) * 31) + Integer.hashCode(this.kind);
    }

    public String toString() {
        return "AdverListReq(macAddress=" + this.macAddress + ", shopId=" + this.shopId + ", kind=" + this.kind + ")";
    }

    public AdverListReq(String macAddress, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(macAddress, "macAddress");
        this.macAddress = macAddress;
        this.shopId = i;
        this.kind = i2;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ AdverListReq(String str, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, (i3 & 4) != 0 ? 3 : i2);
        if ((i3 & 1) != 0 && (str = WifiUtil.INSTANCE.getMac()) == null) {
            str = "";
        }
    }

    public final String getMacAddress() {
        return this.macAddress;
    }

    public final int getShopId() {
        return this.shopId;
    }

    public final int getKind() {
        return this.kind;
    }
}
