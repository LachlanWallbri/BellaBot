package com.pudutech.mirsdk.hardware;

import android.content.Context;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.RFIDInterface;
import com.pudutech.rfidlib.RFIDListener;
import com.pudutech.rfidlib.XstraDynamicProtocol;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RFIDImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000?\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u000b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\tH\u0016J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0012\u0010\u0016\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/RFIDImpl;", "Lcom/pudutech/mirsdk/hardware/RFIDInterface$Stub;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "listeners", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/hardware/IRFIDData;", "myRFIDListener", "com/pudutech/mirsdk/hardware/RFIDImpl$myRFIDListener$1", "Lcom/pudutech/mirsdk/hardware/RFIDImpl$myRFIDListener$1;", "rfidInterface", "Lcom/pudutech/rfidlib/RFIDInterface;", "addRFIDListener", "", "name", "listener", "controlRFID", "open", "", "removeRFIDListener", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class RFIDImpl extends RFIDInterface.Stub {
    private final String TAG;
    private final ThreadSafeListener<IRFIDData> listeners;
    private final RFIDImpl$myRFIDListener$1 myRFIDListener;
    private final com.pudutech.rfidlib.RFIDInterface rfidInterface;

    /* JADX WARN: Type inference failed for: r3v2, types: [com.pudutech.mirsdk.hardware.RFIDImpl$myRFIDListener$1] */
    public RFIDImpl(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "RFIDImpl";
        this.rfidInterface = new com.pudutech.rfidlib.RFIDInterface(context, new XstraDynamicProtocol());
        this.listeners = new ThreadSafeListener<>();
        this.myRFIDListener = new RFIDListener() { // from class: com.pudutech.mirsdk.hardware.RFIDImpl$myRFIDListener$1
            @Override // com.pudutech.rfidlib.RFIDListener
            public void onData(final byte[] rawData) {
                ThreadSafeListener threadSafeListener;
                Intrinsics.checkParameterIsNotNull(rawData, "rawData");
                RFIDListener.DefaultImpls.onData(this, rawData);
                threadSafeListener = RFIDImpl.this.listeners;
                threadSafeListener.notify(new Function2<IRFIDData, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.RFIDImpl$myRFIDListener$1$onData$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IRFIDData iRFIDData, String str) {
                        invoke2(iRFIDData, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IRFIDData l, String str) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        l.onData(rawData);
                    }
                });
            }

            @Override // com.pudutech.rfidlib.RFIDListener
            public void onError(final int err_no) {
                ThreadSafeListener threadSafeListener;
                threadSafeListener = RFIDImpl.this.listeners;
                threadSafeListener.notify(new Function2<IRFIDData, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.RFIDImpl$myRFIDListener$1$onError$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IRFIDData iRFIDData, String str) {
                        invoke2(iRFIDData, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IRFIDData l, String str) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        l.onError(err_no);
                    }
                });
            }

            @Override // com.pudutech.rfidlib.RFIDListener
            public void onReadBlockData(final boolean success, final byte[] blockData) {
                ThreadSafeListener threadSafeListener;
                threadSafeListener = RFIDImpl.this.listeners;
                threadSafeListener.notify(new Function2<IRFIDData, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.RFIDImpl$myRFIDListener$1$onReadBlockData$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IRFIDData iRFIDData, String str) {
                        invoke2(iRFIDData, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IRFIDData l, String str) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        l.onReadBlockData(success, blockData);
                    }
                });
            }

            @Override // com.pudutech.rfidlib.RFIDListener
            public void onUID(boolean success, final byte[] cardID) {
                ThreadSafeListener threadSafeListener;
                if (success) {
                    threadSafeListener = RFIDImpl.this.listeners;
                    threadSafeListener.notify(new Function2<IRFIDData, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.RFIDImpl$myRFIDListener$1$onUID$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IRFIDData iRFIDData, String str) {
                            invoke2(iRFIDData, str);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IRFIDData l, String str) {
                            Intrinsics.checkParameterIsNotNull(l, "l");
                            Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                            l.onUID(cardID);
                        }
                    });
                }
            }

            @Override // com.pudutech.rfidlib.RFIDListener
            public void onWriteBlockData(final boolean success) {
                ThreadSafeListener threadSafeListener;
                threadSafeListener = RFIDImpl.this.listeners;
                threadSafeListener.notify(new Function2<IRFIDData, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.RFIDImpl$myRFIDListener$1$onWriteBlockData$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IRFIDData iRFIDData, String str) {
                        invoke2(iRFIDData, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IRFIDData l, String str) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        l.onWriteBlockData(success);
                    }
                });
            }
        };
    }

    @Override // com.pudutech.mirsdk.hardware.RFIDInterface
    public void addRFIDListener(String name, IRFIDData listener) {
        if (name == null || listener == null) {
            return;
        }
        this.listeners.add(name, listener);
    }

    @Override // com.pudutech.mirsdk.hardware.RFIDInterface
    public void removeRFIDListener(String name) {
        if (name != null) {
            this.listeners.remove(name);
        }
    }

    @Override // com.pudutech.mirsdk.hardware.RFIDInterface
    public void controlRFID(boolean open) {
        if (open) {
            this.rfidInterface.openRFID();
            this.rfidInterface.addRFIDListener("hardware", this.myRFIDListener);
        } else {
            this.rfidInterface.closeRFID();
            this.rfidInterface.removeRFIDListener("hardware");
        }
    }
}
