package com.pudutech.freeinstall_ui.activity;

import com.pudutech.freeinstall_ui.adapter.ChargeListItem;
import com.pudutech.freeinstall_ui.adapter.DoublePathListItem;
import com.pudutech.freeinstall_ui.adapter.TableListItem;
import com.pudutech.freeinstall_ui.bean.VirtualItemBean;
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
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
/* compiled from: MapCompleteActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.activity.MapCompleteActivity$initData$1", m3970f = "MapCompleteActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes3.dex */
public final class MapCompleteActivity$initData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5223p$;
    final /* synthetic */ MapCompleteActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapCompleteActivity$initData$1(MapCompleteActivity mapCompleteActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mapCompleteActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MapCompleteActivity$initData$1 mapCompleteActivity$initData$1 = new MapCompleteActivity$initData$1(this.this$0, completion);
        mapCompleteActivity$initData$1.f5223p$ = (CoroutineScope) obj;
        return mapCompleteActivity$initData$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MapCompleteActivity$initData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5223p$;
        List<TableListItem> addTable = SpDataUtils.INSTANCE.getAddTable();
        List<TableListItem> addDoorPoint = SpDataUtils.INSTANCE.getAddDoorPoint();
        List<TableListItem> addMeal = SpDataUtils.INSTANCE.getAddMeal();
        List<TableListItem> addStation = SpDataUtils.INSTANCE.getAddStation();
        List<ChargeListItem> addChargePile = SpDataUtils.INSTANCE.getAddChargePile();
        List<Destination> exceptionPoint = SpDataUtils.INSTANCE.getExceptionPoint();
        SpDataUtils.INSTANCE.getAddCruisePath();
        List<List<Vector3d>> cruisePathBack = SpDataUtils.INSTANCE.getCruisePathBack();
        List<VirtualItemBean> virtual = SpDataUtils.INSTANCE.getVirtual();
        List<DoublePathListItem> doublePath = SpDataUtils.INSTANCE.getDoublePath();
        this.this$0.setTopMap();
        this.this$0.setCruisePath(cruisePathBack);
        this.this$0.setVirtual(virtual);
        this.this$0.setDoublePath(doublePath);
        this.this$0.setPointView(addTable, exceptionPoint, Constants.POINT_TYPE_TABLE);
        this.this$0.setPointView(addDoorPoint, exceptionPoint, Constants.POINT_TYPE_DOOR);
        this.this$0.setPointView(addMeal, exceptionPoint, "dining_outlet");
        this.this$0.setPointView(addStation, exceptionPoint, "parking");
        this.this$0.setChargePoint(addChargePile);
        this.this$0.isDataLoad = true;
        return Unit.INSTANCE;
    }
}
