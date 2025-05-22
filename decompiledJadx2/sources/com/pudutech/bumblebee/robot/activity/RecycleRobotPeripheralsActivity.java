package com.pudutech.bumblebee.robot.activity;

import android.content.ComponentName;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.bumblebee.robot.C4144R;
import com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener;
import com.pudutech.bumblebee.robot.aidl.IRobotListener;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
import com.pudutech.bumblebee.robot.aidl.serialize.LEDFaceScreenMode;
import com.pudutech.bumblebee.robot.aidl.serialize.SurfaceLED;
import com.pudutech.bumblebee.robot.surface_led.LEDHelper;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.random.Random;
import kotlin.random.RandomKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: RecycleRobotPeripheralsActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010 \u001a\u00020!J\u0006\u0010\"\u001a\u00020!J\u0006\u0010#\u001a\u00020!J\u0012\u0010$\u001a\u00020!2\b\u0010%\u001a\u0004\u0018\u00010&H\u0014J\b\u0010'\u001a\u00020!H\u0014J.\u0010(\u001a\u00020!2\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010+\u001a\u00020*2\b\b\u0002\u0010,\u001a\u00020*ø\u0001\u0000¢\u0006\u0004\b-\u0010.J.\u0010/\u001a\u00020\u00112\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010+\u001a\u00020*2\b\b\u0002\u0010,\u001a\u00020*ø\u0001\u0000¢\u0006\u0004\b0\u00101R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f\u0082\u0002\u0004\n\u0002\b\u0019¨\u00062"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/activity/RecycleRobotPeripheralsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "commonListener", "Lcom/pudutech/bumblebee/robot/aidl/IRobotListener$Stub;", "getCommonListener", "()Lcom/pudutech/bumblebee/robot/aidl/IRobotListener$Stub;", "connection", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/bumblebee/robot/aidl/RobotInterface;", "getConnection", "()Lcom/pudutech/base/architecture/AIDLConnection;", "setConnection", "(Lcom/pudutech/base/architecture/AIDLConnection;)V", "receiveCnt", "", "recycleListener", "Lcom/pudutech/bumblebee/robot/aidl/IRecycleRobotListener$Stub;", "getRecycleListener", "()Lcom/pudutech/bumblebee/robot/aidl/IRecycleRobotListener$Stub;", "robotInterface", "getRobotInterface", "()Lcom/pudutech/bumblebee/robot/aidl/RobotInterface;", "setRobotInterface", "(Lcom/pudutech/bumblebee/robot/aidl/RobotInterface;)V", "servicePath", "getServicePath", "()Ljava/lang/String;", "setServicePath", "(Ljava/lang/String;)V", "initLED", "", "initLEDFaceScreen", "initLora", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "setAllLed", "red", "Lkotlin/UByte;", "green", "blue", "setAllLed-b33U2AM", "(BBB)V", "toRGBInt", "toRGBInt-b33U2AM", "(BBB)I", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class RecycleRobotPeripheralsActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private AIDLConnection<RobotInterface> connection;
    private int receiveCnt;
    private RobotInterface robotInterface;
    private final String TAG = "PeripheralsActivity";
    private String servicePath = "com.pudutech.bumblebee.robot.RobotService";
    private final IRobotListener.Stub commonListener = new RecycleRobotPeripheralsActivity$commonListener$1(this);
    private final IRecycleRobotListener.Stub recycleListener = new RecycleRobotPeripheralsActivity$recycleListener$1(this);

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
    public final int m4315toRGBIntb33U2AM(byte red, byte green, byte blue) {
        return ((red & 255) << 16) | ((green & 255) << 8) | (blue & 255);
    }

    public final String getServicePath() {
        return this.servicePath;
    }

    public final void setServicePath(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.servicePath = str;
    }

    public final AIDLConnection<RobotInterface> getConnection() {
        return this.connection;
    }

    public final void setConnection(AIDLConnection<RobotInterface> aIDLConnection) {
        this.connection = aIDLConnection;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C4144R.layout.robot_app_recycle_peripherals);
        String stringExtra = getIntent().getStringExtra("servicePath");
        if (stringExtra != null) {
            this.servicePath = stringExtra;
            Pdlog.m3273d(this.TAG, "change servicePath=" + this.servicePath);
        }
        TextView tvTitle = (TextView) _$_findCachedViewById(C4144R.id.tvTitle);
        Intrinsics.checkExpressionValueIsNotNull(tvTitle, "tvTitle");
        tvTitle.setText("回盘机器人外设");
        final String str = this.servicePath;
        final RecycleRobotPeripheralsActivity$onCreate$3 recycleRobotPeripheralsActivity$onCreate$3 = RecycleRobotPeripheralsActivity$onCreate$3.INSTANCE;
        this.connection = new AIDLConnection<RobotInterface>(str, recycleRobotPeripheralsActivity$onCreate$3) { // from class: com.pudutech.bumblebee.robot.activity.RecycleRobotPeripheralsActivity$onCreate$2
            @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                String str2;
                String str3;
                super.onServiceConnected(name, service);
                Pdlog.m3275i("RobotDevicesConnection", "onConnected name=" + name + "  " + service);
                RecycleRobotPeripheralsActivity.this.setRobotInterface(getInterface());
                RobotInterface robotInterface = RecycleRobotPeripheralsActivity.this.getRobotInterface();
                if (robotInterface == null) {
                    Intrinsics.throwNpe();
                }
                str2 = RecycleRobotPeripheralsActivity.this.TAG;
                robotInterface.addListener(str2, RecycleRobotPeripheralsActivity.this.getCommonListener());
                RobotInterface robotInterface2 = RecycleRobotPeripheralsActivity.this.getRobotInterface();
                if (robotInterface2 == null) {
                    Intrinsics.throwNpe();
                }
                str3 = RecycleRobotPeripheralsActivity.this.TAG;
                robotInterface2.addRecycleRobotListener(str3, RecycleRobotPeripheralsActivity.this.getRecycleListener());
                RobotInterface robotInterface3 = RecycleRobotPeripheralsActivity.this.getRobotInterface();
                if (robotInterface3 != null) {
                    robotInterface3.requestPallets();
                }
            }

            @Override // android.content.ServiceConnection
            public void onBindingDied(ComponentName name) {
                String str2;
                super.onBindingDied(name);
                str2 = RecycleRobotPeripheralsActivity.this.TAG;
                Pdlog.m3277w(str2, "onBindingDied name=" + name);
            }

            @Override // android.content.ServiceConnection
            public void onNullBinding(ComponentName name) {
                String str2;
                super.onNullBinding(name);
                str2 = RecycleRobotPeripheralsActivity.this.TAG;
                Pdlog.m3277w(str2, "onNullBinding name=" + name);
            }

            @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                String str2;
                super.onServiceDisconnected(name);
                str2 = RecycleRobotPeripheralsActivity.this.TAG;
                Pdlog.m3277w(str2, "onServiceDisconnected name=" + name);
            }
        };
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RecycleRobotPeripheralsActivity$onCreate$4(this, null), 3, null);
        ((Button) _$_findCachedViewById(C4144R.id.btnBack)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.RecycleRobotPeripheralsActivity$onCreate$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecycleRobotPeripheralsActivity.this.finish();
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnOpen)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.RecycleRobotPeripheralsActivity$onCreate$6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RobotInterface robotInterface = RecycleRobotPeripheralsActivity.this.getRobotInterface();
                if (robotInterface != null) {
                    robotInterface.open();
                }
            }
        });
        initLED();
        initLEDFaceScreen();
        initLora();
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
        ((Button) _$_findCachedViewById(C4144R.id.btnLEDPuduBlue)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.RecycleRobotPeripheralsActivity$initLED$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecycleRobotPeripheralsActivity.this.m4314setAllLedb33U2AM((byte) 22, (byte) -96, (byte) -1);
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnLEDWhite)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.RecycleRobotPeripheralsActivity$initLED$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecycleRobotPeripheralsActivity.this.m4314setAllLedb33U2AM((byte) -1, (byte) -1, (byte) -1);
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnLEDRed)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.RecycleRobotPeripheralsActivity$initLED$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecycleRobotPeripheralsActivity.m4312setAllLedb33U2AM$default(RecycleRobotPeripheralsActivity.this, (byte) -1, (byte) 0, (byte) 0, 6, null);
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnLEDGreen)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.RecycleRobotPeripheralsActivity$initLED$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecycleRobotPeripheralsActivity.m4312setAllLedb33U2AM$default(RecycleRobotPeripheralsActivity.this, (byte) 0, (byte) -1, (byte) 0, 5, null);
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnLEDBlue)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.RecycleRobotPeripheralsActivity$initLED$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecycleRobotPeripheralsActivity.m4312setAllLedb33U2AM$default(RecycleRobotPeripheralsActivity.this, (byte) 0, (byte) 0, (byte) -1, 3, null);
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnLEDBlack)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.RecycleRobotPeripheralsActivity$initLED$6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecycleRobotPeripheralsActivity.m4312setAllLedb33U2AM$default(RecycleRobotPeripheralsActivity.this, (byte) 0, (byte) 0, (byte) 0, 7, null);
            }
        });
    }

    /* renamed from: toRGBInt-b33U2AM$default, reason: not valid java name */
    public static /* synthetic */ int m4313toRGBIntb33U2AM$default(RecycleRobotPeripheralsActivity recycleRobotPeripheralsActivity, byte b, byte b2, byte b3, int i, Object obj) {
        if ((i & 1) != 0) {
            b = 0;
        }
        if ((i & 2) != 0) {
            b2 = 0;
        }
        if ((i & 4) != 0) {
            b3 = 0;
        }
        return recycleRobotPeripheralsActivity.m4315toRGBIntb33U2AM(b, b2, b3);
    }

    /* renamed from: setAllLed-b33U2AM$default, reason: not valid java name */
    public static /* synthetic */ void m4312setAllLedb33U2AM$default(RecycleRobotPeripheralsActivity recycleRobotPeripheralsActivity, byte b, byte b2, byte b3, int i, Object obj) {
        if ((i & 1) != 0) {
            b = 0;
        }
        if ((i & 2) != 0) {
            b2 = 0;
        }
        if ((i & 4) != 0) {
            b3 = 0;
        }
        recycleRobotPeripheralsActivity.m4314setAllLedb33U2AM(b, b2, b3);
    }

    /* renamed from: setAllLed-b33U2AM, reason: not valid java name */
    public final void m4314setAllLedb33U2AM(byte red, byte green, byte blue) {
        for (SurfaceLED surfaceLED : LEDHelper.INSTANCE.getAllHolabot()) {
            RobotInterface robotInterface = this.robotInterface;
            if (robotInterface != null) {
                robotInterface.controlRGB(surfaceLED, m4315toRGBIntb33U2AM(red, green, blue), m4313toRGBIntb33U2AM$default(this, (byte) 0, (byte) 0, (byte) 0, 7, null), 1000);
            }
        }
    }

    public final void initLEDFaceScreen() {
        ((Button) _$_findCachedViewById(C4144R.id.btnLookAround)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.RecycleRobotPeripheralsActivity$initLEDFaceScreen$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RobotInterface robotInterface = RecycleRobotPeripheralsActivity.this.getRobotInterface();
                if (robotInterface != null) {
                    robotInterface.controlLEDFaceScreen(LEDFaceScreenMode.LookAround);
                }
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnRandomChange)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.RecycleRobotPeripheralsActivity$initLEDFaceScreen$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int nextInt = RandomKt.nextInt(Random.INSTANCE, ArraysKt.getIndices(LEDFaceScreenMode.values()));
                RobotInterface robotInterface = RecycleRobotPeripheralsActivity.this.getRobotInterface();
                if (robotInterface != null) {
                    robotInterface.controlLEDFaceScreen(LEDFaceScreenMode.values()[nextInt]);
                }
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnIdle)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.RecycleRobotPeripheralsActivity$initLEDFaceScreen$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RobotInterface robotInterface = RecycleRobotPeripheralsActivity.this.getRobotInterface();
                if (robotInterface != null) {
                    robotInterface.controlLEDFaceScreen(LEDFaceScreenMode.CloseLED);
                }
            }
        });
    }

    public final void initLora() {
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 0;
        ((Button) _$_findCachedViewById(C4144R.id.btnSendSnrMsg)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.RecycleRobotPeripheralsActivity$initLora$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = RecycleRobotPeripheralsActivity.this.TAG;
                Pdlog.m3273d(str, "click btnSendSnrMsg " + RecycleRobotPeripheralsActivity.this.getRobotInterface());
                Ref.IntRef intRef2 = intRef;
                intRef2.element = intRef2.element + 1;
                TextView tvSendCnt = (TextView) RecycleRobotPeripheralsActivity.this._$_findCachedViewById(C4144R.id.tvSendCnt);
                Intrinsics.checkExpressionValueIsNotNull(tvSendCnt, "tvSendCnt");
                tvSendCnt.setText("发送计数: " + intRef.element);
                RobotInterface robotInterface = RecycleRobotPeripheralsActivity.this.getRobotInterface();
                if (robotInterface != null) {
                    robotInterface.checkRemoteDeviceResponse();
                }
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnResetRecCnt)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.RecycleRobotPeripheralsActivity$initLora$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i;
                RecycleRobotPeripheralsActivity.this.receiveCnt = 0;
                TextView tvReceiveCnt = (TextView) RecycleRobotPeripheralsActivity.this._$_findCachedViewById(C4144R.id.tvReceiveCnt);
                Intrinsics.checkExpressionValueIsNotNull(tvReceiveCnt, "tvReceiveCnt");
                StringBuilder sb = new StringBuilder();
                sb.append("接收计数: ");
                i = RecycleRobotPeripheralsActivity.this.receiveCnt;
                sb.append(i);
                tvReceiveCnt.setText(sb.toString());
            }
        });
        ((Button) _$_findCachedViewById(C4144R.id.btnResetSendCnt)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot.activity.RecycleRobotPeripheralsActivity$initLora$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                intRef.element = 0;
                TextView tvSendCnt = (TextView) RecycleRobotPeripheralsActivity.this._$_findCachedViewById(C4144R.id.tvSendCnt);
                Intrinsics.checkExpressionValueIsNotNull(tvSendCnt, "tvSendCnt");
                tvSendCnt.setText("发送计数: " + intRef.element);
            }
        });
    }

    public final IRobotListener.Stub getCommonListener() {
        return this.commonListener;
    }

    public final IRecycleRobotListener.Stub getRecycleListener() {
        return this.recycleListener;
    }
}
