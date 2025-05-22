package com.pudutech.disinfect.baselib.network.response;

import com.iflytek.aiui.AIUIConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: RobotActiveStatusResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\bHÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003JM\u0010\u001f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\f¨\u0006&"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/FeaturedBean;", "", "title", "", AIUIConstant.KEY_CONTENT, "name", "image_url", "price", "", "unit", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DLjava/lang/String;)V", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "getImage_url", "setImage_url", "getName", "getPrice", "()D", "setPrice", "(D)V", "getTitle", "setTitle", "getUnit", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class FeaturedBean {
    private String content;
    private String image_url;
    private final String name;
    private double price;
    private String title;
    private final String unit;

    public static /* synthetic */ FeaturedBean copy$default(FeaturedBean featuredBean, String str, String str2, String str3, String str4, double d, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = featuredBean.title;
        }
        if ((i & 2) != 0) {
            str2 = featuredBean.content;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = featuredBean.name;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = featuredBean.image_url;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            d = featuredBean.price;
        }
        double d2 = d;
        if ((i & 32) != 0) {
            str5 = featuredBean.unit;
        }
        return featuredBean.copy(str, str6, str7, str8, d2, str5);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component2, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component4, reason: from getter */
    public final String getImage_url() {
        return this.image_url;
    }

    /* renamed from: component5, reason: from getter */
    public final double getPrice() {
        return this.price;
    }

    /* renamed from: component6, reason: from getter */
    public final String getUnit() {
        return this.unit;
    }

    public final FeaturedBean copy(String title, String content, String name, String image_url, double price, String unit) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        return new FeaturedBean(title, content, name, image_url, price, unit);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FeaturedBean)) {
            return false;
        }
        FeaturedBean featuredBean = (FeaturedBean) other;
        return Intrinsics.areEqual(this.title, featuredBean.title) && Intrinsics.areEqual(this.content, featuredBean.content) && Intrinsics.areEqual(this.name, featuredBean.name) && Intrinsics.areEqual(this.image_url, featuredBean.image_url) && Double.compare(this.price, featuredBean.price) == 0 && Intrinsics.areEqual(this.unit, featuredBean.unit);
    }

    public int hashCode() {
        String str = this.title;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.content;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.name;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.image_url;
        int hashCode4 = (((hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31) + Double.hashCode(this.price)) * 31;
        String str5 = this.unit;
        return hashCode4 + (str5 != null ? str5.hashCode() : 0);
    }

    public String toString() {
        return "FeaturedBean(title=" + this.title + ", content=" + this.content + ", name=" + this.name + ", image_url=" + this.image_url + ", price=" + this.price + ", unit=" + this.unit + ")";
    }

    public FeaturedBean(String str, String content, String str2, String str3, double d, String str4) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        this.title = str;
        this.content = content;
        this.name = str2;
        this.image_url = str3;
        this.price = d;
        this.unit = str4;
    }

    public /* synthetic */ FeaturedBean(String str, String str2, String str3, String str4, double d, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, d, (i & 32) != 0 ? "" : str5);
    }

    public final String getContent() {
        return this.content;
    }

    public final String getImage_url() {
        return this.image_url;
    }

    public final String getName() {
        return this.name;
    }

    public final double getPrice() {
        return this.price;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getUnit() {
        return this.unit;
    }

    public final void setContent(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.content = str;
    }

    public final void setImage_url(String str) {
        this.image_url = str;
    }

    public final void setPrice(double d) {
        this.price = d;
    }

    public final void setTitle(String str) {
        this.title = str;
    }
}
