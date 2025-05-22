package com.pudu.library.loracall;

import android.content.Context;
import com.pudu.library.loracall.LoRaHelper;
import com.pudu.library.loracall.bean.LoRaDeviceState;
import com.pudu.library.loracall.bean.LoraReceiveCall;
import com.pudu.library.loracall.bean.LoraTableParam;
import com.pudutech.bumblebee.presenter.robot_open_task.config.ActionConfig;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: LoRaHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001-B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u001e\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\bJ\u0006\u0010!\u001a\u00020\u0004J\u0010\u0010\"\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u001a\u0010#\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\u00042\b\b\u0002\u0010%\u001a\u00020\u0006H\u0002J\u000e\u0010&\u001a\u00020\u00112\u0006\u0010'\u001a\u00020(J\u000e\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u0004J\u0010\u0010+\u001a\u00020\u00112\u0006\u0010'\u001a\u00020,H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006."}, m3961d2 = {"Lcom/pudu/library/loracall/LoRaHelper;", "", "()V", "bindState", "", "devAdder", "", "mListener", "Lcom/pudu/library/loracall/LoRaHelper$LoRaHelperListener;", "mapTable", "Lcom/pudu/library/loracall/bean/LoraTableParam;", "getMapTable", "()Lcom/pudu/library/loracall/bean/LoraTableParam;", "setMapTable", "(Lcom/pudu/library/loracall/bean/LoraTableParam;)V", "onStateChangeListener", "Lkotlin/Function0;", "", "getOnStateChangeListener", "()Lkotlin/jvm/functions/Function0;", ActionConfig.ACTION_CANCEL_CALL, "receiveCall", "Lcom/pudu/library/loracall/bean/LoraReceiveCall;", "handleCall", "handleReceiveMsg", "msgType", "Lcom/pudu/library/loracall/ReceiveMsgType;", "init", "context", "Landroid/content/Context;", "config", "Lcom/pudu/library/loracall/LoRaConfig;", "listener", "isButtonCalling", "preCall", "sendCallAck", "isSuc", "dav", "setArriveState", "state", "", "setBindState", "bind", "switchState", "Lcom/pudu/library/loracall/bean/LoRaDeviceState;", "LoRaHelperListener", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoRaHelper {
    private static volatile boolean bindState;
    public static final LoRaHelper INSTANCE = new LoRaHelper();
    private static volatile byte[] devAdder = new byte[0];
    private static volatile LoraTableParam mapTable = new LoraTableParam(null, null, null, null, 15, null);
    private static LoRaHelperListener mListener = new LoRaHelperListener() { // from class: com.pudu.library.loracall.LoRaHelper$mListener$1
        @Override // com.pudu.library.loracall.LoRaHelper.LoRaHelperListener
        public void callTable(String call) {
            Intrinsics.checkParameterIsNotNull(call, "call");
        }

        @Override // com.pudu.library.loracall.LoRaHelper.LoRaHelperListener
        public void cancelCallTable(String tableName) {
            Intrinsics.checkParameterIsNotNull(tableName, "tableName");
        }

        @Override // com.pudu.library.loracall.LoRaHelper.LoRaHelperListener
        public LoRaDeviceState getDeviceState() {
            return LoRaDeviceState.IDLE;
        }
    };
    private static final Function0<Unit> onStateChangeListener = new Function0<Unit>() { // from class: com.pudu.library.loracall.LoRaHelper$onStateChangeListener$1
        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            boolean z;
            LoRaHelper.LoRaHelperListener loRaHelperListener;
            final LoRaDeviceState deviceState;
            LoRaHelper loRaHelper = LoRaHelper.INSTANCE;
            z = LoRaHelper.bindState;
            if (z) {
                deviceState = LoRaDeviceState.BINDING;
            } else {
                LoRaHelper loRaHelper2 = LoRaHelper.INSTANCE;
                loRaHelperListener = LoRaHelper.mListener;
                deviceState = loRaHelperListener.getDeviceState();
            }
            KotlinUtilsKt.log$default(LoRaHelper.INSTANCE, null, new Function0<String>() { // from class: com.pudu.library.loracall.LoRaHelper$onStateChangeListener$1.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "状态监听--" + LoRaDeviceState.this;
                }
            }, 1, null);
            LoRaHelper.INSTANCE.switchState(deviceState);
        }
    };

    /* compiled from: LoRaHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, m3961d2 = {"Lcom/pudu/library/loracall/LoRaHelper$LoRaHelperListener;", "", "callTable", "", "tableName", "", "cancelCallTable", "getDeviceState", "Lcom/pudu/library/loracall/bean/LoRaDeviceState;", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface LoRaHelperListener {
        void callTable(String tableName);

        void cancelCallTable(String tableName);

        LoRaDeviceState getDeviceState();
    }

    private LoRaHelper() {
    }

    public final LoraTableParam getMapTable() {
        return mapTable;
    }

    public final void setMapTable(LoraTableParam loraTableParam) {
        Intrinsics.checkParameterIsNotNull(loraTableParam, "<set-?>");
        mapTable = loraTableParam;
    }

    public final void init(Context context, LoRaConfig config, LoRaHelperListener listener) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(config, "config");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.LoRaHelper$init$1
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "初始化";
            }
        }, 1, null);
        mListener = listener;
        BuildersKt__Builders_commonKt.launch$default(KotlinUtilsKt.getMyScope(), null, null, new LoRaHelper$init$2(context, config, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleReceiveMsg(final ReceiveMsgType msgType) {
        int type;
        if (msgType instanceof LoraMsg) {
            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.LoRaHelper$handleReceiveMsg$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "呼叫器透传消息----" + ((LoraMsg) ReceiveMsgType.this).toLoraReceiveCall();
                }
            }, 1, null);
            LoraReceiveCall loraReceiveCall = ((LoraMsg) msgType).toLoraReceiveCall();
            if (loraReceiveCall == null || (type = loraReceiveCall.getType()) == 1) {
                return;
            }
            if (type == 2) {
                handleCall(loraReceiveCall);
                return;
            } else if (type == 3) {
                cancelCall(loraReceiveCall);
                return;
            } else {
                if (type != 4) {
                    return;
                }
                preCall(loraReceiveCall);
                return;
            }
        }
        if (msgType instanceof LoraState) {
            return;
        }
        boolean z = msgType instanceof LoraNetState;
    }

    private final void preCall(LoraReceiveCall receiveCall) {
        LoRaClient.sendHaveAckMsg$default(LoRaClient.INSTANCE.getInstance(), new LoRaMsgCallRes2(receiveCall.getDevAddr(), 2, Random.INSTANCE.nextDouble()), null, 2, null);
    }

    private final void cancelCall(LoraReceiveCall receiveCall) {
        boolean z = true;
        if (!(receiveCall.getDevAddr().length == 0)) {
            final String hexString = KotlinUtilsKt.toHexString(ArraysKt.reversedArray(receiveCall.getDevAddr()));
            final String tableForDevAdder = LoRaClient.INSTANCE.getInstance().getTableForDevAdder(hexString);
            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.LoRaHelper$cancelCall$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "按键呼叫器呼叫取消地址： " + hexString + "  tableName:" + tableForDevAdder;
                }
            }, 1, null);
            String str = tableForDevAdder;
            if (str != null && !StringsKt.isBlank(str)) {
                z = false;
            }
            if (!z) {
                BuildersKt__Builders_commonKt.launch$default(KotlinUtilsKt.getMyScope(), Dispatchers.getMain(), null, new LoRaHelper$cancelCall$2(tableForDevAdder, null), 2, null);
            }
        }
        LoRaClient.sendHaveAckMsg$default(LoRaClient.INSTANCE.getInstance(), new ArriveCancelMsg(false, receiveCall.getDevAddr()), null, 2, null);
    }

    private final void handleCall(LoraReceiveCall receiveCall) {
        if (!(receiveCall.getDevAddr().length == 0)) {
            final String hexString = KotlinUtilsKt.toHexString(ArraysKt.reversedArray(receiveCall.getDevAddr()));
            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.LoRaHelper$handleCall$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    boolean z;
                    StringBuilder sb = new StringBuilder();
                    sb.append("按键呼叫器呼叫地址： ");
                    sb.append(hexString);
                    sb.append("  bindState:");
                    LoRaHelper loRaHelper = LoRaHelper.INSTANCE;
                    z = LoRaHelper.bindState;
                    sb.append(z);
                    return sb.toString();
                }
            }, 1, null);
            if (mListener.getDeviceState() == LoRaDeviceState.IDLE && !bindState) {
                devAdder = receiveCall.getDevAddr();
                final String tableForDevAdder = LoRaClient.INSTANCE.getInstance().getTableForDevAdder(hexString);
                KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.LoRaHelper$handleCall$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "呼叫桌号： " + tableForDevAdder;
                    }
                }, 1, null);
                String str = tableForDevAdder;
                if (str == null || StringsKt.isBlank(str)) {
                    sendCallAck$default(this, false, null, 2, null);
                    return;
                } else {
                    sendCallAck$default(this, true, null, 2, null);
                    BuildersKt__Builders_commonKt.launch$default(KotlinUtilsKt.getMyScope(), Dispatchers.getMain(), null, new LoRaHelper$handleCall$3(tableForDevAdder, null), 2, null);
                    return;
                }
            }
            sendCallAck(false, receiveCall.getDevAddr());
        }
    }

    public final Function0<Unit> getOnStateChangeListener() {
        return onStateChangeListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void switchState(final LoRaDeviceState state) {
        KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.LoRaHelper$switchState$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                byte[] bArr;
                StringBuilder sb = new StringBuilder();
                sb.append("switchState:");
                sb.append(LoRaDeviceState.this);
                sb.append(" devAdder:");
                LoRaHelper loRaHelper = LoRaHelper.INSTANCE;
                bArr = LoRaHelper.devAdder;
                sb.append(KotlinUtilsKt.toHexString(bArr));
                return sb.toString();
            }
        }, 1, null);
        LoRaClient.sendHaveAckMsg$default(LoRaClient.INSTANCE.getInstance(), new StateSwitchMsg(state), null, 2, null);
        LoraBroadcastHandler.INSTANCE.startBroadcast();
    }

    public final void setArriveState(final String state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.LoRaHelper$setArriveState$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "setState:" + state;
            }
        }, 1, null);
        int hashCode = state.hashCode();
        if (hashCode == 930446413) {
            if (state.equals("Arrived")) {
                LoRaClient.sendHaveAckMsg$default(LoRaClient.INSTANCE.getInstance(), new ArriveCancelMsg(true, devAdder), null, 2, null);
                devAdder = new byte[0];
                return;
            }
            return;
        }
        if (hashCode == 2011110042 && state.equals("Cancel")) {
            LoRaClient.sendHaveAckMsg$default(LoRaClient.INSTANCE.getInstance(), new ArriveCancelMsg(false, devAdder), null, 2, null);
            devAdder = new byte[0];
        }
    }

    static /* synthetic */ void sendCallAck$default(LoRaHelper loRaHelper, boolean z, byte[] bArr, int i, Object obj) {
        if ((i & 2) != 0) {
            bArr = devAdder;
        }
        loRaHelper.sendCallAck(z, bArr);
    }

    private final void sendCallAck(final boolean isSuc, final byte[] dav) {
        KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.LoRaHelper$sendCallAck$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "sendCallAck:" + KotlinUtilsKt.toHexString(dav) + " isSuc:" + isSuc;
            }
        }, 1, null);
        LoRaClient.sendHaveAckMsg$default(LoRaClient.INSTANCE.getInstance(), new RespondCallMsg(dav, isSuc, 10), null, 2, null);
    }

    public final boolean isButtonCalling() {
        return !(devAdder.length == 0);
    }

    public final void setBindState(final boolean bind) {
        bindState = bind;
        KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.LoRaHelper$setBindState$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "setBindState--" + bind;
            }
        }, 1, null);
        onStateChangeListener.invoke();
    }
}
