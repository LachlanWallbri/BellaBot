package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.initialization_task.LocationInitContract;
import com.pudutech.bumblebee.presenter.initialization_task.LocationInitPresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: CheckLocationHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J)\u0010\u0013\u001a\u00020\f2!\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0007J\u0006\u0010\u0015\u001a\u00020\fJ\u0006\u0010\u0016\u001a\u00020\u0017J\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u0019J\u0006\u0010\u001a\u001a\u00020\bJ\u0010\u0010\u001b\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\bH\u0016J\u0006\u0010\u001c\u001a\u00020\fJ)\u0010\u001d\u001a\u00020\f2!\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R/\u0010\u0005\u001a#\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/CheckLocationHelper;", "Lcom/pudutech/bumblebee/presenter/initialization_task/LocationInitContract$ViewInterface;", "()V", "TAG", "", "locationInitDoneListener", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "b", "", "locationInitPresenter", "Lcom/pudutech/bumblebee/presenter/initialization_task/LocationInitPresenter;", "getLocationInitPresenter", "()Lcom/pudutech/bumblebee/presenter/initialization_task/LocationInitPresenter;", "locationInitPresenter$delegate", "Lkotlin/Lazy;", "addLocationInitDoneListener", "l", "checkLocationInitResult", "getLocateCase", "Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "getRelocatePoints", "", "isLocated", "locationCheckResult", "reCheckLocation", "removeLocationInitDoneListener", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CheckLocationHelper implements LocationInitContract.ViewInterface {
    public static final CheckLocationHelper INSTANCE;
    private static final String TAG;
    private static final CopyOnWriteArrayList<Function1<Boolean, Unit>> locationInitDoneListener;

    /* renamed from: locationInitPresenter$delegate, reason: from kotlin metadata */
    private static final Lazy locationInitPresenter;

    private final LocationInitPresenter getLocationInitPresenter() {
        return (LocationInitPresenter) locationInitPresenter.getValue();
    }

    static {
        CheckLocationHelper checkLocationHelper = new CheckLocationHelper();
        INSTANCE = checkLocationHelper;
        TAG = TAG;
        locationInitPresenter = LazyKt.lazy(new Function0<LocationInitPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.CheckLocationHelper$locationInitPresenter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LocationInitPresenter invoke() {
                LocationInitPresenter locationInitPresenter2;
                PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
                BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(LocationInitPresenter.class).toString());
                Pdlog.m3273d(presenterHolder.getTAG(), "popOrCreateIt " + Reflection.getOrCreateKotlinClass(LocationInitPresenter.class) + ' ' + basePresenterInterface);
                if (basePresenterInterface == null) {
                    locationInitPresenter2 = new LocationInitPresenter();
                } else {
                    presenterHolder.getBox().remove(Reflection.getOrCreateKotlinClass(LocationInitPresenter.class).toString());
                    if (!(basePresenterInterface instanceof LocationInitPresenter)) {
                        basePresenterInterface = null;
                    }
                    locationInitPresenter2 = (LocationInitPresenter) basePresenterInterface;
                }
                if (locationInitPresenter2 == null) {
                    Intrinsics.throwNpe();
                }
                return locationInitPresenter2;
            }
        });
        locationInitDoneListener = new CopyOnWriteArrayList<>();
        checkLocationHelper.getLocationInitPresenter().replaceView(checkLocationHelper);
    }

    private CheckLocationHelper() {
    }

    public final void addLocationInitDoneListener(Function1<? super Boolean, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        if (locationInitDoneListener.contains(l)) {
            return;
        }
        locationInitDoneListener.add(l);
    }

    public final void removeLocationInitDoneListener(Function1<? super Boolean, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        locationInitDoneListener.remove(l);
    }

    @Override // com.pudutech.bumblebee.presenter.initialization_task.LocationInitContract.ViewInterface
    public void locationCheckResult(boolean b) {
        Pdlog.m3273d(TAG, "locationCheckResult : b = " + b + "; ");
        Iterator<T> it = locationInitDoneListener.iterator();
        while (it.hasNext()) {
            ((Function1) it.next()).invoke(Boolean.valueOf(b));
        }
    }

    public final LocateCase getLocateCase() {
        return getLocationInitPresenter().getLocateCase();
    }

    public final void reCheckLocation() {
        getLocationInitPresenter().reLoadLocation();
    }

    public final boolean isLocated() {
        return getLocationInitPresenter().isLocation();
    }

    public final void checkLocationInitResult() {
        getLocationInitPresenter().checkLocationInit();
    }

    public final List<String> getRelocatePoints() {
        return getLocationInitPresenter().getrelocatePoints();
    }
}
