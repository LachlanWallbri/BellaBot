package com.pudutech.disinfect.baselib.network;

import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.net.SolicitNet;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: NetWorkApiManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$SolicitApiService;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
final class NetWorkApiManager$solicitNet$2 extends Lambda implements Function0<NetWorkApiManager.SolicitApiService> {
    public static final NetWorkApiManager$solicitNet$2 INSTANCE = new NetWorkApiManager$solicitNet$2();

    NetWorkApiManager$solicitNet$2() {
        super(0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final NetWorkApiManager.SolicitApiService invoke() {
        return (NetWorkApiManager.SolicitApiService) new SolicitNet().setTest(NetWorkApiManager.INSTANCE.isTestServer()).getApi(NetWorkApiManager.SolicitApiService.class, NetWorkApiManager.INSTANCE.isTestServer() ? "https://queue-call-test.pudutech.com" : "https://queue-call.pudutech.com");
    }
}
