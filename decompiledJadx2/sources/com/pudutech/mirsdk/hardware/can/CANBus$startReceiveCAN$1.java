package com.pudutech.mirsdk.hardware.can;

import android.util.Log;
import com.pudutech.base.Pdlog;
import java.io.InputStream;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final class CANBus$startReceiveCAN$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ byte[] $bytes;
    final /* synthetic */ CANBus this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CANBus$startReceiveCAN$1(CANBus cANBus, byte[] bArr) {
        super(0);
        this.this$0 = cANBus;
        this.$bytes = bArr;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        String str;
        AtomicReference atomicReference;
        InputStream inputStream;
        String str2;
        while (true) {
            try {
                inputStream = this.this$0.socketInputStream;
                Integer valueOf = inputStream != null ? Integer.valueOf(inputStream.read(this.$bytes, 0, 8)) : null;
                if (valueOf != null && valueOf.intValue() == 8) {
                    this.this$0.receivedCAN = true;
                    CANBus cANBus = this.this$0;
                    byte b = this.$bytes[0];
                    byte[] bArr = this.$bytes;
                    byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
                    Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                    cANBus.m4424onRecveUNIVaU(b, UByteArray.m4572constructorimpl(copyOf));
                }
                str2 = this.this$0.TAG;
                Pdlog.m3274e(str2, "recv CAN size error:" + valueOf);
            } catch (Exception e) {
                str = this.this$0.TAG;
                Pdlog.m3274e(str, "recv exception:", Log.getStackTraceString(e));
                atomicReference = this.this$0.recvThread;
                atomicReference.set(null);
                return;
            }
        }
    }
}
