package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.util.NavigationBarUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: TransparentLoadDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\b\u0010\u0018\u001a\u00020\u0016H\u0016J\b\u0010\u0019\u001a\u00020\u0016H\u0016J\b\u0010\u001a\u001a\u00020\u0016H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R$\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR&\u0010\u0010\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00068\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/TransparentLoadDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "TAG", "", ES6Iterator.VALUE_PROPERTY, AIUIConstant.KEY_CONTENT, "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "srcId", "getSrcId", "()I", "setSrcId", "(I)V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "", "initView", "onAttachedToWindow", "onDetachedFromWindow", "show", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class TransparentLoadDialog extends Dialog {
    private final String TAG;
    private String content;
    private int srcId;

    public final String getContent() {
        return this.content;
    }

    public final void setContent(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.content = value;
        TextView textView = (TextView) findViewById(C4188R.id.loading_tip_tv);
        if (textView != null) {
            textView.setText(value);
        }
    }

    public final int getSrcId() {
        return this.srcId;
    }

    public final void setSrcId(int i) {
        this.srcId = i;
        ImageView imageView = (ImageView) findViewById(C4188R.id.loading_iv);
        if (imageView != null) {
            imageView.setImageResource(this.srcId);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransparentLoadDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "TransparentLoadDialog";
        this.content = "";
        this.srcId = C4188R.drawable.ic_small_white_loading;
        build();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransparentLoadDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "TransparentLoadDialog";
        this.content = "";
        this.srcId = C4188R.drawable.ic_small_white_loading;
        build();
    }

    private final void build() {
        View inflate = getLayoutInflater().inflate(C4188R.layout.dialog_transparent_load, (ViewGroup) null);
        requestWindowFeature(1);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawableResource(R.color.transparent);
            window.setGravity(17);
            window.setAttributes(attributes);
            setContentView(inflate);
        }
        setCancelable(false);
        initView();
    }

    private final void initView() {
        ImageView imageView = (ImageView) findViewById(C4188R.id.loading_iv);
        if (imageView != null) {
            imageView.setImageResource(this.srcId);
        }
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Pdlog.m3273d(this.TAG, "onAttachedToWindow ");
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(1000L);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        ((ImageView) findViewById(C4188R.id.loading_iv)).startAnimation(rotateAnimation);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Pdlog.m3273d(this.TAG, "onDetachedFromWindow ");
        ImageView imageView = (ImageView) findViewById(C4188R.id.loading_iv);
        if (imageView != null) {
            imageView.clearAnimation();
        }
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
    }
}
