package com.pudutech.peanut.robot_ui.p063ui.dialog;

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
import androidx.core.app.NotificationCompat;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.util.NavigationBarUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: CustomMsgDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0010\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010#\u001a\u00020\u0017H\u0002J\u0010\u0010$\u001a\u00020\u00172\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010%\u001a\u00020\u0017H\u0002J\b\u0010&\u001a\u00020\u0017H\u0016R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\r\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0012\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\"\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\"\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR$\u0010 \u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\t@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u000f\"\u0004\b\"\u0010\u0011¨\u0006'"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/dialog/CustomMsgDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "TAG", "", "kotlin.jvm.PlatformType", "_context", ES6Iterator.VALUE_PROPERTY, "btnText", "getBtnText", "()Ljava/lang/String;", "setBtnText", "(Ljava/lang/String;)V", NotificationCompat.CATEGORY_MESSAGE, "getMsg", "setMsg", "onBtn1Click", "Lkotlin/Function0;", "", "getOnBtn1Click", "()Lkotlin/jvm/functions/Function0;", "setOnBtn1Click", "(Lkotlin/jvm/functions/Function0;)V", "onCloseBtnClick", "getOnCloseBtnClick", "setOnCloseBtnClick", "v", "title", "getTitle", "setTitle", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "init", "initView", "show", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public class CustomMsgDialog extends Dialog {
    private final String TAG;
    private Context _context;
    private String btnText;
    private String msg;
    private Function0<Unit> onBtn1Click;
    private Function0<Unit> onCloseBtnClick;
    private String title;

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        this.title = v;
        TextView textView = (TextView) findViewById(C5508R.id.title_tv);
        if (textView != null) {
            textView.setText(v);
        }
    }

    public final String getMsg() {
        return this.msg;
    }

    public final void setMsg(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.msg = value;
        TextView textView = (TextView) findViewById(C5508R.id.msg_tv);
        if (textView != null) {
            textView.setText(value);
        }
    }

    public final String getBtnText() {
        return this.btnText;
    }

    public final void setBtnText(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.btnText = value;
        Button button = (Button) findViewById(C5508R.id.btn_1);
        if (button != null) {
            button.setText(value);
        }
    }

    public final Function0<Unit> getOnCloseBtnClick() {
        return this.onCloseBtnClick;
    }

    public final void setOnCloseBtnClick(Function0<Unit> function0) {
        this.onCloseBtnClick = function0;
    }

    public final Function0<Unit> getOnBtn1Click() {
        return this.onBtn1Click;
    }

    public final void setOnBtn1Click(Function0<Unit> function0) {
        this.onBtn1Click = function0;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomMsgDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.title = "";
        this.msg = "";
        this.btnText = "";
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomMsgDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.title = "";
        this.msg = "";
        this.btnText = "";
        init(context);
    }

    private final void init(Context context) {
        this._context = context;
        build();
    }

    private final void build() {
        View inflate = getLayoutInflater().inflate(C5508R.layout.dialog_costom_msg, (ViewGroup) null);
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
        TextView textView = (TextView) findViewById(C5508R.id.title_tv);
        if (textView != null) {
            textView.setText(this.title);
        }
        TextView textView2 = (TextView) findViewById(C5508R.id.msg_tv);
        if (textView2 != null) {
            textView2.setText(this.msg);
        }
        Button button = (Button) findViewById(C5508R.id.btn_1);
        if (button != null) {
            button.setText(this.btnText);
        }
        FrameLayout frameLayout = (FrameLayout) findViewById(C5508R.id.cancel);
        if (frameLayout != null) {
            frameLayout.setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.CustomMsgDialog$initView$1
                @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
                public void onSingleClick() {
                    Function0<Unit> onCloseBtnClick = CustomMsgDialog.this.getOnCloseBtnClick();
                    if (onCloseBtnClick != null) {
                        onCloseBtnClick.invoke();
                    }
                    CustomMsgDialog.this.dismiss();
                }
            });
        }
        Button button2 = (Button) findViewById(C5508R.id.btn_1);
        if (button2 != null) {
            button2.setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.CustomMsgDialog$initView$2
                @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
                public void onSingleClick() {
                    Function0<Unit> onBtn1Click = CustomMsgDialog.this.getOnBtn1Click();
                    if (onBtn1Click != null) {
                        onBtn1Click.invoke();
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
