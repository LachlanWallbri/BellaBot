package com.pudutech.bumblebee.robot.disinfection_device;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SprayDevice.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.disinfection_device.SprayDevice$handleSpringReceiveData$3", m3970f = "SprayDevice.kt", m3971i = {0}, m3972l = {104}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes2.dex */
public final class SprayDevice$handleSpringReceiveData$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ byte[] $bytes;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4768p$;
    final /* synthetic */ SprayDevice this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SprayDevice$handleSpringReceiveData$3(SprayDevice sprayDevice, byte[] bArr, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sprayDevice;
        this.$bytes = bArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SprayDevice$handleSpringReceiveData$3 sprayDevice$handleSpringReceiveData$3 = new SprayDevice$handleSpringReceiveData$3(this.this$0, this.$bytes, completion);
        sprayDevice$handleSpringReceiveData$3.f4768p$ = (CoroutineScope) obj;
        return sprayDevice$handleSpringReceiveData$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SprayDevice$handleSpringReceiveData$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4768p$;
            MainCoroutineDispatcher main = Dispatchers.getMain();
            C41811 c41811 = new C41811(null);
            this.L$0 = coroutineScope;
            this.label = 1;
            if (BuildersKt.withContext(main, c41811, this) == coroutine_suspended) {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SprayDevice.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.disinfection_device.SprayDevice$handleSpringReceiveData$3$1", m3970f = "SprayDevice.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.robot.disinfection_device.SprayDevice$handleSpringReceiveData$3$1 */
    /* loaded from: classes2.dex */
    public static final class C41811 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4769p$;

        C41811(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C41811 c41811 = new C41811(completion);
            c41811.f4769p$ = (CoroutineScope) obj;
            return c41811;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C41811) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4769p$;
            Function1 onSprayMagneticConfigStatusListener$Robot_release = SprayDevice$handleSpringReceiveData$3.this.this$0.getOnSprayMagneticConfigStatusListener$Robot_release();
            if (onSprayMagneticConfigStatusListener$Robot_release != null) {
                return (Unit) onSprayMagneticConfigStatusListener$Robot_release.invoke(Boxing.boxBoolean(UByteArray.m4577getimpl(SprayDevice$handleSpringReceiveData$3.this.$bytes, 3) == UByte.m4528constructorimpl((byte) 1)));
            }
            return null;
        }
    }
}
