package kotlin;

import androidx.exifinterface.media.ExifInterface;
import kotlin.jvm.functions.Function0;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Preconditions.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0001\n\u0002\b\u0004\u001a\u001c\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a/\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u0001H\bH\u0087\b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\t\u001a=\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u0001H\b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\n\u001a\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0087\b\u001a\u001c\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a*\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a/\u0010\u000f\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u0001H\bH\u0087\b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\t\u001a=\u0010\u000f\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u0001H\b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\n¨\u0006\u0010"}, m3961d2 = {"check", "", ES6Iterator.VALUE_PROPERTY, "", "lazyMessage", "Lkotlin/Function0;", "", "checkNotNull", ExifInterface.GPS_DIRECTION_TRUE, "(Ljava/lang/Object;)Ljava/lang/Object;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "error", "", "message", "require", "requireNotNull", "kotlin-stdlib"}, m3962k = 5, m3963mv = {1, 1, 16}, m3965xi = 1, m3966xs = "kotlin/PreconditionsKt")
/* loaded from: classes2.dex */
class PreconditionsKt__PreconditionsKt extends PreconditionsKt__AssertionsJVMKt {
    private static final void require(boolean z) {
        if (!z) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    private static final void require(boolean z, Function0<? extends Object> function0) {
        if (!z) {
            throw new IllegalArgumentException(function0.invoke().toString());
        }
    }

    private static final <T> T requireNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    private static final <T> T requireNotNull(T t, Function0<? extends Object> function0) {
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException(function0.invoke().toString());
    }

    private static final void check(boolean z) {
        if (!z) {
            throw new IllegalStateException("Check failed.".toString());
        }
    }

    private static final void check(boolean z, Function0<? extends Object> function0) {
        if (!z) {
            throw new IllegalStateException(function0.invoke().toString());
        }
    }

    private static final <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    private static final <T> T checkNotNull(T t, Function0<? extends Object> function0) {
        if (t != null) {
            return t;
        }
        throw new IllegalStateException(function0.invoke().toString());
    }

    private static final Void error(Object obj) {
        throw new IllegalStateException(obj.toString());
    }
}
