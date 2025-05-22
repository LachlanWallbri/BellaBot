package com.pudutech.peanut.robot_ui.p063ui.adapter;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.peanut.robot_ui.C5508R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: TablesClassifyAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006R$\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/TablesClassifyAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/peanut/robot_ui/ui/adapter/TablesClassifyItem;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "setContext", ES6Iterator.VALUE_PROPERTY, "", "isDarkTheme", "()Z", "setDarkTheme", "(Z)V", "convert", "", "helper", "item", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class TablesClassifyAdapter extends BaseQuickAdapter<TablesClassifyItem, BaseViewHolder> {
    private Context context;
    private boolean isDarkTheme;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TablesClassifyAdapter(Context context) {
        super(C5508R.layout.item_table_classify);
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, TablesClassifyItem item) {
        TextView textView;
        TextView textView2;
        String str;
        TextView textView3;
        TextView textView4 = helper != null ? (TextView) helper.getView(C5508R.id.unselect_text) : null;
        RelativeLayout relativeLayout = helper != null ? (RelativeLayout) helper.getView(C5508R.id.rlBg) : null;
        if (helper != null) {
        }
        if (this.isDarkTheme) {
            if (relativeLayout != null) {
                relativeLayout.setBackground(this.context.getDrawable(C5508R.drawable.shape_radius_16_black39));
            }
            if (helper != null && (textView3 = (TextView) helper.getView(C5508R.id.unselect_text)) != null) {
                textView3.setTextColor(this.context.getColor(C5508R.color.white));
            }
        } else {
            if (relativeLayout != null) {
                relativeLayout.setBackground(this.context.getDrawable(C5508R.drawable.shape_radius_16_white));
            }
            if (item != null && item.isSelect()) {
                if (textView4 != null) {
                    textView4.setBackground(this.context.getDrawable(C5508R.drawable.shape_radius_8_blu));
                }
                if (helper != null && (textView2 = (TextView) helper.getView(C5508R.id.unselect_text)) != null) {
                    textView2.setTextColor(this.context.getColor(C5508R.color.white));
                }
            } else if (helper != null && (textView = (TextView) helper.getView(C5508R.id.unselect_text)) != null) {
                textView.setTextColor(this.context.getColor(C5508R.color.font_color_1));
            }
        }
        if (textView4 != null) {
            if (item == null || (str = item.getName()) == null) {
                str = "";
            }
            textView4.setText(str);
        }
        if (item != null && item.isSelect()) {
            if (textView4 != null) {
                textView4.setBackground(this.context.getDrawable(C5508R.drawable.shape_radius_8_blu));
            }
        } else if (this.isDarkTheme) {
            if (textView4 != null) {
                textView4.setBackground(this.context.getDrawable(C5508R.drawable.shape_radius_8_61666b));
            }
        } else if (textView4 != null) {
            textView4.setBackground(this.context.getDrawable(C5508R.drawable.shape_radius_8_cdd1d5));
        }
    }
}
