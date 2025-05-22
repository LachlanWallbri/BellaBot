package com.pudu.loracall.library.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.pudu.loracall.library.C3965R;

/* loaded from: classes4.dex */
public abstract class LoraPerformanceFragmentBinding extends ViewDataBinding {
    public final TextView receiveCountView;
    public final TextView receiveCycleHint;
    public final TextView receiveCycleView;
    public final EditText rfModuleEdit;
    public final TextView rfModuleView;
    public final Button sendButton;
    public final RelativeLayout signalLayout;
    public final ImageView signalRefreshView;
    public final TextView signalStateView;
    public final Switch switchView;

    /* JADX INFO: Access modifiers changed from: protected */
    public LoraPerformanceFragmentBinding(Object obj, View view, int i, TextView textView, TextView textView2, TextView textView3, EditText editText, TextView textView4, Button button, RelativeLayout relativeLayout, ImageView imageView, TextView textView5, Switch r13) {
        super(obj, view, i);
        this.receiveCountView = textView;
        this.receiveCycleHint = textView2;
        this.receiveCycleView = textView3;
        this.rfModuleEdit = editText;
        this.rfModuleView = textView4;
        this.sendButton = button;
        this.signalLayout = relativeLayout;
        this.signalRefreshView = imageView;
        this.signalStateView = textView5;
        this.switchView = r13;
    }

    public static LoraPerformanceFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraPerformanceFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LoraPerformanceFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_performance_fragment, viewGroup, z, obj);
    }

    public static LoraPerformanceFragmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraPerformanceFragmentBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LoraPerformanceFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_performance_fragment, null, false, obj);
    }

    public static LoraPerformanceFragmentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraPerformanceFragmentBinding bind(View view, Object obj) {
        return (LoraPerformanceFragmentBinding) bind(obj, view, C3965R.layout.lora_performance_fragment);
    }
}
