package es.dmoral.toasty;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class Toasty {
    public static final int LENGTH_LONG = 1;
    public static final int LENGTH_SHORT = 0;
    private static final Typeface LOADED_TOAST_TYPEFACE = Typeface.create("sans-serif-condensed", 0);
    private static Typeface currentTypeface = LOADED_TOAST_TYPEFACE;
    private static int textSize = 16;
    private static boolean tintIcon = true;
    private static boolean allowQueue = true;
    private static Toast lastToast = null;

    private Toasty() {
    }

    public static Toast normal(Context context, int i) {
        return normal(context, (CharSequence) context.getString(i), 0, (Drawable) null, false);
    }

    public static Toast normal(Context context, CharSequence charSequence) {
        return normal(context, charSequence, 0, (Drawable) null, false);
    }

    public static Toast normal(Context context, int i, Drawable drawable) {
        return normal(context, (CharSequence) context.getString(i), 0, drawable, true);
    }

    public static Toast normal(Context context, CharSequence charSequence, Drawable drawable) {
        return normal(context, charSequence, 0, drawable, true);
    }

    public static Toast normal(Context context, int i, int i2) {
        return normal(context, (CharSequence) context.getString(i), i2, (Drawable) null, false);
    }

    public static Toast normal(Context context, CharSequence charSequence, int i) {
        return normal(context, charSequence, i, (Drawable) null, false);
    }

    public static Toast normal(Context context, int i, int i2, Drawable drawable) {
        return normal(context, (CharSequence) context.getString(i), i2, drawable, true);
    }

    public static Toast normal(Context context, CharSequence charSequence, int i, Drawable drawable) {
        return normal(context, charSequence, i, drawable, true);
    }

    public static Toast normal(Context context, int i, int i2, Drawable drawable, boolean z) {
        return custom(context, (CharSequence) context.getString(i), drawable, ToastyUtils.getColor(context, C5967R.color.normalColor), ToastyUtils.getColor(context, C5967R.color.defaultTextColor), i2, z, true);
    }

    public static Toast normal(Context context, CharSequence charSequence, int i, Drawable drawable, boolean z) {
        return custom(context, charSequence, drawable, ToastyUtils.getColor(context, C5967R.color.normalColor), ToastyUtils.getColor(context, C5967R.color.defaultTextColor), i, z, true);
    }

    public static Toast warning(Context context, int i) {
        return warning(context, (CharSequence) context.getString(i), 0, true);
    }

    public static Toast warning(Context context, CharSequence charSequence) {
        return warning(context, charSequence, 0, true);
    }

    public static Toast warning(Context context, int i, int i2) {
        return warning(context, (CharSequence) context.getString(i), i2, true);
    }

    public static Toast warning(Context context, CharSequence charSequence, int i) {
        return warning(context, charSequence, i, true);
    }

    public static Toast warning(Context context, int i, int i2, boolean z) {
        return custom(context, (CharSequence) context.getString(i), ToastyUtils.getDrawable(context, C5967R.drawable.ic_error_outline_white_24dp), ToastyUtils.getColor(context, C5967R.color.warningColor), ToastyUtils.getColor(context, C5967R.color.defaultTextColor), i2, z, true);
    }

    public static Toast warning(Context context, CharSequence charSequence, int i, boolean z) {
        return custom(context, charSequence, ToastyUtils.getDrawable(context, C5967R.drawable.ic_error_outline_white_24dp), ToastyUtils.getColor(context, C5967R.color.warningColor), ToastyUtils.getColor(context, C5967R.color.defaultTextColor), i, z, true);
    }

    public static Toast info(Context context, int i) {
        return info(context, (CharSequence) context.getString(i), 0, true);
    }

    public static Toast info(Context context, CharSequence charSequence) {
        return info(context, charSequence, 0, true);
    }

    public static Toast info(Context context, int i, int i2) {
        return info(context, (CharSequence) context.getString(i), i2, true);
    }

    public static Toast info(Context context, CharSequence charSequence, int i) {
        return info(context, charSequence, i, true);
    }

    public static Toast info(Context context, int i, int i2, boolean z) {
        return custom(context, (CharSequence) context.getString(i), ToastyUtils.getDrawable(context, C5967R.drawable.ic_info_outline_white_24dp), ToastyUtils.getColor(context, C5967R.color.infoColor), ToastyUtils.getColor(context, C5967R.color.defaultTextColor), i2, z, true);
    }

    public static Toast info(Context context, CharSequence charSequence, int i, boolean z) {
        return custom(context, charSequence, ToastyUtils.getDrawable(context, C5967R.drawable.ic_info_outline_white_24dp), ToastyUtils.getColor(context, C5967R.color.infoColor), ToastyUtils.getColor(context, C5967R.color.defaultTextColor), i, z, true);
    }

    public static Toast success(Context context, int i) {
        return success(context, (CharSequence) context.getString(i), 0, true);
    }

    public static Toast success(Context context, CharSequence charSequence) {
        return success(context, charSequence, 0, true);
    }

    public static Toast success(Context context, int i, int i2) {
        return success(context, (CharSequence) context.getString(i), i2, true);
    }

    public static Toast success(Context context, CharSequence charSequence, int i) {
        return success(context, charSequence, i, true);
    }

    public static Toast success(Context context, int i, int i2, boolean z) {
        return custom(context, (CharSequence) context.getString(i), ToastyUtils.getDrawable(context, C5967R.drawable.ic_check_white_24dp), ToastyUtils.getColor(context, C5967R.color.successColor), ToastyUtils.getColor(context, C5967R.color.defaultTextColor), i2, z, true);
    }

    public static Toast success(Context context, CharSequence charSequence, int i, boolean z) {
        return custom(context, charSequence, ToastyUtils.getDrawable(context, C5967R.drawable.ic_check_white_24dp), ToastyUtils.getColor(context, C5967R.color.successColor), ToastyUtils.getColor(context, C5967R.color.defaultTextColor), i, z, true);
    }

    public static Toast error(Context context, int i) {
        return error(context, (CharSequence) context.getString(i), 0, true);
    }

    public static Toast error(Context context, CharSequence charSequence) {
        return error(context, charSequence, 0, true);
    }

    public static Toast error(Context context, int i, int i2) {
        return error(context, (CharSequence) context.getString(i), i2, true);
    }

    public static Toast error(Context context, CharSequence charSequence, int i) {
        return error(context, charSequence, i, true);
    }

    public static Toast error(Context context, int i, int i2, boolean z) {
        return custom(context, (CharSequence) context.getString(i), ToastyUtils.getDrawable(context, C5967R.drawable.ic_clear_white_24dp), ToastyUtils.getColor(context, C5967R.color.errorColor), ToastyUtils.getColor(context, C5967R.color.defaultTextColor), i2, z, true);
    }

    public static Toast error(Context context, CharSequence charSequence, int i, boolean z) {
        return custom(context, charSequence, ToastyUtils.getDrawable(context, C5967R.drawable.ic_clear_white_24dp), ToastyUtils.getColor(context, C5967R.color.errorColor), ToastyUtils.getColor(context, C5967R.color.defaultTextColor), i, z, true);
    }

    public static Toast custom(Context context, int i, Drawable drawable, int i2, boolean z) {
        return custom(context, (CharSequence) context.getString(i), drawable, -1, ToastyUtils.getColor(context, C5967R.color.defaultTextColor), i2, z, false);
    }

    public static Toast custom(Context context, CharSequence charSequence, Drawable drawable, int i, boolean z) {
        return custom(context, charSequence, drawable, -1, ToastyUtils.getColor(context, C5967R.color.defaultTextColor), i, z, false);
    }

    public static Toast custom(Context context, int i, int i2, int i3, int i4, boolean z, boolean z2) {
        return custom(context, context.getString(i), ToastyUtils.getDrawable(context, i2), ToastyUtils.getColor(context, i3), ToastyUtils.getColor(context, C5967R.color.defaultTextColor), i4, z, z2);
    }

    public static Toast custom(Context context, CharSequence charSequence, int i, int i2, int i3, boolean z, boolean z2) {
        return custom(context, charSequence, ToastyUtils.getDrawable(context, i), ToastyUtils.getColor(context, i2), ToastyUtils.getColor(context, C5967R.color.defaultTextColor), i3, z, z2);
    }

    public static Toast custom(Context context, int i, Drawable drawable, int i2, int i3, boolean z, boolean z2) {
        return custom(context, context.getString(i), drawable, ToastyUtils.getColor(context, i2), ToastyUtils.getColor(context, C5967R.color.defaultTextColor), i3, z, z2);
    }

    public static Toast custom(Context context, int i, Drawable drawable, int i2, int i3, int i4, boolean z, boolean z2) {
        return custom(context, context.getString(i), drawable, ToastyUtils.getColor(context, i2), ToastyUtils.getColor(context, i3), i4, z, z2);
    }

    public static Toast custom(Context context, CharSequence charSequence, Drawable drawable, int i, int i2, int i3, boolean z, boolean z2) {
        Drawable drawable2;
        Toast makeText = Toast.makeText(context, "", i3);
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(C5967R.layout.toast_layout, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(C5967R.id.toast_icon);
        TextView textView = (TextView) inflate.findViewById(C5967R.id.toast_text);
        if (z2) {
            drawable2 = ToastyUtils.tint9PatchDrawableFrame(context, i);
        } else {
            drawable2 = ToastyUtils.getDrawable(context, C5967R.drawable.toast_frame);
        }
        ToastyUtils.setBackground(inflate, drawable2);
        if (!z) {
            imageView.setVisibility(8);
        } else {
            if (drawable == null) {
                throw new IllegalArgumentException("Avoid passing 'icon' as null if 'withIcon' is set to true");
            }
            if (tintIcon) {
                drawable = ToastyUtils.tintIcon(drawable, i2);
            }
            ToastyUtils.setBackground(imageView, drawable);
        }
        textView.setText(charSequence);
        textView.setTextColor(i2);
        textView.setTypeface(currentTypeface);
        textView.setTextSize(2, textSize);
        makeText.setView(inflate);
        if (!allowQueue) {
            Toast toast = lastToast;
            if (toast != null) {
                toast.cancel();
            }
            lastToast = makeText;
        }
        return makeText;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class Config {
        private Typeface typeface = Toasty.currentTypeface;
        private int textSize = Toasty.textSize;
        private boolean tintIcon = Toasty.tintIcon;
        private boolean allowQueue = true;

        private Config() {
        }

        public static Config getInstance() {
            return new Config();
        }

        public static void reset() {
            Typeface unused = Toasty.currentTypeface = Toasty.LOADED_TOAST_TYPEFACE;
            int unused2 = Toasty.textSize = 16;
            boolean unused3 = Toasty.tintIcon = true;
            boolean unused4 = Toasty.allowQueue = true;
        }

        public Config setToastTypeface(Typeface typeface) {
            this.typeface = typeface;
            return this;
        }

        public Config setTextSize(int i) {
            this.textSize = i;
            return this;
        }

        public Config tintIcon(boolean z) {
            this.tintIcon = z;
            return this;
        }

        public Config allowQueue(boolean z) {
            this.allowQueue = z;
            return this;
        }

        public void apply() {
            Typeface unused = Toasty.currentTypeface = this.typeface;
            int unused2 = Toasty.textSize = this.textSize;
            boolean unused3 = Toasty.tintIcon = this.tintIcon;
            boolean unused4 = Toasty.allowQueue = this.allowQueue;
        }
    }
}
