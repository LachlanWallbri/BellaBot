package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.NavigationBarUtil;
import com.pudutech.disinfect.baselib.ext.view.ViewExtKt;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.lib_update.module.model.Version;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppUpdateTipMsgDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0018\u001a\u00020\rH\u0002J\u000e\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001bJ\u0010\u0010\u001c\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010\u001d\u001a\u00020\rH\u0002J\b\u0010\u001e\u001a\u00020\rH\u0016J\u000e\u0010\u001f\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001bR\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R(\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006 "}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/AppUpdateTipMsgDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "vr", "Lcom/pudutech/lib_update/module/model/VerionResult;", "(Landroid/content/Context;Lcom/pudutech/lib_update/module/model/VerionResult;)V", "TAG", "", "kotlin.jvm.PlatformType", "_context", "onCloseBtnClick", "Lkotlin/Function0;", "", "getOnCloseBtnClick", "()Lkotlin/jvm/functions/Function0;", "setOnCloseBtnClick", "(Lkotlin/jvm/functions/Function0;)V", "onUpdateClick", "Lkotlin/Function1;", "getOnUpdateClick", "()Lkotlin/jvm/functions/Function1;", "setOnUpdateClick", "(Lkotlin/jvm/functions/Function1;)V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "canCancel", "c", "", "init", "initView", "show", "showBatteryInfo", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public class AppUpdateTipMsgDialog extends Dialog {
    private final String TAG;
    private Context _context;
    private Function0<Unit> onCloseBtnClick;
    private Function1<? super VerionResult, Unit> onUpdateClick;
    private VerionResult vr;

    public static final /* synthetic */ VerionResult access$getVr$p(AppUpdateTipMsgDialog appUpdateTipMsgDialog) {
        VerionResult verionResult = appUpdateTipMsgDialog.vr;
        if (verionResult == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vr");
        }
        return verionResult;
    }

    public final Function0<Unit> getOnCloseBtnClick() {
        return this.onCloseBtnClick;
    }

    public final void setOnCloseBtnClick(Function0<Unit> function0) {
        this.onCloseBtnClick = function0;
    }

    public final Function1<VerionResult, Unit> getOnUpdateClick() {
        return this.onUpdateClick;
    }

    public final void setOnUpdateClick(Function1<? super VerionResult, Unit> function1) {
        this.onUpdateClick = function1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppUpdateTipMsgDialog(Context context, VerionResult vr) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(vr, "vr");
        this.TAG = getClass().getSimpleName();
        this.vr = vr;
        init(context);
    }

    private final void init(Context context) {
        this._context = context;
        build();
    }

    private final void build() {
        View inflate = getLayoutInflater().inflate(C4188R.layout.fragment_version_setup_check_result_dialog, (ViewGroup) null);
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
        TextView textView = (TextView) findViewById(C4188R.id.show_check_version);
        if (textView != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr7_32));
            sb.append("：");
            VerionResult verionResult = this.vr;
            if (verionResult == null) {
                Intrinsics.throwUninitializedPropertyAccessException("vr");
            }
            Version version = verionResult.getVersion();
            sb.append(version != null ? version.getVersion_name() : null);
            textView.setText(sb.toString());
        }
        TextView textView2 = (TextView) findViewById(C4188R.id.edit_check_update_result_show);
        if (textView2 != null) {
            VerionResult verionResult2 = this.vr;
            if (verionResult2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("vr");
            }
            Version version2 = verionResult2.getVersion();
            textView2.setText(version2 != null ? version2.getVersion_description() : null);
        }
        FrameLayout frameLayout = (FrameLayout) findViewById(C4188R.id.cancel);
        if (frameLayout != null) {
            frameLayout.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.AppUpdateTipMsgDialog$initView$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view) {
                    invoke2(view);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Function0<Unit> onCloseBtnClick = AppUpdateTipMsgDialog.this.getOnCloseBtnClick();
                    if (onCloseBtnClick != null) {
                        onCloseBtnClick.invoke();
                    }
                    AppUpdateTipMsgDialog.this.dismiss();
                }
            }, 3, null));
        }
        Button button = (Button) findViewById(C4188R.id.update);
        if (button != null) {
            button.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.AppUpdateTipMsgDialog$initView$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view) {
                    invoke2(view);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Function1<VerionResult, Unit> onUpdateClick = AppUpdateTipMsgDialog.this.getOnUpdateClick();
                    if (onUpdateClick != null) {
                        onUpdateClick.invoke(AppUpdateTipMsgDialog.access$getVr$p(AppUpdateTipMsgDialog.this));
                    }
                }
            }, 3, null));
        }
    }

    public final void canCancel(boolean c) {
        if (c) {
            FrameLayout frameLayout = (FrameLayout) findViewById(C4188R.id.cancel);
            if (frameLayout != null) {
                frameLayout.setVisibility(0);
                return;
            }
            return;
        }
        FrameLayout frameLayout2 = (FrameLayout) findViewById(C4188R.id.cancel);
        if (frameLayout2 != null) {
            frameLayout2.setVisibility(8);
        }
    }

    public final void showBatteryInfo(boolean show) {
        TextView tvInfo = (TextView) findViewById(C4188R.id.tvInfo);
        Intrinsics.checkExpressionValueIsNotNull(tvInfo, "tvInfo");
        ViewExtKt.visibleOrGone(tvInfo, show);
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
