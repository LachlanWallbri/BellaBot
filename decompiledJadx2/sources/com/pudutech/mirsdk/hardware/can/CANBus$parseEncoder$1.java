package com.pudutech.mirsdk.hardware.can;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.HardwareScopeKt;
import com.pudutech.mirsdk.hardware.ISensorData;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m3961d2 = {"<anonymous>", "", "<anonymous parameter 0>", "", "bytes", "Lkotlin/UByteArray;", "invoke", "(I[B)V"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CANBus$parseEncoder$1 extends Lambda implements Function2<Integer, UByteArray, Unit> {
    final /* synthetic */ CANBus this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CANBus$parseEncoder$1(CANBus cANBus) {
        super(2);
        this.this$0 = cANBus;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
        invoke(num.intValue(), uByteArray.getStorage());
        return Unit.INSTANCE;
    }

    public final void invoke(int i, byte[] bytes) {
        Job job;
        AtomicBoolean atomicBoolean;
        AtomicBoolean atomicBoolean2;
        String str;
        AtomicBoolean atomicBoolean3;
        String str2;
        String str3;
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        this.this$0.getEncoder().update((short) ((((UByteArray.m4577getimpl(bytes, 1) & 255) & 255) << 8) | (UByteArray.m4577getimpl(bytes, 2) & 255)), (short) ((UByteArray.m4577getimpl(bytes, 4) & 255) | (((UByteArray.m4577getimpl(bytes, 3) & 255) & 255) << 8)));
        this.this$0.setReceivedEncoder(true);
        this.this$0.getSensorDataProvider().notify(new Function2<ISensorData, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$parseEncoder$1.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ISensorData iSensorData, String str4) {
                invoke2(iSensorData, str4);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ISensorData it, String str4) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str4, "<anonymous parameter 1>");
                it.onEncoder(CANBus$parseEncoder$1.this.this$0.getEncoder().get_distance().getLeft(), CANBus$parseEncoder$1.this.this$0.getEncoder().get_distance().getRight(), 0.025d);
                it.onSpeed(CANBus$parseEncoder$1.this.this$0.getEncoder().get_speed().getLineSpeed(), CANBus$parseEncoder$1.this.this$0.getEncoder().get_speed().getAngularSpeed(), 0.025d);
            }
        });
        if (!this.this$0.getEncoder().getHasSetConfigure()) {
            str3 = this.this$0.TAG;
            Pdlog.m3273d(str3, "Encoder's configuration is not set");
            return;
        }
        job = this.this$0.controlWheelJob;
        if (job == null || !job.isActive()) {
            return;
        }
        atomicBoolean = this.this$0.checkJobFlag;
        if (atomicBoolean.get()) {
            return;
        }
        if (Math.abs(this.this$0.getEncoder().get_speed().getLineSpeed()) > 0.15d || Math.abs(this.this$0.getEncoder().get_speed().getAngularSpeed()) > 0.25d) {
            atomicBoolean2 = this.this$0.checkJobFlag;
            atomicBoolean2.set(true);
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "check to cancel wheel encoder.speed.lineSpeed=" + this.this$0.getEncoder().get_speed().getLineSpeed() + ",encoder.speed.angularSpeed=" + this.this$0.getEncoder().get_speed().getAngularSpeed());
            BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new C51562(null), 3, null);
            atomicBoolean3 = this.this$0.checkJobFlag;
            atomicBoolean3.set(false);
            str2 = this.this$0.TAG;
            Pdlog.m3273d(str2, "check to cancel wheel end");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CANBus.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.can.CANBus$parseEncoder$1$2", m3970f = "CANBus.kt", m3971i = {0}, m3972l = {633}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
    /* renamed from: com.pudutech.mirsdk.hardware.can.CANBus$parseEncoder$1$2 */
    /* loaded from: classes5.dex */
    public static final class C51562 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6038p$;

        C51562(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C51562 c51562 = new C51562(completion);
            c51562.f6038p$ = (CoroutineScope) obj;
            return c51562;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C51562) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            String str;
            Job job;
            String str2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f6038p$;
                str = CANBus$parseEncoder$1.this.this$0.TAG;
                Pdlog.m3273d(str, "cancel wheel start");
                CANBus$parseEncoder$1.this.this$0.notifyChangeStateChargingPile(false);
                job = CANBus$parseEncoder$1.this.this$0.controlWheelJob;
                if (job != null) {
                    this.L$0 = coroutineScope;
                    this.label = 1;
                    if (JobKt.cancelAndJoin(job, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            CANBus$parseEncoder$1.this.this$0.controlWheel(0.0d, 0.0d, false);
            str2 = CANBus$parseEncoder$1.this.this$0.TAG;
            Pdlog.m3273d(str2, "cancel wheel end");
            return Unit.INSTANCE;
        }
    }
}
