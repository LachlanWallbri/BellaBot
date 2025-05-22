package com.pudutech.module_robot_selfcheck.p058ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.fragment.FragmentKt;
import com.pudutech.base.FilePath;
import com.pudutech.disinfect.baselib.base.fragment.BaseFragment;
import com.pudutech.disinfect.baselib.callback.SimpleAnimationListener;
import com.pudutech.disinfect.baselib.ext.view.NavigationExtKt;
import com.pudutech.disinfect.baselib.util.GlideUtils;
import com.pudutech.module_robot_selfcheck.C5365R;
import com.pudutech.module_robot_selfcheck.p058ui.p059vm.SelfCheckViewModel;
import java.io.File;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* compiled from: SplashFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u001a\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\fH\u0016J\u0012\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0017J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0005H\u0002J\b\u0010\u0013\u001a\u00020\u0005H\u0002¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/ui/fragment/SplashFragment;", "Lcom/pudutech/disinfect/baselib/base/fragment/BaseFragment;", "Lcom/pudutech/module_robot_selfcheck/ui/vm/SelfCheckViewModel;", "()V", "createObserver", "", "displayFromSDCard", "logoPath", "", "v", "Landroid/widget/ImageView;", "getViewModel", "Lkotlin/Lazy;", "initView", "savedInstanceState", "Landroid/os/Bundle;", "layoutId", "", "showAppLogo", "showLogoAnim", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SplashFragment extends BaseFragment<SelfCheckViewModel> {
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
        return C5365R.layout.fragment_splash;
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public void initView(Bundle savedInstanceState) {
        showAppLogo();
        showLogoAnim();
    }

    private final void showLogoAnim() {
        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), C5365R.anim.anim_log_enter);
        loadAnimation.setAnimationListener(new SimpleAnimationListener() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.SplashFragment$showLogoAnim$1
            @Override // com.pudutech.disinfect.baselib.callback.SimpleAnimationListener, android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
                SimpleAnimationListener.DefaultImpls.onAnimationRepeat(this, animation);
            }

            @Override // com.pudutech.disinfect.baselib.callback.SimpleAnimationListener, android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                SimpleAnimationListener.DefaultImpls.onAnimationStart(this, animation);
            }

            @Override // com.pudutech.disinfect.baselib.callback.SimpleAnimationListener, android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                NavigationExtKt.nav(SplashFragment.this).navigate(C5365R.id.action_splashFragment_2_SelfCheckFragment);
            }
        });
        ((ImageView) _$_findCachedViewById(C5365R.id.logo)).startAnimation(loadAnimation);
    }

    private final void showAppLogo() {
        if (new File(FilePath.CUSTOM_WELCOME_LOGO).exists()) {
            String str = FilePath.CUSTOM_WELCOME_LOGO;
            ImageView logo = (ImageView) _$_findCachedViewById(C5365R.id.logo);
            Intrinsics.checkExpressionValueIsNotNull(logo, "logo");
            displayFromSDCard(str, logo);
            return;
        }
        if (new File(FilePath.WELCOME_LOGO).exists()) {
            String str2 = FilePath.WELCOME_LOGO;
            ImageView logo2 = (ImageView) _$_findCachedViewById(C5365R.id.logo);
            Intrinsics.checkExpressionValueIsNotNull(logo2, "logo");
            displayFromSDCard(str2, logo2);
            return;
        }
        ((ImageView) _$_findCachedViewById(C5365R.id.logo)).setImageResource(C5365R.mipmap.img_logo);
    }

    private final void displayFromSDCard(String logoPath, ImageView v) {
        GlideUtils.Companion companion = GlideUtils.INSTANCE;
        Uri parse = Uri.parse("file://" + logoPath);
        Intrinsics.checkExpressionValueIsNotNull(parse, "Uri.parse(\"file://$logoPath\")");
        companion.loadImageViewFormUri(parse, v);
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public Lazy<SelfCheckViewModel> getViewModel() {
        final int i = C5365R.id.nav_self_check;
        final KProperty kProperty = null;
        final Function0 function0 = (Function0) null;
        final Lazy lazy = LazyKt.lazy(new Function0<NavBackStackEntry>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.SplashFragment$getViewModel$$inlined$navGraphViewModels$1
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
        return FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(SelfCheckViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.SplashFragment$getViewModel$$inlined$navGraphViewModels$2
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.SplashFragment$getViewModel$$inlined$navGraphViewModels$3
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
