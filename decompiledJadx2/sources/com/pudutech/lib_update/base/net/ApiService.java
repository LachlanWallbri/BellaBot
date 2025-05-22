package com.pudutech.lib_update.base.net;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.lib_update.module.model.CheckUpdateRequestParams;
import com.pudutech.lib_update.module.model.SwitchVersionResult;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.pd_network.bean.BaseResponse;
import com.pudutech.pd_network.bean.PdHost;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/* compiled from: ApiService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0007J!\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\tH§@ø\u0001\u0000¢\u0006\u0002\u0010\nJ!\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\rH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/lib_update/base/net/ApiService;", "", "checkSystemUpdate", "Lcom/pudutech/pd_network/bean/BaseResponse;", "Lcom/pudutech/lib_update/module/model/VerionResult;", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "Lcom/pudutech/lib_update/base/net/SystemUpdateReq;", "(Lcom/pudutech/lib_update/base/net/SystemUpdateReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkUpdate", "Lcom/pudutech/lib_update/base/net/AppUpdateReq;", "(Lcom/pudutech/lib_update/base/net/AppUpdateReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "switchVersion", "Lcom/pudutech/lib_update/module/model/SwitchVersionResult;", "Lcom/pudutech/lib_update/module/model/CheckUpdateRequestParams;", "(Lcom/pudutech/lib_update/module/model/CheckUpdateRequestParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public interface ApiService {
    @Headers({PdHost.OTA_URL})
    @POST("/api/common/ota/client/get_update/v1")
    Object checkSystemUpdate(@Body SystemUpdateReq systemUpdateReq, Continuation<? super BaseResponse<VerionResult>> continuation);

    @Headers({PdHost.OTA_URL})
    @POST("/api/common/ota/client/get_update/v1")
    Object checkUpdate(@Body AppUpdateReq appUpdateReq, Continuation<? super BaseResponse<VerionResult>> continuation);

    @Headers({PdHost.OTA_URL})
    @POST("/api/common/ota/client/get_switch/v1")
    Object switchVersion(@Body CheckUpdateRequestParams checkUpdateRequestParams, Continuation<? super BaseResponse<SwitchVersionResult>> continuation);
}
