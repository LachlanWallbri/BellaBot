package com.pudutech.peanut.robot_ui.p063ui.dialog;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.util.NavigationBarUtil;

/* loaded from: classes5.dex */
public class FixErrorDialog extends Dialog {
    private ImageView bigIv;
    private TextView cancelIv;
    private Context context;
    private ImageView smallIv;

    public FixErrorDialog(Context context) {
        super(context);
        init(context);
    }

    public FixErrorDialog(Context context, int i) {
        super(context, i);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        build();
    }

    private void build() {
        View inflate = getLayoutInflater().inflate(C5508R.layout.dialog_fix_error, (ViewGroup) null);
        requestWindowFeature(1);
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(3846);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setGravity(17);
        window.setAttributes(attributes);
        this.bigIv = (ImageView) inflate.findViewById(C5508R.id.big_repair_iv);
        this.smallIv = (ImageView) inflate.findViewById(C5508R.id.small_repair_iv);
        this.cancelIv = (TextView) inflate.findViewById(C5508R.id.cancel_tv);
        setContentView(inflate);
        window.setLayout(-1, -1);
        window.setBackgroundDrawableResource(R.color.transparent);
        setCanceledOnTouchOutside(true);
    }

    public void setOnCancelClickListener(View.OnClickListener onClickListener) {
        TextView textView = this.cancelIv;
        if (textView != null) {
            textView.setOnClickListener(onClickListener);
        }
    }

    @Override // android.app.Dialog
    public void show() {
        NavigationBarUtil.focusNotAle(getWindow());
        super.show();
        NavigationBarUtil.hideNavigationBar(getWindow());
        NavigationBarUtil.clearFocusNotAle(getWindow());
        startAnimation();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimation();
    }

    private void startAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(5000L);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        this.bigIv.startAnimation(rotateAnimation);
        RotateAnimation rotateAnimation2 = new RotateAnimation(360.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation2.setDuration(5000L);
        rotateAnimation2.setRepeatCount(-1);
        rotateAnimation2.setInterpolator(new LinearInterpolator());
        this.smallIv.startAnimation(rotateAnimation2);
    }

    private void stopAnimation() {
        this.bigIv.clearAnimation();
        this.smallIv.clearAnimation();
    }
}
