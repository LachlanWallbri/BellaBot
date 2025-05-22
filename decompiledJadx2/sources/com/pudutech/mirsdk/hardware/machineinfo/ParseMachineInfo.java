package com.pudutech.mirsdk.hardware.machineinfo;

import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import kotlin.Metadata;
import kotlin.UByte;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ParseMachineInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000fJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/machineinfo/ParseMachineInfo;", "", "()V", "parseAudioType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$AudioType;", "id", "", "parseESP", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$ESP32Type;", "parseFboardType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$HeadType;", "parseLoraType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$LoraType;", "parseMachineModelType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "", "parseMonocularType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$MonocularType;", "parseProductType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$ObsoleteProductMachineType;", "parseRGBD", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$RGBDType;", "parseScanCodeType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$ScanCodeType;", "parseSlamCoreType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$SlamcoreType;", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ParseMachineInfo {
    public static final ParseMachineInfo INSTANCE = new ParseMachineInfo();

    private ParseMachineInfo() {
    }

    public final MachineInfo.ESP32Type parseESP(byte id) {
        return MachineInfo.ESP32Type.INSTANCE.m4439valueOf3swpYEE(UByte.m4522boximpl(UByte.m4528constructorimpl(id)));
    }

    public final MachineInfo.RGBDType parseRGBD(byte id) {
        return MachineInfo.RGBDType.INSTANCE.m4455valueOf3swpYEE(UByte.m4522boximpl(UByte.m4528constructorimpl(id)));
    }

    public final MachineInfo.ObsoleteProductMachineType parseProductType(byte id) {
        return MachineInfo.ObsoleteProductMachineType.INSTANCE.m4453valueOf3swpYEE(UByte.m4522boximpl(UByte.m4528constructorimpl(id)));
    }

    public final MachineInfo.AudioType parseAudioType(byte id) {
        return MachineInfo.AudioType.INSTANCE.m4434valueOf3swpYEE(UByte.m4522boximpl(UByte.m4528constructorimpl(id)));
    }

    public final MachineInfo.LoraType parseLoraType(byte id) {
        return MachineInfo.LoraType.INSTANCE.m4447valueOf3swpYEE(UByte.m4522boximpl(UByte.m4528constructorimpl(id)));
    }

    public final MachineInfo.ScanCodeType parseScanCodeType(byte id) {
        return MachineInfo.ScanCodeType.INSTANCE.m4456valueOf3swpYEE(UByte.m4522boximpl(UByte.m4528constructorimpl(id)));
    }

    public final MachineInfo.MonocularType parseMonocularType(byte id) {
        return MachineInfo.MonocularType.INSTANCE.m4451valueOf3swpYEE(UByte.m4522boximpl(UByte.m4528constructorimpl(id)));
    }

    public final MachineInfo.SlamcoreType parseSlamCoreType(byte id) {
        return MachineInfo.SlamcoreType.INSTANCE.m4457valueOf3swpYEE(UByte.m4522boximpl(UByte.m4528constructorimpl(id)));
    }

    public final MachineModel parseMachineModelType(int id) {
        return MachineModel.INSTANCE.fromId(id);
    }

    public final MachineInfo.HeadType parseFboardType(byte id) {
        return MachineInfo.HeadType.INSTANCE.m4442valueOf3swpYEE(UByte.m4522boximpl(UByte.m4528constructorimpl(id)));
    }
}
