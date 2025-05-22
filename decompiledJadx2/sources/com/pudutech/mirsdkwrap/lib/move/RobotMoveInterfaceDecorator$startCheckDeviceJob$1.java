package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.base.Pdlog;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: RobotMoveInterfaceDecorator.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator$startCheckDeviceJob$1", m3970f = "RobotMoveInterfaceDecorator.kt", m3971i = {0}, m3972l = {136}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
public final class RobotMoveInterfaceDecorator$startCheckDeviceJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6614p$;
    final /* synthetic */ RobotMoveInterfaceDecorator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RobotMoveInterfaceDecorator$startCheckDeviceJob$1(RobotMoveInterfaceDecorator robotMoveInterfaceDecorator, Continuation continuation) {
        super(2, continuation);
        this.this$0 = robotMoveInterfaceDecorator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotMoveInterfaceDecorator$startCheckDeviceJob$1 robotMoveInterfaceDecorator$startCheckDeviceJob$1 = new RobotMoveInterfaceDecorator$startCheckDeviceJob$1(this.this$0, completion);
        robotMoveInterfaceDecorator$startCheckDeviceJob$1.f6614p$ = (CoroutineScope) obj;
        return robotMoveInterfaceDecorator$startCheckDeviceJob$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotMoveInterfaceDecorator$startCheckDeviceJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        Job job;
        Job job2;
        String str2;
        Job job3;
        Job job4;
        String str3;
        Job job5;
        Job launch$default;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6614p$;
            str = this.this$0.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("startCheckDeviceJob: start ");
            job = this.this$0.checkDeviceJob;
            sb.append(job);
            Pdlog.m3273d(str, sb.toString());
            job2 = this.this$0.checkDeviceJob;
            if (job2 != null) {
                Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
            }
            str2 = this.this$0.TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("startCheckDeviceJob: cancel checkDeviceJob: ");
            job3 = this.this$0.checkDeviceJob;
            sb2.append(job3);
            Pdlog.m3273d(str2, sb2.toString());
            job4 = this.this$0.checkDeviceJob;
            if (job4 != null) {
                this.L$0 = coroutineScope;
                this.label = 1;
                if (job4.join(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        str3 = this.this$0.TAG;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("startCheckDeviceJob: join checkDeviceJob: ");
        job5 = this.this$0.checkDeviceJob;
        sb3.append(job5);
        Pdlog.m3273d(str3, sb3.toString());
        RobotMoveInterfaceDecorator robotMoveInterfaceDecorator = this.this$0;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, BaseMove.INSTANCE.getMoveTask$module_robot_mirsdk_wrapper_release(), null, new C53521(null), 2, null);
        robotMoveInterfaceDecorator.checkDeviceJob = launch$default;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: RobotMoveInterfaceDecorator.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator$startCheckDeviceJob$1$1", m3970f = "RobotMoveInterfaceDecorator.kt", m3971i = {0, 0, 0}, m3972l = {159}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "repCount", "it"}, m3975s = {"L$0", "I$0", "I$3"})
    /* renamed from: com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator$startCheckDeviceJob$1$1 */
    /* loaded from: classes6.dex */
    public static final class C53521 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6615p$;

        C53521(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C53521 c53521 = new C53521(completion);
            c53521.f6615p$ = (CoroutineScope) obj;
            return c53521;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C53521) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x010b  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0073  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0105 -> B:5:0x0108). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            String str;
            Job job;
            String str2;
            int i;
            CoroutineScope coroutineScope;
            int i2;
            int i3;
            C53521 c53521;
            String str3;
            boolean checkDeviceReady;
            String str4;
            Function0 function0;
            String str5;
            Function0 function02;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i4 = this.label;
            if (i4 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope2 = this.f6615p$;
                str = RobotMoveInterfaceDecorator$startCheckDeviceJob$1.this.this$0.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("startCheckDeviceJob: create checkDeviceJob:");
                job = RobotMoveInterfaceDecorator$startCheckDeviceJob$1.this.this$0.checkDeviceJob;
                sb.append(job);
                Pdlog.m3273d(str, sb.toString());
                RobotMoveInterfaceDecorator$startCheckDeviceJob$1.this.this$0.openDeviceIfNeed();
                str2 = RobotMoveInterfaceDecorator$startCheckDeviceJob$1.this.this$0.TAG;
                Pdlog.m3273d(str2, "startCheckDeviceJob: openDeviceIfNeed");
                i = 40;
                coroutineScope = coroutineScope2;
                i2 = 40;
                i3 = 0;
                c53521 = this;
                if (i3 < i) {
                }
            } else {
                if (i4 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i5 = this.I$3;
                i = this.I$2;
                i3 = this.I$1;
                i2 = this.I$0;
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                c53521 = this;
                i3++;
                if (i3 < i) {
                    int intValue = Boxing.boxInt(i3).intValue();
                    str3 = RobotMoveInterfaceDecorator$startCheckDeviceJob$1.this.this$0.TAG;
                    Pdlog.m3273d(str3, "startCheckDeviceJob: repeat:" + intValue);
                    checkDeviceReady = RobotMoveInterfaceDecorator$startCheckDeviceJob$1.this.this$0.checkDeviceReady();
                    if (checkDeviceReady) {
                        str5 = RobotMoveInterfaceDecorator$startCheckDeviceJob$1.this.this$0.TAG;
                        Pdlog.m3273d(str5, "startCheckDeviceJob device open success");
                        function02 = RobotMoveInterfaceDecorator$startCheckDeviceJob$1.this.this$0.delayMoveTask;
                        if (function02 != null) {
                        }
                        return Unit.INSTANCE;
                    }
                    if (intValue == i2 - 1) {
                        str4 = RobotMoveInterfaceDecorator$startCheckDeviceJob$1.this.this$0.TAG;
                        Pdlog.m3273d(str4, "startCheckDeviceJob device open timeout");
                        function0 = RobotMoveInterfaceDecorator$startCheckDeviceJob$1.this.this$0.delayMoveTask;
                        if (function0 != null) {
                        }
                        return Unit.INSTANCE;
                    }
                    c53521.L$0 = coroutineScope;
                    c53521.I$0 = i2;
                    c53521.I$1 = i3;
                    c53521.I$2 = i;
                    c53521.I$3 = intValue;
                    c53521.label = 1;
                    if (DelayKt.delay(300L, c53521) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    i3++;
                    if (i3 < i) {
                        return Unit.INSTANCE;
                    }
                }
            }
        }
    }
}
