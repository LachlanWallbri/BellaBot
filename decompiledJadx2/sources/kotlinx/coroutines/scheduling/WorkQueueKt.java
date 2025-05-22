package kotlinx.coroutines.scheduling;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: WorkQueue.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m3961d2 = {"BUFFER_CAPACITY", "", "BUFFER_CAPACITY_BASE", "MASK", "NOTHING_TO_STEAL", "", "TASK_STOLEN", "kotlinx-coroutines-core"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class WorkQueueKt {
    public static final int BUFFER_CAPACITY = 128;
    public static final int BUFFER_CAPACITY_BASE = 7;
    public static final int MASK = 127;
    public static final long NOTHING_TO_STEAL = -2;
    public static final long TASK_STOLEN = -1;
}
