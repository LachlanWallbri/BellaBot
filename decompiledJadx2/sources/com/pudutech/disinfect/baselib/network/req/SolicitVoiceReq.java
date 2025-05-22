package com.pudutech.disinfect.baselib.network.req;

import kotlin.Metadata;

/* compiled from: PromotionsReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/SolicitVoiceReq;", "", "type", "", "shop_id", "(II)V", "getShop_id", "()I", "setShop_id", "(I)V", "getType", "setType", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SolicitVoiceReq {
    private int shop_id;
    private int type;

    public SolicitVoiceReq(int i, int i2) {
        this.type = i;
        this.shop_id = i2;
    }

    public final int getShop_id() {
        return this.shop_id;
    }

    public final int getType() {
        return this.type;
    }

    public final void setShop_id(int i) {
        this.shop_id = i;
    }

    public final void setType(int i) {
        this.type = i;
    }
}
