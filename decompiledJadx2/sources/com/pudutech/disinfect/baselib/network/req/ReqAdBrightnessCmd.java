package com.pudutech.disinfect.baselib.network.req;

import kotlin.Metadata;

/* compiled from: ReqAdBrightnessCmd.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/ReqAdBrightnessCmd;", "", "brightness", "", "(I)V", "getBrightness", "()I", "toString", "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ReqAdBrightnessCmd {
    private final int brightness;

    public ReqAdBrightnessCmd(int i) {
        this.brightness = i;
    }

    public final int getBrightness() {
        return this.brightness;
    }

    public String toString() {
        return "ReqAdBrightnessCmd(brightness='" + this.brightness + "')";
    }
}
