package com.pudutech.disinfect.baselib.network.req;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PromotionsReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/InWorkContentReq;", "", "config_id", "", "text", "", "shop_id", "mac", "lang", "(ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getConfig_id", "()I", "setConfig_id", "(I)V", "getLang", "()Ljava/lang/String;", "setLang", "(Ljava/lang/String;)V", "getMac", "setMac", "getShop_id", "setShop_id", "getText", "setText", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class InWorkContentReq {
    private int config_id;
    private String lang;
    private String mac;
    private int shop_id;
    private String text;

    public InWorkContentReq(int i, String text, int i2, String mac, String lang) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(lang, "lang");
        this.config_id = i;
        this.text = text;
        this.shop_id = i2;
        this.mac = mac;
        this.lang = lang;
    }

    public final int getConfig_id() {
        return this.config_id;
    }

    public final String getLang() {
        return this.lang;
    }

    public final String getMac() {
        return this.mac;
    }

    public final int getShop_id() {
        return this.shop_id;
    }

    public final String getText() {
        return this.text;
    }

    public final void setConfig_id(int i) {
        this.config_id = i;
    }

    public final void setLang(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.lang = str;
    }

    public final void setMac(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mac = str;
    }

    public final void setShop_id(int i) {
        this.shop_id = i;
    }

    public final void setText(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.text = str;
    }
}
