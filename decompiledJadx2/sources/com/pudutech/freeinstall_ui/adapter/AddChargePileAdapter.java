package com.pudutech.freeinstall_ui.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.freeinstall_ui.AppContext;
import com.pudutech.module_freeinstall_ui.C5362R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddChargePileAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0011\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u0002H\u0014RL\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010RL\u0010\u0011\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010RL\u0010\u0014\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010RL\u0010\u0017\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000e\"\u0004\b\u0019\u0010\u0010¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/adapter/AddChargePileAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/freeinstall_ui/adapter/ChargeListItem;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "()V", "onDeleteClickListener", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "tableListItem", "", "pos", "", "getOnDeleteClickListener", "()Lkotlin/jvm/functions/Function2;", "setOnDeleteClickListener", "(Lkotlin/jvm/functions/Function2;)V", "onDeleteSureClickListener", "getOnDeleteSureClickListener", "setOnDeleteSureClickListener", "onItemClickListener", "getOnItemClickListener", "setOnItemClickListener", "onRenameClickListener", "getOnRenameClickListener", "setOnRenameClickListener", "convert", "holder", "item", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class AddChargePileAdapter extends BaseQuickAdapter<ChargeListItem, BaseViewHolder> {
    private Function2<? super ChargeListItem, ? super Integer, Unit> onDeleteClickListener;
    private Function2<? super ChargeListItem, ? super Integer, Unit> onDeleteSureClickListener;
    private Function2<? super ChargeListItem, ? super Integer, Unit> onItemClickListener;
    private Function2<? super ChargeListItem, ? super Integer, Unit> onRenameClickListener;

    public AddChargePileAdapter() {
        super(C5362R.layout.add_table_item);
    }

    public final Function2<ChargeListItem, Integer, Unit> getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public final void setOnItemClickListener(Function2<? super ChargeListItem, ? super Integer, Unit> function2) {
        this.onItemClickListener = function2;
    }

    public final Function2<ChargeListItem, Integer, Unit> getOnDeleteClickListener() {
        return this.onDeleteClickListener;
    }

    public final void setOnDeleteClickListener(Function2<? super ChargeListItem, ? super Integer, Unit> function2) {
        this.onDeleteClickListener = function2;
    }

    public final Function2<ChargeListItem, Integer, Unit> getOnDeleteSureClickListener() {
        return this.onDeleteSureClickListener;
    }

    public final void setOnDeleteSureClickListener(Function2<? super ChargeListItem, ? super Integer, Unit> function2) {
        this.onDeleteSureClickListener = function2;
    }

    public final Function2<ChargeListItem, Integer, Unit> getOnRenameClickListener() {
        return this.onRenameClickListener;
    }

    public final void setOnRenameClickListener(Function2<? super ChargeListItem, ? super Integer, Unit> function2) {
        this.onRenameClickListener = function2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(final BaseViewHolder holder, final ChargeListItem item) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        Intrinsics.checkParameterIsNotNull(item, "item");
        TextView tvRename = (TextView) holder.getView(C5362R.id.tv_rename);
        ImageView ivDelete = (ImageView) holder.getView(C5362R.id.iv_delete);
        ImageView ivDeleteSure = (ImageView) holder.getView(C5362R.id.iv_delete_sure);
        TextView tvTableName = (TextView) holder.getView(C5362R.id.tv_table_name);
        Intrinsics.checkExpressionValueIsNotNull(tvTableName, "tvTableName");
        tvTableName.setText(item.getDockerResult().getName());
        if (item.isSelect()) {
            tvTableName.setTextColor(AppContext.INSTANCE.getContext().getColor(C5362R.color.color_0072FF));
            Intrinsics.checkExpressionValueIsNotNull(tvRename, "tvRename");
            tvRename.setVisibility(0);
            tvTableName.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            tvTableName.setHorizontallyScrolling(true);
            tvTableName.setSelected(true);
        } else {
            tvTableName.setTextColor(AppContext.INSTANCE.getContext().getColor(C5362R.color.white));
            Intrinsics.checkExpressionValueIsNotNull(tvRename, "tvRename");
            tvRename.setVisibility(8);
            tvTableName.setEllipsize(TextUtils.TruncateAt.END);
        }
        if (item.isDelete()) {
            Intrinsics.checkExpressionValueIsNotNull(ivDeleteSure, "ivDeleteSure");
            ivDeleteSure.setVisibility(0);
            Intrinsics.checkExpressionValueIsNotNull(ivDelete, "ivDelete");
            ivDelete.setVisibility(8);
        } else {
            Intrinsics.checkExpressionValueIsNotNull(ivDeleteSure, "ivDeleteSure");
            ivDeleteSure.setVisibility(8);
            Intrinsics.checkExpressionValueIsNotNull(ivDelete, "ivDelete");
            ivDelete.setVisibility(0);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.adapter.AddChargePileAdapter$convert$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function2<ChargeListItem, Integer, Unit> onItemClickListener = AddChargePileAdapter.this.getOnItemClickListener();
                if (onItemClickListener != null) {
                    onItemClickListener.invoke(item, Integer.valueOf(holder.getAdapterPosition()));
                }
            }
        });
        tvRename.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.adapter.AddChargePileAdapter$convert$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function2<ChargeListItem, Integer, Unit> onRenameClickListener = AddChargePileAdapter.this.getOnRenameClickListener();
                if (onRenameClickListener != null) {
                    onRenameClickListener.invoke(item, Integer.valueOf(holder.getAdapterPosition()));
                }
            }
        });
        ivDelete.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.adapter.AddChargePileAdapter$convert$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function2<ChargeListItem, Integer, Unit> onDeleteClickListener = AddChargePileAdapter.this.getOnDeleteClickListener();
                if (onDeleteClickListener != null) {
                    onDeleteClickListener.invoke(item, Integer.valueOf(holder.getAdapterPosition()));
                }
            }
        });
        ivDeleteSure.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.adapter.AddChargePileAdapter$convert$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function2<ChargeListItem, Integer, Unit> onDeleteSureClickListener = AddChargePileAdapter.this.getOnDeleteSureClickListener();
                if (onDeleteSureClickListener != null) {
                    onDeleteSureClickListener.invoke(item, Integer.valueOf(holder.getAdapterPosition()));
                }
            }
        });
    }
}
