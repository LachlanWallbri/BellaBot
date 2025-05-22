package com.pudutech.importmusic;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes5.dex */
public class NotiDialog extends AlertDialog {
    private static final String TAG = "NotiDialog";
    private FrameLayout mFlRootView;
    private DialogType mType;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public enum DialogType {
        NOTIPROGRESS,
        NOTISUCCESS,
        NOTICRASH,
        NOTIOUTOFLEGALSIZE
    }

    public void setContent(int i) {
    }

    public NotiDialog(Context context, DialogType dialogType) {
        super(context);
        requestWindowFeature(1);
        this.mType = dialogType;
        super.setContentView(C4619R.layout.import_music_add_music_success_tip_layout);
        Window window = getWindow();
        if (window != null) {
            window.setLayout(-1, -1);
            window.setBackgroundDrawableResource(C4619R.color.transparent_background);
        }
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setContentView(int i) {
        setContentView(View.inflate(getContext(), i, null));
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setContentView(View view) {
        Button button;
        this.mFlRootView = (FrameLayout) findViewById(C4619R.id.dialog_root_layout);
        if (this.mFlRootView == null || view == null) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        layoutParams.width = dp2px(600.0f);
        layoutParams.height = dp2px(400.0f);
        this.mFlRootView.addView(view, layoutParams);
        TextView textView = (TextView) this.mFlRootView.findViewById(C4619R.id.dialog_title);
        if (textView != null) {
            textView.getPaint().setFakeBoldText(true);
        }
        int i = C46184.$SwitchMap$com$pudutech$importmusic$NotiDialog$DialogType[this.mType.ordinal()];
        if (i == 1) {
            Button button2 = (Button) this.mFlRootView.findViewById(C4619R.id.import_success_btn);
            if (button2 != null) {
                button2.getPaint().setFakeBoldText(true);
                button2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.importmusic.NotiDialog.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        NotiDialog.this.dismiss();
                    }
                });
                return;
            }
            return;
        }
        if (i != 2) {
            if (i == 3 && (button = (Button) this.mFlRootView.findViewById(C4619R.id.outof_size_confirm_btn)) != null) {
                button.getPaint().setFakeBoldText(true);
                button.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.importmusic.NotiDialog.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        NotiDialog.this.dismiss();
                    }
                });
                return;
            }
            return;
        }
        Button button3 = (Button) this.mFlRootView.findViewById(C4619R.id.crash_confirm_btn);
        if (button3 != null) {
            button3.getPaint().setFakeBoldText(true);
            button3.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.importmusic.NotiDialog.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NotiDialog.this.dismiss();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* renamed from: com.pudutech.importmusic.NotiDialog$4 */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class C46184 {
        static final /* synthetic */ int[] $SwitchMap$com$pudutech$importmusic$NotiDialog$DialogType = new int[DialogType.values().length];

        static {
            try {
                $SwitchMap$com$pudutech$importmusic$NotiDialog$DialogType[DialogType.NOTISUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$pudutech$importmusic$NotiDialog$DialogType[DialogType.NOTICRASH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$pudutech$importmusic$NotiDialog$DialogType[DialogType.NOTIOUTOFLEGALSIZE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public DialogType getType() {
        return this.mType;
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
