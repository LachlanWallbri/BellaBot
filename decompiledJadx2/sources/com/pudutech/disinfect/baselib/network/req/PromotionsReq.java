package com.pudutech.disinfect.baselib.network.req;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: PromotionsReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/PromotionsReq;", "", TypedValues.Cycle.S_WAVE_OFFSET, "", "limit", "shop_id", "(III)V", "getLimit", "()I", "setLimit", "(I)V", "getOffset", "setOffset", "getShop_id", "setShop_id", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PromotionsReq {
    private int limit;
    private int offset;
    private int shop_id;

    public PromotionsReq(int i, int i2, int i3) {
        this.offset = i;
        this.limit = i2;
        this.shop_id = i3;
    }

    public /* synthetic */ PromotionsReq(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i4 & 2) != 0 ? 20 : i2, i3);
    }

    public final int getLimit() {
        return this.limit;
    }

    public final int getOffset() {
        return this.offset;
    }

    public final int getShop_id() {
        return this.shop_id;
    }

    public final void setLimit(int i) {
        this.limit = i;
    }

    public final void setOffset(int i) {
        this.offset = i;
    }

    public final void setShop_id(int i) {
        this.shop_id = i;
    }
}
