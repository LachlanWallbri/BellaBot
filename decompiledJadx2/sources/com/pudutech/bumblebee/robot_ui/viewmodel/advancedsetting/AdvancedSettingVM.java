package com.pudutech.bumblebee.robot_ui.viewmodel.advancedsetting;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.viewmodel.VMExtKt;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.base.viewmodel.ext.BaseViewModelExtKt;
import com.pudutech.disinfect.baselib.network.response.EnvItem;
import com.pudutech.disinfect.baselib.network.response.RobotActiveStatusResp;
import com.pudutech.disinfect.baselib.network.response.SwitchEnvResp;
import com.pudutech.disinfect.baselib.state.AppException;
import com.pudutech.pd_network.report.utils.GsonUtils;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: AdvancedSettingVM.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0004J\u000e\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0007J\u0006\u0010\u0019\u001a\u00020\u0015J\u000e\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\t0\r¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000f¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/viewmodel/advancedsetting/AdvancedSettingVM;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "TAG", "", "_localserverListLV", "Landroidx/lifecycle/MutableLiveData;", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/advancedsetting/ServerInfoBean;", "_serverListLV", "", "_serverStateLV", "", "localserverListLV", "Landroidx/lifecycle/LiveData;", "getLocalserverListLV", "()Landroidx/lifecycle/LiveData;", "serverListLV", "getServerListLV", "serverStateLV", "getServerStateLV", "checkServerState", "", "host", "compareSelectedLocal", "selected", "requestServerList", "saveServerInfo", "serverInfo", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class AdvancedSettingVM extends BaseViewModel {
    private final String TAG = "AdvancedSettingVM";
    private final MutableLiveData<List<ServerInfoBean>> _serverListLV = new MutableLiveData<>();
    private final LiveData<List<ServerInfoBean>> serverListLV = VMExtKt.toLiveData(this._serverListLV);
    private final MutableLiveData<Boolean> _serverStateLV = new MutableLiveData<>();
    private final LiveData<Boolean> serverStateLV = VMExtKt.toLiveData(this._serverStateLV);
    private final MutableLiveData<ServerInfoBean> _localserverListLV = new MutableLiveData<>();
    private final LiveData<ServerInfoBean> localserverListLV = VMExtKt.toLiveData(this._localserverListLV);

    public AdvancedSettingVM() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C44161(null), 2, null);
    }

    public final LiveData<List<ServerInfoBean>> getServerListLV() {
        return this.serverListLV;
    }

    public final LiveData<Boolean> getServerStateLV() {
        return this.serverStateLV;
    }

    public final LiveData<ServerInfoBean> getLocalserverListLV() {
        return this.localserverListLV;
    }

    /* compiled from: AdvancedSettingVM.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.viewmodel.advancedsetting.AdvancedSettingVM$1", m3970f = "AdvancedSettingVM.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.robot_ui.viewmodel.advancedsetting.AdvancedSettingVM$1 */
    /* loaded from: classes4.dex */
    static final class C44161 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4991p$;

        C44161(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C44161 c44161 = new C44161(completion);
            c44161.f4991p$ = (CoroutineScope) obj;
            return c44161;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C44161) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4991p$;
            if (Constans.INSTANCE.getServerInfo().length() == 0) {
                Pdlog.m3273d(AdvancedSettingVM.this.TAG, "Constans.serverInfo is empty");
                AdvancedSettingVM.this._localserverListLV.postValue(null);
            } else {
                ServerInfoBean serverInfoBean = (ServerInfoBean) GsonUtils.fromLocalJson(Constans.INSTANCE.getServerInfo(), ServerInfoBean.class);
                Pdlog.m3273d(AdvancedSettingVM.this.TAG, " localServerInfo:" + serverInfoBean + ' ');
                AdvancedSettingVM.this._localserverListLV.postValue(serverInfoBean);
            }
            return Unit.INSTANCE;
        }
    }

    public final void requestServerList() {
        BaseViewModelExtKt.requestNetworkData$default(ViewModelKt.getViewModelScope(this), new AdvancedSettingVM$requestServerList$1(null), new Function1<SwitchEnvResp, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.advancedsetting.AdvancedSettingVM$requestServerList$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SwitchEnvResp switchEnvResp) {
                invoke2(switchEnvResp);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SwitchEnvResp it) {
                MutableLiveData mutableLiveData;
                Integer id;
                Intrinsics.checkParameterIsNotNull(it, "it");
                Pdlog.m3273d(AdvancedSettingVM.this.TAG, "requestServerList: " + it);
                ArrayList arrayList = new ArrayList();
                List<EnvItem> item = it.getItem();
                if (item != null) {
                    int i = 0;
                    for (Object obj : item) {
                        int i2 = i + 1;
                        if (i < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        EnvItem envItem = (EnvItem) obj;
                        Integer id2 = envItem.getId();
                        String name = envItem.getName();
                        String host = envItem.getHost();
                        ServerInfoBean value = AdvancedSettingVM.this.getLocalserverListLV().getValue();
                        arrayList.add(new ServerInfoBean(id2, name, host, Intrinsics.areEqual(value != null ? value.getId() : null, envItem.getId()) || (AdvancedSettingVM.this._localserverListLV.getValue() == 0 && (id = envItem.getId()) != null && id.intValue() == 1)));
                        i = i2;
                    }
                }
                mutableLiveData = AdvancedSettingVM.this._serverListLV;
                mutableLiveData.postValue(arrayList);
            }
        }, new Function1<AppException, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.advancedsetting.AdvancedSettingVM$requestServerList$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AppException appException) {
                invoke2(appException);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AppException it) {
                MutableLiveData mutableLiveData;
                Intrinsics.checkParameterIsNotNull(it, "it");
                Pdlog.m3273d(AdvancedSettingVM.this.TAG, "requestServerList error:" + it);
                mutableLiveData = AdvancedSettingVM.this._serverListLV;
                mutableLiveData.postValue(CollectionsKt.emptyList());
            }
        }, null, 16, null);
    }

    public final boolean compareSelectedLocal(ServerInfoBean selected) {
        Integer id;
        Intrinsics.checkParameterIsNotNull(selected, "selected");
        ServerInfoBean value = this.localserverListLV.getValue();
        int intValue = (value == null || (id = value.getId()) == null) ? 1 : id.intValue();
        Integer id2 = selected.getId();
        return id2 != null && intValue == id2.intValue();
    }

    public final void checkServerState(String host) {
        Intrinsics.checkParameterIsNotNull(host, "host");
        Pdlog.m3273d(this.TAG, "checkServerState host: " + host);
        BaseViewModelExtKt.requestNetworkData$default(ViewModelKt.getViewModelScope(this), new AdvancedSettingVM$checkServerState$1(host, null), new Function1<RobotActiveStatusResp, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.advancedsetting.AdvancedSettingVM$checkServerState$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(RobotActiveStatusResp robotActiveStatusResp) {
                invoke2(robotActiveStatusResp);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RobotActiveStatusResp it) {
                MutableLiveData mutableLiveData;
                Intrinsics.checkParameterIsNotNull(it, "it");
                Pdlog.m3273d(AdvancedSettingVM.this.TAG, "checkServerState: true -- " + it);
                mutableLiveData = AdvancedSettingVM.this._serverStateLV;
                mutableLiveData.postValue(true);
            }
        }, new Function1<AppException, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.advancedsetting.AdvancedSettingVM$checkServerState$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AppException appException) {
                invoke2(appException);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AppException it) {
                MutableLiveData mutableLiveData;
                Intrinsics.checkParameterIsNotNull(it, "it");
                Pdlog.m3273d(AdvancedSettingVM.this.TAG, "checkServerState: false -- " + it.getErrorMsg());
                mutableLiveData = AdvancedSettingVM.this._serverStateLV;
                mutableLiveData.postValue(false);
            }
        }, null, 16, null);
    }

    public final void saveServerInfo(ServerInfoBean serverInfo) {
        Intrinsics.checkParameterIsNotNull(serverInfo, "serverInfo");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new AdvancedSettingVM$saveServerInfo$1(this, serverInfo, null), 2, null);
    }
}
