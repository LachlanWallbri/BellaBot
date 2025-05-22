package com.pudutech.bumblebee.presenter.map_switch_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract;
import com.pudutech.bumblebee.presenter.utils.WifiUtil;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.remotemaintenance.config.IoTConfig;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: MapSwitchPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/bumblebee/presenter/map_switch_task/MapSwitchPresenter$switchResult$1", "Lcom/pudutech/mirsdkwrap/lib/map/RobotMapManager$SwitchMapResultListener;", "onResult", "", "b", "", IoTConfig.PARAM_ERROR_MSG, "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MapSwitchPresenter$switchResult$1 implements RobotMapManager.SwitchMapResultListener {
    final /* synthetic */ MapSwitchPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MapSwitchPresenter$switchResult$1(MapSwitchPresenter mapSwitchPresenter) {
        this.this$0 = mapSwitchPresenter;
    }

    @Override // com.pudutech.mirsdkwrap.lib.map.RobotMapManager.SwitchMapResultListener
    public void onResult(boolean b, final String errorMsg) {
        Pdlog.m3273d(this.this$0.getTAG(), "onResult: b=" + b + "  errorMsg=" + errorMsg);
        if (!b) {
            this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchPresenter$switchResult$1$onResult$1
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
                    MapSwitchContract.ViewInterface theView;
                    theView = MapSwitchPresenter$switchResult$1.this.this$0.getTheView();
                    if (theView != null) {
                        theView.mo4294showSwitchErrorolJ16Uo(errorMsg, UByte.m4522boximpl(UByte.m4528constructorimpl((byte) (-1))), "");
                    }
                }
            });
        }
        String mac = WifiUtil.INSTANCE.getMac();
        if (mac != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new MapSwitchPresenter$switchResult$1$onResult$$inlined$let$lambda$1(mac, null, this), 3, null);
        }
        this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchPresenter$switchResult$1$onResult$3
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
                MapSwitchContract.ViewInterface theView;
                theView = MapSwitchPresenter$switchResult$1.this.this$0.getTheView();
                if (theView != null) {
                    theView.showChosen(MapSwitchContract.Type.MAP, MapSwitchPresenter$switchResult$1.this.this$0.getMapChosen());
                }
            }
        });
        final ArrayList<String> dinningOutLets = RobotMapManager.INSTANCE.getDinningOutLets();
        this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchPresenter$switchResult$1$onResult$4
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
                MapSwitchContract.ViewInterface theView;
                theView = MapSwitchPresenter$switchResult$1.this.this$0.getTheView();
                if (theView != null) {
                    theView.showAll(MapSwitchContract.Type.DINING_LET_OUT, dinningOutLets);
                }
            }
        });
        final ArrayList<String> ushers = RobotMapManager.INSTANCE.getUshers();
        this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchPresenter$switchResult$1$onResult$5
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
                MapSwitchContract.ViewInterface theView;
                theView = MapSwitchPresenter$switchResult$1.this.this$0.getTheView();
                if (theView != null) {
                    theView.showAll(MapSwitchContract.Type.USHER, ushers);
                }
            }
        });
        this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchPresenter$switchResult$1$onResult$6
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
                MapSwitchContract.ViewInterface theView;
                theView = MapSwitchPresenter$switchResult$1.this.this$0.getTheView();
                if (theView != null) {
                    theView.showChosen(MapSwitchContract.Type.DINING_LET_OUT, MapSwitchPresenter$switchResult$1.this.this$0.getDiningOutLetChosen());
                }
            }
        });
        this.this$0.notifyMapDrawPath();
    }
}
