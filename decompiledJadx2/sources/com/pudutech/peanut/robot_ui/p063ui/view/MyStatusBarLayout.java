package com.pudutech.peanut.robot_ui.p063ui.view;

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
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.util.NetStatusUtil;
import com.pudutech.peanut.robot_ui.util.NetWorkChangeEvent;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MyStatusBarLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000E\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0010*\u0001\u0014\u0018\u00002\u00020\u0001:\u0001&B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u000e\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0010J\u000e\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\tJ\u000e\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0010J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\tH\u0002J\u000e\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\u0010J\b\u0010!\u001a\u00020\u0017H\u0002J\u0010\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\tH\u0002J\u000e\u0010$\u001a\u00020\u00172\u0006\u0010\u0002\u001a\u00020\u0003J\u0006\u0010%\u001a\u00020\u0017R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0018\u00010\u0012R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015¨\u0006'"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/view/MyStatusBarLayout;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "kotlin.jvm.PlatformType", "currentPower", "isWhiteBg", "", "netWorkChangeReceiver", "Lcom/pudutech/peanut/robot_ui/ui/view/MyStatusBarLayout$NetWorkChangeReceiver;", "schedulerCountChangeListener", "com/pudutech/peanut/robot_ui/ui/view/MyStatusBarLayout$schedulerCountChangeListener$1", "Lcom/pudutech/peanut/robot_ui/ui/view/MyStatusBarLayout$schedulerCountChangeListener$1;", "initView", "", "onNetStatus", "isconnect", "setBattery", "i", "setBlueToothConnect", "isConnect", "setRobotScheduleCount", "setWhiteBg", "isWhite", "setWifiStyle", "showNum", "int", "startChangeListener", "stopChangeListener", "NetWorkChangeReceiver", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MyStatusBarLayout extends RelativeLayout {
    private final String TAG;
    private HashMap _$_findViewCache;
    private int currentPower;
    private boolean isWhiteBg;
    private NetWorkChangeReceiver netWorkChangeReceiver;
    private final MyStatusBarLayout$schedulerCountChangeListener$1 schedulerCountChangeListener;

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

    /* JADX WARN: Type inference failed for: r1v4, types: [com.pudutech.peanut.robot_ui.ui.view.MyStatusBarLayout$schedulerCountChangeListener$1] */
    public MyStatusBarLayout(Context context) {
        super(context);
        this.TAG = getClass().getSimpleName();
        this.currentPower = 100;
        this.schedulerCountChangeListener = new RobotMoveManager.SchedulerCountChangeListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.MyStatusBarLayout$schedulerCountChangeListener$1
            @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.SchedulerCountChangeListener
            public void onCountChange(int r2) {
                MyStatusBarLayout.this.showNum(r2);
            }
        };
        initView();
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [com.pudutech.peanut.robot_ui.ui.view.MyStatusBarLayout$schedulerCountChangeListener$1] */
    public MyStatusBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = getClass().getSimpleName();
        this.currentPower = 100;
        this.schedulerCountChangeListener = new RobotMoveManager.SchedulerCountChangeListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.MyStatusBarLayout$schedulerCountChangeListener$1
            @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.SchedulerCountChangeListener
            public void onCountChange(int r2) {
                MyStatusBarLayout.this.showNum(r2);
            }
        };
        initView();
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [com.pudutech.peanut.robot_ui.ui.view.MyStatusBarLayout$schedulerCountChangeListener$1] */
    public MyStatusBarLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = getClass().getSimpleName();
        this.currentPower = 100;
        this.schedulerCountChangeListener = new RobotMoveManager.SchedulerCountChangeListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.MyStatusBarLayout$schedulerCountChangeListener$1
            @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.SchedulerCountChangeListener
            public void onCountChange(int r2) {
                MyStatusBarLayout.this.showNum(r2);
            }
        };
        initView();
    }

    private final void initView() {
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        Object systemService = context.getSystemService("layout_inflater");
        if (systemService != null) {
            ((LayoutInflater) systemService).inflate(C5508R.layout.layout_my_status_bar, this);
            Integer power = BatteryInfoManager.INSTANCE.getPower();
            setBattery(power != null ? power.intValue() : 0);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
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
        setWifiStyle();
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

    private final void setWifiStyle() {
        if (NetStatusUtil.isConnected(RobotContext.INSTANCE.getContext()) && NetStatusUtil.isWifi(RobotContext.INSTANCE.getContext())) {
            onNetStatus(true);
        } else {
            onNetStatus(false);
        }
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
        ((BatteryView) _$_findCachedViewById(C5508R.id.battery_view)).setPower(this.currentPower);
        TextView battery_num = (TextView) _$_findCachedViewById(C5508R.id.battery_num);
        Intrinsics.checkExpressionValueIsNotNull(battery_num, "battery_num");
        battery_num.setText(String.valueOf(this.currentPower) + "%");
        if (this.currentPower <= 10) {
            ((TextView) _$_findCachedViewById(C5508R.id.battery_num)).setTextColor(getContext().getColor(C5508R.color.low_power_color));
        } else if (this.isWhiteBg) {
            ((BatteryView) _$_findCachedViewById(C5508R.id.battery_view)).setIsWhiteBg(true);
            ((TextView) _$_findCachedViewById(C5508R.id.battery_num)).setTextColor(getContext().getColor(C5508R.color.c_a8a8a8));
        } else {
            ((BatteryView) _$_findCachedViewById(C5508R.id.battery_view)).setIsWhiteBg(false);
            ((TextView) _$_findCachedViewById(C5508R.id.battery_num)).setTextColor(getContext().getColor(C5508R.color.font_title_black));
        }
    }

    private final void setRobotScheduleCount(int i) {
        Pdlog.m3273d(this.TAG, "setRobotScheduleCount = " + i);
        if (i > 0) {
            ImageView schedule_ic = (ImageView) _$_findCachedViewById(C5508R.id.schedule_ic);
            Intrinsics.checkExpressionValueIsNotNull(schedule_ic, "schedule_ic");
            schedule_ic.setVisibility(0);
            TextView schedule_count = (TextView) _$_findCachedViewById(C5508R.id.schedule_count);
            Intrinsics.checkExpressionValueIsNotNull(schedule_count, "schedule_count");
            schedule_count.setVisibility(0);
            TextView schedule_count2 = (TextView) _$_findCachedViewById(C5508R.id.schedule_count);
            Intrinsics.checkExpressionValueIsNotNull(schedule_count2, "schedule_count");
            StringBuilder sb = new StringBuilder();
            sb.append('x');
            sb.append(i);
            schedule_count2.setText(sb.toString());
            if (this.isWhiteBg) {
                ((ImageView) _$_findCachedViewById(C5508R.id.schedule_ic)).setImageResource(C5508R.drawable.ic_icon_statuebar_robot_darkbg);
                ((TextView) _$_findCachedViewById(C5508R.id.schedule_count)).setTextColor(getContext().getColor(C5508R.color.c_a8a8a8));
                return;
            } else {
                ((ImageView) _$_findCachedViewById(C5508R.id.schedule_ic)).setImageResource(C5508R.drawable.ic_icon_statuebar_robot_whitebg);
                ((TextView) _$_findCachedViewById(C5508R.id.schedule_count)).setTextColor(getContext().getColor(C5508R.color.font_title_black));
                return;
            }
        }
        ImageView schedule_ic2 = (ImageView) _$_findCachedViewById(C5508R.id.schedule_ic);
        Intrinsics.checkExpressionValueIsNotNull(schedule_ic2, "schedule_ic");
        schedule_ic2.setVisibility(4);
        TextView schedule_count3 = (TextView) _$_findCachedViewById(C5508R.id.schedule_count);
        Intrinsics.checkExpressionValueIsNotNull(schedule_count3, "schedule_count");
        schedule_count3.setVisibility(4);
    }

    public final void onNetStatus(boolean isconnect) {
        Pdlog.m3273d(this.TAG, "onNetStatus = " + isconnect);
        if (isconnect) {
            if (this.isWhiteBg) {
                ((ImageView) _$_findCachedViewById(C5508R.id.wifi_status_show)).setImageResource(C5508R.drawable.ic_icon_statuebar_wifi_connected_darkbg);
                return;
            } else {
                ((ImageView) _$_findCachedViewById(C5508R.id.wifi_status_show)).setImageResource(C5508R.drawable.ic_icon_statuebar_wifi_connected_whitebg);
                return;
            }
        }
        ((ImageView) _$_findCachedViewById(C5508R.id.wifi_status_show)).setImageResource(C5508R.drawable.ic_icon_statuebar_wifi_unconnect);
    }

    /* compiled from: MyStatusBarLayout.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/view/MyStatusBarLayout$NetWorkChangeReceiver;", "Lcom/pudutech/peanut/robot_ui/util/NetWorkChangeEvent;", "(Lcom/pudutech/peanut/robot_ui/ui/view/MyStatusBarLayout;)V", "onNetworkChange", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final class NetWorkChangeReceiver extends NetWorkChangeEvent {
        public NetWorkChangeReceiver() {
        }

        @Override // com.pudutech.peanut.robot_ui.util.NetWorkChangeEvent
        public void onNetworkChange() {
            if (NetStatusUtil.isConnected(RobotContext.INSTANCE.getContext()) && NetStatusUtil.isWifi(RobotContext.INSTANCE.getContext())) {
                MyStatusBarLayout.this.onNetStatus(true);
            } else {
                MyStatusBarLayout.this.onNetStatus(false);
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
        setWifiStyle();
        if (isWhite) {
            ((BatteryView) _$_findCachedViewById(C5508R.id.battery_view)).setIsWhiteBg(true);
            ((TextView) _$_findCachedViewById(C5508R.id.battery_num)).setTextColor(getContext().getColor(C5508R.color.c_a8a8a8));
        } else {
            ((BatteryView) _$_findCachedViewById(C5508R.id.battery_view)).setIsWhiteBg(false);
            ((TextView) _$_findCachedViewById(C5508R.id.battery_num)).setTextColor(getContext().getColor(C5508R.color.font_title_black));
        }
    }

    public final void setBlueToothConnect(boolean isConnect) {
        ImageView ivBlueTooth = (ImageView) _$_findCachedViewById(C5508R.id.ivBlueTooth);
        Intrinsics.checkExpressionValueIsNotNull(ivBlueTooth, "ivBlueTooth");
        ivBlueTooth.setVisibility(0);
        if (isConnect) {
            if (this.isWhiteBg) {
                ((ImageView) _$_findCachedViewById(C5508R.id.ivBlueTooth)).setImageResource(C5508R.drawable.ic_icon_statuebar_bluetooth_connected_darkbg);
                return;
            } else {
                ((ImageView) _$_findCachedViewById(C5508R.id.ivBlueTooth)).setImageResource(C5508R.drawable.ic_icon_statuebar_bluetooth_connected_whitebg);
                return;
            }
        }
        ((ImageView) _$_findCachedViewById(C5508R.id.ivBlueTooth)).setImageResource(C5508R.drawable.ic_icon_statuebar_bluetooth_unconnect);
    }
}
