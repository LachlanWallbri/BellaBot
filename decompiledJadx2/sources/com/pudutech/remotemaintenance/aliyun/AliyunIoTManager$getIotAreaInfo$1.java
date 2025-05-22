package com.pudutech.remotemaintenance.aliyun;

import com.pudutech.base.Pdlog;
import com.pudutech.remotemaintenance.net.IoTServerApiManager;
import com.pudutech.remotemaintenance.net.ServerApiManager;
import com.pudutech.remotemaintenance.net.req.AreaInfoReq;
import com.pudutech.remotemaintenance.net.resp.AreaInfoResp;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AliyunIoTManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.remotemaintenance.aliyun.AliyunIoTManager$getIotAreaInfo$1", m3970f = "AliyunIoTManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes.dex */
public final class AliyunIoTManager$getIotAreaInfo$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7087p$;
    final /* synthetic */ AliyunIoTManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AliyunIoTManager$getIotAreaInfo$1(AliyunIoTManager aliyunIoTManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = aliyunIoTManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AliyunIoTManager$getIotAreaInfo$1 aliyunIoTManager$getIotAreaInfo$1 = new AliyunIoTManager$getIotAreaInfo$1(this.this$0, completion);
        aliyunIoTManager$getIotAreaInfo$1.f7087p$ = (CoroutineScope) obj;
        return aliyunIoTManager$getIotAreaInfo$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AliyunIoTManager$getIotAreaInfo$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        try {
            this.this$0.requestAreaCount = 0;
            this.this$0.disposeGetAreaInfo = ServerApiManager.ServerApi.DefaultImpls.getAreaInfo$default(ServerApiManager.INSTANCE.getServerApi(), null, new AreaInfoReq(), 1, null).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() { // from class: com.pudutech.remotemaintenance.aliyun.AliyunIoTManager$getIotAreaInfo$1.1
                @Override // io.reactivex.functions.Function
                public final Observable<Object> apply(Observable<Throwable> throwableObservable) {
                    Intrinsics.checkParameterIsNotNull(throwableObservable, "throwableObservable");
                    return throwableObservable.flatMap(new Function<T, ObservableSource<? extends R>>() { // from class: com.pudutech.remotemaintenance.aliyun.AliyunIoTManager.getIotAreaInfo.1.1.1
                        @Override // io.reactivex.functions.Function
                        public final Observable<? extends Object> apply(Throwable throwable) {
                            int i;
                            int i2;
                            int i3;
                            int i4;
                            Intrinsics.checkParameterIsNotNull(throwable, "throwable");
                            i = AliyunIoTManager$getIotAreaInfo$1.this.this$0.requestAreaCount;
                            if (i < 0) {
                                Pdlog.m3277w(AliyunIoTManager.TAG, "getAreaInfo 已经手动断开了,不用重试了");
                                Observable.error(throwable);
                            }
                            AliyunIoTManager aliyunIoTManager = AliyunIoTManager$getIotAreaInfo$1.this.this$0;
                            i2 = aliyunIoTManager.requestAreaCount;
                            aliyunIoTManager.requestAreaCount = i2 + 1;
                            if (throwable instanceof IOException) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("获取节点失败，第");
                                i4 = AliyunIoTManager$getIotAreaInfo$1.this.this$0.requestAreaCount;
                                sb.append(i4);
                                sb.append("次请求失败，正在重试，throwable = ");
                                sb.append(throwable);
                                Pdlog.m3277w(AliyunIoTManager.TAG, sb.toString());
                                return Observable.timer(8000L, TimeUnit.MILLISECONDS);
                            }
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("获取节点失败，第");
                            i3 = AliyunIoTManager$getIotAreaInfo$1.this.this$0.requestAreaCount;
                            sb2.append(i3);
                            sb2.append("次请求失败，不重试了，throwable = ");
                            sb2.append(throwable);
                            Pdlog.m3277w(AliyunIoTManager.TAG, sb2.toString());
                            return Observable.error(throwable);
                        }
                    });
                }
            }).observeOn(Schedulers.m3958io()).subscribeOn(Schedulers.m3958io()).subscribe(new Consumer<IoTServerApiManager.HttpResult<AreaInfoResp>>() { // from class: com.pudutech.remotemaintenance.aliyun.AliyunIoTManager$getIotAreaInfo$1.2
                @Override // io.reactivex.functions.Consumer
                public final void accept(IoTServerApiManager.HttpResult<AreaInfoResp> httpResult) {
                    Pdlog.m3275i(AliyunIoTManager.TAG, "getIotAreaInfo(iot) code=" + httpResult.getRet() + ", msg=" + httpResult.getMessage() + ", data=" + httpResult.getData());
                    if (httpResult.getRet() == 0 && httpResult.getData() != null) {
                        AreaInfo areaInfo = new AreaInfo();
                        areaInfo.setRegion_id(httpResult.getData().getRegion_id());
                        areaInfo.setUrl(httpResult.getData().getUrl());
                        AliyunIoTManager$getIotAreaInfo$1.this.this$0.saveAreaInfo(areaInfo);
                    } else {
                        Pdlog.m3273d(AliyunIoTManager.TAG, "getIotAreaInfo, onFailure() errCode=" + httpResult.getRet() + ", errMsg=" + httpResult.getMessage());
                    }
                    AliyunIoTManager$getIotAreaInfo$1.this.this$0.checkLocalAreaInfo();
                }
            }, new Consumer<Throwable>() { // from class: com.pudutech.remotemaintenance.aliyun.AliyunIoTManager$getIotAreaInfo$1.3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    Pdlog.m3273d(AliyunIoTManager.TAG, "getIotAreaInfo, onFailure(), errMsg=" + th.getMessage());
                    AliyunIoTManager$getIotAreaInfo$1.this.this$0.checkLocalAreaInfo();
                }
            });
        } catch (Exception e) {
            Pdlog.m3274e(AliyunIoTManager.TAG, "getIotAreaInfo, onFailure() " + e.getMessage());
            this.this$0.checkLocalAreaInfo();
        }
        return Unit.INSTANCE;
    }
}
