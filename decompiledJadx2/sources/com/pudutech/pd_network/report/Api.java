package com.pudutech.pd_network.report;

import com.pudutech.pd_network.bean.BaseResponse;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/* compiled from: PuduReportManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001JB\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00040\u00032\u0019\b\u0001\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\b2\b\b\u0001\u0010\t\u001a\u00020\u0007H§@ø\u0001\u0000¢\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/pd_network/report/Api;", "", "report", "Lretrofit2/Response;", "Lcom/pudutech/pd_network/bean/BaseResponse;", "json", "", "", "Lkotlin/jvm/JvmSuppressWildcards;", "url", "(Ljava/util/Map;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface Api {
    @POST
    Object report(@Body Map<String, Object> map, @Url String str, Continuation<? super Response<BaseResponse<Object>>> continuation);
}
