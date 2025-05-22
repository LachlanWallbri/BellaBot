package com.pudutech.bumblebee.presenter.cruise_task;

import com.pudutech.bumblebee.presenter.cruise_task.CruiseMapContract;
import com.pudutech.mirsdkwrap.lib.map.MapCruiseLine;
import com.pudutech.mirsdkwrap.lib.map.MapCruisePathLoader;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CruiseMapPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CruiseMapPresenter$replaceView$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ CruiseMapPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CruiseMapPresenter$replaceView$1(CruiseMapPresenter cruiseMapPresenter) {
        super(0);
        this.this$0 = cruiseMapPresenter;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CruiseMapPresenter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "it", "Lcom/pudutech/mirsdkwrap/lib/map/MapCruisePathLoader;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.bumblebee.presenter.cruise_task.CruiseMapPresenter$replaceView$1$1 */
    /* loaded from: classes4.dex */
    public static final class C40421 extends Lambda implements Function1<MapCruisePathLoader, Unit> {
        C40421() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(MapCruisePathLoader mapCruisePathLoader) {
            invoke2(mapCruisePathLoader);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final MapCruisePathLoader mapCruisePathLoader) {
            if (mapCruisePathLoader != null) {
                CruiseMapPresenter$replaceView$1.this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.cruise_task.CruiseMapPresenter$replaceView$1$1$$special$$inlined$let$lambda$1
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
                        CruiseMapContract.ViewInterface theView;
                        theView = CruiseMapPresenter$replaceView$1.this.this$0.getTheView();
                        if (theView != null) {
                            theView.showBackGround(MapCruisePathLoader.this.getMaxX(), MapCruisePathLoader.this.getMinX(), MapCruisePathLoader.this.getMaxY(), MapCruisePathLoader.this.getMinY(), MapCruisePathLoader.this.getMapLines());
                        }
                    }
                });
                CruiseMapPresenter$replaceView$1.this.this$0.all = mapCruisePathLoader.getCruiseLines();
                CruiseMapPresenter$replaceView$1.this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.cruise_task.CruiseMapPresenter$replaceView$1$1$$special$$inlined$let$lambda$2
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
                        CruiseMapContract.ViewInterface theView;
                        ArrayList<MapCruiseLine> arrayList;
                        theView = CruiseMapPresenter$replaceView$1.this.this$0.getTheView();
                        if (theView != null) {
                            arrayList = CruiseMapPresenter$replaceView$1.this.this$0.all;
                            theView.showAll(arrayList);
                        }
                    }
                });
            }
        }
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        RobotMapManager.INSTANCE.getCruisePath(new C40421());
    }
}
