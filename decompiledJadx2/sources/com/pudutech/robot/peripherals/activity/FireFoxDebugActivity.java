package com.pudutech.robot.peripherals.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.robot.peripherals.C5707R;
import com.pudutech.robot.peripherals.RobotPeripheralsFactory;
import com.pudutech.robot.peripherals.config.LightBeltType;
import com.pudutech.robot.peripherals.disinfection.DisinfectRobotPeripherals;
import com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals;
import com.pudutech.robot.peripherals.firefox.FirefoxRobotPeripherals;
import com.pudutech.robot.peripherals.firefox.IFirefoxRobotPeripherals;
import com.pudutech.robot.peripherals.hola.HolaBotPeripherals;
import com.pudutech.robot.peripherals.hola.IHolaBotPeripherals;
import com.pudutech.robot.peripherals.interf.IRobotPeripherals;
import com.pudutech.robot.peripherals.peanut.IPeanutRobotPeripherals;
import com.pudutech.robot.peripherals.peanut.PeanutRobotPeripherals;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FireFoxDebugActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/activity/FireFoxDebugActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class FireFoxDebugActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;

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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5707R.layout.peripherals_activity_firefox_layout);
        initView();
    }

    private final void initView() {
        Object obj;
        RobotPeripheralsFactory robotPeripheralsFactory = RobotPeripheralsFactory.INSTANCE;
        if (Intrinsics.areEqual(IFirefoxRobotPeripherals.class, IFirefoxRobotPeripherals.class)) {
            Object instance = FirefoxRobotPeripherals.INSTANCE.getINSTANCE();
            if (instance == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.firefox.IFirefoxRobotPeripherals");
            }
            obj = (IRobotPeripherals) ((IFirefoxRobotPeripherals) instance);
        } else if (Intrinsics.areEqual(IFirefoxRobotPeripherals.class, IPeanutRobotPeripherals.class)) {
            Object instance2 = PeanutRobotPeripherals.INSTANCE.getINSTANCE();
            if (instance2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.firefox.IFirefoxRobotPeripherals");
            }
            obj = (IRobotPeripherals) ((IFirefoxRobotPeripherals) instance2);
        } else if (Intrinsics.areEqual(IFirefoxRobotPeripherals.class, IDisinfectRobotPeripherals.class)) {
            Object instance3 = DisinfectRobotPeripherals.INSTANCE.getINSTANCE();
            if (instance3 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.firefox.IFirefoxRobotPeripherals");
            }
            obj = (IRobotPeripherals) ((IFirefoxRobotPeripherals) instance3);
        } else {
            if (!Intrinsics.areEqual(IFirefoxRobotPeripherals.class, IHolaBotPeripherals.class)) {
                throw new IllegalArgumentException("getRobotPeripherals " + IFirefoxRobotPeripherals.class + " not find ");
            }
            Object instance4 = HolaBotPeripherals.INSTANCE.getINSTANCE();
            if (instance4 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.firefox.IFirefoxRobotPeripherals");
            }
            obj = (IRobotPeripherals) ((IFirefoxRobotPeripherals) instance4);
        }
        final IFirefoxRobotPeripherals iFirefoxRobotPeripherals = (IFirefoxRobotPeripherals) obj;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "this.applicationContext");
        iFirefoxRobotPeripherals.init(applicationContext, false);
        ((Button) _$_findCachedViewById(C5707R.id.light_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.FireFoxDebugActivity$initView$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IFirefoxRobotPeripherals.this.controlLightBelt(new LightBeltType[]{LightBeltType.LeftEar, LightBeltType.RightEar}, "Led2_1_1");
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.close_light_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.FireFoxDebugActivity$initView$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IFirefoxRobotPeripherals.this.stopLightBelt(LightBeltType.LeftEar, LightBeltType.RightEar);
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.temp_light_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.FireFoxDebugActivity$initView$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IFirefoxRobotPeripherals.this.controlLightBelt(new LightBeltType[]{LightBeltType.LeftEar, LightBeltType.RightEar}, "Led2_9_2");
            }
        });
        TextView tv_title = (TextView) _$_findCachedViewById(C5707R.id.tv_title);
        Intrinsics.checkExpressionValueIsNotNull(tv_title, "tv_title");
        tv_title.setText("火狐调试界面");
        ((ImageView) _$_findCachedViewById(C5707R.id.iv_back)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.FireFoxDebugActivity$initView$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FireFoxDebugActivity.this.finish();
            }
        });
    }
}
