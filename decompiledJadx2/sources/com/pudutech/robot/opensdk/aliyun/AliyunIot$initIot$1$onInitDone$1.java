package com.pudutech.robot.opensdk.aliyun;

import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.pudutech.robot.opensdk.interf.ICallback;
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

/* compiled from: AliyunIot.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.opensdk.aliyun.AliyunIot$initIot$1$onInitDone$1", m3970f = "AliyunIot.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
final class AliyunIot$initIot$1$onInitDone$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7245p$;
    final /* synthetic */ AliyunIot$initIot$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AliyunIot$initIot$1$onInitDone$1(AliyunIot$initIot$1 aliyunIot$initIot$1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = aliyunIot$initIot$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AliyunIot$initIot$1$onInitDone$1 aliyunIot$initIot$1$onInitDone$1 = new AliyunIot$initIot$1$onInitDone$1(this.this$0, completion);
        aliyunIot$initIot$1$onInitDone$1.f7245p$ = (CoroutineScope) obj;
        return aliyunIot$initIot$1$onInitDone$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AliyunIot$initIot$1$onInitDone$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AliyunIot$iotNotifyListener$1 aliyunIot$iotNotifyListener$1;
        ConnectState connectState;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7245p$;
        this.this$0.this$0.isInit = true;
        this.this$0.this$0.isIniting = false;
        ICallback iCallback = this.this$0.$callBack;
        if (iCallback != null) {
            ICallback.DefaultImpls.onSuccess$default(iCallback, null, 1, null);
        }
        this.this$0.this$0.connectState = ConnectState.CONNECTED;
        aliyunIot$iotNotifyListener$1 = this.this$0.this$0.iotNotifyListener;
        connectState = this.this$0.this$0.connectState;
        aliyunIot$iotNotifyListener$1.onConnectStateChange("", connectState);
        return Unit.INSTANCE;
    }
}
