package com.pudutech.mirsdk.hardware;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.base.CommonKt;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.UCollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlinx.coroutines.TimeoutKt;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: MachineInfoProcess.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001:\u00013B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u001a\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001a\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\u0017H\u0002ø\u0001\u0000¢\u0006\u0004\b \u0010!J\u001a\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0007H\u0002ø\u0001\u0000¢\u0006\u0004\b$\u0010%Jh\u0010&\u001a\u00020'2*\u0010(\u001a&\u0012\u0004\u0012\u00020\u0007\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020*0)\u0012\u0004\u0012\u00020*0)2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020*0,2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020*0,H\u0086@ø\u0001\u0000ø\u0001\u0000¢\u0006\u0002\u0010.J\"\u0010/\u001a\u00020*2\u0006\u0010#\u001a\u00020\u001b2\u0006\u00100\u001a\u00020\u0017H\u0002ø\u0001\u0000¢\u0006\u0004\b1\u00102R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000eø\u0001\u0000¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/MachineInfoProcess;", "", "()V", "TAG", "", "fetchList", "", "Lkotlin/UByte;", "fetchSuccess", "", "getFetchSuccess", "()Z", "setFetchSuccess", "(Z)V", "isNewMachineTypeVersion", "setNewMachineTypeVersion", "machineInfo", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo;", "getMachineInfo", "()Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo;", "byteArray2float", "", "src", "Lkotlin/UByteArray;", "byteArray2float-GBYM_sE", "([B)F", "byteArray2int", "", "byteArray2int-GBYM_sE", "([B)I", "checkAllByteIsFF", "bytes", "checkAllByteIsFF-GBYM_sE", "([B)Z", "combineAskCmd", "id", "combineAskCmd-7apg3OU", "(B)[B", "fetchFromHardware", "Lcom/pudutech/mirsdk/hardware/MachineInfoProcess$FetchResult;", "registerCAN", "Lkotlin/Function2;", "", "unRegisterCAN", "Lkotlin/reflect/KFunction1;", "sendCAN", "(Lkotlin/jvm/functions/Function2;Lkotlin/reflect/KFunction;Lkotlin/reflect/KFunction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parseMachineInfoCAN", "byteArray", "parseMachineInfoCAN-eUNIVaU", "(I[B)V", "FetchResult", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class MachineInfoProcess {
    private final String TAG = "MachineInfo";
    private List<UByte> fetchList = new ArrayList();
    private boolean fetchSuccess;
    private boolean isNewMachineTypeVersion;
    private final MachineInfo machineInfo;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: MachineInfoProcess.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/MachineInfoProcess$FetchResult;", "", "(Ljava/lang/String;I)V", "Success", "Fail", "NotSetup", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum FetchResult {
        Success,
        Fail,
        NotSetup
    }

    public MachineInfoProcess() {
        MachineInfo machineInfo = new MachineInfo();
        this.machineInfo = machineInfo;
        machineInfo.getFloatData().put(MachineInfo.FloatInfo.machineWheelDiameter, Float.valueOf(0.44f));
        this.machineInfo.getFloatData().put(MachineInfo.FloatInfo.machineWheelPerimeter, Float.valueOf(0.14f));
        this.machineInfo.getFloatData().put(MachineInfo.FloatInfo.machineWheelbase, Float.valueOf(0.3836f));
        HashMap<MachineInfo.FloatInfo, Float> floatData = this.machineInfo.getFloatData();
        MachineInfo.FloatInfo floatInfo = MachineInfo.FloatInfo.encoderPulsePerCircle;
        Float valueOf = Float.valueOf(0.1f);
        floatData.put(floatInfo, valueOf);
        this.machineInfo.getFloatData().put(MachineInfo.FloatInfo.encoderSampleTimesPerPulse, valueOf);
        this.machineInfo.getFloatData().put(MachineInfo.FloatInfo.reductionRatio, valueOf);
        this.machineInfo.getIntData().put(MachineInfo.IntInfo.isEncoderCountInv, 1);
        this.machineInfo.getIntData().put(MachineInfo.IntInfo.uwbTagPcbMajorVersion, 0);
        this.machineInfo.getIntData().put(MachineInfo.IntInfo.uwbTagPcbMinorVersion, 0);
        this.machineInfo.getIntData().put(MachineInfo.IntInfo.chassisPcbMajorVersion, 0);
        this.machineInfo.getIntData().put(MachineInfo.IntInfo.chassisPcbMinorVersion, 0);
        this.machineInfo.getIntData().put(MachineInfo.IntInfo.infraredSensorVersion, 1);
        this.machineInfo.getIntData().put(MachineInfo.IntInfo.ldsSensorVersion, 2);
        this.machineInfo.getIntData().put(MachineInfo.IntInfo.motorVersion, 1);
        this.machineInfo.getIntData().put(MachineInfo.IntInfo.weighSensorVersion, 0);
        this.machineInfo.getIntData().put(MachineInfo.IntInfo.batteryVersion, 0);
        this.machineInfo.getIntData().put(MachineInfo.IntInfo.uwbTagPcbMPD_year, 0);
        this.machineInfo.getIntData().put(MachineInfo.IntInfo.uwbTagPcbMPD_month, 0);
        this.machineInfo.getIntData().put(MachineInfo.IntInfo.uwbTagPcbMPD_day, 0);
        this.machineInfo.getIntData().put(MachineInfo.IntInfo.chassisPcbMPD_year, 0);
        this.machineInfo.getIntData().put(MachineInfo.IntInfo.chassisPcbMPD_month, 0);
        this.machineInfo.getIntData().put(MachineInfo.IntInfo.chassisPcbMPD_day, 0);
        byte b = (byte) 0;
        this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.ESP32Version, UByte.m4522boximpl(UByte.m4528constructorimpl(b)));
        this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.RGBDVersion, UByte.m4522boximpl(UByte.m4528constructorimpl(b)));
        this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.MachineType, UByte.m4522boximpl(UByte.m4528constructorimpl(b)));
        this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.AudioVersion, UByte.m4522boximpl(UByte.m4528constructorimpl(b)));
        this.machineInfo.getByte24Data().put(MachineInfo.Byte24Info.LoraVersion, UByte.m4522boximpl(UByte.m4528constructorimpl(b)));
        this.machineInfo.getByte24Data().put(MachineInfo.Byte24Info.ScanCodeDevice, UByte.m4522boximpl(UByte.m4528constructorimpl(b)));
        this.machineInfo.getByte24Data().put(MachineInfo.Byte24Info.MonocularCamera, UByte.m4522boximpl(UByte.m4528constructorimpl(b)));
        this.machineInfo.getByte24Data().put(MachineInfo.Byte24Info.Slamcore, UByte.m4522boximpl(UByte.m4528constructorimpl(b)));
    }

    public final boolean getFetchSuccess() {
        return this.fetchSuccess;
    }

    public final void setFetchSuccess(boolean z) {
        this.fetchSuccess = z;
    }

    /* renamed from: isNewMachineTypeVersion, reason: from getter */
    public final boolean getIsNewMachineTypeVersion() {
        return this.isNewMachineTypeVersion;
    }

    public final void setNewMachineTypeVersion(boolean z) {
        this.isNewMachineTypeVersion = z;
    }

    public final MachineInfo getMachineInfo() {
        return this.machineInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: parseMachineInfoCAN-eUNIVaU, reason: not valid java name */
    public final void m4416parseMachineInfoCANeUNIVaU(int id, byte[] byteArray) {
        MachineInfo.IntInfo intInfo;
        MachineInfo.FloatInfo floatInfo;
        if (UByteArray.m4577getimpl(byteArray, 0) == UByte.m4528constructorimpl((byte) 83)) {
            byte m4577getimpl = UByteArray.m4577getimpl(byteArray, 1);
            MachineInfo.FloatInfo[] values = MachineInfo.FloatInfo.values();
            int length = values.length;
            int i = 0;
            while (true) {
                intInfo = null;
                if (i >= length) {
                    floatInfo = null;
                    break;
                }
                floatInfo = values[i];
                if (floatInfo.getId() == m4577getimpl) {
                    break;
                } else {
                    i++;
                }
            }
            MachineInfo.IntInfo[] values2 = MachineInfo.IntInfo.values();
            int length2 = values2.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length2) {
                    break;
                }
                MachineInfo.IntInfo intInfo2 = values2[i2];
                if (intInfo2.getId() == m4577getimpl) {
                    intInfo = intInfo2;
                    break;
                }
                i2++;
            }
            if (floatInfo != null || intInfo != null) {
                if (UByteArray.m4577getimpl(byteArray, 6) != UByte.m4528constructorimpl((byte) 2)) {
                    Pdlog.m3277w(this.TAG, "index:" + UByte.m4563toStringimpl(m4577getimpl) + " byte6:" + UByte.m4563toStringimpl(UByteArray.m4577getimpl(byteArray, 6)) + " is not 0x02");
                    return;
                }
                synchronized (this.fetchList) {
                    this.fetchList.remove(UByte.m4522boximpl(m4577getimpl));
                }
                byte[] m4572constructorimpl = UByteArray.m4572constructorimpl(ArraysKt.copyOfRange(byteArray, 2, 6));
                if (m4414checkAllByteIsFFGBYM_sE(m4572constructorimpl)) {
                    Pdlog.m3277w(this.TAG, "index:" + UByte.m4563toStringimpl(m4577getimpl) + " is not set");
                    return;
                }
                this.fetchSuccess = true;
                if (floatInfo != null) {
                    float m4412byteArray2floatGBYM_sE = m4412byteArray2floatGBYM_sE(m4572constructorimpl);
                    this.machineInfo.getFloatData().put(floatInfo, Float.valueOf(m4412byteArray2floatGBYM_sE));
                    Pdlog.m3275i(this.TAG, "success fetch " + floatInfo + ':' + m4412byteArray2floatGBYM_sE);
                    return;
                }
                if (intInfo != null) {
                    int m4413byteArray2intGBYM_sE = m4413byteArray2intGBYM_sE(m4572constructorimpl);
                    this.machineInfo.getIntData().put(intInfo, Integer.valueOf(m4413byteArray2intGBYM_sE));
                    Pdlog.m3275i(this.TAG, "success fetch " + intInfo + ':' + m4413byteArray2intGBYM_sE);
                    return;
                }
                return;
            }
            int i3 = m4577getimpl & 255;
            if (i3 != 23) {
                if (i3 != 24) {
                    if (i3 == 27) {
                        synchronized (this.fetchList) {
                            this.fetchList.remove(UByte.m4522boximpl(m4577getimpl));
                        }
                        byte[] uByteArray = UCollectionsKt.toUByteArray(UByteArray.m4570boximpl(byteArray));
                        Pdlog.m3273d(this.TAG, "new machine type " + CommonKt.m4421toHexStringGBYM_sE(uByteArray));
                        int m4577getimpl2 = (((UByteArray.m4577getimpl(uByteArray, 4) & 255) & 255) << 8) | (UByteArray.m4577getimpl(uByteArray, 5) & 255);
                        int m4577getimpl3 = UByteArray.m4577getimpl(uByteArray, 3) & 255;
                        int m4577getimpl4 = UByteArray.m4577getimpl(uByteArray, 2) & 255;
                        Pdlog.m3273d(this.TAG, "new machine type mode:" + m4577getimpl2 + " , mainVersion:" + m4577getimpl3 + " ,minorVersion:" + m4577getimpl4);
                        if (m4577getimpl2 != 0) {
                            this.machineInfo.getProductMachineType().setModel(MachineModel.INSTANCE.fromId(m4577getimpl2));
                            this.machineInfo.getProductMachineType().setMainVersion(m4577getimpl3);
                            this.machineInfo.getProductMachineType().setMinorVersion(m4577getimpl4);
                            this.isNewMachineTypeVersion = true;
                            Pdlog.m3273d(this.TAG, "new machine type mode:" + this.machineInfo.getProductMachineType().getModel() + " , mainVersion:" + m4577getimpl3 + " ,minorVersion:" + m4577getimpl4);
                            return;
                        }
                        return;
                    }
                    Pdlog.m3277w(this.TAG, "unknown machine info index:" + UByte.m4563toStringimpl(m4577getimpl));
                    return;
                }
                synchronized (this.fetchList) {
                    this.fetchList.remove(UByte.m4522boximpl(m4577getimpl));
                }
                byte[] m4572constructorimpl2 = UByteArray.m4572constructorimpl(ArraysKt.copyOfRange(byteArray, 2, 6));
                byte m4577getimpl5 = UByteArray.m4577getimpl(m4572constructorimpl2, MachineInfo.Byte24Info.LoraVersion.getId() & 255);
                byte b = (byte) 0;
                if (m4577getimpl5 == UByte.m4528constructorimpl(b)) {
                    Pdlog.m3273d(this.TAG, "no lora device");
                    this.machineInfo.getByte24Data().put(MachineInfo.Byte24Info.LoraVersion, UByte.m4522boximpl(MachineInfo.LoraType.NoDevice.getId()));
                } else if (m4577getimpl5 == UByte.m4528constructorimpl((byte) 1)) {
                    Pdlog.m3273d(this.TAG, "lora device H4_490");
                    this.machineInfo.getByte24Data().put(MachineInfo.Byte24Info.LoraVersion, UByte.m4522boximpl(MachineInfo.LoraType.H4_490.getId()));
                } else if (m4577getimpl5 == UByte.m4528constructorimpl((byte) 2)) {
                    Pdlog.m3273d(this.TAG, "lora device H8_868");
                    this.machineInfo.getByte24Data().put(MachineInfo.Byte24Info.LoraVersion, UByte.m4522boximpl(MachineInfo.LoraType.H8_868.getId()));
                } else if (m4577getimpl5 == UByte.m4528constructorimpl((byte) 3)) {
                    Pdlog.m3273d(this.TAG, "lora device H9_915");
                    this.machineInfo.getByte24Data().put(MachineInfo.Byte24Info.LoraVersion, UByte.m4522boximpl(MachineInfo.LoraType.H9_915.getId()));
                } else if (m4577getimpl5 == UByte.m4528constructorimpl((byte) 4)) {
                    Pdlog.m3273d(this.TAG, "lora device H9K_920");
                    this.machineInfo.getByte24Data().put(MachineInfo.Byte24Info.LoraVersion, UByte.m4522boximpl(MachineInfo.LoraType.H9K_920.getId()));
                } else {
                    Pdlog.m3277w(this.TAG, "unknown lora type " + UByte.m4563toStringimpl(UByteArray.m4577getimpl(m4572constructorimpl2, MachineInfo.Byte24Info.LoraVersion.getId() & 255)));
                }
                byte m4577getimpl6 = UByteArray.m4577getimpl(m4572constructorimpl2, MachineInfo.Byte24Info.ScanCodeDevice.getId() & 255);
                if (m4577getimpl6 == UByte.m4528constructorimpl(b)) {
                    Pdlog.m3273d(this.TAG, "no scan code device");
                    this.machineInfo.getByte24Data().put(MachineInfo.Byte24Info.ScanCodeDevice, UByte.m4522boximpl(MachineInfo.ScanCodeType.NoDevice.getId()));
                } else if (m4577getimpl6 == UByte.m4528constructorimpl((byte) 1)) {
                    Pdlog.m3273d(this.TAG, "exist scan code device");
                    this.machineInfo.getByte24Data().put(MachineInfo.Byte24Info.ScanCodeDevice, UByte.m4522boximpl(MachineInfo.ScanCodeType.Default.getId()));
                } else {
                    Pdlog.m3277w(this.TAG, "unknown scan code device status " + UByte.m4563toStringimpl(UByteArray.m4577getimpl(m4572constructorimpl2, MachineInfo.Byte24Info.ScanCodeDevice.getId() & 255)));
                }
                byte m4577getimpl7 = UByteArray.m4577getimpl(m4572constructorimpl2, MachineInfo.Byte24Info.MonocularCamera.getId() & 255);
                if (m4577getimpl7 == UByte.m4528constructorimpl(b)) {
                    Pdlog.m3273d(this.TAG, "no monocular camera");
                    this.machineInfo.getByte24Data().put(MachineInfo.Byte24Info.MonocularCamera, UByte.m4522boximpl(MachineInfo.MonocularType.NoDevice.getId()));
                } else if (m4577getimpl7 == UByte.m4528constructorimpl((byte) 1)) {
                    Pdlog.m3273d(this.TAG, "exist xinyoukang monocular camera");
                    this.machineInfo.getByte24Data().put(MachineInfo.Byte24Info.MonocularCamera, UByte.m4522boximpl(MachineInfo.MonocularType.XinYouKang.getId()));
                } else if (m4577getimpl7 == UByte.m4528constructorimpl((byte) 2)) {
                    Pdlog.m3273d(this.TAG, "exist YuXiang monocular camera");
                    this.machineInfo.getByte24Data().put(MachineInfo.Byte24Info.MonocularCamera, UByte.m4522boximpl(MachineInfo.MonocularType.YuXiang.getId()));
                } else {
                    Pdlog.m3277w(this.TAG, "unknown monocular status " + UByte.m4563toStringimpl(UByteArray.m4577getimpl(m4572constructorimpl2, MachineInfo.Byte24Info.MonocularCamera.getId() & 255)));
                }
                byte m4577getimpl8 = UByteArray.m4577getimpl(m4572constructorimpl2, MachineInfo.Byte24Info.Slamcore.getId() & 255);
                if (m4577getimpl8 == UByte.m4528constructorimpl(b)) {
                    Pdlog.m3273d(this.TAG, "no slamcore");
                    this.machineInfo.getByte24Data().put(MachineInfo.Byte24Info.Slamcore, UByte.m4522boximpl(MachineInfo.SlamcoreType.NoDevice.getId()));
                    return;
                } else {
                    if (m4577getimpl8 == UByte.m4528constructorimpl((byte) 1)) {
                        Pdlog.m3273d(this.TAG, "exist slamcore");
                        this.machineInfo.getByte24Data().put(MachineInfo.Byte24Info.Slamcore, UByte.m4522boximpl(MachineInfo.SlamcoreType.Default.getId()));
                        return;
                    }
                    Pdlog.m3277w(this.TAG, "unknown slamcore status " + UByte.m4563toStringimpl(UByteArray.m4577getimpl(m4572constructorimpl2, MachineInfo.Byte24Info.Slamcore.getId() & 255)));
                    return;
                }
            }
            synchronized (this.fetchList) {
                this.fetchList.remove(UByte.m4522boximpl(m4577getimpl));
            }
            byte[] m4572constructorimpl3 = UByteArray.m4572constructorimpl(ArraysKt.copyOfRange(byteArray, 2, 6));
            byte m4577getimpl9 = UByteArray.m4577getimpl(m4572constructorimpl3, MachineInfo.Byte23Info.ESP32Version.getId() & 255);
            if (m4577getimpl9 == MachineInfo.ESP32Type.NODevice.getId()) {
                Pdlog.m3273d(this.TAG, "no ESP32 device");
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.ESP32Version, UByte.m4522boximpl(MachineInfo.ESP32Type.NODevice.getId()));
            } else if (m4577getimpl9 == MachineInfo.ESP32Type.SingleDevice.getId()) {
                Pdlog.m3273d(this.TAG, "Single ESP32 device");
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.ESP32Version, UByte.m4522boximpl(MachineInfo.ESP32Type.SingleDevice.getId()));
            } else if (m4577getimpl9 == MachineInfo.ESP32Type.IntegrationSample.getId()) {
                Pdlog.m3273d(this.TAG, "Integration ESP32 CP2102N device");
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.ESP32Version, UByte.m4522boximpl(MachineInfo.ESP32Type.IntegrationSample.getId()));
            } else if (m4577getimpl9 == MachineInfo.ESP32Type.IntegrationFactory.getId()) {
                Pdlog.m3273d(this.TAG, "Integration ESP32 CP2102N device");
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.ESP32Version, UByte.m4522boximpl(MachineInfo.ESP32Type.IntegrationFactory.getId()));
            } else if (m4577getimpl9 == MachineInfo.ESP32Type.EasyNode.getId()) {
                Pdlog.m3273d(this.TAG, "EasyNode device");
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.ESP32Version, UByte.m4522boximpl(MachineInfo.ESP32Type.EasyNode.getId()));
            } else {
                Pdlog.m3277w(this.TAG, "unknown esp type");
            }
            byte m4577getimpl10 = UByteArray.m4577getimpl(m4572constructorimpl3, MachineInfo.Byte23Info.RGBDVersion.getId() & 255);
            byte b2 = (byte) 0;
            if (m4577getimpl10 == UByte.m4528constructorimpl(b2)) {
                Pdlog.m3273d(this.TAG, "No RGBD Device");
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.RGBDVersion, UByte.m4522boximpl(MachineInfo.RGBDType.NODevice.getId()));
            } else if (m4577getimpl10 == UByte.m4528constructorimpl((byte) 1)) {
                Pdlog.m3273d(this.TAG, "Two RGBD Device");
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.RGBDVersion, UByte.m4522boximpl(MachineInfo.RGBDType.TwoDevice.getId()));
            } else if (m4577getimpl10 == UByte.m4528constructorimpl((byte) 2)) {
                Pdlog.m3273d(this.TAG, "Three RGBD Device");
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.RGBDVersion, UByte.m4522boximpl(MachineInfo.RGBDType.ThreeDevice.getId()));
            } else if (m4577getimpl10 == UByte.m4528constructorimpl((byte) 3)) {
                Pdlog.m3273d(this.TAG, "Single Realsense RGBD Device");
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.RGBDVersion, UByte.m4522boximpl(MachineInfo.RGBDType.SingleRealsenseDevice.getId()));
            } else if (m4577getimpl10 == UByte.m4528constructorimpl((byte) 4)) {
                Pdlog.m3273d(this.TAG, "TwoDeviceD415");
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.RGBDVersion, UByte.m4522boximpl(MachineInfo.RGBDType.TwoDeviceD415.getId()));
            } else if (m4577getimpl10 == UByte.m4528constructorimpl((byte) 5)) {
                Pdlog.m3273d(this.TAG, "AngStrongAndRealsense");
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.RGBDVersion, UByte.m4522boximpl(MachineInfo.RGBDType.AngStrongAndRealsense.getId()));
            } else if (m4577getimpl10 == UByte.m4528constructorimpl((byte) 6)) {
                Pdlog.m3273d(this.TAG, "TwoDeviceUpDown");
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.RGBDVersion, UByte.m4522boximpl(MachineInfo.RGBDType.TwoDeviceUpDown.getId()));
            } else if (m4577getimpl10 == UByte.m4528constructorimpl((byte) 7)) {
                Pdlog.m3273d(this.TAG, "UpOrbDownRealsenseDevice");
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.RGBDVersion, UByte.m4522boximpl(MachineInfo.RGBDType.UpOrbDownRealsenseDevice.getId()));
            } else {
                Pdlog.m3277w(this.TAG, "unknown rgbd type");
            }
            byte m4577getimpl11 = UByteArray.m4577getimpl(m4572constructorimpl3, MachineInfo.Byte23Info.MachineType.getId() & 255);
            if (m4577getimpl11 == MachineInfo.ObsoleteProductMachineType.BellabotV0.getId()) {
                Pdlog.m3273d(this.TAG, "BellaBot V0 Machine");
                if (!this.isNewMachineTypeVersion) {
                    this.machineInfo.getProductMachineType().setModel(MachineModel.BellaBot);
                    this.machineInfo.getProductMachineType().setMainVersion(0);
                    this.machineInfo.getProductMachineType().setMinorVersion(0);
                }
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.MachineType, UByte.m4522boximpl(MachineInfo.ObsoleteProductMachineType.BellabotV0.getId()));
            } else if (m4577getimpl11 == MachineInfo.ObsoleteProductMachineType.BellabotV1.getId()) {
                Pdlog.m3273d(this.TAG, "BellaBot V1 Machine");
                if (!this.isNewMachineTypeVersion) {
                    this.machineInfo.getProductMachineType().setModel(MachineModel.BellaBot);
                    this.machineInfo.getProductMachineType().setMainVersion(0);
                    this.machineInfo.getProductMachineType().setMinorVersion(1);
                }
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.MachineType, UByte.m4522boximpl(MachineInfo.ObsoleteProductMachineType.BellabotV1.getId()));
            } else if (m4577getimpl11 == MachineInfo.ObsoleteProductMachineType.BellabotV2.getId()) {
                Pdlog.m3273d(this.TAG, "BellaBot V2 Machine");
                if (!this.isNewMachineTypeVersion) {
                    this.machineInfo.getProductMachineType().setModel(MachineModel.BellaBot);
                    this.machineInfo.getProductMachineType().setMainVersion(0);
                    this.machineInfo.getProductMachineType().setMinorVersion(2);
                }
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.MachineType, UByte.m4522boximpl(MachineInfo.ObsoleteProductMachineType.BellabotV2.getId()));
            } else if (m4577getimpl11 == MachineInfo.ObsoleteProductMachineType.BellabotV3.getId()) {
                Pdlog.m3273d(this.TAG, "BellaBot V3 Machine");
                if (!this.isNewMachineTypeVersion) {
                    this.machineInfo.getProductMachineType().setModel(MachineModel.BellaBot);
                    this.machineInfo.getProductMachineType().setMainVersion(0);
                    this.machineInfo.getProductMachineType().setMinorVersion(3);
                }
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.MachineType, UByte.m4522boximpl(MachineInfo.ObsoleteProductMachineType.BellabotV3.getId()));
            } else if (m4577getimpl11 == MachineInfo.ObsoleteProductMachineType.BellabotEnclosed.getId()) {
                Pdlog.m3273d(this.TAG, "BellaBot Enclosed Machine");
                if (!this.isNewMachineTypeVersion) {
                    this.machineInfo.getProductMachineType().setModel(MachineModel.BellaBot);
                    this.machineInfo.getProductMachineType().setMainVersion(2);
                    this.machineInfo.getProductMachineType().setMinorVersion(0);
                }
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.MachineType, UByte.m4522boximpl(MachineInfo.ObsoleteProductMachineType.BellabotEnclosed.getId()));
            } else if (m4577getimpl11 == MachineInfo.ObsoleteProductMachineType.RecycleDog.getId()) {
                Pdlog.m3273d(this.TAG, "RecycleDog Machine");
                if (!this.isNewMachineTypeVersion) {
                    this.machineInfo.getProductMachineType().setModel(MachineModel.RecycleDog);
                    this.machineInfo.getProductMachineType().setMainVersion(0);
                    this.machineInfo.getProductMachineType().setMinorVersion(1);
                }
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.MachineType, UByte.m4522boximpl(MachineInfo.ObsoleteProductMachineType.RecycleDog.getId()));
            } else if (m4577getimpl11 == MachineInfo.ObsoleteProductMachineType.RecycleDogV2.getId()) {
                Pdlog.m3273d(this.TAG, "RecycleDogV2 Machine");
                if (!this.isNewMachineTypeVersion) {
                    this.machineInfo.getProductMachineType().setModel(MachineModel.RecycleDog);
                    this.machineInfo.getProductMachineType().setMainVersion(0);
                    this.machineInfo.getProductMachineType().setMinorVersion(2);
                }
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.MachineType, UByte.m4522boximpl(MachineInfo.ObsoleteProductMachineType.RecycleDogV2.getId()));
            } else if (m4577getimpl11 == MachineInfo.ObsoleteProductMachineType.Puductor.getId()) {
                Pdlog.m3273d(this.TAG, "Puductor Machine");
                if (!this.isNewMachineTypeVersion) {
                    this.machineInfo.getProductMachineType().setModel(MachineModel.Puductor);
                    this.machineInfo.getProductMachineType().setMainVersion(0);
                    this.machineInfo.getProductMachineType().setMinorVersion(0);
                }
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.MachineType, UByte.m4522boximpl(MachineInfo.ObsoleteProductMachineType.Puductor.getId()));
            } else if (m4577getimpl11 == MachineInfo.ObsoleteProductMachineType.Hls.getId()) {
                Pdlog.m3273d(this.TAG, "Hls Machine");
                if (!this.isNewMachineTypeVersion) {
                    this.machineInfo.getProductMachineType().setModel(MachineModel.Hls);
                    this.machineInfo.getProductMachineType().setMainVersion(0);
                    this.machineInfo.getProductMachineType().setMinorVersion(0);
                }
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.MachineType, UByte.m4522boximpl(MachineInfo.ObsoleteProductMachineType.Hls.getId()));
            } else {
                Pdlog.m3277w(this.TAG, "unknown machine type");
            }
            byte m4577getimpl12 = UByteArray.m4577getimpl(m4572constructorimpl3, MachineInfo.Byte23Info.AudioVersion.getId() & 255);
            if (m4577getimpl12 == UByte.m4528constructorimpl(b2)) {
                Pdlog.m3273d(this.TAG, "Default Audio");
                this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.AudioVersion, UByte.m4522boximpl(MachineInfo.AudioType.Default.getId()));
            } else {
                if (m4577getimpl12 == UByte.m4528constructorimpl((byte) 1)) {
                    Pdlog.m3273d(this.TAG, "SongLe Audio");
                    this.machineInfo.getByte23Data().put(MachineInfo.Byte23Info.AudioVersion, UByte.m4522boximpl(MachineInfo.AudioType.SongLe.getId()));
                    return;
                }
                Pdlog.m3277w(this.TAG, "unknown audioversion type " + UByte.m4563toStringimpl(UByteArray.m4577getimpl(m4572constructorimpl3, MachineInfo.Byte23Info.AudioVersion.getId() & 255)));
            }
        }
    }

    /* renamed from: checkAllByteIsFF-GBYM_sE, reason: not valid java name */
    private final boolean m4414checkAllByteIsFFGBYM_sE(byte[] bytes) {
        for (byte b : bytes) {
            if (b != UByte.m4528constructorimpl((byte) 255)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: byteArray2int-GBYM_sE, reason: not valid java name */
    private final int m4413byteArray2intGBYM_sE(byte[] src) {
        byte[] copyOf = Arrays.copyOf(src, src.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        ByteBuffer order = ByteBuffer.wrap(copyOf).order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkExpressionValueIsNotNull(order, "ByteBuffer.wrap(src.toBy…der(ByteOrder.BIG_ENDIAN)");
        return order.getInt();
    }

    /* renamed from: byteArray2float-GBYM_sE, reason: not valid java name */
    private final float m4412byteArray2floatGBYM_sE(byte[] src) {
        byte[] copyOf = Arrays.copyOf(src, src.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        ByteBuffer order = ByteBuffer.wrap(copyOf).order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkExpressionValueIsNotNull(order, "ByteBuffer.wrap(src.toBy…der(ByteOrder.BIG_ENDIAN)");
        return order.getFloat();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: combineAskCmd-7apg3OU, reason: not valid java name */
    public final byte[] m4415combineAskCmd7apg3OU(byte id) {
        byte[] m4571constructorimpl = UByteArray.m4571constructorimpl(7);
        UByteArray.m4582setVurrAj0(m4571constructorimpl, 0, (byte) 0);
        UByteArray.m4582setVurrAj0(m4571constructorimpl, 1, TarConstants.LF_GNUTYPE_SPARSE);
        UByteArray.m4582setVurrAj0(m4571constructorimpl, 2, id);
        UByteArray.m4582setVurrAj0(m4571constructorimpl, 3, (byte) 0);
        UByteArray.m4582setVurrAj0(m4571constructorimpl, 4, (byte) 0);
        UByteArray.m4582setVurrAj0(m4571constructorimpl, 5, (byte) 0);
        UByteArray.m4582setVurrAj0(m4571constructorimpl, 6, (byte) 2);
        return m4571constructorimpl;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object fetchFromHardware(Function2<? super UByte, ? super Function2<? super Integer, ? super UByteArray, Unit>, Unit> function2, KFunction<Unit> kFunction, KFunction<Unit> kFunction2, Continuation<? super FetchResult> continuation) {
        MachineInfoProcess$fetchFromHardware$1 machineInfoProcess$fetchFromHardware$1;
        Object obj;
        int i;
        MachineInfoProcess machineInfoProcess;
        if (continuation instanceof MachineInfoProcess$fetchFromHardware$1) {
            machineInfoProcess$fetchFromHardware$1 = (MachineInfoProcess$fetchFromHardware$1) continuation;
            if ((machineInfoProcess$fetchFromHardware$1.label & Integer.MIN_VALUE) != 0) {
                machineInfoProcess$fetchFromHardware$1.label -= Integer.MIN_VALUE;
                obj = machineInfoProcess$fetchFromHardware$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = machineInfoProcess$fetchFromHardware$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    function2.invoke(UByte.m4522boximpl(TarConstants.LF_GNUTYPE_SPARSE), new MachineInfoProcess$fetchFromHardware$2(this));
                    MachineInfoProcess$fetchFromHardware$fetchResult$1 machineInfoProcess$fetchFromHardware$fetchResult$1 = new MachineInfoProcess$fetchFromHardware$fetchResult$1(this, kFunction2, null);
                    machineInfoProcess$fetchFromHardware$1.L$0 = this;
                    machineInfoProcess$fetchFromHardware$1.L$1 = function2;
                    machineInfoProcess$fetchFromHardware$1.L$2 = kFunction;
                    machineInfoProcess$fetchFromHardware$1.L$3 = kFunction2;
                    machineInfoProcess$fetchFromHardware$1.label = 1;
                    obj = TimeoutKt.withTimeoutOrNull(10000L, machineInfoProcess$fetchFromHardware$fetchResult$1, machineInfoProcess$fetchFromHardware$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    machineInfoProcess = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    kFunction = (KFunction) machineInfoProcess$fetchFromHardware$1.L$2;
                    machineInfoProcess = (MachineInfoProcess) machineInfoProcess$fetchFromHardware$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                ((Function1) kFunction).invoke(UByte.m4522boximpl(TarConstants.LF_GNUTYPE_SPARSE));
                if (!Intrinsics.areEqual((Boolean) obj, Boxing.boxBoolean(true))) {
                    if (machineInfoProcess.fetchSuccess) {
                        Pdlog.m3275i(machineInfoProcess.TAG, "fetch success");
                        return FetchResult.Success;
                    }
                    Pdlog.m3277w(machineInfoProcess.TAG, "machine info empty, need setup first");
                    return FetchResult.NotSetup;
                }
                Pdlog.m3277w(machineInfoProcess.TAG, "machine info fetch fail");
                return FetchResult.Fail;
            }
        }
        machineInfoProcess$fetchFromHardware$1 = new MachineInfoProcess$fetchFromHardware$1(this, continuation);
        obj = machineInfoProcess$fetchFromHardware$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = machineInfoProcess$fetchFromHardware$1.label;
        if (i != 0) {
        }
        ((Function1) kFunction).invoke(UByte.m4522boximpl(TarConstants.LF_GNUTYPE_SPARSE));
        if (!Intrinsics.areEqual((Boolean) obj, Boxing.boxBoolean(true))) {
        }
    }
}
