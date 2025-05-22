package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.view.View;
import android.widget.TextView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageInfo;
import com.pudutech.bumblebee.robot_ui.C4188R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VoiceModeTitleItemProvider.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/VoiceModeTitleItemProvider;", "Lcom/chad/library/adapter/base/provider/BaseItemProvider;", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/VoicePackageInfo;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "()V", "convert", "", "helper", "data", RequestParameters.POSITION, "", "layout", "viewType", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class VoiceModeTitleItemProvider extends BaseItemProvider<VoicePackageInfo, BaseViewHolder> {
    @Override // com.chad.library.adapter.base.provider.BaseItemProvider
    public int viewType() {
        return 1;
    }

    @Override // com.chad.library.adapter.base.provider.BaseItemProvider
    public int layout() {
        return C4188R.layout.item_voice_mode_title;
    }

    @Override // com.chad.library.adapter.base.provider.BaseItemProvider
    public void convert(BaseViewHolder helper, VoicePackageInfo data, int position) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(data, "data");
        View view = helper.getView(C4188R.id.voice_mode_title);
        Intrinsics.checkExpressionValueIsNotNull(view, "helper.getView<TextView>(R.id.voice_mode_title)");
        ((TextView) view).setText(data.getName());
    }
}
