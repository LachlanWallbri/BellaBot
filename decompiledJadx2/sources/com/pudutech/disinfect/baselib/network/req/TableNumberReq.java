package com.pudutech.disinfect.baselib.network.req;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: PromotionsReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/TableNumberReq;", "Lcom/pudutech/disinfect/baselib/network/req/BaseReq;", "()V", "group_code", "", "getGroup_code", "()Ljava/lang/String;", "setGroup_code", "(Ljava/lang/String;)V", "shop_id", "", "getShop_id", "()I", "setShop_id", "(I)V", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TableNumberReq extends BaseReq {
    private String group_code = "";
    private int shop_id;

    public final int getShop_id() {
        return this.shop_id;
    }

    public final void setShop_id(int i) {
        this.shop_id = i;
    }

    public final String getGroup_code() {
        return this.group_code;
    }

    public final void setGroup_code(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.group_code = str;
    }
}
