package com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.NavigationBarUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ShowDownloadingDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0014\u001a\u00020\u000fH\u0002J\u0006\u0010\u0015\u001a\u00020\u000fJ\u0006\u0010\u0016\u001a\u00020\u000fJ\u0010\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u000e\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u0010\u001b\u001a\u00020\u000fH\u0016J\u0006\u0010\u001c\u001a\u00020\u000fJ\u000e\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u001aR\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowDownloadingDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "_context", "cancel", "Landroid/view/View;", "downloading_num_show", "Landroid/widget/TextView;", "onCloseBtnClick", "Lkotlin/Function0;", "", "getOnCloseBtnClick", "()Lkotlin/jvm/functions/Function0;", "setOnCloseBtnClick", "(Lkotlin/jvm/functions/Function0;)V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "dismisscheckUpdateDownloadingDialog", "hideCancel", "init", "setTitle", "text", "", "show", "showCancel", "showProgress", "progress", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class ShowDownloadingDialog extends Dialog {
    private Context _context;
    private View cancel;
    private TextView downloading_num_show;
    private Function0<Unit> onCloseBtnClick;

    public final Function0<Unit> getOnCloseBtnClick() {
        return this.onCloseBtnClick;
    }

    public final void setOnCloseBtnClick(Function0<Unit> function0) {
        this.onCloseBtnClick = function0;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShowDownloadingDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShowDownloadingDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        init(context);
    }

    private final void init(Context context) {
        this._context = context;
        build();
    }

    private final void build() {
        View inflate = getLayoutInflater().inflate(C4188R.layout.fragment_version_setup_downloading_dialog, (ViewGroup) null);
        requestWindowFeature(1);
        Window window = getWindow();
        if (window != null) {
            setContentView(inflate);
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            if (attributes != null) {
                attributes.width = -1;
            }
            if (attributes != null) {
                attributes.height = -1;
            }
            window.setAttributes(attributes);
            this.downloading_num_show = inflate != null ? (TextView) inflate.findViewById(C4188R.id.downloading_num_show) : null;
            this.cancel = inflate != null ? inflate.findViewById(C4188R.id.cancel) : null;
        }
        View view = this.cancel;
        if (view != null) {
            view.setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.ShowDownloadingDialog$build$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(null, 0, 3, 0 == true ? 1 : 0);
                }

                @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
                public void onSingleClick() {
                    ShowDownloadingDialog.this.dismiss();
                    Function0<Unit> onCloseBtnClick = ShowDownloadingDialog.this.getOnCloseBtnClick();
                    if (onCloseBtnClick != null) {
                        onCloseBtnClick.invoke();
                    }
                }
            });
        }
        setCancelable(false);
    }

    public final void showProgress(String progress) {
        Intrinsics.checkParameterIsNotNull(progress, "progress");
        TextView textView = this.downloading_num_show;
        if (textView != null) {
            textView.setText(progress);
        }
    }

    public final void dismisscheckUpdateDownloadingDialog() {
        Pdlog.m3273d("ShowDownloadingDialog", "dismisscheckUpdateDownloadingDialog");
        TextView textView = this.downloading_num_show;
        if (textView != null) {
            textView.setText("");
        }
        dismiss();
    }

    public final void showCancel() {
        View view = this.cancel;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    public final void setTitle(String text) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        TextView textView = (TextView) findViewById(C4188R.id.title_tv);
        if (textView != null) {
            textView.setText(text);
        }
    }

    public final void hideCancel() {
        View view = this.cancel;
        if (view != null) {
            view.setVisibility(4);
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
    }
}
