package com.pudutech.peanut.robot_ui.widget;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.bean.TaskModel;
import com.pudutech.peanut.robot_ui.manager.TableTaskManager;
import com.pudutech.peanut.robot_ui.p063ui.helper.PalletCountHelper;
import com.pudutech.peanut.robot_ui.p063ui.view.RobotReturnLayout;
import com.pudutech.peanut.robot_ui.util.NavigationBarUtil;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BottomReturnDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\b\u0010\u000b\u001a\u00020\bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/widget/BottomReturnDialog;", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "context", "Landroid/content/Context;", "type", "", "(Landroid/content/Context;I)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "show", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class BottomReturnDialog extends BottomSheetDialog {
    private final int type;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BottomReturnDialog(Context context, int i) {
        super(context, C5508R.style.ActionSheetDialogStyle);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.type = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.material.bottomsheet.BottomSheetDialog, androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((FrameLayout) findViewById(C5508R.id.flCancel)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.widget.BottomReturnDialog$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TableTaskManager.INSTANCE.clearAllReturnTask();
                BottomReturnDialog.this.dismiss();
            }
        });
        Pdlog.m3274e(TAG, "BottomReturnDialog show");
        ((RobotReturnLayout) findViewById(C5508R.id.robotLayout)).setInputTaskCount(PalletCountHelper.INSTANCE.getCount());
        Pdlog.m3273d(TAG, "setTaskListLayout");
        ((RobotReturnLayout) findViewById(C5508R.id.robotLayout)).start();
        if (this.type == 0) {
            TextView tvTask = (TextView) findViewById(C5508R.id.tvTask);
            Intrinsics.checkExpressionValueIsNotNull(tvTask, "tvTask");
            tvTask.setVisibility(8);
            RobotReturnLayout.initData$default((RobotReturnLayout) findViewById(C5508R.id.robotLayout), TableTaskManager.INSTANCE.getAllReturnTask(), TableTaskManager.INSTANCE.getCurrentTask(), null, 4, null);
            return;
        }
        TextView tvTask2 = (TextView) findViewById(C5508R.id.tvTask);
        Intrinsics.checkExpressionValueIsNotNull(tvTask2, "tvTask");
        tvTask2.setVisibility(0);
        ArrayList<TaskModel> allHistoryReturnTask = TableTaskManager.INSTANCE.getAllHistoryReturnTask();
        if (allHistoryReturnTask != null) {
            RobotReturnLayout.initData$default((RobotReturnLayout) findViewById(C5508R.id.robotLayout), allHistoryReturnTask, TableTaskManager.INSTANCE.getCurrentTask(), null, 4, null);
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
        NavigationBarUtil.hideNavigationBars(window2);
        Window window3 = getWindow();
        if (window3 == null) {
            Intrinsics.throwNpe();
        }
        NavigationBarUtil.clearFocusNotAle(window3);
    }

    /* compiled from: BottomReturnDialog.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/widget/BottomReturnDialog$Companion;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "newInstance", "Lcom/pudutech/peanut/robot_ui/widget/BottomReturnDialog;", "context", "Landroid/content/Context;", "type", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getTAG() {
            return BottomReturnDialog.TAG;
        }

        public final BottomReturnDialog newInstance(Context context, int type) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            BottomReturnDialog bottomReturnDialog = new BottomReturnDialog(context, type);
            if (Build.VERSION.SDK_INT >= 19) {
                bottomReturnDialog.getWindow().addFlags(134217728);
            }
            bottomReturnDialog.setContentView(LayoutInflater.from(context).inflate(C5508R.layout.bottom_sheet_return, (ViewGroup) null));
            return bottomReturnDialog;
        }
    }
}
