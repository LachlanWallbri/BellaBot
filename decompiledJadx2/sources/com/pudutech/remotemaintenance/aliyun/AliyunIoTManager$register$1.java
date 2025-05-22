package com.pudutech.remotemaintenance.aliyun;

import androidx.core.os.EnvironmentCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.remotemaintenance.config.ConnectState;
import com.pudutech.remotemaintenance.net.IoTServerApiManager;
import com.pudutech.remotemaintenance.net.req.DeviceRegisterReq;
import com.pudutech.remotemaintenance.net.resp.DeviceRegisterResp;
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
@DebugMetadata(m3969c = "com.pudutech.remotemaintenance.aliyun.AliyunIoTManager$register$1", m3970f = "AliyunIoTManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes.dex */
public final class AliyunIoTManager$register$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $url;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7089p$;
    final /* synthetic */ AliyunIoTManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AliyunIoTManager$register$1(AliyunIoTManager aliyunIoTManager, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = aliyunIoTManager;
        this.$url = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AliyunIoTManager$register$1 aliyunIoTManager$register$1 = new AliyunIoTManager$register$1(this.this$0, this.$url, completion);
        aliyunIoTManager$register$1.f7089p$ = (CoroutineScope) obj;
        return aliyunIoTManager$register$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AliyunIoTManager$register$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.requestRegisterCount = 0;
        this.this$0.disposeRegister = IoTServerApiManager.INSTANCE.getServerApi().deviceRegister(this.$url + IoTServerApiManager.DEVICE_REGISTER, new DeviceRegisterReq()).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() { // from class: com.pudutech.remotemaintenance.aliyun.AliyunIoTManager$register$1.1
            @Override // io.reactivex.functions.Function
            public final Observable<Object> apply(Observable<Throwable> throwableObservable) {
                Intrinsics.checkParameterIsNotNull(throwableObservable, "throwableObservable");
                return throwableObservable.flatMap(new Function<T, ObservableSource<? extends R>>() { // from class: com.pudutech.remotemaintenance.aliyun.AliyunIoTManager.register.1.1.1
                    @Override // io.reactivex.functions.Function
                    public final Observable<? extends Object> apply(Throwable throwable) {
                        int i;
                        int i2;
                        int i3;
                        int i4;
                        Intrinsics.checkParameterIsNotNull(throwable, "throwable");
                        i = AliyunIoTManager$register$1.this.this$0.requestRegisterCount;
                        if (i < 0) {
                            Pdlog.m3277w(AliyunIoTManager.TAG, "deviceRegister 已经手动断开了,不用重试了");
                            Observable.error(throwable);
                        }
                        AliyunIoTManager aliyunIoTManager = AliyunIoTManager$register$1.this.this$0;
                        i2 = aliyunIoTManager.requestRegisterCount;
                        aliyunIoTManager.requestRegisterCount = i2 + 1;
                        if (throwable instanceof IOException) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("注册设备失败，第");
                            i4 = AliyunIoTManager$register$1.this.this$0.requestRegisterCount;
                            sb.append(i4);
                            sb.append("次请求失败，正在重试，throwable = ");
                            sb.append(throwable);
                            Pdlog.m3277w(AliyunIoTManager.TAG, sb.toString());
                            return Observable.timer(8000L, TimeUnit.MILLISECONDS);
                        }
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("注册设备失败，第");
                        i3 = AliyunIoTManager$register$1.this.this$0.requestRegisterCount;
                        sb2.append(i3);
                        sb2.append("次请求失败，不重试了，throwable = ");
                        sb2.append(throwable);
                        Pdlog.m3277w(AliyunIoTManager.TAG, sb2.toString());
                        return Observable.error(throwable);
                    }
                });
            }
        }).observeOn(Schedulers.m3958io()).subscribeOn(Schedulers.m3958io()).subscribe(new Consumer<IoTServerApiManager.HttpResult<DeviceRegisterResp>>() { // from class: com.pudutech.remotemaintenance.aliyun.AliyunIoTManager$register$1.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(IoTServerApiManager.HttpResult<DeviceRegisterResp> httpResult) {
                AreaInfo localAreaInfo;
                Pdlog.m3275i(AliyunIoTManager.TAG, "getFileUpdate(iot) code=" + httpResult.getRet() + ", msg=" + httpResult.getMessage() + ", data=" + httpResult.getData());
                if (httpResult.getRet() == 0) {
                    DeviceRegisterResp data = httpResult.getData();
                    DeviceInfo deviceInfo = null;
                    if (data != null) {
                        DeviceInfo deviceInfo2 = new DeviceInfo();
                        deviceInfo2.setProductKey(data.getProductKey());
                        deviceInfo2.setDeviceName(data.getDeviceName());
                        deviceInfo2.setDeviceSecret(data.getDeviceSecret());
                        deviceInfo2.setIotId(data.getIotId());
                        localAreaInfo = AliyunIoTManager$register$1.this.this$0.getLocalAreaInfo();
                        deviceInfo2.setRegion_id(localAreaInfo != null ? localAreaInfo.getRegion_id() : null);
                        deviceInfo = deviceInfo2;
                    }
                    if (deviceInfo != null) {
                        AliyunIoTManager$register$1.this.this$0.saveDeviceInfo(deviceInfo);
                        AliyunIoTManager$register$1.this.this$0.deviceInfo = deviceInfo;
                        AliyunIoTManager$register$1.this.this$0.toServer();
                        return;
                    }
                    return;
                }
                Pdlog.m3273d(AliyunIoTManager.TAG, "device register, onFailure() errCode=" + httpResult.getRet() + ", errMsg=" + httpResult.getMessage());
                AliyunIoTManager aliyunIoTManager = AliyunIoTManager$register$1.this.this$0;
                ConnectState connectState = ConnectState.CONNECT_FAILURE;
                int ret = httpResult.getRet();
                String message = httpResult.getMessage();
                if (message == null) {
                    message = EnvironmentCompat.MEDIA_UNKNOWN;
                }
                AliyunIoTManager.callbackConnectState$default(aliyunIoTManager, connectState, ret, message, false, 8, null);
            }
        }, new Consumer<Throwable>() { // from class: com.pudutech.remotemaintenance.aliyun.AliyunIoTManager$register$1.3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Pdlog.m3273d(AliyunIoTManager.TAG, "device register, onFailure() errCode=-1, errMsg=" + th.getMessage());
                AliyunIoTManager.callbackConnectState$default(AliyunIoTManager$register$1.this.this$0, ConnectState.CONNECT_FAILURE, 0, null, false, 14, null);
            }
        });
        return Unit.INSTANCE;
    }
}
