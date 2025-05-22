package com.pudutech.pdmqtt.service;

import com.pudutech.pdmqtt.OnConnectStateChangeLis;
import com.pudutech.pdmqtt.client.MqttConnectState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: connect_state_ext.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001j\u0002`\u0004B\u001d\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0011\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0002H\u0096\u0002R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\f¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/pdmqtt/service/OnConnectChangeEntry;", "Lkotlin/Function1;", "Lcom/pudutech/pdmqtt/client/MqttConnectState;", "", "Lcom/pudutech/pdmqtt/client/OnConnectStateChangeListener;", "clientKey", "", "lisId", "lis", "Lcom/pudutech/pdmqtt/OnConnectStateChangeLis;", "(Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/pdmqtt/OnConnectStateChangeLis;)V", "getClientKey", "()Ljava/lang/String;", "getLis", "()Lcom/pudutech/pdmqtt/OnConnectStateChangeLis;", "getLisId", "invoke", "state", "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class OnConnectChangeEntry implements Function1<MqttConnectState, Unit> {
    private final String clientKey;
    private final OnConnectStateChangeLis lis;
    private final String lisId;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MqttConnectState.values().length];

        static {
            $EnumSwitchMapping$0[MqttConnectState.CONNECTED.ordinal()] = 1;
            $EnumSwitchMapping$0[MqttConnectState.DISCONNECTED.ordinal()] = 2;
        }
    }

    public OnConnectChangeEntry(String clientKey, String lisId, OnConnectStateChangeLis lis) {
        Intrinsics.checkParameterIsNotNull(clientKey, "clientKey");
        Intrinsics.checkParameterIsNotNull(lisId, "lisId");
        Intrinsics.checkParameterIsNotNull(lis, "lis");
        this.clientKey = clientKey;
        this.lisId = lisId;
        this.lis = lis;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(MqttConnectState mqttConnectState) {
        invoke2(mqttConnectState);
        return Unit.INSTANCE;
    }

    public final String getClientKey() {
        return this.clientKey;
    }

    public final String getLisId() {
        return this.lisId;
    }

    public final OnConnectStateChangeLis getLis() {
        return this.lis;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public void invoke2(MqttConnectState state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        OnConnectStateChangeLis onConnectStateChangeLis = this.lis;
        int i = WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
        int i2 = 2;
        if (i == 1) {
            i2 = 3;
        } else if (i != 2) {
            i2 = 1;
        }
        onConnectStateChangeLis.onConnectStateChange(i2);
    }
}
