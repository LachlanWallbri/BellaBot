package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.disinfect.baselib.ext.view.ViewExtKt;
import com.pudutech.disinfect.baselib.util.NavigationBarUtil;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LockMachineDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010&\u001a\u00020\u0010H\u0016J\u0010\u0010'\u001a\u00020!2\u0006\u0010(\u001a\u00020\u001dH\u0014J\u000e\u0010)\u001a\u00020!2\u0006\u0010*\u001a\u00020\nJ\u0010\u0010+\u001a\u00020!2\u0006\u0010,\u001a\u00020\nH\u0016J\b\u0010-\u001a\u00020!H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR7\u0010\u001b\u001a\u001f\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020!\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u0006."}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/LockMachineDialog;", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/BumbleBaseDialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "clickable", "", "getClickable", "()Z", "setClickable", "(Z)V", "mClickCount", "", "getMClickCount", "()I", "setMClickCount", "(I)V", "mCurTimer", "", "getMCurTimer", "()J", "setMCurTimer", "(J)V", "onNegativeButtonClicked", "Lkotlin/Function1;", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "negativeButton", "", "getOnNegativeButtonClicked", "()Lkotlin/jvm/functions/Function1;", "setOnNegativeButtonClicked", "(Lkotlin/jvm/functions/Function1;)V", "getLayoutId", "initView", "view", "loadImgShow", "isShow", "onWindowFocusChanged", "hasFocus", "show", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class LockMachineDialog extends BumbleBaseDialog {
    private final String TAG;
    private boolean clickable;
    private int mClickCount;
    private long mCurTimer;
    private Function1<? super View, Unit> onNegativeButtonClicked;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LockMachineDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "LockMachineDialog";
        this.clickable = true;
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final int getMClickCount() {
        return this.mClickCount;
    }

    public final void setMClickCount(int i) {
        this.mClickCount = i;
    }

    public final long getMCurTimer() {
        return this.mCurTimer;
    }

    public final void setMCurTimer(long j) {
        this.mCurTimer = j;
    }

    public final boolean getClickable() {
        return this.clickable;
    }

    public final void setClickable(boolean z) {
        this.clickable = z;
    }

    public final Function1<View, Unit> getOnNegativeButtonClicked() {
        return this.onNegativeButtonClicked;
    }

    public final void setOnNegativeButtonClicked(Function1<? super View, Unit> function1) {
        this.onNegativeButtonClicked = function1;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    public int getLayoutId() {
        return C4188R.layout.dialog_locked_machine;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    public void initView(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.initView(view);
        if (this.clickable) {
            ((FrameLayout) findViewById(C4188R.id.locked_machine_root)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.LockMachineDialog$initView$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                    invoke2(view2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    if (SystemClock.elapsedRealtime() - LockMachineDialog.this.getMCurTimer() <= 1000) {
                        LockMachineDialog lockMachineDialog = LockMachineDialog.this;
                        lockMachineDialog.setMClickCount(lockMachineDialog.getMClickCount() + 1);
                        if (LockMachineDialog.this.getMClickCount() > 4) {
                            Context mContext = LockMachineDialog.this.getMContext();
                            if (mContext != null) {
                                mContext.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
                            }
                            Pdlog.m3273d(LockMachineDialog.this.getTAG(), "open wifi:" + LockMachineDialog.this.getMClickCount());
                            LockMachineDialog.this.setMClickCount(0);
                        }
                    } else {
                        LockMachineDialog.this.setMClickCount(0);
                    }
                    LockMachineDialog.this.setMCurTimer(SystemClock.elapsedRealtime());
                    Pdlog.m3273d(LockMachineDialog.this.getTAG(), "open wifi###mClickCount:" + LockMachineDialog.this.getMClickCount() + "###mCurTimer:" + LockMachineDialog.this.getMCurTimer());
                }
            }, 3, null));
        }
        ConstraintLayout cl_left = (ConstraintLayout) findViewById(C4188R.id.cl_left);
        Intrinsics.checkExpressionValueIsNotNull(cl_left, "cl_left");
        final Map emptyMap = MapsKt.emptyMap();
        final int i = 0;
        cl_left.setOnClickListener(new OnLazyVoiceClickListener(emptyMap, i) { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.LockMachineDialog$initView$$inlined$singleClick$1
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick(View v) {
                Intrinsics.checkParameterIsNotNull(v, "v");
                super.onSingleClick(v);
                Function1<View, Unit> onNegativeButtonClicked = this.getOnNegativeButtonClicked();
                if (onNegativeButtonClicked != null) {
                    onNegativeButtonClicked.invoke(v);
                }
            }
        });
    }

    public final void loadImgShow(boolean isShow) {
        ImageView iv_left = (ImageView) findViewById(C4188R.id.iv_left);
        Intrinsics.checkExpressionValueIsNotNull(iv_left, "iv_left");
        ViewExtKt.visibleOrGone(iv_left, isShow);
        if (isShow) {
            ImageView imageView = (ImageView) findViewById(C4188R.id.iv_left);
            RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 180.0f, 1, 0.5f, 1, 0.5f);
            rotateAnimation.setInterpolator(new LinearInterpolator());
            rotateAnimation.setDuration(500L);
            rotateAnimation.setRepeatCount(0);
            imageView.startAnimation(rotateAnimation);
            return;
        }
        ((ImageView) findViewById(C4188R.id.iv_left)).clearAnimation();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog, android.app.Dialog
    public void show() {
        super.show();
        Window window = getWindow();
        if (window != null) {
            window.setLayout(-1, -1);
        }
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            NavigationBarUtil.focusNotAle(getWindow());
            NavigationBarUtil.hideNavigationBar(getWindow());
            NavigationBarUtil.clearFocusNotAle(getWindow());
        }
    }
}
