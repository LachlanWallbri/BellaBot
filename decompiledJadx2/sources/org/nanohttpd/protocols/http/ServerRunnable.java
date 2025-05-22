package org.nanohttpd.protocols.http;

import android.util.Log;
import com.pudutech.base.Pdlog;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class ServerRunnable implements Runnable {
    private static final String TAG = "ServerRunnable";
    private IOException bindException;
    private boolean hasBinded = false;
    private NanoHTTPD httpd;
    private final int timeout;

    public ServerRunnable(NanoHTTPD nanoHTTPD, int i) {
        this.httpd = nanoHTTPD;
        this.timeout = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.httpd.getMyServerSocket().bind(this.httpd.hostname != null ? new InetSocketAddress(this.httpd.hostname, this.httpd.myPort) : new InetSocketAddress(this.httpd.myPort));
            this.hasBinded = true;
            do {
                try {
                    Socket accept = this.httpd.getMyServerSocket().accept();
                    if (this.timeout > 0) {
                        accept.setSoTimeout(this.timeout);
                    }
                    this.httpd.asyncRunner.exec(this.httpd.createClientHandler(accept, accept.getInputStream()));
                } catch (IOException e) {
                    NanoHTTPD.LOG.log(Level.FINE, "Communication with the client broken", (Throwable) e);
                    Pdlog.m3274e(TAG, "receive the socket stream occur error " + Log.getStackTraceString(e));
                }
            } while (!this.httpd.getMyServerSocket().isClosed());
        } catch (IOException e2) {
            this.bindException = e2;
            Pdlog.m3274e(TAG, "ServerRunnable occur error->" + Log.getStackTraceString(e2));
        }
    }

    public IOException getBindException() {
        return this.bindException;
    }

    public boolean hasBinded() {
        return this.hasBinded;
    }
}
