package org.eclipse.paho.client.mqttv3.internal;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import org.eclipse.paho.client.mqttv3.spi.NetworkModuleFactory;

/* loaded from: classes9.dex */
public class SSLNetworkModuleFactory implements NetworkModuleFactory {
    @Override // org.eclipse.paho.client.mqttv3.spi.NetworkModuleFactory
    public Set<String> getSupportedUriSchemes() {
        return Collections.unmodifiableSet(new HashSet(Arrays.asList("ssl")));
    }

    @Override // org.eclipse.paho.client.mqttv3.spi.NetworkModuleFactory
    public void validateURI(URI uri) throws IllegalArgumentException {
        String path = uri.getPath();
        if (path != null && !path.isEmpty()) {
            throw new IllegalArgumentException(uri.toString());
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.spi.NetworkModuleFactory
    public NetworkModule createNetworkModule(URI uri, MqttConnectOptions mqttConnectOptions, String str) throws MqttException {
        SSLSocketFactoryFactory sSLSocketFactoryFactory;
        String[] enabledCipherSuites;
        String host = uri.getHost();
        int port = uri.getPort();
        if (port == -1) {
            port = 8883;
        }
        String path = uri.getPath();
        if (path != null && !path.isEmpty()) {
            throw new IllegalArgumentException(uri.toString());
        }
        SocketFactory socketFactory = mqttConnectOptions.getSocketFactory();
        if (socketFactory == null) {
            SSLSocketFactoryFactory sSLSocketFactoryFactory2 = new SSLSocketFactoryFactory();
            Properties sSLProperties = mqttConnectOptions.getSSLProperties();
            if (sSLProperties != null) {
                sSLSocketFactoryFactory2.initialize(sSLProperties, null);
            }
            sSLSocketFactoryFactory = sSLSocketFactoryFactory2;
            socketFactory = sSLSocketFactoryFactory2.createSocketFactory(null);
        } else {
            if (!(socketFactory instanceof SSLSocketFactory)) {
                throw ExceptionHelper.createMqttException(32105);
            }
            sSLSocketFactoryFactory = null;
        }
        SSLNetworkModule sSLNetworkModule = new SSLNetworkModule((SSLSocketFactory) socketFactory, host, port, str);
        sSLNetworkModule.setSSLhandshakeTimeout(mqttConnectOptions.getConnectionTimeout());
        sSLNetworkModule.setSSLHostnameVerifier(mqttConnectOptions.getSSLHostnameVerifier());
        sSLNetworkModule.setHttpsHostnameVerificationEnabled(mqttConnectOptions.isHttpsHostnameVerificationEnabled());
        if (sSLSocketFactoryFactory != null && (enabledCipherSuites = sSLSocketFactoryFactory.getEnabledCipherSuites(null)) != null) {
            sSLNetworkModule.setEnabledCiphers(enabledCipherSuites);
        }
        return sSLNetworkModule;
    }
}
