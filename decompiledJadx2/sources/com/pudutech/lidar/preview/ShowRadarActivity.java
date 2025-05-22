package com.pudutech.lidar.preview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.C4705R;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.ListLidarNodePool;
import com.pudutech.lidar.base.BaseLidarAdapter;
import com.pudutech.lidar.base.CalibrationHelper;
import com.pudutech.lidar.base.LidarError;
import com.pudutech.lidar.base.LidarErrorType;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Typography;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: ShowRadarActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 A2\u00020\u0001:\u0001AB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201J\u0010\u00102\u001a\u00020/2\u0006\u00103\u001a\u00020\u0010H\u0002J\b\u00104\u001a\u00020/H\u0002J\u0010\u00105\u001a\u00020/2\u0006\u00103\u001a\u00020\u0010H\u0002J\u0010\u00106\u001a\u00020/2\u0006\u00103\u001a\u00020\u0010H\u0002J\u0006\u00107\u001a\u00020/J\u0012\u00108\u001a\u00020/2\b\u00109\u001a\u0004\u0018\u00010:H\u0014J\b\u0010;\u001a\u00020/H\u0014J\b\u0010<\u001a\u00020/H\u0014J\b\u0010=\u001a\u00020/H\u0014J\u0006\u0010>\u001a\u00020/J\u0010\u0010?\u001a\u00020/2\u0006\u0010@\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010#\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010+\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\f\"\u0004\b-\u0010\u000e¨\u0006B"}, m3961d2 = {"Lcom/pudutech/lidar/preview/ShowRadarActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "callback", "Landroid/os/Handler$Callback;", "getCallback", "()Landroid/os/Handler$Callback;", "cleanHandler", "Landroid/os/Handler;", "getCleanHandler", "()Landroid/os/Handler;", "setCleanHandler", "(Landroid/os/Handler;)V", "hasChange", "", "getHasChange", "()Z", "setHasChange", "(Z)V", "lastError", "Lcom/pudutech/lidar/base/LidarErrorType;", "getLastError", "()Lcom/pudutech/lidar/base/LidarErrorType;", "setLastError", "(Lcom/pudutech/lidar/base/LidarErrorType;)V", "lastErrorTime", "", "getLastErrorTime", "()J", "setLastErrorTime", "(J)V", "mRadarView", "Lcom/pudutech/lidar/preview/DrawRaderView;", "runnable", "Ljava/lang/Runnable;", "getRunnable", "()Ljava/lang/Runnable;", "setRunnable", "(Ljava/lang/Runnable;)V", "showLidarHelper", "Lcom/pudutech/lidar/preview/ShowLidarHelper;", "viewHandler", "getViewHandler", "setViewHandler", "addCalibration", "", "angleDegree", "", "handleCalibrationCheckEvent", "isChecked", "handleCalibrationTouchEvent", "handleDebugCheckEvent", "handleOriginCheckEvent", "init", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "resetCalibration", "toast", "str", "Companion", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ShowRadarActivity extends AppCompatActivity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static BaseLidarAdapter lidar;
    private HashMap _$_findViewCache;
    private boolean hasChange;
    private LidarErrorType lastError;
    private long lastErrorTime;
    private DrawRaderView mRadarView;
    private Handler viewHandler;
    private final String TAG = "Lidar_ShowRadarActivity";
    private final ShowLidarHelper showLidarHelper = new ShowLidarHelper();
    private Runnable runnable = new Runnable() { // from class: com.pudutech.lidar.preview.ShowRadarActivity$runnable$1
        @Override // java.lang.Runnable
        public final void run() {
            DrawRaderView drawRaderView;
            ShowLidarHelper showLidarHelper;
            ShowLidarHelper showLidarHelper2;
            DrawRaderView drawRaderView2;
            ListLidarNodePool obtain = ListLidarNodePool.INSTANCE.obtain();
            drawRaderView = ShowRadarActivity.this.mRadarView;
            if (drawRaderView != null) {
                drawRaderView2 = ShowRadarActivity.this.mRadarView;
                if (drawRaderView2 == null) {
                    Intrinsics.throwNpe();
                }
                if (obtain == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.pudutech.lidar.LidarNode>");
                }
                drawRaderView2.showData(obtain);
            }
            showLidarHelper = ShowRadarActivity.this.showLidarHelper;
            if (obtain == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.pudutech.lidar.LidarNode>");
            }
            showLidarHelper.updateLidar(obtain);
            TextView tvLidarInfo = (TextView) ShowRadarActivity.this._$_findCachedViewById(C4705R.id.tvLidarInfo);
            Intrinsics.checkExpressionValueIsNotNull(tvLidarInfo, "tvLidarInfo");
            showLidarHelper2 = ShowRadarActivity.this.showLidarHelper;
            tvLidarInfo.setText(showLidarHelper2.getLidarInfo(ShowRadarActivity.INSTANCE.getLidar()));
            obtain.recycle();
        }
    };
    private Handler cleanHandler = new Handler();
    private final Handler.Callback callback = new Handler.Callback() { // from class: com.pudutech.lidar.preview.ShowRadarActivity$callback$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            DrawRaderView drawRaderView;
            ShowLidarHelper showLidarHelper;
            DrawRaderView drawRaderView2;
            ShowLidarHelper showLidarHelper2;
            int i = message.what;
            if (i == 1) {
                Object obj = message.obj;
                if (obj != null) {
                    List<LidarNode> list = (List) obj;
                    drawRaderView = ShowRadarActivity.this.mRadarView;
                    if (drawRaderView != null) {
                        for (LidarNode lidarNode : list) {
                            double d = lidarNode.angleInRad - 1.5707963267948966d;
                            double d2 = 1000;
                            lidarNode.ptX = lidarNode.distanceM * Math.cos(d) * d2;
                            lidarNode.ptY = lidarNode.distanceM * Math.sin(d) * d2;
                        }
                        showLidarHelper = ShowRadarActivity.this.showLidarHelper;
                        showLidarHelper.updateLidar(list);
                        drawRaderView2 = ShowRadarActivity.this.mRadarView;
                        if (drawRaderView2 == null) {
                            Intrinsics.throwNpe();
                        }
                        drawRaderView2.showData(list);
                        TextView tvLidarInfo = (TextView) ShowRadarActivity.this._$_findCachedViewById(C4705R.id.tvLidarInfo);
                        Intrinsics.checkExpressionValueIsNotNull(tvLidarInfo, "tvLidarInfo");
                        showLidarHelper2 = ShowRadarActivity.this.showLidarHelper;
                        tvLidarInfo.setText(showLidarHelper2.getLidarInfo(ShowRadarActivity.INSTANCE.getLidar()));
                        if (list instanceof ListLidarNodePool) {
                            ((ListLidarNodePool) list).recycle();
                        }
                    }
                    ShowRadarActivity.this.getCleanHandler().removeCallbacks(ShowRadarActivity.this.getRunnable());
                    ShowRadarActivity.this.getCleanHandler().postDelayed(ShowRadarActivity.this.getRunnable(), 500L);
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.pudutech.lidar.LidarNode>");
                }
            } else if (i == 2) {
                Switch swErrorToast = (Switch) ShowRadarActivity.this._$_findCachedViewById(C4705R.id.swErrorToast);
                Intrinsics.checkExpressionValueIsNotNull(swErrorToast, "swErrorToast");
                if (swErrorToast.isChecked()) {
                    Object obj2 = message.obj;
                    if (obj2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.lidar.base.LidarError");
                    }
                    LidarError lidarError = (LidarError) obj2;
                    if (ShowRadarActivity.this.getLastError() != lidarError.getType() || SystemClock.elapsedRealtime() - ShowRadarActivity.this.getLastErrorTime() > 5000) {
                        ShowRadarActivity.this.toast("error! " + lidarError.getType() + " : " + lidarError.getDescription());
                        ShowRadarActivity.this.setLastErrorTime(SystemClock.elapsedRealtime());
                    }
                    ShowRadarActivity.this.setLastError(lidarError.getType());
                }
            }
            return true;
        }
    };

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* compiled from: ShowRadarActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/lidar/preview/ShowRadarActivity$Companion;", "", "()V", "lidar", "Lcom/pudutech/lidar/base/BaseLidarAdapter;", "getLidar", "()Lcom/pudutech/lidar/base/BaseLidarAdapter;", "setLidar", "(Lcom/pudutech/lidar/base/BaseLidarAdapter;)V", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final BaseLidarAdapter getLidar() {
            return ShowRadarActivity.lidar;
        }

        public final void setLidar(BaseLidarAdapter baseLidarAdapter) {
            ShowRadarActivity.lidar = baseLidarAdapter;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pdlog.m3273d(this.TAG, "onCreate");
        Window window = getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window, "window");
        View decorView = window.getDecorView();
        Intrinsics.checkExpressionValueIsNotNull(decorView, "decorView");
        decorView.setSystemUiVisibility(3846);
        setContentView(C4705R.layout.activity_show_radar);
        this.mRadarView = (DrawRaderView) findViewById(C4705R.id.rader_view);
        if (lidar == null) {
            Pdlog.m3274e(this.TAG, "param error: lidarAdapter is null");
            Toast.makeText(this, "lidar is null. exist and try again", 1).show();
            Unit unit = Unit.INSTANCE;
        }
        ShowLidarHelper showLidarHelper = this.showLidarHelper;
        BaseLidarAdapter baseLidarAdapter = lidar;
        showLidarHelper.setLidarVersion(baseLidarAdapter != null ? baseLidarAdapter.getVersion() : null);
        ShowLidarHelper showLidarHelper2 = this.showLidarHelper;
        BaseLidarAdapter baseLidarAdapter2 = lidar;
        showLidarHelper2.setOriginStartAngle_rad(baseLidarAdapter2 != null ? Double.valueOf(baseLidarAdapter2.getAngleStart()) : null);
        ShowLidarHelper showLidarHelper3 = this.showLidarHelper;
        BaseLidarAdapter baseLidarAdapter3 = lidar;
        showLidarHelper3.setOriginEndAngle_rad(baseLidarAdapter3 != null ? Double.valueOf(baseLidarAdapter3.getAngleEnd()) : null);
        TextView tvLidarInfo = (TextView) _$_findCachedViewById(C4705R.id.tvLidarInfo);
        Intrinsics.checkExpressionValueIsNotNull(tvLidarInfo, "tvLidarInfo");
        tvLidarInfo.setText(this.showLidarHelper.getLidarInfo(lidar));
        init();
    }

    public final Runnable getRunnable() {
        return this.runnable;
    }

    public final void setRunnable(Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "<set-?>");
        this.runnable = runnable;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.viewHandler = new Handler(this.callback);
        BaseLidarAdapter baseLidarAdapter = lidar;
        if (baseLidarAdapter != null) {
            Handler handler = this.viewHandler;
            if (handler == null) {
                Intrinsics.throwNpe();
            }
            baseLidarAdapter.setViewHandler(new WeakReference<>(handler));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        Switch swDebugFilter = (Switch) _$_findCachedViewById(C4705R.id.swDebugFilter);
        Intrinsics.checkExpressionValueIsNotNull(swDebugFilter, "swDebugFilter");
        swDebugFilter.setChecked(false);
        Switch swCalibration = (Switch) _$_findCachedViewById(C4705R.id.swCalibration);
        Intrinsics.checkExpressionValueIsNotNull(swCalibration, "swCalibration");
        swCalibration.setChecked(false);
        this.viewHandler = (Handler) null;
        BaseLidarAdapter baseLidarAdapter = lidar;
        if (baseLidarAdapter != null) {
            baseLidarAdapter.setViewHandler((WeakReference) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toast(String str) {
        Toast.makeText(this, str, 1).show();
    }

    public final Handler getViewHandler() {
        return this.viewHandler;
    }

    public final void setViewHandler(Handler handler) {
        this.viewHandler = handler;
    }

    public final Handler getCleanHandler() {
        return this.cleanHandler;
    }

    public final void setCleanHandler(Handler handler) {
        Intrinsics.checkParameterIsNotNull(handler, "<set-?>");
        this.cleanHandler = handler;
    }

    public final LidarErrorType getLastError() {
        return this.lastError;
    }

    public final void setLastError(LidarErrorType lidarErrorType) {
        this.lastError = lidarErrorType;
    }

    public final long getLastErrorTime() {
        return this.lastErrorTime;
    }

    public final void setLastErrorTime(long j) {
        this.lastErrorTime = j;
    }

    public final Handler.Callback getCallback() {
        return this.callback;
    }

    public final void init() {
        ((Button) _$_findCachedViewById(C4705R.id.start_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.lidar.preview.ShowRadarActivity$init$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseLidarAdapter lidar2 = ShowRadarActivity.INSTANCE.getLidar();
                if (lidar2 != null) {
                    lidar2.startScan();
                }
            }
        });
        ((Button) _$_findCachedViewById(C4705R.id.stop_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.lidar.preview.ShowRadarActivity$init$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseLidarAdapter lidar2 = ShowRadarActivity.INSTANCE.getLidar();
                if (lidar2 != null) {
                    lidar2.stopScan();
                }
            }
        });
        ((Button) _$_findCachedViewById(C4705R.id.back_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.lidar.preview.ShowRadarActivity$init$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShowRadarActivity.this.finish();
                ShowRadarActivity.this.overridePendingTransition(0, 0);
            }
        });
        ((Switch) _$_findCachedViewById(C4705R.id.swOriginDataViewer)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.lidar.preview.ShowRadarActivity$init$4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ShowRadarActivity.this.handleOriginCheckEvent(z);
            }
        });
        ((Switch) _$_findCachedViewById(C4705R.id.swDebugFilter)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.lidar.preview.ShowRadarActivity$init$5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ShowRadarActivity.this.handleDebugCheckEvent(z);
            }
        });
        ((Switch) _$_findCachedViewById(C4705R.id.swCalibration)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.lidar.preview.ShowRadarActivity$init$6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ShowRadarActivity.this.handleCalibrationCheckEvent(z);
            }
        });
        ((Button) _$_findCachedViewById(C4705R.id.btnCalibrationUp)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.lidar.preview.ShowRadarActivity$init$7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = ShowRadarActivity.this.TAG;
                Pdlog.m3273d(str, "onclick btnCalibrationUp");
                ShowRadarActivity.this.addCalibration(0.1d);
            }
        });
        handleCalibrationTouchEvent();
        ((Button) _$_findCachedViewById(C4705R.id.btnCalibrationDown)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.lidar.preview.ShowRadarActivity$init$8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = ShowRadarActivity.this.TAG;
                Pdlog.m3275i(str, "onclick btnCalibrationDown");
                ShowRadarActivity.this.addCalibration(-0.1d);
            }
        });
        ((Button) _$_findCachedViewById(C4705R.id.btnCalibrationSave)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.lidar.preview.ShowRadarActivity$init$9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = ShowRadarActivity.this.TAG;
                Pdlog.m3275i(str, "onclick save");
                BaseLidarAdapter lidar2 = ShowRadarActivity.INSTANCE.getLidar();
                if (lidar2 != null) {
                    CalibrationHelper.INSTANCE.save(lidar2.getAngleCalibrationDrift());
                    ShowRadarActivity.this.toast("保存完成");
                }
            }
        });
        BaseLidarAdapter baseLidarAdapter = lidar;
        if (baseLidarAdapter != null) {
            TextView tvCalibration = (TextView) _$_findCachedViewById(C4705R.id.tvCalibration);
            Intrinsics.checkExpressionValueIsNotNull(tvCalibration, "tvCalibration");
            tvCalibration.setText("角度矫正:" + Math.toDegrees(baseLidarAdapter.getAngleCalibrationDrift()) + Typography.degree);
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [T, kotlinx.coroutines.Job] */
    private final void handleCalibrationTouchEvent() {
        final Ref.LongRef longRef = new Ref.LongRef();
        longRef.element = 0L;
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (Job) 0;
        ((Button) _$_findCachedViewById(C4705R.id.btnCalibrationUp)).setOnTouchListener(new View.OnTouchListener() { // from class: com.pudutech.lidar.preview.ShowRadarActivity$handleCalibrationTouchEvent$1
            /* JADX WARN: Type inference failed for: r11v4, types: [T, kotlinx.coroutines.Job] */
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent event) {
                ?? launch$default;
                Intrinsics.checkExpressionValueIsNotNull(event, "event");
                if (event.getAction() == 0) {
                    longRef.element = SystemClock.elapsedRealtime();
                    Job job = (Job) objectRef.element;
                    if (job != null) {
                        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                    }
                    Ref.ObjectRef objectRef2 = objectRef;
                    launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C47151(null), 2, null);
                    objectRef2.element = launch$default;
                    return false;
                }
                if (event.getAction() != 1) {
                    return false;
                }
                Job job2 = (Job) objectRef.element;
                if (job2 != null) {
                    Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
                }
                return SystemClock.elapsedRealtime() - longRef.element > ((long) 500);
            }

            /* compiled from: ShowRadarActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.lidar.preview.ShowRadarActivity$handleCalibrationTouchEvent$1$1", m3970f = "ShowRadarActivity.kt", m3971i = {0, 1}, m3972l = {172, 175}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
            /* renamed from: com.pudutech.lidar.preview.ShowRadarActivity$handleCalibrationTouchEvent$1$1 */
            /* loaded from: classes5.dex */
            static final class C47151 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5463p$;

                C47151(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C47151 c47151 = new C47151(completion);
                    c47151.f5463p$ = (CoroutineScope) obj;
                    return c47151;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C47151) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                    jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
                    	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                    	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                    	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                    */
                /* JADX WARN: Removed duplicated region for block: B:9:0x0058 A[RETURN] */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:8:0x0056 -> B:6:0x0059). Please report as a decompilation issue!!! */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r7) {
                    /*
                        r6 = this;
                        java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r1 = r6.label
                        r2 = 2
                        r3 = 1
                        if (r1 == 0) goto L27
                        if (r1 == r3) goto L1f
                        if (r1 != r2) goto L17
                        java.lang.Object r1 = r6.L$0
                        kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                        kotlin.ResultKt.throwOnFailure(r7)
                        r7 = r6
                        goto L59
                    L17:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r0)
                        throw r7
                    L1f:
                        java.lang.Object r1 = r6.L$0
                        kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L39
                    L27:
                        kotlin.ResultKt.throwOnFailure(r7)
                        kotlinx.coroutines.CoroutineScope r1 = r6.f5463p$
                        r4 = 500(0x1f4, double:2.47E-321)
                        r6.L$0 = r1
                        r6.label = r3
                        java.lang.Object r7 = kotlinx.coroutines.DelayKt.delay(r4, r6)
                        if (r7 != r0) goto L39
                        return r0
                    L39:
                        com.pudutech.lidar.preview.ShowRadarActivity$handleCalibrationTouchEvent$1 r7 = com.pudutech.lidar.preview.ShowRadarActivity$handleCalibrationTouchEvent$1.this
                        com.pudutech.lidar.preview.ShowRadarActivity r7 = com.pudutech.lidar.preview.ShowRadarActivity.this
                        java.lang.String r7 = com.pudutech.lidar.preview.ShowRadarActivity.access$getTAG$p(r7)
                        java.lang.Object[] r3 = new java.lang.Object[r3]
                        r4 = 0
                        java.lang.String r5 = "long press"
                        r3[r4] = r5
                        com.pudutech.base.Pdlog.m3273d(r7, r3)
                        r7 = r6
                    L4c:
                        r3 = 100
                        r7.L$0 = r1
                        r7.label = r2
                        java.lang.Object r3 = kotlinx.coroutines.DelayKt.delay(r3, r7)
                        if (r3 != r0) goto L59
                        return r0
                    L59:
                        com.pudutech.lidar.preview.ShowRadarActivity$handleCalibrationTouchEvent$1 r3 = com.pudutech.lidar.preview.ShowRadarActivity$handleCalibrationTouchEvent$1.this
                        com.pudutech.lidar.preview.ShowRadarActivity r3 = com.pudutech.lidar.preview.ShowRadarActivity.this
                        r4 = 4591870180066957722(0x3fb999999999999a, double:0.1)
                        r3.addCalibration(r4)
                        goto L4c
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.pudutech.lidar.preview.ShowRadarActivity$handleCalibrationTouchEvent$1.C47151.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }
        });
        ((Button) _$_findCachedViewById(C4705R.id.btnCalibrationDown)).setOnTouchListener(new View.OnTouchListener() { // from class: com.pudutech.lidar.preview.ShowRadarActivity$handleCalibrationTouchEvent$2
            /* JADX WARN: Type inference failed for: r11v4, types: [T, kotlinx.coroutines.Job] */
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent event) {
                ?? launch$default;
                Intrinsics.checkExpressionValueIsNotNull(event, "event");
                if (event.getAction() == 0) {
                    longRef.element = SystemClock.elapsedRealtime();
                    Job job = (Job) objectRef.element;
                    if (job != null) {
                        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                    }
                    Ref.ObjectRef objectRef2 = objectRef;
                    launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C47161(null), 2, null);
                    objectRef2.element = launch$default;
                    return false;
                }
                if (event.getAction() != 1) {
                    return false;
                }
                Job job2 = (Job) objectRef.element;
                if (job2 != null) {
                    Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
                }
                return SystemClock.elapsedRealtime() - longRef.element > ((long) 500);
            }

            /* compiled from: ShowRadarActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.lidar.preview.ShowRadarActivity$handleCalibrationTouchEvent$2$1", m3970f = "ShowRadarActivity.kt", m3971i = {0, 1}, m3972l = {191, 194}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
            /* renamed from: com.pudutech.lidar.preview.ShowRadarActivity$handleCalibrationTouchEvent$2$1 */
            /* loaded from: classes5.dex */
            static final class C47161 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5464p$;

                C47161(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C47161 c47161 = new C47161(completion);
                    c47161.f5464p$ = (CoroutineScope) obj;
                    return c47161;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C47161) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                    jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
                    	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                    	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                    	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                    */
                /* JADX WARN: Removed duplicated region for block: B:9:0x0058 A[RETURN] */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:8:0x0056 -> B:6:0x0059). Please report as a decompilation issue!!! */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r7) {
                    /*
                        r6 = this;
                        java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r1 = r6.label
                        r2 = 2
                        r3 = 1
                        if (r1 == 0) goto L27
                        if (r1 == r3) goto L1f
                        if (r1 != r2) goto L17
                        java.lang.Object r1 = r6.L$0
                        kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                        kotlin.ResultKt.throwOnFailure(r7)
                        r7 = r6
                        goto L59
                    L17:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r0)
                        throw r7
                    L1f:
                        java.lang.Object r1 = r6.L$0
                        kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L39
                    L27:
                        kotlin.ResultKt.throwOnFailure(r7)
                        kotlinx.coroutines.CoroutineScope r1 = r6.f5464p$
                        r4 = 500(0x1f4, double:2.47E-321)
                        r6.L$0 = r1
                        r6.label = r3
                        java.lang.Object r7 = kotlinx.coroutines.DelayKt.delay(r4, r6)
                        if (r7 != r0) goto L39
                        return r0
                    L39:
                        com.pudutech.lidar.preview.ShowRadarActivity$handleCalibrationTouchEvent$2 r7 = com.pudutech.lidar.preview.ShowRadarActivity$handleCalibrationTouchEvent$2.this
                        com.pudutech.lidar.preview.ShowRadarActivity r7 = com.pudutech.lidar.preview.ShowRadarActivity.this
                        java.lang.String r7 = com.pudutech.lidar.preview.ShowRadarActivity.access$getTAG$p(r7)
                        java.lang.Object[] r3 = new java.lang.Object[r3]
                        r4 = 0
                        java.lang.String r5 = "long press"
                        r3[r4] = r5
                        com.pudutech.base.Pdlog.m3273d(r7, r3)
                        r7 = r6
                    L4c:
                        r3 = 100
                        r7.L$0 = r1
                        r7.label = r2
                        java.lang.Object r3 = kotlinx.coroutines.DelayKt.delay(r3, r7)
                        if (r3 != r0) goto L59
                        return r0
                    L59:
                        com.pudutech.lidar.preview.ShowRadarActivity$handleCalibrationTouchEvent$2 r3 = com.pudutech.lidar.preview.ShowRadarActivity$handleCalibrationTouchEvent$2.this
                        com.pudutech.lidar.preview.ShowRadarActivity r3 = com.pudutech.lidar.preview.ShowRadarActivity.this
                        r4 = -4631501856787818086(0xbfb999999999999a, double:-0.1)
                        r3.addCalibration(r4)
                        goto L4c
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.pudutech.lidar.preview.ShowRadarActivity$handleCalibrationTouchEvent$2.C47161.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleCalibrationCheckEvent(boolean isChecked) {
        Pdlog.m3273d(this.TAG, "OnCheckedChangeListener swCalibration  isChecked=" + isChecked);
        if (isChecked) {
            Switch swOriginDataViewer = (Switch) _$_findCachedViewById(C4705R.id.swOriginDataViewer);
            Intrinsics.checkExpressionValueIsNotNull(swOriginDataViewer, "swOriginDataViewer");
            if (swOriginDataViewer.isChecked()) {
                Switch swOriginDataViewer2 = (Switch) _$_findCachedViewById(C4705R.id.swOriginDataViewer);
                Intrinsics.checkExpressionValueIsNotNull(swOriginDataViewer2, "swOriginDataViewer");
                swOriginDataViewer2.setChecked(false);
            }
        }
        if (isChecked) {
            this.hasChange = false;
            View layoutCalibration = _$_findCachedViewById(C4705R.id.layoutCalibration);
            Intrinsics.checkExpressionValueIsNotNull(layoutCalibration, "layoutCalibration");
            layoutCalibration.setVisibility(0);
            BaseLidarAdapter baseLidarAdapter = lidar;
            if (baseLidarAdapter != null) {
                TextView tvCalibration = (TextView) _$_findCachedViewById(C4705R.id.tvCalibration);
                Intrinsics.checkExpressionValueIsNotNull(tvCalibration, "tvCalibration");
                StringBuilder sb = new StringBuilder();
                sb.append("角度矫正:");
                Object[] objArr = {Double.valueOf(Math.toDegrees(baseLidarAdapter.getAngleCalibrationDrift()))};
                String format = String.format("%.2f", Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
                sb.append(format);
                sb.append(Typography.degree);
                tvCalibration.setText(sb.toString());
                return;
            }
            return;
        }
        if (this.hasChange) {
            resetCalibration();
        }
        View layoutCalibration2 = _$_findCachedViewById(C4705R.id.layoutCalibration);
        Intrinsics.checkExpressionValueIsNotNull(layoutCalibration2, "layoutCalibration");
        layoutCalibration2.setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleDebugCheckEvent(boolean isChecked) {
        Pdlog.m3273d(this.TAG, "OnCheckedChangeListener swDebugFilter  isChecked=" + isChecked);
        if (isChecked) {
            Switch swOriginDataViewer = (Switch) _$_findCachedViewById(C4705R.id.swOriginDataViewer);
            Intrinsics.checkExpressionValueIsNotNull(swOriginDataViewer, "swOriginDataViewer");
            if (swOriginDataViewer.isChecked()) {
                Switch swOriginDataViewer2 = (Switch) _$_findCachedViewById(C4705R.id.swOriginDataViewer);
                Intrinsics.checkExpressionValueIsNotNull(swOriginDataViewer2, "swOriginDataViewer");
                swOriginDataViewer2.setChecked(false);
            }
        }
        if (isChecked) {
            Pair<Double, Double> loadAngle = this.showLidarHelper.loadAngle();
            if (loadAngle == null) {
                toast("没有调试文件，过滤角度设置为 [0°, 360°]");
                BaseLidarAdapter baseLidarAdapter = lidar;
                if (baseLidarAdapter != null) {
                    BaseLidarAdapter.configOutput$default(baseLidarAdapter, 0.0d, 0.0d, 6.283185307179586d, 1, null);
                    return;
                }
                return;
            }
            toast("载入调试文件，过滤角度设置为 [" + loadAngle.getFirst().doubleValue() + "°, " + loadAngle.getSecond().doubleValue() + "°]");
            BaseLidarAdapter baseLidarAdapter2 = lidar;
            if (baseLidarAdapter2 != null) {
                BaseLidarAdapter.configOutput$default(baseLidarAdapter2, 0.0d, Math.toRadians(loadAngle.getFirst().doubleValue()), Math.toRadians(loadAngle.getSecond().doubleValue()), 1, null);
                return;
            }
            return;
        }
        ShowLidarHelper showLidarHelper = this.showLidarHelper;
        if (showLidarHelper.getOriginStartAngle_rad() == null || showLidarHelper.getOriginEndAngle_rad() == null) {
            return;
        }
        Object[] objArr = new Object[2];
        Double originStartAngle_rad = showLidarHelper.getOriginStartAngle_rad();
        if (originStartAngle_rad == null) {
            Intrinsics.throwNpe();
        }
        objArr[0] = Double.valueOf(Math.toDegrees(originStartAngle_rad.doubleValue()));
        Double originEndAngle_rad = showLidarHelper.getOriginEndAngle_rad();
        if (originEndAngle_rad == null) {
            Intrinsics.throwNpe();
        }
        objArr[1] = Double.valueOf(Math.toDegrees(originEndAngle_rad.doubleValue()));
        String format = String.format("[%.1f°, %.1f°]", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
        toast("还原过滤角度为 " + format);
        BaseLidarAdapter baseLidarAdapter3 = lidar;
        if (baseLidarAdapter3 != null) {
            Double originStartAngle_rad2 = showLidarHelper.getOriginStartAngle_rad();
            if (originStartAngle_rad2 == null) {
                Intrinsics.throwNpe();
            }
            double doubleValue = originStartAngle_rad2.doubleValue();
            Double originEndAngle_rad2 = showLidarHelper.getOriginEndAngle_rad();
            if (originEndAngle_rad2 == null) {
                Intrinsics.throwNpe();
            }
            BaseLidarAdapter.configOutput$default(baseLidarAdapter3, 0.0d, doubleValue, originEndAngle_rad2.doubleValue(), 1, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleOriginCheckEvent(boolean isChecked) {
        Pdlog.m3273d(this.TAG, "OnCheckedChangeListener swOriginDataViewer  isChecked=" + isChecked);
        if (isChecked) {
            Switch swCalibration = (Switch) _$_findCachedViewById(C4705R.id.swCalibration);
            Intrinsics.checkExpressionValueIsNotNull(swCalibration, "swCalibration");
            if (swCalibration.isChecked()) {
                Switch swCalibration2 = (Switch) _$_findCachedViewById(C4705R.id.swCalibration);
                Intrinsics.checkExpressionValueIsNotNull(swCalibration2, "swCalibration");
                swCalibration2.setChecked(false);
            }
        }
        if (isChecked) {
            Switch swDebugFilter = (Switch) _$_findCachedViewById(C4705R.id.swDebugFilter);
            Intrinsics.checkExpressionValueIsNotNull(swDebugFilter, "swDebugFilter");
            if (swDebugFilter.isChecked()) {
                Switch swDebugFilter2 = (Switch) _$_findCachedViewById(C4705R.id.swDebugFilter);
                Intrinsics.checkExpressionValueIsNotNull(swDebugFilter2, "swDebugFilter");
                swDebugFilter2.setChecked(false);
            }
        }
        BaseLidarAdapter baseLidarAdapter = lidar;
        if (baseLidarAdapter != null) {
            baseLidarAdapter.setViewerOriginDataSwitcher(isChecked);
        }
    }

    public final boolean getHasChange() {
        return this.hasChange;
    }

    public final void setHasChange(boolean z) {
        this.hasChange = z;
    }

    public final void resetCalibration() {
        Double angleCalibration = CalibrationHelper.INSTANCE.getAngleCalibration();
        double doubleValue = angleCalibration != null ? angleCalibration.doubleValue() : 0.0d;
        BaseLidarAdapter baseLidarAdapter = lidar;
        if (baseLidarAdapter != null) {
            BaseLidarAdapter.configInstall$default(baseLidarAdapter, false, false, doubleValue, 3, null);
        }
    }

    public final void addCalibration(double angleDegree) {
        Pdlog.m3273d(this.TAG, "addCalibration angle_degree=" + angleDegree);
        BaseLidarAdapter baseLidarAdapter = lidar;
        if (baseLidarAdapter != null) {
            this.hasChange = true;
            if (baseLidarAdapter == null) {
                Intrinsics.throwNpe();
            }
            double degrees = Math.toDegrees(baseLidarAdapter.getAngleCalibrationDrift());
            Pdlog.m3273d(this.TAG, "angleCalibrationDrift=" + degrees + Typography.degree);
            double d = (double) 10;
            double rint = Math.rint((angleDegree + degrees) * d) / 10.0d;
            if (rint > d) {
                rint = 10.0d;
            }
            if (rint < -10) {
                rint = -10.0d;
            }
            Pdlog.m3273d(this.TAG, "adjust drift=" + degrees + Typography.degree);
            BaseLidarAdapter baseLidarAdapter2 = lidar;
            if (baseLidarAdapter2 != null) {
                BaseLidarAdapter.configInstall$default(baseLidarAdapter2, false, false, Math.toRadians(rint), 3, null);
                TextView tvCalibration = (TextView) _$_findCachedViewById(C4705R.id.tvCalibration);
                Intrinsics.checkExpressionValueIsNotNull(tvCalibration, "tvCalibration");
                StringBuilder sb = new StringBuilder();
                sb.append("角度矫正:");
                Object[] objArr = {Double.valueOf(Math.toDegrees(baseLidarAdapter2.getAngleCalibrationDrift()))};
                String format = String.format("%.2f", Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
                sb.append(format);
                sb.append(Typography.degree);
                tvCalibration.setText(sb.toString());
            }
        }
    }
}
