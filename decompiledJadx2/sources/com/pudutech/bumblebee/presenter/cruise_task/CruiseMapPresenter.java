package com.pudutech.bumblebee.presenter.cruise_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.cruise_task.CruiseMapContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.mirsdkwrap.lib.map.MapCruiseLine;
import com.pudutech.mirsdkwrap.lib.map.StayPoint;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CruiseMapPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016J\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/cruise_task/CruiseMapPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/cruise_task/CruiseMapContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/cruise_task/CruiseMapContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "all", "Ljava/util/ArrayList;", "Lcom/pudutech/mirsdkwrap/lib/map/MapCruiseLine;", "Lkotlin/collections/ArrayList;", "replaceView", "", "view", "select", "index", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CruiseMapPresenter extends BaseOneViewPresenter<CruiseMapContract.ViewInterface> implements CruiseMapContract.PresenterInterface {
    private final String TAG = "CruiseMapPresenter";
    private ArrayList<MapCruiseLine> all = new ArrayList<>();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void replaceView(CruiseMapContract.ViewInterface view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.replaceView((CruiseMapPresenter) view);
        runOnBusinessThread(new CruiseMapPresenter$replaceView$1(this));
    }

    @Override // com.pudutech.bumblebee.presenter.cruise_task.CruiseMapContract.PresenterInterface
    public void select(final int index) {
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.cruise_task.CruiseMapPresenter$select$1
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
                ArrayList arrayList;
                ArrayList arrayList2;
                ArrayList arrayList3;
                int i = index;
                arrayList = CruiseMapPresenter.this.all;
                if (i <= arrayList.size()) {
                    arrayList2 = CruiseMapPresenter.this.all;
                    Object obj = arrayList2.get(index);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "all[index]");
                    final MapCruiseLine mapCruiseLine = (MapCruiseLine) obj;
                    final ArrayList<StayPoint> stayPoint = mapCruiseLine.getStayPoint();
                    if (stayPoint == null) {
                        stayPoint = new ArrayList<>();
                    }
                    CruiseMapPresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.cruise_task.CruiseMapPresenter$select$1.1
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
                            CruiseMapContract.ViewInterface theView2;
                            theView = CruiseMapPresenter.this.getTheView();
                            if (theView != null) {
                                theView.hasStops(!stayPoint.isEmpty());
                            }
                            theView2 = CruiseMapPresenter.this.getTheView();
                            if (theView2 != null) {
                                theView2.showTheSelected(index, mapCruiseLine, stayPoint);
                            }
                        }
                    });
                    return;
                }
                String tag = CruiseMapPresenter.this.getTAG();
                StringBuilder sb = new StringBuilder();
                sb.append("select=");
                sb.append(index);
                sb.append(" > all.size=");
                arrayList3 = CruiseMapPresenter.this.all;
                sb.append(arrayList3.size());
                Pdlog.m3277w(tag, sb.toString());
            }
        });
    }
}
