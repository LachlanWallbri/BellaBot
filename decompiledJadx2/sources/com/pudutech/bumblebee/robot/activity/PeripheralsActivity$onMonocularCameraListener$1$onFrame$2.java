package com.pudutech.bumblebee.robot.activity;

import android.graphics.Bitmap;
import android.widget.TextView;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.bumblebee.robot.C4144R;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: PeripheralsActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.activity.PeripheralsActivity$onMonocularCameraListener$1$onFrame$2", m3970f = "PeripheralsActivity.kt", m3971i = {0, 1, 1}, m3972l = {224, 228}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", SpeechUtility.TAG_RESOURCE_RESULT}, m3975s = {"L$0", "L$0", "L$1"})
/* loaded from: classes.dex */
public final class PeripheralsActivity$onMonocularCameraListener$1$onFrame$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Bitmap $bitmap;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4746p$;
    final /* synthetic */ PeripheralsActivity$onMonocularCameraListener$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PeripheralsActivity$onMonocularCameraListener$1$onFrame$2(PeripheralsActivity$onMonocularCameraListener$1 peripheralsActivity$onMonocularCameraListener$1, Bitmap bitmap, Continuation continuation) {
        super(2, continuation);
        this.this$0 = peripheralsActivity$onMonocularCameraListener$1;
        this.$bitmap = bitmap;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PeripheralsActivity$onMonocularCameraListener$1$onFrame$2 peripheralsActivity$onMonocularCameraListener$1$onFrame$2 = new PeripheralsActivity$onMonocularCameraListener$1$onFrame$2(this.this$0, this.$bitmap, completion);
        peripheralsActivity$onMonocularCameraListener$1$onFrame$2.f4746p$ = (CoroutineScope) obj;
        return peripheralsActivity$onMonocularCameraListener$1$onFrame$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PeripheralsActivity$onMonocularCameraListener$1$onFrame$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        boolean z = true;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f4746p$;
            CoroutineDispatcher io2 = Dispatchers.getIO();
            C4166xc96226a5 c4166xc96226a5 = new C4166xc96226a5(this, null);
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = BuildersKt.withContext(io2, c4166xc96226a5, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        String str = (String) obj;
        String str2 = str;
        if (str2 != null && str2.length() != 0) {
            z = false;
        }
        if (z) {
            return Unit.INSTANCE;
        }
        MainCoroutineDispatcher main = Dispatchers.getMain();
        C41651 c41651 = new C41651(str, null);
        this.L$0 = coroutineScope;
        this.L$1 = str;
        this.label = 2;
        if (BuildersKt.withContext(main, c41651, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* compiled from: PeripheralsActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.activity.PeripheralsActivity$onMonocularCameraListener$1$onFrame$2$1", m3970f = "PeripheralsActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$onMonocularCameraListener$1$onFrame$2$1 */
    /* loaded from: classes.dex */
    public static final class C41651 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $result;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4747p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C41651(String str, Continuation continuation) {
            super(2, continuation);
            this.$result = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C41651 c41651 = new C41651(this.$result, completion);
            c41651.f4747p$ = (CoroutineScope) obj;
            return c41651;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C41651) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            TextView tv_qrcode_result = (TextView) PeripheralsActivity$onMonocularCameraListener$1$onFrame$2.this.this$0.this$0._$_findCachedViewById(C4144R.id.tv_qrcode_result);
            Intrinsics.checkExpressionValueIsNotNull(tv_qrcode_result, "tv_qrcode_result");
            tv_qrcode_result.setText(this.$result);
            return Unit.INSTANCE;
        }
    }
}
