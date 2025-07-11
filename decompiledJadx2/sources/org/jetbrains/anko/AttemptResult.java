package org.jetbrains.anko;

import androidx.exifinterface.media.ExifInterface;
import com.loc.C3898x;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: Helpers.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\u001b\b\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0011\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J,\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00018\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J)\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u0000\"\u0004\b\u0001\u0010\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\u001a0\u001cH\u0086\bJ\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\n8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\n8Æ\u0002¢\u0006\u0006\u001a\u0004\b\r\u0010\fR\u0015\u0010\u0003\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001f"}, m3961d2 = {"Lorg/jetbrains/anko/AttemptResult;", ExifInterface.GPS_DIRECTION_TRUE, "", ES6Iterator.VALUE_PROPERTY, "error", "", "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "getError", "()Ljava/lang/Throwable;", "hasValue", "", "getHasValue", "()Z", "isError", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "copy", "(Ljava/lang/Object;Ljava/lang/Throwable;)Lorg/jetbrains/anko/AttemptResult;", "equals", "other", "hashCode", "", "then", "R", C3898x.f4339h, "Lkotlin/Function1;", "toString", "", "commons_release"}, m3962k = 1, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final /* data */ class AttemptResult<T> {
    private final Throwable error;
    private final T value;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* bridge */ /* synthetic */ AttemptResult copy$default(AttemptResult attemptResult, Object obj, Throwable th, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = attemptResult.value;
        }
        if ((i & 2) != 0) {
            th = attemptResult.error;
        }
        return attemptResult.copy(obj, th);
    }

    public final T component1() {
        return this.value;
    }

    /* renamed from: component2, reason: from getter */
    public final Throwable getError() {
        return this.error;
    }

    public final AttemptResult<T> copy(T value, Throwable error) {
        return new AttemptResult<>(value, error);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AttemptResult)) {
            return false;
        }
        AttemptResult attemptResult = (AttemptResult) other;
        return Intrinsics.areEqual(this.value, attemptResult.value) && Intrinsics.areEqual(this.error, attemptResult.error);
    }

    public int hashCode() {
        T t = this.value;
        int hashCode = (t != null ? t.hashCode() : 0) * 31;
        Throwable th = this.error;
        return hashCode + (th != null ? th.hashCode() : 0);
    }

    public String toString() {
        return "AttemptResult(value=" + this.value + ", error=" + this.error + ")";
    }

    public AttemptResult(T t, Throwable th) {
        this.value = t;
        this.error = th;
    }

    public final Throwable getError() {
        return this.error;
    }

    public final T getValue() {
        return this.value;
    }

    public final boolean isError() {
        return getError() != null;
    }

    public final boolean getHasValue() {
        return getError() == null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> AttemptResult<R> then(Function1<? super T, ? extends R> f) {
        Intrinsics.checkParameterIsNotNull(f, "f");
        if (getError() != null) {
            return this;
        }
        R r = null;
        Throwable th = (Throwable) null;
        try {
            r = f.invoke((Object) getValue());
        } catch (Throwable th2) {
            th = th2;
        }
        return new AttemptResult<>(r, th);
    }
}
