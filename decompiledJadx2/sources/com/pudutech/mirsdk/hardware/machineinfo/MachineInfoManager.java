package com.pudutech.mirsdk.hardware.machineinfo;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.activity.HardwareConnection;
import com.pudutech.mirsdk.hardware.machineinfo.HardwareConfigureHandler;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: MachineInfoManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/machineinfo/MachineInfoManager;", "", "()V", "TAG", "", "hardwareConfigureHandler", "Lcom/pudutech/mirsdk/hardware/machineinfo/HardwareConfigureHandler;", "initMachineInfo", "", "saveMachineInfo", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MachineInfoManager {
    public static final String TAG = "MachineInfoManager";
    public static final MachineInfoManager INSTANCE = new MachineInfoManager();
    private static final HardwareConfigureHandler hardwareConfigureHandler = new HardwareConfigureHandler(new HardwareConfigureHandler.Callback() { // from class: com.pudutech.mirsdk.hardware.machineinfo.MachineInfoManager$hardwareConfigureHandler$1
        @Override // com.pudutech.mirsdk.hardware.machineinfo.HardwareConfigureHandler.Callback
        public final void send(byte[] it) {
            StringBuilder sb = new StringBuilder();
            sb.append("hardwareConfigureHandler ");
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            sb.append(ArraysKt.toList(it));
            Pdlog.m3273d(MachineInfoManager.TAG, sb.toString());
            HardwareInterface hardwareInterface = HardwareConnection.INSTANCE.getInterface();
            if (hardwareInterface == null) {
                Pdlog.m3274e(MachineInfoManager.TAG, "hardwareConfigureHandler failed , hardwareInterface is null");
            } else {
                hardwareInterface.sendCAN(it);
            }
        }
    });

    private MachineInfoManager() {
    }

    public final void saveMachineInfo() {
        Pdlog.m3273d(TAG, "send info to mcu ");
        hardwareConfigureHandler.flashToMCU();
        HardwareInterface hardwareInterface = HardwareConnection.INSTANCE.getInterface();
        if (hardwareInterface == null) {
            Intrinsics.throwNpe();
        }
        hardwareInterface.refreshMachineInfo();
    }

    public final void initMachineInfo() {
        Pdlog.m3273d(TAG, "initMachineInfo");
        HardwareInterface hardwareInterface = HardwareConnection.INSTANCE.getInterface();
        if (hardwareInterface == null) {
            Intrinsics.throwNpe();
        }
        HashMap<MachineInfo.FloatInfo, Float> floatData = hardwareInterface.getMachineInfo().getFloatData();
        if (floatData != null) {
            for (Map.Entry<MachineInfo.FloatInfo, Float> entry : floatData.entrySet()) {
                HardwareConfigure.getInstance().setFloatData(entry.getKey().name(), entry.getKey().getId() & 255, entry.getValue().floatValue());
                Pdlog.m3273d(TAG, "initMachineInfo setFloatData id = " + (entry.getKey().getId() & 255) + " , value = " + entry.getValue().floatValue());
            }
        }
        HashMap<MachineInfo.IntInfo, Integer> intData = hardwareInterface.getMachineInfo().getIntData();
        if (intData != null) {
            for (Map.Entry<MachineInfo.IntInfo, Integer> entry2 : intData.entrySet()) {
                HardwareConfigure.getInstance().setIntData(entry2.getKey().name(), entry2.getKey().getId() & 255, entry2.getValue().intValue());
                Pdlog.m3273d(TAG, "initMachineInfo setIntData id = " + (entry2.getKey().getId() & 255) + " , value = " + entry2.getValue().intValue());
            }
        }
        HashMap<MachineInfo.Byte23Info, UByte> byte23Data = hardwareInterface.getMachineInfo().getByte23Data();
        if (byte23Data != null) {
            for (Map.Entry<MachineInfo.Byte23Info, UByte> entry3 : byte23Data.entrySet()) {
                HardwareConfigure.getInstance().setByteData(entry3.getKey().name(), 26 - (entry3.getKey().getId() & 255), entry3.getValue().getData());
                Pdlog.m3273d(TAG, "initMachineInfo setByte23Data id = " + (26 - (entry3.getKey().getId() & 255)) + " , value = " + UByte.m4563toStringimpl(entry3.getValue().getData()));
            }
        }
        HashMap<MachineInfo.Byte24Info, UByte> byte24Data = hardwareInterface.getMachineInfo().getByte24Data();
        if (byte24Data != null) {
            for (Map.Entry<MachineInfo.Byte24Info, UByte> entry4 : byte24Data.entrySet()) {
                HardwareConfigure.getInstance().setByteData(entry4.getKey().name(), 30 - (entry4.getKey().getId() & 255), entry4.getValue().getData());
                Pdlog.m3273d(TAG, "initMachineInfo setByte24Data id = " + (30 - (entry4.getKey().getId() & 255)) + " , value = " + UByte.m4563toStringimpl(entry4.getValue().getData()));
            }
        }
        ProductMachineType productMachineType = hardwareInterface.getMachineInfo().getProductMachineType();
        if (productMachineType != null) {
            HardwareConfigure.getInstance().setIntData("model", 33, productMachineType.getModel().getId());
            HardwareConfigure.getInstance().setIntData("mainVersion", 34, productMachineType.getMainVersion());
            HardwareConfigure.getInstance().setIntData("minorVersion", 35, productMachineType.getMinorVersion());
            Pdlog.m3273d(TAG, "productMachineType : model = " + productMachineType.getModel() + "; ");
        }
        HashMap byte28Data = hardwareInterface.getMachineInfo().getByte28Data();
        if (byte28Data != null) {
            for (Map.Entry entry5 : byte28Data.entrySet()) {
                HardwareConfigure.getInstance().setByteData(((MachineInfo.Byte28Info) entry5.getKey()).name(), 34 - (((MachineInfo.Byte28Info) entry5.getKey()).getId() & 255), ((UByte) entry5.getValue()).getData());
                Pdlog.m3273d(TAG, "test-ws initMachineInfo setByte28Data id = " + (34 - (((MachineInfo.Byte28Info) entry5.getKey()).getId() & 255)) + " , value = " + UByte.m4563toStringimpl(((UByte) entry5.getValue()).getData()));
            }
        }
    }
}
