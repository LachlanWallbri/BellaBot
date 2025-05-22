package com.pudu.library.loracall.viewModel;

import android.view.View;
import androidx.lifecycle.MutableLiveData;
import com.pudu.library.loracall.KotlinUtilsKt;
import com.pudu.library.loracall.LoRaClient;
import com.pudu.library.loracall.LoRaHelper;
import com.pudu.library.loracall.LoraMsg;
import com.pudu.library.loracall.LoraNetState;
import com.pudu.library.loracall.LoraUpdateProgressState;
import com.pudu.library.loracall.LoraUpdateState;
import com.pudu.library.loracall.MsgReceiveHandle;
import com.pudu.library.loracall.ReceiveMsgType;
import com.pudu.library.loracall.base.BaseViewModel;
import com.pudu.library.loracall.bean.LoraReceiveCall;
import com.pudu.library.loracall.dao.AppDatabase;
import com.pudu.library.loracall.dao.TableBindDao;
import com.pudu.library.loracall.dao.TableMatchBean;
import com.pudu.loracall.library.C3965R;
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.mirsdk.compat.topo.MapElement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LoRaTableMatchViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010)\u001a\u00020*2\u0006\u0010\u0011\u001a\u00020\u0005H\u0002J\u0006\u0010+\u001a\u00020*J\u0006\u0010,\u001a\u00020\u0016J\u0016\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010.\u001a\u00020\u0005H\u0002J\b\u0010/\u001a\u00020*H\u0014J\u000e\u00100\u001a\u00020*2\u0006\u00101\u001a\u00020\u0007J\u0016\u00102\u001a\u00020*2\u0006\u00103\u001a\u0002042\u0006\u0010.\u001a\u00020\u0005RC\u0010\u0003\u001a*\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0004j\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006`\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0014\u0010\r\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0012¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0014R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0012¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0014R\u001d\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0012¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0014R!\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b(\u0010\f\u001a\u0004\b&\u0010'¨\u00065"}, m3961d2 = {"Lcom/pudu/library/loracall/viewModel/LoRaTableMatchViewModel;", "Lcom/pudu/library/loracall/base/BaseViewModel;", "()V", "allData", "Ljava/util/LinkedHashMap;", "", "", "Lcom/pudu/library/loracall/dao/TableMatchBean;", "Lkotlin/collections/LinkedHashMap;", "getAllData", "()Ljava/util/LinkedHashMap;", "allData$delegate", "Lkotlin/Lazy;", "dao", "Lcom/pudu/library/loracall/dao/TableBindDao;", "getDao", "()Lcom/pudu/library/loracall/dao/TableBindDao;", "devAdder", "Landroidx/lifecycle/MutableLiveData;", "getDevAdder", "()Landroidx/lifecycle/MutableLiveData;", "isGroup", "", "()Z", "listener", "Lcom/pudu/library/loracall/MsgReceiveHandle$Listener;", "getListener", "()Lcom/pudu/library/loracall/MsgReceiveHandle$Listener;", "loraProgress", "", "getLoraProgress", "loraState", "getLoraState", "loraUpdateState", "getLoraUpdateState", "tableList", "getTableList", "topList", "getTopList", "()Ljava/util/List;", "topList$delegate", "changeAllData", "", "checkState", "getDevExist", "getTable", MapElement.Key.GROUP, "onCleared", "saveTable", Constants.POINT_TYPE_TABLE, "tabClickView", "view", "Landroid/view/View;", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoRaTableMatchViewModel extends BaseViewModel {
    private final MutableLiveData<String> devAdder;
    private final boolean isGroup;
    private final MsgReceiveHandle.Listener listener;
    private final MutableLiveData<Integer> loraProgress;
    private final MutableLiveData<Integer> loraState;
    private final MutableLiveData<Integer> loraUpdateState;
    private final MutableLiveData<List<TableMatchBean>> tableList;

    /* renamed from: allData$delegate, reason: from kotlin metadata */
    private final Lazy allData = LazyKt.lazy(new Function0<LinkedHashMap<String, List<? extends TableMatchBean>>>() { // from class: com.pudu.library.loracall.viewModel.LoRaTableMatchViewModel$allData$2
        @Override // kotlin.jvm.functions.Function0
        public final LinkedHashMap<String, List<? extends TableMatchBean>> invoke() {
            return LoRaHelper.INSTANCE.getMapTable().getTableList();
        }
    });

    /* renamed from: topList$delegate, reason: from kotlin metadata */
    private final Lazy topList = LazyKt.lazy(new Function0<List<? extends String>>() { // from class: com.pudu.library.loracall.viewModel.LoRaTableMatchViewModel$topList$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final List<? extends String> invoke() {
            LinkedHashMap allData;
            allData = LoRaTableMatchViewModel.this.getAllData();
            LinkedHashMap linkedHashMap = allData;
            ArrayList arrayList = new ArrayList(linkedHashMap.size());
            Iterator it = linkedHashMap.entrySet().iterator();
            while (it.hasNext()) {
                arrayList.add((String) ((Map.Entry) it.next()).getKey());
            }
            return arrayList;
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public final LinkedHashMap<String, List<TableMatchBean>> getAllData() {
        return (LinkedHashMap) this.allData.getValue();
    }

    public final List<String> getTopList() {
        return (List) this.topList.getValue();
    }

    public LoRaTableMatchViewModel() {
        this.isGroup = getAllData().size() > 1;
        this.tableList = new MutableLiveData<>(new ArrayList());
        this.devAdder = new MutableLiveData<>("");
        this.loraState = new MutableLiveData<>();
        this.loraProgress = new MutableLiveData<>(0);
        this.loraUpdateState = new MutableLiveData<>();
        this.listener = new MsgReceiveHandle.Listener() { // from class: com.pudu.library.loracall.viewModel.LoRaTableMatchViewModel$listener$1
            @Override // com.pudu.library.loracall.MsgReceiveHandle.Listener
            public void receive(ReceiveMsgType msgType) {
                Intrinsics.checkParameterIsNotNull(msgType, "msgType");
                if (msgType instanceof LoraMsg) {
                    LoraReceiveCall loraReceiveCall = ((LoraMsg) msgType).toLoraReceiveCall();
                    if (loraReceiveCall == null || loraReceiveCall.getType() != 1) {
                        return;
                    }
                    final String hexString = KotlinUtilsKt.toHexString(ArraysKt.reversedArray(loraReceiveCall.getDevAddr()));
                    if (hexString.length() > 0) {
                        KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.viewModel.LoRaTableMatchViewModel$listener$1$receive$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final String invoke() {
                                return "按键地址: " + hexString;
                            }
                        }, 1, null);
                        LoRaTableMatchViewModel.this.getDevAdder().postValue(hexString);
                        return;
                    }
                    return;
                }
                if (msgType instanceof LoraUpdateProgressState) {
                    LoRaTableMatchViewModel.this.getLoraProgress().postValue(Integer.valueOf(((LoraUpdateProgressState) msgType).getShowProgress()));
                } else if (msgType instanceof LoraUpdateState) {
                    LoRaTableMatchViewModel.this.getLoraUpdateState().postValue(Integer.valueOf(((LoraUpdateState) msgType).getState()));
                } else if (msgType instanceof LoraNetState) {
                    LoRaTableMatchViewModel.this.getLoraState().postValue(Integer.valueOf(((LoraNetState) msgType).getState()));
                }
            }
        };
        BuildersKt__Builders_commonKt.launch$default(getScope(), null, null, new C39591(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TableBindDao getDao() {
        return AppDatabase.INSTANCE.getInstance().getTableDao();
    }

    /* renamed from: isGroup, reason: from getter */
    public final boolean getIsGroup() {
        return this.isGroup;
    }

    public final MutableLiveData<List<TableMatchBean>> getTableList() {
        return this.tableList;
    }

    public final MutableLiveData<String> getDevAdder() {
        return this.devAdder;
    }

    public final MutableLiveData<Integer> getLoraState() {
        return this.loraState;
    }

    public final MutableLiveData<Integer> getLoraProgress() {
        return this.loraProgress;
    }

    public final MutableLiveData<Integer> getLoraUpdateState() {
        return this.loraUpdateState;
    }

    public final MsgReceiveHandle.Listener getListener() {
        return this.listener;
    }

    /* compiled from: LoRaTableMatchViewModel.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudu.library.loracall.viewModel.LoRaTableMatchViewModel$1", m3970f = "LoRaTableMatchViewModel.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudu.library.loracall.viewModel.LoRaTableMatchViewModel$1 */
    /* loaded from: classes4.dex */
    static final class C39591 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4392p$;

        C39591(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C39591 c39591 = new C39591(completion);
            c39591.f4392p$ = (CoroutineScope) obj;
            return c39591;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C39591) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4392p$;
            if (!LoRaTableMatchViewModel.this.getTopList().isEmpty()) {
                MutableLiveData<List<TableMatchBean>> tableList = LoRaTableMatchViewModel.this.getTableList();
                LoRaTableMatchViewModel loRaTableMatchViewModel = LoRaTableMatchViewModel.this;
                tableList.postValue(loRaTableMatchViewModel.getTable(loRaTableMatchViewModel.getTopList().get(0)));
            }
            LoRaClient companion = LoRaClient.INSTANCE.getInstance();
            String name = coroutineScope.getClass().getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "this::class.java.name");
            companion.addListener(name, LoRaTableMatchViewModel.this.getListener());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<TableMatchBean> getTable(String group) {
        List<TableMatchBean> list = getAllData().get(group);
        return list != null ? list : new ArrayList();
    }

    public final void tabClickView(View view, String group) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(group, "group");
        this.tableList.postValue(getTable(group));
    }

    public final void saveTable(TableMatchBean table) {
        Intrinsics.checkParameterIsNotNull(table, "table");
        String value = this.devAdder.getValue();
        if (value != null) {
            Intrinsics.checkExpressionValueIsNotNull(value, "devAdder.value ?: return");
            if (value.length() == 0) {
                return;
            }
            BuildersKt__Builders_commonKt.launch$default(getScope(), null, null, new LoRaTableMatchViewModel$saveTable$1(this, table, value, null), 3, null);
            this.devAdder.postValue("");
            KotlinUtilsKt.showToast(KotlinUtilsKt.getString(this, C3965R.string.lora_bind_success));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void changeAllData(String devAdder) {
        for (Map.Entry<String, List<TableMatchBean>> entry : getAllData().entrySet()) {
            entry.getKey();
            for (TableMatchBean tableMatchBean : entry.getValue()) {
                if (Intrinsics.areEqual(tableMatchBean.getDevAdder(), devAdder)) {
                    tableMatchBean.setDevAdder("");
                }
            }
        }
    }

    public final boolean getDevExist() {
        String value = this.devAdder.getValue();
        if (value != null) {
            Intrinsics.checkExpressionValueIsNotNull(value, "devAdder.value ?: return false");
            if (value.length() == 0) {
                return false;
            }
            for (Map.Entry<String, List<TableMatchBean>> entry : getAllData().entrySet()) {
                entry.getKey();
                Iterator<T> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    if (Intrinsics.areEqual(((TableMatchBean) it.next()).getDevAdder(), value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final void checkState() {
        LoRaClient.INSTANCE.getInstance().getLoRaNetState(new Function1<Integer, Unit>() { // from class: com.pudu.library.loracall.viewModel.LoRaTableMatchViewModel$checkState$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                LoRaTableMatchViewModel.this.getLoraState().postValue(Integer.valueOf(i));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudu.library.loracall.base.BaseViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        LoRaClient companion = LoRaClient.INSTANCE.getInstance();
        String name = getClass().getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "this::class.java.name");
        companion.removeListener(name);
    }
}
