package com.pudutech.pd_network.sn;

import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.pd_network.bean.BaseResponse;
import com.pudutech.pd_network.bean.GetSNRes;
import com.pudutech.pd_network.bean.PdHost;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/* compiled from: api.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J-\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0014\b\u0001\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/pd_network/sn/Api;", "", "getSN", "Lcom/pudutech/pd_network/bean/BaseResponse;", "Lcom/pudutech/pd_network/bean/GetSNRes;", MapElement.Key.MAP, "", "", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface Api {
    @Headers({PdHost.HK_CENTER_URL})
    @POST(ApiKt.ROBOT_GET_SN)
    Object getSN(@Body Map<String, String> map, Continuation<? super BaseResponse<GetSNRes>> continuation);
}
