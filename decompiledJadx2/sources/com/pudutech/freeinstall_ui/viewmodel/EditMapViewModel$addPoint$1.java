package com.pudutech.freeinstall_ui.viewmodel;

import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_ui.adapter.TableListItem;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.freeinstall_wrapper.LocateMappingManager;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: EditMapViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.EditMapViewModel$addPoint$1", m3970f = "EditMapViewModel.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes2.dex */
public final class EditMapViewModel$addPoint$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5269p$;
    final /* synthetic */ EditMapViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditMapViewModel$addPoint$1(EditMapViewModel editMapViewModel, Continuation continuation) {
        super(2, continuation);
        this.this$0 = editMapViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        EditMapViewModel$addPoint$1 editMapViewModel$addPoint$1 = new EditMapViewModel$addPoint$1(this.this$0, completion);
        editMapViewModel$addPoint$1.f5269p$ = (CoroutineScope) obj;
        return editMapViewModel$addPoint$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((EditMapViewModel$addPoint$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5269p$;
        List<TableListItem> addTable = SpDataUtils.INSTANCE.getAddTable();
        List<TableListItem> addDoorPoint = SpDataUtils.INSTANCE.getAddDoorPoint();
        List<TableListItem> addMeal = SpDataUtils.INSTANCE.getAddMeal();
        List<TableListItem> addStation = SpDataUtils.INSTANCE.getAddStation();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        if (addTable != null) {
            Iterator<T> it = addTable.iterator();
            while (it.hasNext()) {
                arrayList.add(((TableListItem) it.next()).getDestination());
            }
        }
        if (addDoorPoint != null) {
            Iterator<T> it2 = addDoorPoint.iterator();
            while (it2.hasNext()) {
                arrayList.add(((TableListItem) it2.next()).getDestination());
            }
        }
        if (addMeal != null) {
            for (TableListItem tableListItem : addMeal) {
                arrayList.add(tableListItem.getDestination());
                arrayList3.add(tableListItem.getDestination().getVector());
            }
        }
        if (addStation != null) {
            for (TableListItem tableListItem2 : addStation) {
                arrayList.add(tableListItem2.getDestination());
                arrayList2.add(tableListItem2.getDestination().getVector());
            }
        }
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "addPoint" + arrayList);
        ArrayList arrayList4 = new ArrayList();
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            arrayList4.add(((Destination) it3.next()).getVector());
        }
        SpDataUtils.INSTANCE.saveExceptionPoint(LocateMappingManager.INSTANCE.createTopomap(arrayList4, arrayList));
        LocateMappingManager.INSTANCE.createScheduleConfig(arrayList2, arrayList3);
        this.this$0.checkVirtual();
        this.this$0.addChargePile();
        return Unit.INSTANCE;
    }
}
