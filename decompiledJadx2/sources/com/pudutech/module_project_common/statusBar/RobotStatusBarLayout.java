package com.pudutech.module_project_common.statusBar;

import android.content.Context;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.manager.NetWorkChangeEvent;
import com.pudutech.disinfect.baselib.util.NetStatusUtil;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.module_project_common.C5364R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RobotStatusBarLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000[\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u000f*\u0001\u001c\u0018\u00002\u00020\u0001:\u0001.B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u001f\u001a\u00020 H\u0002J\u000e\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u0012J\u000e\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020\tJ\u000e\u0010%\u001a\u00020 2\u0006\u0010&\u001a\u00020\u0012J\u0010\u0010'\u001a\u00020 2\u0006\u0010$\u001a\u00020\tH\u0002J\u000e\u0010(\u001a\u00020 2\u0006\u0010)\u001a\u00020\u0012J\u0010\u0010*\u001a\u00020 2\u0006\u0010+\u001a\u00020\tH\u0002J\u000e\u0010,\u001a\u00020 2\u0006\u0010\u0002\u001a\u00020\u0003J\u0006\u0010-\u001a\u00020 R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0018\u00010\u0016R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000¨\u0006/"}, m3961d2 = {"Lcom/pudutech/module_project_common/statusBar/RobotStatusBarLayout;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "kotlin.jvm.PlatformType", "battery_num", "Landroid/widget/TextView;", "currentPower", "isWhiteBg", "", "ivBlueTooth", "Landroid/widget/ImageView;", "netWorkChangeReceiver", "Lcom/pudutech/module_project_common/statusBar/RobotStatusBarLayout$NetWorkChangeReceiver;", "robotBattery_view", "Lcom/pudutech/module_project_common/statusBar/RobotBatteryView;", "schedule_count", "schedule_ic", "schedulerCountChangeListener", "com/pudutech/module_project_common/statusBar/RobotStatusBarLayout$schedulerCountChangeListener$1", "Lcom/pudutech/module_project_common/statusBar/RobotStatusBarLayout$schedulerCountChangeListener$1;", "wifi_status_show", "initView", "", "onNetStatus", "isconnect", "setBattery", "i", "setBlueToothConnect", "isConnect", "setRobotScheduleCount", "setWhiteBg", "isWhite", "showNum", "int", "startChangeListener", "stopChangeListener", "NetWorkChangeReceiver", "module_project_common_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class RobotStatusBarLayout extends RelativeLayout {
    private final String TAG;
    private TextView battery_num;
    private int currentPower;
    private boolean isWhiteBg;
    private ImageView ivBlueTooth;
    private NetWorkChangeReceiver netWorkChangeReceiver;
    private RobotBatteryView robotBattery_view;
    private TextView schedule_count;
    private ImageView schedule_ic;
    private final RobotStatusBarLayout$schedulerCountChangeListener$1 schedulerCountChangeListener;
    private ImageView wifi_status_show;

    /* JADX WARN: Type inference failed for: r1v4, types: [com.pudutech.module_project_common.statusBar.RobotStatusBarLayout$schedulerCountChangeListener$1] */
    public RobotStatusBarLayout(Context context) {
        super(context);
        this.TAG = getClass().getSimpleName();
        this.currentPower = 100;
        this.schedulerCountChangeListener = new RobotMoveManager.SchedulerCountChangeListener() { // from class: com.pudutech.module_project_common.statusBar.RobotStatusBarLayout$schedulerCountChangeListener$1
            @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.SchedulerCountChangeListener
            public void onCountChange(int r2) {
                RobotStatusBarLayout.this.showNum(r2);
            }
        };
        initView();
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [com.pudutech.module_project_common.statusBar.RobotStatusBarLayout$schedulerCountChangeListener$1] */
    public RobotStatusBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = getClass().getSimpleName();
        this.currentPower = 100;
        this.schedulerCountChangeListener = new RobotMoveManager.SchedulerCountChangeListener() { // from class: com.pudutech.module_project_common.statusBar.RobotStatusBarLayout$schedulerCountChangeListener$1
            @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.SchedulerCountChangeListener
            public void onCountChange(int r2) {
                RobotStatusBarLayout.this.showNum(r2);
            }
        };
        initView();
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [com.pudutech.module_project_common.statusBar.RobotStatusBarLayout$schedulerCountChangeListener$1] */
    public RobotStatusBarLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = getClass().getSimpleName();
        this.currentPower = 100;
        this.schedulerCountChangeListener = new RobotMoveManager.SchedulerCountChangeListener() { // from class: com.pudutech.module_project_common.statusBar.RobotStatusBarLayout$schedulerCountChangeListener$1
            @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.SchedulerCountChangeListener
            public void onCountChange(int r2) {
                RobotStatusBarLayout.this.showNum(r2);
            }
        };
        initView();
    }

    private final void initView() {
        View inflate = LayoutInflater.from(getContext()).inflate(C5364R.layout.layout_robot_status_bar, this);
        View findViewById = inflate.findViewById(C5364R.id.wifi_status_show);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.wifi_status_show)");
        this.wifi_status_show = (ImageView) findViewById;
        View findViewById2 = inflate.findViewById(C5364R.id.schedule_ic);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById(R.id.schedule_ic)");
        this.schedule_ic = (ImageView) findViewById2;
        View findViewById3 = inflate.findViewById(C5364R.id.schedule_count);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "view.findViewById(R.id.schedule_count)");
        this.schedule_count = (TextView) findViewById3;
        View findViewById4 = inflate.findViewById(C5364R.id.ivBlueTooth);
        Intrinsics.checkExpressionValueIsNotNull(findViewById4, "view.findViewById(R.id.ivBlueTooth)");
        this.ivBlueTooth = (ImageView) findViewById4;
        View findViewById5 = inflate.findViewById(C5364R.id.robot_battery_view);
        Intrinsics.checkExpressionValueIsNotNull(findViewById5, "view.findViewById<RobotB…(R.id.robot_battery_view)");
        this.robotBattery_view = (RobotBatteryView) findViewById5;
        View findViewById6 = inflate.findViewById(C5364R.id.battery_num);
        Intrinsics.checkExpressionValueIsNotNull(findViewById6, "view.findViewById(R.id.battery_num)");
        this.battery_num = (TextView) findViewById6;
        Integer power = BatteryInfoManager.INSTANCE.getPower();
        setBattery(power != null ? power.intValue() : 0);
    }

    public final void startChangeListener(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Pdlog.m3273d(this.TAG, "startChangeListener " + context);
        if (context instanceof AppCompatActivity) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) context;
            if (appCompatActivity.isDestroyed() || appCompatActivity.isFinishing()) {
                return;
            }
        }
        if (NetStatusUtil.isConnected(context)) {
            onNetStatus(true);
        } else {
            onNetStatus(false);
        }
        if (this.netWorkChangeReceiver == null) {
            this.netWorkChangeReceiver = new NetWorkChangeReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.setPriority(Integer.MAX_VALUE);
        try {
            context.registerReceiver(this.netWorkChangeReceiver, intentFilter);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
        }
        setRobotScheduleCount(RobotMoveManager.INSTANCE.getSchedulerCount());
        RobotMoveManager.INSTANCE.addSchedulerCountChangeListener(this.schedulerCountChangeListener);
    }

    public final void stopChangeListener() {
        Context context;
        Pdlog.m3273d(this.TAG, "stopChangeListener " + this.netWorkChangeReceiver);
        try {
            RobotMoveManager.INSTANCE.removeSchedulerCountChangeListener(this.schedulerCountChangeListener);
            NetWorkChangeReceiver netWorkChangeReceiver = this.netWorkChangeReceiver;
            if (netWorkChangeReceiver == null || (context = getContext()) == null) {
                return;
            }
            context.unregisterReceiver(netWorkChangeReceiver);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
        }
    }

    public final void setBattery(int i) {
        if (i == this.currentPower) {
            return;
        }
        Pdlog.m3273d(this.TAG, "setPower " + i);
        this.currentPower = i;
        RobotBatteryView robotBatteryView = this.robotBattery_view;
        if (robotBatteryView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("robotBattery_view");
        }
        robotBatteryView.setPower(this.currentPower);
        TextView textView = this.battery_num;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("battery_num");
        }
        textView.setText(String.valueOf(this.currentPower) + "%");
        if (this.currentPower <= 10) {
            TextView textView2 = this.battery_num;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("battery_num");
            }
            textView2.setTextColor(getContext().getColor(C5364R.color.low_power_color));
            RobotBatteryView robotBatteryView2 = this.robotBattery_view;
            if (robotBatteryView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("robotBattery_view");
            }
            robotBatteryView2.setIsWhiteBg(Boolean.valueOf(this.isWhiteBg));
            return;
        }
        if (this.isWhiteBg) {
            RobotBatteryView robotBatteryView3 = this.robotBattery_view;
            if (robotBatteryView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("robotBattery_view");
            }
            robotBatteryView3.setIsWhiteBg(true);
            TextView textView3 = this.battery_num;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("battery_num");
            }
            textView3.setTextColor(getContext().getColor(C5364R.color.white));
            return;
        }
        RobotBatteryView robotBatteryView4 = this.robotBattery_view;
        if (robotBatteryView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("robotBattery_view");
        }
        robotBatteryView4.setIsWhiteBg(false);
        TextView textView4 = this.battery_num;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("battery_num");
        }
        textView4.setTextColor(getContext().getColor(C5364R.color.normal_power_color));
    }

    private final void setRobotScheduleCount(int i) {
        Pdlog.m3273d(this.TAG, "setRobotScheduleCount = " + i);
        if (i > 0) {
            ImageView imageView = this.schedule_ic;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("schedule_ic");
            }
            imageView.setVisibility(0);
            TextView textView = this.schedule_count;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("schedule_count");
            }
            textView.setVisibility(0);
            TextView textView2 = this.schedule_count;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("schedule_count");
            }
            textView2.setText(String.valueOf(i));
            return;
        }
        ImageView imageView2 = this.schedule_ic;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("schedule_ic");
        }
        imageView2.setVisibility(4);
        TextView textView3 = this.schedule_count;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("schedule_count");
        }
        textView3.setVisibility(4);
    }

    public final void onNetStatus(boolean isconnect) {
        Pdlog.m3273d(this.TAG, "onNetStatus = " + isconnect);
        if (isconnect) {
            if (this.isWhiteBg) {
                ImageView imageView = this.wifi_status_show;
                if (imageView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("wifi_status_show");
                }
                imageView.setImageResource(C5364R.drawable.nav_icon_wifi_white_connected);
                return;
            }
            ImageView imageView2 = this.wifi_status_show;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("wifi_status_show");
            }
            imageView2.setImageResource(C5364R.drawable.nav_icon_wifi_connected);
            return;
        }
        if (this.isWhiteBg) {
            ImageView imageView3 = this.wifi_status_show;
            if (imageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("wifi_status_show");
            }
            imageView3.setImageResource(C5364R.drawable.nav_icon_wifi_white_unconnected);
            return;
        }
        ImageView imageView4 = this.wifi_status_show;
        if (imageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("wifi_status_show");
        }
        imageView4.setImageResource(C5364R.drawable.nav_icon_wifi_unconnected);
    }

    /* compiled from: RobotStatusBarLayout.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/module_project_common/statusBar/RobotStatusBarLayout$NetWorkChangeReceiver;", "Lcom/pudutech/disinfect/baselib/network/manager/NetWorkChangeEvent;", "(Lcom/pudutech/module_project_common/statusBar/RobotStatusBarLayout;)V", "onNetworkChange", "", "module_project_common_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final class NetWorkChangeReceiver extends NetWorkChangeEvent {
        public NetWorkChangeReceiver() {
        }

        @Override // com.pudutech.disinfect.baselib.network.manager.NetWorkChangeEvent
        public void onNetworkChange() {
            if (NetStatusUtil.isConnected(RobotStatusBarLayout.this.getContext()) && NetStatusUtil.isWifi(RobotStatusBarLayout.this.getContext())) {
                RobotStatusBarLayout.this.onNetStatus(true);
            } else {
                RobotStatusBarLayout.this.onNetStatus(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showNum(int r5) {
        Pdlog.m3273d(this.TAG, "showNum " + r5);
        setRobotScheduleCount(r5);
    }

    public final void setWhiteBg(boolean isWhite) {
        this.isWhiteBg = isWhite;
        if (isWhite) {
            ImageView imageView = this.wifi_status_show;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("wifi_status_show");
            }
            imageView.setImageResource(C5364R.drawable.nav_icon_wifi_white_connected);
            RobotBatteryView robotBatteryView = this.robotBattery_view;
            if (robotBatteryView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("robotBattery_view");
            }
            robotBatteryView.setIsWhiteBg(true);
            TextView textView = this.battery_num;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("battery_num");
            }
            textView.setTextColor(getContext().getColor(C5364R.color.white));
        }
    }

    public final void setBlueToothConnect(boolean isConnect) {
        ImageView imageView = this.ivBlueTooth;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivBlueTooth");
        }
        imageView.setVisibility(0);
        if (isConnect) {
            ImageView imageView2 = this.ivBlueTooth;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ivBlueTooth");
            }
            imageView2.setImageResource(C5364R.drawable.ic_icon_bluetooth);
            return;
        }
        ImageView imageView3 = this.ivBlueTooth;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivBlueTooth");
        }
        imageView3.setImageResource(C5364R.drawable.ic_icon_bluetooth_a8a8);
    }
}
