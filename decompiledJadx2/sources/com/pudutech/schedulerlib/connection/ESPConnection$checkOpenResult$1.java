package com.pudutech.schedulerlib.connection;

import com.pudutech.schedulerlib.ScheduleController;
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
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: ESPConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.connection.ESPConnection$checkOpenResult$1", m3970f = "ESPConnection.kt", m3971i = {0}, m3972l = {130}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes2.dex */
public final class ESPConnection$checkOpenResult$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ScheduleController.OnConnectStateListener $connectStateListener;
    final /* synthetic */ int $version;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7428p$;
    final /* synthetic */ ESPConnection this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ESPConnection$checkOpenResult$1(ESPConnection eSPConnection, int i, ScheduleController.OnConnectStateListener onConnectStateListener, Continuation continuation) {
        super(2, continuation);
        this.this$0 = eSPConnection;
        this.$version = i;
        this.$connectStateListener = onConnectStateListener;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ESPConnection$checkOpenResult$1 eSPConnection$checkOpenResult$1 = new ESPConnection$checkOpenResult$1(this.this$0, this.$version, this.$connectStateListener, completion);
        eSPConnection$checkOpenResult$1.f7428p$ = (CoroutineScope) obj;
        return eSPConnection$checkOpenResult$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ESPConnection$checkOpenResult$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0029  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0033 -> B:5:0x0036). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        ESPConnection$checkOpenResult$1 eSPConnection$checkOpenResult$1;
        int i;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7428p$;
            eSPConnection$checkOpenResult$1 = this;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
        } else if (i2 == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            eSPConnection$checkOpenResult$1 = this;
            ESPScheduleNative eSPScheduleNative = ESPScheduleNative.INSTANCE;
            i = eSPConnection$checkOpenResult$1.this$0.baudRate;
            if (eSPScheduleNative.openESP(i, eSPConnection$checkOpenResult$1.$version)) {
                if (ESPScheduleNative.INSTANCE.checkHardwareHandshake()) {
                    eSPConnection$checkOpenResult$1.$connectStateListener.onSuccessful();
                    eSPConnection$checkOpenResult$1.this$0.isRunning = true;
                    eSPConnection$checkOpenResult$1.this$0.startMsgTask();
                }
                return Unit.INSTANCE;
            }
            if (CoroutineScopeKt.isActive(coroutineScope)) {
                eSPConnection$checkOpenResult$1.L$0 = coroutineScope;
                eSPConnection$checkOpenResult$1.label = 1;
                if (DelayKt.delay(2000L, eSPConnection$checkOpenResult$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                ESPScheduleNative eSPScheduleNative2 = ESPScheduleNative.INSTANCE;
                i = eSPConnection$checkOpenResult$1.this$0.baudRate;
                if (eSPScheduleNative2.openESP(i, eSPConnection$checkOpenResult$1.$version)) {
                }
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                    return Unit.INSTANCE;
                }
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
