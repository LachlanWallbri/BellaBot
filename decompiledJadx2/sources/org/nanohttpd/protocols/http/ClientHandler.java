package org.nanohttpd.protocols.http;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.logging.Level;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class ClientHandler implements Runnable {
    private final Socket acceptSocket;
    private final NanoHTTPD httpd;
    private final InputStream inputStream;

    public ClientHandler(NanoHTTPD nanoHTTPD, InputStream inputStream, Socket socket) {
        this.httpd = nanoHTTPD;
        this.inputStream = inputStream;
        this.acceptSocket = socket;
    }

    public void close() {
        NanoHTTPD.safeClose(this.inputStream);
        NanoHTTPD.safeClose(this.acceptSocket);
    }

    @Override // java.lang.Runnable
    public void run() {
        OutputStream outputStream = null;
        try {
            try {
                outputStream = this.acceptSocket.getOutputStream();
                HTTPSession hTTPSession = new HTTPSession(this.httpd, this.httpd.getTempFileManagerFactory().create(), this.inputStream, outputStream, this.acceptSocket.getInetAddress());
                while (!this.acceptSocket.isClosed()) {
                    hTTPSession.execute();
                }
            } catch (Exception e) {
                if ((!(e instanceof SocketException) || !"NanoHttpd Shutdown".equals(e.getMessage())) && !(e instanceof SocketTimeoutException)) {
                    NanoHTTPD.LOG.log(Level.SEVERE, "Communication with the client broken, or an bug in the handler code", (Throwable) e);
                }
            }
        } finally {
            NanoHTTPD.safeClose(outputStream);
            NanoHTTPD.safeClose(this.inputStream);
            NanoHTTPD.safeClose(this.acceptSocket);
            this.httpd.asyncRunner.closed(this);
        }
    }
}
