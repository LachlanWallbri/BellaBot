package org.nanohttpd.protocols.http.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import org.nanohttpd.protocols.util.IFactoryThrowing;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class DefaultServerSocketFactory implements IFactoryThrowing<ServerSocket, IOException> {
    @Override // org.nanohttpd.protocols.util.IFactoryThrowing
    public ServerSocket create() throws IOException {
        return new ServerSocket();
    }
}
