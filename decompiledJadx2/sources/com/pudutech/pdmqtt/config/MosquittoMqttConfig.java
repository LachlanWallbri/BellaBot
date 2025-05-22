package com.pudutech.pdmqtt.config;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: mosquitto_client_config.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\b\u0010\u0012\u001a\u00020\u0003H\u0016J\b\u0010\u0013\u001a\u00020\u0003H\u0016J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J1\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\b\u0010\u001f\u001a\u00020\u0003H\u0016J\b\u0010 \u001a\u00020\u0003H\u0016J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010!\u001a\u00020\u0003H\u0016J\t\u0010\"\u001a\u00020\u0003HÖ\u0001J\b\u0010#\u001a\u00020\u0003H\u0016R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006$"}, m3961d2 = {"Lcom/pudutech/pdmqtt/config/MosquittoMqttConfig;", "Lcom/pudutech/pdmqtt/config/BaseMqttConfig;", "productKey", "", "deviceName", "host", "port", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDeviceName", "()Ljava/lang/String;", "setDeviceName", "(Ljava/lang/String;)V", "getHost", "setHost", "getPort", "setPort", "getProductKey", "setProductKey", "certificateJsonInfo", TmpConstant.KEY_CLIENT_ID, "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", TransferTable.COLUMN_KEY, CognitoUserPoolsSignInProvider.AttributeKeys.PASSWORD, "serverURI", "toString", CognitoUserPoolsSignInProvider.AttributeKeys.USERNAME, "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class MosquittoMqttConfig implements BaseMqttConfig {
    private String deviceName;
    private String host;
    private String port;
    private String productKey;

    public static /* synthetic */ MosquittoMqttConfig copy$default(MosquittoMqttConfig mosquittoMqttConfig, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mosquittoMqttConfig.productKey;
        }
        if ((i & 2) != 0) {
            str2 = mosquittoMqttConfig.deviceName;
        }
        if ((i & 4) != 0) {
            str3 = mosquittoMqttConfig.host;
        }
        if ((i & 8) != 0) {
            str4 = mosquittoMqttConfig.port;
        }
        return mosquittoMqttConfig.copy(str, str2, str3, str4);
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    /* renamed from: certificateJsonInfo */
    public String getCertificateInfo() {
        return "            {\n            \"certificate\" : \"-----BEGIN CERTIFICATE-----\nMIIB2zCCAUQCCQCZLnlNhmN8BjANBgkqhkiG9w0BAQUFADAyMTAwLgYDVQQKDCdU\nTFMgUHJvamVjdCBEb2RneSBDZXJ0aWZpY2F0ZSBBdXRob3JpdHkwHhcNMjEwMzE3\nMTAwMjI3WhcNMzQxMTI0MTAwMjI3WjAyMTAwLgYDVQQKDCdUTFMgUHJvamVjdCBE\nb2RneSBDZXJ0aWZpY2F0ZSBBdXRob3JpdHkwgZ8wDQYJKoZIhvcNAQEBBQADgY0A\nMIGJAoGBAMxt02KMuOSCSuIlFmaqDkmTkh1nV4TEMqn1wOQ+KvhfUtEp/cGw1jlW\nUmvMRiwW/xyBpJypAWuWPu4I+w/l+WG5vbsh/03blLpLiPt1SeMi9UGExd8oWw9N\n/8zGyPzbXKWZnUty8Vpe8ta6em3xIPdZWLVV5XR8EUYBWstYTKDHAgMBAAEwDQYJ\nKoZIhvcNAQEFBQADgYEAOPBpsUbuoUKkc+2Pv+2LtaInoIyTBcIPW0PF3Ih0nJYq\nUdmWADBA/zzcVbe79aNqtOhmh1/xKMXOGJt9qYx5yMPecK7biK/6vEXdahzS5tB6\n6TeOfte48IKgqf2oPLd5KbLUUbCbCd6CmqAegeHzrYi9J69x7WMoSymmz4YEQAI=\n-----END CERTIFICATE-----\n\"\n            }";
    }

    /* renamed from: component1, reason: from getter */
    public final String getProductKey() {
        return this.productKey;
    }

    public final String component2() {
        return this.deviceName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getHost() {
        return this.host;
    }

    /* renamed from: component4, reason: from getter */
    public final String getPort() {
        return this.port;
    }

    public final MosquittoMqttConfig copy(String productKey, String deviceName, String host, String port) {
        Intrinsics.checkParameterIsNotNull(productKey, "productKey");
        Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
        Intrinsics.checkParameterIsNotNull(host, "host");
        Intrinsics.checkParameterIsNotNull(port, "port");
        return new MosquittoMqttConfig(productKey, deviceName, host, port);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MosquittoMqttConfig)) {
            return false;
        }
        MosquittoMqttConfig mosquittoMqttConfig = (MosquittoMqttConfig) other;
        return Intrinsics.areEqual(this.productKey, mosquittoMqttConfig.productKey) && Intrinsics.areEqual(this.deviceName, mosquittoMqttConfig.deviceName) && Intrinsics.areEqual(this.host, mosquittoMqttConfig.host) && Intrinsics.areEqual(this.port, mosquittoMqttConfig.port);
    }

    public int hashCode() {
        String str = this.productKey;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.deviceName;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.host;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.port;
        return hashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    public String password() {
        return "";
    }

    public String toString() {
        return "MosquittoMqttConfig(productKey=" + this.productKey + ", deviceName=" + this.deviceName + ", host=" + this.host + ", port=" + this.port + ")";
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    public String username() {
        return "";
    }

    public MosquittoMqttConfig(String productKey, String deviceName, String host, String port) {
        Intrinsics.checkParameterIsNotNull(productKey, "productKey");
        Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
        Intrinsics.checkParameterIsNotNull(host, "host");
        Intrinsics.checkParameterIsNotNull(port, "port");
        this.productKey = productKey;
        this.deviceName = deviceName;
        this.host = host;
        this.port = port;
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

    public final String getHost() {
        return this.host;
    }

    public final void setHost(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.host = str;
    }

    public final String getPort() {
        return this.port;
    }

    public final void setPort(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.port = str;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    public String serverURI() {
        return "ssl://" + this.host + ':' + this.port;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    /* renamed from: clientId, reason: from getter */
    public String getClientId() {
        return this.deviceName;
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
        return serverURI();
    }
}
