package com.pudutech.peanut.robot_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.preference.MMKVPreference;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.manager.InitRobotAppManager;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: CheckInitViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0011\u001a\u00020\u0005J\u0006\u0010\u0012\u001a\u00020\u0010J\u0006\u0010\u0013\u001a\u00020\u0010J\u0016\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u0017J\u0006\u0010\u0018\u001a\u00020\u0005J\b\u0010\u0019\u001a\u00020\u0010H\u0014J\u0006\u0010\u001a\u001a\u00020\u0010R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007R)\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/CheckInitViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "checkLocationResult", "Landroidx/lifecycle/MutableLiveData;", "", "getCheckLocationResult", "()Landroidx/lifecycle/MutableLiveData;", "initResult", "Lcom/pudutech/peanut/robot_ui/manager/InitRobotAppManager$InitStatus;", "getInitResult", "initResultListener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "step", "", "checkLanguageIsSet", "checkLocation", "checkMirInit", "getError", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "isActived", "onCleared", "syncMap", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CheckInitViewModel extends BaseViewModel {
    private final MutableLiveData<Boolean> checkLocationResult = new MutableLiveData<>();
    private final MutableLiveData<InitRobotAppManager.InitStatus> initResult = new MutableLiveData<>();
    private final Function1<InitRobotAppManager.InitStatus, Unit> initResultListener = new Function1<InitRobotAppManager.InitStatus, Unit>() { // from class: com.pudutech.peanut.robot_ui.viewmodel.CheckInitViewModel$initResultListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(InitRobotAppManager.InitStatus initStatus) {
            invoke2(initStatus);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(InitRobotAppManager.InitStatus it) {
            Intrinsics.checkParameterIsNotNull(it, "it");
            Pdlog.m3273d("CheckInitViewModel", "initResultListener: " + it);
            CheckInitViewModel.this.getInitResult().postValue(it);
        }
    };

    public final MutableLiveData<Boolean> getCheckLocationResult() {
        return this.checkLocationResult;
    }

    public final MutableLiveData<InitRobotAppManager.InitStatus> getInitResult() {
        return this.initResult;
    }

    public final boolean checkLanguageIsSet() {
        return !InitRobotAppManager.INSTANCE.isNeedSetLanguage();
    }

    public final boolean isActived() {
        Boolean decodeBoolean = MMKVPreference.INSTANCE.decodeBoolean(Constans.ACTIVE_FIRST);
        if (decodeBoolean != null) {
            return decodeBoolean.booleanValue();
        }
        return false;
    }

    public final void syncMap() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new CheckInitViewModel$syncMap$1(null), 3, null);
    }

    public final void checkMirInit() {
        if (InitRobotAppManager.INSTANCE.getInitResult() == InitRobotAppManager.InitStatus.INIT) {
            Pdlog.m3273d("CheckInitViewModel", "checkMirInit : INIT");
            InitRobotAppManager.INSTANCE.addInitResultListener(this.initResultListener);
        } else {
            Pdlog.m3273d("CheckInitViewModel", "checkMirInit : else");
            this.initResult.postValue(InitRobotAppManager.INSTANCE.getInitResult());
        }
    }

    public final void checkLocation() {
        RobotMapManager.checkLocationInit$default(RobotMapManager.INSTANCE, new Function1<Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.viewmodel.CheckInitViewModel$checkLocation$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                CheckInitViewModel.this.getCheckLocationResult().postValue(Boolean.valueOf(z));
            }
        }, 0L, 2, null);
    }

    public final ArrayList<String> getError() {
        return InitRobotAppManager.INSTANCE.getErrors();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        InitRobotAppManager.INSTANCE.removeInitResultListener(this.initResultListener);
    }
}
