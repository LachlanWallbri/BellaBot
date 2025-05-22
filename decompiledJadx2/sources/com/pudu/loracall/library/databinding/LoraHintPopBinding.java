package com.pudu.loracall.library.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.pudu.loracall.library.C3965R;

/* loaded from: classes4.dex */
public abstract class LoraHintPopBinding extends ViewDataBinding {
    public final TextView cancelView;
    public final ImageView closeBut;
    public final TextView contentView;
    public final View lineView;
    public final TextView okView;

    /* JADX INFO: Access modifiers changed from: protected */
    public LoraHintPopBinding(Object obj, View view, int i, TextView textView, ImageView imageView, TextView textView2, View view2, TextView textView3) {
        super(obj, view, i);
        this.cancelView = textView;
        this.closeBut = imageView;
        this.contentView = textView2;
        this.lineView = view2;
        this.okView = textView3;
    }

    public static LoraHintPopBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraHintPopBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LoraHintPopBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_hint_pop, viewGroup, z, obj);
    }

    public static LoraHintPopBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraHintPopBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LoraHintPopBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_hint_pop, null, false, obj);
    }

    public static LoraHintPopBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraHintPopBinding bind(View view, Object obj) {
        return (LoraHintPopBinding) bind(obj, view, C3965R.layout.lora_hint_pop);
    }
}
