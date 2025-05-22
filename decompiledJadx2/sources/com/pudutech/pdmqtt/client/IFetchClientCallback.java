package com.pudutech.pdmqtt.client;

import com.pudutech.pdmqtt.config.BaseMqttConfig;
import kotlin.Metadata;

/* compiled from: interfaces.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH&¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/pdmqtt/client/IFetchClientCallback;", "", "onError", "", "requestConfig", "Lcom/pudutech/pdmqtt/config/BaseMqttConfig;", "code", "", "message", "", "onSuccess", "client", "Lcom/pudutech/pdmqtt/client/IMqttClient;", "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface IFetchClientCallback {
    void onError(BaseMqttConfig requestConfig, int code, String message);

    void onSuccess(BaseMqttConfig requestConfig, IMqttClient client);
}
