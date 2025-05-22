package com.pudutech.mirsdk.hardware;

import com.google.zxing.pdf417.PDF417Common;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import java.util.concurrent.atomic.AtomicBoolean;
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
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub$brake$1", m3970f = "HardwareInterfaceStub.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes.dex */
public final class HardwareInterfaceStub$brake$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5897p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HardwareInterfaceStub$brake$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareInterfaceStub$brake$1 hardwareInterfaceStub$brake$1 = new HardwareInterfaceStub$brake$1(completion);
        hardwareInterfaceStub$brake$1.f5897p$ = (CoroutineScope) obj;
        return hardwareInterfaceStub$brake$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareInterfaceStub$brake$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Job job;
        String str;
        ThreadSafeListener threadSafeListener;
        Job launch$default;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        HardwareInterfaceStub hardwareInterfaceStub = HardwareInterfaceStub.INSTANCE;
        job = HardwareInterfaceStub.brakeJob;
        if (job != null) {
            return Unit.INSTANCE;
        }
        HardwareInterfaceStub hardwareInterfaceStub2 = HardwareInterfaceStub.INSTANCE;
        str = HardwareInterfaceStub.TAG;
        Pdlog.m3273d(str, "HarewareEmergencyBrake on");
        HardwareInterfaceStub hardwareInterfaceStub3 = HardwareInterfaceStub.INSTANCE;
        threadSafeListener = HardwareInterfaceStub.hardwareListener;
        threadSafeListener.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$brake$1.1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str2) {
                invoke2(iHardware, str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IHardware it, String str2) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                it.onHarewareEmergencyBrake(true);
            }
        });
        HardwareInterfaceStub hardwareInterfaceStub4 = HardwareInterfaceStub.INSTANCE;
        launch$default = BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new C49692(null), 3, null);
        HardwareInterfaceStub.brakeJob = launch$default;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub$brake$1$2", m3970f = "HardwareInterfaceStub.kt", m3971i = {0, 1}, m3972l = {PDF417Common.MAX_CODEWORDS_IN_BARCODE, 931}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$brake$1$2 */
    /* loaded from: classes.dex */
    public static final class C49692 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5898p$;

        C49692(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C49692 c49692 = new C49692(completion);
            c49692.f5898p$ = (CoroutineScope) obj;
            return c49692;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C49692) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            AtomicBoolean atomicBoolean;
            ThreadSafeListener threadSafeListener;
            String str;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f5898p$;
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        HardwareInterfaceStub.INSTANCE.controlWheel(0.0d, 0.0d, false);
                        HardwareInterfaceStub hardwareInterfaceStub = HardwareInterfaceStub.INSTANCE;
                        threadSafeListener = HardwareInterfaceStub.hardwareListener;
                        threadSafeListener.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub.brake.1.2.1
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str2) {
                                invoke2(iHardware, str2);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(IHardware it, String str2) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                                it.onHarewareEmergencyBrake(false);
                            }
                        });
                        HardwareInterfaceStub hardwareInterfaceStub2 = HardwareInterfaceStub.INSTANCE;
                        str = HardwareInterfaceStub.TAG;
                        Pdlog.m3273d(str, "HarewareEmergencyBrake off");
                        HardwareInterfaceStub hardwareInterfaceStub3 = HardwareInterfaceStub.INSTANCE;
                        HardwareInterfaceStub.brakeJob = (Job) null;
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            while (CoroutineScopeKt.isActive(coroutineScope)) {
                HardwareInterfaceStub hardwareInterfaceStub4 = HardwareInterfaceStub.INSTANCE;
                atomicBoolean = HardwareInterfaceStub.enableBrake;
                if (!atomicBoolean.get()) {
                    break;
                }
                HardwareInterfaceStub.INSTANCE.controlWheel(0.0d, 0.0d, true);
                this.L$0 = coroutineScope;
                this.label = 1;
                if (DelayKt.delay(100L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            HardwareInterfaceStub.INSTANCE.controlWheel(0.0d, 0.0d, true);
            this.L$0 = coroutineScope;
            this.label = 2;
            if (DelayKt.delay(100L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            HardwareInterfaceStub.INSTANCE.controlWheel(0.0d, 0.0d, false);
            HardwareInterfaceStub hardwareInterfaceStub5 = HardwareInterfaceStub.INSTANCE;
            threadSafeListener = HardwareInterfaceStub.hardwareListener;
            threadSafeListener.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub.brake.1.2.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str2) {
                    invoke2(iHardware, str2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IHardware it, String str2) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                    it.onHarewareEmergencyBrake(false);
                }
            });
            HardwareInterfaceStub hardwareInterfaceStub22 = HardwareInterfaceStub.INSTANCE;
            str = HardwareInterfaceStub.TAG;
            Pdlog.m3273d(str, "HarewareEmergencyBrake off");
            HardwareInterfaceStub hardwareInterfaceStub32 = HardwareInterfaceStub.INSTANCE;
            HardwareInterfaceStub.brakeJob = (Job) null;
            return Unit.INSTANCE;
        }
    }
}
