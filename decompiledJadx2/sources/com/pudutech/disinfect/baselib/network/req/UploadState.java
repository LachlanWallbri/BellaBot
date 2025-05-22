package com.pudutech.disinfect.baselib.network.req;

import kotlin.Metadata;

/* compiled from: PromotionsReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/UploadState;", "", "shopId", "", "status", "(II)V", "getShopId", "()I", "setShopId", "(I)V", "getStatus", "setStatus", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class UploadState {
    private int shopId;
    private int status;

    public UploadState(int i, int i2) {
        this.shopId = i;
        this.status = i2;
    }

    public final int getShopId() {
        return this.shopId;
    }

    public final int getStatus() {
        return this.status;
    }

    public final void setShopId(int i) {
        this.shopId = i;
    }

    public final void setStatus(int i) {
        this.status = i;
    }
}
