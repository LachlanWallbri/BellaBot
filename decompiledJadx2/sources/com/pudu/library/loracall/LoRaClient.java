package com.pudu.library.loracall;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudu.library.loracall.MsgReceiveHandle;
import com.pudu.library.loracall.bean.LoRaConfigParam;
import com.pudu.library.loracall.bean.LoRaVersionParam;
import com.pudu.library.loracall.bean.LoraCertificationParam;
import com.pudu.library.loracall.bean.TestPerformanceParam;
import com.pudu.library.loracall.dao.AppDatabase;
import com.pudu.library.loracall.dao.TableBindDao;
import com.pudu.library.loracall.dao.TableMatchBean;
import com.pudu.library.loracall.utils.LoraResetUtils;
import com.pudu.library.loracall.utils.UpdateUtils;
import com.pudu.loracall.library.C3965R;
import com.pudutech.serialport.library.ISerialPortDataReceiveCallback;
import com.pudutech.serialport.library.ISerialPortOpenStatusCallback;
import com.pudutech.serialport.library.SerialPortHelper;
import java.io.File;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.Job;
import org.apache.http.HttpStatus;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: LoRaClient.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Ô\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0002-2\u0018\u0000 z2\u00020\u0001:\u0001zB\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020FJ\u0006\u0010G\u001a\u00020BJ!\u0010H\u001a\u00020B2\u0006\u0010I\u001a\u00020\u001f2\u0006\u0010J\u001a\u00020\u0019H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010KJ\u0019\u0010L\u001a\u00020\u00122\u0006\u0010M\u001a\u00020\u0012H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010NJ\u0011\u0010O\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010PJ)\u0010Q\u001a\u00020B2!\u0010R\u001a\u001d\u0012\u0013\u0012\u00110T¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b(W\u0012\u0004\u0012\u00020B0SJ)\u0010X\u001a\u00020B2!\u0010R\u001a\u001d\u0012\u0013\u0012\u00110D¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020B0SJ)\u0010Z\u001a\u00020B2!\u0010R\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b([\u0012\u0004\u0012\u00020B0SJ)\u0010\\\u001a\u00020B2!\u0010R\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b([\u0012\u0004\u0012\u00020B0SJ-\u0010]\u001a\u00020B2%\b\u0002\u0010R\u001a\u001f\u0012\u0013\u0012\u00110^¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b(W\u0012\u0004\u0012\u00020B\u0018\u00010SJ \u0010_\u001a\u00020B2\u0018\b\u0002\u0010R\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010`\u0012\u0004\u0012\u00020B\u0018\u00010SJ\u001e\u0010a\u001a\u00020B2\u0016\b\u0002\u0010R\u001a\u0010\u0012\u0004\u0012\u00020b\u0012\u0004\u0012\u00020B\u0018\u00010SJ\u0010\u0010c\u001a\u0004\u0018\u00010D2\u0006\u0010d\u001a\u00020DJ \u0010e\u001a\u00020B2\u0018\b\u0002\u0010R\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010f\u0012\u0004\u0012\u00020B\u0018\u00010SJ\u0006\u0010g\u001a\u00020\u0012J\u0006\u0010h\u001a\u00020BJ\u000e\u0010i\u001a\u00020B2\u0006\u0010C\u001a\u00020DJ7\u0010j\u001a\u00020B2\u0006\u0010k\u001a\u00020l2'\b\u0002\u0010R\u001a!\u0012\u0015\u0012\u0013\u0018\u00010m¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b([\u0012\u0004\u0012\u00020B\u0018\u00010SJ5\u0010n\u001a\u00020B2\u0006\u0010W\u001a\u00020o2%\b\u0002\u0010R\u001a\u001f\u0012\u0013\u0012\u00110\u0012¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b(p\u0012\u0004\u0012\u00020B\u0018\u00010SJ\u0010\u0010q\u001a\u00020B2\u0006\u0010r\u001a\u00020sH\u0002J1\u0010t\u001a\u00020B2\u0006\u0010u\u001a\u00020<2!\u0010R\u001a\u001d\u0012\u0013\u0012\u00110\u0012¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b(p\u0012\u0004\u0012\u00020B0SJ7\u0010v\u001a\u00020B2\b\b\u0002\u0010w\u001a\u00020D2%\b\u0002\u0010R\u001a\u001f\u0012\u0013\u0012\u00110\u0012¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b(p\u0012\u0004\u0012\u00020B\u0018\u00010SJ5\u0010x\u001a\u00020B2\u0006\u0010W\u001a\u00020y2%\b\u0002\u0010R\u001a\u001f\u0012\u0013\u0012\u00110\u0012¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b(p\u0012\u0004\u0012\u00020B\u0018\u00010SR\u0014\u0010\u0003\u001a\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000eX\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001c\u0010$\u001a\u0004\u0018\u00010%X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0004\n\u0002\u0010.R\u000e\u0010/\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u000202X\u0082\u0004¢\u0006\u0004\n\u0002\u00103R(\u00106\u001a\u0004\u0018\u0001052\b\u00104\u001a\u0004\u0018\u000105@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001a\u0010;\u001a\u00020<X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006{"}, m3961d2 = {"Lcom/pudu/library/loracall/LoRaClient;", "", "()V", "dao", "Lcom/pudu/library/loracall/dao/TableBindDao;", "getDao$library_loracall_release", "()Lcom/pudu/library/loracall/dao/TableBindDao;", "deviceType", "", "getDeviceType", "()I", "setDeviceType", "(I)V", "firmwareType", "", "getFirmwareType$library_loracall_release", "()B", "isOpened", "", "()Z", "setOpened", "(Z)V", "job", "Lkotlinx/coroutines/Job;", "mConfig", "Lcom/pudu/library/loracall/LoRaConfig;", "getMConfig", "()Lcom/pudu/library/loracall/LoRaConfig;", "setMConfig", "(Lcom/pudu/library/loracall/LoRaConfig;)V", "mContext", "Landroid/content/Context;", "getMContext$library_loracall_release", "()Landroid/content/Context;", "setMContext$library_loracall_release", "(Landroid/content/Context;)V", "msgSendHandle", "Lcom/pudu/library/loracall/MsgSendHandle;", "getMsgSendHandle$library_loracall_release", "()Lcom/pudu/library/loracall/MsgSendHandle;", "setMsgSendHandle$library_loracall_release", "(Lcom/pudu/library/loracall/MsgSendHandle;)V", "receiveHandle", "Lcom/pudu/library/loracall/MsgReceiveHandle;", "serialPortDataReceiveCallback", "com/pudu/library/loracall/LoRaClient$serialPortDataReceiveCallback$1", "Lcom/pudu/library/loracall/LoRaClient$serialPortDataReceiveCallback$1;", "serialPortHelper", "Lcom/pudutech/serialport/library/SerialPortHelper;", "serialPortOpenStatusCallback", "com/pudu/library/loracall/LoRaClient$serialPortOpenStatusCallback$1", "Lcom/pudu/library/loracall/LoRaClient$serialPortOpenStatusCallback$1;", ES6Iterator.VALUE_PROPERTY, "Ljava/io/File;", "updateFile", "getUpdateFile", "()Ljava/io/File;", "setUpdateFile", "(Ljava/io/File;)V", "updateFileByte", "", "getUpdateFileByte$library_loracall_release", "()[B", "setUpdateFileByte$library_loracall_release", "([B)V", "addListener", "", TransferTable.COLUMN_KEY, "", "listener", "Lcom/pudu/library/loracall/MsgReceiveHandle$Listener;", "closeSerialPort", "connectLoRa", "context", "config", "(Landroid/content/Context;Lcom/pudu/library/loracall/LoRaConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "enableWifiModule", "enable", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchLoRaRssi", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLoRaConfig", "callback", "Lkotlin/Function1;", "Lcom/pudu/library/loracall/bean/LoRaConfigParam;", "Lkotlin/ParameterName;", "name", NotificationCompat.CATEGORY_MESSAGE, "getLoRaMac", "mac", "getLoRaNetState", "state", "getLoRaState", "getLoRaVersion", "Lcom/pudu/library/loracall/bean/LoRaVersionParam;", "getRunState", "Lcom/pudu/library/loracall/LoraRunState;", "getSignalStrength", "Lcom/pudu/library/loracall/LoraSignalStrength;", "getTableForDevAdder", "devAdder", "getUpdateState", "Lcom/pudu/library/loracall/LoraUpdateState;", "isExistLora", "reconnect", "removeListener", "sendApprove", "param", "Lcom/pudu/library/loracall/bean/LoraCertificationParam;", "Lcom/pudu/library/loracall/LoraApproveState;", "sendHaveAckMsg", "Lcom/pudu/library/loracall/BaseMsg;", "isSuccess", "sendMsg", "slipMsg", "Lcom/pudu/library/loracall/SlipMsgBean;", "setLoRaConfig", "data", "startUpdate", "filePath", "testPerformance", "Lcom/pudu/library/loracall/bean/TestPerformanceParam;", "Companion", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoRaClient {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy instance$delegate = LazyKt.lazy(new Function0<LoRaClient>() { // from class: com.pudu.library.loracall.LoRaClient$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LoRaClient invoke() {
            return new LoRaClient();
        }
    });
    private boolean isOpened;
    private Job job;
    public Context mContext;
    private MsgSendHandle msgSendHandle;
    private File updateFile;
    private final SerialPortHelper serialPortHelper = SerialPortHelper.INSTANCE.getINSTANCE();
    private final MsgReceiveHandle receiveHandle = new MsgReceiveHandle();
    private int deviceType;
    private LoRaConfig mConfig = new LoRaConfig(null, null, null, null, 0, 0, this.deviceType, null, 191, null);
    private final LoRaClient$serialPortDataReceiveCallback$1 serialPortDataReceiveCallback = new ISerialPortDataReceiveCallback() { // from class: com.pudu.library.loracall.LoRaClient$serialPortDataReceiveCallback$1
        @Override // com.pudutech.serialport.library.ISerialPortDataReceiveCallback
        public void onReceive(final byte[] data, final int length) {
            MsgReceiveHandle msgReceiveHandle;
            Intrinsics.checkParameterIsNotNull(data, "data");
            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.LoRaClient$serialPortDataReceiveCallback$1$onReceive$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "onReceive：" + KotlinUtilsKt.toHexString(data) + "\n" + length;
                }
            }, 1, null);
            msgReceiveHandle = LoRaClient.this.receiveHandle;
            msgReceiveHandle.receiveMsg(data);
        }
    };
    private final LoRaClient$serialPortOpenStatusCallback$1 serialPortOpenStatusCallback = new ISerialPortOpenStatusCallback() { // from class: com.pudu.library.loracall.LoRaClient$serialPortOpenStatusCallback$1
        @Override // com.pudutech.serialport.library.ISerialPortOpenStatusCallback
        public void callbackOpenStatus(final boolean opened) {
            SerialPortHelper serialPortHelper;
            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.LoRaClient$serialPortOpenStatusCallback$1$callbackOpenStatus$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "callbackOpenStatus：" + opened;
                }
            }, 1, null);
            if (opened) {
                LoRaClient.this.setOpened(opened);
                if (LoRaClient.this.getMsgSendHandle() == null) {
                    LoRaClient loRaClient = LoRaClient.this;
                    serialPortHelper = loRaClient.serialPortHelper;
                    loRaClient.setMsgSendHandle$library_loracall_release(new MsgSendHandle(serialPortHelper));
                    MsgSendHandle msgSendHandle = LoRaClient.this.getMsgSendHandle();
                    if (msgSendHandle != null) {
                        msgSendHandle.start();
                        return;
                    }
                    return;
                }
                return;
            }
            LoRaClient.this.reconnect();
        }
    };
    private final byte firmwareType = 1;
    private byte[] updateFileByte = new byte[0];

    public final Context getMContext$library_loracall_release() {
        Context context = this.mContext;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        }
        return context;
    }

    public final void setMContext$library_loracall_release(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.mContext = context;
    }

    /* compiled from: LoRaClient.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m3961d2 = {"Lcom/pudu/library/loracall/LoRaClient$Companion;", "", "()V", "instance", "Lcom/pudu/library/loracall/LoRaClient;", "getInstance", "()Lcom/pudu/library/loracall/LoRaClient;", "instance$delegate", "Lkotlin/Lazy;", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        public final LoRaClient getInstance() {
            Lazy lazy = LoRaClient.instance$delegate;
            Companion companion = LoRaClient.INSTANCE;
            return (LoRaClient) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* renamed from: getMsgSendHandle$library_loracall_release, reason: from getter */
    public final MsgSendHandle getMsgSendHandle() {
        return this.msgSendHandle;
    }

    public final void setMsgSendHandle$library_loracall_release(MsgSendHandle msgSendHandle) {
        this.msgSendHandle = msgSendHandle;
    }

    /* renamed from: isOpened, reason: from getter */
    public final boolean getIsOpened() {
        return this.isOpened;
    }

    public final void setOpened(boolean z) {
        this.isOpened = z;
    }

    public final TableBindDao getDao$library_loracall_release() {
        return AppDatabase.INSTANCE.getInstance().getTableDao();
    }

    public final int getDeviceType() {
        return this.deviceType;
    }

    public final void setDeviceType(int i) {
        this.deviceType = i;
    }

    public final LoRaConfig getMConfig() {
        return this.mConfig;
    }

    public final void setMConfig(LoRaConfig loRaConfig) {
        Intrinsics.checkParameterIsNotNull(loRaConfig, "<set-?>");
        this.mConfig = loRaConfig;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendMsg(SlipMsgBean slipMsg) {
        if (this.serialPortHelper.getOpened()) {
            MsgSendHandle msgSendHandle = this.msgSendHandle;
            if (msgSendHandle != null) {
                msgSendHandle.sendMsg(slipMsg);
                return;
            }
            return;
        }
        Function1<SlipMsgBean, Unit> callback = slipMsg.getCallback();
        if (callback != null) {
            callback.invoke(new SlipMsgBean());
        }
        KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.LoRaClient$sendMsg$1
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "串口未开启";
            }
        }, 1, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object connectLoRa(Context context, LoRaConfig loRaConfig, Continuation<? super Unit> continuation) {
        LoRaClient$connectLoRa$1 loRaClient$connectLoRa$1;
        int i;
        LoRaClient loRaClient;
        if (continuation instanceof LoRaClient$connectLoRa$1) {
            loRaClient$connectLoRa$1 = (LoRaClient$connectLoRa$1) continuation;
            if ((loRaClient$connectLoRa$1.label & Integer.MIN_VALUE) != 0) {
                loRaClient$connectLoRa$1.label -= Integer.MIN_VALUE;
                LoRaClient$connectLoRa$1 loRaClient$connectLoRa$12 = loRaClient$connectLoRa$1;
                Object obj = loRaClient$connectLoRa$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = loRaClient$connectLoRa$12.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    this.deviceType = loRaConfig.getDeviceType();
                    this.mContext = context;
                    this.mConfig = loRaConfig;
                    this.serialPortHelper.init(context, loRaConfig.getID_VENDOR(), loRaConfig.getID_PRODUCT(), loRaConfig.getPRODUCT(), loRaConfig.getINTERFACE_NUN(), loRaConfig.getBAUD_RATE(), loRaConfig.getFLAGS(), this.serialPortOpenStatusCallback, this.serialPortDataReceiveCallback);
                    LoraResetUtils loraResetUtils = LoraResetUtils.INSTANCE;
                    String reset_pin = loRaConfig.getRESET_PIN();
                    loRaClient$connectLoRa$12.L$0 = this;
                    loRaClient$connectLoRa$12.L$1 = context;
                    loRaClient$connectLoRa$12.L$2 = loRaConfig;
                    loRaClient$connectLoRa$12.label = 1;
                    if (loraResetUtils.loraReset(reset_pin, loRaClient$connectLoRa$12) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    loRaClient = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    loRaClient = (LoRaClient) loRaClient$connectLoRa$12.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                loRaClient.serialPortHelper.openSerialPort();
                UpdateUtils.INSTANCE.initUpdateFile();
                return Unit.INSTANCE;
            }
        }
        loRaClient$connectLoRa$1 = new LoRaClient$connectLoRa$1(this, continuation);
        LoRaClient$connectLoRa$1 loRaClient$connectLoRa$122 = loRaClient$connectLoRa$1;
        Object obj2 = loRaClient$connectLoRa$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = loRaClient$connectLoRa$122.label;
        if (i != 0) {
        }
        loRaClient.serialPortHelper.openSerialPort();
        UpdateUtils.INSTANCE.initUpdateFile();
        return Unit.INSTANCE;
    }

    public final void reconnect() {
        Job launch$default;
        if (this.isOpened) {
            Job job = this.job;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            launch$default = BuildersKt__Builders_commonKt.launch$default(KotlinUtilsKt.getMyScope(), null, null, new LoRaClient$reconnect$1(this, null), 3, null);
            this.job = launch$default;
        }
    }

    public final boolean isExistLora() {
        return LoRaVersionParam.INSTANCE.getLoRaVersion().length >= 6;
    }

    public final void closeSerialPort() {
        KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.LoRaClient$closeSerialPort$1
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "closeSerialPort：";
            }
        }, 1, null);
        this.serialPortHelper.closeSerialPort();
        MsgSendHandle msgSendHandle = this.msgSendHandle;
        if (msgSendHandle != null) {
            msgSendHandle.stopHandle();
        }
        this.msgSendHandle = (MsgSendHandle) null;
    }

    public final void addListener(String key, MsgReceiveHandle.Listener listener) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.receiveHandle.addListener(key, listener);
    }

    public final void removeListener(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        this.receiveHandle.removeListener(key);
    }

    public final void getLoRaState(final Function1<? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        sendMsg(new SlipMsgBean((byte) 1, (byte) 1, null, new Function1<SlipMsgBean, Unit>() { // from class: com.pudu.library.loracall.LoRaClient$getLoRaState$msgBean$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SlipMsgBean slipMsgBean) {
                invoke2(slipMsgBean);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SlipMsgBean it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (!(it.getData().length == 0)) {
                    Function1.this.invoke(Integer.valueOf(it.getData()[0]));
                } else {
                    Function1.this.invoke(-1);
                }
            }
        }, 4, null));
    }

    public final void getLoRaNetState(final Function1<? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        sendMsg(new SlipMsgBean((byte) 1, (byte) 2, null, new Function1<SlipMsgBean, Unit>() { // from class: com.pudu.library.loracall.LoRaClient$getLoRaNetState$msgBean$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SlipMsgBean slipMsgBean) {
                invoke2(slipMsgBean);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SlipMsgBean it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (!(it.getData().length == 0)) {
                    Function1.this.invoke(Integer.valueOf(it.getData()[0]));
                } else {
                    Function1.this.invoke(-1);
                }
            }
        }, 4, null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void getLoRaVersion$default(LoRaClient loRaClient, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = (Function1) null;
        }
        loRaClient.getLoRaVersion(function1);
    }

    public final void getLoRaVersion(final Function1<? super LoRaVersionParam, Unit> callback) {
        sendMsg(new SlipMsgBean((byte) 1, (byte) 3, null, new Function1<SlipMsgBean, Unit>() { // from class: com.pudu.library.loracall.LoRaClient$getLoRaVersion$msgBean$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SlipMsgBean slipMsgBean) {
                invoke2(slipMsgBean);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SlipMsgBean it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Function1 function1 = Function1.this;
                if (function1 != null) {
                }
            }
        }, 4, null));
    }

    public final void getLoRaMac(final Function1<? super String, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        sendMsg(new SlipMsgBean((byte) 1, (byte) HttpStatus.SC_RESET_CONTENT, null, new Function1<SlipMsgBean, Unit>() { // from class: com.pudu.library.loracall.LoRaClient$getLoRaMac$msgBean$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SlipMsgBean slipMsgBean) {
                invoke2(slipMsgBean);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SlipMsgBean it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (it.getData().length >= 4) {
                    Function1.this.invoke(KotlinUtilsKt.toHexString(ArraysKt.reversedArray(ArraysKt.copyOfRange(it.getData(), 0, 4))));
                } else {
                    Function1.this.invoke("");
                }
            }
        }, 4, null));
    }

    public final void setLoRaConfig(byte[] data, final Function1<? super Boolean, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        sendMsg(new SlipMsgBean((byte) 3, (byte) 5, data, new Function1<SlipMsgBean, Unit>() { // from class: com.pudu.library.loracall.LoRaClient$setLoRaConfig$msgBean$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SlipMsgBean slipMsgBean) {
                invoke2(slipMsgBean);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SlipMsgBean it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Function1.this.invoke(Boolean.valueOf(it.getType() != ((byte) 0)));
            }
        }));
    }

    public final void getLoRaConfig(final Function1<? super LoRaConfigParam, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        sendMsg(new SlipMsgBean((byte) 1, (byte) 5, null, new Function1<SlipMsgBean, Unit>() { // from class: com.pudu.library.loracall.LoRaClient$getLoRaConfig$msgBean$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SlipMsgBean slipMsgBean) {
                invoke2(slipMsgBean);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SlipMsgBean it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Function1.this.invoke(new LoRaConfigParam(it.getData()));
            }
        }, 4, null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void sendHaveAckMsg$default(LoRaClient loRaClient, BaseMsg baseMsg, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = (Function1) null;
        }
        loRaClient.sendHaveAckMsg(baseMsg, function1);
    }

    public final void sendHaveAckMsg(BaseMsg msg, final Function1<? super Boolean, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        sendMsg(new SlipMsgBean((byte) 3, (byte) 32, msg.getData(), new Function1<SlipMsgBean, Unit>() { // from class: com.pudu.library.loracall.LoRaClient$sendHaveAckMsg$msgBean$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SlipMsgBean slipMsgBean) {
                invoke2(slipMsgBean);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SlipMsgBean it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Function1 function1 = Function1.this;
                if (function1 != null) {
                }
            }
        }));
    }

    /* renamed from: getFirmwareType$library_loracall_release, reason: from getter */
    public final byte getFirmwareType() {
        return this.firmwareType;
    }

    public final File getUpdateFile() {
        return this.updateFile;
    }

    public final void setUpdateFile(File file) {
        byte[] bArr;
        this.updateFile = file;
        if (file == null || (bArr = FilesKt.readBytes(file)) == null) {
            bArr = new byte[0];
        }
        this.updateFileByte = bArr;
    }

    /* renamed from: getUpdateFileByte$library_loracall_release, reason: from getter */
    public final byte[] getUpdateFileByte() {
        return this.updateFileByte;
    }

    public final void setUpdateFileByte$library_loracall_release(byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "<set-?>");
        this.updateFileByte = bArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void startUpdate$default(LoRaClient loRaClient, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = UpdateUtils.INSTANCE.getUpdateFilePath();
        }
        if ((i & 2) != 0) {
            function1 = (Function1) null;
        }
        loRaClient.startUpdate(str, function1);
    }

    public final void startUpdate(final String filePath, final Function1<? super Boolean, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.LoRaClient$startUpdate$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "开始更新固件:" + filePath;
            }
        }, 1, null);
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            setUpdateFile(file);
            if (LoRaVersionParam.INSTANCE.getLoRaVersion().length == 0) {
                getLoRaVersion$default(this, null, 1, null);
            }
            sendMsg(new SlipMsgBean((byte) 2, (byte) 208, new byte[]{this.firmwareType}, new Function1<SlipMsgBean, Unit>() { // from class: com.pudu.library.loracall.LoRaClient$startUpdate$msgBean$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SlipMsgBean slipMsgBean) {
                    invoke2(slipMsgBean);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SlipMsgBean it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Function1 function1 = Function1.this;
                    if (function1 != null) {
                    }
                }
            }));
            return;
        }
        KotlinUtilsKt.showToast(KotlinUtilsKt.getString(this, C3965R.string.lora_upgrade_file_not_exist));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void getUpdateState$default(LoRaClient loRaClient, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = (Function1) null;
        }
        loRaClient.getUpdateState(function1);
    }

    public final void getUpdateState(final Function1<? super LoraUpdateState, Unit> callback) {
        sendMsg(new SlipMsgBean((byte) 1, (byte) 216, new byte[]{this.firmwareType}, new Function1<SlipMsgBean, Unit>() { // from class: com.pudu.library.loracall.LoRaClient$getUpdateState$msgBean$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SlipMsgBean slipMsgBean) {
                invoke2(slipMsgBean);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SlipMsgBean it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                LoraUpdateState loraUpdateState = it.getData().length >= 2 ? new LoraUpdateState(it.getData()[0], it.getData()[1]) : null;
                Function1 function1 = Function1.this;
                if (function1 != null) {
                }
            }
        }));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void getRunState$default(LoRaClient loRaClient, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = (Function1) null;
        }
        loRaClient.getRunState(function1);
    }

    public final void getRunState(final Function1<? super LoraRunState, Unit> callback) {
        sendMsg(new SlipMsgBean((byte) 1, (byte) 4, new byte[]{this.firmwareType}, new Function1<SlipMsgBean, Unit>() { // from class: com.pudu.library.loracall.LoRaClient$getRunState$msgBean$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SlipMsgBean slipMsgBean) {
                invoke2(slipMsgBean);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SlipMsgBean it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                LoraRunState loraRunState = (it.getData().length == 0) ^ true ? new LoraRunState(it.getData()[0]) : null;
                Function1 function1 = Function1.this;
                if (function1 != null) {
                }
            }
        }));
    }

    public final String getTableForDevAdder(String devAdder) {
        Intrinsics.checkParameterIsNotNull(devAdder, "devAdder");
        TableMatchBean table = getDao$library_loracall_release().getTable(devAdder);
        if (table != null) {
            return table.getTableName();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void sendApprove$default(LoRaClient loRaClient, LoraCertificationParam loraCertificationParam, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = (Function1) null;
        }
        loRaClient.sendApprove(loraCertificationParam, function1);
    }

    public final void sendApprove(LoraCertificationParam param, final Function1<? super LoraApproveState, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(param, "param");
        byte[] codingData = param.codingData();
        if (codingData != null) {
            sendMsg(new SlipMsgBean((byte) 2, (byte) 218, codingData, new Function1<SlipMsgBean, Unit>() { // from class: com.pudu.library.loracall.LoRaClient$sendApprove$msgBean$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SlipMsgBean slipMsgBean) {
                    invoke2(slipMsgBean);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SlipMsgBean it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    LoraApproveState loraApproveState = (it.getData().length == 0) ^ true ? new LoraApproveState(it.getData()[0]) : null;
                    Function1 function1 = Function1.this;
                    if (function1 != null) {
                    }
                }
            }));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void getSignalStrength$default(LoRaClient loRaClient, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = (Function1) null;
        }
        loRaClient.getSignalStrength(function1);
    }

    public final void getSignalStrength(final Function1<? super LoraSignalStrength, Unit> callback) {
        sendMsg(new SlipMsgBean((byte) 1, (byte) 48, null, new Function1<SlipMsgBean, Unit>() { // from class: com.pudu.library.loracall.LoRaClient$getSignalStrength$msgBean$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SlipMsgBean slipMsgBean) {
                invoke2(slipMsgBean);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SlipMsgBean it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Function1 function1 = Function1.this;
                if (function1 != null) {
                }
            }
        }, 4, null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void testPerformance$default(LoRaClient loRaClient, TestPerformanceParam testPerformanceParam, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = (Function1) null;
        }
        loRaClient.testPerformance(testPerformanceParam, function1);
    }

    public final void testPerformance(TestPerformanceParam msg, final Function1<? super Boolean, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        sendMsg(new SlipMsgBean((byte) 3, (byte) 49, msg.getData(), new Function1<SlipMsgBean, Unit>() { // from class: com.pudu.library.loracall.LoRaClient$testPerformance$msgBean$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SlipMsgBean slipMsgBean) {
                invoke2(slipMsgBean);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SlipMsgBean it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Function1 function1 = Function1.this;
                if (function1 != null) {
                }
            }
        }));
    }

    public final Object enableWifiModule(final boolean z, Continuation<? super Boolean> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        byte[] bArr = new byte[1];
        bArr[0] = z ? (byte) 1 : (byte) 0;
        sendMsg(new SlipMsgBean((byte) 2, (byte) 6, bArr, new Function1<SlipMsgBean, Unit>() { // from class: com.pudu.library.loracall.LoRaClient$enableWifiModule$$inlined$suspendCancellableCoroutine$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SlipMsgBean slipMsgBean) {
                invoke2(slipMsgBean);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final SlipMsgBean it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.LoRaClient$enableWifiModule$$inlined$suspendCancellableCoroutine$lambda$1.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "enableWifiModule:" + SlipMsgBean.this;
                    }
                }, 1, null);
                if (it.getType() == ((byte) 16) && it.getId() == ((byte) 6)) {
                    boolean z2 = it.getData()[0] == 1;
                    CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                    Boolean valueOf = Boolean.valueOf(z2 == z);
                    Result.Companion companion = Result.INSTANCE;
                    cancellableContinuation.resumeWith(Result.m4510constructorimpl(valueOf));
                    return;
                }
                CancellableContinuation cancellableContinuation2 = CancellableContinuation.this;
                Result.Companion companion2 = Result.INSTANCE;
                cancellableContinuation2.resumeWith(Result.m4510constructorimpl(false));
            }
        }));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final Object fetchLoRaRssi(Continuation<? super Integer> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        sendMsg(new SlipMsgBean((byte) 1, (byte) 7, null, new Function1<SlipMsgBean, Unit>() { // from class: com.pudu.library.loracall.LoRaClient$fetchLoRaRssi$$inlined$suspendCancellableCoroutine$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SlipMsgBean slipMsgBean) {
                invoke2(slipMsgBean);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final SlipMsgBean it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.LoRaClient$fetchLoRaRssi$$inlined$suspendCancellableCoroutine$lambda$1.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "fetchLoRaRssi:" + SlipMsgBean.this;
                    }
                }, 1, null);
                if (it.getType() == ((byte) 16) && it.getId() == ((byte) 7)) {
                    if (it.getData()[0] == 1) {
                        CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                        Integer valueOf = Integer.valueOf(KotlinUtilsKt.readInt16LE$default(new byte[]{it.getData()[1], it.getData()[2]}, 0, 1, null));
                        Result.Companion companion = Result.INSTANCE;
                        cancellableContinuation.resumeWith(Result.m4510constructorimpl(valueOf));
                        return;
                    }
                }
                CancellableContinuation cancellableContinuation2 = CancellableContinuation.this;
                Result.Companion companion2 = Result.INSTANCE;
                cancellableContinuation2.resumeWith(Result.m4510constructorimpl(0));
            }
        }, 4, null));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
