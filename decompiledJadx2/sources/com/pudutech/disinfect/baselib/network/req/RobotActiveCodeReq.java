package com.pudutech.disinfect.baselib.network.req;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: RobotActiveCodeReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/RobotActiveCodeReq;", "Lcom/pudutech/disinfect/baselib/network/req/BaseReq;", "()V", "data", "Lcom/pudutech/disinfect/baselib/network/req/RobotActiveCodeData;", "getData", "()Lcom/pudutech/disinfect/baselib/network/req/RobotActiveCodeData;", "setData", "(Lcom/pudutech/disinfect/baselib/network/req/RobotActiveCodeData;)V", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RobotActiveCodeReq extends BaseReq {
    private RobotActiveCodeData data;

    public RobotActiveCodeReq() {
        setType("active_code");
    }

    public final RobotActiveCodeData getData() {
        return this.data;
    }

    public final void setData(RobotActiveCodeData robotActiveCodeData) {
        this.data = robotActiveCodeData;
    }
}
