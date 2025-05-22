package com.pudutech.disinfect.baselib.network.req;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PromotionsReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u000f\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/MapBean;", "", "map_name", "", "point_info", "is_use", "", "mode", "", "(Ljava/lang/String;Ljava/lang/String;I[I)V", "()I", "set_use", "(I)V", "getMap_name", "()Ljava/lang/String;", "setMap_name", "(Ljava/lang/String;)V", "getMode", "()[I", "setMode", "([I)V", "getPoint_info", "setPoint_info", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MapBean {
    private int is_use;
    private String map_name;
    private int[] mode;
    private String point_info;

    public MapBean(String map_name, String point_info, int i, int[] mode) {
        Intrinsics.checkParameterIsNotNull(map_name, "map_name");
        Intrinsics.checkParameterIsNotNull(point_info, "point_info");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        this.map_name = map_name;
        this.point_info = point_info;
        this.is_use = i;
        this.mode = mode;
    }

    public /* synthetic */ MapBean(String str, String str2, int i, int[] iArr, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i2 & 4) != 0 ? 0 : i, iArr);
    }

    public final String getMap_name() {
        return this.map_name;
    }

    public final int[] getMode() {
        return this.mode;
    }

    public final String getPoint_info() {
        return this.point_info;
    }

    /* renamed from: is_use, reason: from getter */
    public final int getIs_use() {
        return this.is_use;
    }

    public final void setMap_name(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.map_name = str;
    }

    public final void setMode(int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "<set-?>");
        this.mode = iArr;
    }

    public final void setPoint_info(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.point_info = str;
    }

    public final void set_use(int i) {
        this.is_use = i;
    }
}
