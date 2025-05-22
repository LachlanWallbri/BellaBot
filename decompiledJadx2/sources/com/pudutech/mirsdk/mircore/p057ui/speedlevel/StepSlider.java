package com.pudutech.mirsdk.mircore.p057ui.speedlevel;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.view.GestureDetectorCompat;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.mircore.C5224R;
import com.pudutech.mirsdk.mircore.MirCoreImpl;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes6.dex */
public class StepSlider extends LinearLayoutCompat {
    private static final String TAG = "StepSlider";
    private int curStep;
    private GestureDetectorCompat gestureDetectorCompat;
    private GestureDetector.SimpleOnGestureListener gestureListener;
    private TextView[] hinters;
    private OnStepClickListener onStepClickListener;
    private SectionSeekBar sectionSeekBar;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes6.dex */
    public interface OnStepClickListener {
        void onStepClicked(int i);
    }

    public StepSlider(Context context) {
        this(context, null);
    }

    public StepSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StepSlider(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.curStep = -1;
        this.gestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.pudutech.mirsdk.mircore.ui.speedlevel.StepSlider.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                float x = motionEvent.getX();
                motionEvent.getY();
                int width = StepSlider.this.getWidth() / StepSlider.this.hinters.length;
                int i2 = 0;
                int i3 = 0;
                while (i2 < StepSlider.this.hinters.length) {
                    if (x >= i3 && x <= i3 + width) {
                        StepSlider.this.playSoundEffect(0);
                        Log.d(StepSlider.TAG, "step " + i2);
                        if (StepSlider.this.onStepClickListener != null) {
                            StepSlider.this.onStepClickListener.onStepClicked(i2);
                        }
                        StepSlider.this.setCurStep(i2);
                        return true;
                    }
                    i2++;
                    i3 += width;
                }
                return true;
            }
        };
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        setOrientation(1);
        inflate(context, C5224R.layout.layout_step_slider, this);
        this.sectionSeekBar = (SectionSeekBar) findViewById(C5224R.id.section_seek_bar);
        ViewGroup viewGroup = (ViewGroup) findViewById(C5224R.id.hints);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C5224R.styleable.StepSlider, i, 0);
        CharSequence[] textArray = obtainStyledAttributes.getTextArray(C5224R.styleable.StepSlider_android_entries);
        if (MirCoreImpl.INSTANCE.getMachineType() != MachineModel.Ninetales) {
            textArray = (CharSequence[]) Arrays.copyOfRange(textArray, 4, textArray.length);
        }
        if (textArray != null) {
            this.hinters = new TextView[textArray.length];
            for (int i2 = 0; i2 < textArray.length; i2++) {
                this.hinters[i2] = makeText(i2);
                this.hinters[i2].setTextColor(getResources().getColor(C5224R.color.const_text));
                viewGroup.addView(this.hinters[i2]);
                this.hinters[i2].setText(textArray[i2]);
            }
        }
        this.sectionSeekBar.setSectionSize(textArray.length);
        obtainStyledAttributes.recycle();
        this.gestureDetectorCompat = new GestureDetectorCompat(context, this.gestureListener);
    }

    private TextView makeText(int i) {
        TextView textView = new TextView(getContext());
        textView.setTextSize(2, 20.0f);
        textView.setTextColor(-1);
        LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(-2, -2);
        if (i > 0) {
            textView.setGravity(5);
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
        }
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        Log.d(TAG, "onInterceptTouchEvent");
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Log.d(TAG, "onTouchEvent");
        return this.gestureDetectorCompat.onTouchEvent(motionEvent);
    }

    public void setOnStepClickListener(OnStepClickListener onStepClickListener) {
        this.onStepClickListener = onStepClickListener;
    }

    public void setCurStep(int i) {
        this.curStep = i;
        this.sectionSeekBar.setHighlightIdx(i);
        toggleHidden();
    }

    public int getCurStep() {
        return this.curStep;
    }

    private void toggleHidden() {
        int i = 1;
        while (true) {
            TextView[] textViewArr = this.hinters;
            if (i >= textViewArr.length - 1) {
                return;
            }
            if (i == this.curStep) {
                textViewArr[i].setVisibility(0);
            } else {
                textViewArr[i].setVisibility(4);
            }
            i++;
        }
    }
}
