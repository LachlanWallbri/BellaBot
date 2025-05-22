package com.pudutech.mirsdk.hardware.can;

import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.base.CommonKt;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
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
final class CANBus$launchCANSendThread$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ CANBus this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CANBus$launchCANSendThread$1(CANBus cANBus) {
        super(0);
        this.this$0 = cANBus;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        String str;
        String str2;
        AtomicReference atomicReference;
        String str3;
        ArrayBlockingQueue arrayBlockingQueue;
        String str4;
        OutputStream outputStream;
        while (true) {
            try {
                arrayBlockingQueue = this.this$0.dataQueue;
                Object take = arrayBlockingQueue.take();
                Intrinsics.checkExpressionValueIsNotNull(take, "dataQueue.take()");
                byte[] storage = ((UByteArray) take).getStorage();
                byte[] copyOf = Arrays.copyOf(storage, storage.length);
                Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                str4 = this.this$0.TAG;
                Pdlog.m3273d(str4, "output to can service " + CommonKt.toHexString(copyOf));
                outputStream = this.this$0.socketOutputStream;
                if (outputStream != null) {
                    outputStream.write(copyOf);
                }
            } catch (IOException e) {
                str3 = this.this$0.TAG;
                Pdlog.m3274e(str3, "send exception:", Log.getStackTraceString(e));
                atomicReference = this.this$0.sendThread;
                atomicReference.set(null);
                return;
            } catch (InterruptedException e2) {
                str2 = this.this$0.TAG;
                Pdlog.m3274e(str2, "send exception:", Log.getStackTraceString(e2));
                atomicReference = this.this$0.sendThread;
                atomicReference.set(null);
                return;
            } catch (Exception e3) {
                str = this.this$0.TAG;
                Pdlog.m3274e(str, "send exception:", Log.getStackTraceString(e3));
                atomicReference = this.this$0.sendThread;
                atomicReference.set(null);
                return;
            }
        }
    }
}
