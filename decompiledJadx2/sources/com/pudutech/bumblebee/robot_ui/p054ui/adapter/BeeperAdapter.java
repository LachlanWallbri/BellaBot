package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.bean.Beeper;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BeeperAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0002H\u0014RL\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010RL\u0010\u0011\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/BeeperAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/bumblebee/robot_ui/bean/Beeper;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "()V", "onDeleteClickListener", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "beeper", "", "pos", "", "getOnDeleteClickListener", "()Lkotlin/jvm/functions/Function2;", "setOnDeleteClickListener", "(Lkotlin/jvm/functions/Function2;)V", "onItemClickListener", "getOnItemClickListener", "setOnItemClickListener", "convert", "holder", "item", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class BeeperAdapter extends BaseQuickAdapter<Beeper, BaseViewHolder> {
    private Function2<? super Beeper, ? super Integer, Unit> onDeleteClickListener;
    private Function2<? super Beeper, ? super Integer, Unit> onItemClickListener;

    public BeeperAdapter() {
        super(C4188R.layout.item_beeper_info);
    }

    public final Function2<Beeper, Integer, Unit> getOnDeleteClickListener() {
        return this.onDeleteClickListener;
    }

    public final void setOnDeleteClickListener(Function2<? super Beeper, ? super Integer, Unit> function2) {
        this.onDeleteClickListener = function2;
    }

    public final Function2<Beeper, Integer, Unit> getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public final void setOnItemClickListener(Function2<? super Beeper, ? super Integer, Unit> function2) {
        this.onItemClickListener = function2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(final BaseViewHolder holder, final Beeper item) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        Intrinsics.checkParameterIsNotNull(item, "item");
        View view = holder.getView(C4188R.id.tv_beeper_name);
        Intrinsics.checkExpressionValueIsNotNull(view, "getView<TextView>(R.id.tv_beeper_name)");
        ((TextView) view).setText(item.getMac());
        ((TextView) holder.getView(C4188R.id.tv_beeper_delete)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.BeeperAdapter$convert$$inlined$apply$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Function2<Beeper, Integer, Unit> onDeleteClickListener = this.getOnDeleteClickListener();
                if (onDeleteClickListener != null) {
                    onDeleteClickListener.invoke(item, Integer.valueOf(BaseViewHolder.this.getLayoutPosition()));
                }
            }
        }, 3, null));
        holder.itemView.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.BeeperAdapter$convert$$inlined$apply$lambda$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Function2<Beeper, Integer, Unit> onItemClickListener = this.getOnItemClickListener();
                if (onItemClickListener != null) {
                    onItemClickListener.invoke(item, Integer.valueOf(BaseViewHolder.this.getLayoutPosition()));
                }
            }
        }, 3, null));
    }
}
