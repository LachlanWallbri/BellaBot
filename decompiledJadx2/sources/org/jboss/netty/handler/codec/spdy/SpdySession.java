package org.jboss.netty.handler.codec.spdy;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import org.jboss.netty.channel.MessageEvent;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public final class SpdySession {
    private static final SpdyProtocolException STREAM_CLOSED = new SpdyProtocolException("Stream closed");
    private final Map<Integer, StreamState> activeStreams = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public int numActiveStreams() {
        return this.activeStreams.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean noActiveStreams() {
        return this.activeStreams.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isActiveStream(int i) {
        return this.activeStreams.containsKey(new Integer(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<Integer> getActiveStreams() {
        TreeSet treeSet = new TreeSet(new PriorityComparator());
        treeSet.addAll(this.activeStreams.keySet());
        return treeSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void acceptStream(int i, byte b, boolean z, boolean z2, int i2, int i3) {
        if (z && z2) {
            return;
        }
        this.activeStreams.put(new Integer(i), new StreamState(b, z, z2, i2, i3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeStream(int i) {
        Integer num = new Integer(i);
        StreamState streamState = this.activeStreams.get(num);
        this.activeStreams.remove(num);
        if (streamState != null) {
            for (MessageEvent removePendingWrite = streamState.removePendingWrite(); removePendingWrite != null; removePendingWrite = streamState.removePendingWrite()) {
                removePendingWrite.getFuture().setFailure(STREAM_CLOSED);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isRemoteSideClosed(int i) {
        StreamState streamState = this.activeStreams.get(new Integer(i));
        return streamState == null || streamState.isRemoteSideClosed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void closeRemoteSide(int i) {
        Integer num = new Integer(i);
        StreamState streamState = this.activeStreams.get(num);
        if (streamState != null) {
            streamState.closeRemoteSide();
            if (streamState.isLocalSideClosed()) {
                this.activeStreams.remove(num);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isLocalSideClosed(int i) {
        StreamState streamState = this.activeStreams.get(new Integer(i));
        return streamState == null || streamState.isLocalSideClosed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void closeLocalSide(int i) {
        Integer num = new Integer(i);
        StreamState streamState = this.activeStreams.get(num);
        if (streamState != null) {
            streamState.closeLocalSide();
            if (streamState.isRemoteSideClosed()) {
                this.activeStreams.remove(num);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasReceivedReply(int i) {
        StreamState streamState = this.activeStreams.get(new Integer(i));
        return streamState != null && streamState.hasReceivedReply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void receivedReply(int i) {
        StreamState streamState = this.activeStreams.get(new Integer(i));
        if (streamState != null) {
            streamState.receivedReply();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSendWindowSize(int i) {
        StreamState streamState = this.activeStreams.get(new Integer(i));
        if (streamState != null) {
            return streamState.getSendWindowSize();
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int updateSendWindowSize(int i, int i2) {
        StreamState streamState = this.activeStreams.get(new Integer(i));
        if (streamState != null) {
            return streamState.updateSendWindowSize(i2);
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int updateReceiveWindowSize(int i, int i2) {
        StreamState streamState = this.activeStreams.get(new Integer(i));
        if (i2 > 0) {
            streamState.setReceiveWindowSizeLowerBound(0);
        }
        if (streamState != null) {
            return streamState.updateReceiveWindowSize(i2);
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getReceiveWindowSizeLowerBound(int i) {
        StreamState streamState = this.activeStreams.get(new Integer(i));
        if (streamState != null) {
            return streamState.getReceiveWindowSizeLowerBound();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateAllReceiveWindowSizes(int i) {
        for (StreamState streamState : this.activeStreams.values()) {
            streamState.updateReceiveWindowSize(i);
            if (i < 0) {
                streamState.setReceiveWindowSizeLowerBound(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean putPendingWrite(int i, MessageEvent messageEvent) {
        StreamState streamState = this.activeStreams.get(new Integer(i));
        return streamState != null && streamState.putPendingWrite(messageEvent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageEvent getPendingWrite(int i) {
        StreamState streamState = this.activeStreams.get(new Integer(i));
        if (streamState != null) {
            return streamState.getPendingWrite();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageEvent removePendingWrite(int i) {
        StreamState streamState = this.activeStreams.get(new Integer(i));
        if (streamState != null) {
            return streamState.removePendingWrite();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static final class StreamState {
        private volatile boolean localSideClosed;
        private final ConcurrentLinkedQueue<MessageEvent> pendingWriteQueue = new ConcurrentLinkedQueue<>();
        private final byte priority;
        private final AtomicInteger receiveWindowSize;
        private volatile int receiveWindowSizeLowerBound;
        private boolean receivedReply;
        private volatile boolean remoteSideClosed;
        private final AtomicInteger sendWindowSize;

        StreamState(byte b, boolean z, boolean z2, int i, int i2) {
            this.priority = b;
            this.remoteSideClosed = z;
            this.localSideClosed = z2;
            this.sendWindowSize = new AtomicInteger(i);
            this.receiveWindowSize = new AtomicInteger(i2);
        }

        byte getPriority() {
            return this.priority;
        }

        boolean isRemoteSideClosed() {
            return this.remoteSideClosed;
        }

        void closeRemoteSide() {
            this.remoteSideClosed = true;
        }

        boolean isLocalSideClosed() {
            return this.localSideClosed;
        }

        void closeLocalSide() {
            this.localSideClosed = true;
        }

        boolean hasReceivedReply() {
            return this.receivedReply;
        }

        void receivedReply() {
            this.receivedReply = true;
        }

        int getSendWindowSize() {
            return this.sendWindowSize.get();
        }

        int updateSendWindowSize(int i) {
            return this.sendWindowSize.addAndGet(i);
        }

        int updateReceiveWindowSize(int i) {
            return this.receiveWindowSize.addAndGet(i);
        }

        int getReceiveWindowSizeLowerBound() {
            return this.receiveWindowSizeLowerBound;
        }

        void setReceiveWindowSizeLowerBound(int i) {
            this.receiveWindowSizeLowerBound = i;
        }

        boolean putPendingWrite(MessageEvent messageEvent) {
            return this.pendingWriteQueue.offer(messageEvent);
        }

        MessageEvent getPendingWrite() {
            return this.pendingWriteQueue.peek();
        }

        MessageEvent removePendingWrite() {
            return this.pendingWriteQueue.poll();
        }
    }

    /* loaded from: classes7.dex */
    private final class PriorityComparator implements Comparator<Integer> {
        PriorityComparator() {
        }

        @Override // java.util.Comparator
        public int compare(Integer num, Integer num2) {
            return ((StreamState) SpdySession.this.activeStreams.get(num)).getPriority() - ((StreamState) SpdySession.this.activeStreams.get(num2)).getPriority();
        }
    }
}
