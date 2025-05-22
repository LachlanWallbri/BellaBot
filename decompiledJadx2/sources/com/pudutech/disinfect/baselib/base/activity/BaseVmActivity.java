package com.pudutech.disinfect.baselib.base.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.ext.GetViewModelExtKt;
import com.pudutech.disinfect.baselib.network.manager.NetState;
import com.pudutech.disinfect.baselib.network.manager.NetworkStateManager;
import com.pudutech.disinfect.baselib.util.InputMethodUtil;
import com.pudutech.disinfect.baselib.util.NavigationBar;
import java.util.HashMap;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: BaseVmActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0012H&J\r\u0010\u0016\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\rJ\b\u0010\u0017\u001a\u00020\u0012H\u0016J\b\u0010\u0018\u001a\u00020\u0012H\u0002J\u0012\u0010\u0019\u001a\u00020\u00122\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0002J\u0012\u0010\u001c\u001a\u00020\u00122\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH&J\b\u0010\u001d\u001a\u00020\u001eH&J\u0012\u0010\u001f\u001a\u00020\u00122\b\u0010 \u001a\u0004\u0018\u00010\u001bH\u0014J\u0010\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020&H\u0016J\u0012\u0010'\u001a\u00020\u00122\b\b\u0002\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020\u0012H\u0004J\u0010\u0010+\u001a\u00020\u00142\u0006\u0010,\u001a\u00020\u0014H\u0002J\u0010\u0010-\u001a\u00020\u00142\u0006\u0010,\u001a\u00020\u0014H\u0003R\u001a\u0010\u0005\u001a\u00020\u0006X\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u00028\u0000X\u0086.¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006."}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/base/activity/BaseVmActivity;", "VM", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "mActivity", "Landroid/app/Activity;", "getMActivity", "()Landroid/app/Activity;", "setMActivity", "(Landroid/app/Activity;)V", "mViewModel", "getMViewModel", "()Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "setMViewModel", "(Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;)V", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "attachBaseContext", "", "newBase", "Landroid/content/Context;", "createObserver", "createViewModel", "dismissLoading", "hideNavigationBarWithoutBack", "init", "saveInstanceState", "Landroid/os/Bundle;", "initView", "layoutId", "", "onCreate", "savedInstanceState", "onNetWorkStateChanged", "netState", "Lcom/pudutech/disinfect/baselib/network/manager/NetState;", "onWindowFocusChanged", "hasFocus", "", "showLoading", "message", "", "translucent", "updateBaseContext", "context", "updateResource", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public abstract class BaseVmActivity<VM extends BaseViewModel> extends AppCompatActivity {
    private HashMap _$_findViewCache;
    protected Activity mActivity;
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
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public abstract void createObserver();

    public void dismissLoading() {
    }

    public abstract void initView(Bundle saveInstanceState);

    public abstract int layoutId();

    public void onNetWorkStateChanged(NetState netState) {
        Intrinsics.checkParameterIsNotNull(netState, "netState");
    }

    public void showLoading(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
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

    public static /* synthetic */ void showLoading$default(BaseVmActivity baseVmActivity, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showLoading");
        }
        if ((i & 1) != 0) {
            str = "网络请求中....";
        }
        baseVmActivity.showLoading(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Activity getMActivity() {
        Activity activity = this.mActivity;
        if (activity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivity");
        }
        return activity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setMActivity(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "<set-?>");
        this.mActivity = activity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivity = this;
        supportRequestWindowFeature(1);
        Window window = getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window, "window");
        window.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.pudutech.disinfect.baselib.base.activity.BaseVmActivity$onCreate$1
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public final void onSystemUiVisibilityChange(int i) {
                BaseVmActivity.this.translucent();
            }
        });
        hideNavigationBarWithoutBack();
        InputMethodUtil.changeInputMethodIfNeed(this, "gokey");
        if (layoutId() != 0) {
            setContentView(layoutId());
        }
        init(savedInstanceState);
    }

    private final void init(Bundle saveInstanceState) {
        this.mViewModel = createViewModel();
        initView(saveInstanceState);
        createObserver();
        NetworkStateManager.INSTANCE.getInstance().getNetworkStateCallback().observe(this, new Observer<NetState>() { // from class: com.pudutech.disinfect.baselib.base.activity.BaseVmActivity$init$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(NetState it) {
                BaseVmActivity baseVmActivity = BaseVmActivity.this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                baseVmActivity.onNetWorkStateChanged(it);
            }
        });
    }

    private final VM createViewModel() {
        ViewModel viewModel = new ViewModelProvider(this).get((Class) GetViewModelExtKt.getVmClazz(this));
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProvider(this).get(getVmClazz(this))");
        return (VM) viewModel;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        translucent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(Context newBase) {
        Intrinsics.checkParameterIsNotNull(newBase, "newBase");
        super.attachBaseContext(updateBaseContext(newBase));
    }

    private final Context updateBaseContext(Context context) {
        return Build.VERSION.SDK_INT >= 24 ? updateResource(context) : context;
    }

    private final Context updateResource(Context context) {
        Resources resources = context.getResources();
        Locale locale = Locale.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        configuration.setLocales(new LocaleList(locale));
        Context createConfigurationContext = context.createConfigurationContext(configuration);
        Intrinsics.checkExpressionValueIsNotNull(createConfigurationContext, "context.createConfigurationContext(configuration)");
        return createConfigurationContext;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void translucent() {
        if (Build.VERSION.SDK_INT >= 19) {
            Window window = getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window, "window");
            View decorView = window.getDecorView();
            Intrinsics.checkExpressionValueIsNotNull(decorView, "window.decorView");
            decorView.setSystemUiVisibility(3846);
        }
    }

    private final void hideNavigationBarWithoutBack() {
        NavigationBar.statusBarDisable(62849024, getApplicationContext());
    }
}
