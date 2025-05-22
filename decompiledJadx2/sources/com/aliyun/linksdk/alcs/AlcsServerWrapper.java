package com.aliyun.linksdk.alcs;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.alcs.api.server.AlcsServer;
import com.aliyun.alink.linksdk.alcs.api.server.AlcsServerConfig;
import com.aliyun.alink.linksdk.alcs.coap.AlcsCoAPContext;
import com.aliyun.alink.linksdk.alcs.coap.AlcsCoAPRequest;
import com.aliyun.alink.linksdk.alcs.coap.AlcsCoAPResponse;
import com.aliyun.alink.linksdk.alcs.coap.IAlcsCoAPResHandler;
import com.aliyun.alink.linksdk.alcs.coap.resources.AlcsCoAPResource;
import com.aliyun.alink.linksdk.tools.ALog;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AlcsServerWrapper implements IAlcsServer {
    private static final String TAG = "AlcsServerWrapper";
    private AlcsServer alcsServer;
    private IServerMessageDeliver messageDeliver = null;

    public AlcsServerWrapper(AlcsServerConfig alcsServerConfig) {
        ALog.m479d(TAG, "AlcsServerWrapper()，constructor");
        this.alcsServer = new AlcsServer(alcsServerConfig);
    }

    @Override // com.aliyun.linksdk.alcs.IAlcsServer
    public void startServer() {
        ALog.m479d(TAG, "startServer()");
        AlcsServer alcsServer = this.alcsServer;
        if (alcsServer == null) {
            return;
        }
        alcsServer.start();
    }

    @Override // com.aliyun.linksdk.alcs.IAlcsServer
    public void updateSvrPrefix(String str) {
        ALog.m479d(TAG, "updatePrefixSecret()");
        AlcsServer alcsServer = this.alcsServer;
        if (alcsServer == null) {
            return;
        }
        alcsServer.updateSvrPrefix(str);
    }

    @Override // com.aliyun.linksdk.alcs.IAlcsServer
    public void updateBlackList(String str) {
        ALog.m479d(TAG, "updateBlackList()");
        AlcsServer alcsServer = this.alcsServer;
        if (alcsServer == null) {
            return;
        }
        alcsServer.updateBlackList(str);
    }

    @Override // com.aliyun.linksdk.alcs.IAlcsServer
    public void removeSvrKey(String str) {
        AlcsServer alcsServer = this.alcsServer;
        if (alcsServer == null) {
            return;
        }
        alcsServer.removeSvrKey(str);
    }

    @Override // com.aliyun.linksdk.alcs.IAlcsServer
    public void addSvrAccessKey(String str, String str2) {
        AlcsServer alcsServer = this.alcsServer;
        if (alcsServer == null) {
            return;
        }
        alcsServer.addSvrAccessKey(str, str2);
    }

    @Override // com.aliyun.linksdk.alcs.IAlcsServer
    public boolean sendResponse(boolean z, AlcsCoAPResponse alcsCoAPResponse) {
        ALog.m479d(TAG, "sendResponse(), isSec = " + z);
        AlcsServer alcsServer = this.alcsServer;
        if (alcsServer == null) {
            return false;
        }
        if (z) {
            return alcsServer.sendResponseSecure(alcsCoAPResponse);
        }
        return alcsServer.sendResponse(alcsCoAPResponse);
    }

    @Override // com.aliyun.linksdk.alcs.IAlcsServer
    public boolean registerAllResource(boolean z, AlcsCoAPResource alcsCoAPResource) {
        ALog.m479d(TAG, "registerAllResource()");
        if (this.alcsServer == null || alcsCoAPResource == null) {
            return false;
        }
        alcsCoAPResource.setNeedAuth(z ? 1 : 0);
        alcsCoAPResource.setHandler(new ServerMessageDeleverWrapper(z, alcsCoAPResource.getHandler()));
        return this.alcsServer.registerAllResource(alcsCoAPResource);
    }

    @Override // com.aliyun.linksdk.alcs.IAlcsServer
    public void unRegisterResource(String str) {
        ALog.m479d(TAG, "unRegisterResource(), path = " + str);
        if (this.alcsServer == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.alcsServer.unRegisterResource(str);
    }

    @Override // com.aliyun.linksdk.alcs.IAlcsServer
    public boolean publishResoucre(String str, Object obj) {
        ALog.m479d(TAG, "publishResoucre()");
        if (this.alcsServer == null) {
            return false;
        }
        byte[] bArr = null;
        if (bArr instanceof byte[]) {
            bArr = (byte[]) obj;
        } else {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(obj);
                objectOutputStream.flush();
                bArr = byteArrayOutputStream.toByteArray();
            } catch (Exception e) {
                ALog.m480e(TAG, "publishResoucre(): convert payload Obj to byte array error");
                e.printStackTrace();
            }
        }
        if (bArr == null) {
            ALog.m479d(TAG, "publishResoucre(): payload is empty");
            return false;
        }
        return this.alcsServer.notifyRes(str, bArr);
    }

    @Override // com.aliyun.linksdk.alcs.IAlcsServer
    public void stopServer() {
        ALog.m479d(TAG, "stopServer()");
        AlcsServer alcsServer = this.alcsServer;
        if (alcsServer == null) {
            return;
        }
        alcsServer.stop();
    }

    @Override // com.aliyun.linksdk.alcs.IAlcsServer
    public void setServerMessageDeliver(IServerMessageDeliver iServerMessageDeliver) {
        ALog.m479d(TAG, "setServerMessageDeliverer()");
        this.messageDeliver = iServerMessageDeliver;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    private class ServerMessageDeleverWrapper implements IAlcsCoAPResHandler {
        private IAlcsCoAPResHandler handler;
        private boolean isSecurity;

        public ServerMessageDeleverWrapper(boolean z, IAlcsCoAPResHandler iAlcsCoAPResHandler) {
            this.isSecurity = z;
            this.handler = iAlcsCoAPResHandler;
        }

        @Override // com.aliyun.alink.linksdk.alcs.coap.IAlcsCoAPResHandler
        public void onRecRequest(AlcsCoAPContext alcsCoAPContext, AlcsCoAPRequest alcsCoAPRequest) {
            ALog.m479d(AlcsServerWrapper.TAG, "onRecRequest()");
            IAlcsCoAPResHandler iAlcsCoAPResHandler = this.handler;
            if (iAlcsCoAPResHandler != null) {
                iAlcsCoAPResHandler.onRecRequest(alcsCoAPContext, alcsCoAPRequest);
            }
            if (AlcsServerWrapper.this.messageDeliver != null) {
                if (this.isSecurity) {
                    AlcsServerWrapper.this.messageDeliver.onRecSecurityRequest(alcsCoAPContext, alcsCoAPRequest);
                } else {
                    AlcsServerWrapper.this.messageDeliver.onRecRequest(alcsCoAPContext, alcsCoAPRequest);
                }
            }
        }
    }
}
