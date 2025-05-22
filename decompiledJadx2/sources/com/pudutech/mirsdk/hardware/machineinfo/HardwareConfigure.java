package com.pudutech.mirsdk.hardware.machineinfo;

import androidx.collection.ArrayMap;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarVersion;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes4.dex */
public class HardwareConfigure {
    private static final int FLOAT_LENGTH = 4;
    private static final int INT_LENGTH = 4;
    private static final String TAG = "HardwareConfigure";
    private static HardwareConfigure instance = new HardwareConfigure();
    Map<Integer, ConfigBean> mapByID = new ArrayMap();
    Map<String, ConfigBean> mapByName = new ArrayMap();

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes4.dex */
    public static class ConfigBean {
        private String[] descs;
        private Object[] enums;

        /* renamed from: id */
        private int f6074id;
        private Object value;
        private String name = "";
        private String desc = "";
        private String type = "";

        public String toString() {
            return "Config{id=" + this.f6074id + ", name='" + this.name + "', desc='" + this.desc + "', type='" + this.type + "', value=" + this.value + ", enums=" + Arrays.toString(this.enums) + '}';
        }
    }

    private HardwareConfigure() {
    }

    public static HardwareConfigure getInstance() {
        return instance;
    }

    public byte[] get4Bytes(int i) {
        if (!this.mapByID.containsKey(Integer.valueOf(i))) {
            return new byte[0];
        }
        if (i != 27) {
            ConfigBean configBean = this.mapByID.get(Integer.valueOf(i));
            Object obj = configBean.value;
            String str = configBean.type;
            char c = 65535;
            int hashCode = str.hashCode();
            if (hashCode != 104431) {
                if (hashCode != 3039496) {
                    if (hashCode == 97526364 && str.equals("float")) {
                        c = 0;
                    }
                } else if (str.equals("byte")) {
                    c = 2;
                }
            } else if (str.equals("int")) {
                c = 1;
            }
            if (c == 0) {
                return toByteArray(toFloat(obj));
            }
            if (c == 1) {
                if (i == 12) {
                    return toByteArray(LidarVersion.getValue((LidarVersion) obj));
                }
                return toByteArray(((Integer) obj).intValue());
            }
            if (c != 2) {
                return new byte[0];
            }
            if (i == 23) {
                return parse23();
            }
            return i == 24 ? parse24() : new byte[0];
        }
        ConfigBean configBean2 = this.mapByID.get(33);
        ConfigBean configBean3 = this.mapByID.get(34);
        ConfigBean configBean4 = this.mapByID.get(35);
        byte[] array = ByteBuffer.allocate(2).putShort((short) ((MachineModel) configBean2.value).getId()).array();
        return new byte[]{(byte) ((Integer) configBean4.value).intValue(), (byte) ((Integer) configBean3.value).intValue(), array[0], array[1]};
    }

    private byte[] parse24() {
        byte id = this.mapByName.containsKey(MachineInfo.Byte24Info.LoraVersion.name()) ? ((MachineInfo.LoraType) this.mapByName.get(MachineInfo.Byte24Info.LoraVersion.name()).value).getId() : (byte) 0;
        byte id2 = this.mapByName.containsKey(MachineInfo.Byte24Info.ScanCodeDevice.name()) ? ((MachineInfo.ScanCodeType) this.mapByName.get(MachineInfo.Byte24Info.ScanCodeDevice.name()).value).getId() : (byte) 0;
        return new byte[]{this.mapByName.containsKey(MachineInfo.Byte24Info.Slamcore.name()) ? ((MachineInfo.SlamcoreType) this.mapByName.get(MachineInfo.Byte24Info.Slamcore.name()).value).getId() : (byte) 0, this.mapByName.containsKey(MachineInfo.Byte24Info.MonocularCamera.name()) ? ((MachineInfo.MonocularType) this.mapByName.get(MachineInfo.Byte24Info.MonocularCamera.name()).value).getId() : (byte) 0, id2, id};
    }

    private byte[] parse23() {
        return new byte[]{this.mapByName.containsKey(MachineInfo.Byte23Info.AudioVersion.name()) ? ((MachineInfo.AudioType) this.mapByName.get(MachineInfo.Byte23Info.AudioVersion.name()).value).getId() : (byte) 0, this.mapByName.containsKey(MachineInfo.Byte23Info.MachineType.name()) ? ((MachineInfo.ObsoleteProductMachineType) this.mapByName.get(MachineInfo.Byte23Info.MachineType.name()).value).getId() : (byte) 0, this.mapByName.containsKey(MachineInfo.Byte23Info.RGBDVersion.name()) ? ((MachineInfo.RGBDType) this.mapByName.get(MachineInfo.Byte23Info.RGBDVersion.name()).value).getId() : (byte) 0, this.mapByName.containsKey(MachineInfo.Byte23Info.ESP32Version.name()) ? ((MachineInfo.ESP32Type) this.mapByName.get(MachineInfo.Byte23Info.ESP32Version.name()).value).getId() : (byte) 0};
    }

    public static float toFloat(Object obj) {
        if (obj.getClass().equals(Float.class)) {
            return ((Float) obj).floatValue();
        }
        if (obj.getClass().equals(Double.class)) {
            return ((Double) obj).floatValue();
        }
        throw new IllegalArgumentException("not float32/64 value");
    }

    public void setFloatData(String str, int i, float f) {
        if (this.mapByName.containsKey(str)) {
            this.mapByID.get(Integer.valueOf(i)).value = Float.valueOf(f);
            this.mapByName.get(str).value = Float.valueOf(f);
            return;
        }
        ConfigBean configBean = new ConfigBean();
        configBean.value = Float.valueOf(f);
        configBean.type = "float";
        configBean.f6074id = i;
        configBean.name = str;
        this.mapByID.put(Integer.valueOf(i), configBean);
        this.mapByName.put(str, configBean);
    }

    public void setIntData(String str, int i, int i2) {
        if (this.mapByName.containsKey(str)) {
            handleInt(str, i, i2);
        } else {
            storeInt(str, i, i2);
        }
    }

    private void handleInt(String str, int i, int i2) {
        if (i == 12) {
            this.mapByID.get(Integer.valueOf(i)).value = LidarVersion.valueOf(i2);
            this.mapByName.get(str).value = this.mapByID.get(Integer.valueOf(i)).value;
            return;
        }
        if (i == 33) {
            this.mapByID.get(Integer.valueOf(i)).value = ParseMachineInfo.INSTANCE.parseMachineModelType(i2);
            this.mapByName.get(str).value = this.mapByID.get(Integer.valueOf(i)).value;
            return;
        }
        this.mapByID.get(Integer.valueOf(i)).value = Integer.valueOf(i2);
        this.mapByName.get(str).value = this.mapByID.get(Integer.valueOf(i)).value;
    }

    private void storeInt(String str, int i, int i2) {
        ConfigBean configBean = new ConfigBean();
        configBean.type = "int";
        configBean.f6074id = i;
        configBean.name = str;
        int i3 = 0;
        if (i == 12) {
            configBean.value = LidarVersion.valueOf(i2);
            configBean.enums = LidarVersion.values();
            int length = configBean.enums.length;
            configBean.descs = new String[length];
            while (i3 < length) {
                configBean.descs[i3] = ((LidarVersion) configBean.enums[i3]).name();
                i3++;
            }
        } else if (i == 33) {
            configBean.value = ParseMachineInfo.INSTANCE.parseMachineModelType(i2);
            configBean.enums = MachineModel.values();
            int length2 = configBean.enums.length;
            configBean.descs = new String[length2];
            while (i3 < length2) {
                configBean.descs[i3] = ((MachineModel) configBean.enums[i3]).name();
                i3++;
            }
        } else {
            configBean.value = Integer.valueOf(i2);
        }
        this.mapByID.put(Integer.valueOf(i), configBean);
        this.mapByName.put(str, configBean);
    }

    public void setByteData(String str, int i, byte b) {
        if (this.mapByName.containsKey(str)) {
            handleByte(str, i, b);
        } else {
            storeByte(str, i, b);
        }
    }

    private void handleByte(String str, int i, byte b) {
        switch (i) {
            case 23:
                this.mapByID.get(Integer.valueOf(i)).value = ParseMachineInfo.INSTANCE.parseESP(b);
                this.mapByName.get(str).value = this.mapByID.get(Integer.valueOf(i)).value;
                return;
            case 24:
                this.mapByID.get(Integer.valueOf(i)).value = ParseMachineInfo.INSTANCE.parseRGBD(b);
                this.mapByName.get(str).value = this.mapByID.get(Integer.valueOf(i)).value;
                return;
            case 25:
                this.mapByID.get(Integer.valueOf(i)).value = ParseMachineInfo.INSTANCE.parseProductType(b);
                this.mapByName.get(str).value = this.mapByID.get(Integer.valueOf(i)).value;
                return;
            case 26:
                this.mapByID.get(Integer.valueOf(i)).value = ParseMachineInfo.INSTANCE.parseAudioType(b);
                this.mapByName.get(str).value = this.mapByID.get(Integer.valueOf(i)).value;
                return;
            case 27:
                this.mapByID.get(Integer.valueOf(i)).value = ParseMachineInfo.INSTANCE.parseLoraType(b);
                this.mapByName.get(str).value = this.mapByID.get(Integer.valueOf(i)).value;
                return;
            case 28:
                this.mapByID.get(Integer.valueOf(i)).value = ParseMachineInfo.INSTANCE.parseScanCodeType(b);
                this.mapByName.get(str).value = this.mapByID.get(Integer.valueOf(i)).value;
                return;
            case 29:
                this.mapByID.get(Integer.valueOf(i)).value = ParseMachineInfo.INSTANCE.parseMonocularType(b);
                this.mapByName.get(str).value = this.mapByID.get(Integer.valueOf(i)).value;
                return;
            case 30:
                this.mapByID.get(Integer.valueOf(i)).value = ParseMachineInfo.INSTANCE.parseSlamCoreType(b);
                this.mapByName.get(str).value = this.mapByID.get(Integer.valueOf(i)).value;
                return;
            case 31:
                this.mapByID.get(Integer.valueOf(i)).value = ParseMachineInfo.INSTANCE.parseFboardType(b);
                this.mapByName.get(str).value = this.mapByID.get(Integer.valueOf(i)).value;
                return;
            default:
                Pdlog.m3273d(TAG, "handleByte not in case");
                return;
        }
    }

    private void storeByte(String str, int i, byte b) {
        ConfigBean configBean = new ConfigBean();
        configBean.type = "byte";
        configBean.f6074id = i;
        configBean.name = str;
        switch (configBean.f6074id) {
            case 23:
                storeESP32Type(b, configBean);
                break;
            case 24:
                storeRGBDType(b, configBean);
                break;
            case 25:
                storeObsolete(b, configBean);
                break;
            case 26:
                storeAudioType(b, configBean);
                break;
            case 27:
                storeLoraType(b, configBean);
                break;
            case 28:
                storeScanCodeType(b, configBean);
                break;
            case 29:
                storeMonocularType(b, configBean);
                break;
            case 30:
                storeSlamcoreType(b, configBean);
                break;
            default:
                Pdlog.m3273d(TAG, "not in case");
                break;
        }
        this.mapByID.put(Integer.valueOf(i), configBean);
        this.mapByName.put(str, configBean);
    }

    private void storeSlamcoreType(byte b, ConfigBean configBean) {
        configBean.value = ParseMachineInfo.INSTANCE.parseSlamCoreType(b);
        configBean.enums = MachineInfo.SlamcoreType.values();
        int length = configBean.enums.length;
        configBean.descs = new String[length];
        for (int i = 0; i < length; i++) {
            configBean.descs[i] = ((MachineInfo.SlamcoreType) configBean.enums[i]).name();
        }
    }

    private void storeMonocularType(byte b, ConfigBean configBean) {
        configBean.value = ParseMachineInfo.INSTANCE.parseMonocularType(b);
        configBean.enums = MachineInfo.MonocularType.values();
        int length = configBean.enums.length;
        configBean.descs = new String[length];
        for (int i = 0; i < length; i++) {
            configBean.descs[i] = ((MachineInfo.MonocularType) configBean.enums[i]).name();
        }
    }

    private void storeScanCodeType(byte b, ConfigBean configBean) {
        configBean.value = ParseMachineInfo.INSTANCE.parseScanCodeType(b);
        configBean.enums = MachineInfo.ScanCodeType.values();
        int length = configBean.enums.length;
        configBean.descs = new String[length];
        for (int i = 0; i < length; i++) {
            configBean.descs[i] = ((MachineInfo.ScanCodeType) configBean.enums[i]).name();
        }
    }

    private void storeLoraType(byte b, ConfigBean configBean) {
        configBean.value = ParseMachineInfo.INSTANCE.parseLoraType(b);
        configBean.enums = MachineInfo.LoraType.values();
        int length = configBean.enums.length;
        configBean.descs = new String[length];
        for (int i = 0; i < length; i++) {
            configBean.descs[i] = ((MachineInfo.LoraType) configBean.enums[i]).name();
        }
    }

    private void storeAudioType(byte b, ConfigBean configBean) {
        configBean.value = ParseMachineInfo.INSTANCE.parseAudioType(b);
        configBean.enums = MachineInfo.AudioType.values();
        int length = configBean.enums.length;
        configBean.descs = new String[length];
        for (int i = 0; i < length; i++) {
            configBean.descs[i] = ((MachineInfo.AudioType) configBean.enums[i]).name();
        }
    }

    private void storeObsolete(byte b, ConfigBean configBean) {
        configBean.value = ParseMachineInfo.INSTANCE.parseProductType(b);
        configBean.enums = MachineInfo.ObsoleteProductMachineType.values();
        int length = configBean.enums.length;
        configBean.descs = new String[length];
        for (int i = 0; i < length; i++) {
            configBean.descs[i] = ((MachineInfo.ObsoleteProductMachineType) configBean.enums[i]).name();
        }
    }

    private void storeRGBDType(byte b, ConfigBean configBean) {
        configBean.value = ParseMachineInfo.INSTANCE.parseRGBD(b);
        configBean.enums = MachineInfo.RGBDType.values();
        int length = configBean.enums.length;
        configBean.descs = new String[length];
        for (int i = 0; i < length; i++) {
            configBean.descs[i] = ((MachineInfo.RGBDType) configBean.enums[i]).name();
        }
    }

    private void storeESP32Type(byte b, ConfigBean configBean) {
        configBean.value = ParseMachineInfo.INSTANCE.parseESP(b);
        configBean.enums = MachineInfo.ESP32Type.values();
        int length = configBean.enums.length;
        configBean.descs = new String[length];
        for (int i = 0; i < length; i++) {
            configBean.descs[i] = ((MachineInfo.ESP32Type) configBean.enums[i]).name();
        }
    }

    private static byte[] toByteArray(int i) {
        return ByteBuffer.allocate(4).putInt(i).array();
    }

    private static byte[] toByteArray(float f) {
        return ByteBuffer.allocate(4).putFloat(f).array();
    }
}
