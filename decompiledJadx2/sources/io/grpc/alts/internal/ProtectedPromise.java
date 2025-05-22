package io.grpc.alts.internal;

import com.google.common.base.Preconditions;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.DefaultChannelPromise;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ProtectedPromise extends DefaultChannelPromise {
    private boolean doneAllocating;
    private int expectedCount;
    private int failureCount;
    private int successfulCount;
    private final List<ChannelPromise> unprotectedPromises;

    public ProtectedPromise(Channel channel, EventExecutor eventExecutor, int i) {
        super(channel, eventExecutor);
        this.unprotectedPromises = new ArrayList(i);
    }

    public void addUnprotectedPromise(ChannelPromise channelPromise) {
        this.unprotectedPromises.add(channelPromise);
    }

    public ChannelPromise newPromise() {
        Preconditions.checkState(!this.doneAllocating, "Done allocating. No more promises can be allocated.");
        this.expectedCount++;
        return this;
    }

    public ChannelPromise doneAllocatingPromises() {
        if (!this.doneAllocating) {
            this.doneAllocating = true;
            if (this.successfulCount == this.expectedCount) {
                trySuccessInternal(null);
                return super.setSuccess((Void) null);
            }
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise
    public boolean tryFailure(Throwable th) {
        if (!awaitingPromises()) {
            return false;
        }
        this.failureCount++;
        if (this.failureCount != 1) {
            return true;
        }
        tryFailureInternal(th);
        return super.tryFailure(th);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.DefaultChannelPromise, io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.channel.ChannelPromise
    public ChannelPromise setFailure(Throwable th) {
        tryFailure(th);
        return this;
    }

    private boolean awaitingPromises() {
        return this.successfulCount + this.failureCount < this.expectedCount;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.DefaultChannelPromise, io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise
    public ChannelPromise setSuccess(Void r1) {
        trySuccess(r1);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise
    public boolean trySuccess(Void r4) {
        if (!awaitingPromises()) {
            return false;
        }
        this.successfulCount++;
        if (this.successfulCount != this.expectedCount || !this.doneAllocating) {
            return true;
        }
        trySuccessInternal(r4);
        return super.trySuccess((ProtectedPromise) r4);
    }

    private void trySuccessInternal(Void r3) {
        for (int i = 0; i < this.unprotectedPromises.size(); i++) {
            this.unprotectedPromises.get(i).trySuccess(r3);
        }
    }

    private void tryFailureInternal(Throwable th) {
        for (int i = 0; i < this.unprotectedPromises.size(); i++) {
            this.unprotectedPromises.get(i).tryFailure(th);
        }
    }
}
