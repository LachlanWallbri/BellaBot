package com.pudutech.mirsdk.hardware;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1$initSchedulerResult$1", m3970f = "HardwareInterfaceStub.kt", m3971i = {0}, m3972l = {153}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull"}, m3975s = {"L$0"})
/* loaded from: classes.dex */
final class HardwareInterfaceStub$open$1$initSchedulerResult$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5917p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HardwareInterfaceStub$open$1$initSchedulerResult$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareInterfaceStub$open$1$initSchedulerResult$1 hardwareInterfaceStub$open$1$initSchedulerResult$1 = new HardwareInterfaceStub$open$1$initSchedulerResult$1(completion);
        hardwareInterfaceStub$open$1$initSchedulerResult$1.f5917p$ = (CoroutineScope) obj;
        return hardwareInterfaceStub$open$1$initSchedulerResult$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((HardwareInterfaceStub$open$1$initSchedulerResult$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1$initSchedulerResult$1$1 */
    /* loaded from: classes.dex */
    final /* synthetic */ class C50331 extends MutablePropertyReference0 {
        C50331(HardwareInterfaceStub hardwareInterfaceStub) {
            super(hardwareInterfaceStub);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public String getName() {
            return "scheduler";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(HardwareInterfaceStub.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public String getSignature() {
            return "getScheduler()Lcom/pudutech/mirsdk/hardware/ScheduleCommunicationImpl;";
        }

        @Override // kotlin.reflect.KProperty0
        public Object get() {
            return HardwareInterfaceStub.access$getScheduler$p((HardwareInterfaceStub) this.receiver);
        }

        @Override // kotlin.reflect.KMutableProperty0
        public void set(Object obj) {
            HardwareInterfaceStub.scheduler = (ScheduleCommunicationImpl) obj;
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        ScheduleCommunicationImpl scheduleCommunicationImpl;
        ScheduleCommunicationImpl scheduleCommunicationImpl2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5917p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        do {
            HardwareInterfaceStub hardwareInterfaceStub = HardwareInterfaceStub.INSTANCE;
            scheduleCommunicationImpl = HardwareInterfaceStub.scheduler;
            if (scheduleCommunicationImpl == null) {
                this.L$0 = coroutineScope;
                this.label = 1;
            } else {
                HardwareInterfaceStub hardwareInterfaceStub2 = HardwareInterfaceStub.INSTANCE;
                scheduleCommunicationImpl2 = HardwareInterfaceStub.scheduler;
                return Boxing.boxBoolean(scheduleCommunicationImpl2 != null);
            }
        } while (DelayKt.delay(50L, this) != coroutine_suspended);
        return coroutine_suspended;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1$initSchedulerResult$1$2 */
    /* loaded from: classes.dex */
    final /* synthetic */ class C50342 extends MutablePropertyReference0 {
        C50342(HardwareInterfaceStub hardwareInterfaceStub) {
            super(hardwareInterfaceStub);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public String getName() {
            return "scheduler";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(HardwareInterfaceStub.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public String getSignature() {
            return "getScheduler()Lcom/pudutech/mirsdk/hardware/ScheduleCommunicationImpl;";
        }

        @Override // kotlin.reflect.KProperty0
        public Object get() {
            return HardwareInterfaceStub.access$getScheduler$p((HardwareInterfaceStub) this.receiver);
        }

        @Override // kotlin.reflect.KMutableProperty0
        public void set(Object obj) {
            HardwareInterfaceStub.scheduler = (ScheduleCommunicationImpl) obj;
        }
    }
}
