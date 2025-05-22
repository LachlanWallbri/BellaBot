package com.pudutech.disinfect.baselib.network.response;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: iot_call.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/G4CodeData;", "", "effect_count", "", "expire", "", "secrete", "", "shop_id", "(IJLjava/lang/String;J)V", "getEffect_count", "()I", "getExpire", "()J", "getSecrete", "()Ljava/lang/String;", "getShop_id", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class G4CodeData {
    private final int effect_count;
    private final long expire;
    private final String secrete;
    private final long shop_id;

    public static /* synthetic */ G4CodeData copy$default(G4CodeData g4CodeData, int i, long j, String str, long j2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = g4CodeData.effect_count;
        }
        if ((i2 & 2) != 0) {
            j = g4CodeData.expire;
        }
        long j3 = j;
        if ((i2 & 4) != 0) {
            str = g4CodeData.secrete;
        }
        String str2 = str;
        if ((i2 & 8) != 0) {
            j2 = g4CodeData.shop_id;
        }
        return g4CodeData.copy(i, j3, str2, j2);
    }

    /* renamed from: component1, reason: from getter */
    public final int getEffect_count() {
        return this.effect_count;
    }

    /* renamed from: component2, reason: from getter */
    public final long getExpire() {
        return this.expire;
    }

    /* renamed from: component3, reason: from getter */
    public final String getSecrete() {
        return this.secrete;
    }

    /* renamed from: component4, reason: from getter */
    public final long getShop_id() {
        return this.shop_id;
    }

    public final G4CodeData copy(int effect_count, long expire, String secrete, long shop_id) {
        Intrinsics.checkParameterIsNotNull(secrete, "secrete");
        return new G4CodeData(effect_count, expire, secrete, shop_id);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof G4CodeData)) {
            return false;
        }
        G4CodeData g4CodeData = (G4CodeData) other;
        return this.effect_count == g4CodeData.effect_count && this.expire == g4CodeData.expire && Intrinsics.areEqual(this.secrete, g4CodeData.secrete) && this.shop_id == g4CodeData.shop_id;
    }

    public int hashCode() {
        int hashCode = ((Integer.hashCode(this.effect_count) * 31) + Long.hashCode(this.expire)) * 31;
        String str = this.secrete;
        return ((hashCode + (str != null ? str.hashCode() : 0)) * 31) + Long.hashCode(this.shop_id);
    }

    public String toString() {
        return "G4CodeData(effect_count=" + this.effect_count + ", expire=" + this.expire + ", secrete=" + this.secrete + ", shop_id=" + this.shop_id + ")";
    }

    public G4CodeData(int i, long j, String secrete, long j2) {
        Intrinsics.checkParameterIsNotNull(secrete, "secrete");
        this.effect_count = i;
        this.expire = j;
        this.secrete = secrete;
        this.shop_id = j2;
    }

    public final int getEffect_count() {
        return this.effect_count;
    }

    public final long getExpire() {
        return this.expire;
    }

    public final String getSecrete() {
        return this.secrete;
    }

    public final long getShop_id() {
        return this.shop_id;
    }
}
