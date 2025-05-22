package com.pudutech.mirsdkwrap.lib.robot;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MachineInfoHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0004\u001d\u001e\u001f B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0007J\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006J\b\u0010\f\u001a\u0004\u0018\u00010\rJ\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\r\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0012J\r\u0010\u0013\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0012J\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015J\u0015\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0019J\r\u0010\u001a\u001a\u0004\u0018\u00010\u001b¢\u0006\u0002\u0010\u001cR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/MachineInfoHelper;", "", "()V", "TAG", "", "machineInfo", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo;", "getLoraType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$LoraType;", "getMachineInfo", "Lcom/pudutech/mirsdkwrap/lib/robot/MachineInfoHelper$MachineInfos;", "getMachineInfoClass", "getMagicSensor", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$MagicSensor;", "getMonocularDeviceType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$MonocularType;", "getRobotMainVersion", "", "()Ljava/lang/Integer;", "getRobotMinorVersion", "getRobotType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "init", "", "m", "init$module_robot_mirsdk_wrapper_release", "isRGBD", "", "()Ljava/lang/Boolean;", "MachineInfos", "MachineNameWithFloatValue", "MachineNameWithIntValue", "MachineNameWithType", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MachineInfoHelper {
    private static MachineInfo machineInfo;
    public static final MachineInfoHelper INSTANCE = new MachineInfoHelper();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MachineInfo.Byte23Info.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[MachineInfo.Byte23Info.ESP32Version.ordinal()] = 1;
            $EnumSwitchMapping$0[MachineInfo.Byte23Info.RGBDVersion.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[MachineInfo.Byte24Info.values().length];
            $EnumSwitchMapping$1[MachineInfo.Byte24Info.LoraVersion.ordinal()] = 1;
            $EnumSwitchMapping$1[MachineInfo.Byte24Info.ScanCodeDevice.ordinal()] = 2;
            $EnumSwitchMapping$1[MachineInfo.Byte24Info.MonocularCamera.ordinal()] = 3;
            $EnumSwitchMapping$1[MachineInfo.Byte24Info.Slamcore.ordinal()] = 4;
        }
    }

    private MachineInfoHelper() {
    }

    public final void init$module_robot_mirsdk_wrapper_release(MachineInfo m) {
        Intrinsics.checkParameterIsNotNull(m, "m");
        machineInfo = m;
    }

    public final MachineInfo getMachineInfoClass() {
        return machineInfo;
    }

    public final MachineModel getRobotType() {
        ProductMachineType productType;
        MachineInfo machineInfo2 = machineInfo;
        if (machineInfo2 == null || (productType = machineInfo2.getProductType()) == null) {
            return null;
        }
        return productType.getModel();
    }

    public final Integer getRobotMainVersion() {
        ProductMachineType productType;
        MachineInfo machineInfo2 = machineInfo;
        if (machineInfo2 == null || (productType = machineInfo2.getProductType()) == null) {
            return null;
        }
        return Integer.valueOf(productType.getMainVersion());
    }

    public final Integer getRobotMinorVersion() {
        ProductMachineType productType;
        MachineInfo machineInfo2 = machineInfo;
        if (machineInfo2 == null || (productType = machineInfo2.getProductType()) == null) {
            return null;
        }
        return Integer.valueOf(productType.getMinorVersion());
    }

    public final MachineInfo.MonocularType getMonocularDeviceType() {
        MachineInfo machineInfo2 = machineInfo;
        if (machineInfo2 != null) {
            return machineInfo2.getMonocularDeviceType();
        }
        return null;
    }

    public final MachineInfo.LoraType getLoraType() {
        MachineInfo machineInfo2 = machineInfo;
        if (machineInfo2 != null) {
            return machineInfo2.getLoraType();
        }
        return null;
    }

    public final MachineInfo.MagicSensor getMagicSensor() {
        MachineInfo machineInfo2 = machineInfo;
        if (machineInfo2 != null) {
            return machineInfo2.getMagicSensor();
        }
        return null;
    }

    public final Boolean isRGBD() {
        try {
            MachineInfo machineInfo2 = machineInfo;
            if (machineInfo2 == null) {
                Intrinsics.throwNpe();
            }
            if (machineInfo2.getRGBDMode() != MachineInfo.RGBDType.NODevice) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            Pdlog.m3274e(TAG, "wrong RGBDType.");
            return false;
        }
    }

    public final MachineInfos getMachineInfo() {
        if (machineInfo == null) {
            return null;
        }
        MachineInfos machineInfos = new MachineInfos(new ArrayList(0), new ArrayList(0), new ArrayList(0));
        MachineInfo machineInfo2 = machineInfo;
        if (machineInfo2 == null) {
            Intrinsics.throwNpe();
        }
        for (Map.Entry<MachineInfo.FloatInfo, Float> entry : machineInfo2.getFloatData().entrySet()) {
            machineInfos.getFloatValus().add(new MachineNameWithFloatValue(entry.getKey().name(), entry.getValue().floatValue()));
        }
        MachineInfo machineInfo3 = machineInfo;
        if (machineInfo3 == null) {
            Intrinsics.throwNpe();
        }
        for (Map.Entry<MachineInfo.IntInfo, Integer> entry2 : machineInfo3.getIntData().entrySet()) {
            machineInfos.getIntValues().add(new MachineNameWithIntValue(entry2.getKey().name(), entry2.getValue().intValue()));
        }
        MachineInfo machineInfo4 = machineInfo;
        if (machineInfo4 == null) {
            Intrinsics.throwNpe();
        }
        Iterator<Map.Entry<MachineInfo.Byte23Info, UByte>> it = machineInfo4.getByte23Data().entrySet().iterator();
        while (it.hasNext()) {
            int i = WhenMappings.$EnumSwitchMapping$0[it.next().getKey().ordinal()];
            if (i == 1) {
                ArrayList<MachineNameWithType> types = machineInfos.getTypes();
                String name = MachineInfo.Byte23Info.ESP32Version.name();
                MachineInfo machineInfo5 = machineInfo;
                if (machineInfo5 == null) {
                    Intrinsics.throwNpe();
                }
                types.add(new MachineNameWithType(name, machineInfo5.getESPMode().name()));
            } else if (i == 2) {
                ArrayList<MachineNameWithType> types2 = machineInfos.getTypes();
                String name2 = MachineInfo.Byte23Info.RGBDVersion.name();
                MachineInfo machineInfo6 = machineInfo;
                if (machineInfo6 == null) {
                    Intrinsics.throwNpe();
                }
                types2.add(new MachineNameWithType(name2, machineInfo6.getRGBDMode().name()));
            }
        }
        ArrayList<MachineNameWithType> types3 = machineInfos.getTypes();
        MachineInfo machineInfo7 = machineInfo;
        if (machineInfo7 == null) {
            Intrinsics.throwNpe();
        }
        types3.add(new MachineNameWithType("MachineTypeModel", machineInfo7.getProductMachineType().getModel().name()));
        ArrayList<MachineNameWithType> types4 = machineInfos.getTypes();
        MachineInfo machineInfo8 = machineInfo;
        if (machineInfo8 == null) {
            Intrinsics.throwNpe();
        }
        types4.add(new MachineNameWithType("MachineTypeMainVersion", String.valueOf(machineInfo8.getProductMachineType().getMainVersion())));
        ArrayList<MachineNameWithType> types5 = machineInfos.getTypes();
        MachineInfo machineInfo9 = machineInfo;
        if (machineInfo9 == null) {
            Intrinsics.throwNpe();
        }
        types5.add(new MachineNameWithType("MachineTypeMinorVersion", String.valueOf(machineInfo9.getProductMachineType().getMinorVersion())));
        ArrayList<MachineNameWithType> types6 = machineInfos.getTypes();
        MachineInfo machineInfo10 = machineInfo;
        if (machineInfo10 == null) {
            Intrinsics.throwNpe();
        }
        types6.add(new MachineNameWithType("MachineTypeMinorVersion", machineInfo10.getSlamwareType().toString()));
        for (MachineInfo.Byte24Info byte24Info : MachineInfo.Byte24Info.values()) {
            int i2 = WhenMappings.$EnumSwitchMapping$1[byte24Info.ordinal()];
            if (i2 == 1) {
                ArrayList<MachineNameWithType> types7 = machineInfos.getTypes();
                String name3 = byte24Info.name();
                MachineInfo machineInfo11 = machineInfo;
                if (machineInfo11 == null) {
                    Intrinsics.throwNpe();
                }
                types7.add(new MachineNameWithType(name3, machineInfo11.getLoraType().name()));
            } else if (i2 == 2) {
                ArrayList<MachineNameWithType> types8 = machineInfos.getTypes();
                String name4 = byte24Info.name();
                MachineInfo machineInfo12 = machineInfo;
                if (machineInfo12 == null) {
                    Intrinsics.throwNpe();
                }
                types8.add(new MachineNameWithType(name4, machineInfo12.getScanCodeDeviceType().name()));
            } else if (i2 == 3) {
                ArrayList<MachineNameWithType> types9 = machineInfos.getTypes();
                String name5 = byte24Info.name();
                MachineInfo machineInfo13 = machineInfo;
                if (machineInfo13 == null) {
                    Intrinsics.throwNpe();
                }
                types9.add(new MachineNameWithType(name5, machineInfo13.getMonocularDeviceType().name()));
            } else if (i2 == 4) {
                ArrayList<MachineNameWithType> types10 = machineInfos.getTypes();
                String name6 = byte24Info.name();
                MachineInfo machineInfo14 = machineInfo;
                if (machineInfo14 == null) {
                    Intrinsics.throwNpe();
                }
                types10.add(new MachineNameWithType(name6, machineInfo14.getSlamwareType().name()));
            }
        }
        return machineInfos;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MachineInfoHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/MachineInfoHelper$MachineNameWithFloatValue;", "", "name", "", ES6Iterator.VALUE_PROPERTY, "", "(Ljava/lang/String;F)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getValue", "()F", "setValue", "(F)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final /* data */ class MachineNameWithFloatValue {
        private String name;
        private float value;

        public static /* synthetic */ MachineNameWithFloatValue copy$default(MachineNameWithFloatValue machineNameWithFloatValue, String str, float f, int i, Object obj) {
            if ((i & 1) != 0) {
                str = machineNameWithFloatValue.name;
            }
            if ((i & 2) != 0) {
                f = machineNameWithFloatValue.value;
            }
            return machineNameWithFloatValue.copy(str, f);
        }

        /* renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component2, reason: from getter */
        public final float getValue() {
            return this.value;
        }

        public final MachineNameWithFloatValue copy(String name, float value) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            return new MachineNameWithFloatValue(name, value);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MachineNameWithFloatValue)) {
                return false;
            }
            MachineNameWithFloatValue machineNameWithFloatValue = (MachineNameWithFloatValue) other;
            return Intrinsics.areEqual(this.name, machineNameWithFloatValue.name) && Float.compare(this.value, machineNameWithFloatValue.value) == 0;
        }

        public int hashCode() {
            String str = this.name;
            return ((str != null ? str.hashCode() : 0) * 31) + Float.floatToIntBits(this.value);
        }

        public String toString() {
            return "MachineNameWithFloatValue(name=" + this.name + ", value=" + this.value + ")";
        }

        public MachineNameWithFloatValue(String name, float f) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            this.name = name;
            this.value = f;
        }

        public final String getName() {
            return this.name;
        }

        public final float getValue() {
            return this.value;
        }

        public final void setName(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.name = str;
        }

        public final void setValue(float f) {
            this.value = f;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MachineInfoHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/MachineInfoHelper$MachineNameWithIntValue;", "", "name", "", ES6Iterator.VALUE_PROPERTY, "", "(Ljava/lang/String;I)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getValue", "()I", "setValue", "(I)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final /* data */ class MachineNameWithIntValue {
        private String name;
        private int value;

        public static /* synthetic */ MachineNameWithIntValue copy$default(MachineNameWithIntValue machineNameWithIntValue, String str, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = machineNameWithIntValue.name;
            }
            if ((i2 & 2) != 0) {
                i = machineNameWithIntValue.value;
            }
            return machineNameWithIntValue.copy(str, i);
        }

        /* renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component2, reason: from getter */
        public final int getValue() {
            return this.value;
        }

        public final MachineNameWithIntValue copy(String name, int value) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            return new MachineNameWithIntValue(name, value);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MachineNameWithIntValue)) {
                return false;
            }
            MachineNameWithIntValue machineNameWithIntValue = (MachineNameWithIntValue) other;
            return Intrinsics.areEqual(this.name, machineNameWithIntValue.name) && this.value == machineNameWithIntValue.value;
        }

        public int hashCode() {
            String str = this.name;
            return ((str != null ? str.hashCode() : 0) * 31) + this.value;
        }

        public String toString() {
            return "MachineNameWithIntValue(name=" + this.name + ", value=" + this.value + ")";
        }

        public MachineNameWithIntValue(String name, int i) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            this.name = name;
            this.value = i;
        }

        public final String getName() {
            return this.name;
        }

        public final int getValue() {
            return this.value;
        }

        public final void setName(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.name = str;
        }

        public final void setValue(int i) {
            this.value = i;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MachineInfoHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/MachineInfoHelper$MachineNameWithType;", "", "name", "", "type", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getType", "setType", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final /* data */ class MachineNameWithType {
        private String name;
        private String type;

        public static /* synthetic */ MachineNameWithType copy$default(MachineNameWithType machineNameWithType, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = machineNameWithType.name;
            }
            if ((i & 2) != 0) {
                str2 = machineNameWithType.type;
            }
            return machineNameWithType.copy(str, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component2, reason: from getter */
        public final String getType() {
            return this.type;
        }

        public final MachineNameWithType copy(String name, String type) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(type, "type");
            return new MachineNameWithType(name, type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MachineNameWithType)) {
                return false;
            }
            MachineNameWithType machineNameWithType = (MachineNameWithType) other;
            return Intrinsics.areEqual(this.name, machineNameWithType.name) && Intrinsics.areEqual(this.type, machineNameWithType.type);
        }

        public int hashCode() {
            String str = this.name;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.type;
            return hashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "MachineNameWithType(name=" + this.name + ", type=" + this.type + ")";
        }

        public MachineNameWithType(String name, String type) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(type, "type");
            this.name = name;
            this.type = type;
        }

        public final String getName() {
            return this.name;
        }

        public final String getType() {
            return this.type;
        }

        public final void setName(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.name = str;
        }

        public final void setType(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.type = str;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MachineInfoHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005\u0012\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0003j\b\u0012\u0004\u0012\u00020\u0007`\u0005\u0012\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0003j\b\u0012\u0004\u0012\u00020\t`\u0005¢\u0006\u0002\u0010\nJ\u0019\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003J\u0019\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0003j\b\u0012\u0004\u0012\u00020\u0007`\u0005HÆ\u0003J\u0019\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0003j\b\u0012\u0004\u0012\u00020\t`\u0005HÆ\u0003JW\u0010\u0016\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00052\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0003j\b\u0012\u0004\u0012\u00020\u0007`\u00052\u0018\b\u0002\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0003j\b\u0012\u0004\u0012\u00020\t`\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R*\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR*\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0003j\b\u0012\u0004\u0012\u00020\u0007`\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR*\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0003j\b\u0012\u0004\u0012\u00020\t`\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000e¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/MachineInfoHelper$MachineInfos;", "", "floatValus", "Ljava/util/ArrayList;", "Lcom/pudutech/mirsdkwrap/lib/robot/MachineInfoHelper$MachineNameWithFloatValue;", "Lkotlin/collections/ArrayList;", "intValues", "Lcom/pudutech/mirsdkwrap/lib/robot/MachineInfoHelper$MachineNameWithIntValue;", "types", "Lcom/pudutech/mirsdkwrap/lib/robot/MachineInfoHelper$MachineNameWithType;", "(Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;)V", "getFloatValus", "()Ljava/util/ArrayList;", "setFloatValus", "(Ljava/util/ArrayList;)V", "getIntValues", "setIntValues", "getTypes", "setTypes", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final /* data */ class MachineInfos {
        private ArrayList<MachineNameWithFloatValue> floatValus;
        private ArrayList<MachineNameWithIntValue> intValues;
        private ArrayList<MachineNameWithType> types;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MachineInfos copy$default(MachineInfos machineInfos, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, int i, Object obj) {
            if ((i & 1) != 0) {
                arrayList = machineInfos.floatValus;
            }
            if ((i & 2) != 0) {
                arrayList2 = machineInfos.intValues;
            }
            if ((i & 4) != 0) {
                arrayList3 = machineInfos.types;
            }
            return machineInfos.copy(arrayList, arrayList2, arrayList3);
        }

        public final ArrayList<MachineNameWithFloatValue> component1() {
            return this.floatValus;
        }

        public final ArrayList<MachineNameWithIntValue> component2() {
            return this.intValues;
        }

        public final ArrayList<MachineNameWithType> component3() {
            return this.types;
        }

        public final MachineInfos copy(ArrayList<MachineNameWithFloatValue> floatValus, ArrayList<MachineNameWithIntValue> intValues, ArrayList<MachineNameWithType> types) {
            Intrinsics.checkParameterIsNotNull(floatValus, "floatValus");
            Intrinsics.checkParameterIsNotNull(intValues, "intValues");
            Intrinsics.checkParameterIsNotNull(types, "types");
            return new MachineInfos(floatValus, intValues, types);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MachineInfos)) {
                return false;
            }
            MachineInfos machineInfos = (MachineInfos) other;
            return Intrinsics.areEqual(this.floatValus, machineInfos.floatValus) && Intrinsics.areEqual(this.intValues, machineInfos.intValues) && Intrinsics.areEqual(this.types, machineInfos.types);
        }

        public int hashCode() {
            ArrayList<MachineNameWithFloatValue> arrayList = this.floatValus;
            int hashCode = (arrayList != null ? arrayList.hashCode() : 0) * 31;
            ArrayList<MachineNameWithIntValue> arrayList2 = this.intValues;
            int hashCode2 = (hashCode + (arrayList2 != null ? arrayList2.hashCode() : 0)) * 31;
            ArrayList<MachineNameWithType> arrayList3 = this.types;
            return hashCode2 + (arrayList3 != null ? arrayList3.hashCode() : 0);
        }

        public String toString() {
            return "MachineInfos(floatValus=" + this.floatValus + ", intValues=" + this.intValues + ", types=" + this.types + ")";
        }

        public MachineInfos(ArrayList<MachineNameWithFloatValue> floatValus, ArrayList<MachineNameWithIntValue> intValues, ArrayList<MachineNameWithType> types) {
            Intrinsics.checkParameterIsNotNull(floatValus, "floatValus");
            Intrinsics.checkParameterIsNotNull(intValues, "intValues");
            Intrinsics.checkParameterIsNotNull(types, "types");
            this.floatValus = floatValus;
            this.intValues = intValues;
            this.types = types;
        }

        public final ArrayList<MachineNameWithFloatValue> getFloatValus() {
            return this.floatValus;
        }

        public final void setFloatValus(ArrayList<MachineNameWithFloatValue> arrayList) {
            Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
            this.floatValus = arrayList;
        }

        public final ArrayList<MachineNameWithIntValue> getIntValues() {
            return this.intValues;
        }

        public final void setIntValues(ArrayList<MachineNameWithIntValue> arrayList) {
            Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
            this.intValues = arrayList;
        }

        public final ArrayList<MachineNameWithType> getTypes() {
            return this.types;
        }

        public final void setTypes(ArrayList<MachineNameWithType> arrayList) {
            Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
            this.types = arrayList;
        }
    }
}
