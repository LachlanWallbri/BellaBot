package io.grpc.internal;

import com.google.common.base.Preconditions;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class AtomicBackoff {
    private static final Logger log = Logger.getLogger(AtomicBackoff.class.getName());
    private final String name;
    private final AtomicLong value = new AtomicLong();

    public AtomicBackoff(String str, long j) {
        Preconditions.checkArgument(j > 0, "value must be positive");
        this.name = str;
        this.value.set(j);
    }

    public State getState() {
        return new State(this.value.get());
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class State {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final long savedValue;

        private State(long j) {
            this.savedValue = j;
        }

        public long get() {
            return this.savedValue;
        }

        public void backoff() {
            long j = this.savedValue;
            long max = Math.max(2 * j, j);
            if (AtomicBackoff.this.value.compareAndSet(this.savedValue, max)) {
                AtomicBackoff.log.log(Level.WARNING, "Increased {0} to {1}", new Object[]{AtomicBackoff.this.name, Long.valueOf(max)});
            }
        }
    }
}
