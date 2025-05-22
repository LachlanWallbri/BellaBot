package com.pudutech.bumblebee.presenter.map_switch_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MapSwitchPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u000e\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0006H\u0016J\b\u0010\u0017\u001a\u00020\u0013H\u0016J\b\u0010\u0018\u001a\u00020\u0013H\u0002J\b\u0010\u0019\u001a\u00020\u0013H\u0014R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\u0004\u0018\u00010\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0016\u0010\u000b\u001a\u0004\u0018\u00010\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\bR\u0010\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\b¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/map_switch_task/MapSwitchPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapSwitchContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapSwitchContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "diningOutLetChosen", "getDiningOutLetChosen", "mapChosen", "getMapChosen", "switchResult", "com/pudutech/bumblebee/presenter/map_switch_task/MapSwitchPresenter$switchResult$1", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapSwitchPresenter$switchResult$1;", "usherChosen", "getUsherChosen", "actionChoose", "", "type", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapSwitchContract$Type;", "name", "loadMap", "notifyMapDrawPath", "onViewRemoved", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MapSwitchPresenter extends BaseOneViewPresenter<MapSwitchContract.ViewInterface> implements MapSwitchContract.PresenterInterface {
    private final String TAG = "MapSwitchPresenter";
    private final MapSwitchPresenter$switchResult$1 switchResult = new MapSwitchPresenter$switchResult$1(this);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract.PresenterInterface
    public String getMapChosen() {
        return RobotMapManager.INSTANCE.getDefaultPdmap();
    }

    @Override // com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract.PresenterInterface
    public String getDiningOutLetChosen() {
        return RobotMapManager.INSTANCE.getCurrentMapDiningOutLetChosen();
    }

    @Override // com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract.PresenterInterface
    public String getUsherChosen() {
        return RobotMapManager.INSTANCE.getCurrentMapUsherChosen();
    }

    @Override // com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract.PresenterInterface
    public void loadMap() {
        runOnBusinessThread(new MapSwitchPresenter$loadMap$1(this));
        RobotMapManager.INSTANCE.addSwitchMapResultListener(this.switchResult);
    }

    @Override // com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract.PresenterInterface
    public void actionChoose(final MapSwitchContract.Type type, final String name) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3275i(getTAG(), "actionChoose " + type + ' ' + name);
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchPresenter$actionChoose$1
            /* JADX INFO: Access modifiers changed from: package-private */
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
                if (type == MapSwitchContract.Type.MAP) {
                    RobotMapManager.INSTANCE.switchMap("0", name);
                }
                if (type == MapSwitchContract.Type.DINING_LET_OUT && RobotMapManager.INSTANCE.setCurrentMapDiningOutLetChosen(name)) {
                    MapSwitchPresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchPresenter$actionChoose$1.1
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
                            MapSwitchContract.ViewInterface theView;
                            theView = MapSwitchPresenter.this.getTheView();
                            if (theView != null) {
                                theView.showChosen(MapSwitchContract.Type.DINING_LET_OUT, name);
                            }
                        }
                    });
                }
                if (type == MapSwitchContract.Type.USHER && RobotMapManager.INSTANCE.setCurrentMapUsherChosen(name)) {
                    MapSwitchPresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchPresenter$actionChoose$1.2
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
                            MapSwitchContract.ViewInterface theView;
                            theView = MapSwitchPresenter.this.getTheView();
                            if (theView != null) {
                                theView.showChosen(MapSwitchContract.Type.USHER, name);
                            }
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewRemoved() {
        super.onViewRemoved();
        RobotMapManager.INSTANCE.removeSwitchMapResultListener(this.switchResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyMapDrawPath() {
        RobotMapManager robotMapManager = RobotMapManager.INSTANCE;
        String mapChosen = getMapChosen();
        if (mapChosen == null) {
            mapChosen = "";
        }
        robotMapManager.getMapDrawPath(mapChosen, new MapSwitchPresenter$notifyMapDrawPath$1(this));
    }
}
