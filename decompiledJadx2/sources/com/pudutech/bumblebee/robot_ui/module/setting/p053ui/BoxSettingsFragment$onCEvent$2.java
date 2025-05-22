package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import com.pudutech.bumblebee.robot_ui.p054ui.adapter.BeeperAdapter;
import java.util.ArrayList;
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

/* compiled from: BoxSettingsFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.module.setting.ui.BoxSettingsFragment$onCEvent$2", m3970f = "BoxSettingsFragment.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes3.dex */
final class BoxSettingsFragment$onCEvent$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4877p$;
    final /* synthetic */ BoxSettingsFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BoxSettingsFragment$onCEvent$2(BoxSettingsFragment boxSettingsFragment, Continuation continuation) {
        super(2, continuation);
        this.this$0 = boxSettingsFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        BoxSettingsFragment$onCEvent$2 boxSettingsFragment$onCEvent$2 = new BoxSettingsFragment$onCEvent$2(this.this$0, completion);
        boxSettingsFragment$onCEvent$2.f4877p$ = (CoroutineScope) obj;
        return boxSettingsFragment$onCEvent$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BoxSettingsFragment$onCEvent$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        BeeperAdapter beeperAdapter;
        ArrayList arrayList;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f4877p$;
        if (BoxSettingsFragment.access$getRvBeeper$p(this.this$0).getVisibility() == 8) {
            BoxSettingsFragment.access$getRvBeeper$p(this.this$0).setVisibility(0);
        }
        this.this$0.resetRvBeeperHeight();
        beeperAdapter = this.this$0.beeperAdapter;
        if (beeperAdapter != null) {
            arrayList = this.this$0.beeperList;
            beeperAdapter.setNewData(arrayList);
        }
        return Unit.INSTANCE;
    }
}
