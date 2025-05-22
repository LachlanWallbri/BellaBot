package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.content.Context;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.util.RobotSpeedUtil;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AdvancedSettingsFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/bumblebee/robot_ui/module/setting/ui/AdvancedSettingsFragment$initDeliverSpeed$1$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initDeliverSpeed$1$$special$$inlined$let$lambda$1 */
/* loaded from: classes3.dex */
final class C4213x293d5fe1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $speed_index;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4867p$;
    final /* synthetic */ AdvancedSettingsFragment$initDeliverSpeed$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C4213x293d5fe1(int i, Continuation continuation, AdvancedSettingsFragment$initDeliverSpeed$1 advancedSettingsFragment$initDeliverSpeed$1) {
        super(2, continuation);
        this.$speed_index = i;
        this.this$0 = advancedSettingsFragment$initDeliverSpeed$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C4213x293d5fe1 c4213x293d5fe1 = new C4213x293d5fe1(this.$speed_index, completion, this.this$0);
        c4213x293d5fe1.f4867p$ = (CoroutineScope) obj;
        return c4213x293d5fe1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C4213x293d5fe1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ArrayList arrayList;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f4867p$;
        RobotSpeedUtil robotSpeedUtil = RobotSpeedUtil.INSTANCE;
        Context context = RobotContext.INSTANCE.getContext();
        arrayList = this.this$0.this$0.speedList;
        Object obj2 = arrayList.get(this.$speed_index);
        Intrinsics.checkExpressionValueIsNotNull(obj2, "speedList[speed_index]");
        robotSpeedUtil.setDeliverSpeedLevel(context, (String) obj2);
        return Unit.INSTANCE;
    }
}
