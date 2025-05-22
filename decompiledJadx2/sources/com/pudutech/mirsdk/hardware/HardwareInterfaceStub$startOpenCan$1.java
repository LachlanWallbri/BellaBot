package com.pudutech.mirsdk.hardware;

import com.felhr.usbserial.FTDISerialDevice;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep;
import com.pudutech.mirsdk.hardware.serialize.StepState;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub$startOpenCan$1", m3970f = "HardwareInterfaceStub.kt", m3971i = {0, 0, 1, 1, 2}, m3972l = {622, FTDISerialDevice.FTDI_BAUDRATE_4800, 628}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "success", "$this$launch", "success", "$this$launch"}, m3975s = {"L$0", "I$0", "L$0", "I$0", "L$0"})
/* loaded from: classes5.dex */
final class HardwareInterfaceStub$startOpenCan$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5927p$;

    HardwareInterfaceStub$startOpenCan$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareInterfaceStub$startOpenCan$1 hardwareInterfaceStub$startOpenCan$1 = new HardwareInterfaceStub$startOpenCan$1(completion);
        hardwareInterfaceStub$startOpenCan$1.f5927p$ = (CoroutineScope) obj;
        return hardwareInterfaceStub$startOpenCan$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareInterfaceStub$startOpenCan$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x00a0 A[Catch: all -> 0x003a, Exception -> 0x003d, TryCatch #0 {Exception -> 0x003d, blocks: (B:8:0x0019, B:9:0x0098, B:11:0x00a0, B:16:0x00b7, B:20:0x002c, B:21:0x0082, B:24:0x008b, B:28:0x0036, B:29:0x0069, B:32:0x0072, B:37:0x0045), top: B:2:0x000d, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00b7 A[Catch: all -> 0x003a, Exception -> 0x003d, TRY_LEAVE, TryCatch #0 {Exception -> 0x003d, blocks: (B:8:0x0019, B:9:0x0098, B:11:0x00a0, B:16:0x00b7, B:20:0x002c, B:21:0x0082, B:24:0x008b, B:28:0x0036, B:29:0x0069, B:32:0x0072, B:37:0x0045), top: B:2:0x000d, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x008b A[Catch: all -> 0x003a, Exception -> 0x003d, TryCatch #0 {Exception -> 0x003d, blocks: (B:8:0x0019, B:9:0x0098, B:11:0x00a0, B:16:0x00b7, B:20:0x002c, B:21:0x0082, B:24:0x008b, B:28:0x0036, B:29:0x0069, B:32:0x0072, B:37:0x0045), top: B:2:0x000d, outer: #1 }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ThreadSafeListener threadSafeListener;
        AtomicBoolean atomicBoolean;
        CoroutineScope coroutineScope;
        int i;
        CoroutineScope coroutineScope2;
        int i2;
        int i3;
        ThreadSafeListener threadSafeListener2;
        ThreadSafeListener threadSafeListener3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = this.label;
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
                threadSafeListener.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$startOpenCan$1.3
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
            if (i4 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope3 = this.f5927p$;
                Pdlog.m3275i("Hardware", "open CAN async");
                HardwareInterfaceStub hardwareInterfaceStub2 = HardwareInterfaceStub.INSTANCE;
                atomicBoolean = HardwareInterfaceStub.waitSelfCheck;
                atomicBoolean.set(true);
                HardwareInterfaceStub hardwareInterfaceStub3 = HardwareInterfaceStub.INSTANCE;
                this.L$0 = coroutineScope3;
                this.I$0 = 0;
                this.label = 1;
                Object bootUpCAN = hardwareInterfaceStub3.bootUpCAN(this);
                if (bootUpCAN == coroutine_suspended) {
                    return coroutine_suspended;
                }
                coroutineScope = coroutineScope3;
                obj = bootUpCAN;
                i = 0;
            } else {
                if (i4 != 1) {
                    if (i4 != 2) {
                        if (i4 == 3) {
                            ResultKt.throwOnFailure(obj);
                            i3 = ((Boolean) obj).booleanValue();
                            if (i3 != 0) {
                                Pdlog.m3274e("Hardware", "CAN boot up fail");
                                HardwareInterfaceStub hardwareInterfaceStub4 = HardwareInterfaceStub.INSTANCE;
                                threadSafeListener3 = HardwareInterfaceStub.hardwareListener;
                                threadSafeListener3.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$startOpenCan$1.1
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str) {
                                        invoke2(iHardware, str);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(IHardware l, String str) {
                                        Intrinsics.checkParameterIsNotNull(l, "l");
                                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                                        l.onOpenStep(HardwareOpenStep.Finish, StepState.Fail, "CAN boot up fail");
                                    }
                                });
                            } else {
                                HardwareInterfaceStub hardwareInterfaceStub5 = HardwareInterfaceStub.INSTANCE;
                                threadSafeListener2 = HardwareInterfaceStub.hardwareListener;
                                threadSafeListener2.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$startOpenCan$1.2
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str) {
                                        invoke2(iHardware, str);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(IHardware l, String str) {
                                        Intrinsics.checkParameterIsNotNull(l, "l");
                                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                                        l.onOpenStep(HardwareOpenStep.Finish, StepState.Success, "");
                                    }
                                });
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    int i5 = this.I$0;
                    coroutineScope2 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    i2 = i5;
                    if (((Boolean) obj).booleanValue()) {
                        HardwareInterfaceStub hardwareInterfaceStub6 = HardwareInterfaceStub.INSTANCE;
                        this.L$0 = coroutineScope2;
                        this.label = 3;
                        obj = hardwareInterfaceStub6.checkSensor(this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        i3 = ((Boolean) obj).booleanValue();
                        if (i3 != 0) {
                        }
                        return Unit.INSTANCE;
                    }
                    i3 = i2;
                    if (i3 != 0) {
                    }
                    return Unit.INSTANCE;
                }
                int i6 = this.I$0;
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                i = i6;
            }
            if (((Boolean) obj).booleanValue()) {
                HardwareInterfaceStub hardwareInterfaceStub7 = HardwareInterfaceStub.INSTANCE;
                this.L$0 = coroutineScope;
                this.I$0 = i;
                this.label = 2;
                obj = hardwareInterfaceStub7.notFetchMachineInfo(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                coroutineScope2 = coroutineScope;
                i2 = i;
                if (((Boolean) obj).booleanValue()) {
                }
            } else {
                i3 = i;
                if (i3 != 0) {
                }
                return Unit.INSTANCE;
            }
        } finally {
            HardwareInterfaceStub hardwareInterfaceStub8 = HardwareInterfaceStub.INSTANCE;
            HardwareInterfaceStub.openJob = (Job) null;
        }
    }
}
