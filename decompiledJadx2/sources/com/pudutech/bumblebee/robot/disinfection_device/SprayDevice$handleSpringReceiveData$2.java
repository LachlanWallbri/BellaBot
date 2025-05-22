package com.pudutech.bumblebee.robot.disinfection_device;

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
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SprayDevice.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.disinfection_device.SprayDevice$handleSpringReceiveData$2", m3970f = "SprayDevice.kt", m3971i = {0}, m3972l = {83}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes2.dex */
public final class SprayDevice$handleSpringReceiveData$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ byte[] $bytes;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4766p$;
    final /* synthetic */ SprayDevice this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SprayDevice$handleSpringReceiveData$2(SprayDevice sprayDevice, byte[] bArr, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sprayDevice;
        this.$bytes = bArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SprayDevice$handleSpringReceiveData$2 sprayDevice$handleSpringReceiveData$2 = new SprayDevice$handleSpringReceiveData$2(this.this$0, this.$bytes, completion);
        sprayDevice$handleSpringReceiveData$2.f4766p$ = (CoroutineScope) obj;
        return sprayDevice$handleSpringReceiveData$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SprayDevice$handleSpringReceiveData$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4766p$;
            MainCoroutineDispatcher main = Dispatchers.getMain();
            C41801 c41801 = new C41801(null);
            this.L$0 = coroutineScope;
            this.label = 1;
            if (BuildersKt.withContext(main, c41801, this) == coroutine_suspended) {
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
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.disinfection_device.SprayDevice$handleSpringReceiveData$2$1", m3970f = "SprayDevice.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.robot.disinfection_device.SprayDevice$handleSpringReceiveData$2$1 */
    /* loaded from: classes2.dex */
    public static final class C41801 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4767p$;

        C41801(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C41801 c41801 = new C41801(completion);
            c41801.f4767p$ = (CoroutineScope) obj;
            return c41801;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C41801) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4767p$;
            if (UByteArray.m4577getimpl(SprayDevice$handleSpringReceiveData$2.this.$bytes, 3) == UByte.m4528constructorimpl((byte) 1)) {
                str = "摩乔25mm上通磁浮子";
            } else if (UByteArray.m4577getimpl(SprayDevice$handleSpringReceiveData$2.this.$bytes, 3) == UByte.m4528constructorimpl((byte) 2)) {
                str = "摩乔25mm下通磁浮子";
            } else {
                str = UByteArray.m4577getimpl(SprayDevice$handleSpringReceiveData$2.this.$bytes, 3) == UByte.m4528constructorimpl((byte) 255) ? "类型未配置" : "未知类型";
            }
            Function1 onSprayMagneticTypeListener$Robot_release = SprayDevice$handleSpringReceiveData$2.this.this$0.getOnSprayMagneticTypeListener$Robot_release();
            if (onSprayMagneticTypeListener$Robot_release != null) {
                return (Unit) onSprayMagneticTypeListener$Robot_release.invoke(str);
            }
            return null;
        }
    }
}
