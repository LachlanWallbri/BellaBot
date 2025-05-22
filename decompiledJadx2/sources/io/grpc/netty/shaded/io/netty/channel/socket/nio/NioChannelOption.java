package io.grpc.netty.shaded.io.netty.channel.socket.nio;

import io.grpc.netty.shaded.io.netty.channel.ChannelException;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import java.io.IOException;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.nio.channels.Channel;
import java.nio.channels.NetworkChannel;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class NioChannelOption<T> extends ChannelOption<T> {
    private final SocketOption<T> option;

    private NioChannelOption(SocketOption<T> socketOption) {
        super(socketOption.name());
        this.option = socketOption;
    }

    /* renamed from: of */
    public static <T> ChannelOption<T> m3914of(SocketOption<T> socketOption) {
        return new NioChannelOption(socketOption);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> boolean setOption(Channel channel, NioChannelOption<T> nioChannelOption, T t) {
        NetworkChannel networkChannel = (NetworkChannel) channel;
        if (!networkChannel.supportedOptions().contains(((NioChannelOption) nioChannelOption).option)) {
            return false;
        }
        if ((networkChannel instanceof ServerSocketChannel) && ((NioChannelOption) nioChannelOption).option == StandardSocketOptions.IP_TOS) {
            return false;
        }
        try {
            networkChannel.setOption(((NioChannelOption) nioChannelOption).option, t);
            return true;
        } catch (IOException e) {
            throw new ChannelException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T getOption(Channel channel, NioChannelOption<T> nioChannelOption) {
        NetworkChannel networkChannel = (NetworkChannel) channel;
        if (!networkChannel.supportedOptions().contains(((NioChannelOption) nioChannelOption).option)) {
            return null;
        }
        if ((networkChannel instanceof ServerSocketChannel) && ((NioChannelOption) nioChannelOption).option == StandardSocketOptions.IP_TOS) {
            return null;
        }
        try {
            return (T) networkChannel.getOption(((NioChannelOption) nioChannelOption).option);
        } catch (IOException e) {
            throw new ChannelException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ChannelOption[] getOptions(Channel channel) {
        NetworkChannel networkChannel = (NetworkChannel) channel;
        Set<SocketOption<?>> supportedOptions = networkChannel.supportedOptions();
        int i = 0;
        if (networkChannel instanceof ServerSocketChannel) {
            ArrayList arrayList = new ArrayList(supportedOptions.size());
            for (SocketOption<?> socketOption : supportedOptions) {
                if (socketOption != StandardSocketOptions.IP_TOS) {
                    arrayList.add(new NioChannelOption(socketOption));
                }
            }
            return (ChannelOption[]) arrayList.toArray(new ChannelOption[0]);
        }
        ChannelOption[] channelOptionArr = new ChannelOption[supportedOptions.size()];
        Iterator<SocketOption<?>> it = supportedOptions.iterator();
        while (it.hasNext()) {
            channelOptionArr[i] = new NioChannelOption(it.next());
            i++;
        }
        return channelOptionArr;
    }
}
