package org.eclipse.paho.client.mqttv3.spi;

import java.net.URI;
import java.util.Set;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.NetworkModule;

/* loaded from: classes9.dex */
public interface NetworkModuleFactory {
    NetworkModule createNetworkModule(URI uri, MqttConnectOptions mqttConnectOptions, String str) throws MqttException;

    Set<String> getSupportedUriSchemes();

    void validateURI(URI uri) throws IllegalArgumentException;
}
