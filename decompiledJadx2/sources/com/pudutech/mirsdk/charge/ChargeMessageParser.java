package com.pudutech.mirsdk.charge;

import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.base.CommonKt;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.ThreadPoolDispatcherKt;

/* compiled from: ChargeMessageParser.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0002\u0016\u0017B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0016\u0010\u000e\u001a\n \u0010*\u0004\u0018\u00010\u000f0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/mirsdk/charge/ChargeMessageParser;", "", "mac", "", "(Ljava/lang/String;)V", "TAG", "getMac", "()Ljava/lang/String;", "messageParserListener", "Lcom/pudutech/mirsdk/charge/ChargeMessageParser$OnChargeMessageParserListener;", "getMessageParserListener", "()Lcom/pudutech/mirsdk/charge/ChargeMessageParser$OnChargeMessageParserListener;", "setMessageParserListener", "(Lcom/pudutech/mirsdk/charge/ChargeMessageParser$OnChargeMessageParserListener;)V", "recvBytesBuffer", "Ljava/nio/ByteBuffer;", "kotlin.jvm.PlatformType", "parseMessage", "", "data", "", "parseMessageByCmd", "Companion", "OnChargeMessageParserListener", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ChargeMessageParser {
    private static final ExecutorCoroutineDispatcher notifyMessageContext = ThreadPoolDispatcherKt.newSingleThreadContext("notify_charge_msg");
    private final String TAG;
    private final String mac;
    private OnChargeMessageParserListener messageParserListener;
    private final ByteBuffer recvBytesBuffer;

    /* compiled from: ChargeMessageParser.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J8\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\nH&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\u0011\u001a\u00020\u0003H&J\b\u0010\u0012\u001a\u00020\u0003H&J\b\u0010\u0013\u001a\u00020\u0003H&J\b\u0010\u0014\u001a\u00020\u0003H&J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H&¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/mirsdk/charge/ChargeMessageParser$OnChargeMessageParserListener;", "", "onChargeArrived", "", "onChargeLeave", "onChargeStartReply", "isSuccess", "", "onChargeState", AUserTrack.UTKEY_ERROR_CODE, "", "busyState", "inVol", "outVol", "current", "temp", "onChargeStopReply", "onChargeTimeout", "onCheckPileSuccess", "onCmdFail", "onRebootReply", "onVersion", "version", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public interface OnChargeMessageParserListener {
        void onChargeArrived();

        void onChargeLeave();

        void onChargeStartReply(boolean isSuccess);

        void onChargeState(int errorCode, int busyState, int inVol, int outVol, int current, int temp);

        void onChargeStopReply(boolean isSuccess);

        void onChargeTimeout();

        void onCheckPileSuccess();

        void onCmdFail();

        void onRebootReply();

        void onVersion(String version);
    }

    public ChargeMessageParser(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        this.mac = mac;
        this.TAG = "ChargeMessageParser";
        this.recvBytesBuffer = ByteBuffer.allocate(512);
    }

    public final String getMac() {
        return this.mac;
    }

    public final OnChargeMessageParserListener getMessageParserListener() {
        return this.messageParserListener;
    }

    public final void setMessageParserListener(OnChargeMessageParserListener onChargeMessageParserListener) {
        this.messageParserListener = onChargeMessageParserListener;
    }

    public final void parseMessage(byte[] data) {
        int i;
        Intrinsics.checkParameterIsNotNull(data, "data");
        Pdlog.m3273d(this.TAG, "mac:" + this.mac + " parseMessage:" + CommonKt.toHexString(data));
        ByteBuffer byteBuffer = this.recvBytesBuffer;
        byteBuffer.put(data);
        byteBuffer.flip();
        if (byteBuffer.remaining() < ChargeMessageCenter.INSTANCE.getHEAD().length) {
            byteBuffer.position(byteBuffer.limit());
            byteBuffer.limit(byteBuffer.capacity());
            return;
        }
        while (byteBuffer.remaining() >= ChargeMessageCenter.INSTANCE.getHEAD().length) {
            int i2 = 0;
            while (i2 < ChargeMessageCenter.INSTANCE.getHEAD().length && ChargeMessageCenter.INSTANCE.getHEAD()[i2] == byteBuffer.get()) {
                i2++;
            }
            if (i2 != ChargeMessageCenter.INSTANCE.getHEAD().length) {
                if (byteBuffer.hasRemaining()) {
                    byte[] bArr = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bArr);
                    byteBuffer.position(0);
                    byteBuffer.limit(byteBuffer.capacity());
                    byteBuffer.put(new byte[byteBuffer.capacity()]);
                    byteBuffer.position(0);
                    byteBuffer.limit(byteBuffer.capacity());
                    byteBuffer.put(bArr);
                    byteBuffer.flip();
                } else {
                    byteBuffer.clear();
                    return;
                }
            } else {
                if (byteBuffer.hasRemaining()) {
                    int i3 = byteBuffer.get() & 255;
                    if (i3 == 248) {
                        Pdlog.m3274e(this.TAG, "command fail");
                        if (byteBuffer.hasRemaining()) {
                            byte[] bArr2 = new byte[byteBuffer.remaining()];
                            byteBuffer.get(bArr2);
                            byteBuffer.position(0);
                            byteBuffer.limit(byteBuffer.capacity());
                            byteBuffer.put(new byte[byteBuffer.capacity()]);
                            byteBuffer.position(0);
                            byteBuffer.limit(byteBuffer.capacity());
                            byteBuffer.put(bArr2);
                            byteBuffer.flip();
                        } else {
                            byteBuffer.position(0);
                            byteBuffer.limit(byteBuffer.capacity());
                            byteBuffer.put(new byte[byteBuffer.capacity()]);
                            byteBuffer.position(0);
                            byteBuffer.clear();
                            return;
                        }
                    } else if (i3 == 192) {
                        if (byteBuffer.hasRemaining()) {
                            byteBuffer.get();
                            if (byteBuffer.hasRemaining()) {
                                byteBuffer.get();
                                if (byteBuffer.hasRemaining() && byteBuffer.remaining() > (i = byteBuffer.get() & 255)) {
                                    byte[] bArr3 = new byte[i];
                                    int position = byteBuffer.position();
                                    byteBuffer.position(0);
                                    int i4 = 0;
                                    for (int i5 = 0; byteBuffer.hasRemaining() && i5 < ChargeMessageCenter.INSTANCE.getHEAD().length + i + 4; i5++) {
                                        i4 = (i4 + (byteBuffer.get() & 255)) % 256;
                                    }
                                    byteBuffer.position(position);
                                    byteBuffer.get(bArr3);
                                    int i6 = byteBuffer.get() & 255;
                                    if (i4 == i6) {
                                        Pdlog.m3273d(this.TAG, "crc success");
                                        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, notifyMessageContext, null, new ChargeMessageParser$parseMessage$$inlined$apply$lambda$1(bArr3, null, this, data), 2, null);
                                    } else {
                                        Pdlog.m3273d(this.TAG, "crc fail " + i4 + "  realCrc" + i6);
                                    }
                                    if (byteBuffer.hasRemaining()) {
                                        byte[] bArr4 = new byte[byteBuffer.remaining()];
                                        byteBuffer.get(bArr4);
                                        byteBuffer.position(0);
                                        byteBuffer.limit(byteBuffer.capacity());
                                        byteBuffer.put(new byte[byteBuffer.capacity()]);
                                        byteBuffer.position(0);
                                        byteBuffer.limit(byteBuffer.capacity());
                                        byteBuffer.put(bArr4);
                                        byteBuffer.flip();
                                    } else {
                                        byteBuffer.position(0);
                                        byteBuffer.limit(byteBuffer.capacity());
                                        byteBuffer.put(new byte[byteBuffer.capacity()]);
                                        byteBuffer.position(0);
                                        byteBuffer.clear();
                                        return;
                                    }
                                }
                            }
                        }
                    } else if (byteBuffer.hasRemaining()) {
                        byte[] bArr5 = new byte[byteBuffer.remaining()];
                        byteBuffer.get(bArr5);
                        byteBuffer.position(0);
                        byteBuffer.limit(byteBuffer.capacity());
                        byteBuffer.put(new byte[byteBuffer.capacity()]);
                        byteBuffer.position(0);
                        byteBuffer.limit(byteBuffer.capacity());
                        byteBuffer.put(bArr5);
                        byteBuffer.flip();
                    } else {
                        byteBuffer.position(0);
                        byteBuffer.limit(byteBuffer.capacity());
                        byteBuffer.put(new byte[byteBuffer.capacity()]);
                        byteBuffer.position(0);
                        byteBuffer.clear();
                        return;
                    }
                }
                byteBuffer.position(0);
                break;
            }
        }
        byte[] bArr6 = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr6);
        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());
        byteBuffer.put(new byte[byteBuffer.capacity()]);
        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());
        byteBuffer.put(bArr6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void parseMessageByCmd(byte[] data) {
        switch (data[0]) {
            case -96:
                StringBuffer stringBuffer = new StringBuffer();
                int length = data.length;
                int i = 1;
                while (i < length) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(data[i] & 255);
                    sb.append(i != data.length - 1 ? "." : "");
                    stringBuffer.append(sb.toString());
                    i++;
                }
                Pdlog.m3273d(this.TAG, "parseMessageByCmd onVersion : " + stringBuffer);
                OnChargeMessageParserListener onChargeMessageParserListener = this.messageParserListener;
                if (onChargeMessageParserListener != null) {
                    String stringBuffer2 = stringBuffer.toString();
                    Intrinsics.checkExpressionValueIsNotNull(stringBuffer2, "versionSb.toString()");
                    onChargeMessageParserListener.onVersion(stringBuffer2);
                    return;
                }
                return;
            case -95:
                if (data.length < 11) {
                    Pdlog.m3273d(this.TAG, "cmd data fail " + CommonKt.toHexString(data));
                    return;
                }
                int i2 = (data[1] << 8) | (data[2] & 255);
                int i3 = data[3] & 255;
                int i4 = (data[4] << 8) | (data[5] & 255);
                int i5 = (data[6] << 8) | (data[7] & 255);
                int i6 = (data[8] << 8) | (data[9] & 255);
                int i7 = data[10] & 255;
                Pdlog.m3273d(this.TAG, "parseMessageByCmd onChargeState errorCode:" + i2 + " busyState:" + i3 + " inVol:" + i4 + "mV outVol:" + i5 + "mV current:" + i6 + "mA temp:" + i7);
                OnChargeMessageParserListener onChargeMessageParserListener2 = this.messageParserListener;
                if (onChargeMessageParserListener2 != null) {
                    onChargeMessageParserListener2.onChargeState(i2, i3, i4, i5, i6, i7);
                    return;
                }
                return;
            case -94:
                if (data.length < 4) {
                    Pdlog.m3273d(this.TAG, "cmd data fail " + CommonKt.toHexString(data));
                    return;
                }
                if (data[2] == ((byte) 79) && data[3] == ((byte) 75)) {
                    if (data[1] == ((byte) 1)) {
                        Pdlog.m3273d(this.TAG, "parseMessageByCmd onChargeStartReply");
                        OnChargeMessageParserListener onChargeMessageParserListener3 = this.messageParserListener;
                        if (onChargeMessageParserListener3 != null) {
                            onChargeMessageParserListener3.onChargeStartReply(true);
                            return;
                        }
                        return;
                    }
                    if (data[1] == ((byte) 2)) {
                        Pdlog.m3273d(this.TAG, "parseMessageByCmd onChargeStopReply");
                        OnChargeMessageParserListener onChargeMessageParserListener4 = this.messageParserListener;
                        if (onChargeMessageParserListener4 != null) {
                            onChargeMessageParserListener4.onChargeStopReply(true);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            case -93:
                if (data.length < 3) {
                    Pdlog.m3273d(this.TAG, "cmd data fail " + CommonKt.toHexString(data));
                    return;
                }
                if (data[1] == ((byte) 79) && data[2] == ((byte) 75)) {
                    Pdlog.m3273d(this.TAG, "parseMessageByCmd onRebootReply");
                    OnChargeMessageParserListener onChargeMessageParserListener5 = this.messageParserListener;
                    if (onChargeMessageParserListener5 != null) {
                        onChargeMessageParserListener5.onRebootReply();
                        return;
                    }
                    return;
                }
                return;
            case -92:
                if (data.length == 3) {
                    byte b = (byte) 1;
                    if (data[1] == b && data[2] == b) {
                        Pdlog.m3273d(this.TAG, "parseMessageByCmd onChargeArrived");
                        OnChargeMessageParserListener onChargeMessageParserListener6 = this.messageParserListener;
                        if (onChargeMessageParserListener6 != null) {
                            onChargeMessageParserListener6.onChargeArrived();
                            return;
                        }
                        return;
                    }
                    byte b2 = (byte) 0;
                    if (data[1] == b2 && data[2] == b2) {
                        Pdlog.m3273d(this.TAG, "parseMessageByCmd onChargeLeave");
                        OnChargeMessageParserListener onChargeMessageParserListener7 = this.messageParserListener;
                        if (onChargeMessageParserListener7 != null) {
                            onChargeMessageParserListener7.onChargeLeave();
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            case -91:
                if (data.length < 2) {
                    Pdlog.m3273d(this.TAG, "cmd data fail " + CommonKt.toHexString(data));
                    return;
                }
                if (data[1] == ((byte) 1)) {
                    Pdlog.m3273d(this.TAG, "parseMessageByCmd onChargeTimeout");
                    OnChargeMessageParserListener onChargeMessageParserListener8 = this.messageParserListener;
                    if (onChargeMessageParserListener8 != null) {
                        onChargeMessageParserListener8.onChargeTimeout();
                        return;
                    }
                    return;
                }
                return;
            case -90:
                if (data.length == 4 && data[1] == ((byte) 2) && data[2] == ((byte) 79) && data[3] == ((byte) 75)) {
                    Pdlog.m3273d(this.TAG, "parseMessageByCmd onCheckPileSuccess");
                    OnChargeMessageParserListener onChargeMessageParserListener9 = this.messageParserListener;
                    if (onChargeMessageParserListener9 != null) {
                        onChargeMessageParserListener9.onCheckPileSuccess();
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }
}
