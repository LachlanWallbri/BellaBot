package com.pudutech.bumblebee.presenter.initialization_task;

import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.presenter.initialization_task.LocationInitContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationInitPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0006H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0006H\u0016¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/initialization_task/LocationInitPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/initialization_task/LocationInitContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/initialization_task/LocationInitContract$PresenterInterface;", "()V", "addRelocationPoint", "", "relocatePoint", "", "checkLocationInit", "getLocateCase", "Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "getrelocatePoints", "", "isLocation", "", "reLoadLocation", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LocationInitPresenter extends BaseOneViewPresenter<LocationInitContract.ViewInterface> implements LocationInitContract.PresenterInterface {
    @Override // com.pudutech.bumblebee.presenter.initialization_task.LocationInitContract.PresenterInterface
    public void checkLocationInit() {
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.initialization_task.LocationInitPresenter$checkLocationInit$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                final boolean isLocalizationSuccess = SDK.INSTANCE.isLocalizationSuccess();
                LocationInitPresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.initialization_task.LocationInitPresenter$checkLocationInit$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        LocationInitContract.ViewInterface theView;
                        theView = LocationInitPresenter.this.getTheView();
                        if (theView != null) {
                            theView.locationCheckResult(isLocalizationSuccess);
                        }
                    }
                });
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.initialization_task.LocationInitContract.PresenterInterface
    public void reLoadLocation() {
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.initialization_task.LocationInitPresenter$reLoadLocation$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                SDK.INSTANCE.reloadLocalization();
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.initialization_task.LocationInitContract.PresenterInterface
    public boolean isLocation() {
        return MirSdkManager.INSTANCE.isLocated();
    }

    @Override // com.pudutech.bumblebee.presenter.initialization_task.LocationInitContract.PresenterInterface
    public LocateCase getLocateCase() {
        return SDK.INSTANCE.getLocateCase();
    }

    @Override // com.pudutech.bumblebee.presenter.initialization_task.LocationInitContract.PresenterInterface
    public List<String> getrelocatePoints() {
        return SDK.INSTANCE.getRelocationPoints();
    }

    @Override // com.pudutech.bumblebee.presenter.initialization_task.LocationInitContract.PresenterInterface
    public void addRelocationPoint(String relocatePoint) {
        Intrinsics.checkParameterIsNotNull(relocatePoint, "relocatePoint");
        SDK.INSTANCE.addRelocationPoint(relocatePoint);
    }
}
