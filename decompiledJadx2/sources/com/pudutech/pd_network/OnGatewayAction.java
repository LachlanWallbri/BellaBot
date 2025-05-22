package com.pudutech.pd_network;

import com.loc.C3898x;
import com.pudutech.pd_network.bean.ServiceGatewayConfig;
import kotlin.Metadata;

/* compiled from: interface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/pd_network/OnGatewayAction;", "", "onException", "", C3898x.f4338g, "", "onSuccess", "bean", "Lcom/pudutech/pd_network/bean/ServiceGatewayConfig;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface OnGatewayAction {
    void onException(Throwable e);

    void onSuccess(ServiceGatewayConfig bean);
}
