package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.app.Application;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.pudutech.bumblebee.business.base.BaseApplication;
import com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageInfo;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VoiceModeItemProvider.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J \u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\u0014\u001a\u00020\nH\u0016J\b\u0010\u0015\u001a\u00020\nH\u0016RL\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/VoiceModeItemProvider;", "Lcom/chad/library/adapter/base/provider/BaseItemProvider;", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/VoicePackageInfo;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "()V", "onUpdateClick", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "data", "", RequestParameters.POSITION, "", "getOnUpdateClick", "()Lkotlin/jvm/functions/Function2;", "setOnUpdateClick", "(Lkotlin/jvm/functions/Function2;)V", "convert", "helper", "it", "layout", "viewType", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class VoiceModeItemProvider extends BaseItemProvider<VoicePackageInfo, BaseViewHolder> {
    private Function2<? super VoicePackageInfo, ? super Integer, Unit> onUpdateClick;

    @Override // com.chad.library.adapter.base.provider.BaseItemProvider
    public int viewType() {
        return 0;
    }

    public final Function2<VoicePackageInfo, Integer, Unit> getOnUpdateClick() {
        return this.onUpdateClick;
    }

    public final void setOnUpdateClick(Function2<? super VoicePackageInfo, ? super Integer, Unit> function2) {
        this.onUpdateClick = function2;
    }

    @Override // com.chad.library.adapter.base.provider.BaseItemProvider
    public int layout() {
        return C4188R.layout.item_voice_mode;
    }

    @Override // com.chad.library.adapter.base.provider.BaseItemProvider
    public void convert(BaseViewHolder helper, final VoicePackageInfo it, final int position) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(it, "it");
        ImageView imageView = (ImageView) helper.getView(C4188R.id.voice_mode_select);
        TextView titleTv = (TextView) helper.getView(C4188R.id.voice_mode_content);
        TextView updateTv = (TextView) helper.getView(C4188R.id.voice_mode_load);
        updateTv.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.VoiceModeItemProvider$convert$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function2<VoicePackageInfo, Integer, Unit> onUpdateClick = VoiceModeItemProvider.this.getOnUpdateClick();
                if (onUpdateClick != null) {
                    onUpdateClick.invoke(it, Integer.valueOf(position));
                }
            }
        });
        Application companion = BaseApplication.INSTANCE.getInstance();
        if (!it.getIsExist()) {
            Intrinsics.checkExpressionValueIsNotNull(updateTv, "updateTv");
            ViewExtKt.show(updateTv);
            updateTv.setTextColor(companion.getColor(C4188R.color.c_32C271));
            updateTv.setText(companion.getString(C4188R.string.pdStr7_77));
        } else if (it.getNewVersionAvailable()) {
            Intrinsics.checkExpressionValueIsNotNull(updateTv, "updateTv");
            ViewExtKt.show(updateTv);
            updateTv.setText(companion.getString(C4188R.string.pdStr7_87));
            updateTv.setTextColor(companion.getColor(C4188R.color.c_0066FF));
        } else {
            Intrinsics.checkExpressionValueIsNotNull(updateTv, "updateTv");
            ViewExtKt.gone(updateTv);
        }
        Intrinsics.checkExpressionValueIsNotNull(titleTv, "titleTv");
        titleTv.setText(String.valueOf(it.getName()));
        if (it.getSelected()) {
            imageView.setImageResource(C4188R.drawable.ic_single_checked);
        } else {
            imageView.setImageResource(C4188R.drawable.ic_single_uncheck);
        }
    }
}
