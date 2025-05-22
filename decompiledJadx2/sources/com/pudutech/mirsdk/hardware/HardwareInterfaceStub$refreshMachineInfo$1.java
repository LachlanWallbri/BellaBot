package com.pudutech.mirsdk.hardware;

import com.pudutech.mirsdk.hardware.can.CANBus;
import com.pudutech.mirsdk.hardware.can.CANParserManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlinx.coroutines.CoroutineScope;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub$refreshMachineInfo$1", m3970f = "HardwareInterfaceStub.kt", m3971i = {0}, m3972l = {807}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes.dex */
final class HardwareInterfaceStub$refreshMachineInfo$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5926p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HardwareInterfaceStub$refreshMachineInfo$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareInterfaceStub$refreshMachineInfo$1 hardwareInterfaceStub$refreshMachineInfo$1 = new HardwareInterfaceStub$refreshMachineInfo$1(completion);
        hardwareInterfaceStub$refreshMachineInfo$1.f5926p$ = (CoroutineScope) obj;
        return hardwareInterfaceStub$refreshMachineInfo$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareInterfaceStub$refreshMachineInfo$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u00062E\u0010\u0007\u001aA\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00010\b¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\r¢\u0006\u0004\b\u000e\u0010\u000f"}, m3961d2 = {"<anonymous>", "", "p1", "Lkotlin/UByte;", "Lkotlin/ParameterName;", "name", "protocol", "p2", "Lkotlin/Function2;", "", "id", "Lkotlin/UByteArray;", "byteArray", "parser", "invoke", "(BLkotlin/jvm/functions/Function2;)V"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$refreshMachineInfo$1$1 */
    /* loaded from: classes.dex */
    public static final /* synthetic */ class C50631 extends FunctionReference implements Function2<UByte, Function2<? super Integer, ? super UByteArray, ? extends Unit>, Unit> {
        C50631(CANParserManager cANParserManager) {
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
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$refreshMachineInfo$1$2 */
    /* loaded from: classes.dex */
    public static final /* synthetic */ class C50642 extends FunctionReference implements Function1<UByte, Unit> {
        C50642(CANParserManager cANParserManager) {
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
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$refreshMachineInfo$1$3 */
    /* loaded from: classes.dex */
    public static final /* synthetic */ class C50653 extends FunctionReference implements Function1<UByteArray, Unit> {
        C50653(CANBus cANBus) {
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

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MachineInfoProcess machineInfoProcess;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5926p$;
            HardwareInterfaceStub hardwareInterfaceStub = HardwareInterfaceStub.INSTANCE;
            machineInfoProcess = HardwareInterfaceStub.machineInfoProcess;
            C50631 c50631 = new C50631(HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE).getCanParserManager());
            C50642 c50642 = new C50642(HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE).getCanParserManager());
            C50653 c50653 = new C50653(HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE));
            this.L$0 = coroutineScope;
            this.label = 1;
            if (machineInfoProcess.fetchFromHardware(c50631, c50642, c50653, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
