package com.pudutech.bumblebee.robot_ui.manager;

import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.battery_task.BatteryContract2;
import com.pudutech.bumblebee.presenter.battery_task.BatteryPresenter2;
import com.pudutech.robot.module.report.TrackingReportManager;
import com.pudutech.robot.module.report.track2.BaseBatteryChargeConfig;
import com.pudutech.robot.module.report.track2.BatteryChargeTask;
import com.pudutech.robot.module.report.track2.ChargeKt;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BatteryInfoManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0001J\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u000eJ\u0018\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0006j\b\u0012\u0004\u0012\u00020\u0001`\bH\u0002J\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0011J\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u0011J\u000e\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0001J\u0010\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u0015H\u0016J\b\u0010\u001f\u001a\u00020\u000eH\u0016J\u0010\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u0018H\u0016J\u0010\u0010\"\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u001aH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R*\u0010\u0005\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00070\u0006j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/BatteryInfoManager;", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ViewInterface;", "()V", "TAG", "", "batteryChangeNotifyListeners", "Ljava/util/ArrayList;", "Ljava/lang/ref/WeakReference;", "Lkotlin/collections/ArrayList;", "batteryPresenter", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryPresenter2;", "trackTask", "Lcom/pudutech/robot/module/report/track2/BatteryChargeTask;", "addBatteryChangeNotifyListeners", "", "listener", "checkAllowedForMove", "", "clearFirstTimeWarning", "getActiveListeners", "getChargerEvent", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ChargerModel;", "getNeedShowLowPowerNotify", "getPower", "", "getPowerEvent", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$PowerModel;", "isCharging", "removeBatteryChangeNotifyListeners", "showChargerEvent", "model", "showLowerNotify", "showPowerChange", "i", "showPowerEvent", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class BatteryInfoManager implements BatteryContract2.ViewInterface {
    private static BatteryChargeTask trackTask;
    public static final BatteryInfoManager INSTANCE = new BatteryInfoManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final BatteryPresenter2 batteryPresenter = new BatteryPresenter2();
    private static final ArrayList<WeakReference<BatteryContract2.ViewInterface>> batteryChangeNotifyListeners = new ArrayList<>();

    static {
        batteryPresenter.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager.1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                BatteryInfoManager.access$getBatteryPresenter$p(BatteryInfoManager.INSTANCE).replaceView(BatteryInfoManager.INSTANCE);
            }
        });
    }

    private BatteryInfoManager() {
    }

    public static final /* synthetic */ BatteryPresenter2 access$getBatteryPresenter$p(BatteryInfoManager batteryInfoManager) {
        return batteryPresenter;
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showChargerEvent(BatteryContract2.ChargerModel model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Pdlog.m3273d(TAG, "showChargerEvent : model = " + model + "; ");
        if (model.isCharging()) {
            trackTask = ChargeKt.newBatteryChargeTask(TrackingReportManager.INSTANCE, new BaseBatteryChargeConfig.PeanutBatteryChargeConfig("Line"));
            BatteryChargeTask batteryChargeTask = trackTask;
            if (batteryChargeTask != null) {
                batteryChargeTask.onStart(String.valueOf(getPower()));
            }
        } else {
            BatteryChargeTask batteryChargeTask2 = trackTask;
            if (batteryChargeTask2 != null) {
                batteryChargeTask2.onStop(String.valueOf(getPower()));
            }
        }
        Iterator<T> it = getActiveListeners().iterator();
        while (it.hasNext()) {
            ((BatteryContract2.ViewInterface) it.next()).showChargerEvent(model);
        }
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showLowerNotify() {
        Iterator<T> it = getActiveListeners().iterator();
        while (it.hasNext()) {
            ((BatteryContract2.ViewInterface) it.next()).showLowerNotify();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ArrayList<BatteryContract2.ViewInterface> getActiveListeners() {
        ArrayList<BatteryContract2.ViewInterface> arrayList = new ArrayList<>();
        Iterator<WeakReference<BatteryContract2.ViewInterface>> it = batteryChangeNotifyListeners.iterator();
        Intrinsics.checkExpressionValueIsNotNull(it, "batteryChangeNotifyListeners.iterator()");
        while (it.hasNext()) {
            Object obj = (BatteryContract2.ViewInterface) it.next().get();
            if (obj != null) {
                if (obj instanceof AppCompatActivity) {
                    AppCompatActivity appCompatActivity = (AppCompatActivity) obj;
                    if (appCompatActivity.isFinishing() || appCompatActivity.isDestroyed()) {
                        it.remove();
                    } else {
                        arrayList.add(obj);
                    }
                } else {
                    arrayList.add(obj);
                }
            } else {
                it.remove();
            }
        }
        return arrayList;
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerChange(int i) {
        Pdlog.m3273d(TAG, "showPowerChange : i = " + i + "; ");
        ChargeKt.onChargeChange(TrackingReportManager.INSTANCE, i);
        Iterator<T> it = getActiveListeners().iterator();
        while (it.hasNext()) {
            ((BatteryContract2.ViewInterface) it.next()).showPowerChange(i);
        }
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerEvent(BatteryContract2.PowerModel model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Pdlog.m3273d(TAG, "showPowerEvent : model = " + model + "; ");
        Iterator<T> it = getActiveListeners().iterator();
        while (it.hasNext()) {
            ((BatteryContract2.ViewInterface) it.next()).showPowerEvent(model);
        }
    }

    public final void addBatteryChangeNotifyListeners(BatteryContract2.ViewInterface listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Iterator<WeakReference<BatteryContract2.ViewInterface>> it = batteryChangeNotifyListeners.iterator();
        Intrinsics.checkExpressionValueIsNotNull(it, "batteryChangeNotifyListeners.iterator()");
        while (it.hasNext()) {
            WeakReference<BatteryContract2.ViewInterface> next = it.next();
            Intrinsics.checkExpressionValueIsNotNull(next, "iterator.next()");
            BatteryContract2.ViewInterface viewInterface = next.get();
            if (viewInterface == null) {
                Pdlog.m3274e(TAG, "addLocationResultNotifyListeners : has null listener ????");
                it.remove();
            } else if (Intrinsics.areEqual(viewInterface, listener)) {
                return;
            }
        }
        batteryChangeNotifyListeners.add(new WeakReference<>(listener));
    }

    public final void removeBatteryChangeNotifyListeners(BatteryContract2.ViewInterface listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Iterator<WeakReference<BatteryContract2.ViewInterface>> it = batteryChangeNotifyListeners.iterator();
        Intrinsics.checkExpressionValueIsNotNull(it, "batteryChangeNotifyListeners.iterator()");
        while (it.hasNext()) {
            WeakReference<BatteryContract2.ViewInterface> next = it.next();
            Intrinsics.checkExpressionValueIsNotNull(next, "iterator.next()");
            BatteryContract2.ViewInterface viewInterface = next.get();
            if (viewInterface == null) {
                Pdlog.m3274e(TAG, "addLocationResultNotifyListeners : has null listener ????");
                it.remove();
            } else if (Intrinsics.areEqual(viewInterface, listener)) {
                it.remove();
            }
        }
    }

    public final int getPower() {
        return batteryPresenter.getMPowerModel().getPowerPercent();
    }

    public final boolean getNeedShowLowPowerNotify() {
        return batteryPresenter.getShowLowerNotify();
    }

    public final boolean isCharging() {
        return batteryPresenter.getMChargerModel().isCharging();
    }

    public final BatteryContract2.ChargerModel getChargerEvent() {
        return batteryPresenter.getMChargerModel();
    }

    public final BatteryContract2.PowerModel getPowerEvent() {
        return batteryPresenter.getMPowerModel();
    }

    public final boolean checkAllowedForMove() {
        return batteryPresenter.checkAllowedForMove();
    }

    public final void clearFirstTimeWarning() {
        batteryPresenter.clearFirstTimeWarning();
    }
}
