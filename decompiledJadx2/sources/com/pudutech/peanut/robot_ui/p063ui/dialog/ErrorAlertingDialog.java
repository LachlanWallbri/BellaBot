package com.pudutech.peanut.robot_ui.p063ui.dialog;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.util.NavigationBarUtil;

/* loaded from: classes5.dex */
public class ErrorAlertingDialog extends Dialog {
    private Context context;
    private Button mBtnSure;
    private TextView mContentTV;

    public ErrorAlertingDialog(Context context) {
        super(context);
        init(context);
    }

    public ErrorAlertingDialog(Context context, int i) {
        super(context, i);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        build();
    }

    private void build() {
        View inflate = getLayoutInflater().inflate(C5508R.layout.dialog_error_alerting, (ViewGroup) null);
        this.mContentTV = (TextView) inflate.findViewById(C5508R.id.show_tips);
        this.mBtnSure = (Button) inflate.findViewById(C5508R.id.btn_sure);
        requestWindowFeature(1);
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(3846);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setGravity(17);
        window.setAttributes(attributes);
        this.mBtnSure.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.ErrorAlertingDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ErrorAlertingDialog.this.dismiss();
            }
        });
        setContentView(inflate);
        window.setLayout(-1, -1);
        window.setBackgroundDrawableResource(R.color.transparent);
        setCanceledOnTouchOutside(true);
    }

    public void setContent(String str) {
        this.mContentTV.setText(str);
    }

    @Override // android.app.Dialog
    public void show() {
        NavigationBarUtil.focusNotAle(getWindow());
        super.show();
        NavigationBarUtil.hideNavigationBar(getWindow());
        NavigationBarUtil.clearFocusNotAle(getWindow());
    }
}
