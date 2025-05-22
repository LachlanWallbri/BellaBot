package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.SelectSpeakerAdapter;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelectSpeakerAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\f\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0017B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0002H\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006R(\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R(\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SelectSpeakerAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SelectSpeakerAdapter$SpeakerData;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "setContext", "playing", "Lkotlin/Function1;", "", "getPlaying", "()Lkotlin/jvm/functions/Function1;", "setPlaying", "(Lkotlin/jvm/functions/Function1;)V", "selectSpeaker", "getSelectSpeaker", "setSelectSpeaker", "convert", "helper", "item", "SpeakerData", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SelectSpeakerAdapter extends BaseQuickAdapter<SpeakerData, BaseViewHolder> {
    private Context context;
    private Function1<? super SpeakerData, Unit> playing;
    private Function1<? super SpeakerData, Unit> selectSpeaker;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectSpeakerAdapter(Context context) {
        super(C4188R.layout.item_select_speaker);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    public final Function1<SpeakerData, Unit> getPlaying() {
        return this.playing;
    }

    public final void setPlaying(Function1<? super SpeakerData, Unit> function1) {
        this.playing = function1;
    }

    public final Function1<SpeakerData, Unit> getSelectSpeaker() {
        return this.selectSpeaker;
    }

    public final void setSelectSpeaker(Function1<? super SpeakerData, Unit> function1) {
        this.selectSpeaker = function1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(final BaseViewHolder helper, final SpeakerData item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        TextView tryPlay = (TextView) helper.getView(C4188R.id.try_play_tv);
        TextView name = (TextView) helper.getView(C4188R.id.name_text);
        ImageView chooseIv = (ImageView) helper.getView(C4188R.id.choose_iv);
        Intrinsics.checkExpressionValueIsNotNull(name, "name");
        name.setText(item.getSpeakerName());
        Intrinsics.checkExpressionValueIsNotNull(chooseIv, "chooseIv");
        chooseIv.setSelected(item.isSelect());
        chooseIv.setTag(item);
        chooseIv.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.SelectSpeakerAdapter$convert$$inlined$let$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Function1<SelectSpeakerAdapter.SpeakerData, Unit> selectSpeaker = SelectSpeakerAdapter.this.getSelectSpeaker();
                if (selectSpeaker != null) {
                    selectSpeaker.invoke(item);
                }
            }
        }, 3, null));
        Intrinsics.checkExpressionValueIsNotNull(tryPlay, "tryPlay");
        tryPlay.setTag(item);
        tryPlay.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.SelectSpeakerAdapter$convert$$inlined$let$lambda$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Function1<SelectSpeakerAdapter.SpeakerData, Unit> playing = SelectSpeakerAdapter.this.getPlaying();
                if (playing != null) {
                    playing.invoke(item);
                }
            }
        }, 3, null));
    }

    /* compiled from: SelectSpeakerAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00062\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SelectSpeakerAdapter$SpeakerData;", "", "speaker", "", "speakerName", "isSelect", "", "loading", "(Ljava/lang/String;Ljava/lang/String;ZZ)V", "()Z", "setSelect", "(Z)V", "getLoading", "setLoading", "getSpeaker", "()Ljava/lang/String;", "getSpeakerName", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final /* data */ class SpeakerData {
        private boolean isSelect;
        private boolean loading;
        private final String speaker;
        private final String speakerName;

        public static /* synthetic */ SpeakerData copy$default(SpeakerData speakerData, String str, String str2, boolean z, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = speakerData.speaker;
            }
            if ((i & 2) != 0) {
                str2 = speakerData.speakerName;
            }
            if ((i & 4) != 0) {
                z = speakerData.isSelect;
            }
            if ((i & 8) != 0) {
                z2 = speakerData.loading;
            }
            return speakerData.copy(str, str2, z, z2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getSpeaker() {
            return this.speaker;
        }

        /* renamed from: component2, reason: from getter */
        public final String getSpeakerName() {
            return this.speakerName;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getIsSelect() {
            return this.isSelect;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getLoading() {
            return this.loading;
        }

        public final SpeakerData copy(String speaker, String speakerName, boolean isSelect, boolean loading) {
            Intrinsics.checkParameterIsNotNull(speaker, "speaker");
            Intrinsics.checkParameterIsNotNull(speakerName, "speakerName");
            return new SpeakerData(speaker, speakerName, isSelect, loading);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SpeakerData)) {
                return false;
            }
            SpeakerData speakerData = (SpeakerData) other;
            return Intrinsics.areEqual(this.speaker, speakerData.speaker) && Intrinsics.areEqual(this.speakerName, speakerData.speakerName) && this.isSelect == speakerData.isSelect && this.loading == speakerData.loading;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            String str = this.speaker;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.speakerName;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            boolean z = this.isSelect;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            int i2 = (hashCode2 + i) * 31;
            boolean z2 = this.loading;
            int i3 = z2;
            if (z2 != 0) {
                i3 = 1;
            }
            return i2 + i3;
        }

        public String toString() {
            return "SpeakerData(speaker=" + this.speaker + ", speakerName=" + this.speakerName + ", isSelect=" + this.isSelect + ", loading=" + this.loading + ")";
        }

        public SpeakerData(String speaker, String speakerName, boolean z, boolean z2) {
            Intrinsics.checkParameterIsNotNull(speaker, "speaker");
            Intrinsics.checkParameterIsNotNull(speakerName, "speakerName");
            this.speaker = speaker;
            this.speakerName = speakerName;
            this.isSelect = z;
            this.loading = z2;
        }

        public final boolean getLoading() {
            return this.loading;
        }

        public final String getSpeaker() {
            return this.speaker;
        }

        public final String getSpeakerName() {
            return this.speakerName;
        }

        public final boolean isSelect() {
            return this.isSelect;
        }

        public final void setLoading(boolean z) {
            this.loading = z;
        }

        public final void setSelect(boolean z) {
            this.isSelect = z;
        }
    }
}
