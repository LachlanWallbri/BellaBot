package com.pudu.library.loracall.base;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.pudu.loracall.library.BR;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u001aH&J\b\u0010\u001b\u001a\u00020\u0018H&J\b\u0010\u001c\u001a\u00020\u001aH\u0016J\b\u0010\u001d\u001a\u00020\u0018H&J\n\u0010\u001e\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010\u001f\u001a\u00020\u00182\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\b\u0010\"\u001a\u00020\u0018H\u0014J\b\u0010#\u001a\u00020\u0018H\u0014J\b\u0010$\u001a\u00020\u0018H\u0002R\u001c\u0010\u0005\u001a\u00028\u0000X\u0086.¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001b\u0010\u000b\u001a\u00020\f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000¨\u0006%"}, m3961d2 = {"Lcom/pudu/library/loracall/base/BaseActivity;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/databinding/ViewDataBinding;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "getBinding", "()Landroidx/databinding/ViewDataBinding;", "setBinding", "(Landroidx/databinding/ViewDataBinding;)V", "Landroidx/databinding/ViewDataBinding;", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "handler$delegate", "Lkotlin/Lazy;", "viewModel", "Landroidx/lifecycle/ViewModel;", "dispatchKeyEvent", "", "event", "Landroid/view/KeyEvent;", "hideNavigationBarWithoutBack", "", "initContentView", "", "initData", "initVariableId", "initView", "initViewModel", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "translucent", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {
    public T binding;

    /* renamed from: handler$delegate, reason: from kotlin metadata */
    private final Lazy handler = LazyKt.lazy(new Function0<Handler>() { // from class: com.pudu.library.loracall.base.BaseActivity$handler$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Handler invoke() {
            return new Handler();
        }
    });
    private ViewModel viewModel;

    public final Handler getHandler() {
        return (Handler) this.handler.getValue();
    }

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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT == 26) {
            setRequestedOrientation(-1);
        } else {
            setRequestedOrientation(1);
        }
        T t = (T) DataBindingUtil.setContentView(this, initContentView());
        Intrinsics.checkExpressionValueIsNotNull(t, "DataBindingUtil.setConte…(this, initContentView())");
        this.binding = t;
        ViewModel initViewModel = initViewModel();
        if (initViewModel == null) {
            initViewModel = new ViewModelProvider(this).get(BaseViewModel.class);
            Intrinsics.checkExpressionValueIsNotNull(initViewModel, "ViewModelProvider(this).…aseViewModel::class.java)");
        }
        this.viewModel = initViewModel;
        T t2 = this.binding;
        if (t2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        int initVariableId = initVariableId();
        ViewModel viewModel = this.viewModel;
        if (viewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        t2.setVariable(initVariableId, viewModel);
        T t3 = this.binding;
        if (t3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        t3.setLifecycleOwner(this);
        initView();
        initData();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        translucent();
    }

    private final void translucent() {
        if (Build.VERSION.SDK_INT >= 19) {
            Window window = getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window, "window");
            View decorView = window.getDecorView();
            Intrinsics.checkExpressionValueIsNotNull(decorView, "window.decorView");
            decorView.setSystemUiVisibility(3846);
        }
    }

    public int initVariableId() {
        return BR.viewModel;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        getHandler().removeCallbacksAndMessages(null);
    }

    private final void hideNavigationBarWithoutBack() {
        NavigationBar.statusBarDisable(62849024, this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event == null || event.getKeyCode() != 4) {
            return super.dispatchKeyEvent(event);
        }
        return true;
    }
}
