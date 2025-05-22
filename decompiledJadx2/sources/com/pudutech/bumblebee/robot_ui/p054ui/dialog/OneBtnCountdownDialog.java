package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.CountdownUtil;
import com.pudutech.disinfect.baselib.util.NavigationBarUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: OneBtnCountdownDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\"\u001a\u00020\u0019H\u0002J\b\u0010#\u001a\u00020\u0019H\u0002J\b\u0010$\u001a\u00020\u0019H\u0002J\b\u0010%\u001a\u00020\u0019H\u0016J\b\u0010&\u001a\u00020\u0019H\u0016R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\t\"\u0004\b\u0016\u0010\u000bR\"\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR$\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\t\"\u0004\b!\u0010\u000b¨\u0006'"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/OneBtnCountdownDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", ES6Iterator.VALUE_PROPERTY, "", "btn1Text", "getBtn1Text", "()Ljava/lang/String;", "setBtn1Text", "(Ljava/lang/String;)V", "countdownDisposable", "Lio/reactivex/disposables/Disposable;", "countdownTime", "", "getCountdownTime", "()J", "setCountdownTime", "(J)V", NotificationCompat.CATEGORY_MESSAGE, "getMsg", "setMsg", "onBtn1Click", "Lkotlin/Function0;", "", "getOnBtn1Click", "()Lkotlin/jvm/functions/Function0;", "setOnBtn1Click", "(Lkotlin/jvm/functions/Function0;)V", "v", "title", "getTitle", "setTitle", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "countdown", "initView", "onDetachedFromWindow", "show", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class OneBtnCountdownDialog extends Dialog {
    private String btn1Text;
    private Disposable countdownDisposable;
    private long countdownTime;
    private String msg;
    private Function0<Unit> onBtn1Click;
    private String title;

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        this.title = v;
        TextView textView = (TextView) findViewById(C4188R.id.title_tv);
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
        TextView textView = (TextView) findViewById(C4188R.id.msg_tv);
        if (textView != null) {
            textView.setText(value);
        }
    }

    public final String getBtn1Text() {
        return this.btn1Text;
    }

    public final void setBtn1Text(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.btn1Text = value;
        Button button = (Button) findViewById(C4188R.id.btn_1);
        if (button != null) {
            button.setText(value);
        }
    }

    public final Function0<Unit> getOnBtn1Click() {
        return this.onBtn1Click;
    }

    public final void setOnBtn1Click(Function0<Unit> function0) {
        this.onBtn1Click = function0;
    }

    public final long getCountdownTime() {
        return this.countdownTime;
    }

    public final void setCountdownTime(long j) {
        this.countdownTime = j;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OneBtnCountdownDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.title = "";
        this.msg = "";
        this.btn1Text = "";
        this.countdownTime = 5L;
        build();
    }

    private final void build() {
        View inflate = getLayoutInflater().inflate(C4188R.layout.dialog_one_btn_countdown_tip, (ViewGroup) null);
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
        TextView textView = (TextView) findViewById(C4188R.id.title_tv);
        if (textView != null) {
            textView.setText(this.title);
        }
        TextView textView2 = (TextView) findViewById(C4188R.id.msg_tv);
        if (textView2 != null) {
            textView2.setText(this.msg);
        }
        Button button = (Button) findViewById(C4188R.id.btn_1);
        if (button != null) {
            button.setText(this.btn1Text);
        }
        Button button2 = (Button) findViewById(C4188R.id.btn_1);
        if (button2 != null) {
            button2.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.OneBtnCountdownDialog$initView$1
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
                    Function0<Unit> onBtn1Click = OneBtnCountdownDialog.this.getOnBtn1Click();
                    if (onBtn1Click != null) {
                        onBtn1Click.invoke();
                    }
                }
            }, 3, null));
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
        countdown();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Disposable disposable = this.countdownDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    private final void countdown() {
        this.countdownDisposable = CountdownUtil.INSTANCE.createCountDown(this.countdownTime).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.OneBtnCountdownDialog$countdown$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Long l) {
                String str = OneBtnCountdownDialog.this.getBtn1Text() + " (" + l + ')';
                SpannableString spannableString = new SpannableString(str);
                spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(OneBtnCountdownDialog.this.getContext(), C4188R.color.c_7fffffff)), OneBtnCountdownDialog.this.getBtn1Text().length(), str.length(), 33);
                Button button = (Button) OneBtnCountdownDialog.this.findViewById(C4188R.id.btn_1);
                if (button != null) {
                    button.setText(spannableString);
                }
            }
        }, new Consumer<Throwable>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.OneBtnCountdownDialog$countdown$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
            }
        }, new Action() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.OneBtnCountdownDialog$countdown$3
            @Override // io.reactivex.functions.Action
            public final void run() {
                Function0<Unit> onBtn1Click = OneBtnCountdownDialog.this.getOnBtn1Click();
                if (onBtn1Click != null) {
                    onBtn1Click.invoke();
                }
                OneBtnCountdownDialog.this.dismiss();
            }
        });
    }
}
