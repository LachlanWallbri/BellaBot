package com.pudutech.bumblebee.robot_ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CImageButton.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\u0010\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0015H\u0016J\u0010\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\tH\u0016R\u000e\u0010\u000b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000f8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/widget/CImageButton;", "Landroidx/appcompat/widget/AppCompatImageView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "mOpacityDisabled", "mOpacityNormal", "mOpacityPressed", "onTouchChangeOpacityListener", "Landroid/view/View$OnTouchListener;", "init", "", "onAttachedToWindow", "onWindowFocusChanged", "hasWindowFocus", "", "setEnabled", "enabled", "setImageResource", "resId", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CImageButton extends AppCompatImageView {
    private HashMap _$_findViewCache;
    private final int mOpacityDisabled;
    private final int mOpacityNormal;
    private final int mOpacityPressed;
    private final View.OnTouchListener onTouchChangeOpacityListener;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mOpacityNormal = 255;
        this.mOpacityPressed = (int) 153.0d;
        this.mOpacityDisabled = (int) 76.5d;
        this.onTouchChangeOpacityListener = new View.OnTouchListener() { // from class: com.pudutech.bumblebee.robot_ui.widget.CImageButton$onTouchChangeOpacityListener$1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent event) {
                int i2;
                int i3;
                if (!CImageButton.this.isEnabled()) {
                    return false;
                }
                Intrinsics.checkExpressionValueIsNotNull(event, "event");
                int action = event.getAction();
                if (action == 0) {
                    CImageButton cImageButton = CImageButton.this;
                    i2 = cImageButton.mOpacityPressed;
                    cImageButton.setAlpha(i2);
                    return false;
                }
                if (action != 1 && action != 3) {
                    return false;
                }
                CImageButton cImageButton2 = CImageButton.this;
                i3 = cImageButton2.mOpacityNormal;
                cImageButton2.setAlpha(i3);
                return false;
            }
        };
        init();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CImageButton(Context context) {
        this(context, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    private final void init() {
        setClickable(true);
        setOnTouchListener(this.onTouchChangeOpacityListener);
        if (isEnabled()) {
            return;
        }
        setAlpha(this.mOpacityDisabled);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        if (isEnabled()) {
            return;
        }
        setAlpha(this.mOpacityDisabled);
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        if (enabled) {
            setAlpha(this.mOpacityNormal);
        } else {
            setAlpha(this.mOpacityDisabled);
        }
        super.setEnabled(enabled);
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            setEnabled(isEnabled());
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setEnabled(isEnabled());
    }
}
