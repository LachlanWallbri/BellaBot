package com.pudutech.event_tracking.upload;

import com.pudutech.event_tracking.bean.UpLoadReq;
import com.pudutech.pd_network.bean.BaseResponse;
import com.pudutech.pd_network.bean.PdHost;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/* compiled from: Api.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J'\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/event_tracking/upload/Api;", "", "upload", "Lretrofit2/Response;", "Lcom/pudutech/pd_network/bean/BaseResponse;", "reqData", "Lcom/pudutech/event_tracking/bean/UpLoadReq;", "(Lcom/pudutech/event_tracking/bean/UpLoadReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public interface Api {
    @Headers({PdHost.ROBOT_COLL})
    @POST("/api/common/app_track/collect/v1")
    Object upload(@Body UpLoadReq upLoadReq, Continuation<? super Response<BaseResponse<Object>>> continuation);
}
