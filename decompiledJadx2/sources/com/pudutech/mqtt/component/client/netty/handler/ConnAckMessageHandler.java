package com.pudutech.mqtt.component.client.netty.handler;

import com.pudutech.base.Pdlog;
import com.pudutech.mqtt.component.client.callback.LoginStateCallback;
import com.pudutech.mqtt.component.client.config.LoginState;
import com.pudutech.mqtt.component.client.netty.NettyMqttClient;
import io.netty.channel.Channel;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.mqtt.MqttConnAckMessage;
import io.netty.handler.codec.mqtt.MqttConnectReturnCode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: ConnAckMessageHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/netty/handler/ConnAckMessageHandler;", "Lcom/pudutech/mqtt/component/client/netty/handler/AbstractMessageHandler;", "Lio/netty/handler/codec/mqtt/MqttConnAckMessage;", "()V", "action", "", "channel", "Lio/netty/channel/Channel;", "message", "mqttClient", "Lcom/pudutech/mqtt/component/client/netty/NettyMqttClient;", "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ConnAckMessageHandler extends AbstractMessageHandler<MqttConnAckMessage> {

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MqttConnectReturnCode.values().length];

        static {
            $EnumSwitchMapping$0[MqttConnectReturnCode.CONNECTION_ACCEPTED.ordinal()] = 1;
            $EnumSwitchMapping$0[MqttConnectReturnCode.CONNECTION_REFUSED_BAD_USER_NAME_OR_PASSWORD.ordinal()] = 2;
            $EnumSwitchMapping$0[MqttConnectReturnCode.CONNECTION_REFUSED_IDENTIFIER_REJECTED.ordinal()] = 3;
            $EnumSwitchMapping$0[MqttConnectReturnCode.CONNECTION_REFUSED_SERVER_UNAVAILABLE.ordinal()] = 4;
            $EnumSwitchMapping$0[MqttConnectReturnCode.CONNECTION_REFUSED_NOT_AUTHORIZED.ordinal()] = 5;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00e2  */
    @Override // com.pudutech.mqtt.component.client.netty.handler.AbstractMessageHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void action(Channel channel, MqttConnAckMessage message, NettyMqttClient mqttClient) {
        LoginStateCallback loginStateCallback;
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(mqttClient, "mqttClient");
        Pdlog.m3273d(getTAG(), "收到连接确认消息：channel = " + channel + ", message = " + message);
        DecoderResult decoderResult = message.decoderResult();
        Intrinsics.checkExpressionValueIsNotNull(decoderResult, "message.decoderResult()");
        if (decoderResult.isFailure()) {
            Pdlog.m3277w(getTAG(), "连接确认消息解析失败");
            LoginStateCallback loginStateCallback2 = mqttClient.getParamsOptions().getLoginStateCallback();
            if (loginStateCallback2 != null) {
                LoginStateCallback.DefaultImpls.callbackLoginState$default(loginStateCallback2, LoginState.LoginFailure, null, 2, null);
                return;
            }
            return;
        }
        MqttConnectReturnCode connectReturnCode = message.variableHeader().connectReturnCode();
        String str = "登录失败：未授权登录";
        if (connectReturnCode != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[connectReturnCode.ordinal()];
            if (i == 1) {
                Pdlog.m3273d(getTAG(), "登录成功");
                mqttClient.addHeartbeatHandler();
                LoginStateCallback loginStateCallback3 = mqttClient.getParamsOptions().getLoginStateCallback();
                if (loginStateCallback3 != null) {
                    LoginStateCallback.DefaultImpls.callbackLoginState$default(loginStateCallback3, LoginState.LoginSuccessful, null, 2, null);
                    return;
                }
                return;
            }
            if (i == 2) {
                Pdlog.m3277w(getTAG(), "登录失败：用户名或密码错误");
                mqttClient.setAllowAutoReconnect(false);
                str = "登录失败：用户名或密码错误";
            } else if (i == 3) {
                Pdlog.m3277w(getTAG(), "登录失败：ClientId不允许连接");
                mqttClient.setAllowAutoReconnect(false);
                str = "登录失败：ClientId不允许连接";
            } else if (i == 4) {
                Pdlog.m3277w(getTAG(), "登录失败：服务不可用");
                str = "登录失败：服务不可用";
            } else if (i == 5) {
                Pdlog.m3277w(getTAG(), "登录失败：未授权登录");
                mqttClient.setAllowAutoReconnect(false);
            }
            loginStateCallback = mqttClient.getParamsOptions().getLoginStateCallback();
            if (loginStateCallback != null) {
                loginStateCallback.callbackLoginState(LoginState.LoginFailure, str);
            }
            channel.close();
        }
        Pdlog.m3277w(getTAG(), "登录失败：未知错误");
        str = "登录失败：未知错误";
        loginStateCallback = mqttClient.getParamsOptions().getLoginStateCallback();
        if (loginStateCallback != null) {
        }
        channel.close();
    }
}
