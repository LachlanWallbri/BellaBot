package com.pudutech.pd_network.ability;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.pd_network.bean.BaseResponse;
import com.pudutech.pd_network.bean.PdHost;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/* compiled from: SupportAbilityComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/pd_network/ability/Api;", "", "updateAbility", "Lcom/pudutech/pd_network/bean/BaseResponse;", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "Lcom/pudutech/pd_network/ability/AbilityReq;", "(Lcom/pudutech/pd_network/ability/AbilityReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface Api {
    @Headers({PdHost.ROBOT_BIZ})
    @POST("/api/common/abilities/update/v1")
    Object updateAbility(@Body AbilityReq abilityReq, Continuation<? super BaseResponse<Object>> continuation);
}
