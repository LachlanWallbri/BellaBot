package com.nineoldandroids.animation;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.animation.AnimationUtils;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes4.dex */
public class AnimatorInflater {
    private static final int AnimatorSet_ordering = 0;
    private static final int Animator_duration = 1;
    private static final int Animator_interpolator = 0;
    private static final int Animator_repeatCount = 3;
    private static final int Animator_repeatMode = 4;
    private static final int Animator_startOffset = 2;
    private static final int Animator_valueFrom = 5;
    private static final int Animator_valueTo = 6;
    private static final int Animator_valueType = 7;
    private static final int PropertyAnimator_propertyName = 0;
    private static final int TOGETHER = 0;
    private static final int VALUE_TYPE_FLOAT = 0;
    private static final int[] AnimatorSet = {R.attr.ordering};
    private static final int[] PropertyAnimator = {R.attr.propertyName};
    private static final int[] Animator = {R.attr.interpolator, R.attr.duration, R.attr.startOffset, R.attr.repeatCount, R.attr.repeatMode, R.attr.valueFrom, R.attr.valueTo, R.attr.valueType};

    public static Animator loadAnimator(Context context, int i) throws Resources.NotFoundException {
        XmlResourceParser xmlResourceParser = null;
        try {
            try {
                xmlResourceParser = context.getResources().getAnimation(i);
                return createAnimatorFromXml(context, xmlResourceParser);
            } catch (IOException e) {
                Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                notFoundException.initCause(e);
                throw notFoundException;
            } catch (XmlPullParserException e2) {
                Resources.NotFoundException notFoundException2 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                notFoundException2.initCause(e2);
                throw notFoundException2;
            }
        } finally {
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
        }
    }

    private static Animator createAnimatorFromXml(Context context, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return createAnimatorFromXml(context, xmlPullParser, Xml.asAttributeSet(xmlPullParser), null, 0);
    }

    private static Animator createAnimatorFromXml(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, AnimatorSet animatorSet, int i) throws XmlPullParserException, IOException {
        int i2;
        int depth = xmlPullParser.getDepth();
        ArrayList arrayList = null;
        Animator animator = null;
        while (true) {
            int next = xmlPullParser.next();
            if ((next != 3 || xmlPullParser.getDepth() > depth) && next != 1) {
                if (next == 2) {
                    String name = xmlPullParser.getName();
                    if (name.equals("objectAnimator")) {
                        animator = loadObjectAnimator(context, attributeSet);
                    } else if (name.equals("animator")) {
                        animator = loadAnimator(context, attributeSet, null);
                    } else if (name.equals(TmpConstant.PROPERTY_IDENTIFIER_SET)) {
                        animator = new AnimatorSet();
                        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AnimatorSet);
                        TypedValue typedValue = new TypedValue();
                        obtainStyledAttributes.getValue(0, typedValue);
                        createAnimatorFromXml(context, xmlPullParser, attributeSet, (AnimatorSet) animator, typedValue.type == 16 ? typedValue.data : 0);
                        obtainStyledAttributes.recycle();
                    } else {
                        throw new RuntimeException("Unknown animator name: " + xmlPullParser.getName());
                    }
                    if (animatorSet != null) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(animator);
                    }
                }
            }
        }
        if (animatorSet != null && arrayList != null) {
            Animator[] animatorArr = new Animator[arrayList.size()];
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                animatorArr[i2] = (Animator) it.next();
                i2++;
            }
            if (i == 0) {
                animatorSet.playTogether(animatorArr);
            } else {
                animatorSet.playSequentially(animatorArr);
            }
        }
        return animator;
    }

    private static ObjectAnimator loadObjectAnimator(Context context, AttributeSet attributeSet) throws Resources.NotFoundException {
        ObjectAnimator objectAnimator = new ObjectAnimator();
        loadAnimator(context, attributeSet, objectAnimator);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, PropertyAnimator);
        objectAnimator.setPropertyName(obtainStyledAttributes.getString(0));
        obtainStyledAttributes.recycle();
        return objectAnimator;
    }

    private static ValueAnimator loadAnimator(Context context, AttributeSet attributeSet, ValueAnimator valueAnimator) throws Resources.NotFoundException {
        int i;
        int i2;
        int i3;
        int i4;
        float f;
        float f2;
        float f3;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Animator);
        long j = obtainStyledAttributes.getInt(1, 0);
        long j2 = obtainStyledAttributes.getInt(2, 0);
        int i5 = obtainStyledAttributes.getInt(7, 0);
        ValueAnimator valueAnimator2 = valueAnimator == null ? new ValueAnimator() : valueAnimator;
        boolean z = i5 == 0;
        TypedValue peekValue = obtainStyledAttributes.peekValue(5);
        boolean z2 = peekValue != null;
        int i6 = z2 ? peekValue.type : 0;
        TypedValue peekValue2 = obtainStyledAttributes.peekValue(6);
        boolean z3 = peekValue2 != null;
        int i7 = z3 ? peekValue2.type : 0;
        if ((z2 && i6 >= 28 && i6 <= 31) || (z3 && i7 >= 28 && i7 <= 31)) {
            valueAnimator2.setEvaluator(new ArgbEvaluator());
            z = false;
        }
        if (!z) {
            i = 0;
            if (z2) {
                if (i6 == 5) {
                    i3 = (int) obtainStyledAttributes.getDimension(5, 0.0f);
                } else if (i6 >= 28 && i6 <= 31) {
                    i3 = obtainStyledAttributes.getColor(5, 0);
                } else {
                    i3 = obtainStyledAttributes.getInt(5, 0);
                }
                if (z3) {
                    if (i7 == 5) {
                        i4 = (int) obtainStyledAttributes.getDimension(6, 0.0f);
                    } else if (i7 >= 28 && i7 <= 31) {
                        i4 = obtainStyledAttributes.getColor(6, 0);
                    } else {
                        i4 = obtainStyledAttributes.getInt(6, 0);
                    }
                    valueAnimator2.setIntValues(i3, i4);
                } else {
                    valueAnimator2.setIntValues(i3);
                }
            } else if (z3) {
                if (i7 == 5) {
                    i2 = (int) obtainStyledAttributes.getDimension(6, 0.0f);
                } else if (i7 >= 28 && i7 <= 31) {
                    i2 = obtainStyledAttributes.getColor(6, 0);
                } else {
                    i2 = obtainStyledAttributes.getInt(6, 0);
                }
                valueAnimator2.setIntValues(i2);
            }
        } else if (z2) {
            if (i6 == 5) {
                f2 = obtainStyledAttributes.getDimension(5, 0.0f);
            } else {
                f2 = obtainStyledAttributes.getFloat(5, 0.0f);
            }
            if (z3) {
                if (i7 == 5) {
                    f3 = obtainStyledAttributes.getDimension(6, 0.0f);
                } else {
                    f3 = obtainStyledAttributes.getFloat(6, 0.0f);
                }
                i = 0;
                valueAnimator2.setFloatValues(f2, f3);
            } else {
                i = 0;
                valueAnimator2.setFloatValues(f2);
            }
        } else {
            i = 0;
            if (i7 == 5) {
                f = obtainStyledAttributes.getDimension(6, 0.0f);
            } else {
                f = obtainStyledAttributes.getFloat(6, 0.0f);
            }
            valueAnimator2.setFloatValues(f);
        }
        valueAnimator2.setDuration(j);
        valueAnimator2.setStartDelay(j2);
        if (obtainStyledAttributes.hasValue(3)) {
            valueAnimator2.setRepeatCount(obtainStyledAttributes.getInt(3, i));
        }
        if (obtainStyledAttributes.hasValue(4)) {
            valueAnimator2.setRepeatMode(obtainStyledAttributes.getInt(4, 1));
        }
        int resourceId = obtainStyledAttributes.getResourceId(i, i);
        if (resourceId > 0) {
            valueAnimator2.setInterpolator(AnimationUtils.loadInterpolator(context, resourceId));
        }
        obtainStyledAttributes.recycle();
        return valueAnimator2;
    }
}
