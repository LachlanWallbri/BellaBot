package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import org.eclipse.paho.client.mqttv3.internal.NetworkModule;
import org.eclipse.paho.client.mqttv3.spi.NetworkModuleFactory;

/* loaded from: classes9.dex */
public class WebSocketNetworkModuleFactory implements NetworkModuleFactory {
    @Override // org.eclipse.paho.client.mqttv3.spi.NetworkModuleFactory
    public void validateURI(URI uri) throws IllegalArgumentException {
    }

    @Override // org.eclipse.paho.client.mqttv3.spi.NetworkModuleFactory
    public Set<String> getSupportedUriSchemes() {
        return Collections.unmodifiableSet(new HashSet(Arrays.asList("ws")));
    }

    @Override // org.eclipse.paho.client.mqttv3.spi.NetworkModuleFactory
    public NetworkModule createNetworkModule(URI uri, MqttConnectOptions mqttConnectOptions, String str) throws MqttException {
        String host = uri.getHost();
        int port = uri.getPort();
        if (port == -1) {
            port = 80;
        }
        int i = port;
        SocketFactory socketFactory = mqttConnectOptions.getSocketFactory();
        if (socketFactory == null) {
            socketFactory = SocketFactory.getDefault();
        } else if (socketFactory instanceof SSLSocketFactory) {
            throw ExceptionHelper.createMqttException(32105);
        }
        WebSocketNetworkModule webSocketNetworkModule = new WebSocketNetworkModule(socketFactory, uri.toString(), host, i, str, mqttConnectOptions.getCustomWebSocketHeaders());
        webSocketNetworkModule.setConnectTimeout(mqttConnectOptions.getConnectionTimeout());
        return webSocketNetworkModule;
    }
}
