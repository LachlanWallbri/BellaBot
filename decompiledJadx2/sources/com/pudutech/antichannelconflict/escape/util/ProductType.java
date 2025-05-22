package com.pudutech.antichannelconflict.escape.util;

import kotlin.Metadata;

/* compiled from: ProductType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/antichannelconflict/escape/util/ProductType;", "", "productName", "", "code", "", "(Ljava/lang/String;ILjava/lang/String;I)V", "getCode", "()I", "getProductName", "()Ljava/lang/String;", "PUDUBOT", "HOLABOT", "BELLABOT", "CLEANBOT", "FIREFOX", "KETTYBOT", "SWIFTBOT", "HLS_2", "CC_1", "SH_1", "PG_1", "HLS_VSLAM", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public enum ProductType {
    PUDUBOT("欢乐送", 61),
    HOLABOT("好啦", 63),
    BELLABOT("贝拉", 62),
    CLEANBOT("欢乐消2", 65),
    FIREFOX("闪电匣", 68),
    KETTYBOT("葫芦", 67),
    SWIFTBOT("巧乐送", 70),
    HLS_2("欢乐送2", 73),
    CC_1("出尘", 69),
    SH_1("狮虎", 71),
    PG_1("盘古", 72),
    HLS_VSLAM("欢1 valsam", 75);

    private final int code;
    private final String productName;

    ProductType(String str, int i) {
        this.productName = str;
        this.code = i;
    }

    public final int getCode() {
        return this.code;
    }

    public final String getProductName() {
        return this.productName;
    }
}
