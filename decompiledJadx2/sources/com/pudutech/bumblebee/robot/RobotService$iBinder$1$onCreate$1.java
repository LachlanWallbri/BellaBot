package com.pudutech.bumblebee.robot;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.ICANData;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: RobotService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.RobotService$iBinder$1$onCreate$1", m3970f = "RobotService.kt", m3971i = {0}, m3972l = {103}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes.dex */
public final class RobotService$iBinder$1$onCreate$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ ExecutorCoroutineDispatcher $worker;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4731p$;
    final /* synthetic */ RobotService$iBinder$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RobotService$iBinder$1$onCreate$1(RobotService$iBinder$1 robotService$iBinder$1, Context context, ExecutorCoroutineDispatcher executorCoroutineDispatcher, Continuation continuation) {
        super(2, continuation);
        this.this$0 = robotService$iBinder$1;
        this.$context = context;
        this.$worker = executorCoroutineDispatcher;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotService$iBinder$1$onCreate$1 robotService$iBinder$1$onCreate$1 = new RobotService$iBinder$1$onCreate$1(this.this$0, this.$context, this.$worker, completion);
        robotService$iBinder$1$onCreate$1.f4731p$ = (CoroutineScope) obj;
        return robotService$iBinder$1$onCreate$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotService$iBinder$1$onCreate$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        CANParserManager cANParserManager;
        String str2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4731p$;
            AIDLConnection<HardwareInterface> hardware = this.this$0.getHardware();
            Context context = this.$context;
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = AIDLConnection.connect$default(hardware, context, null, this, 2, null);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        if (!((Boolean) obj).booleanValue()) {
            str2 = this.this$0.this$0.TAG;
            Pdlog.m3274e(str2, "connect hardware service fail");
        } else {
            str = this.this$0.this$0.TAG;
            Pdlog.m3275i(str, "connect hardware service success interface=" + this.this$0.getHardware().getInterface());
            HardwareInterface hardwareInterface = this.this$0.getHardware().getInterface();
            if (hardwareInterface != null) {
                cANParserManager = this.this$0.canParser;
                byte[] filterIDs = cANParserManager.getFilterIDs();
                byte[] copyOf = Arrays.copyOf(filterIDs, filterIDs.length);
                Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                hardwareInterface.addCANDataListener("robot", copyOf, new ICANData.Stub() { // from class: com.pudutech.bumblebee.robot.RobotService$iBinder$1$onCreate$1.1
                    @Override // com.pudutech.mirsdk.hardware.ICANData
                    public void onData(int id, byte[] p0) {
                        String str3;
                        CANParserManager cANParserManager2;
                        if (p0 != null) {
                            str3 = RobotService$iBinder$1$onCreate$1.this.this$0.this$0.TAG;
                            Pdlog.m3276v(str3, "ICANData.onData size:" + p0.length);
                            cANParserManager2 = RobotService$iBinder$1$onCreate$1.this.this$0.canParser;
                            byte[] copyOf2 = Arrays.copyOf(p0, p0.length);
                            Intrinsics.checkExpressionValueIsNotNull(copyOf2, "java.util.Arrays.copyOf(this, size)");
                            cANParserManager2.m4297parseeUNIVaU(id, UByteArray.m4572constructorimpl(copyOf2));
                            return;
                        }
                        Pdlog.m3274e("TAG", "ICANData.onData null data");
                    }
                });
            }
        }
        this.$worker.close();
        return Unit.INSTANCE;
    }
}
