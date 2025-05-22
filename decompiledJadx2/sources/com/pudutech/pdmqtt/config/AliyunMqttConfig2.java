package com.pudutech.pdmqtt.config;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.p022h2.api.Constraint;
import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.Typography;

/* compiled from: aliyun_client_config.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\b\u0010\u0015\u001a\u00020\u0003H\u0016J\b\u0010\u0016\u001a\u00020\u0003H\u0016J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J;\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\b\u0010#\u001a\u00020\u0003H\u0016J\b\u0010$\u001a\u00020\u0003H\u0016J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010%\u001a\u00020\u0003H\u0016J\t\u0010&\u001a\u00020\u0003HÖ\u0001J\b\u0010'\u001a\u00020\u0003H\u0016R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\fR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\n\"\u0004\b\u0014\u0010\f¨\u0006("}, m3961d2 = {"Lcom/pudutech/pdmqtt/config/AliyunMqttConfig2;", "Lcom/pudutech/pdmqtt/config/BaseMqttConfig;", "productKey", "", "deviceName", Constraint.PARAM_DEVICE_SECRET, "address", "timestamp", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAddress", "()Ljava/lang/String;", "setAddress", "(Ljava/lang/String;)V", "getDeviceName", "setDeviceName", "getDeviceSecret", "setDeviceSecret", "getProductKey", "setProductKey", "getTimestamp", "setTimestamp", "certificateJsonInfo", TmpConstant.KEY_CLIENT_ID, "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", TransferTable.COLUMN_KEY, CognitoUserPoolsSignInProvider.AttributeKeys.PASSWORD, "serverURI", "toString", CognitoUserPoolsSignInProvider.AttributeKeys.USERNAME, "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class AliyunMqttConfig2 implements BaseMqttConfig {
    private String address;
    private String deviceName;
    private String deviceSecret;
    private String productKey;
    private String timestamp;

    public static /* synthetic */ AliyunMqttConfig2 copy$default(AliyunMqttConfig2 aliyunMqttConfig2, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = aliyunMqttConfig2.productKey;
        }
        if ((i & 2) != 0) {
            str2 = aliyunMqttConfig2.deviceName;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = aliyunMqttConfig2.deviceSecret;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = aliyunMqttConfig2.address;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = aliyunMqttConfig2.timestamp;
        }
        return aliyunMqttConfig2.copy(str, str6, str7, str8, str5);
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    /* renamed from: certificateJsonInfo */
    public String getCertificateInfo() {
        return "        {\n        \"certificate\":\"-----BEGIN CERTIFICATE-----\nMIIDdTCCAl2gAwIBAgILBAAAAAABFUtaw5QwDQYJKoZIhvcNAQEFBQAwVzELMAkG\nA1UEBhMCQkUxGTAXBgNVBAoTEEdsb2JhbFNpZ24gbnYtc2ExEDAOBgNVBAsTB1Jv\nb3QgQ0ExGzAZBgNVBAMTEkdsb2JhbFNpZ24gUm9vdCBDQTAeFw05ODA5MDExMjAw\nMDBaFw0yODAxMjgxMjAwMDBaMFcxCzAJBgNVBAYTAkJFMRkwFwYDVQQKExBHbG9i\nYWxTaWduIG52LXNhMRAwDgYDVQQLEwdSb290IENBMRswGQYDVQQDExJHbG9iYWxT\naWduIFJvb3QgQ0EwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDaDuaZ\njc6j40+Kfvvxi4Mla+pIH/EqsLmVEQS98GPR4mdmzxzdzxtIK+6NiY6arymAZavp\nxy0Sy6scTHAHoT0KMM0VjU/43dSMUBUc71DuxC73/OlS8pF94G3VNTCOXkNz8kHp\n1Wrjsok6Vjk4bwY8iGlbKk3Fp1S4bInMm/k8yuX9ifUSPJJ4ltbcdG6TRGHRjcdG\nsnUOhugZitVtbNV4FpWi6cgKOOvyJBNPc1STE4U6G7weNLWLBYy5d4ux2x8gkasJ\nU26Qzns3dLlwR5EiUWMWea6xrkEmCMgZK9FGqkjWZCrXgzT/LCrBbBlDSgeF59N8\n9iFo7+ryUp9/k5DPAgMBAAGjQjBAMA4GA1UdDwEB/wQEAwIBBjAPBgNVHRMBAf8E\nBTADAQH/MB0GA1UdDgQWBBRge2YaRQ2XyolQL30EzTSo//z9SzANBgkqhkiG9w0B\nAQUFAAOCAQEA1nPnfE920I2/7LqivjTFKDK1fPxsnCwrvQmeU79rXqoRSLblCKOz\nyj1hTdNGCbM+w6DjY1Ub8rrvrTnhQ7k4o+YviiY776BQVvnGCv04zcQLcFGUl5gE\n38NflNUVyRRBnMRddWQVDf9VMOyGj/8N7yy5Y0b2qvzfvGn9LhJIZJrglfCm7ymP\nAbEVtQwdpf5pLGkkeB6zpxxxYu7KyJesF12KwvhHhm4qxFYxldBniYUr+WymXUad\nDKqC5JlR3XC321Y9YeRq4VzW9v493kHMB65jUr9TU/Qr6cf9tveCX4XSQRjbgbME\nHMUfpIBvFSDJ3gyICh3WZlXi/EjJKSZp4A==\n-----END CERTIFICATE-----\"\n        }";
    }

    /* renamed from: component1, reason: from getter */
    public final String getProductKey() {
        return this.productKey;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDeviceName() {
        return this.deviceName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getDeviceSecret() {
        return this.deviceSecret;
    }

    /* renamed from: component4, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    /* renamed from: component5, reason: from getter */
    public final String getTimestamp() {
        return this.timestamp;
    }

    public final AliyunMqttConfig2 copy(String productKey, String deviceName, String deviceSecret, String address, String timestamp) {
        Intrinsics.checkParameterIsNotNull(productKey, "productKey");
        Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
        Intrinsics.checkParameterIsNotNull(deviceSecret, "deviceSecret");
        Intrinsics.checkParameterIsNotNull(address, "address");
        Intrinsics.checkParameterIsNotNull(timestamp, "timestamp");
        return new AliyunMqttConfig2(productKey, deviceName, deviceSecret, address, timestamp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AliyunMqttConfig2)) {
            return false;
        }
        AliyunMqttConfig2 aliyunMqttConfig2 = (AliyunMqttConfig2) other;
        return Intrinsics.areEqual(this.productKey, aliyunMqttConfig2.productKey) && Intrinsics.areEqual(this.deviceName, aliyunMqttConfig2.deviceName) && Intrinsics.areEqual(this.deviceSecret, aliyunMqttConfig2.deviceSecret) && Intrinsics.areEqual(this.address, aliyunMqttConfig2.address) && Intrinsics.areEqual(this.timestamp, aliyunMqttConfig2.timestamp);
    }

    public int hashCode() {
        String str = this.productKey;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.deviceName;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.deviceSecret;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.address;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.timestamp;
        return hashCode4 + (str5 != null ? str5.hashCode() : 0);
    }

    public String toString() {
        return "AliyunMqttConfig2(productKey=" + this.productKey + ", deviceName=" + this.deviceName + ", deviceSecret=" + this.deviceSecret + ", address=" + this.address + ", timestamp=" + this.timestamp + ")";
    }

    public AliyunMqttConfig2(String productKey, String deviceName, String deviceSecret, String address, String timestamp) {
        Intrinsics.checkParameterIsNotNull(productKey, "productKey");
        Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
        Intrinsics.checkParameterIsNotNull(deviceSecret, "deviceSecret");
        Intrinsics.checkParameterIsNotNull(address, "address");
        Intrinsics.checkParameterIsNotNull(timestamp, "timestamp");
        this.productKey = productKey;
        this.deviceName = deviceName;
        this.deviceSecret = deviceSecret;
        this.address = address;
        this.timestamp = timestamp;
    }

    public final String getProductKey() {
        return this.productKey;
    }

    public final void setProductKey(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.productKey = str;
    }

    public final String getDeviceName() {
        return this.deviceName;
    }

    public final void setDeviceName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.deviceName = str;
    }

    public final String getDeviceSecret() {
        return this.deviceSecret;
    }

    public final void setDeviceSecret(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.deviceSecret = str;
    }

    public final String getAddress() {
        return this.address;
    }

    public final void setAddress(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.address = str;
    }

    public /* synthetic */ AliyunMqttConfig2(String str, String str2, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, (i & 16) != 0 ? String.valueOf(System.currentTimeMillis()) : str5);
    }

    public final String getTimestamp() {
        return this.timestamp;
    }

    public final void setTimestamp(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.timestamp = str;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    public String serverURI() {
        return "ssl://" + this.address;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    /* renamed from: clientId */
    public String getClientId() {
        return this.productKey + "." + this.deviceName + "|timestamp=" + this.timestamp + ",_v=paho-android-1.0.0,securemode=2,signmethod=hmacsha256|";
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    public String username() {
        return this.deviceName + Typography.amp + this.productKey;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    public String password() {
        String str = TmpConstant.KEY_CLIENT_ID + this.productKey + "." + this.deviceName + "deviceName" + this.deviceName + "productKey" + this.productKey + "timestamp" + this.timestamp;
        Mac mac = Mac.getInstance("HmacSHA256");
        String str2 = this.deviceSecret;
        Charset charset = Charsets.UTF_8;
        if (str2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = str2.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        mac.init(new SecretKeySpec(bytes, "HmacSHA256"));
        Charset charset2 = Charsets.UTF_8;
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes2 = str.getBytes(charset2);
        Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
        byte[] doFinal = mac.doFinal(bytes2);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {new BigInteger(1, doFinal)};
        String format = String.format("%064x", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        return format;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttMessage
    public String productKey() {
        return this.productKey;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttMessage
    public String deviceName() {
        return this.deviceName;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    public String key() {
        return "AliyunMqttConfig(productKey='" + this.productKey + "', deviceName='" + this.deviceName + "', deviceSecret='" + this.deviceSecret + "')";
    }
}
