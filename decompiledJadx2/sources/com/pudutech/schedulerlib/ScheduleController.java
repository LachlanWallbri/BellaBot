package com.pudutech.schedulerlib;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import com.google.gson.Gson;
import com.iflytek.aiui.constant.InternalConstant;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.SchCommunicateInfoListener;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.RobotScheduleInfo;
import com.pudutech.mirsdk.hardware.serialize.ScheduleCommunicateInfo;
import com.pudutech.schedulerlib.ScheduleController;
import com.pudutech.schedulerlib.connection.BaseEspConnection;
import com.pudutech.schedulerlib.connection.ESPConnection;
import com.pudutech.schedulerlib.connection.NetworkConnection;
import com.pudutech.schedulerlib.connection.PeanutsEspConnection;
import com.pudutech.schedulerlib.connection.ScheduleMsgReceiveInterface;
import com.pudutech.schedulerlib.scheduleInfo.CurrentRobotInfo;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadPoolDispatcherKt;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: ScheduleController.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000§\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0014*\u0003\u000414\u0018\u0000 `2\u00020\u0001:\u0004`abcB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u00072\u0006\u0010:\u001a\u00020\fJ\b\u0010;\u001a\u00020'H\u0002J\b\u0010<\u001a\u000208H\u0002J\b\u0010=\u001a\u000208H\u0002J\u0006\u0010>\u001a\u000208J\u0010\u0010?\u001a\u0002082\u0006\u0010@\u001a\u00020AH\u0002J\r\u0010B\u001a\u0004\u0018\u00010'¢\u0006\u0002\u0010CJ\u0011\u0010D\u001a\u0004\u0018\u00010\u001dH\u0000¢\u0006\u0004\bE\u0010FJ\b\u0010G\u001a\u0004\u0018\u00010\u0007J\u0006\u0010H\u001a\u00020\u0007J\u000f\u0010I\u001a\u0004\u0018\u00010\u0007H\u0000¢\u0006\u0002\bJJ\u000f\u0010K\u001a\u0004\u0018\u00010\u0007H\u0000¢\u0006\u0002\bLJ\u0016\u0010M\u001a\u0002082\u0006\u0010N\u001a\u00020\u000e2\u0006\u0010O\u001a\u00020PJ\u0018\u0010Q\u001a\u0002082\u0006\u0010N\u001a\u00020\u000e2\u0006\u0010R\u001a\u00020\u001dH\u0002J\u0010\u0010S\u001a\u0002082\u0006\u0010N\u001a\u00020\u000eH\u0002J\u000e\u0010T\u001a\u0002082\u0006\u00109\u001a\u00020\u0007J\u000e\u0010U\u001a\u0002082\u0006\u0010V\u001a\u00020\u001dJ\u0015\u0010W\u001a\u0002082\u0006\u0010X\u001a\u00020\u001dH\u0000¢\u0006\u0002\bYJ\u0010\u0010Z\u001a\u0002082\b\u0010[\u001a\u0004\u0018\u00010AJ\u0015\u0010\\\u001a\u0002082\u0006\u0010]\u001a\u00020'H\u0000¢\u0006\u0002\b^J\u0006\u0010_\u001a\u00020'R\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0013\u001a\u001a\u0012\u0004\u0012\u00020\u0007\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u00150\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0016\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0017j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0019\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0017j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u001c\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001d0\u0017j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001d`\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u000b¢\u0006\b\n\u0000\u001a\u0004\b*\u0010#R\u000e\u0010+\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u000201X\u0082\u000e¢\u0006\u0004\n\u0002\u00102R\u0010\u00103\u001a\u000204X\u0082\u000e¢\u0006\u0004\n\u0002\u00105R*\u00106\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001d0\u0017j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001d`\u0018X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006d"}, m3961d2 = {"Lcom/pudutech/schedulerlib/ScheduleController;", "", "()V", "FPSScope", "com/pudutech/schedulerlib/ScheduleController$FPSScope$1", "Lcom/pudutech/schedulerlib/ScheduleController$FPSScope$1;", "TAG", "", "commInfo", "Lcom/pudutech/mirsdk/hardware/serialize/ScheduleCommunicateInfo;", "commListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/hardware/SchCommunicateInfoListener;", "context", "Landroid/content/Context;", "currentMsgId", "", "debuger", "Ljava/util/concurrent/atomic/AtomicBoolean;", "duplicateMap", "", "Lkotlin/Pair;", "errESP", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "errUDP", "espConnection", "Lcom/pudutech/schedulerlib/connection/BaseEspConnection;", "espFps", "", "fpsJob", "Lkotlinx/coroutines/Job;", "fspListener", "Lcom/pudutech/schedulerlib/ScheduleController$FPSCallback;", "getFspListener$schedulerlib_release", "()Lcom/pudutech/base/architecture/ThreadSafeListener;", "gson", "Lcom/google/gson/Gson;", "hasInit", "", "infoListener", "Lcom/pudutech/schedulerlib/ScheduleController$ScheInfoCallback;", "getInfoListener", "isConnected", "mChannel", "map_md5", "networkConnection", "Lcom/pudutech/schedulerlib/connection/NetworkConnection;", "onESPConnectStateListener", "com/pudutech/schedulerlib/ScheduleController$onESPConnectStateListener$1", "Lcom/pudutech/schedulerlib/ScheduleController$onESPConnectStateListener$1;", "onUDPConnectStateListener", "com/pudutech/schedulerlib/ScheduleController$onUDPConnectStateListener$1", "Lcom/pudutech/schedulerlib/ScheduleController$onUDPConnectStateListener$1;", "udpFps", "addInfoListener", "", "name", "lisener", "checkWifi", "closeESP", "closeUDP", "destroyScheduler", "dispatchMsg", "otherInfo", "Lcom/pudutech/mirsdk/hardware/serialize/RobotScheduleInfo;", "espIsConnected", "()Ljava/lang/Boolean;", "getCurrentChannel", "getCurrentChannel$schedulerlib_release", "()Ljava/lang/Integer;", "getEspVersion", "getGit", "getMapFlag", "getMapFlag$schedulerlib_release", "getMulticastAddress", "getMulticastAddress$schedulerlib_release", "init", InternalConstant.KEY_APP, "esp", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$ESP32Type;", "openESP", "esp_version", "openUDP", "removeInfoListener", "resetBaudRate", "baud", "resetChannel", "chl", "resetChannel$schedulerlib_release", "sendMsg", "info", "setDebuger", "sta", "setDebuger$schedulerlib_release", "udpIsConnected", "Companion", "FPSCallback", "OnConnectStateListener", "ScheInfoCallback", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class ScheduleController {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Object LOCK = new Object();
    private static final Lazy instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<ScheduleController>() { // from class: com.pudutech.schedulerlib.ScheduleController$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ScheduleController invoke() {
            return new ScheduleController(null);
        }
    });
    private final ScheduleController$FPSScope$1 FPSScope;
    private final String TAG;
    private final ScheduleCommunicateInfo commInfo;
    private final ThreadSafeListener<SchCommunicateInfoListener> commListener;
    private Context context;
    private long currentMsgId;
    private final AtomicBoolean debuger;
    private volatile Map<String, Pair<Long, Long>> duplicateMap;
    private final LinkedHashMap<String, String> errESP;
    private final LinkedHashMap<String, String> errUDP;
    private BaseEspConnection espConnection;
    private final LinkedHashMap<String, Integer> espFps;
    private Job fpsJob;
    private final ThreadSafeListener<FPSCallback> fspListener;
    private final Gson gson;
    private boolean hasInit;
    private final ThreadSafeListener<ScheInfoCallback> infoListener;
    private AtomicBoolean isConnected;
    private int mChannel;
    private String map_md5;
    private final NetworkConnection networkConnection;
    private ScheduleController$onESPConnectStateListener$1 onESPConnectStateListener;
    private ScheduleController$onUDPConnectStateListener$1 onUDPConnectStateListener;
    private final LinkedHashMap<String, Integer> udpFps;

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* compiled from: ScheduleController.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0080\u0001\u0010\u0002\u001a\u00020\u00032:\u0010\u0004\u001a6\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u00070\u0005j\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u0007`\t2:\u0010\n\u001a6\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00070\u0005j\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0007`\tH&¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/schedulerlib/ScheduleController$FPSCallback;", "", "updateFPS", "", "fps", "Ljava/util/LinkedHashMap;", "", "", "", "Lkotlin/collections/LinkedHashMap;", "eps", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public interface FPSCallback {
        void updateFPS(LinkedHashMap<String, Map<String, Integer>> fps, LinkedHashMap<String, Map<String, String>> eps);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* compiled from: ScheduleController.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/schedulerlib/ScheduleController$OnConnectStateListener;", "", "onFailure", "", "onSuccessful", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public interface OnConnectStateListener {
        void onFailure();

        void onSuccessful();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* compiled from: ScheduleController.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/schedulerlib/ScheduleController$ScheInfoCallback;", "", "otherSchInfo", "", "info", "Lcom/pudutech/mirsdk/hardware/serialize/RobotScheduleInfo;", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public interface ScheInfoCallback {
        void otherSchInfo(RobotScheduleInfo info);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MachineInfo.ESP32Type.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[MachineInfo.ESP32Type.EasyNode.ordinal()] = 1;
            $EnumSwitchMapping$0[MachineInfo.ESP32Type.SingleDevice.ordinal()] = 2;
            $EnumSwitchMapping$0[MachineInfo.ESP32Type.IntegrationSample.ordinal()] = 3;
            $EnumSwitchMapping$0[MachineInfo.ESP32Type.IntegrationFactory.ordinal()] = 4;
        }
    }

    public final String getGit() {
        return "{\"Schedule\":\"commit: 112aa4d, current user: wuminghao, push auth: 许炜槟<xuweibin@pudutech.com>, git status: On branch devYour branch is up to date with 'origin/dev'.nothing to commit, working tree clean build time: 2021-02-11 22:02diff encode string:  time: Fri Jun 11 21:43:46 2021 +0800\"}";
    }

    /* JADX WARN: Type inference failed for: r0v15, types: [com.pudutech.schedulerlib.ScheduleController$FPSScope$1] */
    /* JADX WARN: Type inference failed for: r0v16, types: [com.pudutech.schedulerlib.ScheduleController$onUDPConnectStateListener$1] */
    /* JADX WARN: Type inference failed for: r0v17, types: [com.pudutech.schedulerlib.ScheduleController$onESPConnectStateListener$1] */
    private ScheduleController() {
        this.TAG = "ScheduleController";
        this.networkConnection = new NetworkConnection();
        this.commListener = new ThreadSafeListener<>();
        this.gson = new Gson();
        this.espFps = new LinkedHashMap<>();
        this.udpFps = new LinkedHashMap<>();
        this.errESP = new LinkedHashMap<>();
        this.errUDP = new LinkedHashMap<>();
        this.commInfo = new ScheduleCommunicateInfo();
        this.debuger = new AtomicBoolean(false);
        this.mChannel = 2;
        this.duplicateMap = new LinkedHashMap();
        this.fspListener = new ThreadSafeListener<>();
        this.infoListener = new ThreadSafeListener<>();
        this.FPSScope = new CoroutineScope() { // from class: com.pudutech.schedulerlib.ScheduleController$FPSScope$1
            private final CoroutineContext coroutineContext = ThreadPoolDispatcherKt.newSingleThreadContext("ScheduleFPS");

            @Override // kotlinx.coroutines.CoroutineScope
            public CoroutineContext getCoroutineContext() {
                return this.coroutineContext;
            }
        };
        this.onUDPConnectStateListener = new OnConnectStateListener() { // from class: com.pudutech.schedulerlib.ScheduleController$onUDPConnectStateListener$1
            @Override // com.pudutech.schedulerlib.ScheduleController.OnConnectStateListener
            public void onSuccessful() {
                AtomicBoolean atomicBoolean;
                AtomicBoolean atomicBoolean2;
                atomicBoolean = ScheduleController.this.isConnected;
                if (atomicBoolean.get()) {
                    return;
                }
                atomicBoolean2 = ScheduleController.this.isConnected;
                atomicBoolean2.set(true);
            }

            @Override // com.pudutech.schedulerlib.ScheduleController.OnConnectStateListener
            public void onFailure() {
                String str;
                str = ScheduleController.this.TAG;
                Pdlog.m3274e(str, "udp connect failure.");
                ScheduleController.this.hasInit = false;
            }
        };
        this.onESPConnectStateListener = new OnConnectStateListener() { // from class: com.pudutech.schedulerlib.ScheduleController$onESPConnectStateListener$1
            @Override // com.pudutech.schedulerlib.ScheduleController.OnConnectStateListener
            public void onSuccessful() {
                String str;
                int i;
                BaseEspConnection baseEspConnection;
                AtomicBoolean atomicBoolean;
                AtomicBoolean atomicBoolean2;
                int i2;
                str = ScheduleController.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onESPConnectStateListener onSuccessful mChannel=");
                i = ScheduleController.this.mChannel;
                sb.append(i);
                Pdlog.m3273d(str, sb.toString());
                ScheduleController.this.hasInit = true;
                baseEspConnection = ScheduleController.this.espConnection;
                if (baseEspConnection != null) {
                    i2 = ScheduleController.this.mChannel;
                    baseEspConnection.resetChannel(i2);
                }
                atomicBoolean = ScheduleController.this.isConnected;
                if (atomicBoolean.get()) {
                    return;
                }
                atomicBoolean2 = ScheduleController.this.isConnected;
                atomicBoolean2.set(true);
            }

            @Override // com.pudutech.schedulerlib.ScheduleController.OnConnectStateListener
            public void onFailure() {
                String str;
                str = ScheduleController.this.TAG;
                Pdlog.m3274e(str, "esp connect failure.");
            }
        };
        this.isConnected = new AtomicBoolean(false);
    }

    public /* synthetic */ ScheduleController(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final ThreadSafeListener<FPSCallback> getFspListener$schedulerlib_release() {
        return this.fspListener;
    }

    public final ThreadSafeListener<ScheInfoCallback> getInfoListener() {
        return this.infoListener;
    }

    public final void init(Context app, MachineInfo.ESP32Type esp) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(app, "app");
        Intrinsics.checkParameterIsNotNull(esp, "esp");
        Pdlog.m3273d(this.TAG, "ScheduleController init " + UByte.m4563toStringimpl(esp.getId()));
        if (this.hasInit) {
            Pdlog.m3273d(this.TAG, "ScheduleController has inited ");
            return;
        }
        this.context = app;
        openUDP(app);
        int i = WhenMappings.$EnumSwitchMapping$0[esp.ordinal()];
        if (i == 1) {
            this.espConnection = new PeanutsEspConnection();
            openESP(app, esp.getId() & 255);
        } else if (i == 2 || i == 3 || i == 4) {
            this.espConnection = new ESPConnection();
            openESP(app, esp.getId() & 255);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.FPSScope, null, null, new ScheduleController$init$1(this, null), 3, null);
        this.fpsJob = launch$default;
    }

    public final void addInfoListener(String name, SchCommunicateInfoListener lisener) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lisener, "lisener");
        this.commListener.add(name, lisener);
    }

    public final void removeInfoListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.commListener.remove(name);
    }

    public final void setDebuger$schedulerlib_release(boolean sta) {
        this.debuger.set(sta);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* compiled from: ScheduleController.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/schedulerlib/ScheduleController$Companion;", "", "()V", "LOCK", "instance", "Lcom/pudutech/schedulerlib/ScheduleController;", "getInstance", "()Lcom/pudutech/schedulerlib/ScheduleController;", "instance$delegate", "Lkotlin/Lazy;", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class Companion {
        public final ScheduleController getInstance() {
            Lazy lazy = ScheduleController.instance$delegate;
            Companion companion = ScheduleController.INSTANCE;
            return (ScheduleController) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0137 A[Catch: all -> 0x003a, TryCatch #1 {, blocks: (B:5:0x0005, B:8:0x0020, B:9:0x0071, B:10:0x007f, B:12:0x0085, B:15:0x00a4, B:20:0x00a8, B:24:0x0137, B:25:0x015d, B:30:0x00b7, B:33:0x00c4, B:35:0x00d0, B:36:0x00d3, B:39:0x00e8, B:41:0x010e, B:42:0x0111, B:46:0x003e), top: B:4:0x0005, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void dispatchMsg(final RobotScheduleInfo otherInfo) {
        synchronized (LOCK) {
            boolean z = false;
            try {
                String str = this.TAG;
                Object[] objArr = new Object[2];
                StringBuilder sb = new StringBuilder();
                sb.append(otherInfo.getMsg_type().equals(C3898x.f4338g) ? "esp" : "net");
                sb.append(" otherInfo:");
                objArr[0] = sb.toString();
                objArr[1] = this.gson.toJson(otherInfo);
                Pdlog.m3273d(str, objArr);
            } catch (Exception e) {
                String str2 = this.TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("otherInfo with gson exception ");
                sb2.append(e.getLocalizedMessage());
                sb2.append(' ');
                StackTraceElement[] stackTrace = e.getStackTrace();
                Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
                sb2.append(ArraysKt.contentDeepToString(stackTrace));
                Pdlog.m3277w(str2, sb2.toString());
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Iterator<Map.Entry<String, Pair<Long, Long>>> it = this.duplicateMap.entrySet().iterator();
            while (it.hasNext()) {
                if (elapsedRealtime - it.next().getValue().getSecond().longValue() > 1000) {
                    it.remove();
                }
            }
            if (this.duplicateMap.containsKey(otherInfo.getRobot_id()) && this.duplicateMap.get(otherInfo.getRobot_id()) != null) {
                Pair<Long, Long> pair = this.duplicateMap.get(otherInfo.getRobot_id());
                if (pair == null) {
                    Intrinsics.throwNpe();
                }
                if (pair.getFirst().longValue() >= otherInfo.getMsg_id()) {
                    String str3 = this.TAG;
                    Object[] objArr2 = new Object[1];
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("robot ");
                    sb3.append(otherInfo.getRobot_id());
                    sb3.append(" duplicate id ");
                    Pair<Long, Long> pair2 = this.duplicateMap.get(otherInfo.getRobot_id());
                    if (pair2 == null) {
                        Intrinsics.throwNpe();
                    }
                    sb3.append(pair2.getFirst().longValue());
                    sb3.append(" receive id ");
                    sb3.append(otherInfo.getMsg_id());
                    objArr2[0] = sb3.toString();
                    Pdlog.m3273d(str3, objArr2);
                    if (z) {
                        this.infoListener.notify(new Function2<ScheInfoCallback, String, Unit>() { // from class: com.pudutech.schedulerlib.ScheduleController$dispatchMsg$$inlined$synchronized$lambda$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(ScheduleController.ScheInfoCallback scheInfoCallback, String str4) {
                                invoke2(scheInfoCallback, str4);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(ScheduleController.ScheInfoCallback it2, String str4) {
                                Intrinsics.checkParameterIsNotNull(it2, "it");
                                Intrinsics.checkParameterIsNotNull(str4, "<anonymous parameter 1>");
                                it2.otherSchInfo(otherInfo);
                            }
                        });
                        this.duplicateMap.put(otherInfo.getRobot_id(), new Pair<>(Long.valueOf(otherInfo.getMsg_id()), Long.valueOf(elapsedRealtime)));
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            z = true;
            if (z) {
            }
            Unit unit2 = Unit.INSTANCE;
        }
    }

    private final void openESP(Context app, int esp_version) {
        SharedPreferences sharedPreferences = app.getSharedPreferences("mirsdk", 0);
        int i = sharedPreferences.getInt(ScheduleConstant.PREFERENCE_KEY, -1);
        if (i == -1) {
            i = SpUtils.get(app, ScheduleConstant.PREFERENCE_KEY, 1);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt(ScheduleConstant.PREFERENCE_KEY, i);
            edit.apply();
        }
        this.mChannel = i;
        Pdlog.m3273d(this.TAG, "openESP mChannel=" + this.mChannel);
        Boolean espIsConnected = espIsConnected();
        if (espIsConnected == null) {
            Intrinsics.throwNpe();
        }
        if (espIsConnected.booleanValue()) {
            Pdlog.m3273d(this.TAG, "openESP has connected---->only to set channel");
            BaseEspConnection baseEspConnection = this.espConnection;
            if (baseEspConnection != null) {
                baseEspConnection.resetChannel(i);
                return;
            }
            return;
        }
        BaseEspConnection baseEspConnection2 = this.espConnection;
        if (baseEspConnection2 != null) {
            baseEspConnection2.setMsgCallBack(new ScheduleMsgReceiveInterface() { // from class: com.pudutech.schedulerlib.ScheduleController$openESP$1
                @Override // com.pudutech.schedulerlib.connection.ScheduleMsgReceiveInterface
                public void decodeMsg(byte[] msg) {
                    String str;
                    LinkedHashMap linkedHashMap;
                    LinkedHashMap linkedHashMap2;
                    String str2;
                    String str3;
                    String str4;
                    LinkedHashMap linkedHashMap3;
                    LinkedHashMap linkedHashMap4;
                    LinkedHashMap linkedHashMap5;
                    String str5;
                    LinkedHashMap linkedHashMap6;
                    LinkedHashMap linkedHashMap7;
                    LinkedHashMap linkedHashMap8;
                    Intrinsics.checkParameterIsNotNull(msg, "msg");
                    RobotScheduleInfo unpackageScheduleMsg = SchedulePackageProcess.INSTANCE.unpackageScheduleMsg(msg);
                    if (unpackageScheduleMsg != null) {
                        str2 = ScheduleController.this.map_md5;
                        if (str2 != null) {
                            str5 = ScheduleController.this.map_md5;
                            Boolean valueOf = str5 != null ? Boolean.valueOf(str5.equals(unpackageScheduleMsg.getMap_flag())) : null;
                            if (valueOf == null) {
                                Intrinsics.throwNpe();
                            }
                            if (valueOf.booleanValue()) {
                                ScheduleController.this.dispatchMsg(unpackageScheduleMsg);
                                linkedHashMap6 = ScheduleController.this.espFps;
                                synchronized (linkedHashMap6) {
                                    linkedHashMap7 = ScheduleController.this.espFps;
                                    LinkedHashMap linkedHashMap9 = linkedHashMap7;
                                    String robot_id = unpackageScheduleMsg.getRobot_id();
                                    linkedHashMap8 = ScheduleController.this.espFps;
                                    Integer num = (Integer) linkedHashMap8.get(unpackageScheduleMsg.getRobot_id());
                                    linkedHashMap9.put(robot_id, Integer.valueOf(num != null ? 1 + num.intValue() : 1));
                                    Unit unit = Unit.INSTANCE;
                                }
                                return;
                            }
                        }
                        str3 = ScheduleController.this.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("map does not match: current map flag ");
                        str4 = ScheduleController.this.map_md5;
                        sb.append(str4);
                        sb.append(", robot ");
                        sb.append(unpackageScheduleMsg.getRobot_id());
                        sb.append("'s map flag ");
                        sb.append(unpackageScheduleMsg.getMap_flag());
                        Pdlog.m3273d(str3, sb.toString());
                        linkedHashMap3 = ScheduleController.this.errESP;
                        synchronized (linkedHashMap3) {
                            if (unpackageScheduleMsg.getMap_flag().length() > 8) {
                                linkedHashMap5 = ScheduleController.this.errESP;
                                String robot_id2 = unpackageScheduleMsg.getRobot_id();
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("地图不匹配：");
                                String map_flag = unpackageScheduleMsg.getMap_flag();
                                if (map_flag == null) {
                                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                                }
                                String substring = map_flag.substring(0, 4);
                                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                sb2.append(substring);
                                sb2.append("....");
                                String map_flag2 = unpackageScheduleMsg.getMap_flag();
                                int length = unpackageScheduleMsg.getMap_flag().length() - 4;
                                if (map_flag2 != null) {
                                    String substring2 = map_flag2.substring(length);
                                    Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
                                    sb2.append(substring2);
                                } else {
                                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                                }
                            } else {
                                linkedHashMap4 = ScheduleController.this.errESP;
                            }
                        }
                        return;
                    }
                    str = ScheduleController.this.TAG;
                    Pdlog.m3273d(str, "other info is null");
                    linkedHashMap = ScheduleController.this.espFps;
                    synchronized (linkedHashMap) {
                        linkedHashMap2 = ScheduleController.this.errESP;
                    }
                }
            });
        }
        BaseEspConnection baseEspConnection3 = this.espConnection;
        if (baseEspConnection3 != null) {
            baseEspConnection3.openESPDevice(this.onESPConnectStateListener, esp_version);
        }
        if (esp_version != (MachineInfo.ESP32Type.EasyNode.getId() & 255)) {
            Pdlog.m3273d(this.TAG, "other device to set channel");
            BaseEspConnection baseEspConnection4 = this.espConnection;
            if (baseEspConnection4 != null) {
                baseEspConnection4.resetChannel(i);
            }
        }
    }

    private final void openUDP(Context app) {
        Pdlog.m3273d(this.TAG, "open udp");
        if (udpIsConnected()) {
            return;
        }
        this.networkConnection.setUdpMsgDecodeCallBack$schedulerlib_release(new ScheduleMsgReceiveInterface() { // from class: com.pudutech.schedulerlib.ScheduleController$openUDP$1
            @Override // com.pudutech.schedulerlib.connection.ScheduleMsgReceiveInterface
            public void decodeMsg(byte[] msg) {
                String str;
                LinkedHashMap linkedHashMap;
                LinkedHashMap linkedHashMap2;
                String str2;
                String str3;
                String str4;
                LinkedHashMap linkedHashMap3;
                LinkedHashMap linkedHashMap4;
                LinkedHashMap linkedHashMap5;
                String str5;
                LinkedHashMap linkedHashMap6;
                LinkedHashMap linkedHashMap7;
                LinkedHashMap linkedHashMap8;
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                RobotScheduleInfo unpackageScheduleMsg = SchedulePackageProcess.INSTANCE.unpackageScheduleMsg(msg);
                if (unpackageScheduleMsg != null) {
                    str2 = ScheduleController.this.map_md5;
                    if (str2 != null) {
                        str5 = ScheduleController.this.map_md5;
                        Boolean valueOf = str5 != null ? Boolean.valueOf(str5.equals(unpackageScheduleMsg.getMap_flag())) : null;
                        if (valueOf == null) {
                            Intrinsics.throwNpe();
                        }
                        if (valueOf.booleanValue()) {
                            ScheduleController.this.dispatchMsg(unpackageScheduleMsg);
                            linkedHashMap6 = ScheduleController.this.udpFps;
                            synchronized (linkedHashMap6) {
                                linkedHashMap7 = ScheduleController.this.udpFps;
                                LinkedHashMap linkedHashMap9 = linkedHashMap7;
                                String robot_id = unpackageScheduleMsg.getRobot_id();
                                linkedHashMap8 = ScheduleController.this.udpFps;
                                Integer num = (Integer) linkedHashMap8.get(unpackageScheduleMsg.getRobot_id());
                                linkedHashMap9.put(robot_id, Integer.valueOf(num != null ? 1 + num.intValue() : 1));
                                Unit unit = Unit.INSTANCE;
                            }
                            return;
                        }
                    }
                    str3 = ScheduleController.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("map does not match: current map flag ");
                    str4 = ScheduleController.this.map_md5;
                    sb.append(str4);
                    sb.append(", robot ");
                    sb.append(unpackageScheduleMsg.getRobot_id());
                    sb.append("'s map flag ");
                    sb.append(unpackageScheduleMsg.getMap_flag());
                    Pdlog.m3273d(str3, sb.toString());
                    linkedHashMap3 = ScheduleController.this.errUDP;
                    synchronized (linkedHashMap3) {
                        if (unpackageScheduleMsg.getMap_flag().length() > 8) {
                            linkedHashMap5 = ScheduleController.this.errUDP;
                            String robot_id2 = unpackageScheduleMsg.getRobot_id();
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("地图不匹配：");
                            String map_flag = unpackageScheduleMsg.getMap_flag();
                            if (map_flag == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            String substring = map_flag.substring(0, 4);
                            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                            sb2.append(substring);
                            sb2.append("....");
                            String map_flag2 = unpackageScheduleMsg.getMap_flag();
                            int length = unpackageScheduleMsg.getMap_flag().length() - 4;
                            if (map_flag2 != null) {
                                String substring2 = map_flag2.substring(length);
                                Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
                                sb2.append(substring2);
                            } else {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                        } else {
                            linkedHashMap4 = ScheduleController.this.errUDP;
                        }
                    }
                    return;
                }
                str = ScheduleController.this.TAG;
                Pdlog.m3273d(str, "other info is null");
                linkedHashMap = ScheduleController.this.udpFps;
                synchronized (linkedHashMap) {
                    linkedHashMap2 = ScheduleController.this.errUDP;
                }
            }
        });
        this.networkConnection.start$schedulerlib_release(app, this.onUDPConnectStateListener);
        Pdlog.m3273d(this.TAG, "finish start udp");
    }

    public final void destroyScheduler() {
        BuildersKt__BuildersKt.runBlocking$default(null, new ScheduleController$destroyScheduler$1(this, null), 1, null);
    }

    public final void resetChannel$schedulerlib_release(int chl) {
        synchronized (LOCK) {
            this.duplicateMap.clear();
            BaseEspConnection baseEspConnection = this.espConnection;
            if (baseEspConnection != null) {
                baseEspConnection.resetChannel(chl);
            }
            this.networkConnection.resetMulticastAddress$schedulerlib_release(chl);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void resetBaudRate(int baud) {
        BaseEspConnection baseEspConnection = this.espConnection;
        if (baseEspConnection != null) {
            baseEspConnection.setBaudRate(baud);
        }
    }

    public final boolean udpIsConnected() {
        return this.networkConnection.getIsRunning();
    }

    public final Boolean espIsConnected() {
        boolean isEspRunning;
        BaseEspConnection baseEspConnection = this.espConnection;
        if (baseEspConnection == null) {
            isEspRunning = false;
        } else {
            if (baseEspConnection == null) {
                return null;
            }
            isEspRunning = baseEspConnection.getIsEspRunning();
        }
        return Boolean.valueOf(isEspRunning);
    }

    public final Integer getCurrentChannel$schedulerlib_release() {
        BaseEspConnection baseEspConnection = this.espConnection;
        if (baseEspConnection != null) {
            return Integer.valueOf(baseEspConnection.getChannel());
        }
        return null;
    }

    public final String getEspVersion() {
        BaseEspConnection baseEspConnection = this.espConnection;
        if (baseEspConnection != null) {
            return baseEspConnection.getEspVersion();
        }
        return null;
    }

    public final String getMulticastAddress$schedulerlib_release() {
        return this.networkConnection.getMulticastAddress$schedulerlib_release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void closeUDP() {
        this.networkConnection.destroy$schedulerlib_release();
        if (udpIsConnected()) {
            return;
        }
        this.isConnected.set(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void closeESP() {
        BaseEspConnection baseEspConnection = this.espConnection;
        if (baseEspConnection != null) {
            baseEspConnection.closeESPDevice();
        }
        Boolean espIsConnected = espIsConnected();
        if (espIsConnected == null) {
            Intrinsics.throwNpe();
        }
        if (!espIsConnected.booleanValue()) {
            this.isConnected.set(false);
        }
        this.hasInit = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkWifi() {
        Context context = this.context;
        Object systemService = context != null ? context.getSystemService("wifi") : null;
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.wifi.WifiManager");
        }
        WifiManager wifiManager = (WifiManager) systemService;
        if (!wifiManager.isWifiEnabled()) {
            return false;
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        Intrinsics.checkExpressionValueIsNotNull(connectionInfo, "mWifiManager.connectionInfo");
        if (connectionInfo.getIpAddress() == 0) {
            return false;
        }
        WifiInfo connectionInfo2 = wifiManager.getConnectionInfo();
        Intrinsics.checkExpressionValueIsNotNull(connectionInfo2, "mWifiManager.connectionInfo");
        return connectionInfo2.getRssi() > -88;
    }

    public final void sendMsg(RobotScheduleInfo info) {
        BaseEspConnection baseEspConnection;
        if (info == null) {
            return;
        }
        String map_flag = info.getMap_flag();
        this.map_md5 = map_flag;
        if (map_flag == null) {
            Pdlog.m3273d(this.TAG, "map flag is null, do not send this msg");
            return;
        }
        if (!this.isConnected.get()) {
            Pdlog.m3273d(this.TAG, "not connected");
            return;
        }
        this.currentMsgId++;
        CurrentRobotInfo currentRobotInfo = new CurrentRobotInfo();
        currentRobotInfo.setData(info);
        currentRobotInfo.getData().setMsg_id(this.currentMsgId);
        try {
            Pdlog.m3273d(this.TAG, "current schedule info", this.gson.toJson(currentRobotInfo));
            Boolean espIsConnected = espIsConnected();
            if (espIsConnected == null) {
                Intrinsics.throwNpe();
            }
            if (espIsConnected.booleanValue()) {
                currentRobotInfo.getData().setMsg_type(C3898x.f4338g);
                byte[] bArr = (byte[]) null;
                try {
                    bArr = SchedulePackageProcess.INSTANCE.packageScheduleMsg(currentRobotInfo);
                } catch (Exception e) {
                    String str = this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Encode Schedule Info error: ");
                    sb.append(e.getLocalizedMessage());
                    sb.append(' ');
                    StackTraceElement[] stackTrace = e.getStackTrace();
                    Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
                    sb.append(ArraysKt.contentDeepToString(stackTrace));
                    Pdlog.m3277w(str, sb.toString());
                }
                if (bArr != null && (baseEspConnection = this.espConnection) != null) {
                    baseEspConnection.sendMsg(bArr);
                }
            }
            if (udpIsConnected()) {
                currentRobotInfo.getData().setMsg_type("u");
                byte[] bArr2 = (byte[]) null;
                try {
                    bArr2 = SchedulePackageProcess.INSTANCE.packageScheduleMsg(currentRobotInfo);
                } catch (Exception e2) {
                    String str2 = this.TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Encode Schedule Info error: ");
                    sb2.append(e2.getLocalizedMessage());
                    sb2.append(' ');
                    StackTraceElement[] stackTrace2 = e2.getStackTrace();
                    Intrinsics.checkExpressionValueIsNotNull(stackTrace2, "e.stackTrace");
                    sb2.append(ArraysKt.contentDeepToString(stackTrace2));
                    Pdlog.m3277w(str2, sb2.toString());
                }
                if (bArr2 != null) {
                    this.networkConnection.sendScheduleMsgFromUdp$schedulerlib_release(bArr2);
                }
            }
        } catch (Exception e3) {
            String str3 = this.TAG;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("schinfo with gson exception ");
            sb3.append(e3.getLocalizedMessage());
            sb3.append(' ');
            StackTraceElement[] stackTrace3 = e3.getStackTrace();
            Intrinsics.checkExpressionValueIsNotNull(stackTrace3, "e.stackTrace");
            sb3.append(ArraysKt.contentDeepToString(stackTrace3));
            Pdlog.m3277w(str3, sb3.toString());
        }
    }

    /* renamed from: getMapFlag$schedulerlib_release, reason: from getter */
    public final String getMap_md5() {
        return this.map_md5;
    }
}
