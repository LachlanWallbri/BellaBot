package io.netty.channel.udt.nio;

import com.barchart.udt.SocketUDT;
import com.barchart.udt.TypeUDT;
import com.barchart.udt.nio.ChannelUDT;
import com.barchart.udt.nio.KindUDT;
import com.barchart.udt.nio.RendezvousChannelUDT;
import com.barchart.udt.nio.SelectorProviderUDT;
import com.barchart.udt.nio.ServerSocketChannelUDT;
import com.barchart.udt.nio.SocketChannelUDT;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFactory;
import io.netty.channel.udt.UdtChannel;
import io.netty.channel.udt.UdtServerChannel;
import java.io.IOException;
import java.nio.channels.spi.SelectorProvider;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
@Deprecated
/* loaded from: classes.dex */
public final class NioUdtProvider<T extends UdtChannel> implements ChannelFactory<T> {
    public static final ChannelFactory<UdtServerChannel> BYTE_ACCEPTOR = new NioUdtProvider(TypeUDT.STREAM, KindUDT.ACCEPTOR);
    public static final ChannelFactory<UdtChannel> BYTE_CONNECTOR = new NioUdtProvider(TypeUDT.STREAM, KindUDT.CONNECTOR);
    public static final SelectorProvider BYTE_PROVIDER = SelectorProviderUDT.STREAM;
    public static final ChannelFactory<UdtChannel> BYTE_RENDEZVOUS = new NioUdtProvider(TypeUDT.STREAM, KindUDT.RENDEZVOUS);
    public static final ChannelFactory<UdtServerChannel> MESSAGE_ACCEPTOR = new NioUdtProvider(TypeUDT.DATAGRAM, KindUDT.ACCEPTOR);
    public static final ChannelFactory<UdtChannel> MESSAGE_CONNECTOR = new NioUdtProvider(TypeUDT.DATAGRAM, KindUDT.CONNECTOR);
    public static final SelectorProvider MESSAGE_PROVIDER = SelectorProviderUDT.DATAGRAM;
    public static final ChannelFactory<UdtChannel> MESSAGE_RENDEZVOUS = new NioUdtProvider(TypeUDT.DATAGRAM, KindUDT.RENDEZVOUS);
    private final KindUDT kind;
    private final TypeUDT type;

    public static ChannelUDT channelUDT(Channel channel) {
        if (channel instanceof NioUdtByteAcceptorChannel) {
            return ((NioUdtByteAcceptorChannel) channel).mo4506javaChannel();
        }
        if (channel instanceof NioUdtByteRendezvousChannel) {
            return ((NioUdtByteRendezvousChannel) channel).mo4506javaChannel();
        }
        if (channel instanceof NioUdtByteConnectorChannel) {
            return ((NioUdtByteConnectorChannel) channel).mo4506javaChannel();
        }
        if (channel instanceof NioUdtMessageAcceptorChannel) {
            return ((NioUdtMessageAcceptorChannel) channel).mo4506javaChannel();
        }
        if (channel instanceof NioUdtMessageRendezvousChannel) {
            return ((NioUdtMessageRendezvousChannel) channel).mo4506javaChannel();
        }
        if (channel instanceof NioUdtMessageConnectorChannel) {
            return ((NioUdtMessageConnectorChannel) channel).mo4506javaChannel();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ServerSocketChannelUDT newAcceptorChannelUDT(TypeUDT typeUDT) {
        try {
            return SelectorProviderUDT.from(typeUDT).openServerSocketChannel();
        } catch (IOException e) {
            throw new ChannelException("failed to open a server socket channel", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SocketChannelUDT newConnectorChannelUDT(TypeUDT typeUDT) {
        try {
            return SelectorProviderUDT.from(typeUDT).openSocketChannel();
        } catch (IOException e) {
            throw new ChannelException("failed to open a socket channel", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RendezvousChannelUDT newRendezvousChannelUDT(TypeUDT typeUDT) {
        try {
            return SelectorProviderUDT.from(typeUDT).openRendezvousChannel();
        } catch (IOException e) {
            throw new ChannelException("failed to open a rendezvous channel", e);
        }
    }

    public static SocketUDT socketUDT(Channel channel) {
        ChannelUDT channelUDT = channelUDT(channel);
        if (channelUDT == null) {
            return null;
        }
        return channelUDT.socketUDT();
    }

    private NioUdtProvider(TypeUDT typeUDT, KindUDT kindUDT) {
        this.type = typeUDT;
        this.kind = kindUDT;
    }

    public KindUDT kind() {
        return this.kind;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* renamed from: io.netty.channel.udt.nio.NioUdtProvider$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C70911 {
        static final /* synthetic */ int[] $SwitchMap$com$barchart$udt$TypeUDT;
        static final /* synthetic */ int[] $SwitchMap$com$barchart$udt$nio$KindUDT;

        static {
            int[] iArr = new int[KindUDT.values().length];
            $SwitchMap$com$barchart$udt$nio$KindUDT = iArr;
            try {
                iArr[KindUDT.ACCEPTOR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$barchart$udt$nio$KindUDT[KindUDT.CONNECTOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$barchart$udt$nio$KindUDT[KindUDT.RENDEZVOUS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[TypeUDT.values().length];
            $SwitchMap$com$barchart$udt$TypeUDT = iArr2;
            try {
                iArr2[TypeUDT.DATAGRAM.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$barchart$udt$TypeUDT[TypeUDT.STREAM.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Override // io.netty.channel.ChannelFactory, io.netty.bootstrap.ChannelFactory
    public T newChannel() {
        int i = C70911.$SwitchMap$com$barchart$udt$nio$KindUDT[this.kind.ordinal()];
        if (i == 1) {
            int i2 = C70911.$SwitchMap$com$barchart$udt$TypeUDT[this.type.ordinal()];
            if (i2 == 1) {
                return new NioUdtMessageAcceptorChannel();
            }
            if (i2 == 2) {
                return new NioUdtByteAcceptorChannel();
            }
            throw new IllegalStateException("wrong type=" + this.type);
        }
        if (i == 2) {
            int i3 = C70911.$SwitchMap$com$barchart$udt$TypeUDT[this.type.ordinal()];
            if (i3 == 1) {
                return new NioUdtMessageConnectorChannel();
            }
            if (i3 == 2) {
                return new NioUdtByteConnectorChannel();
            }
            throw new IllegalStateException("wrong type=" + this.type);
        }
        if (i == 3) {
            int i4 = C70911.$SwitchMap$com$barchart$udt$TypeUDT[this.type.ordinal()];
            if (i4 == 1) {
                return new NioUdtMessageRendezvousChannel();
            }
            if (i4 == 2) {
                return new NioUdtByteRendezvousChannel();
            }
            throw new IllegalStateException("wrong type=" + this.type);
        }
        throw new IllegalStateException("wrong kind=" + this.kind);
    }

    public TypeUDT type() {
        return this.type;
    }
}
