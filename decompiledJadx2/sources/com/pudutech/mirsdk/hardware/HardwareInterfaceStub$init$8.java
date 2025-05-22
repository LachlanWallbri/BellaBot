package com.pudutech.mirsdk.hardware;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub$init$8", m3970f = "HardwareInterfaceStub.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes.dex */
public final class HardwareInterfaceStub$init$8 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5912p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardwareInterfaceStub$init$8(Context context, Continuation continuation) {
        super(2, continuation);
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareInterfaceStub$init$8 hardwareInterfaceStub$init$8 = new HardwareInterfaceStub$init$8(this.$context, completion);
        hardwareInterfaceStub$init$8.f5912p$ = (CoroutineScope) obj;
        return hardwareInterfaceStub$init$8;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareInterfaceStub$init$8) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        ScheduleCommunicationImpl scheduleCommunicationImpl;
        String wIFIMac;
        MachineInfoProcess machineInfoProcess;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean z = SpUtils.get(this.$context, "mirhardware", "esp_enable", true);
        HardwareInterfaceStub hardwareInterfaceStub = HardwareInterfaceStub.INSTANCE;
        str = HardwareInterfaceStub.TAG;
        Pdlog.m3275i(str, "enableEsp:" + z);
        HardwareInterfaceStub hardwareInterfaceStub2 = HardwareInterfaceStub.INSTANCE;
        scheduleCommunicationImpl = HardwareInterfaceStub.scheduler;
        if (scheduleCommunicationImpl == null) {
            HardwareInterfaceStub hardwareInterfaceStub3 = HardwareInterfaceStub.INSTANCE;
            Context context = this.$context;
            HardwareInterfaceStub hardwareInterfaceStub4 = HardwareInterfaceStub.INSTANCE;
            machineInfoProcess = HardwareInterfaceStub.machineInfoProcess;
            HardwareInterfaceStub.scheduler = new ScheduleCommunicationImpl(context, machineInfoProcess, z);
        }
        HardwareConfig hardwareConfig = HardwareConfig.INSTANCE;
        wIFIMac = HardwareInterfaceStub.INSTANCE.getWIFIMac();
        hardwareConfig.setMAC(wIFIMac);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$init$8$1 */
    /* loaded from: classes.dex */
    final /* synthetic */ class C50031 extends MutablePropertyReference0 {
        C50031(HardwareInterfaceStub hardwareInterfaceStub) {
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
