package com.pudutech.disinfect.baselib.network.req;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PromotionsReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\"\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/MapInfoReq;", "", "shop_id", "", "mac", "", "mapList", "", "Lcom/pudutech/disinfect/baselib/network/req/MapBean;", "(ILjava/lang/String;Ljava/util/List;)V", "getMac", "()Ljava/lang/String;", "setMac", "(Ljava/lang/String;)V", "getMapList", "()Ljava/util/List;", "setMapList", "(Ljava/util/List;)V", "getShop_id", "()I", "setShop_id", "(I)V", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MapInfoReq {
    private String mac;
    private List<MapBean> mapList;
    private int shop_id;

    public MapInfoReq(int i, String mac, List<MapBean> list) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        this.shop_id = i;
        this.mac = mac;
        this.mapList = list;
    }

    public final String getMac() {
        return this.mac;
    }

    public final List<MapBean> getMapList() {
        return this.mapList;
    }

    public final int getShop_id() {
        return this.shop_id;
    }

    public final void setMac(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mac = str;
    }

    public final void setMapList(List<MapBean> list) {
        this.mapList = list;
    }

    public final void setShop_id(int i) {
        this.shop_id = i;
    }
}
