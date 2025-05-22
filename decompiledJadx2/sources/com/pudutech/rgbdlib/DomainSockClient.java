package com.pudutech.rgbdlib;

import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import com.pudutech.base.Pdlog;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes5.dex
  classes6.dex
 */
/* loaded from: classes2.dex */
public class DomainSockClient {
    private static String TAG = "DomainSockClient";
    private static volatile DomainSockClient singleton;
    private InputStream input_stream_;
    protected OnMsgListener listener_;
    private OutputStream output_stream_;
    private String sock_path_ = "/pudu/sensor/rgbd_state";

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes5.dex
      classes6.dex
     */
    /* loaded from: classes2.dex */
    public interface OnMsgListener {
        void callback(String str, String str2);
    }

    private DomainSockClient() {
    }

    public static DomainSockClient instance() {
        if (singleton == null) {
            synchronized (DomainSockClient.class) {
                if (singleton == null) {
                    singleton = new DomainSockClient();
                }
            }
        }
        return singleton;
    }

    public void setMsgCallBack(OnMsgListener onMsgListener) {
        this.listener_ = onMsgListener;
    }

    public void connect(String str) {
        this.sock_path_ = str;
        new Thread(new Runnable() { // from class: com.pudutech.rgbdlib.DomainSockClient.1
            private boolean is_processing_head_ = true;
            private int processing_msg_id_ = 0;
            private int processing_msg_len_ = 0;
            private ByteBuffer buffer_ = ByteBuffer.allocate(4096);

            @Override // java.lang.Runnable
            public void run() {
                int read;
                byte[] bArr = new byte[1024];
                while (true) {
                    LocalSocket localSocket = new LocalSocket();
                    try {
                        LocalSocketAddress localSocketAddress = new LocalSocketAddress(DomainSockClient.this.sock_path_);
                        Pdlog.m3273d(DomainSockClient.TAG, "addr name " + localSocketAddress.getName() + " length " + localSocketAddress.getName().length() + " namespace " + localSocketAddress.getNamespace());
                        localSocket.connect(localSocketAddress);
                        DomainSockClient.this.output_stream_ = localSocket.getOutputStream();
                        DomainSockClient.this.input_stream_ = localSocket.getInputStream();
                        Pdlog.m3273d(DomainSockClient.TAG, "finish create socket");
                        while (true) {
                            read = DomainSockClient.this.input_stream_.read(bArr, 0, 1024);
                            if (read <= 0) {
                                break;
                            }
                            if (this.buffer_.remaining() < read) {
                                ByteBuffer allocate = ByteBuffer.allocate(this.buffer_.capacity() + 4096);
                                allocate.put(this.buffer_.array(), 0, this.buffer_.position());
                                this.buffer_ = allocate;
                            }
                            this.buffer_.put(bArr, 0, read);
                            do {
                            } while (processLoop());
                        }
                        Pdlog.m3274e(DomainSockClient.TAG, "protocol error, read msg type fail:" + read);
                    } catch (IOException e) {
                        Pdlog.m3274e(DomainSockClient.TAG, "Fail to create socket");
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(1500L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }

            private boolean processLoop() {
                if (this.is_processing_head_) {
                    if (this.buffer_.position() >= 4) {
                        int position = this.buffer_.position();
                        this.buffer_.position(0);
                        byte[] bArr = new byte[4];
                        this.buffer_.get(bArr, 0, 4);
                        this.processing_msg_len_ = new BigInteger(bArr).intValue();
                        Pdlog.m3273d(DomainSockClient.TAG, "parsing msg id:" + this.processing_msg_id_ + " len:" + this.processing_msg_len_);
                        this.buffer_.position(position);
                        if (this.processing_msg_len_ > 0) {
                            this.is_processing_head_ = false;
                            if (this.buffer_.remaining() < this.processing_msg_len_) {
                                Pdlog.m3273d(DomainSockClient.TAG, "relloc buffer_ size::" + (this.buffer_.capacity() + this.processing_msg_len_));
                                ByteBuffer allocate = ByteBuffer.allocate(this.buffer_.capacity() + this.processing_msg_len_);
                                allocate.put(this.buffer_.array(), 0, this.buffer_.position());
                                this.buffer_ = allocate;
                            }
                        }
                        return true;
                    }
                } else if (this.processing_msg_len_ <= 0) {
                    Pdlog.m3274e(DomainSockClient.TAG, "exception processing_msg_len_:" + this.processing_msg_len_);
                    this.is_processing_head_ = true;
                } else if (this.buffer_.position() >= this.processing_msg_len_ + 4) {
                    Pdlog.m3273d(DomainSockClient.TAG, "parsing body size:" + this.processing_msg_len_);
                    this.buffer_.flip();
                    this.buffer_.position(4);
                    int i = this.processing_msg_len_;
                    byte[] bArr2 = new byte[i];
                    this.buffer_.get(bArr2, 0, i);
                    String str2 = new String();
                    String str3 = new String();
                    boolean z = true;
                    for (int i2 = 0; i2 < this.processing_msg_len_; i2++) {
                        if (bArr2[i2] == 0) {
                            z = false;
                        } else if (z) {
                            str2 = str2 + ((char) bArr2[i2]);
                        } else {
                            str3 = str3 + ((char) bArr2[i2]);
                        }
                    }
                    Pdlog.m3273d(DomainSockClient.TAG, "parsing msg_id:" + str2);
                    Pdlog.m3273d(DomainSockClient.TAG, "parsing msg_body:" + str3);
                    this.buffer_.compact();
                    Pdlog.m3273d(DomainSockClient.TAG, "buffer_ size():" + this.buffer_.position());
                    DomainSockClient.this.listener_.callback(str2, str3);
                    this.is_processing_head_ = true;
                    return true;
                }
                return false;
            }
        }, "RgbdStateRecv").start();
    }

    public void write(byte[] bArr) throws IOException {
        try {
            this.output_stream_.write(bArr);
        } catch (IOException e) {
            Pdlog.m3274e(TAG, "write exception,stack: ${e.printStackTrace()}");
            e.printStackTrace();
        }
    }
}
