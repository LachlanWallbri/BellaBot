package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListenerKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: RecycleTaskAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\u0002\u0018\u0000 \u001b2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001bB\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0002H\u0014J\u000e\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\rJ\u000e\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0002J\u0014\u0010\u0018\u001a\u00020\u00072\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u001aR\"\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u00020\r8FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0002X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/RecycleTaskAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/RecycleTaskItem;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "()V", "onDataSizeChanged", "Lkotlin/Function0;", "", "getOnDataSizeChanged", "()Lkotlin/jvm/functions/Function0;", "setOnDataSizeChanged", "(Lkotlin/jvm/functions/Function0;)V", "realSize", "", "getRealSize", "()I", "setRealSize", "(I)V", "selectedItem", "convert", "helper", "item", RequestParameters.SUBRESOURCE_DELETE, RequestParameters.POSITION, "setData", "data", "", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class RecycleTaskAdapter extends BaseQuickAdapter<RecycleTaskItem, BaseViewHolder> {
    public static final int MAX = 20;
    private Function0<Unit> onDataSizeChanged;
    private int realSize;
    private RecycleTaskItem selectedItem;

    public RecycleTaskAdapter() {
        super(C4188R.layout.item_recycle_task);
        RecycleTaskItem recycleTaskItem = new RecycleTaskItem("", true);
        this.selectedItem = recycleTaskItem;
        this.mData.add(recycleTaskItem);
    }

    public final Function0<Unit> getOnDataSizeChanged() {
        return this.onDataSizeChanged;
    }

    public final void setOnDataSizeChanged(Function0<Unit> function0) {
        this.onDataSizeChanged = function0;
    }

    public final void setRealSize(int i) {
        this.realSize = i;
    }

    public final int getRealSize() {
        Iterable mData = this.mData;
        Intrinsics.checkExpressionValueIsNotNull(mData, "mData");
        ArrayList arrayList = new ArrayList();
        for (Object obj : mData) {
            if (((RecycleTaskItem) obj).getDestination().length() > 0) {
                arrayList.add(obj);
            }
        }
        return arrayList.size();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(final BaseViewHolder helper, RecycleTaskItem item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        TextView textView = (TextView) helper.getView(C4188R.id.task_tv);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
        textView.setText(item.getDestination());
        TextView textView2 = textView;
        Sdk27PropertiesKt.setBackgroundResource(textView2, item.getSelected() ? C4188R.drawable.shape_item_recycle_task_bg : C4188R.drawable.shape_item_recycle_task_n);
        Sdk27PropertiesKt.setTextColor(textView, ContextCompat.getColor(textView.getContext(), item.getSelected() ? C4188R.color.white : C4188R.color.font_color_1));
        helper.setVisible(C4188R.id.close_iv, item.getDestination().length() > 0);
        View view = helper.getView(C4188R.id.close_iv);
        Intrinsics.checkExpressionValueIsNotNull(view, "helper.getView<View>(R.id.close_iv)");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(view, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.RecycleTaskAdapter$convert$1
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
                int adapterPosition = helper.getAdapterPosition();
                if (adapterPosition == -1) {
                    return;
                }
                RecycleTaskAdapter.this.delete(adapterPosition);
            }
        }, 3, null);
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(textView2, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.RecycleTaskAdapter$convert$2
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
                RecycleTaskItem recycleTaskItem;
                RecycleTaskItem recycleTaskItem2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                int adapterPosition = helper.getAdapterPosition();
                if (adapterPosition == -1) {
                    return;
                }
                recycleTaskItem = RecycleTaskAdapter.this.selectedItem;
                recycleTaskItem.setSelected(false);
                RecycleTaskAdapter.this.getData().get(adapterPosition).setSelected(true);
                RecycleTaskAdapter recycleTaskAdapter = RecycleTaskAdapter.this;
                List<RecycleTaskItem> data = recycleTaskAdapter.getData();
                recycleTaskItem2 = RecycleTaskAdapter.this.selectedItem;
                recycleTaskAdapter.notifyItemChanged(data.indexOf(recycleTaskItem2));
                RecycleTaskAdapter.this.notifyItemChanged(adapterPosition);
                RecycleTaskAdapter recycleTaskAdapter2 = RecycleTaskAdapter.this;
                RecycleTaskItem recycleTaskItem3 = recycleTaskAdapter2.getData().get(adapterPosition);
                Intrinsics.checkExpressionValueIsNotNull(recycleTaskItem3, "data[position]");
                recycleTaskAdapter2.selectedItem = recycleTaskItem3;
            }
        }, 3, null);
    }

    public final void delete(int position) {
        List<T> mData = this.mData;
        Intrinsics.checkExpressionValueIsNotNull(mData, "mData");
        RecycleTaskItem last = (RecycleTaskItem) CollectionsKt.last((List) mData);
        if (position == 19) {
            last.setDestination("");
            notifyItemChanged(this.mData.size() - 1);
            Function0<Unit> function0 = this.onDataSizeChanged;
            if (function0 != null) {
                function0.invoke();
                return;
            }
            return;
        }
        boolean selected = ((RecycleTaskItem) this.mData.get(position)).getSelected();
        remove(position);
        if (last.getDestination().length() > 0) {
            RecycleTaskItem recycleTaskItem = new RecycleTaskItem("", selected);
            addData((RecycleTaskAdapter) recycleTaskItem);
            if (selected) {
                this.selectedItem = recycleTaskItem;
            }
        } else if (selected) {
            last.setSelected(true);
            notifyItemChanged(this.mData.size() - 1);
            Intrinsics.checkExpressionValueIsNotNull(last, "last");
            this.selectedItem = last;
        }
        Function0<Unit> function02 = this.onDataSizeChanged;
        if (function02 != null) {
            function02.invoke();
        }
    }

    public final void setData(RecycleTaskItem data) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(data, "data");
        Iterable mData = this.mData;
        Intrinsics.checkExpressionValueIsNotNull(mData, "mData");
        Iterator it = mData.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (Intrinsics.areEqual(((RecycleTaskItem) obj).getDestination(), data.getDestination())) {
                    break;
                }
            }
        }
        if (obj != null) {
            return;
        }
        int indexOf = this.mData.indexOf(this.selectedItem);
        if ((this.selectedItem.getDestination().length() == 0) && getItemCount() < 20) {
            this.selectedItem.setSelected(false);
            this.selectedItem.setDestination(data.getDestination());
            data.setSelected(true);
            data.setDestination("");
            this.selectedItem = data;
            notifyItemChanged(indexOf);
            addData((RecycleTaskAdapter) data);
        } else {
            this.selectedItem.setDestination(data.getDestination());
            notifyItemChanged(indexOf);
        }
        RecyclerView recyclerView = getRecyclerView();
        if (recyclerView != null) {
            recyclerView.scrollToPosition(this.mData.size() - 1);
        }
        Function0<Unit> function0 = this.onDataSizeChanged;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public final void setData(List<RecycleTaskItem> data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.mData.clear();
        this.mData.addAll(data);
        if (data.size() < 20) {
            RecycleTaskItem recycleTaskItem = new RecycleTaskItem("", true);
            this.selectedItem = recycleTaskItem;
            this.mData.add(recycleTaskItem);
        } else {
            List<T> mData = this.mData;
            Intrinsics.checkExpressionValueIsNotNull(mData, "mData");
            RecycleTaskItem last = (RecycleTaskItem) CollectionsKt.last((List) mData);
            last.setSelected(true);
            Intrinsics.checkExpressionValueIsNotNull(last, "last");
            this.selectedItem = last;
        }
        notifyDataSetChanged();
        RecyclerView recyclerView = getRecyclerView();
        if (recyclerView != null) {
            recyclerView.scrollToPosition(this.mData.size() - 1);
        }
        Function0<Unit> function0 = this.onDataSizeChanged;
        if (function0 != null) {
            function0.invoke();
        }
    }
}
