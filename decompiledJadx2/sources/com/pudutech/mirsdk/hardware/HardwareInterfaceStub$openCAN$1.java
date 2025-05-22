package com.pudutech.mirsdk.hardware;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.HardwareInterfaceStub;
import com.pudutech.mirsdk.hardware.MachineInfoProcess;
import com.pudutech.mirsdk.hardware.can.CANBus;
import com.pudutech.mirsdk.hardware.can.CANParserManager;
import com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep;
import com.pudutech.mirsdk.hardware.serialize.StepState;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCAN$1", m3970f = "HardwareInterfaceStub.kt", m3971i = {0, 0, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6}, m3972l = {254, 268, 299, 300, 303, 305, 306}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "success", "$this$launch", "success", "canBootUpResult", "$this$launch", "success", "canBootUpResult", "sensorList", "selfCheckInfo", "$this$launch", "success", "canBootUpResult", "sensorList", "selfCheckInfo", "imuJob", "$this$launch", "success", "canBootUpResult", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "$this$launch", "success", "canBootUpResult", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "$this$launch", "success", "canBootUpResult", "sensorList", "selfCheckInfo", "imuJob", "encoderJob"}, m3975s = {"L$0", "I$0", "L$0", "I$0", "L$1", "L$0", "I$0", "L$1", "L$2", "L$3", "L$0", "I$0", "L$1", "L$2", "L$3", "L$4", "L$0", "I$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "I$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "I$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
/* loaded from: classes.dex */
final class HardwareInterfaceStub$openCAN$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5919p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HardwareInterfaceStub$openCAN$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareInterfaceStub$openCAN$1 hardwareInterfaceStub$openCAN$1 = new HardwareInterfaceStub$openCAN$1(completion);
        hardwareInterfaceStub$openCAN$1.f5919p$ = (CoroutineScope) obj;
        return hardwareInterfaceStub$openCAN$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareInterfaceStub$openCAN$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u00062E\u0010\u0007\u001aA\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00010\b¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\r¢\u0006\u0004\b\u000e\u0010\u000f"}, m3961d2 = {"<anonymous>", "", "p1", "Lkotlin/UByte;", "Lkotlin/ParameterName;", "name", "protocol", "p2", "Lkotlin/Function2;", "", "id", "Lkotlin/UByteArray;", "byteArray", "parser", "invoke", "(BLkotlin/jvm/functions/Function2;)V"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCAN$1$5 */
    /* loaded from: classes.dex */
    public static final /* synthetic */ class C50435 extends FunctionReference implements Function2<UByte, Function2<? super Integer, ? super UByteArray, ? extends Unit>, Unit> {
        C50435(CANParserManager cANParserManager) {
            super(2, cANParserManager);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "regCanParser";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(CANParserManager.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "regCanParser-eLRuwBU(BLkotlin/jvm/functions/Function2;)V";
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(UByte uByte, Function2<? super Integer, ? super UByteArray, ? extends Unit> function2) {
            invoke(uByte.getData(), (Function2<? super Integer, ? super UByteArray, Unit>) function2);
            return Unit.INSTANCE;
        }

        public final void invoke(byte b, Function2<? super Integer, ? super UByteArray, Unit> p2) {
            Intrinsics.checkParameterIsNotNull(p2, "p2");
            ((CANParserManager) this.receiver).m4428regCanParsereLRuwBU(b, p2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0004\b\u0007\u0010\b"}, m3961d2 = {"<anonymous>", "", "p1", "Lkotlin/UByte;", "Lkotlin/ParameterName;", "name", "protocol", "invoke", "(B)V"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCAN$1$6 */
    /* loaded from: classes.dex */
    public static final /* synthetic */ class C50446 extends FunctionReference implements Function1<UByte, Unit> {
        C50446(CANParserManager cANParserManager) {
            super(1, cANParserManager);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "unRegCanParser";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(CANParserManager.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "unRegCanParser-7apg3OU(B)V";
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(UByte uByte) {
            invoke(uByte.getData());
            return Unit.INSTANCE;
        }

        public final void invoke(byte b) {
            ((CANParserManager) this.receiver).m4429unRegCanParser7apg3OU(b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0004\b\u0007\u0010\b"}, m3961d2 = {"<anonymous>", "", "p1", "Lkotlin/UByteArray;", "Lkotlin/ParameterName;", "name", "bytes", "invoke", "([B)V"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCAN$1$7 */
    /* loaded from: classes.dex */
    public static final /* synthetic */ class C50457 extends FunctionReference implements Function1<UByteArray, Unit> {
        C50457(CANBus cANBus) {
            super(1, cANBus);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return MqttServiceConstants.SEND_ACTION;
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(CANBus.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "send-GBYM_sE([B)V";
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(UByteArray uByteArray) {
            invoke(uByteArray.getStorage());
            return Unit.INSTANCE;
        }

        public final void invoke(byte[] p1) {
            Intrinsics.checkParameterIsNotNull(p1, "p1");
            ((CANBus) this.receiver).m4425sendGBYM_sE(p1);
        }
    }

    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "l", "Lcom/pudutech/mirsdk/hardware/IHardware;", "<anonymous parameter 1>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCAN$1$13 */
    /* loaded from: classes4.dex */
    static final class C503913 extends Lambda implements Function2<IHardware, String, Unit> {

        /* renamed from: $e */
        final /* synthetic */ Exception f5920$e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C503913(Exception exc) {
            super(2);
            this.f5920$e = exc;
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
            this.f5920$e.printStackTrace();
            sb.append(Unit.INSTANCE);
            l.onOpenStep(hardwareOpenStep, stepState, sb.toString());
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000c. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x02b2 A[Catch: all -> 0x0323, TryCatch #0 {all -> 0x0323, blocks: (B:8:0x0031, B:9:0x02ac, B:11:0x02b2, B:13:0x02fd, B:18:0x0050, B:22:0x0293, B:27:0x0070, B:30:0x0249, B:32:0x0255, B:39:0x0271, B:43:0x008c, B:45:0x023c, B:47:0x00a5, B:49:0x021a, B:54:0x00be, B:56:0x01b1, B:59:0x01bf, B:63:0x02c1, B:65:0x02de, B:67:0x00cb, B:69:0x0111, B:71:0x0119, B:72:0x013a, B:77:0x00d7), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x02fd A[Catch: all -> 0x0323, TRY_LEAVE, TryCatch #0 {all -> 0x0323, blocks: (B:8:0x0031, B:9:0x02ac, B:11:0x02b2, B:13:0x02fd, B:18:0x0050, B:22:0x0293, B:27:0x0070, B:30:0x0249, B:32:0x0255, B:39:0x0271, B:43:0x008c, B:45:0x023c, B:47:0x00a5, B:49:0x021a, B:54:0x00be, B:56:0x01b1, B:59:0x01bf, B:63:0x02c1, B:65:0x02de, B:67:0x00cb, B:69:0x0111, B:71:0x0119, B:72:0x013a, B:77:0x00d7), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0293 A[Catch: all -> 0x0323, TryCatch #0 {all -> 0x0323, blocks: (B:8:0x0031, B:9:0x02ac, B:11:0x02b2, B:13:0x02fd, B:18:0x0050, B:22:0x0293, B:27:0x0070, B:30:0x0249, B:32:0x0255, B:39:0x0271, B:43:0x008c, B:45:0x023c, B:47:0x00a5, B:49:0x021a, B:54:0x00be, B:56:0x01b1, B:59:0x01bf, B:63:0x02c1, B:65:0x02de, B:67:0x00cb, B:69:0x0111, B:71:0x0119, B:72:0x013a, B:77:0x00d7), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0255 A[Catch: all -> 0x0323, TryCatch #0 {all -> 0x0323, blocks: (B:8:0x0031, B:9:0x02ac, B:11:0x02b2, B:13:0x02fd, B:18:0x0050, B:22:0x0293, B:27:0x0070, B:30:0x0249, B:32:0x0255, B:39:0x0271, B:43:0x008c, B:45:0x023c, B:47:0x00a5, B:49:0x021a, B:54:0x00be, B:56:0x01b1, B:59:0x01bf, B:63:0x02c1, B:65:0x02de, B:67:0x00cb, B:69:0x0111, B:71:0x0119, B:72:0x013a, B:77:0x00d7), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x026f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0236 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x02de A[Catch: all -> 0x0323, TryCatch #0 {all -> 0x0323, blocks: (B:8:0x0031, B:9:0x02ac, B:11:0x02b2, B:13:0x02fd, B:18:0x0050, B:22:0x0293, B:27:0x0070, B:30:0x0249, B:32:0x0255, B:39:0x0271, B:43:0x008c, B:45:0x023c, B:47:0x00a5, B:49:0x021a, B:54:0x00be, B:56:0x01b1, B:59:0x01bf, B:63:0x02c1, B:65:0x02de, B:67:0x00cb, B:69:0x0111, B:71:0x0119, B:72:0x013a, B:77:0x00d7), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0119 A[Catch: all -> 0x0323, TryCatch #0 {all -> 0x0323, blocks: (B:8:0x0031, B:9:0x02ac, B:11:0x02b2, B:13:0x02fd, B:18:0x0050, B:22:0x0293, B:27:0x0070, B:30:0x0249, B:32:0x0255, B:39:0x0271, B:43:0x008c, B:45:0x023c, B:47:0x00a5, B:49:0x021a, B:54:0x00be, B:56:0x01b1, B:59:0x01bf, B:63:0x02c1, B:65:0x02de, B:67:0x00cb, B:69:0x0111, B:71:0x0119, B:72:0x013a, B:77:0x00d7), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x013a A[Catch: all -> 0x0323, TryCatch #0 {all -> 0x0323, blocks: (B:8:0x0031, B:9:0x02ac, B:11:0x02b2, B:13:0x02fd, B:18:0x0050, B:22:0x0293, B:27:0x0070, B:30:0x0249, B:32:0x0255, B:39:0x0271, B:43:0x008c, B:45:0x023c, B:47:0x00a5, B:49:0x021a, B:54:0x00be, B:56:0x01b1, B:59:0x01bf, B:63:0x02c1, B:65:0x02de, B:67:0x00cb, B:69:0x0111, B:71:0x0119, B:72:0x013a, B:77:0x00d7), top: B:2:0x000c }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        String str;
        AtomicBoolean atomicBoolean;
        ThreadSafeListener threadSafeListener;
        Object bootUp;
        CoroutineScope coroutineScope;
        int i;
        final CANBus.Result result;
        String str2;
        ThreadSafeListener threadSafeListener2;
        ThreadSafeListener threadSafeListener3;
        MachineInfoProcess machineInfoProcess;
        Object fetchFromHardware;
        int i2;
        CANBus.Result result2;
        String str3;
        ThreadSafeListener threadSafeListener4;
        int i3;
        ThreadSafeListener threadSafeListener5;
        String str4;
        ThreadSafeListener threadSafeListener6;
        String str5;
        ThreadSafeListener threadSafeListener7;
        MachineInfoProcess machineInfoProcess2;
        Object checkIMU;
        CANBus.Result result3;
        CoroutineScope coroutineScope2;
        List list;
        HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo;
        int i4;
        Object checkEncoder;
        CoroutineScope coroutineScope3;
        Job job;
        HardwareInterfaceStub$openCAN$1 hardwareInterfaceStub$openCAN$1;
        Job job2;
        Job job3;
        CoroutineScope coroutineScope4;
        int i5;
        CANBus.Result result4;
        List list2;
        HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo2;
        AtomicBoolean atomicBoolean2;
        Job job4;
        List list3;
        String str6;
        ThreadSafeListener threadSafeListener8;
        ThreadSafeListener threadSafeListener9;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope5 = this.f5919p$;
                    HardwareInterfaceStub hardwareInterfaceStub = HardwareInterfaceStub.INSTANCE;
                    str = HardwareInterfaceStub.TAG;
                    Pdlog.m3275i(str, "open CAN async");
                    HardwareInterfaceStub hardwareInterfaceStub2 = HardwareInterfaceStub.INSTANCE;
                    atomicBoolean = HardwareInterfaceStub.waitSelfCheck;
                    atomicBoolean.set(true);
                    HardwareInterfaceStub hardwareInterfaceStub3 = HardwareInterfaceStub.INSTANCE;
                    threadSafeListener = HardwareInterfaceStub.hardwareListener;
                    threadSafeListener.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCAN$1.1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str7) {
                            invoke2(iHardware, str7);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IHardware l, String str7) {
                            Intrinsics.checkParameterIsNotNull(l, "l");
                            Intrinsics.checkParameterIsNotNull(str7, "<anonymous parameter 1>");
                            l.onOpenStep(HardwareOpenStep.CanConnect, StepState.Running, "");
                        }
                    });
                    CANBus access$getCanBus$p = HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE);
                    this.L$0 = coroutineScope5;
                    this.I$0 = 0;
                    this.label = 1;
                    bootUp = access$getCanBus$p.bootUp(this);
                    if (bootUp == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    coroutineScope = coroutineScope5;
                    i = 0;
                    result = (CANBus.Result) bootUp;
                    if (result.isSuccess()) {
                        HardwareInterfaceStub hardwareInterfaceStub4 = HardwareInterfaceStub.INSTANCE;
                        str3 = HardwareInterfaceStub.TAG;
                        Pdlog.m3274e(str3, "canbus boot up fail");
                        HardwareInterfaceStub hardwareInterfaceStub5 = HardwareInterfaceStub.INSTANCE;
                        threadSafeListener4 = HardwareInterfaceStub.hardwareListener;
                        threadSafeListener4.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCAN$1.2
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str7) {
                                invoke2(iHardware, str7);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(IHardware l, String str7) {
                                Intrinsics.checkParameterIsNotNull(l, "l");
                                Intrinsics.checkParameterIsNotNull(str7, "<anonymous parameter 1>");
                                l.onOpenStep(HardwareOpenStep.CanConnect, StepState.Fail, CANBus.Result.this.getDescription());
                            }
                        });
                        if (i == 0) {
                            HardwareInterfaceStub hardwareInterfaceStub6 = HardwareInterfaceStub.INSTANCE;
                            str6 = HardwareInterfaceStub.TAG;
                            Pdlog.m3274e(str6, "CAN boot up fail");
                            HardwareInterfaceStub hardwareInterfaceStub7 = HardwareInterfaceStub.INSTANCE;
                            threadSafeListener8 = HardwareInterfaceStub.hardwareListener;
                            threadSafeListener8.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCAN$1.12
                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str7) {
                                    invoke2(iHardware, str7);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(IHardware l, String str7) {
                                    Intrinsics.checkParameterIsNotNull(l, "l");
                                    Intrinsics.checkParameterIsNotNull(str7, "<anonymous parameter 1>");
                                    l.onOpenStep(HardwareOpenStep.Finish, StepState.Fail, "CAN boot up fail");
                                }
                            });
                        }
                        HardwareInterfaceStub hardwareInterfaceStub8 = HardwareInterfaceStub.INSTANCE;
                        HardwareInterfaceStub.openJob = (Job) null;
                        return Unit.INSTANCE;
                    }
                    HardwareInterfaceStub hardwareInterfaceStub9 = HardwareInterfaceStub.INSTANCE;
                    str2 = HardwareInterfaceStub.TAG;
                    Pdlog.m3275i(str2, "canbus boot up success");
                    HardwareInterfaceStub hardwareInterfaceStub10 = HardwareInterfaceStub.INSTANCE;
                    threadSafeListener2 = HardwareInterfaceStub.hardwareListener;
                    threadSafeListener2.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCAN$1.3
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str7) {
                            invoke2(iHardware, str7);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IHardware l, String str7) {
                            Intrinsics.checkParameterIsNotNull(l, "l");
                            Intrinsics.checkParameterIsNotNull(str7, "<anonymous parameter 1>");
                            l.onOpenStep(HardwareOpenStep.CanConnect, StepState.Success, "");
                        }
                    });
                    HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE).regProtocol();
                    HardwareInterfaceStub hardwareInterfaceStub11 = HardwareInterfaceStub.INSTANCE;
                    threadSafeListener3 = HardwareInterfaceStub.hardwareListener;
                    threadSafeListener3.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCAN$1.4
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str7) {
                            invoke2(iHardware, str7);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IHardware l, String str7) {
                            Intrinsics.checkParameterIsNotNull(l, "l");
                            Intrinsics.checkParameterIsNotNull(str7, "<anonymous parameter 1>");
                            l.onOpenStep(HardwareOpenStep.FetchMachineInfo, StepState.Running, "");
                        }
                    });
                    HardwareInterfaceStub hardwareInterfaceStub12 = HardwareInterfaceStub.INSTANCE;
                    machineInfoProcess = HardwareInterfaceStub.machineInfoProcess;
                    C50435 c50435 = new C50435(HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE).getCanParserManager());
                    C50446 c50446 = new C50446(HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE).getCanParserManager());
                    C50457 c50457 = new C50457(HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE));
                    this.L$0 = coroutineScope;
                    this.I$0 = i;
                    this.L$1 = result;
                    this.label = 2;
                    fetchFromHardware = machineInfoProcess.fetchFromHardware(c50435, c50446, c50457, this);
                    if (fetchFromHardware == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    i2 = i;
                    result2 = result;
                    i3 = HardwareInterfaceStub.WhenMappings.$EnumSwitchMapping$0[((MachineInfoProcess.FetchResult) fetchFromHardware).ordinal()];
                    if (i3 != 1) {
                        HardwareInterfaceStub hardwareInterfaceStub13 = HardwareInterfaceStub.INSTANCE;
                        threadSafeListener5 = HardwareInterfaceStub.hardwareListener;
                        threadSafeListener5.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCAN$1.8
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str7) {
                                invoke2(iHardware, str7);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(IHardware l, String str7) {
                                Intrinsics.checkParameterIsNotNull(l, "l");
                                Intrinsics.checkParameterIsNotNull(str7, "<anonymous parameter 1>");
                                l.onOpenStep(HardwareOpenStep.FetchMachineInfo, StepState.Fail, "MachineInfo fetch fail");
                            }
                        });
                        HardwareInterfaceStub hardwareInterfaceStub14 = HardwareInterfaceStub.INSTANCE;
                        str4 = HardwareInterfaceStub.TAG;
                        Pdlog.m3274e(str4, "MachineInfo fetch fail");
                    } else {
                        if (i3 != 2) {
                            HardwareInterfaceStub hardwareInterfaceStub15 = HardwareInterfaceStub.INSTANCE;
                            threadSafeListener7 = HardwareInterfaceStub.hardwareListener;
                            threadSafeListener7.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCAN$1.10
                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str7) {
                                    invoke2(iHardware, str7);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(IHardware l, String str7) {
                                    Intrinsics.checkParameterIsNotNull(l, "l");
                                    Intrinsics.checkParameterIsNotNull(str7, "<anonymous parameter 1>");
                                    l.onOpenStep(HardwareOpenStep.FetchMachineInfo, StepState.Success, "");
                                }
                            });
                            CANBus access$getCanBus$p2 = HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE);
                            HardwareInterfaceStub hardwareInterfaceStub16 = HardwareInterfaceStub.INSTANCE;
                            machineInfoProcess2 = HardwareInterfaceStub.machineInfoProcess;
                            access$getCanBus$p2.setMachineType(machineInfoProcess2.getMachineInfo().getProductType());
                            final List mutableListOf = CollectionsKt.mutableListOf(HardwareInterfaceStub.SelfCheckSensor.IMU, HardwareInterfaceStub.SelfCheckSensor.Encoder);
                            HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo3 = new HardwareInterfaceStub.SelfCheckSensorInfo() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCAN$1$selfCheckInfo$1
                                @Override // com.pudutech.mirsdk.hardware.HardwareInterfaceStub.SelfCheckSensorInfo
                                public void onState(HardwareInterfaceStub.SelfCheckSensor sensor, boolean result5) {
                                    AtomicBoolean atomicBoolean3;
                                    AtomicBoolean atomicBoolean4;
                                    Intrinsics.checkParameterIsNotNull(sensor, "sensor");
                                    synchronized (mutableListOf) {
                                        if (mutableListOf.contains(sensor)) {
                                            if (!result5) {
                                                HardwareInterfaceStub hardwareInterfaceStub17 = HardwareInterfaceStub.INSTANCE;
                                                atomicBoolean4 = HardwareInterfaceStub.waitSelfCheck;
                                                atomicBoolean4.set(false);
                                            } else {
                                                mutableListOf.remove(sensor);
                                            }
                                            if (mutableListOf.isEmpty()) {
                                                HardwareInterfaceStub hardwareInterfaceStub18 = HardwareInterfaceStub.INSTANCE;
                                                atomicBoolean3 = HardwareInterfaceStub.waitSelfCheck;
                                                atomicBoolean3.set(false);
                                            }
                                        }
                                        Unit unit = Unit.INSTANCE;
                                    }
                                }
                            };
                            this.L$0 = coroutineScope;
                            this.I$0 = i2;
                            this.L$1 = result2;
                            this.L$2 = mutableListOf;
                            this.L$3 = selfCheckSensorInfo3;
                            this.label = 3;
                            checkIMU = HardwareInterfaceStub.INSTANCE.checkIMU(selfCheckSensorInfo3, this);
                            if (checkIMU == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            CoroutineScope coroutineScope6 = coroutineScope;
                            result3 = result2;
                            coroutineScope2 = coroutineScope6;
                            int i6 = i2;
                            list = mutableListOf;
                            selfCheckSensorInfo = selfCheckSensorInfo3;
                            i4 = i6;
                            Job job5 = (Job) checkIMU;
                            this.L$0 = coroutineScope2;
                            this.I$0 = i4;
                            this.L$1 = result3;
                            this.L$2 = list;
                            this.L$3 = selfCheckSensorInfo;
                            this.L$4 = job5;
                            this.label = 4;
                            checkEncoder = HardwareInterfaceStub.INSTANCE.checkEncoder(selfCheckSensorInfo, this);
                            if (checkEncoder != coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            coroutineScope3 = coroutineScope2;
                            job = job5;
                            hardwareInterfaceStub$openCAN$1 = this;
                            HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo4 = selfCheckSensorInfo;
                            job2 = job;
                            job3 = (Job) checkEncoder;
                            coroutineScope4 = coroutineScope3;
                            i5 = i4;
                            result4 = result3;
                            list2 = list;
                            selfCheckSensorInfo2 = selfCheckSensorInfo4;
                            do {
                                HardwareInterfaceStub hardwareInterfaceStub17 = HardwareInterfaceStub.INSTANCE;
                                atomicBoolean2 = HardwareInterfaceStub.waitSelfCheck;
                                if (!atomicBoolean2.get()) {
                                    if (job2 != null) {
                                        hardwareInterfaceStub$openCAN$1.L$0 = coroutineScope4;
                                        hardwareInterfaceStub$openCAN$1.I$0 = i5;
                                        hardwareInterfaceStub$openCAN$1.L$1 = result4;
                                        hardwareInterfaceStub$openCAN$1.L$2 = list2;
                                        hardwareInterfaceStub$openCAN$1.L$3 = selfCheckSensorInfo2;
                                        hardwareInterfaceStub$openCAN$1.L$4 = job2;
                                        hardwareInterfaceStub$openCAN$1.L$5 = job3;
                                        hardwareInterfaceStub$openCAN$1.label = 6;
                                        if (JobKt.cancelAndJoin(job2, hardwareInterfaceStub$openCAN$1) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    }
                                    job4 = job3;
                                    list3 = list2;
                                    HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo5 = selfCheckSensorInfo2;
                                    Job job6 = job2;
                                    if (job4 != null) {
                                        hardwareInterfaceStub$openCAN$1.L$0 = coroutineScope4;
                                        hardwareInterfaceStub$openCAN$1.I$0 = i5;
                                        hardwareInterfaceStub$openCAN$1.L$1 = result4;
                                        hardwareInterfaceStub$openCAN$1.L$2 = list3;
                                        hardwareInterfaceStub$openCAN$1.L$3 = selfCheckSensorInfo5;
                                        hardwareInterfaceStub$openCAN$1.L$4 = job6;
                                        hardwareInterfaceStub$openCAN$1.L$5 = job4;
                                        hardwareInterfaceStub$openCAN$1.label = 7;
                                        if (JobKt.cancelAndJoin(job4, hardwareInterfaceStub$openCAN$1) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    }
                                    i = i5;
                                    if (list3.isEmpty()) {
                                        HardwareInterfaceStub hardwareInterfaceStub18 = HardwareInterfaceStub.INSTANCE;
                                        threadSafeListener9 = HardwareInterfaceStub.hardwareListener;
                                        threadSafeListener9.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCAN$1.11
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str7) {
                                                invoke2(iHardware, str7);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(IHardware l, String str7) {
                                                Intrinsics.checkParameterIsNotNull(l, "l");
                                                Intrinsics.checkParameterIsNotNull(str7, "<anonymous parameter 1>");
                                                l.onOpenStep(HardwareOpenStep.Finish, StepState.Success, "");
                                            }
                                        });
                                        i = 1;
                                    }
                                    if (i == 0) {
                                    }
                                    HardwareInterfaceStub hardwareInterfaceStub82 = HardwareInterfaceStub.INSTANCE;
                                    HardwareInterfaceStub.openJob = (Job) null;
                                    return Unit.INSTANCE;
                                }
                                hardwareInterfaceStub$openCAN$1.L$0 = coroutineScope4;
                                hardwareInterfaceStub$openCAN$1.I$0 = i5;
                                hardwareInterfaceStub$openCAN$1.L$1 = result4;
                                hardwareInterfaceStub$openCAN$1.L$2 = list2;
                                hardwareInterfaceStub$openCAN$1.L$3 = selfCheckSensorInfo2;
                                hardwareInterfaceStub$openCAN$1.L$4 = job2;
                                hardwareInterfaceStub$openCAN$1.L$5 = job3;
                                hardwareInterfaceStub$openCAN$1.label = 5;
                            } while (DelayKt.delay(20L, hardwareInterfaceStub$openCAN$1) != coroutine_suspended);
                            return coroutine_suspended;
                        }
                        HardwareInterfaceStub hardwareInterfaceStub19 = HardwareInterfaceStub.INSTANCE;
                        threadSafeListener6 = HardwareInterfaceStub.hardwareListener;
                        threadSafeListener6.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCAN$1.9
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str7) {
                                invoke2(iHardware, str7);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(IHardware l, String str7) {
                                Intrinsics.checkParameterIsNotNull(l, "l");
                                Intrinsics.checkParameterIsNotNull(str7, "<anonymous parameter 1>");
                                l.onOpenStep(HardwareOpenStep.FetchMachineInfo, StepState.Fail, "MachineInfo fetch not setup");
                            }
                        });
                        HardwareInterfaceStub hardwareInterfaceStub20 = HardwareInterfaceStub.INSTANCE;
                        str5 = HardwareInterfaceStub.TAG;
                        Pdlog.m3274e(str5, "MachineInfo fetch not setup");
                    }
                    i = i2;
                    if (i == 0) {
                    }
                    HardwareInterfaceStub hardwareInterfaceStub822 = HardwareInterfaceStub.INSTANCE;
                    HardwareInterfaceStub.openJob = (Job) null;
                    return Unit.INSTANCE;
                case 1:
                    i = this.I$0;
                    CoroutineScope coroutineScope7 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    coroutineScope = coroutineScope7;
                    bootUp = obj;
                    result = (CANBus.Result) bootUp;
                    if (result.isSuccess()) {
                    }
                    break;
                case 2:
                    result2 = (CANBus.Result) this.L$1;
                    i2 = this.I$0;
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    fetchFromHardware = obj;
                    i3 = HardwareInterfaceStub.WhenMappings.$EnumSwitchMapping$0[((MachineInfoProcess.FetchResult) fetchFromHardware).ordinal()];
                    if (i3 != 1) {
                    }
                    i = i2;
                    if (i == 0) {
                    }
                    HardwareInterfaceStub hardwareInterfaceStub8222 = HardwareInterfaceStub.INSTANCE;
                    HardwareInterfaceStub.openJob = (Job) null;
                    return Unit.INSTANCE;
                case 3:
                    HardwareInterfaceStub$openCAN$1$selfCheckInfo$1 hardwareInterfaceStub$openCAN$1$selfCheckInfo$1 = (HardwareInterfaceStub$openCAN$1$selfCheckInfo$1) this.L$3;
                    List list4 = (List) this.L$2;
                    CANBus.Result result5 = (CANBus.Result) this.L$1;
                    int i7 = this.I$0;
                    CoroutineScope coroutineScope8 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    checkIMU = obj;
                    selfCheckSensorInfo = hardwareInterfaceStub$openCAN$1$selfCheckInfo$1;
                    coroutineScope2 = coroutineScope8;
                    i4 = i7;
                    result3 = result5;
                    list = list4;
                    Job job52 = (Job) checkIMU;
                    this.L$0 = coroutineScope2;
                    this.I$0 = i4;
                    this.L$1 = result3;
                    this.L$2 = list;
                    this.L$3 = selfCheckSensorInfo;
                    this.L$4 = job52;
                    this.label = 4;
                    checkEncoder = HardwareInterfaceStub.INSTANCE.checkEncoder(selfCheckSensorInfo, this);
                    if (checkEncoder != coroutine_suspended) {
                    }
                    break;
                case 4:
                    job = (Job) this.L$4;
                    selfCheckSensorInfo = (HardwareInterfaceStub$openCAN$1$selfCheckInfo$1) this.L$3;
                    list = (List) this.L$2;
                    result3 = (CANBus.Result) this.L$1;
                    i4 = this.I$0;
                    coroutineScope3 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    checkEncoder = obj;
                    hardwareInterfaceStub$openCAN$1 = this;
                    HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo42 = selfCheckSensorInfo;
                    job2 = job;
                    job3 = (Job) checkEncoder;
                    coroutineScope4 = coroutineScope3;
                    i5 = i4;
                    result4 = result3;
                    list2 = list;
                    selfCheckSensorInfo2 = selfCheckSensorInfo42;
                    do {
                        HardwareInterfaceStub hardwareInterfaceStub172 = HardwareInterfaceStub.INSTANCE;
                        atomicBoolean2 = HardwareInterfaceStub.waitSelfCheck;
                        if (!atomicBoolean2.get()) {
                        }
                    } while (DelayKt.delay(20L, hardwareInterfaceStub$openCAN$1) != coroutine_suspended);
                    return coroutine_suspended;
                case 5:
                    job3 = (Job) this.L$5;
                    job2 = (Job) this.L$4;
                    selfCheckSensorInfo2 = (HardwareInterfaceStub$openCAN$1$selfCheckInfo$1) this.L$3;
                    list2 = (List) this.L$2;
                    result4 = (CANBus.Result) this.L$1;
                    i5 = this.I$0;
                    coroutineScope4 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    hardwareInterfaceStub$openCAN$1 = this;
                    do {
                        HardwareInterfaceStub hardwareInterfaceStub1722 = HardwareInterfaceStub.INSTANCE;
                        atomicBoolean2 = HardwareInterfaceStub.waitSelfCheck;
                        if (!atomicBoolean2.get()) {
                        }
                    } while (DelayKt.delay(20L, hardwareInterfaceStub$openCAN$1) != coroutine_suspended);
                    return coroutine_suspended;
                case 6:
                    job3 = (Job) this.L$5;
                    job2 = (Job) this.L$4;
                    selfCheckSensorInfo2 = (HardwareInterfaceStub$openCAN$1$selfCheckInfo$1) this.L$3;
                    list2 = (List) this.L$2;
                    result4 = (CANBus.Result) this.L$1;
                    i5 = this.I$0;
                    coroutineScope4 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    hardwareInterfaceStub$openCAN$1 = this;
                    job4 = job3;
                    list3 = list2;
                    HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo52 = selfCheckSensorInfo2;
                    Job job62 = job2;
                    if (job4 != null) {
                    }
                    i = i5;
                    if (list3.isEmpty()) {
                    }
                    if (i == 0) {
                    }
                    HardwareInterfaceStub hardwareInterfaceStub82222 = HardwareInterfaceStub.INSTANCE;
                    HardwareInterfaceStub.openJob = (Job) null;
                    return Unit.INSTANCE;
                case 7:
                    list3 = (List) this.L$2;
                    i = this.I$0;
                    ResultKt.throwOnFailure(obj);
                    if (list3.isEmpty()) {
                    }
                    if (i == 0) {
                    }
                    HardwareInterfaceStub hardwareInterfaceStub822222 = HardwareInterfaceStub.INSTANCE;
                    HardwareInterfaceStub.openJob = (Job) null;
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th) {
            HardwareInterfaceStub hardwareInterfaceStub21 = HardwareInterfaceStub.INSTANCE;
            HardwareInterfaceStub.openJob = (Job) null;
            throw th;
        }
    }
}
