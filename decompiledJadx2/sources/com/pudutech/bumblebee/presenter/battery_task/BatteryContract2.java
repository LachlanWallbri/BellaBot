package com.pudutech.bumblebee.presenter.battery_task;

import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.mvp_base.BaseViewInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BatteryContract2.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001:\u0005\u0002\u0003\u0004\u0005\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2;", "", "ChargerModel", "PowerModel", "PresenterInterface", "ViewEvent", "ViewInterface", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface BatteryContract2 {

    /* compiled from: BatteryContract2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u000e\u001a\u00020\u000bH&J\b\u0010\u000f\u001a\u00020\u0010H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$PresenterInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BasePresenterInterface;", "chargerModel", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ChargerModel;", "getChargerModel", "()Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ChargerModel;", "powerModel", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$PowerModel;", "getPowerModel", "()Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$PowerModel;", "showLowerNotify", "", "getShowLowerNotify", "()Z", "checkAllowedForMove", "clearFirstTimeWarning", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface PresenterInterface extends BasePresenterInterface {
        boolean checkAllowedForMove();

        void clearFirstTimeWarning();

        ChargerModel getChargerModel();

        PowerModel getPowerModel();

        boolean getShowLowerNotify();
    }

    /* compiled from: BatteryContract2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0010\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ViewEvent;", "", "(Ljava/lang/String;I)V", "POWER_LOW_5_10", "POWER_LOW_2_5", "POWER_EMPTY", "POWER_NORMAL", "CHARGING", "CHARGE_FULL", "CHARGER_DISCONNECT", "CHARGER_CONTACT_FAULT", "ELECTRIC_FAULT", "BATTERY_I2C_FAULT", "OVER_VOLTAGE_FAULT", "OVER_ELECTRIC_FAULT", "OVERHEATING_FAULT", "CHARGE_OVERTIME_FAULT", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum ViewEvent {
        POWER_LOW_5_10,
        POWER_LOW_2_5,
        POWER_EMPTY,
        POWER_NORMAL,
        CHARGING,
        CHARGE_FULL,
        CHARGER_DISCONNECT,
        CHARGER_CONTACT_FAULT,
        ELECTRIC_FAULT,
        BATTERY_I2C_FAULT,
        OVER_VOLTAGE_FAULT,
        OVER_ELECTRIC_FAULT,
        OVERHEATING_FAULT,
        CHARGE_OVERTIME_FAULT
    }

    /* compiled from: BatteryContract2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000bH&¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseViewInterface;", "showChargerEvent", "", "model", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ChargerModel;", "showLowerNotify", "showPowerChange", "i", "", "showPowerEvent", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$PowerModel;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface ViewInterface extends BaseViewInterface {
        void showChargerEvent(ChargerModel model);

        void showLowerNotify();

        void showPowerChange(int i);

        void showPowerEvent(PowerModel model);
    }

    /* compiled from: BatteryContract2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$PowerModel;", "", "event", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ViewEvent;", "powerPercent", "", "(Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ViewEvent;I)V", "getEvent", "()Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ViewEvent;", "getPowerPercent", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final /* data */ class PowerModel {
        private final ViewEvent event;
        private final int powerPercent;

        public static /* synthetic */ PowerModel copy$default(PowerModel powerModel, ViewEvent viewEvent, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                viewEvent = powerModel.event;
            }
            if ((i2 & 2) != 0) {
                i = powerModel.powerPercent;
            }
            return powerModel.copy(viewEvent, i);
        }

        /* renamed from: component1, reason: from getter */
        public final ViewEvent getEvent() {
            return this.event;
        }

        /* renamed from: component2, reason: from getter */
        public final int getPowerPercent() {
            return this.powerPercent;
        }

        public final PowerModel copy(ViewEvent event, int powerPercent) {
            Intrinsics.checkParameterIsNotNull(event, "event");
            return new PowerModel(event, powerPercent);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PowerModel)) {
                return false;
            }
            PowerModel powerModel = (PowerModel) other;
            return Intrinsics.areEqual(this.event, powerModel.event) && this.powerPercent == powerModel.powerPercent;
        }

        public int hashCode() {
            ViewEvent viewEvent = this.event;
            return ((viewEvent != null ? viewEvent.hashCode() : 0) * 31) + this.powerPercent;
        }

        public String toString() {
            return "PowerModel(event=" + this.event + ", powerPercent=" + this.powerPercent + ")";
        }

        public PowerModel(ViewEvent event, int i) {
            Intrinsics.checkParameterIsNotNull(event, "event");
            this.event = event;
            this.powerPercent = i;
        }

        public final ViewEvent getEvent() {
            return this.event;
        }

        public final int getPowerPercent() {
            return this.powerPercent;
        }
    }

    /* compiled from: BatteryContract2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0007HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\r¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ChargerModel;", "", "event", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ViewEvent;", "isCharging", "", AUserTrack.UTKEY_ERROR_CODE, "", "(Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ViewEvent;ZI)V", "getErrorCode", "()I", "getEvent", "()Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ViewEvent;", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final /* data */ class ChargerModel {
        private final int errorCode;
        private final ViewEvent event;
        private final boolean isCharging;

        public static /* synthetic */ ChargerModel copy$default(ChargerModel chargerModel, ViewEvent viewEvent, boolean z, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                viewEvent = chargerModel.event;
            }
            if ((i2 & 2) != 0) {
                z = chargerModel.isCharging;
            }
            if ((i2 & 4) != 0) {
                i = chargerModel.errorCode;
            }
            return chargerModel.copy(viewEvent, z, i);
        }

        /* renamed from: component1, reason: from getter */
        public final ViewEvent getEvent() {
            return this.event;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getIsCharging() {
            return this.isCharging;
        }

        /* renamed from: component3, reason: from getter */
        public final int getErrorCode() {
            return this.errorCode;
        }

        public final ChargerModel copy(ViewEvent event, boolean isCharging, int errorCode) {
            Intrinsics.checkParameterIsNotNull(event, "event");
            return new ChargerModel(event, isCharging, errorCode);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ChargerModel)) {
                return false;
            }
            ChargerModel chargerModel = (ChargerModel) other;
            return Intrinsics.areEqual(this.event, chargerModel.event) && this.isCharging == chargerModel.isCharging && this.errorCode == chargerModel.errorCode;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            ViewEvent viewEvent = this.event;
            int hashCode = (viewEvent != null ? viewEvent.hashCode() : 0) * 31;
            boolean z = this.isCharging;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            return ((hashCode + i) * 31) + this.errorCode;
        }

        public String toString() {
            return "ChargerModel(event=" + this.event + ", isCharging=" + this.isCharging + ", errorCode=" + this.errorCode + ")";
        }

        public ChargerModel(ViewEvent event, boolean z, int i) {
            Intrinsics.checkParameterIsNotNull(event, "event");
            this.event = event;
            this.isCharging = z;
            this.errorCode = i;
        }

        public final int getErrorCode() {
            return this.errorCode;
        }

        public final ViewEvent getEvent() {
            return this.event;
        }

        public final boolean isCharging() {
            return this.isCharging;
        }
    }
}
