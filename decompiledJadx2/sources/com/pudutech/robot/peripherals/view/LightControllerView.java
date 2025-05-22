package com.pudutech.robot.peripherals.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.pudutech.robot.peripherals.C5707R;
import com.pudutech.robot.peripherals.config.LightBeltAnimationColor;
import com.pudutech.robot.peripherals.config.LightBeltAnimationFrame;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LightControllerView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/view/LightControllerView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "getLightBeltAnimationFrame", "Lcom/pudutech/robot/peripherals/config/LightBeltAnimationFrame;", "initView", "", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class LightControllerView extends LinearLayout {
    private final String TAG;
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

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LightControllerView(Context context) {
        this(context, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LightControllerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LightControllerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "LightControllerView";
        initView();
    }

    private final void initView() {
        LayoutInflater.from(getContext()).inflate(C5707R.layout.peripherals_view_light_controller, this);
    }

    public final LightBeltAnimationFrame getLightBeltAnimationFrame() {
        EditText start_color_r = (EditText) _$_findCachedViewById(C5707R.id.start_color_r);
        Intrinsics.checkExpressionValueIsNotNull(start_color_r, "start_color_r");
        int parseInt = Integer.parseInt(start_color_r.getText().toString());
        EditText start_color_g = (EditText) _$_findCachedViewById(C5707R.id.start_color_g);
        Intrinsics.checkExpressionValueIsNotNull(start_color_g, "start_color_g");
        int parseInt2 = Integer.parseInt(start_color_g.getText().toString());
        EditText start_color_b = (EditText) _$_findCachedViewById(C5707R.id.start_color_b);
        Intrinsics.checkExpressionValueIsNotNull(start_color_b, "start_color_b");
        int parseInt3 = Integer.parseInt(start_color_b.getText().toString());
        EditText end_color_r = (EditText) _$_findCachedViewById(C5707R.id.end_color_r);
        Intrinsics.checkExpressionValueIsNotNull(end_color_r, "end_color_r");
        int parseInt4 = Integer.parseInt(end_color_r.getText().toString());
        EditText end_color_g = (EditText) _$_findCachedViewById(C5707R.id.end_color_g);
        Intrinsics.checkExpressionValueIsNotNull(end_color_g, "end_color_g");
        int parseInt5 = Integer.parseInt(end_color_g.getText().toString());
        EditText end_color_b = (EditText) _$_findCachedViewById(C5707R.id.end_color_b);
        Intrinsics.checkExpressionValueIsNotNull(end_color_b, "end_color_b");
        int parseInt6 = Integer.parseInt(end_color_b.getText().toString());
        EditText time_et = (EditText) _$_findCachedViewById(C5707R.id.time_et);
        Intrinsics.checkExpressionValueIsNotNull(time_et, "time_et");
        return new LightBeltAnimationFrame(new LightBeltAnimationColor(parseInt, parseInt2, parseInt3, false, 8, null), new LightBeltAnimationColor(parseInt4, parseInt5, parseInt6, false, 8, null), Integer.parseInt(time_et.getText().toString()));
    }
}
