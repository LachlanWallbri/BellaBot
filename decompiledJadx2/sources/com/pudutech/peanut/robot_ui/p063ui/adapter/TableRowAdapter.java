package com.pudutech.peanut.robot_ui.p063ui.adapter;

import android.content.Context;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.disinfect.baselib.network.response.TableGroupBean;
import com.pudutech.peanut.robot_ui.C5508R;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TableRowAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u0002H\u0014J\u0018\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/TableRowAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/disinfect/baselib/network/response/TableGroupBean;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "setContext", "convert", "", "helper", "item", "setItemStatus", "mView", "Landroid/widget/TextView;", "isDown", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class TableRowAdapter extends BaseQuickAdapter<TableGroupBean, BaseViewHolder> {
    private Context context;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TableRowAdapter(Context context) {
        super(C5508R.layout.item_table_row);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    private final void setItemStatus(TextView mView, boolean isDown) {
        if (isDown) {
            mView.setTextColor(this.context.getResources().getColor(C5508R.color.white));
            mView.setBackground(this.context.getResources().getDrawable(C5508R.drawable.shape_radius_8_blu));
        } else {
            mView.setTextColor(this.context.getResources().getColor(C5508R.color.C11));
            mView.setBackground(this.context.getResources().getDrawable(C5508R.drawable.shape_radius_8_cdd1d5));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, TableGroupBean item) {
        TextView textView = helper != null ? (TextView) helper.getView(C5508R.id.tvContent) : null;
        if (textView == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        textView.setText(item != null ? item.getAlias() : null);
        if (item != null && item.isSelect()) {
            setItemStatus(textView, true);
        } else {
            setItemStatus(textView, false);
        }
    }
}
