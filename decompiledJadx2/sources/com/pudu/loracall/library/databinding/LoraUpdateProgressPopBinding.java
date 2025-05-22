package com.pudu.loracall.library.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.pudu.loracall.library.C3965R;

/* loaded from: classes4.dex */
public abstract class LoraUpdateProgressPopBinding extends ViewDataBinding {
    public final TextView progressHint;
    public final TextView progressView;
    public final TextView titleView;

    /* JADX INFO: Access modifiers changed from: protected */
    public LoraUpdateProgressPopBinding(Object obj, View view, int i, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.progressHint = textView;
        this.progressView = textView2;
        this.titleView = textView3;
    }

    public static LoraUpdateProgressPopBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraUpdateProgressPopBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LoraUpdateProgressPopBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_update_progress_pop, viewGroup, z, obj);
    }

    public static LoraUpdateProgressPopBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraUpdateProgressPopBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LoraUpdateProgressPopBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_update_progress_pop, null, false, obj);
    }

    public static LoraUpdateProgressPopBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraUpdateProgressPopBinding bind(View view, Object obj) {
        return (LoraUpdateProgressPopBinding) bind(obj, view, C3965R.layout.lora_update_progress_pop);
    }
}
