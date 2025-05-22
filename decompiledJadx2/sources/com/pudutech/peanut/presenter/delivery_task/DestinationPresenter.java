package com.pudutech.peanut.presenter.delivery_task;

import com.pudutech.mirsdkwrap.lib.map.Destination;
import com.pudutech.mirsdkwrap.lib.map.DestinationType;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.peanut.presenter.delivery_task.DestinationContract;
import com.pudutech.peanut.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.resources.resource.ResUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DestinationPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/delivery_task/DestinationPresenter;", "Lcom/pudutech/peanut/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/peanut/presenter/delivery_task/DestinationContract$ViewInterface;", "Lcom/pudutech/peanut/presenter/delivery_task/DestinationContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "loadMap", "", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class DestinationPresenter extends BaseOneViewPresenter<DestinationContract.ViewInterface> implements DestinationContract.PresenterInterface {
    private final String TAG = "DestinationPresenter";

    @Override // com.pudutech.peanut.presenter.mvp_base.BaseOneViewPresenter
    protected String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.peanut.presenter.delivery_task.DestinationContract.PresenterInterface
    public void loadMap() {
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.peanut.presenter.delivery_task.DestinationPresenter$loadMap$1
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
                ArrayList<Destination> destinationByTypes = RobotMapManager.INSTANCE.getDestinationByTypes(CollectionsKt.arrayListOf(DestinationType.TABLE, DestinationType.UNKNOWN));
                final ArrayList arrayList = new ArrayList();
                final LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Destination destination : destinationByTypes) {
                    arrayList.add(destination.getName());
                    if (!linkedHashMap.containsKey(destination.getGroup())) {
                        if (destination.getGroup().length() > 0) {
                            ArrayList arrayList2 = new ArrayList();
                            arrayList2.add(destination.getName());
                            linkedHashMap.put(destination.getGroup(), arrayList2);
                        }
                    }
                    if (destination.getGroup().length() > 0) {
                        Object obj = linkedHashMap.get(destination.getGroup());
                        if (obj == null) {
                            Intrinsics.throwNpe();
                        }
                        ((ArrayList) obj).add(destination.getName());
                    }
                }
                DestinationPresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.peanut.presenter.delivery_task.DestinationPresenter$loadMap$1.2
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
                        DestinationContract.ViewInterface theView;
                        theView = DestinationPresenter.this.getTheView();
                        if (theView != null) {
                            theView.showAll(DestinationContract.Type.TABLE, arrayList);
                        }
                    }
                });
                DestinationPresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.peanut.presenter.delivery_task.DestinationPresenter$loadMap$1.3
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
                        DestinationContract.ViewInterface theView;
                        theView = DestinationPresenter.this.getTheView();
                        if (theView != null) {
                            theView.showAllGroups(DestinationContract.Type.TABLE, linkedHashMap);
                        }
                    }
                });
                ArrayList<Destination> destinationByType = RobotMapManager.INSTANCE.getDestinationByType(DestinationType.DINING_OUTLET);
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(destinationByType, 10));
                Iterator<T> it = destinationByType.iterator();
                while (it.hasNext()) {
                    arrayList3.add(((Destination) it.next()).getName());
                }
                final ArrayList arrayList4 = arrayList3;
                final LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                linkedHashMap2.put(ResUtil.INSTANCE.getString("pdStr2_22"), new ArrayList(arrayList4));
                DestinationPresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.peanut.presenter.delivery_task.DestinationPresenter$loadMap$1.4
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
                        DestinationContract.ViewInterface theView;
                        theView = DestinationPresenter.this.getTheView();
                        if (theView != null) {
                            theView.showAll(DestinationContract.Type.DINING_OUTLET, arrayList4);
                        }
                    }
                });
                DestinationPresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.peanut.presenter.delivery_task.DestinationPresenter$loadMap$1.5
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
                        DestinationContract.ViewInterface theView;
                        theView = DestinationPresenter.this.getTheView();
                        if (theView != null) {
                            theView.showAllGroups(DestinationContract.Type.DINING_OUTLET, linkedHashMap2);
                        }
                    }
                });
                ArrayList<Destination> destinationByType2 = RobotMapManager.INSTANCE.getDestinationByType(DestinationType.TRANSLIST);
                ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(destinationByType2, 10));
                Iterator<T> it2 = destinationByType2.iterator();
                while (it2.hasNext()) {
                    arrayList5.add(((Destination) it2.next()).getName());
                }
                final ArrayList arrayList6 = arrayList5;
                final LinkedHashMap linkedHashMap3 = new LinkedHashMap();
                linkedHashMap3.put(ResUtil.INSTANCE.getString("pdStr2_23"), new ArrayList(arrayList6));
                DestinationPresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.peanut.presenter.delivery_task.DestinationPresenter$loadMap$1.6
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
                        DestinationContract.ViewInterface theView;
                        theView = DestinationPresenter.this.getTheView();
                        if (theView != null) {
                            theView.showAll(DestinationContract.Type.TRANSIT, arrayList6);
                        }
                    }
                });
                DestinationPresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.peanut.presenter.delivery_task.DestinationPresenter$loadMap$1.7
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
                        DestinationContract.ViewInterface theView;
                        theView = DestinationPresenter.this.getTheView();
                        if (theView != null) {
                            theView.showAllGroups(DestinationContract.Type.TRANSIT, linkedHashMap3);
                        }
                    }
                });
                ArrayList<Destination> destinationByType3 = RobotMapManager.INSTANCE.getDestinationByType(DestinationType.DISHWASHING);
                ArrayList arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(destinationByType3, 10));
                Iterator<T> it3 = destinationByType3.iterator();
                while (it3.hasNext()) {
                    arrayList7.add(((Destination) it3.next()).getName());
                }
                final ArrayList arrayList8 = arrayList7;
                final LinkedHashMap linkedHashMap4 = new LinkedHashMap();
                linkedHashMap4.put(ResUtil.INSTANCE.getString("pdStr2_24"), new ArrayList(arrayList8));
                DestinationPresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.peanut.presenter.delivery_task.DestinationPresenter$loadMap$1.8
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
                        DestinationContract.ViewInterface theView;
                        theView = DestinationPresenter.this.getTheView();
                        if (theView != null) {
                            theView.showAll(DestinationContract.Type.DISHWASHING_ROOM, arrayList8);
                        }
                    }
                });
                DestinationPresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.peanut.presenter.delivery_task.DestinationPresenter$loadMap$1.9
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
                        DestinationContract.ViewInterface theView;
                        theView = DestinationPresenter.this.getTheView();
                        if (theView != null) {
                            theView.showAllGroups(DestinationContract.Type.DISHWASHING_ROOM, linkedHashMap4);
                        }
                    }
                });
            }
        });
    }
}
