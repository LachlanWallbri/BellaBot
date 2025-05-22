package com.pudutech.robot.module.report.track2;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: peripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0007\b\t\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0004\u000b\f\r\u000e¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BasePeripheralsStatus;", "", "peripherals_name", "", "(Ljava/lang/String;)V", "getPeripherals_name", "()Ljava/lang/String;", "BellaEmergencyStopBtnStatus", "BellaTouchPeripheralsStatus", "BellaTrayPeripheralsStatus", "PhoenixPeripheralsStatus", "Lcom/pudutech/robot/module/report/track2/BasePeripheralsStatus$BellaTouchPeripheralsStatus;", "Lcom/pudutech/robot/module/report/track2/BasePeripheralsStatus$BellaTrayPeripheralsStatus;", "Lcom/pudutech/robot/module/report/track2/BasePeripheralsStatus$BellaEmergencyStopBtnStatus;", "Lcom/pudutech/robot/module/report/track2/BasePeripheralsStatus$PhoenixPeripheralsStatus;", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public abstract class BasePeripheralsStatus {
    private final String peripherals_name;

    private BasePeripheralsStatus(String str) {
        this.peripherals_name = str;
    }

    public /* synthetic */ BasePeripheralsStatus(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    public final String getPeripherals_name() {
        return this.peripherals_name;
    }

    /* compiled from: peripherals.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BasePeripheralsStatus$BellaTouchPeripheralsStatus;", "Lcom/pudutech/robot/module/report/track2/BasePeripheralsStatus;", "page_key", "", TrackKey.TOUCH_TYPE, "", TrackKey.TOUCH_STATUS, "(Ljava/lang/String;II)V", "getPage_key", "()Ljava/lang/String;", "getTouch_status", "()I", "getTouch_type", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class BellaTouchPeripheralsStatus extends BasePeripheralsStatus {
        private final String page_key;
        private final int touch_status;
        private final int touch_type;

        public static /* synthetic */ BellaTouchPeripheralsStatus copy$default(BellaTouchPeripheralsStatus bellaTouchPeripheralsStatus, String str, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = bellaTouchPeripheralsStatus.page_key;
            }
            if ((i3 & 2) != 0) {
                i = bellaTouchPeripheralsStatus.touch_type;
            }
            if ((i3 & 4) != 0) {
                i2 = bellaTouchPeripheralsStatus.touch_status;
            }
            return bellaTouchPeripheralsStatus.copy(str, i, i2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getPage_key() {
            return this.page_key;
        }

        /* renamed from: component2, reason: from getter */
        public final int getTouch_type() {
            return this.touch_type;
        }

        /* renamed from: component3, reason: from getter */
        public final int getTouch_status() {
            return this.touch_status;
        }

        public final BellaTouchPeripheralsStatus copy(String page_key, int touch_type, int touch_status) {
            Intrinsics.checkParameterIsNotNull(page_key, "page_key");
            return new BellaTouchPeripheralsStatus(page_key, touch_type, touch_status);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BellaTouchPeripheralsStatus)) {
                return false;
            }
            BellaTouchPeripheralsStatus bellaTouchPeripheralsStatus = (BellaTouchPeripheralsStatus) other;
            return Intrinsics.areEqual(this.page_key, bellaTouchPeripheralsStatus.page_key) && this.touch_type == bellaTouchPeripheralsStatus.touch_type && this.touch_status == bellaTouchPeripheralsStatus.touch_status;
        }

        public int hashCode() {
            String str = this.page_key;
            return ((((str != null ? str.hashCode() : 0) * 31) + this.touch_type) * 31) + this.touch_status;
        }

        public String toString() {
            return "BellaTouchPeripheralsStatus(page_key=" + this.page_key + ", touch_type=" + this.touch_type + ", touch_status=" + this.touch_status + ")";
        }

        public final String getPage_key() {
            return this.page_key;
        }

        public final int getTouch_type() {
            return this.touch_type;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BellaTouchPeripheralsStatus(String page_key, int i, int i2) {
            super(SensorName.TOUCH_SENSOR.name(), null);
            Intrinsics.checkParameterIsNotNull(page_key, "page_key");
            this.page_key = page_key;
            this.touch_type = i;
            this.touch_status = i2;
        }

        public final int getTouch_status() {
            return this.touch_status;
        }
    }

    /* compiled from: peripherals.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BasePeripheralsStatus$BellaTrayPeripheralsStatus;", "Lcom/pudutech/robot/module/report/track2/BasePeripheralsStatus;", "page_key", "", TrackKey.TRAY_SENSOR_COUNT, "", TrackKey.TRAY_SENSOR_STATUS, "(Ljava/lang/String;II)V", "getPage_key", "()Ljava/lang/String;", "getTray_sensor_count", "()I", "getTray_sensor_status", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class BellaTrayPeripheralsStatus extends BasePeripheralsStatus {
        private final String page_key;
        private final int tray_sensor_count;
        private final int tray_sensor_status;

        public static /* synthetic */ BellaTrayPeripheralsStatus copy$default(BellaTrayPeripheralsStatus bellaTrayPeripheralsStatus, String str, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = bellaTrayPeripheralsStatus.page_key;
            }
            if ((i3 & 2) != 0) {
                i = bellaTrayPeripheralsStatus.tray_sensor_count;
            }
            if ((i3 & 4) != 0) {
                i2 = bellaTrayPeripheralsStatus.tray_sensor_status;
            }
            return bellaTrayPeripheralsStatus.copy(str, i, i2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getPage_key() {
            return this.page_key;
        }

        /* renamed from: component2, reason: from getter */
        public final int getTray_sensor_count() {
            return this.tray_sensor_count;
        }

        /* renamed from: component3, reason: from getter */
        public final int getTray_sensor_status() {
            return this.tray_sensor_status;
        }

        public final BellaTrayPeripheralsStatus copy(String page_key, int tray_sensor_count, int tray_sensor_status) {
            Intrinsics.checkParameterIsNotNull(page_key, "page_key");
            return new BellaTrayPeripheralsStatus(page_key, tray_sensor_count, tray_sensor_status);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BellaTrayPeripheralsStatus)) {
                return false;
            }
            BellaTrayPeripheralsStatus bellaTrayPeripheralsStatus = (BellaTrayPeripheralsStatus) other;
            return Intrinsics.areEqual(this.page_key, bellaTrayPeripheralsStatus.page_key) && this.tray_sensor_count == bellaTrayPeripheralsStatus.tray_sensor_count && this.tray_sensor_status == bellaTrayPeripheralsStatus.tray_sensor_status;
        }

        public int hashCode() {
            String str = this.page_key;
            return ((((str != null ? str.hashCode() : 0) * 31) + this.tray_sensor_count) * 31) + this.tray_sensor_status;
        }

        public String toString() {
            return "BellaTrayPeripheralsStatus(page_key=" + this.page_key + ", tray_sensor_count=" + this.tray_sensor_count + ", tray_sensor_status=" + this.tray_sensor_status + ")";
        }

        public final String getPage_key() {
            return this.page_key;
        }

        public final int getTray_sensor_count() {
            return this.tray_sensor_count;
        }

        public final int getTray_sensor_status() {
            return this.tray_sensor_status;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BellaTrayPeripheralsStatus(String page_key, int i, int i2) {
            super(SensorName.TRAY_SENSOR.name(), null);
            Intrinsics.checkParameterIsNotNull(page_key, "page_key");
            this.page_key = page_key;
            this.tray_sensor_count = i;
            this.tray_sensor_status = i2;
        }
    }

    /* compiled from: peripherals.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BasePeripheralsStatus$BellaEmergencyStopBtnStatus;", "Lcom/pudutech/robot/module/report/track2/BasePeripheralsStatus;", "page_key", "", "emergency_status", "", "(Ljava/lang/String;I)V", "getEmergency_status", "()I", "getPage_key", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class BellaEmergencyStopBtnStatus extends BasePeripheralsStatus {
        private final int emergency_status;
        private final String page_key;

        public static /* synthetic */ BellaEmergencyStopBtnStatus copy$default(BellaEmergencyStopBtnStatus bellaEmergencyStopBtnStatus, String str, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = bellaEmergencyStopBtnStatus.page_key;
            }
            if ((i2 & 2) != 0) {
                i = bellaEmergencyStopBtnStatus.emergency_status;
            }
            return bellaEmergencyStopBtnStatus.copy(str, i);
        }

        /* renamed from: component1, reason: from getter */
        public final String getPage_key() {
            return this.page_key;
        }

        /* renamed from: component2, reason: from getter */
        public final int getEmergency_status() {
            return this.emergency_status;
        }

        public final BellaEmergencyStopBtnStatus copy(String page_key, int emergency_status) {
            Intrinsics.checkParameterIsNotNull(page_key, "page_key");
            return new BellaEmergencyStopBtnStatus(page_key, emergency_status);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BellaEmergencyStopBtnStatus)) {
                return false;
            }
            BellaEmergencyStopBtnStatus bellaEmergencyStopBtnStatus = (BellaEmergencyStopBtnStatus) other;
            return Intrinsics.areEqual(this.page_key, bellaEmergencyStopBtnStatus.page_key) && this.emergency_status == bellaEmergencyStopBtnStatus.emergency_status;
        }

        public int hashCode() {
            String str = this.page_key;
            return ((str != null ? str.hashCode() : 0) * 31) + this.emergency_status;
        }

        public String toString() {
            return "BellaEmergencyStopBtnStatus(page_key=" + this.page_key + ", emergency_status=" + this.emergency_status + ")";
        }

        public final int getEmergency_status() {
            return this.emergency_status;
        }

        public final String getPage_key() {
            return this.page_key;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BellaEmergencyStopBtnStatus(String page_key, int i) {
            super(SensorName.EMERGENCY_STOP_SENSOR.name(), null);
            Intrinsics.checkParameterIsNotNull(page_key, "page_key");
            this.page_key = page_key;
            this.emergency_status = i;
        }
    }

    /* compiled from: peripherals.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BasePeripheralsStatus$PhoenixPeripheralsStatus;", "Lcom/pudutech/robot/module/report/track2/BasePeripheralsStatus;", "name", "", "status", TrackKey.SCENE_ID, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getScene_id", "getStatus", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class PhoenixPeripheralsStatus extends BasePeripheralsStatus {
        private final String name;
        private final String scene_id;
        private final String status;

        public static /* synthetic */ PhoenixPeripheralsStatus copy$default(PhoenixPeripheralsStatus phoenixPeripheralsStatus, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = phoenixPeripheralsStatus.name;
            }
            if ((i & 2) != 0) {
                str2 = phoenixPeripheralsStatus.status;
            }
            if ((i & 4) != 0) {
                str3 = phoenixPeripheralsStatus.scene_id;
            }
            return phoenixPeripheralsStatus.copy(str, str2, str3);
        }

        /* renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component2, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        /* renamed from: component3, reason: from getter */
        public final String getScene_id() {
            return this.scene_id;
        }

        public final PhoenixPeripheralsStatus copy(String name, String status, String scene_id) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(status, "status");
            Intrinsics.checkParameterIsNotNull(scene_id, "scene_id");
            return new PhoenixPeripheralsStatus(name, status, scene_id);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PhoenixPeripheralsStatus)) {
                return false;
            }
            PhoenixPeripheralsStatus phoenixPeripheralsStatus = (PhoenixPeripheralsStatus) other;
            return Intrinsics.areEqual(this.name, phoenixPeripheralsStatus.name) && Intrinsics.areEqual(this.status, phoenixPeripheralsStatus.status) && Intrinsics.areEqual(this.scene_id, phoenixPeripheralsStatus.scene_id);
        }

        public int hashCode() {
            String str = this.name;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.status;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.scene_id;
            return hashCode2 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "PhoenixPeripheralsStatus(name=" + this.name + ", status=" + this.status + ", scene_id=" + this.scene_id + ")";
        }

        public final String getName() {
            return this.name;
        }

        public final String getStatus() {
            return this.status;
        }

        public /* synthetic */ PhoenixPeripheralsStatus(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i & 4) != 0 ? "" : str3);
        }

        public final String getScene_id() {
            return this.scene_id;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PhoenixPeripheralsStatus(String name, String status, String scene_id) {
            super(name, null);
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(status, "status");
            Intrinsics.checkParameterIsNotNull(scene_id, "scene_id");
            this.name = name;
            this.status = status;
            this.scene_id = scene_id;
        }
    }
}
