package com.pudu.library.loracall.base;

import android.R;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.iflytek.aiui.AIUIConstant;
import com.pudu.library.loracall.utils.LoraNavigationBarUtil;
import com.pudu.loracall.library.C3965R;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BasePop.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B]\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0019\b\u0002\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f\u0012'\b\u0002\u0010\r\u001a!\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0000\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u000e¢\u0006\u0002\b\f¢\u0006\u0002\u0010\u000fB\u0005¢\u0006\u0002\u0010\u0010J\b\u00104\u001a\u00020\u000bH\u0016J\u0012\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u000108H\u0016J&\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010>2\b\u00107\u001a\u0004\u0018\u000108H\u0016J\u0010\u0010?\u001a\u00020\u000b2\u0006\u0010@\u001a\u00020AH\u0016J\b\u0010B\u001a\u00020\u000bH\u0016J\u001a\u0010C\u001a\u00020\u000b2\u0006\u0010D\u001a\u00020:2\b\u00107\u001a\u0004\u0018\u000108H\u0016J\u000e\u0010E\u001a\u00020\u000b2\u0006\u0010F\u001a\u00020\u0005J\u000e\u0010G\u001a\u00020\u000b2\u0006\u0010H\u001a\u00020IJ\u001a\u0010G\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020I2\b\u0010K\u001a\u0004\u0018\u00010LH\u0016R\u001a\u0010\u0011\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u00028\u0000X\u0084.¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR9\u0010\r\u001a!\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0000\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u000e¢\u0006\u0002\b\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b\u0006\u0010\u0013R\u001a\u0010'\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0013\"\u0004\b(\u0010\u0015R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b*\u0010&\u001a\u0004\b)\u0010\"R\"\u0010+\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\"\u00101\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010.\"\u0004\b3\u00100R!\u0010\b\u001a\u0015\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\t¢\u0006\u0002\b\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006M"}, m3961d2 = {"Lcom/pudu/library/loracall/base/BasePop;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/databinding/ViewDataBinding;", "Landroidx/fragment/app/DialogFragment;", "layoutId", "", "isCanceled", "", "setParameter", "Lkotlin/Function1;", "Landroid/view/WindowManager$LayoutParams;", "", "Lkotlin/ExtensionFunctionType;", "function", "Lkotlin/Function2;", "(IZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", "()V", "backgroundWhite", "getBackgroundWhite", "()Z", "setBackgroundWhite", "(Z)V", "binding", "getBinding", "()Landroidx/databinding/ViewDataBinding;", "setBinding", "(Landroidx/databinding/ViewDataBinding;)V", "Landroidx/databinding/ViewDataBinding;", "getFunction", "()Lkotlin/jvm/functions/Function2;", "setFunction", "(Lkotlin/jvm/functions/Function2;)V", "inputMode", "getInputMode", "()I", "setInputMode", "(I)V", "isCanceled$delegate", "Lkotlin/Lazy;", "isShowing", "setShowing", "getLayoutId", "layoutId$delegate", "onDismissListener", "Lkotlin/Function0;", "getOnDismissListener", "()Lkotlin/jvm/functions/Function0;", "setOnDismissListener", "(Lkotlin/jvm/functions/Function0;)V", "onShowListener", "getOnShowListener", "setOnShowListener", "dismiss", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "onStart", "onViewCreated", "view", "setHeight", "height", "show", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "manager", AIUIConstant.KEY_TAG, "", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public class BasePop<T extends ViewDataBinding> extends DialogFragment {
    private boolean backgroundWhite;
    protected T binding;
    private Function2<? super T, ? super BasePop<T>, Unit> function;
    private int inputMode;

    /* renamed from: isCanceled$delegate, reason: from kotlin metadata */
    private final Lazy isCanceled;
    private boolean isShowing;

    /* renamed from: layoutId$delegate, reason: from kotlin metadata */
    private final Lazy layoutId;
    private Function0<Unit> onDismissListener;
    private Function0<Unit> onShowListener;
    private Function1<? super WindowManager.LayoutParams, Unit> setParameter;

    private final int getLayoutId() {
        return ((Number) this.layoutId.getValue()).intValue();
    }

    private final boolean isCanceled() {
        return ((Boolean) this.isCanceled.getValue()).booleanValue();
    }

    public BasePop() {
        this.inputMode = 16;
        this.layoutId = LazyKt.lazy(new Function0<Integer>() { // from class: com.pudu.library.loracall.base.BasePop$layoutId$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Integer invoke() {
                return Integer.valueOf(invoke2());
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final int invoke2() {
                Bundle arguments = BasePop.this.getArguments();
                return arguments != null ? arguments.getInt("layoutId") : C3965R.layout.lora_common_blank;
            }
        });
        this.isCanceled = LazyKt.lazy(new Function0<Boolean>() { // from class: com.pudu.library.loracall.base.BasePop$isCanceled$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Boolean invoke() {
                return Boolean.valueOf(invoke2());
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2() {
                Bundle arguments = BasePop.this.getArguments();
                if (arguments != null) {
                    return arguments.getBoolean("isCanceled");
                }
                return false;
            }
        });
    }

    protected final T getBinding() {
        T t = this.binding;
        if (t == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        return t;
    }

    protected final void setBinding(T t) {
        Intrinsics.checkParameterIsNotNull(t, "<set-?>");
        this.binding = t;
    }

    public final Function0<Unit> getOnDismissListener() {
        return this.onDismissListener;
    }

    public final void setOnDismissListener(Function0<Unit> function0) {
        this.onDismissListener = function0;
    }

    public final Function0<Unit> getOnShowListener() {
        return this.onShowListener;
    }

    public final void setOnShowListener(Function0<Unit> function0) {
        this.onShowListener = function0;
    }

    /* renamed from: isShowing, reason: from getter */
    public final boolean getIsShowing() {
        return this.isShowing;
    }

    public final void setShowing(boolean z) {
        this.isShowing = z;
    }

    public final int getInputMode() {
        return this.inputMode;
    }

    public final void setInputMode(int i) {
        this.inputMode = i;
    }

    public final boolean getBackgroundWhite() {
        return this.backgroundWhite;
    }

    public final void setBackgroundWhite(boolean z) {
        this.backgroundWhite = z;
    }

    public final Function2<T, BasePop<T>, Unit> getFunction() {
        return this.function;
    }

    public final void setFunction(Function2<? super T, ? super BasePop<T>, Unit> function2) {
        this.function = function2;
    }

    public /* synthetic */ BasePop(int i, boolean z, C39311 c39311, Function2 function2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? new Function1<WindowManager.LayoutParams, Unit>() { // from class: com.pudu.library.loracall.base.BasePop.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WindowManager.LayoutParams layoutParams) {
                invoke2(layoutParams);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WindowManager.LayoutParams receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                receiver.gravity = 80;
                receiver.width = -1;
            }
        } : c39311, (i2 & 8) != 0 ? (Function2) null : function2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BasePop(int i, boolean z, Function1<? super WindowManager.LayoutParams, Unit> setParameter, Function2<? super T, ? super BasePop<T>, Unit> function2) {
        this();
        Intrinsics.checkParameterIsNotNull(setParameter, "setParameter");
        Bundle bundle = new Bundle();
        bundle.putInt("layoutId", i);
        bundle.putBoolean("isCanceled", z);
        setArguments(bundle);
        this.setParameter = setParameter;
        this.function = function2;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        T t = (T) DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        Intrinsics.checkExpressionValueIsNotNull(t, "DataBindingUtil.inflate(…youtId, container, false)");
        this.binding = t;
        T t2 = this.binding;
        if (t2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        return t2.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Function2<? super T, ? super BasePop<T>, Unit> function2 = this.function;
        if (function2 != null) {
            T t = this.binding;
            if (t == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            }
            function2.invoke(t, this);
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog onCreateDialog = super.onCreateDialog(savedInstanceState);
        Intrinsics.checkExpressionValueIsNotNull(onCreateDialog, "super.onCreateDialog(savedInstanceState)");
        onCreateDialog.requestWindowFeature(1);
        onCreateDialog.setCanceledOnTouchOutside(isCanceled());
        Window window = onCreateDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(R.color.transparent);
        }
        if (window != null) {
            window.setSoftInputMode(this.inputMode);
        }
        return onCreateDialog;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Dialog dialog = getDialog();
        LoraNavigationBarUtil.focusNotAle(dialog != null ? dialog.getWindow() : null);
        super.onStart();
        Dialog dialog2 = getDialog();
        Window window = dialog2 != null ? dialog2.getWindow() : null;
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            Function1<? super WindowManager.LayoutParams, Unit> function1 = this.setParameter;
            if (function1 != null) {
                Intrinsics.checkExpressionValueIsNotNull(params, "params");
                function1.invoke(params);
            }
            if (this.backgroundWhite) {
                params.dimAmount = 0.0f;
            }
            window.setAttributes(params);
        }
        Dialog dialog3 = getDialog();
        LoraNavigationBarUtil.hideNavigationBar(dialog3 != null ? dialog3.getWindow() : null);
        Dialog dialog4 = getDialog();
        LoraNavigationBarUtil.clearFocusNotAle(dialog4 != null ? dialog4.getWindow() : null);
    }

    public final void setHeight(int height) {
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.height = height;
            window.setAttributes(attributes);
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public void show(FragmentManager manager, String tag) {
        Intrinsics.checkParameterIsNotNull(manager, "manager");
        dismiss();
        this.isShowing = true;
        if (isAdded()) {
            FragmentTransaction beginTransaction = manager.beginTransaction();
            Intrinsics.checkNotNullExpressionValue(beginTransaction, "beginTransaction()");
            beginTransaction.remove(this);
            beginTransaction.commit();
        }
        Function0<Unit> function0 = this.onShowListener;
        if (function0 != null) {
            function0.invoke();
        }
        super.show(manager, tag);
    }

    public final void show(FragmentManager fragmentManager) {
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fragmentManager");
        show(fragmentManager, "");
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismiss() {
        if (this.isShowing) {
            try {
                super.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        this.isShowing = false;
        Function0<Unit> function0 = this.onDismissListener;
        if (function0 != null) {
            function0.invoke();
        }
        super.onDismiss(dialog);
    }
}
