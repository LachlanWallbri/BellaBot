package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import android.bluetooth.BluetoothManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleCoroutineScope;
import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.iflytek.aiui.AIUIConstant;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.bluetooth.PdBluetoothConfig;
import com.pudutech.bluetooth.PdBluetoothManager;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.util.FileUtil;
import com.pudutech.disinfect.baselib.util.GsonSingleton;
import com.pudutech.log.ILog;
import com.pudutech.log.ProxyLog;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.DelayKt;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: BluetoothHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0014\u0018\u0000 \u001d2\u00020\u0001:\u0003\u001c\u001d\u001eB\u0005¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u000e\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0004J\b\u0010\u0015\u001a\u00020\u000bH\u0016J\b\u0010\u0016\u001a\u00020\u000bH\u0016J\u0006\u0010\u0017\u001a\u00020\u000bJ\u000e\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0004J\u0014\u0010\u0019\u001a\u0004\u0018\u00010\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004H\u0002J\b\u0010\u001a\u001a\u00020\u0004H\u0002J\b\u0010\u001b\u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/BluetoothHelper;", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/AdapterLifecycleObserver;", "()V", "TAG", "", "_bluetoothList", "", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/BluetoothHelper$BluetoothInfo;", "_bluetoothMac", "callBackBluetoothInfo", "Lkotlin/Function1;", "", "getCallBackBluetoothInfo", "()Lkotlin/jvm/functions/Function1;", "setCallBackBluetoothInfo", "(Lkotlin/jvm/functions/Function1;)V", MqttServiceConstants.CONNECT_ACTION, "destination", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "connectMac", "mac", "create", "destroy", "disConnect", "disConnectMac", "getBluetoothMac", "getBluetoothString", "loadBluetoothList", "BluetoothInfo", "Companion", "DefLog", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class BluetoothHelper extends AdapterLifecycleObserver {
    private final String TAG = "BluetoothHelper";
    private List<BluetoothInfo> _bluetoothList;
    private String _bluetoothMac;
    private Function1<? super BluetoothInfo, Unit> callBackBluetoothInfo;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String BLUETOOTH_LIST_FILE_PATH = BLUETOOTH_LIST_FILE_PATH;
    private static final String BLUETOOTH_LIST_FILE_PATH = BLUETOOTH_LIST_FILE_PATH;

    /* compiled from: BluetoothHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\tR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/BluetoothHelper$Companion;", "", "()V", "BLUETOOTH_LIST_FILE_PATH", "", "getBLUETOOTH_LIST_FILE_PATH", "()Ljava/lang/String;", "enable", "", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getBLUETOOTH_LIST_FILE_PATH() {
            return BluetoothHelper.BLUETOOTH_LIST_FILE_PATH;
        }

        public final void enable(boolean enable) {
            if (enable) {
                Object systemService = RobotContext.INSTANCE.getContext().getSystemService((Class<Object>) BluetoothManager.class);
                Intrinsics.checkExpressionValueIsNotNull(systemService, "RobotContext.context.get…toothManager::class.java)");
                ((BluetoothManager) systemService).getAdapter().enable();
            } else {
                Object systemService2 = RobotContext.INSTANCE.getContext().getSystemService((Class<Object>) BluetoothManager.class);
                Intrinsics.checkExpressionValueIsNotNull(systemService2, "RobotContext.context.get…toothManager::class.java)");
                ((BluetoothManager) systemService2).getAdapter().disable();
            }
        }
    }

    public final Function1<BluetoothInfo, Unit> getCallBackBluetoothInfo() {
        return this.callBackBluetoothInfo;
    }

    public final void setCallBackBluetoothInfo(Function1<? super BluetoothInfo, Unit> function1) {
        this.callBackBluetoothInfo = function1;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.AdapterLifecycleObserver, com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    public void create() {
        super.create();
        LifecycleCoroutineScope lifecycleCoroutineScope = get_lifecycleScope();
        if (lifecycleCoroutineScope != null) {
            BuildersKt__Builders_commonKt.launch$default(lifecycleCoroutineScope, null, null, new BluetoothHelper$create$1(this, null), 3, null);
        }
        AppCompatActivity appCompatActivity = get_activity();
        if (appCompatActivity != null) {
            ProxyLog.INSTANCE.proxyLog(new DefLog());
            PdBluetoothManager.INSTANCE.init(appCompatActivity, new Function1<PdBluetoothConfig, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.BluetoothHelper$create$2$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PdBluetoothConfig pdBluetoothConfig) {
                    invoke2(pdBluetoothConfig);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PdBluetoothConfig receiver) {
                    Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                    receiver.setAutoConnect(false);
                }
            });
        }
    }

    /* compiled from: BluetoothHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/BluetoothHelper$DefLog;", "Lcom/pudutech/log/ILog;", "(Lcom/pudutech/bumblebee/robot_ui/ui/helper/BluetoothHelper;)V", LinkFormat.DOMAIN, "", AIUIConstant.KEY_TAG, "", AIUIConstant.KEY_CONTENT, C3898x.f4338g, "i", "w", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    private final class DefLog implements ILog {
        public DefLog() {
        }

        @Override // com.pudutech.log.ILog
        /* renamed from: d */
        public void mo3283d(String tag, String content) {
            Intrinsics.checkParameterIsNotNull(tag, "tag");
            Intrinsics.checkParameterIsNotNull(content, "content");
            Pdlog.m3273d(tag, content);
        }

        @Override // com.pudutech.log.ILog
        /* renamed from: e */
        public void mo3284e(String tag, String content) {
            Intrinsics.checkParameterIsNotNull(tag, "tag");
            Intrinsics.checkParameterIsNotNull(content, "content");
            Pdlog.m3274e(tag, content);
        }

        @Override // com.pudutech.log.ILog
        /* renamed from: i */
        public void mo3285i(String tag, String content) {
            Intrinsics.checkParameterIsNotNull(tag, "tag");
            Intrinsics.checkParameterIsNotNull(content, "content");
            Pdlog.m3273d(tag, content);
        }

        @Override // com.pudutech.log.ILog
        /* renamed from: w */
        public void mo3286w(String tag, String content) {
            Intrinsics.checkParameterIsNotNull(tag, "tag");
            Intrinsics.checkParameterIsNotNull(content, "content");
            Pdlog.m3277w(tag, content);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadBluetoothList() {
        String readContentFromFile = FileUtil.INSTANCE.readContentFromFile(BLUETOOTH_LIST_FILE_PATH);
        String str = readContentFromFile;
        if (!(str == null || str.length() == 0)) {
            this._bluetoothList = GsonSingleton.INSTANCE.getINSTANCE().fromJsonArray(readContentFromFile, BluetoothInfo.class);
        }
        Log.d(this.TAG, String.valueOf(this._bluetoothList));
    }

    private final String getBluetoothMac(String destination) {
        List<BluetoothInfo> list;
        String str = destination;
        if (!(str == null || str.length() == 0) && (list = this._bluetoothList) != null) {
            for (BluetoothInfo bluetoothInfo : list) {
                if (Intrinsics.areEqual(bluetoothInfo.getDestination(), destination)) {
                    return bluetoothInfo.getBluetoothMac();
                }
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00be A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object connect(String str, Continuation<? super Unit> continuation) {
        BluetoothHelper$connect$1 bluetoothHelper$connect$1;
        int i;
        String str2;
        String str3;
        BluetoothHelper bluetoothHelper;
        if (continuation instanceof BluetoothHelper$connect$1) {
            bluetoothHelper$connect$1 = (BluetoothHelper$connect$1) continuation;
            if ((bluetoothHelper$connect$1.label & Integer.MIN_VALUE) != 0) {
                bluetoothHelper$connect$1.label -= Integer.MIN_VALUE;
                Object obj = bluetoothHelper$connect$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = bluetoothHelper$connect$1.label;
                if (i == 0) {
                    if (i != 1) {
                        if (i == 2) {
                            BluetoothHelper bluetoothHelper2 = (BluetoothHelper) bluetoothHelper$connect$1.L$0;
                            try {
                                ResultKt.throwOnFailure(obj);
                            } catch (Exception e) {
                                e = e;
                                bluetoothHelper = bluetoothHelper2;
                                Pdlog.m3273d(bluetoothHelper.TAG, "connect crash " + e);
                                return Unit.INSTANCE;
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    String str4 = (String) bluetoothHelper$connect$1.L$2;
                    String str5 = (String) bluetoothHelper$connect$1.L$1;
                    bluetoothHelper = (BluetoothHelper) bluetoothHelper$connect$1.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        str3 = str4;
                        str = str5;
                        Pdlog.m3273d(bluetoothHelper.TAG, "connct success");
                        bluetoothHelper$connect$1.L$0 = bluetoothHelper;
                        bluetoothHelper$connect$1.L$1 = str;
                        bluetoothHelper$connect$1.L$2 = str3;
                        bluetoothHelper$connect$1.label = 2;
                        if (DelayKt.delay(100L, bluetoothHelper$connect$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        Pdlog.m3273d(bluetoothHelper.TAG, "connect crash " + e);
                        return Unit.INSTANCE;
                    }
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
                String bluetoothMac = getBluetoothMac(str);
                if (bluetoothMac == null) {
                    str2 = null;
                } else {
                    if (bluetoothMac == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    str2 = bluetoothMac.toUpperCase();
                    Intrinsics.checkExpressionValueIsNotNull(str2, "(this as java.lang.String).toUpperCase()");
                }
                this._bluetoothMac = str2;
                str3 = this._bluetoothMac;
                if (str3 != null) {
                    try {
                        if (!TextUtils.isEmpty(str3)) {
                            PdBluetoothManager pdBluetoothManager = PdBluetoothManager.INSTANCE;
                            if (str3 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            String upperCase = str3.toUpperCase();
                            Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
                            bluetoothHelper$connect$1.L$0 = this;
                            bluetoothHelper$connect$1.L$1 = str;
                            bluetoothHelper$connect$1.L$2 = str3;
                            bluetoothHelper$connect$1.label = 1;
                            if (pdBluetoothManager.connect(upperCase, bluetoothHelper$connect$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            bluetoothHelper = this;
                            Pdlog.m3273d(bluetoothHelper.TAG, "connct success");
                            bluetoothHelper$connect$1.L$0 = bluetoothHelper;
                            bluetoothHelper$connect$1.L$1 = str;
                            bluetoothHelper$connect$1.L$2 = str3;
                            bluetoothHelper$connect$1.label = 2;
                            if (DelayKt.delay(100L, bluetoothHelper$connect$1) == coroutine_suspended) {
                            }
                        }
                    } catch (Exception e3) {
                        e = e3;
                        bluetoothHelper = this;
                        Pdlog.m3273d(bluetoothHelper.TAG, "connect crash " + e);
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
        }
        bluetoothHelper$connect$1 = new BluetoothHelper$connect$1(this, continuation);
        Object obj2 = bluetoothHelper$connect$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = bluetoothHelper$connect$1.label;
        if (i == 0) {
        }
    }

    public final void connectMac(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        LifecycleCoroutineScope lifecycleCoroutineScope = get_lifecycleScope();
        if (lifecycleCoroutineScope != null) {
            BuildersKt__Builders_commonKt.launch$default(lifecycleCoroutineScope, null, null, new BluetoothHelper$connectMac$1(mac, null), 3, null);
        }
    }

    public final void disConnectMac(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        LifecycleCoroutineScope lifecycleCoroutineScope = get_lifecycleScope();
        if (lifecycleCoroutineScope != null) {
            BuildersKt__Builders_commonKt.launch$default(lifecycleCoroutineScope, null, null, new BluetoothHelper$disConnectMac$1(mac, null), 3, null);
        }
    }

    public final void disConnect() {
        LifecycleCoroutineScope lifecycleCoroutineScope;
        String str = this._bluetoothMac;
        if (str == null || (lifecycleCoroutineScope = get_lifecycleScope()) == null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(lifecycleCoroutineScope, null, null, new BluetoothHelper$disConnect$1$1(str, null), 3, null);
    }

    private final String getBluetoothString() {
        StringBuilder sb = new StringBuilder();
        File file = new File(BLUETOOTH_LIST_FILE_PATH);
        if (!file.exists()) {
            return "";
        }
        Iterator it = FilesKt.readLines$default(file, null, 1, null).iterator();
        while (it.hasNext()) {
            sb.append((String) it.next());
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "sb.toString()");
        return sb2;
    }

    /* compiled from: BluetoothHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J+\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/BluetoothHelper$BluetoothInfo;", "", "destination", "", "bluetoothMac", "bluetoothName", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBluetoothMac", "()Ljava/lang/String;", "setBluetoothMac", "(Ljava/lang/String;)V", "getBluetoothName", "setBluetoothName", "getDestination", "setDestination", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final /* data */ class BluetoothInfo {
        private String bluetoothMac;
        private String bluetoothName;
        private String destination;

        public static /* synthetic */ BluetoothInfo copy$default(BluetoothInfo bluetoothInfo, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = bluetoothInfo.destination;
            }
            if ((i & 2) != 0) {
                str2 = bluetoothInfo.bluetoothMac;
            }
            if ((i & 4) != 0) {
                str3 = bluetoothInfo.bluetoothName;
            }
            return bluetoothInfo.copy(str, str2, str3);
        }

        /* renamed from: component1, reason: from getter */
        public final String getDestination() {
            return this.destination;
        }

        /* renamed from: component2, reason: from getter */
        public final String getBluetoothMac() {
            return this.bluetoothMac;
        }

        /* renamed from: component3, reason: from getter */
        public final String getBluetoothName() {
            return this.bluetoothName;
        }

        public final BluetoothInfo copy(String destination, String bluetoothMac, String bluetoothName) {
            Intrinsics.checkParameterIsNotNull(bluetoothMac, "bluetoothMac");
            return new BluetoothInfo(destination, bluetoothMac, bluetoothName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BluetoothInfo)) {
                return false;
            }
            BluetoothInfo bluetoothInfo = (BluetoothInfo) other;
            return Intrinsics.areEqual(this.destination, bluetoothInfo.destination) && Intrinsics.areEqual(this.bluetoothMac, bluetoothInfo.bluetoothMac) && Intrinsics.areEqual(this.bluetoothName, bluetoothInfo.bluetoothName);
        }

        public int hashCode() {
            String str = this.destination;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.bluetoothMac;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.bluetoothName;
            return hashCode2 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "BluetoothInfo(destination=" + this.destination + ", bluetoothMac=" + this.bluetoothMac + ", bluetoothName=" + this.bluetoothName + ")";
        }

        public BluetoothInfo(String str, String bluetoothMac, String str2) {
            Intrinsics.checkParameterIsNotNull(bluetoothMac, "bluetoothMac");
            this.destination = str;
            this.bluetoothMac = bluetoothMac;
            this.bluetoothName = str2;
        }

        public /* synthetic */ BluetoothInfo(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? (String) null : str, str2, (i & 4) != 0 ? (String) null : str3);
        }

        public final String getDestination() {
            return this.destination;
        }

        public final void setDestination(String str) {
            this.destination = str;
        }

        public final String getBluetoothMac() {
            return this.bluetoothMac;
        }

        public final void setBluetoothMac(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.bluetoothMac = str;
        }

        public final String getBluetoothName() {
            return this.bluetoothName;
        }

        public final void setBluetoothName(String str) {
            this.bluetoothName = str;
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.AdapterLifecycleObserver, com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    public void destroy() {
        super.destroy();
        try {
            PdBluetoothManager.INSTANCE.release();
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "PdBluetoothManager.release().error " + Log.getStackTraceString(e));
        }
    }
}
