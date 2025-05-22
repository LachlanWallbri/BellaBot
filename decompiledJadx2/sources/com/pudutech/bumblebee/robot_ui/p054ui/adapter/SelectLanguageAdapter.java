package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.bumblebee.robot_ui.C4188R;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelectLanguageAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0002H\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SelectLanguageAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SelectLanguageItem;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "setContext", "convert", "", "helper", "item", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SelectLanguageAdapter extends BaseQuickAdapter<SelectLanguageItem, BaseViewHolder> {
    private Context context;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectLanguageAdapter(Context context) {
        super(C4188R.layout.item_select_position);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, SelectLanguageItem item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        View view = helper.itemView;
        if (view == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout");
        }
        final RelativeLayout relativeLayout = (RelativeLayout) view;
        relativeLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.SelectLanguageAdapter$convert$1$1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent event) {
                Intrinsics.checkExpressionValueIsNotNull(event, "event");
                if (event.getAction() == 0) {
                    relativeLayout.setBackgroundResource(C4188R.color.item_click);
                }
                if (event.getAction() != 1 && event.getAction() != 3) {
                    return false;
                }
                relativeLayout.setBackgroundResource(C4188R.color.white);
                return false;
            }
        });
        TextView textView = (TextView) helper.getView(C4188R.id.name_text);
        View icon = helper.getView(C4188R.id.select_ic);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
        textView.setText(String.valueOf(item.getOp().getDisplayName()));
        if (item.isSelect()) {
            Intrinsics.checkExpressionValueIsNotNull(icon, "icon");
            icon.setVisibility(0);
        } else {
            Intrinsics.checkExpressionValueIsNotNull(icon, "icon");
            icon.setVisibility(8);
        }
    }
}
