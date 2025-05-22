package com.pudutech.peanut.robot_ui.widget.tablayout.utils;

import android.util.DisplayMetrics;
import android.widget.RelativeLayout;
import com.pudutech.peanut.robot_ui.widget.tablayout.widget.MsgView;

/* loaded from: classes5.dex */
public class UnreadMsgUtils {
    public static void show(MsgView msgView, int i) {
        if (msgView == null) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) msgView.getLayoutParams();
        DisplayMetrics displayMetrics = msgView.getResources().getDisplayMetrics();
        msgView.setVisibility(0);
        if (i <= 0) {
            msgView.setStrokeWidth(0);
            msgView.setText("");
            layoutParams.width = (int) (displayMetrics.density * 5.0f);
            layoutParams.height = (int) (displayMetrics.density * 5.0f);
            msgView.setLayoutParams(layoutParams);
            return;
        }
        layoutParams.height = (int) (displayMetrics.density * 18.0f);
        if (i > 0 && i < 10) {
            layoutParams.width = (int) (displayMetrics.density * 18.0f);
            msgView.setText(i + "");
        } else if (i > 9 && i < 100) {
            layoutParams.width = -2;
            msgView.setPadding((int) (displayMetrics.density * 6.0f), 0, (int) (displayMetrics.density * 6.0f), 0);
            msgView.setText(i + "");
        } else {
            layoutParams.width = -2;
            msgView.setPadding((int) (displayMetrics.density * 6.0f), 0, (int) (displayMetrics.density * 6.0f), 0);
            msgView.setText("99+");
        }
        msgView.setLayoutParams(layoutParams);
    }

    public static void setSize(MsgView msgView, int i) {
        if (msgView == null) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) msgView.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i;
        msgView.setLayoutParams(layoutParams);
    }
}
