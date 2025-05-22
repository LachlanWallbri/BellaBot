package com.pudutech.mirsdk.hardware.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.os.EnvironmentCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.base.Tools;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.Encoder;
import com.pudutech.mirsdk.hardware.Gyroscope;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.HardwareScopeKt;
import com.pudutech.mirsdk.hardware.LidarInterface;
import com.pudutech.mirsdk.hardware.RGBDInterface;
import com.pudutech.mirsdk.hardware.ScheduleCommunication;
import com.pudutech.mirsdk.hardware.library.C5193R;
import com.pudutech.mirsdk.hardware.serialize.HardwareVersion;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.apache.commons.io.FilenameUtils;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00120\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0012H\u0002J\b\u0010\u0017\u001a\u00020\u0012H\u0002J\u0018\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002J\b\u0010\u001c\u001a\u00020\u001aH\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u001aH\u0002J\u0018\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0004H\u0002J\u0018\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u001aH\u0002J\b\u0010'\u001a\u00020\u0012H\u0002J\u0012\u0010(\u001a\u00020\u00122\b\u0010)\u001a\u0004\u0018\u00010*H\u0014J\b\u0010+\u001a\u00020\u0012H\u0014J\b\u0010,\u001a\u00020\u0012H\u0014J\b\u0010-\u001a\u00020\u0012H\u0014J\b\u0010.\u001a\u00020\u0012H\u0002J\b\u0010/\u001a\u00020\u0012H\u0002J\b\u00100\u001a\u00020\u0012H\u0002J\b\u00101\u001a\u00020\u0012H\u0002J\b\u00102\u001a\u00020\u0012H\u0002J\b\u00103\u001a\u00020\u0012H\u0002J\b\u00104\u001a\u00020\u0012H\u0002J\b\u00105\u001a\u00020\u0012H\u0002J\b\u00106\u001a\u00020\u0012H\u0002J\b\u00107\u001a\u00020\u0012H\u0002J\b\u00108\u001a\u00020\u0012H\u0002J\u0010\u00109\u001a\u00020\u00122\u0006\u0010:\u001a\u00020\bH\u0002J\b\u0010;\u001a\u00020\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006<"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/activity/HardwareActivity;", "Landroid/app/Activity;", "()V", "DisinfectionPowerOn", "", "IRLightOn", "SlamcorePowerOn", "TAG", "", "encoderAccumulate", "Lcom/pudutech/mirsdk/hardware/Encoder$DoubleWheel;", "gyroAccumulate", "Lcom/pudutech/mirsdk/hardware/Gyroscope$Data;", "hardware", "Lcom/pudutech/mirsdk/hardware/activity/HardwareConnection;", "moveJob", "Lkotlinx/coroutines/Job;", "checkAuth", "", "node", "callback", "Lkotlin/Function0;", "connectService", "destroy", "forwardBack", "speed", "", "loopTime", "getForwardBackLoopTime", "getResources", "Landroid/content/res/Resources;", "getSpeed", "lightCardview", "card", "Landroidx/cardview/widget/CardView;", "lightOn", "move", "line", "angular", "moveWithRoad", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "setupInitButton", "setupLidar", "setupLidarListener", "setupMarkerCamera", "setupMarkerCameraListener", "setupMoveButtons", "setupRGBD", "setupRGBDListener", "setupSleepMode", "showHardwareVersion", "showMachineInfoPreview", "showState", "state", "stopWheel", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class HardwareActivity extends Activity {
    private boolean DisinfectionPowerOn;
    private boolean IRLightOn;
    private boolean SlamcorePowerOn;
    private HashMap _$_findViewCache;
    private Job moveJob;
    private final String TAG = "HardwareApp";
    private Encoder.DoubleWheel encoderAccumulate = new Encoder.DoubleWheel(0.0d, 0.0d, 3, null);
    private Gyroscope.Data gyroAccumulate = new Gyroscope.Data();
    private final HardwareConnection hardware = HardwareConnection.INSTANCE;

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

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5193R.layout.hardware_activity_main);
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 0);
            }
            if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 0);
            }
        }
        connectService();
        setupInitButton();
        ((CardView) _$_findCachedViewById(C5193R.id.cardView_IRLight)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.checkAuth("camir", new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$1.1
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
                        HardwareConnection hardwareConnection;
                        boolean z;
                        hardwareConnection = HardwareActivity.this.hardware;
                        HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                        if (hardwareInterface != null) {
                            z = HardwareActivity.this.IRLightOn;
                            hardwareInterface.controlCameraIRDLED(!z);
                        }
                    }
                });
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.button_IRLight)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.checkAuth("camir", new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$2.1
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
                        HardwareConnection hardwareConnection;
                        boolean z;
                        hardwareConnection = HardwareActivity.this.hardware;
                        HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                        if (hardwareInterface != null) {
                            z = HardwareActivity.this.IRLightOn;
                            hardwareInterface.controlCameraIRDLED(!z);
                        }
                    }
                });
            }
        });
        ((CardView) _$_findCachedViewById(C5193R.id.cardView_Disinfection)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.checkAuth("disinf", new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$3.1
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
                        HardwareConnection hardwareConnection;
                        boolean z;
                        hardwareConnection = HardwareActivity.this.hardware;
                        HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                        if (hardwareInterface != null) {
                            z = HardwareActivity.this.DisinfectionPowerOn;
                            hardwareInterface.controlDisinfectionPower(!z);
                        }
                    }
                });
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.button_Disinfection)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.checkAuth("disinf", new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$4.1
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
                        HardwareConnection hardwareConnection;
                        boolean z;
                        hardwareConnection = HardwareActivity.this.hardware;
                        HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                        if (hardwareInterface != null) {
                            z = HardwareActivity.this.DisinfectionPowerOn;
                            hardwareInterface.controlDisinfectionPower(!z);
                        }
                    }
                });
            }
        });
        ((CardView) _$_findCachedViewById(C5193R.id.cardView_Slamcore)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.checkAuth("slmcor", new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$5.1
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
                        HardwareConnection hardwareConnection;
                        boolean z;
                        hardwareConnection = HardwareActivity.this.hardware;
                        HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                        if (hardwareInterface != null) {
                            z = HardwareActivity.this.SlamcorePowerOn;
                            hardwareInterface.controlSlamCorePower(!z);
                        }
                    }
                });
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.button_Slamcore)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.checkAuth("slmcor", new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$6.1
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
                        HardwareConnection hardwareConnection;
                        boolean z;
                        hardwareConnection = HardwareActivity.this.hardware;
                        HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                        if (hardwareInterface != null) {
                            z = HardwareActivity.this.SlamcorePowerOn;
                            hardwareInterface.controlSlamCorePower(!z);
                        }
                    }
                });
            }
        });
        setupMoveButtons();
        ((Button) _$_findCachedViewById(C5193R.id.button_ClearWheelError)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareConnection hardwareConnection;
                hardwareConnection = HardwareActivity.this.hardware;
                HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                if (hardwareInterface != null) {
                    hardwareInterface.clearWheelError();
                }
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.button_ClearIMU)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.gyroAccumulate = new Gyroscope.Data();
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.button_ClearEncoder)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.encoderAccumulate = new Encoder.DoubleWheel(0.0d, 0.0d, 3, null);
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.button_MachineInfo)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$10

            /* JADX WARN: Classes with same name are omitted:
              classes4.dex
             */
            /* compiled from: HardwareActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
            /* renamed from: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$10$1 */
            /* loaded from: classes5.dex */
            static final class C50901 extends Lambda implements Function0<Unit> {
                C50901() {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    HardwareActivity.access$setInfoSign$p(HardwareActivity.this, true);
                }
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareConnection hardwareConnection;
                String str;
                HashMap<MachineInfo.Byte24Info, UByte> byte24Data;
                HashMap<MachineInfo.Byte23Info, UByte> byte23Data;
                HashMap<MachineInfo.IntInfo, Integer> intData;
                HashMap<MachineInfo.FloatInfo, Float> floatData;
                Intent intent = new Intent(HardwareActivity.this, (Class<?>) MachineInfoActivity.class);
                hardwareConnection = HardwareActivity.this.hardware;
                HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                MachineInfo machineInfo = hardwareInterface != null ? hardwareInterface.getMachineInfo() : null;
                HashMap hashMap = new HashMap();
                if (machineInfo != null && (floatData = machineInfo.getFloatData()) != null) {
                    for (Map.Entry<MachineInfo.FloatInfo, Float> entry : floatData.entrySet()) {
                        hashMap.put(entry.getKey().name(), String.valueOf(entry.getValue().floatValue()));
                    }
                }
                if (machineInfo != null && (intData = machineInfo.getIntData()) != null) {
                    for (Map.Entry<MachineInfo.IntInfo, Integer> entry2 : intData.entrySet()) {
                        hashMap.put(entry2.getKey().name(), String.valueOf(entry2.getValue().intValue()));
                    }
                }
                if (machineInfo != null && (byte23Data = machineInfo.getByte23Data()) != null) {
                    for (Map.Entry<MachineInfo.Byte23Info, UByte> entry3 : byte23Data.entrySet()) {
                        hashMap.put(entry3.getKey().name(), UByte.m4563toStringimpl(entry3.getValue().getData()));
                    }
                }
                if (machineInfo != null && (byte24Data = machineInfo.getByte24Data()) != null) {
                    for (Map.Entry<MachineInfo.Byte24Info, UByte> entry4 : byte24Data.entrySet()) {
                        hashMap.put(entry4.getKey().name(), UByte.m4563toStringimpl(entry4.getValue().getData()));
                    }
                }
                str = HardwareActivity.this.TAG;
                Pdlog.m3275i(str, "machineInfo " + machineInfo);
                intent.putExtra("machine_info", hashMap);
                HardwareActivity.this.startActivity(intent);
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.button_LidarViewer)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.checkAuth("showld", new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$11.1
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
                        HardwareConnection hardwareConnection;
                        LidarInterface lidarInterface;
                        hardwareConnection = HardwareActivity.this.hardware;
                        HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                        if (hardwareInterface == null || (lidarInterface = hardwareInterface.getLidarInterface()) == null) {
                            return;
                        }
                        lidarInterface.startPreviewActivity();
                    }
                });
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.button_SecondLidarViewer)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.checkAuth("swsdld", new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$12.1
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
                        HardwareConnection hardwareConnection;
                        LidarInterface lidarInterface;
                        hardwareConnection = HardwareActivity.this.hardware;
                        HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                        if (hardwareInterface == null || (lidarInterface = hardwareInterface.getLidarInterface()) == null) {
                            return;
                        }
                        lidarInterface.startSecondLidarPreviewActivity();
                    }
                });
            }
        });
        setupLidar();
        ((Button) _$_findCachedViewById(C5193R.id.buttonOpenSchedule)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.checkAuth("opsc", new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$13.1
                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        ScheduleCommunication scheduler;
                        HardwareInterface hardwareInterface = HardwareConnection.INSTANCE.getInterface();
                        if (hardwareInterface == null || (scheduler = hardwareInterface.getScheduler()) == null) {
                            return;
                        }
                        scheduler.createConnection();
                    }
                });
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.buttonCloseSchedule)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.checkAuth("clsc", new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$14.1
                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        ScheduleCommunication scheduler;
                        HardwareInterface hardwareInterface = HardwareConnection.INSTANCE.getInterface();
                        if (hardwareInterface == null || (scheduler = hardwareInterface.getScheduler()) == null) {
                            return;
                        }
                        scheduler.destroyConnection();
                    }
                });
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.buttonPreviewSchedule)).setOnClickListener(new HardwareActivity$onCreate$15(this));
        ((Button) _$_findCachedViewById(C5193R.id.buttonOpenPeripherals)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$16
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                String str2;
                HardwareConnection hardwareConnection;
                ProductMachineType productType;
                ProductMachineType productType2;
                try {
                    str2 = HardwareActivity.this.TAG;
                    Pdlog.m3275i(str2, "open peripherals view  packageName:" + HardwareActivity.this.getPackageName() + ' ');
                    hardwareConnection = HardwareActivity.this.hardware;
                    HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                    MachineModel machineModel = null;
                    MachineInfo machineInfo = hardwareInterface != null ? hardwareInterface.getMachineInfo() : null;
                    if (((machineInfo == null || (productType2 = machineInfo.getProductType()) == null) ? null : productType2.getModel()) == MachineModel.RecycleDog) {
                        Tools.execCommand("am start -n " + HardwareActivity.this.getPackageName() + "/com.pudutech.bumblebee.robot.activity.RecycleRobotPeripheralsActivity", true);
                        return;
                    }
                    if (machineInfo != null && (productType = machineInfo.getProductType()) != null) {
                        machineModel = productType.getModel();
                    }
                    if (machineModel == MachineModel.Ninetales) {
                        Tools.execCommand("am start -n " + HardwareActivity.this.getPackageName() + "/com.pudutech.bumblebee.robot.activity.DisinfectionActivity", true);
                        return;
                    }
                    Tools.execCommand("am start -n " + HardwareActivity.this.getPackageName() + "/com.pudutech.bumblebee.robot.activity.PeripheralsActivity", true);
                } catch (Exception e) {
                    str = HardwareActivity.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("open peripherals view exception:");
                    e.printStackTrace();
                    sb.append(Unit.INSTANCE);
                    Pdlog.m3274e(str, sb.toString());
                }
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.buttonClearBraceState)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$17

            /* JADX WARN: Classes with same name are omitted:
              classes.dex
              classes4.dex
              classes5.dex
             */
            /* compiled from: HardwareActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$17$1", m3970f = "HardwareActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$17$1 */
            /* loaded from: classes2.dex */
            static final class C50961 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5964p$;

                C50961(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C50961 c50961 = new C50961(completion);
                    c50961.f5964p$ = (CoroutineScope) obj;
                    return c50961;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C50961) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    TextView textBrakeState = (TextView) HardwareActivity.this._$_findCachedViewById(C5193R.id.textBrakeState);
                    Intrinsics.checkExpressionValueIsNotNull(textBrakeState, "textBrakeState");
                    textBrakeState.setText("false");
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), Dispatchers.getMain(), null, new C50961(null), 2, null);
            }
        });
        setupMarkerCamera();
        setupRGBD();
        ((Switch) _$_findCachedViewById(C5193R.id.switchCarpetMode)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$18
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.checkAuth("carpet", new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onCreate$18.1
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
                        HardwareConnection hardwareConnection;
                        hardwareConnection = HardwareActivity.this.hardware;
                        HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                        if (hardwareInterface != null) {
                            Switch switchCarpetMode = (Switch) HardwareActivity.this._$_findCachedViewById(C5193R.id.switchCarpetMode);
                            Intrinsics.checkExpressionValueIsNotNull(switchCarpetMode, "switchCarpetMode");
                            hardwareInterface.switchCarpetMode(switchCarpetMode.isChecked());
                        }
                    }
                });
            }
        });
        BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new HardwareActivity$onCreate$19(this, null), 3, null);
        setupSleepMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkAuth(String node, Function0<Unit> callback) {
        callback.invoke();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        Resources res = super.getResources();
        Intrinsics.checkExpressionValueIsNotNull(res, "res");
        Configuration configuration = res.getConfiguration();
        configuration.fontScale = 1.5f;
        Context createConfigurationContext = createConfigurationContext(configuration);
        Intrinsics.checkExpressionValueIsNotNull(createConfigurationContext, "createConfigurationContext(configuration)");
        Resources resources = createConfigurationContext.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "createConfigurationConte…(configuration).resources");
        return resources;
    }

    private final void connectService() {
        BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new HardwareActivity$connectService$1(this, null), 3, null);
    }

    private final void setupInitButton() {
        ((Button) _$_findCachedViewById(C5193R.id.button_init)).setOnClickListener(new HardwareActivity$setupInitButton$1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showMachineInfoPreview() {
        MachineInfo machineInfo;
        Object obj;
        HardwareInterface hardwareInterface = this.hardware.getInterface();
        if (hardwareInterface == null || (machineInfo = hardwareInterface.getMachineInfo()) == null) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder sb = new StringBuilder();
        sb.append("LidarVersion:");
        if (machineInfo == null || (obj = machineInfo.getInt(MachineInfo.IntInfo.ldsSensorVersion)) == null) {
            obj = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        sb.append(obj);
        sb.append('\n');
        stringBuffer.append(sb.toString());
        stringBuffer.append("ESPMode:" + machineInfo.getESPMode().name() + '\n');
        stringBuffer.append("RGBDMode:" + machineInfo.getRGBDMode().name() + '\n');
        stringBuffer.append("Product:" + machineInfo.getProductType().getModel().name() + '\n');
        stringBuffer.append("Audio:" + machineInfo.getAudioType().name() + '\n');
        stringBuffer.append("Lora:" + machineInfo.getLoraType().name() + '\n');
        stringBuffer.append("ScanCode:" + machineInfo.getScanCodeDeviceType().name() + '\n');
        stringBuffer.append("Monocular:" + machineInfo.getMonocularDeviceType().name() + '\n');
        TextView textView_MachineInfoPreview = (TextView) _$_findCachedViewById(C5193R.id.textView_MachineInfoPreview);
        Intrinsics.checkExpressionValueIsNotNull(textView_MachineInfoPreview, "textView_MachineInfoPreview");
        textView_MachineInfoPreview.setText(stringBuffer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupLidarListener() {
        LidarInterface lidarInterface;
        LidarInterface lidarInterface2;
        HardwareInterface hardwareInterface = this.hardware.getInterface();
        if (hardwareInterface != null && (lidarInterface2 = hardwareInterface.getLidarInterface()) != null) {
            lidarInterface2.addDataListener("ui_debug", new HardwareActivity$setupLidarListener$1(this));
        }
        HardwareInterface hardwareInterface2 = this.hardware.getInterface();
        if (hardwareInterface2 == null || (lidarInterface = hardwareInterface2.getLidarInterface()) == null) {
            return;
        }
        lidarInterface.addStateListener("ui_debug", new HardwareActivity$setupLidarListener$2(this));
    }

    private final void setupLidar() {
        ((Button) _$_findCachedViewById(C5193R.id.button_OpenLidar)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupLidar$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.checkAuth("oplidr", new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupLidar$1.1
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
                        HardwareConnection hardwareConnection;
                        LidarInterface lidarInterface;
                        hardwareConnection = HardwareActivity.this.hardware;
                        HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                        if (hardwareInterface == null || (lidarInterface = hardwareInterface.getLidarInterface()) == null) {
                            return;
                        }
                        lidarInterface.open();
                    }
                });
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.button_StopLidar)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupLidar$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.checkAuth("stlidr", new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupLidar$2.1
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
                        HardwareConnection hardwareConnection;
                        LidarInterface lidarInterface;
                        hardwareConnection = HardwareActivity.this.hardware;
                        HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                        if (hardwareInterface == null || (lidarInterface = hardwareInterface.getLidarInterface()) == null) {
                            return;
                        }
                        lidarInterface.stop();
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupMarkerCameraListener() {
        CameraInterface camera;
        HardwareInterface hardwareInterface = this.hardware.getInterface();
        if (hardwareInterface == null || (camera = hardwareInterface.getCamera()) == null) {
            return;
        }
        camera.addMarkerCameraStateListener("ui_debug", new HardwareActivity$setupMarkerCameraListener$1(this));
    }

    private final void setupMarkerCamera() {
        ((Button) _$_findCachedViewById(C5193R.id.buttonCameraPreview)).setOnClickListener(new HardwareActivity$setupMarkerCamera$1(this));
        ((Button) _$_findCachedViewById(C5193R.id.buttonOpenMarkerCamera)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupMarkerCamera$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.checkAuth("opcm", new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupMarkerCamera$2.1
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
                        HardwareConnection hardwareConnection;
                        CameraInterface camera;
                        hardwareConnection = HardwareActivity.this.hardware;
                        HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                        if (hardwareInterface == null || (camera = hardwareInterface.getCamera()) == null) {
                            return;
                        }
                        camera.openMarkerCamera();
                    }
                });
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.buttonCloseMarkerCamera)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupMarkerCamera$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.checkAuth("clcm", new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupMarkerCamera$3.1
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
                        HardwareConnection hardwareConnection;
                        CameraInterface camera;
                        hardwareConnection = HardwareActivity.this.hardware;
                        HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                        if (hardwareInterface == null || (camera = hardwareInterface.getCamera()) == null) {
                            return;
                        }
                        camera.closeMarkerCamera();
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupRGBDListener() {
        RGBDInterface rGBDInterface;
        HardwareInterface hardwareInterface = this.hardware.getInterface();
        if (hardwareInterface == null || (rGBDInterface = hardwareInterface.getRGBDInterface()) == null) {
            return;
        }
        rGBDInterface.addStateListener("ui_debug", new HardwareActivity$setupRGBDListener$1(this));
    }

    private final void setupRGBD() {
        ((Button) _$_findCachedViewById(C5193R.id.buttonPauseRGBD)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupRGBD$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.checkAuth("psrgbd", new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupRGBD$1.1
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
                        HardwareConnection hardwareConnection;
                        hardwareConnection = HardwareActivity.this.hardware;
                        HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                        if (hardwareInterface != null) {
                            hardwareInterface.controlRGBD(false);
                        }
                    }
                });
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.buttonResumeRGBD)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupRGBD$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.checkAuth("rsrgbd", new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupRGBD$2.1
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
                        HardwareConnection hardwareConnection;
                        hardwareConnection = HardwareActivity.this.hardware;
                        HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                        if (hardwareInterface != null) {
                            hardwareInterface.controlRGBD(true);
                        }
                    }
                });
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.buttonOilStainCheck)).setOnClickListener(new HardwareActivity$setupRGBD$3(this));
        ((Button) _$_findCachedViewById(C5193R.id.buttonEnableParkingMode)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupRGBD$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.checkAuth("parkmd", new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupRGBD$4.1
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
                        HardwareConnection hardwareConnection;
                        hardwareConnection = HardwareActivity.this.hardware;
                        HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                        if (hardwareInterface != null) {
                            hardwareInterface.setRgbdParkingMode(true);
                        }
                    }
                });
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.buttonDisableParkingMode)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupRGBD$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.checkAuth("dispkm", new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupRGBD$5.1
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
                        HardwareConnection hardwareConnection;
                        hardwareConnection = HardwareActivity.this.hardware;
                        HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                        if (hardwareInterface != null) {
                            hardwareInterface.setRgbdParkingMode(false);
                        }
                    }
                });
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.buttonRgbdShow)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupRGBD$6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.checkAuth("swrgbd", new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupRGBD$6.1
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
                        HardwareConnection hardwareConnection;
                        RGBDInterface rGBDInterface;
                        hardwareConnection = HardwareActivity.this.hardware;
                        HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                        if (hardwareInterface == null || (rGBDInterface = hardwareInterface.getRGBDInterface()) == null) {
                            return;
                        }
                        rGBDInterface.startPreviewActivity();
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double getSpeed() {
        EditText editText_Speed = (EditText) _$_findCachedViewById(C5193R.id.editText_Speed);
        Intrinsics.checkExpressionValueIsNotNull(editText_Speed, "editText_Speed");
        Double doubleOrNull = StringsKt.toDoubleOrNull(editText_Speed.getText().toString());
        if (doubleOrNull == null) {
            doubleOrNull = Double.valueOf(0.0d);
        }
        return doubleOrNull.doubleValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double getForwardBackLoopTime() {
        EditText editText_ForwardBackTime = (EditText) _$_findCachedViewById(C5193R.id.editText_ForwardBackTime);
        Intrinsics.checkExpressionValueIsNotNull(editText_ForwardBackTime, "editText_ForwardBackTime");
        Double doubleOrNull = StringsKt.toDoubleOrNull(editText_ForwardBackTime.getText().toString());
        if (doubleOrNull == null) {
            doubleOrNull = Double.valueOf(0.0d);
        }
        return doubleOrNull.doubleValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void move(double line, double angular) {
        BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new HardwareActivity$move$1(this, line, angular, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveWithRoad() {
        BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new HardwareActivity$moveWithRoad$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void forwardBack(double speed, double loopTime) {
        BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new HardwareActivity$forwardBack$1(this, speed, loopTime, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopWheel() {
        BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new HardwareActivity$stopWheel$1(this, null), 3, null);
    }

    private final void setupMoveButtons() {
        ((Button) _$_findCachedViewById(C5193R.id.button_MoveForward)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupMoveButtons$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                double speed;
                speed = HardwareActivity.this.getSpeed();
                HardwareActivity.this.move(speed, 0.0d);
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.button_MoveBack)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupMoveButtons$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                double speed;
                speed = HardwareActivity.this.getSpeed();
                HardwareActivity.this.move(-speed, 0.0d);
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.button_MoveLeft)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupMoveButtons$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                double speed;
                speed = HardwareActivity.this.getSpeed();
                HardwareActivity.this.move(0.0d, speed);
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.button_MoveRight)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupMoveButtons$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                double speed;
                speed = HardwareActivity.this.getSpeed();
                HardwareActivity.this.move(0.0d, -speed);
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.buttonMoveWithRoad)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupMoveButtons$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.moveWithRoad();
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.button_MoveStop)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupMoveButtons$6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.stopWheel();
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.buttonForwardBack)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupMoveButtons$7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                double speed;
                double forwardBackLoopTime;
                HardwareActivity hardwareActivity = HardwareActivity.this;
                speed = hardwareActivity.getSpeed();
                forwardBackLoopTime = HardwareActivity.this.getForwardBackLoopTime();
                hardwareActivity.forwardBack(speed, forwardBackLoopTime);
            }
        });
        ((Button) _$_findCachedViewById(C5193R.id.button_CtrlReleaseMotor)).setOnClickListener(new HardwareActivity$setupMoveButtons$8(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showState(String state) {
        TextView textView_state = (TextView) _$_findCachedViewById(C5193R.id.textView_state);
        Intrinsics.checkExpressionValueIsNotNull(textView_state, "textView_state");
        textView_state.setText(state);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void lightCardview(CardView card, boolean lightOn) {
        if (lightOn) {
            card.setCardBackgroundColor(getResources().getColor(C5193R.color.hardwareColorCardViewEnable));
            return;
        }
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(C5193R.attr.colorBackgroundFloating, typedValue, true);
        card.setCardBackgroundColor(typedValue.data);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showHardwareVersion() {
        HardwareVersion[] hardwareVersion;
        HardwareInterface hardwareInterface = this.hardware.getInterface();
        if (hardwareInterface == null || (hardwareVersion = hardwareInterface.getHardwareVersion()) == null) {
            return;
        }
        String str = "version:";
        for (HardwareVersion hardwareVersion2 : hardwareVersion) {
            str = str + "\nboard:" + hardwareVersion2.getBoard() + '(' + hardwareVersion2.getBoard().getId() + ") " + hardwareVersion2.getVer0() + FilenameUtils.EXTENSION_SEPARATOR + hardwareVersion2.getVer1() + FilenameUtils.EXTENSION_SEPARATOR + hardwareVersion2.getVer2();
        }
        TextView textViewHardwareVersion = (TextView) _$_findCachedViewById(C5193R.id.textViewHardwareVersion);
        Intrinsics.checkExpressionValueIsNotNull(textViewHardwareVersion, "textViewHardwareVersion");
        textViewHardwareVersion.setText(str);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        showMachineInfoPreview();
        BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new HardwareActivity$onResume$1(this, null), 3, null);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new HardwareActivity$onPause$1(this, null), 3, null);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

    private final void destroy() {
        RGBDInterface rGBDInterface;
        LidarInterface lidarInterface;
        LidarInterface lidarInterface2;
        HardwareInterface hardwareInterface = this.hardware.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.removeCANDataListener("hardware_app");
        }
        HardwareInterface hardwareInterface2 = this.hardware.getInterface();
        if (hardwareInterface2 != null) {
            hardwareInterface2.removeListener("hardware_app");
        }
        HardwareInterface hardwareInterface3 = this.hardware.getInterface();
        if (hardwareInterface3 != null) {
            hardwareInterface3.removeSensorListener("Debug");
        }
        HardwareInterface hardwareInterface4 = this.hardware.getInterface();
        if (hardwareInterface4 != null && (lidarInterface2 = hardwareInterface4.getLidarInterface()) != null) {
            lidarInterface2.removeDataListener("ui_debug");
        }
        HardwareInterface hardwareInterface5 = this.hardware.getInterface();
        if (hardwareInterface5 != null && (lidarInterface = hardwareInterface5.getLidarInterface()) != null) {
            lidarInterface.removeStateListener("ui_debug");
        }
        HardwareInterface hardwareInterface6 = this.hardware.getInterface();
        if (hardwareInterface6 == null || (rGBDInterface = hardwareInterface6.getRGBDInterface()) == null) {
            return;
        }
        rGBDInterface.removeStateListener("ui_debug");
    }

    private final void setupSleepMode() {
        boolean z = SpUtils.get((Context) this, "mirhardware", "esp_enable", true);
        Switch switchESP = (Switch) _$_findCachedViewById(C5193R.id.switchESP);
        Intrinsics.checkExpressionValueIsNotNull(switchESP, "switchESP");
        switchESP.setChecked(z);
        ((Switch) _$_findCachedViewById(C5193R.id.switchESP)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupSleepMode$1

            /* compiled from: HardwareActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
            /* renamed from: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupSleepMode$1$2 */
            /* loaded from: classes.dex */
            static final class ViewOnClickListenerC51212 implements View.OnClickListener {
                final /* synthetic */ Dialog $checkPermissionDialog;
                final /* synthetic */ boolean $isChecked;

                ViewOnClickListenerC51212(boolean z, Dialog dialog) {
                    this.$isChecked = z;
                    this.$checkPermissionDialog = dialog;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Switch switchESP = (Switch) HardwareActivity.this._$_findCachedViewById(C5193R.id.switchESP);
                    Intrinsics.checkExpressionValueIsNotNull(switchESP, "switchESP");
                    switchESP.setChecked(!this.$isChecked);
                    this.$checkPermissionDialog.dismiss();
                }
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareActivity.this.checkAuth("swesp", new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupSleepMode$1.1
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
                        HardwareActivity hardwareActivity = HardwareActivity.this;
                        Switch switchESP2 = (Switch) HardwareActivity.this._$_findCachedViewById(C5193R.id.switchESP);
                        Intrinsics.checkExpressionValueIsNotNull(switchESP2, "switchESP");
                        SpUtils.set(hardwareActivity, "mirhardware", "esp_enable", switchESP2.isChecked());
                    }
                });
            }
        });
        Switch switchSleepMode = (Switch) _$_findCachedViewById(C5193R.id.switchSleepMode);
        Intrinsics.checkExpressionValueIsNotNull(switchSleepMode, "switchSleepMode");
        switchSleepMode.setChecked(false);
        ((Switch) _$_findCachedViewById(C5193R.id.switchSleepMode)).setOnClickListener(new HardwareActivity$setupSleepMode$2(this));
    }
}
