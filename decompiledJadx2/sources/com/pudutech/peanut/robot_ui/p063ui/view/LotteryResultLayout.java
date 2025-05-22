package com.pudutech.peanut.robot_ui.p063ui.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.listener.OnLazyClickListener;
import com.pudutech.peanut.robot_ui.p063ui.view.LotteryResultLayout;
import com.pudutech.peanut.robot_ui.util.UiUtils;
import com.warkiz.widget.SizeUtils;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: LotteryResultLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 &2\u00020\u0001:\u0002&'B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001cH\u0002J\u000e\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\fJ\u0016\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\tJ\b\u0010$\u001a\u00020\u001cH\u0002J\b\u0010%\u001a\u00020\u001cH\u0002R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/view/LotteryResultLayout;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "kotlin.jvm.PlatformType", "destinationId", "getDestinationId", "()Ljava/lang/String;", "setDestinationId", "(Ljava/lang/String;)V", "hideListener", "Lcom/pudutech/peanut/robot_ui/ui/view/LotteryResultLayout$OnLayoutHideListener;", "getHideListener", "()Lcom/pudutech/peanut/robot_ui/ui/view/LotteryResultLayout$OnLayoutHideListener;", "setHideListener", "(Lcom/pudutech/peanut/robot_ui/ui/view/LotteryResultLayout$OnLayoutHideListener;)V", "uiHandler", "Landroid/os/Handler;", "hideView", "", "initView", "setDestination", "destination", "showLotteryResult", "winPrize", "", "prizeLevel", "stopNoPrizeAnim", "stopPrizeAnim", "Companion", "OnLayoutHideListener", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LotteryResultLayout extends RelativeLayout {
    public static final int PRIZE_LEVEL_FIRST = 1;
    public static final int PRIZE_LEVEL_SECOND = 2;
    public static final int PRIZE_LEVEL_THIRD = 3;
    public static final int PRIZE_LEVEL_TOP = 0;
    private final String TAG;
    private HashMap _$_findViewCache;
    private String destinationId;
    private OnLayoutHideListener hideListener;
    private final Handler uiHandler;

    /* compiled from: LotteryResultLayout.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/view/LotteryResultLayout$OnLayoutHideListener;", "", "onLayoutHide", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public interface OnLayoutHideListener {
        void onLayoutHide();
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

    public final OnLayoutHideListener getHideListener() {
        return this.hideListener;
    }

    public final void setHideListener(OnLayoutHideListener onLayoutHideListener) {
        this.hideListener = onLayoutHideListener;
    }

    public final String getDestinationId() {
        return this.destinationId;
    }

    public final void setDestinationId(String str) {
        this.destinationId = str;
    }

    public LotteryResultLayout(Context context) {
        super(context);
        this.TAG = getClass().getSimpleName();
        this.uiHandler = new Handler(Looper.getMainLooper());
        initView();
    }

    public LotteryResultLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = getClass().getSimpleName();
        this.uiHandler = new Handler(Looper.getMainLooper());
        initView();
    }

    public LotteryResultLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = getClass().getSimpleName();
        this.uiHandler = new Handler(Looper.getMainLooper());
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
                layoutInflater.inflate(C5508R.layout.layout_lottery_result_container, this);
            }
        }
        ((Button) _$_findCachedViewById(C5508R.id.btn_shoot_prize)).setOnClickListener(new OnLazyClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.LotteryResultLayout$initView$1
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyClickListener
            public void onSingleClick() {
                LotteryResultLayout.this.hideView();
                LotteryResultLayout.OnLayoutHideListener hideListener = LotteryResultLayout.this.getHideListener();
                if (hideListener != null) {
                    hideListener.onLayoutHide();
                }
            }
        });
    }

    public final void showLotteryResult(boolean winPrize, int prizeLevel) {
        String string;
        stopPrizeAnim();
        stopNoPrizeAnim();
        this.uiHandler.removeCallbacksAndMessages(null);
        setVisibility(0);
        if (winPrize) {
            View layout_no_prize = _$_findCachedViewById(C5508R.id.layout_no_prize);
            Intrinsics.checkExpressionValueIsNotNull(layout_no_prize, "layout_no_prize");
            layout_no_prize.setVisibility(8);
            View layout_prize = _$_findCachedViewById(C5508R.id.layout_prize);
            Intrinsics.checkExpressionValueIsNotNull(layout_prize, "layout_prize");
            layout_prize.setVisibility(0);
            TextView lottery_desk_number_prize = (TextView) _$_findCachedViewById(C5508R.id.lottery_desk_number_prize);
            Intrinsics.checkExpressionValueIsNotNull(lottery_desk_number_prize, "lottery_desk_number_prize");
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string2 = getContext().getString(C5508R.string.pdStr7_106);
            Intrinsics.checkExpressionValueIsNotNull(string2, "context.getString(R.string.pdStr7_106)");
            Object[] objArr = {this.destinationId};
            String format = String.format(string2, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            lottery_desk_number_prize.setText(format);
            ((LottieAnimationView) _$_findCachedViewById(C5508R.id.prize_animation)).playAnimation();
            if (prizeLevel == 0) {
                string = getContext().getString(C5508R.string.pdStr7_102);
                Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.pdStr7_102)");
            } else if (prizeLevel == 1) {
                string = getContext().getString(C5508R.string.pdStr7_103);
                Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.pdStr7_103)");
            } else if (prizeLevel == 2) {
                string = getContext().getString(C5508R.string.pdStr7_104);
                Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.pdStr7_104)");
            } else if (prizeLevel != 3) {
                string = "";
            } else {
                string = getContext().getString(C5508R.string.pdStr7_105);
                Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.pdStr7_105)");
            }
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String string3 = getContext().getString(C5508R.string.pdStr7_108);
            Intrinsics.checkExpressionValueIsNotNull(string3, "context.getString(R.string.pdStr7_108)");
            Object[] objArr2 = {string};
            String format2 = String.format(string3, Arrays.copyOf(objArr2, objArr2.length));
            Intrinsics.checkExpressionValueIsNotNull(format2, "java.lang.String.format(format, *args)");
            TextView prize_description_prize = (TextView) _$_findCachedViewById(C5508R.id.prize_description_prize);
            Intrinsics.checkExpressionValueIsNotNull(prize_description_prize, "prize_description_prize");
            prize_description_prize.setText(format2);
            UiUtils.adjustTvTextSize((TextView) _$_findCachedViewById(C5508R.id.prize_description_prize), getWidth() - 150, format2);
            Context context = getContext();
            Context context2 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context2, "context");
            int dp2px = SizeUtils.dp2px(context, context2.getResources().getDimension(C5508R.dimen.lottery_result_btn_width));
            Button button = (Button) _$_findCachedViewById(C5508R.id.btn_shoot_prize);
            Button btn_shoot_prize = (Button) _$_findCachedViewById(C5508R.id.btn_shoot_prize);
            Intrinsics.checkExpressionValueIsNotNull(btn_shoot_prize, "btn_shoot_prize");
            CharSequence text = btn_shoot_prize.getText();
            UiUtils.adjustTvTextSize(button, dp2px, text != null ? text.toString() : null);
            return;
        }
        View layout_prize2 = _$_findCachedViewById(C5508R.id.layout_prize);
        Intrinsics.checkExpressionValueIsNotNull(layout_prize2, "layout_prize");
        layout_prize2.setVisibility(8);
        View layout_no_prize2 = _$_findCachedViewById(C5508R.id.layout_no_prize);
        Intrinsics.checkExpressionValueIsNotNull(layout_no_prize2, "layout_no_prize");
        layout_no_prize2.setVisibility(0);
        TextView lottery_desk_number_no_prize = (TextView) _$_findCachedViewById(C5508R.id.lottery_desk_number_no_prize);
        Intrinsics.checkExpressionValueIsNotNull(lottery_desk_number_no_prize, "lottery_desk_number_no_prize");
        StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
        String string4 = getContext().getString(C5508R.string.pdStr7_106);
        Intrinsics.checkExpressionValueIsNotNull(string4, "context.getString(R.string.pdStr7_106)");
        Object[] objArr3 = {this.destinationId};
        String format3 = String.format(string4, Arrays.copyOf(objArr3, objArr3.length));
        Intrinsics.checkExpressionValueIsNotNull(format3, "java.lang.String.format(format, *args)");
        lottery_desk_number_no_prize.setText(format3);
        ((LottieAnimationView) _$_findCachedViewById(C5508R.id.no_prize_animation)).playAnimation();
        this.uiHandler.postDelayed(new Runnable() { // from class: com.pudutech.peanut.robot_ui.ui.view.LotteryResultLayout$showLotteryResult$1
            @Override // java.lang.Runnable
            public final void run() {
                LotteryResultLayout.this.hideView();
                LotteryResultLayout.OnLayoutHideListener hideListener = LotteryResultLayout.this.getHideListener();
                if (hideListener != null) {
                    hideListener.onLayoutHide();
                }
            }
        }, SolicitService.CAMERA_OPEN_TIME_OUT);
    }

    public final void setDestination(String destination) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        this.destinationId = destination;
    }

    private final void stopNoPrizeAnim() {
        LottieAnimationView no_prize_animation = (LottieAnimationView) _$_findCachedViewById(C5508R.id.no_prize_animation);
        Intrinsics.checkExpressionValueIsNotNull(no_prize_animation, "no_prize_animation");
        if (no_prize_animation.isAnimating()) {
            ((LottieAnimationView) _$_findCachedViewById(C5508R.id.no_prize_animation)).cancelAnimation();
        }
    }

    private final void stopPrizeAnim() {
        LottieAnimationView prize_animation = (LottieAnimationView) _$_findCachedViewById(C5508R.id.prize_animation);
        Intrinsics.checkExpressionValueIsNotNull(prize_animation, "prize_animation");
        if (prize_animation.isAnimating()) {
            ((LottieAnimationView) _$_findCachedViewById(C5508R.id.prize_animation)).cancelAnimation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideView() {
        stopNoPrizeAnim();
        stopPrizeAnim();
        this.uiHandler.removeCallbacksAndMessages(null);
        setVisibility(8);
    }
}
