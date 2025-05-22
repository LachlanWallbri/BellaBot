package com.pudutech.light_network;

import android.util.Log;
import com.pudutech.light_network.wsconnectInfo.MessageRecvHandler;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class WebSocketManager {
    private static final String TAG = "WebSocketManager";
    private static String WS_URL = "ws://echo.websocket.org";
    private static WebSocket mWebSocket = null;
    private static boolean sDebug = true;
    private static MessageRecvHandler sMessageRecvHandler;
    private static Request sRequest;
    private static WebSocketListener sWebSocketListener;

    public static final void initWebSocket(String str, MessageRecvHandler messageRecvHandler) {
        WS_URL = str;
        sMessageRecvHandler = messageRecvHandler;
        doGetWebsocket();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void doGetWebsocket() {
        OkHttpClient oKHttpClient = OKHttpClient.getInstance(null, null, null, sDebug);
        synchronized (WebSocketManager.class) {
            oKHttpClient.newWebSocket(new Request.Builder().url(WS_URL).build(), new OnWebSocketListener());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    public static final class OnWebSocketListener extends WebSocketListener {
        OnWebSocketListener() {
        }

        @Override // okhttp3.WebSocketListener
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            Log.e(WebSocketManager.TAG, "onOpen--->" + response.toString());
            WebSocket unused = WebSocketManager.mWebSocket = webSocket;
        }

        @Override // okhttp3.WebSocketListener
        public void onMessage(WebSocket webSocket, String str) {
            super.onMessage(webSocket, str);
            Log.e(WebSocketManager.TAG, "onMessage--->" + str);
            if (WebSocketManager.sMessageRecvHandler != null) {
                WebSocketManager.sMessageRecvHandler.onMessageRecv(str);
            }
        }

        @Override // okhttp3.WebSocketListener
        public void onMessage(WebSocket webSocket, ByteString byteString) {
            super.onMessage(webSocket, byteString);
            Log.e(WebSocketManager.TAG, "onMessage");
            if (WebSocketManager.sMessageRecvHandler != null) {
                WebSocketManager.sMessageRecvHandler.onMessageRecvByte(byteString);
            }
        }

        @Override // okhttp3.WebSocketListener
        public void onClosing(WebSocket webSocket, int i, String str) {
            super.onClosing(webSocket, i, str);
            Log.e(WebSocketManager.TAG, "onClosing");
        }

        @Override // okhttp3.WebSocketListener
        public void onClosed(WebSocket webSocket, int i, String str) {
            super.onClosed(webSocket, i, str);
            Log.e(WebSocketManager.TAG, "onClosed----->" + i + str);
            if (WebSocketManager.mWebSocket != null) {
                WebSocketManager.mWebSocket.cancel();
                WebSocket unused = WebSocketManager.mWebSocket = null;
            }
            WebSocketManager.doGetWebsocket();
        }

        @Override // okhttp3.WebSocketListener
        public void onFailure(WebSocket webSocket, Throwable th, Response response) {
            super.onFailure(webSocket, th, response);
            WebSocket unused = WebSocketManager.mWebSocket = null;
            Log.e(WebSocketManager.TAG, "onFailure" + th.getMessage());
            WebSocketManager.doGetWebsocket();
        }
    }
}
