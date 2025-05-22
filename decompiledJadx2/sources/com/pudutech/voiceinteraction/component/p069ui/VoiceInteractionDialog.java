package com.pudutech.voiceinteraction.component.p069ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.pudutech.aivoice.component.utils.NavigationBarUtil;
import com.pudutech.base.Pdlog;
import com.pudutech.voiceinteraction.component.C5767R;
import com.pudutech.voiceinteraction.component.p069ui.VoiceInteractionDialog;
import com.pudutech.voiceinteraction.component.p069ui.widget.VolumeChangeView;
import com.pudutech.voiceinteraction.component.utils.DensityUtil;
import com.pudutech.widget.loading.CLoadingView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VoiceInteractionDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001@B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\b\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020$H\u0002J\u0012\u0010&\u001a\u00020$2\b\u0010'\u001a\u0004\u0018\u00010(H\u0014J\u000e\u0010)\u001a\u00020$2\u0006\u0010\u0004\u001a\u00020\u0005J\b\u0010*\u001a\u00020$H\u0002J\b\u0010+\u001a\u00020$H\u0002J\u000e\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020\u0019J\u000e\u0010.\u001a\u00020$2\u0006\u0010/\u001a\u00020\u0005J\u0010\u00100\u001a\u00020$2\b\u00101\u001a\u0004\u0018\u00010\nJ\u0010\u00102\u001a\u00020$2\b\b\u0001\u00103\u001a\u00020\u0005J\u0010\u00104\u001a\u00020$2\b\u00105\u001a\u0004\u0018\u00010\nJ\u000e\u00106\u001a\u00020$2\u0006\u00107\u001a\u00020\u0005J\b\u00108\u001a\u00020$H\u0016J\u000e\u00109\u001a\u00020$2\u0006\u0010:\u001a\u00020;J\u000e\u0010<\u001a\u00020$2\u0006\u0010:\u001a\u00020;J\u000e\u0010=\u001a\u00020$2\u0006\u0010:\u001a\u00020;J\u0010\u0010>\u001a\u00020$2\u0006\u0010?\u001a\u00020\u000fH\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\fX\u0082.¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006A"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/ui/VoiceInteractionDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "random", "", "(Landroid/content/Context;I)V", "theme", "(Landroid/content/Context;II)V", "TAG", "", "TEXT_EXAMPLES", "", "[Ljava/lang/String;", "closeBtn", "Landroid/view/View;", "exampleLayout", "Landroid/view/ViewGroup;", "exampleTextSwitcher", "Landroid/widget/TextSwitcher;", "exampleTextSwitcherAnimation", "Lcom/pudutech/voiceinteraction/component/ui/CTextSwitcherAnimation;", "loadingView", "Lcom/pudutech/widget/loading/CLoadingView;", "onCloseBtnClickListener", "Lcom/pudutech/voiceinteraction/component/ui/VoiceInteractionDialog$OnCloseBtnClickListener;", "reWakeupTipsTextView", "Landroid/widget/TextView;", "requestLayout", "requestTextView", "responseTextView", "titleText", "view", "volumeChangeView", "Lcom/pudutech/voiceinteraction/component/ui/widget/VolumeChangeView;", "initData", "", "initWidget", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "reset", "resetReWakeupTipsText", "setListeners", "setOnCloseBtnClickListener", "listener", "setReWakeupTipsTextCountdown", "countdown", "setRequestText", "requestText", "setRequestTextColor", TypedValues.Custom.S_COLOR, "setResponseText", "responseText", "setVolume", "volume", "show", "showHideLoadingView", "isShow", "", "showHideReWakeupTipsTextView", "showHideVolumeChangeView", "startFadeInAndTranslationAnim", "v", "OnCloseBtnClickListener", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VoiceInteractionDialog extends Dialog {
    private final String TAG;
    private String[] TEXT_EXAMPLES;
    private View closeBtn;
    private ViewGroup exampleLayout;
    private TextSwitcher exampleTextSwitcher;
    private CTextSwitcherAnimation exampleTextSwitcherAnimation;
    private CLoadingView loadingView;
    private OnCloseBtnClickListener onCloseBtnClickListener;
    private int random;
    private TextView reWakeupTipsTextView;
    private ViewGroup requestLayout;
    private TextView requestTextView;
    private TextView responseTextView;
    private TextView titleText;
    private View view;
    private VolumeChangeView volumeChangeView;

    /* compiled from: VoiceInteractionDialog.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/ui/VoiceInteractionDialog$OnCloseBtnClickListener;", "", "onCloseBtnClick", "", "v", "Landroid/view/View;", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface OnCloseBtnClickListener {
        void onCloseBtnClick(View v);
    }

    private final void resetReWakeupTipsText() {
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public VoiceInteractionDialog(Context context, int i) {
        this(context, C5767R.style.style_common_dialog, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VoiceInteractionDialog(Context context, int i, int i2) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "VoiceInteractionDialog";
        this.random = i2;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pdlog.m3273d(this.TAG, "onCreate : start");
        View inflate = LayoutInflater.from(getContext()).inflate(C5767R.layout.dialog_voice_interaction, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(cont…_voice_interaction, null)");
        this.view = inflate;
        initData();
        initWidget();
        setListeners();
        requestWindowFeature(1);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(17);
            window.setAttributes(attributes);
            View view = this.view;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            }
            setContentView(view);
        }
        Pdlog.m3273d(this.TAG, "onCreate : end");
    }

    private final void initData() {
        this.TEXT_EXAMPLES = new String[]{'\"' + getContext().getString(C5767R.string.pdStr14_2) + '\"', '\"' + getContext().getString(C5767R.string.pdStr14_3) + '\"', '\"' + getContext().getString(C5767R.string.pdStr14_4) + '\"'};
    }

    private final void initWidget() {
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        View findViewById = view.findViewById(C5767R.id.ts_example);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.ts_example)");
        this.exampleTextSwitcher = (TextSwitcher) findViewById;
        TextSwitcher textSwitcher = this.exampleTextSwitcher;
        if (textSwitcher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("exampleTextSwitcher");
        }
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() { // from class: com.pudutech.voiceinteraction.component.ui.VoiceInteractionDialog$initWidget$1
            @Override // android.widget.ViewSwitcher.ViewFactory
            public final TextView makeView() {
                TextView textView = new TextView(VoiceInteractionDialog.this.getContext());
                textView.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
                textView.setMaxLines(1);
                textView.setGravity(1);
                textView.setEllipsize(TextUtils.TruncateAt.END);
                textView.setTextColor(Color.parseColor("#ffffff"));
                textView.setTextSize(2, 25.0f);
                return textView;
            }
        });
        TextSwitcher textSwitcher2 = this.exampleTextSwitcher;
        if (textSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("exampleTextSwitcher");
        }
        String[] strArr = this.TEXT_EXAMPLES;
        if (strArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("TEXT_EXAMPLES");
        }
        this.exampleTextSwitcherAnimation = new CTextSwitcherAnimation(textSwitcher2, strArr);
        CTextSwitcherAnimation cTextSwitcherAnimation = this.exampleTextSwitcherAnimation;
        if (cTextSwitcherAnimation == null) {
            Intrinsics.throwUninitializedPropertyAccessException("exampleTextSwitcherAnimation");
        }
        cTextSwitcherAnimation.setDelayTime(1000);
        CTextSwitcherAnimation cTextSwitcherAnimation2 = this.exampleTextSwitcherAnimation;
        if (cTextSwitcherAnimation2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("exampleTextSwitcherAnimation");
        }
        cTextSwitcherAnimation2.setDuration(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        CTextSwitcherAnimation cTextSwitcherAnimation3 = this.exampleTextSwitcherAnimation;
        if (cTextSwitcherAnimation3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("exampleTextSwitcherAnimation");
        }
        cTextSwitcherAnimation3.create();
        View view2 = this.view;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        this.volumeChangeView = (VolumeChangeView) view2.findViewById(C5767R.id.vcv);
        View view3 = this.view;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        this.responseTextView = (TextView) view3.findViewById(C5767R.id.tv_response);
        View view4 = this.view;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        this.requestTextView = (TextView) view4.findViewById(C5767R.id.tv_request);
        View view5 = this.view;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        this.loadingView = (CLoadingView) view5.findViewById(C5767R.id.loading_view);
        View view6 = this.view;
        if (view6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        View findViewById2 = view6.findViewById(C5767R.id.btn_close);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById(R.id.btn_close)");
        this.closeBtn = findViewById2;
        View view7 = this.view;
        if (view7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        this.reWakeupTipsTextView = (TextView) view7.findViewById(C5767R.id.tv_re_wakeup_tips);
        showHideVolumeChangeView(true);
        showHideReWakeupTipsTextView(false);
        setRequestTextColor(Color.parseColor("#fffefe"));
        resetReWakeupTipsText();
        View view8 = this.view;
        if (view8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        View findViewById3 = view8.findViewById(C5767R.id.tv_title);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "view.findViewById(R.id.tv_title)");
        this.titleText = (TextView) findViewById3;
        View view9 = this.view;
        if (view9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        View findViewById4 = view9.findViewById(C5767R.id.layout_example);
        Intrinsics.checkExpressionValueIsNotNull(findViewById4, "view.findViewById(R.id.layout_example)");
        this.exampleLayout = (ViewGroup) findViewById4;
        View view10 = this.view;
        if (view10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        View findViewById5 = view10.findViewById(C5767R.id.layout_request);
        Intrinsics.checkExpressionValueIsNotNull(findViewById5, "view.findViewById(R.id.layout_request)");
        this.requestLayout = (ViewGroup) findViewById5;
    }

    private final void setListeners() {
        View view = this.closeBtn;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("closeBtn");
        }
        view.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.voiceinteraction.component.ui.VoiceInteractionDialog$setListeners$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                VoiceInteractionDialog.OnCloseBtnClickListener onCloseBtnClickListener;
                onCloseBtnClickListener = VoiceInteractionDialog.this.onCloseBtnClickListener;
                if (onCloseBtnClickListener != null) {
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    onCloseBtnClickListener.onCloseBtnClick(it);
                }
            }
        });
    }

    private final void startFadeInAndTranslationAnim(View v) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(v, "alpha", 0.0f, 1.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(v, "translationY", DensityUtil.INSTANCE.dp2Px(144.0f) * 1.0f, 0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2);
        animatorSet.setDuration(500L);
        animatorSet.setInterpolator(new OvershootInterpolator());
        animatorSet.setStartDelay(250L);
        animatorSet.addListener(new VoiceInteractionDialog$startFadeInAndTranslationAnim$1(v));
        animatorSet.start();
    }

    public final void setVolume(int volume) {
        VolumeChangeView volumeChangeView = this.volumeChangeView;
        if (volumeChangeView != null) {
            volumeChangeView.setVolume(volume);
        }
    }

    public final void showHideVolumeChangeView(boolean isShow) {
        VolumeChangeView volumeChangeView = this.volumeChangeView;
        if (volumeChangeView != null) {
            volumeChangeView.setVisibility(isShow ? 0 : 8);
        }
    }

    public final void setResponseText(String responseText) {
        TextView textView = this.responseTextView;
        if (textView != null) {
            textView.setText(responseText);
        }
    }

    public final void setRequestText(String requestText) {
        TextView textView = this.requestTextView;
        if (textView != null) {
            textView.setText(requestText);
        }
    }

    public final void setRequestTextColor(int color) {
        TextView textView = this.requestTextView;
        if (textView != null) {
            textView.setTextColor(color);
        }
    }

    public final void showHideLoadingView(boolean isShow) {
        if (isShow) {
            CLoadingView cLoadingView = this.loadingView;
            if (cLoadingView != null) {
                cLoadingView.show();
                return;
            }
            return;
        }
        CLoadingView cLoadingView2 = this.loadingView;
        if (cLoadingView2 != null) {
            cLoadingView2.hide();
        }
    }

    public final void showHideReWakeupTipsTextView(boolean isShow) {
        TextView textView = this.reWakeupTipsTextView;
        if (textView != null) {
            textView.setVisibility(isShow ? 0 : 8);
        }
    }

    public final void setReWakeupTipsTextCountdown(int countdown) {
        resetReWakeupTipsText();
        TextView textView = this.reWakeupTipsTextView;
        if (textView != null) {
            textView.setText(String.valueOf(textView != null ? textView.getText() : null) + '(' + (countdown / 1000) + ')');
        }
    }

    @Override // android.app.Dialog
    public void show() {
        NavigationBarUtil navigationBarUtil = NavigationBarUtil.INSTANCE;
        Window window = getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(window, "window!!");
        navigationBarUtil.focusNotAle(window);
        super.show();
        NavigationBarUtil navigationBarUtil2 = NavigationBarUtil.INSTANCE;
        Window window2 = getWindow();
        if (window2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(window2, "window!!");
        navigationBarUtil2.hideNavigationBar(window2);
        NavigationBarUtil navigationBarUtil3 = NavigationBarUtil.INSTANCE;
        Window window3 = getWindow();
        if (window3 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(window3, "window!!");
        navigationBarUtil3.clearFocusNotAle(window3);
        Window window4 = getWindow();
        if (window4 != null) {
            window4.setLayout(-1, -1);
        }
        reset(this.random);
    }

    public final void reset(int random) {
        setRequestTextColor(Color.parseColor("#fffefe"));
        TextView textView = this.responseTextView;
        if (textView != null) {
            textView.setText((CharSequence) null);
        }
        TextView textView2 = this.reWakeupTipsTextView;
        if (textView2 != null) {
            textView2.setText((CharSequence) null);
        }
        showHideLoadingView(false);
        showHideReWakeupTipsTextView(false);
        resetReWakeupTipsText();
    }

    public final void setOnCloseBtnClickListener(OnCloseBtnClickListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.onCloseBtnClickListener = listener;
    }
}
