package retrofit2.adapter.rxjava2;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import retrofit2.Call;
import retrofit2.CallAdapter;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
final class RxJava2CallAdapter<R> implements CallAdapter<R, Object> {
    private final boolean isAsync;
    private final boolean isBody;
    private final boolean isCompletable;
    private final boolean isFlowable;
    private final boolean isMaybe;
    private final boolean isResult;
    private final boolean isSingle;
    private final Type responseType;

    @Nullable
    private final Scheduler scheduler;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RxJava2CallAdapter(Type type, @Nullable Scheduler scheduler, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        this.responseType = type;
        this.scheduler = scheduler;
        this.isAsync = z;
        this.isResult = z2;
        this.isBody = z3;
        this.isFlowable = z4;
        this.isSingle = z5;
        this.isMaybe = z6;
        this.isCompletable = z7;
    }

    @Override // retrofit2.CallAdapter
    public Type responseType() {
        return this.responseType;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0037  */
    @Override // retrofit2.CallAdapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object adapt(Call<R> call) {
        Observable callExecuteObservable;
        Observable bodyObservable;
        Scheduler scheduler;
        if (this.isAsync) {
            callExecuteObservable = new CallEnqueueObservable(call);
        } else {
            callExecuteObservable = new CallExecuteObservable(call);
        }
        if (this.isResult) {
            bodyObservable = new ResultObservable(callExecuteObservable);
        } else {
            if (this.isBody) {
                bodyObservable = new BodyObservable(callExecuteObservable);
            }
            scheduler = this.scheduler;
            if (scheduler != null) {
                callExecuteObservable = callExecuteObservable.subscribeOn(scheduler);
            }
            if (!this.isFlowable) {
                return callExecuteObservable.toFlowable(BackpressureStrategy.LATEST);
            }
            if (this.isSingle) {
                return callExecuteObservable.singleOrError();
            }
            if (this.isMaybe) {
                return callExecuteObservable.singleElement();
            }
            return this.isCompletable ? callExecuteObservable.ignoreElements() : callExecuteObservable;
        }
        callExecuteObservable = bodyObservable;
        scheduler = this.scheduler;
        if (scheduler != null) {
        }
        if (!this.isFlowable) {
        }
    }
}
