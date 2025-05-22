package org.greenrobot.greendao.p091rx;

import java.util.concurrent.Callable;
import rx.Observable;
import rx.functions.Func0;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
class RxUtils {
    RxUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> Observable<T> fromCallable(final Callable<T> callable) {
        return Observable.defer(new Func0<Observable<T>>() { // from class: org.greenrobot.greendao.rx.RxUtils.1
            public Observable<T> call() {
                try {
                    return Observable.just(callable.call());
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }
}
