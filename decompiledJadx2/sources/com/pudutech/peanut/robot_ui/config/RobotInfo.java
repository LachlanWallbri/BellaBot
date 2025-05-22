package com.pudutech.peanut.robot_ui.config;

import com.aliyun.alink.linksdk.alcs.api.utils.ErrorCode;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdkwrap.lib.robot.MachineInfoHelper;
import com.pudutech.peanut.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: RobotInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u000eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\tJ\b\u0010\n\u001a\u0004\u0018\u00010\u0006J\b\u0010\u000b\u001a\u0004\u0018\u00010\tJ\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/config/RobotInfo;", "", "()V", "appVersionCode", "", "type", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "getAdUpdateProductName", "getAppUpdateProduct", "Lcom/pudutech/peanut/robot_ui/config/RobotInfo$UpdateInfo;", "getProductMachineType", "getSystemUpdateInfo", "isPeanutBot", "", "UpdateInfo", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class RobotInfo {
    public static final RobotInfo INSTANCE = new RobotInfo();
    private static final String appVersionCode = "1";
    private static MachineModel type;

    public final String getAdUpdateProductName() {
        return "ad_player_app";
    }

    private RobotInfo() {
    }

    public final UpdateInfo getSystemUpdateInfo() {
        if (getProductMachineType() == MachineModel.Peanut) {
            return new UpdateInfo("pdrobot_system_peanut_v0", "1.0.0.0", ErrorCode.UNKNOWN_SUCCESS_CODE);
        }
        return null;
    }

    public final MachineModel getProductMachineType() {
        if (type == null) {
            type = MachineInfoHelper.INSTANCE.getRobotType();
        }
        return type;
    }

    public final UpdateInfo getAppUpdateProduct() {
        return new UpdateInfo(BuildConfig.product_name, "9.1.2", appVersionCode);
    }

    public final boolean isPeanutBot() {
        String str;
        MachineModel productMachineType = getProductMachineType();
        if (productMachineType == null || (str = productMachineType.name()) == null) {
            str = "";
        }
        return StringsKt.contains$default((CharSequence) str, (CharSequence) "PeanutBot", false, 2, (Object) null);
    }

    /* compiled from: RobotInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/config/RobotInfo$UpdateInfo;", "", "name", "", "versionName", "verCode", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getVerCode", "setVerCode", "getVersionName", "setVersionName", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
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
