package com.pudutech.bumblebee.presenter.map_switch_task;

import com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract;
import com.pudutech.mirsdkwrap.lib.map.MapDrawPathLoader;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: MapSwitchPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "it", "Lcom/pudutech/mirsdkwrap/lib/map/MapDrawPathLoader;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MapSwitchPresenter$notifyMapDrawPath$1 extends Lambda implements Function1<MapDrawPathLoader, Unit> {
    final /* synthetic */ MapSwitchPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapSwitchPresenter$notifyMapDrawPath$1(MapSwitchPresenter mapSwitchPresenter) {
        super(1);
        this.this$0 = mapSwitchPresenter;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(MapDrawPathLoader mapDrawPathLoader) {
        invoke2(mapDrawPathLoader);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(final MapDrawPathLoader mapDrawPathLoader) {
        if (mapDrawPathLoader != null) {
            this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchPresenter$notifyMapDrawPath$1$$special$$inlined$let$lambda$1
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
                    theView = this.this$0.getTheView();
                    if (theView != null) {
                        theView.showBackGround(MapDrawPathLoader.this.getMaxX(), MapDrawPathLoader.this.getMinX(), MapDrawPathLoader.this.getMaxY(), MapDrawPathLoader.this.getMinY(), MapDrawPathLoader.this.getMapLines());
                    }
                }
            });
        }
    }
}
