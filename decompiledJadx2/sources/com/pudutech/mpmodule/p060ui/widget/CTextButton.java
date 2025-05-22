package com.pudutech.mpmodule.p060ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import com.pudutech.mpmodule.C5441R;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class CTextButton extends AppCompatTextView {
    private boolean changeEnabled;
    private boolean isAlpha;
    private final float mOpacityDisabled;
    private final float mOpacityNormal;
    private final float mOpacityPressed;
    private View.OnTouchListener onTouchChangeOpacityListener;

    public CTextButton(Context context) {
        this(context, null);
    }

    public CTextButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CTextButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.mOpacityNormal = 1.0f;
        this.mOpacityPressed = 0.3f;
        this.mOpacityDisabled = 0.3f;
        this.isAlpha = true;
        this.onTouchChangeOpacityListener = new View.OnTouchListener() { // from class: com.pudutech.mpmodule.ui.widget.CTextButton.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!CTextButton.this.isEnabled()) {
                    return false;
                }
                int action = motionEvent.getAction();
                if (action == 0) {
                    CTextButton.this.setCustomAlpha(0.3f);
                    return false;
                }
                if (action != 1 && action != 3) {
                    return false;
                }
                CTextButton.this.setCustomAlpha(1.0f);
                return false;
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C5441R.styleable.CTextButton, i, 0);
        if (obtainStyledAttributes != null) {
            for (int i2 = 0; i2 < obtainStyledAttributes.getIndexCount(); i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == C5441R.styleable.CTextButton_ctbtn_initToAlpha) {
                    this.isAlpha = obtainStyledAttributes.getBoolean(index, true);
                }
            }
            obtainStyledAttributes.recycle();
        }
        init();
    }

    private void init() {
        setClickable(true);
        setOnTouchListener(this.onTouchChangeOpacityListener);
        if (isEnabled() || !this.isAlpha) {
            return;
        }
        setCustomAlpha(0.3f);
    }

    @Override // android.widget.TextView, android.view.View
    public void setEnabled(boolean z) {
        if (!this.changeEnabled) {
            if (z) {
                setCustomAlpha(1.0f);
            } else {
                setCustomAlpha(0.3f);
            }
        }
        super.setEnabled(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCustomAlpha(float f) {
        setAlpha(f);
    }

    public void setChangeEnabled(boolean z) {
        this.changeEnabled = z;
    }
}
