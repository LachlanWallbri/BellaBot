package com.pudutech.disinfect.baselib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.C4429R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: OnTheWayLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0006\u0010\u0013\u001a\u00020\u0010J\b\u0010\u0014\u001a\u00020\u0010H\u0002J\u000e\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\fJ\u000e\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\fJ\b\u0010\u0018\u001a\u00020\u0010H\u0002J\b\u0010\u0019\u001a\u00020\u0010H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/widget/OnTheWayLayout;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "animaton", "Landroid/view/animation/Animation;", "genAnimation", "", TypedValues.Transition.S_DURATION, "", "hideLayout", "initView", "setTarget", TypedValues.Attributes.S_TARGET, "showLayout", "startAnimation", "stopAnimation", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class OnTheWayLayout extends RelativeLayout {
    private final String TAG;
    private HashMap _$_findViewCache;
    private Animation animaton;

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

    public OnTheWayLayout(Context context) {
        super(context);
        this.TAG = "OnTheWayLayout";
        initView();
    }

    public OnTheWayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "OnTheWayLayout";
        initView();
    }

    public OnTheWayLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = "OnTheWayLayout";
        initView();
    }

    private final void initView() {
        LayoutInflater.from(getContext()).inflate(C4429R.layout.layout_on_the_way, this);
        genAnimation(10000L);
    }

    private final void genAnimation(long duration) {
        this.animaton = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        Animation animation = this.animaton;
        if (animation == null) {
            Intrinsics.throwUninitializedPropertyAccessException("animaton");
        }
        animation.setDuration(duration);
        Animation animation2 = this.animaton;
        if (animation2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("animaton");
        }
        animation2.setFillAfter(true);
        Animation animation3 = this.animaton;
        if (animation3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("animaton");
        }
        animation3.setInterpolator(new LinearInterpolator());
        Animation animation4 = this.animaton;
        if (animation4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("animaton");
        }
        animation4.setRepeatMode(1);
        Animation animation5 = this.animaton;
        if (animation5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("animaton");
        }
        animation5.setRepeatCount(-1);
    }

    private final void startAnimation() {
        ImageView imageView = (ImageView) _$_findCachedViewById(C4429R.id.iv_circle);
        Animation animation = this.animaton;
        if (animation == null) {
            Intrinsics.throwUninitializedPropertyAccessException("animaton");
        }
        imageView.startAnimation(animation);
        ((LightDotView) _$_findCachedViewById(C4429R.id.light_dot_view)).startDotAnimation();
    }

    private final void stopAnimation() {
        ((ImageView) _$_findCachedViewById(C4429R.id.iv_circle)).clearAnimation();
        ((LightDotView) _$_findCachedViewById(C4429R.id.light_dot_view)).stopDotAnimation();
    }

    public final void showLayout(String target) {
        Intrinsics.checkParameterIsNotNull(target, "target");
        setVisibility(0);
        startAnimation();
        setTarget(target);
    }

    public final void setTarget(String target) {
        Intrinsics.checkParameterIsNotNull(target, "target");
        Pdlog.m3273d(this.TAG, "setTarget : target = " + target + "; ");
        ((TextView) _$_findCachedViewById(C4429R.id.tv_seat_no)).setTextSize(2, 180.0f);
        TextView tv_seat_no = (TextView) _$_findCachedViewById(C4429R.id.tv_seat_no);
        Intrinsics.checkExpressionValueIsNotNull(tv_seat_no, "tv_seat_no");
        tv_seat_no.setText(target);
    }

    public final void hideLayout() {
        setVisibility(8);
        stopAnimation();
    }
}
