package com.pudutech.module_robot_selfcheck.p058ui;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.pudutech.disinfect.baselib.ext.util.CommonExtKt;
import com.pudutech.disinfect.baselib.widget.CTextButton;
import com.pudutech.module_robot_selfcheck.C5365R;
import com.pudutech.module_robot_selfcheck.p058ui.ActiveStatusView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ActiveStatusView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\u001a\u001bB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\b\u0010\u0012\u001a\u00020\u0010H\u0002J\u0010\u0010\u0013\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u000eJ\b\u0010\u0015\u001a\u00020\u0010H\u0002J\b\u0010\u0016\u001a\u00020\u0010H\u0002J\u000e\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/ui/ActiveStatusView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "loadingAnimator", "Landroid/animation/ObjectAnimator;", "onBtnClickListener", "Lcom/pudutech/module_robot_selfcheck/ui/ActiveStatusView$OnBtnClickListener;", "hideAllWidget", "", "init", "setListeners", "setOnBtnClickListener", "listener", "startLoadingAnimator", "stopLoadingAnimator", "switchState", "state", "Lcom/pudutech/module_robot_selfcheck/ui/ActiveStatusView$Status;", "OnBtnClickListener", "Status", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ActiveStatusView extends ConstraintLayout {
    private HashMap _$_findViewCache;
    private ObjectAnimator loadingAnimator;
    private OnBtnClickListener onBtnClickListener;

    /* compiled from: ActiveStatusView.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/ui/ActiveStatusView$OnBtnClickListener;", "", "onActiveBtnClick", "", "v", "Landroid/view/View;", "onAutoActiveBtnClick", "onConfirmBtnClick", "onNetworkSettingBtnClick", "onRetryActiveCodeBtnClick", "onRetryAutoActiveBtnClick", "onRetryBtnClick", "onUseActiveCodeBtnClick", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public interface OnBtnClickListener {
        void onActiveBtnClick(View v);

        void onAutoActiveBtnClick(View v);

        void onConfirmBtnClick(View v);

        void onNetworkSettingBtnClick(View v);

        void onRetryActiveCodeBtnClick(View v);

        void onRetryAutoActiveBtnClick(View v);

        void onRetryBtnClick(View v);

        void onUseActiveCodeBtnClick(View v);
    }

    /* compiled from: ActiveStatusView.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/ui/ActiveStatusView$Status;", "", "(Ljava/lang/String;I)V", "CONNECT_NETWORK", "ACTIVATING", "ACTIVATION_SUCCEED", "ACTIVATION_FAILED_NETWORK_UNCONNECTED", "ACTIVATION_FAILED_UNBOUND", "ACTIVATION_FAILED_TIMEOUT", "ACTIVATION_FAILED_MANUAL", "ACTIVATION_SUCCEED_MANUAL", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum Status {
        CONNECT_NETWORK,
        ACTIVATING,
        ACTIVATION_SUCCEED,
        ACTIVATION_FAILED_NETWORK_UNCONNECTED,
        ACTIVATION_FAILED_UNBOUND,
        ACTIVATION_FAILED_TIMEOUT,
        ACTIVATION_FAILED_MANUAL,
        ACTIVATION_SUCCEED_MANUAL
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

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ActiveStatusView(Context context) {
        this(context, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ActiveStatusView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActiveStatusView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        init();
        setListeners();
    }

    private final void init() {
        LayoutInflater.from(getContext()).inflate(C5365R.layout.view_active_status, (ViewGroup) this, true);
        hideAllWidget();
    }

    private final void setListeners() {
        ((CTextButton) _$_findCachedViewById(C5365R.id.btn_active)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.module_robot_selfcheck.ui.ActiveStatusView$setListeners$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                ActiveStatusView.OnBtnClickListener onBtnClickListener;
                onBtnClickListener = ActiveStatusView.this.onBtnClickListener;
                if (onBtnClickListener != null) {
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    onBtnClickListener.onActiveBtnClick(it);
                }
            }
        });
        ((CTextButton) _$_findCachedViewById(C5365R.id.btn_retry)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.module_robot_selfcheck.ui.ActiveStatusView$setListeners$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                ActiveStatusView.OnBtnClickListener onBtnClickListener;
                onBtnClickListener = ActiveStatusView.this.onBtnClickListener;
                if (onBtnClickListener != null) {
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    onBtnClickListener.onRetryBtnClick(it);
                }
            }
        });
        ((CTextButton) _$_findCachedViewById(C5365R.id.btn_confirm)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.module_robot_selfcheck.ui.ActiveStatusView$setListeners$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                ActiveStatusView.OnBtnClickListener onBtnClickListener;
                onBtnClickListener = ActiveStatusView.this.onBtnClickListener;
                if (onBtnClickListener != null) {
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    onBtnClickListener.onConfirmBtnClick(it);
                }
            }
        });
        ((CTextButton) _$_findCachedViewById(C5365R.id.btn_use_active_code)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.module_robot_selfcheck.ui.ActiveStatusView$setListeners$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                ActiveStatusView.OnBtnClickListener onBtnClickListener;
                onBtnClickListener = ActiveStatusView.this.onBtnClickListener;
                if (onBtnClickListener != null) {
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    onBtnClickListener.onUseActiveCodeBtnClick(it);
                }
            }
        });
        ((CTextButton) _$_findCachedViewById(C5365R.id.btn_retry_active_code)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.module_robot_selfcheck.ui.ActiveStatusView$setListeners$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                ActiveStatusView.OnBtnClickListener onBtnClickListener;
                onBtnClickListener = ActiveStatusView.this.onBtnClickListener;
                if (onBtnClickListener != null) {
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    onBtnClickListener.onRetryActiveCodeBtnClick(it);
                }
            }
        });
        ((CTextButton) _$_findCachedViewById(C5365R.id.btn_auto_active)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.module_robot_selfcheck.ui.ActiveStatusView$setListeners$6
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                ActiveStatusView.OnBtnClickListener onBtnClickListener;
                onBtnClickListener = ActiveStatusView.this.onBtnClickListener;
                if (onBtnClickListener != null) {
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    onBtnClickListener.onAutoActiveBtnClick(it);
                }
            }
        });
        ((CTextButton) _$_findCachedViewById(C5365R.id.btn_retry_auto_active)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.module_robot_selfcheck.ui.ActiveStatusView$setListeners$7
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                ActiveStatusView.OnBtnClickListener onBtnClickListener;
                onBtnClickListener = ActiveStatusView.this.onBtnClickListener;
                if (onBtnClickListener != null) {
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    onBtnClickListener.onRetryAutoActiveBtnClick(it);
                }
            }
        });
        ((CTextButton) _$_findCachedViewById(C5365R.id.btn_network_setting)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.module_robot_selfcheck.ui.ActiveStatusView$setListeners$8
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                ActiveStatusView.OnBtnClickListener onBtnClickListener;
                onBtnClickListener = ActiveStatusView.this.onBtnClickListener;
                if (onBtnClickListener != null) {
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    onBtnClickListener.onNetworkSettingBtnClick(it);
                }
            }
        });
        ((CTextButton) _$_findCachedViewById(C5365R.id.btn_network_setting)).setOnCTouchListener(new Function2<View, MotionEvent, Unit>() { // from class: com.pudutech.module_robot_selfcheck.ui.ActiveStatusView$setListeners$9
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(View view, MotionEvent motionEvent) {
                invoke2(view, motionEvent);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View view, MotionEvent event) {
                Intrinsics.checkParameterIsNotNull(view, "<anonymous parameter 0>");
                Intrinsics.checkParameterIsNotNull(event, "event");
                int action = event.getAction();
                if (action == 0) {
                    ((CTextButton) ActiveStatusView.this._$_findCachedViewById(C5365R.id.btn_network_setting)).setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ActiveStatusView.this.getContext(), C5365R.drawable.ic_wifi_connected_636363), (Drawable) null, (Drawable) null, (Drawable) null);
                } else if (action == 1 || action == 3) {
                    ((CTextButton) ActiveStatusView.this._$_findCachedViewById(C5365R.id.btn_network_setting)).setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ActiveStatusView.this.getContext(), C5365R.drawable.ic_wifi_connected_a8a8a8), (Drawable) null, (Drawable) null, (Drawable) null);
                }
            }
        });
    }

    public final void switchState(Status state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        hideAllWidget();
        TextView tv_status = (TextView) _$_findCachedViewById(C5365R.id.tv_status);
        Intrinsics.checkExpressionValueIsNotNull(tv_status, "tv_status");
        tv_status.setVisibility(0);
        stopLoadingAnimator();
        switch (state) {
            case CONNECT_NETWORK:
                TextView tv_status2 = (TextView) _$_findCachedViewById(C5365R.id.tv_status);
                Intrinsics.checkExpressionValueIsNotNull(tv_status2, "tv_status");
                tv_status2.setText(CommonExtKt.getString(this, C5365R.string.pdStr1_15));
                CTextButton btn_network_setting = (CTextButton) _$_findCachedViewById(C5365R.id.btn_network_setting);
                Intrinsics.checkExpressionValueIsNotNull(btn_network_setting, "btn_network_setting");
                btn_network_setting.setVisibility(0);
                CTextButton btn_active = (CTextButton) _$_findCachedViewById(C5365R.id.btn_active);
                Intrinsics.checkExpressionValueIsNotNull(btn_active, "btn_active");
                btn_active.setVisibility(0);
                return;
            case ACTIVATING:
                TextView tv_status3 = (TextView) _$_findCachedViewById(C5365R.id.tv_status);
                Intrinsics.checkExpressionValueIsNotNull(tv_status3, "tv_status");
                tv_status3.setText(CommonExtKt.getString(this, C5365R.string.pdStr1_14));
                ImageView iv_loading = (ImageView) _$_findCachedViewById(C5365R.id.iv_loading);
                Intrinsics.checkExpressionValueIsNotNull(iv_loading, "iv_loading");
                iv_loading.setVisibility(0);
                startLoadingAnimator();
                return;
            case ACTIVATION_SUCCEED:
                TextView tv_status4 = (TextView) _$_findCachedViewById(C5365R.id.tv_status);
                Intrinsics.checkExpressionValueIsNotNull(tv_status4, "tv_status");
                tv_status4.setText(CommonExtKt.getString(this, C5365R.string.pdStr1_9));
                CTextButton btn_confirm = (CTextButton) _$_findCachedViewById(C5365R.id.btn_confirm);
                Intrinsics.checkExpressionValueIsNotNull(btn_confirm, "btn_confirm");
                btn_confirm.setVisibility(0);
                return;
            case ACTIVATION_FAILED_NETWORK_UNCONNECTED:
                TextView tv_status5 = (TextView) _$_findCachedViewById(C5365R.id.tv_status);
                Intrinsics.checkExpressionValueIsNotNull(tv_status5, "tv_status");
                tv_status5.setText(CommonExtKt.getString(this, C5365R.string.pdStr1_12));
                CTextButton btn_network_setting2 = (CTextButton) _$_findCachedViewById(C5365R.id.btn_network_setting);
                Intrinsics.checkExpressionValueIsNotNull(btn_network_setting2, "btn_network_setting");
                btn_network_setting2.setVisibility(0);
                CTextButton btn_retry = (CTextButton) _$_findCachedViewById(C5365R.id.btn_retry);
                Intrinsics.checkExpressionValueIsNotNull(btn_retry, "btn_retry");
                btn_retry.setVisibility(0);
                return;
            case ACTIVATION_FAILED_UNBOUND:
                TextView tv_status6 = (TextView) _$_findCachedViewById(C5365R.id.tv_status);
                Intrinsics.checkExpressionValueIsNotNull(tv_status6, "tv_status");
                tv_status6.setText(CommonExtKt.getString(this, C5365R.string.pdStr1_10));
                CTextButton btn_network_setting3 = (CTextButton) _$_findCachedViewById(C5365R.id.btn_network_setting);
                Intrinsics.checkExpressionValueIsNotNull(btn_network_setting3, "btn_network_setting");
                btn_network_setting3.setVisibility(0);
                CTextButton btn_retry2 = (CTextButton) _$_findCachedViewById(C5365R.id.btn_retry);
                Intrinsics.checkExpressionValueIsNotNull(btn_retry2, "btn_retry");
                btn_retry2.setVisibility(0);
                return;
            case ACTIVATION_FAILED_TIMEOUT:
                TextView tv_status7 = (TextView) _$_findCachedViewById(C5365R.id.tv_status);
                Intrinsics.checkExpressionValueIsNotNull(tv_status7, "tv_status");
                tv_status7.setText(CommonExtKt.getString(this, C5365R.string.pdStr1_13));
                CTextButton btn_network_setting4 = (CTextButton) _$_findCachedViewById(C5365R.id.btn_network_setting);
                Intrinsics.checkExpressionValueIsNotNull(btn_network_setting4, "btn_network_setting");
                btn_network_setting4.setVisibility(0);
                LinearLayout layout_use_active_code = (LinearLayout) _$_findCachedViewById(C5365R.id.layout_use_active_code);
                Intrinsics.checkExpressionValueIsNotNull(layout_use_active_code, "layout_use_active_code");
                layout_use_active_code.setVisibility(0);
                return;
            case ACTIVATION_FAILED_MANUAL:
                TextView tv_status8 = (TextView) _$_findCachedViewById(C5365R.id.tv_status);
                Intrinsics.checkExpressionValueIsNotNull(tv_status8, "tv_status");
                tv_status8.setText(CommonExtKt.getString(this, C5365R.string.pdStr1_23));
                CTextButton btn_network_setting5 = (CTextButton) _$_findCachedViewById(C5365R.id.btn_network_setting);
                Intrinsics.checkExpressionValueIsNotNull(btn_network_setting5, "btn_network_setting");
                btn_network_setting5.setVisibility(0);
                LinearLayout layout_auto_active = (LinearLayout) _$_findCachedViewById(C5365R.id.layout_auto_active);
                Intrinsics.checkExpressionValueIsNotNull(layout_auto_active, "layout_auto_active");
                layout_auto_active.setVisibility(0);
                return;
            case ACTIVATION_SUCCEED_MANUAL:
                TextView tv_status9 = (TextView) _$_findCachedViewById(C5365R.id.tv_status);
                Intrinsics.checkExpressionValueIsNotNull(tv_status9, "tv_status");
                tv_status9.setText(CommonExtKt.getString(this, C5365R.string.pdStr1_22));
                CTextButton btn_confirm2 = (CTextButton) _$_findCachedViewById(C5365R.id.btn_confirm);
                Intrinsics.checkExpressionValueIsNotNull(btn_confirm2, "btn_confirm");
                btn_confirm2.setVisibility(0);
                return;
            default:
                return;
        }
    }

    private final void hideAllWidget() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            Intrinsics.checkExpressionValueIsNotNull(childAt, "getChildAt(i)");
            childAt.setVisibility(8);
        }
    }

    private final void startLoadingAnimator() {
        stopLoadingAnimator();
        this.loadingAnimator = ObjectAnimator.ofFloat((ImageView) _$_findCachedViewById(C5365R.id.iv_loading), "rotation", 0.0f, 360.0f);
        ObjectAnimator objectAnimator = this.loadingAnimator;
        if (objectAnimator != null) {
            objectAnimator.setRepeatCount(-1);
            objectAnimator.setRepeatMode(1);
            objectAnimator.setDuration(2000L);
            objectAnimator.setInterpolator(new LinearInterpolator());
            objectAnimator.start();
        }
    }

    private final void stopLoadingAnimator() {
        ObjectAnimator objectAnimator = this.loadingAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        this.loadingAnimator = (ObjectAnimator) null;
    }

    public final void setOnBtnClickListener(OnBtnClickListener listener) {
        this.onBtnClickListener = listener;
    }
}
