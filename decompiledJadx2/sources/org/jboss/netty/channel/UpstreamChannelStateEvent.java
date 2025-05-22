package org.jboss.netty.channel;

/* loaded from: classes7.dex */
public class UpstreamChannelStateEvent implements ChannelStateEvent {
    private final Channel channel;
    private final ChannelState state;
    private final Object value;

    public UpstreamChannelStateEvent(Channel channel, ChannelState channelState, Object obj) {
        if (channel == null) {
            throw new NullPointerException("channel");
        }
        if (channelState == null) {
            throw new NullPointerException("state");
        }
        this.channel = channel;
        this.state = channelState;
        this.value = obj;
    }

    @Override // org.jboss.netty.channel.ChannelEvent
    public Channel getChannel() {
        return this.channel;
    }

    @Override // org.jboss.netty.channel.ChannelEvent
    public ChannelFuture getFuture() {
        return Channels.succeededFuture(getChannel());
    }

    @Override // org.jboss.netty.channel.ChannelStateEvent
    public ChannelState getState() {
        return this.state;
    }

    @Override // org.jboss.netty.channel.ChannelStateEvent
    public Object getValue() {
        return this.value;
    }

    public String toString() {
        String obj = getChannel().toString();
        StringBuilder sb = new StringBuilder(obj.length() + 64);
        sb.append(obj);
        int i = C86791.$SwitchMap$org$jboss$netty$channel$ChannelState[getState().ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        sb.append(" INTEREST_CHANGED");
                    } else {
                        sb.append(getState().name());
                        sb.append(": ");
                        sb.append(getValue());
                    }
                } else if (getValue() != null) {
                    sb.append(" CONNECTED: ");
                    sb.append(getValue());
                } else {
                    sb.append(" DISCONNECTED");
                }
            } else if (getValue() != null) {
                sb.append(" BOUND: ");
                sb.append(getValue());
            } else {
                sb.append(" UNBOUND");
            }
        } else if (Boolean.TRUE.equals(getValue())) {
            sb.append(" OPEN");
        } else {
            sb.append(" CLOSED");
        }
        return sb.toString();
    }

    /* renamed from: org.jboss.netty.channel.UpstreamChannelStateEvent$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C86791 {
        static final /* synthetic */ int[] $SwitchMap$org$jboss$netty$channel$ChannelState = new int[ChannelState.values().length];

        static {
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.BOUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.CONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.INTEREST_OPS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
