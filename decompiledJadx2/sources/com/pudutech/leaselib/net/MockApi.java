package com.pudutech.leaselib.net;

import com.pudutech.leaselib.bean.LeaseData;
import com.pudutech.leaselib.bean.ReqLeaseData;
import com.pudutech.lib_update.util.ShellUtils;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MockApi.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/leaselib/net/MockApi;", "Lcom/pudutech/leaselib/net/ApiService;", "()V", "getLeaseState", "Lio/reactivex/Observable;", "Lcom/pudutech/leaselib/net/HttpResult;", "Lcom/pudutech/leaselib/bean/LeaseData;", "hostType", "", "leaseData", "Lcom/pudutech/leaselib/bean/ReqLeaseData;", "lib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MockApi implements ApiService {
    public Observable<HttpResult<LeaseData>> getLeaseState(String hostType, ReqLeaseData leaseData) {
        Intrinsics.checkParameterIsNotNull(hostType, "hostType");
        Intrinsics.checkParameterIsNotNull(leaseData, "leaseData");
        Observable<HttpResult<LeaseData>> subscribeOn = Observable.fromCallable(new Callable<T>() { // from class: com.pudutech.leaselib.net.MockApi$getLeaseState$1
            @Override // java.util.concurrent.Callable
            public final HttpResult<LeaseData> call() {
                Thread.sleep(2000L);
                LeaseData leaseData2 = new LeaseData();
                leaseData2.setUseType(2);
                leaseData2.setUseEndTime((System.currentTimeMillis() / 1000) + 20);
                leaseData2.setRemainingTime(20L);
                return new HttpResult<>(0, ShellUtils.COMMAND_SU, leaseData2);
            }
        }).subscribeOn(Schedulers.m3958io());
        Intrinsics.checkExpressionValueIsNotNull(subscribeOn, "Observable.fromCallable …scribeOn(Schedulers.io())");
        return subscribeOn;
    }
}
