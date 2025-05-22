package com.pudutech.bumblebee.robot_ui.manager;

import com.pudu.library.loracall.bean.LoRaVersionParam;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: lora_bean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LoRaInfo;", "", "hdVersion", "", "bootLoaderVersion", "firmwareVersion", "firmwareType", "", "showVersion", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getBootLoaderVersion", "()Ljava/lang/String;", "getFirmwareType", "()I", "getFirmwareVersion", "getHdVersion", "getShowVersion", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final /* data */ class LoRaInfo {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String bootLoaderVersion;
    private final int firmwareType;
    private final String firmwareVersion;
    private final String hdVersion;
    private final String showVersion;

    public static /* synthetic */ LoRaInfo copy$default(LoRaInfo loRaInfo, String str, String str2, String str3, int i, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = loRaInfo.hdVersion;
        }
        if ((i2 & 2) != 0) {
            str2 = loRaInfo.bootLoaderVersion;
        }
        String str5 = str2;
        if ((i2 & 4) != 0) {
            str3 = loRaInfo.firmwareVersion;
        }
        String str6 = str3;
        if ((i2 & 8) != 0) {
            i = loRaInfo.firmwareType;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            str4 = loRaInfo.showVersion;
        }
        return loRaInfo.copy(str, str5, str6, i3, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final String getHdVersion() {
        return this.hdVersion;
    }

    /* renamed from: component2, reason: from getter */
    public final String getBootLoaderVersion() {
        return this.bootLoaderVersion;
    }

    /* renamed from: component3, reason: from getter */
    public final String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    /* renamed from: component4, reason: from getter */
    public final int getFirmwareType() {
        return this.firmwareType;
    }

    /* renamed from: component5, reason: from getter */
    public final String getShowVersion() {
        return this.showVersion;
    }

    public final LoRaInfo copy(String hdVersion, String bootLoaderVersion, String firmwareVersion, int firmwareType, String showVersion) {
        Intrinsics.checkParameterIsNotNull(hdVersion, "hdVersion");
        Intrinsics.checkParameterIsNotNull(bootLoaderVersion, "bootLoaderVersion");
        Intrinsics.checkParameterIsNotNull(firmwareVersion, "firmwareVersion");
        Intrinsics.checkParameterIsNotNull(showVersion, "showVersion");
        return new LoRaInfo(hdVersion, bootLoaderVersion, firmwareVersion, firmwareType, showVersion);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LoRaInfo)) {
            return false;
        }
        LoRaInfo loRaInfo = (LoRaInfo) other;
        return Intrinsics.areEqual(this.hdVersion, loRaInfo.hdVersion) && Intrinsics.areEqual(this.bootLoaderVersion, loRaInfo.bootLoaderVersion) && Intrinsics.areEqual(this.firmwareVersion, loRaInfo.firmwareVersion) && this.firmwareType == loRaInfo.firmwareType && Intrinsics.areEqual(this.showVersion, loRaInfo.showVersion);
    }

    public int hashCode() {
        String str = this.hdVersion;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.bootLoaderVersion;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.firmwareVersion;
        int hashCode3 = (((hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + Integer.hashCode(this.firmwareType)) * 31;
        String str4 = this.showVersion;
        return hashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "LoRaInfo(hdVersion=" + this.hdVersion + ", bootLoaderVersion=" + this.bootLoaderVersion + ", firmwareVersion=" + this.firmwareVersion + ", firmwareType=" + this.firmwareType + ", showVersion=" + this.showVersion + ")";
    }

    public LoRaInfo(String hdVersion, String bootLoaderVersion, String firmwareVersion, int i, String showVersion) {
        Intrinsics.checkParameterIsNotNull(hdVersion, "hdVersion");
        Intrinsics.checkParameterIsNotNull(bootLoaderVersion, "bootLoaderVersion");
        Intrinsics.checkParameterIsNotNull(firmwareVersion, "firmwareVersion");
        Intrinsics.checkParameterIsNotNull(showVersion, "showVersion");
        this.hdVersion = hdVersion;
        this.bootLoaderVersion = bootLoaderVersion;
        this.firmwareVersion = firmwareVersion;
        this.firmwareType = i;
        this.showVersion = showVersion;
    }

    public final String getHdVersion() {
        return this.hdVersion;
    }

    public final String getBootLoaderVersion() {
        return this.bootLoaderVersion;
    }

    public final String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public final int getFirmwareType() {
        return this.firmwareType;
    }

    public final String getShowVersion() {
        return this.showVersion;
    }

    /* compiled from: lora_bean.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LoRaInfo$Companion;", "", "()V", "fromLoRaVersionParam", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaInfo;", "param", "Lcom/pudu/library/loracall/bean/LoRaVersionParam;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final LoRaInfo fromLoRaVersionParam(LoRaVersionParam param) {
            Intrinsics.checkParameterIsNotNull(param, "param");
            return new LoRaInfo(param.getHdVersion(), param.getBootLoaderVersion(), param.getFirmwareVersion(), param.getFirmwareType(), param.getShowVersion());
        }
    }
}
