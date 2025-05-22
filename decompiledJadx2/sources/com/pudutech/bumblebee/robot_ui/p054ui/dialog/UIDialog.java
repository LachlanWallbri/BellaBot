package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.NavigationBarUtil;
import com.pudutech.disinfect.baselib.ext.view.ViewExtKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: UIDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001-B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010!\u001a\u00020\u00182\b\b\u0002\u0010\"\u001a\u00020#J2\u0010$\u001a\u00020\u00182\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010%\u001a\u00020&2\u0016\b\u0002\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0013J\u000e\u0010'\u001a\u00020\u00182\u0006\u0010(\u001a\u00020#J\u0010\u0010)\u001a\u00020\u00182\u0006\u0010*\u001a\u00020#H\u0016J\b\u0010+\u001a\u00020\u0018H\u0016J2\u0010,\u001a\u00020\u00182\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010%\u001a\u00020&2\u0016\b\u0002\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0013R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R+\u0010\u0012\u001a\u001f\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u0019\u001a\u001f\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u001b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u000f\"\u0004\b\u001d\u0010\u0011R$\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\t\"\u0004\b \u0010\u000b¨\u0006."}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/UIDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", ES6Iterator.VALUE_PROPERTY, "", AIUIConstant.KEY_CONTENT, "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/UIDialog$ButtonStyle;", "endButtonStyle", "getEndButtonStyle", "()Lcom/pudutech/bumblebee/robot_ui/ui/dialog/UIDialog$ButtonStyle;", "setEndButtonStyle", "(Lcom/pudutech/bumblebee/robot_ui/ui/dialog/UIDialog$ButtonStyle;)V", "onNegativeButtonClicked", "Lkotlin/Function1;", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "negativeButton", "", "onPositiveButtonClicked", "positiveButton", "startButtonStyle", "getStartButtonStyle", "setStartButtonStyle", "title", "getTitle", "setTitle", "closeVisible", "visible", "", "endButton", "text", "", "loadImgShow", "isShow", "onWindowFocusChanged", "hasFocus", "show", "startButton", "ButtonStyle", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class UIDialog extends Dialog {
    private String content;
    private ButtonStyle endButtonStyle;
    private Function1<? super View, Unit> onNegativeButtonClicked;
    private Function1<? super View, Unit> onPositiveButtonClicked;
    private ButtonStyle startButtonStyle;
    private String title;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UIDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        requestWindowFeature(1);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(17);
            window.setAttributes(attributes);
            setContentView(C4188R.layout.dialog_ui);
        }
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        ConstraintLayout cl_left = (ConstraintLayout) findViewById(C4188R.id.cl_left);
        Intrinsics.checkExpressionValueIsNotNull(cl_left, "cl_left");
        final Map emptyMap = MapsKt.emptyMap();
        final int i = 0;
        cl_left.setOnClickListener(new OnLazyVoiceClickListener(emptyMap, i) { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.UIDialog$$special$$inlined$singleClick$1
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick(View v) {
                Function1 function1;
                Intrinsics.checkParameterIsNotNull(v, "v");
                super.onSingleClick(v);
                function1 = this.onNegativeButtonClicked;
                if (function1 != null) {
                }
            }
        });
        Button btnEnd = (Button) findViewById(C4188R.id.btnEnd);
        Intrinsics.checkExpressionValueIsNotNull(btnEnd, "btnEnd");
        final Map emptyMap2 = MapsKt.emptyMap();
        btnEnd.setOnClickListener(new OnLazyVoiceClickListener(emptyMap2, i) { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.UIDialog$$special$$inlined$singleClick$2
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick(View v) {
                Function1 function1;
                Intrinsics.checkParameterIsNotNull(v, "v");
                super.onSingleClick(v);
                function1 = this.onPositiveButtonClicked;
                if (function1 != null) {
                }
            }
        });
        ImageView ivClose = (ImageView) findViewById(C4188R.id.ivClose);
        Intrinsics.checkExpressionValueIsNotNull(ivClose, "ivClose");
        final Map emptyMap3 = MapsKt.emptyMap();
        ivClose.setOnClickListener(new OnLazyVoiceClickListener(emptyMap3, i) { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.UIDialog$$special$$inlined$singleClick$3
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick(View v) {
                Intrinsics.checkParameterIsNotNull(v, "v");
                super.onSingleClick(v);
                this.dismiss();
            }
        });
        this.title = "";
        this.content = "";
        this.startButtonStyle = ButtonStyle.Cancel;
        this.endButtonStyle = ButtonStyle.Confirm;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.title = value;
        TextView tvTitle = (TextView) findViewById(C4188R.id.tvTitle);
        Intrinsics.checkExpressionValueIsNotNull(tvTitle, "tvTitle");
        tvTitle.setText(this.title);
    }

    public final String getContent() {
        return this.content;
    }

    public final void setContent(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.content = value;
        TextView tvContent = (TextView) findViewById(C4188R.id.tvContent);
        Intrinsics.checkExpressionValueIsNotNull(tvContent, "tvContent");
        tvContent.setText(this.content);
    }

    public final ButtonStyle getStartButtonStyle() {
        return this.startButtonStyle;
    }

    public final void setStartButtonStyle(ButtonStyle value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.startButtonStyle = value;
        ((TextView) findViewById(C4188R.id.btnStart)).setTextColor(ContextCompat.getColor(getContext(), this.startButtonStyle.getTextColor()));
        ((TextView) findViewById(C4188R.id.btnStart)).setBackgroundResource(this.startButtonStyle.getBackground());
    }

    public final ButtonStyle getEndButtonStyle() {
        return this.endButtonStyle;
    }

    public final void setEndButtonStyle(ButtonStyle value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.endButtonStyle = value;
        ((Button) findViewById(C4188R.id.btnEnd)).setTextColor(ContextCompat.getColor(getContext(), this.endButtonStyle.getTextColor()));
        ((Button) findViewById(C4188R.id.btnEnd)).setBackgroundResource(this.endButtonStyle.getBackground());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void startButton$default(UIDialog uIDialog, boolean z, CharSequence charSequence, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            String string = uIDialog.getContext().getString(C4188R.string.pdStr8_4);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.pdStr8_4)");
            charSequence = string;
        }
        if ((i & 4) != 0) {
            function1 = (Function1) null;
        }
        uIDialog.startButton(z, charSequence, function1);
    }

    public final void startButton(boolean visible, CharSequence text, Function1<? super View, Unit> onNegativeButtonClicked) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        this.onNegativeButtonClicked = onNegativeButtonClicked;
        TextView btnStart = (TextView) findViewById(C4188R.id.btnStart);
        Intrinsics.checkExpressionValueIsNotNull(btnStart, "btnStart");
        btnStart.setText(text);
        TextView btnStart2 = (TextView) findViewById(C4188R.id.btnStart);
        Intrinsics.checkExpressionValueIsNotNull(btnStart2, "btnStart");
        btnStart2.setVisibility(visible ? 0 : 8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void endButton$default(UIDialog uIDialog, boolean z, CharSequence charSequence, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            String string = uIDialog.getContext().getString(C4188R.string.pdStr8_3);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.pdStr8_3)");
            charSequence = string;
        }
        if ((i & 4) != 0) {
            function1 = (Function1) null;
        }
        uIDialog.endButton(z, charSequence, function1);
    }

    public final void endButton(boolean visible, CharSequence text, Function1<? super View, Unit> onPositiveButtonClicked) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        this.onPositiveButtonClicked = onPositiveButtonClicked;
        Button btnEnd = (Button) findViewById(C4188R.id.btnEnd);
        Intrinsics.checkExpressionValueIsNotNull(btnEnd, "btnEnd");
        btnEnd.setText(text);
        Button btnEnd2 = (Button) findViewById(C4188R.id.btnEnd);
        Intrinsics.checkExpressionValueIsNotNull(btnEnd2, "btnEnd");
        btnEnd2.setVisibility(visible ? 0 : 8);
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

    public static /* synthetic */ void closeVisible$default(UIDialog uIDialog, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        uIDialog.closeVisible(z);
    }

    public final void closeVisible(boolean visible) {
        ImageView ivClose = (ImageView) findViewById(C4188R.id.ivClose);
        Intrinsics.checkExpressionValueIsNotNull(ivClose, "ivClose");
        ivClose.setVisibility(visible ? 0 : 8);
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

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            Window window = getWindow();
            if (window == null) {
                Intrinsics.throwNpe();
            }
            NavigationBarUtil.focusNotAle(window);
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

    /* compiled from: UIDialog.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001b\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007j\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/UIDialog$ButtonStyle;", "", "textColor", "", "background", "(Ljava/lang/String;III)V", "getBackground", "()I", "getTextColor", "Confirm", "Cancel", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public enum ButtonStyle {
        Confirm(C4188R.color.white, C4188R.drawable.selector_tip_dialog_btn),
        Cancel(C4188R.color.theme_main_color, C4188R.drawable.rectangle_tip_dialog_btn_blu_exit);

        private final int background;
        private final int textColor;

        ButtonStyle(int i, int i2) {
            this.textColor = i;
            this.background = i2;
        }

        public final int getBackground() {
            return this.background;
        }

        public final int getTextColor() {
            return this.textColor;
        }
    }
}
