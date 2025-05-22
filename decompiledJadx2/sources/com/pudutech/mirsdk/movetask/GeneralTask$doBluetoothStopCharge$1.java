package com.pudutech.mirsdk.movetask;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.bluetooth.BluetoothBleHelper;
import com.pudutech.mirsdk.charge.ChargeMessageCenter;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GeneralTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.GeneralTask$doBluetoothStopCharge$1", m3970f = "GeneralTask.kt", m3971i = {0, 0}, m3972l = {599}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "count"}, m3975s = {"L$0", "I$0"})
/* loaded from: classes6.dex */
public final class GeneralTask$doBluetoothStopCharge$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $mac;
    int I$0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6406p$;
    final /* synthetic */ GeneralTask this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GeneralTask$doBluetoothStopCharge$1(GeneralTask generalTask, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = generalTask;
        this.$mac = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        GeneralTask$doBluetoothStopCharge$1 generalTask$doBluetoothStopCharge$1 = new GeneralTask$doBluetoothStopCharge$1(this.this$0, this.$mac, completion);
        generalTask$doBluetoothStopCharge$1.f6406p$ = (CoroutineScope) obj;
        return generalTask$doBluetoothStopCharge$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GeneralTask$doBluetoothStopCharge$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x004e -> B:5:0x0051). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        int i;
        GeneralTask$doBluetoothStopCharge$1 generalTask$doBluetoothStopCharge$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6406p$;
            i = 0;
            generalTask$doBluetoothStopCharge$1 = this;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
            return Unit.INSTANCE;
        }
        if (i2 == 1) {
            i = this.I$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            generalTask$doBluetoothStopCharge$1 = this;
            i++;
            if (CoroutineScopeKt.isActive(coroutineScope) || BluetoothBleHelper.INSTANCE.getConnectState(generalTask$doBluetoothStopCharge$1.$mac) != 2) {
                return Unit.INSTANCE;
            }
            if (i >= 5) {
                Pdlog.m3273d(generalTask$doBluetoothStopCharge$1.this$0.TAG, "stopChargeJob is time out in stop job");
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new C53001(null), 3, null);
                return Unit.INSTANCE;
            }
            ChargeMessageCenter.INSTANCE.sendStopCharge(generalTask$doBluetoothStopCharge$1.$mac);
            generalTask$doBluetoothStopCharge$1.L$0 = coroutineScope;
            generalTask$doBluetoothStopCharge$1.I$0 = i;
            generalTask$doBluetoothStopCharge$1.label = 1;
            if (DelayKt.delay(500L, generalTask$doBluetoothStopCharge$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            i++;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: GeneralTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.GeneralTask$doBluetoothStopCharge$1$1", m3970f = "GeneralTask.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdk.movetask.GeneralTask$doBluetoothStopCharge$1$1 */
    /* loaded from: classes6.dex */
    public static final class C53001 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6407p$;

        C53001(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C53001 c53001 = new C53001(completion);
            c53001.f6407p$ = (CoroutineScope) obj;
            return c53001;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C53001) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6407p$;
            GeneralTask$doBluetoothStopCharge$1.this.this$0.disconnectChargePile();
            return Unit.INSTANCE;
        }
    }
}
