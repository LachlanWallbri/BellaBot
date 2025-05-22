package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import androidx.cardview.widget.CardView;
import com.airbnb.lottie.LottieAnimationView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.view.MarqueeTextView;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: CruiseStayTtsVoiceAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u00032\u0006\u00100\u001a\u00020\u0002H\u0014J\u0006\u00101\u001a\u00020.J\u000e\u00102\u001a\u00020.2\u0006\u00103\u001a\u00020\"J\u000e\u00104\u001a\u00020.2\u0006\u00103\u001a\u00020\"R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0006R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R&\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,¨\u00065"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/CruiseStayTtsVoiceAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "getContext", "()Landroid/content/Context;", "setContext", "deleteViewListener", "Landroid/view/View$OnClickListener;", "getDeleteViewListener", "()Landroid/view/View$OnClickListener;", "setDeleteViewListener", "(Landroid/view/View$OnClickListener;)V", ES6Iterator.VALUE_PROPERTY, "", "isEnable", "()Z", "setEnable", "(Z)V", "mViewAnmals", "", "Lcom/airbnb/lottie/LottieAnimationView;", "getMViewAnmals", "()Ljava/util/Map;", "setMViewAnmals", "(Ljava/util/Map;)V", "onChooseClickListener", "onClickListener", "previousLongClickView", "Landroid/view/View;", "getPreviousLongClickView", "()Landroid/view/View;", "setPreviousLongClickView", "(Landroid/view/View;)V", "type", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "getType", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "setType", "(Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;)V", "convert", "", "helper", "item", "dismissDeleteView", "onItemClicked", "rootView", "switchDeleteView", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CruiseStayTtsVoiceAdapter extends BaseQuickAdapter<TtsVoiceHelper.TtsConfigData, BaseViewHolder> {
    private final String TAG;
    private Context context;
    private View.OnClickListener deleteViewListener;
    private boolean isEnable;
    private Map<String, LottieAnimationView> mViewAnmals;
    private View.OnClickListener onChooseClickListener;
    private final View.OnClickListener onClickListener;
    private View previousLongClickView;
    private TtsVoiceHelper.TtsVoiceType type;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CruiseStayTtsVoiceAdapter(Context context) {
        super(C4188R.layout.item_select_cruise_stay_tts_voice);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = "TtsVoiceAdapter";
        this.type = TtsVoiceHelper.TtsVoiceType.CRUISE_STAY_TYPE;
        this.mViewAnmals = new LinkedHashMap();
        this.onClickListener = new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.CruiseStayTtsVoiceAdapter$onClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                String str;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Object tag = it.getTag();
                if (tag != null) {
                    TtsVoiceHelper.TtsConfigData ttsConfigData = (TtsVoiceHelper.TtsConfigData) tag;
                    str = CruiseStayTtsVoiceAdapter.this.TAG;
                    Pdlog.m3273d(str, "try play " + ttsConfigData);
                    TtsVoiceHelper.playPcm$default(TtsVoiceHelper.INSTANCE, ttsConfigData, null, 2, null);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper.TtsConfigData");
            }
        };
        this.onChooseClickListener = new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.CruiseStayTtsVoiceAdapter$onChooseClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Object tag = it.getTag();
                if (tag == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper.TtsConfigData");
                }
                TtsVoiceHelper.INSTANCE.changeChoose((TtsVoiceHelper.TtsConfigData) tag, CruiseStayTtsVoiceAdapter.this.getType());
                CruiseStayTtsVoiceAdapter.this.notifyDataSetChanged();
            }
        };
    }

    public final TtsVoiceHelper.TtsVoiceType getType() {
        return this.type;
    }

    public final void setType(TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "<set-?>");
        this.type = ttsVoiceType;
    }

    public final Map<String, LottieAnimationView> getMViewAnmals() {
        return this.mViewAnmals;
    }

    public final void setMViewAnmals(Map<String, LottieAnimationView> map) {
        Intrinsics.checkParameterIsNotNull(map, "<set-?>");
        this.mViewAnmals = map;
    }

    public final View getPreviousLongClickView() {
        return this.previousLongClickView;
    }

    public final void setPreviousLongClickView(View view) {
        this.previousLongClickView = view;
    }

    public final View.OnClickListener getDeleteViewListener() {
        return this.deleteViewListener;
    }

    public final void setDeleteViewListener(View.OnClickListener onClickListener) {
        this.deleteViewListener = onClickListener;
    }

    /* renamed from: isEnable, reason: from getter */
    public final boolean getIsEnable() {
        return this.isEnable;
    }

    public final void setEnable(boolean z) {
        this.isEnable = z;
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, TtsVoiceHelper.TtsConfigData item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        ImageView imageView = (ImageView) helper.getView(C4188R.id.try_play_tv);
        MarqueeTextView name = (MarqueeTextView) helper.getView(C4188R.id.name_text);
        CheckBox chooseIv = (CheckBox) helper.getView(C4188R.id.choose_iv);
        CardView deleteView = (CardView) helper.getView(C4188R.id.delete_card_view);
        LottieAnimationView animationView = (LottieAnimationView) helper.getView(C4188R.id.animation_view);
        Intrinsics.checkExpressionValueIsNotNull(name, "name");
        name.setText(item.getName());
        Intrinsics.checkExpressionValueIsNotNull(chooseIv, "chooseIv");
        chooseIv.setSelected(item.isSelect());
        chooseIv.setTag(item);
        Intrinsics.checkExpressionValueIsNotNull(deleteView, "deleteView");
        deleteView.setTag(item);
        chooseIv.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.CruiseStayTtsVoiceAdapter$convert$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View chooseView) {
                View.OnClickListener onClickListener;
                Intrinsics.checkParameterIsNotNull(chooseView, "chooseView");
                View previousLongClickView = CruiseStayTtsVoiceAdapter.this.getPreviousLongClickView();
                if (previousLongClickView != null) {
                    previousLongClickView.setVisibility(8);
                }
                onClickListener = CruiseStayTtsVoiceAdapter.this.onChooseClickListener;
                onClickListener.onClick(chooseView);
            }
        }, 3, null));
        helper.addOnClickListener(C4188R.id.try_play_tv);
        Intrinsics.checkExpressionValueIsNotNull(animationView, "animationView");
        animationView.setRepeatMode(1);
        animationView.setRepeatCount(-1);
        if (item.isPlay()) {
            imageView.setImageResource(C4188R.drawable.ic_play);
            animationView.setVisibility(0);
            animationView.playAnimation();
        } else {
            imageView.setImageResource(C4188R.drawable.ic_stop);
            animationView.setVisibility(8);
            animationView.cancelAnimation();
        }
        deleteView.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.CruiseStayTtsVoiceAdapter$convert$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View deleteView2) {
                Intrinsics.checkParameterIsNotNull(deleteView2, "deleteView");
                View previousLongClickView = CruiseStayTtsVoiceAdapter.this.getPreviousLongClickView();
                if (previousLongClickView != null) {
                    previousLongClickView.setVisibility(8);
                }
                TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                Object tag = deleteView2.getTag();
                if (tag != null) {
                    ttsVoiceHelper.deleteConfig((TtsVoiceHelper.TtsConfigData) tag, CruiseStayTtsVoiceAdapter.this.getType());
                    CruiseStayTtsVoiceAdapter.this.notifyDataSetChanged();
                    View.OnClickListener deleteViewListener = CruiseStayTtsVoiceAdapter.this.getDeleteViewListener();
                    if (deleteViewListener != null) {
                        deleteViewListener.onClick(deleteView2);
                        return;
                    }
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper.TtsConfigData");
            }
        }, 3, null));
        name.setEnabled(this.isEnable);
        chooseIv.setEnabled(this.isEnable);
    }

    public final void switchDeleteView(View rootView) {
        Intrinsics.checkParameterIsNotNull(rootView, "rootView");
        CardView deleteView = (CardView) rootView.findViewById(C4188R.id.delete_card_view);
        dismissDeleteView();
        Intrinsics.checkExpressionValueIsNotNull(deleteView, "deleteView");
        deleteView.setVisibility(0);
        this.previousLongClickView = deleteView;
    }

    public final void dismissDeleteView() {
        View view = this.previousLongClickView;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public final void onItemClicked(View rootView) {
        Intrinsics.checkParameterIsNotNull(rootView, "rootView");
        dismissDeleteView();
        ((CheckBox) rootView.findViewById(C4188R.id.choose_iv)).performClick();
    }
}
