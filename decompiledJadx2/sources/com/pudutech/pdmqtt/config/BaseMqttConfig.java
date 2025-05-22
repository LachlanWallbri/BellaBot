package com.pudutech.pdmqtt.config;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.pdmqtt.config.BaseMqttMessage;
import kotlin.Metadata;

/* compiled from: base.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0003H&¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/pdmqtt/config/BaseMqttConfig;", "Lcom/pudutech/pdmqtt/config/BaseMqttMessage;", "certificateJsonInfo", "", TmpConstant.KEY_CLIENT_ID, TransferTable.COLUMN_KEY, CognitoUserPoolsSignInProvider.AttributeKeys.PASSWORD, "serverURI", CognitoUserPoolsSignInProvider.AttributeKeys.USERNAME, "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface BaseMqttConfig extends BaseMqttMessage {

    /* compiled from: base.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class DefaultImpls {
        public static String deviceName(BaseMqttConfig baseMqttConfig) {
            return BaseMqttMessage.DefaultImpls.deviceName(baseMqttConfig);
        }

        public static String productKey(BaseMqttConfig baseMqttConfig) {
            return BaseMqttMessage.DefaultImpls.productKey(baseMqttConfig);
        }
    }

    String certificateJsonInfo();

    String clientId();

    String key();

    String password();

    String serverURI();

    String username();
}
