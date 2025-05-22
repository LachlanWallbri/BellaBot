package com.pudutech.freeinstall_ui.viewmodel;

import com.pudutech.freeinstall_ui.AppContext;
import com.pudutech.freeinstall_ui.adapter.CruiseListItem;
import com.pudutech.freeinstall_ui.adapter.FunctionSelectItem;
import com.pudutech.freeinstall_ui.adapter.TableListItem;
import com.pudutech.freeinstall_ui.utils.BusinessFunction;
import com.pudutech.freeinstall_ui.utils.ConfigDataHelper;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.freeinstall_ui.viewmodel.EditMapViewModel;
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
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.EditMapViewModel$getFunctionItemData$1", m3970f = "EditMapViewModel.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes2.dex */
public final class EditMapViewModel$getFunctionItemData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5271p$;
    final /* synthetic */ EditMapViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditMapViewModel$getFunctionItemData$1(EditMapViewModel editMapViewModel, Continuation continuation) {
        super(2, continuation);
        this.this$0 = editMapViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        EditMapViewModel$getFunctionItemData$1 editMapViewModel$getFunctionItemData$1 = new EditMapViewModel$getFunctionItemData$1(this.this$0, completion);
        editMapViewModel$getFunctionItemData$1.f5271p$ = (CoroutineScope) obj;
        return editMapViewModel$getFunctionItemData$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((EditMapViewModel$getFunctionItemData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x019d  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Iterator it;
        Boolean boxBoolean;
        Boolean boxBoolean2;
        boolean z;
        boolean z2;
        Boolean boxBoolean3;
        Boolean boxBoolean4;
        Boolean boxBoolean5;
        Boolean boxBoolean6;
        boolean z3;
        boolean z4;
        Boolean boxBoolean7;
        Boolean boxBoolean8;
        Boolean boxBoolean9;
        Boolean boxBoolean10;
        boolean z5;
        boolean z6;
        Boolean boxBoolean11;
        Boolean boxBoolean12;
        Boolean boxBoolean13;
        Boolean boxBoolean14;
        Boolean boxBoolean15;
        Boolean boxBoolean16;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5271p$;
        ArrayList<BusinessFunction> functionList = ConfigDataHelper.INSTANCE.getFunctionList();
        ArrayList arrayList = new ArrayList();
        List<TableListItem> addTable = SpDataUtils.INSTANCE.getAddTable();
        List<TableListItem> addDoorPoint = SpDataUtils.INSTANCE.getAddDoorPoint();
        List<CruiseListItem> addCruisePath = SpDataUtils.INSTANCE.getAddCruisePath();
        List<TableListItem> addMeal = SpDataUtils.INSTANCE.getAddMeal();
        List<TableListItem> addStation = SpDataUtils.INSTANCE.getAddStation();
        Iterator it2 = functionList.iterator();
        while (it2.hasNext()) {
            BusinessFunction businessFunction = (BusinessFunction) it2.next();
            int i = EditMapViewModel.WhenMappings.$EnumSwitchMapping$0[businessFunction.ordinal()];
            if (i == 1) {
                it = it2;
                String string = AppContext.INSTANCE.getContext().getString(C5362R.string.function_assures);
                Intrinsics.checkExpressionValueIsNotNull(string, "AppContext.context.getSt….string.function_assures)");
                String string2 = AppContext.INSTANCE.getContext().getString(C5362R.string.function_assures_notice);
                Intrinsics.checkExpressionValueIsNotNull(string2, "AppContext.context.getSt….function_assures_notice)");
                arrayList.add(new FunctionSelectItem(string, string2, C5362R.drawable.fun_pic_set_door, businessFunction, (addDoorPoint == null || (boxBoolean2 = Boxing.boxBoolean(addDoorPoint.isEmpty() ^ true)) == null) ? false : boxBoolean2.booleanValue(), (addDoorPoint == null || (boxBoolean = Boxing.boxBoolean(addDoorPoint.isEmpty() ^ true)) == null) ? false : boxBoolean.booleanValue()));
            } else if (i == 2) {
                it = it2;
                String string3 = AppContext.INSTANCE.getContext().getString(C5362R.string.function_reception);
                Intrinsics.checkExpressionValueIsNotNull(string3, "AppContext.context.getSt…tring.function_reception)");
                String string4 = AppContext.INSTANCE.getContext().getString(C5362R.string.function_reception_notice);
                Intrinsics.checkExpressionValueIsNotNull(string4, "AppContext.context.getSt…unction_reception_notice)");
                int i2 = C5362R.drawable.fun_pic_set_receptionist;
                if ((addDoorPoint == null || (boxBoolean6 = Boxing.boxBoolean(addDoorPoint.isEmpty() ^ true)) == null) ? false : boxBoolean6.booleanValue()) {
                    if ((addTable == null || (boxBoolean5 = Boxing.boxBoolean(addTable.isEmpty() ^ true)) == null) ? false : boxBoolean5.booleanValue()) {
                        z = true;
                        if ((addDoorPoint != null || (boxBoolean4 = Boxing.boxBoolean(addDoorPoint.isEmpty() ^ true)) == null) ? false : boxBoolean4.booleanValue()) {
                            if ((addTable == null || (boxBoolean3 = Boxing.boxBoolean(addTable.isEmpty() ^ true)) == null) ? false : boxBoolean3.booleanValue()) {
                                z2 = true;
                                arrayList.add(new FunctionSelectItem(string3, string4, i2, businessFunction, z, z2));
                            }
                        }
                        z2 = false;
                        arrayList.add(new FunctionSelectItem(string3, string4, i2, businessFunction, z, z2));
                    }
                }
                z = false;
                if ((addDoorPoint != null || (boxBoolean4 = Boxing.boxBoolean(addDoorPoint.isEmpty() ^ true)) == null) ? false : boxBoolean4.booleanValue()) {
                }
                z2 = false;
                arrayList.add(new FunctionSelectItem(string3, string4, i2, businessFunction, z, z2));
            } else if (i == 3) {
                it = it2;
                String string5 = AppContext.INSTANCE.getContext().getString(C5362R.string.function_curise);
                Intrinsics.checkExpressionValueIsNotNull(string5, "AppContext.context.getSt…R.string.function_curise)");
                String string6 = AppContext.INSTANCE.getContext().getString(C5362R.string.function_curise_notice);
                Intrinsics.checkExpressionValueIsNotNull(string6, "AppContext.context.getSt…g.function_curise_notice)");
                int i3 = C5362R.drawable.fun_pic_set_cruise;
                if ((addDoorPoint == null || (boxBoolean10 = Boxing.boxBoolean(addDoorPoint.isEmpty() ^ true)) == null) ? false : boxBoolean10.booleanValue()) {
                    if ((addCruisePath == null || (boxBoolean9 = Boxing.boxBoolean(addCruisePath.isEmpty() ^ true)) == null) ? false : boxBoolean9.booleanValue()) {
                        z3 = true;
                        if ((addDoorPoint != null || (boxBoolean8 = Boxing.boxBoolean(addDoorPoint.isEmpty() ^ true)) == null) ? false : boxBoolean8.booleanValue()) {
                            if ((addCruisePath == null || (boxBoolean7 = Boxing.boxBoolean(addCruisePath.isEmpty() ^ true)) == null) ? false : boxBoolean7.booleanValue()) {
                                z4 = true;
                                arrayList.add(new FunctionSelectItem(string5, string6, i3, businessFunction, z3, z4));
                            }
                        }
                        z4 = false;
                        arrayList.add(new FunctionSelectItem(string5, string6, i3, businessFunction, z3, z4));
                    }
                }
                z3 = false;
                if ((addDoorPoint != null || (boxBoolean8 = Boxing.boxBoolean(addDoorPoint.isEmpty() ^ true)) == null) ? false : boxBoolean8.booleanValue()) {
                }
                z4 = false;
                arrayList.add(new FunctionSelectItem(string5, string6, i3, businessFunction, z3, z4));
            } else if (i != 4) {
                it = it2;
            } else {
                String string7 = AppContext.INSTANCE.getContext().getString(C5362R.string.function_meals);
                Intrinsics.checkExpressionValueIsNotNull(string7, "AppContext.context.getSt…(R.string.function_meals)");
                String string8 = AppContext.INSTANCE.getContext().getString(C5362R.string.function_meals_notice);
                Intrinsics.checkExpressionValueIsNotNull(string8, "AppContext.context.getSt…ng.function_meals_notice)");
                int i4 = C5362R.drawable.fun_pic_set_meal;
                if ((addMeal == null || (boxBoolean16 = Boxing.boxBoolean(addMeal.isEmpty() ^ true)) == null) ? false : boxBoolean16.booleanValue()) {
                    if ((addTable == null || (boxBoolean15 = Boxing.boxBoolean(addTable.isEmpty() ^ true)) == null) ? false : boxBoolean15.booleanValue()) {
                        if ((addStation == null || (boxBoolean14 = Boxing.boxBoolean(addStation.isEmpty() ^ true)) == null) ? false : boxBoolean14.booleanValue()) {
                            z5 = true;
                            if ((addMeal != null || (boxBoolean13 = Boxing.boxBoolean(addMeal.isEmpty() ^ true)) == null) ? false : boxBoolean13.booleanValue()) {
                                if ((addTable == null || (boxBoolean12 = Boxing.boxBoolean(addTable.isEmpty() ^ true)) == null) ? false : boxBoolean12.booleanValue()) {
                                    if ((addStation == null || (boxBoolean11 = Boxing.boxBoolean(addStation.isEmpty() ^ true)) == null) ? false : boxBoolean11.booleanValue()) {
                                        z6 = true;
                                        it = it2;
                                        arrayList.add(new FunctionSelectItem(string7, string8, i4, businessFunction, z5, z6));
                                    }
                                }
                            }
                            z6 = false;
                            it = it2;
                            arrayList.add(new FunctionSelectItem(string7, string8, i4, businessFunction, z5, z6));
                        }
                    }
                }
                z5 = false;
                if ((addMeal != null || (boxBoolean13 = Boxing.boxBoolean(addMeal.isEmpty() ^ true)) == null) ? false : boxBoolean13.booleanValue()) {
                }
                z6 = false;
                it = it2;
                arrayList.add(new FunctionSelectItem(string7, string8, i4, businessFunction, z5, z6));
            }
            it2 = it;
        }
        this.this$0.getFunctionData().postValue(arrayList);
        return Unit.INSTANCE;
    }
}
