package com.pudutech.bumblebee.robot.activity;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.bumblebee.robot.C4144R;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
import com.pudutech.bumblebee.robot.aidl.serialize.SurfaceLED;
import com.pudutech.bumblebee.robot.disinfection_device.DisinfectionModuleManager;
import com.pudutech.bumblebee.robot.disinfection_device.SprayDevice;
import com.pudutech.bumblebee.robot.disinfection_device.UvLampDevice;
import com.pudutech.bumblebee.robot.surface_led.LEDHelper;
import com.pudutech.bumblebee.robot.util.SharedPreferencesUtils;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: DisinfectionActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\b*\u0001\n\u0018\u0000 82\u00020\u00012\u00020\u0002:\u00018B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\b\u0010\u0016\u001a\u00020\u0014H\u0002J\b\u0010\u0017\u001a\u00020\u0014H\u0002J\u0012\u0010\u0018\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0012\u0010\u001b\u001a\u00020\u00142\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u0014H\u0014J\b\u0010\u001f\u001a\u00020\u0014H\u0014J.\u0010 \u001a\u00020\u00142\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\"ø\u0001\u0000¢\u0006\u0004\b%\u0010&J\u0010\u0010'\u001a\u00020\u00142\u0006\u0010(\u001a\u00020)H\u0002J\u0010\u0010*\u001a\u00020\u00142\u0006\u0010(\u001a\u00020)H\u0002J\b\u0010+\u001a\u00020\u0014H\u0002J.\u0010,\u001a\u00020\u000f2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\"ø\u0001\u0000¢\u0006\u0004\b-\u0010.J\u0010\u0010/\u001a\u00020\u00142\u0006\u00100\u001a\u000201H\u0002J\u0010\u00102\u001a\u00020\u00142\u0006\u00100\u001a\u000201H\u0002J\u0010\u00103\u001a\u00020\u00142\u0006\u00104\u001a\u00020\u0005H\u0002J\u0010\u00105\u001a\u00020\u00142\u0006\u00100\u001a\u000201H\u0002J\u0010\u00106\u001a\u00020\u00142\u0006\u00104\u001a\u00020\u0005H\u0002J\u0010\u00107\u001a\u00020\u00142\u0006\u00100\u001a\u000201H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00069"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/activity/DisinfectionActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Landroid/view/View$OnClickListener;", "()V", "TAG", "", "connection", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/bumblebee/robot/aidl/RobotInterface;", "disliIDisinfectionRobotListener", "com/pudutech/bumblebee/robot/activity/DisinfectionActivity$disliIDisinfectionRobotListener$1", "Lcom/pudutech/bumblebee/robot/activity/DisinfectionActivity$disliIDisinfectionRobotListener$1;", "robotInterface", "servicePath", "sprayPower", "", "handlerSoftVersionName", "context", "Landroid/content/Context;", "initConnection", "", "initSpray", "initUv", "initView", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "setAllLed", "red", "Lkotlin/UByte;", "green", "blue", "setAllLed-b33U2AM", "(BBB)V", "setLiquidLevelTv", LinkFormat.DOMAIN, "", "setSprayChamberLevelTv", "setSprayPower", "toRGBInt", "toRGBInt-b33U2AM", "(BBB)I", "updateLiquidLevelStatus", "b", "", "updateModuleStatus", "updateSprayError", "s", "updateSprayStatue", "updateUvError", "updateUvStatue", "Companion", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class DisinfectionActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String SPRAY_LEVEL = "spray_level";
    private HashMap _$_findViewCache;
    private AIDLConnection<RobotInterface> connection;
    private RobotInterface robotInterface;
    private final String TAG = "DisinfectionActivity";
    private String servicePath = "com.pudutech.bumblebee.robot.RobotService";
    private DisinfectionActivity$disliIDisinfectionRobotListener$1 disliIDisinfectionRobotListener = new DisinfectionActivity$disliIDisinfectionRobotListener$1(this);
    private int sprayPower = 1;

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

    /* renamed from: toRGBInt-b33U2AM, reason: not valid java name */
    public final int m4305toRGBIntb33U2AM(byte red, byte green, byte blue) {
        return ((red & 255) << 16) | ((green & 255) << 8) | (blue & 255);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C4144R.layout.robot_app_activity_disinfection);
        initConnection();
        initView();
        TextView tvHardwareVersion = (TextView) _$_findCachedViewById(C4144R.id.tvHardwareVersion);
        Intrinsics.checkExpressionValueIsNotNull(tvHardwareVersion, "tvHardwareVersion");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("当前硬件版本号为%s", Arrays.copyOf(new Object[]{DisinfectionModuleManager.INSTANCE.handlerHardVersion()}, 1));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        tvHardwareVersion.setText(format);
        TextView tvSoftVersion = (TextView) _$_findCachedViewById(C4144R.id.tvSoftVersion);
        Intrinsics.checkExpressionValueIsNotNull(tvSoftVersion, "tvSoftVersion");
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        String format2 = String.format("当前软件版本号为%s", Arrays.copyOf(new Object[]{handlerSoftVersionName(this)}, 1));
        Intrinsics.checkExpressionValueIsNotNull(format2, "java.lang.String.format(format, *args)");
        tvSoftVersion.setText(format2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Pdlog.m3273d(this.TAG, "onResume ");
        TextView tvHardwareVersion = (TextView) _$_findCachedViewById(C4144R.id.tvHardwareVersion);
        Intrinsics.checkExpressionValueIsNotNull(tvHardwareVersion, "tvHardwareVersion");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("当前硬件版本号为%s", Arrays.copyOf(new Object[]{DisinfectionModuleManager.INSTANCE.handlerHardVersion()}, 1));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        tvHardwareVersion.setText(format);
    }

    private final String handlerSoftVersionName(Context context) {
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            Intrinsics.checkExpressionValueIsNotNull(str, "packageInfo.versionName");
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateLiquidLevelStatus(boolean b) {
        TextView tvLiquidLevelStatus = (TextView) _$_findCachedViewById(C4144R.id.tvLiquidLevelStatus);
        Intrinsics.checkExpressionValueIsNotNull(tvLiquidLevelStatus, "tvLiquidLevelStatus");
        tvLiquidLevelStatus.setText("当前液位状态:" + b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateModuleStatus(boolean b) {
        TextView module_status = (TextView) _$_findCachedViewById(C4144R.id.module_status);
        Intrinsics.checkExpressionValueIsNotNull(module_status, "module_status");
        module_status.setText("模块状态：" + b);
    }

    private final void updateUvStatue(boolean b) {
        TextView uv_status_tv = (TextView) _$_findCachedViewById(C4144R.id.uv_status_tv);
        Intrinsics.checkExpressionValueIsNotNull(uv_status_tv, "uv_status_tv");
        StringBuilder sb = new StringBuilder();
        sb.append("状态：");
        sb.append(b ? "开" : "关");
        uv_status_tv.setText(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateUvError(String s) {
        TextView uv_error_tv = (TextView) _$_findCachedViewById(C4144R.id.uv_error_tv);
        Intrinsics.checkExpressionValueIsNotNull(uv_error_tv, "uv_error_tv");
        uv_error_tv.setText("异常：" + s);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSprayStatue(boolean b) {
        TextView spray_status_tv = (TextView) _$_findCachedViewById(C4144R.id.spray_status_tv);
        Intrinsics.checkExpressionValueIsNotNull(spray_status_tv, "spray_status_tv");
        StringBuilder sb = new StringBuilder();
        sb.append("状态：");
        sb.append(b ? "开" : "关");
        spray_status_tv.setText(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSprayError(String s) {
        TextView spray_error_tv = (TextView) _$_findCachedViewById(C4144R.id.spray_error_tv);
        Intrinsics.checkExpressionValueIsNotNull(spray_error_tv, "spray_error_tv");
        spray_error_tv.setText("异常：" + s);
    }

    private final void initView() {
        Pdlog.m3273d(this.TAG, "initView ");
        updateModuleStatus(DisinfectionModuleManager.INSTANCE.getModeSwitch());
        DisinfectionModuleManager.INSTANCE.setOnSwitchListener(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$initView$1
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
                DisinfectionActivity.this.updateModuleStatus(z);
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.module_open)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$initView$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectionModuleManager.INSTANCE.boot();
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.module_Close)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$initView$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectionModuleManager.INSTANCE.close();
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.all_open)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$initView$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RobotInterface robotInterface;
                RobotInterface robotInterface2;
                robotInterface = DisinfectionActivity.this.robotInterface;
                if (robotInterface != null) {
                    robotInterface.openUvLampDevice(true, true);
                }
                robotInterface2 = DisinfectionActivity.this.robotInterface;
                if (robotInterface2 != null) {
                    robotInterface2.openSprayDevice(true, false);
                }
            }
        });
        DisinfectionActivity disinfectionActivity = this;
        ((Button) _$_findCachedViewById(C4144R.id.btSelect1)).setOnClickListener(disinfectionActivity);
        ((Button) _$_findCachedViewById(C4144R.id.btSelect2)).setOnClickListener(disinfectionActivity);
        ((Button) _$_findCachedViewById(C4144R.id.all_Close)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$initView$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RobotInterface robotInterface;
                RobotInterface robotInterface2;
                robotInterface = DisinfectionActivity.this.robotInterface;
                if (robotInterface != null) {
                    robotInterface.openUvLampDevice(false, false);
                }
                robotInterface2 = DisinfectionActivity.this.robotInterface;
                if (robotInterface2 != null) {
                    robotInterface2.openSprayDevice(false, false);
                }
            }
        });
        initSpray();
        initUv();
    }

    private final void initUv() {
        updateUvStatue(DisinfectionModuleManager.INSTANCE.getSprayDevice().getSwitch());
        ((Button) _$_findCachedViewById(C4144R.id.uvlamp_open)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$initUv$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                RobotInterface robotInterface;
                str = DisinfectionActivity.this.TAG;
                Pdlog.m3273d(str, "uvlamp_open setOnClickListener");
                robotInterface = DisinfectionActivity.this.robotInterface;
                if (robotInterface != null) {
                    robotInterface.openUvLampDevice(true, true);
                }
                DisinfectionModuleManager.INSTANCE.handlerHardVersion();
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.uvlamp_close)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$initUv$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                RobotInterface robotInterface;
                str = DisinfectionActivity.this.TAG;
                Pdlog.m3273d(str, "uvlamp_close setOnClickListener");
                robotInterface = DisinfectionActivity.this.robotInterface;
                if (robotInterface != null) {
                    robotInterface.openUvLampDevice(false, false);
                }
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.uvlamp_motor_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$initUv$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                String str2;
                str = DisinfectionActivity.this.TAG;
                Pdlog.m3273d(str, "uvlamp_motor_btn ");
                try {
                    UvLampDevice uvLampDevice = DisinfectionModuleManager.INSTANCE.getUvLampDevice();
                    EditText motor_et = (EditText) DisinfectionActivity.this._$_findCachedViewById(C4144R.id.motor_et);
                    Intrinsics.checkExpressionValueIsNotNull(motor_et, "motor_et");
                    uvLampDevice.setMotorAngle(Integer.parseInt(motor_et.getText().toString()));
                } catch (Exception e) {
                    str2 = DisinfectionActivity.this.TAG;
                    Pdlog.m3274e(str2, "initUv : " + e);
                }
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.uvlamp_0_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$initUv$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                String str2;
                str = DisinfectionActivity.this.TAG;
                Pdlog.m3273d(str, "uvlamp_0_btn ");
                try {
                    DisinfectionModuleManager.INSTANCE.getUvLampDevice().setMotor0();
                } catch (Exception e) {
                    str2 = DisinfectionActivity.this.TAG;
                    Pdlog.m3274e(str2, "initUv : " + e);
                }
            }
        });
    }

    private final void initSpray() {
        setLiquidLevelTv(0.0d);
        setSprayChamberLevelTv(0.0d);
        updateSprayStatue(DisinfectionModuleManager.INSTANCE.getSprayDevice().getSwitch());
        ((Button) _$_findCachedViewById(C4144R.id.spray_open)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$initSpray$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                RobotInterface robotInterface;
                str = DisinfectionActivity.this.TAG;
                Pdlog.m3273d(str, "spray_open setOnClickListener");
                robotInterface = DisinfectionActivity.this.robotInterface;
                if (robotInterface != null) {
                    robotInterface.openSprayDevice(true, true);
                }
                DisinfectionActivity.this.updateSprayStatue(true);
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.spray_close)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$initSpray$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                RobotInterface robotInterface;
                str = DisinfectionActivity.this.TAG;
                Pdlog.m3273d(str, "spray_close setOnClickListener");
                robotInterface = DisinfectionActivity.this.robotInterface;
                if (robotInterface != null) {
                    robotInterface.openSprayDevice(false, false);
                }
                DisinfectionActivity.this.updateSprayStatue(false);
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.drain_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$initSpray$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectionModuleManager.INSTANCE.getSprayDevice().drain();
            }
        });
        setSprayPower();
        ((Button) _$_findCachedViewById(C4144R.id.spray_power_up)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$initSpray$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i;
                int i2;
                int i3;
                DisinfectionActivity disinfectionActivity = DisinfectionActivity.this;
                i = disinfectionActivity.sprayPower;
                disinfectionActivity.sprayPower = i + 1;
                i2 = DisinfectionActivity.this.sprayPower;
                if (i2 > 4) {
                    DisinfectionActivity.this.sprayPower = 4;
                    return;
                }
                SharedPreferencesUtils sharedPreferencesUtils = SharedPreferencesUtils.INSTANCE;
                DisinfectionActivity disinfectionActivity2 = DisinfectionActivity.this;
                DisinfectionActivity disinfectionActivity3 = disinfectionActivity2;
                i3 = disinfectionActivity2.sprayPower;
                sharedPreferencesUtils.setParam(disinfectionActivity3, "spray_level", Integer.valueOf(i3));
                DisinfectionActivity.this.setSprayPower();
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.spray_power_down)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$initSpray$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i;
                int i2;
                int i3;
                DisinfectionActivity disinfectionActivity = DisinfectionActivity.this;
                i = disinfectionActivity.sprayPower;
                disinfectionActivity.sprayPower = i - 1;
                i2 = DisinfectionActivity.this.sprayPower;
                if (i2 < 1) {
                    DisinfectionActivity.this.sprayPower = 1;
                    return;
                }
                SharedPreferencesUtils sharedPreferencesUtils = SharedPreferencesUtils.INSTANCE;
                DisinfectionActivity disinfectionActivity2 = DisinfectionActivity.this;
                DisinfectionActivity disinfectionActivity3 = disinfectionActivity2;
                i3 = disinfectionActivity2.sprayPower;
                sharedPreferencesUtils.setParam(disinfectionActivity3, "spray_level", Integer.valueOf(i3));
                DisinfectionActivity.this.setSprayPower();
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.light_open_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$initSpray$6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectionActivity.this.m4304setAllLedb33U2AM((byte) -67, (byte) -67, (byte) 0);
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.light_close_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$initSpray$7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectionActivity.m4302setAllLedb33U2AM$default(DisinfectionActivity.this, (byte) 0, (byte) 0, (byte) 0, 7, null);
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.calibration_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$initSpray$8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                try {
                    EditText liquid_et = (EditText) DisinfectionActivity.this._$_findCachedViewById(C4144R.id.liquid_et);
                    Intrinsics.checkExpressionValueIsNotNull(liquid_et, "liquid_et");
                    String obj = liquid_et.getEditableText().toString();
                    EditText fog_et = (EditText) DisinfectionActivity.this._$_findCachedViewById(C4144R.id.fog_et);
                    Intrinsics.checkExpressionValueIsNotNull(fog_et, "fog_et");
                    DisinfectionModuleManager.INSTANCE.getSprayDevice().calibrationSpray(Integer.parseInt(obj), Integer.parseInt(fog_et.getEditableText().toString()));
                } catch (Exception e) {
                    str = DisinfectionActivity.this.TAG;
                    Pdlog.m3274e(str, "initSpray : " + Log.getStackTraceString(e));
                }
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.start_pre_liquid)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$initSpray$9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                try {
                    DisinfectionModuleManager.INSTANCE.getSprayDevice().open(true, false);
                } catch (Exception e) {
                    str = DisinfectionActivity.this.TAG;
                    Pdlog.m3273d(str, "start pre liquid error " + Log.getStackTraceString(e));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSprayPower() {
        DisinfectionActivity disinfectionActivity = this;
        Object param = SharedPreferencesUtils.INSTANCE.getParam(disinfectionActivity, SPRAY_LEVEL, Integer.valueOf(this.sprayPower));
        if (param == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
        }
        this.sprayPower = ((Integer) param).intValue();
        TextView spray_power_lv_tv = (TextView) _$_findCachedViewById(C4144R.id.spray_power_lv_tv);
        Intrinsics.checkExpressionValueIsNotNull(spray_power_lv_tv, "spray_power_lv_tv");
        spray_power_lv_tv.setText("功率：" + this.sprayPower);
        SprayDevice sprayDevice = DisinfectionModuleManager.INSTANCE.getSprayDevice();
        Object param2 = SharedPreferencesUtils.INSTANCE.getParam(disinfectionActivity, SPRAY_LEVEL, Integer.valueOf(this.sprayPower));
        if (param2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
        }
        sprayDevice.powerControl(((Integer) param2).intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLiquidLevelTv(double d) {
        TextView liquid_level_tv = (TextView) _$_findCachedViewById(C4144R.id.liquid_level_tv);
        Intrinsics.checkExpressionValueIsNotNull(liquid_level_tv, "liquid_level_tv");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("水箱值：%s mm", Arrays.copyOf(new Object[]{Double.valueOf(d)}, 1));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        liquid_level_tv.setText(format);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSprayChamberLevelTv(double d) {
        TextView spray_chamber_level_tv = (TextView) _$_findCachedViewById(C4144R.id.spray_chamber_level_tv);
        Intrinsics.checkExpressionValueIsNotNull(spray_chamber_level_tv, "spray_chamber_level_tv");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("雾化室值：%s mm", Arrays.copyOf(new Object[]{Double.valueOf(d)}, 1));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        spray_chamber_level_tv.setText(format);
    }

    private final void initConnection() {
        final String str = this.servicePath;
        final DisinfectionActivity$initConnection$2 disinfectionActivity$initConnection$2 = DisinfectionActivity$initConnection$2.INSTANCE;
        this.connection = new AIDLConnection<RobotInterface>(str, disinfectionActivity$initConnection$2) { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$initConnection$1
            @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                RobotInterface robotInterface;
                String str2;
                DisinfectionActivity$disliIDisinfectionRobotListener$1 disinfectionActivity$disliIDisinfectionRobotListener$1;
                RobotInterface robotInterface2;
                super.onServiceConnected(name, service);
                Pdlog.m3275i("RobotDevicesConnection", "onConnected name=" + name + "  " + service);
                DisinfectionActivity.this.robotInterface = getInterface();
                robotInterface = DisinfectionActivity.this.robotInterface;
                if (robotInterface == null) {
                    Intrinsics.throwNpe();
                }
                str2 = DisinfectionActivity.this.TAG;
                disinfectionActivity$disliIDisinfectionRobotListener$1 = DisinfectionActivity.this.disliIDisinfectionRobotListener;
                robotInterface.addDisinfectionRobotListener(str2, disinfectionActivity$disliIDisinfectionRobotListener$1);
                robotInterface2 = DisinfectionActivity.this.robotInterface;
                if (robotInterface2 != null) {
                    robotInterface2.open();
                }
            }

            @Override // android.content.ServiceConnection
            public void onBindingDied(ComponentName name) {
                String str2;
                super.onBindingDied(name);
                str2 = DisinfectionActivity.this.TAG;
                Pdlog.m3277w(str2, "onBindingDied name=" + name);
            }

            @Override // android.content.ServiceConnection
            public void onNullBinding(ComponentName name) {
                String str2;
                super.onNullBinding(name);
                str2 = DisinfectionActivity.this.TAG;
                Pdlog.m3277w(str2, "onNullBinding name=" + name);
            }

            @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                String str2;
                super.onServiceDisconnected(name);
                str2 = DisinfectionActivity.this.TAG;
                Pdlog.m3277w(str2, "onServiceDisconnected name=" + name);
            }
        };
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectionActivity$initConnection$3(this, null), 3, null);
    }

    /* renamed from: toRGBInt-b33U2AM$default, reason: not valid java name */
    public static /* synthetic */ int m4303toRGBIntb33U2AM$default(DisinfectionActivity disinfectionActivity, byte b, byte b2, byte b3, int i, Object obj) {
        if ((i & 1) != 0) {
            b = 0;
        }
        if ((i & 2) != 0) {
            b2 = 0;
        }
        if ((i & 4) != 0) {
            b3 = 0;
        }
        return disinfectionActivity.m4305toRGBIntb33U2AM(b, b2, b3);
    }

    /* renamed from: setAllLed-b33U2AM$default, reason: not valid java name */
    public static /* synthetic */ void m4302setAllLedb33U2AM$default(DisinfectionActivity disinfectionActivity, byte b, byte b2, byte b3, int i, Object obj) {
        if ((i & 1) != 0) {
            b = 0;
        }
        if ((i & 2) != 0) {
            b2 = 0;
        }
        if ((i & 4) != 0) {
            b3 = 0;
        }
        disinfectionActivity.m4304setAllLedb33U2AM(b, b2, b3);
    }

    /* renamed from: setAllLed-b33U2AM, reason: not valid java name */
    public final void m4304setAllLedb33U2AM(byte red, byte green, byte blue) {
        for (SurfaceLED surfaceLED : LEDHelper.INSTANCE.getDisinfection()) {
            RobotInterface robotInterface = this.robotInterface;
            if (robotInterface != null) {
                robotInterface.controlRGB(surfaceLED, m4305toRGBIntb33U2AM(red, green, blue), m4303toRGBIntb33U2AM$default(this, (byte) 0, (byte) 0, (byte) 0, 7, null), 1000);
            }
        }
        RobotInterface robotInterface2 = this.robotInterface;
        if (robotInterface2 != null) {
            robotInterface2.controlRGB(SurfaceLED.Bottom, m4305toRGBIntb33U2AM(red, green, blue), m4303toRGBIntb33U2AM$default(this, (byte) 0, (byte) 0, (byte) 0, 7, null), 1000);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        RobotInterface robotInterface = this.robotInterface;
        if (robotInterface != null) {
            robotInterface.removeDisinfectionRobotListener(this.TAG);
        }
        DisinfectionModuleManager.INSTANCE.setOnSwitchListener((Function1) null);
        AIDLConnection<RobotInterface> aIDLConnection = this.connection;
        if (aIDLConnection == null || aIDLConnection == null) {
            return;
        }
        aIDLConnection.disconnect(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer valueOf = v != null ? Integer.valueOf(v.getId()) : null;
        int i = C4144R.id.btSelect1;
        if (valueOf != null && valueOf.intValue() == i) {
            DisinfectionModuleManager.INSTANCE.m4322selectSpringType7apg3OU((byte) 1);
            return;
        }
        int i2 = C4144R.id.btSelect2;
        if (valueOf != null && valueOf.intValue() == i2) {
            DisinfectionModuleManager.INSTANCE.m4322selectSpringType7apg3OU((byte) 2);
        }
    }
}
