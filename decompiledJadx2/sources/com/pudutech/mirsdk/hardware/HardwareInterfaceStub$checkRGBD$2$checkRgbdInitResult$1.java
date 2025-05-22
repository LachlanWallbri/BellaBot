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
  classes5.dex
 */
/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkRGBD$2$checkRgbdInitResult$1", m3970f = "HardwareInterfaceStub.kt", m3971i = {0}, m3972l = {629}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull"}, m3975s = {"L$0"})
/* loaded from: classes.dex */
final class HardwareInterfaceStub$checkRGBD$2$checkRgbdInitResult$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5908p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HardwareInterfaceStub$checkRGBD$2$checkRgbdInitResult$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareInterfaceStub$checkRGBD$2$checkRgbdInitResult$1 hardwareInterfaceStub$checkRGBD$2$checkRgbdInitResult$1 = new HardwareInterfaceStub$checkRGBD$2$checkRgbdInitResult$1(completion);
        hardwareInterfaceStub$checkRGBD$2$checkRgbdInitResult$1.f5908p$ = (CoroutineScope) obj;
        return hardwareInterfaceStub$checkRGBD$2$checkRgbdInitResult$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((HardwareInterfaceStub$checkRGBD$2$checkRgbdInitResult$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkRGBD$2$checkRgbdInitResult$1$1 */
    /* loaded from: classes.dex */
    final /* synthetic */ class C50011 extends MutablePropertyReference0 {
        C50011(HardwareInterfaceStub hardwareInterfaceStub) {
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

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        RGBDInterfaceImpl rGBDInterfaceImpl;
        RGBDInterfaceImpl rGBDInterfaceImpl2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5908p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        do {
            HardwareInterfaceStub hardwareInterfaceStub = HardwareInterfaceStub.INSTANCE;
            rGBDInterfaceImpl = HardwareInterfaceStub.rgbdInterfaceImpl;
            if (rGBDInterfaceImpl == null) {
                this.L$0 = coroutineScope;
                this.label = 1;
            } else {
                HardwareInterfaceStub hardwareInterfaceStub2 = HardwareInterfaceStub.INSTANCE;
                rGBDInterfaceImpl2 = HardwareInterfaceStub.rgbdInterfaceImpl;
                return Boxing.boxBoolean(rGBDInterfaceImpl2 != null);
            }
        } while (DelayKt.delay(50L, this) != coroutine_suspended);
        return coroutine_suspended;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkRGBD$2$checkRgbdInitResult$1$2 */
    /* loaded from: classes.dex */
    final /* synthetic */ class C50022 extends MutablePropertyReference0 {
        C50022(HardwareInterfaceStub hardwareInterfaceStub) {
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
}
