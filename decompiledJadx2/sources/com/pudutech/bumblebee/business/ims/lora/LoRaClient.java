package com.pudutech.bumblebee.business.ims.lora;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.ims.IMSKit;
import com.pudutech.bumblebee.business.ims.MsgResendManager;
import com.pudutech.bumblebee.business.ims.MsgSendQueue;
import com.pudutech.bumblebee.business.ims.config.ConnectStatus;
import com.pudutech.bumblebee.business.ims.config.IMSConfig;
import com.pudutech.bumblebee.business.ims.interf.I_IMSClient;
import com.pudutech.bumblebee.business.ims.listener.IConnectStatusListener;
import com.pudutech.bumblebee.business.ims.listener.IMsgReceivedListener;
import com.pudutech.bumblebee.business.ims.listener.IMsgSentStatusListener;
import com.pudutech.bumblebee.business.ims.lora.LoRaChannelManager2;
import com.pudutech.bumblebee.business.ims.utils.LimitQueue;
import com.pudutech.bumblebee.business.ims.utils.SystemTool;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.protobuf.MessageProtobuf;
import com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.DeviceStatusChangedListener;
import com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.MsgReceivedListener;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDevice;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDeviceStatus;
import java.nio.ByteBuffer;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.io.FilenameUtils;
import org.simpleframework.xml.strategy.Name;

/* compiled from: LoRaClient.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 62\u00020\u00012\u00020\u0002:\u00016B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0018\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u000bH\u0002J\u0010\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020&H\u0002J\u0012\u0010'\u001a\u00020\u001f2\b\u0010(\u001a\u0004\u0018\u00010)H\u0002J$\u0010*\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0010\u0010+\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020\"H\u0016J\u0010\u0010-\u001a\u00020\u001f2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010.\u001a\u00020\u001fH\u0016J\u0010\u0010/\u001a\u00020\u001f2\u0006\u00100\u001a\u00020\u0014H\u0016J\b\u00101\u001a\u00020\u001fH\u0002J$\u00102\u001a\u00020\u001f2\b\u0010%\u001a\u0004\u0018\u00010&2\u0006\u00103\u001a\u00020\r2\b\u00104\u001a\u0004\u0018\u000105H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\n \u001d*\u0004\u0018\u00010\u001c0\u001cX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00067"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/lora/LoRaClient;", "Lcom/pudutech/bumblebee/business/ims/interf/I_IMSClient;", "Lcom/pudutech/bumblebee/business/robotsdk/peripherals_listeners/MsgReceivedListener;", "()V", "connectStatus", "Lcom/pudutech/bumblebee/business/ims/config/ConnectStatus;", "connectStatusListener", "Lcom/pudutech/bumblebee/business/ims/listener/IConnectStatusListener;", "context", "Landroid/content/Context;", "dataLength", "", "isClosed", "", "isDecodingMsg", "legalLength", "msgReceivedListener", "Lcom/pudutech/bumblebee/business/ims/listener/IMsgReceivedListener;", "msgReceivedQueue", "Lcom/pudutech/bumblebee/business/ims/utils/LimitQueue;", "", "msgResendManager", "Lcom/pudutech/bumblebee/business/ims/MsgResendManager;", "msgSendQueue", "Lcom/pudutech/bumblebee/business/ims/MsgSendQueue;", "myMac", "readedBytesCount", "receiverBuffer", "Ljava/nio/ByteBuffer;", "kotlin.jvm.PlatformType", "callbackConnectStatus", "", "decodeMsg", "data", "", Name.LENGTH, "dispatchMsg", NotificationCompat.CATEGORY_MESSAGE, "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$Msg;", "handleLoRaDeviceStatus", "status", "Lcom/pudutech/bumblebee/robot/aidl/serialize/PeripheralDeviceStatus;", "init", "onMsgReceived", "bytes", "parseMsg", "release", "removeMsg", "msgId", "resetReceiveField", "sendMsg", "joinResendManager", "msgSentStatusListener", "Lcom/pudutech/bumblebee/business/ims/listener/IMsgSentStatusListener;", "Companion", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoRaClient implements I_IMSClient, MsgReceivedListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final LoRaClient INSTANCE = Companion.SingletonHolder.INSTANCE.getHolder();
    public static final String TAG = "LoRaClient";
    private ConnectStatus connectStatus;
    private IConnectStatusListener connectStatusListener;
    private Context context;
    private int dataLength;
    private boolean isClosed;
    private boolean isDecodingMsg;
    private final int legalLength;
    private IMsgReceivedListener msgReceivedListener;
    private LimitQueue<String> msgReceivedQueue;
    private MsgResendManager msgResendManager;
    private MsgSendQueue msgSendQueue;
    private String myMac;
    private int readedBytesCount;
    private ByteBuffer receiverBuffer;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[PeripheralDeviceStatus.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            $EnumSwitchMapping$0[PeripheralDeviceStatus.DEVICE_CONNECTING.ordinal()] = 1;
            $EnumSwitchMapping$0[PeripheralDeviceStatus.NORMAL.ordinal()] = 2;
            $EnumSwitchMapping$0[PeripheralDeviceStatus.DEVICE_DISCONNECT.ordinal()] = 3;
            $EnumSwitchMapping$0[PeripheralDeviceStatus.FAULT.ordinal()] = 4;
            $EnumSwitchMapping$1 = new int[ConnectStatus.values().length];
            $EnumSwitchMapping$1[ConnectStatus.Connecting.ordinal()] = 1;
            $EnumSwitchMapping$1[ConnectStatus.Connected.ordinal()] = 2;
            $EnumSwitchMapping$1[ConnectStatus.Unconnected.ordinal()] = 3;
            $EnumSwitchMapping$2 = new int[LoRaChannelManager2.ReceiveType.values().length];
            $EnumSwitchMapping$2[LoRaChannelManager2.ReceiveType.Channel.ordinal()] = 1;
        }
    }

    private LoRaClient() {
        this.connectStatus = ConnectStatus.Unconnected;
        this.isClosed = true;
        this.receiverBuffer = ByteBuffer.allocate(1024);
        this.legalLength = IMSConfig.FRAME_HEAD.length + 1;
    }

    public /* synthetic */ LoRaClient(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // com.pudutech.bumblebee.business.ims.interf.I_IMSClient
    public boolean checkMsgLegitimacy(MessageProtobuf.Msg msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        return I_IMSClient.DefaultImpls.checkMsgLegitimacy(this, msg);
    }

    /* compiled from: LoRaClient.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\tB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/lora/LoRaClient$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/bumblebee/business/ims/lora/LoRaClient;", "getINSTANCE", "()Lcom/pudutech/bumblebee/business/ims/lora/LoRaClient;", "TAG", "", "SingletonHolder", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final LoRaClient getINSTANCE() {
            return LoRaClient.INSTANCE;
        }

        /* compiled from: LoRaClient.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/lora/LoRaClient$Companion$SingletonHolder;", "", "()V", "holder", "Lcom/pudutech/bumblebee/business/ims/lora/LoRaClient;", "getHolder", "()Lcom/pudutech/bumblebee/business/ims/lora/LoRaClient;", "setHolder", "(Lcom/pudutech/bumblebee/business/ims/lora/LoRaClient;)V", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        private static final class SingletonHolder {
            public static final SingletonHolder INSTANCE = new SingletonHolder();
            private static LoRaClient holder = new LoRaClient(null);

            private SingletonHolder() {
            }

            public final LoRaClient getHolder() {
                return holder;
            }

            public final void setHolder(LoRaClient loRaClient) {
                Intrinsics.checkParameterIsNotNull(loRaClient, "<set-?>");
                holder = loRaClient;
            }
        }
    }

    @Override // com.pudutech.bumblebee.business.ims.interf.I_IMSClient
    public I_IMSClient init(Context context, IConnectStatusListener connectStatusListener, IMsgReceivedListener msgReceivedListener) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.connectStatusListener = connectStatusListener;
        this.msgReceivedListener = msgReceivedListener;
        this.myMac = SystemTool.INSTANCE.getMac(context);
        this.msgReceivedQueue = new LimitQueue<>(32);
        PeripheralDeviceStatus deviceStatus = Peripherals.INSTANCE.getIms().getDeviceStatus(PeripheralDevice.LORA);
        if (deviceStatus != null) {
            handleLoRaDeviceStatus(deviceStatus);
        }
        Pdlog.m3273d("LoRaClient", "addDeviceStatusChangedListener()");
        Peripherals.INSTANCE.getIms().addDeviceStatusChangedListener(new DeviceStatusChangedListener() { // from class: com.pudutech.bumblebee.business.ims.lora.LoRaClient$init$2
            @Override // com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.DeviceStatusChangedListener
            public void onDeviceStatusChange(PeripheralDevice device, PeripheralDeviceStatus status, String description) {
                Pdlog.m3273d("LoRaClient", "onDeviceStatusChange() device = " + device + ", status = " + status + ", description = " + description);
                if (device != PeripheralDevice.LORA) {
                    return;
                }
                LoRaClient.this.handleLoRaDeviceStatus(status);
            }
        });
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleLoRaDeviceStatus(PeripheralDeviceStatus status) {
        Pdlog.m3273d("LoRaClient", "handleLoRaDeviceStatus() status = " + status);
        if (status == null) {
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[status.ordinal()];
        if (i == 1) {
            callbackConnectStatus(ConnectStatus.Connecting);
            return;
        }
        if (i == 2) {
            callbackConnectStatus(ConnectStatus.Connected);
        } else if (i == 3) {
            callbackConnectStatus(ConnectStatus.Unconnected);
        } else {
            if (i != 4) {
                return;
            }
            callbackConnectStatus(ConnectStatus.Unconnected);
        }
    }

    private final void callbackConnectStatus(ConnectStatus connectStatus) {
        Pdlog.m3273d("LoRaClient", "callbackConnectStatus() connectStatus = " + connectStatus);
        this.connectStatus = connectStatus;
        int i = WhenMappings.$EnumSwitchMapping$1[connectStatus.ordinal()];
        if (i == 1) {
            IConnectStatusListener iConnectStatusListener = this.connectStatusListener;
            if (iConnectStatusListener != null) {
                iConnectStatusListener.onConnecting();
                return;
            }
            return;
        }
        if (i != 2) {
            if (i != 3) {
                return;
            }
            this.isClosed = true;
            MsgResendManager msgResendManager = this.msgResendManager;
            if (msgResendManager != null) {
                msgResendManager.inactive();
            }
            MsgSendQueue msgSendQueue = this.msgSendQueue;
            if (msgSendQueue != null) {
                msgSendQueue.inactive();
            }
            IConnectStatusListener iConnectStatusListener2 = this.connectStatusListener;
            if (iConnectStatusListener2 != null) {
                iConnectStatusListener2.onUnconnected();
                return;
            }
            return;
        }
        this.isClosed = false;
        if (this.msgResendManager == null) {
            this.msgResendManager = new MsgResendManager();
            MsgResendManager msgResendManager2 = this.msgResendManager;
            if (msgResendManager2 != null) {
                msgResendManager2.start();
            }
        }
        MsgResendManager msgResendManager3 = this.msgResendManager;
        if (msgResendManager3 != null) {
            msgResendManager3.active();
        }
        if (this.msgSendQueue == null) {
            this.msgSendQueue = new MsgSendQueue(this);
            MsgSendQueue msgSendQueue2 = this.msgSendQueue;
            if (msgSendQueue2 != null) {
                msgSendQueue2.start();
            }
        }
        MsgSendQueue msgSendQueue3 = this.msgSendQueue;
        if (msgSendQueue3 != null) {
            msgSendQueue3.active();
        }
        Peripherals.INSTANCE.getIms().addMsgReceivedListener(this);
        IConnectStatusListener iConnectStatusListener3 = this.connectStatusListener;
        if (iConnectStatusListener3 != null) {
            iConnectStatusListener3.onConnected();
        }
    }

    @Override // com.pudutech.bumblebee.business.ims.interf.I_IMSClient
    public void sendMsg(MessageProtobuf.Msg msg, boolean joinResendManager, IMsgSentStatusListener msgSentStatusListener) {
        MsgResendManager msgResendManager;
        if (this.isClosed) {
            if (msgSentStatusListener != null) {
                msgSentStatusListener.onSentFailed(msg, "Msg sent failed, the LoRaClient is closed.");
                return;
            }
            return;
        }
        if (this.connectStatus != ConnectStatus.Connected) {
            if (msgSentStatusListener != null) {
                msgSentStatusListener.onSentFailed(msg, "Msg sent failed, the connectStatus is " + this.connectStatus + FilenameUtils.EXTENSION_SEPARATOR);
                return;
            }
            return;
        }
        if (msg == null) {
            if (msgSentStatusListener != null) {
                msgSentStatusListener.onSentFailed(msg, "Msg sent failed, the msg is null.");
                return;
            }
            return;
        }
        if (!checkMsgLegitimacy(msg)) {
            if (msgSentStatusListener != null) {
                msgSentStatusListener.onSentFailed(msg, "Msg sent failed, the message is illegal");
                return;
            }
            return;
        }
        if (joinResendManager && (msgResendManager = this.msgResendManager) != null) {
            msgResendManager.addMsg(msg, msgSentStatusListener);
        }
        try {
            MsgSendQueue msgSendQueue = this.msgSendQueue;
            if (msgSendQueue != null) {
                msgSendQueue.addMsg(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (msgSentStatusListener != null) {
                msgSentStatusListener.onSentFailed(msg, "Msg sent failed, " + e.getMessage());
            }
        }
    }

    @Override // com.pudutech.bumblebee.business.ims.interf.I_IMSClient
    public void release() {
        Pdlog.m3273d("LoRaClient", "release()");
        MsgResendManager msgResendManager = this.msgResendManager;
        if (msgResendManager != null) {
            msgResendManager.release();
        }
        this.msgResendManager = (MsgResendManager) null;
        MsgSendQueue msgSendQueue = this.msgSendQueue;
        if (msgSendQueue != null) {
            msgSendQueue.release();
        }
        this.msgSendQueue = (MsgSendQueue) null;
        LimitQueue<String> limitQueue = this.msgReceivedQueue;
        if (limitQueue != null) {
            limitQueue.clear();
        }
        this.msgReceivedQueue = (LimitQueue) null;
    }

    @Override // com.pudutech.bumblebee.business.ims.interf.I_IMSClient
    public void removeMsg(String msgId) {
        Intrinsics.checkParameterIsNotNull(msgId, "msgId");
        MsgResendManager msgResendManager = this.msgResendManager;
        if (msgResendManager != null) {
            msgResendManager.removeMsg(msgId);
        }
    }

    @Override // com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.MsgReceivedListener
    public void onMsgReceived(byte[] bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        StringBuilder sb = new StringBuilder();
        sb.append("length = ");
        sb.append(bytes.length);
        sb.append(", onMsgReceived() data = ");
        String arrays = Arrays.toString(bytes);
        Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
        sb.append(arrays);
        sb.append(", receiveType = ");
        sb.append(LoRaChannelManager2.INSTANCE.getINSTANCE().getReceiveType());
        Pdlog.m3273d("LoRaClient", sb.toString());
        if (WhenMappings.$EnumSwitchMapping$2[LoRaChannelManager2.INSTANCE.getINSTANCE().getReceiveType().ordinal()] == 1) {
            LoRaChannelManager2.INSTANCE.getINSTANCE().onReceivedData(bytes);
        } else {
            decodeMsg(bytes, bytes.length);
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
                        if (b2 == IMSConfig.FRAME_HEAD[0] && (b = allocate.get()) == IMSConfig.FRAME_HEAD[1]) {
                            Pdlog.m3273d("LoRaClient", "找到帧头，[" + ((int) b2) + ", " + ((int) b) + ']');
                            this.dataLength = allocate.get() & 255;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("找到数据长度，dataLength = ");
                            sb2.append(this.dataLength);
                            Pdlog.m3273d("LoRaClient", sb2.toString());
                            if (this.dataLength <= 0) {
                                Pdlog.m3273d("LoRaClient", "数据长度不合法，丢弃");
                                resetReceiveField();
                                Peripherals.INSTANCE.getIms().flush();
                            } else if (this.dataLength > this.receiverBuffer.limit()) {
                                Pdlog.m3277w("LoRaClient", "数据长度大于缓冲区大小，丢弃");
                                resetReceiveField();
                                Peripherals.INSTANCE.getIms().flush();
                            } else {
                                byte[] bArr = new byte[allocate.limit() - this.legalLength];
                                allocate.get(bArr);
                                this.receiverBuffer.put(bArr);
                                this.readedBytesCount += bArr.length;
                                allocate.clear();
                                if (this.readedBytesCount == this.dataLength) {
                                    Pdlog.m3273d("LoRaClient", "消息接收完成，开始解析 readedBytesCount = " + this.readedBytesCount + ", dataLength = " + this.dataLength);
                                    parseMsg(this.dataLength);
                                    resetReceiveField();
                                    Peripherals.INSTANCE.getIms().flush();
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
                Peripherals.INSTANCE.getIms().flush();
            } else {
                byte[] bArr2 = new byte[length];
                allocate.get(bArr2);
                this.receiverBuffer.put(bArr2);
                this.readedBytesCount += bArr2.length;
                allocate.clear();
                if (this.readedBytesCount < this.dataLength) {
                    Pdlog.m3273d("LoRaClient", "消息未接收完成，等待下一帧数据");
                    allocate.limit(allocate.capacity());
                } else {
                    Pdlog.m3273d("LoRaClient", "消息接收完成，开始解析 readedBytesCount = " + this.readedBytesCount + ", dataLength = " + this.dataLength);
                    parseMsg(this.dataLength);
                    resetReceiveField();
                    Peripherals.INSTANCE.getIms().flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resetReceiveField();
            Peripherals.INSTANCE.getIms().flush();
        }
    }

    private final void resetReceiveField() {
        this.isDecodingMsg = false;
        this.dataLength = 0;
        this.readedBytesCount = 0;
        this.receiverBuffer.clear();
    }

    private final void parseMsg(int dataLength) {
        this.receiverBuffer.flip();
        byte[] bArr = new byte[dataLength];
        this.receiverBuffer.get(bArr, 0, bArr.length);
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
        IMsgSentStatusListener findMsgSentStatusListenerByMsgId;
        IMsgReceivedListener iMsgReceivedListener;
        String receiver = msg.getReceiver();
        if (this.myMac == null) {
            Intrinsics.throwUninitializedPropertyAccessException("myMac");
        }
        if ((!Intrinsics.areEqual(receiver, r1)) && (!Intrinsics.areEqual(receiver, IMSKit.BROADCAST_MAC))) {
            Pdlog.m3276v("LoRaClient", "The receiver is not me, discard. msg = \n" + msg);
            return;
        }
        MsgResendManager msgResendManager = this.msgResendManager;
        if (msgResendManager != null) {
            msgResendManager.removeMsg(msg.getMsgId());
        }
        if (!msg.getResp()) {
            MessageProtobuf.Msg.Builder msgType = MessageProtobuf.Msg.newBuilder().setMsgId(msg.getMsgId()).setMsgType(msg.getMsgType());
            String str = this.myMac;
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException("myMac");
            }
            I_IMSClient.DefaultImpls.sendMsg$default(this, msgType.setSender(str).setReceiver(msg.getSender()).setResp(true).build(), false, null, 4, null);
        } else {
            MsgResendManager msgResendManager2 = this.msgResendManager;
            if (msgResendManager2 != null && (findMsgSentStatusListenerByMsgId = msgResendManager2.findMsgSentStatusListenerByMsgId(msg.getMsgId())) != null) {
                findMsgSentStatusListenerByMsgId.onSentSucceed(msg);
            }
        }
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("msgReceivedQueue contains = ");
        LimitQueue<String> limitQueue = this.msgReceivedQueue;
        sb.append(limitQueue != null ? Boolean.valueOf(limitQueue.contains(msg.getMsgId())) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d("LoRaClient", objArr);
        LimitQueue<String> limitQueue2 = this.msgReceivedQueue;
        if (limitQueue2 != null && !limitQueue2.contains(msg.getMsgId()) && (iMsgReceivedListener = this.msgReceivedListener) != null) {
            iMsgReceivedListener.onMsgReceived(msg);
        }
        LimitQueue<String> limitQueue3 = this.msgReceivedQueue;
        if (limitQueue3 != null) {
            String msgId = msg.getMsgId();
            Intrinsics.checkExpressionValueIsNotNull(msgId, "msg.msgId");
            limitQueue3.offer(msgId);
        }
    }
}
