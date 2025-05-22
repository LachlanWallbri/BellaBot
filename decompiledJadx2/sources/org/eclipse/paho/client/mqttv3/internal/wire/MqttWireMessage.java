package org.eclipse.paho.client.mqttv3.internal.wire;

import com.google.api.client.http.HttpMethods;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public abstract class MqttWireMessage {
    public static final byte MESSAGE_TYPE_CONNACK = 2;
    public static final byte MESSAGE_TYPE_CONNECT = 1;
    public static final byte MESSAGE_TYPE_DISCONNECT = 14;
    public static final byte MESSAGE_TYPE_PINGREQ = 12;
    public static final byte MESSAGE_TYPE_PINGRESP = 13;
    public static final byte MESSAGE_TYPE_PUBACK = 4;
    public static final byte MESSAGE_TYPE_PUBCOMP = 7;
    public static final byte MESSAGE_TYPE_PUBLISH = 3;
    public static final byte MESSAGE_TYPE_PUBREC = 5;
    public static final byte MESSAGE_TYPE_PUBREL = 6;
    public static final byte MESSAGE_TYPE_SUBACK = 9;
    public static final byte MESSAGE_TYPE_SUBSCRIBE = 8;
    public static final byte MESSAGE_TYPE_UNSUBACK = 11;
    public static final byte MESSAGE_TYPE_UNSUBSCRIBE = 10;
    private static final String[] PACKET_NAMES = {"reserved", HttpMethods.CONNECT, "CONNACK", "PUBLISH", "PUBACK", "PUBREC", "PUBREL", "PUBCOMP", "SUBSCRIBE", "SUBACK", "UNSUBSCRIBE", "UNSUBACK", "PINGREQ", "PINGRESP", "DISCONNECT"};
    protected static final String STRING_ENCODING = "UTF-8";
    protected boolean duplicate = false;
    protected int msgId = 0;
    private byte type;

    protected abstract byte getMessageInfo();

    public byte[] getPayload() throws MqttException {
        return new byte[0];
    }

    protected abstract byte[] getVariableHeader() throws MqttException;

    public boolean isMessageIdRequired() {
        return true;
    }

    public boolean isRetryable() {
        return false;
    }

    public MqttWireMessage(byte b) {
        this.type = b;
    }

    public byte getType() {
        return this.type;
    }

    public int getMessageId() {
        return this.msgId;
    }

    public void setMessageId(int i) {
        this.msgId = i;
    }

    public String getKey() {
        return new Integer(getMessageId()).toString();
    }

    public byte[] getHeader() throws MqttException {
        try {
            int type = ((getType() & 15) << 4) ^ (getMessageInfo() & 15);
            byte[] variableHeader = getVariableHeader();
            int length = variableHeader.length + getPayload().length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeByte(type);
            dataOutputStream.write(encodeMBI(length));
            dataOutputStream.write(variableHeader);
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }

    public static MqttWireMessage createWireMessage(MqttPersistable mqttPersistable) throws MqttException {
        byte[] payloadBytes = mqttPersistable.getPayloadBytes();
        if (payloadBytes == null) {
            payloadBytes = new byte[0];
        }
        return createWireMessage(new MultiByteArrayInputStream(mqttPersistable.getHeaderBytes(), mqttPersistable.getHeaderOffset(), mqttPersistable.getHeaderLength(), payloadBytes, mqttPersistable.getPayloadOffset(), mqttPersistable.getPayloadLength()));
    }

    public static MqttWireMessage createWireMessage(byte[] bArr) throws MqttException {
        return createWireMessage(new ByteArrayInputStream(bArr));
    }

    private static MqttWireMessage createWireMessage(InputStream inputStream) throws MqttException {
        try {
            DataInputStream dataInputStream = new DataInputStream(new CountingInputStream(inputStream));
            int readUnsignedByte = dataInputStream.readUnsignedByte();
            byte b = (byte) (readUnsignedByte >> 4);
            byte b2 = (byte) (readUnsignedByte & 15);
            long counter = (r0.getCounter() + readMBI(dataInputStream).getValue()) - r0.getCounter();
            byte[] bArr = new byte[0];
            if (counter > 0) {
                int i = (int) counter;
                byte[] bArr2 = new byte[i];
                dataInputStream.readFully(bArr2, 0, i);
                bArr = bArr2;
            }
            if (b == 1) {
                return new MqttConnect(b2, bArr);
            }
            if (b == 3) {
                return new MqttPublish(b2, bArr);
            }
            if (b == 4) {
                return new MqttPubAck(b2, bArr);
            }
            if (b == 7) {
                return new MqttPubComp(b2, bArr);
            }
            if (b == 2) {
                return new MqttConnack(b2, bArr);
            }
            if (b == 12) {
                return new MqttPingReq(b2, bArr);
            }
            if (b == 13) {
                return new MqttPingResp(b2, bArr);
            }
            if (b == 8) {
                return new MqttSubscribe(b2, bArr);
            }
            if (b == 9) {
                return new MqttSuback(b2, bArr);
            }
            if (b == 10) {
                return new MqttUnsubscribe(b2, bArr);
            }
            if (b == 11) {
                return new MqttUnsubAck(b2, bArr);
            }
            if (b == 6) {
                return new MqttPubRel(b2, bArr);
            }
            if (b == 5) {
                return new MqttPubRec(b2, bArr);
            }
            if (b == 14) {
                return new MqttDisconnect(b2, bArr);
            }
            throw ExceptionHelper.createMqttException(6);
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static byte[] encodeMBI(long j) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        do {
            byte b = (byte) (j % 128);
            j /= 128;
            if (j > 0) {
                b = (byte) (b | 128);
            }
            byteArrayOutputStream.write(b);
            i++;
            if (j <= 0) {
                break;
            }
        } while (i < 4);
        return byteArrayOutputStream.toByteArray();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static MultiByteInteger readMBI(DataInputStream dataInputStream) throws IOException {
        long j = 0;
        int i = 0;
        int i2 = 1;
        do {
            i++;
            j += (r5 & Byte.MAX_VALUE) * i2;
            i2 *= 128;
        } while ((dataInputStream.readByte() & 128) != 0);
        return new MultiByteInteger(j, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] encodeMessageId() throws MqttException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeShort(this.msgId);
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }

    public void setDuplicate(boolean z) {
        this.duplicate = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void encodeUTF8(DataOutputStream dataOutputStream, String str) throws MqttException {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            byte length = (byte) ((bytes.length >>> 8) & 255);
            byte length2 = (byte) ((bytes.length >>> 0) & 255);
            dataOutputStream.write(length);
            dataOutputStream.write(length2);
            dataOutputStream.write(bytes);
        } catch (UnsupportedEncodingException e) {
            throw new MqttException(e);
        } catch (IOException e2) {
            throw new MqttException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String decodeUTF8(DataInputStream dataInputStream) throws MqttException {
        try {
            byte[] bArr = new byte[dataInputStream.readUnsignedShort()];
            dataInputStream.readFully(bArr);
            return new String(bArr, "UTF-8");
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }

    public String toString() {
        return PACKET_NAMES[this.type];
    }
}
