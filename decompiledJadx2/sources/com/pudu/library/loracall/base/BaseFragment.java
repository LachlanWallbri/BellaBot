package com.pudu.library.loracall.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.pudu.library.loracall.KotlinUtilsKt;
import com.pudu.loracall.library.BR;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\b\u0010\u0012\u001a\u00020\fH\u0016J\b\u0010\u0013\u001a\u00020\u000eH&J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0006\u0010\u0016\u001a\u00020\u0017J\u0012\u0010\u0018\u001a\u00020\u000e2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J&\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016R\u001c\u0010\u0005\u001a\u00028\u0000X\u0086.¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006!"}, m3961d2 = {"Lcom/pudu/library/loracall/base/BaseFragment;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/databinding/ViewDataBinding;", "Landroidx/fragment/app/Fragment;", "()V", "binding", "getBinding", "()Landroidx/databinding/ViewDataBinding;", "setBinding", "(Landroidx/databinding/ViewDataBinding;)V", "Landroidx/databinding/ViewDataBinding;", "initContentView", "", "initData", "", "initLoadingViewModel", "", "Lcom/pudu/library/loracall/base/BaseViewModel;", "initVariableId", "initView", "initViewModel", "Landroidx/lifecycle/ViewModel;", "isBindingInit", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {
    public T binding;

    public abstract int initContentView();

    public abstract void initData();

    public abstract void initView();

    public ViewModel initViewModel() {
        return null;
    }

    public final T getBinding() {
        T t = this.binding;
        if (t == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        return t;
    }

    public final void setBinding(T t) {
        Intrinsics.checkParameterIsNotNull(t, "<set-?>");
        this.binding = t;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        T t = (T) DataBindingUtil.inflate(inflater, initContentView(), container, false);
        Intrinsics.checkExpressionValueIsNotNull(t, "DataBindingUtil.inflate(…View(), container, false)");
        this.binding = t;
        T t2 = this.binding;
        if (t2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        return t2.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            ViewModel initViewModel = initViewModel();
            if (initViewModel == null) {
                initViewModel = new ViewModelProvider(this).get(BaseViewModel.class);
                Intrinsics.checkExpressionValueIsNotNull(initViewModel, "ViewModelProvider(this).…aseViewModel::class.java)");
            }
            T t = this.binding;
            if (t == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            }
            t.setVariable(initVariableId(), initViewModel);
            T t2 = this.binding;
            if (t2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            }
            t2.setLifecycleOwner(this);
            initView();
            initData();
        } catch (Exception e) {
            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.base.BaseFragment$onActivityCreated$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return KotlinUtilsKt.stackTraceToString(e);
                }
            }, 1, null);
        }
    }

    public List<BaseViewModel> initLoadingViewModel() {
        return new ArrayList();
    }

    public final boolean isBindingInit() {
        return this.binding != null;
    }

    public int initVariableId() {
        return BR.viewModel;
    }
}
