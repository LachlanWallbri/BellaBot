package org.jetbrains.anko;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.loc.C3898x;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: buildSpanned.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\"\u0010\u000f\u001a\u00020\u00102\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\u0002\b\u0014H\u0086\b\u001a.\u0010\u0015\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00172\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\u0002\b\u0014H\u0086\b\u001a\u001a\u0010\u0015\u001a\u00020\u0013*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017\u001a+\u0010\u0015\u001a\u00020\u0013*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u001b\"\u00020\u0017¢\u0006\u0002\u0010\u001c\u001a\u001d\u0010\u001d\u001a\u00020\u0013*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017H\u0086\b\u001a.\u0010\u001d\u001a\u00020\u0013*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u001b\"\u00020\u0017H\u0086\b¢\u0006\u0002\u0010\u001c\u001a\u0015\u0010\u001e\u001a\u00020\u001f*\u00020\u00022\u0006\u0010 \u001a\u00020!H\u0086\b\u001a#\u0010\"\u001a\u00020#*\u00020\u00022\u0014\b\u0004\u0010$\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00130\u0012H\u0086\b\u001a\u0015\u0010&\u001a\u00020'*\u00020\u00022\u0006\u0010 \u001a\u00020!H\u0086\b\u001a\u0015\u0010(\u001a\u00020)*\u00020\u00022\u0006\u0010*\u001a\u00020+H\u0086\b\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0016\u0010\u0005\u001a\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0016\u0010\u0007\u001a\u00020\b*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\n\"\u0016\u0010\u000b\u001a\u00020\f*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006,"}, m3961d2 = {"Bold", "Landroid/text/style/StyleSpan;", "Landroid/text/SpannableStringBuilder;", "getBold", "(Landroid/text/SpannableStringBuilder;)Landroid/text/style/StyleSpan;", "Italic", "getItalic", "Strikethrough", "Landroid/text/style/StrikethroughSpan;", "getStrikethrough", "(Landroid/text/SpannableStringBuilder;)Landroid/text/style/StrikethroughSpan;", "Underline", "Landroid/text/style/UnderlineSpan;", "getUnderline", "(Landroid/text/SpannableStringBuilder;)Landroid/text/style/UnderlineSpan;", "buildSpanned", "Landroid/text/Spanned;", C3898x.f4339h, "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "append", "span", "", "text", "", "spans", "", "(Landroid/text/SpannableStringBuilder;Ljava/lang/CharSequence;[Ljava/lang/Object;)V", "appendln", "backgroundColor", "Landroid/text/style/BackgroundColorSpan;", TypedValues.Custom.S_COLOR, "", "clickable", "Landroid/text/style/ClickableSpan;", "onClick", "Landroid/view/View;", "foregroundColor", "Landroid/text/style/ForegroundColorSpan;", "link", "Landroid/text/style/URLSpan;", "url", "", "commons_release"}, m3962k = 2, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class BuildSpannedKt {
    public static final Spanned buildSpanned(Function1<? super SpannableStringBuilder, Unit> f) {
        Intrinsics.checkParameterIsNotNull(f, "f");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        f.invoke(spannableStringBuilder);
        return spannableStringBuilder;
    }

    public static final StyleSpan getBold(SpannableStringBuilder receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new StyleSpan(1);
    }

    public static final StyleSpan getItalic(SpannableStringBuilder receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new StyleSpan(2);
    }

    public static final UnderlineSpan getUnderline(SpannableStringBuilder receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new UnderlineSpan();
    }

    public static final StrikethroughSpan getStrikethrough(SpannableStringBuilder receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new StrikethroughSpan();
    }

    public static final ForegroundColorSpan foregroundColor(SpannableStringBuilder receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new ForegroundColorSpan(i);
    }

    public static final BackgroundColorSpan backgroundColor(SpannableStringBuilder receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new BackgroundColorSpan(i);
    }

    public static final ClickableSpan clickable(SpannableStringBuilder receiver, final Function1<? super View, Unit> onClick) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        return new ClickableSpan() { // from class: org.jetbrains.anko.BuildSpannedKt$clickable$1
            @Override // android.text.style.ClickableSpan
            public void onClick(View widget) {
                Intrinsics.checkParameterIsNotNull(widget, "widget");
                Function1.this.invoke(widget);
            }
        };
    }

    public static final URLSpan link(SpannableStringBuilder receiver, String url) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(url, "url");
        return new URLSpan(url);
    }

    public static final void append(SpannableStringBuilder receiver, CharSequence text, Object... spans) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(spans, "spans");
        int length = text.length();
        receiver.append(text);
        int length2 = spans.length - 1;
        if (length2 < 0) {
            return;
        }
        int i = 0;
        while (true) {
            receiver.setSpan(spans[i], receiver.length() - length, receiver.length(), 17);
            if (i == length2) {
                return;
            } else {
                i++;
            }
        }
    }

    public static final void append(SpannableStringBuilder receiver, CharSequence text, Object span) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(span, "span");
        int length = text.length();
        receiver.append(text);
        receiver.setSpan(span, receiver.length() - length, receiver.length(), 17);
    }

    public static final SpannableStringBuilder append(SpannableStringBuilder receiver, Object span, Function1<? super SpannableStringBuilder, Unit> f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(span, "span");
        Intrinsics.checkParameterIsNotNull(f, "f");
        SpannableStringBuilder spannableStringBuilder = receiver;
        int length = spannableStringBuilder.length();
        f.invoke(spannableStringBuilder);
        spannableStringBuilder.setSpan(span, length, spannableStringBuilder.length(), 17);
        return spannableStringBuilder;
    }

    public static final void appendln(SpannableStringBuilder receiver, CharSequence text, Object... spans) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(spans, "spans");
        append(receiver, text, Arrays.copyOf(spans, spans.length));
        StringsKt.appendln(receiver);
    }

    public static final void appendln(SpannableStringBuilder receiver, CharSequence text, Object span) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(span, "span");
        append(receiver, text, span);
        StringsKt.appendln(receiver);
    }
}
