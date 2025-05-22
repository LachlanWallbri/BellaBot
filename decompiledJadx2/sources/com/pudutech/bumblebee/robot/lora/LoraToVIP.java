package com.pudutech.bumblebee.robot.lora;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.lora.library.IDataReceiveListener;
import com.pudutech.lora.library.ISerialPortOpenStatusListener;
import com.pudutech.lora.library.LoRaChannel;
import com.pudutech.lora.library.LoRaClient;
import com.pudutech.lora.library.LoraWatch;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.simpleframework.xml.strategy.Name;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: LoraToVIP.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0007J \u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u000fH\u0016J\u000e\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u0011R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/lora/LoraToVIP;", "Lcom/pudutech/lora/library/IDataReceiveListener;", "Lcom/pudutech/lora/library/ISerialPortOpenStatusListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "client", "Lcom/pudutech/lora/library/LoRaClient;", "getContext", "()Landroid/content/Context;", "loraJob", "Lkotlinx/coroutines/Job;", "loraOpened", "", "noticeVIP", "", NotificationCompat.CATEGORY_MESSAGE, "onReceive", "channel", "Lcom/pudutech/lora/library/LoRaChannel;", "data", "", Name.LENGTH, "", "isopened", "openLora", "version", "Lcom/pudutech/lora/library/LoraWatch;", "stopNoticeVIP", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class LoraToVIP implements IDataReceiveListener, ISerialPortOpenStatusListener {
    private final String TAG;
    private final LoRaClient client;
    private final Context context;
    private Job loraJob;
    private boolean loraOpened;

    public LoraToVIP(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = "Lora";
        this.client = new LoRaClient();
    }

    public final Context getContext() {
        return this.context;
    }

    public final void openLora(LoraWatch version) {
        Intrinsics.checkParameterIsNotNull(version, "version");
        this.client.init(this.context, this, this, version);
        this.client.connect();
    }

    @Override // com.pudutech.lora.library.IDataReceiveListener
    public void onReceive(LoRaChannel channel, byte[] data, int length) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(data, "data");
        Pdlog.m3273d(this.TAG, "Not care receive data");
    }

    @Override // com.pudutech.lora.library.ISerialPortOpenStatusListener
    public void onReceive(boolean isopened) {
        this.loraOpened = isopened;
    }

    public final void noticeVIP(String msg) {
        if (msg == null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new LoraToVIP$noticeVIP$1(this, msg, null), 3, null);
    }

    public final void stopNoticeVIP() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new LoraToVIP$stopNoticeVIP$1(this, null), 3, null);
    }
}
