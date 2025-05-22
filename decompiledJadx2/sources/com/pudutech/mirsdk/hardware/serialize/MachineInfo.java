package com.pudutech.mirsdk.hardware.serialize;

import android.os.Parcel;
import android.os.Parcelable;
import com.pudutech.easynodelib.EasyNode;
import com.pudutech.lidar.rplidar_50c.LDS_50C;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: MachineInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0019\b\u0007\u0018\u0000 K2\u00020\u0001:\rHIJKLMNOPQRSTB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010#\u001a\u00020\u001aH\u0016J\u0006\u0010$\u001a\u00020%J\u0013\u0010&\u001a\u0004\u0018\u00010\t2\u0006\u0010'\u001a\u00020\bø\u0001\u0000J\u0006\u0010(\u001a\u00020)J\u0015\u0010*\u001a\u0004\u0018\u00010\u00152\u0006\u0010+\u001a\u00020\u0014¢\u0006\u0002\u0010,J\u0015\u0010-\u001a\u0004\u0018\u00010\u001a2\u0006\u0010.\u001a\u00020\u0019¢\u0006\u0002\u0010/J\u0006\u00100\u001a\u000201J\u0006\u00102\u001a\u000203J\u0006\u00104\u001a\u00020\u001eJ\u0006\u00105\u001a\u000206J\u0006\u00107\u001a\u000208J\u0006\u00109\u001a\u00020:J\u0010\u0010;\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010=\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010>\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010?\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010@\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010A\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010B\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010C\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010D\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010E\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0018\u0010F\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010G\u001a\u00020\u001aH\u0016R9\u0010\u0006\u001a\u001e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007j\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t`\nX\u0086\u000eø\u0001\u0000¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR9\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\t0\u0007j\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\t`\nX\u0086\u000eø\u0001\u0000¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000eR6\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0007j\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0015`\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR6\u0010\u0018\u001a\u001e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u0007j\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a`\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\f\"\u0004\b\u001c\u0010\u000eR\u001a\u0010\u001d\u001a\u00020\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006U"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "()V", "byte23Data", "Ljava/util/HashMap;", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$Byte23Info;", "Lkotlin/UByte;", "Lkotlin/collections/HashMap;", "getByte23Data", "()Ljava/util/HashMap;", "setByte23Data", "(Ljava/util/HashMap;)V", "byte24Data", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$Byte24Info;", "getByte24Data", "setByte24Data", "floatData", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$FloatInfo;", "", "getFloatData", "setFloatData", "intData", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$IntInfo;", "", "getIntData", "setIntData", "productMachineType", "Lcom/pudutech/mirsdk/hardware/serialize/ProductMachineType;", "getProductMachineType", "()Lcom/pudutech/mirsdk/hardware/serialize/ProductMachineType;", "setProductMachineType", "(Lcom/pudutech/mirsdk/hardware/serialize/ProductMachineType;)V", "describeContents", "getAudioType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$AudioType;", "getByte23", "info", "getESPMode", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$ESP32Type;", "getFloat", "floatInfo", "(Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$FloatInfo;)Ljava/lang/Float;", "getInt", "intInfo", "(Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$IntInfo;)Ljava/lang/Integer;", "getLoraType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$LoraType;", "getMonocularDeviceType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$MonocularType;", "getProductType", "getRGBDMode", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$RGBDType;", "getScanCodeDeviceType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$ScanCodeType;", "getSlamwareType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$SlamcoreType;", "readByte23Data", "", "readByte24Data", "readFloatData", "readIntData", "readMachineType", "writeByte23Data", "writeByte24Data", "writeFloatData", "writeIntData", "writeMachineType", "writeToParcel", "flags", "AudioType", "Byte23Info", "Byte24Info", "CREATOR", "ESP32Type", "FloatInfo", "IntInfo", "LoraType", "MonocularType", "ObsoleteProductMachineType", "RGBDType", "ScanCodeType", "SlamcoreType", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class MachineInfo implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private HashMap<Byte23Info, UByte> byte23Data;
    private HashMap<Byte24Info, UByte> byte24Data;
    private HashMap<FloatInfo, Float> floatData;
    private HashMap<IntInfo, Integer> intData;
    private ProductMachineType productMachineType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$FloatInfo;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "machineWheelDiameter", "machineWheelPerimeter", "machineWheelbase", "encoderPulsePerCircle", "encoderSampleTimesPerPulse", "reductionRatio", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public enum FloatInfo {
        machineWheelDiameter((byte) 0),
        machineWheelPerimeter((byte) 1),
        machineWheelbase((byte) 2),
        encoderPulsePerCircle((byte) 3),
        encoderSampleTimesPerPulse((byte) 4),
        reductionRatio((byte) 5);

        private final byte id;

        FloatInfo(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }
    }

    public MachineInfo() {
        this.floatData = new HashMap<>();
        this.intData = new HashMap<>();
        this.byte23Data = new HashMap<>();
        this.byte24Data = new HashMap<>();
        this.productMachineType = new ProductMachineType(MachineModel.Hls, 0, 0);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$IntInfo;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "isEncoderCountInv", "uwbTagPcbMajorVersion", "uwbTagPcbMinorVersion", "chassisPcbMajorVersion", "chassisPcbMinorVersion", "infraredSensorVersion", "ldsSensorVersion", "motorVersion", "weighSensorVersion", "batteryVersion", "uwbTagPcbMPD_year", "uwbTagPcbMPD_month", "uwbTagPcbMPD_day", "chassisPcbMPD_year", "chassisPcbMPD_month", "chassisPcbMPD_day", "slamCameraVersion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public enum IntInfo {
        isEncoderCountInv((byte) 6),
        uwbTagPcbMajorVersion((byte) 7),
        uwbTagPcbMinorVersion((byte) 8),
        chassisPcbMajorVersion((byte) 9),
        chassisPcbMinorVersion((byte) 10),
        infraredSensorVersion((byte) 11),
        ldsSensorVersion((byte) 12),
        motorVersion((byte) 13),
        weighSensorVersion((byte) 14),
        batteryVersion((byte) 15),
        uwbTagPcbMPD_year((byte) 16),
        uwbTagPcbMPD_month((byte) 17),
        uwbTagPcbMPD_day((byte) 18),
        chassisPcbMPD_year((byte) 19),
        chassisPcbMPD_month((byte) 20),
        chassisPcbMPD_day((byte) 21),
        slamCameraVersion((byte) 22);

        private final byte id;

        IntInfo(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$Byte23Info;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "ESP32Version", "RGBDVersion", "MachineType", "AudioVersion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public enum Byte23Info {
        ESP32Version((byte) 3),
        RGBDVersion((byte) 2),
        MachineType((byte) 1),
        AudioVersion((byte) 0);

        private final byte id;

        Byte23Info(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$Byte24Info;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "LoraVersion", "ScanCodeDevice", "MonocularCamera", "Slamcore", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public enum Byte24Info {
        LoraVersion((byte) 3),
        ScanCodeDevice((byte) 2),
        MonocularCamera((byte) 1),
        Slamcore((byte) 0);

        private final byte id;

        Byte24Info(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$Byte28Info;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "NPUVersion", "MatrixMicVersion", "HostCoreboardVersion", "FboardVersion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum Byte28Info {
        NPUVersion((byte) 3),
        MatrixMicVersion((byte) 2),
        HostCoreboardVersion((byte) 1),
        FboardVersion((byte) 0);

        private final byte id;

        Byte28Info(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0001\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$ESP32Type;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "NODevice", "SingleDevice", "IntegrationSample", "IntegrationFactory", EasyNode.TAG, "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public enum ESP32Type {
        NODevice((byte) 0),
        SingleDevice((byte) 1),
        IntegrationSample((byte) 2),
        IntegrationFactory((byte) 3),
        EasyNode((byte) 4);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        ESP32Type(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes.dex
          classes4.dex
          classes5.dex
         */
        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$ESP32Type$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$ESP32Type;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes2.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final ESP32Type m4439valueOf3swpYEE(UByte id) {
                for (ESP32Type eSP32Type : ESP32Type.values()) {
                    if (UByte.m4534equalsimpl(eSP32Type.getId(), id)) {
                        return eSP32Type;
                    }
                }
                return null;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$Byte29Info;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "LidarCommunicate", "Lidar", "LteType", "CarbinDoorMotorType", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum Byte29Info {
        LidarCommunicate((byte) 3),
        Lidar((byte) 2),
        LteType((byte) 1),
        CarbinDoorMotorType((byte) 0);

        private final byte id;

        Byte29Info(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0086\u0001\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$RGBDType;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "NODevice", "TwoDevice", "ThreeDevice", "SingleRealsenseDevice", "TwoDeviceD415", "AngStrongAndRealsense", "TwoDeviceUpDown", "UpOrbDownRealsenseDevice", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public enum RGBDType {
        NODevice((byte) 0),
        TwoDevice((byte) 1),
        ThreeDevice((byte) 2),
        SingleRealsenseDevice((byte) 3),
        TwoDeviceD415((byte) 4),
        AngStrongAndRealsense((byte) 5),
        TwoDeviceUpDown((byte) 6),
        UpOrbDownRealsenseDevice((byte) 7);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        RGBDType(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes.dex
          classes4.dex
          classes5.dex
         */
        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$RGBDType$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$RGBDType;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes2.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final RGBDType m4455valueOf3swpYEE(UByte id) {
                for (RGBDType rGBDType : RGBDType.values()) {
                    if (UByte.m4534equalsimpl(rGBDType.getId(), id)) {
                        return rGBDType;
                    }
                }
                return null;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$Byte30Info;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "TrayBoardType", "FunctionBoardType", "ChassisBoardType", "DoorBoardType", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum Byte30Info {
        TrayBoardType((byte) 3),
        FunctionBoardType((byte) 2),
        ChassisBoardType((byte) 1),
        DoorBoardType((byte) 0);

        private final byte id;

        Byte30Info(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }
    }

    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$Byte31Info;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "LaserProjection", "VedioOutput", "DistributionAreaLight", "MagicSensor", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum Byte31Info {
        LaserProjection((byte) 3),
        VedioOutput((byte) 2),
        DistributionAreaLight((byte) 1),
        MagicSensor((byte) 0);

        private final byte id;

        Byte31Info(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0086\u0001\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u0019B\u0012\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u0016R\u0016\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$ObsoleteProductMachineType;", "", "Landroid/os/Parcelable;", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Hls", "BellabotV0", "BellabotV1", "RecycleDog", "BellabotV2", "BellabotEnclosed", "Puductor", "RecycleDogV2", "BellabotV3", "CREATOR", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public enum ObsoleteProductMachineType implements Parcelable {
        Hls((byte) 0),
        BellabotV0((byte) 7),
        BellabotV1((byte) 8),
        RecycleDog((byte) 9),
        BellabotV2((byte) 10),
        BellabotEnclosed((byte) 11),
        Puductor((byte) 12),
        RecycleDogV2((byte) 13),
        BellabotV3((byte) 14);


        /* renamed from: CREATOR, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        ObsoleteProductMachineType(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            parcel.writeString(name());
        }

        /* JADX WARN: Classes with same name are omitted:
          classes.dex
          classes4.dex
          classes5.dex
         */
        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000bJ\u001a\u0010\f\u001a\u0004\u0018\u00010\u00022\b\u0010\r\u001a\u0004\u0018\u00010\u000eø\u0001\u0000¢\u0006\u0002\b\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$ObsoleteProductMachineType$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$ObsoleteProductMachineType;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$ObsoleteProductMachineType;", "valueOf", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* renamed from: com.pudutech.mirsdk.hardware.serialize.MachineInfo$ObsoleteProductMachineType$CREATOR, reason: from kotlin metadata */
        /* loaded from: classes2.dex */
        public static final class Companion implements Parcelable.Creator<ObsoleteProductMachineType> {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final ObsoleteProductMachineType m4453valueOf3swpYEE(UByte id) {
                for (ObsoleteProductMachineType obsoleteProductMachineType : ObsoleteProductMachineType.values()) {
                    if (UByte.m4534equalsimpl(obsoleteProductMachineType.getId(), id)) {
                        return obsoleteProductMachineType;
                    }
                }
                return null;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ObsoleteProductMachineType createFromParcel(Parcel parcel) {
                Intrinsics.checkParameterIsNotNull(parcel, "parcel");
                String readString = parcel.readString();
                Intrinsics.checkExpressionValueIsNotNull(readString, "parcel.readString()");
                return ObsoleteProductMachineType.valueOf(readString);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ObsoleteProductMachineType[] newArray(int size) {
                return new ObsoleteProductMachineType[size];
            }
        }
    }

    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$Byte32Info;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "PowerBoardType", "UsbCanMcuType", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum Byte32Info {
        PowerBoardType((byte) 3),
        UsbCanMcuType((byte) 2);

        private final byte id;

        Byte32Info(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$AudioType;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "Default", "SongLe", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public enum AudioType {
        Default((byte) 0),
        SongLe((byte) 1);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        AudioType(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes.dex
          classes4.dex
          classes5.dex
         */
        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$AudioType$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$AudioType;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes2.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final AudioType m4434valueOf3swpYEE(UByte id) {
                for (AudioType audioType : AudioType.values()) {
                    if (UByte.m4534equalsimpl(audioType.getId(), id)) {
                        return audioType;
                    }
                }
                return null;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0001\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$LoraType;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "NoDevice", "H4_490", "H8_868", "H9_915", "H9K_920", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public enum LoraType {
        NoDevice((byte) 0),
        H4_490((byte) 1),
        H8_868((byte) 2),
        H9_915((byte) 3),
        H9K_920((byte) 4);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        LoraType(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes.dex
          classes4.dex
          classes5.dex
         */
        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$LoraType$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$LoraType;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes2.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final LoraType m4447valueOf3swpYEE(UByte id) {
                for (LoraType loraType : LoraType.values()) {
                    if (UByte.m4534equalsimpl(loraType.getId(), id)) {
                        return loraType;
                    }
                }
                return null;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$ScanCodeType;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "NoDevice", "Default", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public enum ScanCodeType {
        NoDevice((byte) 0),
        Default((byte) 1);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        ScanCodeType(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes.dex
          classes4.dex
          classes5.dex
         */
        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$ScanCodeType$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$ScanCodeType;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes2.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final ScanCodeType m4456valueOf3swpYEE(UByte id) {
                for (ScanCodeType scanCodeType : ScanCodeType.values()) {
                    if (UByte.m4534equalsimpl(scanCodeType.getId(), id)) {
                        return scanCodeType;
                    }
                }
                return null;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$MonocularType;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "NoDevice", "XinYouKang", "YuXiang", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public enum MonocularType {
        NoDevice((byte) 0),
        XinYouKang((byte) 1),
        YuXiang((byte) 2);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        MonocularType(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes.dex
          classes4.dex
          classes5.dex
         */
        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$MonocularType$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$MonocularType;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes2.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final MonocularType m4451valueOf3swpYEE(UByte id) {
                for (MonocularType monocularType : MonocularType.values()) {
                    if (UByte.m4534equalsimpl(monocularType.getId(), id)) {
                        return monocularType;
                    }
                }
                return null;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$SlamcoreType;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "NoDevice", "Default", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public enum SlamcoreType {
        NoDevice((byte) 0),
        Default((byte) 1);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        SlamcoreType(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes.dex
          classes4.dex
          classes5.dex
         */
        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$SlamcoreType$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$SlamcoreType;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes2.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final SlamcoreType m4457valueOf3swpYEE(UByte id) {
                for (SlamcoreType slamcoreType : SlamcoreType.values()) {
                    if (UByte.m4534equalsimpl(slamcoreType.getId(), id)) {
                        return slamcoreType;
                    }
                }
                return null;
            }
        }
    }

    public final HashMap<FloatInfo, Float> getFloatData() {
        return this.floatData;
    }

    public final void setFloatData(HashMap<FloatInfo, Float> hashMap) {
        Intrinsics.checkParameterIsNotNull(hashMap, "<set-?>");
        this.floatData = hashMap;
    }

    public final HashMap<IntInfo, Integer> getIntData() {
        return this.intData;
    }

    public final void setIntData(HashMap<IntInfo, Integer> hashMap) {
        Intrinsics.checkParameterIsNotNull(hashMap, "<set-?>");
        this.intData = hashMap;
    }

    public final HashMap<Byte23Info, UByte> getByte23Data() {
        return this.byte23Data;
    }

    public final void setByte23Data(HashMap<Byte23Info, UByte> hashMap) {
        Intrinsics.checkParameterIsNotNull(hashMap, "<set-?>");
        this.byte23Data = hashMap;
    }

    public final HashMap<Byte24Info, UByte> getByte24Data() {
        return this.byte24Data;
    }

    public final void setByte24Data(HashMap<Byte24Info, UByte> hashMap) {
        Intrinsics.checkParameterIsNotNull(hashMap, "<set-?>");
        this.byte24Data = hashMap;
    }

    public final ProductMachineType getProductMachineType() {
        return this.productMachineType;
    }

    public final void setProductMachineType(ProductMachineType productMachineType) {
        Intrinsics.checkParameterIsNotNull(productMachineType, "<set-?>");
        this.productMachineType = productMachineType;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MachineInfo(Parcel parcel) {
        this();
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        readFloatData(parcel);
        readIntData(parcel);
        readByte23Data(parcel);
        readByte24Data(parcel);
        readMachineType(parcel);
    }

    public final Float getFloat(FloatInfo floatInfo) {
        Intrinsics.checkParameterIsNotNull(floatInfo, "floatInfo");
        return this.floatData.get(floatInfo);
    }

    public final Integer getInt(IntInfo intInfo) {
        Intrinsics.checkParameterIsNotNull(intInfo, "intInfo");
        return this.intData.get(intInfo);
    }

    public final UByte getByte23(Byte23Info info) {
        Intrinsics.checkParameterIsNotNull(info, "info");
        return this.byte23Data.get(info);
    }

    public final ESP32Type getESPMode() {
        ESP32Type m4439valueOf3swpYEE = ESP32Type.INSTANCE.m4439valueOf3swpYEE(this.byte23Data.get(Byte23Info.ESP32Version));
        return m4439valueOf3swpYEE != null ? m4439valueOf3swpYEE : ESP32Type.SingleDevice;
    }

    public final RGBDType getRGBDMode() {
        RGBDType m4455valueOf3swpYEE = RGBDType.INSTANCE.m4455valueOf3swpYEE(this.byte23Data.get(Byte23Info.RGBDVersion));
        return m4455valueOf3swpYEE != null ? m4455valueOf3swpYEE : RGBDType.NODevice;
    }

    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$Has4GType;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "NoDevice", "HasDevice", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum Has4GType {
        NoDevice((byte) 0),
        HasDevice((byte) 1);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        Has4GType(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$Has4GType$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$Has4GType;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final Has4GType m4441valueOf3swpYEE(UByte id) {
                for (Has4GType has4GType : Has4GType.values()) {
                    if (UByte.m4534equalsimpl(has4GType.getId(), id)) {
                        return has4GType;
                    }
                }
                return null;
            }
        }
    }

    public final ProductMachineType getProductType() {
        return this.productMachineType;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0086\u0001\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$LidarCommunicateType;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "NoDevice", "USB", "Ethernet", "ttyS2", "ttyS4", "Firmware", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum LidarCommunicateType {
        NoDevice((byte) 0),
        USB((byte) 1),
        Ethernet((byte) 2),
        ttyS2((byte) 3),
        ttyS4((byte) 4),
        Firmware((byte) 5);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        LidarCommunicateType(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$LidarCommunicateType$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$LidarCommunicateType;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final LidarCommunicateType m4445valueOf3swpYEE(UByte id) {
                for (LidarCommunicateType lidarCommunicateType : LidarCommunicateType.values()) {
                    if (UByte.m4534equalsimpl(lidarCommunicateType.getId(), id)) {
                        return lidarCommunicateType;
                    }
                }
                return null;
            }
        }
    }

    public final AudioType getAudioType() {
        AudioType m4434valueOf3swpYEE = AudioType.INSTANCE.m4434valueOf3swpYEE(this.byte23Data.get(Byte23Info.AudioVersion));
        return m4434valueOf3swpYEE != null ? m4434valueOf3swpYEE : AudioType.Default;
    }

    public final LoraType getLoraType() {
        LoraType m4447valueOf3swpYEE = LoraType.INSTANCE.m4447valueOf3swpYEE(this.byte24Data.get(Byte24Info.LoraVersion));
        return m4447valueOf3swpYEE != null ? m4447valueOf3swpYEE : LoraType.NoDevice;
    }

    public final ScanCodeType getScanCodeDeviceType() {
        ScanCodeType m4456valueOf3swpYEE = ScanCodeType.INSTANCE.m4456valueOf3swpYEE(this.byte24Data.get(Byte24Info.ScanCodeDevice));
        return m4456valueOf3swpYEE != null ? m4456valueOf3swpYEE : ScanCodeType.NoDevice;
    }

    public final MonocularType getMonocularDeviceType() {
        MonocularType m4451valueOf3swpYEE = MonocularType.INSTANCE.m4451valueOf3swpYEE(this.byte24Data.get(Byte24Info.MonocularCamera));
        return m4451valueOf3swpYEE != null ? m4451valueOf3swpYEE : MonocularType.NoDevice;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\b\u0086\u0001\u0018\u0000 \u001c2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001cB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$LidarType;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "NoDevice", "KrLG", "EAI_X4", "RPLIDAR_A3", "EAI_G4", "RPLIDAR_S1", "EAI_G6", "RPLIDAR_A2M7", "EAI_T05", "EAI_TG30", "EAI_G7", "EAI_TG30_SLAMWARE", "LTME_02A", "LD_06", LDS_50C.TAG, "LDS_15D", "EAI_G7_PLUS", "ECHOX", "PANDARXT", "RSLIDAR_16", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum LidarType {
        NoDevice((byte) 0),
        KrLG((byte) 1),
        EAI_X4((byte) 2),
        RPLIDAR_A3((byte) 3),
        EAI_G4((byte) 4),
        RPLIDAR_S1((byte) 5),
        EAI_G6((byte) 6),
        RPLIDAR_A2M7((byte) 7),
        EAI_T05((byte) 8),
        EAI_TG30((byte) 9),
        EAI_G7((byte) 10),
        EAI_TG30_SLAMWARE((byte) 11),
        LTME_02A((byte) 12),
        LD_06((byte) 13),
        LDS_50C((byte) 14),
        LDS_15D((byte) 15),
        EAI_G7_PLUS((byte) 16),
        ECHOX((byte) 17),
        PANDARXT((byte) 18),
        RSLIDAR_16((byte) 19);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        LidarType(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$LidarType$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$LidarType;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final LidarType m4446valueOf3swpYEE(UByte id) {
                for (LidarType lidarType : LidarType.values()) {
                    if (UByte.m4534equalsimpl(lidarType.getId(), id)) {
                        return lidarType;
                    }
                }
                return null;
            }
        }
    }

    public final SlamcoreType getSlamwareType() {
        SlamcoreType m4457valueOf3swpYEE = SlamcoreType.INSTANCE.m4457valueOf3swpYEE(this.byte24Data.get(Byte24Info.Slamcore));
        return m4457valueOf3swpYEE != null ? m4457valueOf3swpYEE : SlamcoreType.NoDevice;
    }

    private final void readFloatData(Parcel parcel) {
        FloatInfo floatInfo;
        int readInt = parcel.readInt();
        this.floatData.clear();
        if (1 > readInt) {
            return;
        }
        int i = 1;
        while (true) {
            byte m4528constructorimpl = UByte.m4528constructorimpl((byte) parcel.readInt());
            float readFloat = parcel.readFloat();
            FloatInfo[] values = FloatInfo.values();
            int length = values.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    floatInfo = null;
                    break;
                }
                floatInfo = values[i2];
                if (floatInfo.getId() == m4528constructorimpl) {
                    break;
                } else {
                    i2++;
                }
            }
            if (floatInfo != null) {
                this.floatData.put(floatInfo, Float.valueOf(readFloat));
            }
            if (i == readInt) {
                return;
            } else {
                i++;
            }
        }
    }

    private final void readIntData(Parcel parcel) {
        IntInfo intInfo;
        int readInt = parcel.readInt();
        this.intData.clear();
        if (1 > readInt) {
            return;
        }
        int i = 1;
        while (true) {
            byte m4528constructorimpl = UByte.m4528constructorimpl((byte) parcel.readInt());
            int readInt2 = parcel.readInt();
            IntInfo[] values = IntInfo.values();
            int length = values.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    intInfo = null;
                    break;
                }
                intInfo = values[i2];
                if (intInfo.getId() == m4528constructorimpl) {
                    break;
                } else {
                    i2++;
                }
            }
            if (intInfo != null) {
                this.intData.put(intInfo, Integer.valueOf(readInt2));
            }
            if (i == readInt) {
                return;
            } else {
                i++;
            }
        }
    }

    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0001\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$LteType;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "NoDevice", "FourthG", "FifthG", "FourthGRouter", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum LteType {
        NoDevice((byte) 0),
        FourthG((byte) 1),
        FifthG((byte) 2),
        FourthGRouter((byte) 3);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        LteType(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$LteType$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$LteType;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final LteType m4448valueOf3swpYEE(UByte id) {
                for (LteType lteType : LteType.values()) {
                    if (UByte.m4534equalsimpl(lteType.getId(), id)) {
                        return lteType;
                    }
                }
                return null;
            }
        }
    }

    private final void writeFloatData(Parcel parcel) {
        parcel.writeInt(this.floatData.size());
        for (Map.Entry<FloatInfo, Float> entry : this.floatData.entrySet()) {
            FloatInfo key = entry.getKey();
            float floatValue = entry.getValue().floatValue();
            parcel.writeInt(key.getId() & 255);
            parcel.writeFloat(floatValue);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0001\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$CarbinDoorMotorType;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "NoDevice", "PanFeng", "FuXing", "ShunLi", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum CarbinDoorMotorType {
        NoDevice((byte) 0),
        PanFeng((byte) 1),
        FuXing((byte) 2),
        ShunLi((byte) 3);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        CarbinDoorMotorType(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$CarbinDoorMotorType$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$CarbinDoorMotorType;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final CarbinDoorMotorType m4435valueOf3swpYEE(UByte id) {
                for (CarbinDoorMotorType carbinDoorMotorType : CarbinDoorMotorType.values()) {
                    if (UByte.m4534equalsimpl(carbinDoorMotorType.getId(), id)) {
                        return carbinDoorMotorType;
                    }
                }
                return null;
            }
        }
    }

    private final void writeIntData(Parcel parcel) {
        parcel.writeInt(this.intData.size());
        for (Map.Entry<IntInfo, Integer> entry : this.intData.entrySet()) {
            IntInfo key = entry.getKey();
            int intValue = entry.getValue().intValue();
            parcel.writeInt(key.getId() & 255);
            parcel.writeInt(intValue);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$NPUType;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "NoDevice", "DeepEye1000", "RK1808", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum NPUType {
        NoDevice((byte) 0),
        DeepEye1000((byte) 1),
        RK1808((byte) 2);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        NPUType(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$NPUType$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$NPUType;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final NPUType m4452valueOf3swpYEE(UByte id) {
                for (NPUType nPUType : NPUType.values()) {
                    if (UByte.m4534equalsimpl(nPUType.getId(), id)) {
                        return nPUType;
                    }
                }
                return null;
            }
        }
    }

    private final void readByte23Data(Parcel parcel) {
        Byte23Info byte23Info;
        int readInt = parcel.readInt();
        this.byte23Data.clear();
        if (1 > readInt) {
            return;
        }
        int i = 1;
        while (true) {
            byte m4528constructorimpl = UByte.m4528constructorimpl((byte) parcel.readInt());
            byte m4528constructorimpl2 = UByte.m4528constructorimpl((byte) parcel.readInt());
            Byte23Info[] values = Byte23Info.values();
            int length = values.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    byte23Info = null;
                    break;
                }
                byte23Info = values[i2];
                if (byte23Info.getId() == m4528constructorimpl) {
                    break;
                } else {
                    i2++;
                }
            }
            if (byte23Info != null) {
                this.byte23Data.put(byte23Info, UByte.m4522boximpl(m4528constructorimpl2));
            }
            if (i == readInt) {
                return;
            } else {
                i++;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$MatrixMicType;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "NoDevice", "STM32AC108", "BH6080", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum MatrixMicType {
        NoDevice((byte) 0),
        STM32AC108((byte) 1),
        BH6080((byte) 2);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        MatrixMicType(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$MatrixMicType$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$MatrixMicType;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final MatrixMicType m4450valueOf3swpYEE(UByte id) {
                for (MatrixMicType matrixMicType : MatrixMicType.values()) {
                    if (UByte.m4534equalsimpl(matrixMicType.getId(), id)) {
                        return matrixMicType;
                    }
                }
                return null;
            }
        }
    }

    private final void writeByte23Data(Parcel parcel) {
        parcel.writeInt(this.byte23Data.size());
        for (Map.Entry<Byte23Info, UByte> entry : this.byte23Data.entrySet()) {
            Byte23Info key = entry.getKey();
            byte data = entry.getValue().getData();
            parcel.writeInt(key.getId() & 255);
            parcel.writeInt(data & 255);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\u0001\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000fB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$HostCoreboardType;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "NoDevice", "FriendlyRK3399RAM4ROM16", "RongPinRK3399RAM2ROM16", "RongPinRK3399RAM4ROM16", "A311D", "Qualcomm865", "PuduCore", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum HostCoreboardType {
        NoDevice((byte) 0),
        FriendlyRK3399RAM4ROM16((byte) 1),
        RongPinRK3399RAM2ROM16((byte) 2),
        RongPinRK3399RAM4ROM16((byte) 3),
        A311D((byte) 4),
        Qualcomm865((byte) 5),
        PuduCore((byte) 6);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        HostCoreboardType(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$HostCoreboardType$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$HostCoreboardType;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final HostCoreboardType m4443valueOf3swpYEE(UByte id) {
                for (HostCoreboardType hostCoreboardType : HostCoreboardType.values()) {
                    if (UByte.m4534equalsimpl(hostCoreboardType.getId(), id)) {
                        return hostCoreboardType;
                    }
                }
                return null;
            }
        }
    }

    private final void readByte24Data(Parcel parcel) {
        Byte24Info byte24Info;
        int readInt = parcel.readInt();
        this.byte24Data.clear();
        if (1 > readInt) {
            return;
        }
        int i = 1;
        while (true) {
            byte m4528constructorimpl = UByte.m4528constructorimpl((byte) parcel.readInt());
            byte m4528constructorimpl2 = UByte.m4528constructorimpl((byte) parcel.readInt());
            Byte24Info[] values = Byte24Info.values();
            int length = values.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    byte24Info = null;
                    break;
                }
                byte24Info = values[i2];
                if (byte24Info.getId() == m4528constructorimpl) {
                    break;
                } else {
                    i2++;
                }
            }
            if (byte24Info != null) {
                this.byte24Data.put(byte24Info, UByte.m4522boximpl(m4528constructorimpl2));
            }
            if (i == readInt) {
                return;
            } else {
                i++;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0001\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$HeadType;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "LT6911AndSTM32F107", "LT6911GuoMin", "RK628dGuoMin", "RK628dGD", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum HeadType {
        LT6911AndSTM32F107((byte) 0),
        LT6911GuoMin((byte) 1),
        RK628dGuoMin((byte) 2),
        RK628dGD((byte) 3);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        HeadType(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$HeadType$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$HeadType;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final HeadType m4442valueOf3swpYEE(UByte id) {
                for (HeadType headType : HeadType.values()) {
                    if (UByte.m4534equalsimpl(headType.getId(), id)) {
                        return headType;
                    }
                }
                return null;
            }
        }
    }

    private final void readMachineType(Parcel parcel) {
        this.productMachineType.setModel(MachineModel.INSTANCE.fromId(parcel.readInt()));
        this.productMachineType.setMainVersion(parcel.readInt());
        this.productMachineType.setMinorVersion(parcel.readInt());
    }

    private final void writeMachineType(Parcel parcel) {
        parcel.writeInt(this.productMachineType.getModel().getId());
        parcel.writeInt(this.productMachineType.getMainVersion());
        parcel.writeInt(this.productMachineType.getMinorVersion());
    }

    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$LaserProjection;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "NODevice", "Support", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum LaserProjection {
        NODevice((byte) 0),
        Support((byte) 1);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        LaserProjection(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$LaserProjection$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$LaserProjection;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final LaserProjection m4444valueOf3swpYEE(UByte id) {
                for (LaserProjection laserProjection : LaserProjection.values()) {
                    if (UByte.m4534equalsimpl(laserProjection.getId(), id)) {
                        return laserProjection;
                    }
                }
                return null;
            }
        }
    }

    private final void writeByte24Data(Parcel parcel) {
        parcel.writeInt(this.byte24Data.size());
        for (Map.Entry<Byte24Info, UByte> entry : this.byte24Data.entrySet()) {
            Byte24Info key = entry.getKey();
            byte data = entry.getValue().getData();
            parcel.writeInt(key.getId() & 255);
            parcel.writeInt(data & 255);
        }
    }

    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$VedioOutput;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "NODevice", "Support", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum VedioOutput {
        NODevice((byte) 0),
        Support((byte) 1);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        VedioOutput(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$VedioOutput$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$VedioOutput;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final VedioOutput m4460valueOf3swpYEE(UByte id) {
                for (VedioOutput vedioOutput : VedioOutput.values()) {
                    if (UByte.m4534equalsimpl(vedioOutput.getId(), id)) {
                        return vedioOutput;
                    }
                }
                return null;
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        writeFloatData(parcel);
        writeIntData(parcel);
        writeByte23Data(parcel);
        writeByte24Data(parcel);
        writeMachineType(parcel);
    }

    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$DistributionAreaLight;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "NODevice", "Support", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum DistributionAreaLight {
        NODevice((byte) 0),
        Support((byte) 1);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        DistributionAreaLight(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$DistributionAreaLight$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$DistributionAreaLight;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final DistributionAreaLight m4437valueOf3swpYEE(UByte id) {
                for (DistributionAreaLight distributionAreaLight : DistributionAreaLight.values()) {
                    if (UByte.m4534equalsimpl(distributionAreaLight.getId(), id)) {
                        return distributionAreaLight;
                    }
                }
                return null;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo;", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.serialize.MachineInfo$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes2.dex */
    public static final class Companion implements Parcelable.Creator<MachineInfo> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MachineInfo createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            return new MachineInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MachineInfo[] newArray(int size) {
            return new MachineInfo[size];
        }
    }

    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$MagicSensor;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "NODevice", "Support", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum MagicSensor {
        NODevice((byte) 0),
        Support((byte) 1);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        MagicSensor(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$MagicSensor$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$MagicSensor;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final MagicSensor m4449valueOf3swpYEE(UByte id) {
                for (MagicSensor magicSensor : MagicSensor.values()) {
                    if (UByte.m4534equalsimpl(magicSensor.getId(), id)) {
                        return magicSensor;
                    }
                }
                return null;
            }
        }
    }

    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0001\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$PowerBoardType;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "STM32L1", "HuaDaHC32F460", "GDPower", "GuoMinPower", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum PowerBoardType {
        STM32L1((byte) 0),
        HuaDaHC32F460((byte) 1),
        GDPower((byte) 2),
        GuoMinPower((byte) 3);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        PowerBoardType(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$PowerBoardType$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$PowerBoardType;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final PowerBoardType m4454valueOf3swpYEE(UByte id) {
                for (PowerBoardType powerBoardType : PowerBoardType.values()) {
                    if (UByte.m4534equalsimpl(powerBoardType.getId(), id)) {
                        return powerBoardType;
                    }
                }
                return null;
            }
        }
    }

    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$UsbCanMcuType;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "NODevice", "STM32F107", "N32G455", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum UsbCanMcuType {
        NODevice((byte) 0),
        STM32F107((byte) 1),
        N32G455((byte) 2);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        UsbCanMcuType(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$UsbCanMcuType$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$UsbCanMcuType;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final UsbCanMcuType m4459valueOf3swpYEE(UByte id) {
                for (UsbCanMcuType usbCanMcuType : UsbCanMcuType.values()) {
                    if (UByte.m4534equalsimpl(usbCanMcuType.getId(), id)) {
                        return usbCanMcuType;
                    }
                }
                return null;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$TrayBoardType;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "Stm32", "GD", "HC32F460", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum TrayBoardType {
        Stm32((byte) 0),
        GD((byte) 1),
        HC32F460((byte) 2);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        TrayBoardType(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$TrayBoardType$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$TrayBoardType;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final TrayBoardType m4458valueOf3swpYEE(UByte id) {
                for (TrayBoardType trayBoardType : TrayBoardType.values()) {
                    if (UByte.m4534equalsimpl(trayBoardType.getId(), id)) {
                        return trayBoardType;
                    }
                }
                return null;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0001\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$FunctionBoardType;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "Stm32", "GD", "N32G45X", "STM32F450", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum FunctionBoardType {
        Stm32((byte) 0),
        GD((byte) 1),
        N32G45X((byte) 2),
        STM32F450((byte) 3);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        FunctionBoardType(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$FunctionBoardType$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$FunctionBoardType;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final FunctionBoardType m4440valueOf3swpYEE(UByte id) {
                for (FunctionBoardType functionBoardType : FunctionBoardType.values()) {
                    if (UByte.m4534equalsimpl(functionBoardType.getId(), id)) {
                        return functionBoardType;
                    }
                }
                return null;
            }
        }
    }

    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$ChassisBoardType;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "Stm32F722", "GD32F450", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum ChassisBoardType {
        Stm32F722((byte) 0),
        GD32F450((byte) 1);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        ChassisBoardType(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$ChassisBoardType$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$ChassisBoardType;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final ChassisBoardType m4436valueOf3swpYEE(UByte id) {
                for (ChassisBoardType chassisBoardType : ChassisBoardType.values()) {
                    if (UByte.m4534equalsimpl(chassisBoardType.getId(), id)) {
                        return chassisBoardType;
                    }
                }
                return null;
            }
        }
    }

    /* compiled from: MachineInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$DoorBoardType;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", "STM32F446", "GD32F40X", "N32G45X", "Companion", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum DoorBoardType {
        STM32F446((byte) 0),
        GD32F40X((byte) 1),
        N32G45X((byte) 2);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final byte id;

        DoorBoardType(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* compiled from: MachineInfo.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000¢\u0006\u0002\b\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$DoorBoardType$Companion;", "", "()V", "valueOf", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$DoorBoardType;", "id", "Lkotlin/UByte;", "valueOf-3swpYEE", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* renamed from: valueOf-3swpYEE, reason: not valid java name */
            public final DoorBoardType m4438valueOf3swpYEE(UByte id) {
                for (DoorBoardType doorBoardType : DoorBoardType.values()) {
                    if (UByte.m4534equalsimpl(doorBoardType.getId(), id)) {
                        return doorBoardType;
                    }
                }
                return null;
            }
        }
    }
}
