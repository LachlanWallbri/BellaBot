package com.pudutech.pd_network.gateway;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.pd_network.PdNetConfig;
import com.pudutech.pd_network.bean.BaseResponse;
import com.pudutech.pd_network.bean.GetListReq;
import com.pudutech.pd_network.bean.ServiceGatewayConfig;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/* compiled from: api.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J+\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\tJ+\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/pd_network/gateway/Api;", "", "getDeviceList", "Lcom/pudutech/pd_network/bean/BaseResponse;", "Lcom/pudutech/pd_network/bean/ServiceGatewayConfig;", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "Lcom/pudutech/pd_network/bean/GetListReq;", "url", "", "(Lcom/pudutech/pd_network/bean/GetListReq;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getList", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface Api {
    @POST
    Object getDeviceList(@Body GetListReq getListReq, @Url String str, Continuation<? super BaseResponse<ServiceGatewayConfig>> continuation);

    @POST
    Object getList(@Body GetListReq getListReq, @Url String str, Continuation<? super BaseResponse<ServiceGatewayConfig>> continuation);

    /* compiled from: api.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ Object getList$default(Api api, GetListReq getListReq, String str, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getList");
            }
            if ((i & 2) != 0) {
                str = PdNetConfig.INSTANCE.getBaseGatewayBean().getHost() + ApiKt.GET_LIST;
            }
            return api.getList(getListReq, str, continuation);
        }

        public static /* synthetic */ Object getDeviceList$default(Api api, GetListReq getListReq, String str, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getDeviceList");
            }
            if ((i & 2) != 0) {
                str = PdNetConfig.INSTANCE.getBaseGatewayBean().getHost() + ApiKt.GET_LIST_DEVICE;
            }
            return api.getDeviceList(getListReq, str, continuation);
        }
    }
}
