package com.pudutech.mpmodule.p060ui;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import androidx.appcompat.app.AlertDialog;
import com.pudutech.mpmodule.C5441R;
import com.pudutech.mpmodule.NavigationBarUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class NotiDialog extends AlertDialog {
    private static final String TAG = "NotiDialog";
    private ClickCallBack mCallBack;
    private FrameLayout mFlRootView;

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public interface ClickCallBack {
        void onConfirm();

        void onDeny();
    }

    public void setContent() {
    }

    public void setTitle() {
    }

    public void addClickCallBack(ClickCallBack clickCallBack) {
        this.mCallBack = clickCallBack;
    }

    public NotiDialog(Context context) {
        super(context);
        requestWindowFeature(1);
        super.setContentView(C5441R.layout.module_add_music_success_tip_layout);
        Window window = getWindow();
        if (window != null) {
            window.setLayout(-1, -1);
            window.setBackgroundDrawableResource(C5441R.color.transparent_background);
        }
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setContentView(int i) {
        setContentView(View.inflate(getContext(), i, null));
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setContentView(View view) {
        this.mFlRootView = (FrameLayout) findViewById(C5441R.id.dialog_root_layout);
        if (this.mFlRootView == null || view == null) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        layoutParams.width = dp2px(600.0f);
        layoutParams.height = dp2px(400.0f);
        this.mFlRootView.addView(view, layoutParams);
        Button button = (Button) this.mFlRootView.findViewById(C5441R.id.deny_btn);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mpmodule.ui.NotiDialog.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (NotiDialog.this.mCallBack != null) {
                        NotiDialog.this.mCallBack.onDeny();
                    }
                }
            });
        }
        Button button2 = (Button) this.mFlRootView.findViewById(C5441R.id.confirm_btn);
        if (button2 != null) {
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mpmodule.ui.NotiDialog.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (NotiDialog.this.mCallBack != null) {
                        NotiDialog.this.mCallBack.onConfirm();
                    }
                }
            });
        }
        Button button3 = (Button) this.mFlRootView.findViewById(C5441R.id.module_import_success_btn);
        if (button3 != null) {
            button3.getPaint().setFakeBoldText(true);
            button3.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mpmodule.ui.NotiDialog.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NotiDialog.this.dismiss();
                }
            });
        }
        Button button4 = (Button) this.mFlRootView.findViewById(C5441R.id.module_load_file_over_time_btn);
        if (button4 != null) {
            button4.getPaint().setFakeBoldText(true);
            button4.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mpmodule.ui.NotiDialog.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (NotiDialog.this.mCallBack != null) {
                        NotiDialog.this.mCallBack.onConfirm();
                    }
                }
            });
        }
    }

    public int dp2px(float f) {
        return (int) ((f * getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    @Override // android.app.Dialog
    public void show() {
        NavigationBarUtil.focusNotAle(getWindow());
        super.show();
        NavigationBarUtil.hideNavigationBarForDialog(getWindow());
        NavigationBarUtil.clearFocusNotAle(getWindow());
    }
}
