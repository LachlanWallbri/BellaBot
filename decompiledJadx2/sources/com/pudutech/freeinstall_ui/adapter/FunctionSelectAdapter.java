package com.pudutech.freeinstall_ui.adapter;

import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.freeinstall_ui.utils.ExtandsKt;
import com.pudutech.module_freeinstall_ui.C5362R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FunctionSelectAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0002H\u0014RL\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/adapter/FunctionSelectAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/freeinstall_ui/adapter/FunctionSelectItem;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "()V", "onItemClickListener", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "functionSelectItem", "", "pos", "", "getOnItemClickListener", "()Lkotlin/jvm/functions/Function2;", "setOnItemClickListener", "(Lkotlin/jvm/functions/Function2;)V", "convert", "holder", "item", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class FunctionSelectAdapter extends BaseQuickAdapter<FunctionSelectItem, BaseViewHolder> {
    private Function2<? super FunctionSelectItem, ? super Integer, Unit> onItemClickListener;

    public FunctionSelectAdapter() {
        super(C5362R.layout.function_list_item);
    }

    public final Function2<FunctionSelectItem, Integer, Unit> getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public final void setOnItemClickListener(Function2<? super FunctionSelectItem, ? super Integer, Unit> function2) {
        this.onItemClickListener = function2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(final BaseViewHolder holder, final FunctionSelectItem item) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        Intrinsics.checkParameterIsNotNull(item, "item");
        ImageView imageView = (ImageView) holder.getView(C5362R.id.iv_pic_function);
        TextView tvTitle = (TextView) holder.getView(C5362R.id.tv_title);
        TextView tvContent = (TextView) holder.getView(C5362R.id.tv_content);
        ImageView imageView2 = (ImageView) holder.getView(C5362R.id.iv_select);
        imageView.setImageResource(item.getImageResource());
        Intrinsics.checkExpressionValueIsNotNull(tvTitle, "tvTitle");
        tvTitle.setText(item.getName());
        Intrinsics.checkExpressionValueIsNotNull(tvContent, "tvContent");
        tvContent.setText(item.getContent());
        if (item.getHasSetting()) {
            imageView2.setImageResource(C5362R.drawable.icon_function_has_setting);
        } else if (item.isSelect()) {
            imageView2.setImageResource(C5362R.drawable.icon_function_select);
        } else {
            imageView2.setImageResource(C5362R.drawable.rectangle_btn_a8a8a8_line_8);
        }
        final View view = holder.itemView;
        final long j = 800;
        view.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.adapter.FunctionSelectAdapter$convert$$inlined$singleClick$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(view) > j || (view instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(view, currentTimeMillis);
                    View view3 = view;
                    Function2<FunctionSelectItem, Integer, Unit> onItemClickListener = this.getOnItemClickListener();
                    if (onItemClickListener != null) {
                        onItemClickListener.invoke(item, Integer.valueOf(holder.getAdapterPosition()));
                    }
                }
            }
        });
    }
}
