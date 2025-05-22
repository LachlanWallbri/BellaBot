package com.pudutech.disinfect.baselib.network.req;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TtsInfoReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003JD\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0006HÖ\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/TtsInfoReq;", "", "config_id", "", "shop_id", "lang", "", "mac", "product_code", "(Ljava/lang/Integer;ILjava/lang/String;Ljava/lang/String;I)V", "getConfig_id", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getLang", "()Ljava/lang/String;", "getMac", "getProduct_code", "()I", "getShop_id", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Integer;ILjava/lang/String;Ljava/lang/String;I)Lcom/pudutech/disinfect/baselib/network/req/TtsInfoReq;", "equals", "", "other", "hashCode", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class TtsInfoReq {
    private final Integer config_id;
    private final String lang;
    private final String mac;
    private final int product_code;
    private final int shop_id;

    public static /* synthetic */ TtsInfoReq copy$default(TtsInfoReq ttsInfoReq, Integer num, int i, String str, String str2, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            num = ttsInfoReq.config_id;
        }
        if ((i3 & 2) != 0) {
            i = ttsInfoReq.shop_id;
        }
        int i4 = i;
        if ((i3 & 4) != 0) {
            str = ttsInfoReq.lang;
        }
        String str3 = str;
        if ((i3 & 8) != 0) {
            str2 = ttsInfoReq.mac;
        }
        String str4 = str2;
        if ((i3 & 16) != 0) {
            i2 = ttsInfoReq.product_code;
        }
        return ttsInfoReq.copy(num, i4, str3, str4, i2);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getConfig_id() {
        return this.config_id;
    }

    /* renamed from: component2, reason: from getter */
    public final int getShop_id() {
        return this.shop_id;
    }

    /* renamed from: component3, reason: from getter */
    public final String getLang() {
        return this.lang;
    }

    /* renamed from: component4, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    /* renamed from: component5, reason: from getter */
    public final int getProduct_code() {
        return this.product_code;
    }

    public final TtsInfoReq copy(Integer config_id, int shop_id, String lang, String mac, int product_code) {
        Intrinsics.checkParameterIsNotNull(lang, "lang");
        return new TtsInfoReq(config_id, shop_id, lang, mac, product_code);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TtsInfoReq)) {
            return false;
        }
        TtsInfoReq ttsInfoReq = (TtsInfoReq) other;
        return Intrinsics.areEqual(this.config_id, ttsInfoReq.config_id) && this.shop_id == ttsInfoReq.shop_id && Intrinsics.areEqual(this.lang, ttsInfoReq.lang) && Intrinsics.areEqual(this.mac, ttsInfoReq.mac) && this.product_code == ttsInfoReq.product_code;
    }

    public int hashCode() {
        Integer num = this.config_id;
        int hashCode = (((num != null ? num.hashCode() : 0) * 31) + Integer.hashCode(this.shop_id)) * 31;
        String str = this.lang;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.mac;
        return ((hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + Integer.hashCode(this.product_code);
    }

    public String toString() {
        return "TtsInfoReq(config_id=" + this.config_id + ", shop_id=" + this.shop_id + ", lang=" + this.lang + ", mac=" + this.mac + ", product_code=" + this.product_code + ")";
    }

    public TtsInfoReq(Integer num, int i, String lang, String str, int i2) {
        Intrinsics.checkParameterIsNotNull(lang, "lang");
        this.config_id = num;
        this.shop_id = i;
        this.lang = lang;
        this.mac = str;
        this.product_code = i2;
    }

    public final Integer getConfig_id() {
        return this.config_id;
    }

    public final int getShop_id() {
        return this.shop_id;
    }

    public final String getLang() {
        return this.lang;
    }

    public final String getMac() {
        return this.mac;
    }

    public /* synthetic */ TtsInfoReq(Integer num, int i, String str, String str2, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(num, i, str, str2, (i3 & 16) != 0 ? 62 : i2);
    }

    public final int getProduct_code() {
        return this.product_code;
    }
}
