package com.pudutech.peanut.robot_ui.util;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

/* loaded from: classes5.dex */
public class SoftKeyBoardListener {
    private OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener;
    private View rootView;
    int rootViewVisibleHeight;

    /* loaded from: classes5.dex */
    public interface OnSoftKeyBoardChangeListener {
        void keyBoardHide(int i);

        void keyBoardShow(int i);
    }

    public SoftKeyBoardListener(Activity activity) {
        this.rootView = activity.getWindow().getDecorView();
        this.rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.pudutech.peanut.robot_ui.util.SoftKeyBoardListener.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                Rect rect = new Rect();
                SoftKeyBoardListener.this.rootView.getWindowVisibleDisplayFrame(rect);
                int height = rect.height();
                System.out.println("" + height);
                if (SoftKeyBoardListener.this.rootViewVisibleHeight == 0) {
                    SoftKeyBoardListener.this.rootViewVisibleHeight = height;
                    return;
                }
                if (SoftKeyBoardListener.this.rootViewVisibleHeight == height) {
                    return;
                }
                if (SoftKeyBoardListener.this.rootViewVisibleHeight - height > 200) {
                    if (SoftKeyBoardListener.this.onSoftKeyBoardChangeListener != null) {
                        SoftKeyBoardListener.this.onSoftKeyBoardChangeListener.keyBoardShow(SoftKeyBoardListener.this.rootViewVisibleHeight - height);
                    }
                    SoftKeyBoardListener.this.rootViewVisibleHeight = height;
                } else if (height - SoftKeyBoardListener.this.rootViewVisibleHeight > 200) {
                    if (SoftKeyBoardListener.this.onSoftKeyBoardChangeListener != null) {
                        SoftKeyBoardListener.this.onSoftKeyBoardChangeListener.keyBoardHide(height - SoftKeyBoardListener.this.rootViewVisibleHeight);
                    }
                    SoftKeyBoardListener.this.rootViewVisibleHeight = height;
                }
            }
        });
    }

    private void setOnSoftKeyBoardChangeListener(OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        this.onSoftKeyBoardChangeListener = onSoftKeyBoardChangeListener;
    }

    public static void setListener(Activity activity, OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        new SoftKeyBoardListener(activity).setOnSoftKeyBoardChangeListener(onSoftKeyBoardChangeListener);
    }
}
