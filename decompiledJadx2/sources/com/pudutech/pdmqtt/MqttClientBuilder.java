package com.pudutech.pdmqtt;

import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.pdmqtt.client.IFetchClientCallback;
import com.pudutech.pdmqtt.config.BaseMqttConfig;
import kotlin.Metadata;

/* compiled from: PuduMqttManagerWrap.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0012R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/pdmqtt/MqttClientBuilder;", "", "()V", "clientCallback", "Lcom/pudutech/pdmqtt/client/IFetchClientCallback;", "getClientCallback", "()Lcom/pudutech/pdmqtt/client/IFetchClientCallback;", "setClientCallback", "(Lcom/pudutech/pdmqtt/client/IFetchClientCallback;)V", "mqttConfig", "Lcom/pudutech/pdmqtt/config/BaseMqttConfig;", "getMqttConfig", "()Lcom/pudutech/pdmqtt/config/BaseMqttConfig;", "setMqttConfig", "(Lcom/pudutech/pdmqtt/config/BaseMqttConfig;)V", "checkConfig", "", TransferTable.COLUMN_KEY, "", "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MqttClientBuilder {
    private IFetchClientCallback clientCallback;
    private BaseMqttConfig mqttConfig;

    public final BaseMqttConfig getMqttConfig() {
        return this.mqttConfig;
    }

    public final void setMqttConfig(BaseMqttConfig baseMqttConfig) {
        this.mqttConfig = baseMqttConfig;
    }

    public final IFetchClientCallback getClientCallback() {
        return this.clientCallback;
    }

    public final void setClientCallback(IFetchClientCallback iFetchClientCallback) {
        this.clientCallback = iFetchClientCallback;
    }

    public final void checkConfig() {
        if (this.mqttConfig == null) {
            throw new Exception("客户端不能为空");
        }
        if (this.clientCallback == null) {
            throw new Exception("clientCallback 不能为空");
        }
    }

    public final String key() {
        String key;
        BaseMqttConfig baseMqttConfig = this.mqttConfig;
        return (baseMqttConfig == null || (key = baseMqttConfig.key()) == null) ? "" : key;
    }
}
