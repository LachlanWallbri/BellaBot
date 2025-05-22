package org.eclipse.paho.client.mqttv3.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class TCPNetworkModule implements NetworkModule {
    private static final String CLASS_NAME;
    private static final Logger log;
    private int conTimeout;
    private SocketFactory factory;
    private String host;
    private int port;
    protected Socket socket;

    static {
        String name = TCPNetworkModule.class.getName();
        CLASS_NAME = name;
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, name);
    }

    public TCPNetworkModule(SocketFactory socketFactory, String str, int i, String str2) {
        log.setResourceName(str2);
        this.factory = socketFactory;
        this.host = str;
        this.port = i;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public void start() throws IOException, MqttException {
        try {
            log.fine(CLASS_NAME, "start", "252", new Object[]{this.host, new Integer(this.port), new Long(this.conTimeout * 1000)});
            InetSocketAddress inetSocketAddress = new InetSocketAddress(this.host, this.port);
            if (this.factory instanceof SSLSocketFactory) {
                Socket socket = new Socket();
                socket.connect(inetSocketAddress, this.conTimeout * 1000);
                this.socket = ((SSLSocketFactory) this.factory).createSocket(socket, this.host, this.port, true);
            } else {
                Socket createSocket = this.factory.createSocket();
                this.socket = createSocket;
                createSocket.connect(inetSocketAddress, this.conTimeout * 1000);
            }
        } catch (ConnectException e) {
            log.fine(CLASS_NAME, "start", "250", null, e);
            throw new MqttException(32103, e);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public InputStream getInputStream() throws IOException {
        return this.socket.getInputStream();
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public OutputStream getOutputStream() throws IOException {
        return this.socket.getOutputStream();
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public void stop() throws IOException {
        Socket socket = this.socket;
        if (socket != null) {
            socket.close();
        }
    }

    public void setConnectTimeout(int i) {
        this.conTimeout = i;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public String getServerURI() {
        return "tcp://" + this.host + ":" + this.port;
    }
}
