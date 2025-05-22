package com.pudutech.freeinstall_ui.viewmodel;

import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_ui.adapter.DoublePathListItem;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.freeinstall_wrapper.LocateMappingManager;
import java.util.ArrayList;
import java.util.List;
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
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SureMapViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.SureMapViewModel$setDoublePath$1", m3970f = "SureMapViewModel.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes2.dex */
public final class SureMapViewModel$setDoublePath$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List $listItems;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5298p$;
    final /* synthetic */ SureMapViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SureMapViewModel$setDoublePath$1(SureMapViewModel sureMapViewModel, List list, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sureMapViewModel;
        this.$listItems = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SureMapViewModel$setDoublePath$1 sureMapViewModel$setDoublePath$1 = new SureMapViewModel$setDoublePath$1(this.this$0, this.$listItems, completion);
        sureMapViewModel$setDoublePath$1.f5298p$ = (CoroutineScope) obj;
        return sureMapViewModel$setDoublePath$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SureMapViewModel$setDoublePath$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5298p$;
        ArrayList arrayList = new ArrayList();
        for (DoublePathListItem doublePathListItem : this.$listItems) {
            if (doublePathListItem.isSelect()) {
                arrayList.add(Boxing.boxInt(Integer.parseInt(doublePathListItem.getName())));
            }
        }
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "setDoublePath " + arrayList);
        SpDataUtils.INSTANCE.saveDoublePath(this.$listItems);
        LocateMappingManager.INSTANCE.setTwoWayroad(arrayList);
        this.this$0.addPath();
        return Unit.INSTANCE;
    }
}
