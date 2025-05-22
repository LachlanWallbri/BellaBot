package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.ImageView;
import java.lang.reflect.Field;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
class ImageViewUtils {
    private static Field sDrawMatrixField = null;
    private static boolean sDrawMatrixFieldFetched = false;
    private static boolean sTryHiddenAnimateTransform = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void animateTransform(ImageView imageView, Matrix matrix) {
        if (Build.VERSION.SDK_INT >= 29) {
            imageView.animateTransform(matrix);
            return;
        }
        if (matrix == null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                drawable.setBounds(0, 0, (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight(), (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom());
                imageView.invalidate();
                return;
            }
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            hiddenAnimateTransform(imageView, matrix);
            return;
        }
        Drawable drawable2 = imageView.getDrawable();
        if (drawable2 != null) {
            drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight());
            Matrix matrix2 = null;
            fetchDrawMatrixField();
            Field field = sDrawMatrixField;
            if (field != null) {
                try {
                    Matrix matrix3 = (Matrix) field.get(imageView);
                    if (matrix3 == null) {
                        try {
                            matrix2 = new Matrix();
                            sDrawMatrixField.set(imageView, matrix2);
                        } catch (IllegalAccessException unused) {
                        }
                    }
                    matrix2 = matrix3;
                } catch (IllegalAccessException unused2) {
                }
            }
            if (matrix2 != null) {
                matrix2.set(matrix);
            }
            imageView.invalidate();
        }
    }

    private static void hiddenAnimateTransform(ImageView imageView, Matrix matrix) {
        if (sTryHiddenAnimateTransform) {
            try {
                imageView.animateTransform(matrix);
            } catch (NoSuchMethodError unused) {
                sTryHiddenAnimateTransform = false;
            }
        }
    }

    /* renamed from: androidx.transition.ImageViewUtils$1 */
    /* loaded from: classes.dex */
    static class C06781 extends AnimatorListenerAdapter {
        final /* synthetic */ ImageView val$view;

        C06781(ImageView imageView) {
            this.val$view = imageView;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            ImageView.ScaleType scaleType = (ImageView.ScaleType) this.val$view.getTag(C0680R.id.save_scale_type);
            this.val$view.setScaleType(scaleType);
            this.val$view.setTag(C0680R.id.save_scale_type, null);
            if (scaleType == ImageView.ScaleType.MATRIX) {
                ImageView imageView = this.val$view;
                imageView.setImageMatrix((Matrix) imageView.getTag(C0680R.id.save_image_matrix));
                this.val$view.setTag(C0680R.id.save_image_matrix, null);
            }
            animator.removeListener(this);
        }
    }

    private static void fetchDrawMatrixField() {
        if (sDrawMatrixFieldFetched) {
            return;
        }
        try {
            Field declaredField = ImageView.class.getDeclaredField("mDrawMatrix");
            sDrawMatrixField = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException unused) {
        }
        sDrawMatrixFieldFetched = true;
    }

    private ImageViewUtils() {
    }
}
