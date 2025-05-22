package com.pudutech.peanut.robot_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: LocationAndMapModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000-\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u000b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u000eH\u0014J\u0006\u0010\u0010\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\f¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/LocationAndMapModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "TAG", "", "checkLocationInit", "Landroidx/lifecycle/MutableLiveData;", "", "getCheckLocationInit", "()Landroidx/lifecycle/MutableLiveData;", "switchMapResultListener", "com/pudutech/peanut/robot_ui/viewmodel/LocationAndMapModel$switchMapResultListener$1", "Lcom/pudutech/peanut/robot_ui/viewmodel/LocationAndMapModel$switchMapResultListener$1;", "getLocationInitStatus", "", "onCleared", "reloadLocation", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LocationAndMapModel extends BaseViewModel {
    private final String TAG = "LocationAndMapModel";
    private final MutableLiveData<Boolean> checkLocationInit = new MutableLiveData<>();
    private final LocationAndMapModel$switchMapResultListener$1 switchMapResultListener = new RobotMapManager.SwitchMapResultListener() { // from class: com.pudutech.peanut.robot_ui.viewmodel.LocationAndMapModel$switchMapResultListener$1
        @Override // com.pudutech.mirsdkwrap.lib.map.RobotMapManager.SwitchMapResultListener
        public void onResult(boolean b, String errorMsg) {
            String str;
            str = LocationAndMapModel.this.TAG;
            Pdlog.m3273d(str, "switchMapResultListener onResult = " + b);
        }
    };

    /* JADX WARN: Type inference failed for: r0v2, types: [com.pudutech.peanut.robot_ui.viewmodel.LocationAndMapModel$switchMapResultListener$1] */
    public LocationAndMapModel() {
        RobotMapManager.INSTANCE.addSwitchMapResultListener(this.switchMapResultListener);
    }

    public final MutableLiveData<Boolean> getCheckLocationInit() {
        return this.checkLocationInit;
    }

    public final void reloadLocation() {
        RobotMapManager.INSTANCE.reloadLocation();
    }

    public final void getLocationInitStatus() {
        this.checkLocationInit.setValue(null);
        RobotMapManager.INSTANCE.checkLocationSelftInit(new Function1<Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.viewmodel.LocationAndMapModel$getLocationInitStatus$1
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
                String str;
                str = LocationAndMapModel.this.TAG;
                Pdlog.m3273d(str, "getLocationInitStatus " + z);
                LocationAndMapModel.this.getCheckLocationInit().setValue(Boolean.valueOf(z));
            }
        }, 15000L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        RobotMapManager.INSTANCE.removeSwitchMapResultListener(this.switchMapResultListener);
    }
}
