package androidx.core.os;

import androidx.core.util.ObjectsCompat;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class OperationCanceledException extends RuntimeException {
    public OperationCanceledException() {
        this(null);
    }

    public OperationCanceledException(String str) {
        super(ObjectsCompat.toString(str, "The operation has been canceled."));
    }
}
