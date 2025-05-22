package com.pudutech.mirsdk.mircore.module.speedlevel;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MODE.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0010\b\u0086\u0001\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/module/speedlevel/MODE;", "", "(Ljava/lang/String;I)V", "Default", "Slowest", "Slower", "Slow", "Normal", "Fast", "Faster", "Fastest", "Flyup", "Tortoise", "Laziest", "Lazier", "Lazy", "Companion", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public enum MODE {
    Default,
    Slowest,
    Slower,
    Slow,
    Normal,
    Fast,
    Faster,
    Fastest,
    Flyup,
    Tortoise,
    Laziest,
    Lazier,
    Lazy;


    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MODE.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/module/speedlevel/MODE$Companion;", "", "()V", "check", "", "mode", "", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean check(String mode) {
            Intrinsics.checkParameterIsNotNull(mode, "mode");
            for (MODE mode2 : MODE.values()) {
                if (Intrinsics.areEqual(mode2.name(), mode)) {
                    return true;
                }
            }
            return false;
        }
    }
}
