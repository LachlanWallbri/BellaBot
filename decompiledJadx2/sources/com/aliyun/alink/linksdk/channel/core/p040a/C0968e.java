package com.aliyun.alink.linksdk.channel.core.p040a;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.channel.core.p041b.C0969a;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.MqttConfigure;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttPingSender;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import org.eclipse.paho.client.mqttv3.internal.NetworkModule;
import org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import org.eclipse.paho.client.mqttv3.internal.websocket.WebSocketNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.websocket.WebSocketSecureNetworkModule;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: IoTMqttAsyncClient.java */
/* renamed from: com.aliyun.alink.linksdk.channel.core.a.e */
/* loaded from: classes.dex */
public class C0968e extends MqttAsyncClient {
    public C0968e(String str, String str2, MqttClientPersistence mqttClientPersistence) {
        super(str, str2, mqttClientPersistence);
    }

    public C0968e(String str, String str2, MqttClientPersistence mqttClientPersistence, MqttPingSender mqttPingSender) {
        super(str, str2, mqttClientPersistence, mqttPingSender);
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttAsyncClient
    protected NetworkModule[] createNetworkModules(String str, MqttConnectOptions mqttConnectOptions) {
        String[] serverURIs = mqttConnectOptions.getServerURIs();
        if (serverURIs == null) {
            serverURIs = new String[]{str};
        } else if (serverURIs.length == 0) {
            serverURIs = new String[]{str};
        }
        NetworkModule[] networkModuleArr = new NetworkModule[serverURIs.length];
        for (int i = 0; i < serverURIs.length; i++) {
            networkModuleArr[i] = m392a(serverURIs[i], mqttConnectOptions);
        }
        return networkModuleArr;
    }

    /* renamed from: a */
    private NetworkModule m392a(String str, MqttConnectOptions mqttConnectOptions) {
        SSLSocketFactoryFactory sSLSocketFactoryFactory;
        String[] enabledCipherSuites;
        SSLSocketFactoryFactory sSLSocketFactoryFactory2;
        String[] enabledCipherSuites2;
        C0969a.m393a("IoTMqttAsyncClient", "createNetworkModule 115 address=" + str + ", client=" + this);
        SocketFactory socketFactory = mqttConnectOptions.getSocketFactory();
        int validateURI = MqttConnectOptions.validateURI(str);
        try {
            URI uri = new URI(str);
            if (uri.getHost() == null && str.contains("_")) {
                try {
                    Field declaredField = URI.class.getDeclaredField("host");
                    declaredField.setAccessible(true);
                    declaredField.set(uri, m391a(str.substring(uri.getScheme().length() + 3)));
                } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
                    throw ExceptionHelper.createMqttException(e.getCause());
                }
            }
            String host = uri.getHost();
            int port = uri.getPort();
            if (validateURI == 0) {
                if (port == -1) {
                    port = 1883;
                }
                if (socketFactory == null) {
                    socketFactory = SocketFactory.getDefault();
                } else if (socketFactory instanceof SSLSocketFactory) {
                    throw ExceptionHelper.createMqttException(32105);
                }
                TCPNetworkModule tCPNetworkModule = new TCPNetworkModule(socketFactory, host, port, getClientId());
                tCPNetworkModule.setConnectTimeout(mqttConnectOptions.getConnectionTimeout());
                return tCPNetworkModule;
            }
            if (validateURI != 1) {
                if (validateURI == 3) {
                    int i = port == -1 ? 80 : port;
                    if (socketFactory == null) {
                        socketFactory = SocketFactory.getDefault();
                    } else if (socketFactory instanceof SSLSocketFactory) {
                        throw ExceptionHelper.createMqttException(32105);
                    }
                    WebSocketNetworkModule webSocketNetworkModule = new WebSocketNetworkModule(socketFactory, str, host, i, getClientId());
                    webSocketNetworkModule.setConnectTimeout(mqttConnectOptions.getConnectionTimeout());
                    return webSocketNetworkModule;
                }
                if (validateURI != 4) {
                    return null;
                }
                int i2 = port == -1 ? 443 : port;
                if (socketFactory == null) {
                    SSLSocketFactoryFactory sSLSocketFactoryFactory3 = new SSLSocketFactoryFactory();
                    Properties sSLProperties = mqttConnectOptions.getSSLProperties();
                    if (sSLProperties != null) {
                        sSLSocketFactoryFactory3.initialize(sSLProperties, null);
                    }
                    sSLSocketFactoryFactory2 = sSLSocketFactoryFactory3;
                    socketFactory = sSLSocketFactoryFactory3.createSocketFactory(null);
                } else {
                    if (!(socketFactory instanceof SSLSocketFactory)) {
                        throw ExceptionHelper.createMqttException(32105);
                    }
                    sSLSocketFactoryFactory2 = null;
                }
                WebSocketSecureNetworkModule webSocketSecureNetworkModule = new WebSocketSecureNetworkModule((SSLSocketFactory) socketFactory, str, host, i2, getClientId());
                webSocketSecureNetworkModule.setSSLhandshakeTimeout(mqttConnectOptions.getConnectionTimeout());
                if (sSLSocketFactoryFactory2 != null && (enabledCipherSuites2 = sSLSocketFactoryFactory2.getEnabledCipherSuites(null)) != null) {
                    webSocketSecureNetworkModule.setEnabledCiphers(enabledCipherSuites2);
                }
                return webSocketSecureNetworkModule;
            }
            C0969a.m394b("IoTMqttAsyncClient", "createNetworkModule useITLS channel=" + MqttConfigure.SECURE_MODE + ",host=" + host + ", port=" + port);
            if (port == -1) {
                port = 1883;
            }
            if (MqttConfigure.SECURE_MODE == 8) {
                C0964a c0964a = new C0964a();
                c0964a.f868c = MqttConfigure.productKey;
                c0964a.f869d = MqttConfigure.productSecret;
                if (!TextUtils.isEmpty(host)) {
                    c0964a.f866a = host;
                } else {
                    c0964a.f866a = c0964a.f868c + c0964a.f866a;
                }
                if (c0964a.f867b != -1) {
                    c0964a.f867b = port;
                }
                return new C0966c(c0964a);
            }
            if (port == -1) {
                port = 8883;
            }
            if (socketFactory == null) {
                sSLSocketFactoryFactory = new SSLSocketFactoryFactory();
                Properties sSLProperties2 = mqttConnectOptions.getSSLProperties();
                if (sSLProperties2 != null) {
                    sSLSocketFactoryFactory.initialize(sSLProperties2, null);
                }
                socketFactory = sSLSocketFactoryFactory.createSocketFactory(null);
            } else {
                if (!(socketFactory instanceof SSLSocketFactory)) {
                    throw ExceptionHelper.createMqttException(32105);
                }
                sSLSocketFactoryFactory = null;
            }
            SSLNetworkModule sSLNetworkModule = new SSLNetworkModule((SSLSocketFactory) socketFactory, host, port, getClientId());
            SSLNetworkModule sSLNetworkModule2 = sSLNetworkModule;
            sSLNetworkModule2.setSSLhandshakeTimeout(mqttConnectOptions.getConnectionTimeout());
            sSLNetworkModule2.setSSLHostnameVerifier(mqttConnectOptions.getSSLHostnameVerifier());
            if (sSLSocketFactoryFactory != null && (enabledCipherSuites = sSLSocketFactoryFactory.getEnabledCipherSuites(null)) != null) {
                sSLNetworkModule2.setEnabledCiphers(enabledCipherSuites);
            }
            return sSLNetworkModule;
        } catch (URISyntaxException e2) {
            throw new IllegalArgumentException("Malformed URI: " + str + ", " + e2.getMessage());
        }
    }

    /* renamed from: a */
    private String m391a(String str) {
        int indexOf = str.indexOf(58);
        if (indexOf == -1) {
            indexOf = str.indexOf(47);
        }
        if (indexOf == -1) {
            indexOf = str.length();
        }
        return str.substring(0, indexOf);
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttAsyncClient, org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect(MqttConnectOptions mqttConnectOptions, Object obj, IMqttActionListener iMqttActionListener) {
        C0969a.m394b("IoTMqttAsyncClient", "mqtt-paho connect start, userContext = [" + obj + "], callback = [" + iMqttActionListener + "], [ clientId = " + getClientId() + "]");
        return super.connect(mqttConnectOptions, obj, iMqttActionListener);
    }
}
