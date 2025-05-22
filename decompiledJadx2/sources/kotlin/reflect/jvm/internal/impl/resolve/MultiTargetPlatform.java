package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: MultiTargetPlatform.kt */
/* loaded from: classes2.dex */
public abstract class MultiTargetPlatform implements Comparable<MultiTargetPlatform> {
    public static final Companion Companion = new Companion(null);
    public static final ModuleDescriptor.Capability<MultiTargetPlatform> CAPABILITY = new ModuleDescriptor.Capability<>("MULTI_TARGET_PLATFORM");

    private MultiTargetPlatform() {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* compiled from: MultiTargetPlatform.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
