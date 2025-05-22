package com.pudutech.bumblebee.presenter.battery_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.core_devices_task.battery.Battery;
import com.pudutech.bumblebee.business.core_devices_task.battery.PowerState;
import com.pudutech.bumblebee.business.core_devices_task.battery.SystemBatteryListener;
import com.pudutech.bumblebee.presenter.battery_task.BatteryContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseMultiViewPresenter;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BatteryPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000  2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0001 B\u0005¢\u0006\u0002\u0010\u0005J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0002H\u0016J\b\u0010\u0012\u001a\u00020\u000eH\u0016J\b\u0010\u0013\u001a\u00020\u0010H\u0016J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J$\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010\u001f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0002H\u0002R$\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@VX\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/battery_task/BatteryPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseMultiViewPresenter;", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract$PresenterInterface;", "Lcom/pudutech/bumblebee/business/core_devices_task/battery/SystemBatteryListener;", "()V", "<set-?>", "", "TAG", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "wasShowFirstWarning", "", "attachView", "", "view", "checkAllowedForMove", "clearFirstTimeWarning", "onPowerChange", "powerPercent", "", "onStateChange", "powerState", "Lcom/pudutech/bumblebee/business/core_devices_task/battery/PowerState;", "chargeState", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "show", "event", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract$ViewEvent;", "showWhenAttach", "Companion", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class BatteryPresenter extends BaseMultiViewPresenter<BatteryContract.ViewInterface> implements BatteryContract.PresenterInterface, SystemBatteryListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy battery$delegate = LazyKt.lazy(new Function0<Battery>() { // from class: com.pudutech.bumblebee.presenter.battery_task.BatteryPresenter$Companion$battery$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Battery invoke() {
            return CoreDevices.INSTANCE.getBattery();
        }
    });
    private String TAG = "BatteryPresenter";
    private boolean wasShowFirstWarning;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[PowerState.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[PowerState.EMPTY.ordinal()] = 1;
            $EnumSwitchMapping$0[PowerState.LOW_LEVEL1.ordinal()] = 2;
            $EnumSwitchMapping$0[PowerState.LOW_LEVEL2.ordinal()] = 3;
            $EnumSwitchMapping$0[PowerState.NORMAL.ordinal()] = 4;
            $EnumSwitchMapping$1 = new int[PowerState.values().length];
            $EnumSwitchMapping$1[PowerState.EMPTY.ordinal()] = 1;
            $EnumSwitchMapping$1[PowerState.LOW_LEVEL1.ordinal()] = 2;
            $EnumSwitchMapping$1[PowerState.LOW_LEVEL2.ordinal()] = 3;
            $EnumSwitchMapping$1[PowerState.NORMAL.ordinal()] = 4;
        }
    }

    public BatteryPresenter() {
        INSTANCE.getBattery().addListener(this);
    }

    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseMultiViewPresenter
    protected String getTAG() {
        return this.TAG;
    }

    public void setTAG(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.TAG = str;
    }

    /* compiled from: BatteryPresenter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/battery_task/BatteryPresenter$Companion;", "", "()V", "battery", "Lcom/pudutech/bumblebee/business/core_devices_task/battery/Battery;", "getBattery", "()Lcom/pudutech/bumblebee/business/core_devices_task/battery/Battery;", "battery$delegate", "Lkotlin/Lazy;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        /* JADX INFO: Access modifiers changed from: private */
        public final Battery getBattery() {
            Lazy lazy = BatteryPresenter.battery$delegate;
            Companion companion = BatteryPresenter.INSTANCE;
            return (Battery) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseMultiViewPresenter
    public void attachView(BatteryContract.ViewInterface view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.attachView((BatteryPresenter) view);
        showWhenAttach(view);
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract.PresenterInterface
    public boolean checkAllowedForMove() {
        return INSTANCE.getBattery().getPowerState() != PowerState.EMPTY;
    }

    @Override // com.pudutech.bumblebee.business.core_devices_task.battery.SystemBatteryListener
    public void onStateChange(int powerPercent, PowerState powerState, ChargeState chargeState) {
        Pdlog.m3275i(getTAG(), "onStateChange percent=" + powerPercent + "  state=" + powerState + " charge=" + chargeState);
        if (chargeState == ChargeState.ChargeFull) {
            show(BatteryContract.ViewEvent.CHARGE_FULL);
            return;
        }
        if (chargeState == ChargeState.Charging) {
            show(BatteryContract.ViewEvent.CHARGING);
            return;
        }
        if (chargeState == ChargeState.Idle) {
            show(BatteryContract.ViewEvent.CHARGER_DISCONNECT);
            return;
        }
        if (powerState == null) {
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[powerState.ordinal()];
        if (i == 1) {
            show(BatteryContract.ViewEvent.POWER_EMPTY);
            return;
        }
        if (i == 2) {
            if (!this.wasShowFirstWarning) {
                show(BatteryContract.ViewEvent.POWER_LOW_WARNING_FIRST_TIME);
                return;
            } else {
                show(BatteryContract.ViewEvent.POWER_LOW_NO_WARING);
                return;
            }
        }
        if (i == 3) {
            show(BatteryContract.ViewEvent.POWER_LOW_WARNING);
        } else {
            if (i != 4) {
                return;
            }
            this.wasShowFirstWarning = false;
            show(BatteryContract.ViewEvent.POWER_NORMAL);
        }
    }

    @Override // com.pudutech.bumblebee.business.core_devices_task.battery.SystemBatteryListener
    public void onPowerChange(final int powerPercent) {
        Pdlog.m3275i(getTAG(), "onPowerChange percent=" + powerPercent);
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.battery_task.BatteryPresenter$onPowerChange$1
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
                CopyOnWriteArrayList mViews;
                mViews = BatteryPresenter.this.getMViews();
                Iterator it = mViews.iterator();
                while (it.hasNext()) {
                    BatteryContract.ViewInterface viewInterface = (BatteryContract.ViewInterface) ((WeakReference) it.next()).get();
                    if (viewInterface != null) {
                        viewInterface.showBattery(powerPercent);
                    }
                }
            }
        });
    }

    private final void show(final BatteryContract.ViewEvent event) {
        Pdlog.m3273d(getTAG(), "show event=" + event + " when views size=" + getMViews().size());
        final int powerPercent = INSTANCE.getBattery().getPowerPercent();
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.battery_task.BatteryPresenter$show$1
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
                CopyOnWriteArrayList mViews;
                mViews = BatteryPresenter.this.getMViews();
                Iterator it = mViews.iterator();
                while (it.hasNext()) {
                    BatteryContract.ViewInterface viewInterface = (BatteryContract.ViewInterface) ((WeakReference) it.next()).get();
                    if (viewInterface != null) {
                        viewInterface.showBatteryEvent(event, powerPercent);
                    }
                }
            }
        });
    }

    private final void showWhenAttach(BatteryContract.ViewInterface view) {
        Pdlog.m3275i(getTAG(), "show when attach. powerState=" + INSTANCE.getBattery().getPowerState() + " chargerState=" + INSTANCE.getBattery().getChargerState());
        PowerState powerState = INSTANCE.getBattery().getPowerState();
        if (INSTANCE.getBattery().getChargerState() == ChargeState.Charging) {
            view.showBatteryEvent(BatteryContract.ViewEvent.CHARGING, INSTANCE.getBattery().getPowerPercent());
        } else if (INSTANCE.getBattery().getChargerState() == ChargeState.ChargeFull) {
            view.showBatteryEvent(BatteryContract.ViewEvent.CHARGE_FULL, INSTANCE.getBattery().getPowerPercent());
        } else {
            view.showBatteryEvent(BatteryContract.ViewEvent.CHARGER_DISCONNECT, INSTANCE.getBattery().getPowerPercent());
        }
        int i = WhenMappings.$EnumSwitchMapping$1[powerState.ordinal()];
        if (i == 1) {
            view.showBatteryEvent(BatteryContract.ViewEvent.POWER_EMPTY, INSTANCE.getBattery().getPowerPercent());
            return;
        }
        if (i == 2) {
            if (!this.wasShowFirstWarning) {
                show(BatteryContract.ViewEvent.POWER_LOW_WARNING_FIRST_TIME);
                return;
            } else {
                show(BatteryContract.ViewEvent.POWER_LOW_NO_WARING);
                return;
            }
        }
        if (i == 3) {
            show(BatteryContract.ViewEvent.POWER_LOW_WARNING);
        } else {
            if (i != 4) {
                return;
            }
            show(BatteryContract.ViewEvent.POWER_NORMAL);
        }
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract.PresenterInterface
    public void clearFirstTimeWarning() {
        Pdlog.m3275i(getTAG(), "clearFirstTimeWarning");
        if (INSTANCE.getBattery().getPowerState() == PowerState.LOW_LEVEL1 || INSTANCE.getBattery().getPowerState() == PowerState.LOW_LEVEL2 || INSTANCE.getBattery().getPowerState() == PowerState.EMPTY) {
            this.wasShowFirstWarning = true;
        }
    }
}
