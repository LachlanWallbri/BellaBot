package com.pudutech.mirsdk.robotsdk;

import android.content.Context;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.pudutech.base.Pdlog;
import com.pudutech.gatecontrollerlib.GateControllerMsg;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.base.CommonKt;
import com.pudutech.mirsdk.elv.proto.Elevator;
import com.pudutech.serialport.library.ISerialPortDataReceiveCallback;
import com.pudutech.serialport.library.ISerialPortOpenStatusCallback;
import com.pudutech.serialport.library.SerialPortHelper;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
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
/* compiled from: ElvLoraConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e*\u0002\u001c\u001f\u0018\u00002\u00020\u0001:\u000289B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J0\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0006H\u0016J\u000e\u0010)\u001a\u00020\"2\u0006\u0010*\u001a\u00020\nJ \u0010+\u001a\u00020,2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0006H\u0002J\b\u0010-\u001a\u00020\"H\u0016J(\u0010.\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00062\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0006H\u0016J(\u0010/\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00062\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0006H\u0016J0\u00100\u001a\u00020\"2\u0006\u00101\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0006H\u0016J \u00102\u001a\u00020\"2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0006H\u0016J \u00103\u001a\u00020\"2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0006H\u0016J(\u00104\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00062\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0006H\u0016J\u0010\u00105\u001a\u00020\"2\u0006\u00106\u001a\u00020\bH\u0002J\u0010\u00107\u001a\u00020\"2\u0006\u00106\u001a\u00020\bH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\n \u0018*\u0004\u0018\u00010\u00170\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001dR\u0010\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010 ¨\u0006:"}, m3961d2 = {"Lcom/pudutech/mirsdk/robotsdk/ElvLoraConnection;", "Lcom/pudutech/mirsdk/robotsdk/ElevatorCommunicateInterface;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "crcArray", "", "elevatorListener", "Lcom/pudutech/mirsdk/robotsdk/ProtobufMsgListener;", "headChars", "", "", "[Ljava/lang/Byte;", "headLength", "", "msgLength", "parseContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "parseStage", "Lcom/pudutech/mirsdk/robotsdk/ElvLoraConnection$ParseStage;", "recvBytesBuffer", "Ljava/nio/ByteBuffer;", "kotlin.jvm.PlatformType", "serialPort", "Lcom/pudutech/serialport/library/SerialPortHelper;", "serialPortData", "com/pudutech/mirsdk/robotsdk/ElvLoraConnection$serialPortData$1", "Lcom/pudutech/mirsdk/robotsdk/ElvLoraConnection$serialPortData$1;", "serialPortState", "com/pudutech/mirsdk/robotsdk/ElvLoraConnection$serialPortState$1", "Lcom/pudutech/mirsdk/robotsdk/ElvLoraConnection$serialPortState$1;", "callElv", "", "curr", "dst", "seq", "", "robotId", "elvId", MqttServiceConstants.CONNECT_ACTION, "listener", "createElvMsgHead", "Lcom/pudutech/mirsdk/elv/proto/Elevator$Elv$Builder;", MqttServiceConstants.DISCONNECT_ACTION, "elvEntered", "elvLeft", "elvLeftState", "stat", "enterElvAck", "leaveElvAck", "prepareRide", "sendMsg", "data", "unpackMsg", "LoRaConfig", "ParseStage", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ElvLoraConnection implements ElevatorCommunicateInterface {
    private final String TAG;
    private final Context context;
    private final byte[] crcArray;
    private ProtobufMsgListener elevatorListener;
    private final Byte[] headChars;
    private final int headLength;
    private int msgLength;
    private final ExecutorCoroutineDispatcher parseContext;
    private ParseStage parseStage;
    private final ByteBuffer recvBytesBuffer;
    private final SerialPortHelper serialPort;
    private final ElvLoraConnection$serialPortData$1 serialPortData;
    private final ElvLoraConnection$serialPortState$1 serialPortState;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: ElvLoraConnection.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/mirsdk/robotsdk/ElvLoraConnection$ParseStage;", "", "(Ljava/lang/String;I)V", "FindHead", "AssignPackage", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum ParseStage {
        FindHead,
        AssignPackage
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [com.pudutech.mirsdk.robotsdk.ElvLoraConnection$serialPortState$1] */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.pudutech.mirsdk.robotsdk.ElvLoraConnection$serialPortData$1] */
    public ElvLoraConnection(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = "elv";
        this.parseContext = ThreadPoolDispatcherKt.newSingleThreadContext("ParseElv");
        this.recvBytesBuffer = ByteBuffer.allocate(512);
        byte b = (byte) 80;
        byte b2 = (byte) 85;
        byte b3 = (byte) 68;
        byte b4 = (byte) 69;
        byte b5 = (byte) 76;
        byte b6 = (byte) 70;
        this.headChars = new Byte[]{Byte.valueOf(b), Byte.valueOf(b2), Byte.valueOf(b3), Byte.valueOf(b2), Byte.valueOf(b4), Byte.valueOf(b5), Byte.valueOf((byte) 86), Byte.valueOf(b6)};
        this.headLength = 8;
        this.serialPort = SerialPortHelper.INSTANCE.getINSTANCE();
        this.serialPortState = new ISerialPortOpenStatusCallback() { // from class: com.pudutech.mirsdk.robotsdk.ElvLoraConnection$serialPortState$1
            @Override // com.pudutech.serialport.library.ISerialPortOpenStatusCallback
            public void callbackOpenStatus(boolean opened) {
                String str;
                str = ElvLoraConnection.this.TAG;
                Pdlog.m3273d(str, "elevator lora serial open state " + opened);
            }
        };
        this.serialPortData = new ISerialPortDataReceiveCallback() { // from class: com.pudutech.mirsdk.robotsdk.ElvLoraConnection$serialPortData$1
            @Override // com.pudutech.serialport.library.ISerialPortDataReceiveCallback
            public void onReceive(byte[] data, int length) {
                String str;
                Intrinsics.checkParameterIsNotNull(data, "data");
                str = ElvLoraConnection.this.TAG;
                Pdlog.m3273d(str, "onReceive: " + CommonKt.toHexString(data));
                ElvLoraConnection.this.unpackMsg(data);
            }
        };
        this.parseStage = ParseStage.FindHead;
        this.crcArray = new byte[]{(byte) 0, (byte) 7, (byte) 14, (byte) 9, (byte) 28, (byte) 27, (byte) 18, (byte) 21, (byte) 56, 63, (byte) 54, (byte) 49, (byte) 36, (byte) 35, (byte) 42, (byte) 45, (byte) 112, (byte) 119, (byte) 126, 121, (byte) 108, (byte) 107, (byte) 98, (byte) 101, (byte) 72, (byte) 79, b6, (byte) 65, (byte) 84, TarConstants.LF_GNUTYPE_SPARSE, (byte) 90, (byte) 93, (byte) 224, (byte) 231, (byte) 238, (byte) 233, (byte) 252, (byte) 251, (byte) 242, (byte) 245, (byte) 216, (byte) 223, (byte) 214, (byte) 209, (byte) 196, (byte) 195, (byte) 202, (byte) HttpStatus.SC_RESET_CONTENT, (byte) 144, (byte) 151, (byte) 158, (byte) 153, (byte) 140, (byte) 139, (byte) 130, (byte) 133, (byte) 168, (byte) 175, (byte) 166, (byte) 161, (byte) 180, (byte) 179, (byte) 186, (byte) 189, (byte) 199, (byte) 192, (byte) 201, (byte) HttpStatus.SC_PARTIAL_CONTENT, (byte) 219, (byte) 220, (byte) DimensionsKt.TVDPI, (byte) 210, (byte) 255, (byte) GateControllerMsg.ControlCode.Error, (byte) 241, (byte) 246, (byte) 227, (byte) 228, (byte) 237, (byte) 234, (byte) 183, (byte) 176, (byte) 185, (byte) 190, (byte) 171, (byte) 172, (byte) 165, (byte) 162, (byte) 143, (byte) 136, (byte) 129, (byte) 134, (byte) 147, (byte) 148, (byte) 157, (byte) 154, (byte) 39, (byte) 32, (byte) 41, 46, (byte) 59, (byte) 60, (byte) 53, (byte) 50, (byte) 31, (byte) 24, (byte) 17, (byte) 22, (byte) 3, 4, (byte) 13, (byte) 10, (byte) 87, b, (byte) 89, (byte) 94, (byte) 75, b5, b4, 66, (byte) 111, (byte) 104, (byte) 97, (byte) 102, (byte) 115, (byte) 116, (byte) 125, (byte) 122, (byte) 137, (byte) 142, (byte) 135, (byte) 128, (byte) 149, (byte) 146, (byte) 155, (byte) 156, (byte) 177, (byte) 182, (byte) 191, (byte) 184, (byte) 173, (byte) 170, (byte) 163, (byte) 164, (byte) 249, (byte) 254, (byte) 247, (byte) DimensionsKt.HDPI, (byte) 229, (byte) 226, (byte) 235, (byte) 236, (byte) 193, (byte) 198, (byte) HttpStatus.SC_MULTI_STATUS, (byte) 200, (byte) 221, (byte) 218, (byte) Primes.SMALL_FACTOR_LIMIT, (byte) 212, (byte) 105, (byte) 110, (byte) 103, (byte) 96, (byte) 117, (byte) 114, (byte) 123, (byte) 124, (byte) 81, 86, (byte) 95, (byte) 88, (byte) 77, (byte) 74, (byte) 67, b3, (byte) 25, (byte) 30, (byte) 23, 16, (byte) 5, (byte) 2, (byte) 11, (byte) 12, (byte) 33, (byte) 38, (byte) 47, (byte) 40, (byte) 61, 58, (byte) 51, (byte) 52, (byte) 78, (byte) 73, (byte) 64, (byte) 71, (byte) 82, b2, (byte) 92, 91, (byte) 118, (byte) 113, (byte) 120, (byte) 127, (byte) 106, (byte) 109, (byte) 100, (byte) 99, (byte) 62, 57, (byte) 48, (byte) 55, (byte) 34, (byte) 37, (byte) 44, (byte) 43, (byte) 6, (byte) 1, (byte) 8, 15, (byte) 26, (byte) 29, (byte) 20, (byte) 19, (byte) 174, (byte) 169, (byte) 160, (byte) 167, (byte) 178, (byte) 181, (byte) 188, (byte) 187, (byte) 150, (byte) 145, (byte) 152, (byte) 159, (byte) 138, (byte) 141, (byte) 132, (byte) 131, (byte) 222, (byte) 217, (byte) 208, (byte) 215, (byte) 194, (byte) 197, (byte) 204, (byte) HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION, (byte) 230, (byte) 225, (byte) 232, (byte) 239, (byte) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, (byte) 253, (byte) 244, (byte) 243};
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: ElvLoraConnection.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\u0007X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/mirsdk/robotsdk/ElvLoraConnection$LoRaConfig;", "", "()V", "BAUD_RATE", "", "FLAGS", "ID_PRODUCT", "", "ID_VENDOR", "INTERFACE_BUN", "getINTERFACE_BUN", "()Ljava/lang/String;", "PRODUCT", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    private static final class LoRaConfig {
        public static final int BAUD_RATE = 9600;
        public static final int FLAGS = 0;
        public static final String ID_PRODUCT = "6011";
        public static final String ID_VENDOR = "0403";
        public static final LoRaConfig INSTANCE = new LoRaConfig();
        private static final String INTERFACE_BUN = "00";
        public static final String PRODUCT = "Quad RS232-HS";

        private LoRaConfig() {
        }

        public final String getINTERFACE_BUN() {
            return INTERFACE_BUN;
        }
    }

    public final void connect(ProtobufMsgListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.serialPort.init(this.context, "0403", "6011", "Quad RS232-HS", LoRaConfig.INSTANCE.getINTERFACE_BUN(), 9600, 0, this.serialPortState, this.serialPortData);
        this.elevatorListener = listener;
        this.serialPort.openSerialPort();
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void disconnect() {
        this.serialPort.closeSerialPort();
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void callElv(String curr, String dst, long seq, String robotId, String elvId) {
        Intrinsics.checkParameterIsNotNull(curr, "curr");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(elvId, "elvId");
        Pdlog.m3273d(this.TAG, "callElv " + Elevator.Elv.MsgType.CallElv + " ,currFloor:" + curr + ",destFloor:" + dst + " robotId:" + robotId + ',' + elvId);
        Elevator.Elv.Builder createElvMsgHead = createElvMsgHead(seq, robotId, elvId);
        createElvMsgHead.setMsgType(Elevator.Elv.MsgType.CallElv);
        Elevator.CallElv.Builder callElv = Elevator.CallElv.newBuilder();
        Intrinsics.checkExpressionValueIsNotNull(callElv, "callElv");
        callElv.setCurrFloor(curr);
        callElv.setDestFloor(dst);
        createElvMsgHead.setCallElv(callElv.build());
        byte[] byteArray = createElvMsgHead.build().toByteArray();
        Intrinsics.checkExpressionValueIsNotNull(byteArray, "elv.build().toByteArray()");
        sendMsg(byteArray);
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void prepareRide(String curr, long seq, String robotId, String elvId) {
        Intrinsics.checkParameterIsNotNull(curr, "curr");
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(elvId, "elvId");
        Pdlog.m3273d(this.TAG, "prepareRide");
        Elevator.Elv.Builder createElvMsgHead = createElvMsgHead(seq, robotId, elvId);
        createElvMsgHead.setMsgType(Elevator.Elv.MsgType.PrepareRide);
        Elevator.PrepareRide.Builder prepareRide = Elevator.PrepareRide.newBuilder();
        Intrinsics.checkExpressionValueIsNotNull(prepareRide, "prepareRide");
        prepareRide.setCurrFloor(curr);
        createElvMsgHead.setPrepareRide(prepareRide.build());
        byte[] byteArray = createElvMsgHead.build().toByteArray();
        Intrinsics.checkExpressionValueIsNotNull(byteArray, "elv.build().toByteArray()");
        sendMsg(byteArray);
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void enterElvAck(long seq, String robotId, String elvId) {
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(elvId, "elvId");
        Pdlog.m3273d(this.TAG, "enterElvAck");
        Elevator.Elv.Builder createElvMsgHead = createElvMsgHead(seq, robotId, elvId);
        createElvMsgHead.setMsgType(Elevator.Elv.MsgType.EnterElvAck);
        createElvMsgHead.setEnterElvAck(Elevator.EnterElvAck.newBuilder().build());
        byte[] byteArray = createElvMsgHead.build().toByteArray();
        Intrinsics.checkExpressionValueIsNotNull(byteArray, "elv.build().toByteArray()");
        sendMsg(byteArray);
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void elvEntered(String curr, long seq, String robotId, String elvId) {
        Intrinsics.checkParameterIsNotNull(curr, "curr");
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(elvId, "elvId");
        Pdlog.m3273d(this.TAG, "elvEntered");
        Elevator.Elv.Builder createElvMsgHead = createElvMsgHead(seq, robotId, elvId);
        createElvMsgHead.setMsgType(Elevator.Elv.MsgType.ElvEntered);
        Elevator.ElvEntered.Builder elvEntered = Elevator.ElvEntered.newBuilder();
        Intrinsics.checkExpressionValueIsNotNull(elvEntered, "elvEntered");
        elvEntered.setCurrFloor(curr);
        createElvMsgHead.setElvEntered(elvEntered.build());
        byte[] byteArray = createElvMsgHead.build().toByteArray();
        Intrinsics.checkExpressionValueIsNotNull(byteArray, "elv.build().toByteArray()");
        sendMsg(byteArray);
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void leaveElvAck(long seq, String robotId, String elvId) {
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(elvId, "elvId");
        Pdlog.m3273d(this.TAG, "leaveElvAck");
        Elevator.Elv.Builder createElvMsgHead = createElvMsgHead(seq, robotId, elvId);
        createElvMsgHead.setMsgType(Elevator.Elv.MsgType.LeaveElvAck);
        createElvMsgHead.setLeaveElvAck(Elevator.LeaveElvAck.newBuilder().build());
        byte[] byteArray = createElvMsgHead.build().toByteArray();
        Intrinsics.checkExpressionValueIsNotNull(byteArray, "elv.build().toByteArray()");
        sendMsg(byteArray);
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void elvLeft(String curr, long seq, String robotId, String elvId) {
        Intrinsics.checkParameterIsNotNull(curr, "curr");
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(elvId, "elvId");
        Pdlog.m3273d(this.TAG, "elvLeft");
        Elevator.Elv.Builder createElvMsgHead = createElvMsgHead(seq, robotId, elvId);
        createElvMsgHead.setMsgType(Elevator.Elv.MsgType.ElvLeft);
        Elevator.ElvLeft.Builder elvLeft = Elevator.ElvLeft.newBuilder();
        Intrinsics.checkExpressionValueIsNotNull(elvLeft, "elvLeft");
        elvLeft.setCurrFloor(curr);
        createElvMsgHead.setElvLeft(elvLeft.build());
        byte[] byteArray = createElvMsgHead.build().toByteArray();
        Intrinsics.checkExpressionValueIsNotNull(byteArray, "elv.build().toByteArray()");
        sendMsg(byteArray);
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void elvLeftState(String stat, String curr, long seq, String robotId, String elvId) {
        Intrinsics.checkParameterIsNotNull(stat, "stat");
        Intrinsics.checkParameterIsNotNull(curr, "curr");
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(elvId, "elvId");
        Pdlog.m3273d(this.TAG, "elvLeftState");
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
                    byteBuffer.put(new byte[byteBuffer.capacity()]);
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
                        for (int i3 = 0; byteBuffer.hasRemaining() && i3 < this.headLength + i2 + 1; i3++) {
                            b = this.crcArray[UByte.m4528constructorimpl((byte) (b ^ byteBuffer.get())) & 255];
                        }
                        byteBuffer.position(position);
                        byteBuffer.get(bArr3);
                        if (b == byteBuffer.get()) {
                            Pdlog.m3273d(this.TAG, "crc success");
                            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, this.parseContext, null, new ElvLoraConnection$unpackMsg$$inlined$apply$lambda$1(bArr3, null, this, data), 2, null);
                        } else {
                            Pdlog.m3273d(this.TAG, "crc error, " + String.valueOf((int) b));
                        }
                        this.parseStage = ParseStage.FindHead;
                        if (byteBuffer.hasRemaining()) {
                            byte[] bArr4 = new byte[byteBuffer.remaining()];
                            byteBuffer.get(bArr4);
                            byteBuffer.position(0);
                            byteBuffer.limit(byteBuffer.capacity());
                            byteBuffer.put(new byte[byteBuffer.capacity()]);
                            byteBuffer.position(0);
                            byteBuffer.limit(byteBuffer.capacity());
                            byteBuffer.put(bArr4);
                            byteBuffer.flip();
                        } else {
                            byteBuffer.position(0);
                            byteBuffer.limit(byteBuffer.capacity());
                            byteBuffer.put(new byte[byteBuffer.capacity()]);
                            byteBuffer.position(0);
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
        byteBuffer.put(new byte[byteBuffer.capacity()]);
        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());
        byteBuffer.put(bArr5);
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("access lora recv buffer: ");
        byte[] array = this.recvBytesBuffer.array();
        Intrinsics.checkExpressionValueIsNotNull(array, "recvBytesBuffer.array()");
        sb.append(CommonKt.toHexString(array));
        sb.append(" position: ");
        sb.append(this.recvBytesBuffer.position());
        Pdlog.m3273d(str, sb.toString());
    }

    private final Elevator.Elv.Builder createElvMsgHead(long seq, String robotId, String elvId) {
        Elevator.Elv.Builder elv = Elevator.Elv.newBuilder();
        Intrinsics.checkExpressionValueIsNotNull(elv, "elv");
        elv.setSeq(seq);
        elv.setTs(System.currentTimeMillis());
        elv.setRobotId(robotId);
        elv.setElvId(elvId);
        return elv;
    }

    private final void sendMsg(byte[] data) {
        ByteBuffer allocate = ByteBuffer.allocate(this.headLength + data.length + 2);
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
