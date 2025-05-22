package com.pudu.loracall.library.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.pudu.loracall.library.C3965R;

/* loaded from: classes4.dex */
public abstract class LoraApproveFragmentBinding extends ViewDataBinding {
    public final RelativeLayout fixedLayout;
    public final EditText powerEdit;
    public final TextView powerView;
    public final TextView receiveCountView;
    public final TextView receiveCycleHint;
    public final TextView receiveCycleView;
    public final EditText receiveFrequencyEdit;
    public final TextView receiveFrequencyView;
    public final EditText rfModuleEdit;
    public final TextView rfModuleView;
    public final LinearLayout rzLayout;
    public final Button sendButton;
    public final EditText sendCycleEdit;
    public final TextView sendCycleView;
    public final TextView sendModelView;
    public final EditText sendPowerEdit;
    public final TextView sendPowerView;
    public final TextView sendType;
    public final RelativeLayout signalLayout;
    public final ImageView signalRefreshView;
    public final TextView signalStateView;
    public final EditText silentValueEdit;
    public final TextView silentValueView;
    public final Switch switchView;
    public final TextView waveformView;

    /* JADX INFO: Access modifiers changed from: protected */
    public LoraApproveFragmentBinding(Object obj, View view, int i, RelativeLayout relativeLayout, EditText editText, TextView textView, TextView textView2, TextView textView3, TextView textView4, EditText editText2, TextView textView5, EditText editText3, TextView textView6, LinearLayout linearLayout, Button button, EditText editText4, TextView textView7, TextView textView8, EditText editText5, TextView textView9, TextView textView10, RelativeLayout relativeLayout2, ImageView imageView, TextView textView11, EditText editText6, TextView textView12, Switch r29, TextView textView13) {
        super(obj, view, i);
        this.fixedLayout = relativeLayout;
        this.powerEdit = editText;
        this.powerView = textView;
        this.receiveCountView = textView2;
        this.receiveCycleHint = textView3;
        this.receiveCycleView = textView4;
        this.receiveFrequencyEdit = editText2;
        this.receiveFrequencyView = textView5;
        this.rfModuleEdit = editText3;
        this.rfModuleView = textView6;
        this.rzLayout = linearLayout;
        this.sendButton = button;
        this.sendCycleEdit = editText4;
        this.sendCycleView = textView7;
        this.sendModelView = textView8;
        this.sendPowerEdit = editText5;
        this.sendPowerView = textView9;
        this.sendType = textView10;
        this.signalLayout = relativeLayout2;
        this.signalRefreshView = imageView;
        this.signalStateView = textView11;
        this.silentValueEdit = editText6;
        this.silentValueView = textView12;
        this.switchView = r29;
        this.waveformView = textView13;
    }

    public static LoraApproveFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraApproveFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LoraApproveFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_approve_fragment, viewGroup, z, obj);
    }

    public static LoraApproveFragmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraApproveFragmentBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LoraApproveFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_approve_fragment, null, false, obj);
    }

    public static LoraApproveFragmentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraApproveFragmentBinding bind(View view, Object obj) {
        return (LoraApproveFragmentBinding) bind(obj, view, C3965R.layout.lora_approve_fragment);
    }
}
