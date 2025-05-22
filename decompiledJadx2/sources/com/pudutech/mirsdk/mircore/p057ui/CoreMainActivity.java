package com.pudutech.mirsdk.mircore.p057ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.base.CommonKt;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.RGBDInterface;
import com.pudutech.mirsdk.hardware.serialize.Vector2d;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.C5224R;
import com.pudutech.mirsdk.mircore.MirCoreImpl;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.MirCoreScopeKt;
import com.pudutech.mirsdk.mircore.NavigationInterface;
import com.pudutech.mirsdk.mircore.ScheduleInterface;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitState;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitStep;
import com.pudutech.mirsdk.mircore.coreparcel.MoveMode;
import com.pudutech.mirsdk.mircore.coreparcel.SmoothMode;
import com.pudutech.mirsdk.mircore.mirperception.Perception;
import com.pudutech.mirsdk.mircore.module.speedlevel.PlannerParamUtils;
import com.pudutech.mirsdk.mircore.p057ui.speedconfig.SpeedModeActivity;
import com.pudutech.mirsdk.mircore.p057ui.speedlevel.SettingCellView;
import com.pudutech.mirsdk.mircore.p057ui.speedlevel.StepSlider;
import com.pudutech.mirsdk.mircore.tools.AnalysisUtils;
import com.pudutech.mirsdk.sdksafe.SDKSafe;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: CoreMainActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 M2\u00020\u0001:\u0001MB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J.\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020\u001fJ\u000e\u0010#\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010$\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010%\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010&\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010'\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010(\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010)\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010*\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u001e\u0010+\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u00042\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00160.H\u0002J\b\u0010/\u001a\u00020\rH\u0002J\b\u00100\u001a\u00020\u0016H\u0002J\b\u00101\u001a\u00020\u0016H\u0002J\u0012\u00102\u001a\u00020\u00162\b\u00103\u001a\u0004\u0018\u000104H\u0014J\b\u00105\u001a\u00020\u0016H\u0014J\u0012\u00106\u001a\u00020\u00062\b\u00107\u001a\u0004\u0018\u000108H\u0016J\b\u00109\u001a\u00020\u0016H\u0014J\u000e\u0010:\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010;\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u0010\u0010<\u001a\u00020\u00162\u0006\u0010=\u001a\u00020\u0006H\u0002J\b\u0010>\u001a\u00020\u0016H\u0002J\b\u0010?\u001a\u00020\u0016H\u0002J\u0018\u0010@\u001a\u00020\u00162\u0006\u0010A\u001a\u00020\u001f2\u0006\u0010B\u001a\u00020\u001fH\u0002J\u001e\u0010C\u001a\u00020\u00162\u0006\u0010D\u001a\u00020\u001f2\u0006\u0010E\u001a\u00020\u001d2\u0006\u0010F\u001a\u00020\u001dJ\u000e\u0010G\u001a\u00020\u00162\u0006\u0010H\u001a\u00020\u001fJ\u0018\u0010I\u001a\u00020\u00162\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u00060\fj\b\u0012\u0004\u0012\u00020\u0006`\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006N"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/ui/CoreMainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "begin_recognize", "", "connect_status", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface;", "dockPosArray", "Ljava/util/ArrayList;", "Lcom/pudutech/mirsdk/hardware/serialize/Vector2d;", "Lkotlin/collections/ArrayList;", "hardwareService", "Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "recJob", "Lkotlinx/coroutines/Job;", "recognizeArray", "update_params_loop", "BeginRecognize", "", "v", "Landroid/view/View;", "DataClean", "DataRecord", "FaceCallBack", "flag", "", "yaw", "", "pitch", "dist", "angle", "FaceDetect", "FinishRecognize", "OpenCamera", "OpenCore", "SetBrakeLevel", "ShowCostmap", "ShowNoiseDetect", "SpeedParams", "checkAuth", "node", "callback", "Lkotlin/Function0;", "getAnalysisData", "initSlider", "invisible", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onResume", "onWaitTakeMethodClicked", "setReflactorParam", "show", "status", "startLoop", "startRecognizeLoop", "updateRecognizationRate", "rate", "stdr", "updateReflactorInfo", "distance", "level", "isInDangrousRegion", "updateRoadWidthInfo", "width", "updateStatus", "pose", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "speeds", "Companion", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CoreMainActivity extends AppCompatActivity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static boolean camera_open;
    private static boolean data_record;
    private static boolean face_det;
    private HashMap _$_findViewCache;
    private boolean begin_recognize;
    private boolean connect_status;
    private Job recJob;
    private boolean update_params_loop;
    private final String TAG = "CoreMainActivity";
    private ArrayList<Boolean> recognizeArray = new ArrayList<>();
    private ArrayList<Vector2d> dockPosArray = new ArrayList<>();
    private final AIDLConnection<MirCoreInterface> coreService = new AIDLConnection<>("com.pudutech.mirsdk.mircore.MirCoreService", CoreMainActivity$coreService$1.INSTANCE, null, 4, null);
    private final AIDLConnection<HardwareInterface> hardwareService = new AIDLConnection<>("com.pudutech.mirsdk.hardware.HardwareService", CoreMainActivity$hardwareService$1.INSTANCE, null, 4, null);

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SmoothMode.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;

        static {
            $EnumSwitchMapping$0[SmoothMode.NoSmooth.ordinal()] = 1;
            $EnumSwitchMapping$0[SmoothMode.LightLoad.ordinal()] = 2;
            $EnumSwitchMapping$0[SmoothMode.HeavyLoad.ordinal()] = 3;
            $EnumSwitchMapping$1 = new int[CoreInitState.values().length];
            $EnumSwitchMapping$1[CoreInitState.Success.ordinal()] = 1;
            $EnumSwitchMapping$2 = new int[CoreInitState.values().length];
            $EnumSwitchMapping$2[CoreInitState.Fail.ordinal()] = 1;
            $EnumSwitchMapping$3 = new int[CoreInitStep.values().length];
            $EnumSwitchMapping$3[CoreInitStep.Finish.ordinal()] = 1;
            $EnumSwitchMapping$4 = new int[SmoothMode.values().length];
            $EnumSwitchMapping$4[SmoothMode.NoSmooth.ordinal()] = 1;
            $EnumSwitchMapping$4[SmoothMode.LightLoad.ordinal()] = 2;
            $EnumSwitchMapping$4[SmoothMode.HeavyLoad.ordinal()] = 3;
            $EnumSwitchMapping$5 = new int[SmoothMode.values().length];
            $EnumSwitchMapping$5[SmoothMode.NoSmooth.ordinal()] = 1;
            $EnumSwitchMapping$5[SmoothMode.LightLoad.ordinal()] = 2;
            $EnumSwitchMapping$5[SmoothMode.HeavyLoad.ordinal()] = 3;
        }
    }

    public final void OpenCore(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
    }

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
      classes4.dex
     */
    /* compiled from: CoreMainActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\b¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/ui/CoreMainActivity$Companion;", "", "()V", "camera_open", "", "getCamera_open", "()Z", "setCamera_open", "(Z)V", "data_record", "getData_record", "setData_record", "face_det", "getFace_det", "setFace_det", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean getData_record() {
            return CoreMainActivity.data_record;
        }

        public final void setData_record(boolean z) {
            CoreMainActivity.data_record = z;
        }

        public final boolean getCamera_open() {
            return CoreMainActivity.camera_open;
        }

        public final void setCamera_open(boolean z) {
            CoreMainActivity.camera_open = z;
        }

        public final boolean getFace_det() {
            return CoreMainActivity.face_det;
        }

        public final void setFace_det(boolean z) {
            CoreMainActivity.face_det = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00ce  */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onCreate(Bundle savedInstanceState) {
        int i;
        int parseInt;
        int parseInt2;
        super.onCreate(savedInstanceState);
        Pdlog.m3273d(this.TAG, "onCreate Core Activity");
        setContentView(C5224R.layout.sdk_core_main);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (MirCoreImpl.INSTANCE.getInit_modules_status()) {
            this.connect_status = true;
            show(true);
            startLoop();
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new CoreMainActivity$onCreate$1(this, null), 3, null);
        } else {
            this.connect_status = false;
            invisible();
        }
        ((StepSlider) _$_findCachedViewById(C5224R.id.direct_speed_slider)).setOnStepClickListener(new StepSlider.OnStepClickListener() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$onCreate$2
            @Override // com.pudutech.mirsdk.mircore.ui.speedlevel.StepSlider.OnStepClickListener
            public final void onStepClicked(final int i2) {
                String str;
                str = CoreMainActivity.this.TAG;
                Pdlog.m3273d(str, "直达速度 " + CoreMainActivity.this.getResources().getStringArray(C5224R.array.settings_speed_steps)[i2]);
                CoreMainActivity.this.checkAuth("dvec", new Function0<Unit>() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$onCreate$2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        AIDLConnection aIDLConnection;
                        NavigationInterface navigator;
                        aIDLConnection = CoreMainActivity.this.coreService;
                        MirCoreInterface mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface();
                        if (mirCoreInterface == null || (navigator = mirCoreInterface.getNavigator()) == null) {
                            return;
                        }
                        navigator.switchSpeedLevel(MoveMode.Direct, String.valueOf(PlannerParamUtils.INSTANCE.getMaxSpeedArray().get(i2).doubleValue()));
                    }
                });
            }
        });
        ((StepSlider) _$_findCachedViewById(C5224R.id.cruise_speed_slider)).setOnStepClickListener(new StepSlider.OnStepClickListener() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$onCreate$3
            @Override // com.pudutech.mirsdk.mircore.ui.speedlevel.StepSlider.OnStepClickListener
            public final void onStepClicked(final int i2) {
                String str;
                str = CoreMainActivity.this.TAG;
                Pdlog.m3273d(str, "巡航速度 " + CoreMainActivity.this.getResources().getStringArray(C5224R.array.settings_speed_steps)[i2]);
                CoreMainActivity.this.checkAuth("cvec", new Function0<Unit>() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$onCreate$3.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        AIDLConnection aIDLConnection;
                        NavigationInterface navigator;
                        aIDLConnection = CoreMainActivity.this.coreService;
                        MirCoreInterface mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface();
                        if (mirCoreInterface == null || (navigator = mirCoreInterface.getNavigator()) == null) {
                            return;
                        }
                        navigator.switchSpeedLevel(MoveMode.Cruise, String.valueOf(PlannerParamUtils.INSTANCE.getMaxSpeedArray().get(i2).doubleValue()));
                    }
                });
            }
        });
        ((StepSlider) _$_findCachedViewById(C5224R.id.gohome_speed_slider)).setOnStepClickListener(new StepSlider.OnStepClickListener() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$onCreate$4
            @Override // com.pudutech.mirsdk.mircore.ui.speedlevel.StepSlider.OnStepClickListener
            public final void onStepClicked(final int i2) {
                String str;
                str = CoreMainActivity.this.TAG;
                Pdlog.m3273d(str, "返航速度 " + CoreMainActivity.this.getResources().getStringArray(C5224R.array.settings_speed_steps)[i2]);
                CoreMainActivity.this.checkAuth("hdev", new Function0<Unit>() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$onCreate$4.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        AIDLConnection aIDLConnection;
                        NavigationInterface navigator;
                        aIDLConnection = CoreMainActivity.this.coreService;
                        MirCoreInterface mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface();
                        if (mirCoreInterface == null || (navigator = mirCoreInterface.getNavigator()) == null) {
                            return;
                        }
                        navigator.switchSpeedLevel(MoveMode.GoHome, String.valueOf(PlannerParamUtils.INSTANCE.getMaxSpeedArray().get(i2).doubleValue()));
                    }
                });
            }
        });
        TextView tx_brake_level = (TextView) _$_findCachedViewById(C5224R.id.tx_brake_level);
        Intrinsics.checkExpressionValueIsNotNull(tx_brake_level, "tx_brake_level");
        SmoothMode smoothMode = MirCoreImpl.INSTANCE.getSmoothMode();
        if (smoothMode != null) {
            int i2 = WhenMappings.$EnumSwitchMapping$0[smoothMode.ordinal()];
            if (i2 == 1) {
                i = 1;
            } else if (i2 == 2) {
                i = 2;
            } else if (i2 == 3) {
                i = 3;
            }
            tx_brake_level.setText(String.valueOf(i));
            if (!data_record) {
                Button bt_record_data = (Button) _$_findCachedViewById(C5224R.id.bt_record_data);
                Intrinsics.checkExpressionValueIsNotNull(bt_record_data, "bt_record_data");
                bt_record_data.setText("结束录制");
            } else {
                Button bt_record_data2 = (Button) _$_findCachedViewById(C5224R.id.bt_record_data);
                Intrinsics.checkExpressionValueIsNotNull(bt_record_data2, "bt_record_data");
                bt_record_data2.setText("开始录制");
            }
            if (!face_det) {
                Button bt_face_det = (Button) _$_findCachedViewById(C5224R.id.bt_face_det);
                Intrinsics.checkExpressionValueIsNotNull(bt_face_det, "bt_face_det");
                bt_face_det.setText("结束人脸检测");
            } else {
                Button bt_face_det2 = (Button) _$_findCachedViewById(C5224R.id.bt_face_det);
                Intrinsics.checkExpressionValueIsNotNull(bt_face_det2, "bt_face_det");
                bt_face_det2.setText("开启人脸检测");
            }
            if (!camera_open) {
                Button bt_open_camera = (Button) _$_findCachedViewById(C5224R.id.bt_open_camera);
                Intrinsics.checkExpressionValueIsNotNull(bt_open_camera, "bt_open_camera");
                bt_open_camera.setText("关闭相机");
            } else {
                Button bt_open_camera2 = (Button) _$_findCachedViewById(C5224R.id.bt_open_camera);
                Intrinsics.checkExpressionValueIsNotNull(bt_open_camera2, "bt_open_camera");
                bt_open_camera2.setText("打开相机");
            }
            Pdlog.m3273d(this.TAG, "Perception.addVisionListener");
            Perception.INSTANCE.addVisionListener("face_det", new Function5<Integer, Double, Double, Double, Double, Unit>() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$onCreate$5
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(5);
                }

                @Override // kotlin.jvm.functions.Function5
                public /* bridge */ /* synthetic */ Unit invoke(Integer num, Double d, Double d2, Double d3, Double d4) {
                    invoke(num.intValue(), d.doubleValue(), d2.doubleValue(), d3.doubleValue(), d4.doubleValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i3, double d, double d2, double d3, double d4) {
                    CoreMainActivity.this.FaceCallBack(i3, d, d2, d3, d4);
                }
            });
            Perception.INSTANCE.addReflectorDetectListener("reflactor_detect", new Function3<Double, Integer, Integer, Unit>() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$onCreate$6
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Double d, Integer num, Integer num2) {
                    invoke(d.doubleValue(), num.intValue(), num2.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(double d, int i3, int i4) {
                    CoreMainActivity.this.updateReflactorInfo(d, i3, i4);
                }
            });
            Perception.INSTANCE.addRoadWidthMeasureListener("roadwidth_measure", new Function1<Double, Unit>() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$onCreate$7
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Double d) {
                    invoke(d.doubleValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(double d) {
                    CoreMainActivity.this.updateRoadWidthInfo(d);
                }
            });
            if (new File("sdcard/pudu/config/feasibal_segment.config").exists()) {
                File file = new File("sdcard/pudu/config/");
                if (!file.exists()) {
                    Pdlog.m3273d(this.TAG, "create folder: ", file);
                    file.mkdir();
                }
                String str = Perception.INSTANCE.switchFeasibalRegionSeg(false) ? "1" : "0";
                FilesKt.writeText$default(new File("sdcard/pudu/config/feasibal_segment.config"), str, null, 2, null);
                parseInt = Integer.parseInt(str);
            } else {
                parseInt = Integer.parseInt(FilesKt.readText$default(new File("sdcard/pudu/config/feasibal_segment.config"), null, 1, null));
            }
            if (parseInt != 1) {
                ((Switch) _$_findCachedViewById(C5224R.id.switchUseCapeSeg)).setChecked(false);
            } else {
                ((Switch) _$_findCachedViewById(C5224R.id.switchUseCapeSeg)).setChecked(true);
            }
            ((Switch) _$_findCachedViewById(C5224R.id.switchUseCapeSeg)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$onCreate$8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Perception perception = Perception.INSTANCE;
                    Switch switchUseCapeSeg = (Switch) CoreMainActivity.this._$_findCachedViewById(C5224R.id.switchUseCapeSeg);
                    Intrinsics.checkExpressionValueIsNotNull(switchUseCapeSeg, "switchUseCapeSeg");
                    if (perception.switchFeasibalRegionSeg(switchUseCapeSeg.isChecked())) {
                        File file2 = new File("sdcard/pudu/config/feasibal_segment.config");
                        Switch switchUseCapeSeg2 = (Switch) CoreMainActivity.this._$_findCachedViewById(C5224R.id.switchUseCapeSeg);
                        Intrinsics.checkExpressionValueIsNotNull(switchUseCapeSeg2, "switchUseCapeSeg");
                        FilesKt.writeText$default(file2, switchUseCapeSeg2.isChecked() ? "0" : "1", null, 2, null);
                        return;
                    }
                    Switch r4 = (Switch) CoreMainActivity.this._$_findCachedViewById(C5224R.id.switchUseCapeSeg);
                    Switch switchUseCapeSeg3 = (Switch) CoreMainActivity.this._$_findCachedViewById(C5224R.id.switchUseCapeSeg);
                    Intrinsics.checkExpressionValueIsNotNull(switchUseCapeSeg3, "switchUseCapeSeg");
                    r4.setChecked(!switchUseCapeSeg3.isChecked());
                }
            });
            if (new File("sdcard/pudu/config/dynamic.config").exists()) {
                File file2 = new File("sdcard/pudu/config/");
                if (!file2.exists()) {
                    Pdlog.m3273d(this.TAG, "create folder: ", file2);
                    file2.mkdir();
                }
                String str2 = Perception.INSTANCE.switchDynamic(true) ? "1" : "0";
                FilesKt.writeText$default(new File("sdcard/pudu/config/dynamic.config"), str2, null, 2, null);
                parseInt2 = Integer.parseInt(str2);
            } else {
                parseInt2 = Integer.parseInt(FilesKt.readText$default(new File("sdcard/pudu/config/dynamic.config"), null, 1, null));
            }
            if (parseInt2 != 0) {
                ((Switch) _$_findCachedViewById(C5224R.id.switchUseDynamic)).setChecked(false);
            } else {
                ((Switch) _$_findCachedViewById(C5224R.id.switchUseDynamic)).setChecked(true);
            }
            ((Switch) _$_findCachedViewById(C5224R.id.switchUseDynamic)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$onCreate$9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Perception perception = Perception.INSTANCE;
                    Switch switchUseDynamic = (Switch) CoreMainActivity.this._$_findCachedViewById(C5224R.id.switchUseDynamic);
                    Intrinsics.checkExpressionValueIsNotNull(switchUseDynamic, "switchUseDynamic");
                    if (perception.switchDynamic(switchUseDynamic.isChecked())) {
                        File file3 = new File("sdcard/pudu/config/dynamic.config");
                        Switch switchUseDynamic2 = (Switch) CoreMainActivity.this._$_findCachedViewById(C5224R.id.switchUseDynamic);
                        Intrinsics.checkExpressionValueIsNotNull(switchUseDynamic2, "switchUseDynamic");
                        FilesKt.writeText$default(file3, switchUseDynamic2.isChecked() ? "1" : "0", null, 2, null);
                        return;
                    }
                    Switch r4 = (Switch) CoreMainActivity.this._$_findCachedViewById(C5224R.id.switchUseDynamic);
                    Switch switchUseDynamic3 = (Switch) CoreMainActivity.this._$_findCachedViewById(C5224R.id.switchUseDynamic);
                    Intrinsics.checkExpressionValueIsNotNull(switchUseDynamic3, "switchUseDynamic");
                    r4.setChecked(!switchUseDynamic3.isChecked());
                }
            });
        }
        i = -1;
        tx_brake_level.setText(String.valueOf(i));
        if (!data_record) {
        }
        if (!face_det) {
        }
        if (!camera_open) {
        }
        Pdlog.m3273d(this.TAG, "Perception.addVisionListener");
        Perception.INSTANCE.addVisionListener("face_det", new Function5<Integer, Double, Double, Double, Double, Unit>() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$onCreate$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(5);
            }

            @Override // kotlin.jvm.functions.Function5
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, Double d, Double d2, Double d3, Double d4) {
                invoke(num.intValue(), d.doubleValue(), d2.doubleValue(), d3.doubleValue(), d4.doubleValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i3, double d, double d2, double d3, double d4) {
                CoreMainActivity.this.FaceCallBack(i3, d, d2, d3, d4);
            }
        });
        Perception.INSTANCE.addReflectorDetectListener("reflactor_detect", new Function3<Double, Integer, Integer, Unit>() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$onCreate$6
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(Double d, Integer num, Integer num2) {
                invoke(d.doubleValue(), num.intValue(), num2.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(double d, int i3, int i4) {
                CoreMainActivity.this.updateReflactorInfo(d, i3, i4);
            }
        });
        Perception.INSTANCE.addRoadWidthMeasureListener("roadwidth_measure", new Function1<Double, Unit>() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$onCreate$7
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Double d) {
                invoke(d.doubleValue());
                return Unit.INSTANCE;
            }

            public final void invoke(double d) {
                CoreMainActivity.this.updateRoadWidthInfo(d);
            }
        });
        if (new File("sdcard/pudu/config/feasibal_segment.config").exists()) {
        }
        if (parseInt != 1) {
        }
        ((Switch) _$_findCachedViewById(C5224R.id.switchUseCapeSeg)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$onCreate$8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Perception perception = Perception.INSTANCE;
                Switch switchUseCapeSeg = (Switch) CoreMainActivity.this._$_findCachedViewById(C5224R.id.switchUseCapeSeg);
                Intrinsics.checkExpressionValueIsNotNull(switchUseCapeSeg, "switchUseCapeSeg");
                if (perception.switchFeasibalRegionSeg(switchUseCapeSeg.isChecked())) {
                    File file22 = new File("sdcard/pudu/config/feasibal_segment.config");
                    Switch switchUseCapeSeg2 = (Switch) CoreMainActivity.this._$_findCachedViewById(C5224R.id.switchUseCapeSeg);
                    Intrinsics.checkExpressionValueIsNotNull(switchUseCapeSeg2, "switchUseCapeSeg");
                    FilesKt.writeText$default(file22, switchUseCapeSeg2.isChecked() ? "0" : "1", null, 2, null);
                    return;
                }
                Switch r4 = (Switch) CoreMainActivity.this._$_findCachedViewById(C5224R.id.switchUseCapeSeg);
                Switch switchUseCapeSeg3 = (Switch) CoreMainActivity.this._$_findCachedViewById(C5224R.id.switchUseCapeSeg);
                Intrinsics.checkExpressionValueIsNotNull(switchUseCapeSeg3, "switchUseCapeSeg");
                r4.setChecked(!switchUseCapeSeg3.isChecked());
            }
        });
        if (new File("sdcard/pudu/config/dynamic.config").exists()) {
        }
        if (parseInt2 != 0) {
        }
        ((Switch) _$_findCachedViewById(C5224R.id.switchUseDynamic)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$onCreate$9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Perception perception = Perception.INSTANCE;
                Switch switchUseDynamic = (Switch) CoreMainActivity.this._$_findCachedViewById(C5224R.id.switchUseDynamic);
                Intrinsics.checkExpressionValueIsNotNull(switchUseDynamic, "switchUseDynamic");
                if (perception.switchDynamic(switchUseDynamic.isChecked())) {
                    File file3 = new File("sdcard/pudu/config/dynamic.config");
                    Switch switchUseDynamic2 = (Switch) CoreMainActivity.this._$_findCachedViewById(C5224R.id.switchUseDynamic);
                    Intrinsics.checkExpressionValueIsNotNull(switchUseDynamic2, "switchUseDynamic");
                    FilesKt.writeText$default(file3, switchUseDynamic2.isChecked() ? "1" : "0", null, 2, null);
                    return;
                }
                Switch r4 = (Switch) CoreMainActivity.this._$_findCachedViewById(C5224R.id.switchUseDynamic);
                Switch switchUseDynamic3 = (Switch) CoreMainActivity.this._$_findCachedViewById(C5224R.id.switchUseDynamic);
                Intrinsics.checkExpressionValueIsNotNull(switchUseDynamic3, "switchUseDynamic");
                r4.setChecked(!switchUseDynamic3.isChecked());
            }
        });
    }

    public final void FaceCallBack(final int flag, final double yaw, final double pitch, final double dist, final double angle) {
        runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$FaceCallBack$1
            @Override // java.lang.Runnable
            public final void run() {
                TextView face_result_txt = (TextView) CoreMainActivity.this._$_findCachedViewById(C5224R.id.face_result_txt);
                Intrinsics.checkExpressionValueIsNotNull(face_result_txt, "face_result_txt");
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] objArr = {Integer.valueOf(flag), Double.valueOf(yaw), Double.valueOf(pitch), Double.valueOf(dist), Double.valueOf(angle)};
                String format = String.format(" Flag: %d Yaw: %.2f Pitch: %.2f Dist: %.2f Angle: %.2f", Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                face_result_txt.setText(format);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkAuth(String node, final Function0<Unit> callback) {
        SDKSafe.INSTANCE.checkControlAuth(this, node, new Function1<Integer, Unit>() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$checkAuth$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                if (i != 0) {
                    return;
                }
                Function0.this.invoke();
            }
        });
    }

    public final void SpeedParams(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        Pdlog.m3273d(this.TAG, "connect status " + this.connect_status);
        if (this.connect_status) {
            checkAuth("navpm", new Function0<Unit>() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$SpeedParams$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: Classes with same name are omitted:
                  classes4.dex
                 */
                /* compiled from: CoreMainActivity.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.ui.CoreMainActivity$SpeedParams$1$1", m3970f = "CoreMainActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                /* renamed from: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$SpeedParams$1$1 */
                /* loaded from: classes6.dex */
                public static final class C52481 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f6264p$;

                    C52481(Continuation continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        C52481 c52481 = new C52481(completion);
                        c52481.f6264p$ = (CoroutineScope) obj;
                        return c52481;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C52481) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f6264p$;
                        CoreMainActivity.this.startActivity(new Intent(CoreMainActivity.this, (Class<?>) SpeedModeActivity.class));
                        return Unit.INSTANCE;
                    }
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    BuildersKt__Builders_commonKt.launch$default(MirCoreScopeKt.getMirCoreScope(), Dispatchers.getMain(), null, new C52481(null), 2, null);
                }
            });
        }
    }

    public final void ShowCostmap(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        if (this.connect_status) {
            Pdlog.m3273d(this.TAG, "start costmap show view");
            startActivity(new Intent(this, (Class<?>) ShowCostmapActivity.class));
        }
    }

    public final void ShowNoiseDetect(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        if (this.connect_status) {
            Pdlog.m3273d(this.TAG, "start show noise detect view");
            Intent intent = new Intent(this, (Class<?>) ShowNoiseDetectActivity.class);
            Pdlog.m3273d(this.TAG, "finish construct intent");
            startActivity(intent);
        }
    }

    public final void BeginRecognize(View v) {
        RGBDInterface rGBDInterface;
        Intrinsics.checkParameterIsNotNull(v, "v");
        Pdlog.m3273d(this.TAG, "begin recognize parking marker");
        HardwareInterface hardwareInterface = this.hardwareService.getInterface();
        boolean isEnabled = (hardwareInterface == null || (rGBDInterface = hardwareInterface.getRGBDInterface()) == null) ? false : rGBDInterface.isEnabled();
        if (this.connect_status && !this.begin_recognize && isEnabled) {
            View findViewById = findViewById(C5224R.id.marker_id);
            if (findViewById == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.EditText");
            }
            Integer intOrNull = StringsKt.toIntOrNull(((EditText) findViewById).getText().toString());
            if (intOrNull == null || !isEnabled) {
                if (isEnabled) {
                    Pdlog.m3273d(this.TAG, "do not have rgbd");
                    return;
                }
                Pdlog.m3273d(this.TAG, "invalid marker id " + intOrNull);
                return;
            }
            Perception.INSTANCE.setDockingSwitch(true, intOrNull.intValue());
            HardwareInterface hardwareInterface2 = this.hardwareService.getInterface();
            if (hardwareInterface2 != null) {
                hardwareInterface2.setRgbdParkingMode(true);
            }
            this.begin_recognize = true;
            startRecognizeLoop();
            Pdlog.m3273d(this.TAG, "marker_id text is " + intOrNull);
        }
    }

    public final void onWaitTakeMethodClicked(View v) {
        ScheduleInterface scheduler;
        ScheduleInterface scheduler2;
        Intrinsics.checkParameterIsNotNull(v, "v");
        RadioButton radio_wait_take_by_pos = (RadioButton) _$_findCachedViewById(C5224R.id.radio_wait_take_by_pos);
        Intrinsics.checkExpressionValueIsNotNull(radio_wait_take_by_pos, "radio_wait_take_by_pos");
        if (radio_wait_take_by_pos.isChecked()) {
            Pdlog.m3273d(this.TAG, "set criterion no_task robot: by pos-goal, param {\"method_no_task\": 1}");
            MirCoreInterface mirCoreInterface = this.coreService.getInterface();
            if (mirCoreInterface != null && (scheduler2 = mirCoreInterface.getScheduler()) != null) {
                scheduler2.setGroupTaskParam("{\"method_no_task\": 1}");
            }
        }
        RadioButton radio_wait_take_by_sch = (RadioButton) _$_findCachedViewById(C5224R.id.radio_wait_take_by_sch);
        Intrinsics.checkExpressionValueIsNotNull(radio_wait_take_by_sch, "radio_wait_take_by_sch");
        if (radio_wait_take_by_sch.isChecked()) {
            Pdlog.m3273d(this.TAG, "set criterion no_task robot: by sch mode, param {\"method_no_task\": 0}");
            MirCoreInterface mirCoreInterface2 = this.coreService.getInterface();
            if (mirCoreInterface2 == null || (scheduler = mirCoreInterface2.getScheduler()) == null) {
                return;
            }
            scheduler.setGroupTaskParam("{\"method_no_task\": 0}");
        }
    }

    public final void SetBrakeLevel(View v) {
        NavigationInterface navigator;
        SmoothMode smoothMode;
        int i;
        NavigationInterface navigator2;
        Intrinsics.checkParameterIsNotNull(v, "v");
        int i2 = 1;
        Pdlog.m3273d(this.TAG, "set brake level");
        if (this.connect_status) {
            View findViewById = findViewById(C5224R.id.brake_level);
            if (findViewById == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.EditText");
            }
            Integer intOrNull = StringsKt.toIntOrNull(((EditText) findViewById).getText().toString());
            if (intOrNull == null) {
                smoothMode = SmoothMode.LightLoad;
            } else if (intOrNull.intValue() == 1) {
                smoothMode = SmoothMode.NoSmooth;
            } else if (intOrNull.intValue() == 2) {
                smoothMode = SmoothMode.LightLoad;
            } else {
                smoothMode = intOrNull.intValue() == 3 ? SmoothMode.HeavyLoad : SmoothMode.LightLoad;
            }
            MirCoreInterface mirCoreInterface = this.coreService.getInterface();
            if (mirCoreInterface != null && (navigator2 = mirCoreInterface.getNavigator()) != null) {
                navigator2.updateSmoothMode(smoothMode);
            }
            int i3 = WhenMappings.$EnumSwitchMapping$4[smoothMode.ordinal()];
            if (i3 == 1) {
                i = 1;
            } else if (i3 == 2) {
                i = 2;
            } else {
                if (i3 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                i = 3;
            }
            Toast.makeText(this, "setting brake level to " + i + ':' + smoothMode, 0).show();
            String str = this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("set brake level to ");
            sb.append(smoothMode);
            Pdlog.m3273d(str, sb.toString());
        } else {
            Pdlog.m3273d(this.TAG, "connect_status error, cannot set brake level");
        }
        TextView tx_brake_level = (TextView) _$_findCachedViewById(C5224R.id.tx_brake_level);
        Intrinsics.checkExpressionValueIsNotNull(tx_brake_level, "tx_brake_level");
        MirCoreInterface mirCoreInterface2 = this.coreService.getInterface();
        SmoothMode smoothMode2 = (mirCoreInterface2 == null || (navigator = mirCoreInterface2.getNavigator()) == null) ? null : navigator.getSmoothMode();
        if (smoothMode2 != null) {
            int i4 = WhenMappings.$EnumSwitchMapping$5[smoothMode2.ordinal()];
            if (i4 != 1) {
                if (i4 == 2) {
                    i2 = 2;
                } else if (i4 == 3) {
                    i2 = 3;
                }
            }
            tx_brake_level.setText(String.valueOf(i2));
        }
        i2 = -1;
        tx_brake_level.setText(String.valueOf(i2));
    }

    public final void FinishRecognize(View v) {
        RGBDInterface rGBDInterface;
        Intrinsics.checkParameterIsNotNull(v, "v");
        Pdlog.m3273d(this.TAG, "finish recognize parking marker");
        HardwareInterface hardwareInterface = this.hardwareService.getInterface();
        boolean isEnabled = (hardwareInterface == null || (rGBDInterface = hardwareInterface.getRGBDInterface()) == null) ? false : rGBDInterface.isEnabled();
        if (this.connect_status && this.begin_recognize && isEnabled) {
            this.begin_recognize = false;
            BuildersKt__Builders_commonKt.launch$default(MirCoreScopeKt.getMirCoreScope(), null, null, new CoreMainActivity$FinishRecognize$1(this, null), 3, null);
            this.recognizeArray.clear();
            this.dockPosArray.clear();
            HardwareInterface hardwareInterface2 = this.hardwareService.getInterface();
            if (hardwareInterface2 != null) {
                hardwareInterface2.setRgbdParkingMode(false);
            }
        }
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        Integer valueOf = item != null ? Integer.valueOf(item.getItemId()) : null;
        if (valueOf != null && valueOf.intValue() == 16908332) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        initSlider();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.update_params_loop = false;
    }

    private final void initSlider() {
        String p2P_Mode = PlannerParamUtils.INSTANCE.getP2P_Mode();
        String cruise_Mode = PlannerParamUtils.INSTANCE.getCruise_Mode();
        String gohome_Mode = PlannerParamUtils.INSTANCE.getGohome_Mode();
        boolean contains = PlannerParamUtils.INSTANCE.getSpeedLevels().contains(p2P_Mode);
        boolean contains2 = PlannerParamUtils.INSTANCE.getSpeedLevels().contains(cruise_Mode);
        boolean contains3 = PlannerParamUtils.INSTANCE.getSpeedLevels().contains(gohome_Mode);
        if (!contains) {
            PlannerParamUtils.INSTANCE.setDeliverToNormal();
        }
        if (!contains2) {
            PlannerParamUtils.INSTANCE.setCruiseToNormal();
        }
        if (!contains3) {
            PlannerParamUtils.INSTANCE.setGoHomeToNormal();
        }
        StepSlider stepSlider = (StepSlider) _$_findCachedViewById(C5224R.id.direct_speed_slider);
        ArrayList<String> speedLevels = PlannerParamUtils.INSTANCE.getSpeedLevels();
        if (!contains) {
            p2P_Mode = "Normal";
        }
        stepSlider.setCurStep(speedLevels.indexOf(p2P_Mode));
        StepSlider stepSlider2 = (StepSlider) _$_findCachedViewById(C5224R.id.cruise_speed_slider);
        ArrayList<String> speedLevels2 = PlannerParamUtils.INSTANCE.getSpeedLevels();
        if (!contains2) {
            cruise_Mode = "Normal";
        }
        stepSlider2.setCurStep(speedLevels2.indexOf(cruise_Mode));
        StepSlider stepSlider3 = (StepSlider) _$_findCachedViewById(C5224R.id.gohome_speed_slider);
        ArrayList<String> speedLevels3 = PlannerParamUtils.INSTANCE.getSpeedLevels();
        if (!contains3) {
            gohome_Mode = "Normal";
        }
        stepSlider3.setCurStep(speedLevels3.indexOf(gohome_Mode));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void show(boolean status) {
        if (status) {
            this.update_params_loop = true;
            Button core_service_bt = (Button) _$_findCachedViewById(C5224R.id.core_service_bt);
            Intrinsics.checkExpressionValueIsNotNull(core_service_bt, "core_service_bt");
            core_service_bt.setText("关闭");
            TextView core_linker_count = (TextView) _$_findCachedViewById(C5224R.id.core_linker_count);
            Intrinsics.checkExpressionValueIsNotNull(core_linker_count, "core_linker_count");
            core_linker_count.setText("LinkSuccess");
            updateStatus(MirCoreImpl.INSTANCE.getPosition$mircore_packRelease(), MirCoreImpl.INSTANCE.getSpeeds$mircore_packRelease());
            Button switch_speed_view = (Button) _$_findCachedViewById(C5224R.id.switch_speed_view);
            Intrinsics.checkExpressionValueIsNotNull(switch_speed_view, "switch_speed_view");
            switch_speed_view.setVisibility(0);
            SettingCellView deliver_cell = (SettingCellView) _$_findCachedViewById(C5224R.id.deliver_cell);
            Intrinsics.checkExpressionValueIsNotNull(deliver_cell, "deliver_cell");
            deliver_cell.setVisibility(0);
            SettingCellView cruise_cell = (SettingCellView) _$_findCachedViewById(C5224R.id.cruise_cell);
            Intrinsics.checkExpressionValueIsNotNull(cruise_cell, "cruise_cell");
            cruise_cell.setVisibility(0);
            Button bt_costmap = (Button) _$_findCachedViewById(C5224R.id.bt_costmap);
            Intrinsics.checkExpressionValueIsNotNull(bt_costmap, "bt_costmap");
            bt_costmap.setVisibility(0);
            return;
        }
        Button core_service_bt2 = (Button) _$_findCachedViewById(C5224R.id.core_service_bt);
        Intrinsics.checkExpressionValueIsNotNull(core_service_bt2, "core_service_bt");
        core_service_bt2.setText("继续（启动失败）");
        TextView core_linker_count2 = (TextView) _$_findCachedViewById(C5224R.id.core_linker_count);
        Intrinsics.checkExpressionValueIsNotNull(core_linker_count2, "core_linker_count");
        core_linker_count2.setText("LinkFail");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void invisible() {
        this.update_params_loop = false;
        Button core_service_bt = (Button) _$_findCachedViewById(C5224R.id.core_service_bt);
        Intrinsics.checkExpressionValueIsNotNull(core_service_bt, "core_service_bt");
        core_service_bt.setText("启动");
        TextView core_linker_count = (TextView) _$_findCachedViewById(C5224R.id.core_linker_count);
        Intrinsics.checkExpressionValueIsNotNull(core_linker_count, "core_linker_count");
        core_linker_count.setText("LinkFial");
        updateStatus(new Vector3d(0.0d, 0.0d, 0.0d, 7, null), new Vector2d(0.0d, 0.0d, 3, null));
        Button switch_speed_view = (Button) _$_findCachedViewById(C5224R.id.switch_speed_view);
        Intrinsics.checkExpressionValueIsNotNull(switch_speed_view, "switch_speed_view");
        switch_speed_view.setVisibility(4);
        SettingCellView deliver_cell = (SettingCellView) _$_findCachedViewById(C5224R.id.deliver_cell);
        Intrinsics.checkExpressionValueIsNotNull(deliver_cell, "deliver_cell");
        deliver_cell.setVisibility(4);
        SettingCellView cruise_cell = (SettingCellView) _$_findCachedViewById(C5224R.id.cruise_cell);
        Intrinsics.checkExpressionValueIsNotNull(cruise_cell, "cruise_cell");
        cruise_cell.setVisibility(4);
        Button bt_costmap = (Button) _$_findCachedViewById(C5224R.id.bt_costmap);
        Intrinsics.checkExpressionValueIsNotNull(bt_costmap, "bt_costmap");
        bt_costmap.setVisibility(4);
    }

    private final void startLoop() {
        BuildersKt__Builders_commonKt.launch$default(MirCoreScopeKt.getMirCoreScope(), null, null, new CoreMainActivity$startLoop$1(this, null), 3, null);
    }

    private final void startRecognizeLoop() {
        BuildersKt__Builders_commonKt.launch$default(MirCoreScopeKt.getMirCoreScope(), null, null, new CoreMainActivity$startRecognizeLoop$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateStatus(Vector3d pose, Vector2d speeds) {
        TextView tx_robot_pose = (TextView) _$_findCachedViewById(C5224R.id.tx_robot_pose);
        Intrinsics.checkExpressionValueIsNotNull(tx_robot_pose, "tx_robot_pose");
        tx_robot_pose.setText("(" + CommonKt.format(pose.getX(), 4) + "," + CommonKt.format(pose.getY(), 4) + "," + CommonKt.format(pose.getZ(), 4) + ")");
        TextView tx_linear_speed = (TextView) _$_findCachedViewById(C5224R.id.tx_linear_speed);
        Intrinsics.checkExpressionValueIsNotNull(tx_linear_speed, "tx_linear_speed");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Double.valueOf(speeds.getX())};
        String format = String.format("%.4f", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        tx_linear_speed.setText(format + "m/s");
        TextView tx_angular_speed = (TextView) _$_findCachedViewById(C5224R.id.tx_angular_speed);
        Intrinsics.checkExpressionValueIsNotNull(tx_angular_speed, "tx_angular_speed");
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        Object[] objArr2 = {Double.valueOf(speeds.getY())};
        String format2 = String.format("%.4f", Arrays.copyOf(objArr2, objArr2.length));
        Intrinsics.checkExpressionValueIsNotNull(format2, "java.lang.String.format(format, *args)");
        tx_angular_speed.setText(format2 + "rad/s");
        TextView tx_cliff_dis = (TextView) _$_findCachedViewById(C5224R.id.tx_cliff_dis);
        Intrinsics.checkExpressionValueIsNotNull(tx_cliff_dis, "tx_cliff_dis");
        tx_cliff_dis.setText(String.valueOf(Perception.INSTANCE.getCliffInfo()) + "mm");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateRecognizationRate(double rate, double stdr) {
        TextView tx_recognization_rate = (TextView) _$_findCachedViewById(C5224R.id.tx_recognization_rate);
        Intrinsics.checkExpressionValueIsNotNull(tx_recognization_rate, "tx_recognization_rate");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Double.valueOf(rate)};
        String format = String.format("%.2f", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        tx_recognization_rate.setText(format + "%");
        TextView tx_stdr = (TextView) _$_findCachedViewById(C5224R.id.tx_stdr);
        Intrinsics.checkExpressionValueIsNotNull(tx_stdr, "tx_stdr");
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        Object[] objArr2 = {Double.valueOf(stdr)};
        String format2 = String.format("%.4f", Arrays.copyOf(objArr2, objArr2.length));
        Intrinsics.checkExpressionValueIsNotNull(format2, "java.lang.String.format(format, *args)");
        tx_stdr.setText(format2 + "m");
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("recognizaition_rate ");
        TextView tx_recognization_rate2 = (TextView) _$_findCachedViewById(C5224R.id.tx_recognization_rate);
        Intrinsics.checkExpressionValueIsNotNull(tx_recognization_rate2, "tx_recognization_rate");
        sb.append(tx_recognization_rate2.getText());
        sb.append(" stdr ");
        TextView tx_stdr2 = (TextView) _$_findCachedViewById(C5224R.id.tx_stdr);
        Intrinsics.checkExpressionValueIsNotNull(tx_stdr2, "tx_stdr");
        sb.append(tx_stdr2.getText());
        sb.append(' ');
        Pdlog.m3273d(str, sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Vector2d getAnalysisData() {
        double[] markerPose = Perception.INSTANCE.getMarkerPose();
        double d = markerPose[6];
        double d2 = 0;
        this.recognizeArray.add(Boolean.valueOf(d > d2));
        if (d > d2) {
            this.dockPosArray.add(new Vector2d(markerPose[3], markerPose[4]));
        }
        Iterator<Boolean> it = this.recognizeArray.iterator();
        int i = 0;
        while (it.hasNext()) {
            Boolean okor = it.next();
            Intrinsics.checkExpressionValueIsNotNull(okor, "okor");
            if (okor.booleanValue()) {
                i++;
            }
        }
        double size = this.recognizeArray.size() > 0 ? ((i * 1.0d) / this.recognizeArray.size()) * 100 : 0.0d;
        double calStdr = AnalysisUtils.INSTANCE.calStdr(this.dockPosArray);
        Pdlog.m3273d(this.TAG, "size " + this.recognizeArray.size() + " rate " + size + "  truecnt " + i);
        return new Vector2d(size, calStdr);
    }

    public final void FaceDetect(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        if (face_det) {
            face_det = false;
            Button bt_face_det = (Button) _$_findCachedViewById(C5224R.id.bt_face_det);
            Intrinsics.checkExpressionValueIsNotNull(bt_face_det, "bt_face_det");
            bt_face_det.setText("开启人脸检测");
        } else {
            face_det = true;
            Button bt_face_det2 = (Button) _$_findCachedViewById(C5224R.id.bt_face_det);
            Intrinsics.checkExpressionValueIsNotNull(bt_face_det2, "bt_face_det");
            bt_face_det2.setText("结束人脸检测");
        }
        Perception.INSTANCE.enableVision(face_det);
        Perception.INSTANCE.enableFaceFilter(false);
    }

    public final void OpenCamera(View v) {
        CameraInterface camera;
        CameraInterface camera2;
        Intrinsics.checkParameterIsNotNull(v, "v");
        if (!camera_open) {
            HardwareInterface hardwareInterface = this.hardwareService.getInterface();
            if (hardwareInterface != null && (camera2 = hardwareInterface.getCamera()) != null) {
                camera2.openMonocularCamera();
            }
            camera_open = true;
            Button bt_open_camera = (Button) _$_findCachedViewById(C5224R.id.bt_open_camera);
            Intrinsics.checkExpressionValueIsNotNull(bt_open_camera, "bt_open_camera");
            bt_open_camera.setText("关闭相机");
            return;
        }
        HardwareInterface hardwareInterface2 = this.hardwareService.getInterface();
        if (hardwareInterface2 != null && (camera = hardwareInterface2.getCamera()) != null) {
            camera.closeMonocularCamera();
        }
        camera_open = false;
        Button bt_open_camera2 = (Button) _$_findCachedViewById(C5224R.id.bt_open_camera);
        Intrinsics.checkExpressionValueIsNotNull(bt_open_camera2, "bt_open_camera");
        bt_open_camera2.setText("打开相机");
    }

    public final void DataRecord(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        if (data_record) {
            data_record = false;
            Button bt_record_data = (Button) _$_findCachedViewById(C5224R.id.bt_record_data);
            Intrinsics.checkExpressionValueIsNotNull(bt_record_data, "bt_record_data");
            bt_record_data.setText("开始录制");
        } else {
            data_record = true;
            Button bt_record_data2 = (Button) _$_findCachedViewById(C5224R.id.bt_record_data);
            Intrinsics.checkExpressionValueIsNotNull(bt_record_data2, "bt_record_data");
            bt_record_data2.setText("结束录制");
        }
        Perception.INSTANCE.enableDataRecord(data_record);
    }

    public final void DataClean(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        Perception.INSTANCE.cleanData();
        Toast.makeText(this, "清除数据完成", 0).show();
    }

    public final void setReflactorParam(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        EditText min_reflactor_dist = (EditText) _$_findCachedViewById(C5224R.id.min_reflactor_dist);
        Intrinsics.checkExpressionValueIsNotNull(min_reflactor_dist, "min_reflactor_dist");
        Double doubleOrNull = StringsKt.toDoubleOrNull(min_reflactor_dist.getText().toString());
        EditText mid_reflactor_dist = (EditText) _$_findCachedViewById(C5224R.id.mid_reflactor_dist);
        Intrinsics.checkExpressionValueIsNotNull(mid_reflactor_dist, "mid_reflactor_dist");
        Double doubleOrNull2 = StringsKt.toDoubleOrNull(mid_reflactor_dist.getText().toString());
        EditText max_reflactor_dist = (EditText) _$_findCachedViewById(C5224R.id.max_reflactor_dist);
        Intrinsics.checkExpressionValueIsNotNull(max_reflactor_dist, "max_reflactor_dist");
        Double doubleOrNull3 = StringsKt.toDoubleOrNull(max_reflactor_dist.getText().toString());
        if (doubleOrNull != null && doubleOrNull2 != null && doubleOrNull3 != null) {
            Perception.INSTANCE.setReflectorLevel(0, doubleOrNull.doubleValue());
            Perception.INSTANCE.setReflectorLevel(1, doubleOrNull2.doubleValue());
            Perception.INSTANCE.setReflectorLevel(2, doubleOrNull3.doubleValue());
            Toast.makeText(this, "设置完成！", 0).show();
            return;
        }
        Toast.makeText(this, "参数无效！", 0).show();
    }

    public final void updateReflactorInfo(final double distance, final int level, final int isInDangrousRegion) {
        Pdlog.m3273d(this.TAG, "updateReflactorInfo!");
        runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$updateReflactorInfo$1
            @Override // java.lang.Runnable
            public final void run() {
                EditText editText = (EditText) CoreMainActivity.this._$_findCachedViewById(C5224R.id.reflactor_result);
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] objArr = {Double.valueOf(distance), Integer.valueOf(level), Integer.valueOf(isInDangrousRegion)};
                String format = String.format("Dist: %.2f Level: %d Flag: %d", Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                editText.setText(format);
            }
        });
    }

    public final void updateRoadWidthInfo(final double width) {
        Pdlog.m3273d(this.TAG, "updateRoadWidthInfo!");
        runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$updateRoadWidthInfo$1
            @Override // java.lang.Runnable
            public final void run() {
                EditText editText = (EditText) CoreMainActivity.this._$_findCachedViewById(C5224R.id.roadwidth_result);
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] objArr = {Double.valueOf(width)};
                String format = String.format("%.2fm", Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                editText.setText(format);
            }
        });
    }
}
