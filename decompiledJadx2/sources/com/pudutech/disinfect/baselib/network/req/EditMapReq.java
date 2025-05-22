package com.pudutech.disinfect.baselib.network.req;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PromotionsReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/EditMapReq;", "", "shop_id", "", "map_name", "", "point_info", "(ILjava/lang/String;Ljava/lang/String;)V", "getMap_name", "()Ljava/lang/String;", "setMap_name", "(Ljava/lang/String;)V", "getPoint_info", "setPoint_info", "getShop_id", "()I", "setShop_id", "(I)V", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class EditMapReq {
    private String map_name;
    private String point_info;
    private int shop_id;

    public EditMapReq(int i, String map_name, String point_info) {
        Intrinsics.checkParameterIsNotNull(map_name, "map_name");
        Intrinsics.checkParameterIsNotNull(point_info, "point_info");
        this.shop_id = i;
        this.map_name = map_name;
        this.point_info = point_info;
    }

    public final String getMap_name() {
        return this.map_name;
    }

    public final String getPoint_info() {
        return this.point_info;
    }

    public final int getShop_id() {
        return this.shop_id;
    }

    public final void setMap_name(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.map_name = str;
    }

    public final void setPoint_info(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.point_info = str;
    }

    public final void setShop_id(int i) {
        this.shop_id = i;
    }
}
