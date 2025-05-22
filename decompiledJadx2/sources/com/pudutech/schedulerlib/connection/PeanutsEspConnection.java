package com.pudutech.schedulerlib.connection;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.schedulerlib.ScheduleController;
import com.pudutech.schedulerlib.connection.BaseEspConnection;
import com.pudutech.schedulerlib.connection.PeanutEspService;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* compiled from: PeanutsEspConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0011H\u0016J\b\u0010\u0015\u001a\u00020\u0004H\u0016J\b\u0010\u0016\u001a\u00020\u0006H\u0016J\n\u0010\u0017\u001a\u0004\u0018\u00010\u0006H\u0016J\u0018\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0004H\u0016J\u0010\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u0004H\u0016J\u0010\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u0004H\u0016J\u0010\u0010#\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, m3961d2 = {"Lcom/pudutech/schedulerlib/connection/PeanutsEspConnection;", "Lcom/pudutech/schedulerlib/connection/BaseEspConnection;", "()V", "ESP_DATA_MESSAGE", "", "TAG", "", "callback", "Lcom/pudutech/schedulerlib/connection/ScheduleMsgReceiveInterface;", "channel", "espCallback", "Landroid/os/Handler$Callback;", "espHandler", "Landroid/os/Handler;", "espHandlerThread", "Landroid/os/HandlerThread;", "isEspRunning", "", "closeESPDevice", "", "espIsRunning", "getCurrentChannel", "getESPErrorCode", "getEspVersion", "openESPDevice", "connectStateListener", "Lcom/pudutech/schedulerlib/ScheduleController$OnConnectStateListener;", "high_version", "resetChannel", "chl", "sendMsg", NotificationCompat.CATEGORY_MESSAGE, "", "setBaudRate", "baud", "setMsgCallBack", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class PeanutsEspConnection implements BaseEspConnection {
    private ScheduleMsgReceiveInterface callback;
    private Handler espHandler;
    private boolean isEspRunning;
    private final String TAG = "PeanutsEspConnection";
    private final int ESP_DATA_MESSAGE = 10;
    private HandlerThread espHandlerThread = new HandlerThread("esp_data_thread");
    private int channel = 1;
    private Handler.Callback espCallback = new Handler.Callback() { // from class: com.pudutech.schedulerlib.connection.PeanutsEspConnection$espCallback$1
        /* JADX WARN: Code restructure failed: missing block: B:8:0x001a, code lost:
        
            r1 = r3.this$0.callback;
         */
        @Override // android.os.Handler.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public boolean handleMessage(Message msg) {
            int i;
            ScheduleMsgReceiveInterface scheduleMsgReceiveInterface;
            Integer valueOf = msg != null ? Integer.valueOf(msg.what) : null;
            i = PeanutsEspConnection.this.ESP_DATA_MESSAGE;
            if (valueOf == null || valueOf.intValue() != i || scheduleMsgReceiveInterface == null) {
                return true;
            }
            Object obj = msg != null ? msg.obj : null;
            if (obj == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.ByteArray");
            }
            scheduleMsgReceiveInterface.decodeMsg((byte[]) obj);
            return true;
        }
    };

    @Override // com.pudutech.schedulerlib.connection.BaseEspConnection
    public String getESPErrorCode() {
        return "v:1.0.0";
    }

    @Override // com.pudutech.schedulerlib.connection.BaseEspConnection
    public String getEspVersion() {
        return "str";
    }

    @Override // com.pudutech.schedulerlib.connection.BaseEspConnection
    public void setBaudRate(int baud) {
    }

    public PeanutsEspConnection() {
        this.espHandlerThread.start();
        this.espHandler = new Handler(this.espHandlerThread.getLooper(), this.espCallback);
    }

    @Override // com.pudutech.schedulerlib.connection.BaseEspConnection
    public String bytesToHexString(byte[] bArr) {
        return BaseEspConnection.DefaultImpls.bytesToHexString(this, bArr);
    }

    @Override // com.pudutech.schedulerlib.connection.BaseEspConnection
    public void resetChannel(int chl) {
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("reset channel, current channel ");
        sb.append(this.channel);
        sb.append(", new channel ");
        int i = chl + 1;
        sb.append(i);
        sb.append(" chl=");
        sb.append(chl);
        Pdlog.m3273d(str, sb.toString());
        this.channel = i;
        PeanutEspService.INSTANCE.setChannel(this.channel);
    }

    @Override // com.pudutech.schedulerlib.connection.BaseEspConnection
    public void setMsgCallBack(ScheduleMsgReceiveInterface callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.callback = callback;
    }

    @Override // com.pudutech.schedulerlib.connection.BaseEspConnection
    public void openESPDevice(final ScheduleController.OnConnectStateListener connectStateListener, int high_version) {
        Intrinsics.checkParameterIsNotNull(connectStateListener, "connectStateListener");
        Pdlog.m3273d(this.TAG, "start to openESPDevice");
        PeanutEspService.INSTANCE.initEsp(new Function1<Integer, Unit>() { // from class: com.pudutech.schedulerlib.connection.PeanutsEspConnection$openESPDevice$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                String str;
                String str2;
                str = PeanutsEspConnection.this.TAG;
                Pdlog.m3273d(str, "openESPDevice result=" + i);
                if (i != 0) {
                    PeanutsEspConnection.this.isEspRunning = false;
                    connectStateListener.onFailure();
                    str2 = PeanutsEspConnection.this.TAG;
                    Pdlog.m3273d(str2, "openESPDevice failure");
                    return;
                }
                connectStateListener.onSuccessful();
                PeanutsEspConnection.this.isEspRunning = true;
                PeanutEspService.INSTANCE.setOnEspListener(new PeanutEspService.OnEspListener() { // from class: com.pudutech.schedulerlib.connection.PeanutsEspConnection$openESPDevice$1.1
                    @Override // com.pudutech.schedulerlib.connection.PeanutEspService.OnEspListener
                    public void onEspData(byte[] esp) {
                        String str3;
                        Handler handler;
                        Handler handler2;
                        int i2;
                        Intrinsics.checkParameterIsNotNull(esp, "esp");
                        str3 = PeanutsEspConnection.this.TAG;
                        Pdlog.m3273d(str3, "receiver onEspData len=" + esp.length);
                        handler = PeanutsEspConnection.this.espHandler;
                        Message obtainMessage = handler != null ? handler.obtainMessage() : null;
                        if (obtainMessage != null) {
                            i2 = PeanutsEspConnection.this.ESP_DATA_MESSAGE;
                            obtainMessage.what = i2;
                        }
                        if (obtainMessage != null) {
                            obtainMessage.obj = esp.clone();
                        }
                        handler2 = PeanutsEspConnection.this.espHandler;
                        if (handler2 != null) {
                            handler2.sendMessage(obtainMessage);
                        }
                    }
                });
            }
        });
    }

    @Override // com.pudutech.schedulerlib.connection.BaseEspConnection
    public void closeESPDevice() {
        PeanutEspService.INSTANCE.unInitEsp();
        PeanutEspService.INSTANCE.setOnEspListener(null);
        this.isEspRunning = false;
    }

    @Override // com.pudutech.schedulerlib.connection.BaseEspConnection
    public int sendMsg(byte[] msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        return PeanutEspService.INSTANCE.sendMessage(msg);
    }

    @Override // com.pudutech.schedulerlib.connection.BaseEspConnection
    /* renamed from: espIsRunning, reason: from getter */
    public boolean getIsEspRunning() {
        return this.isEspRunning;
    }

    @Override // com.pudutech.schedulerlib.connection.BaseEspConnection
    /* renamed from: getCurrentChannel, reason: from getter */
    public int getChannel() {
        return this.channel;
    }
}
