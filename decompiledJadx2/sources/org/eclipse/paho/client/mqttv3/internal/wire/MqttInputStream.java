package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.ClientState;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class MqttInputStream extends InputStream {
    private static final String CLASS_NAME;
    private static final Logger log;
    private ClientState clientState;

    /* renamed from: in */
    private DataInputStream f9998in;
    private byte[] packet;
    private long packetLen;
    private ByteArrayOutputStream bais = new ByteArrayOutputStream();
    private long remLen = -1;

    static {
        String name = MqttInputStream.class.getName();
        CLASS_NAME = name;
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, name);
    }

    public MqttInputStream(ClientState clientState, InputStream inputStream) {
        this.clientState = null;
        this.clientState = clientState;
        this.f9998in = new DataInputStream(inputStream);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.f9998in.read();
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.f9998in.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f9998in.close();
    }

    public MqttWireMessage readMqttWireMessage() throws IOException, MqttException {
        try {
            if (this.remLen < 0) {
                this.bais.reset();
                byte readByte = this.f9998in.readByte();
                this.clientState.notifyReceivedBytes(1);
                byte b = (byte) ((readByte >>> 4) & 15);
                if (b < 1 || b > 14) {
                    throw ExceptionHelper.createMqttException(32108);
                }
                this.remLen = MqttWireMessage.readMBI(this.f9998in).getValue();
                this.bais.write(readByte);
                this.bais.write(MqttWireMessage.encodeMBI(this.remLen));
                this.packet = new byte[(int) (this.bais.size() + this.remLen)];
                this.packetLen = 0L;
            }
            if (this.remLen < 0) {
                return null;
            }
            readFully();
            this.remLen = -1L;
            byte[] byteArray = this.bais.toByteArray();
            System.arraycopy(byteArray, 0, this.packet, 0, byteArray.length);
            MqttWireMessage createWireMessage = MqttWireMessage.createWireMessage(this.packet);
            log.fine(CLASS_NAME, "readMqttWireMessage", "501", new Object[]{createWireMessage});
            return createWireMessage;
        } catch (SocketTimeoutException unused) {
            return null;
        }
    }

    private void readFully() throws IOException {
        int size = this.bais.size();
        long j = this.packetLen;
        int i = size + ((int) j);
        int i2 = (int) (this.remLen - j);
        if (i2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        int i3 = 0;
        while (i3 < i2) {
            try {
                int read = this.f9998in.read(this.packet, i + i3, i2 - i3);
                this.clientState.notifyReceivedBytes(read);
                if (read < 0) {
                    throw new EOFException();
                }
                i3 += read;
            } catch (SocketTimeoutException e) {
                this.packetLen += i3;
                throw e;
            }
        }
    }
}
