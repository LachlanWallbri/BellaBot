package com.pudutech.mirsdk.robotsdk;

import android.content.Context;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.pudutech.base.Pdlog;
import com.pudutech.gatecontrollerlib.GateControllerMsg;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.base.CommonKt;
import com.pudutech.serialport.library.ISerialPortDataReceiveCallback;
import com.pudutech.serialport.library.ISerialPortOpenStatusCallback;
import com.pudutech.serialport.library.SerialPortHelper;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.http.HttpStatus;
import org.bouncycastle.math.Primes;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.jetbrains.anko.DimensionsKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: AccLoraConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0010*\u0002!$\u0018\u00002\u00020\u0001:\u0003456B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u0006H\u0016J \u0010+\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u0006H\u0016J\u000e\u0010,\u001a\u00020'2\u0006\u0010-\u001a\u00020\bJ \u0010.\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u000fH\u0002J\b\u00100\u001a\u00020'H\u0016J\u0010\u00101\u001a\u00020'2\u0006\u00102\u001a\u00020\fH\u0002J\u0010\u00103\u001a\u00020'2\u0006\u00102\u001a\u00020\fH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\n \u001d*\u0004\u0018\u00010\u001c0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\"R\u0010\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010%¨\u00067"}, m3961d2 = {"Lcom/pudutech/mirsdk/robotsdk/AccLoraConnection;", "Lcom/pudutech/mirsdk/robotsdk/AccConnectionInterface;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "accListener", "Lcom/pudutech/mirsdk/robotsdk/AccLoraConnection$AccLoraListener;", "getContext", "()Landroid/content/Context;", "crcArray", "", "headChars", "", "", "[Ljava/lang/Byte;", "headLength", "", "loraDataLen", "loraHeaderLen", "loraMsgLen", "msgLength", "parseContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "parseStage", "Lcom/pudutech/mirsdk/robotsdk/AccLoraConnection$ParseStage;", "recvBytesBuffer", "Ljava/nio/ByteBuffer;", "kotlin.jvm.PlatformType", "serialPort", "Lcom/pudutech/serialport/library/SerialPortHelper;", "serialPortData", "com/pudutech/mirsdk/robotsdk/AccLoraConnection$serialPortData$1", "Lcom/pudutech/mirsdk/robotsdk/AccLoraConnection$serialPortData$1;", "serialPortState", "com/pudutech/mirsdk/robotsdk/AccLoraConnection$serialPortState$1", "Lcom/pudutech/mirsdk/robotsdk/AccLoraConnection$serialPortState$1;", "callCloseDoor", "", "seq", "robotId", "accId", "callOpenDoor", MqttServiceConstants.CONNECT_ACTION, "listener", "createAccMsgHead", "cmd", MqttServiceConstants.DISCONNECT_ACTION, "sendMsg", "data", "unpackMsg", "AccLoraListener", "LoRaConfig", "ParseStage", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AccLoraConnection implements AccConnectionInterface {
    private final String TAG;
    private AccLoraListener accListener;
    private final Context context;
    private final byte[] crcArray;
    private final Byte[] headChars;
    private final int headLength;
    private int loraDataLen;
    private int loraHeaderLen;
    private int loraMsgLen;
    private int msgLength;
    private final ExecutorCoroutineDispatcher parseContext;
    private ParseStage parseStage;
    private final ByteBuffer recvBytesBuffer;
    private final SerialPortHelper serialPort;
    private final AccLoraConnection$serialPortData$1 serialPortData;
    private final AccLoraConnection$serialPortState$1 serialPortState;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: AccLoraConnection.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/mirsdk/robotsdk/AccLoraConnection$AccLoraListener;", "", "onStatus", "", "accId", "", "robotId", "cmd", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface AccLoraListener {
        void onStatus(String accId, String robotId, byte cmd);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: AccLoraConnection.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/mirsdk/robotsdk/AccLoraConnection$ParseStage;", "", "(Ljava/lang/String;I)V", "FindHead", "AssignPackage", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum ParseStage {
        FindHead,
        AssignPackage
    }

    /* JADX WARN: Type inference failed for: r1v10, types: [com.pudutech.mirsdk.robotsdk.AccLoraConnection$serialPortState$1] */
    /* JADX WARN: Type inference failed for: r1v11, types: [com.pudutech.mirsdk.robotsdk.AccLoraConnection$serialPortData$1] */
    public AccLoraConnection(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = "acc";
        this.parseContext = ThreadPoolDispatcherKt.newSingleThreadContext("ParseAcc");
        this.recvBytesBuffer = ByteBuffer.allocate(512);
        byte b = (byte) 80;
        byte b2 = (byte) 85;
        byte b3 = (byte) 68;
        byte b4 = (byte) 65;
        byte b5 = (byte) 67;
        byte b6 = (byte) 70;
        this.headChars = new Byte[]{Byte.valueOf(b), Byte.valueOf(b2), Byte.valueOf(b3), Byte.valueOf(b2), Byte.valueOf(b4), Byte.valueOf(b5), Byte.valueOf(b5), Byte.valueOf(b6)};
        this.headLength = 8;
        this.loraDataLen = 31;
        this.loraHeaderLen = 10;
        this.loraMsgLen = this.loraHeaderLen + this.loraDataLen;
        this.serialPort = SerialPortHelper.INSTANCE.getINSTANCE();
        this.serialPortState = new ISerialPortOpenStatusCallback() { // from class: com.pudutech.mirsdk.robotsdk.AccLoraConnection$serialPortState$1
            @Override // com.pudutech.serialport.library.ISerialPortOpenStatusCallback
            public void callbackOpenStatus(boolean opened) {
                String str;
                str = AccLoraConnection.this.TAG;
                Pdlog.m3273d(str, "elevator lora serial open state " + opened);
            }
        };
        this.serialPortData = new ISerialPortDataReceiveCallback() { // from class: com.pudutech.mirsdk.robotsdk.AccLoraConnection$serialPortData$1
            @Override // com.pudutech.serialport.library.ISerialPortDataReceiveCallback
            public void onReceive(byte[] data, int length) {
                String str;
                Intrinsics.checkParameterIsNotNull(data, "data");
                str = AccLoraConnection.this.TAG;
                Pdlog.m3273d(str, "lora recv data " + CommonKt.toHexString(data) + " length:" + length);
                AccLoraConnection.this.unpackMsg(data);
            }
        };
        this.parseStage = ParseStage.FindHead;
        this.crcArray = new byte[]{(byte) 0, (byte) 7, (byte) 14, (byte) 9, (byte) 28, (byte) 27, (byte) 18, (byte) 21, (byte) 56, 63, (byte) 54, (byte) 49, (byte) 36, (byte) 35, (byte) 42, (byte) 45, (byte) 112, (byte) 119, (byte) 126, 121, (byte) 108, (byte) 107, (byte) 98, (byte) 101, (byte) 72, (byte) 79, b6, b4, (byte) 84, TarConstants.LF_GNUTYPE_SPARSE, (byte) 90, (byte) 93, (byte) 224, (byte) 231, (byte) 238, (byte) 233, (byte) 252, (byte) 251, (byte) 242, (byte) 245, (byte) 216, (byte) 223, (byte) 214, (byte) 209, (byte) 196, (byte) 195, (byte) 202, (byte) HttpStatus.SC_RESET_CONTENT, (byte) 144, (byte) 151, (byte) 158, (byte) 153, (byte) 140, (byte) 139, (byte) 130, (byte) 133, (byte) 168, (byte) 175, (byte) 166, (byte) 161, (byte) 180, (byte) 179, (byte) 186, (byte) 189, (byte) 199, (byte) 192, (byte) 201, (byte) HttpStatus.SC_PARTIAL_CONTENT, (byte) 219, (byte) 220, (byte) DimensionsKt.TVDPI, (byte) 210, (byte) 255, (byte) GateControllerMsg.ControlCode.Error, (byte) 241, (byte) 246, (byte) 227, (byte) 228, (byte) 237, (byte) 234, (byte) 183, (byte) 176, (byte) 185, (byte) 190, (byte) 171, (byte) 172, (byte) 165, (byte) 162, (byte) 143, (byte) 136, (byte) 129, (byte) 134, (byte) 147, (byte) 148, (byte) 157, (byte) 154, (byte) 39, (byte) 32, (byte) 41, 46, (byte) 59, (byte) 60, (byte) 53, (byte) 50, (byte) 31, (byte) 24, (byte) 17, (byte) 22, (byte) 3, 4, (byte) 13, (byte) 10, (byte) 87, b, (byte) 89, (byte) 94, (byte) 75, (byte) 76, (byte) 69, 66, (byte) 111, (byte) 104, (byte) 97, (byte) 102, (byte) 115, (byte) 116, (byte) 125, (byte) 122, (byte) 137, (byte) 142, (byte) 135, (byte) 128, (byte) 149, (byte) 146, (byte) 155, (byte) 156, (byte) 177, (byte) 182, (byte) 191, (byte) 184, (byte) 173, (byte) 170, (byte) 163, (byte) 164, (byte) 249, (byte) 254, (byte) 247, (byte) DimensionsKt.HDPI, (byte) 229, (byte) 226, (byte) 235, (byte) 236, (byte) 193, (byte) 198, (byte) HttpStatus.SC_MULTI_STATUS, (byte) 200, (byte) 221, (byte) 218, (byte) Primes.SMALL_FACTOR_LIMIT, (byte) 212, (byte) 105, (byte) 110, (byte) 103, (byte) 96, (byte) 117, (byte) 114, (byte) 123, (byte) 124, (byte) 81, 86, (byte) 95, (byte) 88, (byte) 77, (byte) 74, b5, b3, (byte) 25, (byte) 30, (byte) 23, 16, (byte) 5, (byte) 2, (byte) 11, (byte) 12, (byte) 33, (byte) 38, (byte) 47, (byte) 40, (byte) 61, 58, (byte) 51, (byte) 52, (byte) 78, (byte) 73, (byte) 64, (byte) 71, (byte) 82, b2, (byte) 92, 91, (byte) 118, (byte) 113, (byte) 120, (byte) 127, (byte) 106, (byte) 109, (byte) 100, (byte) 99, (byte) 62, 57, (byte) 48, (byte) 55, (byte) 34, (byte) 37, (byte) 44, (byte) 43, (byte) 6, (byte) 1, (byte) 8, 15, (byte) 26, (byte) 29, (byte) 20, (byte) 19, (byte) 174, (byte) 169, (byte) 160, (byte) 167, (byte) 178, (byte) 181, (byte) 188, (byte) 187, (byte) 150, (byte) 145, (byte) 152, (byte) 159, (byte) 138, (byte) 141, (byte) 132, (byte) 131, (byte) 222, (byte) 217, (byte) 208, (byte) 215, (byte) 194, (byte) 197, (byte) 204, (byte) HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION, (byte) 230, (byte) 225, (byte) 232, (byte) 239, (byte) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, (byte) 253, (byte) 244, (byte) 243};
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: AccLoraConnection.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0004\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/mirsdk/robotsdk/AccLoraConnection$LoRaConfig;", "", "()V", "BAUD_RATE", "", "FLAGS", "ID_PRODUCT", "", "ID_VENDOR", "INTERFACE_BUN", "", "getINTERFACE_BUN", "()Ljava/lang/Void;", "PRODUCT", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    private static final class LoRaConfig {
        public static final int BAUD_RATE = 9600;
        public static final int FLAGS = 0;
        public static final String ID_PRODUCT = "5742";
        public static final String ID_VENDOR = "0483";
        public static final LoRaConfig INSTANCE = new LoRaConfig();
        private static final Void INTERFACE_BUN = null;
        public static final String PRODUCT = "pudu lora";

        private LoRaConfig() {
        }

        public final Void getINTERFACE_BUN() {
            return INTERFACE_BUN;
        }
    }

    public final void connect(AccLoraListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        SerialPortHelper serialPortHelper = this.serialPort;
        if ((serialPortHelper != null ? Boolean.valueOf(serialPortHelper.isOpened()) : null).booleanValue()) {
            return;
        }
        this.serialPort.init(this.context, LoRaConfig.ID_VENDOR, LoRaConfig.ID_PRODUCT, LoRaConfig.PRODUCT, (String) LoRaConfig.INSTANCE.getINTERFACE_BUN(), 9600, 0, this.serialPortState, this.serialPortData);
        this.accListener = listener;
        this.serialPort.openSerialPort();
    }

    @Override // com.pudutech.mirsdk.robotsdk.AccConnectionInterface
    public void disconnect() {
        this.serialPort.closeSerialPort();
    }

    @Override // com.pudutech.mirsdk.robotsdk.AccConnectionInterface
    public void callOpenDoor(String seq, String robotId, String accId) {
        Intrinsics.checkParameterIsNotNull(seq, "seq");
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(accId, "accId");
        Pdlog.m3273d(this.TAG, "elevator lora callOpenDoor  " + seq + ' ' + robotId + ' ' + accId);
        sendMsg(createAccMsgHead(robotId, accId, (byte) 1));
    }

    @Override // com.pudutech.mirsdk.robotsdk.AccConnectionInterface
    public void callCloseDoor(String seq, String robotId, String accId) {
        Intrinsics.checkParameterIsNotNull(seq, "seq");
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(accId, "accId");
        Pdlog.m3273d(this.TAG, "elevator lora callCloseDoor  " + seq + ' ' + robotId + ' ' + accId);
        sendMsg(createAccMsgHead(robotId, accId, (byte) 4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void unpackMsg(byte[] data) {
        ByteBuffer byteBuffer = this.recvBytesBuffer;
        byteBuffer.put(data);
        byteBuffer.flip();
        if (byteBuffer.remaining() < this.headLength) {
            byteBuffer.position(byteBuffer.limit());
            byteBuffer.limit(byteBuffer.capacity());
            return;
        }
        while (byteBuffer.remaining() >= this.headLength) {
            int i = 0;
            while (true) {
                Byte[] bArr = this.headChars;
                if (i >= bArr.length || bArr[i].byteValue() != byteBuffer.get()) {
                    break;
                } else {
                    i++;
                }
            }
            if (i != this.headChars.length) {
                if (byteBuffer.hasRemaining()) {
                    byte[] bArr2 = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bArr2);
                    byteBuffer.position(0);
                    byteBuffer.limit(byteBuffer.capacity());
                    byteBuffer.put(bArr2);
                    byteBuffer.flip();
                } else {
                    byteBuffer.clear();
                    break;
                }
            } else {
                this.parseStage = ParseStage.AssignPackage;
                if (byteBuffer.hasRemaining()) {
                    this.msgLength = byteBuffer.get();
                    int remaining = byteBuffer.remaining();
                    int i2 = this.msgLength;
                    if (remaining > i2) {
                        byte[] bArr3 = new byte[i2];
                        this.msgLength = 0;
                        int position = byteBuffer.position();
                        byteBuffer.position(0);
                        byte b = (byte) 0;
                        for (int i3 = 0; byteBuffer.hasRemaining() && i3 < this.loraMsgLen - 1; i3++) {
                            b = this.crcArray[UByte.m4528constructorimpl((byte) (b ^ byteBuffer.get())) & 255];
                        }
                        byteBuffer.position(position);
                        byteBuffer.get(bArr3);
                        if (b == byteBuffer.get()) {
                            String str = new String(ArraysKt.copyOfRange(bArr3, 0, 12), Charsets.UTF_8);
                            String str2 = new String(ArraysKt.copyOfRange(bArr3, 12, 29), Charsets.UTF_8);
                            byte b2 = bArr3[30];
                            Pdlog.m3273d(this.TAG, "elevator unpackMsg accId: " + str + ", robotId: " + str2 + ", cmd:" + ((int) b2));
                            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, this.parseContext, null, new AccLoraConnection$unpackMsg$$inlined$apply$lambda$1(str, str2, b2, null, this, data), 2, null);
                        }
                        this.parseStage = ParseStage.FindHead;
                        if (byteBuffer.hasRemaining()) {
                            byte[] bArr4 = new byte[byteBuffer.remaining()];
                            byteBuffer.get(bArr4);
                            byteBuffer.position(0);
                            byteBuffer.limit(byteBuffer.capacity());
                            byteBuffer.put(bArr4);
                            byteBuffer.flip();
                        } else {
                            byteBuffer.clear();
                            break;
                        }
                    }
                }
                byteBuffer.position(0);
                break;
            }
        }
        byte[] bArr5 = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr5);
        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());
        byteBuffer.put(bArr5);
        String str3 = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("access lora recv buffer: ");
        byte[] array = this.recvBytesBuffer.array();
        Intrinsics.checkExpressionValueIsNotNull(array, "recvBytesBuffer.array()");
        sb.append(CommonKt.toHexString(array));
        sb.append(" position: ");
        sb.append(this.recvBytesBuffer.position());
        Pdlog.m3273d(str3, sb.toString());
    }

    private final byte[] createAccMsgHead(String robotId, String accId, byte cmd) {
        ByteBuffer allocate = ByteBuffer.allocate(this.loraDataLen);
        Pdlog.m3273d(this.TAG, "robotId: " + robotId + " , accId:" + accId);
        allocate.position(0);
        Charset charset = Charsets.UTF_8;
        if (accId == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = accId.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        allocate.put(bytes);
        allocate.position(12);
        Charset charset2 = Charsets.UTF_8;
        if (robotId == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes2 = robotId.getBytes(charset2);
        Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
        allocate.put(bytes2);
        allocate.position(29);
        allocate.put((byte) 1);
        allocate.put(cmd);
        allocate.flip();
        Pdlog.m3273d(this.TAG, "elevator lora createAccMsgHead " + allocate.array());
        byte[] array = allocate.array();
        Intrinsics.checkExpressionValueIsNotNull(array, "bytes.array()");
        return array;
    }

    private final void sendMsg(byte[] data) {
        ByteBuffer allocate = ByteBuffer.allocate(this.loraMsgLen);
        Pdlog.m3273d(this.TAG, "elevator lora sendMsg  head size:" + this.headChars.length + "  data size: " + data.length);
        allocate.put(ArraysKt.toByteArray(this.headChars));
        allocate.put((byte) data.length);
        allocate.put(data);
        allocate.flip();
        byte b = (byte) 0;
        while (allocate.hasRemaining()) {
            b = this.crcArray[UByte.m4528constructorimpl((byte) (b ^ allocate.get())) & 255];
        }
        allocate.position(allocate.limit());
        allocate.limit(allocate.capacity());
        allocate.put(b);
        allocate.flip();
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("elevator lora sendMsg size: ");
        sb.append(allocate.array().length);
        sb.append(" bytes: ");
        byte[] array = allocate.array();
        Intrinsics.checkExpressionValueIsNotNull(array, "bytes.array()");
        sb.append(CommonKt.toHexString(array));
        Pdlog.m3273d(str, sb.toString());
        this.serialPort.writeData(allocate.array());
    }
}
