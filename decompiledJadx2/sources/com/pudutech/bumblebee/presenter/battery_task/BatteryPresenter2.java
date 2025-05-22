package com.pudutech.bumblebee.presenter.battery_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.core_devices_task.battery.Battery;
import com.pudutech.bumblebee.business.core_devices_task.battery.PowerState;
import com.pudutech.bumblebee.business.core_devices_task.battery.SystemBatteryListener;
import com.pudutech.bumblebee.presenter.battery_task.BatteryContract2;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BatteryPresenter2.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 )2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0001)B\u0005¢\u0006\u0002\u0010\u0005J\u0012\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0002J\b\u0010 \u001a\u00020\u0018H\u0016J\b\u0010!\u001a\u00020\u001dH\u0016J\u0010\u0010\"\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020$H\u0016J$\u0010%\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020$2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u001a\u0010(\u001a\u00020\u001d2\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010#\u001a\u00020$H\u0002R$\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@VX\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/battery_task/BatteryPresenter2;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$PresenterInterface;", "Lcom/pudutech/bumblebee/business/core_devices_task/battery/SystemBatteryListener;", "()V", "<set-?>", "", "TAG", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "chargerModel", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ChargerModel;", "getChargerModel", "()Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ChargerModel;", "mChargerModel", "mPowerModel", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$PowerModel;", "powerModel", "getPowerModel", "()Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$PowerModel;", "showLowerNotify", "", "getShowLowerNotify", "()Z", "wasShowFirstWarning", "chargerFSM", "", "chargeState", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "checkAllowedForMove", "clearFirstTimeWarning", "onPowerChange", "powerPercent", "", "onStateChange", "powerState", "Lcom/pudutech/bumblebee/business/core_devices_task/battery/PowerState;", "powerFSM", "Companion", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class BatteryPresenter2 extends BaseOneViewPresenter<BatteryContract2.ViewInterface> implements BatteryContract2.PresenterInterface, SystemBatteryListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy battery$delegate = LazyKt.lazy(new Function0<Battery>() { // from class: com.pudutech.bumblebee.presenter.battery_task.BatteryPresenter2$Companion$battery$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Battery invoke() {
            return CoreDevices.INSTANCE.getBattery();
        }
    });
    private boolean wasShowFirstWarning;
    private String TAG = "BatteryPresenter2";
    private BatteryContract2.PowerModel mPowerModel = new BatteryContract2.PowerModel(BatteryContract2.ViewEvent.POWER_NORMAL, 0);
    private BatteryContract2.ChargerModel mChargerModel = new BatteryContract2.ChargerModel(BatteryContract2.ViewEvent.CHARGER_DISCONNECT, false, 0);

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[ChargeState.ChargeFull.ordinal()] = 1;
            $EnumSwitchMapping$0[ChargeState.Charging.ordinal()] = 2;
            $EnumSwitchMapping$0[ChargeState.Idle.ordinal()] = 3;
            $EnumSwitchMapping$0[ChargeState.ChargeErrorContact.ordinal()] = 4;
            $EnumSwitchMapping$0[ChargeState.ChargeErrorElectric.ordinal()] = 5;
            $EnumSwitchMapping$0[ChargeState.ErrorBatteryPackComm.ordinal()] = 6;
            $EnumSwitchMapping$0[ChargeState.ErrorOverVolt.ordinal()] = 7;
            $EnumSwitchMapping$0[ChargeState.ErrorOverElectric.ordinal()] = 8;
            $EnumSwitchMapping$0[ChargeState.ErrorOverTemperature.ordinal()] = 9;
            $EnumSwitchMapping$0[ChargeState.ErrorOverTime.ordinal()] = 10;
            $EnumSwitchMapping$1 = new int[PowerState.values().length];
            $EnumSwitchMapping$1[PowerState.EMPTY.ordinal()] = 1;
            $EnumSwitchMapping$1[PowerState.LOW_LEVEL1.ordinal()] = 2;
            $EnumSwitchMapping$1[PowerState.LOW_LEVEL2.ordinal()] = 3;
            $EnumSwitchMapping$1[PowerState.NORMAL.ordinal()] = 4;
        }
    }

    public BatteryPresenter2() {
        INSTANCE.getBattery().addListener(this);
        powerFSM(INSTANCE.getBattery().getPowerState(), INSTANCE.getBattery().getPowerPercent());
        chargerFSM(INSTANCE.getBattery().getChargerState());
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.PresenterInterface
    /* renamed from: getPowerModel, reason: from getter */
    public BatteryContract2.PowerModel getMPowerModel() {
        return this.mPowerModel;
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.PresenterInterface
    /* renamed from: getChargerModel, reason: from getter */
    public BatteryContract2.ChargerModel getMChargerModel() {
        return this.mChargerModel;
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.PresenterInterface
    public boolean getShowLowerNotify() {
        return (this.wasShowFirstWarning || this.mPowerModel.getEvent() == BatteryContract2.ViewEvent.POWER_NORMAL) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    public void setTAG(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.TAG = str;
    }

    /* compiled from: BatteryPresenter2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/battery_task/BatteryPresenter2$Companion;", "", "()V", "battery", "Lcom/pudutech/bumblebee/business/core_devices_task/battery/Battery;", "getBattery", "()Lcom/pudutech/bumblebee/business/core_devices_task/battery/Battery;", "battery$delegate", "Lkotlin/Lazy;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        /* JADX INFO: Access modifiers changed from: private */
        public final Battery getBattery() {
            Lazy lazy = BatteryPresenter2.battery$delegate;
            Companion companion = BatteryPresenter2.INSTANCE;
            return (Battery) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.PresenterInterface
    public boolean checkAllowedForMove() {
        return INSTANCE.getBattery().getPowerState() != PowerState.EMPTY;
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.PresenterInterface
    public void clearFirstTimeWarning() {
        Pdlog.m3275i(getTAG(), "clearFirstTimeWarning");
        if (INSTANCE.getBattery().getPowerState() == PowerState.LOW_LEVEL1 || INSTANCE.getBattery().getPowerState() == PowerState.LOW_LEVEL2 || INSTANCE.getBattery().getPowerState() == PowerState.EMPTY) {
            this.wasShowFirstWarning = true;
            powerFSM(INSTANCE.getBattery().getPowerState(), INSTANCE.getBattery().getPowerPercent());
        }
    }

    @Override // com.pudutech.bumblebee.business.core_devices_task.battery.SystemBatteryListener
    public void onStateChange(int powerPercent, PowerState powerState, final ChargeState chargeState) {
        Pdlog.m3275i(getTAG(), "onStateChange percent=" + powerPercent + "  state=" + powerState + " charge=" + chargeState + " old=" + this.mChargerModel + " view=" + getTheView());
        powerFSM(powerState, powerPercent);
        if (powerState != null) {
            final BatteryContract2.PowerModel powerModel = this.mPowerModel;
            this.wasShowFirstWarning = false;
            runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.battery_task.BatteryPresenter2$onStateChange$$inlined$let$lambda$1
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

                /* JADX WARN: Code restructure failed: missing block: B:5:0x0016, code lost:
                
                    r0 = r2.getTheView();
                 */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final void invoke2() {
                    boolean z;
                    BatteryContract2.ViewInterface theView;
                    BatteryContract2.ViewInterface theView2;
                    BatteryContract2.ViewInterface theView3;
                    z = this.wasShowFirstWarning;
                    if (!z && BatteryPresenter2.INSTANCE.getBattery().getPowerState() != PowerState.NORMAL && theView3 != null) {
                        theView3.showLowerNotify();
                    }
                    String tag = this.getTAG();
                    StringBuilder sb = new StringBuilder();
                    sb.append("show power event on UIThread mView=");
                    theView = this.getTheView();
                    sb.append(theView);
                    Pdlog.m3275i(tag, sb.toString());
                    theView2 = this.getTheView();
                    if (theView2 != null) {
                        theView2.showPowerEvent(BatteryContract2.PowerModel.this);
                    }
                }
            });
        }
        if (chargeState != null) {
            chargerFSM(chargeState);
            final BatteryContract2.ChargerModel chargerModel = this.mChargerModel;
            runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.battery_task.BatteryPresenter2$onStateChange$$inlined$let$lambda$2
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
                    BatteryContract2.ViewInterface theView;
                    BatteryContract2.ViewInterface theView2;
                    String tag = this.getTAG();
                    StringBuilder sb = new StringBuilder();
                    sb.append("show charge event ");
                    sb.append(BatteryContract2.ChargerModel.this);
                    sb.append(" on UIThread mView=");
                    theView = this.getTheView();
                    sb.append(theView);
                    Pdlog.m3275i(tag, sb.toString());
                    theView2 = this.getTheView();
                    if (theView2 != null) {
                        theView2.showChargerEvent(BatteryContract2.ChargerModel.this);
                    }
                }
            });
        }
    }

    @Override // com.pudutech.bumblebee.business.core_devices_task.battery.SystemBatteryListener
    public void onPowerChange(final int powerPercent) {
        Pdlog.m3275i(getTAG(), "onPowerChange percent=" + powerPercent + " old=" + this.mPowerModel + " view=" + getTheView());
        powerFSM(INSTANCE.getBattery().getPowerState(), powerPercent);
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.battery_task.BatteryPresenter2$onPowerChange$1
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
                BatteryContract2.ViewInterface theView;
                BatteryContract2.ViewInterface theView2;
                String tag = BatteryPresenter2.this.getTAG();
                StringBuilder sb = new StringBuilder();
                sb.append("show power change on UIThread mView=");
                theView = BatteryPresenter2.this.getTheView();
                sb.append(theView);
                Pdlog.m3275i(tag, sb.toString());
                theView2 = BatteryPresenter2.this.getTheView();
                if (theView2 != null) {
                    theView2.showPowerChange(powerPercent);
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final synchronized void chargerFSM(ChargeState chargeState) {
        int i;
        BatteryContract2.ViewEvent viewEvent = BatteryContract2.ViewEvent.CHARGER_DISCONNECT;
        boolean z = true;
        if (chargeState != null) {
            switch (chargeState) {
                case ChargeFull:
                    viewEvent = BatteryContract2.ViewEvent.CHARGE_FULL;
                    i = 0;
                    break;
                case Charging:
                    viewEvent = BatteryContract2.ViewEvent.CHARGING;
                    this.wasShowFirstWarning = false;
                    i = 0;
                    break;
                case Idle:
                    viewEvent = BatteryContract2.ViewEvent.CHARGER_DISCONNECT;
                    i = 0;
                    break;
                case ChargeErrorContact:
                    viewEvent = BatteryContract2.ViewEvent.CHARGER_CONTACT_FAULT;
                    i = 8001;
                    break;
                case ChargeErrorElectric:
                    viewEvent = BatteryContract2.ViewEvent.ELECTRIC_FAULT;
                    i = 0;
                    break;
                case ErrorBatteryPackComm:
                    viewEvent = BatteryContract2.ViewEvent.BATTERY_I2C_FAULT;
                    i = 8002;
                    break;
                case ErrorOverVolt:
                    viewEvent = BatteryContract2.ViewEvent.OVER_VOLTAGE_FAULT;
                    i = 8003;
                    break;
                case ErrorOverElectric:
                    viewEvent = BatteryContract2.ViewEvent.OVER_ELECTRIC_FAULT;
                    i = 8004;
                    break;
                case ErrorOverTemperature:
                    viewEvent = BatteryContract2.ViewEvent.OVERHEATING_FAULT;
                    i = 8005;
                    break;
                case ErrorOverTime:
                    viewEvent = BatteryContract2.ViewEvent.CHARGE_OVERTIME_FAULT;
                    i = 8006;
                    break;
            }
            Pdlog.m3275i(getTAG(), "fsm event=" + viewEvent + " code=" + i + " chargeState=" + chargeState);
            if (chargeState != ChargeState.Idle) {
                z = false;
            }
            this.mChargerModel = new BatteryContract2.ChargerModel(viewEvent, z, i);
        }
        Pdlog.m3274e(getTAG(), "missing case " + chargeState);
        i = 0;
        Pdlog.m3275i(getTAG(), "fsm event=" + viewEvent + " code=" + i + " chargeState=" + chargeState);
        if (chargeState != ChargeState.Idle) {
        }
        this.mChargerModel = new BatteryContract2.ChargerModel(viewEvent, z, i);
    }

    private final synchronized void powerFSM(PowerState powerState, int powerPercent) {
        BatteryContract2.ViewEvent viewEvent;
        BatteryContract2.ViewEvent event = this.mPowerModel.getEvent();
        if (powerState != null) {
            int i = WhenMappings.$EnumSwitchMapping$1[powerState.ordinal()];
            if (i == 1) {
                viewEvent = BatteryContract2.ViewEvent.POWER_EMPTY;
            } else if (i == 2) {
                viewEvent = BatteryContract2.ViewEvent.POWER_LOW_5_10;
            } else if (i == 3) {
                viewEvent = BatteryContract2.ViewEvent.POWER_LOW_2_5;
            } else if (i == 4) {
                viewEvent = BatteryContract2.ViewEvent.POWER_NORMAL;
            } else {
                Pdlog.m3274e(getTAG(), "missing case " + powerState);
            }
            event = viewEvent;
        }
        this.mPowerModel = new BatteryContract2.PowerModel(event, powerPercent);
    }
}
