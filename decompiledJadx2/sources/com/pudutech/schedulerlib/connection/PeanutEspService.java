package com.pudutech.schedulerlib.connection;

import androidx.core.app.NotificationCompat;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.easynodelib.EasyNode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* compiled from: PeanutEspService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u0012\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001)B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001d\u001a\u00020\tJ)\u0010\u001e\u001a\u00020\r2!\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\r0\bJ\u000e\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u00020\r2\u0006\u0010%\u001a\u00020\tJ\u0010\u0010&\u001a\u00020\r2\b\u0010'\u001a\u0004\u0018\u00010\u0006J\u0006\u0010(\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R7\u0010\u0007\u001a\u001f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006*"}, m3961d2 = {"Lcom/pudutech/schedulerlib/connection/PeanutEspService;", "", "()V", "TAG", "", "mOnEspListener", "Lcom/pudutech/schedulerlib/connection/PeanutEspService$OnEspListener;", "onTestListener", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "index", "", "getOnTestListener", "()Lkotlin/jvm/functions/Function1;", "setOnTestListener", "(Lkotlin/jvm/functions/Function1;)V", "pause", "", "getPause", "()Z", "setPause", "(Z)V", "receiveIndex", "getReceiveIndex", "()I", "setReceiveIndex", "(I)V", "getChannel", "initEsp", "callback", SpeechUtility.TAG_RESOURCE_RESULT, "sendMessage", NotificationCompat.CATEGORY_MESSAGE, "", "setChannel", "chl", "setOnEspListener", "l", "unInitEsp", "OnEspListener", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class PeanutEspService {
    public static final PeanutEspService INSTANCE = new PeanutEspService();
    private static final String TAG = "PeanutEspService";
    private static OnEspListener mOnEspListener;
    private static Function1<? super Integer, Unit> onTestListener;
    private static boolean pause;
    private static int receiveIndex;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* compiled from: PeanutEspService.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/schedulerlib/connection/PeanutEspService$OnEspListener;", "", "onEspData", "", "esp", "", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public interface OnEspListener {
        void onEspData(byte[] esp);
    }

    public final int getChannel() {
        return 1;
    }

    private PeanutEspService() {
    }

    public final int getReceiveIndex() {
        return receiveIndex;
    }

    public final void setReceiveIndex(int i) {
        receiveIndex = i;
    }

    public final Function1<Integer, Unit> getOnTestListener() {
        return onTestListener;
    }

    public final void setOnTestListener(Function1<? super Integer, Unit> function1) {
        onTestListener = function1;
    }

    public final boolean getPause() {
        return pause;
    }

    public final void setPause(boolean z) {
        pause = z;
    }

    public final void initEsp(Function1<? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new PeanutEspService$initEsp$1(callback, null), 3, null);
    }

    public final int sendMessage(byte[] msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        return EasyNode.INSTANCE.sendTheESP32Data(msg);
    }

    public final void setChannel(int chl) {
        EasyNode.INSTANCE.setESPChannel(chl);
    }

    public final void unInitEsp() {
        EasyNode.INSTANCE.unInitEspNode();
        receiveIndex = 0;
        pause = false;
    }

    public final void setOnEspListener(OnEspListener l) {
        mOnEspListener = l;
    }
}
