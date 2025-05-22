package com.pudutech.bumblebee.business.ims.lora;

import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.ims.IMSKit;
import com.pudutech.bumblebee.business.ims.config.LoRaChannelConfig;
import com.pudutech.bumblebee.business.ims.config.MsgType;
import com.pudutech.bumblebee.business.ims.lora.LoRaChannelManager2;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.protobuf.MessageProtobuf;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: LoRaChannelManager2.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 J2\u00020\u0001:\tJKLMNOPQRB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0002J\u0010\u00100\u001a\u00020/2\u0006\u00101\u001a\u00020-H\u0002J4\u00102\u001a\u0002032\u0006\u00104\u001a\u00020'2\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u00107\u001a\u0004\u0018\u000108H\u0002J\b\u00109\u001a\u000203H\u0002J\b\u0010:\u001a\u000203H\u0002J\u0016\u0010;\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006J\u000e\u0010<\u001a\u0002032\u0006\u0010=\u001a\u00020\u0018J\b\u0010>\u001a\u000203H\u0002J\b\u0010?\u001a\u000203H\u0002J\u001a\u0010?\u001a\u0002032\b\u00105\u001a\u0004\u0018\u00010\u00052\b\u0010@\u001a\u0004\u0018\u00010\u001aJ\u0018\u0010A\u001a\u00020\u00142\u0006\u0010B\u001a\u00020\u001c2\u0006\u0010C\u001a\u00020DH\u0002J\u001a\u0010E\u001a\u0002032\b\u00105\u001a\u0004\u0018\u00010\u00052\b\u0010@\u001a\u0004\u0018\u00010\u001aJ\b\u0010F\u001a\u000203H\u0002J\u0010\u0010G\u001a\u0002032\u0006\u0010H\u001a\u00020IH\u0002R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\u000bR\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0016\u0010#\u001a\n %*\u0004\u0018\u00010$0$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006S"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2;", "", "()V", "channelList", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/business/ims/lora/Channel;", "Lkotlin/collections/ArrayList;", "currentChannel", "getCurrentChannel", "()Lcom/pudutech/bumblebee/business/ims/lora/Channel;", "setCurrentChannel", "(Lcom/pudutech/bumblebee/business/ims/lora/Channel;)V", "currentChannelType", "Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$CurrentChannelType;", "defaultChannel", "getDefaultChannel", "setDefaultChannel", "headString", "", "isDecoding", "", "isFirstStartup", "isSettingChannel", "moduleAddressBytes", "", "onSetupChannelListener", "Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$OnSetupChannelListener;", "readedBytesCount", "", "receiveType", "Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$ReceiveType;", "getReceiveType", "()Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$ReceiveType;", "setReceiveType", "(Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$ReceiveType;)V", "receiverBuffer", "Ljava/nio/ByteBuffer;", "kotlin.jvm.PlatformType", "setupChannelStatus", "Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$SetupChannelStatus;", "setupChannelTimer", "Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$SetupChannelTimer;", "sourceChannel", "targetChannel", "calcChannelByte", "", "frequency", "", "calcChannelFrequency", "byte", "callbackSetupChannelStatus", "", "status", "channel", "errMsg", "resetFailedType", "Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$ResetFailedType;", "cancelSetupChannelTimer", "decodeMsg", "getChannelList", "onReceivedData", "data", "resetLoRa", "setupChannel", "listener", "setupLevel", "gpioNum", "level", "Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$Level;", "setupLocalChannel", "startSetupChannelTimer", "switchLevelMode", "mode", "Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$LevelMode;", "Companion", "CurrentChannelType", "Level", "LevelMode", "OnSetupChannelListener", "ReceiveType", "ResetFailedType", "SetupChannelStatus", "SetupChannelTimer", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoRaChannelManager2 {
    private static final int HEAD_LENGTH = 3;
    private static final int M0_GPIO_NUM = 134;
    private static final int M1_GPIO_NUM = 131;
    private static final String TAG = "LoRaChannelManager2";
    private final ArrayList<Channel> channelList;
    private volatile Channel currentChannel;
    private CurrentChannelType currentChannelType;
    private Channel defaultChannel;
    private String headString;
    private boolean isDecoding;
    private boolean isFirstStartup;
    private boolean isSettingChannel;
    private byte[] moduleAddressBytes;
    private OnSetupChannelListener onSetupChannelListener;
    private int readedBytesCount;
    private ReceiveType receiveType;
    private ByteBuffer receiverBuffer;
    private SetupChannelStatus setupChannelStatus;
    private SetupChannelTimer setupChannelTimer;
    private Channel sourceChannel;
    private Channel targetChannel;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<LoRaChannelManager2>() { // from class: com.pudutech.bumblebee.business.ims.lora.LoRaChannelManager2$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LoRaChannelManager2 invoke() {
            return new LoRaChannelManager2(null);
        }
    });

    /* compiled from: LoRaChannelManager2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$CurrentChannelType;", "", "(Ljava/lang/String;I)V", "CentralControl", "Watch", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum CurrentChannelType {
        CentralControl,
        Watch
    }

    /* compiled from: LoRaChannelManager2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$LevelMode;", "", "(Ljava/lang/String;I)V", "DeepSleep", "Normal", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum LevelMode {
        DeepSleep,
        Normal
    }

    /* compiled from: LoRaChannelManager2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$ReceiveType;", "", "(Ljava/lang/String;I)V", "Business", "Channel", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum ReceiveType {
        Business,
        Channel
    }

    /* compiled from: LoRaChannelManager2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$ResetFailedType;", "", "(Ljava/lang/String;I)V", "CentralControlTimeout", "Other", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum ResetFailedType {
        CentralControlTimeout,
        Other
    }

    /* compiled from: LoRaChannelManager2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$SetupChannelStatus;", "", "(Ljava/lang/String;I)V", "Normal", "Setting", "Succeed", "Failed", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum SetupChannelStatus {
        Normal,
        Setting,
        Succeed,
        Failed
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
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
        this.receiveType = ReceiveType.Business;
        this.setupChannelStatus = SetupChannelStatus.Normal;
        this.defaultChannel = new Channel(LoRaChannelConfig.INSTANCE.getDefaultFrequency().floatValue(), LoRaChannelConfig.INSTANCE.getDefaultFrequency().floatValue());
        this.currentChannelType = CurrentChannelType.CentralControl;
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

    public final Channel getDefaultChannel() {
        return this.defaultChannel;
    }

    public final void setDefaultChannel(Channel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "<set-?>");
        this.defaultChannel = channel;
    }

    public final Channel getCurrentChannel() {
        return this.currentChannel;
    }

    public final void setCurrentChannel(Channel channel) {
        this.currentChannel = channel;
    }

    /* compiled from: LoRaChannelManager2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$Level;", "", ES6Iterator.VALUE_PROPERTY, "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "High", "Low", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
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
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$Companion;", "", "()V", "HEAD_LENGTH", "", "INSTANCE", "Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2;", "getINSTANCE", "()Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2;", "INSTANCE$delegate", "Lkotlin/Lazy;", "M0_GPIO_NUM", "M1_GPIO_NUM", "TAG", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
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

    public final void onReceivedData(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        StringBuilder sb = new StringBuilder();
        sb.append("onReceivedData() data = ");
        String arrays = Arrays.toString(data);
        Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
        sb.append(arrays);
        Pdlog.m3275i(TAG, sb.toString());
        try {
            this.receiverBuffer.put(data);
            this.readedBytesCount += data.length;
            if (this.readedBytesCount < 3) {
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
            this.readedBytesCount = 0;
            this.isDecoding = false;
            this.headString = (String) null;
        }
    }

    private final void decodeMsg() {
        String str;
        this.isDecoding = true;
        ByteBuffer byteBuffer = this.receiverBuffer;
        if (byteBuffer != null) {
            Pdlog.m3275i(TAG, "headString = " + this.headString);
            String str2 = this.headString;
            if (str2 != null) {
                int hashCode = str2.hashCode();
                if (hashCode == 37278) {
                    if (str2.equals("#rm")) {
                        int i = this.readedBytesCount;
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
                            Pdlog.m3275i(TAG, sb.toString());
                            int i2 = bArr[3] & 255;
                            if (i2 == 0) {
                                byte b = (byte) 193;
                                byte[] bArr2 = {b, b, b};
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("获取模组参数：");
                                String arrays2 = Arrays.toString(bArr2);
                                Intrinsics.checkExpressionValueIsNotNull(arrays2, "java.util.Arrays.toString(this)");
                                sb2.append(arrays2);
                                Pdlog.m3275i(TAG, sb2.toString());
                                Peripherals.INSTANCE.getIms().sendMsg(bArr2);
                            }
                            Pdlog.m3275i(TAG, "模组当前模式： " + i2);
                            if (i2 == 3) {
                                Pdlog.m3275i(TAG, "设置信道成功");
                                callbackSetupChannelStatus$default(this, SetupChannelStatus.Succeed, this.targetChannel, null, null, 12, null);
                                cancelSetupChannelTimer();
                                this.targetChannel = (Channel) null;
                            }
                            byteBuffer.clear();
                            this.readedBytesCount = 0;
                            this.isDecoding = false;
                            this.headString = (String) null;
                            return;
                        }
                        return;
                    }
                } else if (hashCode == 37284 && str2.equals("#rs")) {
                    int i3 = this.readedBytesCount;
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
                            Pdlog.m3275i(TAG, sb3.toString());
                        } else {
                            Pdlog.m3275i(TAG, "非首次启动，切换到DeepSleep模式设置信道");
                            switchLevelMode(LevelMode.DeepSleep);
                        }
                        byteBuffer.clear();
                        this.readedBytesCount = 0;
                        this.isDecoding = false;
                        this.headString = (String) null;
                        return;
                    }
                    return;
                }
            }
            int i4 = this.readedBytesCount;
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
                Pdlog.m3275i(TAG, sb4.toString());
                this.moduleAddressBytes = ArraysKt.copyOfRange(bArr4, 1, 7);
                byte[] copyOfRange = ArraysKt.copyOfRange(bArr4, 8, 10);
                this.sourceChannel = new Channel(calcChannelFrequency(copyOfRange[0]), calcChannelFrequency(copyOfRange[1]));
                Object[] objArr = new Object[1];
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
                objArr[0] = sb5.toString();
                Pdlog.m3275i(TAG, objArr);
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
                    Pdlog.m3275i(TAG, sb6.toString());
                    if (Intrinsics.areEqual(this.targetChannel, this.sourceChannel)) {
                        switchLevelMode(LevelMode.Normal);
                        byte b2 = (byte) 196;
                        byte[] bArr6 = {b2, b2, b2};
                        StringBuilder sb7 = new StringBuilder();
                        sb7.append("切换模式：");
                        String arrays7 = Arrays.toString(bArr6);
                        Intrinsics.checkExpressionValueIsNotNull(arrays7, "java.util.Arrays.toString(this)");
                        sb7.append(arrays7);
                        Pdlog.m3275i(TAG, sb7.toString());
                        Peripherals.INSTANCE.getIms().sendMsg(bArr6);
                    } else {
                        Pdlog.m3277w(TAG, "设置信道失败");
                    }
                }
                byteBuffer.clear();
                this.readedBytesCount = 0;
                this.isDecoding = false;
                this.headString = (String) null;
            }
        }
    }

    public final void setupChannel(Channel channel, OnSetupChannelListener listener) {
        MessageProtobuf.Msg.Builder channel2;
        if (channel == null) {
            Pdlog.m3277w(TAG, "setupChannel() failure, reason : channel is null.");
            callbackSetupChannelStatus$default(this, SetupChannelStatus.Failed, null, "channel is null", null, 10, null);
            return;
        }
        this.targetChannel = channel;
        this.onSetupChannelListener = listener;
        callbackSetupChannelStatus$default(this, SetupChannelStatus.Setting, this.targetChannel, null, null, 12, null);
        MessageProtobuf.Msg.Builder generateCommonMsgBuilder = IMSKit.INSTANCE.getInstance().generateCommonMsgBuilder(MsgType.SetChannel);
        IMSKit.sendMsg$default(IMSKit.INSTANCE.getInstance(), (generateCommonMsgBuilder == null || (channel2 = generateCommonMsgBuilder.setChannel(MessageProtobuf.Channel.newBuilder().setChannelId(channel.getChannelId()).build())) == null) ? null : channel2.build(), new Function1<MessageProtobuf.Msg, Unit>() { // from class: com.pudutech.bumblebee.business.ims.lora.LoRaChannelManager2$setupChannel$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MessageProtobuf.Msg msg) {
                invoke2(msg);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MessageProtobuf.Msg it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                LoRaChannelManager2.this.setReceiveType(LoRaChannelManager2.ReceiveType.Channel);
                LoRaChannelManager2.this.startSetupChannelTimer();
            }
        }, new Function2<MessageProtobuf.Msg, String, Unit>() { // from class: com.pudutech.bumblebee.business.ims.lora.LoRaChannelManager2$setupChannel$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(MessageProtobuf.Msg msg, String str) {
                invoke2(msg, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MessageProtobuf.Msg msg, String str) {
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                LoRaChannelManager2.callbackSetupChannelStatus$default(LoRaChannelManager2.this, LoRaChannelManager2.SetupChannelStatus.Failed, null, "中控无响应", LoRaChannelManager2.ResetFailedType.CentralControlTimeout, 2, null);
            }
        }, false, false, 24, null);
    }

    public final void setupLocalChannel(Channel channel, OnSetupChannelListener listener) {
        if (channel == null) {
            Pdlog.m3277w(TAG, "setupLocalChannel() failure, reason : channel is null.");
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
        Pdlog.m3275i(TAG, "private setupChannel() targetChannel = " + this.targetChannel + ", sourceChannel = " + this.sourceChannel);
        if (Intrinsics.areEqual(this.targetChannel, this.sourceChannel)) {
            Pdlog.m3277w(TAG, "目标信道与原信道一致，无需设置");
            switchLevelMode(LevelMode.Normal);
            byte b = (byte) 196;
            byte[] bArr = {b, b, b};
            StringBuilder sb = new StringBuilder();
            sb.append("切换模式：");
            String arrays = Arrays.toString(bArr);
            Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
            sb.append(arrays);
            Pdlog.m3275i(TAG, sb.toString());
            Peripherals.INSTANCE.getIms().sendMsg(bArr);
            cancelSetupChannelTimer();
            return;
        }
        Channel channel = this.targetChannel;
        if (channel == null) {
            Pdlog.m3277w(TAG, "目标信道为空，设置信道失败");
            callbackSetupChannelStatus$default(this, SetupChannelStatus.Failed, null, "目标信道为空，设置信道失败", null, 10, null);
            cancelSetupChannelTimer();
            return;
        }
        if (channel != null) {
            Pdlog.m3275i(TAG, "开始设置信道，targetChannel = " + this.targetChannel + ", sourceChannel = " + this.sourceChannel);
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
                Pdlog.m3275i(TAG, sb2.toString());
                Peripherals.INSTANCE.getIms().sendMsg(bArr2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void callbackSetupChannelStatus$default(LoRaChannelManager2 loRaChannelManager2, SetupChannelStatus setupChannelStatus, Channel channel, String str, ResetFailedType resetFailedType, int i, Object obj) {
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
        Pdlog.m3273d(TAG, "callbackSetupChannelStatus() status = " + status + ", channel = " + channel + ", errMsg = " + errMsg + ", resetFailedType = " + resetFailedType);
        try {
            this.setupChannelStatus = status;
            int i = WhenMappings.$EnumSwitchMapping$0[status.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    ByteBuffer byteBuffer = this.receiverBuffer;
                    if (byteBuffer != null) {
                        byteBuffer.clear();
                    }
                    this.readedBytesCount = 0;
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
                    this.currentChannelType = CurrentChannelType.CentralControl;
                    OnSetupChannelListener onSetupChannelListener2 = this.onSetupChannelListener;
                    if (onSetupChannelListener2 != null) {
                        Channel channel2 = this.currentChannel;
                        if (channel2 == null) {
                            Intrinsics.throwNpe();
                        }
                        onSetupChannelListener2.onSucceed(channel2);
                    }
                    this.receiveType = ReceiveType.Business;
                    return;
                }
                if (i != 4) {
                    return;
                }
                this.isSettingChannel = false;
                Pdlog.m3275i(TAG, "receiveType = " + this.receiveType);
                switchLevelMode(LevelMode.Normal);
                cancelSetupChannelTimer();
                OnSetupChannelListener onSetupChannelListener3 = this.onSetupChannelListener;
                if (onSetupChannelListener3 != null) {
                    onSetupChannelListener3.onFailed(errMsg, resetFailedType);
                }
                this.receiveType = ReceiveType.Business;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final byte calcChannelByte(float frequency) {
        int intValue = new BigDecimal(String.valueOf(frequency)).subtract(new BigDecimal(LoRaChannelConfig.INSTANCE.getMinFrequency().toString())).divide(new BigDecimal(String.valueOf(0.2f))).intValue();
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
        Pdlog.m3275i(TAG, "result = " + intValue + ", hexString = " + upperCase + ", byte = " + ((int) parseInt));
        return parseInt;
    }

    private final float calcChannelFrequency(byte r4) {
        BigDecimal minFrequency = LoRaChannelConfig.INSTANCE.getMinFrequency();
        BigDecimal multiply = new BigDecimal(LoRaChannelConfig.INSTANCE.getStepFrequency().toString()).multiply(new BigDecimal(String.valueOf(r4 & 255)));
        Intrinsics.checkExpressionValueIsNotNull(multiply, "this.multiply(other)");
        return minFrequency.add(multiply).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startSetupChannelTimer() {
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
            Pdlog.m3275i(TAG, "setup level to low : m1[" + setupLevel(131, Level.Low) + "], m0[" + setupLevel(134, Level.Low) + "], execTime = " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
            return;
        }
        if (i != 2) {
            return;
        }
        Pdlog.m3273d(TAG, "LevelMode.Normal stack = " + Log.getStackTraceString(new Throwable()));
        long currentTimeMillis2 = System.currentTimeMillis();
        Pdlog.m3275i(TAG, "setup level to high : m1[" + setupLevel(131, Level.High) + "], m0[" + setupLevel(134, Level.High) + "], execTime = " + (System.currentTimeMillis() - currentTimeMillis2) + "ms");
    }

    private final boolean setupLevel(int gpioNum, Level level) {
        FileWriter fileWriter = (FileWriter) null;
        try {
            try {
                FileWriter fileWriter2 = new FileWriter(new File("/sys/class/gpio/gpio" + gpioNum + "/value"));
                try {
                    fileWriter2.write(level.getValue());
                    fileWriter2.flush();
                    fileWriter2.close();
                    return true;
                } catch (Exception e) {
                    e = e;
                    fileWriter = fileWriter2;
                    e.printStackTrace();
                    Pdlog.m3274e(TAG, "setupLevel() failed, gpioNum = " + gpioNum + ", level = " + level + ", reason:" + e);
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileWriter = fileWriter2;
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetLoRa() {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new LoRaChannelManager2$resetLoRa$1(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LoRaChannelManager2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0005B\u0005¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$SetupChannelTimer;", "Ljava/util/Timer;", "()V", "task", "Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$SetupChannelTimer$SetupChannelTimerTask;", "SetupChannelTimerTask", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class SetupChannelTimer extends Timer {
        private final SetupChannelTimerTask task = new SetupChannelTimerTask();

        public SetupChannelTimer() {
            schedule(this.task, 0L, 5000L);
        }

        /* compiled from: LoRaChannelManager2.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$SetupChannelTimer$SetupChannelTimerTask;", "Ljava/util/TimerTask;", "()V", "retryCount", "", "run", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        private static final class SetupChannelTimerTask extends TimerTask {
            private int retryCount;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                this.retryCount++;
                if (this.retryCount <= 5) {
                    LoRaChannelManager2.INSTANCE.getINSTANCE().switchLevelMode(LevelMode.DeepSleep);
                    if (this.retryCount > 1) {
                        LoRaChannelManager2.INSTANCE.getINSTANCE().resetLoRa();
                        return;
                    }
                    return;
                }
                LoRaChannelManager2.INSTANCE.getINSTANCE().cancelSetupChannelTimer();
                LoRaChannelManager2.callbackSetupChannelStatus$default(LoRaChannelManager2.INSTANCE.getINSTANCE(), SetupChannelStatus.Failed, null, "LoRa模块异常", null, 10, null);
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
            this.channelList.add(new Channel(startFrequency.floatValue(), add.subtract(multiply).floatValue()));
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
            this.channelList.add(new Channel(add2.floatValue(), add3.subtract(multiply2).floatValue()));
            System.out.println((Object) ("frequency = " + add2));
            add2 = add2.add(LoRaChannelConfig.INSTANCE.getEachGroupFrequency());
            Intrinsics.checkExpressionValueIsNotNull(add2, "frequency.add(LoRaChanne…onfig.eachGroupFrequency)");
        } while (add2.floatValue() < LoRaChannelConfig.INSTANCE.getEndFrequency().add(LoRaChannelConfig.INSTANCE.getEachGroupFrequency()).floatValue());
        CollectionsKt.sort(this.channelList);
        int size = this.channelList.size();
        int i = 1;
        if (1 <= size) {
            int i2 = 1;
            while (true) {
                Channel channel = this.channelList.get(i2 - 1);
                Intrinsics.checkExpressionValueIsNotNull(channel, "channelList[i - 1]");
                channel.setChannelId(i2);
                if (i2 == size) {
                    break;
                }
                i2++;
            }
        }
        Iterator<Channel> it = this.channelList.iterator();
        while (it.hasNext()) {
            System.out.println((Object) ("channel = " + it.next() + '\n'));
        }
        System.out.println((Object) ("size = " + this.channelList.size()));
        ArrayList<Channel> arrayList = new ArrayList<>();
        arrayList.addAll(this.channelList);
        int size2 = arrayList.size();
        if (1 <= size2) {
            while (true) {
                Channel channel2 = arrayList.get(i - 1);
                Intrinsics.checkExpressionValueIsNotNull(channel2, "availableChannelList[i - 1]");
                channel2.setChannelId(i);
                if (i == size2) {
                    break;
                }
                i++;
            }
        }
        Iterator<Channel> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            System.out.println((Object) ("available channel = " + it2.next() + '\n'));
        }
        System.out.println((Object) ("available channel size = " + arrayList.size()));
        return arrayList;
    }

    /* compiled from: LoRaChannelManager2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$OnSetupChannelListener;", "", "onFailed", "", "errMsg", "", "resetFailedType", "Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$ResetFailedType;", "onSetting", "onSucceed", "channel", "Lcom/pudutech/bumblebee/business/ims/lora/Channel;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface OnSetupChannelListener {
        void onFailed(String errMsg, ResetFailedType resetFailedType);

        void onSetting();

        void onSucceed(Channel channel);

        /* compiled from: LoRaChannelManager2.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
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
