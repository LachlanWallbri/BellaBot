package com.pudutech.disinfect.baselib.network.req;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReqRobotMapUse.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0003H\u0016R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/ReqRobotMapUse;", "", "mac", "", "cur_map_name", "", "(Ljava/lang/String;Ljava/util/List;)V", "getCur_map_name", "()Ljava/util/List;", "setCur_map_name", "(Ljava/util/List;)V", "getMac", "()Ljava/lang/String;", "setMac", "(Ljava/lang/String;)V", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.disinfect.baselib.network.req.ReqRobotMapUse, reason: from toString */
/* loaded from: classes4.dex */
public final class ReqRobotMac {
    private List<String> cur_map_name;
    private String mac;

    public ReqRobotMac(String mac, List<String> cur_map_name) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(cur_map_name, "cur_map_name");
        this.mac = mac;
        this.cur_map_name = cur_map_name;
    }

    public final List<String> getCur_map_name() {
        return this.cur_map_name;
    }

    public final String getMac() {
        return this.mac;
    }

    public final void setCur_map_name(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.cur_map_name = list;
    }

    public final void setMac(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mac = str;
    }

    public String toString() {
        return "ReqRobotMac(mac='" + this.mac + "' cur_map_name=" + this.cur_map_name + ')';
    }
}
