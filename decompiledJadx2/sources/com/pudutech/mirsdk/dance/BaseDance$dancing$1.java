package com.pudutech.mirsdk.dance;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.aidl.serialize.Dance;
import com.pudutech.mirsdk.aidl.serialize.DanceStatus;
import com.pudutech.mirsdk.dance.BaseDance;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
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
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BaseDance.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.dance.BaseDance$dancing$1", m3970f = "BaseDance.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
public final class BaseDance$dancing$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5775p$;
    final /* synthetic */ BaseDance this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseDance$dancing$1(BaseDance baseDance, Continuation continuation) {
        super(2, continuation);
        this.this$0 = baseDance;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        BaseDance$dancing$1 baseDance$dancing$1 = new BaseDance$dancing$1(this.this$0, completion);
        baseDance$dancing$1.f5775p$ = (CoroutineScope) obj;
        return baseDance$dancing$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseDance$dancing$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AIDLConnection aIDLConnection;
        MirCoreInterface mirCoreInterface;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5775p$;
            Pdlog.m3273d(this.this$0.getTAG(), "Dancing start job");
            aIDLConnection = this.this$0.coreService;
            if (aIDLConnection == null || (mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface()) == null || !mirCoreInterface.hasCoreReady()) {
                Pdlog.m3277w(this.this$0.getTAG(), "core not ready to run task");
                this.this$0.setMDanceStatus(0);
                this.this$0.danceCallback.invoke(DanceStatus.IDLE);
                return Unit.INSTANCE;
            }
            this.this$0.setMDanceStatus(1);
            this.this$0.danceCallback.invoke(DanceStatus.DANCING);
            while (CoroutineScopeKt.isActive(coroutineScope)) {
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    this.this$0.setMDanceStatus(0);
                    Pdlog.m3273d(this.this$0.getTAG(), "Dancing job is not active");
                    this.this$0.danceCallback.invoke(DanceStatus.IDLE);
                    return Unit.INSTANCE;
                }
                if (this.this$0.getMDanceStatus() == 2) {
                    Pdlog.m3273d(this.this$0.getTAG(), "dancing request stop");
                    this.this$0.resetPos();
                    return Unit.INSTANCE;
                }
                if (this.this$0.getMDanceStatus() == 3 || this.this$0.getMDanceStatus() == 0) {
                    Pdlog.m3273d(this.this$0.getTAG(), "dancing error request break");
                    this.this$0.stopRobot();
                    return Unit.INSTANCE;
                }
                Dance dance = this.this$0.getDanceList().get(this.this$0.getMLooperIndex());
                Intrinsics.checkExpressionValueIsNotNull(dance, "danceList[mLooperIndex]");
                Dance dance2 = dance;
                int i = BaseDance.WhenMappings.$EnumSwitchMapping$0[dance2.getMode().ordinal()];
                if (i == 1) {
                    this.this$0.danceRotate(dance2);
                } else if (i == 2) {
                    this.this$0.danceWalk(dance2);
                }
            }
            Pdlog.m3273d(this.this$0.getTAG(), "Dancing end  job");
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
