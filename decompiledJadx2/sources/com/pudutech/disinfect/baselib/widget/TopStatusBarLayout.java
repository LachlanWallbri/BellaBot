package com.pudutech.disinfect.baselib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.C4429R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: TopStatusBarLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001c\u001dB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u00020\rH\u0002J\b\u0010\u0011\u001a\u00020\rH\u0002J\u0010\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\tH\u0007J\u0015\u0010\u0013\u001a\u00020\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u0015J\u000e\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u000fJ\u000e\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u000fJ\u000e\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\tR\u000e\u0010\u000b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/widget/TopStatusBarLayout;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "theme_type", "changeNetStatus", "", "link", "", "init", "initView", "refreshTheme", "setBattery", "power", "(Ljava/lang/Integer;)V", "setBluetooth", "b", "setCharge", "isCharge", "setRobotCount", "count", "Companion", "Theme", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class TopStatusBarLayout extends LinearLayout {
    private static final String TAG = "TopStatusLayout";
    public static final int THEME_BLACK = 1;
    public static final int THEME_GRAY = 2;
    private HashMap _$_findViewCache;
    private int theme_type;

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* compiled from: TopStatusBarLayout.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/widget/TopStatusBarLayout$Theme;", "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    /* loaded from: classes5.dex */
    public @interface Theme {
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TopStatusBarLayout(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.theme_type = 1;
        init();
    }

    private final void init() {
        initView();
        refreshTheme(1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TopStatusBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.theme_type = 1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C4429R.styleable.TopStatusBarLayout);
        if (obtainStyledAttributes.hasValue(C4429R.styleable.TopStatusBarLayout_theme_type)) {
            this.theme_type = obtainStyledAttributes.getInteger(C4429R.styleable.TopStatusBarLayout_theme_type, 1);
        }
        obtainStyledAttributes.recycle();
        init();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TopStatusBarLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.theme_type = 1;
    }

    private final void initView() {
        LayoutInflater.from(getContext()).inflate(C4429R.layout.layout_top_status_bar, this);
    }

    public final void refreshTheme(int theme_type) {
        Pdlog.m3273d(TAG, "change the color when the theme is set, the default theme is white " + theme_type);
        if (theme_type == 1) {
            TextView textView = (TextView) _$_findCachedViewById(C4429R.id.tv_batter_power);
            if (textView != null) {
                textView.setTextColor(ContextCompat.getColor(getContext(), C4429R.color.bg_black_22));
            }
            ImageView imageView = (ImageView) _$_findCachedViewById(C4429R.id.iv_robot);
            if (imageView != null) {
                imageView.setImageResource(C4429R.drawable.icon_statuebar_robot);
            }
            ImageView imageView2 = (ImageView) _$_findCachedViewById(C4429R.id.iv_lora);
            if (imageView2 != null) {
                imageView2.setImageResource(C4429R.drawable.icon_lora);
            }
            TextView textView2 = (TextView) _$_findCachedViewById(C4429R.id.tv_lora_num);
            if (textView2 != null) {
                textView2.setTextColor(ContextCompat.getColor(getContext(), C4429R.color.bg_black_22));
            }
            TextView textView3 = (TextView) _$_findCachedViewById(C4429R.id.tv_robot_count);
            if (textView3 != null) {
                textView3.setTextColor(ContextCompat.getColor(getContext(), C4429R.color.bg_black_22));
            }
            BatteryView batteryView = (BatteryView) _$_findCachedViewById(C4429R.id.batter_view);
            if (batteryView != null) {
                batteryView.setBatteryPaintColor("#FF222222");
                return;
            }
            return;
        }
        if (theme_type != 2) {
            return;
        }
        TextView textView4 = (TextView) _$_findCachedViewById(C4429R.id.tv_batter_power);
        if (textView4 != null) {
            textView4.setTextColor(ContextCompat.getColor(getContext(), C4429R.color.bg_black_a8));
        }
        ImageView imageView3 = (ImageView) _$_findCachedViewById(C4429R.id.iv_robot);
        if (imageView3 != null) {
            imageView3.setImageResource(C4429R.drawable.icon_robot_gray);
        }
        ImageView imageView4 = (ImageView) _$_findCachedViewById(C4429R.id.iv_lora);
        if (imageView4 != null) {
            imageView4.setImageResource(C4429R.drawable.icon_lora_gray);
        }
        TextView textView5 = (TextView) _$_findCachedViewById(C4429R.id.tv_lora_num);
        if (textView5 != null) {
            textView5.setTextColor(ContextCompat.getColor(getContext(), C4429R.color.bg_black_a8));
        }
        TextView textView6 = (TextView) _$_findCachedViewById(C4429R.id.tv_robot_count);
        if (textView6 != null) {
            textView6.setTextColor(ContextCompat.getColor(getContext(), C4429R.color.bg_black_a8));
        }
        BatteryView batteryView2 = (BatteryView) _$_findCachedViewById(C4429R.id.batter_view);
        if (batteryView2 != null) {
            batteryView2.setBatteryPaintColor("#FFA8A8A8");
        }
    }

    public final void setCharge(boolean isCharge) {
        BatteryView batteryView = (BatteryView) _$_findCachedViewById(C4429R.id.batter_view);
        if (batteryView != null) {
            batteryView.setCharge(isCharge);
        }
    }

    public final void setBattery(Integer power) {
        if (power == null) {
            LinearLayout l_battery = (LinearLayout) _$_findCachedViewById(C4429R.id.l_battery);
            Intrinsics.checkExpressionValueIsNotNull(l_battery, "l_battery");
            l_battery.setVisibility(8);
            return;
        }
        LinearLayout l_battery2 = (LinearLayout) _$_findCachedViewById(C4429R.id.l_battery);
        Intrinsics.checkExpressionValueIsNotNull(l_battery2, "l_battery");
        l_battery2.setVisibility(0);
        TextView textView = (TextView) _$_findCachedViewById(C4429R.id.tv_batter_power);
        if (textView != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(power);
            sb.append('%');
            textView.setText(sb.toString());
        }
        if (power.intValue() <= 10) {
            TextView textView2 = (TextView) _$_findCachedViewById(C4429R.id.tv_batter_power);
            if (textView2 != null) {
                textView2.setTextColor(ContextCompat.getColor(getContext(), C4429R.color.btn_red_normal_color));
                return;
            }
            return;
        }
        TextView textView3 = (TextView) _$_findCachedViewById(C4429R.id.tv_batter_power);
        if (textView3 != null) {
            textView3.setTextColor(ContextCompat.getColor(getContext(), C4429R.color.bg_black_22));
        }
    }

    public final void setRobotCount(int count) {
        Pdlog.m3273d(TAG, "setRobotCount is " + count);
        if (count > 1) {
            LinearLayout l_robot = (LinearLayout) _$_findCachedViewById(C4429R.id.l_robot);
            Intrinsics.checkExpressionValueIsNotNull(l_robot, "l_robot");
            l_robot.setVisibility(0);
            TextView tv_robot_count = (TextView) _$_findCachedViewById(C4429R.id.tv_robot_count);
            Intrinsics.checkExpressionValueIsNotNull(tv_robot_count, "tv_robot_count");
            StringBuilder sb = new StringBuilder();
            sb.append('x');
            sb.append(count);
            tv_robot_count.setText(sb.toString());
            return;
        }
        LinearLayout l_robot2 = (LinearLayout) _$_findCachedViewById(C4429R.id.l_robot);
        Intrinsics.checkExpressionValueIsNotNull(l_robot2, "l_robot");
        l_robot2.setVisibility(4);
    }

    public final void setBluetooth(boolean b) {
        ImageView iv_bluetooth = (ImageView) _$_findCachedViewById(C4429R.id.iv_bluetooth);
        Intrinsics.checkExpressionValueIsNotNull(iv_bluetooth, "iv_bluetooth");
        iv_bluetooth.setVisibility(b ? 0 : 8);
    }

    public final void changeNetStatus(boolean link) {
        if (this.theme_type != 1) {
            if (link) {
                ((ImageView) _$_findCachedViewById(C4429R.id.iv_wifi)).setImageResource(C4429R.drawable.icon_wifi_gray);
                return;
            } else {
                ((ImageView) _$_findCachedViewById(C4429R.id.iv_wifi)).setImageResource(C4429R.drawable.icon_wifi_unconnect);
                return;
            }
        }
        if (link) {
            ((ImageView) _$_findCachedViewById(C4429R.id.iv_wifi)).setImageResource(C4429R.drawable.icon_wifi);
        } else {
            ((ImageView) _$_findCachedViewById(C4429R.id.iv_wifi)).setImageResource(C4429R.drawable.icon_wifi_unconnect);
        }
    }
}
