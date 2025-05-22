package com.pudutech.disinfect.baselib.network.req;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: RobotActiveStatusReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/RobotActiveStatusReq;", "Lcom/pudutech/disinfect/baselib/network/req/BaseReq;", "()V", "hard_id", "", "getHard_id", "()Ljava/lang/String;", "setHard_id", "(Ljava/lang/String;)V", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RobotActiveStatusReq extends BaseReq {
    private String hard_id = "";

    public final String getHard_id() {
        return this.hard_id;
    }

    public final void setHard_id(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.hard_id = str;
    }
}
