package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.bumblebee.presenter.information_system_task.InformationSystemContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.view.VerticalCenterSpan;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ArriveDishInfoAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0002H\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/ArriveDishInfoAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/bumblebee/presenter/information_system_task/InformationSystemContract$OrderInfo;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "setContext", "isBirthdayTheme", "", "()Z", "setBirthdayTheme", "(Z)V", "convert", "", "helper", "item", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class ArriveDishInfoAdapter extends BaseQuickAdapter<InformationSystemContract.OrderInfo, BaseViewHolder> {
    private Context context;
    private boolean isBirthdayTheme;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ArriveDishInfoAdapter(Context context) {
        super(C4188R.layout.item_arrive_dish_info);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    /* renamed from: isBirthdayTheme, reason: from getter */
    public final boolean getIsBirthdayTheme() {
        return this.isBirthdayTheme;
    }

    public final void setBirthdayTheme(boolean z) {
        this.isBirthdayTheme = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, InformationSystemContract.OrderInfo item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        TextView textView = (TextView) helper.getView(C4188R.id.name_text);
        if (this.isBirthdayTheme) {
            textView.setBackgroundColor(this.context.getColor(C4188R.color.deliver_arrive_birthday_dish_item_bg));
            textView.setTextColor(-1);
        } else {
            textView.setBackgroundColor(this.context.getColor(C4188R.color.deliver_arrive_dish_item_bg));
            textView.setTextColor(this.context.getColor(C4188R.color.font_color_1));
        }
        String dishName = item.getDishName();
        if (item.getDishesCount() > 0) {
            int length = dishName.length();
            String str = (dishName + " x") + ((int) item.getDishesCount());
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
            spannableStringBuilder.setSpan(new VerticalCenterSpan(24.0f), length, str.length(), 34);
            Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
            textView.setText(spannableStringBuilder);
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
        textView.setText(dishName);
    }
}
