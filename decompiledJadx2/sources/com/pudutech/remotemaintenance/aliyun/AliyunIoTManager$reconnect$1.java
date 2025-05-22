package com.pudutech.remotemaintenance.aliyun;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.remotemaintenance.config.ConnectState;
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
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AliyunIoTManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.remotemaintenance.aliyun.AliyunIoTManager$reconnect$1", m3970f = "AliyunIoTManager.kt", m3971i = {0}, m3972l = {237}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes.dex */
public final class AliyunIoTManager$reconnect$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $isFail;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7088p$;
    final /* synthetic */ AliyunIoTManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AliyunIoTManager$reconnect$1(AliyunIoTManager aliyunIoTManager, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = aliyunIoTManager;
        this.$isFail = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AliyunIoTManager$reconnect$1 aliyunIoTManager$reconnect$1 = new AliyunIoTManager$reconnect$1(this.this$0, this.$isFail, completion);
        aliyunIoTManager$reconnect$1.f7088p$ = (CoroutineScope) obj;
        return aliyunIoTManager$reconnect$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AliyunIoTManager$reconnect$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ConnectState connectState;
        ConnectState connectState2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.L$0 = this.f7088p$;
            this.label = 1;
            if (DelayKt.delay(SolicitService.CAMERA_OPEN_TIME_OUT, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        connectState = this.this$0.connectState;
        if (connectState != ConnectState.CONNECTING) {
            connectState2 = this.this$0.connectState;
            if (connectState2 != ConnectState.CONNECTED) {
                StringBuilder sb = new StringBuilder();
                sb.append("reconnect() threadId = ");
                Thread currentThread = Thread.currentThread();
                Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                sb.append(currentThread.getId());
                Pdlog.m3273d(AliyunIoTManager.TAG, sb.toString());
                this.this$0.disconnect();
                Pdlog.m3273d(AliyunIoTManager.TAG, "开始连接");
                AliyunIoTManager.callbackConnectState$default(this.this$0, ConnectState.CONNECTING, 0, null, false, 14, null);
                if (this.$isFail) {
                    this.this$0.checkLocalAreaInfo();
                } else {
                    this.this$0.getIotAreaInfo();
                }
                return Unit.INSTANCE;
            }
        }
        Pdlog.m3277w(AliyunIoTManager.TAG, "正在连接中或已连接，无需重连");
        return Unit.INSTANCE;
    }
}
