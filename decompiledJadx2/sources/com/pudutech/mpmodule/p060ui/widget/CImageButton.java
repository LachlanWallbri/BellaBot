package com.pudutech.mpmodule.p060ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class CImageButton extends AppCompatImageView {
    private final int mOpacityDisabled;
    private final int mOpacityNormal;
    private final int mOpacityPressed;
    private View.OnTouchListener onTouchChangeOpacityListener;

    public CImageButton(Context context) {
        this(context, null);
    }

    public CImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mOpacityNormal = 255;
        this.mOpacityPressed = 153;
        this.mOpacityDisabled = 76;
        this.onTouchChangeOpacityListener = new View.OnTouchListener() { // from class: com.pudutech.mpmodule.ui.widget.CImageButton.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!CImageButton.this.isEnabled()) {
                    return false;
                }
                int action = motionEvent.getAction();
                if (action == 0) {
                    CImageButton.this.setAlpha(153);
                    return false;
                }
                if (action != 1 && action != 3) {
                    return false;
                }
                CImageButton.this.setAlpha(255);
                return false;
            }
        };
        init();
    }

    private void init() {
        setClickable(true);
        setOnTouchListener(this.onTouchChangeOpacityListener);
        if (isEnabled()) {
            return;
        }
        setAlpha(76);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
        if (isEnabled()) {
            return;
        }
        setAlpha(76);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        if (z) {
            setAlpha(255);
        } else {
            setAlpha(76);
        }
        super.setEnabled(z);
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            setEnabled(isEnabled());
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setEnabled(isEnabled());
    }
}
