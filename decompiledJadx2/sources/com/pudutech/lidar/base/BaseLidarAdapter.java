package com.pudutech.lidar.base;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarAdapterCallback;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.LidarVersion;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: BaseLidarAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0083\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b*\u0001=\b&\u0018\u0000 _2\u00020\u0001:\u0002_`B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020\nH\u0002J\b\u0010Q\u001a\u00020\"H&J$\u0010R\u001a\u00020O2\b\b\u0002\u0010%\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010\u0011\u001a\u00020\nJ$\u0010S\u001a\u00020O2\b\b\u0002\u0010L\u001a\u00020\n2\b\b\u0002\u0010\u0016\u001a\u00020\n2\b\b\u0002\u0010\u0014\u001a\u00020\nJ\u001c\u0010T\u001a\b\u0012\u0004\u0012\u00020V0U2\f\u0010W\u001a\b\u0012\u0004\u0012\u00020V0UH\u0002J\b\u0010X\u001a\u00020OH&J\b\u0010Y\u001a\u00020OH&J\b\u0010Z\u001a\u00020OH&J\b\u0010[\u001a\u00020OH&J\u001d\u0010\\\u001a\u00020\"*\u00020\n2\u0006\u0010]\u001a\u00020\n2\u0006\u0010^\u001a\u00020\nH\u0082\bR\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\rX\u0084D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u001e\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0014\u0010\u0018\u001a\u00020\u0019X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010#\u001a\u00020\"2\u0006\u0010\u0010\u001a\u00020\"@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001e\u0010%\u001a\u00020\"2\u0006\u0010\u0010\u001a\u00020\"@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b%\u0010$R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010*\u001a\u00020)2\u0006\u0010\u0010\u001a\u00020)@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0014\u0010-\u001a\u00020\u0007X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0014\u00100\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R$\u00104\u001a\u00020\"2\u0006\u00103\u001a\u00020\"@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010$\"\u0004\b6\u00107R\u0014\u00108\u001a\u000209X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0010\u0010<\u001a\u00020=X\u0082\u0004¢\u0006\u0004\n\u0002\u0010>R\u000e\u0010?\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u000209X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bA\u0010BR\"\u0010C\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR$\u0010I\u001a\u00020\"2\u0006\u00103\u001a\u00020\"@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010$\"\u0004\bK\u00107R\u001e\u0010L\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bM\u0010\u0013¨\u0006a"}, m3961d2 = {"Lcom/pudutech/lidar/base/BaseLidarAdapter;", "", "context", "Landroid/content/Context;", "version", "Lcom/pudutech/lidar/LidarVersion;", "callback", "Lcom/pudutech/lidar/LidarAdapterCallback;", "(Landroid/content/Context;Lcom/pudutech/lidar/LidarVersion;Lcom/pudutech/lidar/LidarAdapterCallback;)V", "MATH_2PI", "", "MAX_DIFF", "TAG", "", "getTAG", "()Ljava/lang/String;", "<set-?>", "angleCalibrationDrift", "getAngleCalibrationDrift", "()D", "angleEnd", "getAngleEnd", "angleStart", "getAngleStart", "baseLidarListener", "Lcom/pudutech/lidar/base/LidarListener;", "getBaseLidarListener", "()Lcom/pudutech/lidar/base/LidarListener;", "controlHandler", "Landroid/os/Handler;", "getControlHandler", "()Landroid/os/Handler;", "history", "Lcom/pudutech/lidar/base/BaseLidarAdapter$History;", "", "isInstallUpSizeDown", "()Z", "isOriginClockwise", "lastErrorTimestamp", "", "lastReceiveTimestamp", "", "legalCnt", "getLegalCnt", "()I", "lidarAdapterCallback", "getLidarAdapterCallback", "()Lcom/pudutech/lidar/LidarAdapterCallback;", "mContext", "getMContext", "()Landroid/content/Context;", ES6Iterator.VALUE_PROPERTY, "needSelfCheckByInnerScan", "getNeedSelfCheckByInnerScan", "setNeedSelfCheckByInnerScan", "(Z)V", "parseDataThread", "Landroid/os/HandlerThread;", "getParseDataThread", "()Landroid/os/HandlerThread;", "process", "com/pudutech/lidar/base/BaseLidarAdapter$process$1", "Lcom/pudutech/lidar/base/BaseLidarAdapter$process$1;", "processHandler", "processThread", "getVersion", "()Lcom/pudutech/lidar/LidarVersion;", "viewHandler", "Ljava/lang/ref/WeakReference;", "getViewHandler", "()Ljava/lang/ref/WeakReference;", "setViewHandler", "(Ljava/lang/ref/WeakReference;)V", "viewerOriginDataSwitcher", "getViewerOriginDataSwitcher", "setViewerOriginDataSwitcher", "zeroAngle", "getZeroAngle", "checkEndAngle", "", "endAngle", "checkLidarServiceOK", "configInstall", "configOutput", "handle", "", "Lcom/pudutech/lidar/LidarNode;", "list", "startLidarService", "startScan", "stopLidarService", "stopScan", "checkInAngleRang", "start", "end", "Companion", "History", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public abstract class BaseLidarAdapter {
    private final double MATH_2PI;
    private final double MAX_DIFF;
    private final String TAG;
    private double angleCalibrationDrift;
    private double angleEnd;
    private double angleStart;
    private final LidarListener baseLidarListener;
    private final Handler controlHandler;
    private final History history;
    private boolean isInstallUpSizeDown;
    private boolean isOriginClockwise;
    private long lastErrorTimestamp;
    private long lastReceiveTimestamp;
    private int legalCnt;
    private final LidarAdapterCallback lidarAdapterCallback;
    private final Context mContext;
    private boolean needSelfCheckByInnerScan;
    private final HandlerThread parseDataThread;
    private final BaseLidarAdapter$process$1 process;
    private final Handler processHandler;
    private final HandlerThread processThread;
    private final LidarVersion version;
    private WeakReference<Handler> viewHandler;
    private boolean viewerOriginDataSwitcher;
    private double zeroAngle;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int ONE_FRAME_COMPLETED_FLAG = 1;
    private static final int ERROR_FLAG = 2;
    private static final int ONE_FRAME_LEGAL_CNT_AT_LEAST = 5;
    private static final double LEGAL_DISTANCE_M = 0.05d;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[LidarVersion.values().length];

        static {
            $EnumSwitchMapping$0[LidarVersion.MID_70.ordinal()] = 1;
        }
    }

    public abstract boolean checkLidarServiceOK();

    public abstract void startLidarService();

    public abstract void startScan();

    public abstract void stopLidarService();

    public abstract void stopScan();

    /* JADX WARN: Type inference failed for: r2v1, types: [com.pudutech.lidar.base.BaseLidarAdapter$process$1] */
    public BaseLidarAdapter(Context context, LidarVersion version, final LidarAdapterCallback lidarAdapterCallback) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(version, "version");
        this.version = version;
        this.TAG = "LidarAdapter";
        this.parseDataThread = new HandlerThread("LidarControlAndParseThread");
        this.processThread = new HandlerThread("LidarProcessThread");
        this.mContext = context;
        this.process = new Handler.Callback() { // from class: com.pudutech.lidar.base.BaseLidarAdapter$process$1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message msg) {
                List<? extends LidarNode> handle;
                Handler handler;
                List<? extends LidarNode> list;
                if (msg == null) {
                    return false;
                }
                if (msg.what == BaseLidarAdapter.INSTANCE.getONE_FRAME_COMPLETED_FLAG()) {
                    Object obj = msg.obj;
                    if (obj == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.pudutech.lidar.LidarNode>");
                    }
                    ArrayList arrayList = (List) obj;
                    List list2 = arrayList;
                    if (!list2.isEmpty()) {
                        BaseLidarAdapter.this.checkEndAngle(((LidarNode) CollectionsKt.last(arrayList)).angleInRad);
                    }
                    if (BaseLidarAdapter.this.getViewHandler() != null && BaseLidarAdapter.this.getViewerOriginDataSwitcher() && (!list2.isEmpty())) {
                        int size = arrayList.size();
                        ArrayList arrayList2 = new ArrayList(size);
                        for (int i = 0; i < size; i++) {
                            LidarNode lidarNode = new LidarNode();
                            lidarNode.angleInRad = ((LidarNode) arrayList.get(i)).angleInRad;
                            lidarNode.distance_m = ((LidarNode) arrayList.get(i)).distance_m;
                            arrayList2.add(lidarNode);
                        }
                        arrayList = arrayList2;
                    }
                    handle = BaseLidarAdapter.this.handle(arrayList);
                    WeakReference<Handler> viewHandler = BaseLidarAdapter.this.getViewHandler();
                    if (viewHandler != null && (handler = viewHandler.get()) != null) {
                        if (BaseLidarAdapter.this.getViewerOriginDataSwitcher()) {
                            Object obj2 = msg.obj;
                            if (obj2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.pudutech.lidar.LidarNode>");
                            }
                            list = (List) obj2;
                        } else {
                            list = handle;
                        }
                        handler.obtainMessage(BaseLidarAdapter.INSTANCE.getONE_FRAME_COMPLETED_FLAG(), list).sendToTarget();
                    }
                    if (BaseLidarAdapter.this.getLegalCnt() < BaseLidarAdapter.INSTANCE.getONE_FRAME_LEGAL_CNT_AT_LEAST()) {
                        Pdlog.m3274e(BaseLidarAdapter.this.getTAG(), "legal cnt=" + BaseLidarAdapter.this.getLegalCnt() + " < " + BaseLidarAdapter.INSTANCE.getONE_FRAME_LEGAL_CNT_AT_LEAST() + " when legal distance=" + BaseLidarAdapter.INSTANCE.getLEGAL_DISTANCE_M());
                        LidarErrorType lidarErrorType = LidarErrorType.ILLEGAL_FRAME;
                        StringBuilder sb = new StringBuilder();
                        sb.append("legal cnt is ");
                        sb.append(BaseLidarAdapter.this.getLegalCnt());
                        sb.append(". too less");
                        BaseLidarAdapter.this.getLidarAdapterCallback().onError(new LidarError(lidarErrorType, sb.toString()));
                        return true;
                    }
                    BaseLidarAdapter.this.getLidarAdapterCallback().onOneFrame(handle);
                }
                return true;
            }
        };
        this.parseDataThread.start();
        this.controlHandler = new Handler(this.parseDataThread.getLooper());
        this.processThread.start();
        this.processHandler = new Handler(this.processThread.getLooper(), this.process);
        this.MATH_2PI = 6.283185307179586d;
        this.isOriginClockwise = true;
        this.angleEnd = 6.283185307179586d;
        this.needSelfCheckByInnerScan = true;
        this.baseLidarListener = new LidarListener() { // from class: com.pudutech.lidar.base.BaseLidarAdapter$baseLidarListener$1
            @Override // com.pudutech.lidar.base.LidarListener
            public void onOneFrameComplete(List<? extends LidarNode> list) {
                Handler handler;
                Handler handler2;
                Intrinsics.checkParameterIsNotNull(list, "list");
                handler = BaseLidarAdapter.this.processHandler;
                handler.removeMessages(BaseLidarAdapter.INSTANCE.getONE_FRAME_COMPLETED_FLAG());
                handler2 = BaseLidarAdapter.this.processHandler;
                handler2.obtainMessage(BaseLidarAdapter.INSTANCE.getONE_FRAME_COMPLETED_FLAG(), list).sendToTarget();
            }

            @Override // com.pudutech.lidar.base.LidarListener
            public void onProtocolError(String msg) {
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                BaseLidarAdapter.this.getLidarAdapterCallback().onError(new LidarError(LidarErrorType.LIDAR_INNER_ERROR, msg));
            }
        };
        this.lidarAdapterCallback = new LidarAdapterCallback() { // from class: com.pudutech.lidar.base.BaseLidarAdapter$lidarAdapterCallback$1
            @Override // com.pudutech.lidar.LidarAdapterCallback
            public void onPowerRequest(boolean isOn) {
                LidarAdapterCallback lidarAdapterCallback2 = lidarAdapterCallback;
                if (lidarAdapterCallback2 != null) {
                    lidarAdapterCallback2.onPowerRequest(isOn);
                }
            }

            @Override // com.pudutech.lidar.LidarAdapterCallback
            public void onOneFrame(List<? extends LidarNode> list) {
                Intrinsics.checkParameterIsNotNull(list, "list");
                LidarAdapterCallback lidarAdapterCallback2 = lidarAdapterCallback;
                if (lidarAdapterCallback2 != null) {
                    lidarAdapterCallback2.onOneFrame(list);
                }
            }

            @Override // com.pudutech.lidar.LidarAdapterCallback
            public void onError(LidarError errorMsg) {
                Handler handler;
                Intrinsics.checkParameterIsNotNull(errorMsg, "errorMsg");
                LidarAdapterCallback lidarAdapterCallback2 = lidarAdapterCallback;
                if (lidarAdapterCallback2 != null) {
                    lidarAdapterCallback2.onError(errorMsg);
                }
                WeakReference<Handler> viewHandler = BaseLidarAdapter.this.getViewHandler();
                if (viewHandler == null || (handler = viewHandler.get()) == null) {
                    return;
                }
                handler.obtainMessage(BaseLidarAdapter.INSTANCE.getERROR_FLAG(), errorMsg).sendToTarget();
            }
        };
        this.history = new History();
        this.MAX_DIFF = Math.toRadians(60.0d);
    }

    public final LidarVersion getVersion() {
        return this.version;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String getTAG() {
        return this.TAG;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final HandlerThread getParseDataThread() {
        return this.parseDataThread;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Context getMContext() {
        return this.mContext;
    }

    public final Handler getControlHandler() {
        return this.controlHandler;
    }

    public final WeakReference<Handler> getViewHandler() {
        return this.viewHandler;
    }

    public final void setViewHandler(WeakReference<Handler> weakReference) {
        this.viewHandler = weakReference;
    }

    public final boolean getViewerOriginDataSwitcher() {
        return this.viewerOriginDataSwitcher;
    }

    public final void setViewerOriginDataSwitcher(boolean z) {
        this.viewerOriginDataSwitcher = z;
        Pdlog.m3275i(this.TAG, "set viewerOriginDataSwitcher=" + z);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: BaseLidarAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/lidar/base/BaseLidarAdapter$Companion;", "", "()V", "ERROR_FLAG", "", "getERROR_FLAG", "()I", "LEGAL_DISTANCE_M", "", "getLEGAL_DISTANCE_M", "()D", "ONE_FRAME_COMPLETED_FLAG", "getONE_FRAME_COMPLETED_FLAG", "ONE_FRAME_LEGAL_CNT_AT_LEAST", "getONE_FRAME_LEGAL_CNT_AT_LEAST", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getONE_FRAME_COMPLETED_FLAG() {
            return BaseLidarAdapter.ONE_FRAME_COMPLETED_FLAG;
        }

        public final int getERROR_FLAG() {
            return BaseLidarAdapter.ERROR_FLAG;
        }

        public final int getONE_FRAME_LEGAL_CNT_AT_LEAST() {
            return BaseLidarAdapter.ONE_FRAME_LEGAL_CNT_AT_LEAST;
        }

        public final double getLEGAL_DISTANCE_M() {
            return BaseLidarAdapter.LEGAL_DISTANCE_M;
        }
    }

    /* renamed from: isOriginClockwise, reason: from getter */
    public final boolean getIsOriginClockwise() {
        return this.isOriginClockwise;
    }

    /* renamed from: isInstallUpSizeDown, reason: from getter */
    public final boolean getIsInstallUpSizeDown() {
        return this.isInstallUpSizeDown;
    }

    public final double getAngleCalibrationDrift() {
        return this.angleCalibrationDrift;
    }

    public final double getZeroAngle() {
        return this.zeroAngle;
    }

    public final double getAngleStart() {
        return this.angleStart;
    }

    public final double getAngleEnd() {
        return this.angleEnd;
    }

    public final int getLegalCnt() {
        return this.legalCnt;
    }

    public static /* synthetic */ void configInstall$default(BaseLidarAdapter baseLidarAdapter, boolean z, boolean z2, double d, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: configInstall");
        }
        if ((i & 1) != 0) {
            z = baseLidarAdapter.isOriginClockwise;
        }
        if ((i & 2) != 0) {
            z2 = baseLidarAdapter.isInstallUpSizeDown;
        }
        if ((i & 4) != 0) {
            d = baseLidarAdapter.angleCalibrationDrift;
        }
        baseLidarAdapter.configInstall(z, z2, d);
    }

    public final void configInstall(boolean isOriginClockwise, boolean isInstallUpSizeDown, double angleCalibrationDrift) {
        Pdlog.m3275i("LidarAdapter", "configInstall isOriginClockwise=" + isOriginClockwise + "  isInstallUpSizeDown=" + isInstallUpSizeDown + "  angleCalibrationDrift=" + angleCalibrationDrift);
        this.isOriginClockwise = isOriginClockwise;
        this.isInstallUpSizeDown = isInstallUpSizeDown;
        this.angleCalibrationDrift = angleCalibrationDrift;
    }

    public static /* synthetic */ void configOutput$default(BaseLidarAdapter baseLidarAdapter, double d, double d2, double d3, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: configOutput");
        }
        if ((i & 1) != 0) {
            d = baseLidarAdapter.zeroAngle;
        }
        double d4 = d;
        if ((i & 2) != 0) {
            d2 = baseLidarAdapter.angleStart;
        }
        double d5 = d2;
        if ((i & 4) != 0) {
            d3 = baseLidarAdapter.angleEnd;
        }
        baseLidarAdapter.configOutput(d4, d5, d3);
    }

    public final void configOutput(double zeroAngle, double angleStart, double angleEnd) {
        Pdlog.m3273d("lds50LidarAdapter", "configOutput zeroAngle=" + zeroAngle + "  angleStart=" + angleStart + "  angleEnd=" + angleEnd);
        this.zeroAngle = zeroAngle;
        this.angleStart = angleStart;
        this.angleEnd = angleEnd;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00bb, code lost:
    
        if (r13 <= r10) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x00d9, code lost:
    
        if (r13 <= r15) goto L65;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List<LidarNode> handle(List<? extends LidarNode> list) {
        int i;
        double d;
        boolean z;
        int i2 = (this.isOriginClockwise ? -1 : 1) * (this.isInstallUpSizeDown ? -1 : 1);
        double d2 = this.angleStart;
        double d3 = this.zeroAngle;
        double d4 = this.angleCalibrationDrift;
        double d5 = d2 + d3 + d4;
        double d6 = this.angleEnd + d3 + d4;
        if (i2 < 0) {
            double d7 = -d6;
            d6 = -d5;
            d5 = d7;
        }
        while (true) {
            i = 0;
            d = 0;
            if (d5 >= d) {
                break;
            }
            d5 += this.MATH_2PI;
        }
        while (true) {
            double d8 = this.MATH_2PI;
            if (d5 <= d8) {
                break;
            }
            d5 -= d8;
        }
        while (d6 < d) {
            d6 += this.MATH_2PI;
        }
        while (true) {
            double d9 = this.MATH_2PI;
            if (d6 <= d9) {
                break;
            }
            d6 -= d9;
        }
        boolean z2 = Math.abs(d5 - d6) < Math.toRadians(1.0E-6d);
        ArrayList arrayList = new ArrayList();
        if (z2) {
            for (LidarNode lidarNode : list) {
                if (lidarNode.distance_m > LEGAL_DISTANCE_M) {
                    i++;
                }
                if (i2 < 0) {
                    lidarNode.angleInRad = this.MATH_2PI - lidarNode.angleInRad;
                }
                lidarNode.angleInRad = (lidarNode.angleInRad - this.zeroAngle) - this.angleCalibrationDrift;
                arrayList.add(lidarNode);
            }
        } else {
            int i3 = 0;
            int i4 = 0;
            for (LidarNode lidarNode2 : list) {
                if (lidarNode2.distance_m > LEGAL_DISTANCE_M) {
                    i3++;
                }
                double d10 = lidarNode2.angleInRad;
                if (d5 < d6) {
                    if (d10 >= d5) {
                    }
                    z = false;
                } else {
                    if (d5 > d6 && (d10 < 0.0d || d10 > d6)) {
                        double d11 = this.MATH_2PI;
                        if (d10 >= d5) {
                        }
                        z = false;
                    }
                    z = true;
                }
                if (z) {
                    if (i2 < 0) {
                        lidarNode2.angleInRad = this.MATH_2PI - lidarNode2.angleInRad;
                    }
                    lidarNode2.angleInRad = (lidarNode2.angleInRad - this.zeroAngle) - this.angleCalibrationDrift;
                    arrayList.add(lidarNode2);
                } else if (lidarNode2.distance_m > LEGAL_DISTANCE_M && lidarNode2.distance_m < 0.3d) {
                    i4++;
                }
            }
            if (i4 < ONE_FRAME_LEGAL_CNT_AT_LEAST && this.needSelfCheckByInnerScan) {
                Pdlog.m3277w(this.TAG, "can't scan the inner construction");
                this.lidarAdapterCallback.onError(new LidarError(LidarErrorType.ILLEGAL_FRAME, "can't scan the inner construction"));
            }
            i = i3;
        }
        this.legalCnt = i;
        return arrayList;
    }

    private final boolean checkInAngleRang(double d, double d2, double d3) {
        if (d2 < d3) {
            return d >= d2 && d <= d3;
        }
        if (d2 <= d3) {
            return true;
        }
        if (d < 0.0d || d > d3) {
            double d4 = this.MATH_2PI;
            if (d < d2 || d > d4) {
                return false;
            }
        }
        return true;
    }

    public final boolean getNeedSelfCheckByInnerScan() {
        return this.needSelfCheckByInnerScan;
    }

    public final void setNeedSelfCheckByInnerScan(boolean z) {
        this.needSelfCheckByInnerScan = z;
        Pdlog.m3273d(this.TAG, "needSelfCheckByInnerScan=" + z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final LidarListener getBaseLidarListener() {
        return this.baseLidarListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final LidarAdapterCallback getLidarAdapterCallback() {
        return this.lidarAdapterCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkEndAngle(double endAngle) {
        if (SystemClock.elapsedRealtime() - this.lastReceiveTimestamp > 1000) {
            this.history.clean();
            this.lastErrorTimestamp = 0L;
        }
        this.lastReceiveTimestamp = SystemClock.elapsedRealtime();
        this.history.put(endAngle);
        if (!this.history.getIsFull() || Math.abs(ArraysKt.average(this.history.getArray()) - endAngle) <= this.MAX_DIFF) {
            return;
        }
        this.history.clean();
        if (SystemClock.elapsedRealtime() - this.lastErrorTimestamp < 5000) {
            Pdlog.m3274e(this.TAG, "angle wrong twice in 5s");
            this.lidarAdapterCallback.onError(new LidarError(LidarErrorType.ILLEGAL_FRAME, "angle wrong twice in 5s"));
        }
        this.lastErrorTimestamp = SystemClock.elapsedRealtime();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: BaseLidarAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\r\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/lidar/base/BaseLidarAdapter$History;", "", "()V", TmpConstant.TYPE_VALUE_ARRAY, "", "getArray", "()[D", "<set-?>", "", "index", "getIndex", "()I", "", "isFull", "()Z", "clean", "", "put", ES6Iterator.VALUE_PROPERTY, "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class History {
        private final double[] array;
        private int index;
        private boolean isFull;

        public History() {
            double[] dArr = new double[10];
            for (int i = 0; i < 10; i++) {
                dArr[i] = 0.0d;
            }
            this.array = dArr;
        }

        public final double[] getArray() {
            return this.array;
        }

        public final int getIndex() {
            return this.index;
        }

        /* renamed from: isFull, reason: from getter */
        public final boolean getIsFull() {
            return this.isFull;
        }

        public final void put(double value) {
            double[] dArr = this.array;
            int i = this.index;
            dArr[i] = value;
            int i2 = i + 1;
            this.index = i2;
            if (i2 == dArr.length) {
                this.isFull = true;
                this.index = 0;
            }
        }

        public final void clean() {
            int i = 0;
            this.isFull = false;
            this.index = 0;
            double[] dArr = this.array;
            int length = dArr.length;
            int i2 = 0;
            while (i < length) {
                double d = dArr[i];
                this.array[i2] = 0.0d;
                i++;
                i2++;
            }
        }
    }
}
