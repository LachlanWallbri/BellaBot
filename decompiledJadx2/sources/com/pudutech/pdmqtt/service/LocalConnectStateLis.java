package com.pudutech.pdmqtt.service;

import com.pudutech.pdmqtt.OnConnectStateChangeLis;
import com.pudutech.pdmqtt.client.MqttConnectState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: connect_state_ext.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016R!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/pdmqtt/service/LocalConnectStateLis;", "Lcom/pudutech/pdmqtt/OnConnectStateChangeLis$Stub;", "lis", "Lkotlin/Function1;", "Lcom/pudutech/pdmqtt/client/MqttConnectState;", "", "Lcom/pudutech/pdmqtt/client/OnConnectStateChangeListener;", "(Lkotlin/jvm/functions/Function1;)V", "getLis", "()Lkotlin/jvm/functions/Function1;", "onConnectStateChange", "state", "", "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class LocalConnectStateLis extends OnConnectStateChangeLis.Stub {
    private final Function1<MqttConnectState, Unit> lis;

    /* JADX WARN: Multi-variable type inference failed */
    public LocalConnectStateLis(Function1<? super MqttConnectState, Unit> lis) {
        Intrinsics.checkParameterIsNotNull(lis, "lis");
        this.lis = lis;
    }

    public final Function1<MqttConnectState, Unit> getLis() {
        return this.lis;
    }

    @Override // com.pudutech.pdmqtt.OnConnectStateChangeLis
    public void onConnectStateChange(int state) {
        if (state == 1) {
            this.lis.invoke(MqttConnectState.CONNECTING);
        } else if (state == 2) {
            this.lis.invoke(MqttConnectState.DISCONNECTED);
        } else {
            this.lis.invoke(MqttConnectState.CONNECTED);
        }
    }
}
