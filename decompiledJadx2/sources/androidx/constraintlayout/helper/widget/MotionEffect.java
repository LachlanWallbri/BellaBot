package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.motion.widget.Debug;
import androidx.constraintlayout.motion.widget.KeyAttributes;
import androidx.constraintlayout.motion.widget.KeyPosition;
import androidx.constraintlayout.motion.widget.MotionController;
import androidx.constraintlayout.motion.widget.MotionHelper;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.C0205R;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.HashMap;

/* loaded from: classes.dex */
public class MotionEffect extends MotionHelper {
    public static final int AUTO = -1;
    public static final int EAST = 2;
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final String TAG = "FadeMove";
    private static final int UNSET = -1;
    public static final int WEST = 3;
    private int fadeMove;
    private float motionEffectAlpha;
    private int motionEffectEnd;
    private int motionEffectStart;
    private boolean motionEffectStrictMove;
    private int motionEffectTranslationX;
    private int motionEffectTranslationY;
    private int viewTransitionId;

    public boolean isDecorator() {
        return true;
    }

    public MotionEffect(Context context) {
        super(context);
        this.motionEffectAlpha = 0.1f;
        this.motionEffectStart = 49;
        this.motionEffectEnd = 50;
        this.motionEffectTranslationX = 0;
        this.motionEffectTranslationY = 0;
        this.motionEffectStrictMove = true;
        this.viewTransitionId = -1;
        this.fadeMove = -1;
    }

    public MotionEffect(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.motionEffectAlpha = 0.1f;
        this.motionEffectStart = 49;
        this.motionEffectEnd = 50;
        this.motionEffectTranslationX = 0;
        this.motionEffectTranslationY = 0;
        this.motionEffectStrictMove = true;
        this.viewTransitionId = -1;
        this.fadeMove = -1;
        init(context, attrs);
    }

    public MotionEffect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.motionEffectAlpha = 0.1f;
        this.motionEffectStart = 49;
        this.motionEffectEnd = 50;
        this.motionEffectTranslationX = 0;
        this.motionEffectTranslationY = 0;
        this.motionEffectStrictMove = true;
        this.viewTransitionId = -1;
        this.fadeMove = -1;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attrs, C0205R.styleable.MotionEffect);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == C0205R.styleable.MotionEffect_motionEffect_start) {
                    this.motionEffectStart = obtainStyledAttributes.getInt(index, this.motionEffectStart);
                    this.motionEffectStart = Math.max(Math.min(this.motionEffectStart, 99), 0);
                } else if (index == C0205R.styleable.MotionEffect_motionEffect_end) {
                    this.motionEffectEnd = obtainStyledAttributes.getInt(index, this.motionEffectEnd);
                    this.motionEffectEnd = Math.max(Math.min(this.motionEffectEnd, 99), 0);
                } else if (index == C0205R.styleable.MotionEffect_motionEffect_translationX) {
                    this.motionEffectTranslationX = obtainStyledAttributes.getDimensionPixelOffset(index, this.motionEffectTranslationX);
                } else if (index == C0205R.styleable.MotionEffect_motionEffect_translationY) {
                    this.motionEffectTranslationY = obtainStyledAttributes.getDimensionPixelOffset(index, this.motionEffectTranslationY);
                } else if (index == C0205R.styleable.MotionEffect_motionEffect_alpha) {
                    this.motionEffectAlpha = obtainStyledAttributes.getFloat(index, this.motionEffectAlpha);
                } else if (index == C0205R.styleable.MotionEffect_motionEffect_move) {
                    this.fadeMove = obtainStyledAttributes.getInt(index, this.fadeMove);
                } else if (index == C0205R.styleable.MotionEffect_motionEffect_strict) {
                    this.motionEffectStrictMove = obtainStyledAttributes.getBoolean(index, this.motionEffectStrictMove);
                } else if (index == C0205R.styleable.MotionEffect_motionEffect_viewTransition) {
                    this.viewTransitionId = obtainStyledAttributes.getResourceId(index, this.viewTransitionId);
                }
            }
            int i2 = this.motionEffectStart;
            int i3 = this.motionEffectEnd;
            if (i2 == i3) {
                if (i2 > 0) {
                    this.motionEffectStart = i2 - 1;
                } else {
                    this.motionEffectEnd = i3 + 1;
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x01b5, code lost:
    
        if (r15 == 0.0f) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0181, code lost:
    
        if (r14 == 0.0f) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0195, code lost:
    
        if (r14 == 0.0f) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01a5, code lost:
    
        if (r15 == 0.0f) goto L57;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onPreSetup(MotionLayout motionLayout, HashMap<View, MotionController> controllerMap) {
        KeyAttributes keyAttributes;
        KeyAttributes keyAttributes2;
        KeyAttributes keyAttributes3;
        boolean z;
        HashMap<View, MotionController> hashMap = controllerMap;
        View[] views = getViews((ConstraintLayout) getParent());
        if (views == null) {
            Log.v(TAG, String.valueOf(Debug.getLoc()).concat(" views = null"));
            return;
        }
        KeyAttributes keyAttributes4 = new KeyAttributes();
        KeyAttributes keyAttributes5 = new KeyAttributes();
        keyAttributes4.setValue("alpha", Float.valueOf(this.motionEffectAlpha));
        keyAttributes5.setValue("alpha", Float.valueOf(this.motionEffectAlpha));
        keyAttributes4.setFramePosition(this.motionEffectStart);
        keyAttributes5.setFramePosition(this.motionEffectEnd);
        KeyPosition keyPosition = new KeyPosition();
        keyPosition.setFramePosition(this.motionEffectStart);
        keyPosition.setType(0);
        keyPosition.setValue(TypedValues.Position.S_PERCENT_X, 0);
        keyPosition.setValue(TypedValues.Position.S_PERCENT_Y, 0);
        KeyPosition keyPosition2 = new KeyPosition();
        keyPosition2.setFramePosition(this.motionEffectEnd);
        keyPosition2.setType(0);
        keyPosition2.setValue(TypedValues.Position.S_PERCENT_X, 1);
        keyPosition2.setValue(TypedValues.Position.S_PERCENT_Y, 1);
        KeyAttributes keyAttributes6 = null;
        if (this.motionEffectTranslationX > 0) {
            keyAttributes = new KeyAttributes();
            keyAttributes2 = new KeyAttributes();
            keyAttributes.setValue("translationX", Integer.valueOf(this.motionEffectTranslationX));
            keyAttributes.setFramePosition(this.motionEffectEnd);
            keyAttributes2.setValue("translationX", 0);
            keyAttributes2.setFramePosition(this.motionEffectEnd - 1);
        } else {
            keyAttributes = null;
            keyAttributes2 = null;
        }
        if (this.motionEffectTranslationY > 0) {
            keyAttributes6 = new KeyAttributes();
            keyAttributes3 = new KeyAttributes();
            keyAttributes6.setValue("translationY", Integer.valueOf(this.motionEffectTranslationY));
            keyAttributes6.setFramePosition(this.motionEffectEnd);
            keyAttributes3.setValue("translationY", 0);
            keyAttributes3.setFramePosition(this.motionEffectEnd - 1);
        } else {
            keyAttributes3 = null;
        }
        int i = this.fadeMove;
        if (i == -1) {
            int[] iArr = new int[4];
            for (View view : views) {
                MotionController motionController = hashMap.get(view);
                if (motionController != null) {
                    float finalX = motionController.getFinalX() - motionController.getStartX();
                    float finalY = motionController.getFinalY() - motionController.getStartY();
                    if (finalY < 0.0f) {
                        iArr[1] = iArr[1] + 1;
                    }
                    if (finalY > 0.0f) {
                        iArr[0] = iArr[0] + 1;
                    }
                    if (finalX > 0.0f) {
                        iArr[3] = iArr[3] + 1;
                    }
                    if (finalX < 0.0f) {
                        iArr[2] = iArr[2] + 1;
                    }
                }
            }
            int i2 = 0;
            int i3 = iArr[0];
            for (int i4 = 1; i4 < 4; i4++) {
                if (i3 < iArr[i4]) {
                    i3 = iArr[i4];
                    i2 = i4;
                }
            }
            i = i2;
        }
        int i5 = 0;
        while (i5 < views.length) {
            MotionController motionController2 = hashMap.get(views[i5]);
            if (motionController2 != null) {
                float finalX2 = motionController2.getFinalX() - motionController2.getStartX();
                float finalY2 = motionController2.getFinalY() - motionController2.getStartY();
                if (i == 0) {
                    if (finalY2 > 0.0f) {
                        if (this.motionEffectStrictMove) {
                        }
                        z = false;
                    }
                    z = true;
                } else if (i == 1) {
                    if (finalY2 < 0.0f) {
                        if (this.motionEffectStrictMove) {
                        }
                        z = false;
                    }
                    z = true;
                } else if (i == 2) {
                    if (finalX2 < 0.0f) {
                        if (this.motionEffectStrictMove) {
                        }
                        z = false;
                    }
                    z = true;
                } else {
                    if (i == 3) {
                        if (finalX2 > 0.0f) {
                            if (this.motionEffectStrictMove) {
                            }
                            z = false;
                        }
                    }
                    z = true;
                }
                if (z) {
                    int i6 = this.viewTransitionId;
                    if (i6 == -1) {
                        motionController2.addKey(keyAttributes4);
                        motionController2.addKey(keyAttributes5);
                        motionController2.addKey(keyPosition);
                        motionController2.addKey(keyPosition2);
                        if (this.motionEffectTranslationX > 0) {
                            motionController2.addKey(keyAttributes);
                            motionController2.addKey(keyAttributes2);
                        }
                        if (this.motionEffectTranslationY > 0) {
                            motionController2.addKey(keyAttributes6);
                            motionController2.addKey(keyAttributes3);
                        }
                    } else {
                        motionLayout.applyViewTransition(i6, motionController2);
                    }
                    i5++;
                    hashMap = controllerMap;
                }
            }
            i5++;
            hashMap = controllerMap;
        }
    }
}
