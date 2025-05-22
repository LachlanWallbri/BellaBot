package com.pudutech.disinfect.baselib.ext;

import android.app.Application;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.pudutech.disinfect.baselib.base.BaseApp;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: GetViewModelExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0019\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004\u001a\u001e\u0010\u0005\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0006*\u00020\u0007H\u0087\b¢\u0006\u0002\u0010\b\u001a\u001e\u0010\t\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0006*\u00020\nH\u0086\b¢\u0006\u0002\u0010\u000b\u001a\u001e\u0010\t\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0006*\u00020\u0007H\u0086\b¢\u0006\u0002\u0010\b\u001a\u001e\u0010\f\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0006*\u00020\nH\u0087\b¢\u0006\u0002\u0010\u000b¨\u0006\r"}, m3961d2 = {"getVmClazz", "VM", "obj", "", "(Ljava/lang/Object;)Ljava/lang/Object;", "getActivityViewModel", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "Landroidx/fragment/app/Fragment;", "(Landroidx/fragment/app/Fragment;)Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "getAppViewModel", "Landroidx/appcompat/app/AppCompatActivity;", "(Landroidx/appcompat/app/AppCompatActivity;)Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "getViewModel", "module_baselib_robot_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class GetViewModelExtKt {
    public static final <VM> VM getVmClazz(Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "obj");
        Type genericSuperclass = obj.getClass().getGenericSuperclass();
        if (genericSuperclass != null) {
            return (VM) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.reflect.ParameterizedType");
    }

    public static final /* synthetic */ <VM extends BaseViewModel> VM getAppViewModel(AppCompatActivity getAppViewModel) {
        Intrinsics.checkParameterIsNotNull(getAppViewModel, "$this$getAppViewModel");
        Application application = getAppViewModel.getApplication();
        if (!(application instanceof BaseApp)) {
            application = null;
        }
        BaseApp baseApp = (BaseApp) application;
        if (baseApp == null) {
            throw new NullPointerException("please check your base app is instance of Application");
        }
        ViewModelProvider appViewModelProvider = baseApp.getAppViewModelProvider();
        Intrinsics.reifiedOperationMarker(4, "VM");
        ViewModel viewModel = appViewModelProvider.get(BaseViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "it.getAppViewModelProvider().get(VM::class.java)");
        return (VM) viewModel;
    }

    public static final /* synthetic */ <VM extends BaseViewModel> VM getAppViewModel(Fragment getAppViewModel) {
        Intrinsics.checkParameterIsNotNull(getAppViewModel, "$this$getAppViewModel");
        FragmentActivity requireActivity = getAppViewModel.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "this.requireActivity()");
        Application application = requireActivity.getApplication();
        if (!(application instanceof BaseApp)) {
            application = null;
        }
        BaseApp baseApp = (BaseApp) application;
        if (baseApp == null) {
            throw new NullPointerException("please check your base app is instance of Application");
        }
        ViewModelProvider appViewModelProvider = baseApp.getAppViewModelProvider();
        Intrinsics.reifiedOperationMarker(4, "VM");
        ViewModel viewModel = appViewModelProvider.get(BaseViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "it.getAppViewModelProvider().get(VM::class.java)");
        return (VM) viewModel;
    }

    @Deprecated(message = "deprecated function now can use ktx function by viewModels() can acquire")
    public static final /* synthetic */ <VM extends BaseViewModel> VM getViewModel(AppCompatActivity getViewModel) {
        Intrinsics.checkParameterIsNotNull(getViewModel, "$this$getViewModel");
        ViewModelProvider viewModelProvider = new ViewModelProvider(getViewModel, new ViewModelProvider.AndroidViewModelFactory(getViewModel.getApplication()));
        Intrinsics.reifiedOperationMarker(4, "VM");
        ViewModel viewModel = viewModelProvider.get(BaseViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProvider(this, …     .get(VM::class.java)");
        return (VM) viewModel;
    }

    @Deprecated(message = "deprecated function now can use ktx function by activityViewModels() acquire")
    public static final /* synthetic */ <VM extends BaseViewModel> VM getActivityViewModel(Fragment getActivityViewModel) {
        Intrinsics.checkParameterIsNotNull(getActivityViewModel, "$this$getActivityViewModel");
        FragmentActivity requireActivity = getActivityViewModel.requireActivity();
        FragmentActivity requireActivity2 = getActivityViewModel.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity2, "this.requireActivity()");
        ViewModelProvider viewModelProvider = new ViewModelProvider(requireActivity, new ViewModelProvider.AndroidViewModelFactory(requireActivity2.getApplication()));
        Intrinsics.reifiedOperationMarker(4, "VM");
        ViewModel viewModel = viewModelProvider.get(BaseViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProvider(requir…    ).get(VM::class.java)");
        return (VM) viewModel;
    }
}
