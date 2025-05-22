package com.pudutech.bumblebee.robot_ui.p054ui.view;

import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.TablesClassifyDragAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.TablesClassifyItem;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TableClassifyDragCallback.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0018\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J$\u0010\u001f\u001a\u00020\u00122\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000bJ \u0010#\u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u001bH\u0016J\u001a\u0010%\u001a\u00020\u00122\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010&\u001a\u00020\u000bH\u0016J\u0018\u0010'\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020\u000bH\u0016J>\u0010)\u001a\u00020\u001226\u0010*\u001a2\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00120\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0004R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000RJ\u0010\f\u001a2\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00120\rX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006+"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/TableClassifyDragCallback;", "Landroidx/recyclerview/widget/ItemTouchHelper$Callback;", "adapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/TablesClassifyDragAdapter;", "(Lcom/pudutech/bumblebee/robot_ui/ui/adapter/TablesClassifyDragAdapter;)V", "TAG", "", "getAdapter", "()Lcom/pudutech/bumblebee/robot_ui/ui/adapter/TablesClassifyDragAdapter;", "setAdapter", "frmPosition", "", "mOnDragFinishListener", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "frm", TypedValues.Transition.S_TO, "", "getMOnDragFinishListener", "()Lkotlin/jvm/functions/Function2;", "setMOnDragFinishListener", "(Lkotlin/jvm/functions/Function2;)V", "clearView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "getMovementFlags", "isLongPressDragEnabled", "", "listSwapAndSave", "lst", "", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/TablesClassifyItem;", "onMove", TypedValues.Attributes.S_TARGET, "onSelectedChanged", "actionState", "onSwiped", "direction", "setOnDragFinishListener", "listener", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TableClassifyDragCallback extends ItemTouchHelper.Callback {
    private final String TAG;
    private TablesClassifyDragAdapter adapter;
    private int frmPosition;
    public Function2<? super Integer, ? super Integer, Unit> mOnDragFinishListener;

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
    }

    public final TablesClassifyDragAdapter getAdapter() {
        return this.adapter;
    }

    public final void setAdapter(TablesClassifyDragAdapter tablesClassifyDragAdapter) {
        Intrinsics.checkParameterIsNotNull(tablesClassifyDragAdapter, "<set-?>");
        this.adapter = tablesClassifyDragAdapter;
    }

    public TableClassifyDragCallback(TablesClassifyDragAdapter adapter) {
        Intrinsics.checkParameterIsNotNull(adapter, "adapter");
        this.adapter = adapter;
        this.TAG = "TableClassifyDragCallback";
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        return ItemTouchHelper.Callback.makeMovementFlags(12, 0);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        Intrinsics.checkParameterIsNotNull(target, "target");
        this.adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        View view;
        View view2;
        if (actionState != 0) {
            if (viewHolder != null && (view2 = viewHolder.itemView) != null) {
                view2.setScaleX(1.2f);
            }
            if (viewHolder != null && (view = viewHolder.itemView) != null) {
                view.setScaleY(1.2f);
            }
            this.frmPosition = viewHolder != null ? viewHolder.getAdapterPosition() : -1;
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        super.clearView(recyclerView, viewHolder);
        int adapterPosition = viewHolder.getAdapterPosition();
        View view = viewHolder.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "viewHolder.itemView");
        view.setScaleX(1.0f);
        View view2 = viewHolder.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view2, "viewHolder.itemView");
        view2.setScaleY(1.0f);
        List<TablesClassifyItem> data = this.adapter.getData();
        Intrinsics.checkExpressionValueIsNotNull(data, "adapter.data");
        listSwapAndSave(data, this.frmPosition, adapterPosition);
    }

    public final void listSwapAndSave(List<TablesClassifyItem> lst, int frm, int to) {
        Intrinsics.checkParameterIsNotNull(lst, "lst");
        Pdlog.m3273d(this.TAG, "listSwapAndSave lst:" + lst.size() + ",frm:" + frm + ",to:" + to + ' ');
        if (frm >= lst.size() || frm < 0 || to >= lst.size() || to < 0) {
            Pdlog.m3273d(this.TAG, "listSwapAndSave no swap ");
            return;
        }
        TablesClassifyItem tablesClassifyItem = lst.get(frm);
        lst.remove(frm);
        lst.add(to, tablesClassifyItem);
        Function2<? super Integer, ? super Integer, Unit> function2 = this.mOnDragFinishListener;
        if (function2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mOnDragFinishListener");
        }
        function2.invoke(Integer.valueOf(frm), Integer.valueOf(to));
    }

    public final Function2<Integer, Integer, Unit> getMOnDragFinishListener() {
        Function2 function2 = this.mOnDragFinishListener;
        if (function2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mOnDragFinishListener");
        }
        return function2;
    }

    public final void setMOnDragFinishListener(Function2<? super Integer, ? super Integer, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "<set-?>");
        this.mOnDragFinishListener = function2;
    }

    public final void setOnDragFinishListener(Function2<? super Integer, ? super Integer, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.mOnDragFinishListener = listener;
    }
}
