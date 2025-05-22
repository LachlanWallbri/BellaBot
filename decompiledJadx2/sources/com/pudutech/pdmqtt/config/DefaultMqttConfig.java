package com.pudutech.pdmqtt.config;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.pdmqtt.config.BaseMqttConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: base.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\b\u0010\u0011\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003JE\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\b\u0010\b\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J\b\u0010\u0005\u001a\u00020\u0003H\u0016R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006 "}, m3961d2 = {"Lcom/pudutech/pdmqtt/config/DefaultMqttConfig;", "Lcom/pudutech/pdmqtt/config/BaseMqttConfig;", "serverURI", "", TmpConstant.KEY_CLIENT_ID, CognitoUserPoolsSignInProvider.AttributeKeys.USERNAME, CognitoUserPoolsSignInProvider.AttributeKeys.PASSWORD, "certificateInfo", TransferTable.COLUMN_KEY, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCertificateInfo", "()Ljava/lang/String;", "getClientId", "getKey", "getPassword", "getServerURI", "getUsername", "certificateJsonInfo", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "", "toString", "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class DefaultMqttConfig implements BaseMqttConfig {
    private final String certificateInfo;
    private final String clientId;
    private final String key;
    private final String password;
    private final String serverURI;
    private final String username;

    public static /* synthetic */ DefaultMqttConfig copy$default(DefaultMqttConfig defaultMqttConfig, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = defaultMqttConfig.serverURI;
        }
        if ((i & 2) != 0) {
            str2 = defaultMqttConfig.clientId;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = defaultMqttConfig.username;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = defaultMqttConfig.password;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = defaultMqttConfig.certificateInfo;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = defaultMqttConfig.key;
        }
        return defaultMqttConfig.copy(str, str7, str8, str9, str10, str6);
    }

    /* renamed from: component1, reason: from getter */
    public final String getServerURI() {
        return this.serverURI;
    }

    public final String component2() {
        return this.clientId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getUsername() {
        return this.username;
    }

    /* renamed from: component4, reason: from getter */
    public final String getPassword() {
        return this.password;
    }

    public final String component5() {
        return this.certificateInfo;
    }

    /* renamed from: component6, reason: from getter */
    public final String getKey() {
        return this.key;
    }

    public final DefaultMqttConfig copy(String serverURI, String clientId, String username, String password, String certificateInfo, String key) {
        Intrinsics.checkParameterIsNotNull(serverURI, "serverURI");
        Intrinsics.checkParameterIsNotNull(clientId, "clientId");
        Intrinsics.checkParameterIsNotNull(username, "username");
        Intrinsics.checkParameterIsNotNull(password, "password");
        Intrinsics.checkParameterIsNotNull(certificateInfo, "certificateInfo");
        Intrinsics.checkParameterIsNotNull(key, "key");
        return new DefaultMqttConfig(serverURI, clientId, username, password, certificateInfo, key);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DefaultMqttConfig)) {
            return false;
        }
        DefaultMqttConfig defaultMqttConfig = (DefaultMqttConfig) other;
        return Intrinsics.areEqual(this.serverURI, defaultMqttConfig.serverURI) && Intrinsics.areEqual(this.clientId, defaultMqttConfig.clientId) && Intrinsics.areEqual(this.username, defaultMqttConfig.username) && Intrinsics.areEqual(this.password, defaultMqttConfig.password) && Intrinsics.areEqual(this.certificateInfo, defaultMqttConfig.certificateInfo) && Intrinsics.areEqual(this.key, defaultMqttConfig.key);
    }

    public int hashCode() {
        String str = this.serverURI;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.clientId;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.username;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.password;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.certificateInfo;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.key;
        return hashCode5 + (str6 != null ? str6.hashCode() : 0);
    }

    public String toString() {
        return "DefaultMqttConfig(serverURI=" + this.serverURI + ", clientId=" + this.clientId + ", username=" + this.username + ", password=" + this.password + ", certificateInfo=" + this.certificateInfo + ", key=" + this.key + ")";
    }

    public DefaultMqttConfig(String serverURI, String clientId, String username, String password, String certificateInfo, String key) {
        Intrinsics.checkParameterIsNotNull(serverURI, "serverURI");
        Intrinsics.checkParameterIsNotNull(clientId, "clientId");
        Intrinsics.checkParameterIsNotNull(username, "username");
        Intrinsics.checkParameterIsNotNull(password, "password");
        Intrinsics.checkParameterIsNotNull(certificateInfo, "certificateInfo");
        Intrinsics.checkParameterIsNotNull(key, "key");
        this.serverURI = serverURI;
        this.clientId = clientId;
        this.username = username;
        this.password = password;
        this.certificateInfo = certificateInfo;
        this.key = key;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttMessage
    public String deviceName() {
        return BaseMqttConfig.DefaultImpls.deviceName(this);
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttMessage
    public String productKey() {
        return BaseMqttConfig.DefaultImpls.productKey(this);
    }

    public final String getServerURI() {
        return this.serverURI;
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final String getUsername() {
        return this.username;
    }

    public final String getPassword() {
        return this.password;
    }

    public final String getCertificateInfo() {
        return this.certificateInfo;
    }

    public final String getKey() {
        return this.key;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    public String serverURI() {
        return this.serverURI;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    /* renamed from: clientId, reason: from getter */
    public String getClientId() {
        return this.clientId;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    public String username() {
        return this.username;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    public String password() {
        return this.password;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    /* renamed from: certificateJsonInfo, reason: from getter */
    public String getCertificateInfo() {
        return this.certificateInfo;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    public String key() {
        return this.key;
    }
}
