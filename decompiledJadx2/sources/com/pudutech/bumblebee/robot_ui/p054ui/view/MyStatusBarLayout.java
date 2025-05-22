package com.pudutech.bumblebee.robot_ui.p054ui.view;

import android.content.Context;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleInfoContract;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleInfoPresenter;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.manager.SafeModeManager;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import com.pudutech.bumblebee.robot_ui.util.NetStatusUtil;
import com.pudutech.disinfect.fox.util.SlamNetworkStateReceive;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MyStatusBarLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000S\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b*\u0001\u0011\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005B\u001b\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB#\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u001fH\u0016J\u000e\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\nJ\u0010\u0010$\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\nH\u0002J\b\u0010%\u001a\u00020\u001cH\u0002J\u0010\u0010&\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020\nH\u0016J\b\u0010(\u001a\u00020\u001cH\u0002J\b\u0010)\u001a\u00020\u001cH\u0002R\u0016\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/MyStatusBarLayout;", "Landroid/widget/RelativeLayout;", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleInfoContract$ViewInterface;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "kotlin.jvm.PlatformType", "currentPower", "safeModeListener", "com/pudutech/bumblebee/robot_ui/ui/view/MyStatusBarLayout$safeModeListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/view/MyStatusBarLayout$safeModeListener$1;", "scheduleInfoPresenter", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleInfoPresenter;", "getScheduleInfoPresenter", "()Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleInfoPresenter;", "scheduleInfoPresenter$delegate", "Lkotlin/Lazy;", "slamNetworkStateReceive", "Lcom/pudutech/disinfect/fox/util/SlamNetworkStateReceive;", "initView", "", "onNetStatus", "isconnect", "", "onWindowFocusChanged", "hasWindowFocus", "setBattery", "i", "setRobotScheduleCount", "showCloud", "showNum", "int", "startChangeListener", "stopChangeListener", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MyStatusBarLayout extends RelativeLayout implements ScheduleInfoContract.ViewInterface {
    private final String TAG;
    private HashMap _$_findViewCache;
    private int currentPower;
    private final MyStatusBarLayout$safeModeListener$1 safeModeListener;

    /* renamed from: scheduleInfoPresenter$delegate, reason: from kotlin metadata */
    private final Lazy scheduleInfoPresenter;
    private final SlamNetworkStateReceive slamNetworkStateReceive;

    private final ScheduleInfoPresenter getScheduleInfoPresenter() {
        return (ScheduleInfoPresenter) this.scheduleInfoPresenter.getValue();
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

    /* JADX WARN: Type inference failed for: r2v8, types: [com.pudutech.bumblebee.robot_ui.ui.view.MyStatusBarLayout$safeModeListener$1] */
    public MyStatusBarLayout(Context context) {
        super(context);
        this.TAG = getClass().getSimpleName();
        this.currentPower = 100;
        this.scheduleInfoPresenter = LazyKt.lazy(MyStatusBarLayout$scheduleInfoPresenter$2.INSTANCE);
        this.slamNetworkStateReceive = new SlamNetworkStateReceive(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.MyStatusBarLayout$slamNetworkStateReceive$1
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
                MyStatusBarLayout.this.onNetStatus(z);
            }
        });
        this.safeModeListener = new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.MyStatusBarLayout$safeModeListener$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public void invoke(boolean isOpen) {
                ImageView safe_mode_status = (ImageView) MyStatusBarLayout.this._$_findCachedViewById(C4188R.id.safe_mode_status);
                Intrinsics.checkExpressionValueIsNotNull(safe_mode_status, "safe_mode_status");
                safe_mode_status.setVisibility(isOpen ? 0 : 8);
            }
        };
        initView();
    }

    /* JADX WARN: Type inference failed for: r1v8, types: [com.pudutech.bumblebee.robot_ui.ui.view.MyStatusBarLayout$safeModeListener$1] */
    public MyStatusBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = getClass().getSimpleName();
        this.currentPower = 100;
        this.scheduleInfoPresenter = LazyKt.lazy(MyStatusBarLayout$scheduleInfoPresenter$2.INSTANCE);
        this.slamNetworkStateReceive = new SlamNetworkStateReceive(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.MyStatusBarLayout$slamNetworkStateReceive$1
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
                MyStatusBarLayout.this.onNetStatus(z);
            }
        });
        this.safeModeListener = new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.MyStatusBarLayout$safeModeListener$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public void invoke(boolean isOpen) {
                ImageView safe_mode_status = (ImageView) MyStatusBarLayout.this._$_findCachedViewById(C4188R.id.safe_mode_status);
                Intrinsics.checkExpressionValueIsNotNull(safe_mode_status, "safe_mode_status");
                safe_mode_status.setVisibility(isOpen ? 0 : 8);
            }
        };
        initView();
    }

    /* JADX WARN: Type inference failed for: r1v8, types: [com.pudutech.bumblebee.robot_ui.ui.view.MyStatusBarLayout$safeModeListener$1] */
    public MyStatusBarLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = getClass().getSimpleName();
        this.currentPower = 100;
        this.scheduleInfoPresenter = LazyKt.lazy(MyStatusBarLayout$scheduleInfoPresenter$2.INSTANCE);
        this.slamNetworkStateReceive = new SlamNetworkStateReceive(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.MyStatusBarLayout$slamNetworkStateReceive$1
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
                MyStatusBarLayout.this.onNetStatus(z);
            }
        });
        this.safeModeListener = new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.MyStatusBarLayout$safeModeListener$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public void invoke(boolean isOpen) {
                ImageView safe_mode_status = (ImageView) MyStatusBarLayout.this._$_findCachedViewById(C4188R.id.safe_mode_status);
                Intrinsics.checkExpressionValueIsNotNull(safe_mode_status, "safe_mode_status");
                safe_mode_status.setVisibility(isOpen ? 0 : 8);
            }
        };
        initView();
    }

    private final void initView() {
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        Object systemService = context.getSystemService("layout_inflater");
        if (systemService != null) {
            ((LayoutInflater) systemService).inflate(C4188R.layout.layout_my_status_bar, this);
            setBattery(BatteryInfoManager.INSTANCE.getPower());
            showCloud();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Pdlog.m3273d(this.TAG, "onWindowFocusChanged hasWindowFocus = " + hasWindowFocus);
        if (hasWindowFocus) {
            startChangeListener();
        } else {
            stopChangeListener();
        }
    }

    private final void startChangeListener() {
        Pdlog.m3273d(this.TAG, "startChangeListener " + getContext());
        Context context = getContext();
        if (context != null) {
            if (context instanceof AppCompatActivity) {
                AppCompatActivity appCompatActivity = (AppCompatActivity) context;
                if (appCompatActivity.isDestroyed() || appCompatActivity.isFinishing()) {
                    return;
                }
            }
            if (!NetStatusUtil.isWiFiOn(getContext()) || !NetStatusUtil.isWiFiConnect(getContext())) {
                onNetStatus(false);
            } else {
                onNetStatus(true);
            }
            RobotContext.INSTANCE.getContext().registerReceiver(this.slamNetworkStateReceive, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            getScheduleInfoPresenter().replaceView(this);
            setRobotScheduleCount(getScheduleInfoPresenter().getNum());
            ImageView safe_mode_status = (ImageView) _$_findCachedViewById(C4188R.id.safe_mode_status);
            Intrinsics.checkExpressionValueIsNotNull(safe_mode_status, "safe_mode_status");
            safe_mode_status.setVisibility(Constans.INSTANCE.isSafeMode() ? 0 : 8);
            SafeModeManager.INSTANCE.addSafeStateListener(this.safeModeListener);
        }
    }

    private final void stopChangeListener() {
        getScheduleInfoPresenter().removeView(this);
        Pdlog.m3273d(this.TAG, "scheduleInfoPresenter.removeView = " + getContext());
        try {
            RobotContext.INSTANCE.getContext().unregisterReceiver(this.slamNetworkStateReceive);
        } catch (IllegalArgumentException e) {
            Pdlog.m3274e(this.TAG, "stopChangeListener ex : " + e.getMessage());
        }
        SafeModeManager.INSTANCE.removeSafeStateListener(this.safeModeListener);
    }

    public final void setBattery(int i) {
        if (i == this.currentPower) {
            return;
        }
        Pdlog.m3273d(this.TAG, "setPower " + i);
        this.currentPower = i;
        ((BatteryView) _$_findCachedViewById(C4188R.id.battery_view)).setPower(this.currentPower);
        TextView battery_num = (TextView) _$_findCachedViewById(C4188R.id.battery_num);
        Intrinsics.checkExpressionValueIsNotNull(battery_num, "battery_num");
        battery_num.setText(String.valueOf(this.currentPower) + "%");
        if (this.currentPower <= 10) {
            ((TextView) _$_findCachedViewById(C4188R.id.battery_num)).setTextColor(getContext().getColor(C4188R.color.low_power_color));
        } else {
            ((TextView) _$_findCachedViewById(C4188R.id.battery_num)).setTextColor(getContext().getColor(C4188R.color.normal_power_color));
        }
    }

    private final void setRobotScheduleCount(int i) {
        Pdlog.m3273d(this.TAG, "setRobotScheduleCount = " + i);
        if (i > 0) {
            ImageView schedule_ic = (ImageView) _$_findCachedViewById(C4188R.id.schedule_ic);
            Intrinsics.checkExpressionValueIsNotNull(schedule_ic, "schedule_ic");
            schedule_ic.setVisibility(0);
            TextView schedule_count = (TextView) _$_findCachedViewById(C4188R.id.schedule_count);
            Intrinsics.checkExpressionValueIsNotNull(schedule_count, "schedule_count");
            schedule_count.setVisibility(0);
            TextView schedule_count2 = (TextView) _$_findCachedViewById(C4188R.id.schedule_count);
            Intrinsics.checkExpressionValueIsNotNull(schedule_count2, "schedule_count");
            schedule_count2.setText(String.valueOf(i));
            return;
        }
        ImageView schedule_ic2 = (ImageView) _$_findCachedViewById(C4188R.id.schedule_ic);
        Intrinsics.checkExpressionValueIsNotNull(schedule_ic2, "schedule_ic");
        schedule_ic2.setVisibility(8);
        TextView schedule_count3 = (TextView) _$_findCachedViewById(C4188R.id.schedule_count);
        Intrinsics.checkExpressionValueIsNotNull(schedule_count3, "schedule_count");
        schedule_count3.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onNetStatus(boolean isconnect) {
        Pdlog.m3273d(this.TAG, "onNetStatus = " + isconnect);
        if (isconnect) {
            ((ImageView) _$_findCachedViewById(C4188R.id.wifi_status_show)).setImageResource(C4188R.drawable.nav_icon_wifi_connected);
        } else {
            ((ImageView) _$_findCachedViewById(C4188R.id.wifi_status_show)).setImageResource(C4188R.drawable.nav_icon_wifi_unconnected);
        }
    }

    @Override // com.pudutech.bumblebee.presenter.schedule_task.ScheduleInfoContract.ViewInterface
    public void showNum(int r5) {
        Pdlog.m3273d(this.TAG, "showNum " + r5 + "  context = " + getContext());
        setRobotScheduleCount(r5);
    }

    private final void showCloud() {
        if (Constans.INSTANCE.getServerInfo().length() == 0) {
            ImageView private_cloud_iv = (ImageView) _$_findCachedViewById(C4188R.id.private_cloud_iv);
            Intrinsics.checkExpressionValueIsNotNull(private_cloud_iv, "private_cloud_iv");
            ViewExtKt.gone(private_cloud_iv);
        } else {
            ImageView private_cloud_iv2 = (ImageView) _$_findCachedViewById(C4188R.id.private_cloud_iv);
            Intrinsics.checkExpressionValueIsNotNull(private_cloud_iv2, "private_cloud_iv");
            ViewExtKt.show(private_cloud_iv2);
        }
    }
}
