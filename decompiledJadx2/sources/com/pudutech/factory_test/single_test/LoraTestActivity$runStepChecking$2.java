package com.pudutech.factory_test.single_test;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
import com.pudutech.factory_test.ServiceConnectionKt;
import com.pudutech.factory_test.single_test.LoraTestActivity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.random.Random;
import kotlin.random.RandomKt;
import kotlin.ranges.IntRange;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LoraTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.LoraTestActivity$runStepChecking$2", m3970f = "LoraTestActivity.kt", m3971i = {0}, m3972l = {137}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes.dex */
public final class LoraTestActivity$runStepChecking$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.IntRef $cnt;
    final /* synthetic */ Ref.ByteRef $randomNum;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5188p$;
    final /* synthetic */ LoraTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoraTestActivity$runStepChecking$2(LoraTestActivity loraTestActivity, Ref.ByteRef byteRef, Ref.IntRef intRef, Continuation continuation) {
        super(2, continuation);
        this.this$0 = loraTestActivity;
        this.$randomNum = byteRef;
        this.$cnt = intRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        LoraTestActivity$runStepChecking$2 loraTestActivity$runStepChecking$2 = new LoraTestActivity$runStepChecking$2(this.this$0, this.$randomNum, this.$cnt, completion);
        loraTestActivity$runStepChecking$2.f5188p$ = (CoroutineScope) obj;
        return loraTestActivity$runStepChecking$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LoraTestActivity$runStepChecking$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5188p$;
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "start sending");
            CoroutineDispatcher io2 = Dispatchers.getIO();
            C45161 c45161 = new C45161(null);
            this.L$0 = coroutineScope;
            this.label = 1;
            if (BuildersKt.withContext(io2, c45161, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        str2 = this.this$0.TAG;
        Pdlog.m3275i(str2, "check receiveBack.cnt=" + this.$cnt.element);
        if (this.$cnt.element < 1) {
            this.this$0.setStep(LoraTestActivity.Step.FAIL);
            this.this$0.getMTestItem().setFailDescription("没有收到回复。可能是默认信道不一致，或者模块本身存在问题");
        } else {
            this.this$0.setStep(LoraTestActivity.Step.SUCCESS);
        }
        this.this$0.FSM();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LoraTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.LoraTestActivity$runStepChecking$2$1", m3970f = "LoraTestActivity.kt", m3971i = {0, 0}, m3972l = {141}, m3973m = "invokeSuspend", m3974n = {"$this$withContext", "it"}, m3975s = {"L$0", "I$2"})
    /* renamed from: com.pudutech.factory_test.single_test.LoraTestActivity$runStepChecking$2$1 */
    /* loaded from: classes.dex */
    public static final class C45161 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5189p$;

        C45161(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C45161 c45161 = new C45161(completion);
            c45161.f5189p$ = (CoroutineScope) obj;
            return c45161;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C45161) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0074  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x002c  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x006f -> B:5:0x0072). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            int i;
            CoroutineScope coroutineScope;
            int i2;
            C45161 c45161;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                i = 5;
                coroutineScope = this.f5189p$;
                i2 = 0;
                c45161 = this;
                if (i2 < i) {
                }
            } else {
                if (i3 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i = this.I$1;
                i2 = this.I$0;
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                c45161 = this;
                i2++;
                if (i2 < i) {
                    int intValue = Boxing.boxInt(i2).intValue();
                    LoraTestActivity$runStepChecking$2.this.$randomNum.element = (byte) RandomKt.nextInt(Random.INSTANCE, new IntRange(0, 255));
                    RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
                    if (rbInterface != null) {
                        rbInterface.broadcastToRemoteDevice(new byte[]{LoraTestActivity$runStepChecking$2.this.$randomNum.element});
                    }
                    c45161.L$0 = coroutineScope;
                    c45161.I$0 = i2;
                    c45161.I$1 = i;
                    c45161.I$2 = intValue;
                    c45161.label = 1;
                    if (DelayKt.delay(2000L, c45161) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    i2++;
                    if (i2 < i) {
                        LoraTestActivity$runStepChecking$2.this.this$0.setOnReceive((Function1) null);
                        return Unit.INSTANCE;
                    }
                }
            }
        }
    }
}
