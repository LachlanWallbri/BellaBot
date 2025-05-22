package com.pudutech.robot.peripherals.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.util.WifiUtil;
import com.pudutech.robot.peripherals.C5707R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MainActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/activity/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MainActivity extends AppCompatActivity {
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
        setContentView(C5707R.layout.peripherals_activity_main);
        ((Button) _$_findCachedViewById(C5707R.id.btn_firefox)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.MainActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, (Class<?>) FireFoxDebugActivity.class));
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.btn_ninetails)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.MainActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, (Class<?>) NineTailsDebugActivity.class));
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.btn_peanut)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.MainActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, (Class<?>) PeanutDebugActivity.class));
            }
        });
        ((Button) _$_findCachedViewById(C5707R.id.btn_hola)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.MainActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, (Class<?>) HolaDebugActivity.class));
            }
        });
        TextView tv_title = (TextView) _$_findCachedViewById(C5707R.id.tv_title);
        Intrinsics.checkExpressionValueIsNotNull(tv_title, "tv_title");
        tv_title.setText("外设调试界面");
        ((ImageView) _$_findCachedViewById(C5707R.id.iv_back)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.peripherals.activity.MainActivity$onCreate$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.this.finish();
            }
        });
        String cls = MainActivity.class.toString();
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("current mac address ");
        WifiUtil wifiUtil = WifiUtil.INSTANCE;
        sb.append(wifiUtil != null ? wifiUtil.getMac() : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(cls, objArr);
        TextView tv_mac = (TextView) _$_findCachedViewById(C5707R.id.tv_mac);
        Intrinsics.checkExpressionValueIsNotNull(tv_mac, "tv_mac");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("当前机器Mac地址:");
        String mac = WifiUtil.INSTANCE.getMac();
        if (mac == null) {
            mac = "";
        }
        sb2.append((Object) mac);
        tv_mac.setText(sb2.toString());
    }
}
