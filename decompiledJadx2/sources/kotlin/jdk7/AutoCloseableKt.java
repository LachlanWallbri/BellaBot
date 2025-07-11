package kotlin.jdk7;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: AutoCloseable.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0001\u001a8\u0010\u0005\u001a\u0002H\u0006\"\n\b\u0000\u0010\u0007*\u0004\u0018\u00010\u0002\"\u0004\b\u0001\u0010\u0006*\u0002H\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u00060\tH\u0087\b¢\u0006\u0002\u0010\n¨\u0006\u000b"}, m3961d2 = {"closeFinally", "", "Ljava/lang/AutoCloseable;", "cause", "", "use", "R", ExifInterface.GPS_DIRECTION_TRUE, "block", "Lkotlin/Function1;", "(Ljava/lang/AutoCloseable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib-jdk7"}, m3962k = 2, m3963mv = {1, 1, 16}, m3964pn = "kotlin")
/* loaded from: classes2.dex */
public final class AutoCloseableKt {
    private static final <T extends AutoCloseable, R> R use(T t, Function1<? super T, ? extends R> function1) {
        Throwable th = (Throwable) null;
        try {
            R invoke = function1.invoke(t);
            InlineMarker.finallyStart(1);
            closeFinally(t, th);
            InlineMarker.finallyEnd(1);
            return invoke;
        } finally {
        }
    }

    public static final void closeFinally(AutoCloseable autoCloseable, Throwable th) {
        if (autoCloseable == null) {
            return;
        }
        if (th == null) {
            autoCloseable.close();
            return;
        }
        try {
            autoCloseable.close();
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
    }
}
