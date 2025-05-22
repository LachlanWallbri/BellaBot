package org.jetbrains.anko;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.iflytek.aiui.constant.InternalConstant;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: CustomViewProperties.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b(\")\u0010\u0002\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018G@Æ\u0002X\u0086\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\"(\u0010\n\u001a\u00020\t*\u00020\u000b2\u0006\u0010\b\u001a\u00020\t8G@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\"-\u0010\u0011\u001a\u0004\u0018\u00010\u0010*\u00020\u000b2\b\u0010\u0000\u001a\u0004\u0018\u00010\u00108Æ\u0002@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\")\u0010\u0016\u001a\u00020\t*\u00020\u000b2\u0006\u0010\u0000\u001a\u00020\t8Æ\u0002@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0017\u0010\r\"\u0004\b\u0018\u0010\u000f\")\u0010\u0019\u001a\u00020\t*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\t8G@Æ\u0002X\u0086\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d\"(\u0010\u001e\u001a\u00020\t*\u00020\u000b2\u0006\u0010\u0000\u001a\u00020\t8G@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001f\u0010\r\"\u0004\b \u0010\u000f\".\u0010!\u001a\u0004\u0018\u00010\u0010*\u00020\"2\b\u0010\u0000\u001a\u0004\u0018\u00010\u00108Æ\u0002@Æ\u0002X\u0086\u000e¢\u0006\f\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&\"*\u0010'\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018Æ\u0002@Æ\u0002X\u0086\u000e¢\u0006\f\u001a\u0004\b'\u0010\u0005\"\u0004\b(\u0010\u0007\")\u0010)\u001a\u00020\t*\u00020\u000b2\u0006\u0010\u0000\u001a\u00020\t8Æ\u0002@FX\u0086\u000e¢\u0006\f\u001a\u0004\b*\u0010\r\"\u0004\b+\u0010\u000f\")\u0010,\u001a\u00020\t*\u00020\u000b2\u0006\u0010\u0000\u001a\u00020\t8G@Æ\u0002X\u0086\u000e¢\u0006\f\u001a\u0004\b-\u0010\r\"\u0004\b.\u0010\u000f\".\u0010/\u001a\u00020\t*\u00020\u000b2\u0006\u0010\u0000\u001a\u00020\t8G@FX\u0087\u000e¢\u0006\u0012\u0012\u0004\b0\u00101\u001a\u0004\b2\u0010\r\"\u0004\b3\u0010\u000f\".\u00104\u001a\u00020\t*\u00020\u000b2\u0006\u0010\u0000\u001a\u00020\t8G@FX\u0087\u000e¢\u0006\u0012\u0012\u0004\b5\u00101\u001a\u0004\b6\u0010\r\"\u0004\b7\u0010\u000f\")\u00108\u001a\u00020\t*\u00020\u000b2\u0006\u0010\u0000\u001a\u00020\t8Æ\u0002@FX\u0086\u000e¢\u0006\f\u001a\u0004\b9\u0010\r\"\u0004\b:\u0010\u000f\"(\u0010;\u001a\u00020\t*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\t8G@FX\u0086\u000e¢\u0006\f\u001a\u0004\b<\u0010\u001b\"\u0004\b=\u0010\u001d\"(\u0010>\u001a\u00020\t*\u00020\u00032\u0006\u0010\b\u001a\u00020\t8G@FX\u0086\u000e¢\u0006\f\u001a\u0004\b?\u0010\u001b\"\u0004\b@\u0010\u001d\"(\u0010A\u001a\u00020\t*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\t8G@FX\u0086\u000e¢\u0006\f\u001a\u0004\bB\u0010\u001b\"\u0004\bC\u0010\u001d\")\u0010D\u001a\u00020\t*\u00020\u000b2\u0006\u0010\u0000\u001a\u00020\t8Æ\u0002@FX\u0086\u000e¢\u0006\f\u001a\u0004\bE\u0010\r\"\u0004\bF\u0010\u000f\"(\u0010G\u001a\u00020\t*\u00020\u000b2\u0006\u0010\u0000\u001a\u00020\t8G@FX\u0086\u000e¢\u0006\f\u001a\u0004\bH\u0010\r\"\u0004\bI\u0010\u000f¨\u0006J"}, m3961d2 = {ES6Iterator.VALUE_PROPERTY, "", "allCaps", "Landroid/widget/TextView;", "getAllCaps", "(Landroid/widget/TextView;)Z", "setAllCaps", "(Landroid/widget/TextView;Z)V", "colorId", "", "backgroundColorResource", "Landroid/view/View;", "getBackgroundColorResource", "(Landroid/view/View;)I", "setBackgroundColorResource", "(Landroid/view/View;I)V", "Landroid/graphics/drawable/Drawable;", "backgroundDrawable", "getBackgroundDrawable", "(Landroid/view/View;)Landroid/graphics/drawable/Drawable;", "setBackgroundDrawable", "(Landroid/view/View;Landroid/graphics/drawable/Drawable;)V", "bottomPadding", "getBottomPadding", "setBottomPadding", "ems", "getEms", "(Landroid/widget/TextView;)I", "setEms", "(Landroid/widget/TextView;I)V", "horizontalPadding", "getHorizontalPadding", "setHorizontalPadding", InternalConstant.DTYPE_IMAGE, "Landroid/widget/ImageView;", "getImage", "(Landroid/widget/ImageView;)Landroid/graphics/drawable/Drawable;", "setImage", "(Landroid/widget/ImageView;Landroid/graphics/drawable/Drawable;)V", "isSelectable", "setSelectable", "leftPadding", "getLeftPadding", "setLeftPadding", "padding", "getPadding", "setPadding", "paddingHorizontal", "paddingHorizontal$annotations", "(Landroid/view/View;)V", "getPaddingHorizontal", "setPaddingHorizontal", "paddingVertical", "paddingVertical$annotations", "getPaddingVertical", "setPaddingVertical", "rightPadding", "getRightPadding", "setRightPadding", "textAppearance", "getTextAppearance", "setTextAppearance", "textColorResource", "getTextColorResource", "setTextColorResource", "textSizeDimen", "getTextSizeDimen", "setTextSizeDimen", "topPadding", "getTopPadding", "setTopPadding", "verticalPadding", "getVerticalPadding", "setVerticalPadding", "platform-base_release"}, m3962k = 2, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class CustomViewPropertiesKt {
    @Deprecated(message = "Use horizontalPadding instead", replaceWith = @ReplaceWith(expression = "horizontalPadding", imports = {}))
    public static /* synthetic */ void paddingHorizontal$annotations(View view) {
    }

    @Deprecated(message = "Use verticalPadding instead", replaceWith = @ReplaceWith(expression = "verticalPadding", imports = {}))
    public static /* synthetic */ void paddingVertical$annotations(View view) {
    }

    public static final Drawable getBackgroundDrawable(View receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.getBackground();
    }

    public static final void setBackgroundDrawable(View receiver$0, Drawable drawable) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setBackgroundDrawable(drawable);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    public static final int getBackgroundColorResource(View receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public static final void setBackgroundColorResource(View receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Context context = receiver$0.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        receiver$0.setBackgroundColor(context.getResources().getColor(i));
    }

    public static final int getLeftPadding(View receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.getPaddingLeft();
    }

    public static final void setLeftPadding(View receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setPadding(i, receiver$0.getPaddingTop(), receiver$0.getPaddingRight(), receiver$0.getPaddingBottom());
    }

    public static final int getTopPadding(View receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.getPaddingTop();
    }

    public static final void setTopPadding(View receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setPadding(receiver$0.getPaddingLeft(), i, receiver$0.getPaddingRight(), receiver$0.getPaddingBottom());
    }

    public static final int getRightPadding(View receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.getPaddingRight();
    }

    public static final void setRightPadding(View receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setPadding(receiver$0.getPaddingLeft(), receiver$0.getPaddingTop(), i, receiver$0.getPaddingBottom());
    }

    public static final int getBottomPadding(View receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.getPaddingBottom();
    }

    public static final void setBottomPadding(View receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setPadding(receiver$0.getPaddingLeft(), receiver$0.getPaddingTop(), receiver$0.getPaddingRight(), i);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    public static final int getPaddingHorizontal(View receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public static final void setPaddingHorizontal(View receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setPadding(i, receiver$0.getPaddingTop(), i, receiver$0.getPaddingBottom());
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    public static final int getHorizontalPadding(View receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public static final void setHorizontalPadding(View receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setPadding(i, receiver$0.getPaddingTop(), i, receiver$0.getPaddingBottom());
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    public static final int getPaddingVertical(View receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public static final void setPaddingVertical(View receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setPadding(receiver$0.getPaddingLeft(), i, receiver$0.getPaddingRight(), i);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    public static final int getVerticalPadding(View receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public static final void setVerticalPadding(View receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setPadding(receiver$0.getPaddingLeft(), i, receiver$0.getPaddingRight(), i);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    public static final int getPadding(View receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public static final void setPadding(View receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setPadding(i, i, i, i);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    public static final boolean getAllCaps(TextView receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public static final void setAllCaps(TextView receiver$0, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setAllCaps(z);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    public static final int getEms(TextView receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public static final void setEms(TextView receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setEms(i);
    }

    public static final boolean isSelectable(TextView receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.isTextSelectable();
    }

    public static final void setSelectable(TextView receiver$0, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setTextIsSelectable(z);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    public static final int getTextAppearance(TextView receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public static final void setTextAppearance(TextView receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (Build.VERSION.SDK_INT >= 23) {
            receiver$0.setTextAppearance(i);
        } else {
            receiver$0.setTextAppearance(receiver$0.getContext(), i);
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    public static final int getTextSizeDimen(TextView receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public static final void setTextSizeDimen(TextView receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Context context = receiver$0.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        receiver$0.setTextSize(0, context.getResources().getDimension(i));
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    public static final int getTextColorResource(TextView receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public static final void setTextColorResource(TextView receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Context context = receiver$0.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        receiver$0.setTextColor(context.getResources().getColor(i));
    }

    public static final Drawable getImage(ImageView receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.getDrawable();
    }

    public static final void setImage(ImageView receiver$0, Drawable drawable) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setImageDrawable(drawable);
    }
}
