package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.RecycleTaskDialogAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.RecycleTaskItem;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListenerKt;
import com.pudutech.disinfect.baselib.ext.view.ViewExtKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: RecycleTaskDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0015\u001a\u00020\fH\u0016J\u0010\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u0018H\u0014J\b\u0010\u0019\u001a\u00020\rH\u0014J\u0014\u0010\u001a\u001a\u00020\r2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cR$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR@\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b2\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/RecycleTaskDialog;", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/BumbleBaseDialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", ES6Iterator.VALUE_PROPERTY, "", "isHistory", "()Z", "setHistory", "(Z)V", "Lkotlin/Function1;", "", "", "onDelete", "getOnDelete", "()Lkotlin/jvm/functions/Function1;", "setOnDelete", "(Lkotlin/jvm/functions/Function1;)V", "taskAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/RecycleTaskDialogAdapter;", "getLayoutId", "initView", "view", "Landroid/view/View;", "setData", "setTasks", "list", "", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/RecycleTaskItem;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class RecycleTaskDialog extends BumbleBaseDialog {
    private boolean isHistory;
    private Function1<? super Integer, Unit> onDelete;
    private RecycleTaskDialogAdapter taskAdapter;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecycleTaskDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    public final Function1<Integer, Unit> getOnDelete() {
        return this.onDelete;
    }

    public final void setOnDelete(Function1<? super Integer, Unit> function1) {
        this.onDelete = function1;
        RecycleTaskDialogAdapter recycleTaskDialogAdapter = this.taskAdapter;
        if (recycleTaskDialogAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("taskAdapter");
        }
        recycleTaskDialogAdapter.setOnDelete(this.onDelete);
    }

    /* renamed from: isHistory, reason: from getter */
    public final boolean getIsHistory() {
        return this.isHistory;
    }

    public final void setHistory(boolean z) {
        this.isHistory = z;
        Group group_title = (Group) findViewById(C4188R.id.group_title);
        Intrinsics.checkExpressionValueIsNotNull(group_title, "group_title");
        ViewExtKt.visibleOrGone(group_title, this.isHistory);
        TextView confirm = (TextView) findViewById(C4188R.id.confirm);
        Intrinsics.checkExpressionValueIsNotNull(confirm, "confirm");
        ViewExtKt.visibleOrGone(confirm, !this.isHistory);
        RecycleTaskDialogAdapter recycleTaskDialogAdapter = this.taskAdapter;
        if (recycleTaskDialogAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("taskAdapter");
        }
        recycleTaskDialogAdapter.setCanDelete(!this.isHistory);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    public int getLayoutId() {
        return C4188R.layout.dialog_recycle_task;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    public void initView(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.initView(view);
        this.taskAdapter = new RecycleTaskDialogAdapter();
        RecyclerView rv_task = (RecyclerView) findViewById(C4188R.id.rv_task);
        Intrinsics.checkExpressionValueIsNotNull(rv_task, "rv_task");
        rv_task.setLayoutManager(new GridLayoutManager(getContext(), 4, 1, false));
        RecycleTaskDialogAdapter recycleTaskDialogAdapter = this.taskAdapter;
        if (recycleTaskDialogAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("taskAdapter");
        }
        recycleTaskDialogAdapter.bindToRecyclerView((RecyclerView) findViewById(C4188R.id.rv_task));
        RecycleTaskDialogAdapter recycleTaskDialogAdapter2 = this.taskAdapter;
        if (recycleTaskDialogAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("taskAdapter");
        }
        recycleTaskDialogAdapter2.setOnDelete(this.onDelete);
        Group group_title = (Group) findViewById(C4188R.id.group_title);
        Intrinsics.checkExpressionValueIsNotNull(group_title, "group_title");
        ViewExtKt.visibleOrGone(group_title, this.isHistory);
        TextView confirm = (TextView) findViewById(C4188R.id.confirm);
        Intrinsics.checkExpressionValueIsNotNull(confirm, "confirm");
        ViewExtKt.visibleOrGone(confirm, !this.isHistory);
        TextView confirm2 = (TextView) findViewById(C4188R.id.confirm);
        Intrinsics.checkExpressionValueIsNotNull(confirm2, "confirm");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(confirm2, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.RecycleTaskDialog$initView$1
            /* JADX INFO: Access modifiers changed from: package-private */
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
                RecycleTaskDialog.this.dismiss();
            }
        }, 3, null);
        ImageView close = (ImageView) findViewById(C4188R.id.close);
        Intrinsics.checkExpressionValueIsNotNull(close, "close");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(close, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.RecycleTaskDialog$initView$2
            /* JADX INFO: Access modifiers changed from: package-private */
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
                RecycleTaskDialog.this.dismiss();
            }
        }, 3, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    public void setData() {
        super.setData();
    }

    public final void setTasks(List<RecycleTaskItem> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        RecycleTaskDialogAdapter recycleTaskDialogAdapter = this.taskAdapter;
        if (recycleTaskDialogAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("taskAdapter");
        }
        recycleTaskDialogAdapter.setNewData(list);
    }
}
