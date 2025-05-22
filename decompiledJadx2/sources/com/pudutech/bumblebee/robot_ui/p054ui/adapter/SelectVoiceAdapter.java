package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.bumblebee.presenter.delivery_task.DeliverSelectVoiceContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import kotlin.Metadata;

/* compiled from: SelectVoiceAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0014¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SelectVoiceAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliverSelectVoiceContract$SelectVoiceInfo;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "()V", "convert", "", "helper", "item", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SelectVoiceAdapter extends BaseQuickAdapter<DeliverSelectVoiceContract.SelectVoiceInfo, BaseViewHolder> {
    public SelectVoiceAdapter() {
        super(C4188R.layout.item_select_voice);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, DeliverSelectVoiceContract.SelectVoiceInfo item) {
        if (item != null) {
            TextView textView = helper != null ? (TextView) helper.getView(C4188R.id.voice_name_tv) : null;
            ImageView imageView = helper != null ? (ImageView) helper.getView(C4188R.id.check_iv) : null;
            ConstraintLayout constraintLayout = helper != null ? (ConstraintLayout) helper.getView(C4188R.id.item_select_cl) : null;
            if (textView != null) {
                textView.setText(item.getVoiceName());
            }
            if (item.getCheckState()) {
                if (imageView != null) {
                    imageView.setImageResource(C4188R.drawable.ic_radio_seletcted);
                }
                if (constraintLayout != null) {
                    constraintLayout.setBackgroundResource(C4188R.drawable.shape_radius_8_solid_while_stroke_2_0072ff);
                    return;
                }
                return;
            }
            if (imageView != null) {
                imageView.setImageResource(C4188R.drawable.ic_radio_unselected);
            }
            if (constraintLayout != null) {
                constraintLayout.setBackgroundResource(C4188R.drawable.shape_radius_8_white);
            }
        }
    }
}
