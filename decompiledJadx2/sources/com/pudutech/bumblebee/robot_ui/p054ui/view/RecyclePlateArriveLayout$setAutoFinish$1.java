package com.pudutech.bumblebee.robot_ui.p054ui.view;

import android.widget.TextView;
import com.pudutech.bumblebee.robot_ui.C4188R;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RecyclePlateArriveLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.view.RecyclePlateArriveLayout$setAutoFinish$1", m3970f = "RecyclePlateArriveLayout.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
public final class RecyclePlateArriveLayout$setAutoFinish$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0 $callback;
    final /* synthetic */ TextView $tvCountdown;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4958p$;
    final /* synthetic */ RecyclePlateArriveLayout this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecyclePlateArriveLayout$setAutoFinish$1(RecyclePlateArriveLayout recyclePlateArriveLayout, TextView textView, Function0 function0, Continuation continuation) {
        super(2, continuation);
        this.this$0 = recyclePlateArriveLayout;
        this.$tvCountdown = textView;
        this.$callback = function0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RecyclePlateArriveLayout$setAutoFinish$1 recyclePlateArriveLayout$setAutoFinish$1 = new RecyclePlateArriveLayout$setAutoFinish$1(this.this$0, this.$tvCountdown, this.$callback, completion);
        recyclePlateArriveLayout$setAutoFinish$1.f4958p$ = (CoroutineScope) obj;
        return recyclePlateArriveLayout$setAutoFinish$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RecyclePlateArriveLayout$setAutoFinish$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Job job;
        int i;
        Job countDownCoroutines;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f4958p$;
        job = this.this$0.countdownJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        RecyclePlateArriveLayout recyclePlateArriveLayout = this.this$0;
        i = recyclePlateArriveLayout.countdownMillis;
        countDownCoroutines = recyclePlateArriveLayout.countDownCoroutines(i / 1000, coroutineScope, new Function1<Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RecyclePlateArriveLayout$setAutoFinish$1.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i2) {
                RecyclePlateArriveLayout$setAutoFinish$1.this.this$0.countdownMillis = i2 * 1000;
                TextView textView = RecyclePlateArriveLayout$setAutoFinish$1.this.$tvCountdown;
                if (textView != null) {
                    textView.setText('(' + i2 + RecyclePlateArriveLayout$setAutoFinish$1.this.this$0.getContext().getString(C4188R.string.unit_second) + ')');
                }
            }
        }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RecyclePlateArriveLayout$setAutoFinish$1.2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                RecyclePlateArriveLayout$setAutoFinish$1.this.$callback.invoke();
                RecyclePlateArriveLayout$setAutoFinish$1.this.this$0.release();
            }
        });
        recyclePlateArriveLayout.countdownJob = countDownCoroutines;
        return Unit.INSTANCE;
    }
}
