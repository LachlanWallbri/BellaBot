package com.pudutech.freeinstall_ui.viewmodel;

import com.pudutech.freeinstall_ui.AppContext;
import com.pudutech.freeinstall_ui.adapter.BusinessSetItem;
import com.pudutech.freeinstall_ui.adapter.ChargeListItem;
import com.pudutech.freeinstall_ui.adapter.CruiseListItem;
import com.pudutech.freeinstall_ui.adapter.FunctionSelectItem;
import com.pudutech.freeinstall_ui.adapter.TableListItem;
import com.pudutech.freeinstall_ui.utils.BusinessFunction;
import com.pudutech.freeinstall_ui.utils.ConfigDataHelper;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.module_freeinstall_ui.C5362R;
import java.util.ArrayList;
import java.util.Iterator;
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
/* compiled from: EditMapViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.EditMapViewModel$getBusinessItemData$1", m3970f = "EditMapViewModel.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes2.dex */
public final class EditMapViewModel$getBusinessItemData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $from;
    final /* synthetic */ List $functionItemList;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5270p$;
    final /* synthetic */ EditMapViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditMapViewModel$getBusinessItemData$1(EditMapViewModel editMapViewModel, List list, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = editMapViewModel;
        this.$functionItemList = list;
        this.$from = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        EditMapViewModel$getBusinessItemData$1 editMapViewModel$getBusinessItemData$1 = new EditMapViewModel$getBusinessItemData$1(this.this$0, this.$functionItemList, this.$from, completion);
        editMapViewModel$getBusinessItemData$1.f5270p$ = (CoroutineScope) obj;
        return editMapViewModel$getBusinessItemData$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((EditMapViewModel$getBusinessItemData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean z;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Boolean boxBoolean;
        Boolean boxBoolean2;
        Boolean boxBoolean3;
        Boolean boxBoolean4;
        Boolean boxBoolean5;
        Boolean boxBoolean6;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5270p$;
        ArrayList arrayList = new ArrayList();
        List<TableListItem> addTable = SpDataUtils.INSTANCE.getAddTable();
        List<TableListItem> addDoorPoint = SpDataUtils.INSTANCE.getAddDoorPoint();
        List<CruiseListItem> addCruisePath = SpDataUtils.INSTANCE.getAddCruisePath();
        List<TableListItem> addMeal = SpDataUtils.INSTANCE.getAddMeal();
        List<TableListItem> addStation = SpDataUtils.INSTANCE.getAddStation();
        List<ChargeListItem> addChargePile = SpDataUtils.INSTANCE.getAddChargePile();
        Iterator it = this.$functionItemList.iterator();
        while (true) {
            z = false;
            if (!it.hasNext()) {
                obj2 = null;
                break;
            }
            obj2 = it.next();
            FunctionSelectItem functionSelectItem = (FunctionSelectItem) obj2;
            if (Boxing.boxBoolean(functionSelectItem.getBusinessFunction() == BusinessFunction.FUNCTION_MEALS && functionSelectItem.isSelect()).booleanValue()) {
                break;
            }
        }
        if (((FunctionSelectItem) obj2) != null) {
            String string = AppContext.INSTANCE.getContext().getString(C5362R.string.set_meal_point);
            Intrinsics.checkExpressionValueIsNotNull(string, "AppContext.context.getSt…(R.string.set_meal_point)");
            String string2 = AppContext.INSTANCE.getContext().getString(C5362R.string.set_meal_position_content);
            Intrinsics.checkExpressionValueIsNotNull(string2, "AppContext.context.getSt…et_meal_position_content)");
            arrayList.add(new BusinessSetItem(string, string2, C5362R.drawable.pic_set_meals_bus, 1, (addMeal == null || (boxBoolean6 = Boxing.boxBoolean(addMeal.isEmpty() ^ true)) == null) ? false : boxBoolean6.booleanValue(), true));
        }
        Iterator it2 = this.$functionItemList.iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj3 = null;
                break;
            }
            obj3 = it2.next();
            FunctionSelectItem functionSelectItem2 = (FunctionSelectItem) obj3;
            if (Boxing.boxBoolean((functionSelectItem2.getBusinessFunction() == BusinessFunction.FUNCTION_ASSURES || functionSelectItem2.getBusinessFunction() == BusinessFunction.FUNCTION_RECEPTIONIST || functionSelectItem2.getBusinessFunction() == BusinessFunction.FUNCTION_CRUISE) && functionSelectItem2.isSelect()).booleanValue()) {
                break;
            }
        }
        if (((FunctionSelectItem) obj3) != null) {
            String string3 = AppContext.INSTANCE.getContext().getString(C5362R.string.set_door_position);
            Intrinsics.checkExpressionValueIsNotNull(string3, "AppContext.context.getSt…string.set_door_position)");
            String string4 = AppContext.INSTANCE.getContext().getString(C5362R.string.set_door_position_content);
            Intrinsics.checkExpressionValueIsNotNull(string4, "AppContext.context.getSt…et_door_position_content)");
            arrayList.add(new BusinessSetItem(string3, string4, C5362R.drawable.pic_set_door_bus, 2, (addDoorPoint == null || (boxBoolean5 = Boxing.boxBoolean(addDoorPoint.isEmpty() ^ true)) == null) ? false : boxBoolean5.booleanValue(), true));
        }
        Iterator it3 = this.$functionItemList.iterator();
        while (true) {
            if (!it3.hasNext()) {
                obj4 = null;
                break;
            }
            obj4 = it3.next();
            FunctionSelectItem functionSelectItem3 = (FunctionSelectItem) obj4;
            if (Boxing.boxBoolean((functionSelectItem3.getBusinessFunction() == BusinessFunction.FUNCTION_RECEPTIONIST || functionSelectItem3.getBusinessFunction() == BusinessFunction.FUNCTION_MEALS) && functionSelectItem3.isSelect()).booleanValue()) {
                break;
            }
        }
        if (((FunctionSelectItem) obj4) != null) {
            String string5 = AppContext.INSTANCE.getContext().getString(C5362R.string.set_table_num);
            Intrinsics.checkExpressionValueIsNotNull(string5, "AppContext.context.getSt…g(R.string.set_table_num)");
            String string6 = AppContext.INSTANCE.getContext().getString(C5362R.string.set_table_position_content);
            Intrinsics.checkExpressionValueIsNotNull(string6, "AppContext.context.getSt…t_table_position_content)");
            arrayList.add(new BusinessSetItem(string5, string6, C5362R.drawable.pic_set_table_bus, 3, (addTable == null || (boxBoolean4 = Boxing.boxBoolean(addTable.isEmpty() ^ true)) == null) ? false : boxBoolean4.booleanValue(), true));
        }
        Iterator it4 = this.$functionItemList.iterator();
        while (true) {
            if (!it4.hasNext()) {
                obj5 = null;
                break;
            }
            obj5 = it4.next();
            FunctionSelectItem functionSelectItem4 = (FunctionSelectItem) obj5;
            if (Boxing.boxBoolean(functionSelectItem4.getBusinessFunction() == BusinessFunction.FUNCTION_CRUISE && functionSelectItem4.isSelect()).booleanValue()) {
                break;
            }
        }
        if (((FunctionSelectItem) obj5) != null) {
            String string7 = AppContext.INSTANCE.getContext().getString(C5362R.string.set_cruise_path);
            Intrinsics.checkExpressionValueIsNotNull(string7, "AppContext.context.getSt…R.string.set_cruise_path)");
            String string8 = AppContext.INSTANCE.getContext().getString(C5362R.string.set_cruise_position_content);
            Intrinsics.checkExpressionValueIsNotNull(string8, "AppContext.context.getSt…_cruise_position_content)");
            arrayList.add(new BusinessSetItem(string7, string8, C5362R.drawable.pic_set_cruise_bus, 4, (addCruisePath == null || (boxBoolean3 = Boxing.boxBoolean(addCruisePath.isEmpty() ^ true)) == null) ? false : boxBoolean3.booleanValue(), true));
        }
        String string9 = AppContext.INSTANCE.getContext().getString(C5362R.string.set_station_point);
        Intrinsics.checkExpressionValueIsNotNull(string9, "AppContext.context.getSt…string.set_station_point)");
        String string10 = AppContext.INSTANCE.getContext().getString(C5362R.string.set_meal_position_content);
        Intrinsics.checkExpressionValueIsNotNull(string10, "AppContext.context.getSt…et_meal_position_content)");
        arrayList.add(new BusinessSetItem(string9, string10, C5362R.drawable.pic_set_station_bus, 5, (addStation == null || (boxBoolean2 = Boxing.boxBoolean(addStation.isEmpty() ^ true)) == null) ? false : boxBoolean2.booleanValue(), false, 32, null));
        if (ConfigDataHelper.INSTANCE.getFunctionList().contains(BusinessFunction.FUNCTION_CHARGE)) {
            String string11 = AppContext.INSTANCE.getContext().getString(C5362R.string.set_charging_pile);
            Intrinsics.checkExpressionValueIsNotNull(string11, "AppContext.context.getSt…string.set_charging_pile)");
            String string12 = AppContext.INSTANCE.getContext().getString(C5362R.string.set_charge_position_content);
            Intrinsics.checkExpressionValueIsNotNull(string12, "AppContext.context.getSt…_charge_position_content)");
            int i = C5362R.drawable.pic_set_charge_bus;
            if (addChargePile != null && (boxBoolean = Boxing.boxBoolean(!addChargePile.isEmpty())) != null) {
                z = boxBoolean.booleanValue();
            }
            arrayList.add(new BusinessSetItem(string11, string12, i, 6, z, false, 32, null));
        }
        if (this.$from == 3) {
            String string13 = AppContext.INSTANCE.getContext().getString(C5362R.string.expand_map);
            Intrinsics.checkExpressionValueIsNotNull(string13, "AppContext.context.getString(R.string.expand_map)");
            String string14 = AppContext.INSTANCE.getContext().getString(C5362R.string.set_expand_position_content);
            Intrinsics.checkExpressionValueIsNotNull(string14, "AppContext.context.getSt…_expand_position_content)");
            arrayList.add(new BusinessSetItem(string13, string14, C5362R.drawable.pic_expand_map_bus, 7, false, false, 48, null));
        }
        this.this$0.getPointData().postValue(arrayList);
        return Unit.INSTANCE;
    }
}
