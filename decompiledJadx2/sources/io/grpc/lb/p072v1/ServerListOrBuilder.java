package io.grpc.lb.p072v1;

import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ServerListOrBuilder extends MessageOrBuilder {
    Server getServers(int i);

    int getServersCount();

    List<Server> getServersList();

    ServerOrBuilder getServersOrBuilder(int i);

    List<? extends ServerOrBuilder> getServersOrBuilderList();
}
