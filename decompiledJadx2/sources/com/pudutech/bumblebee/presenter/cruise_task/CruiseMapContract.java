package com.pudutech.bumblebee.presenter.cruise_task;

import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.mvp_base.BaseViewInterface;
import com.pudutech.mirsdkwrap.lib.map.MapCruiseLine;
import com.pudutech.mirsdkwrap.lib.map.MapLine;
import com.pudutech.mirsdkwrap.lib.map.StayPoint;
import java.util.ArrayList;
import kotlin.Metadata;

/* compiled from: CruiseMapContract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/cruise_task/CruiseMapContract;", "", "()V", "PresenterInterface", "ViewInterface", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CruiseMapContract {

    /* compiled from: CruiseMapContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/cruise_task/CruiseMapContract$PresenterInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BasePresenterInterface;", "select", "", "index", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface PresenterInterface extends BasePresenterInterface {
        void select(int index);
    }

    /* compiled from: CruiseMapContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0004H&J \u0010\u0005\u001a\u00020\u00032\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tH&J@\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0007j\b\u0012\u0004\u0012\u00020\u0011`\tH&J0\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\b2\u0016\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00150\u0007j\b\u0012\u0004\u0012\u00020\u0015`\tH&¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/cruise_task/CruiseMapContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseViewInterface;", "hasStops", "", "", "showAll", "mapModels", "Ljava/util/ArrayList;", "Lcom/pudutech/mirsdkwrap/lib/map/MapCruiseLine;", "Lkotlin/collections/ArrayList;", "showBackGround", "maxX", "", "minX", "maxY", "minY", "model", "Lcom/pudutech/mirsdkwrap/lib/map/MapLine;", "showTheSelected", "selectedIndex", "stopDots", "Lcom/pudutech/mirsdkwrap/lib/map/StayPoint;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface ViewInterface extends BaseViewInterface {
        void hasStops(boolean hasStops);

        void showAll(ArrayList<MapCruiseLine> mapModels);

        void showBackGround(int maxX, int minX, int maxY, int minY, ArrayList<MapLine> model);

        void showTheSelected(int selectedIndex, MapCruiseLine model, ArrayList<StayPoint> stopDots);
    }
}
