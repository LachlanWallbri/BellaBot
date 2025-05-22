package com.pudutech.bumblebee.presenter.robot_open_task.config;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.p022h2.api.Constraint;
import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.pdmqtt.config.BaseMqttConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;

/* compiled from: AliyunClientConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\b\u0010\u0018\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003JE\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\b\u0010&\u001a\u00020\u0003H\u0016J\b\u0010'\u001a\u00020\u0003H\u0016J\b\u0010(\u001a\u00020\u0003H\u0016J\b\u0010)\u001a\u00020\u0003H\u0016J\b\u0010*\u001a\u00020\u0003H\u0016R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000b\"\u0004\b\u0017\u0010\r¨\u0006+"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/config/AliyunClientConfig;", "Lcom/pudutech/pdmqtt/config/BaseMqttConfig;", "url", "", TmpConstant.KEY_CLIENT_ID, "productKey", "deviceName", Constraint.PARAM_DEVICE_SECRET, "original", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getClientId", "()Ljava/lang/String;", "setClientId", "(Ljava/lang/String;)V", "getDeviceName", "setDeviceName", "getDeviceSecret", "setDeviceSecret", "getOriginal", "setOriginal", "getProductKey", "setProductKey", "getUrl", "setUrl", "certificateJsonInfo", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "", TransferTable.COLUMN_KEY, CognitoUserPoolsSignInProvider.AttributeKeys.PASSWORD, "serverURI", "toString", CognitoUserPoolsSignInProvider.AttributeKeys.USERNAME, "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class AliyunClientConfig implements BaseMqttConfig {
    private String clientId;
    private String deviceName;
    private String deviceSecret;
    private String original;
    private String productKey;
    private String url;

    public static /* synthetic */ AliyunClientConfig copy$default(AliyunClientConfig aliyunClientConfig, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = aliyunClientConfig.url;
        }
        if ((i & 2) != 0) {
            str2 = aliyunClientConfig.clientId;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = aliyunClientConfig.productKey;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = aliyunClientConfig.deviceName;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = aliyunClientConfig.deviceSecret;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = aliyunClientConfig.original;
        }
        return aliyunClientConfig.copy(str, str7, str8, str9, str10, str6);
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    /* renamed from: certificateJsonInfo */
    public String getCertificateInfo() {
        return "";
    }

    /* renamed from: component1, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    public final String component2() {
        return this.clientId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getProductKey() {
        return this.productKey;
    }

    /* renamed from: component4, reason: from getter */
    public final String getDeviceName() {
        return this.deviceName;
    }

    /* renamed from: component5, reason: from getter */
    public final String getDeviceSecret() {
        return this.deviceSecret;
    }

    /* renamed from: component6, reason: from getter */
    public final String getOriginal() {
        return this.original;
    }

    public final AliyunClientConfig copy(String url, String clientId, String productKey, String deviceName, String deviceSecret, String original) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(clientId, "clientId");
        Intrinsics.checkParameterIsNotNull(productKey, "productKey");
        Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
        Intrinsics.checkParameterIsNotNull(deviceSecret, "deviceSecret");
        Intrinsics.checkParameterIsNotNull(original, "original");
        return new AliyunClientConfig(url, clientId, productKey, deviceName, deviceSecret, original);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AliyunClientConfig)) {
            return false;
        }
        AliyunClientConfig aliyunClientConfig = (AliyunClientConfig) other;
        return Intrinsics.areEqual(this.url, aliyunClientConfig.url) && Intrinsics.areEqual(this.clientId, aliyunClientConfig.clientId) && Intrinsics.areEqual(this.productKey, aliyunClientConfig.productKey) && Intrinsics.areEqual(this.deviceName, aliyunClientConfig.deviceName) && Intrinsics.areEqual(this.deviceSecret, aliyunClientConfig.deviceSecret) && Intrinsics.areEqual(this.original, aliyunClientConfig.original);
    }

    public int hashCode() {
        String str = this.url;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.clientId;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.productKey;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.deviceName;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.deviceSecret;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.original;
        return hashCode5 + (str6 != null ? str6.hashCode() : 0);
    }

    public AliyunClientConfig(String url, String clientId, String productKey, String deviceName, String deviceSecret, String original) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(clientId, "clientId");
        Intrinsics.checkParameterIsNotNull(productKey, "productKey");
        Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
        Intrinsics.checkParameterIsNotNull(deviceSecret, "deviceSecret");
        Intrinsics.checkParameterIsNotNull(original, "original");
        this.url = url;
        this.clientId = clientId;
        this.productKey = productKey;
        this.deviceName = deviceName;
        this.deviceSecret = deviceSecret;
        this.original = original;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttMessage
    public String deviceName() {
        return BaseMqttConfig.DefaultImpls.deviceName(this);
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttMessage
    public String productKey() {
        return BaseMqttConfig.DefaultImpls.productKey(this);
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.url = str;
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final void setClientId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.clientId = str;
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

    public /* synthetic */ AliyunClientConfig(String str, String str2, String str3, String str4, String str5, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, (i & 32) != 0 ? "cn-shanghai" : str6);
    }

    public final String getOriginal() {
        return this.original;
    }

    public final void setOriginal(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.original = str;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    public String serverURI() {
        return this.url;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    /* renamed from: clientId, reason: from getter */
    public String getClientId() {
        return this.clientId;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    public String username() {
        return this.deviceName + Typography.amp + this.productKey;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    public String password() {
        return this.deviceSecret;
    }

    @Override // com.pudutech.pdmqtt.config.BaseMqttConfig
    public String key() {
        return "AliyunMqttConfig(productKey='" + this.productKey + "', deviceName='" + this.deviceName + "', deviceSecret='" + this.deviceSecret + "', original='" + this.original + "')";
    }

    public String toString() {
        return "url= " + this.url + " ,clientId=" + this.clientId + " ,username=" + username() + " ,password=" + password();
    }
}
