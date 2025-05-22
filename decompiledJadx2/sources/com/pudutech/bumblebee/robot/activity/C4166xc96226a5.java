package com.pudutech.bumblebee.robot.activity;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: PeripheralsActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.activity.PeripheralsActivity$onMonocularCameraListener$1$onFrame$2$result$1", m3970f = "PeripheralsActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* renamed from: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$onMonocularCameraListener$1$onFrame$2$result$1 */
/* loaded from: classes.dex */
public final class C4166xc96226a5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4748p$;
    final /* synthetic */ PeripheralsActivity$onMonocularCameraListener$1$onFrame$2 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C4166xc96226a5(PeripheralsActivity$onMonocularCameraListener$1$onFrame$2 peripheralsActivity$onMonocularCameraListener$1$onFrame$2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = peripheralsActivity$onMonocularCameraListener$1$onFrame$2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C4166xc96226a5 c4166xc96226a5 = new C4166xc96226a5(this.this$0, completion);
        c4166xc96226a5.f4748p$ = (CoroutineScope) obj;
        return c4166xc96226a5;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((C4166xc96226a5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        PeripheralsActivity peripheralsActivity = this.this$0.this$0.this$0;
        Bitmap bitmap = this.this$0.$bitmap;
        Intrinsics.checkExpressionValueIsNotNull(bitmap, "bitmap");
        return peripheralsActivity.syncDecodeQRCode(bitmap);
    }
}
