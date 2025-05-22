package com.pudutech.pd_network.report;

import com.aliyun.alink.linksdk.tmp.api.DevFoundOutputParams;
import com.pudutech.pd_network.bean.GatewayName;
import kotlin.Metadata;

/* compiled from: IReportClient.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H&J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H&J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H&¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/pd_network/report/IReportClient;", "", "report", "", DevFoundOutputParams.PARAMS_GATEWAY_NAME, "Lcom/pudutech/pd_network/bean/GatewayName;", "apiStr", "", "data", "url", "reportOrThrown", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface IReportClient {
    void report(GatewayName gatewayName, String apiStr, String data);

    void report(String url, String data);

    void reportOrThrown(String url, String data);
}
