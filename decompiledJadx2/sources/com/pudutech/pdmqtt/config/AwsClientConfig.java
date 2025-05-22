package com.pudutech.pdmqtt.config;

import com.aliyun.alink.linksdk.tmp.api.DevFoundOutputParams;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: aws_client_config.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\b\u0010\u000e\u001a\u00020\u0003H\u0016J\b\u0010\u000f\u001a\u00020\u0003H\u0016J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\b\u0010\u0015\u001a\u00020\u0003H\u0016J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u001c\u001a\u00020\u0003H\u0016J\b\u0010\u001d\u001a\u00020\u0003H\u0016J\b\u0010\u001e\u001a\u00020\u0003H\u0016J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J\b\u0010 \u001a\u00020\u0003H\u0016R\u000e\u0010\b\u001a\u00020\u0003X\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006!"}, m3961d2 = {"Lcom/pudutech/pdmqtt/config/AwsClientConfig;", "Lcom/pudutech/pdmqtt/config/BaseMqttConfig;", "address", "", "certificate", DevFoundOutputParams.PARAMS_DEVICE_NAME, TransferTable.COLUMN_KEY, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "TAG", "getAddress", "()Ljava/lang/String;", "getCertificate", "getDevice_name", "getKey", "certificateJsonInfo", TmpConstant.KEY_CLIENT_ID, "component1", "component2", "component3", "component4", "copy", "deviceName", "equals", "", "other", "", "hashCode", "", CognitoUserPoolsSignInProvider.AttributeKeys.PASSWORD, "productKey", "serverURI", "toString", CognitoUserPoolsSignInProvider.AttributeKeys.USERNAME, "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class AwsClientConfig implements BaseMqttConfig {
    private final String TAG;
    private final String address;
    private final String certificate;
    private final String device_name;
    private final String key;

    public static /* synthetic */ AwsClientConfig copy$default(AwsClientConfig awsClientConfig, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = awsClientConfig.address;
        }
        if ((i & 2) != 0) {
            str2 = awsClientConfig.certificate;
        }
        if ((i & 4) != 0) {
            str3 = awsClientConfig.device_name;
        }
        if ((i & 8) != 0) {
            str4 = awsClientConfig.key;
        }
        return awsClientConfig.copy(str, str2, str3, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    /* renamed from: component2, reason: from getter */
    public final String getCertificate() {
        return this.certificate;
    }

    public final String component3() {
        return this.device_name;
    }

    /* renamed from: component4, reason: from getter */
    public final String getKey() {
        return this.key;
    }

    public final AwsClientConfig copy(String address, String certificate, String device_name, String key) {
        Intrinsics.checkParameterIsNotNull(address, "address");
        Intrinsics.checkParameterIsNotNull(certificate, "certificate");
        Intrinsics.checkParameterIsNotNull(device_name, "device_name");
        Intrinsics.checkParameterIsNotNull(key, "key");
        return new AwsClientConfig(address, certificate, device_name, key);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AwsClientConfig)) {
            return false;
        }
        AwsClientConfig awsClientConfig = (AwsClientConfig) other;
        return Intrinsics.areEqual(this.address, awsClientConfig.address) && Intrinsics.areEqual(this.certificate, awsClientConfig.certificate) && Intrinsics.areEqual(this.device_name, awsClientConfig.device_name) && Intrinsics.areEqual(this.key, awsClientConfig.key);
    }

    public int hashCode() {
        String str = this.address;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.certificate;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.device_name;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.key;
        return hashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    public String password() {
        return "";
    }

    public String toString() {
        return "AwsClientConfig(address=" + this.address + ", certificate=" + this.certificate + ", device_name=" + this.device_name + ", key=" + this.key + ")";
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    public String username() {
        return "";
    }

    public AwsClientConfig(String address, String certificate, String device_name, String key) {
        Intrinsics.checkParameterIsNotNull(address, "address");
        Intrinsics.checkParameterIsNotNull(certificate, "certificate");
        Intrinsics.checkParameterIsNotNull(device_name, "device_name");
        Intrinsics.checkParameterIsNotNull(key, "key");
        this.address = address;
        this.certificate = certificate;
        this.device_name = device_name;
        this.key = key;
        this.TAG = "AwsClientConfig";
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

    public final String getKey() {
        return this.key;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    public String serverURI() {
        return "ssl://" + this.address;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    /* renamed from: clientId, reason: from getter */
    public String getDevice_name() {
        return this.device_name;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    /* renamed from: certificateJsonInfo */
    public String getCertificateInfo() {
        return StringsKt.trimIndent("\n       {\n       \"certificate\":\"" + this.certificate + "\",\n       \"key\":\"" + this.key + "\"\n       }\n    ");
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    public String key() {
        return serverURI();
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttMessage
    public String productKey() {
        throw new Exception("未实现  不要调用");
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttMessage
    public String deviceName() {
        return this.device_name;
    }
}
