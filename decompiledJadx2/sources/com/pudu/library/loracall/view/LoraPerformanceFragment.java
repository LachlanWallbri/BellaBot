package com.pudu.library.loracall.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import com.pudu.library.loracall.LoraSignalStrength;
import com.pudu.library.loracall.base.BaseFragment;
import com.pudu.library.loracall.viewModel.LoraPerformanceViewModel;
import com.pudu.loracall.library.C3965R;
import com.pudu.loracall.library.databinding.LoraPerformanceFragmentBinding;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: LoraPerformanceFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0013H\u0016R#\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000e¨\u0006\u0015"}, m3961d2 = {"Lcom/pudu/library/loracall/view/LoraPerformanceFragment;", "Lcom/pudu/library/loracall/base/BaseFragment;", "Lcom/pudu/loracall/library/databinding/LoraPerformanceFragmentBinding;", "()V", "rotateAnimation", "Landroid/view/animation/Animation;", "kotlin.jvm.PlatformType", "getRotateAnimation", "()Landroid/view/animation/Animation;", "rotateAnimation$delegate", "Lkotlin/Lazy;", "viewModel", "Lcom/pudu/library/loracall/viewModel/LoraPerformanceViewModel;", "getViewModel", "()Lcom/pudu/library/loracall/viewModel/LoraPerformanceViewModel;", "viewModel$delegate", "initContentView", "", "initData", "", "initView", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoraPerformanceFragment extends BaseFragment<LoraPerformanceFragmentBinding> {

    /* renamed from: rotateAnimation$delegate, reason: from kotlin metadata */
    private final Lazy rotateAnimation;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX INFO: Access modifiers changed from: private */
    public final Animation getRotateAnimation() {
        return (Animation) this.rotateAnimation.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LoraPerformanceViewModel getViewModel() {
        return (LoraPerformanceViewModel) this.viewModel.getValue();
    }

    public LoraPerformanceFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.pudu.library.loracall.view.LoraPerformanceFragment$$special$$inlined$viewModels$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(LoraPerformanceViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudu.library.loracall.view.LoraPerformanceFragment$$special$$inlined$viewModels$2
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
        this.rotateAnimation = LazyKt.lazy(new Function0<Animation>() { // from class: com.pudu.library.loracall.view.LoraPerformanceFragment$rotateAnimation$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Animation invoke() {
                return AnimationUtils.loadAnimation(LoraPerformanceFragment.this.getContext(), C3965R.anim.lora_rotate);
            }
        });
    }

    @Override // com.pudu.library.loracall.base.BaseFragment
    public int initContentView() {
        return C3965R.layout.lora_performance_fragment;
    }

    @Override // com.pudu.library.loracall.base.BaseFragment
    public void initView() {
        Switch r0 = getBinding().switchView;
        Intrinsics.checkExpressionValueIsNotNull(r0, "binding.switchView");
        r0.setChecked(getViewModel().getOpenMode() == 1);
        getBinding().switchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudu.library.loracall.view.LoraPerformanceFragment$initView$1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                LoraPerformanceViewModel viewModel;
                viewModel = LoraPerformanceFragment.this.getViewModel();
                viewModel.setOpenMode(z ? 1 : 0);
            }
        });
        getBinding().rfModuleEdit.setText(getViewModel().getRfModule());
        EditText editText = getBinding().rfModuleEdit;
        Intrinsics.checkExpressionValueIsNotNull(editText, "binding.rfModuleEdit");
        editText.addTextChangedListener(new TextWatcher() { // from class: com.pudu.library.loracall.view.LoraPerformanceFragment$initView$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                LoraPerformanceViewModel viewModel;
                viewModel = LoraPerformanceFragment.this.getViewModel();
                viewModel.setRfModule(String.valueOf(s));
            }
        });
        getBinding().sendButton.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoraPerformanceFragment$initView$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoraPerformanceViewModel viewModel;
                viewModel = LoraPerformanceFragment.this.getViewModel();
                viewModel.test();
            }
        });
        getBinding().signalRefreshView.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoraPerformanceFragment$initView$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Animation rotateAnimation;
                LoraPerformanceViewModel viewModel;
                ImageView imageView = LoraPerformanceFragment.this.getBinding().signalRefreshView;
                rotateAnimation = LoraPerformanceFragment.this.getRotateAnimation();
                imageView.startAnimation(rotateAnimation);
                viewModel = LoraPerformanceFragment.this.getViewModel();
                viewModel.m4292getSignalState();
            }
        });
    }

    @Override // com.pudu.library.loracall.base.BaseFragment
    public void initData() {
        getViewModel().getSignalState().observe(this, new Observer<LoraSignalStrength>() { // from class: com.pudu.library.loracall.view.LoraPerformanceFragment$initData$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(LoraSignalStrength loraSignalStrength) {
                TextView textView = LoraPerformanceFragment.this.getBinding().signalStateView;
                Intrinsics.checkExpressionValueIsNotNull(textView, "binding.signalStateView");
                textView.setText(loraSignalStrength.getSignalStrength());
                TextView textView2 = LoraPerformanceFragment.this.getBinding().receiveCycleView;
                Intrinsics.checkExpressionValueIsNotNull(textView2, "binding.receiveCycleView");
                textView2.setText(loraSignalStrength.getReceiveCycle());
                TextView textView3 = LoraPerformanceFragment.this.getBinding().receiveCountView;
                Intrinsics.checkExpressionValueIsNotNull(textView3, "binding.receiveCountView");
                textView3.setText(loraSignalStrength.getReceiveCount());
                LoraPerformanceFragment.this.getBinding().signalRefreshView.clearAnimation();
            }
        });
    }
}
