package com.pudutech.mirsdkwrap.lib.move.bean;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveErrorClassifyDefine.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0006\u0010\u000e\u001a\u00020\u000f\u001a\n\u0010\u0010\u001a\u00020\u0002*\u00020\u0011\"7\u0010\u0000\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\"!\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\f¨\u0006\u0012"}, m3961d2 = {"ErrorProcessMap", "Ljava/util/HashMap;", "", "Lcom/pudutech/mirsdkwrap/lib/move/bean/Classify;", "Lkotlin/collections/HashMap;", "getErrorProcessMap", "()Ljava/util/HashMap;", "ErrorProcessMap$delegate", "Lkotlin/Lazy;", "noDetailNeeded", "", "getNoDetailNeeded", "()Ljava/util/List;", "noDetailNeeded$delegate", "main", "", "toProcessKey", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveError;", "module_robot_mirsdk_wrapper_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MoveErrorClassifyDefineKt {
    private static final Lazy noDetailNeeded$delegate = LazyKt.lazy(new Function0<List<? extends String>>() { // from class: com.pudutech.mirsdkwrap.lib.move.bean.MoveErrorClassifyDefineKt$noDetailNeeded$2
        @Override // kotlin.jvm.functions.Function0
        public final List<? extends String> invoke() {
            return CollectionsKt.listOf((Object[]) new String[]{"LostCAN", "LostEncoder", "LostIMU", "LostBattery", "LostLidar", "LostCamera", "LostRGBD", "InternalError", "CanNotReach", "PoseNotInit", "CoreNotReady", "UnknownError", "NoDefine"});
        }
    });
    private static final Lazy ErrorProcessMap$delegate = LazyKt.lazy(new Function0<HashMap<String, Classify>>() { // from class: com.pudutech.mirsdkwrap.lib.move.bean.MoveErrorClassifyDefineKt$ErrorProcessMap$2
        @Override // kotlin.jvm.functions.Function0
        public final HashMap<String, Classify> invoke() {
            return MapsKt.hashMapOf(TuplesKt.m3968to("LostCAN_Error", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("LostEncoder_Error", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("LostIMU_Error", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("LostBattery_Error", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("LostLidar_Error", new Classify(MoveErrorProcess.AUTO_RESUME, DisplayLevel.CAUTION)), TuplesKt.m3968to("LostCamera_Error", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("LostRGBD_Error", new Classify(MoveErrorProcess.AUTO_RESUME, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorLeft_Fatal_HardwareCurrentOver", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorLeft_Fatal_MotherCurrentOver", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorLeft_Fatal_MotherVoltageOver", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorLeft_Fatal_MotherVoltageLow", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorLeft_Fatal_TemperatureOver", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorLeft_Fatal_EncoderError", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorLeft_Fatal_ABZBreak", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorLeft_Fatal_HallError", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorLeft_Fatal_CurrentZeroDriftError", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorLeft_Fatal_EepromError", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorLeft_Fatal_MotorTempOver", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorLeft_Fatal_MosTempOver", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorLeft_Fatal_OutLosePhase", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorLeft_Fatal_TaskLoadOver", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorLeft_Error_SpeedOver", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorLeft_Error_MotorStuck", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorLeft_Error_MotorOver", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorLeft_Error_PhaseCurOver", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorLeft_Error_SpeedFlowDeviation", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorLeft_Error_EmergencyKeyError", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorLeft_Error_CANBreak", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorLeft_Error_CANCmdLose", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorLeft_Fatal_MosSoftStartError", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorLeft_Error_TouchSwitchReset", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorLeft_Error_EmergencyKeyPressed", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorLeft_Event_EmergencyKeyPressed", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorLeft_Error_BumpSwitchReset", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorRight_Fatal_HardwareCurrentOver", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorRight_Fatal_MotherCurrentOver", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorRight_Fatal_MotherVoltageOver", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorRight_Fatal_MotherVoltageLow", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorRight_Fatal_TemperatureOver", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorRight_Fatal_EncoderError", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorRight_Fatal_ABZBreak", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorRight_Fatal_HallError", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorRight_Fatal_CurrentZeroDriftError", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorRight_Fatal_EepromError", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorRight_Fatal_MotorTempOver", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorRight_Fatal_MosTempOver", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorRight_Fatal_OutLosePhase", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorRight_Fatal_TaskLoadOver", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorRight_Error_SpeedOver", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorRight_Error_MotorStuck", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorRight_Error_MotorOver", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorRight_Error_PhaseCurOver", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorRight_Error_SpeedFlowDeviation", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorRight_Error_EmergencyKeyError", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorRight_Error_CANBreak", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorRight_Error_CANCmdLose", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorRight_Fatal_MosSoftStartError", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.CAUTION)), TuplesKt.m3968to("WheelErrorRight_Error_TouchSwitchReset", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorRight_Error_EmergencyKeyPressed", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorRight_Event_EmergencyKeyPressed", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("WheelErrorRight_Error_BumpSwitchReset", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("InternalError_Error", new Classify(MoveErrorProcess.REBOOT_POWER, DisplayLevel.NOTE)), TuplesKt.m3968to("LostLocalization_Error_NoImage", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("LostLocalization_Error_NoOdom", new Classify(MoveErrorProcess.RESTART_SOFTWARE, DisplayLevel.NOTE)), TuplesKt.m3968to("LostLocalization_Error_ErrorMap", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("LostLocalization_Error_NoParam", new Classify(MoveErrorProcess.RESTART_SOFTWARE, DisplayLevel.NOTE)), TuplesKt.m3968to("LostLocalization_Error_NoInit", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("LostLocalization_Error_ParamError", new Classify(MoveErrorProcess.RESTART_SOFTWARE, DisplayLevel.NOTE)), TuplesKt.m3968to("LostLocalization_Error_MapError", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("LostLocalization_Error_NoMarker", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("LostLocalization_Error_MarkerError", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("LostLocalization_Error_LaserLocateLose", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("LostLocalization_Error_ErrorMove", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("CanNotReach_Error", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("PoseNotInit_Error", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("CoreNotReady_Error", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)), TuplesKt.m3968to("UnknownError_Error", new Classify(MoveErrorProcess.RESTART_SOFTWARE, DisplayLevel.NOTE)), TuplesKt.m3968to("NoDefine_Error", new Classify(MoveErrorProcess.RESTART_SOFTWARE, DisplayLevel.NOTE)), TuplesKt.m3968to("BusinessDefine_Error_CollisionSensorTrigger", new Classify(MoveErrorProcess.TRY, DisplayLevel.NOTE)));
        }
    });

    public static final HashMap<String, Classify> getErrorProcessMap() {
        return (HashMap) ErrorProcessMap$delegate.getValue();
    }

    public static final List<String> getNoDetailNeeded() {
        return (List) noDetailNeeded$delegate.getValue();
    }

    public static final String toProcessKey(MoveError toProcessKey) {
        String str;
        Intrinsics.checkParameterIsNotNull(toProcessKey, "$this$toProcessKey");
        String str2 = toProcessKey.getError_type() + '_' + toProcessKey.getLevel();
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        if (getNoDetailNeeded().contains(toProcessKey.getError_type())) {
            str = "";
        } else {
            str = '_' + toProcessKey.getDetail();
        }
        sb.append(str);
        return sb.toString();
    }

    public static final void main() {
        for (Map.Entry<String, Classify> entry : getErrorProcessMap().entrySet()) {
            String key = entry.getKey();
            entry.getValue();
            List split$default = StringsKt.split$default((CharSequence) key, new String[]{"_"}, false, 0, 6, (Object) null);
            String str = (String) split$default.get(0);
            String str2 = (String) split$default.get(1);
            String str3 = split$default.size() > 2 ? (String) split$default.get(2) : "";
            String str4 = "adb shell \"echo '{\\\"error_type\\\":\\\"" + str + "\\\",\\\"level\\\":\\\"" + str2 + "\\\",\\\"detail\\\":\\\"" + str3 + "\\\"}' > sdcard/debug_custom_error\"";
            System.out.println((Object) str4);
            File file = new File("C:\\Users\\dell\\Desktop\\error\\single_error\\" + str + '_' + str3 + ".bat");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(str4 + "\npause");
            fileWriter.close();
        }
    }
}
