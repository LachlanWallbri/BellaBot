package com.pudutech.mirsdk.hardware;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.HardwareInterfaceStub;
import com.pudutech.mirsdk.hardware.MachineInfoProcess;
import com.pudutech.mirsdk.hardware.can.CANBus;
import com.pudutech.mirsdk.hardware.can.CANParserManager;
import com.pudutech.mirsdk.hardware.serialize.DeviceType;
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
  classes5.dex
 */
/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCANEx$1", m3970f = "HardwareInterfaceStub.kt", m3971i = {0, 0, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10}, m3972l = {1085, 1099, 1136, 1137, 1141, 1146, 1151, 1153, 1154, 1155, 1156}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "success", "$this$launch", "success", "canBootUpResult", "$this$launch", "success", "canBootUpResult", "sensorList", "selfCheckInfo", "$this$launch", "success", "canBootUpResult", "sensorList", "selfCheckInfo", "imuJob", "$this$launch", "success", "canBootUpResult", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "rgbdJob", "$this$launch", "success", "canBootUpResult", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "rgbdJob", "lidarJob", "$this$launch", "success", "canBootUpResult", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "rgbdJob", "lidarJob", "$this$launch", "success", "canBootUpResult", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "rgbdJob", "lidarJob", "$this$launch", "success", "canBootUpResult", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "rgbdJob", "lidarJob", "$this$launch", "success", "canBootUpResult", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "rgbdJob", "lidarJob", "$this$launch", "success", "canBootUpResult", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "rgbdJob", "lidarJob"}, m3975s = {"L$0", "I$0", "L$0", "I$0", "L$1", "L$0", "I$0", "L$1", "L$2", "L$3", "L$0", "I$0", "L$1", "L$2", "L$3", "L$4", "L$0", "I$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "I$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "I$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "I$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "I$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "I$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "I$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7"})
/* loaded from: classes.dex */
final class HardwareInterfaceStub$openCANEx$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List $deviceTypes;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5921p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardwareInterfaceStub$openCANEx$1(List list, Continuation continuation) {
        super(2, continuation);
        this.$deviceTypes = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareInterfaceStub$openCANEx$1 hardwareInterfaceStub$openCANEx$1 = new HardwareInterfaceStub$openCANEx$1(this.$deviceTypes, completion);
        hardwareInterfaceStub$openCANEx$1.f5921p$ = (CoroutineScope) obj;
        return hardwareInterfaceStub$openCANEx$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareInterfaceStub$openCANEx$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u00062E\u0010\u0007\u001aA\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00010\b¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\r¢\u0006\u0004\b\u000e\u0010\u000f"}, m3961d2 = {"<anonymous>", "", "p1", "Lkotlin/UByte;", "Lkotlin/ParameterName;", "name", "protocol", "p2", "Lkotlin/Function2;", "", "id", "Lkotlin/UByteArray;", "byteArray", "parser", "invoke", "(BLkotlin/jvm/functions/Function2;)V"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCANEx$1$5 */
    /* loaded from: classes.dex */
    public static final /* synthetic */ class C50565 extends FunctionReference implements Function2<UByte, Function2<? super Integer, ? super UByteArray, ? extends Unit>, Unit> {
        C50565(CANParserManager cANParserManager) {
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
      classes5.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0004\b\u0007\u0010\b"}, m3961d2 = {"<anonymous>", "", "p1", "Lkotlin/UByte;", "Lkotlin/ParameterName;", "name", "protocol", "invoke", "(B)V"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCANEx$1$6 */
    /* loaded from: classes.dex */
    public static final /* synthetic */ class C50576 extends FunctionReference implements Function1<UByte, Unit> {
        C50576(CANParserManager cANParserManager) {
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
      classes5.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0004\b\u0007\u0010\b"}, m3961d2 = {"<anonymous>", "", "p1", "Lkotlin/UByteArray;", "Lkotlin/ParameterName;", "name", "bytes", "invoke", "([B)V"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCANEx$1$7 */
    /* loaded from: classes.dex */
    public static final /* synthetic */ class C50587 extends FunctionReference implements Function1<UByteArray, Unit> {
        C50587(CANBus cANBus) {
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
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCANEx$1$13 */
    /* loaded from: classes4.dex */
    static final class C505213 extends Lambda implements Function2<IHardware, String, Unit> {

        /* renamed from: $e */
        final /* synthetic */ Exception f5922$e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C505213(Exception exc) {
            super(2);
            this.f5922$e = exc;
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
            this.f5922$e.printStackTrace();
            sb.append(Unit.INSTANCE);
            l.onOpenStep(hardwareOpenStep, stepState, sb.toString());
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000c. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0494 A[Catch: all -> 0x04dd, TryCatch #0 {all -> 0x04dd, blocks: (B:8:0x0039, B:10:0x0461, B:12:0x0467, B:14:0x04b4, B:19:0x0060, B:23:0x0440, B:29:0x0088, B:32:0x041d, B:36:0x00b0, B:39:0x03fe, B:43:0x00d8, B:46:0x03b0, B:48:0x03bc, B:52:0x03df, B:56:0x0100, B:58:0x03a1, B:61:0x0125, B:63:0x0353, B:64:0x036f, B:66:0x0376, B:68:0x0380, B:72:0x0142, B:74:0x031c, B:76:0x0325, B:78:0x032f, B:84:0x0164, B:86:0x02f3, B:91:0x0175, B:94:0x026a, B:97:0x0278, B:99:0x02ae, B:101:0x02b8, B:102:0x02bd, B:104:0x02c1, B:106:0x02cb, B:107:0x02d0, B:111:0x0476, B:113:0x0494, B:115:0x0188, B:117:0x01cf, B:119:0x01d7, B:120:0x01f8, B:124:0x0195), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01d7 A[Catch: all -> 0x04dd, TryCatch #0 {all -> 0x04dd, blocks: (B:8:0x0039, B:10:0x0461, B:12:0x0467, B:14:0x04b4, B:19:0x0060, B:23:0x0440, B:29:0x0088, B:32:0x041d, B:36:0x00b0, B:39:0x03fe, B:43:0x00d8, B:46:0x03b0, B:48:0x03bc, B:52:0x03df, B:56:0x0100, B:58:0x03a1, B:61:0x0125, B:63:0x0353, B:64:0x036f, B:66:0x0376, B:68:0x0380, B:72:0x0142, B:74:0x031c, B:76:0x0325, B:78:0x032f, B:84:0x0164, B:86:0x02f3, B:91:0x0175, B:94:0x026a, B:97:0x0278, B:99:0x02ae, B:101:0x02b8, B:102:0x02bd, B:104:0x02c1, B:106:0x02cb, B:107:0x02d0, B:111:0x0476, B:113:0x0494, B:115:0x0188, B:117:0x01cf, B:119:0x01d7, B:120:0x01f8, B:124:0x0195), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01f8 A[Catch: all -> 0x04dd, TryCatch #0 {all -> 0x04dd, blocks: (B:8:0x0039, B:10:0x0461, B:12:0x0467, B:14:0x04b4, B:19:0x0060, B:23:0x0440, B:29:0x0088, B:32:0x041d, B:36:0x00b0, B:39:0x03fe, B:43:0x00d8, B:46:0x03b0, B:48:0x03bc, B:52:0x03df, B:56:0x0100, B:58:0x03a1, B:61:0x0125, B:63:0x0353, B:64:0x036f, B:66:0x0376, B:68:0x0380, B:72:0x0142, B:74:0x031c, B:76:0x0325, B:78:0x032f, B:84:0x0164, B:86:0x02f3, B:91:0x0175, B:94:0x026a, B:97:0x0278, B:99:0x02ae, B:101:0x02b8, B:102:0x02bd, B:104:0x02c1, B:106:0x02cb, B:107:0x02d0, B:111:0x0476, B:113:0x0494, B:115:0x0188, B:117:0x01cf, B:119:0x01d7, B:120:0x01f8, B:124:0x0195), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0467 A[Catch: all -> 0x04dd, TryCatch #0 {all -> 0x04dd, blocks: (B:8:0x0039, B:10:0x0461, B:12:0x0467, B:14:0x04b4, B:19:0x0060, B:23:0x0440, B:29:0x0088, B:32:0x041d, B:36:0x00b0, B:39:0x03fe, B:43:0x00d8, B:46:0x03b0, B:48:0x03bc, B:52:0x03df, B:56:0x0100, B:58:0x03a1, B:61:0x0125, B:63:0x0353, B:64:0x036f, B:66:0x0376, B:68:0x0380, B:72:0x0142, B:74:0x031c, B:76:0x0325, B:78:0x032f, B:84:0x0164, B:86:0x02f3, B:91:0x0175, B:94:0x026a, B:97:0x0278, B:99:0x02ae, B:101:0x02b8, B:102:0x02bd, B:104:0x02c1, B:106:0x02cb, B:107:0x02d0, B:111:0x0476, B:113:0x0494, B:115:0x0188, B:117:0x01cf, B:119:0x01d7, B:120:0x01f8, B:124:0x0195), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x04b4 A[Catch: all -> 0x04dd, TRY_LEAVE, TryCatch #0 {all -> 0x04dd, blocks: (B:8:0x0039, B:10:0x0461, B:12:0x0467, B:14:0x04b4, B:19:0x0060, B:23:0x0440, B:29:0x0088, B:32:0x041d, B:36:0x00b0, B:39:0x03fe, B:43:0x00d8, B:46:0x03b0, B:48:0x03bc, B:52:0x03df, B:56:0x0100, B:58:0x03a1, B:61:0x0125, B:63:0x0353, B:64:0x036f, B:66:0x0376, B:68:0x0380, B:72:0x0142, B:74:0x031c, B:76:0x0325, B:78:0x032f, B:84:0x0164, B:86:0x02f3, B:91:0x0175, B:94:0x026a, B:97:0x0278, B:99:0x02ae, B:101:0x02b8, B:102:0x02bd, B:104:0x02c1, B:106:0x02cb, B:107:0x02d0, B:111:0x0476, B:113:0x0494, B:115:0x0188, B:117:0x01cf, B:119:0x01d7, B:120:0x01f8, B:124:0x0195), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0440 A[Catch: all -> 0x04dd, TryCatch #0 {all -> 0x04dd, blocks: (B:8:0x0039, B:10:0x0461, B:12:0x0467, B:14:0x04b4, B:19:0x0060, B:23:0x0440, B:29:0x0088, B:32:0x041d, B:36:0x00b0, B:39:0x03fe, B:43:0x00d8, B:46:0x03b0, B:48:0x03bc, B:52:0x03df, B:56:0x0100, B:58:0x03a1, B:61:0x0125, B:63:0x0353, B:64:0x036f, B:66:0x0376, B:68:0x0380, B:72:0x0142, B:74:0x031c, B:76:0x0325, B:78:0x032f, B:84:0x0164, B:86:0x02f3, B:91:0x0175, B:94:0x026a, B:97:0x0278, B:99:0x02ae, B:101:0x02b8, B:102:0x02bd, B:104:0x02c1, B:106:0x02cb, B:107:0x02d0, B:111:0x0476, B:113:0x0494, B:115:0x0188, B:117:0x01cf, B:119:0x01d7, B:120:0x01f8, B:124:0x0195), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0460  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x041d A[Catch: all -> 0x04dd, TryCatch #0 {all -> 0x04dd, blocks: (B:8:0x0039, B:10:0x0461, B:12:0x0467, B:14:0x04b4, B:19:0x0060, B:23:0x0440, B:29:0x0088, B:32:0x041d, B:36:0x00b0, B:39:0x03fe, B:43:0x00d8, B:46:0x03b0, B:48:0x03bc, B:52:0x03df, B:56:0x0100, B:58:0x03a1, B:61:0x0125, B:63:0x0353, B:64:0x036f, B:66:0x0376, B:68:0x0380, B:72:0x0142, B:74:0x031c, B:76:0x0325, B:78:0x032f, B:84:0x0164, B:86:0x02f3, B:91:0x0175, B:94:0x026a, B:97:0x0278, B:99:0x02ae, B:101:0x02b8, B:102:0x02bd, B:104:0x02c1, B:106:0x02cb, B:107:0x02d0, B:111:0x0476, B:113:0x0494, B:115:0x0188, B:117:0x01cf, B:119:0x01d7, B:120:0x01f8, B:124:0x0195), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x03fe A[Catch: all -> 0x04dd, TryCatch #0 {all -> 0x04dd, blocks: (B:8:0x0039, B:10:0x0461, B:12:0x0467, B:14:0x04b4, B:19:0x0060, B:23:0x0440, B:29:0x0088, B:32:0x041d, B:36:0x00b0, B:39:0x03fe, B:43:0x00d8, B:46:0x03b0, B:48:0x03bc, B:52:0x03df, B:56:0x0100, B:58:0x03a1, B:61:0x0125, B:63:0x0353, B:64:0x036f, B:66:0x0376, B:68:0x0380, B:72:0x0142, B:74:0x031c, B:76:0x0325, B:78:0x032f, B:84:0x0164, B:86:0x02f3, B:91:0x0175, B:94:0x026a, B:97:0x0278, B:99:0x02ae, B:101:0x02b8, B:102:0x02bd, B:104:0x02c1, B:106:0x02cb, B:107:0x02d0, B:111:0x0476, B:113:0x0494, B:115:0x0188, B:117:0x01cf, B:119:0x01d7, B:120:0x01f8, B:124:0x0195), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x03bc A[Catch: all -> 0x04dd, TryCatch #0 {all -> 0x04dd, blocks: (B:8:0x0039, B:10:0x0461, B:12:0x0467, B:14:0x04b4, B:19:0x0060, B:23:0x0440, B:29:0x0088, B:32:0x041d, B:36:0x00b0, B:39:0x03fe, B:43:0x00d8, B:46:0x03b0, B:48:0x03bc, B:52:0x03df, B:56:0x0100, B:58:0x03a1, B:61:0x0125, B:63:0x0353, B:64:0x036f, B:66:0x0376, B:68:0x0380, B:72:0x0142, B:74:0x031c, B:76:0x0325, B:78:0x032f, B:84:0x0164, B:86:0x02f3, B:91:0x0175, B:94:0x026a, B:97:0x0278, B:99:0x02ae, B:101:0x02b8, B:102:0x02bd, B:104:0x02c1, B:106:0x02cb, B:107:0x02d0, B:111:0x0476, B:113:0x0494, B:115:0x0188, B:117:0x01cf, B:119:0x01d7, B:120:0x01f8, B:124:0x0195), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x03dd  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0376 A[Catch: all -> 0x04dd, TryCatch #0 {all -> 0x04dd, blocks: (B:8:0x0039, B:10:0x0461, B:12:0x0467, B:14:0x04b4, B:19:0x0060, B:23:0x0440, B:29:0x0088, B:32:0x041d, B:36:0x00b0, B:39:0x03fe, B:43:0x00d8, B:46:0x03b0, B:48:0x03bc, B:52:0x03df, B:56:0x0100, B:58:0x03a1, B:61:0x0125, B:63:0x0353, B:64:0x036f, B:66:0x0376, B:68:0x0380, B:72:0x0142, B:74:0x031c, B:76:0x0325, B:78:0x032f, B:84:0x0164, B:86:0x02f3, B:91:0x0175, B:94:0x026a, B:97:0x0278, B:99:0x02ae, B:101:0x02b8, B:102:0x02bd, B:104:0x02c1, B:106:0x02cb, B:107:0x02d0, B:111:0x0476, B:113:0x0494, B:115:0x0188, B:117:0x01cf, B:119:0x01d7, B:120:0x01f8, B:124:0x0195), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x03a0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x030f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0310  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0276  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x03d7 -> B:45:0x03da). Please report as a decompilation issue!!! */
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
        String str3;
        ThreadSafeListener threadSafeListener4;
        CANBus.Result result2;
        int i2;
        int i3;
        ThreadSafeListener threadSafeListener5;
        String str4;
        ThreadSafeListener threadSafeListener6;
        String str5;
        ThreadSafeListener threadSafeListener7;
        MachineInfoProcess machineInfoProcess2;
        final List mutableListOf;
        Object checkIMU;
        CoroutineScope coroutineScope2;
        HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo;
        Object checkEncoder;
        HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo2;
        CoroutineScope coroutineScope3;
        CANBus.Result result3;
        List list;
        Job job;
        int i4;
        Job job2;
        Job job3;
        CoroutineScope coroutineScope4;
        Job job4;
        Job job5;
        Job job6;
        int i5;
        CANBus.Result result4;
        List list2;
        HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo3;
        Object checkRGBD;
        CoroutineScope coroutineScope5;
        Job job7;
        Job job8;
        Object checkLidar;
        HardwareInterfaceStub$openCANEx$1 hardwareInterfaceStub$openCANEx$1;
        Job job9;
        Job job10;
        CoroutineScope coroutineScope6;
        int i6;
        CANBus.Result result5;
        List list3;
        HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo4;
        Job job11;
        Job job12;
        AtomicBoolean atomicBoolean2;
        Job job13;
        List list4;
        int i7;
        String str6;
        ThreadSafeListener threadSafeListener8;
        ThreadSafeListener threadSafeListener9;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope7 = this.f5921p$;
                    HardwareInterfaceStub hardwareInterfaceStub = HardwareInterfaceStub.INSTANCE;
                    str = HardwareInterfaceStub.TAG;
                    Pdlog.m3275i(str, "open CAN async");
                    HardwareInterfaceStub hardwareInterfaceStub2 = HardwareInterfaceStub.INSTANCE;
                    atomicBoolean = HardwareInterfaceStub.waitSelfCheck;
                    atomicBoolean.set(true);
                    HardwareInterfaceStub hardwareInterfaceStub3 = HardwareInterfaceStub.INSTANCE;
                    threadSafeListener = HardwareInterfaceStub.hardwareListener;
                    threadSafeListener.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCANEx$1.1
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
                    this.L$0 = coroutineScope7;
                    this.I$0 = 0;
                    this.label = 1;
                    bootUp = access$getCanBus$p.bootUp(this);
                    if (bootUp == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    coroutineScope = coroutineScope7;
                    i = 0;
                    result = (CANBus.Result) bootUp;
                    if (result.isSuccess()) {
                        HardwareInterfaceStub hardwareInterfaceStub4 = HardwareInterfaceStub.INSTANCE;
                        str3 = HardwareInterfaceStub.TAG;
                        Pdlog.m3274e(str3, "canbus boot up fail");
                        HardwareInterfaceStub hardwareInterfaceStub5 = HardwareInterfaceStub.INSTANCE;
                        threadSafeListener4 = HardwareInterfaceStub.hardwareListener;
                        threadSafeListener4.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCANEx$1.2
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
                            threadSafeListener8.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCANEx$1.12
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
                    threadSafeListener2.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCANEx$1.3
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
                    threadSafeListener3.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCANEx$1.4
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
                    C50565 c50565 = new C50565(HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE).getCanParserManager());
                    C50576 c50576 = new C50576(HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE).getCanParserManager());
                    C50587 c50587 = new C50587(HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE));
                    this.L$0 = coroutineScope;
                    this.I$0 = i;
                    this.L$1 = result;
                    this.label = 2;
                    fetchFromHardware = machineInfoProcess.fetchFromHardware(c50565, c50576, c50587, this);
                    if (fetchFromHardware == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    int i8 = i;
                    result2 = result;
                    CoroutineScope coroutineScope8 = coroutineScope;
                    i2 = i8;
                    i3 = HardwareInterfaceStub.WhenMappings.$EnumSwitchMapping$4[((MachineInfoProcess.FetchResult) fetchFromHardware).ordinal()];
                    if (i3 != 1) {
                        HardwareInterfaceStub hardwareInterfaceStub13 = HardwareInterfaceStub.INSTANCE;
                        threadSafeListener5 = HardwareInterfaceStub.hardwareListener;
                        threadSafeListener5.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCANEx$1.8
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
                    } else if (i3 != 2) {
                        HardwareInterfaceStub hardwareInterfaceStub15 = HardwareInterfaceStub.INSTANCE;
                        threadSafeListener7 = HardwareInterfaceStub.hardwareListener;
                        threadSafeListener7.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCANEx$1.10
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
                        mutableListOf = CollectionsKt.mutableListOf(HardwareInterfaceStub.SelfCheckSensor.IMU, HardwareInterfaceStub.SelfCheckSensor.Encoder);
                        if (this.$deviceTypes != null && this.$deviceTypes.contains(DeviceType.RGBD)) {
                            mutableListOf.add(HardwareInterfaceStub.SelfCheckSensor.RGBD);
                        }
                        if (this.$deviceTypes != null && this.$deviceTypes.contains(DeviceType.Lidar)) {
                            mutableListOf.add(HardwareInterfaceStub.SelfCheckSensor.Lidar);
                        }
                        HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo5 = new HardwareInterfaceStub.SelfCheckSensorInfo() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCANEx$1$selfCheckInfo$1
                            @Override // com.pudutech.mirsdk.hardware.HardwareInterfaceStub.SelfCheckSensorInfo
                            public void onState(HardwareInterfaceStub.SelfCheckSensor sensor, boolean result6) {
                                AtomicBoolean atomicBoolean3;
                                AtomicBoolean atomicBoolean4;
                                Intrinsics.checkParameterIsNotNull(sensor, "sensor");
                                synchronized (mutableListOf) {
                                    if (mutableListOf.contains(sensor)) {
                                        if (!result6) {
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
                        this.L$0 = coroutineScope8;
                        this.I$0 = i2;
                        this.L$1 = result2;
                        this.L$2 = mutableListOf;
                        this.L$3 = selfCheckSensorInfo5;
                        this.label = 3;
                        checkIMU = HardwareInterfaceStub.INSTANCE.checkIMU(selfCheckSensorInfo5, this);
                        if (checkIMU == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        coroutineScope2 = coroutineScope8;
                        selfCheckSensorInfo = selfCheckSensorInfo5;
                        Job job14 = (Job) checkIMU;
                        this.L$0 = coroutineScope2;
                        this.I$0 = i2;
                        this.L$1 = result2;
                        this.L$2 = mutableListOf;
                        this.L$3 = selfCheckSensorInfo;
                        this.L$4 = job14;
                        this.label = 4;
                        checkEncoder = HardwareInterfaceStub.INSTANCE.checkEncoder(selfCheckSensorInfo, this);
                        if (checkEncoder != coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        CANBus.Result result6 = result2;
                        selfCheckSensorInfo2 = selfCheckSensorInfo;
                        coroutineScope3 = coroutineScope2;
                        result3 = result6;
                        int i9 = i2;
                        list = mutableListOf;
                        job = job14;
                        i4 = i9;
                        job2 = (Job) checkEncoder;
                        job3 = (Job) null;
                        if (this.$deviceTypes == null && this.$deviceTypes.contains(DeviceType.RGBD)) {
                            this.L$0 = coroutineScope3;
                            this.I$0 = i4;
                            this.L$1 = result3;
                            this.L$2 = list;
                            this.L$3 = selfCheckSensorInfo2;
                            this.L$4 = job;
                            this.L$5 = job2;
                            this.L$6 = job3;
                            this.label = 5;
                            checkRGBD = HardwareInterfaceStub.INSTANCE.checkRGBD(selfCheckSensorInfo2, this);
                            if (checkRGBD == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            coroutineScope5 = coroutineScope3;
                            job7 = job2;
                            Job job15 = job;
                            job6 = job7;
                            job4 = (Job) checkRGBD;
                            coroutineScope4 = coroutineScope5;
                            i5 = i4;
                            result4 = result3;
                            list2 = list;
                            selfCheckSensorInfo3 = selfCheckSensorInfo2;
                            job5 = job15;
                            job8 = (Job) null;
                            if (this.$deviceTypes != null) {
                                this.L$0 = coroutineScope4;
                                this.I$0 = i5;
                                this.L$1 = result4;
                                this.L$2 = list2;
                                this.L$3 = selfCheckSensorInfo3;
                                this.L$4 = job5;
                                this.L$5 = job6;
                                this.L$6 = job4;
                                this.L$7 = job8;
                                this.label = 6;
                                checkLidar = HardwareInterfaceStub.INSTANCE.checkLidar(selfCheckSensorInfo3, this);
                                if (checkLidar == coroutine_suspended) {
                                }
                                job8 = (Job) checkLidar;
                            }
                            hardwareInterfaceStub$openCANEx$1 = this;
                            Job job16 = job6;
                            job9 = job4;
                            job10 = job8;
                            coroutineScope6 = coroutineScope4;
                            i6 = i5;
                            result5 = result4;
                            list3 = list2;
                            selfCheckSensorInfo4 = selfCheckSensorInfo3;
                            job11 = job5;
                            job12 = job16;
                            HardwareInterfaceStub hardwareInterfaceStub17 = HardwareInterfaceStub.INSTANCE;
                            atomicBoolean2 = HardwareInterfaceStub.waitSelfCheck;
                            if (atomicBoolean2.get()) {
                            }
                        } else {
                            coroutineScope4 = coroutineScope3;
                            job4 = job3;
                            HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo6 = selfCheckSensorInfo2;
                            job5 = job;
                            job6 = job2;
                            i5 = i4;
                            result4 = result3;
                            list2 = list;
                            selfCheckSensorInfo3 = selfCheckSensorInfo6;
                            job8 = (Job) null;
                            if (this.$deviceTypes != null && this.$deviceTypes.contains(DeviceType.Lidar)) {
                                this.L$0 = coroutineScope4;
                                this.I$0 = i5;
                                this.L$1 = result4;
                                this.L$2 = list2;
                                this.L$3 = selfCheckSensorInfo3;
                                this.L$4 = job5;
                                this.L$5 = job6;
                                this.L$6 = job4;
                                this.L$7 = job8;
                                this.label = 6;
                                checkLidar = HardwareInterfaceStub.INSTANCE.checkLidar(selfCheckSensorInfo3, this);
                                if (checkLidar == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                job8 = (Job) checkLidar;
                            }
                            hardwareInterfaceStub$openCANEx$1 = this;
                            Job job162 = job6;
                            job9 = job4;
                            job10 = job8;
                            coroutineScope6 = coroutineScope4;
                            i6 = i5;
                            result5 = result4;
                            list3 = list2;
                            selfCheckSensorInfo4 = selfCheckSensorInfo3;
                            job11 = job5;
                            job12 = job162;
                            HardwareInterfaceStub hardwareInterfaceStub172 = HardwareInterfaceStub.INSTANCE;
                            atomicBoolean2 = HardwareInterfaceStub.waitSelfCheck;
                            if (atomicBoolean2.get()) {
                                hardwareInterfaceStub$openCANEx$1.L$0 = coroutineScope6;
                                hardwareInterfaceStub$openCANEx$1.I$0 = i6;
                                hardwareInterfaceStub$openCANEx$1.L$1 = result5;
                                hardwareInterfaceStub$openCANEx$1.L$2 = list3;
                                hardwareInterfaceStub$openCANEx$1.L$3 = selfCheckSensorInfo4;
                                hardwareInterfaceStub$openCANEx$1.L$4 = job11;
                                hardwareInterfaceStub$openCANEx$1.L$5 = job12;
                                hardwareInterfaceStub$openCANEx$1.L$6 = job9;
                                hardwareInterfaceStub$openCANEx$1.L$7 = job10;
                                hardwareInterfaceStub$openCANEx$1.label = 7;
                                if (DelayKt.delay(20L, hardwareInterfaceStub$openCANEx$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                HardwareInterfaceStub hardwareInterfaceStub1722 = HardwareInterfaceStub.INSTANCE;
                                atomicBoolean2 = HardwareInterfaceStub.waitSelfCheck;
                                if (atomicBoolean2.get()) {
                                    if (job11 != null) {
                                        hardwareInterfaceStub$openCANEx$1.L$0 = coroutineScope6;
                                        hardwareInterfaceStub$openCANEx$1.I$0 = i6;
                                        hardwareInterfaceStub$openCANEx$1.L$1 = result5;
                                        hardwareInterfaceStub$openCANEx$1.L$2 = list3;
                                        hardwareInterfaceStub$openCANEx$1.L$3 = selfCheckSensorInfo4;
                                        hardwareInterfaceStub$openCANEx$1.L$4 = job11;
                                        hardwareInterfaceStub$openCANEx$1.L$5 = job12;
                                        hardwareInterfaceStub$openCANEx$1.L$6 = job9;
                                        hardwareInterfaceStub$openCANEx$1.L$7 = job10;
                                        hardwareInterfaceStub$openCANEx$1.label = 8;
                                        if (JobKt.cancelAndJoin(job11, hardwareInterfaceStub$openCANEx$1) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    }
                                    if (job12 != null) {
                                        hardwareInterfaceStub$openCANEx$1.L$0 = coroutineScope6;
                                        hardwareInterfaceStub$openCANEx$1.I$0 = i6;
                                        hardwareInterfaceStub$openCANEx$1.L$1 = result5;
                                        hardwareInterfaceStub$openCANEx$1.L$2 = list3;
                                        hardwareInterfaceStub$openCANEx$1.L$3 = selfCheckSensorInfo4;
                                        hardwareInterfaceStub$openCANEx$1.L$4 = job11;
                                        hardwareInterfaceStub$openCANEx$1.L$5 = job12;
                                        hardwareInterfaceStub$openCANEx$1.L$6 = job9;
                                        hardwareInterfaceStub$openCANEx$1.L$7 = job10;
                                        hardwareInterfaceStub$openCANEx$1.label = 9;
                                        if (JobKt.cancelAndJoin(job12, hardwareInterfaceStub$openCANEx$1) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    }
                                    if (job9 != null) {
                                        hardwareInterfaceStub$openCANEx$1.L$0 = coroutineScope6;
                                        hardwareInterfaceStub$openCANEx$1.I$0 = i6;
                                        hardwareInterfaceStub$openCANEx$1.L$1 = result5;
                                        hardwareInterfaceStub$openCANEx$1.L$2 = list3;
                                        hardwareInterfaceStub$openCANEx$1.L$3 = selfCheckSensorInfo4;
                                        hardwareInterfaceStub$openCANEx$1.L$4 = job11;
                                        hardwareInterfaceStub$openCANEx$1.L$5 = job12;
                                        hardwareInterfaceStub$openCANEx$1.L$6 = job9;
                                        hardwareInterfaceStub$openCANEx$1.L$7 = job10;
                                        hardwareInterfaceStub$openCANEx$1.label = 10;
                                        if (JobKt.cancelAndJoin(job9, hardwareInterfaceStub$openCANEx$1) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    }
                                    Job job17 = job9;
                                    job13 = job10;
                                    list4 = list3;
                                    if (job13 != null) {
                                        i = i6;
                                        if (list4.isEmpty()) {
                                        }
                                        if (i == 0) {
                                        }
                                        HardwareInterfaceStub hardwareInterfaceStub82 = HardwareInterfaceStub.INSTANCE;
                                        HardwareInterfaceStub.openJob = (Job) null;
                                        return Unit.INSTANCE;
                                    }
                                    hardwareInterfaceStub$openCANEx$1.L$0 = coroutineScope6;
                                    hardwareInterfaceStub$openCANEx$1.I$0 = i6;
                                    hardwareInterfaceStub$openCANEx$1.L$1 = result5;
                                    hardwareInterfaceStub$openCANEx$1.L$2 = list4;
                                    hardwareInterfaceStub$openCANEx$1.L$3 = selfCheckSensorInfo4;
                                    hardwareInterfaceStub$openCANEx$1.L$4 = job11;
                                    hardwareInterfaceStub$openCANEx$1.L$5 = job12;
                                    hardwareInterfaceStub$openCANEx$1.L$6 = job17;
                                    hardwareInterfaceStub$openCANEx$1.L$7 = job13;
                                    hardwareInterfaceStub$openCANEx$1.label = 11;
                                    if (JobKt.cancelAndJoin(job13, hardwareInterfaceStub$openCANEx$1) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    i7 = i6;
                                    i = i7;
                                    if (list4.isEmpty()) {
                                        HardwareInterfaceStub hardwareInterfaceStub18 = HardwareInterfaceStub.INSTANCE;
                                        threadSafeListener9 = HardwareInterfaceStub.hardwareListener;
                                        threadSafeListener9.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCANEx$1.11
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
                                    HardwareInterfaceStub hardwareInterfaceStub822 = HardwareInterfaceStub.INSTANCE;
                                    HardwareInterfaceStub.openJob = (Job) null;
                                    return Unit.INSTANCE;
                                }
                            }
                        }
                    } else {
                        HardwareInterfaceStub hardwareInterfaceStub19 = HardwareInterfaceStub.INSTANCE;
                        threadSafeListener6 = HardwareInterfaceStub.hardwareListener;
                        threadSafeListener6.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$openCANEx$1.9
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
                    HardwareInterfaceStub hardwareInterfaceStub8222 = HardwareInterfaceStub.INSTANCE;
                    HardwareInterfaceStub.openJob = (Job) null;
                    return Unit.INSTANCE;
                case 1:
                    int i10 = this.I$0;
                    CoroutineScope coroutineScope9 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    coroutineScope = coroutineScope9;
                    i = i10;
                    bootUp = obj;
                    result = (CANBus.Result) bootUp;
                    if (result.isSuccess()) {
                    }
                    break;
                case 2:
                    result = (CANBus.Result) this.L$1;
                    i = this.I$0;
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    fetchFromHardware = obj;
                    int i82 = i;
                    result2 = result;
                    CoroutineScope coroutineScope82 = coroutineScope;
                    i2 = i82;
                    i3 = HardwareInterfaceStub.WhenMappings.$EnumSwitchMapping$4[((MachineInfoProcess.FetchResult) fetchFromHardware).ordinal()];
                    if (i3 != 1) {
                    }
                    i = i2;
                    if (i == 0) {
                    }
                    HardwareInterfaceStub hardwareInterfaceStub82222 = HardwareInterfaceStub.INSTANCE;
                    HardwareInterfaceStub.openJob = (Job) null;
                    return Unit.INSTANCE;
                case 3:
                    selfCheckSensorInfo = (HardwareInterfaceStub$openCANEx$1$selfCheckInfo$1) this.L$3;
                    mutableListOf = (List) this.L$2;
                    result2 = (CANBus.Result) this.L$1;
                    i2 = this.I$0;
                    coroutineScope2 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    checkIMU = obj;
                    Job job142 = (Job) checkIMU;
                    this.L$0 = coroutineScope2;
                    this.I$0 = i2;
                    this.L$1 = result2;
                    this.L$2 = mutableListOf;
                    this.L$3 = selfCheckSensorInfo;
                    this.L$4 = job142;
                    this.label = 4;
                    checkEncoder = HardwareInterfaceStub.INSTANCE.checkEncoder(selfCheckSensorInfo, this);
                    if (checkEncoder != coroutine_suspended) {
                    }
                    break;
                case 4:
                    Job job18 = (Job) this.L$4;
                    HardwareInterfaceStub$openCANEx$1$selfCheckInfo$1 hardwareInterfaceStub$openCANEx$1$selfCheckInfo$1 = (HardwareInterfaceStub$openCANEx$1$selfCheckInfo$1) this.L$3;
                    List list5 = (List) this.L$2;
                    CANBus.Result result7 = (CANBus.Result) this.L$1;
                    int i11 = this.I$0;
                    CoroutineScope coroutineScope10 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    checkEncoder = obj;
                    job = job18;
                    coroutineScope3 = coroutineScope10;
                    i4 = i11;
                    result3 = result7;
                    list = list5;
                    selfCheckSensorInfo2 = hardwareInterfaceStub$openCANEx$1$selfCheckInfo$1;
                    job2 = (Job) checkEncoder;
                    job3 = (Job) null;
                    if (this.$deviceTypes == null) {
                        break;
                    }
                    coroutineScope4 = coroutineScope3;
                    job4 = job3;
                    HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo62 = selfCheckSensorInfo2;
                    job5 = job;
                    job6 = job2;
                    i5 = i4;
                    result4 = result3;
                    list2 = list;
                    selfCheckSensorInfo3 = selfCheckSensorInfo62;
                    job8 = (Job) null;
                    if (this.$deviceTypes != null) {
                    }
                    hardwareInterfaceStub$openCANEx$1 = this;
                    Job job1622 = job6;
                    job9 = job4;
                    job10 = job8;
                    coroutineScope6 = coroutineScope4;
                    i6 = i5;
                    result5 = result4;
                    list3 = list2;
                    selfCheckSensorInfo4 = selfCheckSensorInfo3;
                    job11 = job5;
                    job12 = job1622;
                    HardwareInterfaceStub hardwareInterfaceStub17222 = HardwareInterfaceStub.INSTANCE;
                    atomicBoolean2 = HardwareInterfaceStub.waitSelfCheck;
                    if (atomicBoolean2.get()) {
                    }
                    break;
                case 5:
                    job7 = (Job) this.L$5;
                    job = (Job) this.L$4;
                    selfCheckSensorInfo2 = (HardwareInterfaceStub$openCANEx$1$selfCheckInfo$1) this.L$3;
                    list = (List) this.L$2;
                    result3 = (CANBus.Result) this.L$1;
                    i4 = this.I$0;
                    coroutineScope5 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    checkRGBD = obj;
                    Job job152 = job;
                    job6 = job7;
                    job4 = (Job) checkRGBD;
                    coroutineScope4 = coroutineScope5;
                    i5 = i4;
                    result4 = result3;
                    list2 = list;
                    selfCheckSensorInfo3 = selfCheckSensorInfo2;
                    job5 = job152;
                    job8 = (Job) null;
                    if (this.$deviceTypes != null) {
                    }
                    hardwareInterfaceStub$openCANEx$1 = this;
                    Job job16222 = job6;
                    job9 = job4;
                    job10 = job8;
                    coroutineScope6 = coroutineScope4;
                    i6 = i5;
                    result5 = result4;
                    list3 = list2;
                    selfCheckSensorInfo4 = selfCheckSensorInfo3;
                    job11 = job5;
                    job12 = job16222;
                    HardwareInterfaceStub hardwareInterfaceStub172222 = HardwareInterfaceStub.INSTANCE;
                    atomicBoolean2 = HardwareInterfaceStub.waitSelfCheck;
                    if (atomicBoolean2.get()) {
                    }
                    break;
                case 6:
                    job4 = (Job) this.L$6;
                    job6 = (Job) this.L$5;
                    job5 = (Job) this.L$4;
                    selfCheckSensorInfo3 = (HardwareInterfaceStub$openCANEx$1$selfCheckInfo$1) this.L$3;
                    list2 = (List) this.L$2;
                    result4 = (CANBus.Result) this.L$1;
                    i5 = this.I$0;
                    coroutineScope4 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    checkLidar = obj;
                    job8 = (Job) checkLidar;
                    hardwareInterfaceStub$openCANEx$1 = this;
                    Job job162222 = job6;
                    job9 = job4;
                    job10 = job8;
                    coroutineScope6 = coroutineScope4;
                    i6 = i5;
                    result5 = result4;
                    list3 = list2;
                    selfCheckSensorInfo4 = selfCheckSensorInfo3;
                    job11 = job5;
                    job12 = job162222;
                    HardwareInterfaceStub hardwareInterfaceStub1722222 = HardwareInterfaceStub.INSTANCE;
                    atomicBoolean2 = HardwareInterfaceStub.waitSelfCheck;
                    if (atomicBoolean2.get()) {
                    }
                    break;
                case 7:
                    job10 = (Job) this.L$7;
                    job9 = (Job) this.L$6;
                    job12 = (Job) this.L$5;
                    job11 = (Job) this.L$4;
                    selfCheckSensorInfo4 = (HardwareInterfaceStub$openCANEx$1$selfCheckInfo$1) this.L$3;
                    list3 = (List) this.L$2;
                    result5 = (CANBus.Result) this.L$1;
                    i6 = this.I$0;
                    coroutineScope6 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    hardwareInterfaceStub$openCANEx$1 = this;
                    HardwareInterfaceStub hardwareInterfaceStub17222222 = HardwareInterfaceStub.INSTANCE;
                    atomicBoolean2 = HardwareInterfaceStub.waitSelfCheck;
                    if (atomicBoolean2.get()) {
                    }
                    break;
                case 8:
                    job10 = (Job) this.L$7;
                    job9 = (Job) this.L$6;
                    job12 = (Job) this.L$5;
                    job11 = (Job) this.L$4;
                    selfCheckSensorInfo4 = (HardwareInterfaceStub$openCANEx$1$selfCheckInfo$1) this.L$3;
                    list3 = (List) this.L$2;
                    result5 = (CANBus.Result) this.L$1;
                    i6 = this.I$0;
                    coroutineScope6 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    hardwareInterfaceStub$openCANEx$1 = this;
                    if (job12 != null) {
                    }
                    if (job9 != null) {
                    }
                    Job job172 = job9;
                    job13 = job10;
                    list4 = list3;
                    if (job13 != null) {
                    }
                    break;
                case 9:
                    job10 = (Job) this.L$7;
                    job9 = (Job) this.L$6;
                    job12 = (Job) this.L$5;
                    job11 = (Job) this.L$4;
                    selfCheckSensorInfo4 = (HardwareInterfaceStub$openCANEx$1$selfCheckInfo$1) this.L$3;
                    list3 = (List) this.L$2;
                    result5 = (CANBus.Result) this.L$1;
                    i6 = this.I$0;
                    coroutineScope6 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    hardwareInterfaceStub$openCANEx$1 = this;
                    if (job9 != null) {
                    }
                    Job job1722 = job9;
                    job13 = job10;
                    list4 = list3;
                    if (job13 != null) {
                    }
                    break;
                case 10:
                    job10 = (Job) this.L$7;
                    job9 = (Job) this.L$6;
                    job12 = (Job) this.L$5;
                    job11 = (Job) this.L$4;
                    selfCheckSensorInfo4 = (HardwareInterfaceStub$openCANEx$1$selfCheckInfo$1) this.L$3;
                    list3 = (List) this.L$2;
                    result5 = (CANBus.Result) this.L$1;
                    i6 = this.I$0;
                    coroutineScope6 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    hardwareInterfaceStub$openCANEx$1 = this;
                    Job job17222 = job9;
                    job13 = job10;
                    list4 = list3;
                    if (job13 != null) {
                    }
                    break;
                case 11:
                    list4 = (List) this.L$2;
                    i7 = this.I$0;
                    ResultKt.throwOnFailure(obj);
                    i = i7;
                    if (list4.isEmpty()) {
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
