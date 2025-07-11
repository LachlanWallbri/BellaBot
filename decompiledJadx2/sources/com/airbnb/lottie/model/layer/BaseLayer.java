package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import androidx.core.view.ViewCompat;
import com.airbnb.lottie.C0750L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.DrawingContent;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.MaskKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.KeyPathElement;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public abstract class BaseLayer implements DrawingContent, BaseKeyframeAnimation.AnimationListener, KeyPathElement {
    private static final int CLIP_SAVE_FLAG = 2;
    private static final int CLIP_TO_LAYER_SAVE_FLAG = 16;
    private static final int MATRIX_SAVE_FLAG = 1;
    private static final int SAVE_FLAGS = 19;
    private final String drawTraceName;
    final Layer layerModel;
    final LottieDrawable lottieDrawable;
    private MaskKeyframeAnimation mask;
    private BaseLayer matteLayer;
    private BaseLayer parentLayer;
    private List<BaseLayer> parentLayers;
    final TransformKeyframeAnimation transform;
    private final Path path = new Path();
    private final Matrix matrix = new Matrix();
    private final Paint contentPaint = new LPaint(1);
    private final Paint dstInPaint = new LPaint(1, PorterDuff.Mode.DST_IN);
    private final Paint dstOutPaint = new LPaint(1, PorterDuff.Mode.DST_OUT);
    private final Paint mattePaint = new LPaint(1);
    private final Paint clearPaint = new LPaint(PorterDuff.Mode.CLEAR);
    private final RectF rect = new RectF();
    private final RectF maskBoundsRect = new RectF();
    private final RectF matteBoundsRect = new RectF();
    private final RectF tempMaskBoundsRect = new RectF();
    final Matrix boundsMatrix = new Matrix();
    private final List<BaseKeyframeAnimation<?, ?>> animations = new ArrayList();
    private boolean visible = true;

    abstract void drawLayer(Canvas canvas, Matrix matrix, int i);

    void resolveChildKeyPath(KeyPath keyPath, int i, List<KeyPath> list, KeyPath keyPath2) {
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void setContents(List<Content> list, List<Content> list2) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BaseLayer forModel(Layer layer, LottieDrawable lottieDrawable, LottieComposition lottieComposition) {
        switch (layer.getLayerType()) {
            case SHAPE:
                return new ShapeLayer(lottieDrawable, layer);
            case PRE_COMP:
                return new CompositionLayer(lottieDrawable, layer, lottieComposition.getPrecomps(layer.getRefId()), lottieComposition);
            case SOLID:
                return new SolidLayer(lottieDrawable, layer);
            case IMAGE:
                return new ImageLayer(lottieDrawable, layer);
            case NULL:
                return new NullLayer(lottieDrawable, layer);
            case TEXT:
                return new TextLayer(lottieDrawable, layer);
            default:
                Logger.warning("Unknown layer type " + layer.getLayerType());
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseLayer(LottieDrawable lottieDrawable, Layer layer) {
        this.lottieDrawable = lottieDrawable;
        this.layerModel = layer;
        this.drawTraceName = layer.getName() + "#draw";
        if (layer.getMatteType() == Layer.MatteType.INVERT) {
            this.mattePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        } else {
            this.mattePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        }
        this.transform = layer.getTransform().createAnimation();
        this.transform.addListener(this);
        if (layer.getMasks() != null && !layer.getMasks().isEmpty()) {
            this.mask = new MaskKeyframeAnimation(layer.getMasks());
            Iterator<BaseKeyframeAnimation<ShapeData, Path>> it = this.mask.getMaskAnimations().iterator();
            while (it.hasNext()) {
                it.next().addUpdateListener(this);
            }
            for (BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation : this.mask.getOpacityAnimations()) {
                addAnimation(baseKeyframeAnimation);
                baseKeyframeAnimation.addUpdateListener(this);
            }
        }
        setupInOutAnimations();
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        invalidateSelf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Layer getLayerModel() {
        return this.layerModel;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMatteLayer(BaseLayer baseLayer) {
        this.matteLayer = baseLayer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasMatteOnThisLayer() {
        return this.matteLayer != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setParentLayer(BaseLayer baseLayer) {
        this.parentLayer = baseLayer;
    }

    private void setupInOutAnimations() {
        if (!this.layerModel.getInOutKeyframes().isEmpty()) {
            final FloatKeyframeAnimation floatKeyframeAnimation = new FloatKeyframeAnimation(this.layerModel.getInOutKeyframes());
            floatKeyframeAnimation.setIsDiscrete();
            floatKeyframeAnimation.addUpdateListener(new BaseKeyframeAnimation.AnimationListener() { // from class: com.airbnb.lottie.model.layer.BaseLayer.1
                @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
                public void onValueChanged() {
                    BaseLayer.this.setVisible(floatKeyframeAnimation.getFloatValue() == 1.0f);
                }
            });
            setVisible(floatKeyframeAnimation.getValue().floatValue() == 1.0f);
            addAnimation(floatKeyframeAnimation);
            return;
        }
        setVisible(true);
    }

    private void invalidateSelf() {
        this.lottieDrawable.invalidateSelf();
    }

    public void addAnimation(BaseKeyframeAnimation<?, ?> baseKeyframeAnimation) {
        if (baseKeyframeAnimation == null) {
            return;
        }
        this.animations.add(baseKeyframeAnimation);
    }

    public void removeAnimation(BaseKeyframeAnimation<?, ?> baseKeyframeAnimation) {
        this.animations.remove(baseKeyframeAnimation);
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void getBounds(RectF rectF, Matrix matrix, boolean z) {
        this.rect.set(0.0f, 0.0f, 0.0f, 0.0f);
        buildParentLayerListIfNeeded();
        this.boundsMatrix.set(matrix);
        if (z) {
            List<BaseLayer> list = this.parentLayers;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.boundsMatrix.preConcat(this.parentLayers.get(size).transform.getMatrix());
                }
            } else {
                BaseLayer baseLayer = this.parentLayer;
                if (baseLayer != null) {
                    this.boundsMatrix.preConcat(baseLayer.transform.getMatrix());
                }
            }
        }
        this.boundsMatrix.preConcat(this.transform.getMatrix());
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void draw(Canvas canvas, Matrix matrix, int i) {
        C0750L.beginSection(this.drawTraceName);
        if (!this.visible || this.layerModel.isHidden()) {
            C0750L.endSection(this.drawTraceName);
            return;
        }
        buildParentLayerListIfNeeded();
        C0750L.beginSection("Layer#parentMatrix");
        this.matrix.reset();
        this.matrix.set(matrix);
        for (int size = this.parentLayers.size() - 1; size >= 0; size--) {
            this.matrix.preConcat(this.parentLayers.get(size).transform.getMatrix());
        }
        C0750L.endSection("Layer#parentMatrix");
        int intValue = (int) ((((i / 255.0f) * (this.transform.getOpacity() == null ? 100 : this.transform.getOpacity().getValue().intValue())) / 100.0f) * 255.0f);
        if (!hasMatteOnThisLayer() && !hasMasksOnThisLayer()) {
            this.matrix.preConcat(this.transform.getMatrix());
            C0750L.beginSection("Layer#drawLayer");
            drawLayer(canvas, this.matrix, intValue);
            C0750L.endSection("Layer#drawLayer");
            recordRenderTime(C0750L.endSection(this.drawTraceName));
            return;
        }
        C0750L.beginSection("Layer#computeBounds");
        getBounds(this.rect, this.matrix, false);
        intersectBoundsWithMatte(this.rect, matrix);
        this.matrix.preConcat(this.transform.getMatrix());
        intersectBoundsWithMask(this.rect, this.matrix);
        if (!this.rect.intersect(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight())) {
            this.rect.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
        C0750L.endSection("Layer#computeBounds");
        if (!this.rect.isEmpty()) {
            C0750L.beginSection("Layer#saveLayer");
            Utils.saveLayerCompat(canvas, this.rect, this.contentPaint);
            C0750L.endSection("Layer#saveLayer");
            clearCanvas(canvas);
            C0750L.beginSection("Layer#drawLayer");
            drawLayer(canvas, this.matrix, intValue);
            C0750L.endSection("Layer#drawLayer");
            if (hasMasksOnThisLayer()) {
                applyMasks(canvas, this.matrix);
            }
            if (hasMatteOnThisLayer()) {
                C0750L.beginSection("Layer#drawMatte");
                C0750L.beginSection("Layer#saveLayer");
                Utils.saveLayerCompat(canvas, this.rect, this.mattePaint, 19);
                C0750L.endSection("Layer#saveLayer");
                clearCanvas(canvas);
                this.matteLayer.draw(canvas, matrix, intValue);
                C0750L.beginSection("Layer#restoreLayer");
                canvas.restore();
                C0750L.endSection("Layer#restoreLayer");
                C0750L.endSection("Layer#drawMatte");
            }
            C0750L.beginSection("Layer#restoreLayer");
            canvas.restore();
            C0750L.endSection("Layer#restoreLayer");
        }
        recordRenderTime(C0750L.endSection(this.drawTraceName));
    }

    private void recordRenderTime(float f) {
        this.lottieDrawable.getComposition().getPerformanceTracker().recordRenderTime(this.layerModel.getName(), f);
    }

    private void clearCanvas(Canvas canvas) {
        C0750L.beginSection("Layer#clearLayer");
        canvas.drawRect(this.rect.left - 1.0f, this.rect.top - 1.0f, this.rect.right + 1.0f, this.rect.bottom + 1.0f, this.clearPaint);
        C0750L.endSection("Layer#clearLayer");
    }

    private void intersectBoundsWithMask(RectF rectF, Matrix matrix) {
        this.maskBoundsRect.set(0.0f, 0.0f, 0.0f, 0.0f);
        if (hasMasksOnThisLayer()) {
            int size = this.mask.getMasks().size();
            for (int i = 0; i < size; i++) {
                Mask mask = this.mask.getMasks().get(i);
                this.path.set(this.mask.getMaskAnimations().get(i).getValue());
                this.path.transform(matrix);
                int i2 = C07952.$SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[mask.getMaskMode().ordinal()];
                if (i2 == 1 || i2 == 2) {
                    return;
                }
                if ((i2 == 3 || i2 == 4) && mask.isInverted()) {
                    return;
                }
                this.path.computeBounds(this.tempMaskBoundsRect, false);
                if (i == 0) {
                    this.maskBoundsRect.set(this.tempMaskBoundsRect);
                } else {
                    RectF rectF2 = this.maskBoundsRect;
                    rectF2.set(Math.min(rectF2.left, this.tempMaskBoundsRect.left), Math.min(this.maskBoundsRect.top, this.tempMaskBoundsRect.top), Math.max(this.maskBoundsRect.right, this.tempMaskBoundsRect.right), Math.max(this.maskBoundsRect.bottom, this.tempMaskBoundsRect.bottom));
                }
            }
            if (rectF.intersect(this.maskBoundsRect)) {
                return;
            }
            rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.airbnb.lottie.model.layer.BaseLayer$2 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C07952 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode = new int[Mask.MaskMode.values().length];

        static {
            try {
                $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[Mask.MaskMode.MASK_MODE_NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[Mask.MaskMode.MASK_MODE_SUBTRACT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[Mask.MaskMode.MASK_MODE_INTERSECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[Mask.MaskMode.MASK_MODE_ADD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType = new int[Layer.LayerType.values().length];
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[Layer.LayerType.SHAPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[Layer.LayerType.PRE_COMP.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[Layer.LayerType.SOLID.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[Layer.LayerType.IMAGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[Layer.LayerType.NULL.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[Layer.LayerType.TEXT.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[Layer.LayerType.UNKNOWN.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    private void intersectBoundsWithMatte(RectF rectF, Matrix matrix) {
        if (hasMatteOnThisLayer() && this.layerModel.getMatteType() != Layer.MatteType.INVERT) {
            this.matteBoundsRect.set(0.0f, 0.0f, 0.0f, 0.0f);
            this.matteLayer.getBounds(this.matteBoundsRect, matrix, true);
            if (rectF.intersect(this.matteBoundsRect)) {
                return;
            }
            rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    private void applyMasks(Canvas canvas, Matrix matrix) {
        C0750L.beginSection("Layer#saveLayer");
        Utils.saveLayerCompat(canvas, this.rect, this.dstInPaint, 19);
        if (Build.VERSION.SDK_INT < 28) {
            canvas.drawColor(0);
        }
        C0750L.endSection("Layer#saveLayer");
        for (int i = 0; i < this.mask.getMasks().size(); i++) {
            Mask mask = this.mask.getMasks().get(i);
            BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation = this.mask.getMaskAnimations().get(i);
            BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2 = this.mask.getOpacityAnimations().get(i);
            int i2 = C07952.$SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[mask.getMaskMode().ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    if (i == 0) {
                        this.contentPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
                        this.contentPaint.setAlpha(255);
                        canvas.drawRect(this.rect, this.contentPaint);
                    }
                    if (mask.isInverted()) {
                        applyInvertedSubtractMask(canvas, matrix, mask, baseKeyframeAnimation, baseKeyframeAnimation2);
                    } else {
                        applySubtractMask(canvas, matrix, mask, baseKeyframeAnimation, baseKeyframeAnimation2);
                    }
                } else if (i2 != 3) {
                    if (i2 == 4) {
                        if (mask.isInverted()) {
                            applyInvertedAddMask(canvas, matrix, mask, baseKeyframeAnimation, baseKeyframeAnimation2);
                        } else {
                            applyAddMask(canvas, matrix, mask, baseKeyframeAnimation, baseKeyframeAnimation2);
                        }
                    }
                } else if (mask.isInverted()) {
                    applyInvertedIntersectMask(canvas, matrix, mask, baseKeyframeAnimation, baseKeyframeAnimation2);
                } else {
                    applyIntersectMask(canvas, matrix, mask, baseKeyframeAnimation, baseKeyframeAnimation2);
                }
            } else if (areAllMasksNone()) {
                this.contentPaint.setAlpha(255);
                canvas.drawRect(this.rect, this.contentPaint);
            }
        }
        C0750L.beginSection("Layer#restoreLayer");
        canvas.restore();
        C0750L.endSection("Layer#restoreLayer");
    }

    private boolean areAllMasksNone() {
        if (this.mask.getMaskAnimations().isEmpty()) {
            return false;
        }
        for (int i = 0; i < this.mask.getMasks().size(); i++) {
            if (this.mask.getMasks().get(i).getMaskMode() != Mask.MaskMode.MASK_MODE_NONE) {
                return false;
            }
        }
        return true;
    }

    private void applyAddMask(Canvas canvas, Matrix matrix, Mask mask, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation, BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2) {
        this.path.set(baseKeyframeAnimation.getValue());
        this.path.transform(matrix);
        this.contentPaint.setAlpha((int) (baseKeyframeAnimation2.getValue().intValue() * 2.55f));
        canvas.drawPath(this.path, this.contentPaint);
    }

    private void applyInvertedAddMask(Canvas canvas, Matrix matrix, Mask mask, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation, BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2) {
        Utils.saveLayerCompat(canvas, this.rect, this.contentPaint);
        canvas.drawRect(this.rect, this.contentPaint);
        this.path.set(baseKeyframeAnimation.getValue());
        this.path.transform(matrix);
        this.contentPaint.setAlpha((int) (baseKeyframeAnimation2.getValue().intValue() * 2.55f));
        canvas.drawPath(this.path, this.dstOutPaint);
        canvas.restore();
    }

    private void applySubtractMask(Canvas canvas, Matrix matrix, Mask mask, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation, BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2) {
        this.path.set(baseKeyframeAnimation.getValue());
        this.path.transform(matrix);
        canvas.drawPath(this.path, this.dstOutPaint);
    }

    private void applyInvertedSubtractMask(Canvas canvas, Matrix matrix, Mask mask, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation, BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2) {
        Utils.saveLayerCompat(canvas, this.rect, this.dstOutPaint);
        canvas.drawRect(this.rect, this.contentPaint);
        this.dstOutPaint.setAlpha((int) (baseKeyframeAnimation2.getValue().intValue() * 2.55f));
        this.path.set(baseKeyframeAnimation.getValue());
        this.path.transform(matrix);
        canvas.drawPath(this.path, this.dstOutPaint);
        canvas.restore();
    }

    private void applyIntersectMask(Canvas canvas, Matrix matrix, Mask mask, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation, BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2) {
        Utils.saveLayerCompat(canvas, this.rect, this.dstInPaint);
        this.path.set(baseKeyframeAnimation.getValue());
        this.path.transform(matrix);
        this.contentPaint.setAlpha((int) (baseKeyframeAnimation2.getValue().intValue() * 2.55f));
        canvas.drawPath(this.path, this.contentPaint);
        canvas.restore();
    }

    private void applyInvertedIntersectMask(Canvas canvas, Matrix matrix, Mask mask, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation, BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2) {
        Utils.saveLayerCompat(canvas, this.rect, this.dstInPaint);
        canvas.drawRect(this.rect, this.contentPaint);
        this.dstOutPaint.setAlpha((int) (baseKeyframeAnimation2.getValue().intValue() * 2.55f));
        this.path.set(baseKeyframeAnimation.getValue());
        this.path.transform(matrix);
        canvas.drawPath(this.path, this.dstOutPaint);
        canvas.restore();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasMasksOnThisLayer() {
        MaskKeyframeAnimation maskKeyframeAnimation = this.mask;
        return (maskKeyframeAnimation == null || maskKeyframeAnimation.getMaskAnimations().isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVisible(boolean z) {
        if (z != this.visible) {
            this.visible = z;
            invalidateSelf();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setProgress(float f) {
        this.transform.setProgress(f);
        if (this.mask != null) {
            for (int i = 0; i < this.mask.getMaskAnimations().size(); i++) {
                this.mask.getMaskAnimations().get(i).setProgress(f);
            }
        }
        if (this.layerModel.getTimeStretch() != 0.0f) {
            f /= this.layerModel.getTimeStretch();
        }
        BaseLayer baseLayer = this.matteLayer;
        if (baseLayer != null) {
            this.matteLayer.setProgress(baseLayer.layerModel.getTimeStretch() * f);
        }
        for (int i2 = 0; i2 < this.animations.size(); i2++) {
            this.animations.get(i2).setProgress(f);
        }
    }

    private void buildParentLayerListIfNeeded() {
        if (this.parentLayers != null) {
            return;
        }
        if (this.parentLayer == null) {
            this.parentLayers = Collections.emptyList();
            return;
        }
        this.parentLayers = new ArrayList();
        for (BaseLayer baseLayer = this.parentLayer; baseLayer != null; baseLayer = baseLayer.parentLayer) {
            this.parentLayers.add(baseLayer);
        }
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.layerModel.getName();
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void resolveKeyPath(KeyPath keyPath, int i, List<KeyPath> list, KeyPath keyPath2) {
        if (keyPath.matches(getName(), i)) {
            if (!"__container".equals(getName())) {
                keyPath2 = keyPath2.addKey(getName());
                if (keyPath.fullyResolvesTo(getName(), i)) {
                    list.add(keyPath2.resolve(this));
                }
            }
            if (keyPath.propagateToChildren(getName(), i)) {
                resolveChildKeyPath(keyPath, i + keyPath.incrementDepthBy(getName(), i), list, keyPath2);
            }
        }
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T t, LottieValueCallback<T> lottieValueCallback) {
        this.transform.applyValueCallback(t, lottieValueCallback);
    }
}
