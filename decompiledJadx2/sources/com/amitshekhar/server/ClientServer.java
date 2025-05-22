package com.amitshekhar.server;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import androidx.sqlite.p003db.SupportSQLiteDatabase;
import com.amitshekhar.sqlite.DBFactory;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ClientServer implements Runnable {
    private static final String TAG = "ClientServer";
    private boolean mIsRunning;
    private final int mPort;
    private final RequestHandler mRequestHandler;
    private ServerSocket mServerSocket;

    public ClientServer(Context context, int i, DBFactory dBFactory) {
        this.mRequestHandler = new RequestHandler(context, dBFactory);
        this.mPort = i;
    }

    public void start() {
        this.mIsRunning = true;
        new Thread(this).start();
    }

    public void stop() {
        try {
            this.mIsRunning = false;
            if (this.mServerSocket != null) {
                this.mServerSocket.close();
                this.mServerSocket = null;
            }
        } catch (Exception e) {
            Log.e(TAG, "Error closing the server socket.", e);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.mServerSocket = new ServerSocket(this.mPort);
            while (this.mIsRunning) {
                Socket accept = this.mServerSocket.accept();
                this.mRequestHandler.handle(accept);
                accept.close();
            }
        } catch (SocketException unused) {
        } catch (IOException e) {
            Log.e(TAG, "Web server error.", e);
        } catch (Exception e2) {
            Log.e(TAG, "Exception.", e2);
        }
    }

    public void setCustomDatabaseFiles(HashMap<String, Pair<File, String>> hashMap) {
        this.mRequestHandler.setCustomDatabaseFiles(hashMap);
    }

    public void setInMemoryRoomDatabases(HashMap<String, SupportSQLiteDatabase> hashMap) {
        this.mRequestHandler.setInMemoryRoomDatabases(hashMap);
    }

    public boolean isRunning() {
        return this.mIsRunning;
    }
}
