package com.pudutech.rgbdlib.activity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.calibrationalgorithm.CalibrationAlgorithm;
import com.pudutech.calibrationalgorithm.downwardrgbd.algo.RunCapeSegment;
import com.pudutech.calibrationalgorithm.downwardrgbd.algo.RunLineSegment;
import com.pudutech.calibrationalgorithm.downwardrgbd.algo.RunRoadScout;
import com.pudutech.rgbdlib.C5657R;
import com.pudutech.rgbdlib.RGBDDataCatcher;
import com.pudutech.rgbdlib.RGBDSensor;
import com.pudutech.rgbdlib.pointcloud.PCRenderer;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: ViewRegionActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u000eH\u0014J\u0012\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/rgbdlib/activity/ViewRegionActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "initCapeSeg", "", "initLineSeg", "initRoadScout", "readerer", "Lcom/pudutech/rgbdlib/pointcloud/PCRenderer;", "save", "Ljava/util/concurrent/atomic/AtomicBoolean;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "Companion", "RGBDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ViewRegionActivity extends AppCompatActivity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static RGBDSensor rgbsSensor;
    private HashMap _$_findViewCache;
    private boolean initCapeSeg;
    private boolean initLineSeg;
    private boolean initRoadScout;
    private PCRenderer readerer;
    private final String TAG = "ViewRegion";
    private AtomicBoolean save = new AtomicBoolean(false);

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

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* compiled from: ViewRegionActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/rgbdlib/activity/ViewRegionActivity$Companion;", "", "()V", "rgbsSensor", "Lcom/pudutech/rgbdlib/RGBDSensor;", "getRgbsSensor", "()Lcom/pudutech/rgbdlib/RGBDSensor;", "setRgbsSensor", "(Lcom/pudutech/rgbdlib/RGBDSensor;)V", "RGBDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final RGBDSensor getRgbsSensor() {
            return ViewRegionActivity.rgbsSensor;
        }

        public final void setRgbsSensor(RGBDSensor rGBDSensor) {
            ViewRegionActivity.rgbsSensor = rGBDSensor;
        }
    }

    public static final /* synthetic */ PCRenderer access$getReaderer$p(ViewRegionActivity viewRegionActivity) {
        PCRenderer pCRenderer = viewRegionActivity.readerer;
        if (pCRenderer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("readerer");
        }
        return pCRenderer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5657R.layout.activity_view_region);
        if (rgbsSensor == null) {
            Pdlog.m3274e(this.TAG, "param error: RGBDSensor is null");
            Toast.makeText(this, "rgbd is null. exist and try again", 1).show();
            Unit unit = Unit.INSTANCE;
        }
        this.initCapeSeg = CalibrationAlgorithm.INSTANCE.getDownwardRGBD().getRunCapeSegment().initParam();
        this.initLineSeg = CalibrationAlgorithm.INSTANCE.getDownwardRGBD().getRunLineSegment().initParam();
        this.initRoadScout = CalibrationAlgorithm.INSTANCE.getDownwardRGBD().getRunRoadScout().initParam();
        this.readerer = new PCRenderer(60000);
        ((GLSurfaceView) _$_findCachedViewById(C5657R.id.glView)).setEGLContextClientVersion(2);
        GLSurfaceView gLSurfaceView = (GLSurfaceView) _$_findCachedViewById(C5657R.id.glView);
        PCRenderer pCRenderer = this.readerer;
        if (pCRenderer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("readerer");
        }
        gLSurfaceView.setRenderer(pCRenderer);
        ((Button) _$_findCachedViewById(C5657R.id.button_start_cape_seg)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.rgbdlib.activity.ViewRegionActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean z;
                RGBDSensor rgbsSensor2;
                ThreadSafeListener<RGBDDataCatcher> centernRGBDListeners;
                ThreadSafeListener<RGBDDataCatcher> centernRGBDListeners2;
                RGBDSensor rgbsSensor3 = ViewRegionActivity.INSTANCE.getRgbsSensor();
                if (rgbsSensor3 != null && (centernRGBDListeners2 = rgbsSensor3.getCenternRGBDListeners()) != null) {
                    centernRGBDListeners2.remove("view_region");
                }
                z = ViewRegionActivity.this.initCapeSeg;
                if (!z || (rgbsSensor2 = ViewRegionActivity.INSTANCE.getRgbsSensor()) == null || (centernRGBDListeners = rgbsSensor2.getCenternRGBDListeners()) == null) {
                    return;
                }
                centernRGBDListeners.add("view_region", new RGBDDataCatcher() { // from class: com.pudutech.rgbdlib.activity.ViewRegionActivity$onCreate$2.1
                    @Override // com.pudutech.rgbdlib.RGBDDataCatcher
                    public void onFrameDescriptor(ParcelFileDescriptor parcelFileDescriptor, int rows, int cols, int memorySize) {
                        AtomicBoolean atomicBoolean;
                        String str;
                        Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                        RunCapeSegment runCapeSegment = CalibrationAlgorithm.INSTANCE.getDownwardRGBD().getRunCapeSegment();
                        int fd = parcelFileDescriptor.getFd();
                        atomicBoolean = ViewRegionActivity.this.save;
                        float[] process = runCapeSegment.process(fd, rows, cols, memorySize, atomicBoolean.get());
                        Integer valueOf = process != null ? Integer.valueOf(process.length) : null;
                        if (valueOf == null) {
                            Intrinsics.throwNpe();
                        }
                        if (valueOf.intValue() <= 33) {
                            str = ViewRegionActivity.this.TAG;
                            Pdlog.m3273d(str, "abandon " + process.length);
                            return;
                        }
                        ViewRegionActivity.access$getReaderer$p(ViewRegionActivity.this).getPointCloud().UpdatePoints(process);
                    }
                });
            }
        });
        ((Button) _$_findCachedViewById(C5657R.id.button_start_line_seg)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.rgbdlib.activity.ViewRegionActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean z;
                RGBDSensor rgbsSensor2;
                ThreadSafeListener<RGBDDataCatcher> centernRGBDListeners;
                ThreadSafeListener<RGBDDataCatcher> centernRGBDListeners2;
                RGBDSensor rgbsSensor3 = ViewRegionActivity.INSTANCE.getRgbsSensor();
                if (rgbsSensor3 != null && (centernRGBDListeners2 = rgbsSensor3.getCenternRGBDListeners()) != null) {
                    centernRGBDListeners2.remove("view_region");
                }
                z = ViewRegionActivity.this.initLineSeg;
                if (!z || (rgbsSensor2 = ViewRegionActivity.INSTANCE.getRgbsSensor()) == null || (centernRGBDListeners = rgbsSensor2.getCenternRGBDListeners()) == null) {
                    return;
                }
                centernRGBDListeners.add("view_region", new RGBDDataCatcher() { // from class: com.pudutech.rgbdlib.activity.ViewRegionActivity$onCreate$3.1
                    @Override // com.pudutech.rgbdlib.RGBDDataCatcher
                    public void onFrameDescriptor(ParcelFileDescriptor parcelFileDescriptor, int rows, int cols, int memorySize) {
                        AtomicBoolean atomicBoolean;
                        String str;
                        Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                        RunLineSegment runLineSegment = CalibrationAlgorithm.INSTANCE.getDownwardRGBD().getRunLineSegment();
                        int fd = parcelFileDescriptor.getFd();
                        atomicBoolean = ViewRegionActivity.this.save;
                        float[] process = runLineSegment.process(fd, rows, cols, memorySize, atomicBoolean.get());
                        Integer valueOf = process != null ? Integer.valueOf(process.length) : null;
                        if (valueOf == null) {
                            Intrinsics.throwNpe();
                        }
                        if (valueOf.intValue() <= 33) {
                            str = ViewRegionActivity.this.TAG;
                            Pdlog.m3273d(str, "abandon " + process.length);
                            return;
                        }
                        ViewRegionActivity.access$getReaderer$p(ViewRegionActivity.this).getPointCloud().UpdatePoints(process);
                    }
                });
            }
        });
        ((Button) _$_findCachedViewById(C5657R.id.button_start_road_scout_seg)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.rgbdlib.activity.ViewRegionActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean z;
                RGBDSensor rgbsSensor2;
                ThreadSafeListener<RGBDDataCatcher> centernRGBDListeners;
                ThreadSafeListener<RGBDDataCatcher> centernRGBDListeners2;
                RGBDSensor rgbsSensor3 = ViewRegionActivity.INSTANCE.getRgbsSensor();
                if (rgbsSensor3 != null && (centernRGBDListeners2 = rgbsSensor3.getCenternRGBDListeners()) != null) {
                    centernRGBDListeners2.remove("view_region");
                }
                z = ViewRegionActivity.this.initRoadScout;
                if (!z || (rgbsSensor2 = ViewRegionActivity.INSTANCE.getRgbsSensor()) == null || (centernRGBDListeners = rgbsSensor2.getCenternRGBDListeners()) == null) {
                    return;
                }
                centernRGBDListeners.add("view_region", new RGBDDataCatcher() { // from class: com.pudutech.rgbdlib.activity.ViewRegionActivity$onCreate$4.1
                    @Override // com.pudutech.rgbdlib.RGBDDataCatcher
                    public void onFrameDescriptor(ParcelFileDescriptor parcelFileDescriptor, int rows, int cols, int memorySize) {
                        AtomicBoolean atomicBoolean;
                        String str;
                        Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                        RunRoadScout runRoadScout = CalibrationAlgorithm.INSTANCE.getDownwardRGBD().getRunRoadScout();
                        int fd = parcelFileDescriptor.getFd();
                        atomicBoolean = ViewRegionActivity.this.save;
                        float[] process = runRoadScout.process(fd, rows, cols, memorySize, atomicBoolean.get());
                        Integer valueOf = process != null ? Integer.valueOf(process.length) : null;
                        if (valueOf == null) {
                            Intrinsics.throwNpe();
                        }
                        if (valueOf.intValue() <= 33) {
                            str = ViewRegionActivity.this.TAG;
                            Pdlog.m3273d(str, "abandon " + process.length);
                            return;
                        }
                        ViewRegionActivity.access$getReaderer$p(ViewRegionActivity.this).getPointCloud().UpdatePoints(process);
                    }
                });
            }
        });
        ((Button) _$_findCachedViewById(C5657R.id.button_save)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.rgbdlib.activity.ViewRegionActivity$onCreate$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AtomicBoolean atomicBoolean;
                AtomicBoolean atomicBoolean2;
                AtomicBoolean atomicBoolean3;
                atomicBoolean = ViewRegionActivity.this.save;
                boolean z = atomicBoolean.get();
                atomicBoolean2 = ViewRegionActivity.this.save;
                atomicBoolean2.set(!z);
                atomicBoolean3 = ViewRegionActivity.this.save;
                if (atomicBoolean3.get()) {
                    ((Button) ViewRegionActivity.this._$_findCachedViewById(C5657R.id.button_save)).setText(C5657R.string.savingImgButton);
                } else {
                    ((Button) ViewRegionActivity.this._$_findCachedViewById(C5657R.id.button_save)).setText(C5657R.string.saveImgButton);
                }
            }
        });
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent event) {
        PCRenderer pCRenderer = this.readerer;
        if (pCRenderer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("readerer");
        }
        return pCRenderer.onTouchEvent(event);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        ThreadSafeListener<RGBDDataCatcher> centernRGBDListeners;
        super.onDestroy();
        RGBDSensor rGBDSensor = rgbsSensor;
        if (rGBDSensor == null || (centernRGBDListeners = rGBDSensor.getCenternRGBDListeners()) == null) {
            return;
        }
        centernRGBDListeners.remove("view_region");
    }
}
