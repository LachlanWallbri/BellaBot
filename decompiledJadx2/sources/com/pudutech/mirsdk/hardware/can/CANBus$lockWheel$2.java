package com.pudutech.mirsdk.hardware.can;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.HardwareScopeKt;
import com.pudutech.mirsdk.hardware.can.CANBus;
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
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.can.CANBus$lockWheel$2", m3970f = "CANBus.kt", m3971i = {0}, m3972l = {456}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
public final class CANBus$lockWheel$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6032p$;
    final /* synthetic */ CANBus this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CANBus$lockWheel$2(CANBus cANBus, Continuation continuation) {
        super(2, continuation);
        this.this$0 = cANBus;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CANBus$lockWheel$2 cANBus$lockWheel$2 = new CANBus$lockWheel$2(this.this$0, completion);
        cANBus$lockWheel$2.f6032p$ = (CoroutineScope) obj;
        return cANBus$lockWheel$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CANBus$lockWheel$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Job job;
        Job launch$default;
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6032p$;
            job = this.this$0.controlWheelJob;
            if (job != null) {
                this.L$0 = coroutineScope;
                this.label = 1;
                if (JobKt.cancelAndJoin(job, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        CANBus cANBus = this.this$0;
        launch$default = BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new C51431(null), 3, null);
        cANBus.controlWheelJob = launch$default;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "chargepile send control wheel on scope end");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CANBus.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.can.CANBus$lockWheel$2$1", m3970f = "CANBus.kt", m3971i = {0, 0, 0, 0, 0, 1, 1, 1, 1}, m3972l = {476, 483}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "linearSpeed", "repeatTime", "delayTime", "it", "$this$launch", "linearSpeed", "repeatTime", "delayTime"}, m3975s = {"L$0", "L$1", "I$0", "L$2", "I$3", "L$0", "L$1", "I$0", "L$2"})
    /* renamed from: com.pudutech.mirsdk.hardware.can.CANBus$lockWheel$2$1 */
    /* loaded from: classes5.dex */
    public static final class C51431 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6033p$;

        C51431(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C51431 c51431 = new C51431(completion);
            c51431.f6033p$ = (CoroutineScope) obj;
            return c51431;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C51431) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x00cf  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0104  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0120  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x00fd -> B:17:0x0100). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            String str;
            Object obj2;
            CoroutineScope coroutineScope;
            Ref.DoubleRef doubleRef;
            int i;
            C51431 c51431;
            int i2;
            Ref.LongRef longRef;
            int i3;
            String str2;
            Ref.LongRef longRef2;
            int i4;
            Ref.DoubleRef doubleRef2;
            CoroutineScope coroutineScope2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i5 = this.label;
            if (i5 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope3 = this.f6033p$;
                Ref.DoubleRef doubleRef3 = new Ref.DoubleRef();
                doubleRef3.element = -0.05d;
                int i6 = 4;
                Ref.LongRef longRef3 = new Ref.LongRef();
                longRef3.element = 50L;
                int i7 = CANBus.WhenMappings.$EnumSwitchMapping$0[CANBus$lockWheel$2.this.this$0.getMachineType().getModel().ordinal()];
                if (i7 == 1) {
                    doubleRef3.element = 0.03d;
                } else if (i7 == 2) {
                    doubleRef3.element = -0.03d;
                    i6 = 3;
                    longRef3.element = 70L;
                }
                str = CANBus$lockWheel$2.this.this$0.TAG;
                Pdlog.m3273d(str, "move back start linearSpeed=" + doubleRef3.element + " repeatTime: " + i6 + " delayTime: " + longRef3.element);
                obj2 = coroutine_suspended;
                coroutineScope = coroutineScope3;
                doubleRef = doubleRef3;
                i = i6;
                c51431 = this;
                i2 = 0;
                longRef = longRef3;
                i3 = i;
                if (i2 < i) {
                }
            } else {
                if (i5 != 1) {
                    if (i5 == 2) {
                        longRef2 = (Ref.LongRef) this.L$2;
                        i4 = this.I$0;
                        doubleRef2 = (Ref.DoubleRef) this.L$1;
                        coroutineScope2 = (CoroutineScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        obj2 = coroutine_suspended;
                        c51431 = this;
                        while (CoroutineScopeKt.isActive(coroutineScope2)) {
                            CANBus$lockWheel$2.this.this$0.controlWheel(0.0d, 0.0d, true);
                            c51431.L$0 = coroutineScope2;
                            c51431.L$1 = doubleRef2;
                            c51431.I$0 = i4;
                            c51431.L$2 = longRef2;
                            c51431.label = 2;
                            if (DelayKt.delay(100L, c51431) == obj2) {
                                return obj2;
                            }
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i8 = this.I$3;
                i = this.I$2;
                i2 = this.I$1;
                longRef = (Ref.LongRef) this.L$2;
                i3 = this.I$0;
                doubleRef = (Ref.DoubleRef) this.L$1;
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj2 = coroutine_suspended;
                c51431 = this;
                i2++;
                if (i2 < i) {
                    int intValue = Boxing.boxInt(i2).intValue();
                    CANBus$lockWheel$2.this.this$0.controlWheel(doubleRef.element, 0.0d, true);
                    long j = longRef.element;
                    c51431.L$0 = coroutineScope;
                    c51431.L$1 = doubleRef;
                    c51431.I$0 = i3;
                    c51431.L$2 = longRef;
                    c51431.I$1 = i2;
                    c51431.I$2 = i;
                    c51431.I$3 = intValue;
                    c51431.label = 1;
                    if (DelayKt.delay(j, c51431) == obj2) {
                        return obj2;
                    }
                    i2++;
                    if (i2 < i) {
                        str2 = CANBus$lockWheel$2.this.this$0.TAG;
                        Pdlog.m3273d(str2, "chargepile send control wheel on scope");
                        longRef2 = longRef;
                        i4 = i3;
                        doubleRef2 = doubleRef;
                        coroutineScope2 = coroutineScope;
                        while (CoroutineScopeKt.isActive(coroutineScope2)) {
                        }
                        return Unit.INSTANCE;
                    }
                }
            }
        }
    }
}
