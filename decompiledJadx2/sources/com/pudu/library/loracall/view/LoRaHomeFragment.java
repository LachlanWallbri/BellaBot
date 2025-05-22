package com.pudu.library.loracall.view;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.iflytek.aiui.AIUIConstant;
import com.pudu.library.loracall.KotlinUtilsKt;
import com.pudu.library.loracall.LoRaHelper;
import com.pudu.library.loracall.base.BaseAdapter;
import com.pudu.library.loracall.base.BaseFragment;
import com.pudu.library.loracall.base.BasePop;
import com.pudu.library.loracall.dao.TableMatchBean;
import com.pudu.library.loracall.viewModel.LoRaConfigViewModel;
import com.pudu.library.loracall.viewModel.LoRaTableMatchViewModel;
import com.pudu.loracall.library.C3965R;
import com.pudu.loracall.library.databinding.LoraHintPopBinding;
import com.pudu.loracall.library.databinding.LoraHomeFragmentBinding;
import com.pudu.loracall.library.databinding.LoraPopBinding;
import com.pudu.loracall.library.databinding.LoraPopItemBinding;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;

/* compiled from: LoRaHomeFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0002J\u0010\u00101\u001a\u00020.2\u0006\u00102\u001a\u000203H\u0002J\b\u00104\u001a\u00020\u0016H\u0002J$\u00105\u001a\u00020$2\u0006\u00106\u001a\u0002072\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020.09H\u0002J\b\u0010;\u001a\u00020$H\u0002J\b\u0010<\u001a\u00020$H\u0002J\b\u0010=\u001a\u000203H\u0016J\b\u0010>\u001a\u00020.H\u0016J\b\u0010?\u001a\u00020.H\u0002J\b\u0010@\u001a\u00020.H\u0016J\b\u0010A\u001a\u00020BH\u0016J\b\u0010C\u001a\u00020.H\u0016J\u0018\u0010D\u001a\u00020.2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u000203H\u0007J\u0012\u0010H\u001a\u00020.2\b\u0010G\u001a\u0004\u0018\u00010IH\u0002J\n\u0010J\u001a\u00020K*\u000207R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\rR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\t\u001a\u0004\b\u0017\u0010\u0018R#\u0010\u001a\u001a\n \u001c*\u0004\u0018\u00010\u001b0\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\t\u001a\u0004\b\u001d\u0010\u001eR\u001b\u0010 \u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\t\u001a\u0004\b!\u0010\u0007R\u001b\u0010#\u001a\u00020$8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\t\u001a\u0004\b%\u0010&R\u001b\u0010(\u001a\u00020)8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\t\u001a\u0004\b*\u0010+¨\u0006L"}, m3961d2 = {"Lcom/pudu/library/loracall/view/LoRaHomeFragment;", "Lcom/pudu/library/loracall/base/BaseFragment;", "Lcom/pudu/loracall/library/databinding/LoraHomeFragmentBinding;", "()V", "adapter", "Lcom/pudu/library/loracall/base/BaseAdapter;", "getAdapter", "()Lcom/pudu/library/loracall/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "configViewModel", "Lcom/pudu/library/loracall/viewModel/LoRaConfigViewModel;", "getConfigViewModel", "()Lcom/pudu/library/loracall/viewModel/LoRaConfigViewModel;", "configViewModel$delegate", "job", "Lkotlinx/coroutines/Job;", "getJob", "()Lkotlinx/coroutines/Job;", "setJob", "(Lkotlinx/coroutines/Job;)V", "pop", "Landroid/widget/PopupWindow;", "getPop", "()Landroid/widget/PopupWindow;", "pop$delegate", "rotateAnimation", "Landroid/view/animation/Animation;", "kotlin.jvm.PlatformType", "getRotateAnimation", "()Landroid/view/animation/Animation;", "rotateAnimation$delegate", "topAdapter", "getTopAdapter", "topAdapter$delegate", "updatePop", "Landroidx/fragment/app/DialogFragment;", "getUpdatePop", "()Landroidx/fragment/app/DialogFragment;", "updatePop$delegate", "viewModel", "Lcom/pudu/library/loracall/viewModel/LoRaTableMatchViewModel;", "getViewModel", "()Lcom/pudu/library/loracall/viewModel/LoRaTableMatchViewModel;", "viewModel$delegate", "backgroundAlpha", "", "bgAlpha", "", "changeLoraState", "it", "", "getFilterPop", "getHintPop", AIUIConstant.KEY_CONTENT, "", "click", "Lkotlin/Function1;", "Landroid/view/View;", "getUpdateFilePop", "getUpdateProgressPop", "initContentView", "initData", "initLoraData", "initView", "initViewModel", "Landroidx/lifecycle/ViewModel;", "onDestroyView", "setDrawableRight", "view", "Landroid/widget/TextView;", "drawable", "setIntrinsicBounds", "Landroid/graphics/drawable/Drawable;", "isOneDecimal", "", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoRaHomeFragment extends BaseFragment<LoraHomeFragmentBinding> {

    /* renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    /* renamed from: configViewModel$delegate, reason: from kotlin metadata */
    private final Lazy configViewModel;
    private Job job;

    /* renamed from: pop$delegate, reason: from kotlin metadata */
    private final Lazy pop;

    /* renamed from: rotateAnimation$delegate, reason: from kotlin metadata */
    private final Lazy rotateAnimation;

    /* renamed from: topAdapter$delegate, reason: from kotlin metadata */
    private final Lazy topAdapter;

    /* renamed from: updatePop$delegate, reason: from kotlin metadata */
    private final Lazy updatePop;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX INFO: Access modifiers changed from: private */
    public final BaseAdapter getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LoRaConfigViewModel getConfigViewModel() {
        return (LoRaConfigViewModel) this.configViewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PopupWindow getPop() {
        return (PopupWindow) this.pop.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Animation getRotateAnimation() {
        return (Animation) this.rotateAnimation.getValue();
    }

    private final BaseAdapter getTopAdapter() {
        return (BaseAdapter) this.topAdapter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DialogFragment getUpdatePop() {
        return (DialogFragment) this.updatePop.getValue();
    }

    public final LoRaTableMatchViewModel getViewModel() {
        return (LoRaTableMatchViewModel) this.viewModel.getValue();
    }

    public LoRaHomeFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$$special$$inlined$viewModels$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        Function0 function02 = (Function0) null;
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(LoRaTableMatchViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$$special$$inlined$viewModels$2
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
        }, function02);
        final Function0<Fragment> function03 = new Function0<Fragment>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$$special$$inlined$viewModels$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        this.configViewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(LoRaConfigViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$$special$$inlined$viewModels$4
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
        }, function02);
        this.topAdapter = LazyKt.lazy(new Function0<BaseAdapter>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$topAdapter$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter invoke() {
                return new BaseAdapter(C3965R.layout.lora_table_top_item, LoRaHomeFragment.this.getViewModel(), null, null, 12, null);
            }
        });
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$adapter$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter invoke() {
                return new BaseAdapter(C3965R.layout.lora_table_content_item, LoRaHomeFragment.this.getViewModel(), null, null, 12, null);
            }
        });
        this.updatePop = LazyKt.lazy(new Function0<DialogFragment>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$updatePop$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DialogFragment invoke() {
                DialogFragment updateFilePop;
                updateFilePop = LoRaHomeFragment.this.getUpdateFilePop();
                return updateFilePop;
            }
        });
        this.pop = LazyKt.lazy(new Function0<PopupWindow>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$pop$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PopupWindow invoke() {
                PopupWindow filterPop;
                filterPop = LoRaHomeFragment.this.getFilterPop();
                return filterPop;
            }
        });
        this.rotateAnimation = LazyKt.lazy(new Function0<Animation>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$rotateAnimation$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Animation invoke() {
                return AnimationUtils.loadAnimation(LoRaHomeFragment.this.getContext(), C3965R.anim.lora_rotate);
            }
        });
    }

    @Override // com.pudu.library.loracall.base.BaseFragment
    public ViewModel initViewModel() {
        return getConfigViewModel();
    }

    @Override // com.pudu.library.loracall.base.BaseFragment
    public int initContentView() {
        return C3965R.layout.lora_home_fragment;
    }

    @Override // com.pudu.library.loracall.base.BaseFragment
    public void initView() {
        RecyclerView recyclerView = getBinding().topRecyclerView;
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "binding.topRecyclerView");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        RecyclerView recyclerView2 = getBinding().topRecyclerView;
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "binding.topRecyclerView");
        recyclerView2.setAdapter(getTopAdapter());
        RecyclerView recyclerView3 = getBinding().recyclerView;
        Intrinsics.checkExpressionValueIsNotNull(recyclerView3, "binding.recyclerView");
        recyclerView3.setLayoutManager(new GridLayoutManager(getContext(), 3));
        RecyclerView recyclerView4 = getBinding().recyclerView;
        Intrinsics.checkExpressionValueIsNotNull(recyclerView4, "binding.recyclerView");
        recyclerView4.setAdapter(getAdapter());
        getAdapter().setBindHolder(new LoRaHomeFragment$initView$1(this));
        getBinding().configButton.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$initView$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LinearLayout linearLayout = LoRaHomeFragment.this.getBinding().configLayout;
                Intrinsics.checkExpressionValueIsNotNull(linearLayout, "binding.configLayout");
                if (linearLayout.getVisibility() == 8) {
                    LinearLayout linearLayout2 = LoRaHomeFragment.this.getBinding().configLayout;
                    Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "binding.configLayout");
                    linearLayout2.setVisibility(0);
                    LoRaHomeFragment loRaHomeFragment = LoRaHomeFragment.this;
                    TextView textView = loRaHomeFragment.getBinding().configButton;
                    Intrinsics.checkExpressionValueIsNotNull(textView, "binding.configButton");
                    loRaHomeFragment.setDrawableRight(textView, C3965R.mipmap.lora_down_icon);
                    return;
                }
                LinearLayout linearLayout3 = LoRaHomeFragment.this.getBinding().configLayout;
                Intrinsics.checkExpressionValueIsNotNull(linearLayout3, "binding.configLayout");
                linearLayout3.setVisibility(8);
                LoRaHomeFragment loRaHomeFragment2 = LoRaHomeFragment.this;
                TextView textView2 = loRaHomeFragment2.getBinding().configButton;
                Intrinsics.checkExpressionValueIsNotNull(textView2, "binding.configButton");
                loRaHomeFragment2.setDrawableRight(textView2, C3965R.mipmap.lora_right_icon);
            }
        });
        getBinding().aboutButton.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$initView$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LinearLayout linearLayout = LoRaHomeFragment.this.getBinding().aboutLayout;
                Intrinsics.checkExpressionValueIsNotNull(linearLayout, "binding.aboutLayout");
                if (linearLayout.getVisibility() == 8) {
                    LinearLayout linearLayout2 = LoRaHomeFragment.this.getBinding().aboutLayout;
                    Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "binding.aboutLayout");
                    linearLayout2.setVisibility(0);
                    LoRaHomeFragment loRaHomeFragment = LoRaHomeFragment.this;
                    TextView textView = loRaHomeFragment.getBinding().aboutButton;
                    Intrinsics.checkExpressionValueIsNotNull(textView, "binding.aboutButton");
                    loRaHomeFragment.setDrawableRight(textView, C3965R.mipmap.lora_down_icon);
                    return;
                }
                LinearLayout linearLayout3 = LoRaHomeFragment.this.getBinding().aboutLayout;
                Intrinsics.checkExpressionValueIsNotNull(linearLayout3, "binding.aboutLayout");
                linearLayout3.setVisibility(8);
                LoRaHomeFragment loRaHomeFragment2 = LoRaHomeFragment.this;
                TextView textView2 = loRaHomeFragment2.getBinding().aboutButton;
                Intrinsics.checkExpressionValueIsNotNull(textView2, "binding.aboutButton");
                loRaHomeFragment2.setDrawableRight(textView2, C3965R.mipmap.lora_right_icon);
            }
        });
        getBinding().okButton.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$initView$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoRaConfigViewModel configViewModel;
                configViewModel = LoRaHomeFragment.this.getConfigViewModel();
                configViewModel.saveLoRaConfig();
            }
        });
        getBinding().expandTextView.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$initView$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PopupWindow pop;
                LoRaHomeFragment.this.backgroundAlpha(0.4f);
                pop = LoRaHomeFragment.this.getPop();
                pop.showAsDropDown(view);
            }
        });
        getBinding().deleteButton.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$initView$6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoRaHomeFragment.this.getViewModel().getDevAdder().postValue("");
            }
        });
        getBinding().stateRefreshView.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$initView$7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Animation rotateAnimation;
                ImageView imageView = LoRaHomeFragment.this.getBinding().stateRefreshView;
                rotateAnimation = LoRaHomeFragment.this.getRotateAnimation();
                imageView.startAnimation(rotateAnimation);
                LoRaHomeFragment.this.getViewModel().checkState();
            }
        });
        getBinding().updateBut.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$initView$8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DialogFragment updatePop;
                updatePop = LoRaHomeFragment.this.getUpdatePop();
                updatePop.show(LoRaHomeFragment.this.getChildFragmentManager(), "");
            }
        });
        getConfigViewModel().getLoRaMac().observe(this, new Observer<String>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$initView$9
            @Override // androidx.lifecycle.Observer
            public final void onChanged(String str) {
                TextView textView = LoRaHomeFragment.this.getBinding().loRaMacView;
                Intrinsics.checkExpressionValueIsNotNull(textView, "binding.loRaMacView");
                textView.setText(str);
            }
        });
    }

    @Override // com.pudu.library.loracall.base.BaseFragment
    public void initData() {
        initLoraData();
        if (getViewModel().getIsGroup()) {
            RecyclerView recyclerView = getBinding().topRecyclerView;
            Intrinsics.checkExpressionValueIsNotNull(recyclerView, "binding.topRecyclerView");
            recyclerView.setVisibility(0);
        } else {
            RecyclerView recyclerView2 = getBinding().topRecyclerView;
            Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "binding.topRecyclerView");
            recyclerView2.setVisibility(8);
        }
        getTopAdapter().submitList(getViewModel().getTopList());
        LoRaHomeFragment loRaHomeFragment = this;
        getViewModel().getTableList().observe(loRaHomeFragment, new Observer<List<? extends TableMatchBean>>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$initData$1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends TableMatchBean> list) {
                onChanged2((List<TableMatchBean>) list);
            }

            /* renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<TableMatchBean> list) {
                BaseAdapter adapter;
                adapter = LoRaHomeFragment.this.getAdapter();
                adapter.submitList(list);
                if (list.isEmpty()) {
                    TextView textView = LoRaHomeFragment.this.getBinding().emptyLayout;
                    Intrinsics.checkExpressionValueIsNotNull(textView, "binding.emptyLayout");
                    textView.setVisibility(0);
                } else {
                    TextView textView2 = LoRaHomeFragment.this.getBinding().emptyLayout;
                    Intrinsics.checkExpressionValueIsNotNull(textView2, "binding.emptyLayout");
                    textView2.setVisibility(8);
                }
            }
        });
        getViewModel().getDevAdder().observe(loRaHomeFragment, new Observer<String>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$initData$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(String it) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.length() > 0) {
                    TextView textView = LoRaHomeFragment.this.getBinding().checkIdView;
                    Intrinsics.checkExpressionValueIsNotNull(textView, "binding.checkIdView");
                    textView.setText(LoRaHomeFragment.this.getString(C3965R.string.lora_key_detected) + it);
                    ImageView imageView = LoRaHomeFragment.this.getBinding().deleteButton;
                    Intrinsics.checkExpressionValueIsNotNull(imageView, "binding.deleteButton");
                    imageView.setVisibility(0);
                    LoRaHomeFragment.this.getBinding().stateView.setBackgroundColor(LoRaHomeFragment.this.getResources().getColor(C3965R.color.lora_1CC33D));
                    return;
                }
                TextView textView2 = LoRaHomeFragment.this.getBinding().checkIdView;
                Intrinsics.checkExpressionValueIsNotNull(textView2, "binding.checkIdView");
                textView2.setText(LoRaHomeFragment.this.getString(C3965R.string.lora_twice_detect));
                ImageView imageView2 = LoRaHomeFragment.this.getBinding().deleteButton;
                Intrinsics.checkExpressionValueIsNotNull(imageView2, "binding.deleteButton");
                imageView2.setVisibility(8);
                LoRaHomeFragment.this.getBinding().stateView.setBackgroundColor(LoRaHomeFragment.this.getResources().getColor(C3965R.color.lora_A8A8A8));
            }
        });
        getViewModel().getLoraState().observe(loRaHomeFragment, new Observer<Integer>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$initData$3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Integer it) {
                LoRaHomeFragment loRaHomeFragment2 = LoRaHomeFragment.this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                loRaHomeFragment2.changeLoraState(it.intValue());
            }
        });
        final FragmentActivity activity = getActivity();
        if (activity != null) {
            KotlinUtilsKt.log(this, "activity------", new Function0<String>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$initData$4$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    String name = FragmentActivity.this.getClass().getName();
                    Intrinsics.checkExpressionValueIsNotNull(name, "it::class.java.name");
                    return name;
                }
            });
            if (!Intrinsics.areEqual(activity.getClass().getName(), "com.pudu.lorabuttoncall.MainActivity")) {
                getLifecycle().addObserver(new LifecycleObserver() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$initData$4$2
                    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
                    public final void onResume() {
                        KotlinUtilsKt.log(this, "activity------", new Function0<String>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$initData$4$2$onResume$1
                            @Override // kotlin.jvm.functions.Function0
                            public final String invoke() {
                                return "onResume";
                            }
                        });
                        LoRaHelper.INSTANCE.setBindState(true);
                    }

                    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
                    public final void onPause() {
                        KotlinUtilsKt.log(this, "activity------", new Function0<String>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$initData$4$2$onPause$1
                            @Override // kotlin.jvm.functions.Function0
                            public final String invoke() {
                                return "onPause";
                            }
                        });
                        LoRaHelper.INSTANCE.setBindState(false);
                    }
                });
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    public final Job getJob() {
        return this.job;
    }

    public final void setJob(Job job) {
        this.job = job;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initLoraData() {
        Job launch$default;
        getConfigViewModel().m4291getLoRaVersion();
        getConfigViewModel().m4290getLoRaConfig();
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new LoRaHomeFragment$initLoraData$1(this, null), 3, null);
        this.job = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void changeLoraState(int it) {
        getBinding().stateRefreshView.clearAnimation();
        if (it == -1) {
            TextView textView = getBinding().netStateView;
            Intrinsics.checkExpressionValueIsNotNull(textView, "binding.netStateView");
            textView.setText("");
            return;
        }
        if (it == 0) {
            TextView textView2 = getBinding().netStateView;
            Intrinsics.checkExpressionValueIsNotNull(textView2, "binding.netStateView");
            textView2.setText(getString(C3965R.string.lora_unregistered));
            getBinding().netStateView.setTextColor(AppCompatResources.getColorStateList(requireContext(), C3965R.color.lora_A8A8A8));
            return;
        }
        if (it == 1) {
            TextView textView3 = getBinding().netStateView;
            Intrinsics.checkExpressionValueIsNotNull(textView3, "binding.netStateView");
            textView3.setText(getString(C3965R.string.lora_registered));
            getBinding().netStateView.setTextColor(AppCompatResources.getColorStateList(requireContext(), C3965R.color.lora_0072FF));
            return;
        }
        if (it == 2) {
            TextView textView4 = getBinding().netStateView;
            Intrinsics.checkExpressionValueIsNotNull(textView4, "binding.netStateView");
            textView4.setText(getString(C3965R.string.lora_has_logged));
            getBinding().netStateView.setTextColor(AppCompatResources.getColorStateList(requireContext(), C3965R.color.lora_1CC33D));
            return;
        }
        if (it != 3) {
            return;
        }
        TextView textView5 = getBinding().netStateView;
        Intrinsics.checkExpressionValueIsNotNull(textView5, "binding.netStateView");
        textView5.setText(getString(C3965R.string.lora_disconnect));
        getBinding().netStateView.setTextColor(AppCompatResources.getColorStateList(requireContext(), C3965R.color.lora_FB313B));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PopupWindow getFilterPop() {
        LoraPopBinding inflate = LoraPopBinding.inflate(LayoutInflater.from(getContext()));
        Intrinsics.checkExpressionValueIsNotNull(inflate, "LoraPopBinding.inflate(L…utInflater.from(context))");
        final PopupWindow popupWindow = new PopupWindow();
        popupWindow.setContentView(inflate.getRoot());
        popupWindow.setWidth(-2);
        popupWindow.setHeight(-2);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$getFilterPop$1
            @Override // android.widget.PopupWindow.OnDismissListener
            public final void onDismiss() {
                LoRaHomeFragment.this.backgroundAlpha(1.0f);
            }
        });
        BaseAdapter baseAdapter = new BaseAdapter(C3965R.layout.lora_pop_item, null, null, null, 14, null);
        baseAdapter.setBindHolder(new Function1<ViewDataBinding, Unit>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$getFilterPop$2
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
                LoraPopItemBinding loraPopItemBinding = (LoraPopItemBinding) it;
                final Pair<String, Byte> item = loraPopItemBinding.getItem();
                if (item == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(item, "itemBinding.item!!");
                loraPopItemBinding.nameView.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$getFilterPop$2.1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        LoRaConfigViewModel configViewModel;
                        configViewModel = LoRaHomeFragment.this.getConfigViewModel();
                        configViewModel.setExpandData(item);
                        popupWindow.dismiss();
                    }
                });
            }
        });
        RecyclerView recyclerView = inflate.recyclerView;
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "popBinding.recyclerView");
        recyclerView.setAdapter(baseAdapter);
        baseAdapter.submitList(getConfigViewModel().getPopList());
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
    public final DialogFragment getHintPop(final String content, final Function1<? super View, Unit> click) {
        return new BasePop(C3965R.layout.lora_hint_pop, false, new Function1<WindowManager.LayoutParams, Unit>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$getHintPop$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WindowManager.LayoutParams layoutParams) {
                invoke2(layoutParams);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WindowManager.LayoutParams receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                receiver.gravity = 17;
            }
        }, new Function2<LoraHintPopBinding, BasePop<LoraHintPopBinding>, Unit>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$getHintPop$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(LoraHintPopBinding loraHintPopBinding, BasePop<LoraHintPopBinding> basePop) {
                invoke2(loraHintPopBinding, basePop);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LoraHintPopBinding receiver, final BasePop<LoraHintPopBinding> pop) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                Intrinsics.checkParameterIsNotNull(pop, "pop");
                TextView contentView = receiver.contentView;
                Intrinsics.checkExpressionValueIsNotNull(contentView, "contentView");
                contentView.setText(content);
                receiver.cancelView.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$getHintPop$2.1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BasePop.this.dismiss();
                    }
                });
                receiver.okView.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$getHintPop$2.2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View it) {
                        Function1 function1 = click;
                        Intrinsics.checkExpressionValueIsNotNull(it, "it");
                        function1.invoke(it);
                        pop.dismiss();
                    }
                });
                receiver.closeBut.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$getHintPop$2.3
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BasePop.this.dismiss();
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DialogFragment getUpdateFilePop() {
        return new BasePop(C3965R.layout.lora_update_pop, false, new Function1<WindowManager.LayoutParams, Unit>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$getUpdateFilePop$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WindowManager.LayoutParams layoutParams) {
                invoke2(layoutParams);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WindowManager.LayoutParams receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                receiver.gravity = 17;
            }
        }, new LoRaHomeFragment$getUpdateFilePop$2(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DialogFragment getUpdateProgressPop() {
        return new BasePop(C3965R.layout.lora_update_progress_pop, false, new Function1<WindowManager.LayoutParams, Unit>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$getUpdateProgressPop$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WindowManager.LayoutParams layoutParams) {
                invoke2(layoutParams);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WindowManager.LayoutParams receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                receiver.gravity = 17;
            }
        }, new LoRaHomeFragment$getUpdateProgressPop$2(this));
    }

    public final boolean isOneDecimal(String isOneDecimal) {
        Intrinsics.checkParameterIsNotNull(isOneDecimal, "$this$isOneDecimal");
        Pattern compile = Pattern.compile("^\\d+\\.?\\d?$");
        Intrinsics.checkExpressionValueIsNotNull(compile, "Pattern.compile(pattern)");
        Matcher matcher = compile.matcher(isOneDecimal);
        Intrinsics.checkExpressionValueIsNotNull(matcher, "r.matcher(this)");
        return matcher.matches();
    }

    public final void setDrawableRight(TextView view, int drawable) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Drawable drawable2 = view.getContext().getDrawable(drawable);
        setIntrinsicBounds(drawable2);
        Drawable[] compoundDrawables = view.getCompoundDrawables();
        Intrinsics.checkExpressionValueIsNotNull(compoundDrawables, "view.compoundDrawables");
        view.setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], drawable2, compoundDrawables[3]);
    }

    private final void setIntrinsicBounds(Drawable drawable) {
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
    }
}
