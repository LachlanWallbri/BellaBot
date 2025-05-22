package com.pudutech.lora.library;

import android.content.Context;
import com.iflytek.cloud.SpeechEvent;
import com.pudutech.base.Pdlog;
import com.pudutech.serialport.library.ISerialPortDataReceiveCallback;
import com.pudutech.serialport.library.ISerialPortOpenStatusCallback;
import com.pudutech.serialport.library.SerialPortHelper;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.simpleframework.xml.strategy.Name;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: LoRaClient.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u0000 (2\u00020\u0001:\u0003()*B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0016J\u000e\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0014J\u000e\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0014J,\u0010\u001b\u001a\u00020\u00162\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001e\u001a\u00020\u001fJ\u0006\u0010 \u001a\u00020!J\u0006\u0010\"\u001a\u00020\u0016J\u0010\u0010#\u001a\u00020\u00162\b\u0010\u0019\u001a\u0004\u0018\u00010\u0014J)\u0010#\u001a\u00020\u00162\b\u0010$\u001a\u0004\u0018\u00010\u00142\b\u0010%\u001a\u0004\u0018\u00010\b2\b\u0010&\u001a\u0004\u0018\u00010\u0012¢\u0006\u0002\u0010'R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, m3961d2 = {"Lcom/pudutech/lora/library/LoRaClient;", "", "()V", SpeechEvent.KEY_EVENT_TTS_BUFFER, "Ljava/nio/ByteBuffer;", "dataReceiveListener", "Lcom/pudutech/lora/library/IDataReceiveListener;", "seq", "", "getSeq", "()B", "setSeq", "(B)V", "serialPortHelper", "Lcom/pudutech/serialport/library/SerialPortHelper;", "serialPortOpenStatusListener", "Lcom/pudutech/lora/library/ISerialPortOpenStatusListener;", "bytesToHexString", "", "src", "", "close", "", MqttServiceConstants.CONNECT_ACTION, "getSum", "data", "getXor", "init", "context", "Landroid/content/Context;", "product", "Lcom/pudutech/lora/library/LoraWatch;", "isConnected", "", "release", "sendData", "LoraAddr", "TypeID", "Str", "([BLjava/lang/Byte;Ljava/lang/String;)V", "Companion", "OnSerialPortDataReceiveCallback", "OnSerialPortOpenStatusCallback", "library_lora_release"}, m3962k = 1, m3963mv = {1, 1, 15})
/* loaded from: classes.dex */
public final class LoRaClient {
    public static final String TAG = "LoRaClient";
    private ByteBuffer buffer;
    private IDataReceiveListener dataReceiveListener;
    private ISerialPortOpenStatusListener serialPortOpenStatusListener;
    private SerialPortHelper serialPortHelper = SerialPortHelper.INSTANCE.getINSTANCE();
    private byte seq = 1;

    public final byte getSeq() {
        return this.seq;
    }

    public final void setSeq(byte b) {
        this.seq = b;
    }

    public final void init(Context context, ISerialPortOpenStatusListener serialPortOpenStatusListener, IDataReceiveListener dataReceiveListener, LoraWatch product) {
        Intrinsics.checkParameterIsNotNull(product, "product");
        this.serialPortOpenStatusListener = serialPortOpenStatusListener;
        this.dataReceiveListener = dataReceiveListener;
        this.serialPortHelper.init(context, LoRaConfig.ID_VENDOR, LoRaConfig.ID_PRODUCT, LoRaConfig.PRODUCT, 9600, 0, new OnSerialPortOpenStatusCallback(), new OnSerialPortDataReceiveCallback());
    }

    public final void connect() {
        try {
            this.serialPortHelper.openSerialPort();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void close() {
        this.serialPortHelper.closeSerialPort();
    }

    public final void sendData(byte[] LoraAddr, Byte TypeID, String Str) {
        if (LoraAddr != null) {
            if (!(LoraAddr.length == 0) && LoraAddr.length == 4) {
                if (Str != null) {
                    if (!(Str.length() == 0)) {
                        if (TypeID == null) {
                            Pdlog.m3274e("LoRaClient", "sendData failure, reason : TypeID is null .");
                            return;
                        }
                        Charset forName = Charset.forName("GBK");
                        Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(\"GBK\")");
                        byte[] bytes = Str.getBytes(forName);
                        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                        int frame_headlen_before_data = LoRaConfig.INSTANCE.getFRAME_HEADLEN_BEFORE_DATA() + bytes.length + 1;
                        ByteBuffer byteBuffer = this.buffer;
                        if (byteBuffer == null) {
                            this.buffer = ByteBuffer.allocate(frame_headlen_before_data);
                        } else {
                            if (byteBuffer == null) {
                                Intrinsics.throwNpe();
                            }
                            if (frame_headlen_before_data > byteBuffer.capacity()) {
                                this.buffer = ByteBuffer.allocate(frame_headlen_before_data);
                            } else {
                                ByteBuffer byteBuffer2 = this.buffer;
                                if (byteBuffer2 == null) {
                                    Intrinsics.throwNpe();
                                }
                                byteBuffer2.clear();
                            }
                        }
                        ByteBuffer byteBuffer3 = this.buffer;
                        if (byteBuffer3 != null) {
                            byteBuffer3.put(LoRaConfig.INSTANCE.getFRAME_DEFAULT_HEAD());
                            byteBuffer3.put(this.seq);
                            byteBuffer3.put(LoraAddr);
                            byteBuffer3.put(TypeID.byteValue());
                            byteBuffer3.put((byte) bytes.length);
                            byteBuffer3.put(bytes);
                            byteBuffer3.put(LoRaConfig.INSTANCE.getFRAME_DEFAULT_CHECK_CODE());
                            int capacity = byteBuffer3.capacity() - byteBuffer3.remaining();
                            byte[] bArr = new byte[capacity];
                            byteBuffer3.flip();
                            byteBuffer3.get(bArr, 0, capacity);
                            Pdlog.m3273d("lora", "len:" + bytes.length);
                            bArr[LoRaConfig.INSTANCE.getFRAME_HEADLEN_BEFORE_DATA() + bytes.length] = getSum(bArr);
                            this.seq = (byte) (this.seq + 1);
                            Pdlog.m3273d("lora", "data:" + bytesToHexString(bArr));
                            sendData(bArr);
                            return;
                        }
                        return;
                    }
                }
                Pdlog.m3274e("LoRaClient", "sendData failure, reason : data is null or empty.");
                return;
            }
        }
        Pdlog.m3274e("LoRaClient", "sendData failure, reason : Loraaddr is null or length != 4.");
    }

    public final String bytesToHexString(byte[] src) {
        StringBuilder sb = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte b : src) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public final void sendData(byte[] data) {
        if (data != null) {
            if (!(data.length == 0)) {
                this.serialPortHelper.writeData(data);
                StringBuilder sb = new StringBuilder();
                sb.append("sendData successful, data[");
                String arrays = Arrays.toString(data);
                Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
                sb.append(arrays);
                sb.append("], length[");
                sb.append(data.length);
                sb.append(']');
                Pdlog.m3273d("LoRaClient", sb.toString());
                return;
            }
        }
        Pdlog.m3274e("LoRaClient", "sendData failure, reason : data is null or empty.");
    }

    public final boolean isConnected() {
        return this.serialPortHelper.isOpened();
    }

    public final void release() {
        this.serialPortHelper.release();
    }

    public final byte getSum(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        int i = 0;
        for (byte b : data) {
            i += b;
        }
        return (byte) i;
    }

    public final byte getXor(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        byte b = data[0];
        int length = data.length;
        for (int i = 1; i < length; i++) {
            b = (byte) (b ^ data[i]);
        }
        return b;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* compiled from: LoRaClient.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/lora/library/LoRaClient$OnSerialPortDataReceiveCallback;", "Lcom/pudutech/serialport/library/ISerialPortDataReceiveCallback;", "(Lcom/pudutech/lora/library/LoRaClient;)V", "onReceive", "", "data", "", Name.LENGTH, "", "library_lora_release"}, m3962k = 1, m3963mv = {1, 1, 15})
    /* loaded from: classes.dex */
    private final class OnSerialPortDataReceiveCallback implements ISerialPortDataReceiveCallback {
        public OnSerialPortDataReceiveCallback() {
        }

        @Override // com.pudutech.serialport.library.ISerialPortDataReceiveCallback
        public void onReceive(byte[] data, int length) {
            Intrinsics.checkParameterIsNotNull(data, "data");
            StringBuilder sb = new StringBuilder();
            sb.append("receiveData successful, data[");
            String arrays = Arrays.toString(data);
            Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
            sb.append(arrays);
            sb.append("], length[");
            sb.append(data.length);
            sb.append(']');
            Pdlog.m3273d("LoRaClient", sb.toString());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* compiled from: LoRaClient.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/lora/library/LoRaClient$OnSerialPortOpenStatusCallback;", "Lcom/pudutech/serialport/library/ISerialPortOpenStatusCallback;", "(Lcom/pudutech/lora/library/LoRaClient;)V", "callbackOpenStatus", "", "opened", "", "library_lora_release"}, m3962k = 1, m3963mv = {1, 1, 15})
    /* loaded from: classes.dex */
    private final class OnSerialPortOpenStatusCallback implements ISerialPortOpenStatusCallback {
        public OnSerialPortOpenStatusCallback() {
        }

        @Override // com.pudutech.serialport.library.ISerialPortOpenStatusCallback
        public void callbackOpenStatus(boolean opened) {
            Pdlog.m3273d("lora", "OpenStatus calllback");
            if (opened) {
                ISerialPortOpenStatusListener iSerialPortOpenStatusListener = LoRaClient.this.serialPortOpenStatusListener;
                if (iSerialPortOpenStatusListener != null) {
                    iSerialPortOpenStatusListener.onReceive(opened);
                    return;
                }
                return;
            }
            Pdlog.m3273d("lora", "device open fail");
        }
    }
}
