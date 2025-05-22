package com.pudutech.rgbdlib.activity;

import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.rgbdlib.C5657R;
import com.pudutech.rgbdlib.RGBDDataCatcher;
import com.pudutech.rgbdlib.RGBDSensor;
import com.pudutech.robot.module.report.track2.TrackKey;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: StressTestingActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0016H\u0002J\u0010\u0010\"\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0016H\u0002J\u0010\u0010#\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0016H\u0002J\u0010\u0010$\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0016H\u0002J\u0012\u0010%\u001a\u00020 2\b\u0010&\u001a\u0004\u0018\u00010'H\u0014J\b\u0010(\u001a\u00020 H\u0002J\b\u0010)\u001a\u00020 H\u0002J\u0010\u0010*\u001a\u00020 2\u0006\u0010+\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, m3961d2 = {"Lcom/pudutech/rgbdlib/activity/StressTestingActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "centerFrame", "Ljava/util/concurrent/atomic/AtomicInteger;", "centerOpen", "Ljava/util/concurrent/atomic/AtomicBoolean;", "downFrame", "downOpen", "format", "Ljava/text/DecimalFormat;", "fpsCheckingJob", "Lkotlinx/coroutines/Job;", "leftFrame", "leftOpen", "rightFrame", "rightOpen", "startFPSCheck", "switchCheckingJob", "totalCenterFrame", "", "totalCenterOpen", "totalDownFrame", "totalDownOpen", "totalIndex", "totalLeftFrame", "totalLeftOpen", "totalRightFrame", "totalRightOpen", "getCenterFPS", "", "total", "getDownFPS", "getLeftFPS", "getRightFPS", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "removeFPSCheck", "removeSwitchCheck", "writeLog", NotificationCompat.CATEGORY_MESSAGE, "Companion", "RGBDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class StressTestingActivity extends AppCompatActivity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static RGBDSensor rgbdSensor;
    private HashMap _$_findViewCache;
    private Job fpsCheckingJob;
    private Job switchCheckingJob;
    private int totalCenterFrame;
    private int totalCenterOpen;
    private int totalDownFrame;
    private int totalDownOpen;
    private int totalIndex;
    private int totalLeftFrame;
    private int totalLeftOpen;
    private int totalRightFrame;
    private int totalRightOpen;
    private final String TAG = "RGBDStressTesting";
    private AtomicBoolean leftOpen = new AtomicBoolean(false);
    private AtomicInteger leftFrame = new AtomicInteger(0);
    private AtomicBoolean rightOpen = new AtomicBoolean(false);
    private AtomicInteger rightFrame = new AtomicInteger(0);
    private AtomicBoolean centerOpen = new AtomicBoolean(false);
    private AtomicInteger centerFrame = new AtomicInteger(0);
    private AtomicBoolean downOpen = new AtomicBoolean(false);
    private AtomicInteger downFrame = new AtomicInteger(0);
    private final DecimalFormat format = new DecimalFormat("0.##");
    private AtomicBoolean startFPSCheck = new AtomicBoolean(false);

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
    /* compiled from: StressTestingActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/rgbdlib/activity/StressTestingActivity$Companion;", "", "()V", "rgbdSensor", "Lcom/pudutech/rgbdlib/RGBDSensor;", "getRgbdSensor", "()Lcom/pudutech/rgbdlib/RGBDSensor;", "setRgbdSensor", "(Lcom/pudutech/rgbdlib/RGBDSensor;)V", "RGBDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final RGBDSensor getRgbdSensor() {
            return StressTestingActivity.rgbdSensor;
        }

        public final void setRgbdSensor(RGBDSensor rGBDSensor) {
            StressTestingActivity.rgbdSensor = rGBDSensor;
        }
    }

    public static final /* synthetic */ DecimalFormat access$getFormat$p(StressTestingActivity stressTestingActivity) {
        return stressTestingActivity.format;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5657R.layout.activity_stress_testing);
        if (rgbdSensor == null) {
            Pdlog.m3274e(this.TAG, "param error: RGBDSensor is null");
            Toast.makeText(this, "rgbd is null. exist and try again", 1).show();
            Unit unit = Unit.INSTANCE;
        }
        ((Button) _$_findCachedViewById(C5657R.id.checkFPS_button)).setOnClickListener(new StressTestingActivity$onCreate$2(this));
        ((Button) _$_findCachedViewById(C5657R.id.checkSwitch_button)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.rgbdlib.activity.StressTestingActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Job job;
                Job job2;
                Job launch$default;
                ThreadSafeListener downRGBDListeners;
                ThreadSafeListener<RGBDDataCatcher> centernRGBDListeners;
                ThreadSafeListener<RGBDDataCatcher> rightRGBDListeners;
                ThreadSafeListener<RGBDDataCatcher> leftRGBDListeners;
                Job job3;
                job = StressTestingActivity.this.fpsCheckingJob;
                if (job != null && job.isActive()) {
                    job3 = StressTestingActivity.this.fpsCheckingJob;
                    if (job3 != null) {
                        Job.DefaultImpls.cancel$default(job3, (CancellationException) null, 1, (Object) null);
                    }
                    StressTestingActivity.this.removeFPSCheck();
                }
                job2 = StressTestingActivity.this.switchCheckingJob;
                if (job2 == null || !job2.isActive()) {
                    StressTestingActivity.this.totalLeftOpen = 0;
                    StressTestingActivity.this.totalRightOpen = 0;
                    StressTestingActivity.this.totalCenterOpen = 0;
                    StressTestingActivity.this.totalDownOpen = 0;
                    StressTestingActivity.this.totalIndex = 0;
                    RGBDSensor rgbdSensor2 = StressTestingActivity.INSTANCE.getRgbdSensor();
                    if (rgbdSensor2 != null) {
                        rgbdSensor2.closeRGBD();
                    }
                    RGBDSensor rgbdSensor3 = StressTestingActivity.INSTANCE.getRgbdSensor();
                    if (rgbdSensor3 != null && (leftRGBDListeners = rgbdSensor3.getLeftRGBDListeners()) != null) {
                        leftRGBDListeners.add(TrackKey.SWITCH_CHECK, new RGBDDataCatcher() { // from class: com.pudutech.rgbdlib.activity.StressTestingActivity$onCreate$3.1
                            @Override // com.pudutech.rgbdlib.RGBDDataCatcher
                            public void onFrameDescriptor(ParcelFileDescriptor parcelFileDescriptor, int rows, int cols, int memorySize) {
                                AtomicBoolean atomicBoolean;
                                Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                                if (parcelFileDescriptor.getFd() != -1) {
                                    atomicBoolean = StressTestingActivity.this.leftOpen;
                                    atomicBoolean.set(true);
                                }
                            }
                        });
                    }
                    RGBDSensor rgbdSensor4 = StressTestingActivity.INSTANCE.getRgbdSensor();
                    if (rgbdSensor4 != null && (rightRGBDListeners = rgbdSensor4.getRightRGBDListeners()) != null) {
                        rightRGBDListeners.add(TrackKey.SWITCH_CHECK, new RGBDDataCatcher() { // from class: com.pudutech.rgbdlib.activity.StressTestingActivity$onCreate$3.2
                            @Override // com.pudutech.rgbdlib.RGBDDataCatcher
                            public void onFrameDescriptor(ParcelFileDescriptor parcelFileDescriptor, int rows, int cols, int memorySize) {
                                AtomicBoolean atomicBoolean;
                                Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                                if (parcelFileDescriptor.getFd() != -1) {
                                    atomicBoolean = StressTestingActivity.this.rightOpen;
                                    atomicBoolean.set(true);
                                }
                            }
                        });
                    }
                    RGBDSensor rgbdSensor5 = StressTestingActivity.INSTANCE.getRgbdSensor();
                    if (rgbdSensor5 != null && (centernRGBDListeners = rgbdSensor5.getCenternRGBDListeners()) != null) {
                        centernRGBDListeners.add(TrackKey.SWITCH_CHECK, new RGBDDataCatcher() { // from class: com.pudutech.rgbdlib.activity.StressTestingActivity$onCreate$3.3
                            @Override // com.pudutech.rgbdlib.RGBDDataCatcher
                            public void onFrameDescriptor(ParcelFileDescriptor parcelFileDescriptor, int rows, int cols, int memorySize) {
                                AtomicBoolean atomicBoolean;
                                Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                                if (parcelFileDescriptor.getFd() != -1) {
                                    atomicBoolean = StressTestingActivity.this.centerOpen;
                                    atomicBoolean.set(true);
                                }
                            }
                        });
                    }
                    RGBDSensor rgbdSensor6 = StressTestingActivity.INSTANCE.getRgbdSensor();
                    if (rgbdSensor6 != null && (downRGBDListeners = rgbdSensor6.getDownRGBDListeners()) != null) {
                        downRGBDListeners.add(TrackKey.SWITCH_CHECK, new RGBDDataCatcher() { // from class: com.pudutech.rgbdlib.activity.StressTestingActivity$onCreate$3.4
                            @Override // com.pudutech.rgbdlib.RGBDDataCatcher
                            public void onFrameDescriptor(ParcelFileDescriptor parcelFileDescriptor, int rows, int cols, int memorySize) {
                                AtomicBoolean atomicBoolean;
                                Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                                if (parcelFileDescriptor.getFd() != -1) {
                                    atomicBoolean = StressTestingActivity.this.downOpen;
                                    atomicBoolean.set(true);
                                }
                            }
                        });
                    }
                    StressTestingActivity stressTestingActivity = StressTestingActivity.this;
                    launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C56725(null), 3, null);
                    stressTestingActivity.switchCheckingJob = launch$default;
                }
            }

            /* JADX WARN: Classes with same name are omitted:
              classes5.dex
             */
            /* compiled from: StressTestingActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.rgbdlib.activity.StressTestingActivity$onCreate$3$5", m3970f = "StressTestingActivity.kt", m3971i = {0, 1}, m3972l = {184, 186}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
            /* renamed from: com.pudutech.rgbdlib.activity.StressTestingActivity$onCreate$3$5 */
            /* loaded from: classes6.dex */
            static final class C56725 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f7151p$;

                C56725(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C56725 c56725 = new C56725(completion);
                    c56725.f7151p$ = (CoroutineScope) obj;
                    return c56725;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C56725) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:11:0x00a4  */
                /* JADX WARN: Removed duplicated region for block: B:14:0x00be  */
                /* JADX WARN: Removed duplicated region for block: B:17:0x00d8  */
                /* JADX WARN: Removed duplicated region for block: B:21:0x003a  */
                /* JADX WARN: Removed duplicated region for block: B:31:0x006b  */
                /* JADX WARN: Removed duplicated region for block: B:34:0x0078 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:35:0x0079  */
                /* JADX WARN: Removed duplicated region for block: B:36:0x0281  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x008a  */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x0079 -> B:6:0x007c). Please report as a decompilation issue!!! */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final Object invokeSuspend(Object obj) {
                    CoroutineScope coroutineScope;
                    Object obj2;
                    C56725 c56725;
                    Object obj3;
                    CoroutineScope coroutineScope2;
                    RGBDSensor rgbdSensor;
                    int i;
                    AtomicBoolean atomicBoolean;
                    AtomicBoolean atomicBoolean2;
                    AtomicBoolean atomicBoolean3;
                    AtomicBoolean atomicBoolean4;
                    AtomicBoolean atomicBoolean5;
                    AtomicBoolean atomicBoolean6;
                    AtomicBoolean atomicBoolean7;
                    AtomicBoolean atomicBoolean8;
                    int i2;
                    int i3;
                    int i4;
                    int i5;
                    int i6;
                    int i7;
                    int i8;
                    int i9;
                    int i10;
                    int i11;
                    int i12;
                    int i13;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i14 = this.label;
                    if (i14 == 0) {
                        ResultKt.throwOnFailure(obj);
                        coroutineScope = this.f7151p$;
                        obj2 = coroutine_suspended;
                        c56725 = this;
                        if (CoroutineScopeKt.isActive(coroutineScope)) {
                        }
                    } else if (i14 == 1) {
                        coroutineScope2 = (CoroutineScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        obj3 = coroutine_suspended;
                        c56725 = this;
                        rgbdSensor = StressTestingActivity.INSTANCE.getRgbdSensor();
                        if (rgbdSensor != null) {
                        }
                        c56725.L$0 = coroutineScope2;
                        c56725.label = 2;
                        if (DelayKt.delay(SolicitService.CAMERA_OPEN_TIME_OUT, c56725) != obj3) {
                        }
                    } else if (i14 == 2) {
                        CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        coroutineScope = coroutineScope3;
                        obj2 = coroutine_suspended;
                        c56725 = this;
                        atomicBoolean = StressTestingActivity.this.leftOpen;
                        if (atomicBoolean.get()) {
                            StressTestingActivity stressTestingActivity = StressTestingActivity.this;
                            i13 = stressTestingActivity.totalLeftOpen;
                            stressTestingActivity.totalLeftOpen = i13 + 1;
                        }
                        atomicBoolean2 = StressTestingActivity.this.rightOpen;
                        if (atomicBoolean2.get()) {
                            StressTestingActivity stressTestingActivity2 = StressTestingActivity.this;
                            i12 = stressTestingActivity2.totalRightOpen;
                            stressTestingActivity2.totalRightOpen = i12 + 1;
                        }
                        atomicBoolean3 = StressTestingActivity.this.centerOpen;
                        if (atomicBoolean3.get()) {
                            StressTestingActivity stressTestingActivity3 = StressTestingActivity.this;
                            i11 = stressTestingActivity3.totalCenterOpen;
                            stressTestingActivity3.totalCenterOpen = i11 + 1;
                        }
                        atomicBoolean4 = StressTestingActivity.this.downOpen;
                        if (atomicBoolean4.get()) {
                            StressTestingActivity stressTestingActivity4 = StressTestingActivity.this;
                            i10 = stressTestingActivity4.totalDownOpen;
                            stressTestingActivity4.totalDownOpen = i10 + 1;
                        }
                        atomicBoolean5 = StressTestingActivity.this.leftOpen;
                        atomicBoolean5.set(false);
                        atomicBoolean6 = StressTestingActivity.this.rightOpen;
                        atomicBoolean6.set(false);
                        atomicBoolean7 = StressTestingActivity.this.centerOpen;
                        atomicBoolean7.set(false);
                        atomicBoolean8 = StressTestingActivity.this.downOpen;
                        atomicBoolean8.set(false);
                        TextView leftText = (TextView) StressTestingActivity.this._$_findCachedViewById(C5657R.id.leftText);
                        Intrinsics.checkExpressionValueIsNotNull(leftText, "leftText");
                        StringBuilder sb = new StringBuilder();
                        sb.append("Left Switch Test: ");
                        i2 = StressTestingActivity.this.totalLeftOpen;
                        sb.append(i2);
                        sb.append('/');
                        i3 = StressTestingActivity.this.totalIndex;
                        sb.append(i3);
                        leftText.setText(sb.toString());
                        StressTestingActivity stressTestingActivity5 = StressTestingActivity.this;
                        TextView leftText2 = (TextView) StressTestingActivity.this._$_findCachedViewById(C5657R.id.leftText);
                        Intrinsics.checkExpressionValueIsNotNull(leftText2, "leftText");
                        stressTestingActivity5.writeLog(leftText2.getText().toString());
                        TextView rightText = (TextView) StressTestingActivity.this._$_findCachedViewById(C5657R.id.rightText);
                        Intrinsics.checkExpressionValueIsNotNull(rightText, "rightText");
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Right Switch Test: ");
                        i4 = StressTestingActivity.this.totalRightOpen;
                        sb2.append(i4);
                        sb2.append('/');
                        i5 = StressTestingActivity.this.totalIndex;
                        sb2.append(i5);
                        rightText.setText(sb2.toString());
                        StressTestingActivity stressTestingActivity6 = StressTestingActivity.this;
                        TextView rightText2 = (TextView) StressTestingActivity.this._$_findCachedViewById(C5657R.id.rightText);
                        Intrinsics.checkExpressionValueIsNotNull(rightText2, "rightText");
                        stressTestingActivity6.writeLog(rightText2.getText().toString());
                        TextView centerText = (TextView) StressTestingActivity.this._$_findCachedViewById(C5657R.id.centerText);
                        Intrinsics.checkExpressionValueIsNotNull(centerText, "centerText");
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Center Switch Test: ");
                        i6 = StressTestingActivity.this.totalCenterOpen;
                        sb3.append(i6);
                        sb3.append('/');
                        i7 = StressTestingActivity.this.totalIndex;
                        sb3.append(i7);
                        centerText.setText(sb3.toString());
                        StressTestingActivity stressTestingActivity7 = StressTestingActivity.this;
                        TextView centerText2 = (TextView) StressTestingActivity.this._$_findCachedViewById(C5657R.id.centerText);
                        Intrinsics.checkExpressionValueIsNotNull(centerText2, "centerText");
                        stressTestingActivity7.writeLog(centerText2.getText().toString());
                        TextView downText = (TextView) StressTestingActivity.this._$_findCachedViewById(C5657R.id.downText);
                        Intrinsics.checkExpressionValueIsNotNull(downText, "downText");
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("Down Switch Test: ");
                        i8 = StressTestingActivity.this.totalDownOpen;
                        sb4.append(i8);
                        sb4.append('/');
                        i9 = StressTestingActivity.this.totalIndex;
                        sb4.append(i9);
                        downText.setText(sb4.toString());
                        StressTestingActivity stressTestingActivity8 = StressTestingActivity.this;
                        TextView downText2 = (TextView) StressTestingActivity.this._$_findCachedViewById(C5657R.id.downText);
                        Intrinsics.checkExpressionValueIsNotNull(downText2, "downText");
                        stressTestingActivity8.writeLog(downText2.getText().toString());
                        if (CoroutineScopeKt.isActive(coroutineScope)) {
                            StressTestingActivity stressTestingActivity9 = StressTestingActivity.this;
                            i = stressTestingActivity9.totalIndex;
                            stressTestingActivity9.totalIndex = i + 1;
                            RGBDSensor rgbdSensor2 = StressTestingActivity.INSTANCE.getRgbdSensor();
                            if (rgbdSensor2 != null) {
                                Boxing.boxBoolean(rgbdSensor2.openRGBD());
                            }
                            c56725.L$0 = coroutineScope;
                            c56725.label = 1;
                            if (DelayKt.delay(SolicitService.CAMERA_OPEN_TIME_OUT, c56725) == obj2) {
                                return obj2;
                            }
                            Object obj4 = obj2;
                            coroutineScope2 = coroutineScope;
                            obj3 = obj4;
                            rgbdSensor = StressTestingActivity.INSTANCE.getRgbdSensor();
                            if (rgbdSensor != null) {
                                rgbdSensor.closeRGBD();
                            }
                            c56725.L$0 = coroutineScope2;
                            c56725.label = 2;
                            if (DelayKt.delay(SolicitService.CAMERA_OPEN_TIME_OUT, c56725) != obj3) {
                                return obj3;
                            }
                            CoroutineScope coroutineScope4 = coroutineScope2;
                            obj2 = obj3;
                            coroutineScope = coroutineScope4;
                            atomicBoolean = StressTestingActivity.this.leftOpen;
                            if (atomicBoolean.get()) {
                            }
                            atomicBoolean2 = StressTestingActivity.this.rightOpen;
                            if (atomicBoolean2.get()) {
                            }
                            atomicBoolean3 = StressTestingActivity.this.centerOpen;
                            if (atomicBoolean3.get()) {
                            }
                            atomicBoolean4 = StressTestingActivity.this.downOpen;
                            if (atomicBoolean4.get()) {
                            }
                            atomicBoolean5 = StressTestingActivity.this.leftOpen;
                            atomicBoolean5.set(false);
                            atomicBoolean6 = StressTestingActivity.this.rightOpen;
                            atomicBoolean6.set(false);
                            atomicBoolean7 = StressTestingActivity.this.centerOpen;
                            atomicBoolean7.set(false);
                            atomicBoolean8 = StressTestingActivity.this.downOpen;
                            atomicBoolean8.set(false);
                            TextView leftText3 = (TextView) StressTestingActivity.this._$_findCachedViewById(C5657R.id.leftText);
                            Intrinsics.checkExpressionValueIsNotNull(leftText3, "leftText");
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append("Left Switch Test: ");
                            i2 = StressTestingActivity.this.totalLeftOpen;
                            sb5.append(i2);
                            sb5.append('/');
                            i3 = StressTestingActivity.this.totalIndex;
                            sb5.append(i3);
                            leftText3.setText(sb5.toString());
                            StressTestingActivity stressTestingActivity52 = StressTestingActivity.this;
                            TextView leftText22 = (TextView) StressTestingActivity.this._$_findCachedViewById(C5657R.id.leftText);
                            Intrinsics.checkExpressionValueIsNotNull(leftText22, "leftText");
                            stressTestingActivity52.writeLog(leftText22.getText().toString());
                            TextView rightText3 = (TextView) StressTestingActivity.this._$_findCachedViewById(C5657R.id.rightText);
                            Intrinsics.checkExpressionValueIsNotNull(rightText3, "rightText");
                            StringBuilder sb22 = new StringBuilder();
                            sb22.append("Right Switch Test: ");
                            i4 = StressTestingActivity.this.totalRightOpen;
                            sb22.append(i4);
                            sb22.append('/');
                            i5 = StressTestingActivity.this.totalIndex;
                            sb22.append(i5);
                            rightText3.setText(sb22.toString());
                            StressTestingActivity stressTestingActivity62 = StressTestingActivity.this;
                            TextView rightText22 = (TextView) StressTestingActivity.this._$_findCachedViewById(C5657R.id.rightText);
                            Intrinsics.checkExpressionValueIsNotNull(rightText22, "rightText");
                            stressTestingActivity62.writeLog(rightText22.getText().toString());
                            TextView centerText3 = (TextView) StressTestingActivity.this._$_findCachedViewById(C5657R.id.centerText);
                            Intrinsics.checkExpressionValueIsNotNull(centerText3, "centerText");
                            StringBuilder sb32 = new StringBuilder();
                            sb32.append("Center Switch Test: ");
                            i6 = StressTestingActivity.this.totalCenterOpen;
                            sb32.append(i6);
                            sb32.append('/');
                            i7 = StressTestingActivity.this.totalIndex;
                            sb32.append(i7);
                            centerText3.setText(sb32.toString());
                            StressTestingActivity stressTestingActivity72 = StressTestingActivity.this;
                            TextView centerText22 = (TextView) StressTestingActivity.this._$_findCachedViewById(C5657R.id.centerText);
                            Intrinsics.checkExpressionValueIsNotNull(centerText22, "centerText");
                            stressTestingActivity72.writeLog(centerText22.getText().toString());
                            TextView downText3 = (TextView) StressTestingActivity.this._$_findCachedViewById(C5657R.id.downText);
                            Intrinsics.checkExpressionValueIsNotNull(downText3, "downText");
                            StringBuilder sb42 = new StringBuilder();
                            sb42.append("Down Switch Test: ");
                            i8 = StressTestingActivity.this.totalDownOpen;
                            sb42.append(i8);
                            sb42.append('/');
                            i9 = StressTestingActivity.this.totalIndex;
                            sb42.append(i9);
                            downText3.setText(sb42.toString());
                            StressTestingActivity stressTestingActivity82 = StressTestingActivity.this;
                            TextView downText22 = (TextView) StressTestingActivity.this._$_findCachedViewById(C5657R.id.downText);
                            Intrinsics.checkExpressionValueIsNotNull(downText22, "downText");
                            stressTestingActivity82.writeLog(downText22.getText().toString());
                            if (CoroutineScopeKt.isActive(coroutineScope)) {
                                return Unit.INSTANCE;
                            }
                        }
                    } else {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void writeLog(String msg) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new StressTestingActivity$writeLog$1(this, msg, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getLeftFPS(int total) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new StressTestingActivity$getLeftFPS$1(this, total, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getRightFPS(int total) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new StressTestingActivity$getRightFPS$1(this, total, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getCenterFPS(int total) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new StressTestingActivity$getCenterFPS$1(this, total, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getDownFPS(int total) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new StressTestingActivity$getDownFPS$1(this, total, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeFPSCheck() {
        ThreadSafeListener downRGBDListeners;
        ThreadSafeListener<RGBDDataCatcher> centernRGBDListeners;
        ThreadSafeListener<RGBDDataCatcher> rightRGBDListeners;
        ThreadSafeListener<RGBDDataCatcher> leftRGBDListeners;
        RGBDSensor rGBDSensor = rgbdSensor;
        if (rGBDSensor != null && (leftRGBDListeners = rGBDSensor.getLeftRGBDListeners()) != null) {
            leftRGBDListeners.remove("fps_check");
        }
        RGBDSensor rGBDSensor2 = rgbdSensor;
        if (rGBDSensor2 != null && (rightRGBDListeners = rGBDSensor2.getRightRGBDListeners()) != null) {
            rightRGBDListeners.remove("fps_check");
        }
        RGBDSensor rGBDSensor3 = rgbdSensor;
        if (rGBDSensor3 != null && (centernRGBDListeners = rGBDSensor3.getCenternRGBDListeners()) != null) {
            centernRGBDListeners.remove("fps_check");
        }
        RGBDSensor rGBDSensor4 = rgbdSensor;
        if (rGBDSensor4 != null && (downRGBDListeners = rGBDSensor4.getDownRGBDListeners()) != null) {
            downRGBDListeners.remove("fps_check");
        }
        TextView leftText = (TextView) _$_findCachedViewById(C5657R.id.leftText);
        Intrinsics.checkExpressionValueIsNotNull(leftText, "leftText");
        leftText.setText("");
        TextView rightText = (TextView) _$_findCachedViewById(C5657R.id.rightText);
        Intrinsics.checkExpressionValueIsNotNull(rightText, "rightText");
        rightText.setText("");
        TextView centerText = (TextView) _$_findCachedViewById(C5657R.id.centerText);
        Intrinsics.checkExpressionValueIsNotNull(centerText, "centerText");
        centerText.setText("");
        TextView downText = (TextView) _$_findCachedViewById(C5657R.id.downText);
        Intrinsics.checkExpressionValueIsNotNull(downText, "downText");
        downText.setText("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeSwitchCheck() {
        ThreadSafeListener downRGBDListeners;
        ThreadSafeListener<RGBDDataCatcher> centernRGBDListeners;
        ThreadSafeListener<RGBDDataCatcher> rightRGBDListeners;
        ThreadSafeListener<RGBDDataCatcher> leftRGBDListeners;
        RGBDSensor rGBDSensor = rgbdSensor;
        if (rGBDSensor != null && (leftRGBDListeners = rGBDSensor.getLeftRGBDListeners()) != null) {
            leftRGBDListeners.remove(TrackKey.SWITCH_CHECK);
        }
        RGBDSensor rGBDSensor2 = rgbdSensor;
        if (rGBDSensor2 != null && (rightRGBDListeners = rGBDSensor2.getRightRGBDListeners()) != null) {
            rightRGBDListeners.remove(TrackKey.SWITCH_CHECK);
        }
        RGBDSensor rGBDSensor3 = rgbdSensor;
        if (rGBDSensor3 != null && (centernRGBDListeners = rGBDSensor3.getCenternRGBDListeners()) != null) {
            centernRGBDListeners.remove(TrackKey.SWITCH_CHECK);
        }
        RGBDSensor rGBDSensor4 = rgbdSensor;
        if (rGBDSensor4 != null && (downRGBDListeners = rGBDSensor4.getDownRGBDListeners()) != null) {
            downRGBDListeners.remove(TrackKey.SWITCH_CHECK);
        }
        TextView leftText = (TextView) _$_findCachedViewById(C5657R.id.leftText);
        Intrinsics.checkExpressionValueIsNotNull(leftText, "leftText");
        leftText.setText("");
        TextView rightText = (TextView) _$_findCachedViewById(C5657R.id.rightText);
        Intrinsics.checkExpressionValueIsNotNull(rightText, "rightText");
        rightText.setText("");
        TextView centerText = (TextView) _$_findCachedViewById(C5657R.id.centerText);
        Intrinsics.checkExpressionValueIsNotNull(centerText, "centerText");
        centerText.setText("");
        TextView downText = (TextView) _$_findCachedViewById(C5657R.id.downText);
        Intrinsics.checkExpressionValueIsNotNull(downText, "downText");
        downText.setText("");
    }
}
