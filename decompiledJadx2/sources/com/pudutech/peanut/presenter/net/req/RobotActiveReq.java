package com.pudutech.peanut.presenter.net.req;

import kotlin.Metadata;

/* compiled from: RobotActiveReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/net/req/RobotActiveReq;", "Lcom/pudutech/peanut/presenter/net/req/BaseReq;", "()V", "data", "Lcom/pudutech/peanut/presenter/net/req/Data;", "getData", "()Lcom/pudutech/peanut/presenter/net/req/Data;", "setData", "(Lcom/pudutech/peanut/presenter/net/req/Data;)V", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class RobotActiveReq extends BaseReq {
    private Data data;

    public RobotActiveReq() {
        setType("active_code");
    }

    public final Data getData() {
        return this.data;
    }

    public final void setData(Data data) {
        this.data = data;
    }
}
