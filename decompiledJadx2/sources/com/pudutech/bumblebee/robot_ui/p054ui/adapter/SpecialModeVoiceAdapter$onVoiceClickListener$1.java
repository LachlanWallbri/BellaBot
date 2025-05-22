package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.content.Context;
import android.view.View;
import com.pudutech.bumblebee.presenter.robot_voices.Listener;
import com.pudutech.bumblebee.presenter.robot_voices.PlayEvent;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceProperty;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.presenter.robot_voices_task.AppointVoice;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.SpecialModeVoiceAdapter$onVoiceClickListener$1;
import com.pudutech.resources.voice.VoiceItem;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.AsyncKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SpecialModeVoiceAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SpecialModeVoiceAdapter$onVoiceClickListener$1 implements View.OnClickListener {
    final /* synthetic */ SpecialModeVoiceAdapter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SpecialModeVoiceAdapter$onVoiceClickListener$1(SpecialModeVoiceAdapter specialModeVoiceAdapter) {
        this.this$0 = specialModeVoiceAdapter;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View it) {
        Intrinsics.checkExpressionValueIsNotNull(it, "it");
        Object tag = it.getTag();
        if (tag == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.adapter.SelectVoiceItem");
        }
        SelectVoiceItem selectVoiceItem = (SelectVoiceItem) tag;
        if (selectVoiceItem.isPlaying() && selectVoiceItem.getVoiceTask() != null) {
            selectVoiceItem.setPlaying(false);
            VoicePlayer voicePlayer = VoicePlayer.INSTANCE;
            VoiceTask voiceTask = selectVoiceItem.getVoiceTask();
            if (voiceTask == null) {
                Intrinsics.throwNpe();
            }
            voicePlayer.stop(voiceTask);
            this.this$0.notifyDataSetChanged();
            return;
        }
        if (selectVoiceItem.getVoiceTask() == null) {
            VoiceProperty[] voicePropertyArr = new VoiceProperty[1];
            AppointVoice voice = selectVoiceItem.getVoice();
            if (voice == null) {
                Intrinsics.throwNpe();
            }
            VoiceItem voiceItem = voice.getVoiceItem();
            AppointVoice voice2 = selectVoiceItem.getVoice();
            if (voice2 == null) {
                Intrinsics.throwNpe();
            }
            voicePropertyArr[0] = new VoiceProperty(-1L, voiceItem, voice2.getIndex());
            selectVoiceItem.setVoiceTask(new VoiceTask(-1L, voicePropertyArr).withListener(new C43081(selectVoiceItem)));
        }
        VoicePlayer voicePlayer2 = VoicePlayer.INSTANCE;
        VoiceTask voiceTask2 = selectVoiceItem.getVoiceTask();
        if (voiceTask2 == null) {
            Intrinsics.throwNpe();
        }
        voicePlayer2.play(voiceTask2);
    }

    /* compiled from: SpecialModeVoiceAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/adapter/SpecialModeVoiceAdapter$onVoiceClickListener$1$1", "Lcom/pudutech/bumblebee/presenter/robot_voices/Listener;", "onStateChange", "", "event", "Lcom/pudutech/bumblebee/presenter/robot_voices/PlayEvent;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.bumblebee.robot_ui.ui.adapter.SpecialModeVoiceAdapter$onVoiceClickListener$1$1 */
    /* loaded from: classes3.dex */
    public static final class C43081 implements Listener {
        final /* synthetic */ SelectVoiceItem $item;

        C43081(SelectVoiceItem selectVoiceItem) {
            this.$item = selectVoiceItem;
        }

        @Override // com.pudutech.bumblebee.presenter.robot_voices.Listener
        public void onStateChange(final PlayEvent event) {
            Intrinsics.checkParameterIsNotNull(event, "event");
            AsyncKt.runOnUiThread(SpecialModeVoiceAdapter$onVoiceClickListener$1.this.this$0.getContext(), new Function1<Context, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.SpecialModeVoiceAdapter$onVoiceClickListener$1$1$onStateChange$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Context context) {
                    invoke2(context);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Context receiver) {
                    Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                    if (event == PlayEvent.PLAYING) {
                        SpecialModeVoiceAdapter$onVoiceClickListener$1.C43081.this.$item.setPlaying(true);
                        SpecialModeVoiceAdapter$onVoiceClickListener$1.this.this$0.notifyDataSetChanged();
                    } else if (event == PlayEvent.STOP || event == PlayEvent.COMPLETION_ONCE) {
                        SpecialModeVoiceAdapter$onVoiceClickListener$1.C43081.this.$item.setPlaying(false);
                        SpecialModeVoiceAdapter$onVoiceClickListener$1.this.this$0.notifyDataSetChanged();
                    }
                }
            });
        }
    }
}
