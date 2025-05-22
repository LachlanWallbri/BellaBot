package com.pudutech.bumblebee.robot_ui.p054ui.custom_call;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.bumblebee.robot_ui.util.CountdownUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PaySuccessDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u000eH\u0016J\u0010\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\bH\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/PaySuccessDialog;", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/BumbleBaseDialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "countdownDisposable", "Lio/reactivex/disposables/Disposable;", "mCountDown", "", "getMCountDown", "()J", "setMCountDown", "(J)V", "dismiss", "", "getLayoutId", "", "initView", "view", "Landroid/view/View;", "show", "updataUi", "time", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class PaySuccessDialog extends BumbleBaseDialog {
    private Disposable countdownDisposable;
    private long mCountDown;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PaySuccessDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mCountDown = 5L;
    }

    public final long getMCountDown() {
        return this.mCountDown;
    }

    public final void setMCountDown(long j) {
        this.mCountDown = j;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    public int getLayoutId() {
        return C4188R.layout.dialog_pay_success;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    public void initView(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        ((ConstraintLayout) findViewById(C4188R.id.confirm_cl)).setOnClickListener(new SingleClickListener(null, 0, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.PaySuccessDialog$initView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                PaySuccessDialog.this.dismiss();
            }
        }, 3, null));
        ((FrameLayout) findViewById(C4188R.id.pay_success_close)).setOnClickListener(new SingleClickListener(null, 0, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.PaySuccessDialog$initView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                PaySuccessDialog.this.dismiss();
            }
        }, 3, null));
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog, android.app.Dialog
    public void show() {
        super.show();
        Disposable disposable = this.countdownDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.countdownDisposable = CountdownUtil.INSTANCE.createCountDown(this.mCountDown).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.PaySuccessDialog$show$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Long it) {
                PaySuccessDialog paySuccessDialog = PaySuccessDialog.this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                paySuccessDialog.updataUi(it.longValue());
            }
        }, new Consumer<Throwable>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.PaySuccessDialog$show$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
            }
        }, new Action() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.PaySuccessDialog$show$3
            @Override // io.reactivex.functions.Action
            public final void run() {
                PaySuccessDialog.this.dismiss();
            }
        });
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        Disposable disposable = this.countdownDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        Function0<Unit> onCloseListener = getOnCloseListener();
        if (onCloseListener != null) {
            onCloseListener.invoke();
        }
        super.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updataUi(long time) {
        TextView pay_success_down = (TextView) findViewById(C4188R.id.pay_success_down);
        Intrinsics.checkExpressionValueIsNotNull(pay_success_down, "pay_success_down");
        pay_success_down.setText('(' + time + "s)");
    }
}
