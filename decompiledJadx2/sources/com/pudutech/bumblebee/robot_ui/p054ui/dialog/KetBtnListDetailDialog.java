package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.widget.KeyBtnListDetailView;
import com.pudutech.disinfect.baselib.network.response.KeyBtnWithDestination;
import com.pudutech.disinfect.baselib.util.NavigationBarUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: KetBtnListDetailDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0010\u001a\u00020\u000eH\u0002J\b\u0010\u0011\u001a\u00020\u000eH\u0016J\b\u0010\u0012\u001a\u00020\u000eH\u0016J\u0014\u0010\u0013\u001a\u00020\u000e2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015J\u0014\u0010\u0017\u001a\u00020\u000e2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000e0\rJ\u0014\u0010\u0019\u001a\u00020\u000e2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000e0\rJ\b\u0010\u001a\u001a\u00020\u000eH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/KetBtnListDetailDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "TAG", "", "listView", "Lcom/pudutech/bumblebee/robot_ui/widget/KeyBtnListDetailView;", "onCloseClick", "Lkotlin/Function0;", "", "onRefreshClick", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "onAttachedToWindow", "onDetachedFromWindow", "setData", "list", "", "Lcom/pudutech/disinfect/baselib/network/response/KeyBtnWithDestination;", "setOnCloseClick", "block", "setOnRefreshClick", "show", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class KetBtnListDetailDialog extends Dialog {
    private final String TAG;
    private KeyBtnListDetailView listView;
    private Function0<Unit> onCloseClick;
    private Function0<Unit> onRefreshClick;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KetBtnListDetailDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "KetBtnListDetailDialog";
        build();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KetBtnListDetailDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "KetBtnListDetailDialog";
        build();
    }

    private final void build() {
        View inflate = getLayoutInflater().inflate(C4188R.layout.dialog_key_btn_list_detail, (ViewGroup) null);
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
        findViewById(C4188R.id.tvRefresh).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.KetBtnListDetailDialog$build$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0 function0;
                function0 = KetBtnListDetailDialog.this.onRefreshClick;
                if (function0 != null) {
                }
            }
        });
        findViewById(C4188R.id.ivClose).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.KetBtnListDetailDialog$build$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0 function0;
                function0 = KetBtnListDetailDialog.this.onCloseClick;
                if (function0 != null) {
                    function0.invoke();
                } else {
                    KetBtnListDetailDialog.this.dismiss();
                }
            }
        });
        View findViewById = findViewById(C4188R.id.list);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.list)");
        this.listView = (KeyBtnListDetailView) findViewById;
    }

    public final void setOnRefreshClick(Function0<Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        this.onRefreshClick = block;
    }

    public final void setOnCloseClick(Function0<Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        this.onCloseClick = block;
    }

    public final void setData(List<KeyBtnWithDestination> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        KeyBtnListDetailView keyBtnListDetailView = this.listView;
        if (keyBtnListDetailView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("listView");
        }
        keyBtnListDetailView.setData(list);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Pdlog.m3273d(this.TAG, "onAttachedToWindow ");
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Pdlog.m3273d(this.TAG, "onDetachedFromWindow ");
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
