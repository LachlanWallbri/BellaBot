package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.view.MotionEvent;
import android.view.View;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SmartInputTableFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@¢\u0006\u0004\b\u0007\u0010\b"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "v", "Landroid/view/View;", "event", "Landroid/view/MotionEvent;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.module.setting.ui.SmartInputTableFragment$initView$3", m3970f = "SmartInputTableFragment.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes3.dex */
public final class SmartInputTableFragment$initView$3 extends SuspendLambda implements Function4<CoroutineScope, View, MotionEvent, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4886p$;
    private View p$0;
    private MotionEvent p$1;
    final /* synthetic */ SmartInputTableFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmartInputTableFragment$initView$3(SmartInputTableFragment smartInputTableFragment, Continuation continuation) {
        super(4, continuation);
        this.this$0 = smartInputTableFragment;
    }

    public final Continuation<Unit> create(CoroutineScope create, View v, MotionEvent event, Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(create, "$this$create");
        Intrinsics.checkParameterIsNotNull(v, "v");
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        SmartInputTableFragment$initView$3 smartInputTableFragment$initView$3 = new SmartInputTableFragment$initView$3(this.this$0, continuation);
        smartInputTableFragment$initView$3.f4886p$ = create;
        smartInputTableFragment$initView$3.p$0 = v;
        smartInputTableFragment$initView$3.p$1 = event;
        return smartInputTableFragment$initView$3;
    }

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(CoroutineScope coroutineScope, View view, MotionEvent motionEvent, Continuation<? super Unit> continuation) {
        return ((SmartInputTableFragment$initView$3) create(coroutineScope, view, motionEvent, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f4886p$;
        View view = this.p$0;
        int action = this.p$1.getAction();
        if (action == 0) {
            this.this$0.deleteInputContext();
            this.this$0.postDeleteTask(700L);
        } else if (action == 1 || action == 3) {
            this.this$0.removeDeleteTask();
        }
        return Unit.INSTANCE;
    }
}
