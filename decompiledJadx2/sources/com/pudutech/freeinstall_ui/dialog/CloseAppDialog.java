package com.pudutech.freeinstall_ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.disinfect.baselib.util.NavigationBarUtil;
import com.pudutech.module_freeinstall_ui.C5362R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CloseAppDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0011\u001a\u00020\fH\u0002J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010\u0013\u001a\u00020\fH\u0002J\b\u0010\u0014\u001a\u00020\fH\u0016R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/dialog/CloseAppDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "kotlin.jvm.PlatformType", "_context", "onShutDownClick", "Lkotlin/Function1;", "", "", "getOnShutDownClick", "()Lkotlin/jvm/functions/Function1;", "setOnShutDownClick", "(Lkotlin/jvm/functions/Function1;)V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "init", "initView", "show", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public class CloseAppDialog extends Dialog {
    private final String TAG;
    private Context _context;
    private Function1<? super Boolean, Unit> onShutDownClick;

    public final Function1<Boolean, Unit> getOnShutDownClick() {
        return this.onShutDownClick;
    }

    public final void setOnShutDownClick(Function1<? super Boolean, Unit> function1) {
        this.onShutDownClick = function1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CloseAppDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        init(context);
    }

    private final void init(Context context) {
        this._context = context;
        build();
    }

    private final void build() {
        View inflate = getLayoutInflater().inflate(C5362R.layout.fragment_close_app_dialog, (ViewGroup) null);
        requestWindowFeature(1);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(17);
            window.setAttributes(attributes);
            setContentView(inflate);
        }
        setCancelable(false);
        initView();
    }

    private final void initView() {
        ImageView imageView = (ImageView) findViewById(C5362R.id.ivClose);
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.dialog.CloseAppDialog$initView$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CloseAppDialog.this.dismiss();
                }
            });
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(C5362R.id.llClose);
        if (linearLayout != null) {
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.dialog.CloseAppDialog$initView$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Function1<Boolean, Unit> onShutDownClick = CloseAppDialog.this.getOnShutDownClick();
                    if (onShutDownClick != null) {
                        onShutDownClick.invoke(true);
                    }
                }
            });
        }
        LinearLayout linearLayout2 = (LinearLayout) findViewById(C5362R.id.llRestart);
        if (linearLayout2 != null) {
            linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.dialog.CloseAppDialog$initView$3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Function1<Boolean, Unit> onShutDownClick = CloseAppDialog.this.getOnShutDownClick();
                    if (onShutDownClick != null) {
                        onShutDownClick.invoke(false);
                    }
                }
            });
        }
    }

    @Override // android.app.Dialog
    public void show() {
        Window window = getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        NavigationBarUtil.focusNotAle(window);
        super.show();
        Window window2 = getWindow();
        if (window2 == null) {
            Intrinsics.throwNpe();
        }
        NavigationBarUtil.hideNavigationBar(window2);
        Window window3 = getWindow();
        if (window3 == null) {
            Intrinsics.throwNpe();
        }
        NavigationBarUtil.clearFocusNotAle(window3);
        Window window4 = getWindow();
        if (window4 != null) {
            window4.setLayout(-1, -1);
        }
    }
}
