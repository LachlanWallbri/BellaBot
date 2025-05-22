package io.reactivex.internal.operators.mixed;

import defpackage.C$r8$backportedMethods$utility$Boolean$1$hashCode;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.MaybeSource;
import io.reactivex.Observer;
import io.reactivex.SingleSource;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.operators.maybe.MaybeToObservable;
import io.reactivex.internal.operators.single.SingleToObservable;
import java.util.concurrent.Callable;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
final class ScalarXMapZHelper {
    private ScalarXMapZHelper() {
        throw new IllegalStateException("No instances!");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> boolean tryAsCompletable(Object obj, Function<? super T, ? extends CompletableSource> function, CompletableObserver completableObserver) {
        if (!(obj instanceof Callable)) {
            return false;
        }
        try {
            C$r8$backportedMethods$utility$Boolean$1$hashCode c$r8$backportedMethods$utility$Boolean$1$hashCode = (Object) ((Callable) obj).call();
            CompletableSource completableSource = c$r8$backportedMethods$utility$Boolean$1$hashCode != null ? (CompletableSource) ObjectHelper.requireNonNull(function.apply(c$r8$backportedMethods$utility$Boolean$1$hashCode), "The mapper returned a null CompletableSource") : null;
            if (completableSource == null) {
                EmptyDisposable.complete(completableObserver);
            } else {
                completableSource.subscribe(completableObserver);
            }
            return true;
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, completableObserver);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, R> boolean tryAsMaybe(Object obj, Function<? super T, ? extends MaybeSource<? extends R>> function, Observer<? super R> observer) {
        if (!(obj instanceof Callable)) {
            return false;
        }
        try {
            C$r8$backportedMethods$utility$Boolean$1$hashCode c$r8$backportedMethods$utility$Boolean$1$hashCode = (Object) ((Callable) obj).call();
            MaybeSource maybeSource = c$r8$backportedMethods$utility$Boolean$1$hashCode != null ? (MaybeSource) ObjectHelper.requireNonNull(function.apply(c$r8$backportedMethods$utility$Boolean$1$hashCode), "The mapper returned a null MaybeSource") : null;
            if (maybeSource == null) {
                EmptyDisposable.complete(observer);
            } else {
                maybeSource.subscribe(MaybeToObservable.create(observer));
            }
            return true;
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, observer);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, R> boolean tryAsSingle(Object obj, Function<? super T, ? extends SingleSource<? extends R>> function, Observer<? super R> observer) {
        if (!(obj instanceof Callable)) {
            return false;
        }
        try {
            C$r8$backportedMethods$utility$Boolean$1$hashCode c$r8$backportedMethods$utility$Boolean$1$hashCode = (Object) ((Callable) obj).call();
            SingleSource singleSource = c$r8$backportedMethods$utility$Boolean$1$hashCode != null ? (SingleSource) ObjectHelper.requireNonNull(function.apply(c$r8$backportedMethods$utility$Boolean$1$hashCode), "The mapper returned a null SingleSource") : null;
            if (singleSource == null) {
                EmptyDisposable.complete(observer);
            } else {
                singleSource.subscribe(SingleToObservable.create(observer));
            }
            return true;
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, observer);
            return true;
        }
    }
}
