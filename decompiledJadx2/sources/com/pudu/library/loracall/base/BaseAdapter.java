package com.pudu.library.loracall.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0016\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002<=B/\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ(\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\u0006\u0010/\u001a\u00020\u0005H\u0016J\b\u00100\u001a\u00020\u0005H\u0016J\u0010\u00101\u001a\u00020\u00052\u0006\u0010/\u001a\u00020\u0005H\u0016J\u0018\u00102\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u00032\u0006\u0010/\u001a\u00020\u0005H\u0016J\u0018\u00103\u001a\u00020\u00032\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u0005H\u0016J\u0010\u00107\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u0003H\u0016J\b\u00108\u001a\u000209H\u0002J\u0018\u0010:\u001a\u00020\u000e2\u000e\u0010;\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0018H\u0016R(\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\b\u001a\u00020\tX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\"\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001f\"\u0004\b$\u0010!R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010&\"\u0004\b*\u0010(¨\u0006>"}, m3961d2 = {"Lcom/pudu/library/loracall/base/BaseAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "", "Lcom/pudu/library/loracall/base/BaseAdapter$ViewHolder;", "layoutId", "", "viewModel", "mContext", "callback", "Lcom/pudu/library/loracall/base/BaseAdapter$ItemCallback;", "(ILjava/lang/Object;Ljava/lang/Object;Lcom/pudu/library/loracall/base/BaseAdapter$ItemCallback;)V", "bindHolder", "Lkotlin/Function1;", "Landroidx/databinding/ViewDataBinding;", "", "getBindHolder", "()Lkotlin/jvm/functions/Function1;", "setBindHolder", "(Lkotlin/jvm/functions/Function1;)V", "getCallback", "()Lcom/pudu/library/loracall/base/BaseAdapter$ItemCallback;", "setCallback", "(Lcom/pudu/library/loracall/base/BaseAdapter$ItemCallback;)V", "currDataList", "", "getCurrDataList", "()Ljava/util/List;", "setCurrDataList", "(Ljava/util/List;)V", "emptyLayoutId", "getEmptyLayoutId", "()I", "setEmptyLayoutId", "(I)V", "emptyViewType", "getLayoutId", "setLayoutId", "getMContext", "()Ljava/lang/Object;", "setMContext", "(Ljava/lang/Object;)V", "getViewModel", "setViewModel", "convert", "holder", "item", "oldItem", RequestParameters.POSITION, "getItemCount", "getItemViewType", "onBindViewHolder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onViewRecycled", "shouldSetEmptyView", "", "submitList", "list", "ItemCallback", "ViewHolder", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public class BaseAdapter extends ListAdapter<Object, ViewHolder> {
    private Function1<? super ViewDataBinding, Unit> bindHolder;
    private ItemCallback callback;
    private List<? extends Object> currDataList;
    private int emptyLayoutId;
    private final int emptyViewType;
    private int layoutId;
    private Object mContext;
    private Object viewModel;

    public void convert(ViewHolder holder, Object item, Object oldItem, int position) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        Intrinsics.checkParameterIsNotNull(item, "item");
        Intrinsics.checkParameterIsNotNull(oldItem, "oldItem");
    }

    public int getLayoutId() {
        return this.layoutId;
    }

    public void setLayoutId(int i) {
        this.layoutId = i;
    }

    public Object getViewModel() {
        return this.viewModel;
    }

    public void setViewModel(Object obj) {
        this.viewModel = obj;
    }

    public Object getMContext() {
        return this.mContext;
    }

    public void setMContext(Object obj) {
        this.mContext = obj;
    }

    public /* synthetic */ BaseAdapter(int i, Object obj, Object obj2, ItemCallback itemCallback, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : obj, (i2 & 4) != 0 ? null : obj2, (i2 & 8) != 0 ? new ItemCallback() : itemCallback);
    }

    public ItemCallback getCallback() {
        return this.callback;
    }

    public void setCallback(ItemCallback itemCallback) {
        Intrinsics.checkParameterIsNotNull(itemCallback, "<set-?>");
        this.callback = itemCallback;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseAdapter(int i, Object obj, Object obj2, ItemCallback callback) {
        super(callback);
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.layoutId = i;
        this.viewModel = obj;
        this.mContext = obj2;
        this.callback = callback;
        this.emptyViewType = -23;
    }

    public final Function1<ViewDataBinding, Unit> getBindHolder() {
        return this.bindHolder;
    }

    public final void setBindHolder(Function1<? super ViewDataBinding, Unit> function1) {
        this.bindHolder = function1;
    }

    public final List<Object> getCurrDataList() {
        return this.currDataList;
    }

    public final void setCurrDataList(List<? extends Object> list) {
        this.currDataList = list;
    }

    public final int getEmptyLayoutId() {
        return this.emptyLayoutId;
    }

    public final void setEmptyLayoutId(int i) {
        this.emptyLayoutId = i;
    }

    private final boolean shouldSetEmptyView() {
        return super.getItemCount() == 0 && this.emptyLayoutId != 0;
    }

    @Override // androidx.recyclerview.widget.ListAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (shouldSetEmptyView()) {
            return 1;
        }
        return super.getItemCount();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        if (shouldSetEmptyView()) {
            return this.emptyViewType;
        }
        return super.getItemViewType(position);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        if (shouldSetEmptyView()) {
            ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), this.emptyLayoutId, parent, false);
            Intrinsics.checkExpressionValueIsNotNull(inflate, "DataBindingUtil.inflate(…lse\n                    )");
            return new ViewHolder(inflate);
        }
        ViewDataBinding inflate2 = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayoutId(), parent, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate2, "DataBindingUtil.inflate(…  false\n                )");
        return new ViewHolder(inflate2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        Object oldItem;
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        if (shouldSetEmptyView()) {
            return;
        }
        if (position == 0) {
            oldItem = getItem(position);
        } else {
            oldItem = getItem(position - 1);
        }
        Object param = getItem(position);
        Intrinsics.checkExpressionValueIsNotNull(param, "param");
        Object viewModel = getViewModel();
        int itemCount = getItemCount();
        Intrinsics.checkExpressionValueIsNotNull(oldItem, "oldItem");
        holder.bind(param, viewModel, itemCount, oldItem, getMContext());
        View itemView = holder.itemView;
        Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
        itemView.setTag(param);
        convert(holder, param, oldItem, position);
        Function1<? super ViewDataBinding, Unit> function1 = this.bindHolder;
        if (function1 != null) {
            function1.invoke(holder.getBinding());
        }
    }

    /* compiled from: BaseAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J2\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0011"}, m3961d2 = {"Lcom/pudu/library/loracall/base/BaseAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Landroidx/databinding/ViewDataBinding;", "(Landroidx/databinding/ViewDataBinding;)V", "getBinding", "()Landroidx/databinding/ViewDataBinding;", "setBinding", "bind", "", "item", "", "viewModel", "itemCount", "", "oldItem", "mContext", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkParameterIsNotNull(binding, "binding");
            this.binding = binding;
        }

        public final ViewDataBinding getBinding() {
            return this.binding;
        }

        public final void setBinding(ViewDataBinding viewDataBinding) {
            Intrinsics.checkParameterIsNotNull(viewDataBinding, "<set-?>");
            this.binding = viewDataBinding;
        }

        public final void bind(Object item, Object viewModel, int itemCount, Object oldItem, Object mContext) {
            Intrinsics.checkParameterIsNotNull(item, "item");
            Intrinsics.checkParameterIsNotNull(oldItem, "oldItem");
            ViewDataBinding viewDataBinding = this.binding;
            viewDataBinding.setVariable(BR.item, item);
            viewDataBinding.setVariable(BR.oldItem, oldItem);
            viewDataBinding.setVariable(BR.viewModel, viewModel);
            viewDataBinding.setVariable(BR.mContext, mContext);
            viewDataBinding.setVariable(BR.binding, viewDataBinding);
            viewDataBinding.setVariable(BR.position, Integer.valueOf(getBindingAdapterPosition()));
            viewDataBinding.setVariable(BR.isfood, Boolean.valueOf(itemCount - 1 == getBindingAdapterPosition()));
            viewDataBinding.executePendingBindings();
        }
    }

    @Override // androidx.recyclerview.widget.ListAdapter
    public void submitList(List<Object> list) {
        if (shouldSetEmptyView()) {
            notifyItemRemoved(0);
        }
        this.currDataList = list;
        super.submitList(list);
    }

    /* compiled from: BaseAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0017J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\t"}, m3961d2 = {"Lcom/pudu/library/loracall/base/BaseAdapter$ItemCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static class ItemCallback extends DiffUtil.ItemCallback<Object> {
        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(Object oldItem, Object newItem) {
            Intrinsics.checkParameterIsNotNull(oldItem, "oldItem");
            Intrinsics.checkParameterIsNotNull(newItem, "newItem");
            return Intrinsics.areEqual(oldItem, newItem);
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areContentsTheSame(Object oldItem, Object newItem) {
            Intrinsics.checkParameterIsNotNull(oldItem, "oldItem");
            Intrinsics.checkParameterIsNotNull(newItem, "newItem");
            return Intrinsics.areEqual(oldItem, newItem);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(ViewHolder holder) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        View view = holder.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "holder.itemView");
        view.setTag(null);
    }
}
