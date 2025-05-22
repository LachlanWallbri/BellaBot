package com.pudutech.mirsdk.hardware.can;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.IHardware;
import com.pudutech.mirsdk.hardware.base.CommonKt;
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
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m3961d2 = {"<anonymous>", "", "<anonymous parameter 0>", "", "bytes", "Lkotlin/UByteArray;", "invoke", "(I[B)V"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CANBus$parseBumperSwitch$1 extends Lambda implements Function2<Integer, UByteArray, Unit> {
    final /* synthetic */ CANBus this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CANBus$parseBumperSwitch$1(CANBus cANBus) {
        super(2);
        this.this$0 = cANBus;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
        invoke(num.intValue(), uByteArray.getStorage());
        return Unit.INSTANCE;
    }

    public final void invoke(int i, byte[] bytes) {
        String str;
        String str2;
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "parseBumperSwitch Status:  " + CommonKt.m4421toHexStringGBYM_sE(bytes));
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = UByteArray.m4577getimpl(bytes, 1) & 255;
        BuildersKt__BuildersKt.runBlocking$default(null, new C51521(null), 1, null);
        this.this$0.getHardwareListener().notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$parseBumperSwitch$1.2
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str3) {
                invoke2(iHardware, str3);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IHardware it, String name) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(name, "name");
                it.onBumperSwitchStatus(Ref.IntRef.this.element == 1);
            }
        });
        str2 = this.this$0.TAG;
        Pdlog.m3273d(str2, "parseBumperSwitch bumperSwitchResult = " + intRef.element);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CANBus.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.can.CANBus$parseBumperSwitch$1$1", m3970f = "CANBus.kt", m3971i = {0}, m3972l = {374}, m3973m = "invokeSuspend", m3974n = {"$this$runBlocking"}, m3975s = {"L$0"})
    /* renamed from: com.pudutech.mirsdk.hardware.can.CANBus$parseBumperSwitch$1$1 */
    /* loaded from: classes5.dex */
    public static final class C51521 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6037p$;

        C51521(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C51521 c51521 = new C51521(completion);
            c51521.f6037p$ = (CoroutineScope) obj;
            return c51521;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C51521) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            String str;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f6037p$;
                str = CANBus$parseBumperSwitch$1.this.this$0.TAG;
                Pdlog.m3273d(str, "parseBumperSwitch bumperJob is running, cancle");
                Job access$getBumperJob$p = CANBus.access$getBumperJob$p(CANBus$parseBumperSwitch$1.this.this$0);
                if (access$getBumperJob$p == null) {
                    return null;
                }
                this.L$0 = coroutineScope;
                this.label = 1;
                if (JobKt.cancelAndJoin(access$getBumperJob$p, this) == coroutine_suspended) {
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
}
