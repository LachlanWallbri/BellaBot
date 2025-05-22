package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.view.View;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VoiceFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0010\u0010\u0002\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, m3961d2 = {"<anonymous>", "", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "view", "Landroid/view/View;", RequestParameters.POSITION, "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class VoiceFragment$initCustomVoiceView$6 extends Lambda implements Function3<BaseQuickAdapter<?, ?>, View, Integer, Boolean> {
    final /* synthetic */ VoiceFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VoiceFragment$initCustomVoiceView$6(VoiceFragment voiceFragment) {
        super(3);
        this.this$0 = voiceFragment;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Boolean invoke(BaseQuickAdapter<?, ?> baseQuickAdapter, View view, Integer num) {
        return Boolean.valueOf(invoke(baseQuickAdapter, view, num.intValue()));
    }

    public final boolean invoke(final BaseQuickAdapter<?, ?> baseQuickAdapter, View view, final int i) {
        if (view == null) {
            return true;
        }
        this.this$0.showDeletePopupWindow(view, new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$initCustomVoiceView$6$$special$$inlined$let$lambda$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                List data;
                TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                BaseQuickAdapter baseQuickAdapter2 = baseQuickAdapter;
                Object obj = (baseQuickAdapter2 == null || (data = baseQuickAdapter2.getData()) == null) ? null : data.get(i);
                if (obj != null) {
                    ttsVoiceType = VoiceFragment$initCustomVoiceView$6.this.this$0.customVoiceType;
                    ttsVoiceHelper.deleteConfig((TtsVoiceHelper.TtsConfigData) obj, ttsVoiceType);
                    VoiceFragment.access$getTtsVoiceAdapter$p(VoiceFragment$initCustomVoiceView$6.this.this$0).notifyDataSetChanged();
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper.TtsConfigData");
            }
        });
        return true;
    }
}
