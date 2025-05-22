package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListenerKt;
import com.pudutech.disinfect.baselib.ext.view.ViewExtKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecycleTaskDialogAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0002H\u0014R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR(\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/RecycleTaskDialogAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/RecycleTaskItem;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "()V", "canDelete", "", "getCanDelete", "()Z", "setCanDelete", "(Z)V", "onDelete", "Lkotlin/Function1;", "", "", "getOnDelete", "()Lkotlin/jvm/functions/Function1;", "setOnDelete", "(Lkotlin/jvm/functions/Function1;)V", "convert", "helper", "item", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class RecycleTaskDialogAdapter extends BaseQuickAdapter<RecycleTaskItem, BaseViewHolder> {
    private boolean canDelete;
    private Function1<? super Integer, Unit> onDelete;

    public RecycleTaskDialogAdapter() {
        super(C4188R.layout.item_recycle_task_dialog);
        this.canDelete = true;
    }

    public final Function1<Integer, Unit> getOnDelete() {
        return this.onDelete;
    }

    public final void setOnDelete(Function1<? super Integer, Unit> function1) {
        this.onDelete = function1;
    }

    public final boolean getCanDelete() {
        return this.canDelete;
    }

    public final void setCanDelete(boolean z) {
        this.canDelete = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(final BaseViewHolder helper, RecycleTaskItem item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        helper.setText(C4188R.id.task_tv, item.getDestination());
        View delete = helper.getView(C4188R.id.iv_delete);
        Pdlog.m3273d(BaseQuickAdapter.TAG, "convert canDelete: " + this.canDelete);
        Intrinsics.checkExpressionValueIsNotNull(delete, "delete");
        ViewExtKt.visibleOrGone(delete, this.canDelete);
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(delete, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.RecycleTaskDialogAdapter$convert$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                int adapterPosition = helper.getAdapterPosition();
                if (adapterPosition == -1) {
                    return;
                }
                RecycleTaskDialogAdapter.this.remove(adapterPosition);
                Function1<Integer, Unit> onDelete = RecycleTaskDialogAdapter.this.getOnDelete();
                if (onDelete != null) {
                    onDelete.invoke(Integer.valueOf(adapterPosition));
                }
            }
        }, 3, null);
    }
}
