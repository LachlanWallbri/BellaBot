package com.nineoldandroids.animation;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes4.dex */
public class IntEvaluator implements TypeEvaluator<Integer> {
    @Override // com.nineoldandroids.animation.TypeEvaluator
    public Integer evaluate(float f, Integer num, Integer num2) {
        return Integer.valueOf((int) (num.intValue() + (f * (num2.intValue() - r3))));
    }
}
