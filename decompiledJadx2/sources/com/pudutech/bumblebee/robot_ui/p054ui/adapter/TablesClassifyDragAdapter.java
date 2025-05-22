package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.content.Context;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.bumblebee.robot_ui.C4188R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: TablesClassifyDragAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00032\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002H\u0014J\b\u0010\u001a\u001a\u00020\u0011H\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006R$\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/TablesClassifyDragAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/TablesClassifyItem;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "setContext", ES6Iterator.VALUE_PROPERTY, "", "isDarkTheme", "()Z", "setDarkTheme", "(Z)V", "width", "", "getWidth", "()I", "setWidth", "(I)V", "convert", "", "helper", "item", "getTextColor", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class TablesClassifyDragAdapter extends BaseQuickAdapter<TablesClassifyItem, BaseViewHolder> {
    private Context context;
    private boolean isDarkTheme;
    private int width;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TablesClassifyDragAdapter(Context context) {
        super(C4188R.layout.item_table_classify_drag);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    /* renamed from: isDarkTheme, reason: from getter */
    public final boolean getIsDarkTheme() {
        return this.isDarkTheme;
    }

    public final void setDarkTheme(boolean z) {
        this.isDarkTheme = z;
        notifyDataSetChanged();
    }

    public final int getWidth() {
        return this.width;
    }

    public final void setWidth(int i) {
        this.width = i;
    }

    private final int getTextColor() {
        return this.isDarkTheme ? C4188R.color.white : C4188R.color.selector_table_classify_text_color;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, TablesClassifyItem item) {
        TextView textView = helper != null ? (TextView) helper.getView(C4188R.id.text_table_classify) : null;
        if (this.isDarkTheme) {
            if (textView != null) {
                textView.setBackgroundResource(C4188R.drawable.selector_table_classify_item_dark);
            }
        } else if (textView != null) {
            textView.setBackgroundResource(C4188R.drawable.selector_table_classify_item);
        }
        if (textView != null) {
            textView.setTextColor(this.context.getColorStateList(getTextColor()));
        }
        if (textView != null) {
            textView.setText(item != null ? item.getName() : null);
        }
        if (textView != null) {
            textView.setSelected(item != null && item.isSelect());
        }
        if (textView != null) {
            int i = this.width;
            if (i > 280) {
                i = 280;
            } else if (i < 156) {
                i = 156;
            }
            textView.setWidth(i);
        }
    }
}
