package kotlin.text;

import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: StringNumberConversionsJVM.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, m3961d2 = {"Lkotlin/text/ScreenFloatValueRegEx;", "", "()V", ES6Iterator.VALUE_PROPERTY, "Lkotlin/text/Regex;", "kotlin-stdlib"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
final class ScreenFloatValueRegEx {
    public static final ScreenFloatValueRegEx INSTANCE = new ScreenFloatValueRegEx();
    public static final Regex value = new Regex("[\\x00-\\x20]*[+-]?(NaN|Infinity|((" + ("((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))[pP][+-]?(\\p{Digit}+))") + ")[fFdD]?))[\\x00-\\x20]*");

    private ScreenFloatValueRegEx() {
    }
}
