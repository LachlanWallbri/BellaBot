package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.bumblebee.robot_ui.C4188R;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: MapSelectAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0002H\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/MapSelectAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/MapSelectItem;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "setContext", "convert", "", "helper", "item", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class MapSelectAdapter extends BaseQuickAdapter<MapSelectItem, BaseViewHolder> {
    private Context context;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapSelectAdapter(Context context) {
        super(C4188R.layout.item_multi_map);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, MapSelectItem item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        View view = helper.itemView;
        if (view == null) {
            throw new TypeCastException("null cannot be cast to non-null type androidx.cardview.widget.CardView");
        }
        CardView cardView = (CardView) view;
        TextView textView = (TextView) helper.getView(C4188R.id.name_text);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
        textView.setText(String.valueOf(item.getMapName()));
        if (item.isSelect()) {
            cardView.setCardBackgroundColor(this.context.getColor(C4188R.color.theme_main_color));
            Sdk27PropertiesKt.setTextColor(textView, -1);
        } else {
            cardView.setCardBackgroundColor(this.context.getColor(C4188R.color.item_cruise_way_bg));
            Sdk27PropertiesKt.setTextColor(textView, this.context.getColor(C4188R.color.font_color_1));
        }
    }
}
