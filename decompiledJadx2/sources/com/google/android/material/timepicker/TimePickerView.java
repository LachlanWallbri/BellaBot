package com.google.android.material.timepicker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.C1653R;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;
import com.google.android.material.timepicker.ClockHandView;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
class TimePickerView extends ConstraintLayout implements TimePickerControls {
    private final ClockFaceView clockFace;
    private final ClockHandView clockHandView;
    private final Chip hourView;
    private final Chip minuteView;
    private OnPeriodChangeListener onPeriodChangeListener;
    private OnSelectionChange onSelectionChangeListener;
    private final View.OnClickListener selectionListener;
    private final MaterialButtonToggleGroup toggle;

    /* loaded from: classes2.dex */
    interface OnDoubleTapListener {
        void onDoubleTap();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes.dex */
    interface OnPeriodChangeListener {
        void onPeriodChange(int i);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes.dex */
    interface OnSelectionChange {
        void onSelectionChanged(int i);
    }

    public TimePickerView(Context context) {
        this(context, null);
    }

    public TimePickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimePickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.selectionListener = new View.OnClickListener() { // from class: com.google.android.material.timepicker.TimePickerView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TimePickerView.this.onSelectionChangeListener != null) {
                    TimePickerView.this.onSelectionChangeListener.onSelectionChanged(((Integer) view.getTag(C1653R.id.selection_type)).intValue());
                }
            }
        };
        LayoutInflater.from(context).inflate(C1653R.layout.material_timepicker, this);
        this.clockFace = (ClockFaceView) findViewById(C1653R.id.material_clock_face);
        MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) findViewById(C1653R.id.material_clock_period_toggle);
        this.toggle = materialButtonToggleGroup;
        materialButtonToggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() { // from class: com.google.android.material.timepicker.TimePickerView.2
            @Override // com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener
            public void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup2, int i2, boolean z) {
                int i3 = i2 == C1653R.id.material_clock_period_pm_button ? 1 : 0;
                if (TimePickerView.this.onPeriodChangeListener == null || !z) {
                    return;
                }
                TimePickerView.this.onPeriodChangeListener.onPeriodChange(i3);
            }
        });
        this.minuteView = (Chip) findViewById(C1653R.id.material_minute_tv);
        this.hourView = (Chip) findViewById(C1653R.id.material_hour_tv);
        this.clockHandView = (ClockHandView) findViewById(C1653R.id.material_clock_hand);
        setUpDisplay();
    }

    public void setMinuteHourDelegate(AccessibilityDelegateCompat accessibilityDelegateCompat) {
        ViewCompat.setAccessibilityDelegate(this.hourView, accessibilityDelegateCompat);
    }

    public void setHourClickDelegate(AccessibilityDelegateCompat accessibilityDelegateCompat) {
        ViewCompat.setAccessibilityDelegate(this.minuteView, accessibilityDelegateCompat);
    }

    private void setUpDisplay() {
        this.minuteView.setTag(C1653R.id.selection_type, 12);
        this.hourView.setTag(C1653R.id.selection_type, 10);
        this.minuteView.setOnClickListener(this.selectionListener);
        this.hourView.setOnClickListener(this.selectionListener);
    }

    /* renamed from: com.google.android.material.timepicker.TimePickerView$3 */
    /* loaded from: classes2.dex */
    class C19023 extends GestureDetector.SimpleOnGestureListener {
        C19023() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            boolean onDoubleTap = super.onDoubleTap(motionEvent);
            if (TimePickerView.access$200(TimePickerView.this) != null) {
                TimePickerView.access$200(TimePickerView.this).onDoubleTap();
            }
            return onDoubleTap;
        }
    }

    @Override // com.google.android.material.timepicker.TimePickerControls
    public void setValues(String[] strArr, int i) {
        this.clockFace.setValues(strArr, i);
    }

    @Override // com.google.android.material.timepicker.TimePickerControls
    public void setHandRotation(float f) {
        this.clockHandView.setHandRotation(f);
    }

    public void setHandRotation(float f, boolean z) {
        this.clockHandView.setHandRotation(f, z);
    }

    /* renamed from: com.google.android.material.timepicker.TimePickerView$4 */
    /* loaded from: classes2.dex */
    class ViewOnTouchListenerC19034 implements View.OnTouchListener {
        final /* synthetic */ GestureDetector val$gestureDetector;

        ViewOnTouchListenerC19034(GestureDetector gestureDetector) {
            this.val$gestureDetector = gestureDetector;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (((Checkable) view).isChecked()) {
                return this.val$gestureDetector.onTouchEvent(motionEvent);
            }
            return false;
        }
    }

    public void setAnimateOnTouchUp(boolean z) {
        this.clockHandView.setAnimateOnTouchUp(z);
    }

    @Override // com.google.android.material.timepicker.TimePickerControls
    public void updateTime(int i, int i2, int i3) {
        this.toggle.check(i == 1 ? C1653R.id.material_clock_period_pm_button : C1653R.id.material_clock_period_am_button);
        Locale locale = getResources().getConfiguration().locale;
        String format = String.format(locale, "%02d", Integer.valueOf(i3));
        String format2 = String.format(locale, "%02d", Integer.valueOf(i2));
        this.minuteView.setText(format);
        this.hourView.setText(format2);
    }

    @Override // com.google.android.material.timepicker.TimePickerControls
    public void setActiveSelection(int i) {
        this.minuteView.setChecked(i == 12);
        this.hourView.setChecked(i == 10);
    }

    public void addOnRotateListener(ClockHandView.OnRotateListener onRotateListener) {
        this.clockHandView.addOnRotateListener(onRotateListener);
    }

    public void setOnActionUpListener(ClockHandView.OnActionUpListener onActionUpListener) {
        this.clockHandView.setOnActionUpListener(onActionUpListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnPeriodChangeListener(OnPeriodChangeListener onPeriodChangeListener) {
        this.onPeriodChangeListener = onPeriodChangeListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnSelectionChangeListener(OnSelectionChange onSelectionChange) {
        this.onSelectionChangeListener = onSelectionChange;
    }

    public void showToggle() {
        this.toggle.setVisibility(0);
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (view == this && i == 0) {
            updateToggleConstraints();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateToggleConstraints();
    }

    private void updateToggleConstraints() {
        if (this.toggle.getVisibility() == 0) {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(this);
            constraintSet.clear(C1653R.id.material_clock_display, ViewCompat.getLayoutDirection(this) == 0 ? 2 : 1);
            constraintSet.applyTo(this);
        }
    }
}
