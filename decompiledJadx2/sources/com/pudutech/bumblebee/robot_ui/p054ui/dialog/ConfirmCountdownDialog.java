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
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.CountdownUtil;
import com.pudutech.bumblebee.robot_ui.util.NavigationBarUtil;
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

/* compiled from: ConfirmCountdownDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0014\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010<\u001a\u00020.H\u0002J\b\u0010=\u001a\u00020.H\u0016J\b\u0010>\u001a\u00020.H\u0002J\b\u0010?\u001a\u00020.H\u0016J\b\u0010@\u001a\u00020.H\u0016J\b\u0010A\u001a\u00020.H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00068\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R&\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00068\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0011\"\u0004\b\u001b\u0010\u0013R&\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00068\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0011\"\u0004\b\u001e\u0010\u0013R$\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0016\"\u0004\b!\u0010\u0018R$\u0010\"\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0016\"\u0004\b$\u0010\u0018R\u001a\u0010%\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010,\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\"\u00103\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00100\"\u0004\b5\u00102R\"\u00106\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00100\"\u0004\b8\u00102R$\u00109\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u0016\"\u0004\b;\u0010\u0018¨\u0006B"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/ConfirmCountdownDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "CLOSE_TIME_OUT", "", "TAG", "", "autoClose", "Ljava/lang/Runnable;", ES6Iterator.VALUE_PROPERTY, "btn1Bg", "getBtn1Bg", "()I", "setBtn1Bg", "(I)V", "btn1Text", "getBtn1Text", "()Ljava/lang/String;", "setBtn1Text", "(Ljava/lang/String;)V", "btn2Bg1", "getBtn2Bg1", "setBtn2Bg1", "btn2Bg2", "getBtn2Bg2", "setBtn2Bg2", "btn2Text", "getBtn2Text", "setBtn2Text", AIUIConstant.KEY_CONTENT, "getContent", "setContent", "countDown", "getCountDown", "()J", "setCountDown", "(J)V", "disposable", "Lio/reactivex/disposables/Disposable;", "onBtn1Click", "Lkotlin/Function0;", "", "getOnBtn1Click", "()Lkotlin/jvm/functions/Function0;", "setOnBtn1Click", "(Lkotlin/jvm/functions/Function0;)V", "onBtn2Click", "getOnBtn2Click", "setOnBtn2Click", "onCloseClick", "getOnCloseClick", "setOnCloseClick", "title", "getTitle", "setTitle", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "dismiss", "initView", "onAttachedToWindow", "onDetachedFromWindow", "show", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class ConfirmCountdownDialog extends Dialog {
    private final long CLOSE_TIME_OUT;
    private final String TAG;
    private final Runnable autoClose;
    private int btn1Bg;
    private String btn1Text;
    private int btn2Bg1;
    private int btn2Bg2;
    private String btn2Text;
    private String content;
    private long countDown;
    private Disposable disposable;
    private Function0<Unit> onBtn1Click;
    private Function0<Unit> onBtn2Click;
    private Function0<Unit> onCloseClick;
    private String title;

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.title = value;
        TextView textView = (TextView) findViewById(C4188R.id.title_tv);
        if (textView != null) {
            textView.setText(value);
        }
    }

    public final String getContent() {
        return this.content;
    }

    public final void setContent(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.content = value;
        TextView textView = (TextView) findViewById(C4188R.id.content_tv);
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

    public final String getBtn2Text() {
        return this.btn2Text;
    }

    public final void setBtn2Text(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.btn2Text = value;
        Button button = (Button) findViewById(C4188R.id.btn_2);
        if (button != null) {
            button.setText(value);
        }
    }

    public final long getCountDown() {
        return this.countDown;
    }

    public final void setCountDown(long j) {
        this.countDown = j;
    }

    public final int getBtn1Bg() {
        return this.btn1Bg;
    }

    public final void setBtn1Bg(int i) {
        this.btn1Bg = i;
        Button button = (Button) findViewById(C4188R.id.btn_1);
        if (button != null) {
            button.setBackground(ContextCompat.getDrawable(getContext(), i));
        }
    }

    public final int getBtn2Bg1() {
        return this.btn2Bg1;
    }

    public final void setBtn2Bg1(int i) {
        this.btn2Bg1 = i;
        Button button = (Button) findViewById(C4188R.id.btn_2);
        if (button != null) {
            button.setBackground(ContextCompat.getDrawable(getContext(), i));
        }
    }

    public final int getBtn2Bg2() {
        return this.btn2Bg2;
    }

    public final void setBtn2Bg2(int i) {
        this.btn2Bg2 = i;
        Button button = (Button) findViewById(C4188R.id.btn_2);
        if (button != null) {
            button.setBackground(ContextCompat.getDrawable(getContext(), i));
        }
    }

    public final Function0<Unit> getOnBtn1Click() {
        return this.onBtn1Click;
    }

    public final void setOnBtn1Click(Function0<Unit> function0) {
        this.onBtn1Click = function0;
    }

    public final Function0<Unit> getOnBtn2Click() {
        return this.onBtn2Click;
    }

    public final void setOnBtn2Click(Function0<Unit> function0) {
        this.onBtn2Click = function0;
    }

    public final Function0<Unit> getOnCloseClick() {
        return this.onCloseClick;
    }

    public final void setOnCloseClick(Function0<Unit> function0) {
        this.onCloseClick = function0;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConfirmCountdownDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "ConfirmCountdownDialog";
        this.CLOSE_TIME_OUT = 20000L;
        this.title = "";
        this.content = "";
        this.btn1Text = "";
        this.btn2Text = "";
        this.countDown = 5L;
        this.autoClose = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.ConfirmCountdownDialog$autoClose$1
            @Override // java.lang.Runnable
            public final void run() {
                ConfirmCountdownDialog.this.dismiss();
            }
        };
        this.btn1Bg = C4188R.drawable.selector_fb313b_af2229_radius_8;
        this.btn2Bg1 = C4188R.drawable.shape_b3d4ff_radius_8;
        this.btn2Bg2 = C4188R.drawable.selector_0072ff_004fb2_radius_8;
        build();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConfirmCountdownDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "ConfirmCountdownDialog";
        this.CLOSE_TIME_OUT = 20000L;
        this.title = "";
        this.content = "";
        this.btn1Text = "";
        this.btn2Text = "";
        this.countDown = 5L;
        this.autoClose = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.ConfirmCountdownDialog$autoClose$1
            @Override // java.lang.Runnable
            public final void run() {
                ConfirmCountdownDialog.this.dismiss();
            }
        };
        this.btn1Bg = C4188R.drawable.selector_fb313b_af2229_radius_8;
        this.btn2Bg1 = C4188R.drawable.shape_b3d4ff_radius_8;
        this.btn2Bg2 = C4188R.drawable.selector_0072ff_004fb2_radius_8;
        build();
    }

    private final void build() {
        View inflate = getLayoutInflater().inflate(C4188R.layout.dialog_confirm_countdown, (ViewGroup) null);
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
        TextView textView2 = (TextView) findViewById(C4188R.id.content_tv);
        if (textView2 != null) {
            textView2.setText(this.content);
        }
        Button button = (Button) findViewById(C4188R.id.btn_1);
        if (button != null) {
            button.setBackground(ContextCompat.getDrawable(button.getContext(), this.btn1Bg));
            button.setText(this.btn1Text);
            button.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.ConfirmCountdownDialog$initView$$inlined$apply$lambda$1
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
                    Function0<Unit> onBtn1Click = ConfirmCountdownDialog.this.getOnBtn1Click();
                    if (onBtn1Click != null) {
                        onBtn1Click.invoke();
                    }
                }
            }, 3, null));
        }
        Button button2 = (Button) findViewById(C4188R.id.btn_2);
        if (button2 != null) {
            button2.setBackground(ContextCompat.getDrawable(button2.getContext(), this.btn2Bg1));
            button2.setText(this.btn2Text);
            button2.setEnabled(false);
            button2.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.ConfirmCountdownDialog$initView$$inlined$apply$lambda$2
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
                    Function0<Unit> onBtn2Click = ConfirmCountdownDialog.this.getOnBtn2Click();
                    if (onBtn2Click != null) {
                        onBtn2Click.invoke();
                    }
                }
            }, 3, null));
        }
        ((ImageView) findViewById(C4188R.id.close_iv)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.ConfirmCountdownDialog$initView$3
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
                ConfirmCountdownDialog.this.dismiss();
                Function0<Unit> onCloseClick = ConfirmCountdownDialog.this.getOnCloseClick();
                if (onCloseClick != null) {
                    onCloseClick.invoke();
                }
            }
        }, 3, null));
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.disposable = CountdownUtil.INSTANCE.createCountDown(this.countDown).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.ConfirmCountdownDialog$onAttachedToWindow$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Long l) {
                String str = ConfirmCountdownDialog.this.getBtn2Text() + " (" + l + ')';
                SpannableString spannableString = new SpannableString(str);
                spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(ConfirmCountdownDialog.this.getContext(), C4188R.color.c_7fffffff)), ConfirmCountdownDialog.this.getBtn2Text().length(), str.length(), 33);
                Button button = (Button) ConfirmCountdownDialog.this.findViewById(C4188R.id.btn_2);
                if (button != null) {
                    button.setText(spannableString);
                }
            }
        }, new Consumer<Throwable>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.ConfirmCountdownDialog$onAttachedToWindow$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
            }
        }, new Action() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.ConfirmCountdownDialog$onAttachedToWindow$3
            @Override // io.reactivex.functions.Action
            public final void run() {
                Button button = (Button) ConfirmCountdownDialog.this.findViewById(C4188R.id.btn_2);
                if (button != null) {
                    button.setText(ConfirmCountdownDialog.this.getBtn2Text());
                    button.setBackground(ContextCompat.getDrawable(button.getContext(), ConfirmCountdownDialog.this.getBtn2Bg2()));
                    button.setEnabled(true);
                }
            }
        });
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        TextView textView = (TextView) findViewById(C4188R.id.title_tv);
        if (textView != null) {
            textView.removeCallbacks(this.autoClose);
        }
        super.dismiss();
    }

    @Override // android.app.Dialog
    public void show() {
        NavigationBarUtil.focusNotAle(getWindow());
        super.show();
        NavigationBarUtil.hideNavigationBar(getWindow());
        NavigationBarUtil.clearFocusNotAle(getWindow());
        Window window = getWindow();
        if (window != null) {
            window.setLayout(-1, -1);
        }
        TextView textView = (TextView) findViewById(C4188R.id.title_tv);
        if (textView != null) {
            textView.postDelayed(this.autoClose, this.CLOSE_TIME_OUT);
        }
    }
}
