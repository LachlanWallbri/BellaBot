package com.pudutech.lib_update.base.request;

import androidx.exifinterface.media.ExifInterface;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseRequestManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004H\u0016¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/lib_update/base/request/BaseRequestManager;", "", "()V", "observe", "Lio/reactivex/Observable;", ExifInterface.GPS_DIRECTION_TRUE, "reqObservable", "lib_update_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public class BaseRequestManager {
    public <T> Observable<T> observe(Observable<T> reqObservable) {
        Intrinsics.checkParameterIsNotNull(reqObservable, "reqObservable");
        Observable<T> observeOn = reqObservable.subscribeOn(Schedulers.m3958io()).unsubscribeOn(Schedulers.m3958io()).observeOn(AndroidSchedulers.mainThread());
        Intrinsics.checkExpressionValueIsNotNull(observeOn, "reqObservable.subscribeO…dSchedulers.mainThread())");
        return observeOn;
    }
}
