package com.pudutech.bumblebee.robot_ui.config;

import android.os.Build;
import com.aliyun.alink.linksdk.alcs.api.utils.ErrorCode;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.lib_update.util.SystemProperty;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdkwrap.lib.robot.MachineInfoHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: RobotInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004J\b\u0010\f\u001a\u0004\u0018\u00010\u0007J\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/config/RobotInfo;", "", "()V", "TYPE_PUDU_CORE", "", "appVersionCode", "getAppUpdateProduct", "Lcom/pudutech/bumblebee/robot_ui/config/RobotInfo$UpdateInfo;", "getMonocularCamera", "", "getSystemProperty", TransferTable.COLUMN_KEY, "getSystemUpdateInfo", "isBellabot", "", "isPDCore", "UpdateInfo", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class RobotInfo {
    private static final String TYPE_PUDU_CORE = "pd.core";
    public static final RobotInfo INSTANCE = new RobotInfo();
    private static final String appVersionCode = appVersionCode;
    private static final String appVersionCode = appVersionCode;

    private RobotInfo() {
    }

    public final boolean isPDCore() {
        String buildId = Build.ID;
        Intrinsics.checkExpressionValueIsNotNull(buildId, "buildId");
        return StringsKt.startsWith$default(buildId, TYPE_PUDU_CORE, false, 2, (Object) null);
    }

    public final String getSystemProperty(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return new SystemProperty(RobotContext.INSTANCE.getContext()).getProperty(key);
    }

    public final UpdateInfo getSystemUpdateInfo() {
        Integer robotMinorVersion;
        Integer robotMinorVersion2;
        Integer robotMinorVersion3;
        Integer robotMinorVersion4;
        String str = Build.ID;
        Intrinsics.checkExpressionValueIsNotNull(str, "Build.ID");
        String substringBeforeLast$default = StringsKt.substringBeforeLast$default(StringsKt.substringBeforeLast$default(str, ".", (String) null, 2, (Object) null), ".", (String) null, 2, (Object) null);
        if (StringsKt.startsWith$default(substringBeforeLast$default, TYPE_PUDU_CORE, false, 2, (Object) null)) {
            return new UpdateInfo("pdrobot_system_" + substringBeforeLast$default, getSystemProperty("ro.pudutech.version_name"), getSystemProperty("ro.pudutech.version_code"));
        }
        MachineModel robotType = MachineInfoHelper.INSTANCE.getRobotType();
        if (robotType != null && robotType == MachineModel.BellaBot) {
            Integer robotMainVersion = MachineInfoHelper.INSTANCE.getRobotMainVersion();
            if (robotMainVersion != null && robotMainVersion.intValue() == 0 && (robotMinorVersion4 = MachineInfoHelper.INSTANCE.getRobotMinorVersion()) != null && robotMinorVersion4.intValue() == 0) {
                return new UpdateInfo("pdrobot_system_bellabot_v0", "1.2.0.0", ErrorCode.UNKNOWN_SUCCESS_CODE);
            }
            Integer robotMainVersion2 = MachineInfoHelper.INSTANCE.getRobotMainVersion();
            if (robotMainVersion2 != null && robotMainVersion2.intValue() == 0 && (robotMinorVersion3 = MachineInfoHelper.INSTANCE.getRobotMinorVersion()) != null && robotMinorVersion3.intValue() == 1) {
                return new UpdateInfo("pdrobot_system_bellabot_v1", "1.2.0.0", ErrorCode.UNKNOWN_SUCCESS_CODE);
            }
            Integer robotMainVersion3 = MachineInfoHelper.INSTANCE.getRobotMainVersion();
            if (robotMainVersion3 != null && robotMainVersion3.intValue() == 0 && (robotMinorVersion2 = MachineInfoHelper.INSTANCE.getRobotMinorVersion()) != null && robotMinorVersion2.intValue() == 2) {
                return new UpdateInfo("pdrobot_system_bellabot_v2", "1.2.0.0", ErrorCode.UNKNOWN_SUCCESS_CODE);
            }
            Integer robotMainVersion4 = MachineInfoHelper.INSTANCE.getRobotMainVersion();
            if (robotMainVersion4 != null && robotMainVersion4.intValue() == 0 && (robotMinorVersion = MachineInfoHelper.INSTANCE.getRobotMinorVersion()) != null && robotMinorVersion.intValue() == 3) {
                return new UpdateInfo("pdrobot_system_bellabot_v3", "1.2.0.0", ErrorCode.UNKNOWN_SUCCESS_CODE);
            }
            Integer robotMainVersion5 = MachineInfoHelper.INSTANCE.getRobotMainVersion();
            if (robotMainVersion5 != null && robotMainVersion5.intValue() == 1) {
                return new UpdateInfo("pdrobot_system_bellabot_v2_enclosed", "1.2.0.0", ErrorCode.UNKNOWN_SUCCESS_CODE);
            }
        }
        return null;
    }

    public final int getMonocularCamera() {
        MachineInfo.MonocularType monocularDeviceType = MachineInfoHelper.INSTANCE.getMonocularDeviceType();
        if (monocularDeviceType != null) {
            return monocularDeviceType.ordinal();
        }
        return 0;
    }

    public final UpdateInfo getAppUpdateProduct() {
        Integer robotMinorVersion;
        Integer robotMinorVersion2;
        Integer robotMinorVersion3;
        Integer robotMinorVersion4;
        if (MachineInfoHelper.INSTANCE.getRobotType() == null) {
            return new UpdateInfo("pdrobot_bumblebee", "6.12.0.10", appVersionCode);
        }
        Integer robotMainVersion = MachineInfoHelper.INSTANCE.getRobotMainVersion();
        if (robotMainVersion != null && robotMainVersion.intValue() == 0 && (robotMinorVersion4 = MachineInfoHelper.INSTANCE.getRobotMinorVersion()) != null && robotMinorVersion4.intValue() == 0) {
            return new UpdateInfo("pdrobot_bumblebee_v0", "6.12.0.10", appVersionCode);
        }
        Integer robotMainVersion2 = MachineInfoHelper.INSTANCE.getRobotMainVersion();
        if (robotMainVersion2 != null && robotMainVersion2.intValue() == 0 && (robotMinorVersion3 = MachineInfoHelper.INSTANCE.getRobotMinorVersion()) != null && robotMinorVersion3.intValue() == 1) {
            return new UpdateInfo("pdrobot_bumblebee", "6.12.0.10", appVersionCode);
        }
        Integer robotMainVersion3 = MachineInfoHelper.INSTANCE.getRobotMainVersion();
        if (robotMainVersion3 != null && robotMainVersion3.intValue() == 0 && (robotMinorVersion2 = MachineInfoHelper.INSTANCE.getRobotMinorVersion()) != null && robotMinorVersion2.intValue() == 2) {
            return new UpdateInfo("pdrobot_bumblebee_v2", "6.12.0.10", appVersionCode);
        }
        Integer robotMainVersion4 = MachineInfoHelper.INSTANCE.getRobotMainVersion();
        if (robotMainVersion4 != null && robotMainVersion4.intValue() == 0 && (robotMinorVersion = MachineInfoHelper.INSTANCE.getRobotMinorVersion()) != null && robotMinorVersion.intValue() == 3) {
            return new UpdateInfo("pdrobot_bumblebee_v3", "6.12.0.10", appVersionCode);
        }
        Integer robotMainVersion5 = MachineInfoHelper.INSTANCE.getRobotMainVersion();
        if (robotMainVersion5 != null && robotMainVersion5.intValue() == 1) {
            return new UpdateInfo("pdrobot_bumblebee_v2_enclosed", "6.12.0.10", appVersionCode);
        }
        return new UpdateInfo("pdrobot_bumblebee", "6.12.0.10", appVersionCode);
    }

    public final boolean isBellabot() {
        return MachineInfoHelper.INSTANCE.getRobotType() == MachineModel.BellaBot;
    }

    /* compiled from: RobotInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/config/RobotInfo$UpdateInfo;", "", "name", "", "versionName", "verCode", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getVerCode", "setVerCode", "getVersionName", "setVersionName", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final /* data */ class UpdateInfo {
        private String name;
        private String verCode;
        private String versionName;

        public static /* synthetic */ UpdateInfo copy$default(UpdateInfo updateInfo, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = updateInfo.name;
            }
            if ((i & 2) != 0) {
                str2 = updateInfo.versionName;
            }
            if ((i & 4) != 0) {
                str3 = updateInfo.verCode;
            }
            return updateInfo.copy(str, str2, str3);
        }

        /* renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component2, reason: from getter */
        public final String getVersionName() {
            return this.versionName;
        }

        /* renamed from: component3, reason: from getter */
        public final String getVerCode() {
            return this.verCode;
        }

        public final UpdateInfo copy(String name, String versionName, String verCode) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(versionName, "versionName");
            Intrinsics.checkParameterIsNotNull(verCode, "verCode");
            return new UpdateInfo(name, versionName, verCode);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UpdateInfo)) {
                return false;
            }
            UpdateInfo updateInfo = (UpdateInfo) other;
            return Intrinsics.areEqual(this.name, updateInfo.name) && Intrinsics.areEqual(this.versionName, updateInfo.versionName) && Intrinsics.areEqual(this.verCode, updateInfo.verCode);
        }

        public int hashCode() {
            String str = this.name;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.versionName;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.verCode;
            return hashCode2 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "UpdateInfo(name=" + this.name + ", versionName=" + this.versionName + ", verCode=" + this.verCode + ")";
        }

        public UpdateInfo(String name, String versionName, String verCode) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(versionName, "versionName");
            Intrinsics.checkParameterIsNotNull(verCode, "verCode");
            this.name = name;
            this.versionName = versionName;
            this.verCode = verCode;
        }

        public final String getName() {
            return this.name;
        }

        public final String getVerCode() {
            return this.verCode;
        }

        public final String getVersionName() {
            return this.versionName;
        }

        public final void setName(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.name = str;
        }

        public final void setVerCode(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.verCode = str;
        }

        public final void setVersionName(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.versionName = str;
        }
    }
}
