package com.pudutech.bumblebee.robot.lora;

import com.pudutech.lora.library.LoRaClient;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: LoraToVIP.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.lora.LoraToVIP$noticeVIP$1", m3970f = "LoraToVIP.kt", m3971i = {0}, m3972l = {32}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes.dex */
public final class LoraToVIP$noticeVIP$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $msg;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4788p$;
    final /* synthetic */ LoraToVIP this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoraToVIP$noticeVIP$1(LoraToVIP loraToVIP, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = loraToVIP;
        this.$msg = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        LoraToVIP$noticeVIP$1 loraToVIP$noticeVIP$1 = new LoraToVIP$noticeVIP$1(this.this$0, this.$msg, completion);
        loraToVIP$noticeVIP$1.f4788p$ = (CoroutineScope) obj;
        return loraToVIP$noticeVIP$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LoraToVIP$noticeVIP$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0028, code lost:
    
        r1 = r7.this$0.loraJob;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Job job;
        Job job2;
        boolean z;
        Job launch$default;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4788p$;
            job = this.this$0.loraJob;
            if (job != null && job2 != null) {
                this.L$0 = coroutineScope;
                this.label = 1;
                if (JobKt.cancelAndJoin(job2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        z = this.this$0.loraOpened;
        if (!z) {
            return Unit.INSTANCE;
        }
        LoraToVIP loraToVIP = this.this$0;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C41841(null), 3, null);
        loraToVIP.loraJob = launch$default;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* compiled from: LoraToVIP.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.lora.LoraToVIP$noticeVIP$1$1", m3970f = "LoraToVIP.kt", m3971i = {0, 0}, m3972l = {45}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "LoraAddr"}, m3975s = {"L$0", "L$1"})
    /* renamed from: com.pudutech.bumblebee.robot.lora.LoraToVIP$noticeVIP$1$1 */
    /* loaded from: classes.dex */
    public static final class C41841 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4789p$;

        C41841(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C41841 c41841 = new C41841(completion);
            c41841.f4789p$ = (CoroutineScope) obj;
            return c41841;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C41841) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x0097  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0094  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x002d  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0087 -> B:5:0x008a). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            C41841 c41841;
            boolean z;
            boolean z2;
            LoRaClient loRaClient;
            boolean z3;
            boolean z4;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f4789p$;
                c41841 = this;
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                }
            } else if (i == 1) {
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                c41841 = this;
                z4 = LoraToVIP$noticeVIP$1.this.this$0.loraOpened;
                if (!z4) {
                    return Unit.INSTANCE;
                }
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                    z = LoraToVIP$noticeVIP$1.this.this$0.loraOpened;
                    if (!z) {
                        return Unit.INSTANCE;
                    }
                    byte b = (byte) 255;
                    byte[] bArr = {b, b, b, b};
                    z2 = LoraToVIP$noticeVIP$1.this.this$0.loraOpened;
                    if (!z2) {
                        return Unit.INSTANCE;
                    }
                    loRaClient = LoraToVIP$noticeVIP$1.this.this$0.client;
                    loRaClient.sendData(bArr, Boxing.boxByte((byte) 224), LoraToVIP$noticeVIP$1.this.$msg);
                    z3 = LoraToVIP$noticeVIP$1.this.this$0.loraOpened;
                    if (!z3) {
                        return Unit.INSTANCE;
                    }
                    c41841.L$0 = coroutineScope;
                    c41841.L$1 = bArr;
                    c41841.label = 1;
                    if (DelayKt.delay(30000L, c41841) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    z4 = LoraToVIP$noticeVIP$1.this.this$0.loraOpened;
                    if (!z4) {
                    }
                    if (CoroutineScopeKt.isActive(coroutineScope)) {
                        return Unit.INSTANCE;
                    }
                }
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
