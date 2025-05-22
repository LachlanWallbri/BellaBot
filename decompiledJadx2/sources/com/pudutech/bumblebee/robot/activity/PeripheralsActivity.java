package com.pudutech.bumblebee.robot.activity;

import android.content.ComponentName;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.common.HybridBinarizer;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.bumblebee.robot.C4144R;
import com.pudutech.bumblebee.robot.activity.PeripheralsActivity;
import com.pudutech.bumblebee.robot.aidl.IDeliveryRobotListener;
import com.pudutech.bumblebee.robot.aidl.IRobotListener;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
import com.pudutech.bumblebee.robot.aidl.serialize.LEDScreenMode;
import com.pudutech.bumblebee.robot.aidl.serialize.Pallet;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDevice;
import com.pudutech.bumblebee.robot.aidl.serialize.SurfaceLED;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchPlace;
import com.pudutech.bumblebee.robot.aidl.serialize.UpdateEvent;
import com.pudutech.bumblebee.robot.aidl.serialize.UpdateObject;
import com.pudutech.bumblebee.robot.pallet.PalletProtocol;
import com.pudutech.bumblebee.robot.surface_led.LEDHelper;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.IMarkerCameraState;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: PeripheralsActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0002/2\b\u0007\u0018\u00002\u00020\u0001:\u0001\\B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010B\u001a\u00020CJ\u0006\u0010D\u001a\u00020CJ\u0006\u0010E\u001a\u00020CJ\u0006\u0010F\u001a\u00020CJ\u0012\u0010G\u001a\u00020C2\b\u0010H\u001a\u0004\u0018\u00010IH\u0014J\b\u0010J\u001a\u00020CH\u0014J.\u0010K\u001a\u00020C2\b\b\u0002\u0010L\u001a\u00020M2\b\b\u0002\u0010N\u001a\u00020M2\b\b\u0002\u0010O\u001a\u00020Mø\u0001\u0000¢\u0006\u0004\bP\u0010QJ.\u0010R\u001a\u00020C2\b\b\u0002\u0010L\u001a\u00020M2\b\b\u0002\u0010N\u001a\u00020M2\b\b\u0002\u0010O\u001a\u00020Mø\u0001\u0000¢\u0006\u0004\bS\u0010QJ\u0010\u0010T\u001a\u0004\u0018\u00010\n2\u0006\u0010U\u001a\u00020VJ.\u0010W\u001a\u00020)2\b\b\u0002\u0010L\u001a\u00020M2\b\b\u0002\u0010N\u001a\u00020M2\b\b\u0002\u0010O\u001a\u00020Mø\u0001\u0000¢\u0006\u0004\bX\u0010YJ\n\u0010Z\u001a\u000206*\u00020[R!\u0010\u0003\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\"\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\"\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0013\"\u0004\b\u001d\u0010\u0015R\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001a\u0010(\u001a\u00020)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u0010\u0010.\u001a\u00020/X\u0082\u0004¢\u0006\u0004\n\u0002\u00100R\u0010\u00101\u001a\u000202X\u0082\u0004¢\u0006\u0004\n\u0002\u00103R-\u00104\u001a\u001e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020605j\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u000206`7¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u001c\u0010:\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u001a\u0010?\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010%\"\u0004\bA\u0010'\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006]"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/activity/PeripheralsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "HINTS", "Ljava/util/EnumMap;", "Lcom/google/zxing/DecodeHintType;", "", "getHINTS", "()Ljava/util/EnumMap;", "TAG", "", "commonListener", "Lcom/pudutech/bumblebee/robot/aidl/IRobotListener$Stub;", "getCommonListener", "()Lcom/pudutech/bumblebee/robot/aidl/IRobotListener$Stub;", "connection", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/bumblebee/robot/aidl/RobotInterface;", "getConnection", "()Lcom/pudutech/base/architecture/AIDLConnection;", "setConnection", "(Lcom/pudutech/base/architecture/AIDLConnection;)V", "deliveryListener", "Lcom/pudutech/bumblebee/robot/aidl/IDeliveryRobotListener$Stub;", "getDeliveryListener", "()Lcom/pudutech/bumblebee/robot/aidl/IDeliveryRobotListener$Stub;", "hardware", "Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "getHardware", "setHardware", "hardwareInterface", "getHardwareInterface", "()Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "setHardwareInterface", "(Lcom/pudutech/mirsdk/hardware/HardwareInterface;)V", "hardwareServicePath", "getHardwareServicePath", "()Ljava/lang/String;", "setHardwareServicePath", "(Ljava/lang/String;)V", "index", "", "getIndex", "()I", "setIndex", "(I)V", "onMonocularCameraListener", "com/pudutech/bumblebee/robot/activity/PeripheralsActivity$onMonocularCameraListener$1", "Lcom/pudutech/bumblebee/robot/activity/PeripheralsActivity$onMonocularCameraListener$1;", "onMonocularCameraStateListener", "com/pudutech/bumblebee/robot/activity/PeripheralsActivity$onMonocularCameraStateListener$1", "Lcom/pudutech/bumblebee/robot/activity/PeripheralsActivity$onMonocularCameraStateListener$1;", "palletViews", "Ljava/util/HashMap;", "Lcom/pudutech/bumblebee/robot/activity/PeripheralsActivity$PalletView;", "Lkotlin/collections/HashMap;", "getPalletViews", "()Ljava/util/HashMap;", "robotInterface", "getRobotInterface", "()Lcom/pudutech/bumblebee/robot/aidl/RobotInterface;", "setRobotInterface", "(Lcom/pudutech/bumblebee/robot/aidl/RobotInterface;)V", "servicePath", "getServicePath", "setServicePath", "initLED", "", "initLora", "initScreen", "initTray", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "setAllLedExceptPallet", "red", "Lkotlin/UByte;", "green", "blue", "setAllLedExceptPallet-b33U2AM", "(BBB)V", "setAllPalletLED", "setAllPalletLED-b33U2AM", "syncDecodeQRCode", "bitmap", "Landroid/graphics/Bitmap;", "toRGBInt", "toRGBInt-b33U2AM", "(BBB)I", "createNewView", "Lcom/pudutech/bumblebee/robot/aidl/serialize/Pallet;", "PalletView", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class PeripheralsActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private final IRobotListener.Stub commonListener;
    private AIDLConnection<RobotInterface> connection;
    private final IDeliveryRobotListener.Stub deliveryListener;
    private AIDLConnection<HardwareInterface> hardware;
    private HardwareInterface hardwareInterface;
    private int index;
    private final HashMap<Integer, PalletView> palletViews;
    private RobotInterface robotInterface;
    private final String TAG = "PeripheralsActivity";
    private String servicePath = "com.pudutech.bumblebee.robot.RobotService";
    private String hardwareServicePath = "com.pudutech.mirsdk.hardware.HardwareService";
    private final PeripheralsActivity$onMonocularCameraStateListener$1 onMonocularCameraStateListener = new IMarkerCameraState.Stub() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$onMonocularCameraStateListener$1
        @Override // com.pudutech.mirsdk.hardware.IMarkerCameraState
        public void onCameraFrameTick() {
        }

        @Override // com.pudutech.mirsdk.hardware.IMarkerCameraState
        public void onOpened(boolean p0) {
            String str;
            str = PeripheralsActivity.this.TAG;
            Pdlog.m3273d(str, "onMonocularCameraStateListener onOpened() p0 = " + p0);
        }
    };
    private final PeripheralsActivity$onMonocularCameraListener$1 onMonocularCameraListener = new PeripheralsActivity$onMonocularCameraListener$1(this);
    private final EnumMap<DecodeHintType, Object> HINTS = new EnumMap<>(DecodeHintType.class);

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[UpdateEvent.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[UpdateEvent.UNNECESSARY.ordinal()] = 1;
            $EnumSwitchMapping$0[UpdateEvent.UPDATING.ordinal()] = 2;
            $EnumSwitchMapping$0[UpdateEvent.FAIL.ordinal()] = 3;
            $EnumSwitchMapping$0[UpdateEvent.SUCCESS.ordinal()] = 4;
            int[] iArr2 = new int[TouchPlace.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[TouchPlace.Head.ordinal()] = 1;
            $EnumSwitchMapping$1[TouchPlace.LeftEar.ordinal()] = 2;
            $EnumSwitchMapping$1[TouchPlace.RightEar.ordinal()] = 3;
            $EnumSwitchMapping$1[TouchPlace.FunctionButton.ordinal()] = 4;
        }
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

    /* renamed from: toRGBInt-b33U2AM, reason: not valid java name */
    public final int m4311toRGBIntb33U2AM(byte red, byte green, byte blue) {
        return ((red & 255) << 16) | ((green & 255) << 8) | (blue & 255);
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.pudutech.bumblebee.robot.activity.PeripheralsActivity$onMonocularCameraStateListener$1] */
    public PeripheralsActivity() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(BarcodeFormat.AZTEC);
        arrayList.add(BarcodeFormat.CODABAR);
        arrayList.add(BarcodeFormat.CODE_39);
        arrayList.add(BarcodeFormat.CODE_93);
        arrayList.add(BarcodeFormat.CODE_128);
        arrayList.add(BarcodeFormat.DATA_MATRIX);
        arrayList.add(BarcodeFormat.EAN_8);
        arrayList.add(BarcodeFormat.EAN_13);
        arrayList.add(BarcodeFormat.ITF);
        arrayList.add(BarcodeFormat.MAXICODE);
        arrayList.add(BarcodeFormat.PDF_417);
        arrayList.add(BarcodeFormat.QR_CODE);
        arrayList.add(BarcodeFormat.RSS_14);
        arrayList.add(BarcodeFormat.RSS_EXPANDED);
        arrayList.add(BarcodeFormat.UPC_A);
        arrayList.add(BarcodeFormat.UPC_E);
        arrayList.add(BarcodeFormat.UPC_EAN_EXTENSION);
        this.HINTS.put((EnumMap<DecodeHintType, Object>) DecodeHintType.TRY_HARDER, (DecodeHintType) BarcodeFormat.QR_CODE);
        this.HINTS.put((EnumMap<DecodeHintType, Object>) DecodeHintType.POSSIBLE_FORMATS, (DecodeHintType) arrayList);
        this.HINTS.put((EnumMap<DecodeHintType, Object>) DecodeHintType.CHARACTER_SET, (DecodeHintType) "utf-8");
        this.palletViews = new HashMap<>();
        this.deliveryListener = new PeripheralsActivity$deliveryListener$1(this);
        this.commonListener = new PeripheralsActivity$commonListener$1(this);
    }

    public final String getServicePath() {
        return this.servicePath;
    }

    public final void setServicePath(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.servicePath = str;
    }

    public final String getHardwareServicePath() {
        return this.hardwareServicePath;
    }

    public final void setHardwareServicePath(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.hardwareServicePath = str;
    }

    public final AIDLConnection<RobotInterface> getConnection() {
        return this.connection;
    }

    public final void setConnection(AIDLConnection<RobotInterface> aIDLConnection) {
        this.connection = aIDLConnection;
    }

    public final AIDLConnection<HardwareInterface> getHardware() {
        return this.hardware;
    }

    public final void setHardware(AIDLConnection<HardwareInterface> aIDLConnection) {
        this.hardware = aIDLConnection;
    }

    public final HardwareInterface getHardwareInterface() {
        return this.hardwareInterface;
    }

    public final void setHardwareInterface(HardwareInterface hardwareInterface) {
        this.hardwareInterface = hardwareInterface;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C4144R.layout.robot_app_activity_peripherals);
        String stringExtra = getIntent().getStringExtra("servicePath");
        if (stringExtra != null) {
            this.servicePath = stringExtra;
            Pdlog.m3273d(this.TAG, "change servicePath=" + this.servicePath);
        }
        final String str = this.servicePath;
        final PeripheralsActivity$onCreate$3 peripheralsActivity$onCreate$3 = PeripheralsActivity$onCreate$3.INSTANCE;
        this.connection = new AIDLConnection<RobotInterface>(str, peripheralsActivity$onCreate$3) { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$onCreate$2
            @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                String str2;
                String str3;
                super.onServiceConnected(name, service);
                Pdlog.m3275i("RobotDevicesConnection", "onConnected name=" + name + "  " + service);
                PeripheralsActivity.this.setRobotInterface(getInterface());
                RobotInterface robotInterface = PeripheralsActivity.this.getRobotInterface();
                if (robotInterface == null) {
                    Intrinsics.throwNpe();
                }
                str2 = PeripheralsActivity.this.TAG;
                robotInterface.addListener(str2, PeripheralsActivity.this.getCommonListener());
                RobotInterface robotInterface2 = PeripheralsActivity.this.getRobotInterface();
                if (robotInterface2 == null) {
                    Intrinsics.throwNpe();
                }
                str3 = PeripheralsActivity.this.TAG;
                robotInterface2.addDeliveryRobotListener(str3, PeripheralsActivity.this.getDeliveryListener());
                RobotInterface robotInterface3 = PeripheralsActivity.this.getRobotInterface();
                if (robotInterface3 != null) {
                    robotInterface3.requestPallets();
                }
            }

            @Override // android.content.ServiceConnection
            public void onBindingDied(ComponentName name) {
                String str2;
                super.onBindingDied(name);
                str2 = PeripheralsActivity.this.TAG;
                Pdlog.m3277w(str2, "onBindingDied name=" + name);
            }

            @Override // android.content.ServiceConnection
            public void onNullBinding(ComponentName name) {
                String str2;
                super.onNullBinding(name);
                str2 = PeripheralsActivity.this.TAG;
                Pdlog.m3277w(str2, "onNullBinding name=" + name);
            }

            @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                String str2;
                super.onServiceDisconnected(name);
                str2 = PeripheralsActivity.this.TAG;
                Pdlog.m3277w(str2, "onServiceDisconnected name=" + name);
            }
        };
        final String str2 = this.hardwareServicePath;
        final PeripheralsActivity$onCreate$5 peripheralsActivity$onCreate$5 = PeripheralsActivity$onCreate$5.INSTANCE;
        this.hardware = new AIDLConnection<HardwareInterface>(str2, peripheralsActivity$onCreate$5) { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$onCreate$4
            @Override // android.content.ServiceConnection
            public void onBindingDied(ComponentName name) {
                String str3;
                super.onBindingDied(name);
                str3 = PeripheralsActivity.this.TAG;
                Pdlog.m3277w(str3, "HardwareInterface onBindingDied name=" + name);
            }

            @Override // android.content.ServiceConnection
            public void onNullBinding(ComponentName name) {
                String str3;
                super.onNullBinding(name);
                str3 = PeripheralsActivity.this.TAG;
                Pdlog.m3277w(str3, "HardwareInterface onNullBinding name=" + name);
            }

            @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                super.onServiceConnected(name, service);
                Pdlog.m3275i("RobotDevicesConnection", "onConnected name=" + name + "  " + service);
                PeripheralsActivity.this.setHardwareInterface(getInterface());
            }

            @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                String str3;
                super.onServiceDisconnected(name);
                str3 = PeripheralsActivity.this.TAG;
                Pdlog.m3277w(str3, "RobotDevicesConnection onServiceDisconnected name=" + name);
            }
        };
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new PeripheralsActivity$onCreate$6(this, null), 3, null);
        ((Button) _$_findCachedViewById(C4144R.id.btnBack)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$onCreate$7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PeripheralsActivity.this.finish();
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnOpen)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$onCreate$8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RobotInterface robotInterface = PeripheralsActivity.this.getRobotInterface();
                if (robotInterface != null) {
                    robotInterface.open();
                }
                HardwareInterface hardwareInterface = PeripheralsActivity.this.getHardwareInterface();
                if (hardwareInterface != null) {
                    hardwareInterface.open();
                }
            }
        });
        ((Switch) _$_findCachedViewById(C4144R.id.switch_monocular_camera)).setOnCheckedChangeListener(new PeripheralsActivity$onCreate$9(this));
        initLED();
        initScreen();
        initLora();
        initTray();
    }

    public final int getIndex() {
        return this.index;
    }

    public final void setIndex(int i) {
        this.index = i;
    }

    public final EnumMap<DecodeHintType, Object> getHINTS() {
        return this.HINTS;
    }

    public final String syncDecodeQRCode(Bitmap bitmap) {
        RGBLuminanceSource rGBLuminanceSource;
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        RGBLuminanceSource rGBLuminanceSource2 = (RGBLuminanceSource) null;
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int[] iArr = new int[width * height];
            bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
            rGBLuminanceSource = new RGBLuminanceSource(width, height, iArr);
        } catch (Exception e) {
            e = e;
        }
        try {
            Result result = new MultiFormatReader().decode(new BinaryBitmap(new HybridBinarizer(rGBLuminanceSource)), this.HINTS);
            Intrinsics.checkExpressionValueIsNotNull(result, "result");
            return result.getText();
        } catch (Exception e2) {
            e = e2;
            rGBLuminanceSource2 = rGBLuminanceSource;
            e.printStackTrace();
            if (rGBLuminanceSource2 == null) {
                return null;
            }
            try {
                Result result2 = new MultiFormatReader().decode(new BinaryBitmap(new GlobalHistogramBinarizer(rGBLuminanceSource2)), this.HINTS);
                Intrinsics.checkExpressionValueIsNotNull(result2, "result");
                return result2.getText();
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        RobotInterface robotInterface = this.robotInterface;
        if (robotInterface != null) {
            robotInterface.removeListener(this.TAG);
        }
        AIDLConnection<RobotInterface> aIDLConnection = this.connection;
        if (aIDLConnection != null) {
            aIDLConnection.disconnect(this);
        }
    }

    public final RobotInterface getRobotInterface() {
        return this.robotInterface;
    }

    public final void setRobotInterface(RobotInterface robotInterface) {
        this.robotInterface = robotInterface;
    }

    public final void initLED() {
        ((Button) _$_findCachedViewById(C4144R.id.btnLEDPuduBlue)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initLED$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PeripheralsActivity.this.m4309setAllLedExceptPalletb33U2AM((byte) 22, (byte) -96, (byte) -1);
                PeripheralsActivity.this.m4310setAllPalletLEDb33U2AM((byte) 22, (byte) -96, (byte) -1);
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnLEDWhite)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initLED$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PeripheralsActivity.this.m4309setAllLedExceptPalletb33U2AM((byte) -1, (byte) -1, (byte) -1);
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnLEDRed)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initLED$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PeripheralsActivity.m4306setAllLedExceptPalletb33U2AM$default(PeripheralsActivity.this, (byte) -1, (byte) 0, (byte) 0, 6, null);
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnLEDGreen)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initLED$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PeripheralsActivity.m4306setAllLedExceptPalletb33U2AM$default(PeripheralsActivity.this, (byte) 0, (byte) -1, (byte) 0, 5, null);
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnLEDBlue)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initLED$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PeripheralsActivity.m4306setAllLedExceptPalletb33U2AM$default(PeripheralsActivity.this, (byte) 0, (byte) 0, (byte) -1, 3, null);
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnLEDBlack)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initLED$6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PeripheralsActivity.m4306setAllLedExceptPalletb33U2AM$default(PeripheralsActivity.this, (byte) 0, (byte) 0, (byte) 0, 7, null);
                PeripheralsActivity.m4307setAllPalletLEDb33U2AM$default(PeripheralsActivity.this, (byte) 0, (byte) 0, (byte) 0, 7, null);
            }
        });
    }

    /* renamed from: toRGBInt-b33U2AM$default, reason: not valid java name */
    public static /* synthetic */ int m4308toRGBIntb33U2AM$default(PeripheralsActivity peripheralsActivity, byte b, byte b2, byte b3, int i, Object obj) {
        if ((i & 1) != 0) {
            b = 0;
        }
        if ((i & 2) != 0) {
            b2 = 0;
        }
        if ((i & 4) != 0) {
            b3 = 0;
        }
        return peripheralsActivity.m4311toRGBIntb33U2AM(b, b2, b3);
    }

    /* renamed from: setAllLedExceptPallet-b33U2AM$default, reason: not valid java name */
    public static /* synthetic */ void m4306setAllLedExceptPalletb33U2AM$default(PeripheralsActivity peripheralsActivity, byte b, byte b2, byte b3, int i, Object obj) {
        if ((i & 1) != 0) {
            b = 0;
        }
        if ((i & 2) != 0) {
            b2 = 0;
        }
        if ((i & 4) != 0) {
            b3 = 0;
        }
        peripheralsActivity.m4309setAllLedExceptPalletb33U2AM(b, b2, b3);
    }

    /* renamed from: setAllLedExceptPallet-b33U2AM, reason: not valid java name */
    public final void m4309setAllLedExceptPalletb33U2AM(byte red, byte green, byte blue) {
        for (SurfaceLED surfaceLED : LEDHelper.INSTANCE.getEars()) {
            RobotInterface robotInterface = this.robotInterface;
            if (robotInterface != null) {
                robotInterface.controlRGB(surfaceLED, m4311toRGBIntb33U2AM(red, green, blue), m4308toRGBIntb33U2AM$default(this, (byte) 0, (byte) 0, (byte) 0, 7, null), 1000);
            }
        }
        for (SurfaceLED surfaceLED2 : CollectionsKt.arrayListOf(SurfaceLED.Bottom, SurfaceLED.FunctionButton, SurfaceLED.HlsLeft, SurfaceLED.HlsRight)) {
            RobotInterface robotInterface2 = this.robotInterface;
            if (robotInterface2 != null) {
                robotInterface2.controlRGB(surfaceLED2, m4311toRGBIntb33U2AM(red, green, blue), m4308toRGBIntb33U2AM$default(this, (byte) 0, (byte) 0, (byte) 0, 7, null), 1000);
            }
        }
    }

    /* renamed from: setAllPalletLED-b33U2AM$default, reason: not valid java name */
    public static /* synthetic */ void m4307setAllPalletLEDb33U2AM$default(PeripheralsActivity peripheralsActivity, byte b, byte b2, byte b3, int i, Object obj) {
        if ((i & 1) != 0) {
            b = 0;
        }
        if ((i & 2) != 0) {
            b2 = 0;
        }
        if ((i & 4) != 0) {
            b3 = 0;
        }
        peripheralsActivity.m4310setAllPalletLEDb33U2AM(b, b2, b3);
    }

    /* renamed from: setAllPalletLED-b33U2AM, reason: not valid java name */
    public final void m4310setAllPalletLEDb33U2AM(byte red, byte green, byte blue) {
        for (SurfaceLED surfaceLED : LEDHelper.INSTANCE.getAllPallets()) {
            RobotInterface robotInterface = this.robotInterface;
            if (robotInterface != null) {
                robotInterface.controlRGB(surfaceLED, m4311toRGBIntb33U2AM(red, green, blue), m4308toRGBIntb33U2AM$default(this, (byte) 0, (byte) 0, (byte) 0, 7, null), 1000);
            }
        }
    }

    public final void initScreen() {
        ((Button) _$_findCachedViewById(C4144R.id.btnRGBTest)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initScreen$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RobotInterface robotInterface = PeripheralsActivity.this.getRobotInterface();
                if (robotInterface != null) {
                    robotInterface.controlLEDScreen(LEDScreenMode.RGB_TEST);
                }
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnInit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initScreen$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RobotInterface robotInterface = PeripheralsActivity.this.getRobotInterface();
                if (robotInterface != null) {
                    robotInterface.controlLEDScreen(LEDScreenMode.INIT);
                }
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnTurnRight)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initScreen$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RobotInterface robotInterface = PeripheralsActivity.this.getRobotInterface();
                if (robotInterface != null) {
                    robotInterface.controlLEDScreen(LEDScreenMode.TURN_RIGHT);
                }
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnShortText)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initScreen$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RobotInterface robotInterface = PeripheralsActivity.this.getRobotInterface();
                if (robotInterface != null) {
                    robotInterface.setupLEDScreenContent("Pudu", -16776961);
                }
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnLongText)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initScreen$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RobotInterface robotInterface = PeripheralsActivity.this.getRobotInterface();
                if (robotInterface != null) {
                    robotInterface.setupLEDScreenContent("0123456789abcdefghij,你好！", -1);
                }
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnIdle)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initScreen$6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RobotInterface robotInterface = PeripheralsActivity.this.getRobotInterface();
                if (robotInterface != null) {
                    robotInterface.controlLEDScreen(LEDScreenMode.IDLE);
                }
            }
        });
        final PeripheralsActivity$initScreen$listener$1 peripheralsActivity$initScreen$listener$1 = new PeripheralsActivity$initScreen$listener$1(this);
        ((Button) _$_findCachedViewById(C4144R.id.btnTryUpdateFontLib)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initScreen$7

            /* JADX WARN: Classes with same name are omitted:
              classes2.dex
             */
            /* compiled from: PeripheralsActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initScreen$7$1", m3970f = "PeripheralsActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initScreen$7$1 */
            /* loaded from: classes.dex */
            static final class C41631 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;

                /* renamed from: p$ */
                private CoroutineScope f4742p$;

                C41631(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C41631 c41631 = new C41631(completion);
                    c41631.f4742p$ = (CoroutineScope) obj;
                    return c41631;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C41631) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    RobotInterface robotInterface = PeripheralsActivity.this.getRobotInterface();
                    if (robotInterface != null) {
                        robotInterface.update(UpdateObject.LED_SCREEN_FONT_LIB, peripheralsActivity$initScreen$listener$1, false);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C41631(null), 3, null);
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnForceUpdateFontLib)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initScreen$8

            /* JADX WARN: Classes with same name are omitted:
              classes2.dex
             */
            /* compiled from: PeripheralsActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initScreen$8$1", m3970f = "PeripheralsActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initScreen$8$1 */
            /* loaded from: classes.dex */
            static final class C41641 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;

                /* renamed from: p$ */
                private CoroutineScope f4743p$;

                C41641(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C41641 c41641 = new C41641(completion);
                    c41641.f4743p$ = (CoroutineScope) obj;
                    return c41641;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C41641) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    RobotInterface robotInterface = PeripheralsActivity.this.getRobotInterface();
                    if (robotInterface != null) {
                        robotInterface.update(UpdateObject.LED_SCREEN_FONT_LIB, peripheralsActivity$initScreen$listener$1, true);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C41641(null), 3, null);
            }
        });
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* compiled from: PeripheralsActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/activity/PeripheralsActivity$PalletView;", "", "switch", "Landroid/widget/Switch;", "info", "Landroid/widget/TextView;", "(Landroid/widget/Switch;Landroid/widget/TextView;)V", "getInfo", "()Landroid/widget/TextView;", "getSwitch", "()Landroid/widget/Switch;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final /* data */ class PalletView {
        private final TextView info;
        private final Switch switch;

        public static /* synthetic */ PalletView copy$default(PalletView palletView, Switch r1, TextView textView, int i, Object obj) {
            if ((i & 1) != 0) {
                r1 = palletView.switch;
            }
            if ((i & 2) != 0) {
                textView = palletView.info;
            }
            return palletView.copy(r1, textView);
        }

        /* renamed from: component1, reason: from getter */
        public final Switch getSwitch() {
            return this.switch;
        }

        /* renamed from: component2, reason: from getter */
        public final TextView getInfo() {
            return this.info;
        }

        public final PalletView copy(Switch r2, TextView info) {
            Intrinsics.checkParameterIsNotNull(r2, "switch");
            Intrinsics.checkParameterIsNotNull(info, "info");
            return new PalletView(r2, info);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PalletView)) {
                return false;
            }
            PalletView palletView = (PalletView) other;
            return Intrinsics.areEqual(this.switch, palletView.switch) && Intrinsics.areEqual(this.info, palletView.info);
        }

        public int hashCode() {
            Switch r0 = this.switch;
            int hashCode = (r0 != null ? r0.hashCode() : 0) * 31;
            TextView textView = this.info;
            return hashCode + (textView != null ? textView.hashCode() : 0);
        }

        public String toString() {
            return "PalletView(switch=" + this.switch + ", info=" + this.info + ")";
        }

        public PalletView(Switch r2, TextView info) {
            Intrinsics.checkParameterIsNotNull(r2, "switch");
            Intrinsics.checkParameterIsNotNull(info, "info");
            this.switch = r2;
            this.info = info;
        }

        public final TextView getInfo() {
            return this.info;
        }

        public final Switch getSwitch() {
            return this.switch;
        }
    }

    public final HashMap<Integer, PalletView> getPalletViews() {
        return this.palletViews;
    }

    public final PalletView createNewView(final Pallet createNewView) {
        Intrinsics.checkParameterIsNotNull(createNewView, "$this$createNewView");
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -2);
        PeripheralsActivity peripheralsActivity = this;
        LinearLayout linearLayout = new LinearLayout(peripheralsActivity);
        linearLayout.setLayoutParams(layoutParams);
        Switch r3 = new Switch(peripheralsActivity);
        r3.setLayoutParams(layoutParams);
        r3.setText("第 " + createNewView.getPalletId() + " 层  电源开关");
        r3.setTextSize(30.0f);
        r3.setChecked(createNewView.getIsPowerOn());
        r3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$createNewView$1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                String str;
                RobotInterface robotInterface;
                PeripheralDevice m4329getDevice7apg3OU = PalletProtocol.INSTANCE.m4329getDevice7apg3OU(UByte.m4528constructorimpl((byte) createNewView.getPalletId()));
                if (m4329getDevice7apg3OU != null) {
                    str = PeripheralsActivity.this.TAG;
                    Pdlog.m3275i(str, "pallet switch change when isChecked=" + z + " isPowerOn=" + createNewView.getIsPowerOn());
                    if (createNewView.getIsPowerOn() == z || (robotInterface = PeripheralsActivity.this.getRobotInterface()) == null) {
                        return;
                    }
                    robotInterface.setPeripheralDevicePower(m4329getDevice7apg3OU, z);
                }
            }
        });
        TextView textView = new TextView(peripheralsActivity);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(30.0f);
        linearLayout.setOrientation(1);
        linearLayout.addView(r3);
        linearLayout.addView(textView);
        ((LinearLayout) _$_findCachedViewById(C4144R.id.layoutPallet)).addView(linearLayout);
        this.palletViews.put(Integer.valueOf(createNewView.getPalletId()), new PalletView(r3, textView));
        PalletView palletView = this.palletViews.get(Integer.valueOf(createNewView.getPalletId()));
        if (palletView == null) {
            Intrinsics.throwNpe();
        }
        return palletView;
    }

    public final IDeliveryRobotListener.Stub getDeliveryListener() {
        return this.deliveryListener;
    }

    public final IRobotListener.Stub getCommonListener() {
        return this.commonListener;
    }

    public final void initLora() {
        ((Switch) _$_findCachedViewById(C4144R.id.swLoraNotice)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initLora$1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    RobotInterface robotInterface = PeripheralsActivity.this.getRobotInterface();
                    if (robotInterface != null) {
                        robotInterface.noticeToVIP("test");
                        return;
                    }
                    return;
                }
                RobotInterface robotInterface2 = PeripheralsActivity.this.getRobotInterface();
                if (robotInterface2 != null) {
                    robotInterface2.stopNoticeVIP();
                }
            }
        });
    }

    public final void initTray() {
        ((Button) _$_findCachedViewById(C4144R.id.btnRequestTray)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initTray$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = PeripheralsActivity.this.TAG;
                Pdlog.m3275i(str, "click request tray");
                RobotInterface robotInterface = PeripheralsActivity.this.getRobotInterface();
                if (robotInterface != null) {
                    robotInterface.requestPallets();
                }
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnTurnAllTraysOff)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initTray$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = PeripheralsActivity.this.TAG;
                Pdlog.m3275i(str, "click turn all off. " + PeripheralsActivity.this.getPalletViews().size());
                Iterator<Map.Entry<Integer, PeripheralsActivity.PalletView>> it = PeripheralsActivity.this.getPalletViews().entrySet().iterator();
                while (it.hasNext()) {
                    it.next().getValue().getSwitch().setChecked(false);
                }
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnTurnAllTraysOn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initTray$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = PeripheralsActivity.this.TAG;
                Pdlog.m3275i(str, "click turn all on. " + PeripheralsActivity.this.getPalletViews().size());
                Iterator<Map.Entry<Integer, PeripheralsActivity.PalletView>> it = PeripheralsActivity.this.getPalletViews().entrySet().iterator();
                while (it.hasNext()) {
                    it.next().getValue().getSwitch().setChecked(true);
                }
            }
        });
    }
}
