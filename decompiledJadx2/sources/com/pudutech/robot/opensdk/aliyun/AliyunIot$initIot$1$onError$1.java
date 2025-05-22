package com.pudutech.robot.opensdk.aliyun;

import android.os.Handler;
import com.aliyun.alink.linkkit.api.LinkKit;
import com.aliyun.alink.linksdk.tools.AError;
import com.pudutech.gatecontrollerlib.GateControllerMsg;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.robot.opensdk.RemoteConnectState;
import com.pudutech.robot.opensdk.interf.ICallback;
import com.pudutech.robot.opensdk.interf.IRemoteConnectStateListener;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AliyunIot.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.opensdk.aliyun.AliyunIot$initIot$1$onError$1", m3970f = "AliyunIot.kt", m3971i = {0, 1}, m3972l = {246, GateControllerMsg.ControlCode.Error}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes6.dex */
public final class AliyunIot$initIot$1$onError$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AError $p0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7243p$;
    final /* synthetic */ AliyunIot$initIot$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AliyunIot$initIot$1$onError$1(AliyunIot$initIot$1 aliyunIot$initIot$1, AError aError, Continuation continuation) {
        super(2, continuation);
        this.this$0 = aliyunIot$initIot$1;
        this.$p0 = aError;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AliyunIot$initIot$1$onError$1 aliyunIot$initIot$1$onError$1 = new AliyunIot$initIot$1$onError$1(this.this$0, this.$p0, completion);
        aliyunIot$initIot$1$onError$1.f7243p$ = (CoroutineScope) obj;
        return aliyunIot$initIot$1$onError$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AliyunIot$initIot$1$onError$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        AError aError;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7243p$;
            AError aError2 = this.$p0;
            if ((aError2 != null && aError2.getCode() == 514) || ((aError = this.$p0) != null && aError.getCode() == 1101020)) {
                LinkKit.getInstance().deinit();
                this.L$0 = coroutineScope;
                this.label = 1;
                if (DelayKt.delay(SolicitService.CAMERA_OPEN_TIME_OUT, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        MainCoroutineDispatcher main = Dispatchers.getMain();
        C57051 c57051 = new C57051(null);
        this.L$0 = coroutineScope;
        this.label = 2;
        if (BuildersKt.withContext(main, c57051, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AliyunIot.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.robot.opensdk.aliyun.AliyunIot$initIot$1$onError$1$1", m3970f = "AliyunIot.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.robot.opensdk.aliyun.AliyunIot$initIot$1$onError$1$1 */
    /* loaded from: classes6.dex */
    public static final class C57051 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7244p$;

        C57051(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C57051 c57051 = new C57051(completion);
            c57051.f7244p$ = (CoroutineScope) obj;
            return c57051;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C57051) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            boolean isNetActive;
            boolean z;
            IRemoteConnectStateListener onConnectListener;
            Handler handler;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7244p$;
            AliyunIot$initIot$1$onError$1.this.this$0.this$0.isIniting = false;
            isNetActive = AliyunIot$initIot$1$onError$1.this.this$0.this$0.isNetActive();
            if (isNetActive) {
                handler = AliyunIot$initIot$1$onError$1.this.this$0.this$0.handler;
                handler.postDelayed(new Runnable() { // from class: com.pudutech.robot.opensdk.aliyun.AliyunIot.initIot.1.onError.1.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        AliyunIot$initIot$1$onError$1.this.this$0.this$0.reconnectIfNeed();
                    }
                }, SolicitService.CAMERA_OPEN_TIME_OUT);
                ICallback iCallback = AliyunIot$initIot$1$onError$1.this.this$0.$callBack;
                if (iCallback != null) {
                    ICallback.DefaultImpls.onSuccess$default(iCallback, null, 1, null);
                }
            } else {
                ICallback iCallback2 = AliyunIot$initIot$1$onError$1.this.this$0.$callBack;
                if (iCallback2 != null) {
                    AError aError = AliyunIot$initIot$1$onError$1.this.$p0;
                    iCallback2.onFailed(new Exception(aError != null ? aError.getMsg() : null));
                }
            }
            z = AliyunIot$initIot$1$onError$1.this.this$0.this$0.isEnable;
            if (z && (onConnectListener = AliyunIot$initIot$1$onError$1.this.this$0.this$0.getOnConnectListener()) != null) {
                onConnectListener.onConnectState(RemoteConnectState.DISCONNECTED);
            }
            return Unit.INSTANCE;
        }
    }
}
