package com.pudutech.freeinstall_ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.disinfect.baselib.util.DensityUtil;
import com.pudutech.freeinstall_ui.AppContext;
import com.pudutech.freeinstall_ui.utils.ExtandsKt;
import com.pudutech.module_freeinstall_ui.C5362R;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BusinessSetAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0002H\u0014RL\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/adapter/BusinessSetAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/freeinstall_ui/adapter/BusinessSetItem;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "()V", "onItemClickListener", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "businessSetItem", "", "pos", "", "getOnItemClickListener", "()Lkotlin/jvm/functions/Function2;", "setOnItemClickListener", "(Lkotlin/jvm/functions/Function2;)V", "convert", "holder", "item", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class BusinessSetAdapter extends BaseQuickAdapter<BusinessSetItem, BaseViewHolder> {
    private Function2<? super BusinessSetItem, ? super Integer, Unit> onItemClickListener;

    public BusinessSetAdapter() {
        super(C5362R.layout.business_list_item);
    }

    public final Function2<BusinessSetItem, Integer, Unit> getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public final void setOnItemClickListener(Function2<? super BusinessSetItem, ? super Integer, Unit> function2) {
        this.onItemClickListener = function2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(final BaseViewHolder holder, final BusinessSetItem item) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        Intrinsics.checkParameterIsNotNull(item, "item");
        ImageView imageView = (ImageView) holder.getView(C5362R.id.iv_pic_point);
        TextView tvTitle = (TextView) holder.getView(C5362R.id.tv_title);
        TextView tvContent = (TextView) holder.getView(C5362R.id.tv_content);
        TextView ivSelect = (TextView) holder.getView(C5362R.id.iv_select);
        TextView tvHasSet = (TextView) holder.getView(C5362R.id.tv_has_set);
        imageView.setImageResource(item.getImageResource());
        Intrinsics.checkExpressionValueIsNotNull(tvTitle, "tvTitle");
        tvTitle.setText(item.getName());
        Intrinsics.checkExpressionValueIsNotNull(tvContent, "tvContent");
        tvContent.setText(item.getContent());
        if (item.isSelect()) {
            Intrinsics.checkExpressionValueIsNotNull(ivSelect, "ivSelect");
            ivSelect.setVisibility(0);
        } else {
            Intrinsics.checkExpressionValueIsNotNull(ivSelect, "ivSelect");
            ivSelect.setVisibility(8);
        }
        if (item.getPointType() == 7) {
            Intrinsics.checkExpressionValueIsNotNull(tvHasSet, "tvHasSet");
            tvHasSet.setText("");
        } else if (item.isSetting()) {
            tvHasSet.setTextColor(AppContext.INSTANCE.getContext().getColor(C5362R.color.color_1cc33d));
            Intrinsics.checkExpressionValueIsNotNull(tvHasSet, "tvHasSet");
            View view = holder.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view, "holder.itemView");
            tvHasSet.setText(view.getContext().getString(C5362R.string.has_set));
        } else {
            tvHasSet.setTextColor(AppContext.INSTANCE.getContext().getColor(C5362R.color.color_FB313B));
            Intrinsics.checkExpressionValueIsNotNull(tvHasSet, "tvHasSet");
            View view2 = holder.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view2, "holder.itemView");
            tvHasSet.setText(view2.getContext().getString(C5362R.string.un_set));
        }
        if (holder.getAdapterPosition() == 0) {
            View view3 = holder.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view3, "holder.itemView");
            ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
            if (layoutParams == null) {
                throw new TypeCastException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.LayoutParams");
            }
            RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
            layoutParams2.setMarginStart(DensityUtil.INSTANCE.dip2px(AppContext.INSTANCE.getContext(), 40.0f));
            View view4 = holder.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view4, "holder.itemView");
            view4.setLayoutParams(layoutParams2);
        }
        final View view5 = holder.itemView;
        final long j = 800;
        view5.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.adapter.BusinessSetAdapter$convert$$inlined$singleClick$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view6) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(view5) > j || (view5 instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(view5, currentTimeMillis);
                    View view7 = view5;
                    Function2<BusinessSetItem, Integer, Unit> onItemClickListener = this.getOnItemClickListener();
                    if (onItemClickListener != null) {
                        onItemClickListener.invoke(item, Integer.valueOf(holder.getAdapterPosition()));
                    }
                }
            }
        });
    }
}
