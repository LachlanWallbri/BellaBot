package com.pudutech.mirsdk.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import com.aliyun.alink.linksdk.tmp.device.panel.data.InvokeServiceData;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.CrossProcessTask;
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.WifiHotUtil;
import com.pudutech.mirsdk.activity.MirSDKActivity;
import com.pudutech.mirsdk.activity.SDKServiceConnection;
import com.pudutech.mirsdk.aidl.BluetoothChargeInterface;
import com.pudutech.mirsdk.aidl.DeviceInterface;
import com.pudutech.mirsdk.aidl.IDeviceListener;
import com.pudutech.mirsdk.aidl.MapAreaDetectionListener;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.aidl.serialize.AccessControlServer;
import com.pudutech.mirsdk.aidl.serialize.Destination;
import com.pudutech.mirsdk.aidl.serialize.ElevatorConnectionType;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.aidl.serialize.MapPackageConfig;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import com.pudutech.mirsdk.base.CommonKt;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import com.pudutech.mirsdk.config.SDKConfig;
import com.pudutech.mirsdk.function.BuildConfig;
import com.pudutech.mirsdk.function.C4946R;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdk.map.StringBase64Kt;
import com.pudutech.mirsdk.mapify.util.CamerConfigHelper;
import com.pudutech.mirsdk.mapify.util.ZipUtils;
import com.pudutech.mirsdk.sdksafe.SDKSafe;
import com.pudutech.schedulerlib.utils.CommandUtils;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.random.Random;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010)\u001a\u00020\u00152\u0006\u0010*\u001a\u00020+H\u0002J\u001e\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u00042\f\u0010/\u001a\b\u0012\u0004\u0012\u00020-00H\u0002J&\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u001e2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020-00H\u0002J\b\u00102\u001a\u00020-H\u0002J\b\u00103\u001a\u00020-H\u0002J\b\u00104\u001a\u00020-H\u0002J\b\u00105\u001a\u00020-H\u0002J\u0010\u00106\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u0004H\u0002J\u000e\u00108\u001a\u00020\u00042\u0006\u00109\u001a\u00020:J\u0010\u0010;\u001a\u00020\u00042\u0006\u0010<\u001a\u00020=H\u0003J\b\u0010>\u001a\u00020-H\u0002J\b\u0010?\u001a\u00020-H\u0002J\b\u0010@\u001a\u00020-H\u0002J\b\u0010A\u001a\u00020-H\u0002J\b\u0010B\u001a\u00020-H\u0002J\b\u0010C\u001a\u00020-H\u0002J\b\u0010D\u001a\u00020-H\u0002J\b\u0010E\u001a\u00020-H\u0002J\u0010\u0010F\u001a\u00020+2\u0006\u0010G\u001a\u00020\u0015H\u0002J\b\u0010H\u001a\u00020-H\u0002J\b\u0010I\u001a\u00020-H\u0002J\u0012\u0010J\u001a\u00020-2\b\u0010K\u001a\u0004\u0018\u00010LH\u0014J\b\u0010M\u001a\u00020-H\u0014J\b\u0010N\u001a\u00020-H\u0015J\b\u0010O\u001a\u00020-H\u0002J\b\u0010P\u001a\u00020-H\u0002J\u001e\u0010Q\u001a\u00020-2\u0006\u0010R\u001a\u00020\u00042\u0006\u0010S\u001a\u00020\u00152\u0006\u0010T\u001a\u00020\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00040#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00040#X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00040#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082.¢\u0006\u0002\n\u0000¨\u0006U"}, m3961d2 = {"Lcom/pudutech/mirsdk/activity/MirSDKActivity;", "Landroid/app/Activity;", "()V", "TAG", "", "accessServerAdapter", "Landroid/widget/ArrayAdapter;", "Lcom/pudutech/mirsdk/aidl/serialize/AccessControlServer;", "accessServerSpinner", "Landroid/widget/Spinner;", "checkPermissionDialog", "Landroid/app/Dialog;", "destinations", "", "Lcom/pudutech/mirsdk/aidl/serialize/Destination;", "elvConnectionTypeAdapter", "Lcom/pudutech/mirsdk/aidl/serialize/ElevatorConnectionType;", "elvConnectionTypeSpinner", "inputStream", "Ljava/io/InputStream;", "lastLayoutHeight", "", "lastLayoutWidth", "leftMageticMax", "outputStream", "Ljava/io/OutputStream;", "pose_update_time", "", "rightMageticMax", "show", "", "socket", "Ljava/net/Socket;", "speed_update_time", "starterGroupArray", "", "supportMagneticFunction", "tableArray", "tableNameArray", "view", "Landroid/view/View;", "byteArray2Int", "bytes", "", "checkAuth", "", "node", "callback", "Lkotlin/Function0;", "flag", "connectHardware", "connectSDK", "coreWorkSpace", "disableConnection", "findFloorId", "name", "getFileNameNoExtension", "file", "Ljava/io/File;", "getIpMacAddress", "context", "Landroid/content/Context;", "initDetDropSwitch", "initFallLevelListener", "initGeometicSwitch", "initReflectorSwitch", "initSlipControlSwitch", "initSpeedLimit", "initWifiAp", "installerComunicationTest", "int22ByteArray", "number", "mapWorkSpace", "moveWorkSpace", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "startConnection", "toolsWorkSpace", "updateConfigJson", "mapName", "sensorleft", "sensorRight", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MirSDKActivity extends Activity {
    private HashMap _$_findViewCache;
    private ArrayAdapter<AccessControlServer> accessServerAdapter;
    private Spinner accessServerSpinner;
    private Dialog checkPermissionDialog;
    private List<Destination> destinations;
    private ArrayAdapter<ElevatorConnectionType> elvConnectionTypeAdapter;
    private Spinner elvConnectionTypeSpinner;
    private InputStream inputStream;
    private int lastLayoutHeight;
    private int lastLayoutWidth;
    private int leftMageticMax;
    private OutputStream outputStream;
    private long pose_update_time;
    private int rightMageticMax;
    private boolean show;
    private Socket socket;
    private long speed_update_time;
    private boolean supportMagneticFunction;
    private View view;
    private final String TAG = "mirsdk_view";
    private final List<String> tableNameArray = new ArrayList();
    private final List<String> tableArray = new ArrayList();
    private final List<String> starterGroupArray = new ArrayList();

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SDKServiceConnection.DataType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;

        static {
            $EnumSwitchMapping$0[SDKServiceConnection.DataType.Battery.ordinal()] = 1;
            $EnumSwitchMapping$0[SDKServiceConnection.DataType.BatteryLevel.ordinal()] = 2;
            $EnumSwitchMapping$0[SDKServiceConnection.DataType.State.ordinal()] = 3;
            $EnumSwitchMapping$0[SDKServiceConnection.DataType.Speed.ordinal()] = 4;
            $EnumSwitchMapping$0[SDKServiceConnection.DataType.Pose.ordinal()] = 5;
            $EnumSwitchMapping$1 = new int[ChargeState.values().length];
            $EnumSwitchMapping$1[ChargeState.CharingUsePile.ordinal()] = 1;
            $EnumSwitchMapping$1[ChargeState.ChargeFullUsePile.ordinal()] = 2;
            $EnumSwitchMapping$2 = new int[ChargeState.values().length];
            $EnumSwitchMapping$2[ChargeState.CharingUsePile.ordinal()] = 1;
            $EnumSwitchMapping$2[ChargeState.ChargeFullUsePile.ordinal()] = 2;
            $EnumSwitchMapping$3 = new int[ChargeState.values().length];
            $EnumSwitchMapping$3[ChargeState.CharingUsePile.ordinal()] = 1;
            $EnumSwitchMapping$3[ChargeState.ChargeFullUsePile.ordinal()] = 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final byte[] int22ByteArray(int number) {
        return new byte[]{(byte) ((number >> 8) & 255), (byte) (number & 255)};
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

    public static final /* synthetic */ Spinner access$getAccessServerSpinner$p(MirSDKActivity mirSDKActivity) {
        Spinner spinner = mirSDKActivity.accessServerSpinner;
        if (spinner == null) {
            Intrinsics.throwUninitializedPropertyAccessException("accessServerSpinner");
        }
        return spinner;
    }

    public static final /* synthetic */ Spinner access$getElvConnectionTypeSpinner$p(MirSDKActivity mirSDKActivity) {
        Spinner spinner = mirSDKActivity.elvConnectionTypeSpinner;
        if (spinner == null) {
            Intrinsics.throwUninitializedPropertyAccessException("elvConnectionTypeSpinner");
        }
        return spinner;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        Pdlog.m3275i(this.TAG, "MirSDKActivity.onCreate");
        super.onCreate(savedInstanceState);
        setContentView(C4946R.layout.mirsdk_activity_main);
        MirSDKActivity mirSDKActivity = this;
        SDKConfig.INSTANCE.init(mirSDKActivity);
        this.checkPermissionDialog = new Dialog(mirSDKActivity, 0);
        Dialog dialog = this.checkPermissionDialog;
        if (dialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("checkPermissionDialog");
        }
        dialog.setCancelable(false);
        Dialog dialog2 = this.checkPermissionDialog;
        if (dialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("checkPermissionDialog");
        }
        dialog2.requestWindowFeature(1);
        View inflate = getLayoutInflater().inflate(C4946R.layout.dev_warning_dialog, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "this.layoutInflater.infl…dev_warning_dialog, null)");
        this.view = inflate;
        Dialog dialog3 = this.checkPermissionDialog;
        if (dialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("checkPermissionDialog");
        }
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        dialog3.setContentView(view);
        Dialog dialog4 = this.checkPermissionDialog;
        if (dialog4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("checkPermissionDialog");
        }
        Window window = dialog4.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        if (window != null) {
            window.setGravity(17);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 0);
            }
            if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 0);
            }
        }
        connectSDK();
        connectHardware();
        toolsWorkSpace();
        moveWorkSpace();
        initFallLevelListener();
        ((Button) _$_findCachedViewById(C4946R.id.button_set_battery_level)).setOnClickListener(new MirSDKActivity$onCreate$1(this));
        ((Button) _$_findCachedViewById(C4946R.id.button_calib)).setOnClickListener(new MirSDKActivity$onCreate$2(this));
        ((ConstraintLayout) _$_findCachedViewById(C4946R.id.layoutRoot)).addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$3

            /* compiled from: MirSDKActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
            /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$3$1 */
            /* loaded from: classes4.dex */
            static final class C48671 extends Lambda implements Function0<Unit> {
                C48671() {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    CrossProcessTask.INSTANCE.jumpActivity(MirSDKActivity.this, "com.pudutech.mirsdk.hardware", "com.pudutech.mirsdk.hardware.activity.HardwareActivity");
                }
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                int i9;
                String str;
                int i10;
                int i11;
                int i12;
                int i13;
                int i14;
                Intrinsics.checkParameterIsNotNull(view2, "<anonymous parameter 0>");
                i9 = MirSDKActivity.this.lastLayoutWidth;
                ConstraintLayout layoutRoot = (ConstraintLayout) MirSDKActivity.this._$_findCachedViewById(C4946R.id.layoutRoot);
                Intrinsics.checkExpressionValueIsNotNull(layoutRoot, "layoutRoot");
                if (i9 == layoutRoot.getWidth()) {
                    i14 = MirSDKActivity.this.lastLayoutHeight;
                    ConstraintLayout layoutRoot2 = (ConstraintLayout) MirSDKActivity.this._$_findCachedViewById(C4946R.id.layoutRoot);
                    Intrinsics.checkExpressionValueIsNotNull(layoutRoot2, "layoutRoot");
                    if (i14 == layoutRoot2.getHeight()) {
                        return;
                    }
                }
                MirSDKActivity mirSDKActivity2 = MirSDKActivity.this;
                ConstraintLayout layoutRoot3 = (ConstraintLayout) mirSDKActivity2._$_findCachedViewById(C4946R.id.layoutRoot);
                Intrinsics.checkExpressionValueIsNotNull(layoutRoot3, "layoutRoot");
                mirSDKActivity2.lastLayoutWidth = layoutRoot3.getWidth();
                MirSDKActivity mirSDKActivity3 = MirSDKActivity.this;
                ConstraintLayout layoutRoot4 = (ConstraintLayout) mirSDKActivity3._$_findCachedViewById(C4946R.id.layoutRoot);
                Intrinsics.checkExpressionValueIsNotNull(layoutRoot4, "layoutRoot");
                mirSDKActivity3.lastLayoutHeight = layoutRoot4.getHeight();
                str = MirSDKActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("OnLayoutChangeListener width:");
                i10 = MirSDKActivity.this.lastLayoutWidth;
                sb.append(i10);
                sb.append(' ');
                i11 = MirSDKActivity.this.lastLayoutHeight;
                sb.append(i11);
                Pdlog.m3276v(str, sb.toString());
                i12 = MirSDKActivity.this.lastLayoutWidth;
                double d = i12 * 0.7d;
                i13 = MirSDKActivity.this.lastLayoutHeight;
                if (d < i13) {
                    LinearLayout linearLayoutRoot = (LinearLayout) MirSDKActivity.this._$_findCachedViewById(C4946R.id.linearLayoutRoot);
                    Intrinsics.checkExpressionValueIsNotNull(linearLayoutRoot, "linearLayoutRoot");
                    linearLayoutRoot.setOrientation(1);
                } else {
                    LinearLayout linearLayoutRoot2 = (LinearLayout) MirSDKActivity.this._$_findCachedViewById(C4946R.id.linearLayoutRoot);
                    Intrinsics.checkExpressionValueIsNotNull(linearLayoutRoot2, "linearLayoutRoot");
                    linearLayoutRoot2.setOrientation(0);
                }
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.buttonHardware)).setOnClickListener(new MirSDKActivity$onCreate$4(this));
        coreWorkSpace();
        TextView textViewIP = (TextView) _$_findCachedViewById(C4946R.id.textViewIP);
        Intrinsics.checkExpressionValueIsNotNull(textViewIP, "textViewIP");
        textViewIP.setText(getIpMacAddress(mirSDKActivity));
        TextView textViewMac = (TextView) _$_findCachedViewById(C4946R.id.textViewMac);
        Intrinsics.checkExpressionValueIsNotNull(textViewMac, "textViewMac");
        textViewMac.setText(CommonKt.getWifiMacMethod());
        TextView textViewSoftwareVer = (TextView) _$_findCachedViewById(C4946R.id.textViewSoftwareVer);
        Intrinsics.checkExpressionValueIsNotNull(textViewSoftwareVer, "textViewSoftwareVer");
        textViewSoftwareVer.setText(BuildConfig.VERSION_NAME);
        ((Button) _$_findCachedViewById(C4946R.id.buttonStartUp)).setOnClickListener(new MirSDKActivity$onCreate$5(this));
        ((Switch) _$_findCachedViewById(C4946R.id.switchInstallMode)).setOnClickListener(new MirSDKActivity$onCreate$6(this));
        CardView securityLayout = (CardView) _$_findCachedViewById(C4946R.id.securityLayout);
        Intrinsics.checkExpressionValueIsNotNull(securityLayout, "securityLayout");
        securityLayout.setVisibility(8);
        ((Switch) _$_findCachedViewById(C4946R.id.securitySwitch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$7
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                    if (sDKInterface != null) {
                        sDKInterface.securitySwitch(false, true);
                        return;
                    }
                    return;
                }
                SDKInterface sDKInterface2 = SDKServiceConnection.INSTANCE.getInterface();
                if (sDKInterface2 != null) {
                    sDKInterface2.securitySwitch(true, false);
                }
            }

            /* compiled from: MirSDKActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$7$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$7$1 */
            /* loaded from: classes4.dex */
            static final class C48721 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ double $rotate_angular;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5684p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C48721(double d, Continuation continuation) {
                    super(2, continuation);
                    this.$rotate_angular = d;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C48721 c48721 = new C48721(this.$rotate_angular, completion);
                    c48721.f5684p$ = (CoroutineScope) obj;
                    return c48721;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C48721) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    MoveActionInterface moveActionInterface;
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f5684p$;
                    SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                    if (sDKInterface != null && (moveActionInterface = sDKInterface.getMoveActionInterface()) != null) {
                        moveActionInterface.rotate(this.$rotate_angular);
                    }
                    return Unit.INSTANCE;
                }
            }
        });
        ((Switch) _$_findCachedViewById(C4946R.id.lockmotorSwitch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$8
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                DeviceInterface deviceInterface;
                SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                if (sDKInterface == null || (deviceInterface = sDKInterface.getDeviceInterface()) == null) {
                    return;
                }
                deviceInterface.lockMotor(arg1);
            }
        });
        ((Switch) _$_findCachedViewById(C4946R.id.reflectorSwitch)).setOnCheckedChangeListener(new MirSDKActivity$onCreate$9(this));
        ((Switch) _$_findCachedViewById(C4946R.id.fallprevention)).setOnCheckedChangeListener(new MirSDKActivity$onCreate$10(this));
        ((Switch) _$_findCachedViewById(C4946R.id.slipControlSwitch)).setOnCheckedChangeListener(new MirSDKActivity$onCreate$11(this));
        mapWorkSpace();
        View findViewById = findViewById(C4946R.id.access_mqtt_server_list);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.access_mqtt_server_list)");
        this.accessServerSpinner = (Spinner) findViewById;
        this.accessServerAdapter = new ArrayAdapter<>(mirSDKActivity, C4946R.layout.support_simple_spinner_dropdown_item, AccessControlServer.values());
        Spinner spinner = this.accessServerSpinner;
        if (spinner == null) {
            Intrinsics.throwUninitializedPropertyAccessException("accessServerSpinner");
        }
        ArrayAdapter<AccessControlServer> arrayAdapter = this.accessServerAdapter;
        if (arrayAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("accessServerAdapter");
        }
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        Spinner spinner2 = this.accessServerSpinner;
        if (spinner2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("accessServerSpinner");
        }
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$12
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> p0) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view2, int position, long id) {
                Intrinsics.checkParameterIsNotNull(parent, "parent");
                Object selectedItem = parent.getSelectedItem();
                if (selectedItem != null) {
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MirSDKActivity$onCreate$12$onItemSelected$1((AccessControlServer) selectedItem, null), 3, null);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.mirsdk.aidl.serialize.AccessControlServer");
            }
        });
        View findViewById2 = findViewById(C4946R.id.elv_conn_list);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById(R.id.elv_conn_list)");
        this.elvConnectionTypeSpinner = (Spinner) findViewById2;
        this.elvConnectionTypeAdapter = new ArrayAdapter<>(mirSDKActivity, C4946R.layout.support_simple_spinner_dropdown_item, ElevatorConnectionType.values());
        Spinner spinner3 = this.elvConnectionTypeSpinner;
        if (spinner3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("elvConnectionTypeSpinner");
        }
        ArrayAdapter<ElevatorConnectionType> arrayAdapter2 = this.elvConnectionTypeAdapter;
        if (arrayAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("elvConnectionTypeAdapter");
        }
        spinner3.setAdapter((SpinnerAdapter) arrayAdapter2);
        Spinner spinner4 = this.elvConnectionTypeSpinner;
        if (spinner4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("elvConnectionTypeSpinner");
        }
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$13
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> p0) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view2, int position, long id) {
                Intrinsics.checkParameterIsNotNull(parent, "parent");
                Object selectedItem = parent.getSelectedItem();
                if (selectedItem != null) {
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MirSDKActivity$onCreate$13$onItemSelected$1((ElevatorConnectionType) selectedItem, null), 3, null);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.mirsdk.aidl.serialize.ElevatorConnectionType");
            }
        });
        installerComunicationTest();
        ((Button) _$_findCachedViewById(C4946R.id.switch_log_grade)).setOnClickListener(new MirSDKActivity$onCreate$14(this));
        initWifiAp();
        ((Button) _$_findCachedViewById(C4946R.id.button_boot_pose_list)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                String str;
                SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                List<String> relocationPoints = sDKInterface != null ? sDKInterface.getRelocationPoints() : null;
                if (relocationPoints != null && !relocationPoints.isEmpty()) {
                    str = MirSDKActivity.this.TAG;
                    Pdlog.m3275i(str, "showing boot pose list size:" + relocationPoints.size());
                    AlertDialog.Builder builder = new AlertDialog.Builder(MirSDKActivity.this);
                    Object[] array = relocationPoints.toArray(new String[0]);
                    if (array != null) {
                        builder.setItems((String[]) array, (DialogInterface.OnClickListener) null).setPositiveButton("OK", (DialogInterface.OnClickListener) null).show();
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                }
                Toast.makeText(MirSDKActivity.this, "boot pose list empty", 0).show();
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.button_set_boot_pose)).setOnClickListener(new MirSDKActivity$onCreate$16(this));
        ((Button) _$_findCachedViewById(C4946R.id.button_set_charge_pose)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$17
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MirSDKActivity mirSDKActivity2 = MirSDKActivity.this;
                mirSDKActivity2.startActivity(new Intent(mirSDKActivity2, (Class<?>) ChargeSetActivity.class));
            }
        });
        SDKServiceConnection.INSTANCE.setSecurityCallback(new Function1<Boolean, Unit>() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$18
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MirSDKActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$18$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$18$1 */
            /* loaded from: classes5.dex */
            public static final class C48621 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ boolean $it;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5679p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C48621(boolean z, Continuation continuation) {
                    super(2, continuation);
                    this.$it = z;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C48621 c48621 = new C48621(this.$it, completion);
                    c48621.f5679p$ = (CoroutineScope) obj;
                    return c48621;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C48621) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f5679p$;
                    if (this.$it) {
                        Toast.makeText(MirSDKActivity.this, "安全开关已打开", 0).show();
                    } else {
                        Toast.makeText(MirSDKActivity.this, "安全开关已关闭", 0).show();
                    }
                    return Unit.INSTANCE;
                }
            }

            public final void invoke(boolean z) {
                String str;
                str = MirSDKActivity.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("securityCallback status: ");
                sb.append(z ? "has opened" : "has closed");
                objArr[0] = sb.toString();
                Pdlog.m3273d(str, objArr);
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new C48621(z, null), 2, null);
            }
        });
        checkAuth("debug", MirSDKActivity$onCreate$19.INSTANCE);
    }

    private final void initWifiAp() {
        Pdlog.m3273d(this.TAG, "BuildConfig is RELEASE");
        WifiHotUtil wifiHotUtil = new WifiHotUtil(this);
        WifiConfiguration wifiApConfig = wifiHotUtil.getWifiApConfig();
        if (wifiApConfig != null) {
            if (!TextUtils.isEmpty(wifiApConfig.SSID)) {
                String str = getString(C4946R.string.ap_name) + wifiApConfig.SSID;
                TextView ap_ssid = (TextView) _$_findCachedViewById(C4946R.id.ap_ssid);
                Intrinsics.checkExpressionValueIsNotNull(ap_ssid, "ap_ssid");
                ap_ssid.setText(str);
            }
            if (!TextUtils.isEmpty(wifiApConfig.preSharedKey)) {
                String str2 = getString(C4946R.string.ap_password) + wifiApConfig.preSharedKey;
                TextView ap_pd = (TextView) _$_findCachedViewById(C4946R.id.ap_pd);
                Intrinsics.checkExpressionValueIsNotNull(ap_pd, "ap_pd");
                ap_pd.setText(str2);
            }
        }
        if (wifiHotUtil.isWifiApEnabled()) {
            String str3 = getString(C4946R.string.ap_status) + " OPEN";
            TextView ap_status = (TextView) _$_findCachedViewById(C4946R.id.ap_status);
            Intrinsics.checkExpressionValueIsNotNull(ap_status, "ap_status");
            ap_status.setText(str3);
        } else {
            String str4 = getString(C4946R.string.ap_status) + " CLOSE";
            TextView ap_status2 = (TextView) _$_findCachedViewById(C4946R.id.ap_status);
            Intrinsics.checkExpressionValueIsNotNull(ap_status2, "ap_status");
            ap_status2.setText(str4);
        }
        ((Button) _$_findCachedViewById(C4946R.id.wifihotspot_control)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$initWifiAp$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                new WifiHotspotSetDialog(MirSDKActivity.this).show();
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.wifihotspot_open)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$initWifiAp$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiHotUtil wifiHotUtil2 = new WifiHotUtil(MirSDKActivity.this);
                WifiConfiguration wifiApConfig2 = wifiHotUtil2.getWifiApConfig();
                if (wifiApConfig2 == null) {
                    Toast.makeText(MirSDKActivity.this, "can not get wifi ap config!!", 1).show();
                    return;
                }
                if (TextUtils.isEmpty(wifiApConfig2.SSID)) {
                    Toast.makeText(MirSDKActivity.this, "can not get wifi ap ssid!!", 1).show();
                    return;
                }
                if (wifiHotUtil2.startWifiAp(wifiApConfig2.SSID, wifiApConfig2.preSharedKey)) {
                    String str5 = MirSDKActivity.this.getString(C4946R.string.ap_status) + " OPEN";
                    TextView ap_status3 = (TextView) MirSDKActivity.this._$_findCachedViewById(C4946R.id.ap_status);
                    Intrinsics.checkExpressionValueIsNotNull(ap_status3, "ap_status");
                    ap_status3.setText(str5);
                    return;
                }
                String str6 = MirSDKActivity.this.getString(C4946R.string.ap_status) + " CLOSE";
                TextView ap_status4 = (TextView) MirSDKActivity.this._$_findCachedViewById(C4946R.id.ap_status);
                Intrinsics.checkExpressionValueIsNotNull(ap_status4, "ap_status");
                ap_status4.setText(str6);
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.wifihotspot_close)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$initWifiAp$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                new WifiHotUtil(MirSDKActivity.this).closeWifiAp();
                String str5 = MirSDKActivity.this.getString(C4946R.string.ap_status) + " CLOSE";
                TextView ap_status3 = (TextView) MirSDKActivity.this._$_findCachedViewById(C4946R.id.ap_status);
                Intrinsics.checkExpressionValueIsNotNull(ap_status3, "ap_status");
                ap_status3.setText(str5);
            }
        });
    }

    private final void initFallLevelListener() {
        int i = SDKConfig.INSTANCE.getPreferences().getInt("BrakingLevel", 2);
        if (i == 1) {
            RadioButton drop_leve_one = (RadioButton) _$_findCachedViewById(C4946R.id.drop_leve_one);
            Intrinsics.checkExpressionValueIsNotNull(drop_leve_one, "drop_leve_one");
            drop_leve_one.setChecked(true);
        } else if (i == 2) {
            RadioButton drop_leve_two = (RadioButton) _$_findCachedViewById(C4946R.id.drop_leve_two);
            Intrinsics.checkExpressionValueIsNotNull(drop_leve_two, "drop_leve_two");
            drop_leve_two.setChecked(true);
        } else if (i == 3) {
            RadioButton drop_leve_third = (RadioButton) _$_findCachedViewById(C4946R.id.drop_leve_third);
            Intrinsics.checkExpressionValueIsNotNull(drop_leve_third, "drop_leve_third");
            drop_leve_third.setChecked(true);
        }
        ((RadioGroup) _$_findCachedViewById(C4946R.id.drop_leve_group)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$initFallLevelListener$1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Integer valueOf = group != null ? Integer.valueOf(group.getCheckedRadioButtonId()) : null;
                RadioButton drop_leve_one2 = (RadioButton) MirSDKActivity.this._$_findCachedViewById(C4946R.id.drop_leve_one);
                Intrinsics.checkExpressionValueIsNotNull(drop_leve_one2, "drop_leve_one");
                int id = drop_leve_one2.getId();
                if (valueOf != null && valueOf.intValue() == id) {
                    SDKConfig.INSTANCE.getPreferences().edit().putInt("BrakingLevel", 1).apply();
                    return;
                }
                RadioButton drop_leve_two2 = (RadioButton) MirSDKActivity.this._$_findCachedViewById(C4946R.id.drop_leve_two);
                Intrinsics.checkExpressionValueIsNotNull(drop_leve_two2, "drop_leve_two");
                int id2 = drop_leve_two2.getId();
                if (valueOf != null && valueOf.intValue() == id2) {
                    SDKConfig.INSTANCE.getPreferences().edit().putInt("BrakingLevel", 2).apply();
                    return;
                }
                RadioButton drop_leve_third2 = (RadioButton) MirSDKActivity.this._$_findCachedViewById(C4946R.id.drop_leve_third);
                Intrinsics.checkExpressionValueIsNotNull(drop_leve_third2, "drop_leve_third");
                int id3 = drop_leve_third2.getId();
                if (valueOf != null && valueOf.intValue() == id3) {
                    SDKConfig.INSTANCE.getPreferences().edit().putInt("BrakingLevel", 3).apply();
                }
            }
        });
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$initFallLevelListener$2(this, null), 2, null);
        ((Button) _$_findCachedViewById(C4946R.id.rgbd_limit_save)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$initFallLevelListener$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditText speed_limit = (EditText) MirSDKActivity.this._$_findCachedViewById(C4946R.id.speed_limit);
                Intrinsics.checkExpressionValueIsNotNull(speed_limit, "speed_limit");
                float parseFloat = Float.parseFloat(speed_limit.getText().toString());
                EditText distance_limit = (EditText) MirSDKActivity.this._$_findCachedViewById(C4946R.id.distance_limit);
                Intrinsics.checkExpressionValueIsNotNull(distance_limit, "distance_limit");
                String obj = distance_limit.getText().toString();
                EditText distance_brake = (EditText) MirSDKActivity.this._$_findCachedViewById(C4946R.id.distance_brake);
                Intrinsics.checkExpressionValueIsNotNull(distance_brake, "distance_brake");
                String obj2 = distance_brake.getText().toString();
                SDKConfig.INSTANCE.getPreferences().edit().putFloat("speedLimit", parseFloat).apply();
                SDKConfig.INSTANCE.getPreferences().edit().putString("distancelimit", obj).apply();
                SDKConfig.INSTANCE.getPreferences().edit().putString("distancebrake", obj2).apply();
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.reflector_limit_save)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$initFallLevelListener$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditText in_brake_dist = (EditText) MirSDKActivity.this._$_findCachedViewById(C4946R.id.in_brake_dist);
                Intrinsics.checkExpressionValueIsNotNull(in_brake_dist, "in_brake_dist");
                double parseDouble = Double.parseDouble(in_brake_dist.getText().toString());
                EditText follow_line_dist = (EditText) MirSDKActivity.this._$_findCachedViewById(C4946R.id.follow_line_dist);
                Intrinsics.checkExpressionValueIsNotNull(follow_line_dist, "follow_line_dist");
                double parseDouble2 = Double.parseDouble(follow_line_dist.getText().toString());
                EditText slow_down_dist = (EditText) MirSDKActivity.this._$_findCachedViewById(C4946R.id.slow_down_dist);
                Intrinsics.checkExpressionValueIsNotNull(slow_down_dist, "slow_down_dist");
                double parseDouble3 = Double.parseDouble(slow_down_dist.getText().toString());
                EditText out_brake_dist = (EditText) MirSDKActivity.this._$_findCachedViewById(C4946R.id.out_brake_dist);
                Intrinsics.checkExpressionValueIsNotNull(out_brake_dist, "out_brake_dist");
                double parseDouble4 = Double.parseDouble(out_brake_dist.getText().toString());
                if (parseDouble < parseDouble2 && parseDouble2 < parseDouble3 && parseDouble4 < parseDouble2) {
                    Toast.makeText(MirSDKActivity.this, "反光板参数配置成功", 0).show();
                    CamerConfigHelper.INSTANCE.setReflectorParam(parseDouble, parseDouble2, parseDouble3, parseDouble4);
                    SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                    if (sDKInterface != null) {
                        sDKInterface.enableReflector(CamerConfigHelper.INSTANCE.getReflectorSwitch() > 0);
                        return;
                    }
                    return;
                }
                Toast.makeText(MirSDKActivity.this, "反光板参数配置不合法, 使用修改前的参数", 0).show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkAuth(String node, final Function0<Unit> callback) {
        SDKSafe.INSTANCE.checkControlAuth(this, node, new Function1<Integer, Unit>() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$checkAuth$1
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkAuth(String node, boolean flag, final Function0<Unit> callback) {
        SDKSafe.INSTANCE.checkControlAuth(this, flag, node, new Function1<Integer, Unit>() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$checkAuth$2
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

    private final void connectSDK() {
        DeviceInterface deviceInterface;
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MirSDKActivity$connectSDK$1(this, null), 3, null);
        SDKServiceConnection.INSTANCE.setOnStateChange(new Function2<SDKServiceConnection.DataType, String, Unit>() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(SDKServiceConnection.DataType dataType, String str) {
                invoke2(dataType, str);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: Classes with same name are omitted:
              classes4.dex
             */
            /* compiled from: MirSDKActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$2$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$2$1 */
            /* loaded from: classes5.dex */
            public static final class C48271 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $data;
                final /* synthetic */ SDKServiceConnection.DataType $type;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5625p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C48271(SDKServiceConnection.DataType dataType, String str, Continuation continuation) {
                    super(2, continuation);
                    this.$type = dataType;
                    this.$data = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C48271 c48271 = new C48271(this.$type, this.$data, completion);
                    c48271.f5625p$ = (CoroutineScope) obj;
                    return c48271;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C48271) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Code restructure failed: missing block: B:16:0x0047, code lost:
                
                    if ((r2 - r4) > 200) goto L19;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:24:0x008a, code lost:
                
                    if ((r2 - r4) > 200) goto L24;
                 */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final Object invokeSuspend(Object obj) {
                    long j;
                    long j2;
                    long j3;
                    long j4;
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f5625p$;
                    int i = MirSDKActivity.WhenMappings.$EnumSwitchMapping$0[this.$type.ordinal()];
                    if (i == 1) {
                        TextView textViewBattery = (TextView) MirSDKActivity.this._$_findCachedViewById(C4946R.id.textViewBattery);
                        Intrinsics.checkExpressionValueIsNotNull(textViewBattery, "textViewBattery");
                        textViewBattery.setText(this.$data);
                    } else if (i == 2) {
                        TextView textViewBatteryLevel = (TextView) MirSDKActivity.this._$_findCachedViewById(C4946R.id.textViewBatteryLevel);
                        Intrinsics.checkExpressionValueIsNotNull(textViewBatteryLevel, "textViewBatteryLevel");
                        textViewBatteryLevel.setText(this.$data);
                    } else if (i == 3) {
                        String str = this.$data;
                        int length = str.length();
                        int i2 = 0;
                        while (true) {
                            if (i2 >= length) {
                                i2 = -1;
                                break;
                            }
                            if (Boxing.boxBoolean(Boxing.boxChar(Boxing.boxChar(str.charAt(i2)).charValue()).equals(Boxing.boxChar(' '))).booleanValue()) {
                                break;
                            }
                            i2++;
                        }
                        int indexOf = StringsKt.indexOf((CharSequence) this.$data, ' ', i2 + 1, false);
                        TextView tx_state = (TextView) MirSDKActivity.this._$_findCachedViewById(C4946R.id.tx_state);
                        Intrinsics.checkExpressionValueIsNotNull(tx_state, "tx_state");
                        tx_state.setText(this.$data.subSequence(i2, indexOf));
                        TextView tx_desc = (TextView) MirSDKActivity.this._$_findCachedViewById(C4946R.id.tx_desc);
                        Intrinsics.checkExpressionValueIsNotNull(tx_desc, "tx_desc");
                        String str2 = this.$data;
                        if (str2 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        String substring = str2.substring(indexOf);
                        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                        tx_desc.setText(substring);
                    } else if (i == 4) {
                        j = MirSDKActivity.this.speed_update_time;
                        if (j != 0) {
                            long elapsedRealtime = SystemClock.elapsedRealtime();
                            j2 = MirSDKActivity.this.speed_update_time;
                        }
                        TextView textViewSpeed = (TextView) MirSDKActivity.this._$_findCachedViewById(C4946R.id.textViewSpeed);
                        Intrinsics.checkExpressionValueIsNotNull(textViewSpeed, "textViewSpeed");
                        textViewSpeed.setText(this.$data);
                        MirSDKActivity.this.speed_update_time = SystemClock.elapsedRealtime();
                    } else if (i == 5) {
                        j3 = MirSDKActivity.this.pose_update_time;
                        if (j3 != 0) {
                            long elapsedRealtime2 = SystemClock.elapsedRealtime();
                            j4 = MirSDKActivity.this.pose_update_time;
                        }
                        TextView textViewPose = (TextView) MirSDKActivity.this._$_findCachedViewById(C4946R.id.textViewPose);
                        Intrinsics.checkExpressionValueIsNotNull(textViewPose, "textViewPose");
                        textViewPose.setText(this.$data);
                        MirSDKActivity.this.pose_update_time = SystemClock.elapsedRealtime();
                    }
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SDKServiceConnection.DataType type, String data) {
                Intrinsics.checkParameterIsNotNull(type, "type");
                Intrinsics.checkParameterIsNotNull(data, "data");
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new C48271(type, data, null), 2, null);
            }
        });
        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
        if (sDKInterface != null && (deviceInterface = sDKInterface.getDeviceInterface()) != null) {
            deviceInterface.addListener("business_mock", new IDeviceListener.Stub() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$3
                @Override // com.pudutech.mirsdk.aidl.IDeviceListener
                public void onBumperSwitchStatus(boolean result) {
                }

                @Override // com.pudutech.mirsdk.aidl.IDeviceListener
                public void onLidar(boolean open) {
                    String str;
                    str = MirSDKActivity.this.TAG;
                    Pdlog.m3275i(str, "onLider :" + open);
                }

                @Override // com.pudutech.mirsdk.aidl.IDeviceListener
                public void onIRLED(boolean lightOn) {
                    String str;
                    str = MirSDKActivity.this.TAG;
                    Pdlog.m3275i(str, "onIRLED :" + lightOn);
                }

                @Override // com.pudutech.mirsdk.aidl.IDeviceListener
                public void onRGBD(boolean p0) {
                    String str;
                    str = MirSDKActivity.this.TAG;
                    Pdlog.m3275i(str, "onRGBD : " + p0);
                }

                @Override // com.pudutech.mirsdk.aidl.IDeviceListener
                public void onMarkerCamera(boolean p0) {
                    String str;
                    str = MirSDKActivity.this.TAG;
                    Pdlog.m3275i(str, "onMarkerCamera: " + p0);
                }

                @Override // com.pudutech.mirsdk.aidl.IDeviceListener
                public void onCollision(boolean p0) {
                    String str;
                    str = MirSDKActivity.this.TAG;
                    Pdlog.m3275i(str, "onCollision : " + p0);
                }

                @Override // com.pudutech.mirsdk.aidl.IDeviceListener
                public void onDisinfectionPower(boolean p0) {
                    String str;
                    str = MirSDKActivity.this.TAG;
                    Pdlog.m3275i(str, "onDisinfectionPower " + p0);
                }

                @Override // com.pudutech.mirsdk.aidl.IDeviceListener
                public void onEmergencyKeyPressed(boolean p0) {
                    String str;
                    str = MirSDKActivity.this.TAG;
                    Pdlog.m3275i(str, "onEmergencyKeyPressed " + p0);
                }

                @Override // com.pudutech.mirsdk.aidl.IDeviceListener
                public void onLockMotor(boolean p0) {
                    String str;
                    str = MirSDKActivity.this.TAG;
                    Pdlog.m3275i(str, "onLockMotor " + p0);
                    Switch lockmotorSwitch = (Switch) MirSDKActivity.this._$_findCachedViewById(C4946R.id.lockmotorSwitch);
                    Intrinsics.checkExpressionValueIsNotNull(lockmotorSwitch, "lockmotorSwitch");
                    lockmotorSwitch.setChecked(p0);
                }
            });
        }
        SDKServiceConnection.INSTANCE.setOnSwitchMapResult(new MirSDKActivity$connectSDK$4(this));
        SDKServiceConnection.INSTANCE.setOnAddRelocatePointResult(new Function2<Boolean, String, Unit>() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                invoke(bool.booleanValue(), str);
                return Unit.INSTANCE;
            }

            /* compiled from: MirSDKActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$5$2", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$5$2 */
            /* loaded from: classes4.dex */
            static final class C48312 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5630p$;

                C48312(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C48312 c48312 = new C48312(completion);
                    c48312.f5630p$ = (CoroutineScope) obj;
                    return c48312;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C48312) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f5630p$;
                    TextView tx_state = (TextView) MirSDKActivity.this._$_findCachedViewById(C4946R.id.tx_state);
                    Intrinsics.checkExpressionValueIsNotNull(tx_state, "tx_state");
                    tx_state.setText("Finish");
                    TextView tx_desc = (TextView) MirSDKActivity.this._$_findCachedViewById(C4946R.id.tx_desc);
                    Intrinsics.checkExpressionValueIsNotNull(tx_desc, "tx_desc");
                    tx_desc.setText("Locate Finish Init");
                    return Unit.INSTANCE;
                }
            }

            public final void invoke(final boolean z, final String str) {
                String str2;
                str2 = MirSDKActivity.this.TAG;
                Pdlog.m3275i(str2, "showing add relocate point result " + z + ' ' + str);
                MirSDKActivity.this.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$5.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Button button_set_boot_pose = (Button) MirSDKActivity.this._$_findCachedViewById(C4946R.id.button_set_boot_pose);
                        Intrinsics.checkExpressionValueIsNotNull(button_set_boot_pose, "button_set_boot_pose");
                        button_set_boot_pose.setEnabled(true);
                        if (z) {
                            Toast.makeText(MirSDKActivity.this, "add boot pose success", 0).show();
                            return;
                        }
                        Toast.makeText(MirSDKActivity.this, "add boot pose fail," + str, 0).show();
                    }
                });
            }
        });
    }

    private final void connectHardware() {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$connectHardware$1(this, null), 2, null);
        ((Button) _$_findCachedViewById(C4946R.id.start_calculate_geometic)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$connectHardware$2

            /* compiled from: MirSDKActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$connectHardware$2$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$connectHardware$2$1 */
            /* loaded from: classes5.dex */
            static final class C48191 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5616p$;

                C48191(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C48191 c48191 = new C48191(completion);
                    c48191.f5616p$ = (CoroutineScope) obj;
                    return c48191;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C48191) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f5616p$;
                    Toast.makeText(MirSDKActivity.this, "磁条传感器异常，不支持设置", 0).show();
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean z;
                z = MirSDKActivity.this.supportMagneticFunction;
                if (!z) {
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new C48191(null), 2, null);
                    return;
                }
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new C48202(null), 2, null);
                MirSDKActivity.this.leftMageticMax = 0;
                MirSDKActivity.this.rightMageticMax = 0;
                HardwareInterface hardwareInterface = HardWareServiceConnection.INSTANCE.getInterface();
                if (hardwareInterface != null) {
                    hardwareInterface.geomagneticCalibration(0, 0, true);
                }
            }

            /* compiled from: MirSDKActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$connectHardware$2$2", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$connectHardware$2$2 */
            /* loaded from: classes5.dex */
            static final class C48202 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5617p$;

                C48202(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C48202 c48202 = new C48202(completion);
                    c48202.f5617p$ = (CoroutineScope) obj;
                    return c48202;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C48202) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f5617p$;
                    TextView geomagnetic_left = (TextView) MirSDKActivity.this._$_findCachedViewById(C4946R.id.geomagnetic_left);
                    Intrinsics.checkExpressionValueIsNotNull(geomagnetic_left, "geomagnetic_left");
                    geomagnetic_left.setText("left:x=0 y=0 z=0");
                    TextView geomagnetic_right = (TextView) MirSDKActivity.this._$_findCachedViewById(C4946R.id.geomagnetic_right);
                    Intrinsics.checkExpressionValueIsNotNull(geomagnetic_right, "geomagnetic_right");
                    geomagnetic_right.setText("right:x=0 y=0 z=0");
                    return Unit.INSTANCE;
                }
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.finish_geometic_calib)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$connectHardware$3

            /* compiled from: MirSDKActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$connectHardware$3$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$connectHardware$3$1 */
            /* loaded from: classes5.dex */
            static final class C48211 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5618p$;

                C48211(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C48211 c48211 = new C48211(completion);
                    c48211.f5618p$ = (CoroutineScope) obj;
                    return c48211;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C48211) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f5618p$;
                    Toast.makeText(MirSDKActivity.this, "磁条传感器异常，不支持设置", 0).show();
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean z;
                String str;
                int i;
                int i2;
                int i3;
                int i4;
                String str2;
                int i5;
                int i6;
                int i7;
                int i8;
                int i9;
                int i10;
                int i11;
                int i12;
                MapPackageConfig pdmapNameList;
                z = MirSDKActivity.this.supportMagneticFunction;
                String str3 = null;
                if (!z) {
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new C48211(null), 2, null);
                    return;
                }
                str = MirSDKActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("finish_geometic_calib : leftMageticMax = ");
                i = MirSDKActivity.this.leftMageticMax;
                sb.append(i);
                sb.append(", ");
                sb.append("rightMageticMax = ");
                i2 = MirSDKActivity.this.rightMageticMax;
                sb.append(i2);
                sb.append(", ");
                sb.append("CamerConfigHelper.magic_sensor_left = ");
                sb.append(CamerConfigHelper.INSTANCE.getMagic_sensor_left());
                sb.append(", ");
                sb.append("CamerConfigHelper.magic_sensor_right = ");
                sb.append(CamerConfigHelper.INSTANCE.getMagic_sensor_right());
                Pdlog.m3273d(str, sb.toString());
                i3 = MirSDKActivity.this.leftMageticMax;
                if (i3 != 0) {
                    i4 = MirSDKActivity.this.rightMageticMax;
                    if (i4 != 0) {
                        str2 = MirSDKActivity.this.TAG;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("leftMageticMax is ");
                        i5 = MirSDKActivity.this.leftMageticMax;
                        sb2.append(i5);
                        sb2.append(" rightMageticMax is ");
                        i6 = MirSDKActivity.this.rightMageticMax;
                        sb2.append(i6);
                        Pdlog.m3273d(str2, sb2.toString());
                        MirSDKActivity mirSDKActivity = MirSDKActivity.this;
                        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                        if (sDKInterface != null && (pdmapNameList = sDKInterface.getPdmapNameList()) != null) {
                            str3 = pdmapNameList.getDef_map();
                        }
                        if (str3 == null) {
                            Intrinsics.throwNpe();
                        }
                        i7 = MirSDKActivity.this.leftMageticMax;
                        i8 = MirSDKActivity.this.rightMageticMax;
                        mirSDKActivity.updateConfigJson(str3, i7 + 500, i8 + 500);
                        CommandUtils.INSTANCE.executeCommand(InvokeServiceData.CALL_TYPE_SYNC);
                        HardwareInterface hardwareInterface = HardWareServiceConnection.INSTANCE.getInterface();
                        if (hardwareInterface != null) {
                            i11 = MirSDKActivity.this.leftMageticMax;
                            i12 = MirSDKActivity.this.rightMageticMax;
                            hardwareInterface.geomagneticCalibration(i11 + 500, i12 + 500, true);
                        }
                        CamerConfigHelper camerConfigHelper = CamerConfigHelper.INSTANCE;
                        i9 = MirSDKActivity.this.leftMageticMax;
                        camerConfigHelper.setMagic_sensor_left(i9 + 500);
                        CamerConfigHelper camerConfigHelper2 = CamerConfigHelper.INSTANCE;
                        i10 = MirSDKActivity.this.rightMageticMax;
                        camerConfigHelper2.setMagic_sensor_right(i10 + 500);
                        MirSDKActivity.this.initGeometicSwitch();
                        HardWareServiceConnection.INSTANCE.removeHardwareListener();
                        MirSDKActivity.this.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$connectHardware$3.2
                            @Override // java.lang.Runnable
                            public final void run() {
                                Toast.makeText(MirSDKActivity.this, "已完成标定", 0).show();
                            }
                        });
                    }
                }
            }
        });
        ((Switch) _$_findCachedViewById(C4946R.id.geomagneticSwitch)).setOnCheckedChangeListener(new MirSDKActivity$connectHardware$4(this));
    }

    public final void updateConfigJson(String mapName, int sensorleft, int sensorRight) {
        List<File> list;
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        File file = new File(MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH, StringBase64Kt.encodeMapName(mapName) + ".pdmap");
        ZipUtils.unzipFile(file, new File(file.getParent(), getFileNameNoExtension(file)));
        Pdlog.m3273d(this.TAG, "encode Dir name is " + mapName + "  ");
        String encodeMapName = StringBase64Kt.encodeMapName(mapName);
        File file2 = new File(MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH, encodeMapName);
        File file3 = new File((MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH + "/") + encodeMapName, "config.json");
        if (!file3.exists()) {
            Pdlog.m3273d(this.TAG, "init encode Dir " + file2.getAbsolutePath() + "  name is " + mapName);
            return;
        }
        CamerConfigHelper.INSTANCE.setConfigJsonSensorParams(file3.getAbsolutePath(), sensorleft, sensorRight);
        File[] listFiles = file2.listFiles();
        if (listFiles != null && (list = ArraysKt.toList(listFiles)) != null) {
            for (File it : list) {
                String str = this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("find file ");
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                sb.append(it.getName());
                Pdlog.m3275i(str, sb.toString());
                if (Intrinsics.areEqual(it.getName(), "scheduling_config.json")) {
                    Pdlog.m3275i(this.TAG, "read " + FilesKt.readText$default(it, null, 1, null));
                }
            }
        }
        File[] listFiles2 = file2.listFiles();
        ZipUtils.zipFiles(listFiles2 != null ? ArraysKt.toList(listFiles2) : null, new File(MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH, encodeMapName + ".pdmap"));
        CommandUtils.INSTANCE.executeCommand(InvokeServiceData.CALL_TYPE_SYNC);
        Pdlog.m3273d("enDir", "init enDir " + file3.getAbsolutePath() + " and file dir name is " + file2.getName());
    }

    public final String getFileNameNoExtension(File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        String obj = file.getName().subSequence(0, file.getName().length() - 6).toString();
        Pdlog.m3273d(this.TAG, "fileNameWithoutSubfix is " + obj);
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initSpeedLimit() {
        MoveActionInterface moveActionInterface;
        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
        if (sDKInterface == null || (moveActionInterface = sDKInterface.getMoveActionInterface()) == null) {
            return;
        }
        moveActionInterface.addMapAreaDetectionListener(this.TAG, new MapAreaDetectionListener.Stub() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$initSpeedLimit$1
            @Override // com.pudutech.mirsdk.aidl.MapAreaDetectionListener
            public void OnElevatorZoneListener(boolean p0) {
            }

            @Override // com.pudutech.mirsdk.aidl.MapAreaDetectionListener
            public void OnNoDetourListener(boolean p0, double p1) {
            }

            @Override // com.pudutech.mirsdk.aidl.MapAreaDetectionListener
            public void OnRGBDFunLimitListener(boolean p0, int p1) {
            }

            @Override // com.pudutech.mirsdk.aidl.MapAreaDetectionListener
            public void OnSpeedLimitListener(boolean p0, double p1) {
                String str;
                str = MirSDKActivity.this.TAG;
                Pdlog.m3273d(str, "OnSpeedLimitListener p0 = " + p0 + " p1 = " + p1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void initDetDropSwitch() {
        MoveActionInterface moveActionInterface;
        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
        if (sDKInterface != null && (moveActionInterface = sDKInterface.getMoveActionInterface()) != null) {
            moveActionInterface.addCliffDistanceStateListener(this.TAG, new MirSDKActivity$initDetDropSwitch$1(this));
        }
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        SDKInterface sDKInterface2 = SDKServiceConnection.INSTANCE.getInterface();
        objectRef.element = sDKInterface2 != null ? Boolean.valueOf(sDKInterface2.getDropDetStatus()) : 0;
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$initDetDropSwitch$2(this, objectRef, null), 2, null);
        initGeometicSwitch();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initGeometicSwitch() {
        HardwareInterface hardwareInterface = HardWareServiceConnection.INSTANCE.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.getMagneticSensor();
        }
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$initGeometicSwitch$1(this, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void initSlipControlSwitch() {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
        objectRef.element = sDKInterface != null ? Boolean.valueOf(sDKInterface.getSlipControlStatus()) : 0;
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$initSlipControlSwitch$1(this, objectRef, null), 2, null);
    }

    private final void installerComunicationTest() {
        Button testComm = (Button) _$_findCachedViewById(C4946R.id.testComm);
        Intrinsics.checkExpressionValueIsNotNull(testComm, "testComm");
        testComm.setVisibility(4);
        Button closeComm = (Button) _$_findCachedViewById(C4946R.id.closeComm);
        Intrinsics.checkExpressionValueIsNotNull(closeComm, "closeComm");
        closeComm.setVisibility(4);
        Button testShow = (Button) _$_findCachedViewById(C4946R.id.testShow);
        Intrinsics.checkExpressionValueIsNotNull(testShow, "testShow");
        testShow.setVisibility(4);
        Button noneShow = (Button) _$_findCachedViewById(C4946R.id.noneShow);
        Intrinsics.checkExpressionValueIsNotNull(noneShow, "noneShow");
        noneShow.setVisibility(4);
        TextView txComm = (TextView) _$_findCachedViewById(C4946R.id.txComm);
        Intrinsics.checkExpressionValueIsNotNull(txComm, "txComm");
        txComm.setVisibility(4);
        TextView txRecv = (TextView) _$_findCachedViewById(C4946R.id.txRecv);
        Intrinsics.checkExpressionValueIsNotNull(txRecv, "txRecv");
        txRecv.setVisibility(4);
    }

    private final void toolsWorkSpace() {
        ((Button) _$_findCachedViewById(C4946R.id.bt_update)).setOnClickListener(new MirSDKActivity$toolsWorkSpace$1(this));
        ((Button) _$_findCachedViewById(C4946R.id.bt_mapify)).setOnClickListener(new MirSDKActivity$toolsWorkSpace$2(this));
        ((Button) _$_findCachedViewById(C4946R.id.laser_marker_mapify)).setOnClickListener(new MirSDKActivity$toolsWorkSpace$3(this));
        ((Button) _$_findCachedViewById(C4946R.id.bt_factory_test)).setOnClickListener(new MirSDKActivity$toolsWorkSpace$4(this));
        ((Button) _$_findCachedViewById(C4946R.id.bt_system)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$toolsWorkSpace$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MirSDKActivity mirSDKActivity = MirSDKActivity.this;
                mirSDKActivity.startActivity(new Intent(mirSDKActivity, (Class<?>) DeviceInfoActivity.class));
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.bt_wifi)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$toolsWorkSpace$6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MirSDKActivity mirSDKActivity = MirSDKActivity.this;
                mirSDKActivity.startActivity(new Intent(mirSDKActivity, (Class<?>) WifiSetActivity.class));
            }
        });
    }

    private final void mapWorkSpace() {
        MapPackageConfig pdmapNameList;
        TextView current_map = (TextView) _$_findCachedViewById(C4946R.id.current_map);
        Intrinsics.checkExpressionValueIsNotNull(current_map, "current_map");
        StringBuilder sb = new StringBuilder();
        sb.append(getResources().getString(C4946R.string.current_map));
        sb.append((char) 65306);
        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
        sb.append((sDKInterface == null || (pdmapNameList = sDKInterface.getPdmapNameList()) == null) ? null : pdmapNameList.getDef_map());
        current_map.setText(sb.toString());
        ((Button) _$_findCachedViewById(C4946R.id.bt_randomSwitchMap)).setOnClickListener(new MirSDKActivity$mapWorkSpace$1(this));
        ((Button) _$_findCachedViewById(C4946R.id.bt_switchPointMap)).setOnClickListener(new MirSDKActivity$mapWorkSpace$2(this));
        ((Button) _$_findCachedViewById(C4946R.id.bt_switchFloor)).setOnClickListener(new MirSDKActivity$mapWorkSpace$3(this));
        ((Button) _$_findCachedViewById(C4946R.id.bt_switchFloorMap)).setOnClickListener(new MirSDKActivity$mapWorkSpace$4(this));
    }

    private final void coreWorkSpace() {
        ((Button) _$_findCachedViewById(C4946R.id.bt_mircore)).setOnClickListener(new MirSDKActivity$coreWorkSpace$1(this));
        ((Button) _$_findCachedViewById(C4946R.id.bt_relocation)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$coreWorkSpace$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MirSDKActivity.this.checkAuth("reloc", new Function0<Unit>() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$coreWorkSpace$2.1
                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                        if (sDKInterface != null) {
                            sDKInterface.reloadLocalization();
                        }
                    }
                });
            }
        });
    }

    /* JADX WARN: Type inference failed for: r1v13, types: [T, kotlinx.coroutines.Job] */
    private final void moveWorkSpace() {
        ((Button) _$_findCachedViewById(C4946R.id.button_goto)).setOnClickListener(new MirSDKActivity$moveWorkSpace$1(this));
        ((Button) _$_findCachedViewById(C4946R.id.button_gohome)).setOnClickListener(new MirSDKActivity$moveWorkSpace$2(this));
        ((Button) _$_findCachedViewById(C4946R.id.button_pause)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MoveActionInterface moveActionInterface;
                SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                if (sDKInterface == null || (moveActionInterface = sDKInterface.getMoveActionInterface()) == null) {
                    return;
                }
                moveActionInterface.pause();
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.button_resume)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MoveActionInterface moveActionInterface;
                SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                if (sDKInterface == null || (moveActionInterface = sDKInterface.getMoveActionInterface()) == null) {
                    return;
                }
                moveActionInterface.resume();
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.button_cruise)).setOnClickListener(new MirSDKActivity$moveWorkSpace$5(this));
        ((Button) _$_findCachedViewById(C4946R.id.button_suspend_charge)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$6

            /* JADX WARN: Classes with same name are omitted:
              classes4.dex
             */
            /* compiled from: MirSDKActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$6$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$6$1 */
            /* loaded from: classes5.dex */
            static final class C48481 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5662p$;

                C48481(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C48481 c48481 = new C48481(completion);
                    c48481.f5662p$ = (CoroutineScope) obj;
                    return c48481;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C48481) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f5662p$;
                    SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                    if (sDKInterface != null) {
                        sDKInterface.suspendCharingUsingPile();
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new C48481(null), 3, null);
            }
        });
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (Job) 0;
        ((Button) _$_findCachedViewById(C4946R.id.button_auto_test_charge)).setOnClickListener(new MirSDKActivity$moveWorkSpace$7(this, objectRef, new Function1<String, Object>() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$autoTestChargeTask$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MirSDKActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$autoTestChargeTask$1$1", m3970f = "MirSDKActivity.kt", m3971i = {0, 1}, m3972l = {1578, 1585}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
            /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$autoTestChargeTask$1$1 */
            /* loaded from: classes5.dex */
            public static final class C48521 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5667p$;

                C48521(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C48521 c48521 = new C48521(completion);
                    c48521.f5667p$ = (CoroutineScope) obj;
                    return c48521;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C48521) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:19:0x0089  */
                /* JADX WARN: Type inference failed for: r0v2, types: [T, kotlinx.coroutines.Job] */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final Object invokeSuspend(Object obj) {
                    CoroutineScope coroutineScope;
                    MoveActionInterface moveActionInterface;
                    Job job;
                    MoveActionInterface moveActionInterface2;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        coroutineScope = this.f5667p$;
                        int i2 = MirSDKActivity.WhenMappings.$EnumSwitchMapping$1[SDKServiceConnection.INSTANCE.getChargeStateStore().ordinal()];
                        if (i2 == 1 || i2 == 2) {
                            SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                            if (sDKInterface != null) {
                                sDKInterface.suspendCharingUsingPile();
                            }
                            this.L$0 = coroutineScope;
                            this.label = 1;
                            if (DelayKt.delay(2000L, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            SDKInterface sDKInterface2 = SDKServiceConnection.INSTANCE.getInterface();
                            if (sDKInterface2 != null && (moveActionInterface = sDKInterface2.getMoveActionInterface()) != null) {
                                moveActionInterface.pause();
                            }
                            job = (Job) Ref.ObjectRef.this.element;
                            if (job != null) {
                                this.L$0 = coroutineScope;
                                this.label = 2;
                                if (JobKt.cancelAndJoin(job, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                            Ref.ObjectRef.this.element = (Job) 0;
                            return Unit.INSTANCE;
                        }
                    } else {
                        if (i != 1) {
                            if (i == 2) {
                                ResultKt.throwOnFailure(obj);
                                Ref.ObjectRef.this.element = (Job) 0;
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        coroutineScope = (CoroutineScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                    }
                    SDKInterface sDKInterface3 = SDKServiceConnection.INSTANCE.getInterface();
                    if (sDKInterface3 != null && (moveActionInterface2 = sDKInterface3.getMoveActionInterface()) != null) {
                        moveActionInterface2.pause();
                    }
                    job = (Job) Ref.ObjectRef.this.element;
                    if (job != null) {
                    }
                    Ref.ObjectRef.this.element = (Job) 0;
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: Type inference failed for: r10v1, types: [T, kotlinx.coroutines.Job] */
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(String pile) {
                ?? launch$default;
                Job launch$default2;
                Intrinsics.checkParameterIsNotNull(pile, "pile");
                Job job = (Job) Ref.ObjectRef.this.element;
                if (job != null && job.isActive()) {
                    launch$default2 = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new C48521(null), 3, null);
                    return launch$default2;
                }
                Ref.ObjectRef objectRef2 = Ref.ObjectRef.this;
                launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new C48532(pile, null), 3, null);
                objectRef2.element = launch$default;
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MirSDKActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$autoTestChargeTask$1$2", m3970f = "MirSDKActivity.kt", m3971i = {0, 1}, m3972l = {1595, 1605}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
            /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$autoTestChargeTask$1$2 */
            /* loaded from: classes5.dex */
            public static final class C48532 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $pile;
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5668p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C48532(String str, Continuation continuation) {
                    super(2, continuation);
                    this.$pile = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C48532 c48532 = new C48532(this.$pile, completion);
                    c48532.f5668p$ = (CoroutineScope) obj;
                    return c48532;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C48532) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:26:0x007e  */
                /* JADX WARN: Removed duplicated region for block: B:29:0x008d A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:30:0x008e  */
                /* JADX WARN: Removed duplicated region for block: B:31:0x0092  */
                /* JADX WARN: Removed duplicated region for block: B:9:0x0036  */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0071 -> B:15:0x004e). Please report as a decompilation issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x008e -> B:7:0x0030). Please report as a decompilation issue!!! */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final Object invokeSuspend(Object obj) {
                    CoroutineScope coroutineScope;
                    C48532 c48532;
                    Object obj2;
                    SDKInterface sDKInterface;
                    MoveActionInterface moveActionInterface;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        coroutineScope = this.f5668p$;
                    } else if (i == 1) {
                        CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        Object obj3 = coroutine_suspended;
                        c48532 = this;
                        int i2 = MirSDKActivity.WhenMappings.$EnumSwitchMapping$2[SDKServiceConnection.INSTANCE.getChargeStateStore().ordinal()];
                        if (i2 != 1 || i2 == 2) {
                            sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                            if (sDKInterface != null) {
                                sDKInterface.suspendCharingUsingPile();
                            }
                            c48532.L$0 = coroutineScope2;
                            c48532.label = 2;
                            if (DelayKt.delay(20000L, c48532) != obj3) {
                                return obj3;
                            }
                            CoroutineScope coroutineScope3 = coroutineScope2;
                            obj2 = obj3;
                            coroutineScope = coroutineScope3;
                            if (CoroutineScopeKt.isActive(coroutineScope)) {
                                SDKInterface sDKInterface2 = SDKServiceConnection.INSTANCE.getInterface();
                                if (sDKInterface2 != null && (moveActionInterface = sDKInterface2.getMoveActionInterface()) != null) {
                                    moveActionInterface.goChargingPile(c48532.$pile);
                                }
                                Object obj4 = obj2;
                                coroutineScope2 = coroutineScope;
                                obj3 = obj4;
                            } else {
                                return Unit.INSTANCE;
                            }
                        }
                        if (CoroutineScopeKt.isActive(coroutineScope2)) {
                            c48532.L$0 = coroutineScope2;
                            c48532.label = 1;
                            if (DelayKt.delay(5000L, c48532) == obj3) {
                                return obj3;
                            }
                            int i22 = MirSDKActivity.WhenMappings.$EnumSwitchMapping$2[SDKServiceConnection.INSTANCE.getChargeStateStore().ordinal()];
                            if (i22 != 1) {
                            }
                            sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                            if (sDKInterface != null) {
                            }
                        }
                        c48532.L$0 = coroutineScope2;
                        c48532.label = 2;
                        if (DelayKt.delay(20000L, c48532) != obj3) {
                        }
                    } else {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        coroutineScope = coroutineScope4;
                    }
                    obj2 = coroutine_suspended;
                    c48532 = this;
                    if (CoroutineScopeKt.isActive(coroutineScope)) {
                    }
                }
            }
        }));
        ((Button) _$_findCachedViewById(C4946R.id.button_autocharge)).setOnClickListener(new MirSDKActivity$moveWorkSpace$8(this));
        ((Button) _$_findCachedViewById(C4946R.id.button_fillin)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MoveActionInterface moveActionInterface;
                SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                if (sDKInterface == null || (moveActionInterface = sDKInterface.getMoveActionInterface()) == null) {
                    return;
                }
                TextView tx_fillin = (TextView) MirSDKActivity.this._$_findCachedViewById(C4946R.id.tx_fillin);
                Intrinsics.checkExpressionValueIsNotNull(tx_fillin, "tx_fillin");
                moveActionInterface.goTo(tx_fillin.getText().toString(), MoveTaskMode.Normal);
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.button_quitfillin)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MoveActionInterface moveActionInterface;
                SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                if (sDKInterface == null || (moveActionInterface = sDKInterface.getMoveActionInterface()) == null) {
                    return;
                }
                moveActionInterface.quitFillIn();
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.button_rotate)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                double nextDouble = Random.INSTANCE.nextDouble(-180.0d, 180.0d);
                TextView tx_angular = (TextView) MirSDKActivity.this._$_findCachedViewById(C4946R.id.tx_angular);
                Intrinsics.checkExpressionValueIsNotNull(tx_angular, "tx_angular");
                tx_angular.setText(((com.pudutech.base.CommonKt.format((3.141592653589793d * nextDouble) / 180.0d, 2).toString() + " rad, ") + com.pudutech.base.CommonKt.format(nextDouble, 2)) + " deg");
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new C48471(nextDouble, null), 3, null);
            }

            /* JADX WARN: Classes with same name are omitted:
              classes4.dex
             */
            /* compiled from: MirSDKActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$11$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$11$1 */
            /* loaded from: classes5.dex */
            static final class C48471 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ double $rotate_angular;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5661p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C48471(double d, Continuation continuation) {
                    super(2, continuation);
                    this.$rotate_angular = d;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C48471 c48471 = new C48471(this.$rotate_angular, completion);
                    c48471.f5661p$ = (CoroutineScope) obj;
                    return c48471;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C48471) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    MoveActionInterface moveActionInterface;
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f5661p$;
                    SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                    if (sDKInterface != null && (moveActionInterface = sDKInterface.getMoveActionInterface()) != null) {
                        moveActionInterface.rotate(this.$rotate_angular);
                    }
                    return Unit.INSTANCE;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startConnection() {
        if (this.socket == null) {
            this.socket = new Socket(getIpMacAddress(this), 16888);
            Socket socket = this.socket;
            this.inputStream = socket != null ? socket.getInputStream() : null;
            Socket socket2 = this.socket;
            this.outputStream = socket2 != null ? socket2.getOutputStream() : null;
        }
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$startConnection$1(this, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void disableConnection() {
        Socket socket = this.socket;
        if (socket != null && socket != null && !socket.isClosed()) {
            this.inputStream = (InputStream) null;
            this.outputStream = (OutputStream) null;
            Socket socket2 = this.socket;
            if (socket2 != null) {
                socket2.close();
            }
            this.socket = (Socket) null;
            this.show = false;
        }
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$disableConnection$1(this, null), 2, null);
    }

    private final int byteArray2Int(byte[] bytes) {
        int length = bytes.length;
        int i = 0;
        int i2 = length;
        while (i2 > 0) {
            int i3 = bytes[i2 - 1] & 255;
            for (int i4 = length - i2; i4 > 0; i4--) {
                i3 <<= 8;
            }
            i2--;
            i |= i3;
        }
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x006a, code lost:
    
        if ((r0.length() == 0) != false) goto L18;
     */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void onResume() {
        AccessControlServer accessControlServer;
        ElevatorConnectionType elevatorConnectionType;
        String str;
        LocateCase locateCase;
        BluetoothChargeInterface bluetoothChargeInterface;
        MoveActionInterface moveActionInterface;
        MoveActionInterface moveActionInterface2;
        MoveActionInterface moveActionInterface3;
        MoveActionInterface moveActionInterface4;
        MapPackageConfig pdmapNameList;
        Pdlog.m3275i(this.TAG, "MirSDKActivity.onResume");
        super.onResume();
        if (SDKServiceConnection.INSTANCE.getInterface() != null) {
            Switch switchInstallMode = (Switch) _$_findCachedViewById(C4946R.id.switchInstallMode);
            Intrinsics.checkExpressionValueIsNotNull(switchInstallMode, "switchInstallMode");
            SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
            if (sDKInterface == null) {
                Intrinsics.throwNpe();
            }
            switchInstallMode.setChecked(sDKInterface.getInstallMode());
            initDetDropSwitch();
            initReflectorSwitch();
            SDKInterface sDKInterface2 = SDKServiceConnection.INSTANCE.getInterface();
            String hardwareVersion = sDKInterface2 != null ? sDKInterface2.getHardwareVersion() : null;
            TextView textViewHardwareVer = (TextView) _$_findCachedViewById(C4946R.id.textViewHardwareVer);
            Intrinsics.checkExpressionValueIsNotNull(textViewHardwareVer, "textViewHardwareVer");
            if (hardwareVersion != null) {
                str = hardwareVersion;
            }
            textViewHardwareVer.setText(str);
            TextView tx_locate_case = (TextView) _$_findCachedViewById(C4946R.id.tx_locate_case);
            Intrinsics.checkExpressionValueIsNotNull(tx_locate_case, "tx_locate_case");
            SDKInterface sDKInterface3 = SDKServiceConnection.INSTANCE.getInterface();
            if (sDKInterface3 == null || (locateCase = sDKInterface3.getLocateCase()) == null) {
                locateCase = LocateCase.Marker;
            }
            tx_locate_case.setText(locateCase.name());
            TextView current_map = (TextView) _$_findCachedViewById(C4946R.id.current_map);
            Intrinsics.checkExpressionValueIsNotNull(current_map, "current_map");
            StringBuilder sb = new StringBuilder();
            sb.append(getResources().getString(C4946R.string.current_map));
            sb.append((char) 65306);
            SDKInterface sDKInterface4 = SDKServiceConnection.INSTANCE.getInterface();
            sb.append((sDKInterface4 == null || (pdmapNameList = sDKInterface4.getPdmapNameList()) == null) ? null : pdmapNameList.getDef_map());
            current_map.setText(sb.toString());
            SDKInterface sDKInterface5 = SDKServiceConnection.INSTANCE.getInterface();
            this.destinations = (sDKInterface5 == null || (moveActionInterface4 = sDKInterface5.getMoveActionInterface()) == null) ? null : moveActionInterface4.getDestinations();
            this.tableNameArray.clear();
            this.tableArray.clear();
            this.starterGroupArray.clear();
            List<Destination> list = this.destinations;
            if (list != null) {
                if (list == null) {
                    Intrinsics.throwNpe();
                }
                for (Destination destination : list) {
                    if (Intrinsics.areEqual(destination.getMode(), "dining_outlet") || Intrinsics.areEqual(destination.getMode(), Constants.POINT_TYPE_DOOR)) {
                        if (!this.starterGroupArray.contains(destination.getGroup())) {
                            this.starterGroupArray.add(destination.getGroup());
                        }
                    } else {
                        this.tableNameArray.add(destination.getName());
                        this.tableArray.add("floor " + destination.getFloor() + ' ' + destination.getName() + '(' + destination.getGroup() + ")[" + destination.getMode() + ']');
                    }
                    Pdlog.m3275i(this.TAG, "find table " + destination.getName());
                }
                TextView textViewMapData = (TextView) _$_findCachedViewById(C4946R.id.textViewMapData);
                Intrinsics.checkExpressionValueIsNotNull(textViewMapData, "textViewMapData");
                StringBuilder sb2 = new StringBuilder();
                sb2.append("destination size:");
                List<Destination> list2 = this.destinations;
                if (list2 == null) {
                    Intrinsics.throwNpe();
                }
                sb2.append(list2.size());
                textViewMapData.setText(sb2.toString());
            }
            SDKInterface sDKInterface6 = SDKServiceConnection.INSTANCE.getInterface();
            if (sDKInterface6 != null && (moveActionInterface3 = sDKInterface6.getMoveActionInterface()) != null) {
                moveActionInterface3.addFillInStateListener(this.TAG, new MirSDKActivity$onResume$1(this));
            }
            SDKInterface sDKInterface7 = SDKServiceConnection.INSTANCE.getInterface();
            if (sDKInterface7 != null && (moveActionInterface2 = sDKInterface7.getMoveActionInterface()) != null) {
                moveActionInterface2.addElevetorRequestListener(this.TAG, new MirSDKActivity$onResume$2(this));
            }
            SDKInterface sDKInterface8 = SDKServiceConnection.INSTANCE.getInterface();
            if (sDKInterface8 != null && (moveActionInterface = sDKInterface8.getMoveActionInterface()) != null) {
                moveActionInterface.addAccessDoorStateListener(this.TAG, new MirSDKActivity$onResume$3(this));
            }
            SDKInterface sDKInterface9 = SDKServiceConnection.INSTANCE.getInterface();
            if (sDKInterface9 != null && (bluetoothChargeInterface = sDKInterface9.getBluetoothChargeInterface()) != null) {
                bluetoothChargeInterface.addBluetoothChargeUpdateListener(this.TAG, new MirSDKActivity$onResume$4(this));
            }
        }
        SDKInterface sDKInterface10 = SDKServiceConnection.INSTANCE.getInterface();
        if (sDKInterface10 == null || (accessControlServer = sDKInterface10.getAccessControlServer()) == null) {
            accessControlServer = AccessControlServer.XinYiLian;
        }
        ArrayAdapter<AccessControlServer> arrayAdapter = this.accessServerAdapter;
        if (arrayAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("accessServerAdapter");
        }
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$onResume$5(this, arrayAdapter.getPosition(accessControlServer), null), 2, null);
        SDKInterface sDKInterface11 = SDKServiceConnection.INSTANCE.getInterface();
        if (sDKInterface11 == null || (elevatorConnectionType = sDKInterface11.getElevatorConnection()) == null) {
            elevatorConnectionType = ElevatorConnectionType.Lora;
        }
        ArrayAdapter<ElevatorConnectionType> arrayAdapter2 = this.elvConnectionTypeAdapter;
        if (arrayAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("elvConnectionTypeAdapter");
        }
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$onResume$6(this, arrayAdapter2.getPosition(elevatorConnectionType), null), 2, null);
        HardwareInterface hardwareInterface = HardWareServiceConnection.INSTANCE.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.getMagneticSensor();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initReflectorSwitch() {
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = CamerConfigHelper.INSTANCE.getReflectorSwitch();
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$initReflectorSwitch$1(this, intRef, null), 2, null);
        if (new File(MapFilePathConfig.REFLECTOR_PARAM_PATH).exists()) {
            try {
                File file = new File(MapFilePathConfig.REFLECTOR_PARAM_PATH);
                FileReader fileReader = new FileReader(file);
                char[] cArr = new char[(int) file.length()];
                fileReader.read(cArr);
                String str = new String(cArr);
                boolean z = false;
                int length = str.length() - 1;
                int i = 0;
                while (i <= length) {
                    boolean z2 = str.charAt(!z ? i : length) <= ' ';
                    if (z) {
                        if (!z2) {
                            break;
                        } else {
                            length--;
                        }
                    } else if (z2) {
                        i++;
                    } else {
                        z = true;
                    }
                }
                JSONObject jSONObject = new JSONObject(str.subSequence(i, length + 1).toString());
                if (jSONObject.has("in_brake_dist")) {
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$initReflectorSwitch$3(this, jSONObject, null), 2, null);
                }
                if (jSONObject.has("follow_line_dist")) {
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$initReflectorSwitch$4(this, jSONObject, null), 2, null);
                }
                if (jSONObject.has("slow_down_dist")) {
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$initReflectorSwitch$5(this, jSONObject, null), 2, null);
                }
                if (jSONObject.has("out_brake_dist")) {
                    Pdlog.m3273d(this.TAG, "set out_brake_dist on text");
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$initReflectorSwitch$6(this, jSONObject, null), 2, null);
                }
                fileReader.close();
            } catch (Exception unused) {
                Pdlog.m3273d(this.TAG, "exception: reflector.cfg error");
            }
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        BluetoothChargeInterface bluetoothChargeInterface;
        MoveActionInterface moveActionInterface;
        MoveActionInterface moveActionInterface2;
        DeviceInterface deviceInterface;
        super.onDestroy();
        Function2<? super Boolean, ? super String, Unit> function2 = (Function2) null;
        SDKServiceConnection.INSTANCE.setOnAddRelocatePointResult(function2);
        SDKServiceConnection.INSTANCE.setOnStateChange(function2);
        SDKServiceConnection.INSTANCE.setOnSwitchMapResult(function2);
        SDKServiceConnection.INSTANCE.setOnAddChargePointResult(function2);
        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
        if (sDKInterface != null && (deviceInterface = sDKInterface.getDeviceInterface()) != null) {
            deviceInterface.removeListener("business_mock");
        }
        SDKInterface sDKInterface2 = SDKServiceConnection.INSTANCE.getInterface();
        if (sDKInterface2 != null && (moveActionInterface2 = sDKInterface2.getMoveActionInterface()) != null) {
            moveActionInterface2.removeFillInStateListener(this.TAG);
        }
        SDKInterface sDKInterface3 = SDKServiceConnection.INSTANCE.getInterface();
        if (sDKInterface3 != null && (moveActionInterface = sDKInterface3.getMoveActionInterface()) != null) {
            moveActionInterface.removeElevatorRequestListener(this.TAG);
        }
        SDKServiceConnection sDKServiceConnection = SDKServiceConnection.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
        sDKServiceConnection.disconnectConnection(applicationContext);
        SDKInterface sDKInterface4 = SDKServiceConnection.INSTANCE.getInterface();
        if (sDKInterface4 == null || (bluetoothChargeInterface = sDKInterface4.getBluetoothChargeInterface()) == null) {
            return;
        }
        bluetoothChargeInterface.removeBluetoothChargeUpdateListener(this.TAG);
    }

    private final String getIpMacAddress(Context context) {
        String str;
        String inetAddress;
        try {
            Object systemService = context.getApplicationContext().getSystemService("wifi");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.net.wifi.WifiManager");
            }
            try {
                int i = ((WifiManager) systemService).getDhcpInfo().ipAddress;
                inetAddress = InetAddress.getByAddress(new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)}).toString();
                Intrinsics.checkExpressionValueIsNotNull(inetAddress, "InetAddress.getByAddress(addressBytes).toString()");
            } catch (Exception unused) {
                str = "invalid";
            }
            try {
                if (inetAddress == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String substring = inetAddress.substring(1);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                return substring;
            } catch (Exception unused2) {
                str = inetAddress;
                Pdlog.m3277w(this.TAG, "get ip fail");
                return str;
            }
        } catch (Exception e) {
            Pdlog.m3277w(this.TAG, "get ip and mac error:" + Log.getStackTraceString(e));
            return "invalid";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String findFloorId(String name) {
        String str = name;
        if (!StringsKt.contains$default((CharSequence) str, (CharSequence) MqttTopic.MULTI_LEVEL_WILDCARD, false, 2, (Object) null)) {
            return "0";
        }
        int indexOf$default = StringsKt.indexOf$default((CharSequence) str, '#', 0, false, 6, (Object) null);
        int i = indexOf$default + 1;
        int indexOf$default2 = StringsKt.indexOf$default((CharSequence) str, '#', i, false, 4, (Object) null);
        if (indexOf$default2 == -1) {
            return "0";
        }
        if (name != null) {
            String substring = name.substring(0, indexOf$default);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            try {
                if (name == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String substring2 = name.substring(i, indexOf$default2);
                Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                Integer.parseInt(substring2);
                return substring;
            } catch (Exception e) {
                Pdlog.m3277w(this.TAG, "get floor index from map name " + name + " exception: " + e.getLocalizedMessage());
                return "0";
            }
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
}
