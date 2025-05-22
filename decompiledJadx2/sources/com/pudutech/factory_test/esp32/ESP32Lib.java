package com.pudutech.factory_test.esp32;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.pudutech.base.Pdlog;
import com.pudutech.factory_test.esp32.port.SerialUSBESP;
import com.pudutech.factory_test.esp32.port.SerialUSBListener;
import com.pudutech.lidar.port.SerialUSB;
import com.pudutech.mirsdk.hardware.base.CommonKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.TypeCastException;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;

/* compiled from: ESP32Lib.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0011\u0010\u0019\u001a\u00020\u001aH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ\u0006\u0010\u001c\u001a\u00020\u000bJ!\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010 J\u000e\u0010!\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000b\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, m3961d2 = {"Lcom/pudutech/factory_test/esp32/ESP32Lib;", "", "()V", "TAG", "", "channel", "", "getChannel", "()B", "onCheckResponse", "Lkotlin/Function0;", "", "onReceiveData", "Lkotlin/Function1;", "", "getOnReceiveData", "()Lkotlin/jvm/functions/Function1;", "setOnReceiveData", "(Lkotlin/jvm/functions/Function1;)V", "parseHandler", "Landroid/os/Handler;", "usbESP", "Lcom/pudutech/factory_test/esp32/port/SerialUSBESP;", "version", "", "checkResponse", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "close", "open", "context", "Landroid/content/Context;", "(Landroid/content/Context;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendData", "data", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class ESP32Lib {
    private Function0<Unit> onCheckResponse;
    private Function1<? super byte[], Unit> onReceiveData;
    private SerialUSBESP usbESP;
    private int version;
    private final String TAG = "ESP32Lib";
    private final byte channel = (byte) 9;
    private final Handler parseHandler = new Handler(new Handler.Callback() { // from class: com.pudutech.factory_test.esp32.ESP32Lib$parseHandler$1
        private byte[] cache = new byte[0];

        public final byte[] getCache() {
            return this.cache;
        }

        public final void setCache(byte[] bArr) {
            Intrinsics.checkParameterIsNotNull(bArr, "<set-?>");
            this.cache = bArr;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            byte[] bArr;
            Function0 function0;
            if (msg == null) {
                return false;
            }
            if (msg.what != SerialUSB.INSTANCE.getMESSAGE_FROM_SERIAL_PORT()) {
                Pdlog.m3276v(ESP32Lib.this.TAG, "no need parse data. msg.what=" + msg.what);
            } else {
                Object obj = msg.obj;
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.ByteArray");
                }
                byte[] bArr2 = (byte[]) obj;
                this.cache = ArraysKt.plus(this.cache, bArr2);
                Pdlog.m3276v(ESP32Lib.this.TAG, "cache.size=" + this.cache.length + ". receive from usb serial " + CommonKt.toHexString(bArr2));
                do {
                    int length = this.cache.length;
                    ESPProtocol eSPProtocol = new ESPProtocol();
                    this.cache = eSPProtocol.parse(this.cache);
                    if (eSPProtocol.getIsParseSuccess() && eSPProtocol.getChannel() == ESP32Lib.this.getChannel()) {
                        if (!eSPProtocol.isDataType()) {
                            function0 = ESP32Lib.this.onCheckResponse;
                            if (function0 != null) {
                            }
                        } else if (!eSPProtocol.checkCRCOK()) {
                            Pdlog.m3277w(ESP32Lib.this.TAG, "crc wrong " + CommonKt.toHexString(eSPProtocol.getCrc()) + ' ' + CommonKt.toHexString(eSPProtocol.calculateCRC()));
                        } else {
                            Function1<byte[], Unit> onReceiveData = ESP32Lib.this.getOnReceiveData();
                            if (onReceiveData != null) {
                                onReceiveData.invoke(eSPProtocol.getData());
                            }
                        }
                    }
                    bArr = this.cache;
                    if (length == bArr.length) {
                        break;
                    }
                } while (!(bArr.length == 0));
                if (this.cache.length > 500) {
                    Pdlog.m3277w(ESP32Lib.this.TAG, "too much couldn't parse. just clean all");
                    this.cache = new byte[0];
                }
            }
            return true;
        }
    });

    public static final /* synthetic */ SerialUSBESP access$getUsbESP$p(ESP32Lib eSP32Lib) {
        SerialUSBESP serialUSBESP = eSP32Lib.usbESP;
        if (serialUSBESP == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usbESP");
        }
        return serialUSBESP;
    }

    public final byte getChannel() {
        return this.channel;
    }

    public final void close() {
        SerialUSBESP serialUSBESP = this.usbESP;
        if (serialUSBESP == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usbESP");
        }
        serialUSBESP.closeUSBSerial();
    }

    public final Function1<byte[], Unit> getOnReceiveData() {
        return this.onReceiveData;
    }

    public final void setOnReceiveData(Function1<? super byte[], Unit> function1) {
        this.onReceiveData = function1;
    }

    public final void sendData(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        ESPProtocol eSPProtocol = new ESPProtocol();
        eSPProtocol.setChannel(this.channel);
        eSPProtocol.setData(data);
        eSPProtocol.m4337setDataLen7apg3OU(UByte.m4528constructorimpl((byte) data.length));
        eSPProtocol.setCrc(eSPProtocol.calculateCRC());
        SerialUSBESP serialUSBESP = this.usbESP;
        if (serialUSBESP == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usbESP");
        }
        serialUSBESP.write(eSPProtocol.toDataTypeFrame());
    }

    public final Object open(final Context context, final int i, Continuation<? super Boolean> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        if (i == 2) {
            Pdlog.m3277w(this.TAG, "open fail. not support version=2");
            Boolean boxBoolean = Boxing.boxBoolean(false);
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuationImpl2.resumeWith(Result.m4510constructorimpl(boxBoolean));
        } else {
            this.usbESP = new SerialUSBESP(context);
            this.version = i;
            access$getUsbESP$p(this).setReceiveHandler(this.parseHandler);
            access$getUsbESP$p(this).openDevice(576000, new SerialUSBListener() { // from class: com.pudutech.factory_test.esp32.ESP32Lib$open$$inlined$suspendCancellableCoroutine$lambda$1
                @Override // com.pudutech.factory_test.esp32.port.SerialUSBListener
                public void onDeviceDisConnect() {
                    Pdlog.m3273d(this.TAG, "onDeviceDisConnect ");
                }

                @Override // com.pudutech.factory_test.esp32.port.SerialUSBListener
                public void onDeviceOpen() {
                    Pdlog.m3273d(this.TAG, "onDeviceOpen ");
                    ESP32Lib eSP32Lib = this;
                    byte[] bytes = "set channel".getBytes(Charsets.UTF_8);
                    Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                    eSP32Lib.sendData(bytes);
                    CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                    Result.Companion companion2 = Result.INSTANCE;
                    cancellableContinuation.resumeWith(Result.m4510constructorimpl(true));
                }
            });
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final Object checkResponse(Continuation<? super Boolean> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        if (this.version >= 3) {
            this.onCheckResponse = new Function0<Unit>() { // from class: com.pudutech.factory_test.esp32.ESP32Lib$checkResponse$$inlined$suspendCancellableCoroutine$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.onCheckResponse = (Function0) null;
                    CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                    Result.Companion companion = Result.INSTANCE;
                    cancellableContinuation.resumeWith(Result.m4510constructorimpl(true));
                }
            };
            ESPProtocol eSPProtocol = new ESPProtocol();
            eSPProtocol.setType(ESPProtocol.INSTANCE.getTYPE_CHECK());
            eSPProtocol.setChannel(getChannel());
            eSPProtocol.m4337setDataLen7apg3OU(UByte.m4528constructorimpl((byte) 0));
            eSPProtocol.setCrc(eSPProtocol.calculateCRC());
            access$getUsbESP$p(this).write(eSPProtocol.toCheckTypeFrame());
        } else {
            Boolean boxBoolean = Boxing.boxBoolean(true);
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuationImpl2.resumeWith(Result.m4510constructorimpl(boxBoolean));
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
