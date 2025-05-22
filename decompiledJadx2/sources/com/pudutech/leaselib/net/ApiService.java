package com.pudutech.leaselib.net;

import com.pudutech.leaselib.bean.LeaseData;
import com.pudutech.leaselib.bean.ReqLeaseData;
import com.pudutech.pd_network.bean.PdHost;
import io.reactivex.Observable;
import kotlin.Metadata;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ApiService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\b\u001a\u00020\tH'¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/leaselib/net/ApiService;", "", "getLeaseState", "Lio/reactivex/Observable;", "Lcom/pudutech/leaselib/net/HttpResult;", "Lcom/pudutech/leaselib/bean/LeaseData;", "leaseData", "Lcom/pudutech/leaselib/bean/ReqLeaseData;", "url", "", "lib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public interface ApiService {
    @Headers({PdHost.ROBOT_BIZ})
    @POST
    Observable<HttpResult<LeaseData>> getLeaseState(@Body ReqLeaseData leaseData, @Url String url);

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: ApiService.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ Observable getLeaseState$default(ApiService apiService, ReqLeaseData reqLeaseData, String str, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getLeaseState");
            }
            if ((i & 2) != 0) {
                str = "/api/common/robot_life/lease/v2";
            }
            return apiService.getLeaseState(reqLeaseData, str);
        }
    }
}
