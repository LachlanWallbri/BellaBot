package com.pudutech.disinfect.baselib.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.pudutech.disinfect.baselib.base.BaseApp;
import com.pudutech.disinfect.baselib.base.CommonFloatViewControlManager;
import com.pudutech.disinfect.baselib.base.FloatViewControlDecoration;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.callback.StatusBarControl;
import com.pudutech.disinfect.baselib.callback.UnPeekLiveData;
import com.pudutech.disinfect.baselib.callback.livedata.event.EventLiveData;
import com.pudutech.disinfect.baselib.ext.GetViewModelExtKt;
import com.pudutech.disinfect.baselib.network.manager.NetState;
import com.pudutech.disinfect.baselib.network.manager.NetworkStateManager;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: BaseVmFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u001a\u001a\u00020\u001bH&J\r\u0010\u001c\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0016J\b\u0010\u001d\u001a\u00020\u001bH\u0016J\b\u0010\u001e\u001a\u00020\u001bH\u0016J\u0012\u0010\u001f\u001a\u00020\u001b2\b\u0010 \u001a\u0004\u0018\u00010!H&J\b\u0010\"\u001a\u00020#H&J\b\u0010$\u001a\u00020\u001bH\u0016J\u0010\u0010%\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020\u000fH\u0016J&\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0010\u0010-\u001a\u00020\u001b2\u0006\u0010.\u001a\u00020/H\u0016J\b\u00100\u001a\u00020\u001bH\u0016J\u001a\u00101\u001a\u00020\u001b2\u0006\u00102\u001a\u00020(2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u00103\u001a\u00020\u001bH\u0002J\b\u00104\u001a\u00020\u001bH\u0002J\b\u00105\u001a\u00020\u001bH\u0016J\u0012\u00106\u001a\u00020\u001b2\b\b\u0002\u00107\u001a\u000208H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u00028\u0000X\u0086.¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u00069"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/base/fragment/BaseVmFragment;", "VM", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "Landroidx/fragment/app/Fragment;", "Lcom/pudutech/disinfect/baselib/callback/StatusBarControl;", "()V", "isFirstLoad", "", "mActivity", "Landroid/app/Activity;", "getMActivity", "()Landroid/app/Activity;", "setMActivity", "(Landroid/app/Activity;)V", "mContext", "Landroid/content/Context;", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "mViewModel", "getMViewModel", "()Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "setMViewModel", "(Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;)V", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "createObserver", "", "createViewModel", "dismissLoading", "initData", "initView", "savedInstanceState", "Landroid/os/Bundle;", "layoutId", "", "lazyLoadData", "onAttach", "context", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onNetworkStateChange", "netState", "Lcom/pudutech/disinfect/baselib/network/manager/NetState;", "onResume", "onViewCreated", "view", "onVisible", "registerDefaultUiChange", "release", "showLoading", "message", "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public abstract class BaseVmFragment<VM extends BaseViewModel> extends Fragment implements StatusBarControl {
    private HashMap _$_findViewCache;
    private boolean isFirstLoad = true;
    private Activity mActivity;
    public Context mContext;
    public VM mViewModel;

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

    public void dismissLoading() {
    }

    public void initData() {
    }

    public abstract void initView(Bundle savedInstanceState);

    public abstract int layoutId();

    public void lazyLoadData() {
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onNetworkStateChange(NetState netState) {
        Intrinsics.checkParameterIsNotNull(netState, "netState");
    }

    public void release() {
    }

    public void showLoading(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
    }

    @Override // com.pudutech.disinfect.baselib.callback.StatusBarControl
    public int getStatusBarTheme() {
        return StatusBarControl.DefaultImpls.getStatusBarTheme(this);
    }

    @Override // com.pudutech.disinfect.baselib.callback.StatusBarControl
    public boolean isShowStatusBar() {
        return StatusBarControl.DefaultImpls.isShowStatusBar(this);
    }

    public final VM getMViewModel() {
        VM vm = this.mViewModel;
        if (vm == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        }
        return vm;
    }

    public final void setMViewModel(VM vm) {
        Intrinsics.checkParameterIsNotNull(vm, "<set-?>");
        this.mViewModel = vm;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Activity getMActivity() {
        return this.mActivity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setMActivity(Activity activity) {
        this.mActivity = activity;
    }

    public final Context getMContext() {
        Context context = this.mContext;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        }
        return context;
    }

    public final void setMContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.mContext = context;
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
        if (this.mActivity == null && (context instanceof Activity)) {
            this.mActivity = (Activity) context;
        }
        Context applicationContext = BaseApp.INSTANCE.getINSTANCE().getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "BaseApp.INSTANCE.applicationContext");
        this.mContext = applicationContext;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        this.isFirstLoad = true;
        if (this.mActivity == null) {
            this.mActivity = requireActivity();
        }
        this.mViewModel = createViewModel();
        initView(savedInstanceState);
        createObserver();
        onVisible();
        registerDefaultUiChange();
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

    private final void onVisible() {
        View view;
        Lifecycle lifecycle = getLifecycle();
        Intrinsics.checkExpressionValueIsNotNull(lifecycle, "lifecycle");
        if (lifecycle.getCurrentState() == Lifecycle.State.STARTED && this.isFirstLoad && (view = getView()) != null) {
            view.postDelayed(new Runnable() { // from class: com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment$onVisible$1
                @Override // java.lang.Runnable
                public final void run() {
                    BaseVmFragment.this.lazyLoadData();
                    UnPeekLiveData<NetState> networkStateCallback = NetworkStateManager.INSTANCE.getInstance().getNetworkStateCallback();
                    LifecycleOwner viewLifecycleOwner = BaseVmFragment.this.getViewLifecycleOwner();
                    Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner, "viewLifecycleOwner");
                    networkStateCallback.observe(viewLifecycleOwner, new Observer<NetState>() { // from class: com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment$onVisible$1.1
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(NetState it) {
                            boolean z;
                            z = BaseVmFragment.this.isFirstLoad;
                            if (z) {
                                return;
                            }
                            BaseVmFragment baseVmFragment = BaseVmFragment.this;
                            Intrinsics.checkExpressionValueIsNotNull(it, "it");
                            baseVmFragment.onNetworkStateChange(it);
                        }
                    });
                    BaseVmFragment.this.isFirstLoad = false;
                }
            }, 120L);
        }
    }

    public static /* synthetic */ void showLoading$default(BaseVmFragment baseVmFragment, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showLoading");
        }
        if ((i & 1) != 0) {
            str = "请求网络中...";
        }
        baseVmFragment.showLoading(str);
    }

    private final void registerDefaultUiChange() {
        VM vm = this.mViewModel;
        if (vm == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        }
        EventLiveData<String> showDialog = vm.getLoadingChange().getShowDialog();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner, "viewLifecycleOwner");
        showDialog.observe(viewLifecycleOwner, new Observer<String>() { // from class: com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment$registerDefaultUiChange$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(String str) {
                BaseVmFragment.showLoading$default(BaseVmFragment.this, null, 1, null);
            }
        });
        VM vm2 = this.mViewModel;
        if (vm2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        }
        EventLiveData<Boolean> dismissDialog = vm2.getLoadingChange().getDismissDialog();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner2, "viewLifecycleOwner");
        dismissDialog.observe(viewLifecycleOwner2, new Observer<Boolean>() { // from class: com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment$registerDefaultUiChange$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean bool) {
                BaseVmFragment.this.dismissLoading();
            }
        });
    }

    private final VM createViewModel() {
        ViewModel viewModel = new ViewModelProvider(requireActivity()).get((Class) GetViewModelExtKt.getVmClazz(this));
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProvider(requir…()).get(getVmClazz(this))");
        return (VM) viewModel;
    }
}
