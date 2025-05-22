package com.pudutech.disinfect.baselib.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.pudutech.disinfect.baselib.base.CommonFloatViewControlManager;
import com.pudutech.disinfect.baselib.base.FloatViewControlDecoration;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.network.manager.NetState;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: BaseFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u0013H&J\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u0016\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0017H&J\b\u0010\u0018\u001a\u00020\u0013H\u0016J\u0012\u0010\u0019\u001a\u00020\u00132\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH&J\u0006\u0010\u001c\u001a\u00020\u0006J\b\u0010\u001d\u001a\u00020\u0015H&J\u0010\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020 H\u0016J&\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0010\u0010'\u001a\u00020\u00132\u0006\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020\u0013H\u0016J\u001a\u0010+\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\"2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR&\u0010\u000e\u001a\u0004\u0018\u00018\u00002\b\u0010\r\u001a\u0004\u0018\u00018\u00008F@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010¨\u0006-"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/base/fragment/BaseFragment;", "VM", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "Landroidx/fragment/app/Fragment;", "()V", "isFirstLoad", "", "mActivity", "Landroidx/appcompat/app/AppCompatActivity;", "getMActivity", "()Landroidx/appcompat/app/AppCompatActivity;", "setMActivity", "(Landroidx/appcompat/app/AppCompatActivity;)V", "<set-?>", "mVM", "getMVM", "()Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "createObserver", "", "getStatusBarTheme", "", "getViewModel", "Lkotlin/Lazy;", "initData", "initView", "savedInstanceState", "Landroid/os/Bundle;", "isShowStatusBar", "layoutId", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onNetworkStateChange", "netState", "Lcom/pudutech/disinfect/baselib/network/manager/NetState;", "onResume", "onViewCreated", "view", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public abstract class BaseFragment<VM extends BaseViewModel> extends Fragment {
    private HashMap _$_findViewCache;
    private boolean isFirstLoad = true;
    public AppCompatActivity mActivity;
    private VM mVM;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

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

    public abstract void createObserver();

    public final int getStatusBarTheme() {
        return 1;
    }

    public abstract Lazy<VM> getViewModel();

    public void initData() {
    }

    public abstract void initView(Bundle savedInstanceState);

    public final boolean isShowStatusBar() {
        return false;
    }

    public abstract int layoutId();

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onNetworkStateChange(NetState netState) {
        Intrinsics.checkParameterIsNotNull(netState, "netState");
    }

    public final VM getMVM() {
        Lazy<VM> viewModel = getViewModel();
        if (viewModel != null) {
            return viewModel.getValue();
        }
        return null;
    }

    public final AppCompatActivity getMActivity() {
        AppCompatActivity appCompatActivity = this.mActivity;
        if (appCompatActivity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivity");
        }
        return appCompatActivity;
    }

    public final void setMActivity(AppCompatActivity appCompatActivity) {
        Intrinsics.checkParameterIsNotNull(appCompatActivity, "<set-?>");
        this.mActivity = appCompatActivity;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(layoutId(), container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super.onAttach(context);
        this.mActivity = (AppCompatActivity) context;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        this.isFirstLoad = true;
        initView(savedInstanceState);
        createObserver();
        initData();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        CommonFloatViewControlManager mFloatViewControl = FloatViewControlDecoration.INSTANCE.getMFloatViewControl();
        if (mFloatViewControl != null) {
            mFloatViewControl.showFloatView(isShowStatusBar(), getStatusBarTheme());
        }
        super.onResume();
    }
}
