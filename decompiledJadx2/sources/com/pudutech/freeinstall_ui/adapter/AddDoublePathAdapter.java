package com.pudutech.freeinstall_ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.module_freeinstall_ui.C5362R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddDoublePathAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0002H\u0014RL\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/adapter/AddDoublePathAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/freeinstall_ui/adapter/DoublePathListItem;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "()V", "onItemClickListener", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "item", "", "pos", "", "getOnItemClickListener", "()Lkotlin/jvm/functions/Function2;", "setOnItemClickListener", "(Lkotlin/jvm/functions/Function2;)V", "convert", "holder", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class AddDoublePathAdapter extends BaseQuickAdapter<DoublePathListItem, BaseViewHolder> {
    private Function2<? super DoublePathListItem, ? super Integer, Unit> onItemClickListener;

    public AddDoublePathAdapter() {
        super(C5362R.layout.add_double_path_item);
    }

    public final Function2<DoublePathListItem, Integer, Unit> getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public final void setOnItemClickListener(Function2<? super DoublePathListItem, ? super Integer, Unit> function2) {
        this.onItemClickListener = function2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(final BaseViewHolder holder, final DoublePathListItem item) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        Intrinsics.checkParameterIsNotNull(item, "item");
        TextView tvName = (TextView) holder.getView(C5362R.id.tv_name);
        ImageView imageView = (ImageView) holder.getView(C5362R.id.iv_select);
        Intrinsics.checkExpressionValueIsNotNull(tvName, "tvName");
        tvName.setText(item.getName());
        if (item.isSelect()) {
            imageView.setImageResource(C5362R.drawable.icon_muti_select);
        } else {
            imageView.setImageResource(C5362R.drawable.icon_muti_unselect);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.adapter.AddDoublePathAdapter$convert$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function2<DoublePathListItem, Integer, Unit> onItemClickListener = AddDoublePathAdapter.this.getOnItemClickListener();
                if (onItemClickListener != null) {
                    onItemClickListener.invoke(item, Integer.valueOf(holder.getAdapterPosition()));
                }
            }
        });
    }
}
