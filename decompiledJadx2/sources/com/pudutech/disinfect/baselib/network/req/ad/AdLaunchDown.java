package com.pudutech.disinfect.baselib.network.req.ad;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: AdLaunchDown.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/ad/AdLaunchDown;", "", "can_download", "", "(I)V", "getCan_download", "()I", "setCan_download", "toString", "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class AdLaunchDown {
    private int can_download;

    public AdLaunchDown() {
        this(0, 1, null);
    }

    public AdLaunchDown(int i) {
        this.can_download = i;
    }

    public /* synthetic */ AdLaunchDown(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 1 : i);
    }

    public final int getCan_download() {
        return this.can_download;
    }

    public final void setCan_download(int i) {
        this.can_download = i;
    }

    public String toString() {
        return "AdLaunchDown(can_down = '" + this.can_download + "')";
    }
}
