package com.pudutech.mirsdk.hardware;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.HardwareInterfaceStub;
import com.pudutech.mirsdk.hardware.can.CANBus;
import com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.StepState;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.TimeoutKt;
import org.apache.http.HttpStatus;
import org.jetbrains.anko.DimensionsKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1", m3970f = "HardwareInterfaceStub.kt", m3971i = {0, 0, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16}, m3972l = {103, 110, 128, 139, 151, 160, 193, 194, 195, HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION, HttpStatus.SC_RESET_CONTENT, HttpStatus.SC_MULTI_STATUS, DimensionsKt.TVDPI, 214, 215, 216, 217}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "startInitTime", "$this$launch", "startInitTime", "success", "$this$launch", "startInitTime", "success", "canBootUpResult", "$this$launch", "startInitTime", "success", "canBootUpResult", "fetchResult", "$this$launch", "startInitTime", "success", "canBootUpResult", "fetchResult", "checkTime", "$this$launch", "startInitTime", "success", "canBootUpResult", "fetchResult", "checkTime", "initSchedulerResult", "$this$launch", "startInitTime", "success", "canBootUpResult", "fetchResult", "checkTime", "initSchedulerResult", "sensorList", "selfCheckInfo", "$this$launch", "startInitTime", "success", "canBootUpResult", "fetchResult", "checkTime", "initSchedulerResult", "sensorList", "selfCheckInfo", "imuJob", "$this$launch", "startInitTime", "success", "canBootUpResult", "fetchResult", "checkTime", "initSchedulerResult", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "$this$launch", "startInitTime", "success", "canBootUpResult", "fetchResult", "checkTime", "initSchedulerResult", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "rgbdJob", "$this$launch", "startInitTime", "success", "canBootUpResult", "fetchResult", "checkTime", "initSchedulerResult", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "rgbdJob", "cameraJob", "$this$launch", "startInitTime", "success", "canBootUpResult", "fetchResult", "checkTime", "initSchedulerResult", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "rgbdJob", "cameraJob", "lidarJob", "$this$launch", "startInitTime", "success", "canBootUpResult", "fetchResult", "checkTime", "initSchedulerResult", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "rgbdJob", "cameraJob", "lidarJob", "$this$launch", "startInitTime", "success", "canBootUpResult", "fetchResult", "checkTime", "initSchedulerResult", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "rgbdJob", "cameraJob", "lidarJob", "$this$launch", "startInitTime", "success", "canBootUpResult", "fetchResult", "checkTime", "initSchedulerResult", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "rgbdJob", "cameraJob", "lidarJob", "$this$launch", "startInitTime", "success", "canBootUpResult", "fetchResult", "checkTime", "initSchedulerResult", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "rgbdJob", "cameraJob", "lidarJob", "$this$launch", "startInitTime", "success", "canBootUpResult", "fetchResult", "checkTime", "initSchedulerResult", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "rgbdJob", "cameraJob", "lidarJob"}, m3975s = {"L$0", "J$0", "L$0", "J$0", "I$0", "L$0", "J$0", "I$0", "L$1", "L$0", "J$0", "I$0", "L$1", "L$2", "L$0", "J$0", "I$0", "L$1", "L$2", "J$1", "L$0", "J$0", "I$0", "L$1", "L$2", "J$1", "L$3", "L$0", "J$0", "I$0", "L$1", "L$2", "J$1", "L$3", "L$4", "L$5", "L$0", "J$0", "I$0", "L$1", "L$2", "J$1", "L$3", "L$4", "L$5", "L$6", "L$0", "J$0", "I$0", "L$1", "L$2", "J$1", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "J$0", "I$0", "L$1", "L$2", "J$1", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "J$0", "I$0", "L$1", "L$2", "J$1", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$0", "J$0", "I$0", "L$1", "L$2", "J$1", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$0", "J$0", "I$0", "L$1", "L$2", "J$1", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$0", "J$0", "I$0", "L$1", "L$2", "J$1", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$0", "J$0", "I$0", "L$1", "L$2", "J$1", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$0", "J$0", "I$0", "L$1", "L$2", "J$1", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$0", "J$0", "I$0", "L$1", "L$2", "J$1", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10"})
/* loaded from: classes.dex */
final class HardwareInterfaceStub$open$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    long J$0;
    long J$1;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5913p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HardwareInterfaceStub$open$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareInterfaceStub$open$1 hardwareInterfaceStub$open$1 = new HardwareInterfaceStub$open$1(completion);
        hardwareInterfaceStub$open$1.f5913p$ = (CoroutineScope) obj;
        return hardwareInterfaceStub$open$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareInterfaceStub$open$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "l", "Lcom/pudutech/mirsdk/hardware/IHardware;", "<anonymous parameter 1>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1$21 */
    /* loaded from: classes.dex */
    static final class C501721 extends Lambda implements Function2<IHardware, String, Unit> {
        public static final C501721 INSTANCE = new C501721();

        C501721() {
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
            l.onOpenStep(HardwareOpenStep.Finish, StepState.Success, "");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "l", "Lcom/pudutech/mirsdk/hardware/IHardware;", "<anonymous parameter 1>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1$22 */
    /* loaded from: classes.dex */
    static final class C501822 extends Lambda implements Function2<IHardware, String, Unit> {
        public static final C501822 INSTANCE = new C501822();

        C501822() {
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
            l.onOpenStep(HardwareOpenStep.Finish, StepState.Fail, "hardware boot up fail");
        }
    }

    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "l", "Lcom/pudutech/mirsdk/hardware/IHardware;", "<anonymous parameter 1>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1$24 */
    /* loaded from: classes4.dex */
    static final class C502024 extends Lambda implements Function2<IHardware, String, Unit> {
        public static final C502024 INSTANCE = new C502024();

        C502024() {
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
            l.onOpenStep(HardwareOpenStep.ESPCheck, StepState.Fail, "Connect ESP Overtime");
        }
    }

    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "l", "Lcom/pudutech/mirsdk/hardware/IHardware;", "<anonymous parameter 1>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1$25 */
    /* loaded from: classes4.dex */
    static final class C502125 extends Lambda implements Function2<IHardware, String, Unit> {
        public static final C502125 INSTANCE = new C502125();

        C502125() {
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
            l.onOpenStep(HardwareOpenStep.ESPCheck, StepState.Success, "");
        }
    }

    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "l", "Lcom/pudutech/mirsdk/hardware/IHardware;", "<anonymous parameter 1>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1$26 */
    /* loaded from: classes4.dex */
    static final class C502226 extends Lambda implements Function2<IHardware, String, Unit> {
        public static final C502226 INSTANCE = new C502226();

        C502226() {
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
            l.onOpenStep(HardwareOpenStep.ESPCheck, StepState.Fail, "Schedule Communication Module Connect Overtime");
        }
    }

    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "l", "Lcom/pudutech/mirsdk/hardware/IHardware;", "<anonymous parameter 1>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1$27 */
    /* loaded from: classes4.dex */
    static final class C502327 extends Lambda implements Function2<IHardware, String, Unit> {
        public static final C502327 INSTANCE = new C502327();

        C502327() {
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
            l.onOpenStep(HardwareOpenStep.Finish, StepState.Success, "");
        }
    }

    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "l", "Lcom/pudutech/mirsdk/hardware/IHardware;", "<anonymous parameter 1>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1$28 */
    /* loaded from: classes4.dex */
    static final class C502428 extends Lambda implements Function2<IHardware, String, Unit> {
        public static final C502428 INSTANCE = new C502428();

        C502428() {
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
            l.onOpenStep(HardwareOpenStep.Finish, StepState.Fail, "hardware boot up fail");
        }
    }

    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "l", "Lcom/pudutech/mirsdk/hardware/IHardware;", "<anonymous parameter 1>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1$29 */
    /* loaded from: classes4.dex */
    static final class C502529 extends Lambda implements Function2<IHardware, String, Unit> {

        /* renamed from: $e */
        final /* synthetic */ Exception f5916$e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C502529(Exception exc) {
            super(2);
            this.f5916$e = exc;
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
            StringBuilder sb = new StringBuilder();
            sb.append("Self check exception: ");
            this.f5916$e.printStackTrace();
            sb.append(Unit.INSTANCE);
            l.onOpenStep(hardwareOpenStep, stepState, sb.toString());
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:114:0x06d5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x06d6  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x06a5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:122:0x06a6  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0622 A[Catch: all -> 0x0a9f, TryCatch #0 {all -> 0x0a9f, blocks: (B:8:0x0045, B:9:0x0a22, B:10:0x0a28, B:12:0x0a2e, B:14:0x0a50, B:15:0x0a6e, B:21:0x0080, B:23:0x09bd, B:25:0x09f5, B:31:0x00c2, B:33:0x094f, B:35:0x0984, B:41:0x010b, B:43:0x0904, B:46:0x0918, B:52:0x0150, B:54:0x08ad, B:56:0x08cd, B:62:0x0195, B:65:0x07e1, B:67:0x07ed, B:73:0x082f, B:75:0x0849, B:77:0x086b, B:84:0x01dd, B:86:0x07c6, B:88:0x0217, B:90:0x0761, B:91:0x0790, B:95:0x0264, B:97:0x06ea, B:99:0x0706, B:101:0x071c, B:104:0x072f, B:108:0x0776, B:110:0x029c, B:112:0x06a8, B:117:0x02cc, B:119:0x067e, B:124:0x02f7, B:126:0x0614, B:128:0x0622, B:129:0x0633, B:134:0x031b, B:136:0x05c3, B:138:0x05d0, B:142:0x0a3f, B:145:0x033c, B:147:0x056b, B:149:0x0573, B:150:0x0593, B:155:0x0358, B:157:0x04f5, B:159:0x04fd, B:160:0x0523, B:165:0x0370, B:167:0x043b, B:169:0x0443, B:170:0x0479, B:175:0x0383, B:177:0x03a7, B:179:0x03ad, B:181:0x03b5, B:189:0x03be, B:192:0x03dc, B:195:0x03ef, B:185:0x040c, B:204:0x038e), top: B:2:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0633 A[Catch: all -> 0x0a9f, TryCatch #0 {all -> 0x0a9f, blocks: (B:8:0x0045, B:9:0x0a22, B:10:0x0a28, B:12:0x0a2e, B:14:0x0a50, B:15:0x0a6e, B:21:0x0080, B:23:0x09bd, B:25:0x09f5, B:31:0x00c2, B:33:0x094f, B:35:0x0984, B:41:0x010b, B:43:0x0904, B:46:0x0918, B:52:0x0150, B:54:0x08ad, B:56:0x08cd, B:62:0x0195, B:65:0x07e1, B:67:0x07ed, B:73:0x082f, B:75:0x0849, B:77:0x086b, B:84:0x01dd, B:86:0x07c6, B:88:0x0217, B:90:0x0761, B:91:0x0790, B:95:0x0264, B:97:0x06ea, B:99:0x0706, B:101:0x071c, B:104:0x072f, B:108:0x0776, B:110:0x029c, B:112:0x06a8, B:117:0x02cc, B:119:0x067e, B:124:0x02f7, B:126:0x0614, B:128:0x0622, B:129:0x0633, B:134:0x031b, B:136:0x05c3, B:138:0x05d0, B:142:0x0a3f, B:145:0x033c, B:147:0x056b, B:149:0x0573, B:150:0x0593, B:155:0x0358, B:157:0x04f5, B:159:0x04fd, B:160:0x0523, B:165:0x0370, B:167:0x043b, B:169:0x0443, B:170:0x0479, B:175:0x0383, B:177:0x03a7, B:179:0x03ad, B:181:0x03b5, B:189:0x03be, B:192:0x03dc, B:195:0x03ef, B:185:0x040c, B:204:0x038e), top: B:2:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0a2e A[Catch: all -> 0x0a9f, TryCatch #0 {all -> 0x0a9f, blocks: (B:8:0x0045, B:9:0x0a22, B:10:0x0a28, B:12:0x0a2e, B:14:0x0a50, B:15:0x0a6e, B:21:0x0080, B:23:0x09bd, B:25:0x09f5, B:31:0x00c2, B:33:0x094f, B:35:0x0984, B:41:0x010b, B:43:0x0904, B:46:0x0918, B:52:0x0150, B:54:0x08ad, B:56:0x08cd, B:62:0x0195, B:65:0x07e1, B:67:0x07ed, B:73:0x082f, B:75:0x0849, B:77:0x086b, B:84:0x01dd, B:86:0x07c6, B:88:0x0217, B:90:0x0761, B:91:0x0790, B:95:0x0264, B:97:0x06ea, B:99:0x0706, B:101:0x071c, B:104:0x072f, B:108:0x0776, B:110:0x029c, B:112:0x06a8, B:117:0x02cc, B:119:0x067e, B:124:0x02f7, B:126:0x0614, B:128:0x0622, B:129:0x0633, B:134:0x031b, B:136:0x05c3, B:138:0x05d0, B:142:0x0a3f, B:145:0x033c, B:147:0x056b, B:149:0x0573, B:150:0x0593, B:155:0x0358, B:157:0x04f5, B:159:0x04fd, B:160:0x0523, B:165:0x0370, B:167:0x043b, B:169:0x0443, B:170:0x0479, B:175:0x0383, B:177:0x03a7, B:179:0x03ad, B:181:0x03b5, B:189:0x03be, B:192:0x03dc, B:195:0x03ef, B:185:0x040c, B:204:0x038e), top: B:2:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:138:0x05d0 A[Catch: all -> 0x0a9f, TryCatch #0 {all -> 0x0a9f, blocks: (B:8:0x0045, B:9:0x0a22, B:10:0x0a28, B:12:0x0a2e, B:14:0x0a50, B:15:0x0a6e, B:21:0x0080, B:23:0x09bd, B:25:0x09f5, B:31:0x00c2, B:33:0x094f, B:35:0x0984, B:41:0x010b, B:43:0x0904, B:46:0x0918, B:52:0x0150, B:54:0x08ad, B:56:0x08cd, B:62:0x0195, B:65:0x07e1, B:67:0x07ed, B:73:0x082f, B:75:0x0849, B:77:0x086b, B:84:0x01dd, B:86:0x07c6, B:88:0x0217, B:90:0x0761, B:91:0x0790, B:95:0x0264, B:97:0x06ea, B:99:0x0706, B:101:0x071c, B:104:0x072f, B:108:0x0776, B:110:0x029c, B:112:0x06a8, B:117:0x02cc, B:119:0x067e, B:124:0x02f7, B:126:0x0614, B:128:0x0622, B:129:0x0633, B:134:0x031b, B:136:0x05c3, B:138:0x05d0, B:142:0x0a3f, B:145:0x033c, B:147:0x056b, B:149:0x0573, B:150:0x0593, B:155:0x0358, B:157:0x04f5, B:159:0x04fd, B:160:0x0523, B:165:0x0370, B:167:0x043b, B:169:0x0443, B:170:0x0479, B:175:0x0383, B:177:0x03a7, B:179:0x03ad, B:181:0x03b5, B:189:0x03be, B:192:0x03dc, B:195:0x03ef, B:185:0x040c, B:204:0x038e), top: B:2:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0a3f A[Catch: all -> 0x0a9f, TryCatch #0 {all -> 0x0a9f, blocks: (B:8:0x0045, B:9:0x0a22, B:10:0x0a28, B:12:0x0a2e, B:14:0x0a50, B:15:0x0a6e, B:21:0x0080, B:23:0x09bd, B:25:0x09f5, B:31:0x00c2, B:33:0x094f, B:35:0x0984, B:41:0x010b, B:43:0x0904, B:46:0x0918, B:52:0x0150, B:54:0x08ad, B:56:0x08cd, B:62:0x0195, B:65:0x07e1, B:67:0x07ed, B:73:0x082f, B:75:0x0849, B:77:0x086b, B:84:0x01dd, B:86:0x07c6, B:88:0x0217, B:90:0x0761, B:91:0x0790, B:95:0x0264, B:97:0x06ea, B:99:0x0706, B:101:0x071c, B:104:0x072f, B:108:0x0776, B:110:0x029c, B:112:0x06a8, B:117:0x02cc, B:119:0x067e, B:124:0x02f7, B:126:0x0614, B:128:0x0622, B:129:0x0633, B:134:0x031b, B:136:0x05c3, B:138:0x05d0, B:142:0x0a3f, B:145:0x033c, B:147:0x056b, B:149:0x0573, B:150:0x0593, B:155:0x0358, B:157:0x04f5, B:159:0x04fd, B:160:0x0523, B:165:0x0370, B:167:0x043b, B:169:0x0443, B:170:0x0479, B:175:0x0383, B:177:0x03a7, B:179:0x03ad, B:181:0x03b5, B:189:0x03be, B:192:0x03dc, B:195:0x03ef, B:185:0x040c, B:204:0x038e), top: B:2:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0573 A[Catch: all -> 0x0a9f, TryCatch #0 {all -> 0x0a9f, blocks: (B:8:0x0045, B:9:0x0a22, B:10:0x0a28, B:12:0x0a2e, B:14:0x0a50, B:15:0x0a6e, B:21:0x0080, B:23:0x09bd, B:25:0x09f5, B:31:0x00c2, B:33:0x094f, B:35:0x0984, B:41:0x010b, B:43:0x0904, B:46:0x0918, B:52:0x0150, B:54:0x08ad, B:56:0x08cd, B:62:0x0195, B:65:0x07e1, B:67:0x07ed, B:73:0x082f, B:75:0x0849, B:77:0x086b, B:84:0x01dd, B:86:0x07c6, B:88:0x0217, B:90:0x0761, B:91:0x0790, B:95:0x0264, B:97:0x06ea, B:99:0x0706, B:101:0x071c, B:104:0x072f, B:108:0x0776, B:110:0x029c, B:112:0x06a8, B:117:0x02cc, B:119:0x067e, B:124:0x02f7, B:126:0x0614, B:128:0x0622, B:129:0x0633, B:134:0x031b, B:136:0x05c3, B:138:0x05d0, B:142:0x0a3f, B:145:0x033c, B:147:0x056b, B:149:0x0573, B:150:0x0593, B:155:0x0358, B:157:0x04f5, B:159:0x04fd, B:160:0x0523, B:165:0x0370, B:167:0x043b, B:169:0x0443, B:170:0x0479, B:175:0x0383, B:177:0x03a7, B:179:0x03ad, B:181:0x03b5, B:189:0x03be, B:192:0x03dc, B:195:0x03ef, B:185:0x040c, B:204:0x038e), top: B:2:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0a50 A[Catch: all -> 0x0a9f, TryCatch #0 {all -> 0x0a9f, blocks: (B:8:0x0045, B:9:0x0a22, B:10:0x0a28, B:12:0x0a2e, B:14:0x0a50, B:15:0x0a6e, B:21:0x0080, B:23:0x09bd, B:25:0x09f5, B:31:0x00c2, B:33:0x094f, B:35:0x0984, B:41:0x010b, B:43:0x0904, B:46:0x0918, B:52:0x0150, B:54:0x08ad, B:56:0x08cd, B:62:0x0195, B:65:0x07e1, B:67:0x07ed, B:73:0x082f, B:75:0x0849, B:77:0x086b, B:84:0x01dd, B:86:0x07c6, B:88:0x0217, B:90:0x0761, B:91:0x0790, B:95:0x0264, B:97:0x06ea, B:99:0x0706, B:101:0x071c, B:104:0x072f, B:108:0x0776, B:110:0x029c, B:112:0x06a8, B:117:0x02cc, B:119:0x067e, B:124:0x02f7, B:126:0x0614, B:128:0x0622, B:129:0x0633, B:134:0x031b, B:136:0x05c3, B:138:0x05d0, B:142:0x0a3f, B:145:0x033c, B:147:0x056b, B:149:0x0573, B:150:0x0593, B:155:0x0358, B:157:0x04f5, B:159:0x04fd, B:160:0x0523, B:165:0x0370, B:167:0x043b, B:169:0x0443, B:170:0x0479, B:175:0x0383, B:177:0x03a7, B:179:0x03ad, B:181:0x03b5, B:189:0x03be, B:192:0x03dc, B:195:0x03ef, B:185:0x040c, B:204:0x038e), top: B:2:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0593 A[Catch: all -> 0x0a9f, TryCatch #0 {all -> 0x0a9f, blocks: (B:8:0x0045, B:9:0x0a22, B:10:0x0a28, B:12:0x0a2e, B:14:0x0a50, B:15:0x0a6e, B:21:0x0080, B:23:0x09bd, B:25:0x09f5, B:31:0x00c2, B:33:0x094f, B:35:0x0984, B:41:0x010b, B:43:0x0904, B:46:0x0918, B:52:0x0150, B:54:0x08ad, B:56:0x08cd, B:62:0x0195, B:65:0x07e1, B:67:0x07ed, B:73:0x082f, B:75:0x0849, B:77:0x086b, B:84:0x01dd, B:86:0x07c6, B:88:0x0217, B:90:0x0761, B:91:0x0790, B:95:0x0264, B:97:0x06ea, B:99:0x0706, B:101:0x071c, B:104:0x072f, B:108:0x0776, B:110:0x029c, B:112:0x06a8, B:117:0x02cc, B:119:0x067e, B:124:0x02f7, B:126:0x0614, B:128:0x0622, B:129:0x0633, B:134:0x031b, B:136:0x05c3, B:138:0x05d0, B:142:0x0a3f, B:145:0x033c, B:147:0x056b, B:149:0x0573, B:150:0x0593, B:155:0x0358, B:157:0x04f5, B:159:0x04fd, B:160:0x0523, B:165:0x0370, B:167:0x043b, B:169:0x0443, B:170:0x0479, B:175:0x0383, B:177:0x03a7, B:179:0x03ad, B:181:0x03b5, B:189:0x03be, B:192:0x03dc, B:195:0x03ef, B:185:0x040c, B:204:0x038e), top: B:2:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:159:0x04fd A[Catch: all -> 0x0a9f, TryCatch #0 {all -> 0x0a9f, blocks: (B:8:0x0045, B:9:0x0a22, B:10:0x0a28, B:12:0x0a2e, B:14:0x0a50, B:15:0x0a6e, B:21:0x0080, B:23:0x09bd, B:25:0x09f5, B:31:0x00c2, B:33:0x094f, B:35:0x0984, B:41:0x010b, B:43:0x0904, B:46:0x0918, B:52:0x0150, B:54:0x08ad, B:56:0x08cd, B:62:0x0195, B:65:0x07e1, B:67:0x07ed, B:73:0x082f, B:75:0x0849, B:77:0x086b, B:84:0x01dd, B:86:0x07c6, B:88:0x0217, B:90:0x0761, B:91:0x0790, B:95:0x0264, B:97:0x06ea, B:99:0x0706, B:101:0x071c, B:104:0x072f, B:108:0x0776, B:110:0x029c, B:112:0x06a8, B:117:0x02cc, B:119:0x067e, B:124:0x02f7, B:126:0x0614, B:128:0x0622, B:129:0x0633, B:134:0x031b, B:136:0x05c3, B:138:0x05d0, B:142:0x0a3f, B:145:0x033c, B:147:0x056b, B:149:0x0573, B:150:0x0593, B:155:0x0358, B:157:0x04f5, B:159:0x04fd, B:160:0x0523, B:165:0x0370, B:167:0x043b, B:169:0x0443, B:170:0x0479, B:175:0x0383, B:177:0x03a7, B:179:0x03ad, B:181:0x03b5, B:189:0x03be, B:192:0x03dc, B:195:0x03ef, B:185:0x040c, B:204:0x038e), top: B:2:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0523 A[Catch: all -> 0x0a9f, TryCatch #0 {all -> 0x0a9f, blocks: (B:8:0x0045, B:9:0x0a22, B:10:0x0a28, B:12:0x0a2e, B:14:0x0a50, B:15:0x0a6e, B:21:0x0080, B:23:0x09bd, B:25:0x09f5, B:31:0x00c2, B:33:0x094f, B:35:0x0984, B:41:0x010b, B:43:0x0904, B:46:0x0918, B:52:0x0150, B:54:0x08ad, B:56:0x08cd, B:62:0x0195, B:65:0x07e1, B:67:0x07ed, B:73:0x082f, B:75:0x0849, B:77:0x086b, B:84:0x01dd, B:86:0x07c6, B:88:0x0217, B:90:0x0761, B:91:0x0790, B:95:0x0264, B:97:0x06ea, B:99:0x0706, B:101:0x071c, B:104:0x072f, B:108:0x0776, B:110:0x029c, B:112:0x06a8, B:117:0x02cc, B:119:0x067e, B:124:0x02f7, B:126:0x0614, B:128:0x0622, B:129:0x0633, B:134:0x031b, B:136:0x05c3, B:138:0x05d0, B:142:0x0a3f, B:145:0x033c, B:147:0x056b, B:149:0x0573, B:150:0x0593, B:155:0x0358, B:157:0x04f5, B:159:0x04fd, B:160:0x0523, B:165:0x0370, B:167:0x043b, B:169:0x0443, B:170:0x0479, B:175:0x0383, B:177:0x03a7, B:179:0x03ad, B:181:0x03b5, B:189:0x03be, B:192:0x03dc, B:195:0x03ef, B:185:0x040c, B:204:0x038e), top: B:2:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0443 A[Catch: all -> 0x0a9f, TryCatch #0 {all -> 0x0a9f, blocks: (B:8:0x0045, B:9:0x0a22, B:10:0x0a28, B:12:0x0a2e, B:14:0x0a50, B:15:0x0a6e, B:21:0x0080, B:23:0x09bd, B:25:0x09f5, B:31:0x00c2, B:33:0x094f, B:35:0x0984, B:41:0x010b, B:43:0x0904, B:46:0x0918, B:52:0x0150, B:54:0x08ad, B:56:0x08cd, B:62:0x0195, B:65:0x07e1, B:67:0x07ed, B:73:0x082f, B:75:0x0849, B:77:0x086b, B:84:0x01dd, B:86:0x07c6, B:88:0x0217, B:90:0x0761, B:91:0x0790, B:95:0x0264, B:97:0x06ea, B:99:0x0706, B:101:0x071c, B:104:0x072f, B:108:0x0776, B:110:0x029c, B:112:0x06a8, B:117:0x02cc, B:119:0x067e, B:124:0x02f7, B:126:0x0614, B:128:0x0622, B:129:0x0633, B:134:0x031b, B:136:0x05c3, B:138:0x05d0, B:142:0x0a3f, B:145:0x033c, B:147:0x056b, B:149:0x0573, B:150:0x0593, B:155:0x0358, B:157:0x04f5, B:159:0x04fd, B:160:0x0523, B:165:0x0370, B:167:0x043b, B:169:0x0443, B:170:0x0479, B:175:0x0383, B:177:0x03a7, B:179:0x03ad, B:181:0x03b5, B:189:0x03be, B:192:0x03dc, B:195:0x03ef, B:185:0x040c, B:204:0x038e), top: B:2:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0479 A[Catch: all -> 0x0a9f, TryCatch #0 {all -> 0x0a9f, blocks: (B:8:0x0045, B:9:0x0a22, B:10:0x0a28, B:12:0x0a2e, B:14:0x0a50, B:15:0x0a6e, B:21:0x0080, B:23:0x09bd, B:25:0x09f5, B:31:0x00c2, B:33:0x094f, B:35:0x0984, B:41:0x010b, B:43:0x0904, B:46:0x0918, B:52:0x0150, B:54:0x08ad, B:56:0x08cd, B:62:0x0195, B:65:0x07e1, B:67:0x07ed, B:73:0x082f, B:75:0x0849, B:77:0x086b, B:84:0x01dd, B:86:0x07c6, B:88:0x0217, B:90:0x0761, B:91:0x0790, B:95:0x0264, B:97:0x06ea, B:99:0x0706, B:101:0x071c, B:104:0x072f, B:108:0x0776, B:110:0x029c, B:112:0x06a8, B:117:0x02cc, B:119:0x067e, B:124:0x02f7, B:126:0x0614, B:128:0x0622, B:129:0x0633, B:134:0x031b, B:136:0x05c3, B:138:0x05d0, B:142:0x0a3f, B:145:0x033c, B:147:0x056b, B:149:0x0573, B:150:0x0593, B:155:0x0358, B:157:0x04f5, B:159:0x04fd, B:160:0x0523, B:165:0x0370, B:167:0x043b, B:169:0x0443, B:170:0x0479, B:175:0x0383, B:177:0x03a7, B:179:0x03ad, B:181:0x03b5, B:189:0x03be, B:192:0x03dc, B:195:0x03ef, B:185:0x040c, B:204:0x038e), top: B:2:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:179:0x03ad A[Catch: all -> 0x0a9f, TryCatch #0 {all -> 0x0a9f, blocks: (B:8:0x0045, B:9:0x0a22, B:10:0x0a28, B:12:0x0a2e, B:14:0x0a50, B:15:0x0a6e, B:21:0x0080, B:23:0x09bd, B:25:0x09f5, B:31:0x00c2, B:33:0x094f, B:35:0x0984, B:41:0x010b, B:43:0x0904, B:46:0x0918, B:52:0x0150, B:54:0x08ad, B:56:0x08cd, B:62:0x0195, B:65:0x07e1, B:67:0x07ed, B:73:0x082f, B:75:0x0849, B:77:0x086b, B:84:0x01dd, B:86:0x07c6, B:88:0x0217, B:90:0x0761, B:91:0x0790, B:95:0x0264, B:97:0x06ea, B:99:0x0706, B:101:0x071c, B:104:0x072f, B:108:0x0776, B:110:0x029c, B:112:0x06a8, B:117:0x02cc, B:119:0x067e, B:124:0x02f7, B:126:0x0614, B:128:0x0622, B:129:0x0633, B:134:0x031b, B:136:0x05c3, B:138:0x05d0, B:142:0x0a3f, B:145:0x033c, B:147:0x056b, B:149:0x0573, B:150:0x0593, B:155:0x0358, B:157:0x04f5, B:159:0x04fd, B:160:0x0523, B:165:0x0370, B:167:0x043b, B:169:0x0443, B:170:0x0479, B:175:0x0383, B:177:0x03a7, B:179:0x03ad, B:181:0x03b5, B:189:0x03be, B:192:0x03dc, B:195:0x03ef, B:185:0x040c, B:204:0x038e), top: B:2:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0439 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:188:0x043a  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0a3d  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x09f5 A[Catch: all -> 0x0a9f, TryCatch #0 {all -> 0x0a9f, blocks: (B:8:0x0045, B:9:0x0a22, B:10:0x0a28, B:12:0x0a2e, B:14:0x0a50, B:15:0x0a6e, B:21:0x0080, B:23:0x09bd, B:25:0x09f5, B:31:0x00c2, B:33:0x094f, B:35:0x0984, B:41:0x010b, B:43:0x0904, B:46:0x0918, B:52:0x0150, B:54:0x08ad, B:56:0x08cd, B:62:0x0195, B:65:0x07e1, B:67:0x07ed, B:73:0x082f, B:75:0x0849, B:77:0x086b, B:84:0x01dd, B:86:0x07c6, B:88:0x0217, B:90:0x0761, B:91:0x0790, B:95:0x0264, B:97:0x06ea, B:99:0x0706, B:101:0x071c, B:104:0x072f, B:108:0x0776, B:110:0x029c, B:112:0x06a8, B:117:0x02cc, B:119:0x067e, B:124:0x02f7, B:126:0x0614, B:128:0x0622, B:129:0x0633, B:134:0x031b, B:136:0x05c3, B:138:0x05d0, B:142:0x0a3f, B:145:0x033c, B:147:0x056b, B:149:0x0573, B:150:0x0593, B:155:0x0358, B:157:0x04f5, B:159:0x04fd, B:160:0x0523, B:165:0x0370, B:167:0x043b, B:169:0x0443, B:170:0x0479, B:175:0x0383, B:177:0x03a7, B:179:0x03ad, B:181:0x03b5, B:189:0x03be, B:192:0x03dc, B:195:0x03ef, B:185:0x040c, B:204:0x038e), top: B:2:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0a26  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0984 A[Catch: all -> 0x0a9f, TryCatch #0 {all -> 0x0a9f, blocks: (B:8:0x0045, B:9:0x0a22, B:10:0x0a28, B:12:0x0a2e, B:14:0x0a50, B:15:0x0a6e, B:21:0x0080, B:23:0x09bd, B:25:0x09f5, B:31:0x00c2, B:33:0x094f, B:35:0x0984, B:41:0x010b, B:43:0x0904, B:46:0x0918, B:52:0x0150, B:54:0x08ad, B:56:0x08cd, B:62:0x0195, B:65:0x07e1, B:67:0x07ed, B:73:0x082f, B:75:0x0849, B:77:0x086b, B:84:0x01dd, B:86:0x07c6, B:88:0x0217, B:90:0x0761, B:91:0x0790, B:95:0x0264, B:97:0x06ea, B:99:0x0706, B:101:0x071c, B:104:0x072f, B:108:0x0776, B:110:0x029c, B:112:0x06a8, B:117:0x02cc, B:119:0x067e, B:124:0x02f7, B:126:0x0614, B:128:0x0622, B:129:0x0633, B:134:0x031b, B:136:0x05c3, B:138:0x05d0, B:142:0x0a3f, B:145:0x033c, B:147:0x056b, B:149:0x0573, B:150:0x0593, B:155:0x0358, B:157:0x04f5, B:159:0x04fd, B:160:0x0523, B:165:0x0370, B:167:0x043b, B:169:0x0443, B:170:0x0479, B:175:0x0383, B:177:0x03a7, B:179:0x03ad, B:181:0x03b5, B:189:0x03be, B:192:0x03dc, B:195:0x03ef, B:185:0x040c, B:204:0x038e), top: B:2:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x09d7  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0918 A[Catch: all -> 0x0a9f, TryCatch #0 {all -> 0x0a9f, blocks: (B:8:0x0045, B:9:0x0a22, B:10:0x0a28, B:12:0x0a2e, B:14:0x0a50, B:15:0x0a6e, B:21:0x0080, B:23:0x09bd, B:25:0x09f5, B:31:0x00c2, B:33:0x094f, B:35:0x0984, B:41:0x010b, B:43:0x0904, B:46:0x0918, B:52:0x0150, B:54:0x08ad, B:56:0x08cd, B:62:0x0195, B:65:0x07e1, B:67:0x07ed, B:73:0x082f, B:75:0x0849, B:77:0x086b, B:84:0x01dd, B:86:0x07c6, B:88:0x0217, B:90:0x0761, B:91:0x0790, B:95:0x0264, B:97:0x06ea, B:99:0x0706, B:101:0x071c, B:104:0x072f, B:108:0x0776, B:110:0x029c, B:112:0x06a8, B:117:0x02cc, B:119:0x067e, B:124:0x02f7, B:126:0x0614, B:128:0x0622, B:129:0x0633, B:134:0x031b, B:136:0x05c3, B:138:0x05d0, B:142:0x0a3f, B:145:0x033c, B:147:0x056b, B:149:0x0573, B:150:0x0593, B:155:0x0358, B:157:0x04f5, B:159:0x04fd, B:160:0x0523, B:165:0x0370, B:167:0x043b, B:169:0x0443, B:170:0x0479, B:175:0x0383, B:177:0x03a7, B:179:0x03ad, B:181:0x03b5, B:189:0x03be, B:192:0x03dc, B:195:0x03ef, B:185:0x040c, B:204:0x038e), top: B:2:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0969  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x08cd A[Catch: all -> 0x0a9f, TryCatch #0 {all -> 0x0a9f, blocks: (B:8:0x0045, B:9:0x0a22, B:10:0x0a28, B:12:0x0a2e, B:14:0x0a50, B:15:0x0a6e, B:21:0x0080, B:23:0x09bd, B:25:0x09f5, B:31:0x00c2, B:33:0x094f, B:35:0x0984, B:41:0x010b, B:43:0x0904, B:46:0x0918, B:52:0x0150, B:54:0x08ad, B:56:0x08cd, B:62:0x0195, B:65:0x07e1, B:67:0x07ed, B:73:0x082f, B:75:0x0849, B:77:0x086b, B:84:0x01dd, B:86:0x07c6, B:88:0x0217, B:90:0x0761, B:91:0x0790, B:95:0x0264, B:97:0x06ea, B:99:0x0706, B:101:0x071c, B:104:0x072f, B:108:0x0776, B:110:0x029c, B:112:0x06a8, B:117:0x02cc, B:119:0x067e, B:124:0x02f7, B:126:0x0614, B:128:0x0622, B:129:0x0633, B:134:0x031b, B:136:0x05c3, B:138:0x05d0, B:142:0x0a3f, B:145:0x033c, B:147:0x056b, B:149:0x0573, B:150:0x0593, B:155:0x0358, B:157:0x04f5, B:159:0x04fd, B:160:0x0523, B:165:0x0370, B:167:0x043b, B:169:0x0443, B:170:0x0479, B:175:0x0383, B:177:0x03a7, B:179:0x03ad, B:181:0x03b5, B:189:0x03be, B:192:0x03dc, B:195:0x03ef, B:185:0x040c, B:204:0x038e), top: B:2:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x090f  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x07ed A[Catch: all -> 0x0a9f, TryCatch #0 {all -> 0x0a9f, blocks: (B:8:0x0045, B:9:0x0a22, B:10:0x0a28, B:12:0x0a2e, B:14:0x0a50, B:15:0x0a6e, B:21:0x0080, B:23:0x09bd, B:25:0x09f5, B:31:0x00c2, B:33:0x094f, B:35:0x0984, B:41:0x010b, B:43:0x0904, B:46:0x0918, B:52:0x0150, B:54:0x08ad, B:56:0x08cd, B:62:0x0195, B:65:0x07e1, B:67:0x07ed, B:73:0x082f, B:75:0x0849, B:77:0x086b, B:84:0x01dd, B:86:0x07c6, B:88:0x0217, B:90:0x0761, B:91:0x0790, B:95:0x0264, B:97:0x06ea, B:99:0x0706, B:101:0x071c, B:104:0x072f, B:108:0x0776, B:110:0x029c, B:112:0x06a8, B:117:0x02cc, B:119:0x067e, B:124:0x02f7, B:126:0x0614, B:128:0x0622, B:129:0x0633, B:134:0x031b, B:136:0x05c3, B:138:0x05d0, B:142:0x0a3f, B:145:0x033c, B:147:0x056b, B:149:0x0573, B:150:0x0593, B:155:0x0358, B:157:0x04f5, B:159:0x04fd, B:160:0x0523, B:165:0x0370, B:167:0x043b, B:169:0x0443, B:170:0x0479, B:175:0x0383, B:177:0x03a7, B:179:0x03ad, B:181:0x03b5, B:189:0x03be, B:192:0x03dc, B:195:0x03ef, B:185:0x040c, B:204:0x038e), top: B:2:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x082f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x07c5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0706 A[Catch: all -> 0x0a9f, TryCatch #0 {all -> 0x0a9f, blocks: (B:8:0x0045, B:9:0x0a22, B:10:0x0a28, B:12:0x0a2e, B:14:0x0a50, B:15:0x0a6e, B:21:0x0080, B:23:0x09bd, B:25:0x09f5, B:31:0x00c2, B:33:0x094f, B:35:0x0984, B:41:0x010b, B:43:0x0904, B:46:0x0918, B:52:0x0150, B:54:0x08ad, B:56:0x08cd, B:62:0x0195, B:65:0x07e1, B:67:0x07ed, B:73:0x082f, B:75:0x0849, B:77:0x086b, B:84:0x01dd, B:86:0x07c6, B:88:0x0217, B:90:0x0761, B:91:0x0790, B:95:0x0264, B:97:0x06ea, B:99:0x0706, B:101:0x071c, B:104:0x072f, B:108:0x0776, B:110:0x029c, B:112:0x06a8, B:117:0x02cc, B:119:0x067e, B:124:0x02f7, B:126:0x0614, B:128:0x0622, B:129:0x0633, B:134:0x031b, B:136:0x05c3, B:138:0x05d0, B:142:0x0a3f, B:145:0x033c, B:147:0x056b, B:149:0x0573, B:150:0x0593, B:155:0x0358, B:157:0x04f5, B:159:0x04fd, B:160:0x0523, B:165:0x0370, B:167:0x043b, B:169:0x0443, B:170:0x0479, B:175:0x0383, B:177:0x03a7, B:179:0x03ad, B:181:0x03b5, B:189:0x03be, B:192:0x03dc, B:195:0x03ef, B:185:0x040c, B:204:0x038e), top: B:2:0x000a }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object obj2;
        CoroutineScope coroutineScope;
        String str;
        long elapsedRealtime;
        CoroutineScope coroutineScope2;
        HardwareInterfaceStub$open$1 hardwareInterfaceStub$open$1;
        Object obj3;
        AtomicBoolean atomicBoolean;
        ThreadSafeListener threadSafeListener;
        Object bootUp;
        int i;
        RGBDInterfaceImpl rGBDInterfaceImpl;
        String str2;
        RGBDInterfaceImpl rGBDInterfaceImpl2;
        ScheduleCommunicationImpl scheduleCommunicationImpl;
        ScheduleCommunicationImpl scheduleCommunicationImpl2;
        final CANBus.Result result;
        ThreadSafeListener threadSafeListener2;
        ThreadSafeListener threadSafeListener3;
        ThreadSafeListener threadSafeListener4;
        String str3;
        ThreadSafeListener threadSafeListener5;
        Object fetchMachineInfo;
        CoroutineScope coroutineScope3;
        CANBus.Result result2;
        HardwareInterfaceStub$open$1 hardwareInterfaceStub$open$12;
        long j;
        String str4;
        ThreadSafeListener threadSafeListener6;
        final HardwareInterfaceStub.Result result3;
        ThreadSafeListener threadSafeListener7;
        MachineInfoProcess machineInfoProcess;
        Object hardwareVersion;
        Object obj4;
        HardwareInterfaceStub.Result result4;
        int i2;
        CoroutineScope coroutineScope4;
        long j2;
        HardwareInterfaceStub$open$1 hardwareInterfaceStub$open$13;
        CANBus.Result result5;
        String str5;
        ThreadSafeListener threadSafeListener8;
        ThreadSafeListener threadSafeListener9;
        Object withTimeoutOrNull;
        HardwareInterfaceStub.Result result6;
        Object obj5;
        String str6;
        ThreadSafeListener threadSafeListener10;
        Boolean bool;
        ThreadSafeListener threadSafeListener11;
        Object withTimeoutOrNull2;
        Object obj6;
        Boolean bool2;
        int i3;
        CoroutineScope coroutineScope5;
        long j3;
        HardwareInterfaceStub$open$1 hardwareInterfaceStub$open$14;
        CANBus.Result result7;
        HardwareInterfaceStub.Result result8;
        ThreadSafeListener threadSafeListener12;
        HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo;
        Object checkIMU;
        long j4;
        HardwareInterfaceStub.Result result9;
        CANBus.Result result10;
        long j5;
        List list;
        Boolean bool3;
        CoroutineScope coroutineScope6;
        ThreadSafeListener threadSafeListener13;
        Object checkEncoder;
        Job job;
        Object checkRGBD;
        Object obj7;
        HardwareInterfaceStub.Result result11;
        Job job2;
        Job job3;
        List list2;
        Boolean bool4;
        HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo2;
        CoroutineScope coroutineScope7;
        int i4;
        CANBus.Result result12;
        long j6;
        MachineInfoProcess machineInfoProcess2;
        Boolean bool5;
        Job job4;
        List list3;
        HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo3;
        Job job5;
        Job job6;
        Object obj8;
        Job job7;
        CoroutineScope coroutineScope8;
        long j7;
        MachineInfoProcess machineInfoProcess3;
        MachineInfoProcess machineInfoProcess4;
        Job job8;
        Object checkCamera;
        Object obj9;
        Job job9;
        Boolean bool6;
        Job job10;
        Object checkLidar;
        Object obj10;
        Job job11;
        CoroutineScope coroutineScope9;
        int i5;
        Object obj11;
        Job job12;
        long j8;
        HardwareInterfaceStub.Result result13;
        CANBus.Result result14;
        Boolean bool7;
        long j9;
        List list4;
        HardwareInterfaceStub$open$1 hardwareInterfaceStub$open$15;
        Job job13;
        HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo4;
        Job job14;
        Job job15;
        AtomicBoolean atomicBoolean2;
        Object obj12;
        Job job16;
        Job job17;
        long j10;
        Job job18;
        Job job19;
        long j11;
        Object obj13;
        Job job20;
        Job job21;
        HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo5;
        List list5;
        String str7;
        USBController uSBController;
        Job job22;
        Job job23;
        Object obj14;
        long j12;
        Object obj15;
        Job job24;
        Job job25;
        HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo6;
        List list6;
        Object obj16;
        long j13;
        Job job26;
        HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo7;
        int i6;
        HardwareInterfaceStub.Result result15;
        List list7;
        HardwareInterfaceStub$open$1 hardwareInterfaceStub$open$16;
        long j14;
        Job job27;
        Job job28;
        CANBus.Result result16;
        Boolean bool8;
        Job job29;
        CoroutineScope coroutineScope10;
        long j15;
        Object obj17;
        Job job30;
        Job job31;
        HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo8;
        List list8;
        Job job32;
        List list9;
        Job job33;
        Job job34;
        Boolean bool9;
        Job job35;
        CANBus.Result result17;
        Job job36;
        Object obj18;
        CoroutineScope coroutineScope11;
        Job job37;
        long j16;
        HardwareInterfaceStub$open$1 hardwareInterfaceStub$open$17;
        HardwareInterfaceStub.Result result18;
        HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo9;
        long j17;
        Object obj19;
        Job job38;
        CoroutineScope coroutineScope12;
        Job job39;
        HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo10;
        Boolean bool10;
        int i7;
        long j18;
        String str8;
        String str9;
        ThreadSafeListener threadSafeListener14;
        ThreadSafeListener threadSafeListener15;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        long j19 = 5000;
        try {
            switch (this.label) {
                case 0:
                    obj2 = coroutine_suspended;
                    ResultKt.throwOnFailure(obj);
                    coroutineScope = this.f5913p$;
                    HardwareInterfaceStub hardwareInterfaceStub = HardwareInterfaceStub.INSTANCE;
                    str = HardwareInterfaceStub.TAG;
                    Pdlog.m3275i(str, "open hardware async");
                    elapsedRealtime = SystemClock.elapsedRealtime();
                    coroutineScope2 = coroutineScope;
                    hardwareInterfaceStub$open$1 = this;
                    obj3 = obj2;
                    while (CoroutineScopeKt.isActive(coroutineScope2)) {
                        HardwareInterfaceStub hardwareInterfaceStub2 = HardwareInterfaceStub.INSTANCE;
                        rGBDInterfaceImpl = HardwareInterfaceStub.rgbdInterfaceImpl;
                        if (rGBDInterfaceImpl != null) {
                            HardwareInterfaceStub hardwareInterfaceStub3 = HardwareInterfaceStub.INSTANCE;
                            scheduleCommunicationImpl2 = HardwareInterfaceStub.scheduler;
                            if (scheduleCommunicationImpl2 != null) {
                                HardwareInterfaceStub hardwareInterfaceStub4 = HardwareInterfaceStub.INSTANCE;
                                atomicBoolean = HardwareInterfaceStub.waitSelfCheck;
                                atomicBoolean.set(true);
                                HardwareInterfaceStub hardwareInterfaceStub5 = HardwareInterfaceStub.INSTANCE;
                                threadSafeListener = HardwareInterfaceStub.hardwareListener;
                                threadSafeListener.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1.5
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str10) {
                                        invoke2(iHardware, str10);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(IHardware l, String str10) {
                                        Intrinsics.checkParameterIsNotNull(l, "l");
                                        Intrinsics.checkParameterIsNotNull(str10, "<anonymous parameter 1>");
                                        l.onOpenStep(HardwareOpenStep.CanConnect, StepState.Running, "");
                                    }
                                });
                                CANBus access$getCanBus$p = HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE);
                                hardwareInterfaceStub$open$1.L$0 = coroutineScope2;
                                hardwareInterfaceStub$open$1.J$0 = elapsedRealtime;
                                hardwareInterfaceStub$open$1.I$0 = 0;
                                hardwareInterfaceStub$open$1.label = 2;
                                bootUp = access$getCanBus$p.bootUp(hardwareInterfaceStub$open$1);
                                if (bootUp == obj3) {
                                    return obj3;
                                }
                                i = 0;
                                result = (CANBus.Result) bootUp;
                                if (result.isSuccess()) {
                                    HardwareInterfaceStub hardwareInterfaceStub6 = HardwareInterfaceStub.INSTANCE;
                                    str4 = HardwareInterfaceStub.TAG;
                                    Pdlog.m3274e(str4, "canbus boot up fail," + result.getDescription());
                                    HardwareInterfaceStub hardwareInterfaceStub7 = HardwareInterfaceStub.INSTANCE;
                                    threadSafeListener6 = HardwareInterfaceStub.hardwareListener;
                                    threadSafeListener6.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1.6
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str10) {
                                            invoke2(iHardware, str10);
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(IHardware l, String str10) {
                                            Intrinsics.checkParameterIsNotNull(l, "l");
                                            Intrinsics.checkParameterIsNotNull(str10, "<anonymous parameter 1>");
                                            l.onOpenStep(HardwareOpenStep.CanConnect, StepState.Fail, CANBus.Result.this.getDescription());
                                        }
                                    });
                                    if (i == 0) {
                                        HardwareInterfaceStub hardwareInterfaceStub8 = HardwareInterfaceStub.INSTANCE;
                                        str9 = HardwareInterfaceStub.TAG;
                                        Pdlog.m3274e(str9, "hardware boot up fail");
                                        HardwareInterfaceStub hardwareInterfaceStub9 = HardwareInterfaceStub.INSTANCE;
                                        threadSafeListener14 = HardwareInterfaceStub.hardwareListener;
                                        threadSafeListener14.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1.20
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str10) {
                                                invoke2(iHardware, str10);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(IHardware l, String str10) {
                                                Intrinsics.checkParameterIsNotNull(l, "l");
                                                Intrinsics.checkParameterIsNotNull(str10, "<anonymous parameter 1>");
                                                l.onOpenStep(HardwareOpenStep.Finish, StepState.Fail, "hardware boot up fail");
                                            }
                                        });
                                    }
                                    HardwareInterfaceStub hardwareInterfaceStub10 = HardwareInterfaceStub.INSTANCE;
                                    str8 = HardwareInterfaceStub.TAG;
                                    Pdlog.m3273d(str8, "init hardware spends start check " + (SystemClock.elapsedRealtime() - elapsedRealtime));
                                    HardwareInterfaceStub hardwareInterfaceStub11 = HardwareInterfaceStub.INSTANCE;
                                    HardwareInterfaceStub.openJob = (Job) null;
                                    return Unit.INSTANCE;
                                }
                                HardwareInterfaceStub hardwareInterfaceStub12 = HardwareInterfaceStub.INSTANCE;
                                threadSafeListener2 = HardwareInterfaceStub.hardwareListener;
                                threadSafeListener2.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1.7
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str10) {
                                        invoke2(iHardware, str10);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(IHardware l, String str10) {
                                        Intrinsics.checkParameterIsNotNull(l, "l");
                                        Intrinsics.checkParameterIsNotNull(str10, "<anonymous parameter 1>");
                                        l.onOpenStep(HardwareOpenStep.CanConnect, StepState.Success, "");
                                    }
                                });
                                HardwareInterfaceStub hardwareInterfaceStub13 = HardwareInterfaceStub.INSTANCE;
                                threadSafeListener3 = HardwareInterfaceStub.hardwareListener;
                                threadSafeListener3.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1.8
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str10) {
                                        invoke2(iHardware, str10);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(IHardware l, String str10) {
                                        Intrinsics.checkParameterIsNotNull(l, "l");
                                        Intrinsics.checkParameterIsNotNull(str10, "<anonymous parameter 1>");
                                        l.onOpenStep(HardwareOpenStep.GetHardwareVersion, StepState.Running, "");
                                    }
                                });
                                HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE).regProtocol();
                                HardwareInterfaceStub hardwareInterfaceStub14 = HardwareInterfaceStub.INSTANCE;
                                threadSafeListener4 = HardwareInterfaceStub.hardwareListener;
                                threadSafeListener4.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1.9
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str10) {
                                        invoke2(iHardware, str10);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(IHardware l, String str10) {
                                        Intrinsics.checkParameterIsNotNull(l, "l");
                                        Intrinsics.checkParameterIsNotNull(str10, "<anonymous parameter 1>");
                                        l.onOpenStep(HardwareOpenStep.GetHardwareVersion, StepState.Success, "");
                                    }
                                });
                                HardwareInterfaceStub hardwareInterfaceStub15 = HardwareInterfaceStub.INSTANCE;
                                str3 = HardwareInterfaceStub.TAG;
                                Pdlog.m3275i(str3, "canbus boot up success");
                                HardwareInterfaceStub hardwareInterfaceStub16 = HardwareInterfaceStub.INSTANCE;
                                HardwareInterfaceStub.carpetModeConfig = new CarpetModeConfig(HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE));
                                HardwareInterfaceStub hardwareInterfaceStub17 = HardwareInterfaceStub.INSTANCE;
                                threadSafeListener5 = HardwareInterfaceStub.hardwareListener;
                                threadSafeListener5.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1.10
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str10) {
                                        invoke2(iHardware, str10);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(IHardware l, String str10) {
                                        Intrinsics.checkParameterIsNotNull(l, "l");
                                        Intrinsics.checkParameterIsNotNull(str10, "<anonymous parameter 1>");
                                        l.onOpenStep(HardwareOpenStep.FetchMachineInfo, StepState.Running, "");
                                    }
                                });
                                HardwareInterfaceStub hardwareInterfaceStub18 = HardwareInterfaceStub.INSTANCE;
                                hardwareInterfaceStub$open$1.L$0 = coroutineScope2;
                                hardwareInterfaceStub$open$1.J$0 = elapsedRealtime;
                                hardwareInterfaceStub$open$1.I$0 = i;
                                hardwareInterfaceStub$open$1.L$1 = result;
                                hardwareInterfaceStub$open$1.label = 3;
                                fetchMachineInfo = hardwareInterfaceStub18.fetchMachineInfo(hardwareInterfaceStub$open$1);
                                if (fetchMachineInfo == obj3) {
                                    return obj3;
                                }
                                coroutineScope3 = coroutineScope2;
                                result2 = result;
                                long j20 = elapsedRealtime;
                                hardwareInterfaceStub$open$12 = hardwareInterfaceStub$open$1;
                                j = j20;
                                result3 = (HardwareInterfaceStub.Result) fetchMachineInfo;
                                if (result3.isSuccess()) {
                                    HardwareInterfaceStub hardwareInterfaceStub19 = HardwareInterfaceStub.INSTANCE;
                                    str5 = HardwareInterfaceStub.TAG;
                                    Pdlog.m3274e(str5, result3.getDescription());
                                    HardwareInterfaceStub hardwareInterfaceStub20 = HardwareInterfaceStub.INSTANCE;
                                    threadSafeListener8 = HardwareInterfaceStub.hardwareListener;
                                    threadSafeListener8.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1.11
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str10) {
                                            invoke2(iHardware, str10);
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(IHardware l, String str10) {
                                            Intrinsics.checkParameterIsNotNull(l, "l");
                                            Intrinsics.checkParameterIsNotNull(str10, "<anonymous parameter 1>");
                                            l.onOpenStep(HardwareOpenStep.FetchMachineInfo, StepState.Fail, HardwareInterfaceStub.Result.this.getDescription());
                                        }
                                    });
                                    elapsedRealtime = j;
                                    if (i == 0) {
                                    }
                                    HardwareInterfaceStub hardwareInterfaceStub102 = HardwareInterfaceStub.INSTANCE;
                                    str8 = HardwareInterfaceStub.TAG;
                                    Pdlog.m3273d(str8, "init hardware spends start check " + (SystemClock.elapsedRealtime() - elapsedRealtime));
                                    HardwareInterfaceStub hardwareInterfaceStub112 = HardwareInterfaceStub.INSTANCE;
                                    HardwareInterfaceStub.openJob = (Job) null;
                                    return Unit.INSTANCE;
                                }
                                HardwareInterfaceStub hardwareInterfaceStub21 = HardwareInterfaceStub.INSTANCE;
                                threadSafeListener7 = HardwareInterfaceStub.hardwareListener;
                                threadSafeListener7.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1.12
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str10) {
                                        invoke2(iHardware, str10);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(IHardware l, String str10) {
                                        Intrinsics.checkParameterIsNotNull(l, "l");
                                        Intrinsics.checkParameterIsNotNull(str10, "<anonymous parameter 1>");
                                        l.onOpenStep(HardwareOpenStep.FetchMachineInfo, StepState.Success, "");
                                    }
                                });
                                CANBus access$getCanBus$p2 = HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE);
                                HardwareInterfaceStub hardwareInterfaceStub22 = HardwareInterfaceStub.INSTANCE;
                                machineInfoProcess = HardwareInterfaceStub.machineInfoProcess;
                                access$getCanBus$p2.setMachineType(machineInfoProcess.getMachineInfo().getProductMachineType());
                                CANBus access$getCanBus$p3 = HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE);
                                hardwareInterfaceStub$open$12.L$0 = coroutineScope3;
                                hardwareInterfaceStub$open$12.J$0 = j;
                                hardwareInterfaceStub$open$12.I$0 = i;
                                hardwareInterfaceStub$open$12.L$1 = result2;
                                hardwareInterfaceStub$open$12.L$2 = result3;
                                hardwareInterfaceStub$open$12.label = 4;
                                hardwareVersion = access$getCanBus$p3.getHardwareVersion(hardwareInterfaceStub$open$12);
                                if (hardwareVersion == obj3) {
                                    return obj3;
                                }
                                CANBus.Result result19 = result2;
                                obj4 = obj3;
                                result4 = result3;
                                i2 = i;
                                coroutineScope4 = coroutineScope3;
                                j2 = j;
                                hardwareInterfaceStub$open$13 = hardwareInterfaceStub$open$12;
                                result5 = result19;
                                if (((Boolean) hardwareVersion).booleanValue()) {
                                    HardwareInterfaceStub hardwareInterfaceStub23 = HardwareInterfaceStub.INSTANCE;
                                    str6 = HardwareInterfaceStub.TAG;
                                    Pdlog.m3274e(str6, "get hardware version fail");
                                    HardwareInterfaceStub hardwareInterfaceStub24 = HardwareInterfaceStub.INSTANCE;
                                    threadSafeListener10 = HardwareInterfaceStub.hardwareListener;
                                    threadSafeListener10.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1.13
                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str10) {
                                            invoke2(iHardware, str10);
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(IHardware l, String str10) {
                                            Intrinsics.checkParameterIsNotNull(l, "l");
                                            Intrinsics.checkParameterIsNotNull(str10, "<anonymous parameter 1>");
                                            l.onOpenStep(HardwareOpenStep.GetHardwareVersion, StepState.Fail, "get hardware version fail");
                                        }
                                    });
                                    elapsedRealtime = j2;
                                    i = i2;
                                    if (i == 0) {
                                    }
                                    HardwareInterfaceStub hardwareInterfaceStub1022 = HardwareInterfaceStub.INSTANCE;
                                    str8 = HardwareInterfaceStub.TAG;
                                    Pdlog.m3273d(str8, "init hardware spends start check " + (SystemClock.elapsedRealtime() - elapsedRealtime));
                                    HardwareInterfaceStub hardwareInterfaceStub1122 = HardwareInterfaceStub.INSTANCE;
                                    HardwareInterfaceStub.openJob = (Job) null;
                                    return Unit.INSTANCE;
                                }
                                HardwareInterfaceStub hardwareInterfaceStub25 = HardwareInterfaceStub.INSTANCE;
                                threadSafeListener9 = HardwareInterfaceStub.hardwareListener;
                                threadSafeListener9.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1.14
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str10) {
                                        invoke2(iHardware, str10);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(IHardware l, String str10) {
                                        Intrinsics.checkParameterIsNotNull(l, "l");
                                        Intrinsics.checkParameterIsNotNull(str10, "<anonymous parameter 1>");
                                        l.onOpenStep(HardwareOpenStep.ESPCheck, StepState.Running, "");
                                    }
                                });
                                HardwareInterfaceStub$open$1$initSchedulerResult$1 hardwareInterfaceStub$open$1$initSchedulerResult$1 = new HardwareInterfaceStub$open$1$initSchedulerResult$1(null);
                                hardwareInterfaceStub$open$13.L$0 = coroutineScope4;
                                hardwareInterfaceStub$open$13.J$0 = j2;
                                hardwareInterfaceStub$open$13.I$0 = i2;
                                hardwareInterfaceStub$open$13.L$1 = result5;
                                hardwareInterfaceStub$open$13.L$2 = result4;
                                hardwareInterfaceStub$open$13.J$1 = 5000L;
                                hardwareInterfaceStub$open$13.label = 5;
                                withTimeoutOrNull = TimeoutKt.withTimeoutOrNull(5000L, hardwareInterfaceStub$open$1$initSchedulerResult$1, hardwareInterfaceStub$open$13);
                                if (withTimeoutOrNull == obj4) {
                                    return obj4;
                                }
                                Object obj20 = obj4;
                                result6 = result4;
                                obj5 = obj20;
                                bool = (Boolean) withTimeoutOrNull;
                                if (Intrinsics.areEqual(bool, Boxing.boxBoolean(true))) {
                                    HardwareInterfaceStub hardwareInterfaceStub26 = HardwareInterfaceStub.INSTANCE;
                                    threadSafeListener11 = HardwareInterfaceStub.hardwareListener;
                                    threadSafeListener11.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1.18
                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str10) {
                                            invoke2(iHardware, str10);
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(IHardware l, String str10) {
                                            Intrinsics.checkParameterIsNotNull(l, "l");
                                            Intrinsics.checkParameterIsNotNull(str10, "<anonymous parameter 1>");
                                            l.onOpenStep(HardwareOpenStep.ESPCheck, StepState.Fail, "Schedule Communication Module Connect Overtime");
                                        }
                                    });
                                    elapsedRealtime = j2;
                                    i = i2;
                                    if (i == 0) {
                                    }
                                    HardwareInterfaceStub hardwareInterfaceStub10222 = HardwareInterfaceStub.INSTANCE;
                                    str8 = HardwareInterfaceStub.TAG;
                                    Pdlog.m3273d(str8, "init hardware spends start check " + (SystemClock.elapsedRealtime() - elapsedRealtime));
                                    HardwareInterfaceStub hardwareInterfaceStub11222 = HardwareInterfaceStub.INSTANCE;
                                    HardwareInterfaceStub.openJob = (Job) null;
                                    return Unit.INSTANCE;
                                }
                                BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new C501015(null), 3, null);
                                HardwareInterfaceStub$open$1$ret$1 hardwareInterfaceStub$open$1$ret$1 = new HardwareInterfaceStub$open$1$ret$1(null);
                                hardwareInterfaceStub$open$13.L$0 = coroutineScope4;
                                hardwareInterfaceStub$open$13.J$0 = j2;
                                hardwareInterfaceStub$open$13.I$0 = i2;
                                hardwareInterfaceStub$open$13.L$1 = result5;
                                hardwareInterfaceStub$open$13.L$2 = result6;
                                hardwareInterfaceStub$open$13.J$1 = j19;
                                hardwareInterfaceStub$open$13.L$3 = bool;
                                hardwareInterfaceStub$open$13.label = 6;
                                withTimeoutOrNull2 = TimeoutKt.withTimeoutOrNull(j19, hardwareInterfaceStub$open$1$ret$1, hardwareInterfaceStub$open$13);
                                if (withTimeoutOrNull2 == obj5) {
                                    return obj5;
                                }
                                HardwareInterfaceStub.Result result20 = result6;
                                obj6 = obj5;
                                bool2 = bool;
                                i3 = i2;
                                coroutineScope5 = coroutineScope4;
                                j3 = j2;
                                hardwareInterfaceStub$open$14 = hardwareInterfaceStub$open$13;
                                result7 = result5;
                                result8 = result20;
                                if (!(!Intrinsics.areEqual((Boolean) withTimeoutOrNull2, Boxing.boxBoolean(true)))) {
                                    HardwareInterfaceStub hardwareInterfaceStub27 = HardwareInterfaceStub.INSTANCE;
                                    threadSafeListener13 = HardwareInterfaceStub.hardwareListener;
                                    threadSafeListener13.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1.16
                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str10) {
                                            invoke2(iHardware, str10);
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(IHardware l, String str10) {
                                            Intrinsics.checkParameterIsNotNull(l, "l");
                                            Intrinsics.checkParameterIsNotNull(str10, "<anonymous parameter 1>");
                                            l.onOpenStep(HardwareOpenStep.ESPCheck, StepState.Fail, "Connect ESP Overtime");
                                        }
                                    });
                                    elapsedRealtime = j3;
                                    i = i3;
                                    if (i == 0) {
                                    }
                                    HardwareInterfaceStub hardwareInterfaceStub102222 = HardwareInterfaceStub.INSTANCE;
                                    str8 = HardwareInterfaceStub.TAG;
                                    Pdlog.m3273d(str8, "init hardware spends start check " + (SystemClock.elapsedRealtime() - elapsedRealtime));
                                    HardwareInterfaceStub hardwareInterfaceStub112222 = HardwareInterfaceStub.INSTANCE;
                                    HardwareInterfaceStub.openJob = (Job) null;
                                    return Unit.INSTANCE;
                                }
                                HardwareInterfaceStub hardwareInterfaceStub28 = HardwareInterfaceStub.INSTANCE;
                                threadSafeListener12 = HardwareInterfaceStub.hardwareListener;
                                threadSafeListener12.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1.17
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str10) {
                                        invoke2(iHardware, str10);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(IHardware l, String str10) {
                                        Intrinsics.checkParameterIsNotNull(l, "l");
                                        Intrinsics.checkParameterIsNotNull(str10, "<anonymous parameter 1>");
                                        l.onOpenStep(HardwareOpenStep.ESPCheck, StepState.Success, "");
                                    }
                                });
                                final List mutableList = CollectionsKt.toMutableList((Collection) ArraysKt.asList(HardwareInterfaceStub.SelfCheckSensor.values()));
                                selfCheckSensorInfo = new HardwareInterfaceStub.SelfCheckSensorInfo() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1$selfCheckInfo$1
                                    @Override // com.pudutech.mirsdk.hardware.HardwareInterfaceStub.SelfCheckSensorInfo
                                    public void onState(HardwareInterfaceStub.SelfCheckSensor sensor, boolean result21) {
                                        AtomicBoolean atomicBoolean3;
                                        AtomicBoolean atomicBoolean4;
                                        Intrinsics.checkParameterIsNotNull(sensor, "sensor");
                                        synchronized (mutableList) {
                                            if (mutableList.contains(sensor)) {
                                                if (!result21) {
                                                    HardwareInterfaceStub hardwareInterfaceStub29 = HardwareInterfaceStub.INSTANCE;
                                                    atomicBoolean4 = HardwareInterfaceStub.waitSelfCheck;
                                                    atomicBoolean4.set(false);
                                                } else {
                                                    mutableList.remove(sensor);
                                                }
                                                if (mutableList.isEmpty()) {
                                                    HardwareInterfaceStub hardwareInterfaceStub30 = HardwareInterfaceStub.INSTANCE;
                                                    atomicBoolean3 = HardwareInterfaceStub.waitSelfCheck;
                                                    atomicBoolean3.set(false);
                                                }
                                            }
                                            Unit unit = Unit.INSTANCE;
                                        }
                                    }
                                };
                                hardwareInterfaceStub$open$14.L$0 = coroutineScope5;
                                hardwareInterfaceStub$open$14.J$0 = j3;
                                hardwareInterfaceStub$open$14.I$0 = i3;
                                hardwareInterfaceStub$open$14.L$1 = result7;
                                hardwareInterfaceStub$open$14.L$2 = result8;
                                hardwareInterfaceStub$open$14.J$1 = j19;
                                hardwareInterfaceStub$open$14.L$3 = bool2;
                                hardwareInterfaceStub$open$14.L$4 = mutableList;
                                hardwareInterfaceStub$open$14.L$5 = selfCheckSensorInfo;
                                Boolean bool11 = bool2;
                                hardwareInterfaceStub$open$14.label = 7;
                                checkIMU = HardwareInterfaceStub.INSTANCE.checkIMU(selfCheckSensorInfo, hardwareInterfaceStub$open$14);
                                if (checkIMU == obj6) {
                                    return obj6;
                                }
                                j4 = j3;
                                result9 = result8;
                                result10 = result7;
                                j5 = j19;
                                list = mutableList;
                                bool3 = bool11;
                                coroutineScope6 = coroutineScope5;
                                Job job40 = (Job) checkIMU;
                                hardwareInterfaceStub$open$14.L$0 = coroutineScope6;
                                hardwareInterfaceStub$open$14.J$0 = j4;
                                hardwareInterfaceStub$open$14.I$0 = i3;
                                hardwareInterfaceStub$open$14.L$1 = result10;
                                hardwareInterfaceStub$open$14.L$2 = result9;
                                hardwareInterfaceStub$open$14.J$1 = j5;
                                hardwareInterfaceStub$open$14.L$3 = bool3;
                                hardwareInterfaceStub$open$14.L$4 = list;
                                hardwareInterfaceStub$open$14.L$5 = selfCheckSensorInfo;
                                hardwareInterfaceStub$open$14.L$6 = job40;
                                hardwareInterfaceStub$open$14.label = 8;
                                checkEncoder = HardwareInterfaceStub.INSTANCE.checkEncoder(selfCheckSensorInfo, hardwareInterfaceStub$open$14);
                                if (checkEncoder != obj6) {
                                    return obj6;
                                }
                                job = job40;
                                Job job41 = (Job) checkEncoder;
                                hardwareInterfaceStub$open$14.L$0 = coroutineScope6;
                                hardwareInterfaceStub$open$14.J$0 = j4;
                                hardwareInterfaceStub$open$14.I$0 = i3;
                                hardwareInterfaceStub$open$14.L$1 = result10;
                                hardwareInterfaceStub$open$14.L$2 = result9;
                                hardwareInterfaceStub$open$14.J$1 = j5;
                                hardwareInterfaceStub$open$14.L$3 = bool3;
                                hardwareInterfaceStub$open$14.L$4 = list;
                                hardwareInterfaceStub$open$14.L$5 = selfCheckSensorInfo;
                                hardwareInterfaceStub$open$14.L$6 = job;
                                hardwareInterfaceStub$open$14.L$7 = job41;
                                hardwareInterfaceStub$open$14.label = 9;
                                checkRGBD = HardwareInterfaceStub.INSTANCE.checkRGBD(selfCheckSensorInfo, hardwareInterfaceStub$open$14);
                                obj7 = obj6;
                                if (checkRGBD != obj7) {
                                    return obj7;
                                }
                                result11 = result9;
                                Job job42 = job;
                                job2 = job41;
                                List list10 = list;
                                job3 = job42;
                                long j21 = j5;
                                list2 = list10;
                                bool4 = bool3;
                                selfCheckSensorInfo2 = selfCheckSensorInfo;
                                coroutineScope7 = coroutineScope6;
                                i4 = i3;
                                result12 = result10;
                                j6 = j21;
                                Job job43 = (Job) checkRGBD;
                                HardwareInterfaceStub hardwareInterfaceStub29 = HardwareInterfaceStub.INSTANCE;
                                machineInfoProcess2 = HardwareInterfaceStub.machineInfoProcess;
                                Object obj21 = obj7;
                                if (machineInfoProcess2.getMachineInfo().getProductMachineType().getModel() != MachineModel.Ninetales) {
                                    HardwareInterfaceStub hardwareInterfaceStub30 = HardwareInterfaceStub.INSTANCE;
                                    machineInfoProcess3 = HardwareInterfaceStub.machineInfoProcess;
                                    if (machineInfoProcess3.getMachineInfo().getProductMachineType().getModel() != MachineModel.Firefox) {
                                        HardwareInterfaceStub hardwareInterfaceStub31 = HardwareInterfaceStub.INSTANCE;
                                        machineInfoProcess4 = HardwareInterfaceStub.machineInfoProcess;
                                        if (machineInfoProcess4.getMachineInfo().getSlamwareType() != MachineInfo.SlamcoreType.Default) {
                                            hardwareInterfaceStub$open$14.L$0 = coroutineScope7;
                                            hardwareInterfaceStub$open$14.J$0 = j4;
                                            hardwareInterfaceStub$open$14.I$0 = i4;
                                            hardwareInterfaceStub$open$14.L$1 = result12;
                                            hardwareInterfaceStub$open$14.L$2 = result11;
                                            hardwareInterfaceStub$open$14.J$1 = j6;
                                            hardwareInterfaceStub$open$14.L$3 = bool4;
                                            hardwareInterfaceStub$open$14.L$4 = list2;
                                            hardwareInterfaceStub$open$14.L$5 = selfCheckSensorInfo2;
                                            hardwareInterfaceStub$open$14.L$6 = job3;
                                            hardwareInterfaceStub$open$14.L$7 = job2;
                                            job8 = job2;
                                            hardwareInterfaceStub$open$14.L$8 = job43;
                                            hardwareInterfaceStub$open$14.label = 10;
                                            checkCamera = HardwareInterfaceStub.INSTANCE.checkCamera(selfCheckSensorInfo2, hardwareInterfaceStub$open$14);
                                            obj9 = obj21;
                                            if (checkCamera == obj9) {
                                                return obj9;
                                            }
                                            job9 = job43;
                                            job4 = (Job) checkCamera;
                                            Object obj22 = obj9;
                                            job7 = job9;
                                            bool5 = bool4;
                                            list3 = list2;
                                            selfCheckSensorInfo3 = selfCheckSensorInfo2;
                                            job5 = job3;
                                            job6 = job8;
                                            obj8 = obj22;
                                            coroutineScope8 = coroutineScope7;
                                            j7 = j4;
                                            hardwareInterfaceStub$open$14.L$0 = coroutineScope8;
                                            hardwareInterfaceStub$open$14.J$0 = j7;
                                            hardwareInterfaceStub$open$14.I$0 = i4;
                                            hardwareInterfaceStub$open$14.L$1 = result12;
                                            hardwareInterfaceStub$open$14.L$2 = result11;
                                            hardwareInterfaceStub$open$14.J$1 = j6;
                                            hardwareInterfaceStub$open$14.L$3 = bool5;
                                            hardwareInterfaceStub$open$14.L$4 = list3;
                                            hardwareInterfaceStub$open$14.L$5 = selfCheckSensorInfo3;
                                            hardwareInterfaceStub$open$14.L$6 = job5;
                                            hardwareInterfaceStub$open$14.L$7 = job6;
                                            hardwareInterfaceStub$open$14.L$8 = job7;
                                            bool6 = bool5;
                                            Job job44 = job4;
                                            hardwareInterfaceStub$open$14.L$9 = job44;
                                            job10 = job44;
                                            hardwareInterfaceStub$open$14.label = 11;
                                            checkLidar = HardwareInterfaceStub.INSTANCE.checkLidar(selfCheckSensorInfo3, hardwareInterfaceStub$open$14);
                                            obj10 = obj8;
                                            if (checkLidar == obj10) {
                                                return obj10;
                                            }
                                            job11 = (Job) checkLidar;
                                            coroutineScope9 = coroutineScope8;
                                            i5 = i4;
                                            Job job45 = job10;
                                            obj11 = obj10;
                                            Job job46 = job5;
                                            job12 = job7;
                                            j8 = j7;
                                            result13 = result11;
                                            result14 = result12;
                                            bool7 = bool6;
                                            j9 = j6;
                                            list4 = list3;
                                            hardwareInterfaceStub$open$15 = hardwareInterfaceStub$open$14;
                                            job13 = job46;
                                            selfCheckSensorInfo4 = selfCheckSensorInfo3;
                                            job14 = job6;
                                            job15 = job45;
                                            while (true) {
                                                HardwareInterfaceStub hardwareInterfaceStub32 = HardwareInterfaceStub.INSTANCE;
                                                atomicBoolean2 = HardwareInterfaceStub.waitSelfCheck;
                                                if (atomicBoolean2.get()) {
                                                    Job job47 = job15;
                                                    Job job48 = job12;
                                                    obj12 = obj11;
                                                    if (HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE).getMachineType().getModel() == MachineModel.Peanut) {
                                                        HardwareInterfaceStub hardwareInterfaceStub33 = HardwareInterfaceStub.INSTANCE;
                                                        str7 = HardwareInterfaceStub.TAG;
                                                        job16 = job14;
                                                        Pdlog.m3273d(str7, "init Peanut usb controller");
                                                        HardwareInterfaceStub hardwareInterfaceStub34 = HardwareInterfaceStub.INSTANCE;
                                                        uSBController = HardwareInterfaceStub.mUsbController;
                                                        uSBController.init();
                                                    } else {
                                                        job16 = job14;
                                                    }
                                                    if (job13 != null) {
                                                        hardwareInterfaceStub$open$15.L$0 = coroutineScope9;
                                                        hardwareInterfaceStub$open$15.J$0 = j8;
                                                        hardwareInterfaceStub$open$15.I$0 = i5;
                                                        hardwareInterfaceStub$open$15.L$1 = result14;
                                                        hardwareInterfaceStub$open$15.L$2 = result13;
                                                        hardwareInterfaceStub$open$15.J$1 = j9;
                                                        hardwareInterfaceStub$open$15.L$3 = bool7;
                                                        hardwareInterfaceStub$open$15.L$4 = list4;
                                                        hardwareInterfaceStub$open$15.L$5 = selfCheckSensorInfo4;
                                                        hardwareInterfaceStub$open$15.L$6 = job13;
                                                        Job job49 = job16;
                                                        hardwareInterfaceStub$open$15.L$7 = job49;
                                                        hardwareInterfaceStub$open$15.L$8 = job48;
                                                        hardwareInterfaceStub$open$15.L$9 = job47;
                                                        CoroutineScope coroutineScope13 = coroutineScope9;
                                                        Job job50 = job11;
                                                        hardwareInterfaceStub$open$15.L$10 = job50;
                                                        job11 = job50;
                                                        hardwareInterfaceStub$open$15.label = 13;
                                                        j11 = j8;
                                                        obj13 = obj12;
                                                        if (JobKt.cancelAndJoin(job13, hardwareInterfaceStub$open$15) != obj13) {
                                                            job20 = job47;
                                                            job21 = job13;
                                                            selfCheckSensorInfo5 = selfCheckSensorInfo4;
                                                            list5 = list4;
                                                            coroutineScope9 = coroutineScope13;
                                                            job18 = job49;
                                                            job19 = job48;
                                                            break;
                                                        } else {
                                                            return obj13;
                                                        }
                                                    } else {
                                                        job17 = job47;
                                                        j10 = j8;
                                                        job18 = job16;
                                                        job19 = job48;
                                                    }
                                                } else {
                                                    job22 = job15;
                                                    job23 = job12;
                                                    hardwareInterfaceStub$open$15.L$0 = coroutineScope9;
                                                    hardwareInterfaceStub$open$15.J$0 = j8;
                                                    hardwareInterfaceStub$open$15.I$0 = i5;
                                                    hardwareInterfaceStub$open$15.L$1 = result14;
                                                    hardwareInterfaceStub$open$15.L$2 = result13;
                                                    hardwareInterfaceStub$open$15.J$1 = j9;
                                                    hardwareInterfaceStub$open$15.L$3 = bool7;
                                                    hardwareInterfaceStub$open$15.L$4 = list4;
                                                    hardwareInterfaceStub$open$15.L$5 = selfCheckSensorInfo4;
                                                    hardwareInterfaceStub$open$15.L$6 = job13;
                                                    hardwareInterfaceStub$open$15.L$7 = job14;
                                                    hardwareInterfaceStub$open$15.L$8 = job23;
                                                    hardwareInterfaceStub$open$15.L$9 = job22;
                                                    Job job51 = job11;
                                                    hardwareInterfaceStub$open$15.L$10 = job51;
                                                    job11 = job51;
                                                    hardwareInterfaceStub$open$15.label = 12;
                                                    obj14 = obj11;
                                                    if (DelayKt.delay(20L, hardwareInterfaceStub$open$15) == obj14) {
                                                        return obj14;
                                                    }
                                                    obj11 = obj14;
                                                    job15 = job22;
                                                    job12 = job23;
                                                }
                                            }
                                            if (job18 != null) {
                                                hardwareInterfaceStub$open$15.L$0 = coroutineScope9;
                                                hardwareInterfaceStub$open$15.J$0 = j10;
                                                hardwareInterfaceStub$open$15.I$0 = i5;
                                                hardwareInterfaceStub$open$15.L$1 = result14;
                                                hardwareInterfaceStub$open$15.L$2 = result13;
                                                hardwareInterfaceStub$open$15.J$1 = j9;
                                                hardwareInterfaceStub$open$15.L$3 = bool7;
                                                hardwareInterfaceStub$open$15.L$4 = list4;
                                                hardwareInterfaceStub$open$15.L$5 = selfCheckSensorInfo4;
                                                hardwareInterfaceStub$open$15.L$6 = job13;
                                                hardwareInterfaceStub$open$15.L$7 = job18;
                                                hardwareInterfaceStub$open$15.L$8 = job19;
                                                hardwareInterfaceStub$open$15.L$9 = job17;
                                                CoroutineScope coroutineScope14 = coroutineScope9;
                                                Job job52 = job11;
                                                hardwareInterfaceStub$open$15.L$10 = job52;
                                                job11 = job52;
                                                hardwareInterfaceStub$open$15.label = 14;
                                                j12 = j10;
                                                obj15 = obj12;
                                                if (JobKt.cancelAndJoin(job18, hardwareInterfaceStub$open$15) == obj15) {
                                                    return obj15;
                                                }
                                                job24 = job17;
                                                job25 = job13;
                                                selfCheckSensorInfo6 = selfCheckSensorInfo4;
                                                list6 = list4;
                                                coroutineScope9 = coroutineScope14;
                                                Unit unit = Unit.INSTANCE;
                                                obj12 = obj15;
                                                list4 = list6;
                                                selfCheckSensorInfo4 = selfCheckSensorInfo6;
                                                job13 = job25;
                                                job17 = job24;
                                                long j22 = j12;
                                                if (job19 != null) {
                                                    hardwareInterfaceStub$open$15.L$0 = coroutineScope9;
                                                    hardwareInterfaceStub$open$15.J$0 = j22;
                                                    hardwareInterfaceStub$open$15.I$0 = i5;
                                                    hardwareInterfaceStub$open$15.L$1 = result14;
                                                    hardwareInterfaceStub$open$15.L$2 = result13;
                                                    hardwareInterfaceStub$open$15.J$1 = j9;
                                                    hardwareInterfaceStub$open$15.L$3 = bool7;
                                                    hardwareInterfaceStub$open$15.L$4 = list4;
                                                    hardwareInterfaceStub$open$15.L$5 = selfCheckSensorInfo4;
                                                    hardwareInterfaceStub$open$15.L$6 = job13;
                                                    hardwareInterfaceStub$open$15.L$7 = job18;
                                                    hardwareInterfaceStub$open$15.L$8 = job19;
                                                    hardwareInterfaceStub$open$15.L$9 = job17;
                                                    coroutineScope10 = coroutineScope9;
                                                    Job job53 = job11;
                                                    hardwareInterfaceStub$open$15.L$10 = job53;
                                                    hardwareInterfaceStub$open$15.label = 15;
                                                    j15 = j22;
                                                    obj17 = obj12;
                                                    if (JobKt.cancelAndJoin(job19, hardwareInterfaceStub$open$15) == obj17) {
                                                        return obj17;
                                                    }
                                                    job30 = job17;
                                                    job31 = job13;
                                                    selfCheckSensorInfo8 = selfCheckSensorInfo4;
                                                    list8 = list4;
                                                    job32 = job53;
                                                    Unit unit2 = Unit.INSTANCE;
                                                    job11 = job32;
                                                    obj16 = obj17;
                                                    hardwareInterfaceStub$open$16 = hardwareInterfaceStub$open$15;
                                                    coroutineScope9 = coroutineScope10;
                                                    Job job54 = job19;
                                                    job28 = job30;
                                                    j13 = j9;
                                                    job26 = job31;
                                                    selfCheckSensorInfo7 = selfCheckSensorInfo8;
                                                    result16 = result14;
                                                    i6 = i5;
                                                    bool8 = bool7;
                                                    result15 = result13;
                                                    job29 = job18;
                                                    list7 = list8;
                                                    j14 = j15;
                                                    job27 = job54;
                                                    if (job28 != null) {
                                                        hardwareInterfaceStub$open$16.L$0 = coroutineScope9;
                                                        hardwareInterfaceStub$open$16.J$0 = j14;
                                                        hardwareInterfaceStub$open$16.I$0 = i6;
                                                        hardwareInterfaceStub$open$16.L$1 = result16;
                                                        hardwareInterfaceStub$open$16.L$2 = result15;
                                                        hardwareInterfaceStub$open$16.J$1 = j13;
                                                        hardwareInterfaceStub$open$16.L$3 = bool8;
                                                        hardwareInterfaceStub$open$16.L$4 = list7;
                                                        hardwareInterfaceStub$open$16.L$5 = selfCheckSensorInfo7;
                                                        hardwareInterfaceStub$open$16.L$6 = job26;
                                                        hardwareInterfaceStub$open$16.L$7 = job29;
                                                        hardwareInterfaceStub$open$16.L$8 = job27;
                                                        hardwareInterfaceStub$open$16.L$9 = job28;
                                                        CoroutineScope coroutineScope15 = coroutineScope9;
                                                        Job job55 = job11;
                                                        hardwareInterfaceStub$open$16.L$10 = job55;
                                                        hardwareInterfaceStub$open$16.label = 16;
                                                        j17 = j13;
                                                        obj19 = obj16;
                                                        if (JobKt.cancelAndJoin(job28, hardwareInterfaceStub$open$16) == obj19) {
                                                            return obj19;
                                                        }
                                                        job38 = job55;
                                                        coroutineScope12 = coroutineScope15;
                                                        job39 = job29;
                                                        selfCheckSensorInfo10 = selfCheckSensorInfo7;
                                                        bool10 = bool8;
                                                        Unit unit3 = Unit.INSTANCE;
                                                        job37 = job28;
                                                        job35 = job26;
                                                        result17 = result16;
                                                        job36 = job38;
                                                        j16 = j17;
                                                        Object obj23 = obj19;
                                                        coroutineScope11 = coroutineScope12;
                                                        list9 = list7;
                                                        job33 = job39;
                                                        obj18 = obj23;
                                                        HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo11 = selfCheckSensorInfo10;
                                                        job34 = job27;
                                                        bool9 = bool10;
                                                        hardwareInterfaceStub$open$17 = hardwareInterfaceStub$open$16;
                                                        result18 = result15;
                                                        selfCheckSensorInfo9 = selfCheckSensorInfo11;
                                                        if (job36 == null) {
                                                            i7 = i6;
                                                            elapsedRealtime = j14;
                                                            if (list9.isEmpty()) {
                                                            }
                                                            if (i == 0) {
                                                            }
                                                            HardwareInterfaceStub hardwareInterfaceStub1022222 = HardwareInterfaceStub.INSTANCE;
                                                            str8 = HardwareInterfaceStub.TAG;
                                                            Pdlog.m3273d(str8, "init hardware spends start check " + (SystemClock.elapsedRealtime() - elapsedRealtime));
                                                            HardwareInterfaceStub hardwareInterfaceStub1122222 = HardwareInterfaceStub.INSTANCE;
                                                            HardwareInterfaceStub.openJob = (Job) null;
                                                            return Unit.INSTANCE;
                                                        }
                                                        hardwareInterfaceStub$open$17.L$0 = coroutineScope11;
                                                        hardwareInterfaceStub$open$17.J$0 = j14;
                                                        hardwareInterfaceStub$open$17.I$0 = i6;
                                                        hardwareInterfaceStub$open$17.L$1 = result17;
                                                        hardwareInterfaceStub$open$17.L$2 = result18;
                                                        hardwareInterfaceStub$open$17.J$1 = j16;
                                                        hardwareInterfaceStub$open$17.L$3 = bool9;
                                                        hardwareInterfaceStub$open$17.L$4 = list9;
                                                        hardwareInterfaceStub$open$17.L$5 = selfCheckSensorInfo9;
                                                        hardwareInterfaceStub$open$17.L$6 = job35;
                                                        hardwareInterfaceStub$open$17.L$7 = job33;
                                                        hardwareInterfaceStub$open$17.L$8 = job34;
                                                        hardwareInterfaceStub$open$17.L$9 = job37;
                                                        hardwareInterfaceStub$open$17.L$10 = job36;
                                                        hardwareInterfaceStub$open$17.label = 17;
                                                        Object obj24 = obj18;
                                                        if (JobKt.cancelAndJoin(job36, hardwareInterfaceStub$open$17) == obj24) {
                                                            return obj24;
                                                        }
                                                        i7 = i6;
                                                        j18 = j14;
                                                        Unit unit4 = Unit.INSTANCE;
                                                        elapsedRealtime = j18;
                                                        if (list9.isEmpty()) {
                                                            HardwareInterfaceStub hardwareInterfaceStub35 = HardwareInterfaceStub.INSTANCE;
                                                            threadSafeListener15 = HardwareInterfaceStub.hardwareListener;
                                                            threadSafeListener15.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1.19
                                                                @Override // kotlin.jvm.functions.Function2
                                                                public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str10) {
                                                                    invoke2(iHardware, str10);
                                                                    return Unit.INSTANCE;
                                                                }

                                                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                                public final void invoke2(IHardware l, String str10) {
                                                                    Intrinsics.checkParameterIsNotNull(l, "l");
                                                                    Intrinsics.checkParameterIsNotNull(str10, "<anonymous parameter 1>");
                                                                    l.onOpenStep(HardwareOpenStep.Finish, StepState.Success, "");
                                                                }
                                                            });
                                                            i = 1;
                                                        } else {
                                                            i = i7;
                                                        }
                                                        if (i == 0) {
                                                        }
                                                        HardwareInterfaceStub hardwareInterfaceStub10222222 = HardwareInterfaceStub.INSTANCE;
                                                        str8 = HardwareInterfaceStub.TAG;
                                                        Pdlog.m3273d(str8, "init hardware spends start check " + (SystemClock.elapsedRealtime() - elapsedRealtime));
                                                        HardwareInterfaceStub hardwareInterfaceStub11222222 = HardwareInterfaceStub.INSTANCE;
                                                        HardwareInterfaceStub.openJob = (Job) null;
                                                        return Unit.INSTANCE;
                                                    }
                                                    CoroutineScope coroutineScope16 = coroutineScope9;
                                                    long j23 = j13;
                                                    list9 = list7;
                                                    job33 = job29;
                                                    job34 = job27;
                                                    bool9 = bool8;
                                                    job35 = job26;
                                                    result17 = result16;
                                                    job36 = job11;
                                                    obj18 = obj16;
                                                    coroutineScope11 = coroutineScope16;
                                                    job37 = job28;
                                                    j16 = j23;
                                                    HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo12 = selfCheckSensorInfo7;
                                                    hardwareInterfaceStub$open$17 = hardwareInterfaceStub$open$16;
                                                    result18 = result15;
                                                    selfCheckSensorInfo9 = selfCheckSensorInfo12;
                                                    if (job36 == null) {
                                                    }
                                                } else {
                                                    obj16 = obj12;
                                                    j13 = j9;
                                                    job26 = job13;
                                                    selfCheckSensorInfo7 = selfCheckSensorInfo4;
                                                    i6 = i5;
                                                    result15 = result13;
                                                    list7 = list4;
                                                    hardwareInterfaceStub$open$16 = hardwareInterfaceStub$open$15;
                                                    j14 = j22;
                                                    Job job56 = job18;
                                                    job27 = job19;
                                                    job28 = job17;
                                                    result16 = result14;
                                                    bool8 = bool7;
                                                    job29 = job56;
                                                    if (job28 != null) {
                                                    }
                                                }
                                            } else {
                                                j12 = j10;
                                                long j222 = j12;
                                                if (job19 != null) {
                                                }
                                            }
                                        }
                                    }
                                }
                                Job job57 = job2;
                                list2.remove(HardwareInterfaceStub.SelfCheckSensor.MarkerCamera);
                                bool5 = bool4;
                                job4 = null;
                                list3 = list2;
                                selfCheckSensorInfo3 = selfCheckSensorInfo2;
                                job5 = job3;
                                job6 = job57;
                                obj8 = obj21;
                                job7 = job43;
                                coroutineScope8 = coroutineScope7;
                                j7 = j4;
                                hardwareInterfaceStub$open$14.L$0 = coroutineScope8;
                                hardwareInterfaceStub$open$14.J$0 = j7;
                                hardwareInterfaceStub$open$14.I$0 = i4;
                                hardwareInterfaceStub$open$14.L$1 = result12;
                                hardwareInterfaceStub$open$14.L$2 = result11;
                                hardwareInterfaceStub$open$14.J$1 = j6;
                                hardwareInterfaceStub$open$14.L$3 = bool5;
                                hardwareInterfaceStub$open$14.L$4 = list3;
                                hardwareInterfaceStub$open$14.L$5 = selfCheckSensorInfo3;
                                hardwareInterfaceStub$open$14.L$6 = job5;
                                hardwareInterfaceStub$open$14.L$7 = job6;
                                hardwareInterfaceStub$open$14.L$8 = job7;
                                bool6 = bool5;
                                Job job442 = job4;
                                hardwareInterfaceStub$open$14.L$9 = job442;
                                job10 = job442;
                                hardwareInterfaceStub$open$14.label = 11;
                                checkLidar = HardwareInterfaceStub.INSTANCE.checkLidar(selfCheckSensorInfo3, hardwareInterfaceStub$open$14);
                                obj10 = obj8;
                                if (checkLidar == obj10) {
                                }
                                job11 = (Job) checkLidar;
                                coroutineScope9 = coroutineScope8;
                                i5 = i4;
                                Job job452 = job10;
                                obj11 = obj10;
                                Job job462 = job5;
                                job12 = job7;
                                j8 = j7;
                                result13 = result11;
                                result14 = result12;
                                bool7 = bool6;
                                j9 = j6;
                                list4 = list3;
                                hardwareInterfaceStub$open$15 = hardwareInterfaceStub$open$14;
                                job13 = job462;
                                selfCheckSensorInfo4 = selfCheckSensorInfo3;
                                job14 = job6;
                                job15 = job452;
                                while (true) {
                                    HardwareInterfaceStub hardwareInterfaceStub322 = HardwareInterfaceStub.INSTANCE;
                                    atomicBoolean2 = HardwareInterfaceStub.waitSelfCheck;
                                    if (atomicBoolean2.get()) {
                                    }
                                    obj11 = obj14;
                                    job15 = job22;
                                    job12 = job23;
                                }
                                if (job18 != null) {
                                }
                            }
                        }
                        HardwareInterfaceStub hardwareInterfaceStub36 = HardwareInterfaceStub.INSTANCE;
                        str2 = HardwareInterfaceStub.TAG;
                        Object[] objArr = new Object[1];
                        StringBuilder sb = new StringBuilder();
                        sb.append("wait rgbd or scheduler interface initialized ");
                        HardwareInterfaceStub hardwareInterfaceStub37 = HardwareInterfaceStub.INSTANCE;
                        rGBDInterfaceImpl2 = HardwareInterfaceStub.rgbdInterfaceImpl;
                        sb.append(rGBDInterfaceImpl2 != null);
                        sb.append(" && ");
                        HardwareInterfaceStub hardwareInterfaceStub38 = HardwareInterfaceStub.INSTANCE;
                        scheduleCommunicationImpl = HardwareInterfaceStub.scheduler;
                        sb.append(scheduleCommunicationImpl != null);
                        objArr[0] = sb.toString();
                        Pdlog.m3273d(str2, objArr);
                        hardwareInterfaceStub$open$1.L$0 = coroutineScope2;
                        hardwareInterfaceStub$open$1.J$0 = elapsedRealtime;
                        hardwareInterfaceStub$open$1.label = 1;
                        if (DelayKt.delay(50L, hardwareInterfaceStub$open$1) == obj3) {
                            return obj3;
                        }
                    }
                    HardwareInterfaceStub hardwareInterfaceStub42 = HardwareInterfaceStub.INSTANCE;
                    atomicBoolean = HardwareInterfaceStub.waitSelfCheck;
                    atomicBoolean.set(true);
                    HardwareInterfaceStub hardwareInterfaceStub52 = HardwareInterfaceStub.INSTANCE;
                    threadSafeListener = HardwareInterfaceStub.hardwareListener;
                    threadSafeListener.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1.5
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str10) {
                            invoke2(iHardware, str10);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IHardware l, String str10) {
                            Intrinsics.checkParameterIsNotNull(l, "l");
                            Intrinsics.checkParameterIsNotNull(str10, "<anonymous parameter 1>");
                            l.onOpenStep(HardwareOpenStep.CanConnect, StepState.Running, "");
                        }
                    });
                    CANBus access$getCanBus$p4 = HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE);
                    hardwareInterfaceStub$open$1.L$0 = coroutineScope2;
                    hardwareInterfaceStub$open$1.J$0 = elapsedRealtime;
                    hardwareInterfaceStub$open$1.I$0 = 0;
                    hardwareInterfaceStub$open$1.label = 2;
                    bootUp = access$getCanBus$p4.bootUp(hardwareInterfaceStub$open$1);
                    if (bootUp == obj3) {
                    }
                case 1:
                    obj2 = coroutine_suspended;
                    elapsedRealtime = this.J$0;
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    coroutineScope2 = coroutineScope;
                    hardwareInterfaceStub$open$1 = this;
                    obj3 = obj2;
                    while (CoroutineScopeKt.isActive(coroutineScope2)) {
                    }
                    HardwareInterfaceStub hardwareInterfaceStub422 = HardwareInterfaceStub.INSTANCE;
                    atomicBoolean = HardwareInterfaceStub.waitSelfCheck;
                    atomicBoolean.set(true);
                    HardwareInterfaceStub hardwareInterfaceStub522 = HardwareInterfaceStub.INSTANCE;
                    threadSafeListener = HardwareInterfaceStub.hardwareListener;
                    threadSafeListener.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1.5
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str10) {
                            invoke2(iHardware, str10);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IHardware l, String str10) {
                            Intrinsics.checkParameterIsNotNull(l, "l");
                            Intrinsics.checkParameterIsNotNull(str10, "<anonymous parameter 1>");
                            l.onOpenStep(HardwareOpenStep.CanConnect, StepState.Running, "");
                        }
                    });
                    CANBus access$getCanBus$p42 = HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE);
                    hardwareInterfaceStub$open$1.L$0 = coroutineScope2;
                    hardwareInterfaceStub$open$1.J$0 = elapsedRealtime;
                    hardwareInterfaceStub$open$1.I$0 = 0;
                    hardwareInterfaceStub$open$1.label = 2;
                    bootUp = access$getCanBus$p42.bootUp(hardwareInterfaceStub$open$1);
                    if (bootUp == obj3) {
                    }
                    break;
                case 2:
                    int i8 = this.I$0;
                    elapsedRealtime = this.J$0;
                    coroutineScope2 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    bootUp = obj;
                    i = i8;
                    hardwareInterfaceStub$open$1 = this;
                    obj3 = coroutine_suspended;
                    result = (CANBus.Result) bootUp;
                    if (result.isSuccess()) {
                    }
                    break;
                case 3:
                    CANBus.Result result21 = (CANBus.Result) this.L$1;
                    int i9 = this.I$0;
                    long j24 = this.J$0;
                    CoroutineScope coroutineScope17 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    fetchMachineInfo = obj;
                    i = i9;
                    coroutineScope3 = coroutineScope17;
                    result2 = result21;
                    j = j24;
                    obj3 = coroutine_suspended;
                    hardwareInterfaceStub$open$12 = this;
                    result3 = (HardwareInterfaceStub.Result) fetchMachineInfo;
                    if (result3.isSuccess()) {
                    }
                    break;
                case 4:
                    result4 = (HardwareInterfaceStub.Result) this.L$2;
                    CANBus.Result result22 = (CANBus.Result) this.L$1;
                    int i10 = this.I$0;
                    long j25 = this.J$0;
                    CoroutineScope coroutineScope18 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    hardwareVersion = obj;
                    i2 = i10;
                    coroutineScope4 = coroutineScope18;
                    result5 = result22;
                    j2 = j25;
                    obj4 = coroutine_suspended;
                    hardwareInterfaceStub$open$13 = this;
                    if (((Boolean) hardwareVersion).booleanValue()) {
                    }
                    break;
                case 5:
                    j19 = this.J$1;
                    HardwareInterfaceStub.Result result23 = (HardwareInterfaceStub.Result) this.L$2;
                    CANBus.Result result24 = (CANBus.Result) this.L$1;
                    int i11 = this.I$0;
                    long j26 = this.J$0;
                    CoroutineScope coroutineScope19 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    withTimeoutOrNull = obj;
                    i2 = i11;
                    coroutineScope4 = coroutineScope19;
                    result5 = result24;
                    j2 = j26;
                    result6 = result23;
                    hardwareInterfaceStub$open$13 = this;
                    obj5 = coroutine_suspended;
                    bool = (Boolean) withTimeoutOrNull;
                    if (Intrinsics.areEqual(bool, Boxing.boxBoolean(true))) {
                    }
                    break;
                case 6:
                    bool2 = (Boolean) this.L$3;
                    long j27 = this.J$1;
                    HardwareInterfaceStub.Result result25 = (HardwareInterfaceStub.Result) this.L$2;
                    CANBus.Result result26 = (CANBus.Result) this.L$1;
                    int i12 = this.I$0;
                    long j28 = this.J$0;
                    CoroutineScope coroutineScope20 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    withTimeoutOrNull2 = obj;
                    i3 = i12;
                    coroutineScope5 = coroutineScope20;
                    result7 = result26;
                    j3 = j28;
                    hardwareInterfaceStub$open$14 = this;
                    result8 = result25;
                    j19 = j27;
                    obj6 = coroutine_suspended;
                    if (!(!Intrinsics.areEqual((Boolean) withTimeoutOrNull2, Boxing.boxBoolean(true)))) {
                    }
                    break;
                case 7:
                    HardwareInterfaceStub$open$1$selfCheckInfo$1 hardwareInterfaceStub$open$1$selfCheckInfo$1 = (HardwareInterfaceStub$open$1$selfCheckInfo$1) this.L$5;
                    List list11 = (List) this.L$4;
                    Boolean bool12 = (Boolean) this.L$3;
                    long j29 = this.J$1;
                    HardwareInterfaceStub.Result result27 = (HardwareInterfaceStub.Result) this.L$2;
                    CANBus.Result result28 = (CANBus.Result) this.L$1;
                    int i13 = this.I$0;
                    long j30 = this.J$0;
                    CoroutineScope coroutineScope21 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    selfCheckSensorInfo = hardwareInterfaceStub$open$1$selfCheckInfo$1;
                    j4 = j30;
                    coroutineScope6 = coroutineScope21;
                    checkIMU = obj;
                    result10 = result28;
                    i3 = i13;
                    hardwareInterfaceStub$open$14 = this;
                    result9 = result27;
                    j5 = j29;
                    bool3 = bool12;
                    list = list11;
                    obj6 = coroutine_suspended;
                    Job job402 = (Job) checkIMU;
                    hardwareInterfaceStub$open$14.L$0 = coroutineScope6;
                    hardwareInterfaceStub$open$14.J$0 = j4;
                    hardwareInterfaceStub$open$14.I$0 = i3;
                    hardwareInterfaceStub$open$14.L$1 = result10;
                    hardwareInterfaceStub$open$14.L$2 = result9;
                    hardwareInterfaceStub$open$14.J$1 = j5;
                    hardwareInterfaceStub$open$14.L$3 = bool3;
                    hardwareInterfaceStub$open$14.L$4 = list;
                    hardwareInterfaceStub$open$14.L$5 = selfCheckSensorInfo;
                    hardwareInterfaceStub$open$14.L$6 = job402;
                    hardwareInterfaceStub$open$14.label = 8;
                    checkEncoder = HardwareInterfaceStub.INSTANCE.checkEncoder(selfCheckSensorInfo, hardwareInterfaceStub$open$14);
                    if (checkEncoder != obj6) {
                    }
                    break;
                case 8:
                    Job job58 = (Job) this.L$6;
                    HardwareInterfaceStub$open$1$selfCheckInfo$1 hardwareInterfaceStub$open$1$selfCheckInfo$12 = (HardwareInterfaceStub$open$1$selfCheckInfo$1) this.L$5;
                    list = (List) this.L$4;
                    bool3 = (Boolean) this.L$3;
                    j5 = this.J$1;
                    HardwareInterfaceStub.Result result29 = (HardwareInterfaceStub.Result) this.L$2;
                    CANBus.Result result30 = (CANBus.Result) this.L$1;
                    int i14 = this.I$0;
                    long j31 = this.J$0;
                    coroutineScope6 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    selfCheckSensorInfo = hardwareInterfaceStub$open$1$selfCheckInfo$12;
                    j4 = j31;
                    obj6 = coroutine_suspended;
                    i3 = i14;
                    result10 = result30;
                    result9 = result29;
                    hardwareInterfaceStub$open$14 = this;
                    job = job58;
                    checkEncoder = obj;
                    Job job412 = (Job) checkEncoder;
                    hardwareInterfaceStub$open$14.L$0 = coroutineScope6;
                    hardwareInterfaceStub$open$14.J$0 = j4;
                    hardwareInterfaceStub$open$14.I$0 = i3;
                    hardwareInterfaceStub$open$14.L$1 = result10;
                    hardwareInterfaceStub$open$14.L$2 = result9;
                    hardwareInterfaceStub$open$14.J$1 = j5;
                    hardwareInterfaceStub$open$14.L$3 = bool3;
                    hardwareInterfaceStub$open$14.L$4 = list;
                    hardwareInterfaceStub$open$14.L$5 = selfCheckSensorInfo;
                    hardwareInterfaceStub$open$14.L$6 = job;
                    hardwareInterfaceStub$open$14.L$7 = job412;
                    hardwareInterfaceStub$open$14.label = 9;
                    checkRGBD = HardwareInterfaceStub.INSTANCE.checkRGBD(selfCheckSensorInfo, hardwareInterfaceStub$open$14);
                    obj7 = obj6;
                    if (checkRGBD != obj7) {
                    }
                    break;
                case 9:
                    Job job59 = (Job) this.L$7;
                    Job job60 = (Job) this.L$6;
                    HardwareInterfaceStub$open$1$selfCheckInfo$1 hardwareInterfaceStub$open$1$selfCheckInfo$13 = (HardwareInterfaceStub$open$1$selfCheckInfo$1) this.L$5;
                    List list12 = (List) this.L$4;
                    Boolean bool13 = (Boolean) this.L$3;
                    long j32 = this.J$1;
                    HardwareInterfaceStub.Result result31 = (HardwareInterfaceStub.Result) this.L$2;
                    CANBus.Result result32 = (CANBus.Result) this.L$1;
                    int i15 = this.I$0;
                    long j33 = this.J$0;
                    coroutineScope7 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    j4 = j33;
                    result12 = result32;
                    i4 = i15;
                    result11 = result31;
                    j6 = j32;
                    hardwareInterfaceStub$open$14 = this;
                    bool4 = bool13;
                    job2 = job59;
                    list2 = list12;
                    checkRGBD = obj;
                    selfCheckSensorInfo2 = hardwareInterfaceStub$open$1$selfCheckInfo$13;
                    job3 = job60;
                    obj7 = coroutine_suspended;
                    Job job432 = (Job) checkRGBD;
                    HardwareInterfaceStub hardwareInterfaceStub292 = HardwareInterfaceStub.INSTANCE;
                    machineInfoProcess2 = HardwareInterfaceStub.machineInfoProcess;
                    Object obj212 = obj7;
                    if (machineInfoProcess2.getMachineInfo().getProductMachineType().getModel() != MachineModel.Ninetales) {
                    }
                    Job job572 = job2;
                    list2.remove(HardwareInterfaceStub.SelfCheckSensor.MarkerCamera);
                    bool5 = bool4;
                    job4 = null;
                    list3 = list2;
                    selfCheckSensorInfo3 = selfCheckSensorInfo2;
                    job5 = job3;
                    job6 = job572;
                    obj8 = obj212;
                    job7 = job432;
                    coroutineScope8 = coroutineScope7;
                    j7 = j4;
                    hardwareInterfaceStub$open$14.L$0 = coroutineScope8;
                    hardwareInterfaceStub$open$14.J$0 = j7;
                    hardwareInterfaceStub$open$14.I$0 = i4;
                    hardwareInterfaceStub$open$14.L$1 = result12;
                    hardwareInterfaceStub$open$14.L$2 = result11;
                    hardwareInterfaceStub$open$14.J$1 = j6;
                    hardwareInterfaceStub$open$14.L$3 = bool5;
                    hardwareInterfaceStub$open$14.L$4 = list3;
                    hardwareInterfaceStub$open$14.L$5 = selfCheckSensorInfo3;
                    hardwareInterfaceStub$open$14.L$6 = job5;
                    hardwareInterfaceStub$open$14.L$7 = job6;
                    hardwareInterfaceStub$open$14.L$8 = job7;
                    bool6 = bool5;
                    Job job4422 = job4;
                    hardwareInterfaceStub$open$14.L$9 = job4422;
                    job10 = job4422;
                    hardwareInterfaceStub$open$14.label = 11;
                    checkLidar = HardwareInterfaceStub.INSTANCE.checkLidar(selfCheckSensorInfo3, hardwareInterfaceStub$open$14);
                    obj10 = obj8;
                    if (checkLidar == obj10) {
                    }
                    job11 = (Job) checkLidar;
                    coroutineScope9 = coroutineScope8;
                    i5 = i4;
                    Job job4522 = job10;
                    obj11 = obj10;
                    Job job4622 = job5;
                    job12 = job7;
                    j8 = j7;
                    result13 = result11;
                    result14 = result12;
                    bool7 = bool6;
                    j9 = j6;
                    list4 = list3;
                    hardwareInterfaceStub$open$15 = hardwareInterfaceStub$open$14;
                    job13 = job4622;
                    selfCheckSensorInfo4 = selfCheckSensorInfo3;
                    job14 = job6;
                    job15 = job4522;
                    while (true) {
                        HardwareInterfaceStub hardwareInterfaceStub3222 = HardwareInterfaceStub.INSTANCE;
                        atomicBoolean2 = HardwareInterfaceStub.waitSelfCheck;
                        if (atomicBoolean2.get()) {
                        }
                        obj11 = obj14;
                        job15 = job22;
                        job12 = job23;
                    }
                    if (job18 != null) {
                    }
                    break;
                case 10:
                    Job job61 = (Job) this.L$8;
                    Job job62 = (Job) this.L$7;
                    job3 = (Job) this.L$6;
                    selfCheckSensorInfo2 = (HardwareInterfaceStub$open$1$selfCheckInfo$1) this.L$5;
                    list2 = (List) this.L$4;
                    bool4 = (Boolean) this.L$3;
                    long j34 = this.J$1;
                    HardwareInterfaceStub.Result result33 = (HardwareInterfaceStub.Result) this.L$2;
                    CANBus.Result result34 = (CANBus.Result) this.L$1;
                    int i16 = this.I$0;
                    long j35 = this.J$0;
                    CoroutineScope coroutineScope22 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    job8 = job62;
                    obj9 = coroutine_suspended;
                    checkCamera = obj;
                    job9 = job61;
                    hardwareInterfaceStub$open$14 = this;
                    result11 = result33;
                    j6 = j34;
                    result12 = result34;
                    i4 = i16;
                    coroutineScope7 = coroutineScope22;
                    j4 = j35;
                    job4 = (Job) checkCamera;
                    Object obj222 = obj9;
                    job7 = job9;
                    bool5 = bool4;
                    list3 = list2;
                    selfCheckSensorInfo3 = selfCheckSensorInfo2;
                    job5 = job3;
                    job6 = job8;
                    obj8 = obj222;
                    coroutineScope8 = coroutineScope7;
                    j7 = j4;
                    hardwareInterfaceStub$open$14.L$0 = coroutineScope8;
                    hardwareInterfaceStub$open$14.J$0 = j7;
                    hardwareInterfaceStub$open$14.I$0 = i4;
                    hardwareInterfaceStub$open$14.L$1 = result12;
                    hardwareInterfaceStub$open$14.L$2 = result11;
                    hardwareInterfaceStub$open$14.J$1 = j6;
                    hardwareInterfaceStub$open$14.L$3 = bool5;
                    hardwareInterfaceStub$open$14.L$4 = list3;
                    hardwareInterfaceStub$open$14.L$5 = selfCheckSensorInfo3;
                    hardwareInterfaceStub$open$14.L$6 = job5;
                    hardwareInterfaceStub$open$14.L$7 = job6;
                    hardwareInterfaceStub$open$14.L$8 = job7;
                    bool6 = bool5;
                    Job job44222 = job4;
                    hardwareInterfaceStub$open$14.L$9 = job44222;
                    job10 = job44222;
                    hardwareInterfaceStub$open$14.label = 11;
                    checkLidar = HardwareInterfaceStub.INSTANCE.checkLidar(selfCheckSensorInfo3, hardwareInterfaceStub$open$14);
                    obj10 = obj8;
                    if (checkLidar == obj10) {
                    }
                    job11 = (Job) checkLidar;
                    coroutineScope9 = coroutineScope8;
                    i5 = i4;
                    Job job45222 = job10;
                    obj11 = obj10;
                    Job job46222 = job5;
                    job12 = job7;
                    j8 = j7;
                    result13 = result11;
                    result14 = result12;
                    bool7 = bool6;
                    j9 = j6;
                    list4 = list3;
                    hardwareInterfaceStub$open$15 = hardwareInterfaceStub$open$14;
                    job13 = job46222;
                    selfCheckSensorInfo4 = selfCheckSensorInfo3;
                    job14 = job6;
                    job15 = job45222;
                    while (true) {
                        HardwareInterfaceStub hardwareInterfaceStub32222 = HardwareInterfaceStub.INSTANCE;
                        atomicBoolean2 = HardwareInterfaceStub.waitSelfCheck;
                        if (atomicBoolean2.get()) {
                        }
                        obj11 = obj14;
                        job15 = job22;
                        job12 = job23;
                    }
                    if (job18 != null) {
                    }
                    break;
                case 11:
                    Job job63 = (Job) this.L$9;
                    job7 = (Job) this.L$8;
                    job6 = (Job) this.L$7;
                    job5 = (Job) this.L$6;
                    selfCheckSensorInfo3 = (HardwareInterfaceStub$open$1$selfCheckInfo$1) this.L$5;
                    list3 = (List) this.L$4;
                    Boolean bool14 = (Boolean) this.L$3;
                    j6 = this.J$1;
                    result11 = (HardwareInterfaceStub.Result) this.L$2;
                    result12 = (CANBus.Result) this.L$1;
                    i4 = this.I$0;
                    j7 = this.J$0;
                    coroutineScope8 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    bool6 = bool14;
                    hardwareInterfaceStub$open$14 = this;
                    obj10 = coroutine_suspended;
                    job10 = job63;
                    checkLidar = obj;
                    job11 = (Job) checkLidar;
                    coroutineScope9 = coroutineScope8;
                    i5 = i4;
                    Job job452222 = job10;
                    obj11 = obj10;
                    Job job462222 = job5;
                    job12 = job7;
                    j8 = j7;
                    result13 = result11;
                    result14 = result12;
                    bool7 = bool6;
                    j9 = j6;
                    list4 = list3;
                    hardwareInterfaceStub$open$15 = hardwareInterfaceStub$open$14;
                    job13 = job462222;
                    selfCheckSensorInfo4 = selfCheckSensorInfo3;
                    job14 = job6;
                    job15 = job452222;
                    while (true) {
                        HardwareInterfaceStub hardwareInterfaceStub322222 = HardwareInterfaceStub.INSTANCE;
                        atomicBoolean2 = HardwareInterfaceStub.waitSelfCheck;
                        if (atomicBoolean2.get()) {
                        }
                        obj11 = obj14;
                        job15 = job22;
                        job12 = job23;
                    }
                    if (job18 != null) {
                    }
                    break;
                case 12:
                    obj11 = coroutine_suspended;
                    Job job64 = (Job) this.L$10;
                    Job job65 = (Job) this.L$9;
                    Job job66 = (Job) this.L$8;
                    Job job67 = (Job) this.L$7;
                    Job job68 = (Job) this.L$6;
                    HardwareInterfaceStub$open$1$selfCheckInfo$1 hardwareInterfaceStub$open$1$selfCheckInfo$14 = (HardwareInterfaceStub$open$1$selfCheckInfo$1) this.L$5;
                    List list13 = (List) this.L$4;
                    Boolean bool15 = (Boolean) this.L$3;
                    long j36 = this.J$1;
                    HardwareInterfaceStub.Result result35 = (HardwareInterfaceStub.Result) this.L$2;
                    CANBus.Result result36 = (CANBus.Result) this.L$1;
                    int i17 = this.I$0;
                    long j37 = this.J$0;
                    job11 = job64;
                    coroutineScope9 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    hardwareInterfaceStub$open$15 = this;
                    job15 = job65;
                    j8 = j37;
                    result14 = result36;
                    i5 = i17;
                    result13 = result35;
                    j9 = j36;
                    list4 = list13;
                    bool7 = bool15;
                    selfCheckSensorInfo4 = hardwareInterfaceStub$open$1$selfCheckInfo$14;
                    job13 = job68;
                    job14 = job67;
                    job12 = job66;
                    while (true) {
                        HardwareInterfaceStub hardwareInterfaceStub3222222 = HardwareInterfaceStub.INSTANCE;
                        atomicBoolean2 = HardwareInterfaceStub.waitSelfCheck;
                        if (atomicBoolean2.get()) {
                        }
                        obj11 = obj14;
                        job15 = job22;
                        job12 = job23;
                    }
                    if (job18 != null) {
                    }
                    break;
                case 13:
                    Job job69 = (Job) this.L$10;
                    job20 = (Job) this.L$9;
                    job19 = (Job) this.L$8;
                    job18 = (Job) this.L$7;
                    job21 = (Job) this.L$6;
                    selfCheckSensorInfo5 = (HardwareInterfaceStub$open$1$selfCheckInfo$1) this.L$5;
                    list5 = (List) this.L$4;
                    Boolean bool16 = (Boolean) this.L$3;
                    long j38 = this.J$1;
                    HardwareInterfaceStub.Result result37 = (HardwareInterfaceStub.Result) this.L$2;
                    CANBus.Result result38 = (CANBus.Result) this.L$1;
                    int i18 = this.I$0;
                    long j39 = this.J$0;
                    job11 = job69;
                    coroutineScope9 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    j11 = j39;
                    result14 = result38;
                    i5 = i18;
                    result13 = result37;
                    j9 = j38;
                    bool7 = bool16;
                    hardwareInterfaceStub$open$15 = this;
                    obj13 = coroutine_suspended;
                    Unit unit5 = Unit.INSTANCE;
                    obj12 = obj13;
                    list4 = list5;
                    selfCheckSensorInfo4 = selfCheckSensorInfo5;
                    job13 = job21;
                    job17 = job20;
                    j10 = j11;
                    if (job18 != null) {
                    }
                    break;
                case 14:
                    Job job70 = (Job) this.L$10;
                    job24 = (Job) this.L$9;
                    job19 = (Job) this.L$8;
                    job18 = (Job) this.L$7;
                    job25 = (Job) this.L$6;
                    selfCheckSensorInfo6 = (HardwareInterfaceStub$open$1$selfCheckInfo$1) this.L$5;
                    list6 = (List) this.L$4;
                    Boolean bool17 = (Boolean) this.L$3;
                    long j40 = this.J$1;
                    HardwareInterfaceStub.Result result39 = (HardwareInterfaceStub.Result) this.L$2;
                    CANBus.Result result40 = (CANBus.Result) this.L$1;
                    int i19 = this.I$0;
                    long j41 = this.J$0;
                    job11 = job70;
                    coroutineScope9 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    j12 = j41;
                    result14 = result40;
                    i5 = i19;
                    result13 = result39;
                    j9 = j40;
                    bool7 = bool17;
                    hardwareInterfaceStub$open$15 = this;
                    obj15 = coroutine_suspended;
                    Unit unit6 = Unit.INSTANCE;
                    obj12 = obj15;
                    list4 = list6;
                    selfCheckSensorInfo4 = selfCheckSensorInfo6;
                    job13 = job25;
                    job17 = job24;
                    long j2222 = j12;
                    if (job19 != null) {
                    }
                    break;
                case 15:
                    Job job71 = (Job) this.L$10;
                    job30 = (Job) this.L$9;
                    job19 = (Job) this.L$8;
                    job18 = (Job) this.L$7;
                    job31 = (Job) this.L$6;
                    selfCheckSensorInfo8 = (HardwareInterfaceStub$open$1$selfCheckInfo$1) this.L$5;
                    list8 = (List) this.L$4;
                    Boolean bool18 = (Boolean) this.L$3;
                    long j42 = this.J$1;
                    HardwareInterfaceStub.Result result41 = (HardwareInterfaceStub.Result) this.L$2;
                    CANBus.Result result42 = (CANBus.Result) this.L$1;
                    int i20 = this.I$0;
                    long j43 = this.J$0;
                    CoroutineScope coroutineScope23 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    coroutineScope10 = coroutineScope23;
                    j15 = j43;
                    job32 = job71;
                    result14 = result42;
                    i5 = i20;
                    result13 = result41;
                    j9 = j42;
                    bool7 = bool18;
                    hardwareInterfaceStub$open$15 = this;
                    obj17 = coroutine_suspended;
                    Unit unit22 = Unit.INSTANCE;
                    job11 = job32;
                    obj16 = obj17;
                    hardwareInterfaceStub$open$16 = hardwareInterfaceStub$open$15;
                    coroutineScope9 = coroutineScope10;
                    Job job542 = job19;
                    job28 = job30;
                    j13 = j9;
                    job26 = job31;
                    selfCheckSensorInfo7 = selfCheckSensorInfo8;
                    result16 = result14;
                    i6 = i5;
                    bool8 = bool7;
                    result15 = result13;
                    job29 = job18;
                    list7 = list8;
                    j14 = j15;
                    job27 = job542;
                    if (job28 != null) {
                    }
                    break;
                case 16:
                    job38 = (Job) this.L$10;
                    job28 = (Job) this.L$9;
                    job27 = (Job) this.L$8;
                    Job job72 = (Job) this.L$7;
                    Job job73 = (Job) this.L$6;
                    selfCheckSensorInfo10 = (HardwareInterfaceStub$open$1$selfCheckInfo$1) this.L$5;
                    List list14 = (List) this.L$4;
                    bool10 = (Boolean) this.L$3;
                    long j44 = this.J$1;
                    result15 = (HardwareInterfaceStub.Result) this.L$2;
                    result16 = (CANBus.Result) this.L$1;
                    i6 = this.I$0;
                    job39 = job72;
                    j14 = this.J$0;
                    coroutineScope12 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    j17 = j44;
                    list7 = list14;
                    job26 = job73;
                    hardwareInterfaceStub$open$16 = this;
                    obj19 = coroutine_suspended;
                    Unit unit32 = Unit.INSTANCE;
                    job37 = job28;
                    job35 = job26;
                    result17 = result16;
                    job36 = job38;
                    j16 = j17;
                    Object obj232 = obj19;
                    coroutineScope11 = coroutineScope12;
                    list9 = list7;
                    job33 = job39;
                    obj18 = obj232;
                    HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo112 = selfCheckSensorInfo10;
                    job34 = job27;
                    bool9 = bool10;
                    hardwareInterfaceStub$open$17 = hardwareInterfaceStub$open$16;
                    result18 = result15;
                    selfCheckSensorInfo9 = selfCheckSensorInfo112;
                    if (job36 == null) {
                    }
                    break;
                case 17:
                    list9 = (List) this.L$4;
                    i7 = this.I$0;
                    j18 = this.J$0;
                    ResultKt.throwOnFailure(obj);
                    Unit unit42 = Unit.INSTANCE;
                    elapsedRealtime = j18;
                    if (list9.isEmpty()) {
                    }
                    if (i == 0) {
                    }
                    HardwareInterfaceStub hardwareInterfaceStub102222222 = HardwareInterfaceStub.INSTANCE;
                    str8 = HardwareInterfaceStub.TAG;
                    Pdlog.m3273d(str8, "init hardware spends start check " + (SystemClock.elapsedRealtime() - elapsedRealtime));
                    HardwareInterfaceStub hardwareInterfaceStub112222222 = HardwareInterfaceStub.INSTANCE;
                    HardwareInterfaceStub.openJob = (Job) null;
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th) {
            HardwareInterfaceStub hardwareInterfaceStub39 = HardwareInterfaceStub.INSTANCE;
            HardwareInterfaceStub.openJob = (Job) null;
            throw th;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1$1 */
    /* loaded from: classes.dex */
    final /* synthetic */ class C50041 extends MutablePropertyReference0 {
        C50041(HardwareInterfaceStub hardwareInterfaceStub) {
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

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1$2 */
    /* loaded from: classes.dex */
    final /* synthetic */ class C50152 extends MutablePropertyReference0 {
        C50152(HardwareInterfaceStub hardwareInterfaceStub) {
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

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1$3 */
    /* loaded from: classes.dex */
    final /* synthetic */ class C50263 extends MutablePropertyReference0 {
        C50263(HardwareInterfaceStub hardwareInterfaceStub) {
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

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1$4 */
    /* loaded from: classes.dex */
    final /* synthetic */ class C50274 extends MutablePropertyReference0 {
        C50274(HardwareInterfaceStub hardwareInterfaceStub) {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1$15", m3970f = "HardwareInterfaceStub.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1$15 */
    /* loaded from: classes.dex */
    public static final class C501015 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5914p$;

        C501015(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C501015 c501015 = new C501015(completion);
            c501015.f5914p$ = (CoroutineScope) obj;
            return c501015;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C501015) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            HardwareInterfaceStub.access$getScheduler$p(HardwareInterfaceStub.INSTANCE).open();
            return Unit.INSTANCE;
        }
    }

    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1$23", m3970f = "HardwareInterfaceStub.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$open$1$23 */
    /* loaded from: classes4.dex */
    static final class C501923 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5915p$;

        C501923(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C501923 c501923 = new C501923(completion);
            c501923.f5915p$ = (CoroutineScope) obj;
            return c501923;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C501923) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5915p$;
            HardwareInterfaceStub.access$getScheduler$p(HardwareInterfaceStub.INSTANCE).open();
            return Unit.INSTANCE;
        }
    }
}
