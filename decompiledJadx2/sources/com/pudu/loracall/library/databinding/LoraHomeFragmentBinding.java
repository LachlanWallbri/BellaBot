package com.pudu.loracall.library.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.pudu.library.loracall.viewModel.LoRaConfigViewModel;
import com.pudu.loracall.library.C3965R;

/* loaded from: classes4.dex */
public abstract class LoraHomeFragmentBinding extends ViewDataBinding {
    public final TextView aboutButton;
    public final LinearLayout aboutLayout;
    public final LinearLayout baseInfoLayout;
    public final TextView broadView;
    public final TextView checkHintView;
    public final TextView checkIdView;
    public final TextView configButton;
    public final LinearLayout configLayout;
    public final ImageView deleteButton;
    public final TextView emptyLayout;
    public final TextView expandTextView;
    public final TextView frequencyView;
    public final TextView loRaMacView;

    @Bindable
    protected LoRaConfigViewModel mViewModel;
    public final TextView net1;
    public final TextView net2;
    public final TextView netStateView;
    public final EditText networkP1Edit;
    public final EditText networkP2Edit;
    public final TextView networkView;
    public final Button okButton;
    public final TextView powerView;
    public final RecyclerView recyclerView;
    public final RelativeLayout signalTestLayout;
    public final ImageView stateRefreshView;
    public final View stateView;
    public final View topItemView;
    public final RecyclerView topRecyclerView;
    public final TextView updateBut;

    public abstract void setViewModel(LoRaConfigViewModel loRaConfigViewModel);

    /* JADX INFO: Access modifiers changed from: protected */
    public LoraHomeFragmentBinding(Object obj, View view, int i, TextView textView, LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView2, TextView textView3, TextView textView4, TextView textView5, LinearLayout linearLayout3, ImageView imageView, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, EditText editText, EditText editText2, TextView textView13, Button button, TextView textView14, RecyclerView recyclerView, RelativeLayout relativeLayout, ImageView imageView2, View view2, View view3, RecyclerView recyclerView2, TextView textView15) {
        super(obj, view, i);
        this.aboutButton = textView;
        this.aboutLayout = linearLayout;
        this.baseInfoLayout = linearLayout2;
        this.broadView = textView2;
        this.checkHintView = textView3;
        this.checkIdView = textView4;
        this.configButton = textView5;
        this.configLayout = linearLayout3;
        this.deleteButton = imageView;
        this.emptyLayout = textView6;
        this.expandTextView = textView7;
        this.frequencyView = textView8;
        this.loRaMacView = textView9;
        this.net1 = textView10;
        this.net2 = textView11;
        this.netStateView = textView12;
        this.networkP1Edit = editText;
        this.networkP2Edit = editText2;
        this.networkView = textView13;
        this.okButton = button;
        this.powerView = textView14;
        this.recyclerView = recyclerView;
        this.signalTestLayout = relativeLayout;
        this.stateRefreshView = imageView2;
        this.stateView = view2;
        this.topItemView = view3;
        this.topRecyclerView = recyclerView2;
        this.updateBut = textView15;
    }

    public LoRaConfigViewModel getViewModel() {
        return this.mViewModel;
    }

    public static LoraHomeFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraHomeFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LoraHomeFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_home_fragment, viewGroup, z, obj);
    }

    public static LoraHomeFragmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraHomeFragmentBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LoraHomeFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_home_fragment, null, false, obj);
    }

    public static LoraHomeFragmentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraHomeFragmentBinding bind(View view, Object obj) {
        return (LoraHomeFragmentBinding) bind(obj, view, C3965R.layout.lora_home_fragment);
    }
}
