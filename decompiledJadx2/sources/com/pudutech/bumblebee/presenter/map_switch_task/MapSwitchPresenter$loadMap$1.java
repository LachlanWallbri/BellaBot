package com.pudutech.bumblebee.presenter.map_switch_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MapSwitchPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MapSwitchPresenter$loadMap$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ MapSwitchPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapSwitchPresenter$loadMap$1(MapSwitchPresenter mapSwitchPresenter) {
        super(0);
        this.this$0 = mapSwitchPresenter;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        RobotMapManager.INSTANCE.getMaps(new Function1<ArrayList<String>, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchPresenter$loadMap$1.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ArrayList<String> arrayList) {
                invoke2(arrayList);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final ArrayList<String> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Pdlog.m3275i(MapSwitchPresenter$loadMap$1.this.this$0.getTAG(), "loadMap. size=" + it.size());
                MapSwitchPresenter$loadMap$1.this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchPresenter.loadMap.1.1.1
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
                        theView = MapSwitchPresenter$loadMap$1.this.this$0.getTheView();
                        if (theView != null) {
                            theView.showAll(MapSwitchContract.Type.MAP, it);
                        }
                    }
                });
            }
        });
        final ArrayList<String> dinningOutLets = RobotMapManager.INSTANCE.getDinningOutLets();
        this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchPresenter$loadMap$1.2
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
                theView = MapSwitchPresenter$loadMap$1.this.this$0.getTheView();
                if (theView != null) {
                    theView.showAll(MapSwitchContract.Type.DINING_LET_OUT, dinningOutLets);
                }
            }
        });
        final ArrayList<String> ushers = RobotMapManager.INSTANCE.getUshers();
        this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchPresenter$loadMap$1.3
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
                theView = MapSwitchPresenter$loadMap$1.this.this$0.getTheView();
                if (theView != null) {
                    theView.showAll(MapSwitchContract.Type.USHER, ushers);
                }
            }
        });
        Pdlog.m3273d(this.this$0.getTAG(), "loadMap diningOutLet = " + dinningOutLets + " ; usher = " + ushers);
    }
}
