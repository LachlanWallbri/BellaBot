package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TtsVoiceAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0002H\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0006R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/TtsVoiceAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "getContext", "()Landroid/content/Context;", "setContext", "onChooseClickListener", "Landroid/view/View$OnClickListener;", "onClickListener", "type", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "getType", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "setType", "(Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;)V", "convert", "", "helper", "item", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class TtsVoiceAdapter extends BaseQuickAdapter<TtsVoiceHelper.TtsConfigData, BaseViewHolder> {
    private final String TAG;
    private Context context;
    private View.OnClickListener onChooseClickListener;
    private final View.OnClickListener onClickListener;
    private TtsVoiceHelper.TtsVoiceType type;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TtsVoiceAdapter(Context context) {
        super(C4188R.layout.item_select_tts_voice);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = "TtsVoiceAdapter";
        this.type = TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE;
        this.onClickListener = new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.TtsVoiceAdapter$onClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                String str;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Object tag = it.getTag();
                if (tag != null) {
                    TtsVoiceHelper.TtsConfigData ttsConfigData = (TtsVoiceHelper.TtsConfigData) tag;
                    str = TtsVoiceAdapter.this.TAG;
                    Pdlog.m3273d(str, "try play " + ttsConfigData);
                    TtsVoiceHelper.playPcm$default(TtsVoiceHelper.INSTANCE, ttsConfigData, null, 2, null);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper.TtsConfigData");
            }
        };
        this.onChooseClickListener = new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.TtsVoiceAdapter$onChooseClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Object tag = it.getTag();
                if (tag == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper.TtsConfigData");
                }
                TtsVoiceHelper.INSTANCE.changeChoose((TtsVoiceHelper.TtsConfigData) tag, TtsVoiceAdapter.this.getType());
                TtsVoiceAdapter.this.notifyDataSetChanged();
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, TtsVoiceHelper.TtsConfigData item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        TextView tryPlay = (TextView) helper.getView(C4188R.id.try_play_tv);
        TextView name = (TextView) helper.getView(C4188R.id.name_text);
        ImageView chooseIv = (ImageView) helper.getView(C4188R.id.choose_iv);
        Intrinsics.checkExpressionValueIsNotNull(name, "name");
        name.setText(item.getName());
        Intrinsics.checkExpressionValueIsNotNull(chooseIv, "chooseIv");
        chooseIv.setSelected(item.isSelect());
        chooseIv.setTag(item);
        chooseIv.setOnClickListener(this.onChooseClickListener);
        Intrinsics.checkExpressionValueIsNotNull(tryPlay, "tryPlay");
        tryPlay.setTag(item);
        tryPlay.setOnClickListener(this.onClickListener);
    }
}
