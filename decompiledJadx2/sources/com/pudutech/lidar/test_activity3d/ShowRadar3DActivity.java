package com.pudutech.lidar.test_activity3d;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.C4705R;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.ListLidarNodePool;
import com.pudutech.lidar.base.BaseLidarAdapter;
import com.pudutech.lidar.base.LidarError;
import com.pudutech.lidar.base.LidarErrorType;
import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ShowRadar3DActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 52\u00020\u0001:\u00015B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010*\u001a\u00020\u00042\b\b\u0002\u0010+\u001a\u00020\u0017J\b\u0010,\u001a\u00020-H\u0002J\u0012\u0010.\u001a\u00020-2\b\u0010/\u001a\u0004\u0018\u000100H\u0014J\b\u00101\u001a\u00020-H\u0014J\b\u00102\u001a\u00020-H\u0014J\u0010\u00103\u001a\u00020-2\u0006\u00104\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0017X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010'\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0011\"\u0004\b)\u0010\u0013¨\u00066"}, m3961d2 = {"Lcom/pudutech/lidar/test_activity3d/ShowRadar3DActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "<set-?>", "", "averageFrameRate", "getAverageFrameRate", "()D", "callback", "Landroid/os/Handler$Callback;", "getCallback", "()Landroid/os/Handler$Callback;", "cleanHandler", "Landroid/os/Handler;", "getCleanHandler", "()Landroid/os/Handler;", "setCleanHandler", "(Landroid/os/Handler;)V", "format", "Ljava/text/DecimalFormat;", "frameCnt", "", "lastError", "Lcom/pudutech/lidar/base/LidarErrorType;", "getLastError", "()Lcom/pudutech/lidar/base/LidarErrorType;", "setLastError", "(Lcom/pudutech/lidar/base/LidarErrorType;)V", "lastErrorTime", "", "getLastErrorTime", "()J", "setLastErrorTime", "(J)V", "lastTimestamp", "oneFrameRateCheckNum", "pointsString", "viewHandler", "getViewHandler", "setViewHandler", "getLidarInfo", "pointNum", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "toast", "str", "Companion", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ShowRadar3DActivity extends AppCompatActivity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static BaseLidarAdapter lidarAdapter;
    private HashMap _$_findViewCache;
    private int frameCnt;
    private LidarErrorType lastError;
    private long lastErrorTime;
    private long lastTimestamp;
    private Handler viewHandler;
    private final String TAG = "ShowRadar3DActivity";
    private Handler cleanHandler = new Handler();
    private String pointsString = "";
    private final DecimalFormat format = new DecimalFormat("0.##", new DecimalFormatSymbols(Locale.US));
    private final int oneFrameRateCheckNum = 10;
    private double averageFrameRate = 10.0d;
    private final Handler.Callback callback = new Handler.Callback() { // from class: com.pudutech.lidar.test_activity3d.ShowRadar3DActivity$callback$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i = message.what;
            if (i == BaseLidarAdapter.INSTANCE.getONE_FRAME_COMPLETED_FLAG()) {
                Object obj = message.obj;
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.pudutech.lidar.LidarNode>");
                }
                List<? extends LidarNode> list = (List) obj;
                TextView tv3dLidarInfo = (TextView) ShowRadar3DActivity.this._$_findCachedViewById(C4705R.id.tv3dLidarInfo);
                Intrinsics.checkExpressionValueIsNotNull(tv3dLidarInfo, "tv3dLidarInfo");
                tv3dLidarInfo.setText(ShowRadar3DActivity.this.getLidarInfo(list.size()));
                ((MySurfaceView) ShowRadar3DActivity.this._$_findCachedViewById(C4705R.id.gl_surface_view)).updateLidar(list);
                if (!(list instanceof ListLidarNodePool)) {
                    return true;
                }
                ((ListLidarNodePool) list).recycle();
                return true;
            }
            if (i != BaseLidarAdapter.INSTANCE.getERROR_FLAG()) {
                return true;
            }
            Object obj2 = message.obj;
            if (obj2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.lidar.base.LidarError");
            }
            LidarError lidarError = (LidarError) obj2;
            if (ShowRadar3DActivity.this.getLastError() != lidarError.getType() || SystemClock.elapsedRealtime() - ShowRadar3DActivity.this.getLastErrorTime() > 5000) {
                ShowRadar3DActivity.this.toast("error! " + lidarError.getType() + " : " + lidarError.getDescription());
                ShowRadar3DActivity.this.setLastErrorTime(SystemClock.elapsedRealtime());
            }
            ShowRadar3DActivity.this.setLastError(lidarError.getType());
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

    public final double getAverageFrameRate() {
        return this.averageFrameRate;
    }

    /* compiled from: ShowRadar3DActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/lidar/test_activity3d/ShowRadar3DActivity$Companion;", "", "()V", "lidarAdapter", "Lcom/pudutech/lidar/base/BaseLidarAdapter;", "getLidarAdapter", "()Lcom/pudutech/lidar/base/BaseLidarAdapter;", "setLidarAdapter", "(Lcom/pudutech/lidar/base/BaseLidarAdapter;)V", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final BaseLidarAdapter getLidarAdapter() {
            return ShowRadar3DActivity.lidarAdapter;
        }

        public final void setLidarAdapter(BaseLidarAdapter baseLidarAdapter) {
            ShowRadar3DActivity.lidarAdapter = baseLidarAdapter;
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
        setContentView(C4705R.layout.activity_show_3dradar);
        initView();
    }

    private final void initView() {
        TextView tv3dLidarInfo = (TextView) _$_findCachedViewById(C4705R.id.tv3dLidarInfo);
        Intrinsics.checkExpressionValueIsNotNull(tv3dLidarInfo, "tv3dLidarInfo");
        tv3dLidarInfo.setText(getLidarInfo$default(this, 0, 1, null));
        if (lidarAdapter == null) {
            Pdlog.m3274e(this.TAG, "param error: lidarAdapter is null");
            Toast.makeText(this, "lidarAdapter is null. exist and try again", 1).show();
            Unit unit = Unit.INSTANCE;
        }
        ((MySurfaceView) _$_findCachedViewById(C4705R.id.gl_surface_view)).requestFocus();
        MySurfaceView gl_surface_view = (MySurfaceView) _$_findCachedViewById(C4705R.id.gl_surface_view);
        Intrinsics.checkExpressionValueIsNotNull(gl_surface_view, "gl_surface_view");
        gl_surface_view.setFocusableInTouchMode(true);
        ((Button) _$_findCachedViewById(C4705R.id.start_3d_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.lidar.test_activity3d.ShowRadar3DActivity$initView$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseLidarAdapter lidarAdapter2 = ShowRadar3DActivity.INSTANCE.getLidarAdapter();
                if (lidarAdapter2 != null) {
                    lidarAdapter2.startScan();
                }
            }
        });
        ((Button) _$_findCachedViewById(C4705R.id.stop_3d_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.lidar.test_activity3d.ShowRadar3DActivity$initView$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseLidarAdapter lidarAdapter2 = ShowRadar3DActivity.INSTANCE.getLidarAdapter();
                if (lidarAdapter2 != null) {
                    lidarAdapter2.stopScan();
                }
            }
        });
        ((Button) _$_findCachedViewById(C4705R.id.back_3d_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.lidar.test_activity3d.ShowRadar3DActivity$initView$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShowRadar3DActivity.this.finish();
                ShowRadar3DActivity.this.overridePendingTransition(0, 0);
            }
        });
    }

    public final Handler.Callback getCallback() {
        return this.callback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toast(String str) {
        Toast.makeText(this, str, 1).show();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        ((MySurfaceView) _$_findCachedViewById(C4705R.id.gl_surface_view)).onResume();
        this.viewHandler = new Handler(this.callback);
        BaseLidarAdapter baseLidarAdapter = lidarAdapter;
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
        WeakReference<Handler> viewHandler;
        Handler handler;
        super.onPause();
        ((MySurfaceView) _$_findCachedViewById(C4705R.id.gl_surface_view)).onPause();
        Handler handler2 = this.viewHandler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        BaseLidarAdapter baseLidarAdapter = lidarAdapter;
        if (baseLidarAdapter != null && (viewHandler = baseLidarAdapter.getViewHandler()) != null && (handler = viewHandler.get()) != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.cleanHandler.removeCallbacksAndMessages(null);
        this.viewHandler = (Handler) null;
        BaseLidarAdapter baseLidarAdapter2 = lidarAdapter;
        if (baseLidarAdapter2 != null) {
            baseLidarAdapter2.setViewHandler((WeakReference) null);
        }
    }

    public static /* synthetic */ String getLidarInfo$default(ShowRadar3DActivity showRadar3DActivity, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return showRadar3DActivity.getLidarInfo(i);
    }

    public final String getLidarInfo(int pointNum) {
        try {
            if (this.frameCnt == 0) {
                this.lastTimestamp = SystemClock.elapsedRealtime();
            }
            this.frameCnt++;
            if (this.frameCnt > this.oneFrameRateCheckNum) {
                String format = this.format.format((((this.oneFrameRateCheckNum * 1000.0d) / (SystemClock.elapsedRealtime() - this.lastTimestamp)) * 10) / 10.0d);
                Intrinsics.checkExpressionValueIsNotNull(format, "format.format(((oneFrame…/ duration) * 10) / 10.0)");
                this.averageFrameRate = Double.parseDouble(format);
                this.frameCnt = 0;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String str = this.pointsString;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        BaseLidarAdapter baseLidarAdapter = lidarAdapter;
        sb.append(baseLidarAdapter != null ? baseLidarAdapter.getVersion() : null);
        sb.append("  ");
        sb.append(getString(C4705R.string.frame_rate));
        sb.append(':');
        sb.append(this.averageFrameRate);
        sb.append("  ");
        sb.append(getString(C4705R.string.point_num));
        sb.append(':');
        sb.append(pointNum);
        return sb.toString();
    }
}
