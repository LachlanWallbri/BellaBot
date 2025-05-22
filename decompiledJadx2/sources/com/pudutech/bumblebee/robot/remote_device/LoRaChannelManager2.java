package com.pudutech.bumblebee.robot.remote_device;

import android.util.Log;
import android.util.Pair;
import com.pudutech.base.Tools;
import com.pudutech.bumblebee.robot.aidl.serialize.Channel;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: LoRaChannelManager2.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 I2\u00020\u0001:\bIJKLMNOPB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020)H\u0002J4\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020#2\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u00102\u001a\u0004\u0018\u00010\r2\n\b\u0002\u00103\u001a\u0004\u0018\u000104H\u0002J\b\u00105\u001a\u00020/H\u0002J\b\u00106\u001a\u00020/H\u0002J\u0010\u00107\u001a\u00020/2\u0006\u00108\u001a\u00020\u0018H\u0002J\u0016\u00109\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006J\u0006\u0010:\u001a\u00020\u0005J\u0006\u0010;\u001a\u00020/J\u000e\u0010<\u001a\u00020/2\u0006\u0010=\u001a\u00020\u0014J\b\u0010>\u001a\u00020/H\u0002J\b\u0010?\u001a\u00020/H\u0002J\u0018\u0010@\u001a\u00020\u000f2\u0006\u00108\u001a\u00020\u00182\u0006\u0010A\u001a\u00020BH\u0002J\u001a\u0010C\u001a\u00020/2\b\u00101\u001a\u0004\u0018\u00010\u00052\b\u0010D\u001a\u0004\u0018\u00010\u0016J\b\u0010E\u001a\u00020/H\u0002J\u0010\u0010F\u001a\u00020/2\u0006\u0010G\u001a\u00020HH\u0002R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\n !*\u0004\u0018\u00010 0 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Q"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2;", "", "()V", "channelList", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/robot/aidl/serialize/Channel;", "Lkotlin/collections/ArrayList;", "currentChannel", "getCurrentChannel", "()Lcom/pudutech/bumblebee/robot/aidl/serialize/Channel;", "setCurrentChannel", "(Lcom/pudutech/bumblebee/robot/aidl/serialize/Channel;)V", "headString", "", "isChangingMode", "", "isDecoding", "isFirstStartup", "isSettingChannel", "moduleAddressBytes", "", "onSetupChannelListener", "Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2$OnSetupChannelListener;", "readBytesCount", "", "receiveType", "Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2$ReceiveType;", "getReceiveType", "()Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2$ReceiveType;", "setReceiveType", "(Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2$ReceiveType;)V", "receiverBuffer", "Ljava/nio/ByteBuffer;", "kotlin.jvm.PlatformType", "setupChannelStatus", "Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2$SetupChannelStatus;", "setupChannelTimer", "Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2$SetupChannelTimer;", "sourceChannel", "targetChannel", "calcChannelByte", "", "frequency", "", "calcChannelFrequency", "byte", "callbackSetupChannelStatus", "", "status", "channel", "errMsg", "resetFailedType", "Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2$ResetFailedType;", "cancelSetupChannelTimer", "decodeMsg", "exportAndSetupDirectionOut", "gpioNum", "getChannelList", "getDefaultChannel", "init", "onReceivedData", "data", "resetLoRa", "setupChannel", "setupLevel", "level", "Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2$Level;", "setupLocalChannel", "listener", "startSetupChannelTimer", "switchLevelMode", "mode", "Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2$LevelMode;", "Companion", "Level", "LevelMode", "OnSetupChannelListener", "ReceiveType", "ResetFailedType", "SetupChannelStatus", "SetupChannelTimer", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class LoRaChannelManager2 {
    private static final int GPIO_NUM_132 = 132;
    private static final int HEAD_LENGTH = 3;
    private static final int M0_GPIO_NUM = 134;
    private static final int M1_GPIO_NUM = 131;
    private static final String TAG = "LoRaChannelManager2";
    private final ArrayList<Channel> channelList;
    private volatile Channel currentChannel;
    private String headString;
    private boolean isChangingMode;
    private boolean isDecoding;
    private boolean isFirstStartup;
    private boolean isSettingChannel;
    private byte[] moduleAddressBytes;
    private OnSetupChannelListener onSetupChannelListener;
    private int readBytesCount;
    private ReceiveType receiveType;
    private ByteBuffer receiverBuffer;
    private SetupChannelStatus setupChannelStatus;
    private SetupChannelTimer setupChannelTimer;
    private Channel sourceChannel;
    private Channel targetChannel;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<LoRaChannelManager2>() { // from class: com.pudutech.bumblebee.robot.remote_device.LoRaChannelManager2$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LoRaChannelManager2 invoke() {
            return new LoRaChannelManager2(null);
        }
    });

    /* compiled from: LoRaChannelManager2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2$LevelMode;", "", "(Ljava/lang/String;I)V", "DeepSleep", "Normal", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public enum LevelMode {
        DeepSleep,
        Normal
    }

    /* compiled from: LoRaChannelManager2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2$ReceiveType;", "", "(Ljava/lang/String;I)V", "Business", "Channel", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public enum ReceiveType {
        Business,
        Channel
    }

    /* compiled from: LoRaChannelManager2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2$ResetFailedType;", "", "(Ljava/lang/String;I)V", "CentralControlTimeout", "Other", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public enum ResetFailedType {
        CentralControlTimeout,
        Other
    }

    /* compiled from: LoRaChannelManager2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2$SetupChannelStatus;", "", "(Ljava/lang/String;I)V", "Normal", "Setting", "Succeed", "Failed", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public enum SetupChannelStatus {
        Normal,
        Setting,
        Succeed,
        Failed
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SetupChannelStatus.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[SetupChannelStatus.Normal.ordinal()] = 1;
            $EnumSwitchMapping$0[SetupChannelStatus.Setting.ordinal()] = 2;
            $EnumSwitchMapping$0[SetupChannelStatus.Succeed.ordinal()] = 3;
            $EnumSwitchMapping$0[SetupChannelStatus.Failed.ordinal()] = 4;
            $EnumSwitchMapping$1 = new int[LevelMode.values().length];
            $EnumSwitchMapping$1[LevelMode.DeepSleep.ordinal()] = 1;
            $EnumSwitchMapping$1[LevelMode.Normal.ordinal()] = 2;
        }
    }

    private LoRaChannelManager2() {
        this.receiverBuffer = ByteBuffer.allocate(64);
        this.isFirstStartup = true;
        this.receiveType = ReceiveType.Channel;
        this.setupChannelStatus = SetupChannelStatus.Normal;
        this.channelList = new ArrayList<>();
    }

    public /* synthetic */ LoRaChannelManager2(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final ReceiveType getReceiveType() {
        return this.receiveType;
    }

    public final void setReceiveType(ReceiveType receiveType) {
        Intrinsics.checkParameterIsNotNull(receiveType, "<set-?>");
        this.receiveType = receiveType;
    }

    public final Channel getCurrentChannel() {
        return this.currentChannel;
    }

    public final void setCurrentChannel(Channel channel) {
        this.currentChannel = channel;
    }

    /* compiled from: LoRaChannelManager2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2$Level;", "", ES6Iterator.VALUE_PROPERTY, "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "High", "Low", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public enum Level {
        High("0"),
        Low("1");

        private final String value;

        Level(String str) {
            this.value = str;
        }

        public final String getValue() {
            return this.value;
        }
    }

    /* compiled from: LoRaChannelManager2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2$Companion;", "", "()V", "GPIO_NUM_132", "", "HEAD_LENGTH", "INSTANCE", "Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2;", "getINSTANCE", "()Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2;", "INSTANCE$delegate", "Lkotlin/Lazy;", "M0_GPIO_NUM", "M1_GPIO_NUM", "TAG", "", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class Companion {
        public final LoRaChannelManager2 getINSTANCE() {
            Lazy lazy = LoRaChannelManager2.INSTANCE$delegate;
            Companion companion = LoRaChannelManager2.INSTANCE;
            return (LoRaChannelManager2) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final Channel getDefaultChannel() {
        Channel channel = new Channel();
        channel.setChannelId(0);
        channel.setSendFrequency(LoRaChannelConfig.INSTANCE.getDefaultFrequency().floatValue());
        channel.setReceiveFrequency(LoRaChannelConfig.INSTANCE.getDefaultFrequency().floatValue());
        return channel;
    }

    public final void init() {
        exportAndSetupDirectionOut(131);
        exportAndSetupDirectionOut(134);
        exportAndSetupDirectionOut(132);
        switchLevelMode(LevelMode.Normal);
        resetLoRa();
    }

    public final void onReceivedData(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        StringBuilder sb = new StringBuilder();
        sb.append("onReceivedData() data = ");
        String arrays = Arrays.toString(data);
        Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
        sb.append(arrays);
        Log.i(TAG, sb.toString());
        try {
            this.receiverBuffer.put(data);
            this.readBytesCount += data.length;
            if (this.readBytesCount < 3) {
                return;
            }
            this.receiverBuffer.flip();
            if (!this.isDecoding) {
                byte[] bArr = new byte[3];
                this.receiverBuffer.get(bArr, 0, bArr.length);
                this.headString = new String(bArr, Charsets.UTF_8);
            }
            decodeMsg();
        } catch (Exception e) {
            e.printStackTrace();
            this.receiverBuffer.clear();
            this.readBytesCount = 0;
            this.isDecoding = false;
            this.headString = (String) null;
        }
    }

    private final void decodeMsg() {
        String str;
        this.isDecoding = true;
        ByteBuffer byteBuffer = this.receiverBuffer;
        Log.i(TAG, "headString = " + this.headString);
        String str2 = this.headString;
        if (str2 != null) {
            int hashCode = str2.hashCode();
            if (hashCode == 37278) {
                if (str2.equals("#rm")) {
                    int i = this.readBytesCount;
                    if (i < 4) {
                        byteBuffer.position(byteBuffer.limit());
                        byteBuffer.limit(byteBuffer.capacity());
                        return;
                    }
                    if (i == 4) {
                        byte[] bArr = new byte[4];
                        byteBuffer.position(0);
                        byteBuffer.get(bArr, 0, bArr.length);
                        StringBuilder sb = new StringBuilder();
                        sb.append("收到#rm消息：");
                        String arrays = Arrays.toString(bArr);
                        Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
                        sb.append(arrays);
                        Log.i(TAG, sb.toString());
                        int i2 = bArr[3] & 255;
                        if (i2 == 0) {
                            byte b = (byte) 193;
                            byte[] bArr2 = {b, b, b};
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("获取模组参数：");
                            String arrays2 = Arrays.toString(bArr2);
                            Intrinsics.checkExpressionValueIsNotNull(arrays2, "java.util.Arrays.toString(this)");
                            sb2.append(arrays2);
                            Log.i(TAG, sb2.toString());
                            LoRaClient.INSTANCE.getINSTANCE().sendData(bArr2);
                        }
                        Log.i(TAG, "模组当前模式： " + i2);
                        if (this.isFirstStartup) {
                            this.receiveType = ReceiveType.Business;
                            this.isFirstStartup = false;
                        } else if (i2 == 3 && this.isChangingMode) {
                            Log.i(TAG, "设置信道成功");
                            callbackSetupChannelStatus$default(this, SetupChannelStatus.Succeed, this.targetChannel, null, null, 12, null);
                            cancelSetupChannelTimer();
                            this.receiveType = ReceiveType.Business;
                            Log.i(TAG, "receiveType = " + this.receiveType);
                            this.targetChannel = (Channel) null;
                            this.isChangingMode = false;
                        }
                        byteBuffer.clear();
                        this.readBytesCount = 0;
                        this.isDecoding = false;
                        this.headString = (String) null;
                        return;
                    }
                    return;
                }
            } else if (hashCode == 37284 && str2.equals("#rs")) {
                int i3 = this.readBytesCount;
                if (i3 < 9) {
                    byteBuffer.position(byteBuffer.limit());
                    byteBuffer.limit(byteBuffer.capacity());
                    return;
                }
                if (i3 == 9) {
                    byte[] bArr3 = new byte[9];
                    byteBuffer.position(0);
                    byteBuffer.get(bArr3, 0, bArr3.length);
                    if (this.isFirstStartup) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("首次启动，忽略#rs消息：");
                        String arrays3 = Arrays.toString(bArr3);
                        Intrinsics.checkExpressionValueIsNotNull(arrays3, "java.util.Arrays.toString(this)");
                        sb3.append(arrays3);
                        Log.i(TAG, sb3.toString());
                    } else {
                        Log.i(TAG, "非首次启动，切换到DeepSleep模式设置信道");
                        switchLevelMode(LevelMode.DeepSleep);
                    }
                    byteBuffer.clear();
                    this.readBytesCount = 0;
                    this.isDecoding = false;
                    this.headString = (String) null;
                    return;
                }
                return;
            }
        }
        int i4 = this.readBytesCount;
        if (i4 < 11) {
            byteBuffer.position(byteBuffer.limit());
            byteBuffer.limit(byteBuffer.capacity());
            return;
        }
        if (i4 == 11) {
            byte[] bArr4 = new byte[11];
            byteBuffer.position(0);
            byteBuffer.get(bArr4, 0, bArr4.length);
            StringBuilder sb4 = new StringBuilder();
            sb4.append("bytes = ");
            String arrays4 = Arrays.toString(bArr4);
            Intrinsics.checkExpressionValueIsNotNull(arrays4, "java.util.Arrays.toString(this)");
            sb4.append(arrays4);
            Log.i(TAG, sb4.toString());
            this.moduleAddressBytes = ArraysKt.copyOfRange(bArr4, 1, 7);
            byte[] copyOfRange = ArraysKt.copyOfRange(bArr4, 8, 10);
            this.sourceChannel = new Channel();
            Channel channel = this.sourceChannel;
            if (channel != null) {
                channel.setSendFrequency(calcChannelFrequency(copyOfRange[0]));
            }
            Channel channel2 = this.sourceChannel;
            if (channel2 != null) {
                channel2.setReceiveFrequency(calcChannelFrequency(copyOfRange[1]));
            }
            StringBuilder sb5 = new StringBuilder();
            sb5.append("模组地址 = ");
            byte[] bArr5 = this.moduleAddressBytes;
            if (bArr5 != null) {
                str = Arrays.toString(bArr5);
                Intrinsics.checkExpressionValueIsNotNull(str, "java.util.Arrays.toString(this)");
            } else {
                str = null;
            }
            sb5.append(str);
            sb5.append(", 原信道 = ");
            sb5.append(this.sourceChannel);
            sb5.append("--");
            String arrays5 = Arrays.toString(copyOfRange);
            Intrinsics.checkExpressionValueIsNotNull(arrays5, "java.util.Arrays.toString(this)");
            sb5.append(arrays5);
            Log.i(TAG, sb5.toString());
            if (!this.isSettingChannel) {
                setupChannel();
            } else {
                StringBuilder sb6 = new StringBuilder();
                sb6.append("收到设置信道回调：");
                String arrays6 = Arrays.toString(bArr4);
                Intrinsics.checkExpressionValueIsNotNull(arrays6, "java.util.Arrays.toString(this)");
                sb6.append(arrays6);
                sb6.append("，目标信道：");
                sb6.append(this.targetChannel);
                sb6.append(", 模组信道：");
                sb6.append(this.sourceChannel);
                Log.i(TAG, sb6.toString());
                if (Intrinsics.areEqual(this.targetChannel, this.sourceChannel)) {
                    this.isChangingMode = true;
                    switchLevelMode(LevelMode.Normal);
                    byte b2 = (byte) 196;
                    byte[] bArr6 = {b2, b2, b2};
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append("切换模式：");
                    String arrays7 = Arrays.toString(bArr6);
                    Intrinsics.checkExpressionValueIsNotNull(arrays7, "java.util.Arrays.toString(this)");
                    sb7.append(arrays7);
                    Log.i(TAG, sb7.toString());
                    LoRaClient.INSTANCE.getINSTANCE().sendData(bArr6);
                } else {
                    Log.w(TAG, "设置信道失败");
                }
            }
            byteBuffer.clear();
            this.readBytesCount = 0;
            this.isDecoding = false;
            this.headString = (String) null;
        }
    }

    public final void setupLocalChannel(Channel channel, OnSetupChannelListener listener) {
        if (channel == null) {
            Log.w(TAG, "setupLocalChannel() failure, reason : channel is null.");
            callbackSetupChannelStatus$default(this, SetupChannelStatus.Failed, null, "channel is null", null, 10, null);
            return;
        }
        this.targetChannel = channel;
        this.onSetupChannelListener = listener;
        callbackSetupChannelStatus$default(this, SetupChannelStatus.Setting, this.targetChannel, null, null, 12, null);
        this.receiveType = ReceiveType.Channel;
        startSetupChannelTimer();
    }

    private final void setupChannel() {
        Log.i(TAG, "private setupChannel() targetChannel = " + this.targetChannel + ", sourceChannel = " + this.sourceChannel);
        if (Intrinsics.areEqual(this.targetChannel, this.sourceChannel)) {
            Log.w(TAG, "目标信道与原信道一致，无需设置");
            switchLevelMode(LevelMode.Normal);
            byte b = (byte) 196;
            byte[] bArr = {b, b, b};
            StringBuilder sb = new StringBuilder();
            sb.append("切换模式：");
            String arrays = Arrays.toString(bArr);
            Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
            sb.append(arrays);
            Log.i(TAG, sb.toString());
            LoRaClient.INSTANCE.getINSTANCE().sendData(bArr);
            callbackSetupChannelStatus$default(this, SetupChannelStatus.Succeed, this.targetChannel, null, null, 12, null);
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new LoRaChannelManager2$setupChannel$1(this, null), 3, null);
            cancelSetupChannelTimer();
            return;
        }
        Channel channel = this.targetChannel;
        if (channel == null) {
            Log.w(TAG, "目标信道为空，设置信道失败");
            callbackSetupChannelStatus$default(this, SetupChannelStatus.Failed, null, "目标信道为空，设置信道失败", null, 10, null);
            cancelSetupChannelTimer();
            return;
        }
        if (channel != null) {
            Log.i(TAG, "开始设置信道，targetChannel = " + this.targetChannel + ", sourceChannel = " + this.sourceChannel);
            this.isSettingChannel = true;
            try {
                ByteBuffer allocate = ByteBuffer.allocate(11);
                allocate.put((byte) 192);
                allocate.put(this.moduleAddressBytes);
                allocate.put((byte) 29);
                allocate.put(calcChannelByte(channel.getSendFrequency()));
                allocate.put(calcChannelByte(channel.getReceiveFrequency()));
                allocate.put((byte) 48);
                byte[] bArr2 = new byte[allocate.capacity() - allocate.remaining()];
                allocate.flip();
                allocate.get(bArr2, 0, bArr2.length);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("切换信道：");
                String arrays2 = Arrays.toString(bArr2);
                Intrinsics.checkExpressionValueIsNotNull(arrays2, "java.util.Arrays.toString(this)");
                sb2.append(arrays2);
                Log.i(TAG, sb2.toString());
                LoRaClient.INSTANCE.getINSTANCE().sendData(bArr2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static /* synthetic */ void callbackSetupChannelStatus$default(LoRaChannelManager2 loRaChannelManager2, SetupChannelStatus setupChannelStatus, Channel channel, String str, ResetFailedType resetFailedType, int i, Object obj) {
        if ((i & 2) != 0) {
            channel = (Channel) null;
        }
        if ((i & 4) != 0) {
            str = (String) null;
        }
        if ((i & 8) != 0) {
            resetFailedType = (ResetFailedType) null;
        }
        loRaChannelManager2.callbackSetupChannelStatus(setupChannelStatus, channel, str, resetFailedType);
    }

    private final void callbackSetupChannelStatus(SetupChannelStatus status, Channel channel, String errMsg, ResetFailedType resetFailedType) {
        Log.d(TAG, "callbackSetupChannelStatus() status = " + status + ", channel = " + channel + ", errMsg = " + errMsg + ", resetFailedType = " + resetFailedType);
        try {
            this.setupChannelStatus = status;
            int i = WhenMappings.$EnumSwitchMapping$0[status.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    this.receiverBuffer.clear();
                    this.readBytesCount = 0;
                    OnSetupChannelListener onSetupChannelListener = this.onSetupChannelListener;
                    if (onSetupChannelListener != null) {
                        onSetupChannelListener.onSetting();
                        return;
                    }
                    return;
                }
                if (i == 3) {
                    this.isSettingChannel = false;
                    if (CollectionsKt.contains(this.channelList, channel)) {
                        channel = this.channelList.get(CollectionsKt.indexOf((List<? extends Channel>) this.channelList, channel));
                    }
                    this.currentChannel = channel;
                    OnSetupChannelListener onSetupChannelListener2 = this.onSetupChannelListener;
                    if (onSetupChannelListener2 != null) {
                        Channel channel2 = this.currentChannel;
                        if (channel2 == null) {
                            Intrinsics.throwNpe();
                        }
                        onSetupChannelListener2.onSucceed(channel2);
                        return;
                    }
                    return;
                }
                if (i != 4) {
                    return;
                }
                this.isSettingChannel = false;
                this.receiveType = ReceiveType.Business;
                Log.i(TAG, "receiveType = " + this.receiveType);
                switchLevelMode(LevelMode.Normal);
                cancelSetupChannelTimer();
                OnSetupChannelListener onSetupChannelListener3 = this.onSetupChannelListener;
                if (onSetupChannelListener3 != null) {
                    onSetupChannelListener3.onFailed(errMsg, resetFailedType);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final byte calcChannelByte(float frequency) {
        int intValue = new BigDecimal(String.valueOf(frequency)).subtract(new BigDecimal(String.valueOf(464.0f))).divide(new BigDecimal(String.valueOf(0.2f))).intValue();
        String hexString = Integer.toHexString(intValue);
        Intrinsics.checkExpressionValueIsNotNull(hexString, "Integer.toHexString(result)");
        Locale locale = Locale.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
        if (hexString == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String upperCase = hexString.toUpperCase(locale);
        Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase(locale)");
        byte parseInt = (byte) Integer.parseInt(upperCase, 16);
        Log.i(TAG, "result = " + intValue + ", hexString = " + upperCase + ", byte = " + ((int) parseInt));
        return parseInt;
    }

    private final float calcChannelFrequency(byte r4) {
        BigDecimal minFrequency = LoRaChannelConfig.INSTANCE.getMinFrequency();
        BigDecimal multiply = new BigDecimal(LoRaChannelConfig.INSTANCE.getStepFrequency().toString()).multiply(new BigDecimal(String.valueOf(r4 & 255)));
        Intrinsics.checkExpressionValueIsNotNull(multiply, "this.multiply(other)");
        return minFrequency.add(multiply).floatValue();
    }

    private final void startSetupChannelTimer() {
        cancelSetupChannelTimer();
        this.setupChannelTimer = new SetupChannelTimer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelSetupChannelTimer() {
        SetupChannelTimer setupChannelTimer = this.setupChannelTimer;
        if (setupChannelTimer != null) {
            setupChannelTimer.cancel();
        }
        this.setupChannelTimer = (SetupChannelTimer) null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void switchLevelMode(LevelMode mode) {
        int i = WhenMappings.$EnumSwitchMapping$1[mode.ordinal()];
        if (i == 1) {
            long currentTimeMillis = System.currentTimeMillis();
            Log.i(TAG, "setup level to low : m1[" + setupLevel(131, Level.Low) + "], m0[" + setupLevel(134, Level.Low) + "], execTime = " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
            return;
        }
        if (i != 2) {
            return;
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        Log.i(TAG, "setup level to high : m1[" + setupLevel(131, Level.High) + "], m0[" + setupLevel(134, Level.High) + "], execTime = " + (System.currentTimeMillis() - currentTimeMillis2) + "ms");
    }

    private final boolean setupLevel(int gpioNum, Level level) {
        FileWriter fileWriter = (FileWriter) null;
        boolean z = true;
        try {
            try {
                String str = "chmod 777 /sys/class/gpio/gpio" + gpioNum + "/value";
                Log.i(TAG, "setupLevel() valueCmd = " + str);
                Tools.execCommand(str, true);
                FileWriter fileWriter2 = new FileWriter(new File("/sys/class/gpio/gpio" + gpioNum + "/value"));
                try {
                    fileWriter2.write(level.getValue());
                    fileWriter2.flush();
                    fileWriter2.close();
                } catch (Exception e) {
                    e = e;
                    fileWriter = fileWriter2;
                    e.printStackTrace();
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                    z = false;
                    return z;
                } catch (Throwable th) {
                    th = th;
                    fileWriter = fileWriter2;
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
            return z;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0158  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void exportAndSetupDirectionOut(int gpioNum) {
        FileWriter fileWriter;
        String str;
        FileWriter fileWriter2 = (FileWriter) null;
        try {
            str = "/sys/class/gpio/gpio" + gpioNum;
            Log.d(TAG, "exportAndSetupDirectionOut() filePath = " + str);
        } catch (Exception e) {
            e = e;
            fileWriter = fileWriter2;
        } catch (Throwable th) {
            th = th;
            fileWriter = fileWriter2;
        }
        if (new File(str).exists()) {
            return;
        }
        String str2 = "chmod 777 /sys/class/gpio/export";
        Log.d(TAG, "exportAndSetupDirectionOut() exportCmd = " + str2);
        Pair<Integer, String> execCommand = Tools.execCommand(str2, true);
        Log.d(TAG, "exportAndSetupDirectionOut() exportResult first = " + ((Integer) execCommand.first) + "\tsecond = " + ((String) execCommand.second));
        StringBuilder sb = new StringBuilder();
        sb.append("/sys/class/gpio/");
        sb.append("export");
        FileWriter fileWriter3 = new FileWriter(new File(sb.toString()));
        try {
            fileWriter3.write(String.valueOf(gpioNum));
            fileWriter3.flush();
            String str3 = "chmod 777 /sys/class/gpio/gpio" + gpioNum + "/direction";
            Log.d(TAG, "exportAndSetupDirectionOut() directionCmd = " + str3);
            Pair<Integer, String> execCommand2 = Tools.execCommand(str3, true);
            Log.d(TAG, "exportAndSetupDirectionOut() directionResult first = " + ((Integer) execCommand2.first) + "\tsecond = " + ((String) execCommand2.second));
            fileWriter = new FileWriter(new File("/sys/class/gpio/gpio" + gpioNum + "/direction"));
        } catch (Exception e2) {
            e = e2;
            fileWriter = fileWriter2;
        } catch (Throwable th2) {
            th = th2;
            fileWriter = fileWriter2;
        }
        try {
            fileWriter.write("out");
            fileWriter.flush();
            fileWriter3.close();
        } catch (Exception e3) {
            e = e3;
            fileWriter2 = fileWriter3;
            try {
                e.printStackTrace();
                if (fileWriter2 != null) {
                    fileWriter2.close();
                }
                if (fileWriter == null) {
                    return;
                }
                fileWriter.close();
            } catch (Throwable th3) {
                th = th3;
                if (fileWriter2 != null) {
                    fileWriter2.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            fileWriter2 = fileWriter3;
            if (fileWriter2 != null) {
            }
            if (fileWriter != null) {
            }
            throw th;
        }
        fileWriter.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetLoRa() {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new LoRaChannelManager2$resetLoRa$1(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LoRaChannelManager2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0005B\u0005¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2$SetupChannelTimer;", "Ljava/util/Timer;", "()V", "task", "Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2$SetupChannelTimer$SetupChannelTimerTask;", "SetupChannelTimerTask", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class SetupChannelTimer extends Timer {
        private final SetupChannelTimerTask task = new SetupChannelTimerTask();

        public SetupChannelTimer() {
            schedule(this.task, 0L, 5000L);
        }

        /* compiled from: LoRaChannelManager2.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2$SetupChannelTimer$SetupChannelTimerTask;", "Ljava/util/TimerTask;", "()V", "retryCount", "", "run", "", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes2.dex */
        private static final class SetupChannelTimerTask extends TimerTask {
            private int retryCount;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                this.retryCount++;
                int i = this.retryCount;
                if (i > 5) {
                    LoRaChannelManager2.INSTANCE.getINSTANCE().cancelSetupChannelTimer();
                    LoRaChannelManager2.callbackSetupChannelStatus$default(LoRaChannelManager2.INSTANCE.getINSTANCE(), SetupChannelStatus.Failed, null, "LoRa模块异常", null, 10, null);
                } else if (i == 1) {
                    LoRaChannelManager2.INSTANCE.getINSTANCE().switchLevelMode(LevelMode.DeepSleep);
                } else {
                    LoRaChannelManager2.INSTANCE.getINSTANCE().switchLevelMode(LevelMode.Normal);
                    LoRaChannelManager2.INSTANCE.getINSTANCE().resetLoRa();
                }
            }
        }
    }

    public final ArrayList<Channel> getChannelList() {
        this.channelList.clear();
        BigDecimal startFrequency = LoRaChannelConfig.INSTANCE.getStartFrequency();
        do {
            BigDecimal add = startFrequency.add(LoRaChannelConfig.INSTANCE.getEachGroupFrequency());
            BigDecimal multiply = LoRaChannelConfig.INSTANCE.getStepFrequency().multiply(new BigDecimal(String.valueOf(2.0f)));
            Intrinsics.checkExpressionValueIsNotNull(multiply, "this.multiply(other)");
            BigDecimal subtract = add.subtract(multiply);
            Channel channel = new Channel();
            channel.setSendFrequency(startFrequency.floatValue());
            channel.setReceiveFrequency(subtract.floatValue());
            this.channelList.add(channel);
            startFrequency = startFrequency.add(LoRaChannelConfig.INSTANCE.getEachGroupFrequency());
            Intrinsics.checkExpressionValueIsNotNull(startFrequency, "frequency.add(LoRaChanne…onfig.eachGroupFrequency)");
            if (startFrequency.compareTo(LoRaChannelConfig.INSTANCE.getDefaultFrequency()) >= 0) {
                break;
            }
        } while (startFrequency.add(LoRaChannelConfig.INSTANCE.getEachGroupFrequency()).compareTo(LoRaChannelConfig.INSTANCE.getDefaultFrequency()) < 0);
        BigDecimal add2 = LoRaChannelConfig.INSTANCE.getDefaultFrequency().add(new BigDecimal(String.valueOf(1.0f)));
        Intrinsics.checkExpressionValueIsNotNull(add2, "LoRaChannelConfig.defaul…Decimal(1.0f.toString()))");
        do {
            BigDecimal add3 = add2.add(LoRaChannelConfig.INSTANCE.getEachGroupFrequency());
            BigDecimal multiply2 = LoRaChannelConfig.INSTANCE.getStepFrequency().multiply(new BigDecimal(String.valueOf(2.0f)));
            Intrinsics.checkExpressionValueIsNotNull(multiply2, "this.multiply(other)");
            BigDecimal subtract2 = add3.subtract(multiply2);
            Channel channel2 = new Channel();
            channel2.setSendFrequency(add2.floatValue());
            channel2.setReceiveFrequency(subtract2.floatValue());
            this.channelList.add(channel2);
            add2 = add2.add(LoRaChannelConfig.INSTANCE.getEachGroupFrequency());
            Intrinsics.checkExpressionValueIsNotNull(add2, "frequency.add(LoRaChanne…onfig.eachGroupFrequency)");
        } while (add2.floatValue() < LoRaChannelConfig.INSTANCE.getEndFrequency().add(LoRaChannelConfig.INSTANCE.getEachGroupFrequency()).floatValue());
        CollectionsKt.sort(this.channelList);
        int size = this.channelList.size();
        int i = 1;
        if (1 <= size) {
            while (true) {
                Channel channel3 = this.channelList.get(i - 1);
                Intrinsics.checkExpressionValueIsNotNull(channel3, "channelList[i - 1]");
                channel3.setChannelId(i);
                if (i == size) {
                    break;
                }
                i++;
            }
        }
        return this.channelList;
    }

    /* compiled from: LoRaChannelManager2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2$OnSetupChannelListener;", "", "onFailed", "", "errMsg", "", "resetFailedType", "Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2$ResetFailedType;", "onSetting", "onSucceed", "channel", "Lcom/pudutech/bumblebee/robot/aidl/serialize/Channel;", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public interface OnSetupChannelListener {
        void onFailed(String errMsg, ResetFailedType resetFailedType);

        void onSetting();

        void onSucceed(Channel channel);

        /* compiled from: LoRaChannelManager2.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes2.dex */
        public static final class DefaultImpls {
            public static /* synthetic */ void onFailed$default(OnSetupChannelListener onSetupChannelListener, String str, ResetFailedType resetFailedType, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onFailed");
                }
                if ((i & 2) != 0) {
                    resetFailedType = ResetFailedType.Other;
                }
                onSetupChannelListener.onFailed(str, resetFailedType);
            }
        }
    }
}
