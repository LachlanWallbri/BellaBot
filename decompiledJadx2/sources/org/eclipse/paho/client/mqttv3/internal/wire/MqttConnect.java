package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class MqttConnect extends MqttWireMessage {
    public static final String KEY = "Con";
    private int MqttVersion;
    private boolean cleanSession;
    private String clientId;
    private int keepAliveInterval;
    private char[] password;
    private String userName;
    private String willDestination;
    private MqttMessage willMessage;

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public String getKey() {
        return "Con";
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    protected byte getMessageInfo() {
        return (byte) 0;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public boolean isMessageIdRequired() {
        return false;
    }

    public MqttConnect(byte b, byte[] bArr) throws IOException, MqttException {
        super((byte) 1);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        decodeUTF8(dataInputStream);
        dataInputStream.readByte();
        dataInputStream.readByte();
        this.keepAliveInterval = dataInputStream.readUnsignedShort();
        this.clientId = decodeUTF8(dataInputStream);
        dataInputStream.close();
    }

    public MqttConnect(String str, int i, boolean z, int i2, String str2, char[] cArr, MqttMessage mqttMessage, String str3) {
        super((byte) 1);
        this.clientId = str;
        this.cleanSession = z;
        this.keepAliveInterval = i2;
        this.userName = str2;
        this.password = cArr;
        this.willMessage = mqttMessage;
        this.willDestination = str3;
        this.MqttVersion = i;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public String toString() {
        return super.toString() + " clientId " + this.clientId + " keepAliveInterval " + this.keepAliveInterval;
    }

    public boolean isCleanSession() {
        return this.cleanSession;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    protected byte[] getVariableHeader() throws MqttException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            if (this.MqttVersion == 3) {
                encodeUTF8(dataOutputStream, "MQIsdp");
            } else if (this.MqttVersion == 4) {
                encodeUTF8(dataOutputStream, "MQTT");
            }
            dataOutputStream.write(this.MqttVersion);
            byte b = this.cleanSession ? (byte) 2 : (byte) 0;
            if (this.willMessage != null) {
                b = (byte) (((byte) (b | 4)) | (this.willMessage.getQos() << 3));
                if (this.willMessage.isRetained()) {
                    b = (byte) (b | 32);
                }
            }
            if (this.userName != null) {
                b = (byte) (b | 128);
                if (this.password != null) {
                    b = (byte) (b | 64);
                }
            }
            dataOutputStream.write(b);
            dataOutputStream.writeShort(this.keepAliveInterval);
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public byte[] getPayload() throws MqttException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            encodeUTF8(dataOutputStream, this.clientId);
            if (this.willMessage != null) {
                encodeUTF8(dataOutputStream, this.willDestination);
                dataOutputStream.writeShort(this.willMessage.getPayload().length);
                dataOutputStream.write(this.willMessage.getPayload());
            }
            if (this.userName != null) {
                encodeUTF8(dataOutputStream, this.userName);
                if (this.password != null) {
                    encodeUTF8(dataOutputStream, new String(this.password));
                }
            }
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }
}
