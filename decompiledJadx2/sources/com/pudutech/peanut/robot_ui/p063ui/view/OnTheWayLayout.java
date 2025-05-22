package com.pudutech.peanut.robot_ui.p063ui.view;

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
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.util.UiUtils;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: OnTheWayLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0006\u0010\u0019\u001a\u00020\u0016J\b\u0010\u001a\u001a\u00020\u0016H\u0002J\u000e\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\fJ\u000e\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\tJ\u000e\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\fJ\b\u0010 \u001a\u00020\u0016H\u0002J\b\u0010!\u001a\u00020\u0016H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\tX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\tX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/view/OnTheWayLayout;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "TYPE_CALL", "getTYPE_CALL", "()I", "TYPE_NOMAL", "getTYPE_NOMAL", "animaton", "Landroid/view/animation/Animation;", "type", "genAnimation", "", TypedValues.Transition.S_DURATION, "", "hideLayout", "initView", "setTarget", TypedValues.Attributes.S_TARGET, "setType", "int", "showLayout", "startAnimation", "stopAnimation", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class OnTheWayLayout extends RelativeLayout {
    private final String TAG;
    private final int TYPE_CALL;
    private final int TYPE_NOMAL;
    private HashMap _$_findViewCache;
    private Animation animaton;
    private int type;

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

    public final int getTYPE_NOMAL() {
        return this.TYPE_NOMAL;
    }

    public final int getTYPE_CALL() {
        return this.TYPE_CALL;
    }

    public OnTheWayLayout(Context context) {
        super(context);
        this.TAG = "OnTheWayLayout";
        this.TYPE_CALL = 1;
        this.type = this.TYPE_NOMAL;
        initView();
    }

    public OnTheWayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "OnTheWayLayout";
        this.TYPE_CALL = 1;
        this.type = this.TYPE_NOMAL;
        initView();
    }

    public OnTheWayLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = "OnTheWayLayout";
        this.TYPE_CALL = 1;
        this.type = this.TYPE_NOMAL;
        initView();
    }

    private final void initView() {
        Context context = getContext();
        if (context != null) {
            Object systemService = context.getSystemService("layout_inflater");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
            }
            LayoutInflater layoutInflater = (LayoutInflater) systemService;
            if (layoutInflater != null) {
                layoutInflater.inflate(C5508R.layout.layout_on_the_way, this);
            }
        }
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
        int i = this.type;
        if (i == this.TYPE_NOMAL) {
            ImageView imageView = (ImageView) _$_findCachedViewById(C5508R.id.iv_circle);
            Animation animation = this.animaton;
            if (animation == null) {
                Intrinsics.throwUninitializedPropertyAccessException("animaton");
            }
            imageView.startAnimation(animation);
            ((LightDotView) _$_findCachedViewById(C5508R.id.light_dot_view)).startDotAnimation();
            return;
        }
        if (i == this.TYPE_CALL) {
            ImageView imageView2 = (ImageView) _$_findCachedViewById(C5508R.id.calling_iv);
            Animation animation2 = this.animaton;
            if (animation2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("animaton");
            }
            imageView2.startAnimation(animation2);
        }
    }

    private final void stopAnimation() {
        ((ImageView) _$_findCachedViewById(C5508R.id.iv_circle)).clearAnimation();
        ((LightDotView) _$_findCachedViewById(C5508R.id.light_dot_view)).stopDotAnimation();
        ((ImageView) _$_findCachedViewById(C5508R.id.calling_iv)).clearAnimation();
    }

    public final void setType(int r9) {
        this.type = r9;
        if (r9 == this.TYPE_CALL) {
            ImageView calling_iv = (ImageView) _$_findCachedViewById(C5508R.id.calling_iv);
            Intrinsics.checkExpressionValueIsNotNull(calling_iv, "calling_iv");
            calling_iv.setVisibility(0);
            TextView call_tv_seat_no = (TextView) _$_findCachedViewById(C5508R.id.call_tv_seat_no);
            Intrinsics.checkExpressionValueIsNotNull(call_tv_seat_no, "call_tv_seat_no");
            call_tv_seat_no.setVisibility(0);
            ImageView iv_circle = (ImageView) _$_findCachedViewById(C5508R.id.iv_circle);
            Intrinsics.checkExpressionValueIsNotNull(iv_circle, "iv_circle");
            iv_circle.setVisibility(8);
            TextView tv_seat_no = (TextView) _$_findCachedViewById(C5508R.id.tv_seat_no);
            Intrinsics.checkExpressionValueIsNotNull(tv_seat_no, "tv_seat_no");
            tv_seat_no.setVisibility(8);
            LightDotView light_dot_view = (LightDotView) _$_findCachedViewById(C5508R.id.light_dot_view);
            Intrinsics.checkExpressionValueIsNotNull(light_dot_view, "light_dot_view");
            light_dot_view.setVisibility(8);
            genAnimation(2000L);
            return;
        }
        if (r9 == this.TYPE_NOMAL) {
            ImageView calling_iv2 = (ImageView) _$_findCachedViewById(C5508R.id.calling_iv);
            Intrinsics.checkExpressionValueIsNotNull(calling_iv2, "calling_iv");
            calling_iv2.setVisibility(8);
            TextView call_tv_seat_no2 = (TextView) _$_findCachedViewById(C5508R.id.call_tv_seat_no);
            Intrinsics.checkExpressionValueIsNotNull(call_tv_seat_no2, "call_tv_seat_no");
            call_tv_seat_no2.setVisibility(8);
            ImageView iv_circle2 = (ImageView) _$_findCachedViewById(C5508R.id.iv_circle);
            Intrinsics.checkExpressionValueIsNotNull(iv_circle2, "iv_circle");
            iv_circle2.setVisibility(0);
            TextView tv_seat_no2 = (TextView) _$_findCachedViewById(C5508R.id.tv_seat_no);
            Intrinsics.checkExpressionValueIsNotNull(tv_seat_no2, "tv_seat_no");
            tv_seat_no2.setVisibility(0);
            LightDotView light_dot_view2 = (LightDotView) _$_findCachedViewById(C5508R.id.light_dot_view);
            Intrinsics.checkExpressionValueIsNotNull(light_dot_view2, "light_dot_view");
            light_dot_view2.setVisibility(0);
            genAnimation(10000L);
        }
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
        int i = this.type;
        if (i == this.TYPE_CALL) {
            ((TextView) _$_findCachedViewById(C5508R.id.call_tv_seat_no)).setTextSize(2, 86.0f);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getContext().getString(C5508R.string.pdStr7_146);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.pdStr7_146)");
            Object[] objArr = {target};
            String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            TextView call_tv_seat_no = (TextView) _$_findCachedViewById(C5508R.id.call_tv_seat_no);
            Intrinsics.checkExpressionValueIsNotNull(call_tv_seat_no, "call_tv_seat_no");
            call_tv_seat_no.setText(format);
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            UiUtils.adjustTvTextSize((TextView) _$_findCachedViewById(C5508R.id.call_tv_seat_no), ((int) context.getResources().getDimension(C5508R.dimen.delivery_on_the_way_target_t_w)) - 100, format);
            return;
        }
        if (i == this.TYPE_NOMAL) {
            ((TextView) _$_findCachedViewById(C5508R.id.tv_seat_no)).setTextSize(2, 180.0f);
            Context context2 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context2, "context");
            float dimension = context2.getResources().getDimension(C5508R.dimen.delivery_on_the_way_target_t_w);
            TextView tv_seat_no = (TextView) _$_findCachedViewById(C5508R.id.tv_seat_no);
            Intrinsics.checkExpressionValueIsNotNull(tv_seat_no, "tv_seat_no");
            tv_seat_no.setText(target);
            UiUtils.adjustTvTextSize((TextView) _$_findCachedViewById(C5508R.id.tv_seat_no), ((int) dimension) - 100, target);
        }
    }

    public final void hideLayout() {
        setVisibility(8);
        stopAnimation();
    }
}
