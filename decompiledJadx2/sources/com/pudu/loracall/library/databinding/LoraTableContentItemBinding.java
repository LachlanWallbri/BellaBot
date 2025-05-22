package com.pudu.loracall.library.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.pudu.library.loracall.dao.TableMatchBean;
import com.pudu.loracall.library.C3965R;

/* loaded from: classes4.dex */
public abstract class LoraTableContentItemBinding extends ViewDataBinding {
    public final TextView idView;
    public final LinearLayout itemLayoutView;

    @Bindable
    protected TableMatchBean mItem;
    public final TextView tableView;

    public abstract void setItem(TableMatchBean tableMatchBean);

    /* JADX INFO: Access modifiers changed from: protected */
    public LoraTableContentItemBinding(Object obj, View view, int i, TextView textView, LinearLayout linearLayout, TextView textView2) {
        super(obj, view, i);
        this.idView = textView;
        this.itemLayoutView = linearLayout;
        this.tableView = textView2;
    }

    public TableMatchBean getItem() {
        return this.mItem;
    }

    public static LoraTableContentItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraTableContentItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LoraTableContentItemBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_table_content_item, viewGroup, z, obj);
    }

    public static LoraTableContentItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraTableContentItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LoraTableContentItemBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_table_content_item, null, false, obj);
    }

    public static LoraTableContentItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraTableContentItemBinding bind(View view, Object obj) {
        return (LoraTableContentItemBinding) bind(obj, view, C3965R.layout.lora_table_content_item);
    }
}
