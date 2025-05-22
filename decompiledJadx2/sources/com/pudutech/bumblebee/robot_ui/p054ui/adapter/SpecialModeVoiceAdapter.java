package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.bumblebee.presenter.robot_voices_task.AppointVoice;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.tts_sdk.utils.AudioTrackUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SpecialModeVoiceAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0002H\u0014R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\u0006R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SpecialModeVoiceAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SelectVoiceItem;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "kotlin.jvm.PlatformType", "getContext", "()Landroid/content/Context;", "setContext", "onTTsClickListener", "Landroid/view/View$OnClickListener;", "onVoiceClickListener", "convert", "", "helper", "item", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SpecialModeVoiceAdapter extends BaseQuickAdapter<SelectVoiceItem, BaseViewHolder> {
    private final String TAG;
    private Context context;
    private final View.OnClickListener onTTsClickListener;
    private final View.OnClickListener onVoiceClickListener;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[VoiceType.values().length];

        static {
            $EnumSwitchMapping$0[VoiceType.APPOINT_VOICE.ordinal()] = 1;
            $EnumSwitchMapping$0[VoiceType.TTS_VOICE.ordinal()] = 2;
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SpecialModeVoiceAdapter(Context context) {
        super(C4188R.layout.item_select_special_music);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = getClass().getSimpleName();
        this.onVoiceClickListener = new SpecialModeVoiceAdapter$onVoiceClickListener$1(this);
        this.onTTsClickListener = new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.SpecialModeVoiceAdapter$onTTsClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Object tag = it.getTag();
                if (tag == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.adapter.SelectVoiceItem");
                }
                final SelectVoiceItem selectVoiceItem = (SelectVoiceItem) tag;
                if (selectVoiceItem.isPlaying() && selectVoiceItem.getTtsVoice() != null) {
                    selectVoiceItem.setPlaying(false);
                    TtsVoiceHelper.INSTANCE.stopCruiseTts();
                    SpecialModeVoiceAdapter.this.notifyDataSetChanged();
                    return;
                }
                List<SelectVoiceItem> data = SpecialModeVoiceAdapter.this.getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "data");
                for (SelectVoiceItem selectVoiceItem2 : data) {
                    if (selectVoiceItem2.getVoiceType() == VoiceType.TTS_VOICE) {
                        selectVoiceItem2.setPlaying(false);
                    }
                }
                TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                TtsVoiceHelper.TtsConfigData ttsVoice = selectVoiceItem.getTtsVoice();
                if (ttsVoice == null) {
                    Intrinsics.throwNpe();
                }
                ttsVoiceHelper.playPcm(ttsVoice, new Function1<AudioTrackUtils.AudioPlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.SpecialModeVoiceAdapter$onTTsClickListener$1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(AudioTrackUtils.AudioPlayEvent audioPlayEvent) {
                        invoke2(audioPlayEvent);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(AudioTrackUtils.AudioPlayEvent it2) {
                        Intrinsics.checkParameterIsNotNull(it2, "it");
                        if (it2 == AudioTrackUtils.AudioPlayEvent.PLAYING) {
                            selectVoiceItem.setPlaying(true);
                            SpecialModeVoiceAdapter.this.notifyDataSetChanged();
                        } else if (it2 == AudioTrackUtils.AudioPlayEvent.STOP || it2 == AudioTrackUtils.AudioPlayEvent.COMPLETE) {
                            selectVoiceItem.setPlaying(false);
                            SpecialModeVoiceAdapter.this.notifyDataSetChanged();
                        }
                    }
                });
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, SelectVoiceItem item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        TextView textView = (TextView) helper.getView(C4188R.id.name_text);
        View icon = helper.getView(C4188R.id.select_ic);
        ImageView iconPlay = (ImageView) helper.getView(C4188R.id.play_btn_iv);
        int i = WhenMappings.$EnumSwitchMapping$0[item.getVoiceType().ordinal()];
        if (i == 1) {
            Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
            AppointVoice voice = item.getVoice();
            if (voice == null) {
                Intrinsics.throwNpe();
            }
            textView.setText(voice.getInfo());
            if (item.isSelect()) {
                Intrinsics.checkExpressionValueIsNotNull(icon, "icon");
                icon.setVisibility(0);
            } else {
                Intrinsics.checkExpressionValueIsNotNull(icon, "icon");
                icon.setVisibility(8);
            }
            Intrinsics.checkExpressionValueIsNotNull(iconPlay, "iconPlay");
            iconPlay.setTag(item);
            iconPlay.setOnClickListener(this.onVoiceClickListener);
            if (item.isPlaying()) {
                iconPlay.setImageResource(C4188R.drawable.icon_special_music_pause);
                return;
            } else {
                iconPlay.setImageResource(C4188R.drawable.icon_special_music_play);
                return;
            }
        }
        if (i != 2) {
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
        TtsVoiceHelper.TtsConfigData ttsVoice = item.getTtsVoice();
        if (ttsVoice == null) {
            Intrinsics.throwNpe();
        }
        textView.setText(ttsVoice.getName());
        if (item.isSelect()) {
            Intrinsics.checkExpressionValueIsNotNull(icon, "icon");
            icon.setVisibility(0);
        } else {
            Intrinsics.checkExpressionValueIsNotNull(icon, "icon");
            icon.setVisibility(8);
        }
        Intrinsics.checkExpressionValueIsNotNull(iconPlay, "iconPlay");
        iconPlay.setTag(item);
        iconPlay.setOnClickListener(this.onTTsClickListener);
        if (item.isPlaying()) {
            iconPlay.setImageResource(C4188R.drawable.icon_special_music_pause);
        } else {
            iconPlay.setImageResource(C4188R.drawable.icon_special_music_play);
        }
    }
}
