package com.pudutech.pdmqtt.config;

import com.aliyun.alink.linksdk.tmp.api.DevFoundOutputParams;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.remotemaintenance.config.IoTConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MqttRegisterInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003JO\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\u0006\u0010!\u001a\u00020\"J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u000b\u001a\u00020\u0003X\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006$"}, m3961d2 = {"Lcom/pudutech/pdmqtt/config/MqttRegisterInfo;", "", "address", "", "certificate", DevFoundOutputParams.PARAMS_DEVICE_NAME, "device_secret", "isp", TransferTable.COLUMN_KEY, DevFoundOutputParams.PARAMS_PRODUCT_KEY, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "TAG", "getAddress", "()Ljava/lang/String;", "getCertificate", "getDevice_name", "getDevice_secret", "getIsp", "getKey", "getProduct_key", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toMqttConfig", "Lcom/pudutech/pdmqtt/config/BaseMqttConfig;", "toString", "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class MqttRegisterInfo {
    private final String TAG;
    private final String address;
    private final String certificate;
    private final String device_name;
    private final String device_secret;
    private final String isp;
    private final String key;
    private final String product_key;

    public static /* synthetic */ MqttRegisterInfo copy$default(MqttRegisterInfo mqttRegisterInfo, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mqttRegisterInfo.address;
        }
        if ((i & 2) != 0) {
            str2 = mqttRegisterInfo.certificate;
        }
        String str8 = str2;
        if ((i & 4) != 0) {
            str3 = mqttRegisterInfo.device_name;
        }
        String str9 = str3;
        if ((i & 8) != 0) {
            str4 = mqttRegisterInfo.device_secret;
        }
        String str10 = str4;
        if ((i & 16) != 0) {
            str5 = mqttRegisterInfo.isp;
        }
        String str11 = str5;
        if ((i & 32) != 0) {
            str6 = mqttRegisterInfo.key;
        }
        String str12 = str6;
        if ((i & 64) != 0) {
            str7 = mqttRegisterInfo.product_key;
        }
        return mqttRegisterInfo.copy(str, str8, str9, str10, str11, str12, str7);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    /* renamed from: component2, reason: from getter */
    public final String getCertificate() {
        return this.certificate;
    }

    /* renamed from: component3, reason: from getter */
    public final String getDevice_name() {
        return this.device_name;
    }

    /* renamed from: component4, reason: from getter */
    public final String getDevice_secret() {
        return this.device_secret;
    }

    /* renamed from: component5, reason: from getter */
    public final String getIsp() {
        return this.isp;
    }

    /* renamed from: component6, reason: from getter */
    public final String getKey() {
        return this.key;
    }

    /* renamed from: component7, reason: from getter */
    public final String getProduct_key() {
        return this.product_key;
    }

    public final MqttRegisterInfo copy(String address, String certificate, String device_name, String device_secret, String isp, String key, String product_key) {
        Intrinsics.checkParameterIsNotNull(address, "address");
        Intrinsics.checkParameterIsNotNull(certificate, "certificate");
        Intrinsics.checkParameterIsNotNull(device_name, "device_name");
        Intrinsics.checkParameterIsNotNull(device_secret, "device_secret");
        Intrinsics.checkParameterIsNotNull(isp, "isp");
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(product_key, "product_key");
        return new MqttRegisterInfo(address, certificate, device_name, device_secret, isp, key, product_key);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MqttRegisterInfo)) {
            return false;
        }
        MqttRegisterInfo mqttRegisterInfo = (MqttRegisterInfo) other;
        return Intrinsics.areEqual(this.address, mqttRegisterInfo.address) && Intrinsics.areEqual(this.certificate, mqttRegisterInfo.certificate) && Intrinsics.areEqual(this.device_name, mqttRegisterInfo.device_name) && Intrinsics.areEqual(this.device_secret, mqttRegisterInfo.device_secret) && Intrinsics.areEqual(this.isp, mqttRegisterInfo.isp) && Intrinsics.areEqual(this.key, mqttRegisterInfo.key) && Intrinsics.areEqual(this.product_key, mqttRegisterInfo.product_key);
    }

    public int hashCode() {
        String str = this.address;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.certificate;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.device_name;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.device_secret;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.isp;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.key;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.product_key;
        return hashCode6 + (str7 != null ? str7.hashCode() : 0);
    }

    public String toString() {
        return "MqttRegisterInfo(address=" + this.address + ", certificate=" + this.certificate + ", device_name=" + this.device_name + ", device_secret=" + this.device_secret + ", isp=" + this.isp + ", key=" + this.key + ", product_key=" + this.product_key + ")";
    }

    public MqttRegisterInfo(String address, String certificate, String device_name, String device_secret, String isp, String key, String product_key) {
        Intrinsics.checkParameterIsNotNull(address, "address");
        Intrinsics.checkParameterIsNotNull(certificate, "certificate");
        Intrinsics.checkParameterIsNotNull(device_name, "device_name");
        Intrinsics.checkParameterIsNotNull(device_secret, "device_secret");
        Intrinsics.checkParameterIsNotNull(isp, "isp");
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(product_key, "product_key");
        this.address = address;
        this.certificate = certificate;
        this.device_name = device_name;
        this.device_secret = device_secret;
        this.isp = isp;
        this.key = key;
        this.product_key = product_key;
        this.TAG = "MqttRegisterInfo";
    }

    public final String getAddress() {
        return this.address;
    }

    public final String getCertificate() {
        return this.certificate;
    }

    public final String getDevice_name() {
        return this.device_name;
    }

    public final String getDevice_secret() {
        return this.device_secret;
    }

    public final String getIsp() {
        return this.isp;
    }

    public final String getKey() {
        return this.key;
    }

    public final String getProduct_key() {
        return this.product_key;
    }

    public final BaseMqttConfig toMqttConfig() {
        String str = this.isp;
        int hashCode = str.hashCode();
        if (hashCode != -1414951308) {
            if (hashCode == 97021 && str.equals("aws")) {
                return new AwsClientConfig(this.address, this.certificate, this.device_name, this.key);
            }
        } else if (str.equals(IoTConfig.IOT_PROGRAM_ALIYUN)) {
            return new AliyunMqttConfig2(this.product_key, this.device_name, this.device_secret, this.address, null, 16, null);
        }
        throw new Exception("不支持的ISP：" + this.isp + ' ');
    }
}
