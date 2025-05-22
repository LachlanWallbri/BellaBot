package com.pudutech.mirsdk.hardware;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep;
import com.pudutech.mirsdk.hardware.serialize.StepState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;

/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub$startOpenHardwareJob$1", m3970f = "HardwareInterfaceStub.kt", m3971i = {0, 0, 1, 1}, m3972l = {134, 136}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "startInitTime", "$this$launch", "startInitTime"}, m3975s = {"L$0", "J$0", "L$0", "J$0"})
/* loaded from: classes5.dex */
final class HardwareInterfaceStub$startOpenHardwareJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    long J$0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5929p$;

    HardwareInterfaceStub$startOpenHardwareJob$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareInterfaceStub$startOpenHardwareJob$1 hardwareInterfaceStub$startOpenHardwareJob$1 = new HardwareInterfaceStub$startOpenHardwareJob$1(completion);
        hardwareInterfaceStub$startOpenHardwareJob$1.f5929p$ = (CoroutineScope) obj;
        return hardwareInterfaceStub$startOpenHardwareJob$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareInterfaceStub$startOpenHardwareJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ThreadSafeListener threadSafeListener;
        long elapsedRealtime;
        CoroutineScope coroutineScope;
        RGBDInterfaceImpl rGBDInterfaceImpl;
        RGBDInterfaceImpl rGBDInterfaceImpl2;
        ScheduleCommunicationImpl scheduleCommunicationImpl;
        ScheduleCommunicationImpl scheduleCommunicationImpl2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            try {
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Self check exception:");
                e.printStackTrace();
                sb.append(Unit.INSTANCE);
                Pdlog.m3274e("Hardware", sb.toString());
                HardwareInterfaceStub hardwareInterfaceStub = HardwareInterfaceStub.INSTANCE;
                threadSafeListener = HardwareInterfaceStub.hardwareListener;
                threadSafeListener.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$startOpenHardwareJob$1.5
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str) {
                        invoke2(iHardware, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IHardware l, String str) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        HardwareOpenStep hardwareOpenStep = HardwareOpenStep.Finish;
                        StepState stepState = StepState.Fail;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Self check exception: ");
                        e.printStackTrace();
                        sb2.append(Unit.INSTANCE);
                        l.onOpenStep(hardwareOpenStep, stepState, sb2.toString());
                    }
                });
            }
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope2 = this.f5929p$;
                Pdlog.m3275i("Hardware", "open hardware async");
                elapsedRealtime = SystemClock.elapsedRealtime();
                coroutineScope = coroutineScope2;
            } else {
                if (i != 1) {
                    if (i == 2) {
                        long j = this.J$0;
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                elapsedRealtime = this.J$0;
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            while (CoroutineScopeKt.isActive(coroutineScope)) {
                HardwareInterfaceStub hardwareInterfaceStub2 = HardwareInterfaceStub.INSTANCE;
                rGBDInterfaceImpl = HardwareInterfaceStub.rgbdInterfaceImpl;
                if (rGBDInterfaceImpl != null) {
                    HardwareInterfaceStub hardwareInterfaceStub3 = HardwareInterfaceStub.INSTANCE;
                    scheduleCommunicationImpl2 = HardwareInterfaceStub.scheduler;
                    if (scheduleCommunicationImpl2 != null) {
                        break;
                    }
                }
                Object[] objArr = new Object[1];
                StringBuilder sb2 = new StringBuilder();
                sb2.append("wait rgbd or scheduler interface initialized ");
                HardwareInterfaceStub hardwareInterfaceStub4 = HardwareInterfaceStub.INSTANCE;
                rGBDInterfaceImpl2 = HardwareInterfaceStub.rgbdInterfaceImpl;
                sb2.append(rGBDInterfaceImpl2 != null);
                sb2.append(" && ");
                HardwareInterfaceStub hardwareInterfaceStub5 = HardwareInterfaceStub.INSTANCE;
                scheduleCommunicationImpl = HardwareInterfaceStub.scheduler;
                sb2.append(scheduleCommunicationImpl != null);
                objArr[0] = sb2.toString();
                Pdlog.m3273d("Hardware", objArr);
                this.L$0 = coroutineScope;
                this.J$0 = elapsedRealtime;
                this.label = 1;
                if (DelayKt.delay(50L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            HardwareInterfaceStub hardwareInterfaceStub6 = HardwareInterfaceStub.INSTANCE;
            this.L$0 = coroutineScope;
            this.J$0 = elapsedRealtime;
            this.label = 2;
            if (hardwareInterfaceStub6.startSelfCheck(elapsedRealtime, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        } finally {
            HardwareInterfaceStub hardwareInterfaceStub7 = HardwareInterfaceStub.INSTANCE;
            HardwareInterfaceStub.openJob = (Job) null;
        }
    }

    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$startOpenHardwareJob$1$1 */
    /* loaded from: classes5.dex */
    final /* synthetic */ class C50691 extends MutablePropertyReference0 {
        C50691(HardwareInterfaceStub hardwareInterfaceStub) {
            super(hardwareInterfaceStub);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public String getName() {
            return "rgbdInterfaceImpl";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(HardwareInterfaceStub.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public String getSignature() {
            return "getRgbdInterfaceImpl()Lcom/pudutech/mirsdk/hardware/RGBDInterfaceImpl;";
        }

        @Override // kotlin.reflect.KProperty0
        public Object get() {
            return HardwareInterfaceStub.access$getRgbdInterfaceImpl$p((HardwareInterfaceStub) this.receiver);
        }

        @Override // kotlin.reflect.KMutableProperty0
        public void set(Object obj) {
            HardwareInterfaceStub.rgbdInterfaceImpl = (RGBDInterfaceImpl) obj;
        }
    }

    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$startOpenHardwareJob$1$2 */
    /* loaded from: classes5.dex */
    final /* synthetic */ class C50702 extends MutablePropertyReference0 {
        C50702(HardwareInterfaceStub hardwareInterfaceStub) {
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

    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$startOpenHardwareJob$1$3 */
    /* loaded from: classes5.dex */
    final /* synthetic */ class C50713 extends MutablePropertyReference0 {
        C50713(HardwareInterfaceStub hardwareInterfaceStub) {
            super(hardwareInterfaceStub);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public String getName() {
            return "rgbdInterfaceImpl";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(HardwareInterfaceStub.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public String getSignature() {
            return "getRgbdInterfaceImpl()Lcom/pudutech/mirsdk/hardware/RGBDInterfaceImpl;";
        }

        @Override // kotlin.reflect.KProperty0
        public Object get() {
            return HardwareInterfaceStub.access$getRgbdInterfaceImpl$p((HardwareInterfaceStub) this.receiver);
        }

        @Override // kotlin.reflect.KMutableProperty0
        public void set(Object obj) {
            HardwareInterfaceStub.rgbdInterfaceImpl = (RGBDInterfaceImpl) obj;
        }
    }

    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$startOpenHardwareJob$1$4 */
    /* loaded from: classes5.dex */
    final /* synthetic */ class C50724 extends MutablePropertyReference0 {
        C50724(HardwareInterfaceStub hardwareInterfaceStub) {
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
