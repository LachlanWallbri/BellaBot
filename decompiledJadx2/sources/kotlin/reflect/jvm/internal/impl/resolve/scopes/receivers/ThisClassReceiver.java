package kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers;

import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: ImplicitClassReceiver.kt */
/* loaded from: classes2.dex */
public interface ThisClassReceiver extends ReceiverValue {
    ClassDescriptor getClassDescriptor();
}
