package com.pudu.library.loracall.view;

import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.pudu.library.loracall.LoraSignalStrength;
import com.pudu.library.loracall.base.BaseAdapter;
import com.pudu.library.loracall.base.BaseFragment;
import com.pudu.library.loracall.viewModel.LoRaApproveViewModel;
import com.pudu.loracall.library.C3965R;
import com.pudu.loracall.library.databinding.LoraApproveFragmentBinding;
import com.pudu.loracall.library.databinding.LoraFilterPopBinding;
import com.pudu.loracall.library.databinding.LoraPopFilterItemBinding;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: LoRaApproveFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002JF\u0010\u001f\u001a\u00020\f2\u0018\u0010 \u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$0\"0!2\"\b\u0002\u0010%\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$0\"\u0012\u0004\u0012\u00020\u001c\u0018\u00010&H\u0002J\b\u0010'\u001a\u00020$H\u0016J\b\u0010(\u001a\u00020\u001cH\u0016J\b\u0010)\u001a\u00020\u001cH\u0016J\u001c\u0010*\u001a\u00020\u001c2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$0\"H\u0002J\u001c\u0010,\u001a\u00020\u001c2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$0\"H\u0002R#\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\n\u001a\u0004\b\u0011\u0010\u000eR\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\n\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0018\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\n\u001a\u0004\b\u0019\u0010\u000e¨\u0006-"}, m3961d2 = {"Lcom/pudu/library/loracall/view/LoRaApproveFragment;", "Lcom/pudu/library/loracall/base/BaseFragment;", "Lcom/pudu/loracall/library/databinding/LoraApproveFragmentBinding;", "()V", "rotateAnimation", "Landroid/view/animation/Animation;", "kotlin.jvm.PlatformType", "getRotateAnimation", "()Landroid/view/animation/Animation;", "rotateAnimation$delegate", "Lkotlin/Lazy;", "sendModelPop", "Landroid/widget/PopupWindow;", "getSendModelPop", "()Landroid/widget/PopupWindow;", "sendModelPop$delegate", "sendTypePop", "getSendTypePop", "sendTypePop$delegate", "viewModel", "Lcom/pudu/library/loracall/viewModel/LoRaApproveViewModel;", "getViewModel", "()Lcom/pudu/library/loracall/viewModel/LoRaApproveViewModel;", "viewModel$delegate", "wavePop", "getWavePop", "wavePop$delegate", "backgroundAlpha", "", "bgAlpha", "", "getFilterPop", "list", "", "Lkotlin/Pair;", "", "", "clickListener", "Lkotlin/Function1;", "initContentView", "initData", "initView", "setSendTypeView", "it", "setWaveView", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoRaApproveFragment extends BaseFragment<LoraApproveFragmentBinding> {

    /* renamed from: rotateAnimation$delegate, reason: from kotlin metadata */
    private final Lazy rotateAnimation;

    /* renamed from: sendModelPop$delegate, reason: from kotlin metadata */
    private final Lazy sendModelPop;

    /* renamed from: sendTypePop$delegate, reason: from kotlin metadata */
    private final Lazy sendTypePop;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* renamed from: wavePop$delegate, reason: from kotlin metadata */
    private final Lazy wavePop;

    /* JADX INFO: Access modifiers changed from: private */
    public final Animation getRotateAnimation() {
        return (Animation) this.rotateAnimation.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PopupWindow getSendModelPop() {
        return (PopupWindow) this.sendModelPop.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PopupWindow getSendTypePop() {
        return (PopupWindow) this.sendTypePop.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LoRaApproveViewModel getViewModel() {
        return (LoRaApproveViewModel) this.viewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PopupWindow getWavePop() {
        return (PopupWindow) this.wavePop.getValue();
    }

    public LoRaApproveFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$$special$$inlined$viewModels$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(LoRaApproveViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$$special$$inlined$viewModels$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) Function0.this.invoke()).getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "ownerProducer().viewModelStore");
                return viewModelStore;
            }
        }, (Function0) null);
        this.sendTypePop = LazyKt.lazy(new Function0<PopupWindow>() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$sendTypePop$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PopupWindow invoke() {
                LoRaApproveViewModel viewModel;
                PopupWindow filterPop;
                LoRaApproveFragment loRaApproveFragment = LoRaApproveFragment.this;
                viewModel = loRaApproveFragment.getViewModel();
                filterPop = loRaApproveFragment.getFilterPop(viewModel.getSendTypeList(), new Function1<Pair<? extends String, ? extends Integer>, Unit>() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$sendTypePop$2.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends String, ? extends Integer> pair) {
                        invoke2((Pair<String, Integer>) pair);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Pair<String, Integer> it) {
                        LoRaApproveViewModel viewModel2;
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        viewModel2 = LoRaApproveFragment.this.getViewModel();
                        viewModel2.getParam().setSendType(it);
                        LoRaApproveFragment.this.setSendTypeView(it);
                    }
                });
                return filterPop;
            }
        });
        this.wavePop = LazyKt.lazy(new Function0<PopupWindow>() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$wavePop$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PopupWindow invoke() {
                LoRaApproveViewModel viewModel;
                PopupWindow filterPop;
                LoRaApproveFragment loRaApproveFragment = LoRaApproveFragment.this;
                viewModel = loRaApproveFragment.getViewModel();
                filterPop = loRaApproveFragment.getFilterPop(viewModel.getWaveList(), new Function1<Pair<? extends String, ? extends Integer>, Unit>() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$wavePop$2.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends String, ? extends Integer> pair) {
                        invoke2((Pair<String, Integer>) pair);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Pair<String, Integer> it) {
                        LoRaApproveViewModel viewModel2;
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        viewModel2 = LoRaApproveFragment.this.getViewModel();
                        viewModel2.getParam().setWave(it);
                        LoRaApproveFragment.this.setWaveView(it);
                    }
                });
                return filterPop;
            }
        });
        this.sendModelPop = LazyKt.lazy(new Function0<PopupWindow>() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$sendModelPop$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PopupWindow invoke() {
                LoRaApproveViewModel viewModel;
                PopupWindow filterPop;
                LoRaApproveFragment loRaApproveFragment = LoRaApproveFragment.this;
                viewModel = loRaApproveFragment.getViewModel();
                filterPop = loRaApproveFragment.getFilterPop(viewModel.getSendModelList(), new Function1<Pair<? extends String, ? extends Integer>, Unit>() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$sendModelPop$2.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends String, ? extends Integer> pair) {
                        invoke2((Pair<String, Integer>) pair);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Pair<String, Integer> it) {
                        LoRaApproveViewModel viewModel2;
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        viewModel2 = LoRaApproveFragment.this.getViewModel();
                        viewModel2.getParam().setSendModel(it);
                        LoRaApproveFragment.this.setSendTypeView(it);
                    }
                });
                return filterPop;
            }
        });
        this.rotateAnimation = LazyKt.lazy(new Function0<Animation>() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$rotateAnimation$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Animation invoke() {
                return AnimationUtils.loadAnimation(LoRaApproveFragment.this.getContext(), C3965R.anim.lora_rotate);
            }
        });
    }

    @Override // com.pudu.library.loracall.base.BaseFragment
    public int initContentView() {
        return C3965R.layout.lora_approve_fragment;
    }

    @Override // com.pudu.library.loracall.base.BaseFragment
    public void initView() {
        Switch r0 = getBinding().switchView;
        Intrinsics.checkExpressionValueIsNotNull(r0, "binding.switchView");
        r0.setChecked(getViewModel().getParam().getJoinMode() == 1);
        getBinding().switchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$initView$1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                LoRaApproveViewModel viewModel;
                viewModel = LoRaApproveFragment.this.getViewModel();
                viewModel.getParam().setJoinMode(z ? 1 : 0);
            }
        });
        TextView textView = getBinding().waveformView;
        Intrinsics.checkExpressionValueIsNotNull(textView, "binding.waveformView");
        textView.setText(getViewModel().getParam().getWave().getFirst());
        getBinding().waveformView.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$initView$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PopupWindow wavePop;
                LoRaApproveFragment.this.backgroundAlpha(0.4f);
                wavePop = LoRaApproveFragment.this.getWavePop();
                wavePop.showAsDropDown(view);
            }
        });
        TextView textView2 = getBinding().sendType;
        Intrinsics.checkExpressionValueIsNotNull(textView2, "binding.sendType");
        textView2.setText(getViewModel().getParam().getSendType().getFirst());
        if (getViewModel().getParam().getSendType().getSecond().intValue() == 1) {
            RelativeLayout relativeLayout = getBinding().fixedLayout;
            Intrinsics.checkExpressionValueIsNotNull(relativeLayout, "binding.fixedLayout");
            relativeLayout.setVisibility(0);
        } else {
            RelativeLayout relativeLayout2 = getBinding().fixedLayout;
            Intrinsics.checkExpressionValueIsNotNull(relativeLayout2, "binding.fixedLayout");
            relativeLayout2.setVisibility(8);
        }
        getBinding().sendType.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$initView$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PopupWindow sendTypePop;
                LoRaApproveFragment.this.backgroundAlpha(0.4f);
                sendTypePop = LoRaApproveFragment.this.getSendTypePop();
                sendTypePop.showAsDropDown(view);
            }
        });
        getBinding().sendPowerEdit.setText(getViewModel().getParam().getSendPower());
        EditText editText = getBinding().sendPowerEdit;
        Intrinsics.checkExpressionValueIsNotNull(editText, "binding.sendPowerEdit");
        editText.addTextChangedListener(new TextWatcher() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$initView$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                LoRaApproveViewModel viewModel;
                viewModel = LoRaApproveFragment.this.getViewModel();
                viewModel.getParam().setSendPower(String.valueOf(s));
            }
        });
        getBinding().powerEdit.setText(getViewModel().getParam().getFixedFrequency());
        EditText editText2 = getBinding().powerEdit;
        Intrinsics.checkExpressionValueIsNotNull(editText2, "binding.powerEdit");
        editText2.addTextChangedListener(new TextWatcher() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$initView$$inlined$doAfterTextChanged$2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                LoRaApproveViewModel viewModel;
                viewModel = LoRaApproveFragment.this.getViewModel();
                viewModel.getParam().setFixedFrequency(String.valueOf(s));
            }
        });
        getBinding().silentValueEdit.setText(getViewModel().getParam().getSilentValue());
        EditText editText3 = getBinding().silentValueEdit;
        Intrinsics.checkExpressionValueIsNotNull(editText3, "binding.silentValueEdit");
        editText3.addTextChangedListener(new TextWatcher() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$initView$$inlined$doAfterTextChanged$3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                LoRaApproveViewModel viewModel;
                viewModel = LoRaApproveFragment.this.getViewModel();
                viewModel.getParam().setSilentValue(String.valueOf(s));
            }
        });
        TextView textView3 = getBinding().sendModelView;
        Intrinsics.checkExpressionValueIsNotNull(textView3, "binding.sendModelView");
        textView3.setText(getViewModel().getParam().getSendModel().getFirst());
        getBinding().sendModelView.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$initView$7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PopupWindow sendModelPop;
                LoRaApproveFragment.this.backgroundAlpha(0.4f);
                sendModelPop = LoRaApproveFragment.this.getSendModelPop();
                sendModelPop.showAsDropDown(view);
            }
        });
        getBinding().sendCycleEdit.setText(getViewModel().getParam().getSendCycle());
        EditText editText4 = getBinding().sendCycleEdit;
        Intrinsics.checkExpressionValueIsNotNull(editText4, "binding.sendCycleEdit");
        editText4.addTextChangedListener(new TextWatcher() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$initView$$inlined$doAfterTextChanged$4
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                LoRaApproveViewModel viewModel;
                viewModel = LoRaApproveFragment.this.getViewModel();
                viewModel.getParam().setSendCycle(String.valueOf(s));
            }
        });
        getBinding().receiveFrequencyEdit.setText(getViewModel().getParam().getReceiveFrequency());
        EditText editText5 = getBinding().receiveFrequencyEdit;
        Intrinsics.checkExpressionValueIsNotNull(editText5, "binding.receiveFrequencyEdit");
        editText5.addTextChangedListener(new TextWatcher() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$initView$$inlined$doAfterTextChanged$5
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                LoRaApproveViewModel viewModel;
                viewModel = LoRaApproveFragment.this.getViewModel();
                viewModel.getParam().setReceiveFrequency(String.valueOf(s));
            }
        });
        getBinding().rfModuleEdit.setText(getViewModel().getParam().getRfModule());
        EditText editText6 = getBinding().rfModuleEdit;
        Intrinsics.checkExpressionValueIsNotNull(editText6, "binding.rfModuleEdit");
        editText6.addTextChangedListener(new TextWatcher() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$initView$$inlined$doAfterTextChanged$6
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                LoRaApproveViewModel viewModel;
                viewModel = LoRaApproveFragment.this.getViewModel();
                viewModel.getParam().setRfModule(String.valueOf(s));
            }
        });
        getBinding().sendButton.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$initView$11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoRaApproveViewModel viewModel;
                viewModel = LoRaApproveFragment.this.getViewModel();
                viewModel.sendParam();
            }
        });
        getBinding().signalRefreshView.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$initView$12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Animation rotateAnimation;
                LoRaApproveViewModel viewModel;
                ImageView imageView = LoRaApproveFragment.this.getBinding().signalRefreshView;
                rotateAnimation = LoRaApproveFragment.this.getRotateAnimation();
                imageView.startAnimation(rotateAnimation);
                viewModel = LoRaApproveFragment.this.getViewModel();
                viewModel.m4289getSignalState();
            }
        });
    }

    @Override // com.pudu.library.loracall.base.BaseFragment
    public void initData() {
        getViewModel().getSignalState().observe(this, new Observer<LoraSignalStrength>() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$initData$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(LoraSignalStrength loraSignalStrength) {
                TextView textView = LoRaApproveFragment.this.getBinding().signalStateView;
                Intrinsics.checkExpressionValueIsNotNull(textView, "binding.signalStateView");
                textView.setText(loraSignalStrength.getSignalStrength());
                TextView textView2 = LoRaApproveFragment.this.getBinding().receiveCycleView;
                Intrinsics.checkExpressionValueIsNotNull(textView2, "binding.receiveCycleView");
                textView2.setText(loraSignalStrength.getReceiveCycle());
                TextView textView3 = LoRaApproveFragment.this.getBinding().receiveCountView;
                Intrinsics.checkExpressionValueIsNotNull(textView3, "binding.receiveCountView");
                textView3.setText(loraSignalStrength.getReceiveCount());
                LoRaApproveFragment.this.getBinding().signalRefreshView.clearAnimation();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ PopupWindow getFilterPop$default(LoRaApproveFragment loRaApproveFragment, List list, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = (Function1) null;
        }
        return loRaApproveFragment.getFilterPop(list, function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PopupWindow getFilterPop(List<Pair<String, Integer>> list, final Function1<? super Pair<String, Integer>, Unit> clickListener) {
        LoraFilterPopBinding inflate = LoraFilterPopBinding.inflate(LayoutInflater.from(getContext()));
        Intrinsics.checkExpressionValueIsNotNull(inflate, "LoraFilterPopBinding.inf…utInflater.from(context))");
        final PopupWindow popupWindow = new PopupWindow();
        popupWindow.setContentView(inflate.getRoot());
        popupWindow.setWidth(-2);
        popupWindow.setHeight(-2);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$getFilterPop$1
            @Override // android.widget.PopupWindow.OnDismissListener
            public final void onDismiss() {
                LoRaApproveFragment.this.backgroundAlpha(1.0f);
            }
        });
        BaseAdapter baseAdapter = new BaseAdapter(C3965R.layout.lora_pop_filter_item, null, null, null, 14, null);
        baseAdapter.setBindHolder(new Function1<ViewDataBinding, Unit>() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$getFilterPop$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ViewDataBinding viewDataBinding) {
                invoke2(viewDataBinding);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ViewDataBinding it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                LoraPopFilterItemBinding loraPopFilterItemBinding = (LoraPopFilterItemBinding) it;
                final Pair<String, Integer> item = loraPopFilterItemBinding.getItem();
                if (item == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(item, "itemBinding.item!!");
                loraPopFilterItemBinding.nameView.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoRaApproveFragment$getFilterPop$2.1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        Function1 function1 = Function1.this;
                        if (function1 != null) {
                        }
                        popupWindow.dismiss();
                    }
                });
            }
        });
        RecyclerView recyclerView = inflate.recyclerView;
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "popBinding.recyclerView");
        recyclerView.setAdapter(baseAdapter);
        baseAdapter.submitList(list);
        return popupWindow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void backgroundAlpha(float bgAlpha) {
        FragmentActivity it = getActivity();
        if (it != null) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            Window window = it.getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window, "it.window");
            WindowManager.LayoutParams attributes = window.getAttributes();
            Intrinsics.checkExpressionValueIsNotNull(attributes, "it.window.attributes");
            attributes.alpha = bgAlpha;
            Window window2 = it.getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window2, "it.window");
            window2.setAttributes(attributes);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setWaveView(Pair<String, Integer> it) {
        TextView textView = getBinding().waveformView;
        Intrinsics.checkExpressionValueIsNotNull(textView, "binding.waveformView");
        textView.setText(it.getFirst());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSendTypeView(Pair<String, Integer> it) {
        TextView textView = getBinding().sendType;
        Intrinsics.checkExpressionValueIsNotNull(textView, "binding.sendType");
        textView.setText(it.getFirst());
        if (it.getSecond().intValue() == 1) {
            RelativeLayout relativeLayout = getBinding().fixedLayout;
            Intrinsics.checkExpressionValueIsNotNull(relativeLayout, "binding.fixedLayout");
            relativeLayout.setVisibility(0);
        } else {
            RelativeLayout relativeLayout2 = getBinding().fixedLayout;
            Intrinsics.checkExpressionValueIsNotNull(relativeLayout2, "binding.fixedLayout");
            relativeLayout2.setVisibility(8);
        }
    }
}
