package com.pudutech.pdmqtt.config;

import kotlin.Metadata;

/* compiled from: base.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/pdmqtt/config/BaseMqttMessage;", "", "deviceName", "", "productKey", "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface BaseMqttMessage {

    /* compiled from: base.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class DefaultImpls {
        public static String deviceName(BaseMqttMessage baseMqttMessage) {
            return "";
        }

        public static String productKey(BaseMqttMessage baseMqttMessage) {
            return "";
        }
    }

    String deviceName();

    String productKey();
}
