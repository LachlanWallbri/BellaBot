package com.pudutech.disinfect.baselib.network.response;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: RobotActiveStatusResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J+\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/ShopBean;", "", "shop_id", "", "name", "", "url", "(ILjava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getShop_id", "()I", "setShop_id", "(I)V", "getUrl", "setUrl", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class ShopBean {
    private String name;
    private int shop_id;
    private String url;

    public static /* synthetic */ ShopBean copy$default(ShopBean shopBean, int i, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = shopBean.shop_id;
        }
        if ((i2 & 2) != 0) {
            str = shopBean.name;
        }
        if ((i2 & 4) != 0) {
            str2 = shopBean.url;
        }
        return shopBean.copy(i, str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final int getShop_id() {
        return this.shop_id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component3, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    public final ShopBean copy(int shop_id, String name, String url) {
        return new ShopBean(shop_id, name, url);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ShopBean)) {
            return false;
        }
        ShopBean shopBean = (ShopBean) other;
        return this.shop_id == shopBean.shop_id && Intrinsics.areEqual(this.name, shopBean.name) && Intrinsics.areEqual(this.url, shopBean.url);
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.shop_id) * 31;
        String str = this.name;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.url;
        return hashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "ShopBean(shop_id=" + this.shop_id + ", name=" + this.name + ", url=" + this.url + ")";
    }

    public ShopBean(int i, String str, String str2) {
        this.shop_id = i;
        this.name = str;
        this.url = str2;
    }

    public final String getName() {
        return this.name;
    }

    public final int getShop_id() {
        return this.shop_id;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final void setShop_id(int i) {
        this.shop_id = i;
    }

    public final void setUrl(String str) {
        this.url = str;
    }
}
