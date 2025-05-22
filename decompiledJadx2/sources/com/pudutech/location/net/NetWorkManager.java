package com.pudutech.location.net;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.bean.PdHost;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/* compiled from: NetWorkManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00072\u00020\u0001:\u0002\u0007\bB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/location/net/NetWorkManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getServerApi", "Lcom/pudutech/location/net/NetWorkManager$ServerApi;", "Companion", "ServerApi", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class NetWorkManager {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String SIM_CARD_ACTIVE = "/api/common/sim/card_activate/v1";
    public static final String SIM_CARD_REPORT = "/api/common/sim/report/v1";
    public static final String SIM_CARD_STATUS = "/api/common/sim/check_card_activate/v1";
    private static final String TAG = "NetWorkManager";
    private static boolean isTestServer;

    /* compiled from: NetWorkManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001b\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u0004\u001a\u00020\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u001b\u0010\t\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\nH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/location/net/NetWorkManager$ServerApi;", "", "activateFlowCard", "Lcom/pudutech/location/net/FlowCardRes;", "reqData", "Lcom/pudutech/location/net/FlowCardReq;", "(Lcom/pudutech/location/net/FlowCardReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStatusFlowCard", "Lcom/pudutech/location/net/FlowCardStatusRes;", "reportFlowCard", "Lcom/pudutech/location/net/FlowCardReportReq;", "(Lcom/pudutech/location/net/FlowCardReportReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public interface ServerApi {
        @Headers({PdHost.ROBOT_BIZ})
        @POST(NetWorkManager.SIM_CARD_ACTIVE)
        Object activateFlowCard(@Body FlowCardReq flowCardReq, Continuation<? super FlowCardRes> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST(NetWorkManager.SIM_CARD_STATUS)
        Object getStatusFlowCard(@Body FlowCardReq flowCardReq, Continuation<? super FlowCardStatusRes> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST(NetWorkManager.SIM_CARD_REPORT)
        Object reportFlowCard(@Body FlowCardReportReq flowCardReportReq, Continuation<? super FlowCardRes> continuation);
    }

    /* compiled from: NetWorkManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\n\"\u0004\b\u000b\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/location/net/NetWorkManager$Companion;", "", "()V", "SIM_CARD_ACTIVE", "", "SIM_CARD_REPORT", "SIM_CARD_STATUS", "TAG", "isTestServer", "", "()Z", "setTestServer", "(Z)V", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isTestServer() {
            return NetWorkManager.isTestServer;
        }

        public final void setTestServer(boolean z) {
            NetWorkManager.isTestServer = z;
        }
    }

    public NetWorkManager(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    public final ServerApi getServerApi() {
        Pdlog.m3273d(TAG, "createService  ServerApi");
        return (ServerApi) PdNetworkManager.f10310INSTANCE.createService(ServerApi.class);
    }
}
