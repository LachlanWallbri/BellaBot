package com.pudutech.disinfect.baselib.network.req;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PromotionsReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/SolicitReq;", "", "text", "", "shop_id", "", "(Ljava/lang/String;I)V", "getShop_id", "()I", "setShop_id", "(I)V", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SolicitReq {
    private int shop_id;
    private String text;

    public SolicitReq(String text, int i) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        this.text = text;
        this.shop_id = i;
    }

    public final int getShop_id() {
        return this.shop_id;
    }

    public final String getText() {
        return this.text;
    }

    public final void setShop_id(int i) {
        this.shop_id = i;
    }

    public final void setText(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.text = str;
    }
}
