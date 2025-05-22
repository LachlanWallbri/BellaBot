package com.pudutech.robot.peripherals.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.robot.peripherals.C5707R;
import com.pudutech.robot.peripherals.RobotPeripheralsFactory;
import com.pudutech.robot.peripherals.config.LightBeltType;
import com.pudutech.robot.peripherals.disinfection.DisinfectRobotPeripherals;
import com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals;
import com.pudutech.robot.peripherals.firefox.FirefoxRobotPeripherals;
import com.pudutech.robot.peripherals.firefox.IFirefoxRobotPeripherals;
import com.pudutech.robot.peripherals.hola.ExpressionType;
import com.pudutech.robot.peripherals.hola.HolaBotPeripherals;
import com.pudutech.robot.peripherals.hola.IHolaBotPeripherals;
import com.pudutech.robot.peripherals.hola.LightColor;
import com.pudutech.robot.peripherals.hola.LightMode;
import com.pudutech.robot.peripherals.peanut.IPeanutRobotPeripherals;
import com.pudutech.robot.peripherals.peanut.PeanutRobotPeripherals;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HolaDebugActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010#\u001a\u00020$H\u0002J\u0012\u0010%\u001a\u00020$2\b\u0010&\u001a\u0004\u0018\u00010'H\u0014R\"\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u000b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\"\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\"\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00110\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006("}, m3961d2 = {"Lcom/pudutech/robot/peripherals/activity/HolaDebugActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", TypedValues.Custom.S_COLOR, "", "Lcom/pudutech/robot/peripherals/hola/LightColor;", "getColor", "()[Lcom/pudutech/robot/peripherals/hola/LightColor;", "setColor", "([Lcom/pudutech/robot/peripherals/hola/LightColor;)V", "[Lcom/pudutech/robot/peripherals/hola/LightColor;", "currentColor", "getCurrentColor", "()Lcom/pudutech/robot/peripherals/hola/LightColor;", "setCurrentColor", "(Lcom/pudutech/robot/peripherals/hola/LightColor;)V", "currentMode", "Lcom/pudutech/robot/peripherals/hola/LightMode;", "getCurrentMode", "()Lcom/pudutech/robot/peripherals/hola/LightMode;", "setCurrentMode", "(Lcom/pudutech/robot/peripherals/hola/LightMode;)V", "eyes", "Lcom/pudutech/robot/peripherals/hola/ExpressionType;", "getEyes", "()[Lcom/pudutech/robot/peripherals/hola/ExpressionType;", "setEyes", "([Lcom/pudutech/robot/peripherals/hola/ExpressionType;)V", "[Lcom/pudutech/robot/peripherals/hola/ExpressionType;", "mode", "getMode", "()[Lcom/pudutech/robot/peripherals/hola/LightMode;", "setMode", "([Lcom/pudutech/robot/peripherals/hola/LightMode;)V", "[Lcom/pudutech/robot/peripherals/hola/LightMode;", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class HolaDebugActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private ExpressionType[] eyes = {ExpressionType.EYES_FAST, ExpressionType.EYES_LEFT_TO_RIGHT, ExpressionType.EYES_SMILE, ExpressionType.EYES_WAIT, ExpressionType.EYES_DOWN, ExpressionType.EYES_BLINK, ExpressionType.EYES_ERROR, ExpressionType.EYES_YELLOW, ExpressionType.EYES_ORANGE, ExpressionType.EYES_RED};
    private LightColor[] color = {LightColor.White, LightColor.Red, LightColor.Green, LightColor.Blue, LightColor.Orange, LightColor.Yellow, LightColor.Purple};
    private LightMode[] mode = {LightMode.LIGHT_CLOSE, LightMode.LIGHT_ALWAYS_BRIGHT, LightMode.LIGHT_FLASHING_CLOSE, LightMode.LIGHT_BREATHE, LightMode.LIGHT_FLASHING_BRIGHT, LightMode.LIGHT_RGB, LightMode.LIGHT_BOOT, LightMode.LIGHT_TURN_LEFT, LightMode.LIGHT_TURN_RIGHT};
    private LightColor currentColor = LightColor.White;
    private LightMode currentMode = LightMode.LIGHT_CLOSE;

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

    public final ExpressionType[] getEyes() {
        return this.eyes;
    }

    public final void setEyes(ExpressionType[] expressionTypeArr) {
        Intrinsics.checkParameterIsNotNull(expressionTypeArr, "<set-?>");
        this.eyes = expressionTypeArr;
    }

    public final LightColor[] getColor() {
        return this.color;
    }

    public final void setColor(LightColor[] lightColorArr) {
        Intrinsics.checkParameterIsNotNull(lightColorArr, "<set-?>");
        this.color = lightColorArr;
    }

    public final LightMode[] getMode() {
        return this.mode;
    }

    public final void setMode(LightMode[] lightModeArr) {
        Intrinsics.checkParameterIsNotNull(lightModeArr, "<set-?>");
        this.mode = lightModeArr;
    }

    public final LightColor getCurrentColor() {
        return this.currentColor;
    }

    public final void setCurrentColor(LightColor lightColor) {
        Intrinsics.checkParameterIsNotNull(lightColor, "<set-?>");
        this.currentColor = lightColor;
    }

    public final LightMode getCurrentMode() {
        return this.currentMode;
    }

    public final void setCurrentMode(LightMode lightMode) {
        Intrinsics.checkParameterIsNotNull(lightMode, "<set-?>");
        this.currentMode = lightMode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5707R.layout.peripherals_activity_hola_layout);
        initView();
    }

    private final void initView() {
        IHolaBotPeripherals iHolaBotPeripherals;
        RobotPeripheralsFactory robotPeripheralsFactory = RobotPeripheralsFactory.INSTANCE;
        if (Intrinsics.areEqual(IHolaBotPeripherals.class, IFirefoxRobotPeripherals.class)) {
            IFirefoxRobotPeripherals instance = FirefoxRobotPeripherals.INSTANCE.getINSTANCE();
            if (instance == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.hola.IHolaBotPeripherals");
            }
            iHolaBotPeripherals = (IHolaBotPeripherals) instance;
        } else if (Intrinsics.areEqual(IHolaBotPeripherals.class, IPeanutRobotPeripherals.class)) {
            IPeanutRobotPeripherals instance2 = PeanutRobotPeripherals.INSTANCE.getINSTANCE();
            if (instance2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.hola.IHolaBotPeripherals");
            }
            iHolaBotPeripherals = (IHolaBotPeripherals) instance2;
        } else if (Intrinsics.areEqual(IHolaBotPeripherals.class, IDisinfectRobotPeripherals.class)) {
            IDisinfectRobotPeripherals instance3 = DisinfectRobotPeripherals.INSTANCE.getINSTANCE();
            if (instance3 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.hola.IHolaBotPeripherals");
            }
            iHolaBotPeripherals = (IHolaBotPeripherals) instance3;
        } else {
            if (!Intrinsics.areEqual(IHolaBotPeripherals.class, IHolaBotPeripherals.class)) {
                throw new IllegalArgumentException("getRobotPeripherals " + IHolaBotPeripherals.class + " not find ");
            }
            IHolaBotPeripherals instance4 = HolaBotPeripherals.INSTANCE.getINSTANCE();
            if (instance4 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.hola.IHolaBotPeripherals");
            }
            iHolaBotPeripherals = instance4;
        }
        final IHolaBotPeripherals iHolaBotPeripherals2 = iHolaBotPeripherals;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "this.applicationContext");
        iHolaBotPeripherals2.init(applicationContext, false);
        iHolaBotPeripherals2.addHardWareConnectListener(new Function1<Boolean, Unit>() { // from class: com.pudutech.robot.peripherals.activity.HolaDebugActivity$initView$1
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
                Pdlog.m3273d("cafe", "connect to hardware " + z);
                if (z) {
                    IHolaBotPeripherals iHolaBotPeripherals3 = IHolaBotPeripherals.this;
                    if (iHolaBotPeripherals3 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.hola.HolaBotPeripherals");
                    }
                    HardwareInterface hardWareInterface = ((HolaBotPeripherals) iHolaBotPeripherals3).getHardWareInterface();
                    if (hardWareInterface != null) {
                        hardWareInterface.open();
                    }
                }
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.btn_switch)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.HolaDebugActivity$initView$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (HolaDebugActivity.this.getCurrentMode() == LightMode.LIGHT_RGB) {
                    iHolaBotPeripherals2.mo4499setLightRGBFh2MPcY(LightBeltType.BottomChassisRight, Byte.MIN_VALUE, (byte) 0, Byte.MIN_VALUE);
                    iHolaBotPeripherals2.mo4499setLightRGBFh2MPcY(LightBeltType.BottomChassisLeft, Byte.MIN_VALUE, Byte.MIN_VALUE, (byte) 0);
                    iHolaBotPeripherals2.mo4499setLightRGBFh2MPcY(LightBeltType.BottomChassis, (byte) 0, Byte.MIN_VALUE, Byte.MIN_VALUE);
                } else {
                    IHolaBotPeripherals.DefaultImpls.m4500setLightMode3UDGHhA$default(iHolaBotPeripherals2, new LightBeltType[]{LightBeltType.BottomChassisRight, LightBeltType.BottomChassisLeft, LightBeltType.BottomChassis}, HolaDebugActivity.this.getCurrentColor(), HolaDebugActivity.this.getCurrentMode(), (byte) 0, (byte) 0, (byte) 0, 56, null);
                }
            }
        });
        TextView tv_title = (TextView) _$_findCachedViewById(C5707R.id.tv_title);
        Intrinsics.checkExpressionValueIsNotNull(tv_title, "tv_title");
        tv_title.setText("好啦调试界面");
        ((ImageView) _$_findCachedViewById(C5707R.id.iv_back)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.HolaDebugActivity$initView$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HolaDebugActivity.this.finish();
            }
        });
        HolaDebugActivity holaDebugActivity = this;
        ArrayAdapter arrayAdapter = new ArrayAdapter(holaDebugActivity, C5707R.layout.support_simple_spinner_dropdown_item, this.eyes);
        arrayAdapter.setDropDownViewResource(C5707R.layout.support_simple_spinner_dropdown_item);
        Spinner spinner_eyes = (Spinner) _$_findCachedViewById(C5707R.id.spinner_eyes);
        Intrinsics.checkExpressionValueIsNotNull(spinner_eyes, "spinner_eyes");
        spinner_eyes.setAdapter((SpinnerAdapter) arrayAdapter);
        ((Spinner) _$_findCachedViewById(C5707R.id.spinner_eyes)).setSelection(0);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(holaDebugActivity, C5707R.layout.support_simple_spinner_dropdown_item, this.color);
        arrayAdapter2.setDropDownViewResource(C5707R.layout.support_simple_spinner_dropdown_item);
        Spinner spinner_color = (Spinner) _$_findCachedViewById(C5707R.id.spinner_color);
        Intrinsics.checkExpressionValueIsNotNull(spinner_color, "spinner_color");
        spinner_color.setAdapter((SpinnerAdapter) arrayAdapter2);
        ((Spinner) _$_findCachedViewById(C5707R.id.spinner_color)).setSelection(0);
        ArrayAdapter arrayAdapter3 = new ArrayAdapter(holaDebugActivity, C5707R.layout.support_simple_spinner_dropdown_item, this.mode);
        arrayAdapter3.setDropDownViewResource(C5707R.layout.support_simple_spinner_dropdown_item);
        Spinner spinner_mode = (Spinner) _$_findCachedViewById(C5707R.id.spinner_mode);
        Intrinsics.checkExpressionValueIsNotNull(spinner_mode, "spinner_mode");
        spinner_mode.setAdapter((SpinnerAdapter) arrayAdapter3);
        ((Spinner) _$_findCachedViewById(C5707R.id.spinner_mode)).setSelection(0);
        Spinner spinner_eyes2 = (Spinner) _$_findCachedViewById(C5707R.id.spinner_eyes);
        Intrinsics.checkExpressionValueIsNotNull(spinner_eyes2, "spinner_eyes");
        spinner_eyes2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.pudutech.robot.peripherals.activity.HolaDebugActivity$initView$4
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("cafe", "eyes onItemSelected: " + position);
                iHolaBotPeripherals2.setFace(HolaDebugActivity.this.getEyes()[position]);
            }
        });
        Spinner spinner_color2 = (Spinner) _$_findCachedViewById(C5707R.id.spinner_color);
        Intrinsics.checkExpressionValueIsNotNull(spinner_color2, "spinner_color");
        spinner_color2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.pudutech.robot.peripherals.activity.HolaDebugActivity$initView$5
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("cafe", "color onItemSelected: " + position);
                HolaDebugActivity holaDebugActivity2 = HolaDebugActivity.this;
                holaDebugActivity2.setCurrentColor(holaDebugActivity2.getColor()[position]);
            }
        });
        Spinner spinner_mode2 = (Spinner) _$_findCachedViewById(C5707R.id.spinner_mode);
        Intrinsics.checkExpressionValueIsNotNull(spinner_mode2, "spinner_mode");
        spinner_mode2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.pudutech.robot.peripherals.activity.HolaDebugActivity$initView$6
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("cafe", "mode onItemSelected: " + position);
                HolaDebugActivity holaDebugActivity2 = HolaDebugActivity.this;
                holaDebugActivity2.setCurrentMode(holaDebugActivity2.getMode()[position]);
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> parent) {
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }
        });
    }
}
