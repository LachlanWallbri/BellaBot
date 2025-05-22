package com.pudutech.module_robot_selfcheck.domain.request;

import android.app.Application;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.BaseApp;
import com.pudutech.disinfect.baselib.base.BaseRequest;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.callback.livedata.BooleanLiveData;
import com.pudutech.disinfect.baselib.util.PackageUtil;
import com.pudutech.disinfect.baselib.util.WifiUtil;
import com.pudutech.resources.language.LanguageUtils;
import com.pudutech.robot.update.AppUpdateManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdateSoftwareRequest.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\b\u001a\u00020\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0007¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/domain/request/UpdateSoftwareRequest;", "Lcom/pudutech/disinfect/baselib/base/BaseRequest;", "VM", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "(Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;)V", "isSkipUpdate", "Lcom/pudutech/disinfect/baselib/callback/livedata/BooleanLiveData;", "()Lcom/pudutech/disinfect/baselib/callback/livedata/BooleanLiveData;", "requestUpdateSoftware", "", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class UpdateSoftwareRequest extends BaseRequest {
    private final BooleanLiveData isSkipUpdate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdateSoftwareRequest(BaseViewModel VM) {
        super(VM);
        Intrinsics.checkParameterIsNotNull(VM, "VM");
        this.isSkipUpdate = new BooleanLiveData();
    }

    /* renamed from: isSkipUpdate, reason: from getter */
    public final BooleanLiveData getIsSkipUpdate() {
        return this.isSkipUpdate;
    }

    public final void requestUpdateSoftware() {
        Pdlog.m3274e("requestUpdateSoftware", new Object[0]);
        Application instance = BaseApp.INSTANCE.getINSTANCE();
        Application application = instance;
        Integer versionCodeByBuildConfig = PackageUtil.INSTANCE.getVersionCodeByBuildConfig(application);
        String productNameByBuildConfig = PackageUtil.INSTANCE.getProductNameByBuildConfig(application);
        if (versionCodeByBuildConfig == null || productNameByBuildConfig == null) {
            Pdlog.m3274e(getTAG(), "versionCode or productName is null");
            this.isSkipUpdate.postValue(true);
            return;
        }
        LanguageUtils languageUtils = new LanguageUtils(application);
        String mac = WifiUtil.INSTANCE.getMac();
        if (mac == null) {
            mac = "";
        }
        AppUpdateManager.CheckUpdate checkUpdate = new AppUpdateManager.CheckUpdate(mac, (languageUtils.getCurrent().getLocale().getLanguage() + "-") + languageUtils.getCurrent().getLocale().getCountry(), PackageUtil.INSTANCE.getVersionName(application), versionCodeByBuildConfig.intValue(), productNameByBuildConfig);
        Pdlog.m3273d(getTAG(), "startCheckFormServer param = " + checkUpdate);
        AppUpdateManager.INSTANCE.checkAppUpdate(application, checkUpdate, false, new UpdateSoftwareRequest$requestUpdateSoftware$1(this, instance));
    }
}
