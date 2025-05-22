package com.pudutech.bumblebee.robot_ui.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BannerData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/bean/BannerData;", "", "imgUrl", "", "(Ljava/lang/String;)V", "getImgUrl", "()Ljava/lang/String;", "setImgUrl", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final /* data */ class BannerData {
    private String imgUrl;

    public static /* synthetic */ BannerData copy$default(BannerData bannerData, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bannerData.imgUrl;
        }
        return bannerData.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getImgUrl() {
        return this.imgUrl;
    }

    public final BannerData copy(String imgUrl) {
        Intrinsics.checkParameterIsNotNull(imgUrl, "imgUrl");
        return new BannerData(imgUrl);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof BannerData) && Intrinsics.areEqual(this.imgUrl, ((BannerData) other).imgUrl);
        }
        return true;
    }

    public int hashCode() {
        String str = this.imgUrl;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "BannerData(imgUrl=" + this.imgUrl + ")";
    }

    public BannerData(String imgUrl) {
        Intrinsics.checkParameterIsNotNull(imgUrl, "imgUrl");
        this.imgUrl = imgUrl;
    }

    public final String getImgUrl() {
        return this.imgUrl;
    }

    public final void setImgUrl(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.imgUrl = str;
    }
}
