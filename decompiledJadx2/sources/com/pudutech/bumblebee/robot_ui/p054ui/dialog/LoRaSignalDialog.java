package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

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
import com.pudutech.disinfect.baselib.ext.view.ViewExtKt;
import com.pudutech.disinfect.baselib.util.NavigationBarUtil;
import com.pudutech.log.ProxyLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoRaSignalDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0018B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\b\u0010\u0012\u001a\u00020\u0010H\u0016J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u000e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0017\u001a\u00020\u0010H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/LoRaSignalDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "TAG", "", "llNoSignalLayout", "Landroid/view/View;", "llSignalLayout", "tvSignType", "Landroid/widget/TextView;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "", "onAttachedToWindow", "onDetachedFromWindow", "realSetSign", "type", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/LoRaSignalDialog$SignType;", "setSignalType", "show", "SignType", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class LoRaSignalDialog extends Dialog {
    private final String TAG;
    private View llNoSignalLayout;
    private View llSignalLayout;
    private TextView tvSignType;

    /* compiled from: LoRaSignalDialog.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/LoRaSignalDialog$SignType;", "", "(Ljava/lang/String;I)V", "Strong", "Middle", "Weak", "None", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public enum SignType {
        Strong,
        Middle,
        Weak,
        None
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SignType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[SignType.None.ordinal()] = 1;
            $EnumSwitchMapping$1 = new int[SignType.values().length];
            $EnumSwitchMapping$1[SignType.Strong.ordinal()] = 1;
            $EnumSwitchMapping$1[SignType.Middle.ordinal()] = 2;
            $EnumSwitchMapping$1[SignType.Weak.ordinal()] = 3;
            $EnumSwitchMapping$1[SignType.None.ordinal()] = 4;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoRaSignalDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "LoRaSignalDialog";
        build();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoRaSignalDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "LoRaSignalDialog";
        build();
    }

    public final void setSignalType(SignType type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        ProxyLog.INSTANCE.mo3285i(this.TAG, "setSignalType > " + type);
        if (WhenMappings.$EnumSwitchMapping$0[type.ordinal()] == 1) {
            View view = this.llSignalLayout;
            if (view != null) {
                ViewExtKt.invisible(view);
            }
            View view2 = this.llNoSignalLayout;
            if (view2 != null) {
                ViewExtKt.visible(view2);
                return;
            }
            return;
        }
        View view3 = this.llSignalLayout;
        if (view3 != null) {
            ViewExtKt.visible(view3);
        }
        View view4 = this.llNoSignalLayout;
        if (view4 != null) {
            ViewExtKt.invisible(view4);
        }
        realSetSign(type);
    }

    private final void realSetSign(SignType type) {
        int i = WhenMappings.$EnumSwitchMapping$1[type.ordinal()];
        if (i == 1) {
            TextView textView = this.tvSignType;
            if (textView != null) {
                textView.setTextColor((int) 4278216447L);
            }
            TextView textView2 = this.tvSignType;
            if (textView2 != null) {
                textView2.setText(getContext().getString(C4188R.string.call_lora_sign_strong));
                return;
            }
            return;
        }
        if (i == 2) {
            TextView textView3 = this.tvSignType;
            if (textView3 != null) {
                textView3.setTextColor((int) 4281516657L);
            }
            TextView textView4 = this.tvSignType;
            if (textView4 != null) {
                textView4.setText(getContext().getString(C4188R.string.call_lora_sign_middle));
                return;
            }
            return;
        }
        if (i != 3) {
            return;
        }
        TextView textView5 = this.tvSignType;
        if (textView5 != null) {
            textView5.setTextColor((int) 4294255170L);
        }
        TextView textView6 = this.tvSignType;
        if (textView6 != null) {
            textView6.setText(getContext().getString(C4188R.string.call_lora_sign_weak));
        }
    }

    private final void build() {
        View inflate = getLayoutInflater().inflate(C4188R.layout.dialog_lora_signal, (ViewGroup) null);
        this.tvSignType = (TextView) inflate.findViewById(C4188R.id.tvSignType);
        this.llNoSignalLayout = inflate.findViewById(C4188R.id.llNoSignalLayout);
        this.llSignalLayout = inflate.findViewById(C4188R.id.llSignalLayout);
        inflate.findViewById(C4188R.id.cancel).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.LoRaSignalDialog$build$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoRaSignalDialog.this.dismiss();
            }
        });
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
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Pdlog.m3273d(this.TAG, "onAttachedToWindow ");
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Pdlog.m3273d(this.TAG, "onDetachedFromWindow ");
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
