package com.pudutech.module_robot_selfcheck.p058ui.fragment;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.fragment.FragmentKt;
import com.pudutech.disinfect.baselib.base.fragment.BaseFragment;
import com.pudutech.disinfect.baselib.ext.view.NavigationExtKt;
import com.pudutech.disinfect.baselib.widget.CTextButton;
import com.pudutech.freeinstall_wrapper.MapingFuntionManager;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.module_robot_selfcheck.C5365R;
import com.pudutech.module_robot_selfcheck.p058ui.p059vm.SelfCheckViewModel;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* compiled from: ActiveSuccessFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00052\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/ui/fragment/ActiveSuccessFragment;", "Lcom/pudutech/disinfect/baselib/base/fragment/BaseFragment;", "Lcom/pudutech/module_robot_selfcheck/ui/vm/SelfCheckViewModel;", "()V", "createObserver", "", "getViewModel", "Lkotlin/Lazy;", "initView", "savedInstanceState", "Landroid/os/Bundle;", "layoutId", "", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ActiveSuccessFragment extends BaseFragment<SelfCheckViewModel> {
    private HashMap _$_findViewCache;

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public void createObserver() {
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public int layoutId() {
        return C5365R.layout.fragment_active_success;
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public void initView(Bundle savedInstanceState) {
        ((CTextButton) _$_findCachedViewById(C5365R.id.btn_confirm)).setOnCTouchListener(new Function2<View, MotionEvent, Unit>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.ActiveSuccessFragment$initView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(View view, MotionEvent motionEvent) {
                invoke2(view, motionEvent);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View v, MotionEvent event) {
                Intrinsics.checkParameterIsNotNull(v, "v");
                Intrinsics.checkParameterIsNotNull(event, "event");
                MapingFuntionManager.INSTANCE.defaultResetRobot();
                MirSdkManager.INSTANCE.notifyMapFinish();
                NavigationExtKt.nav(ActiveSuccessFragment.this).navigate(C5365R.id.action_activeSuccessFragment_to_SelfCheckFragment);
            }
        });
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public Lazy<SelfCheckViewModel> getViewModel() {
        final int i = C5365R.id.nav_self_check;
        final KProperty kProperty = null;
        final Function0 function0 = (Function0) null;
        final Lazy lazy = LazyKt.lazy(new Function0<NavBackStackEntry>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.ActiveSuccessFragment$getViewModel$$inlined$navGraphViewModels$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final NavBackStackEntry invoke() {
                return FragmentKt.findNavController(Fragment.this).getBackStackEntry(i);
            }
        });
        return FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(SelfCheckViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.ActiveSuccessFragment$getViewModel$$inlined$navGraphViewModels$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                Lazy lazy2 = Lazy.this;
                KProperty kProperty2 = kProperty;
                NavBackStackEntry backStackEntry = (NavBackStackEntry) lazy2.getValue();
                Intrinsics.checkExpressionValueIsNotNull(backStackEntry, "backStackEntry");
                ViewModelStore viewModelStore = backStackEntry.getViewModelStore();
                Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "backStackEntry.viewModelStore");
                return viewModelStore;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.ActiveSuccessFragment$getViewModel$$inlined$navGraphViewModels$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory factory;
                Function0 function02 = Function0.this;
                if (function02 != null && (factory = (ViewModelProvider.Factory) function02.invoke()) != null) {
                    return factory;
                }
                Lazy lazy2 = lazy;
                KProperty kProperty2 = kProperty;
                NavBackStackEntry backStackEntry = (NavBackStackEntry) lazy2.getValue();
                Intrinsics.checkExpressionValueIsNotNull(backStackEntry, "backStackEntry");
                ViewModelProvider.Factory defaultViewModelProviderFactory = backStackEntry.getDefaultViewModelProviderFactory();
                Intrinsics.checkExpressionValueIsNotNull(defaultViewModelProviderFactory, "backStackEntry.defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
    }
}
