package io.netty.handler.codec.http2;

import io.netty.handler.codec.http2.Http2Connection;
import io.netty.handler.codec.http2.Http2Stream;
import io.netty.handler.codec.http2.StreamByteDistributor;
import io.netty.util.collection.IntCollections;
import io.netty.util.collection.IntObjectHashMap;
import io.netty.util.collection.IntObjectMap;
import io.netty.util.internal.DefaultPriorityQueue;
import io.netty.util.internal.EmptyPriorityQueue;
import io.netty.util.internal.MathUtil;
import io.netty.util.internal.PriorityQueue;
import io.netty.util.internal.PriorityQueueNode;
import io.netty.util.internal.SystemPropertyUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class WeightedFairQueueByteDistributor implements StreamByteDistributor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int DEFAULT_MAX_STATE_ONLY_SIZE = 5;
    static final int INITIAL_CHILDREN_MAP_SIZE = Math.max(1, SystemPropertyUtil.getInt("io.netty.http2.childrenMapSize", 2));
    private int allocationQuantum;
    private final Http2Connection connection;
    private final State connectionState;
    private final int maxStateOnlySize;
    private final Http2Connection.PropertyKey stateKey;
    private final IntObjectMap<State> stateOnlyMap;
    private final PriorityQueue<State> stateOnlyRemovalQueue;

    public WeightedFairQueueByteDistributor(Http2Connection http2Connection) {
        this(http2Connection, 5);
    }

    public WeightedFairQueueByteDistributor(Http2Connection http2Connection, int i) {
        this.allocationQuantum = 1024;
        if (i < 0) {
            throw new IllegalArgumentException("maxStateOnlySize: " + i + " (expected: >0)");
        }
        if (i == 0) {
            this.stateOnlyMap = IntCollections.emptyMap();
            this.stateOnlyRemovalQueue = EmptyPriorityQueue.instance();
        } else {
            this.stateOnlyMap = new IntObjectHashMap(i);
            this.stateOnlyRemovalQueue = new DefaultPriorityQueue(StateOnlyComparator.INSTANCE, i + 2);
        }
        this.maxStateOnlySize = i;
        this.connection = http2Connection;
        this.stateKey = http2Connection.newKey();
        Http2Stream connectionStream = http2Connection.connectionStream();
        Http2Connection.PropertyKey propertyKey = this.stateKey;
        State state = new State(this, connectionStream, 16);
        this.connectionState = state;
        connectionStream.setProperty(propertyKey, state);
        http2Connection.addListener(new Http2ConnectionAdapter() { // from class: io.netty.handler.codec.http2.WeightedFairQueueByteDistributor.1
            @Override // io.netty.handler.codec.http2.Http2ConnectionAdapter, io.netty.handler.codec.http2.Http2Connection.Listener
            public void onStreamAdded(Http2Stream http2Stream) {
                State state2 = (State) WeightedFairQueueByteDistributor.this.stateOnlyMap.remove(http2Stream.mo3940id());
                if (state2 != null) {
                    WeightedFairQueueByteDistributor.this.stateOnlyRemovalQueue.removeTyped(state2);
                    state2.stream = http2Stream;
                } else {
                    state2 = new State(WeightedFairQueueByteDistributor.this, http2Stream);
                    ArrayList arrayList = new ArrayList(1);
                    WeightedFairQueueByteDistributor.this.connectionState.takeChild(state2, false, arrayList);
                    WeightedFairQueueByteDistributor.this.notifyParentChanged(arrayList);
                }
                int i2 = C72462.$SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[http2Stream.state().ordinal()];
                if (i2 == 1 || i2 == 2) {
                    state2.setStreamReservedOrActivated();
                }
                http2Stream.setProperty(WeightedFairQueueByteDistributor.this.stateKey, state2);
            }

            @Override // io.netty.handler.codec.http2.Http2ConnectionAdapter, io.netty.handler.codec.http2.Http2Connection.Listener
            public void onStreamActive(Http2Stream http2Stream) {
                WeightedFairQueueByteDistributor.this.state(http2Stream).setStreamReservedOrActivated();
            }

            @Override // io.netty.handler.codec.http2.Http2ConnectionAdapter, io.netty.handler.codec.http2.Http2Connection.Listener
            public void onStreamClosed(Http2Stream http2Stream) {
                WeightedFairQueueByteDistributor.this.state(http2Stream).close();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // io.netty.handler.codec.http2.Http2ConnectionAdapter, io.netty.handler.codec.http2.Http2Connection.Listener
            public void onStreamRemoved(Http2Stream http2Stream) {
                State state2 = WeightedFairQueueByteDistributor.this.state(http2Stream);
                state2.stream = null;
                if (WeightedFairQueueByteDistributor.this.maxStateOnlySize != 0) {
                    if (WeightedFairQueueByteDistributor.this.stateOnlyRemovalQueue.size() == WeightedFairQueueByteDistributor.this.maxStateOnlySize) {
                        State state3 = (State) WeightedFairQueueByteDistributor.this.stateOnlyRemovalQueue.peek();
                        if (StateOnlyComparator.INSTANCE.compare(state3, state2) < 0) {
                            WeightedFairQueueByteDistributor.this.stateOnlyRemovalQueue.poll();
                            state3.parent.removeChild(state3);
                            WeightedFairQueueByteDistributor.this.stateOnlyMap.remove(state3.streamId);
                        } else {
                            state2.parent.removeChild(state2);
                            return;
                        }
                    }
                    WeightedFairQueueByteDistributor.this.stateOnlyRemovalQueue.add(state2);
                    WeightedFairQueueByteDistributor.this.stateOnlyMap.put(state2.streamId, (int) state2);
                    return;
                }
                state2.parent.removeChild(state2);
            }
        });
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* renamed from: io.netty.handler.codec.http2.WeightedFairQueueByteDistributor$2 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C72462 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State;

        static {
            int[] iArr = new int[Http2Stream.State.values().length];
            $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State = iArr;
            try {
                iArr[Http2Stream.State.RESERVED_REMOTE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[Http2Stream.State.RESERVED_LOCAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // io.netty.handler.codec.http2.StreamByteDistributor
    public void updateStreamableBytes(StreamByteDistributor.StreamState streamState) {
        state(streamState.stream()).updateStreamableBytes(Http2CodecUtil.streamableBytes(streamState), streamState.hasFrame() && streamState.windowSize() >= 0);
    }

    @Override // io.netty.handler.codec.http2.StreamByteDistributor
    public void updateDependencyTree(int i, int i2, short s, boolean z) {
        ArrayList arrayList;
        State state = state(i);
        if (state == null) {
            if (this.maxStateOnlySize == 0) {
                return;
            }
            state = new State(this, i);
            this.stateOnlyRemovalQueue.add(state);
            this.stateOnlyMap.put(i, (int) state);
        }
        State state2 = state(i2);
        if (state2 == null) {
            if (this.maxStateOnlySize == 0) {
                return;
            }
            state2 = new State(this, i2);
            this.stateOnlyRemovalQueue.add(state2);
            this.stateOnlyMap.put(i2, (int) state2);
            ArrayList arrayList2 = new ArrayList(1);
            this.connectionState.takeChild(state2, false, arrayList2);
            notifyParentChanged(arrayList2);
        }
        if (state.activeCountForTree != 0 && state.parent != null) {
            state.parent.totalQueuedWeights += s - state.weight;
        }
        state.weight = s;
        if (state2 != state.parent || (z && state2.children.size() != 1)) {
            if (state2.isDescendantOf(state)) {
                arrayList = new ArrayList((z ? state2.children.size() : 0) + 2);
                state.parent.takeChild(state2, false, arrayList);
            } else {
                arrayList = new ArrayList((z ? state2.children.size() : 0) + 1);
            }
            state2.takeChild(state, z, arrayList);
            notifyParentChanged(arrayList);
        }
        while (this.stateOnlyRemovalQueue.size() > this.maxStateOnlySize) {
            State poll = this.stateOnlyRemovalQueue.poll();
            poll.parent.removeChild(poll);
            this.stateOnlyMap.remove(poll.streamId);
        }
    }

    @Override // io.netty.handler.codec.http2.StreamByteDistributor
    public boolean distribute(int i, StreamByteDistributor.Writer writer) throws Http2Exception {
        if (this.connectionState.activeCountForTree == 0) {
            return false;
        }
        while (true) {
            int i2 = this.connectionState.activeCountForTree;
            i -= distributeToChildren(i, writer, this.connectionState);
            if (this.connectionState.activeCountForTree == 0 || (i <= 0 && i2 == this.connectionState.activeCountForTree)) {
                break;
            }
        }
        return this.connectionState.activeCountForTree != 0;
    }

    public void allocationQuantum(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("allocationQuantum must be > 0");
        }
        this.allocationQuantum = i;
    }

    private int distribute(int i, StreamByteDistributor.Writer writer, State state) throws Http2Exception {
        if (state.isActive()) {
            int min = Math.min(i, state.streamableBytes);
            state.write(min, writer);
            if (min == 0 && i != 0) {
                state.updateStreamableBytes(state.streamableBytes, false);
            }
            return min;
        }
        return distributeToChildren(i, writer, state);
    }

    private int distributeToChildren(int i, StreamByteDistributor.Writer writer, State state) throws Http2Exception {
        long j = state.totalQueuedWeights;
        State pollPseudoTimeQueue = state.pollPseudoTimeQueue();
        State peekPseudoTimeQueue = state.peekPseudoTimeQueue();
        pollPseudoTimeQueue.setDistributing();
        if (peekPseudoTimeQueue != null) {
            try {
                i = Math.min(i, (int) Math.min((((peekPseudoTimeQueue.pseudoTimeToWrite - pollPseudoTimeQueue.pseudoTimeToWrite) * pollPseudoTimeQueue.weight) / j) + this.allocationQuantum, 2147483647L));
            } finally {
                pollPseudoTimeQueue.unsetDistributing();
                if (pollPseudoTimeQueue.activeCountForTree != 0) {
                    state.offerPseudoTimeQueue(pollPseudoTimeQueue);
                }
            }
        }
        int distribute = distribute(i, writer, pollPseudoTimeQueue);
        state.pseudoTime += distribute;
        pollPseudoTimeQueue.updatePseudoTime(state, distribute, j);
        return distribute;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public State state(Http2Stream http2Stream) {
        return (State) http2Stream.getProperty(this.stateKey);
    }

    private State state(int i) {
        Http2Stream stream = this.connection.stream(i);
        return stream != null ? state(stream) : this.stateOnlyMap.get(i);
    }

    boolean isChild(int i, int i2, short s) {
        State state = state(i2);
        if (state.children.containsKey(i)) {
            State state2 = state(i);
            if (state2.parent == state && state2.weight == s) {
                return true;
            }
        }
        return false;
    }

    int numChildren(int i) {
        State state = state(i);
        if (state == null) {
            return 0;
        }
        return state.children.size();
    }

    void notifyParentChanged(List<ParentChangedEvent> list) {
        for (int i = 0; i < list.size(); i++) {
            ParentChangedEvent parentChangedEvent = list.get(i);
            this.stateOnlyRemovalQueue.priorityChanged(parentChangedEvent.state);
            if (parentChangedEvent.state.parent != null && parentChangedEvent.state.activeCountForTree != 0) {
                parentChangedEvent.state.parent.offerAndInitializePseudoTime(parentChangedEvent.state);
                parentChangedEvent.state.parent.activeCountChangeForTree(parentChangedEvent.state.activeCountForTree);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    private static final class StateOnlyComparator implements Comparator<State>, Serializable {
        static final StateOnlyComparator INSTANCE = new StateOnlyComparator();
        private static final long serialVersionUID = -4806936913002105966L;

        private StateOnlyComparator() {
        }

        @Override // java.util.Comparator
        public int compare(State state, State state2) {
            boolean wasStreamReservedOrActivated = state.wasStreamReservedOrActivated();
            if (wasStreamReservedOrActivated != state2.wasStreamReservedOrActivated()) {
                return wasStreamReservedOrActivated ? -1 : 1;
            }
            int i = state2.dependencyTreeDepth - state.dependencyTreeDepth;
            return i != 0 ? i : state.streamId - state2.streamId;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    private static final class StatePseudoTimeComparator implements Comparator<State>, Serializable {
        static final StatePseudoTimeComparator INSTANCE = new StatePseudoTimeComparator();
        private static final long serialVersionUID = -1437548640227161828L;

        private StatePseudoTimeComparator() {
        }

        @Override // java.util.Comparator
        public int compare(State state, State state2) {
            return MathUtil.compare(state.pseudoTimeToWrite, state2.pseudoTimeToWrite);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public final class State implements PriorityQueueNode {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final byte STATE_IS_ACTIVE = 1;
        private static final byte STATE_IS_DISTRIBUTING = 2;
        private static final byte STATE_STREAM_ACTIVATED = 4;
        int activeCountForTree;
        IntObjectMap<State> children;
        int dependencyTreeDepth;
        private byte flags;
        State parent;
        long pseudoTime;
        private final PriorityQueue<State> pseudoTimeQueue;
        private int pseudoTimeQueueIndex;
        long pseudoTimeToWrite;
        private int stateOnlyQueueIndex;
        Http2Stream stream;
        final int streamId;
        int streamableBytes;
        long totalQueuedWeights;
        short weight;

        State(WeightedFairQueueByteDistributor weightedFairQueueByteDistributor, int i) {
            this(i, null, 0);
        }

        State(WeightedFairQueueByteDistributor weightedFairQueueByteDistributor, Http2Stream http2Stream) {
            this(weightedFairQueueByteDistributor, http2Stream, 0);
        }

        State(WeightedFairQueueByteDistributor weightedFairQueueByteDistributor, Http2Stream http2Stream, int i) {
            this(http2Stream.mo3940id(), http2Stream, i);
        }

        State(int i, Http2Stream http2Stream, int i2) {
            this.children = IntCollections.emptyMap();
            this.pseudoTimeQueueIndex = -1;
            this.stateOnlyQueueIndex = -1;
            this.weight = (short) 16;
            this.stream = http2Stream;
            this.streamId = i;
            this.pseudoTimeQueue = new DefaultPriorityQueue(StatePseudoTimeComparator.INSTANCE, i2);
        }

        boolean isDescendantOf(State state) {
            for (State state2 = this.parent; state2 != null; state2 = state2.parent) {
                if (state2 == state) {
                    return true;
                }
            }
            return false;
        }

        void takeChild(State state, boolean z, List<ParentChangedEvent> list) {
            takeChild(null, state, z, list);
        }

        void takeChild(Iterator<IntObjectMap.PrimitiveEntry<State>> it, State state, boolean z, List<ParentChangedEvent> list) {
            State state2 = state.parent;
            if (state2 != this) {
                list.add(new ParentChangedEvent(state, state2));
                state.setParent(this);
                if (it != null) {
                    it.remove();
                } else if (state2 != null) {
                    state2.children.remove(state.streamId);
                }
                initChildrenIfEmpty();
                this.children.put(state.streamId, (int) state);
            }
            if (!z || this.children.isEmpty()) {
                return;
            }
            Iterator<IntObjectMap.PrimitiveEntry<State>> it2 = removeAllChildrenExcept(state).entries().iterator();
            while (it2.hasNext()) {
                state.takeChild(it2, it2.next().value(), false, list);
            }
        }

        void removeChild(State state) {
            if (this.children.remove(state.streamId) != null) {
                ArrayList arrayList = new ArrayList(state.children.size() + 1);
                arrayList.add(new ParentChangedEvent(state, state.parent));
                state.setParent(null);
                Iterator<IntObjectMap.PrimitiveEntry<State>> it = state.children.entries().iterator();
                while (it.hasNext()) {
                    takeChild(it, it.next().value(), false, arrayList);
                }
                WeightedFairQueueByteDistributor.this.notifyParentChanged(arrayList);
            }
        }

        private IntObjectMap<State> removeAllChildrenExcept(State state) {
            State remove = this.children.remove(state.streamId);
            IntObjectMap<State> intObjectMap = this.children;
            initChildren();
            if (remove != null) {
                this.children.put(remove.streamId, (int) remove);
            }
            return intObjectMap;
        }

        private void setParent(State state) {
            State state2;
            if (this.activeCountForTree != 0 && (state2 = this.parent) != null) {
                state2.removePseudoTimeQueue(this);
                this.parent.activeCountChangeForTree(-this.activeCountForTree);
            }
            this.parent = state;
            this.dependencyTreeDepth = state == null ? Integer.MAX_VALUE : state.dependencyTreeDepth + 1;
        }

        private void initChildrenIfEmpty() {
            if (this.children == IntCollections.emptyMap()) {
                initChildren();
            }
        }

        private void initChildren() {
            this.children = new IntObjectHashMap(WeightedFairQueueByteDistributor.INITIAL_CHILDREN_MAP_SIZE);
        }

        void write(int i, StreamByteDistributor.Writer writer) throws Http2Exception {
            try {
                writer.write(this.stream, i);
            } catch (Throwable th) {
                throw Http2Exception.connectionError(Http2Error.INTERNAL_ERROR, th, "byte distribution write error", new Object[0]);
            }
        }

        void activeCountChangeForTree(int i) {
            int i2 = this.activeCountForTree + i;
            this.activeCountForTree = i2;
            State state = this.parent;
            if (state != null) {
                if (i2 == 0) {
                    state.removePseudoTimeQueue(this);
                } else if (i2 == i && !isDistributing()) {
                    this.parent.offerAndInitializePseudoTime(this);
                }
                this.parent.activeCountChangeForTree(i);
            }
        }

        void updateStreamableBytes(int i, boolean z) {
            if (isActive() != z) {
                if (z) {
                    activeCountChangeForTree(1);
                    setActive();
                } else {
                    activeCountChangeForTree(-1);
                    unsetActive();
                }
            }
            this.streamableBytes = i;
        }

        void updatePseudoTime(State state, int i, long j) {
            this.pseudoTimeToWrite = Math.min(this.pseudoTimeToWrite, state.pseudoTime) + ((i * j) / this.weight);
        }

        void offerAndInitializePseudoTime(State state) {
            state.pseudoTimeToWrite = this.pseudoTime;
            offerPseudoTimeQueue(state);
        }

        void offerPseudoTimeQueue(State state) {
            this.pseudoTimeQueue.offer(state);
            this.totalQueuedWeights += state.weight;
        }

        State pollPseudoTimeQueue() {
            State poll = this.pseudoTimeQueue.poll();
            this.totalQueuedWeights -= poll.weight;
            return poll;
        }

        void removePseudoTimeQueue(State state) {
            if (this.pseudoTimeQueue.removeTyped(state)) {
                this.totalQueuedWeights -= state.weight;
            }
        }

        State peekPseudoTimeQueue() {
            return this.pseudoTimeQueue.peek();
        }

        void close() {
            updateStreamableBytes(0, false);
            this.stream = null;
        }

        boolean wasStreamReservedOrActivated() {
            return (this.flags & 4) != 0;
        }

        void setStreamReservedOrActivated() {
            this.flags = (byte) (this.flags | 4);
        }

        boolean isActive() {
            return (this.flags & 1) != 0;
        }

        private void setActive() {
            this.flags = (byte) (this.flags | 1);
        }

        private void unsetActive() {
            this.flags = (byte) (this.flags & (-2));
        }

        boolean isDistributing() {
            return (this.flags & 2) != 0;
        }

        void setDistributing() {
            this.flags = (byte) (this.flags | 2);
        }

        void unsetDistributing() {
            this.flags = (byte) (this.flags & (-3));
        }

        @Override // io.netty.util.internal.PriorityQueueNode
        public int priorityQueueIndex(DefaultPriorityQueue<?> defaultPriorityQueue) {
            return defaultPriorityQueue == WeightedFairQueueByteDistributor.this.stateOnlyRemovalQueue ? this.stateOnlyQueueIndex : this.pseudoTimeQueueIndex;
        }

        @Override // io.netty.util.internal.PriorityQueueNode
        public void priorityQueueIndex(DefaultPriorityQueue<?> defaultPriorityQueue, int i) {
            if (defaultPriorityQueue == WeightedFairQueueByteDistributor.this.stateOnlyRemovalQueue) {
                this.stateOnlyQueueIndex = i;
            } else {
                this.pseudoTimeQueueIndex = i;
            }
        }

        public String toString() {
            int i = this.activeCountForTree;
            if (i <= 0) {
                i = 1;
            }
            StringBuilder sb = new StringBuilder(i * 256);
            toString(sb);
            return sb.toString();
        }

        private void toString(StringBuilder sb) {
            sb.append("{streamId ");
            sb.append(this.streamId);
            sb.append(" streamableBytes ");
            sb.append(this.streamableBytes);
            sb.append(" activeCountForTree ");
            sb.append(this.activeCountForTree);
            sb.append(" pseudoTimeQueueIndex ");
            sb.append(this.pseudoTimeQueueIndex);
            sb.append(" pseudoTimeToWrite ");
            sb.append(this.pseudoTimeToWrite);
            sb.append(" pseudoTime ");
            sb.append(this.pseudoTime);
            sb.append(" flags ");
            sb.append((int) this.flags);
            sb.append(" pseudoTimeQueue.size() ");
            sb.append(this.pseudoTimeQueue.size());
            sb.append(" stateOnlyQueueIndex ");
            sb.append(this.stateOnlyQueueIndex);
            sb.append(" parent.streamId ");
            State state = this.parent;
            sb.append(state == null ? -1 : state.streamId);
            sb.append("} [");
            if (!this.pseudoTimeQueue.isEmpty()) {
                Iterator it = this.pseudoTimeQueue.iterator();
                while (it.hasNext()) {
                    ((State) it.next()).toString(sb);
                    sb.append(", ");
                }
                sb.setLength(sb.length() - 2);
            }
            sb.append(']');
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static final class ParentChangedEvent {
        final State oldParent;
        final State state;

        ParentChangedEvent(State state, State state2) {
            this.state = state;
            this.oldParent = state2;
        }
    }
}
