package com.pudutech.bumblebee.robot_ui.p054ui.fragment;

import android.widget.TextView;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.disinfect.baselib.ext.view.ViewExtKt;
import com.pudutech.pd_network.PdNetworkManager;
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

/* compiled from: RobotActiveFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.fragment.RobotActiveFragment$initView$3", m3970f = "RobotActiveFragment.kt", m3971i = {0}, m3972l = {89}, m3973m = "invokeSuspend", m3974n = {"$this$launchWhenStarted"}, m3975s = {"L$0"})
/* loaded from: classes3.dex */
final class RobotActiveFragment$initView$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4919p$;
    final /* synthetic */ RobotActiveFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RobotActiveFragment$initView$3(RobotActiveFragment robotActiveFragment, Continuation continuation) {
        super(2, continuation);
        this.this$0 = robotActiveFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotActiveFragment$initView$3 robotActiveFragment$initView$3 = new RobotActiveFragment$initView$3(this.this$0, completion);
        robotActiveFragment$initView$3.f4919p$ = (CoroutineScope) obj;
        return robotActiveFragment$initView$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotActiveFragment$initView$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        TextView sn_tv;
        StringBuilder sb;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4919p$;
            sn_tv = (TextView) this.this$0._$_findCachedViewById(C4188R.id.sn_tv);
            Intrinsics.checkExpressionValueIsNotNull(sn_tv, "sn_tv");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("SN:");
            PdNetworkManager pdNetworkManager = PdNetworkManager.f10310INSTANCE;
            this.L$0 = coroutineScope;
            this.L$1 = sn_tv;
            this.L$2 = sb2;
            this.label = 1;
            obj = pdNetworkManager.mo3303sn(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            sb = sb2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            sb = (StringBuilder) this.L$2;
            sn_tv = (TextView) this.L$1;
            ResultKt.throwOnFailure(obj);
        }
        CharSequence charSequence = (CharSequence) obj;
        if (charSequence.length() == 0) {
            charSequence = "Null";
        }
        sb.append((String) charSequence);
        sn_tv.setText(sb.toString());
        TextView sn_tv2 = (TextView) this.this$0._$_findCachedViewById(C4188R.id.sn_tv);
        Intrinsics.checkExpressionValueIsNotNull(sn_tv2, "sn_tv");
        ViewExtKt.visible(sn_tv2);
        return Unit.INSTANCE;
    }
}
