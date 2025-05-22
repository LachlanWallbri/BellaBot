package com.pudutech.robot.peripherals.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.ext.view.ToastUtils;
import com.pudutech.disinfect.baselib.preference.MMKVPreference;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.HardwareVersion;
import com.pudutech.robot.peripherals.BuildConfig;
import com.pudutech.robot.peripherals.C5707R;
import com.pudutech.robot.peripherals.config.LightBeltType;
import com.pudutech.robot.peripherals.disinfection.BatteryChargeError;
import com.pudutech.robot.peripherals.disinfection.DeviceName;
import com.pudutech.robot.peripherals.disinfection.DisinfectRobotPeripherals;
import com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals;
import com.pudutech.robot.peripherals.disinfection.device.DisinfectDeviceHelper;
import com.pudutech.robot.peripherals.disinfection.device.DisinfectRobotDeviceManager;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: NineTailsDebugActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0010*\u0002\u001b\u001f\u0018\u0000 <2\u00020\u0001:\u0001<B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010$\u001a\u00020\u0011H\u0002J\b\u0010%\u001a\u00020\u0011H\u0016J\b\u0010&\u001a\u00020\u000bH\u0002J\b\u0010'\u001a\u00020\u000bH\u0002J\b\u0010(\u001a\u00020\u0011H\u0002J\b\u0010)\u001a\u00020\u0011H\u0002J\b\u0010*\u001a\u00020\u0011H\u0003J\u0012\u0010+\u001a\u00020\u00112\b\u0010,\u001a\u0004\u0018\u00010-H\u0014J\b\u0010.\u001a\u00020\u0011H\u0014J\b\u0010/\u001a\u00020\u0011H\u0002J\b\u00100\u001a\u00020\u0011H\u0002J\b\u00101\u001a\u00020\u0011H\u0002J\u0010\u00102\u001a\u00020\u00112\u0006\u00103\u001a\u00020\"H\u0002J\u0010\u00104\u001a\u00020\u00112\u0006\u00105\u001a\u00020\u0004H\u0002J\u0010\u00106\u001a\u00020\u00112\u0006\u00107\u001a\u00020\u0004H\u0002J\u0010\u00108\u001a\u00020\u00112\u0006\u00109\u001a\u00020\u000bH\u0002J\u0010\u0010:\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0004H\u0002J\u0018\u0010;\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0017H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\f\u001a\u001f\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u0012\u001a\u001f\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u0013\u001a\u001f\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u0011\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R@\u0010\u0015\u001a4\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001cR+\u0010\u001d\u001a\u001f\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u0011\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006="}, m3961d2 = {"Lcom/pudutech/robot/peripherals/activity/NineTailsDebugActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "batteryBoxOpenStatus", "", "disinfectDeviceHelper", "Lcom/pudutech/robot/peripherals/disinfection/device/DisinfectDeviceHelper;", "disinfectRobotPeripherals", "Lcom/pudutech/robot/peripherals/disinfection/IDisinfectRobotPeripherals;", "isConnectToHardWare", "mgnityType", "", "onMagneticConfigStatusListener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "success", "", "onMagneticTypeNameListener", "onModuleOpenStatusListener", "open", "onSprayDeviceLiquidLevelListener", "Lkotlin/Function2;", "", "waterBoxLevel", "sprayBoxLevel", "onSprayLiquidStatusListener", "com/pudutech/robot/peripherals/activity/NineTailsDebugActivity$onSprayLiquidStatusListener$1", "Lcom/pudutech/robot/peripherals/activity/NineTailsDebugActivity$onSprayLiquidStatusListener$1;", "onSpringOpenStatusListener", "onUvLampDeviceErrorChangeListener", "com/pudutech/robot/peripherals/activity/NineTailsDebugActivity$onUvLampDeviceErrorChangeListener$1", "Lcom/pudutech/robot/peripherals/activity/NineTailsDebugActivity$onUvLampDeviceErrorChangeListener$1;", "sprayPower", "", "springType", "assertConnectHardWare", "finish", "getSoftVersion", "handlerHardVersion", "initCallback", "initClick", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "realCloseDevice", "release", "setSprayPower", "setUvcLampDeviceRotateAngle", "angle", "updateSprayLevelStatus", "flag", "updateSprayStatus", "it", "updateUvError", "s", "updateUvcStatus", "updateWaterLevel", "Companion", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class NineTailsDebugActivity extends AppCompatActivity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String SPRAY_LEVEL = "spray_level";
    private static final String TAG = "NineTailsDebugActivity";
    private static boolean isOpenFirst;
    private HashMap _$_findViewCache;
    private boolean batteryBoxOpenStatus;
    private DisinfectDeviceHelper disinfectDeviceHelper;
    private IDisinfectRobotPeripherals disinfectRobotPeripherals;
    private boolean isConnectToHardWare;
    private int sprayPower = 1;
    private String mgnityType = "";
    private String springType = "";
    private NineTailsDebugActivity$onSprayLiquidStatusListener$1 onSprayLiquidStatusListener = new Function1<Boolean, Unit>() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$onSprayLiquidStatusListener$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public void invoke(boolean it) {
            Pdlog.m3273d("NineTailsDebugActivity", "current liquid status " + it + ' ');
            NineTailsDebugActivity.this.updateSprayLevelStatus(it);
        }
    };
    private Function1<? super Boolean, Unit> onModuleOpenStatusListener = new Function1<Boolean, Unit>() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$onModuleOpenStatusListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            Pdlog.m3273d("NineTailsDebugActivity", "current module open status is " + z);
            TextView module_status = (TextView) NineTailsDebugActivity.this._$_findCachedViewById(C5707R.id.module_status);
            Intrinsics.checkExpressionValueIsNotNull(module_status, "module_status");
            module_status.setText(z ? "模块状态: open " : "模块状态: close");
        }
    };
    private Function2<? super Double, ? super Double, Unit> onSprayDeviceLiquidLevelListener = new Function2<Double, Double, Unit>() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$onSprayDeviceLiquidLevelListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Double d, Double d2) {
            invoke(d.doubleValue(), d2.doubleValue());
            return Unit.INSTANCE;
        }

        public final void invoke(double d, double d2) {
            Pdlog.m3273d("NineTailsDebugActivity", "water box level " + d + "  spray level " + d2);
            NineTailsDebugActivity.this.updateWaterLevel(d, d2);
        }
    };
    private Function1<? super Boolean, Unit> onSpringOpenStatusListener = new Function1<Boolean, Unit>() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$onSpringOpenStatusListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            String str;
            String str2;
            StringBuilder sb;
            String str3;
            String str4;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("spring config  ");
            str = NineTailsDebugActivity.this.springType;
            sb2.append(str);
            sb2.append(' ');
            sb2.append(z);
            Pdlog.m3273d("NineTailsDebugActivity", sb2.toString());
            TextView spring_status = (TextView) NineTailsDebugActivity.this._$_findCachedViewById(C5707R.id.spring_status);
            Intrinsics.checkExpressionValueIsNotNull(spring_status, "spring_status");
            if (z) {
                str4 = NineTailsDebugActivity.this.springType;
                sb = new StringBuilder();
                sb.append(str4);
                str3 = "设置成功";
            } else {
                str2 = NineTailsDebugActivity.this.springType;
                sb = new StringBuilder();
                sb.append(str2);
                str3 = "设置失败";
            }
            sb.append(str3);
            spring_status.setText(sb.toString());
        }
    };
    private Function1<? super String, Unit> onMagneticTypeNameListener = new Function1<String, Unit>() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$onMagneticTypeNameListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(String str) {
            invoke2(str);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(String it) {
            Intrinsics.checkParameterIsNotNull(it, "it");
            Pdlog.m3273d("NineTailsDebugActivity", "current magnetic type is " + it);
            TextView tv_magnetic_name = (TextView) NineTailsDebugActivity.this._$_findCachedViewById(C5707R.id.tv_magnetic_name);
            Intrinsics.checkExpressionValueIsNotNull(tv_magnetic_name, "tv_magnetic_name");
            tv_magnetic_name.setText(it);
        }
    };
    private Function1<? super Boolean, Unit> onMagneticConfigStatusListener = new Function1<Boolean, Unit>() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$onMagneticConfigStatusListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            String str;
            StringBuilder sb;
            String str2;
            String str3;
            Pdlog.m3273d("NineTailsDebugActivity", "current config magnetic state " + z);
            TextView config_status = (TextView) NineTailsDebugActivity.this._$_findCachedViewById(C5707R.id.config_status);
            Intrinsics.checkExpressionValueIsNotNull(config_status, "config_status");
            if (z) {
                str3 = NineTailsDebugActivity.this.mgnityType;
                sb = new StringBuilder();
                sb.append(str3);
                str2 = "配置成功";
            } else {
                str = NineTailsDebugActivity.this.mgnityType;
                sb = new StringBuilder();
                sb.append(str);
                str2 = "配置失败";
            }
            sb.append(str2);
            config_status.setText(sb.toString());
        }
    };
    private NineTailsDebugActivity$onUvLampDeviceErrorChangeListener$1 onUvLampDeviceErrorChangeListener = new NineTailsDebugActivity$onUvLampDeviceErrorChangeListener$1(this);

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

    /* compiled from: NineTailsDebugActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0018\u0010\u0006\u001a\u00020\u00078\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0002¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/activity/NineTailsDebugActivity$Companion;", "", "()V", "SPRAY_LEVEL", "", "TAG", "isOpenFirst", "", "isOpenFirst$annotations", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        @JvmStatic
        private static /* synthetic */ void isOpenFirst$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5707R.layout.peripherals_activity_disinfect_debug);
        initView();
    }

    private final void initView() {
        TextView tv_title = (TextView) _$_findCachedViewById(C5707R.id.tv_title);
        Intrinsics.checkExpressionValueIsNotNull(tv_title, "tv_title");
        tv_title.setText("九尾调试界面");
        TextView tvSoftVersion = (TextView) _$_findCachedViewById(C5707R.id.tvSoftVersion);
        Intrinsics.checkExpressionValueIsNotNull(tvSoftVersion, "tvSoftVersion");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {getSoftVersion()};
        String format = String.format("当前软件版本:%s", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        tvSoftVersion.setText(format);
        Boolean bool = BuildConfig.module_robot_peripherals_is_debug;
        Intrinsics.checkExpressionValueIsNotNull(bool, "BuildConfig.module_robot_peripherals_is_debug");
        if (bool.booleanValue()) {
            DisinfectRobotDeviceManager.INSTANCE.init(this, false);
            this.disinfectDeviceHelper = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceHelper();
            this.disinfectRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
            IDisinfectRobotPeripherals iDisinfectRobotPeripherals = this.disinfectRobotPeripherals;
            if (iDisinfectRobotPeripherals != null) {
                iDisinfectRobotPeripherals.addHardWareConnectListener(new NineTailsDebugActivity$initView$1(this));
            }
        } else {
            this.disinfectDeviceHelper = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceHelper();
            this.disinfectRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
            if (handlerHardVersion().length() > 0) {
                TextView tvHardwareVersion = (TextView) _$_findCachedViewById(C5707R.id.tvHardwareVersion);
                Intrinsics.checkExpressionValueIsNotNull(tvHardwareVersion, "tvHardwareVersion");
                tvHardwareVersion.setText("当前硬件版本:" + handlerHardVersion());
                ToastUtils.INSTANCE.showShortToast("Success: HardWare is Online");
            } else {
                TextView tvHardwareVersion2 = (TextView) _$_findCachedViewById(C5707R.id.tvHardwareVersion);
                Intrinsics.checkExpressionValueIsNotNull(tvHardwareVersion2, "tvHardwareVersion");
                tvHardwareVersion2.setText("");
                ToastUtils.INSTANCE.showShortToast("Fail: HardWare is Offline");
            }
        }
        initClick();
        initCallback();
        updateWaterLevel(0.0d, 0.0d);
        updateSprayStatus(false);
        updateUvcStatus(false);
        setSprayPower();
        updateSprayLevelStatus(false);
        updateUvError("");
    }

    private final void initClick() {
        ((ImageView) _$_findCachedViewById(C5707R.id.iv_back)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NineTailsDebugActivity.this.finish();
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.reset_slam_core)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectDeviceHelper disinfectDeviceHelper;
                NineTailsDebugActivity.this.assertConnectHardWare();
                disinfectDeviceHelper = NineTailsDebugActivity.this.disinfectDeviceHelper;
                if (disinfectDeviceHelper != null) {
                    disinfectDeviceHelper.resetSlamCore();
                }
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.reset_custom_core)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectDeviceHelper disinfectDeviceHelper;
                NineTailsDebugActivity.this.assertConnectHardWare();
                disinfectDeviceHelper = NineTailsDebugActivity.this.disinfectDeviceHelper;
                if (disinfectDeviceHelper != null) {
                    disinfectDeviceHelper.resetCustomCore();
                }
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.module_open)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectDeviceHelper disinfectDeviceHelper;
                NineTailsDebugActivity.this.assertConnectHardWare();
                disinfectDeviceHelper = NineTailsDebugActivity.this.disinfectDeviceHelper;
                if (disinfectDeviceHelper != null) {
                    disinfectDeviceHelper.bootModule();
                }
                TextView module_status = (TextView) NineTailsDebugActivity.this._$_findCachedViewById(C5707R.id.module_status);
                Intrinsics.checkExpressionValueIsNotNull(module_status, "module_status");
                module_status.setText("模块状态: open ");
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.module_Close)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectDeviceHelper disinfectDeviceHelper;
                NineTailsDebugActivity.this.assertConnectHardWare();
                disinfectDeviceHelper = NineTailsDebugActivity.this.disinfectDeviceHelper;
                if (disinfectDeviceHelper != null) {
                    disinfectDeviceHelper.closeModule();
                }
                TextView module_status = (TextView) NineTailsDebugActivity.this._$_findCachedViewById(C5707R.id.module_status);
                Intrinsics.checkExpressionValueIsNotNull(module_status, "module_status");
                module_status.setText("模块状态: close");
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.spray_open)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectDeviceHelper disinfectDeviceHelper;
                NineTailsDebugActivity.this.assertConnectHardWare();
                Pdlog.m3273d("NineTailsDebugActivity", "open Spray");
                disinfectDeviceHelper = NineTailsDebugActivity.this.disinfectDeviceHelper;
                if (disinfectDeviceHelper != null) {
                    DisinfectDeviceHelper.openSprayDevice$default(disinfectDeviceHelper, false, 1, null);
                }
                NineTailsDebugActivity.this.updateSprayStatus(true);
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.spray_close)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectDeviceHelper disinfectDeviceHelper;
                NineTailsDebugActivity.this.assertConnectHardWare();
                Pdlog.m3273d("NineTailsDebugActivity", "close Spray");
                disinfectDeviceHelper = NineTailsDebugActivity.this.disinfectDeviceHelper;
                if (disinfectDeviceHelper != null) {
                    disinfectDeviceHelper.closeDevice(DeviceName.SprayDevice);
                }
                NineTailsDebugActivity.this.updateSprayStatus(false);
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.uvlamp_open)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectDeviceHelper disinfectDeviceHelper;
                NineTailsDebugActivity.this.assertConnectHardWare();
                Pdlog.m3273d("NineTailsDebugActivity", "open UvC");
                disinfectDeviceHelper = NineTailsDebugActivity.this.disinfectDeviceHelper;
                if (disinfectDeviceHelper != null) {
                    DisinfectDeviceHelper.openUvcLampDevice$default(disinfectDeviceHelper, false, 1, null);
                }
                NineTailsDebugActivity.this.updateUvcStatus(true);
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.uvlamp_close)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectDeviceHelper disinfectDeviceHelper;
                NineTailsDebugActivity.this.assertConnectHardWare();
                Pdlog.m3273d("NineTailsDebugActivity", "close UvC");
                disinfectDeviceHelper = NineTailsDebugActivity.this.disinfectDeviceHelper;
                if (disinfectDeviceHelper != null) {
                    disinfectDeviceHelper.closeDevice(DeviceName.UvCLampDevice);
                }
                NineTailsDebugActivity.this.updateUvcStatus(false);
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.all_open)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectDeviceHelper disinfectDeviceHelper;
                DisinfectDeviceHelper disinfectDeviceHelper2;
                NineTailsDebugActivity.this.assertConnectHardWare();
                Pdlog.m3273d("NineTailsDebugActivity", "allOpen");
                disinfectDeviceHelper = NineTailsDebugActivity.this.disinfectDeviceHelper;
                if (disinfectDeviceHelper != null) {
                    DisinfectDeviceHelper.openSprayDevice$default(disinfectDeviceHelper, false, 1, null);
                }
                disinfectDeviceHelper2 = NineTailsDebugActivity.this.disinfectDeviceHelper;
                if (disinfectDeviceHelper2 != null) {
                    DisinfectDeviceHelper.openUvcLampDevice$default(disinfectDeviceHelper2, false, 1, null);
                }
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.all_close)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectDeviceHelper disinfectDeviceHelper;
                NineTailsDebugActivity.this.assertConnectHardWare();
                Pdlog.m3273d("NineTailsDebugActivity", "allClose ");
                disinfectDeviceHelper = NineTailsDebugActivity.this.disinfectDeviceHelper;
                if (disinfectDeviceHelper != null) {
                    disinfectDeviceHelper.closeDevice(DeviceName.UvCLampDevice, DeviceName.SprayDevice);
                }
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.drain_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectDeviceHelper disinfectDeviceHelper;
                Pdlog.m3273d("NineTailsDebugActivity", "backFlow liquid ");
                disinfectDeviceHelper = NineTailsDebugActivity.this.disinfectDeviceHelper;
                if (disinfectDeviceHelper != null) {
                    DisinfectDeviceHelper.backFlowSprayLiquid$default(disinfectDeviceHelper, 0L, 1, null);
                }
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.light_open_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectDeviceHelper disinfectDeviceHelper;
                Pdlog.m3273d("NineTailsDebugActivity", "light open ");
                disinfectDeviceHelper = NineTailsDebugActivity.this.disinfectDeviceHelper;
                if (disinfectDeviceHelper != null) {
                    disinfectDeviceHelper.m4491setLightFh2MPcY(LightBeltType.DisinfectionSprayHead, (byte) -67, (byte) -67, (byte) 0);
                }
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.light_close_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectDeviceHelper disinfectDeviceHelper;
                Pdlog.m3273d("NineTailsDebugActivity", "light close");
                disinfectDeviceHelper = NineTailsDebugActivity.this.disinfectDeviceHelper;
                if (disinfectDeviceHelper != null) {
                    DisinfectDeviceHelper.m4488setLightFh2MPcY$default(disinfectDeviceHelper, LightBeltType.DisinfectionSprayHead, (byte) 0, (byte) 0, (byte) 0, 14, null);
                }
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.spray_power_down)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i;
                int i2;
                int i3;
                NineTailsDebugActivity nineTailsDebugActivity = NineTailsDebugActivity.this;
                i = nineTailsDebugActivity.sprayPower;
                nineTailsDebugActivity.sprayPower = i - 1;
                i2 = NineTailsDebugActivity.this.sprayPower;
                if (i2 < 1) {
                    NineTailsDebugActivity.this.sprayPower = 1;
                    return;
                }
                MMKVPreference mMKVPreference = MMKVPreference.INSTANCE;
                i3 = NineTailsDebugActivity.this.sprayPower;
                mMKVPreference.encode("spray_level", Integer.valueOf(i3));
                NineTailsDebugActivity.this.setSprayPower();
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.spray_power_up)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$16
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i;
                int i2;
                int i3;
                NineTailsDebugActivity nineTailsDebugActivity = NineTailsDebugActivity.this;
                i = nineTailsDebugActivity.sprayPower;
                nineTailsDebugActivity.sprayPower = i + 1;
                i2 = NineTailsDebugActivity.this.sprayPower;
                if (i2 > 4) {
                    NineTailsDebugActivity.this.sprayPower = 4;
                    return;
                }
                MMKVPreference mMKVPreference = MMKVPreference.INSTANCE;
                i3 = NineTailsDebugActivity.this.sprayPower;
                mMKVPreference.encode("spray_level", Integer.valueOf(i3));
                NineTailsDebugActivity.this.setSprayPower();
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.calibration_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$17
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectDeviceHelper disinfectDeviceHelper;
                try {
                    EditText liquid_et = (EditText) NineTailsDebugActivity.this._$_findCachedViewById(C5707R.id.liquid_et);
                    Intrinsics.checkExpressionValueIsNotNull(liquid_et, "liquid_et");
                    String obj = liquid_et.getEditableText().toString();
                    EditText fog_et = (EditText) NineTailsDebugActivity.this._$_findCachedViewById(C5707R.id.fog_et);
                    Intrinsics.checkExpressionValueIsNotNull(fog_et, "fog_et");
                    String obj2 = fog_et.getEditableText().toString();
                    disinfectDeviceHelper = NineTailsDebugActivity.this.disinfectDeviceHelper;
                    if (disinfectDeviceHelper != null) {
                        disinfectDeviceHelper.calibrationSpray(Integer.parseInt(obj), Integer.parseInt(obj2));
                    }
                } catch (Exception e) {
                    Pdlog.m3274e("NineTailsDebugActivity", "initSpray : " + Log.getStackTraceString(e));
                }
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.start_pre_liquid)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$18
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectDeviceHelper disinfectDeviceHelper;
                disinfectDeviceHelper = NineTailsDebugActivity.this.disinfectDeviceHelper;
                if (disinfectDeviceHelper != null) {
                    disinfectDeviceHelper.openSprayDevice(false);
                }
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.btSelect1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$19
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectDeviceHelper disinfectDeviceHelper;
                NineTailsDebugActivity.this.springType = "0.16A";
                disinfectDeviceHelper = NineTailsDebugActivity.this.disinfectDeviceHelper;
                if (disinfectDeviceHelper != null) {
                    disinfectDeviceHelper.m4490selectSpringType7apg3OU((byte) 1);
                }
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.btSelect2)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$20
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectDeviceHelper disinfectDeviceHelper;
                NineTailsDebugActivity.this.springType = "0.8A";
                disinfectDeviceHelper = NineTailsDebugActivity.this.disinfectDeviceHelper;
                if (disinfectDeviceHelper != null) {
                    disinfectDeviceHelper.m4490selectSpringType7apg3OU((byte) 2);
                }
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.btn_up)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$21
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectDeviceHelper disinfectDeviceHelper;
                try {
                    NineTailsDebugActivity.this.mgnityType = "上通磁浮子";
                    disinfectDeviceHelper = NineTailsDebugActivity.this.disinfectDeviceHelper;
                    if (disinfectDeviceHelper != null) {
                        disinfectDeviceHelper.m4489configMagnetic7apg3OU((byte) 1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Pdlog.m3274e("NineTailsDebugActivity", "current config up mgnity occur error " + Log.getStackTraceString(e));
                }
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.btn_down)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$22
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectDeviceHelper disinfectDeviceHelper;
                try {
                    NineTailsDebugActivity.this.mgnityType = "下通磁浮子";
                    disinfectDeviceHelper = NineTailsDebugActivity.this.disinfectDeviceHelper;
                    if (disinfectDeviceHelper != null) {
                        disinfectDeviceHelper.m4489configMagnetic7apg3OU((byte) 1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Pdlog.m3274e("NineTailsDebugActivity", "current config down mgnity occur error " + Log.getStackTraceString(e));
                }
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.get_magnetic_type)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$23
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectDeviceHelper disinfectDeviceHelper;
                try {
                    disinfectDeviceHelper = NineTailsDebugActivity.this.disinfectDeviceHelper;
                    if (disinfectDeviceHelper != null) {
                        disinfectDeviceHelper.getMagneticType();
                    }
                } catch (Exception e) {
                    Pdlog.m3274e("NineTailsDebugActivity", "current get magnetic type occur error " + Log.getStackTraceString(e));
                }
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.uvlamp_0_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$24
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                try {
                    NineTailsDebugActivity.this.setUvcLampDeviceRotateAngle(0);
                } catch (Exception e) {
                    e.printStackTrace();
                    Pdlog.m3274e("NineTailsDebugActivity", "uvlamp_0_btn setMotorAngle occur error " + Log.getStackTraceString(e));
                }
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.uvlamp_motor_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initClick$25
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                try {
                    NineTailsDebugActivity nineTailsDebugActivity = NineTailsDebugActivity.this;
                    EditText motor_et = (EditText) NineTailsDebugActivity.this._$_findCachedViewById(C5707R.id.motor_et);
                    Intrinsics.checkExpressionValueIsNotNull(motor_et, "motor_et");
                    nineTailsDebugActivity.setUvcLampDeviceRotateAngle(Integer.parseInt(motor_et.getText().toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                    Pdlog.m3274e("NineTailsDebugActivity", "uvlamp_motor_btn setMotorAngle occur error " + Log.getStackTraceString(e));
                }
            }
        });
    }

    private final void initCallback() {
        DisinfectDeviceHelper disinfectDeviceHelper = this.disinfectDeviceHelper;
        if (disinfectDeviceHelper != null) {
            disinfectDeviceHelper.addSprayLiquidStatusListener(this.onSprayLiquidStatusListener);
        }
        DisinfectDeviceHelper disinfectDeviceHelper2 = this.disinfectDeviceHelper;
        if (disinfectDeviceHelper2 != null) {
            disinfectDeviceHelper2.addModuleOpenStatusListener(this.onModuleOpenStatusListener);
        }
        DisinfectDeviceHelper disinfectDeviceHelper3 = this.disinfectDeviceHelper;
        if (disinfectDeviceHelper3 != null) {
            disinfectDeviceHelper3.addMagneticTypeListener(this.onMagneticTypeNameListener);
        }
        DisinfectDeviceHelper disinfectDeviceHelper4 = this.disinfectDeviceHelper;
        if (disinfectDeviceHelper4 != null) {
            disinfectDeviceHelper4.addUvcLampDeviceErrorListener(this.onUvLampDeviceErrorChangeListener);
        }
        IDisinfectRobotPeripherals iDisinfectRobotPeripherals = this.disinfectRobotPeripherals;
        if (iDisinfectRobotPeripherals != null) {
            iDisinfectRobotPeripherals.addSprayDeviceLiquidLevelListener(this.onSprayDeviceLiquidLevelListener);
        }
        IDisinfectRobotPeripherals iDisinfectRobotPeripherals2 = this.disinfectRobotPeripherals;
        if (iDisinfectRobotPeripherals2 != null) {
            iDisinfectRobotPeripherals2.addSpringOpenStatusListener(this.onSpringOpenStatusListener);
        }
        IDisinfectRobotPeripherals iDisinfectRobotPeripherals3 = this.disinfectRobotPeripherals;
        if (iDisinfectRobotPeripherals3 != null) {
            iDisinfectRobotPeripherals3.addMagneticConfigStatusListener(this.onMagneticConfigStatusListener);
        }
        IDisinfectRobotPeripherals iDisinfectRobotPeripherals4 = this.disinfectRobotPeripherals;
        if (iDisinfectRobotPeripherals4 != null) {
            iDisinfectRobotPeripherals4.addTheFirstPowerListener(new Function1<Integer, Unit>() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initCallback$1
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
                    Pdlog.m3273d("NineTailsDebugActivity", "disinfectRobotPeripherals first battery power " + i);
                    TextView tv_battery_1 = (TextView) NineTailsDebugActivity.this._$_findCachedViewById(C5707R.id.tv_battery_1);
                    Intrinsics.checkExpressionValueIsNotNull(tv_battery_1, "tv_battery_1");
                    tv_battery_1.setText("1号电池真实电量:" + i + '%');
                }
            });
        }
        IDisinfectRobotPeripherals iDisinfectRobotPeripherals5 = this.disinfectRobotPeripherals;
        if (iDisinfectRobotPeripherals5 != null) {
            iDisinfectRobotPeripherals5.addTheZeroPowerListener(new Function1<Integer, Unit>() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initCallback$2
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
                    Pdlog.m3273d("NineTailsDebugActivity", "disinfectRobotPeripherals zero battery power " + i);
                    TextView tv_battery_0 = (TextView) NineTailsDebugActivity.this._$_findCachedViewById(C5707R.id.tv_battery_0);
                    Intrinsics.checkExpressionValueIsNotNull(tv_battery_0, "tv_battery_0");
                    tv_battery_0.setText("0号电池真实电量:" + i + '%');
                }
            });
        }
        IDisinfectRobotPeripherals iDisinfectRobotPeripherals6 = this.disinfectRobotPeripherals;
        if (iDisinfectRobotPeripherals6 != null) {
            iDisinfectRobotPeripherals6.addBatteryCommunicateErrorListener(new Function1<HashMap<String, Boolean>, Unit>() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initCallback$3
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HashMap<String, Boolean> hashMap) {
                    invoke2(hashMap);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HashMap<String, Boolean> it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Pdlog.m3273d("NineTailsDebugActivity", "battery communicate error " + it);
                    if (it.get(DisinfectRobotPeripherals.BATTERY_NUMBER_00) != null && Intrinsics.areEqual((Object) it.get(DisinfectRobotPeripherals.BATTERY_NUMBER_00), (Object) true)) {
                        TextView tv_battery_0 = (TextView) NineTailsDebugActivity.this._$_findCachedViewById(C5707R.id.tv_battery_0);
                        Intrinsics.checkExpressionValueIsNotNull(tv_battery_0, "tv_battery_0");
                        tv_battery_0.setText("0号电池电量:发生异常");
                    }
                    if (it.get(DisinfectRobotPeripherals.BATTERY_NUMBER_01) == null || !Intrinsics.areEqual((Object) it.get(DisinfectRobotPeripherals.BATTERY_NUMBER_01), (Object) true)) {
                        return;
                    }
                    TextView tv_battery_1 = (TextView) NineTailsDebugActivity.this._$_findCachedViewById(C5707R.id.tv_battery_1);
                    Intrinsics.checkExpressionValueIsNotNull(tv_battery_1, "tv_battery_1");
                    tv_battery_1.setText("1号电池电量:发生异常");
                }
            });
        }
        IDisinfectRobotPeripherals iDisinfectRobotPeripherals7 = this.disinfectRobotPeripherals;
        if (iDisinfectRobotPeripherals7 != null) {
            iDisinfectRobotPeripherals7.addBatteryChargeErrorListener((Function1) new Function1<BatteryChargeError[], Unit>() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initCallback$4
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(BatteryChargeError[] batteryChargeErrorArr) {
                    invoke2(batteryChargeErrorArr);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(BatteryChargeError[] it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Pdlog.m3273d("NineTailsDebugActivity", "battery charge error " + it);
                    if (true ^ (it.length == 0)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("充电异常：");
                        for (BatteryChargeError batteryChargeError : it) {
                            sb.append(batteryChargeError.getValue());
                            sb.append(",");
                        }
                        TextView tv_charge_error_tips = (TextView) NineTailsDebugActivity.this._$_findCachedViewById(C5707R.id.tv_charge_error_tips);
                        Intrinsics.checkExpressionValueIsNotNull(tv_charge_error_tips, "tv_charge_error_tips");
                        tv_charge_error_tips.setText(sb.toString());
                    }
                }
            });
        }
        IDisinfectRobotPeripherals iDisinfectRobotPeripherals8 = this.disinfectRobotPeripherals;
        if (iDisinfectRobotPeripherals8 != null) {
            iDisinfectRobotPeripherals8.addBatteryBoxOpenStatus(new Function1<Boolean, Unit>() { // from class: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initCallback$5
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    boolean z2;
                    boolean z3;
                    z2 = NineTailsDebugActivity.this.batteryBoxOpenStatus;
                    if (z2 != z) {
                        NineTailsDebugActivity.this.batteryBoxOpenStatus = z;
                        Pdlog.m3273d("NineTailsDebugActivity", "Battery Box Open Status " + z);
                        z3 = NineTailsDebugActivity.this.batteryBoxOpenStatus;
                        if (z3) {
                            Pdlog.m3273d("NineTailsDebugActivity", "show Dialog");
                            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C57081(null), 2, null);
                        }
                    }
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: NineTailsDebugActivity.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initCallback$5$1", m3970f = "NineTailsDebugActivity.kt", m3971i = {0}, m3972l = {393}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
                /* renamed from: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initCallback$5$1 */
                /* loaded from: classes6.dex */
                public static final class C57081 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    Object L$0;
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f7292p$;

                    C57081(Continuation continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        C57081 c57081 = new C57081(completion);
                        c57081.f7292p$ = (CoroutineScope) obj;
                        return c57081;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C57081) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.L$0 = this.f7292p$;
                            this.label = 1;
                            if (DelayKt.delay(200L, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        ToastUtils.INSTANCE.showLongToast("当前检测到电池盖板未合上,请进行相关检测,以免电池供电出现故障");
                        NineTailsDebugActivity.this.realCloseDevice();
                        return Unit.INSTANCE;
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSprayLevelStatus(boolean flag) {
        TextView tvLiquidLevelStatus = (TextView) _$_findCachedViewById(C5707R.id.tvLiquidLevelStatus);
        Intrinsics.checkExpressionValueIsNotNull(tvLiquidLevelStatus, "tvLiquidLevelStatus");
        tvLiquidLevelStatus.setText("当前液位状态:" + flag);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateWaterLevel(double waterBoxLevel, double sprayBoxLevel) {
        TextView spray_chamber_level_tv = (TextView) _$_findCachedViewById(C5707R.id.spray_chamber_level_tv);
        Intrinsics.checkExpressionValueIsNotNull(spray_chamber_level_tv, "spray_chamber_level_tv");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {String.valueOf(sprayBoxLevel)};
        String format = String.format("雾化室:%s mm", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        spray_chamber_level_tv.setText(format);
        TextView liquid_level_tv = (TextView) _$_findCachedViewById(C5707R.id.liquid_level_tv);
        Intrinsics.checkExpressionValueIsNotNull(liquid_level_tv, "liquid_level_tv");
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        Object[] objArr2 = {String.valueOf(waterBoxLevel)};
        String format2 = String.format("水箱值：%s mm", Arrays.copyOf(objArr2, objArr2.length));
        Intrinsics.checkExpressionValueIsNotNull(format2, "java.lang.String.format(format, *args)");
        liquid_level_tv.setText(format2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateUvError(String s) {
        TextView uv_error_tv = (TextView) _$_findCachedViewById(C5707R.id.uv_error_tv);
        Intrinsics.checkExpressionValueIsNotNull(uv_error_tv, "uv_error_tv");
        uv_error_tv.setText("紫外设备异常：" + s);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void assertConnectHardWare() {
        Boolean bool = BuildConfig.module_robot_peripherals_is_debug;
        Intrinsics.checkExpressionValueIsNotNull(bool, "BuildConfig.module_robot_peripherals_is_debug");
        if (!bool.booleanValue() || this.isConnectToHardWare) {
            return;
        }
        TextView tvHardwareVersion = (TextView) _$_findCachedViewById(C5707R.id.tvHardwareVersion);
        Intrinsics.checkExpressionValueIsNotNull(tvHardwareVersion, "tvHardwareVersion");
        if (TextUtils.isEmpty(tvHardwareVersion.getText())) {
            ToastUtils.INSTANCE.showShortToast("current state is not connect hardware so can't send data to hardware");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSprayPower() {
        Integer decodeInt = MMKVPreference.INSTANCE.decodeInt(SPRAY_LEVEL, this.sprayPower);
        if (decodeInt == null) {
            Intrinsics.throwNpe();
        }
        this.sprayPower = decodeInt.intValue();
        TextView spray_power_lv_tv = (TextView) _$_findCachedViewById(C5707R.id.spray_power_lv_tv);
        Intrinsics.checkExpressionValueIsNotNull(spray_power_lv_tv, "spray_power_lv_tv");
        spray_power_lv_tv.setText("功率：" + this.sprayPower);
        IDisinfectRobotPeripherals iDisinfectRobotPeripherals = this.disinfectRobotPeripherals;
        if (iDisinfectRobotPeripherals != null) {
            Integer decodeInt2 = MMKVPreference.INSTANCE.decodeInt(SPRAY_LEVEL, this.sprayPower);
            if (decodeInt2 == null) {
                Intrinsics.throwNpe();
            }
            iDisinfectRobotPeripherals.setSpraySpeedRate(decodeInt2.intValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSprayStatus(boolean it) {
        TextView spray_status_tv = (TextView) _$_findCachedViewById(C5707R.id.spray_status_tv);
        Intrinsics.checkExpressionValueIsNotNull(spray_status_tv, "spray_status_tv");
        spray_status_tv.setText(it ? "喷雾设备状态:开启" : "喷雾设备状态:关闭");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateUvcStatus(boolean open) {
        TextView uv_status_tv = (TextView) _$_findCachedViewById(C5707R.id.uv_status_tv);
        Intrinsics.checkExpressionValueIsNotNull(uv_status_tv, "uv_status_tv");
        uv_status_tv.setText(open ? "紫外设备状态:开启" : "紫外设备状态:关闭");
    }

    private final String getSoftVersion() {
        try {
            String str = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            Intrinsics.checkExpressionValueIsNotNull(str, "packageInfo.versionName");
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            Pdlog.m3274e(TAG, "getSoftVersion occur error " + Log.getStackTraceString(e));
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setUvcLampDeviceRotateAngle(int angle) {
        DisinfectDeviceHelper disinfectDeviceHelper;
        try {
            Pdlog.m3273d(TAG, "setUvcLampDeviceRotateAngle " + angle);
            if (this.disinfectDeviceHelper == null || (disinfectDeviceHelper = this.disinfectDeviceHelper) == null) {
                return;
            }
            disinfectDeviceHelper.setMotorRotateAngle(angle);
        } catch (Exception e) {
            e.printStackTrace();
            Pdlog.m3273d(TAG, "setUvcLampDeviceRotateAngle " + Log.getStackTraceString(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String handlerHardVersion() {
        IDisinfectRobotPeripherals iDisinfectRobotPeripherals = this.disinfectRobotPeripherals;
        if (iDisinfectRobotPeripherals == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.disinfection.DisinfectRobotPeripherals");
        }
        HardwareInterface hardWareInterface = ((DisinfectRobotPeripherals) iDisinfectRobotPeripherals).getHardWareInterface();
        if (hardWareInterface == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        HardwareVersion[] hardwareVersion = hardWareInterface.getHardwareVersion();
        Intrinsics.checkExpressionValueIsNotNull(hardwareVersion, "hardWareInterface?.hardwareVersion");
        for (HardwareVersion hardwareVersion2 : hardwareVersion) {
            sb.append(hardwareVersion2.getVer0());
            sb.append("_");
            sb.append(hardwareVersion2.getVer1());
            sb.append("_");
            sb.append(hardwareVersion2.getVer2());
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "stringBuilder.toString()");
        return sb2;
    }

    @Override // android.app.Activity
    public void finish() {
        release();
        super.finish();
    }

    private final void release() {
        realCloseDevice();
        DisinfectDeviceHelper disinfectDeviceHelper = this.disinfectDeviceHelper;
        if (disinfectDeviceHelper != null) {
            disinfectDeviceHelper.closeModule();
        }
        IDisinfectRobotPeripherals iDisinfectRobotPeripherals = this.disinfectRobotPeripherals;
        if (iDisinfectRobotPeripherals != null) {
            if (iDisinfectRobotPeripherals != null) {
                iDisinfectRobotPeripherals.removeSprayDeviceLiquidLevelListener();
            }
            IDisinfectRobotPeripherals iDisinfectRobotPeripherals2 = this.disinfectRobotPeripherals;
            if (iDisinfectRobotPeripherals2 != null) {
                iDisinfectRobotPeripherals2.removeSpringOpenStatusListener();
            }
            IDisinfectRobotPeripherals iDisinfectRobotPeripherals3 = this.disinfectRobotPeripherals;
            if (iDisinfectRobotPeripherals3 != null) {
                iDisinfectRobotPeripherals3.removeMagneticConfigStatusListener();
            }
            IDisinfectRobotPeripherals iDisinfectRobotPeripherals4 = this.disinfectRobotPeripherals;
            if (iDisinfectRobotPeripherals4 != null) {
                iDisinfectRobotPeripherals4.removeTheFirstPowerListener();
            }
            IDisinfectRobotPeripherals iDisinfectRobotPeripherals5 = this.disinfectRobotPeripherals;
            if (iDisinfectRobotPeripherals5 != null) {
                iDisinfectRobotPeripherals5.removeTheZeroPowerListener();
            }
            IDisinfectRobotPeripherals iDisinfectRobotPeripherals6 = this.disinfectRobotPeripherals;
            if (iDisinfectRobotPeripherals6 != null) {
                iDisinfectRobotPeripherals6.removeBatteryCommunicateErrorListener();
            }
            IDisinfectRobotPeripherals iDisinfectRobotPeripherals7 = this.disinfectRobotPeripherals;
            if (iDisinfectRobotPeripherals7 != null) {
                iDisinfectRobotPeripherals7.removeBatteryChargeErrorListener();
            }
            IDisinfectRobotPeripherals iDisinfectRobotPeripherals8 = this.disinfectRobotPeripherals;
            if (iDisinfectRobotPeripherals8 != null) {
                iDisinfectRobotPeripherals8.removeBatteryBoxOpenStatusListener();
            }
            IDisinfectRobotPeripherals iDisinfectRobotPeripherals9 = this.disinfectRobotPeripherals;
            if (iDisinfectRobotPeripherals9 != null) {
                iDisinfectRobotPeripherals9.release();
            }
        }
        DisinfectDeviceHelper disinfectDeviceHelper2 = this.disinfectDeviceHelper;
        if (disinfectDeviceHelper2 != null) {
            if (disinfectDeviceHelper2 != null) {
                disinfectDeviceHelper2.removeSprayLiquidStatusListener(this.onSprayLiquidStatusListener);
            }
            DisinfectDeviceHelper disinfectDeviceHelper3 = this.disinfectDeviceHelper;
            if (disinfectDeviceHelper3 != null) {
                disinfectDeviceHelper3.removeModuleOpenStatusListener();
            }
            DisinfectDeviceHelper disinfectDeviceHelper4 = this.disinfectDeviceHelper;
            if (disinfectDeviceHelper4 != null) {
                disinfectDeviceHelper4.removeUvcLampDeviceErrorListener(this.onUvLampDeviceErrorChangeListener);
            }
            DisinfectDeviceHelper disinfectDeviceHelper5 = this.disinfectDeviceHelper;
            if (disinfectDeviceHelper5 != null) {
                disinfectDeviceHelper5.removeMagneticTypeListener();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void realCloseDevice() {
        Pdlog.m3273d(TAG, "allClose ");
        DisinfectDeviceHelper disinfectDeviceHelper = this.disinfectDeviceHelper;
        if (disinfectDeviceHelper != null) {
            disinfectDeviceHelper.closeDevice(DeviceName.UvCLampDevice, DeviceName.SprayDevice);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        release();
        super.onDestroy();
    }
}
