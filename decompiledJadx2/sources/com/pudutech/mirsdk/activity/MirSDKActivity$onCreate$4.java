package com.pudutech.mirsdk.activity;

import android.view.View;
import com.pudutech.base.architecture.CrossProcessTask;
import com.pudutech.mirsdk.FunctionScope;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MirSDKActivity$onCreate$4 implements View.OnClickListener {
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MirSDKActivity$onCreate$4(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.this$0.checkAuth("hw", new Function0<Unit>() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$4.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: Classes with same name are omitted:
              classes4.dex
             */
            /* compiled from: MirSDKActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$4$1$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$4$1$1, reason: invalid class name */
            /* loaded from: classes5.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5681p$;

                AnonymousClass1(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
                    anonymousClass1.f5681p$ = (CoroutineScope) obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f5681p$;
                    CrossProcessTask.INSTANCE.jumpActivity(MirSDKActivity$onCreate$4.this.this$0, "com.pudutech.mirsdk.hardware", "com.pudutech.mirsdk.hardware.activity.HardwareActivity");
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(null), 2, null);
            }
        });
    }
}
