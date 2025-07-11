package com.nineoldandroids.animation;

import android.view.View;
import com.nineoldandroids.util.FloatProperty;
import com.nineoldandroids.util.IntProperty;
import com.nineoldandroids.util.Property;
import com.nineoldandroids.view.animation.AnimatorProxy;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes4.dex */
final class PreHoneycombCompat {
    static Property<View, Float> ALPHA = new FloatProperty<View>("alpha") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.1
        @Override // com.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setAlpha(f);
        }

        @Override // com.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getAlpha());
        }
    };
    static Property<View, Float> PIVOT_X = new FloatProperty<View>("pivotX") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.2
        @Override // com.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setPivotX(f);
        }

        @Override // com.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getPivotX());
        }
    };
    static Property<View, Float> PIVOT_Y = new FloatProperty<View>("pivotY") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.3
        @Override // com.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setPivotY(f);
        }

        @Override // com.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getPivotY());
        }
    };
    static Property<View, Float> TRANSLATION_X = new FloatProperty<View>("translationX") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.4
        @Override // com.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setTranslationX(f);
        }

        @Override // com.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getTranslationX());
        }
    };
    static Property<View, Float> TRANSLATION_Y = new FloatProperty<View>("translationY") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.5
        @Override // com.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setTranslationY(f);
        }

        @Override // com.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getTranslationY());
        }
    };
    static Property<View, Float> ROTATION = new FloatProperty<View>("rotation") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.6
        @Override // com.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setRotation(f);
        }

        @Override // com.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getRotation());
        }
    };
    static Property<View, Float> ROTATION_X = new FloatProperty<View>("rotationX") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.7
        @Override // com.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setRotationX(f);
        }

        @Override // com.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getRotationX());
        }
    };
    static Property<View, Float> ROTATION_Y = new FloatProperty<View>("rotationY") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.8
        @Override // com.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setRotationY(f);
        }

        @Override // com.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getRotationY());
        }
    };
    static Property<View, Float> SCALE_X = new FloatProperty<View>("scaleX") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.9
        @Override // com.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setScaleX(f);
        }

        @Override // com.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getScaleX());
        }
    };
    static Property<View, Float> SCALE_Y = new FloatProperty<View>("scaleY") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.10
        @Override // com.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setScaleY(f);
        }

        @Override // com.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getScaleY());
        }
    };
    static Property<View, Integer> SCROLL_X = new IntProperty<View>("scrollX") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.11
        @Override // com.nineoldandroids.util.IntProperty
        public void setValue(View view, int i) {
            AnimatorProxy.wrap(view).setScrollX(i);
        }

        @Override // com.nineoldandroids.util.Property
        public Integer get(View view) {
            return Integer.valueOf(AnimatorProxy.wrap(view).getScrollX());
        }
    };
    static Property<View, Integer> SCROLL_Y = new IntProperty<View>("scrollY") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.12
        @Override // com.nineoldandroids.util.IntProperty
        public void setValue(View view, int i) {
            AnimatorProxy.wrap(view).setScrollY(i);
        }

        @Override // com.nineoldandroids.util.Property
        public Integer get(View view) {
            return Integer.valueOf(AnimatorProxy.wrap(view).getScrollY());
        }
    };

    /* renamed from: X */
    static Property<View, Float> f4362X = new FloatProperty<View>("x") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.13
        @Override // com.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setX(f);
        }

        @Override // com.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getX());
        }
    };

    /* renamed from: Y */
    static Property<View, Float> f4363Y = new FloatProperty<View>("y") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.14
        @Override // com.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setY(f);
        }

        @Override // com.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getY());
        }
    };

    private PreHoneycombCompat() {
    }
}
