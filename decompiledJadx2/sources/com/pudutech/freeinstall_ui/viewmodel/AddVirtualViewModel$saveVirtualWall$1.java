package com.pudutech.freeinstall_ui.viewmodel;

import com.pudutech.freeinstall_ui.bean.VirtualItemBean;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.freeinstall_wrapper.LocateMappingManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

/* compiled from: AddVirtualViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.AddVirtualViewModel$saveVirtualWall$1", m3970f = "AddVirtualViewModel.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes2.dex */
final class AddVirtualViewModel$saveVirtualWall$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List $list;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5262p$;
    final /* synthetic */ AddVirtualViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddVirtualViewModel$saveVirtualWall$1(AddVirtualViewModel addVirtualViewModel, List list, Continuation continuation) {
        super(2, continuation);
        this.this$0 = addVirtualViewModel;
        this.$list = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AddVirtualViewModel$saveVirtualWall$1 addVirtualViewModel$saveVirtualWall$1 = new AddVirtualViewModel$saveVirtualWall$1(this.this$0, this.$list, completion);
        addVirtualViewModel$saveVirtualWall$1.f5262p$ = (CoroutineScope) obj;
        return addVirtualViewModel$saveVirtualWall$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AddVirtualViewModel$saveVirtualWall$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5262p$;
        ArrayList arrayList = new ArrayList();
        Iterator it = this.$list.iterator();
        while (it.hasNext()) {
            arrayList.addAll(((VirtualItemBean) it.next()).getVector3ds());
        }
        LocateMappingManager.INSTANCE.saveVirtualWall(arrayList);
        SpDataUtils.INSTANCE.saveVirtual(this.$list);
        this.this$0.getDoublePath();
        return Unit.INSTANCE;
    }
}
