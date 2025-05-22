package com.pudutech.bumblebee.robot.remote_device;

import android.content.Context;
import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.remote_device.LoRaChannelManager2;
import com.pudutech.bumblebee.robot.utils.LimitQueue;
import com.pudutech.bumblebee.robot.utils.SystemTool;
import com.pudutech.bumblebee.robot.utils.UUID;
import com.pudutech.bumblebee.robot_ui.widget.loopview.MessageHandler;
import com.pudutech.hola.protobuf.MessageProtobuf;
import java.nio.ByteBuffer;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.StringsKt;
import org.simpleframework.xml.strategy.Name;
import org.threeten.p095bp.chrono.HijrahDate;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: LoRaClient.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 -2\u00020\u0001:\u0001-B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J+\u0010 \u001a\u00020\u001b2#\u0010!\u001a\u001f\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0017J\u0018\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\nH\u0002J\u0010\u0010&\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020(H\u0002J\u000e\u0010)\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020$J\u0010\u0010*\u001a\u00020\u001b2\u0006\u0010\t\u001a\u00020\nH\u0002J\b\u0010+\u001a\u00020\u001bH\u0002J\u0010\u0010,\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020(H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u0016\u001a\u001f\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001d\u001a\n \u001f*\u0004\u0018\u00010\u001e0\u001eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/remote_device/LoRaClient;", "", "appContext", "Landroid/content/Context;", "lora", "Lcom/pudutech/bumblebee/robot/remote_device/RemoteDeviceInterface;", "(Landroid/content/Context;Lcom/pudutech/bumblebee/robot/remote_device/RemoteDeviceInterface;)V", "getAppContext", "()Landroid/content/Context;", "dataLength", "", "isDecodingMsg", "", "lastTimestamp", "", "legalLength", "localMac", "", "getLora", "()Lcom/pudutech/bumblebee/robot/remote_device/RemoteDeviceInterface;", "msgReceivedQueue", "Lcom/pudutech/bumblebee/robot/utils/LimitQueue;", "onResponse", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "snr", "", "readBytesCount", "receiverBuffer", "Ljava/nio/ByteBuffer;", "kotlin.jvm.PlatformType", "checkResponse", "onResponseCallback", "decodeMsg", "data", "", Name.LENGTH, "dispatchMsg", NotificationCompat.CATEGORY_MESSAGE, "Lcom/pudutech/hola/protobuf/MessageProtobuf$Msg;", "onReceiveMsg", "parseMsg", "resetReceiveField", "sendMsg", "Companion", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class LoRaClient {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int DATA_LENGTH_SIZE = 1;
    private static final byte[] FRAME_HEAD;
    public static final int MSG_RESEND_COUNT = 4;
    public static final double MSG_RESEND_INTERVAL = 2500.0d;
    public static final String TAG = "LoRaClient";
    private final Context appContext;
    private int dataLength;
    private boolean isDecodingMsg;
    private long lastTimestamp;
    private final int legalLength;
    private final String localMac;
    private final RemoteDeviceInterface lora;
    private final LimitQueue<String> msgReceivedQueue;
    private Function1<? super Integer, Unit> onResponse;
    private int readBytesCount;
    private ByteBuffer receiverBuffer;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[LoRaChannelManager2.ReceiveType.values().length];

        static {
            $EnumSwitchMapping$0[LoRaChannelManager2.ReceiveType.Channel.ordinal()] = 1;
        }
    }

    public LoRaClient(Context appContext, RemoteDeviceInterface lora) {
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        Intrinsics.checkParameterIsNotNull(lora, "lora");
        this.appContext = appContext;
        this.lora = lora;
        this.msgReceivedQueue = new LimitQueue<>(32);
        this.receiverBuffer = ByteBuffer.allocate(1024);
        this.legalLength = FRAME_HEAD.length + 1;
        String valueOf = String.valueOf(Random.INSTANCE.nextInt(1000, HijrahDate.MAX_VALUE_OF_ERA));
        StringBuilder sb = new StringBuilder();
        sb.append(valueOf);
        String mac = SystemTool.INSTANCE.getMac(this.appContext);
        if (mac == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        sb.append(StringsKt.removeRange((CharSequence) mac, 0, 4).toString());
        this.localMac = sb.toString();
    }

    public final Context getAppContext() {
        return this.appContext;
    }

    public final RemoteDeviceInterface getLora() {
        return this.lora;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* compiled from: LoRaClient.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/remote_device/LoRaClient$Companion;", "", "()V", "DATA_LENGTH_SIZE", "", "FRAME_HEAD", "", "getFRAME_HEAD", "()[B", "MSG_RESEND_COUNT", "MSG_RESEND_INTERVAL", "", "TAG", "", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final byte[] getFRAME_HEAD() {
            return LoRaClient.FRAME_HEAD;
        }
    }

    static {
        byte b = (byte) 218;
        FRAME_HEAD = new byte[]{b, b};
    }

    private final void sendMsg(MessageProtobuf.Msg msg) {
        Pdlog.m3273d("LoRaClient", "sendMsg() msg = " + msg);
        try {
            byte[] byteArray = msg.toByteArray();
            int length = byteArray.length;
            ByteBuffer allocate = ByteBuffer.allocate(FRAME_HEAD.length + 1 + length);
            allocate.put(FRAME_HEAD);
            allocate.put((byte) (length & 255));
            allocate.put(byteArray);
            byte[] msgData = allocate.array();
            Intrinsics.checkExpressionValueIsNotNull(msgData, "msgData");
            if (msgData.length == 0) {
                return;
            }
            this.lora.send(msgData);
            StringBuilder sb = new StringBuilder();
            sb.append("sendMsg() length = ");
            sb.append(msgData.length);
            sb.append(", data = ");
            String arrays = Arrays.toString(msgData);
            Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
            sb.append(arrays);
            sb.append(", msg = ");
            sb.append(msg);
            Pdlog.m3273d("LoRaClient", sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void decodeMsg(byte[] data, int length) {
        byte b;
        StringBuilder sb = new StringBuilder();
        sb.append("收到串口数据：length = ");
        sb.append(length);
        sb.append(", data = ");
        String arrays = Arrays.toString(data);
        Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
        sb.append(arrays);
        Pdlog.m3273d("LoRaClient", sb.toString());
        try {
            ByteBuffer allocate = ByteBuffer.allocate(256);
            allocate.put(data);
            allocate.flip();
            if (!this.isDecodingMsg) {
                if (allocate.limit() < this.legalLength) {
                    Pdlog.m3273d("LoRaClient", "可读数据小于消息合法长度，等待下一帧数据");
                    allocate.position(allocate.limit());
                    allocate.limit(allocate.capacity());
                } else {
                    while (allocate.hasRemaining()) {
                        byte b2 = allocate.get();
                        if (b2 == FRAME_HEAD[0] && (b = allocate.get()) == FRAME_HEAD[1]) {
                            Pdlog.m3273d("LoRaClient", "找到帧头，[" + ((int) b2) + ", " + ((int) b) + ']');
                            this.dataLength = allocate.get() & 255;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("找到数据长度，dataLength = ");
                            sb2.append(this.dataLength);
                            Pdlog.m3273d("LoRaClient", sb2.toString());
                            if (this.dataLength <= 0) {
                                Pdlog.m3273d("LoRaClient", "数据长度不合法，丢弃");
                                resetReceiveField();
                                this.lora.flush();
                            } else if (this.dataLength > this.receiverBuffer.limit()) {
                                Pdlog.m3277w("LoRaClient", "数据长度大于缓冲区大小，丢弃");
                                resetReceiveField();
                                this.lora.flush();
                            } else {
                                int limit = allocate.limit() - this.legalLength;
                                byte[] bArr = new byte[limit];
                                allocate.get(bArr);
                                this.receiverBuffer.put(bArr);
                                this.readBytesCount += limit;
                                allocate.clear();
                                if (this.readBytesCount == this.dataLength) {
                                    Pdlog.m3273d("LoRaClient", "消息接收完成，开始解析 readBytesCount = " + this.readBytesCount + ", dataLength = " + this.dataLength);
                                    parseMsg(this.dataLength);
                                    resetReceiveField();
                                    this.lora.flush();
                                } else {
                                    this.isDecodingMsg = true;
                                    allocate.limit(allocate.capacity());
                                }
                            }
                        }
                    }
                }
            } else if (this.dataLength > this.receiverBuffer.limit()) {
                Pdlog.m3277w("LoRaClient", "数据长度大于缓冲区大小，丢弃");
                resetReceiveField();
                this.lora.flush();
            } else {
                byte[] bArr2 = new byte[length];
                allocate.get(bArr2);
                this.receiverBuffer.put(bArr2);
                this.readBytesCount += length;
                allocate.clear();
                if (this.readBytesCount < this.dataLength) {
                    Pdlog.m3273d("LoRaClient", "消息未接收完成，等待下一帧数据");
                    allocate.limit(allocate.capacity());
                } else {
                    Pdlog.m3273d("LoRaClient", "消息接收完成，开始解析 readBytesCount = " + this.readBytesCount + ", dataLength = " + this.dataLength);
                    parseMsg(this.dataLength);
                    resetReceiveField();
                    this.lora.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resetReceiveField();
            this.lora.flush();
        }
    }

    private final void resetReceiveField() {
        this.isDecodingMsg = false;
        this.dataLength = 0;
        this.readBytesCount = 0;
        this.receiverBuffer.clear();
    }

    private final void parseMsg(int dataLength) {
        this.receiverBuffer.flip();
        byte[] bArr = new byte[dataLength];
        this.receiverBuffer.get(bArr, 0, dataLength);
        StringBuilder sb = new StringBuilder();
        sb.append("protobufBytes = ");
        String arrays = Arrays.toString(bArr);
        Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
        sb.append(arrays);
        Pdlog.m3273d("LoRaClient", sb.toString());
        try {
            MessageProtobuf.Msg msg = MessageProtobuf.Msg.parseFrom(bArr);
            Pdlog.m3273d("LoRaClient", "parseMsg() finished msg = " + msg);
            Intrinsics.checkExpressionValueIsNotNull(msg, "msg");
            dispatchMsg(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void dispatchMsg(MessageProtobuf.Msg msg) {
        String str = this.localMac;
        if (str == null || str.length() == 0) {
            Pdlog.m3277w("LoRaClient", "dispatchMsg() localMac is null or empty");
            return;
        }
        String receiver = msg.getReceiver();
        if ((!Intrinsics.areEqual(receiver, this.localMac)) && (!Intrinsics.areEqual(receiver, "FFFFFFFFFFFF"))) {
            Pdlog.m3277w("LoRaClient", "The receiver is not me, discard. msg = \n" + msg);
            return;
        }
        boolean contains = this.msgReceivedQueue.contains(msg.getMsgId());
        Pdlog.m3273d("LoRaClient", "msgReceivedQueue contains = " + contains);
        if (!contains) {
            Pdlog.m3273d("LoRaClient", "收到消息，转发到应用层 msg = " + msg);
            Function1<? super Integer, Unit> function1 = this.onResponse;
            if (function1 != null) {
                function1.invoke(Integer.valueOf((int) msg.getTimestamp()));
            }
        }
        LimitQueue<String> limitQueue = this.msgReceivedQueue;
        String msgId = msg.getMsgId();
        Intrinsics.checkExpressionValueIsNotNull(msgId, "msg.msgId");
        limitQueue.offer(msgId);
    }

    public final void checkResponse(Function1<? super Integer, Unit> onResponseCallback) {
        this.lastTimestamp = SystemClock.elapsedRealtime();
        this.onResponse = onResponseCallback;
        MessageProtobuf.Msg msg = MessageProtobuf.Msg.newBuilder().setMsgId(UUID.INSTANCE.generateShortUuid()).setMsgType(MessageHandler.WHAT_SMOOTH_SCROLL_INERTIA).setSender(this.localMac).setReceiver("CCCCCCCCCCCC").build();
        Intrinsics.checkExpressionValueIsNotNull(msg, "msg");
        sendMsg(msg);
    }

    public final void onReceiveMsg(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.lastTimestamp;
        if (elapsedRealtime - j > 5000 || j == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("onReceiveMsg length = ");
        sb.append(data.length);
        sb.append(", data = ");
        String arrays = Arrays.toString(data);
        Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
        sb.append(arrays);
        Pdlog.m3273d("LoRaClient", sb.toString());
        decodeMsg(data, data.length);
    }
}
