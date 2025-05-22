package com.pudutech.peanut.robot_ui.p063ui.dialog;

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
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.bean.TaskModel;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.manager.TableTaskManager;
import com.pudutech.peanut.robot_ui.p063ui.helper.PalletCountHelper;
import com.pudutech.peanut.robot_ui.p063ui.view.RobotReturnLayout;
import com.pudutech.peanut.robot_ui.util.NavigationBarUtil;
import com.pudutech.peanut.robot_ui.widget.BottomReturnDialog;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReturnTipDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\tJ\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0014H\u0016R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/dialog/ReturnTipDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "mType", "(Landroid/content/Context;II)V", "TAG", "", "kotlin.jvm.PlatformType", "_context", "robotLayout", "Lcom/pudutech/peanut/robot_ui/ui/view/RobotReturnLayout;", "tvTask", "Landroid/widget/TextView;", "type", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "", "init", "initView", "mView", "Landroid/view/View;", "show", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public class ReturnTipDialog extends Dialog {
    private final String TAG;
    private Context _context;
    private RobotReturnLayout robotLayout;
    private TextView tvTask;
    private int type;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReturnTipDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReturnTipDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReturnTipDialog(Context context, int i, int i2) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.type = i2;
        init(context);
    }

    private final void init(Context context) {
        this._context = context;
        build();
    }

    private final void build() {
        View viewLayout = getLayoutInflater().inflate(C5508R.layout.bottom_sheet_return, (ViewGroup) null);
        requestWindowFeature(1);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(80);
            window.setAttributes(attributes);
            window.setWindowAnimations(C5508R.style.popwin_anim);
            setContentView(viewLayout);
        }
        setCancelable(true);
        Intrinsics.checkExpressionValueIsNotNull(viewLayout, "viewLayout");
        initView(viewLayout);
    }

    private final void initView(View mView) {
        RobotReturnLayout robotReturnLayout;
        this.robotLayout = (RobotReturnLayout) mView.findViewById(C5508R.id.robotLayout);
        this.tvTask = (TextView) mView.findViewById(C5508R.id.tvTask);
        View findViewById = mView.findViewById(C5508R.id.flCancel);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "mView.findViewById<FrameLayout>(R.id.flCancel)");
        ViewExtKt.onSingleClick(findViewById, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.ReturnTipDialog$initView$1
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
                ReturnTipDialog.this.dismiss();
            }
        });
        Pdlog.m3274e(BottomReturnDialog.INSTANCE.getTAG(), "BottomReturnDialog show");
        RobotReturnLayout robotReturnLayout2 = this.robotLayout;
        if (robotReturnLayout2 != null) {
            robotReturnLayout2.setInputTaskCount(PalletCountHelper.INSTANCE.getCount());
        }
        Pdlog.m3273d(BottomReturnDialog.INSTANCE.getTAG(), "setTaskListLayout");
        RobotReturnLayout robotReturnLayout3 = this.robotLayout;
        if (robotReturnLayout3 != null) {
            robotReturnLayout3.start();
        }
        if (this.type == 0) {
            TextView textView = this.tvTask;
            if (textView != null) {
                textView.setVisibility(8);
            }
            RobotReturnLayout robotReturnLayout4 = this.robotLayout;
            if (robotReturnLayout4 != null) {
                RobotReturnLayout.initData$default(robotReturnLayout4, TableTaskManager.INSTANCE.getAllReturnTask(), TableTaskManager.INSTANCE.getCurrentTask(), null, 4, null);
                return;
            }
            return;
        }
        TextView textView2 = this.tvTask;
        if (textView2 != null) {
            textView2.setVisibility(0);
        }
        ArrayList<TaskModel> allHistoryReturnTask = TableTaskManager.INSTANCE.getAllHistoryReturnTask();
        if (allHistoryReturnTask == null || (robotReturnLayout = this.robotLayout) == null) {
            return;
        }
        RobotReturnLayout.initData$default(robotReturnLayout, allHistoryReturnTask, TableTaskManager.INSTANCE.getCurrentTask(), null, 4, null);
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
            window4.setLayout(-1, -2);
        }
    }
}
