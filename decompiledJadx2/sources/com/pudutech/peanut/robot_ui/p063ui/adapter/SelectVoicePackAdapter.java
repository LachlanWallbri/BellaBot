package com.pudutech.peanut.robot_ui.p063ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.robot.module.voice.pkg.VoicePackageInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelectVoicePackAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0002H\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/SelectVoicePackAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/robot/module/voice/pkg/VoicePackageInfo;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "setContext", "onBtnClickListener", "Landroid/view/View$OnClickListener;", "getOnBtnClickListener", "()Landroid/view/View$OnClickListener;", "setOnBtnClickListener", "(Landroid/view/View$OnClickListener;)V", "convert", "", "helper", "item", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SelectVoicePackAdapter extends BaseQuickAdapter<VoicePackageInfo, BaseViewHolder> {
    private Context context;
    private View.OnClickListener onBtnClickListener;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectVoicePackAdapter(Context context) {
        super(C5508R.layout.item_select_voice_pack);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    public final View.OnClickListener getOnBtnClickListener() {
        return this.onBtnClickListener;
    }

    public final void setOnBtnClickListener(View.OnClickListener onClickListener) {
        this.onBtnClickListener = onClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, VoicePackageInfo item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        TextView textView = (TextView) helper.getView(C5508R.id.name_text);
        View icon = helper.getView(C5508R.id.select_ic);
        RelativeLayout btnLayout = (RelativeLayout) helper.getView(C5508R.id.btn_layout);
        TextView btnTv = (TextView) helper.getView(C5508R.id.btn_tv);
        Intrinsics.checkExpressionValueIsNotNull(btnLayout, "btnLayout");
        btnLayout.setTag(item);
        btnLayout.setOnClickListener(this.onBtnClickListener);
        if (!item.getIsExist()) {
            btnLayout.setVisibility(0);
            textView.setTextColor(this.context.getColor(C5508R.color.font_disable_color));
            btnTv.setTextColor(this.context.getColor(C5508R.color.theme_main_color));
            Intrinsics.checkExpressionValueIsNotNull(btnTv, "btnTv");
            btnTv.setText(this.context.getString(C5508R.string.pdStr7_77));
            btnTv.setBackground(this.context.getDrawable(C5508R.drawable.shape_setting_down_bg));
        } else {
            textView.setTextColor(this.context.getColor(C5508R.color.font_color_1));
            if (item.getNewVersionAvailable()) {
                btnLayout.setVisibility(0);
                Intrinsics.checkExpressionValueIsNotNull(btnTv, "btnTv");
                btnTv.setText(this.context.getString(C5508R.string.pdStr7_87));
                btnTv.setTextColor(this.context.getColor(C5508R.color.theme_green_color));
                btnTv.setBackground(this.context.getDrawable(C5508R.drawable.shape_setting_update_bg));
            } else {
                btnLayout.setVisibility(8);
            }
        }
        if (item.getId() == -1) {
            Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
            textView.setText(this.context.getString(C5508R.string.pdStr7_98));
        } else {
            Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
            textView.setText(String.valueOf(item.getName()));
        }
        if (item.getSelected()) {
            Intrinsics.checkExpressionValueIsNotNull(icon, "icon");
            icon.setVisibility(0);
        } else {
            Intrinsics.checkExpressionValueIsNotNull(icon, "icon");
            icon.setVisibility(8);
        }
    }
}
